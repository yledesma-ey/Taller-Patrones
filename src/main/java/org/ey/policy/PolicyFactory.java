package org.ey.policy;

public class PolicyFactory {
    public static Policy createPolicy(String type) {
        return switch (type.toLowerCase()) {
            case "simple" -> new SimplePolicy();
            case "complex" -> new CompletePolicy();
            default -> throw new IllegalArgumentException("Unknown policy type: " + type);
        };
    }
}
