package org.ey.policy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SimplePolicy implements Policy {
    @Override
    public List<String> apply(List<Map<String, String>> movements, Map<String, Object> policyData) {
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
                applicableEvents.addAll(events);
            }
        }

        return applicableEvents;
    }
}
