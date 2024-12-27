package org.ey.policy;

import org.ey.comparator.ComparatorCommand;
import org.ey.comparator.ComparatorCommandRegistry;
import org.ey.enums.ResolutionEvent;

import java.util.List;
import java.util.Map;

public class CompletePolicy implements Policy {

    @Override
    public void apply(List<Map<String, String>> movements, Map<String, Object> policyData, List<ResolutionEvent> allEvents) {
        String field = (String) policyData.get("field");
        String operator = (String) policyData.get("operator");
        String compareToValue = (String) policyData.get("compareToValue");
        String comparator = (String) policyData.get("comparator");
        List<String> events = (List<String>) policyData.get("events");

        // Verificar si el campo "isForeign" es TRUE
        boolean isForeign = "TRUE".equalsIgnoreCase((String) policyData.get("isForeign"));
        if (isForeign) {
            events.clear();
            events.add("EXTREME_RISK");
            events.add("BEAR");
        }

        for (Map<String, String> movement : movements) {
            String value = movement.get(field);
            boolean isConditionMet = evaluateCondition(value, comparator, compareToValue.toUpperCase());

            if (isConditionMet) {
                switch (operator) {
                    case "NOT" -> {
                        // Elimina los eventos de allEvents
                        for (String event : events) {
                            allEvents.removeIf(e -> e.name().equals(event));
                        }
                    }
                    case "RETURN" -> {
                        // Reemplaza allEvents con los eventos dados y termina
                        allEvents.clear();
                        for (String event : events) {
                            allEvents.add(ResolutionEvent.valueOf(event));
                        }
                        return; // Detiene la evaluación adicional
                    }
                    case "ONLY" -> {
                        // Deja solo los eventos dados en allEvents
                        allEvents.clear(); // Limpia la lista allEvents existente
                        allEvents.addAll(events.stream()
                                .map(ResolutionEvent::valueOf)
                                .toList());
                    }
                    default -> {
                        // Agrega los eventos a allEvents (por defecto)
                        for (String event : events) {
                            if (allEvents.stream().noneMatch(e -> e.name().equals(event))) {
                                allEvents.add(ResolutionEvent.valueOf(event));
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean evaluateCondition(String value, String comparator, String compareToValue) {
        ComparatorCommand command = ComparatorCommandRegistry.getCommand(comparator);
        return command.execute(value, compareToValue);
    }
}
