package org.ey.policy;

import org.ey.enums.ResolutionEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimplePolicy implements Policy {
    @Override
    public void apply(List<Map<String, String>> movements, Map<String, Object> policyData, List<ResolutionEvent> allEvents) {
        List<String> applicableEvents = new ArrayList<>();
        String comparator = ((String) policyData.get("comparator")).trim();
        String compareToValue = (String) policyData.get("compareToValue");
        List<String> events = (List<String>) policyData.get("events");

        for (Map<String, String> movement : movements) {
            double policyValue = Double.parseDouble(compareToValue);
            double amount = Double.parseDouble(movement.get("amount"));

            boolean isConditionMet = switch (comparator) {
                case "greater_than" -> amount > policyValue;
                case "greater_or_equal" -> amount >= policyValue;
                case "less_or_equal" -> amount <= policyValue;
                default -> false;
            };

            if (isConditionMet) {
                for (String event : events) {
                    allEvents.removeIf(e -> e.name().equals(event));
                }
               // applicableEvents.addAll(events);
            }
        }

       // return applicableEvents;
    }
}
