package org.ey.policy;

import java.util.Map;

public class PolicyFactory {
    public static Policy createPolicy(Map<String, Object> policyData) {
        boolean isComplex = policyData.containsKey("field") && policyData.containsKey("operator");
        return isComplex ? new CompletePolicy() : new SimplePolicy();
    }
}
