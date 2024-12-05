package org.ey.policy;

import org.ey.enums.ResolutionEvent;

import java.util.List;
import java.util.Map;

public interface Policy {

   void apply(List<Map<String, String>> movements, Map<String, Object> policyData, List<ResolutionEvent> allEvents);

}
