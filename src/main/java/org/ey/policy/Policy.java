package org.ey.policy;

import java.util.List;
import java.util.Map;

public interface Policy {

    List<String> apply(List<Map<String, String>> movements, Map<String, Object> policyData);

}
