package org.ey.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.*;

class JsonPolicyReader {
    /**
     * <pre>
     * Retorna un lista, donde cada elemento es un json de policy.
     * Cada elemento json es devuelto como Map, donde la key es la etiqueta del dato, y el value es el valor
     * del String o una lista de String, habrá que evaluar en cada caso.
     *
     * Se ignoran los valores null o no presentes.
     * Ej:
     * "[]" -> Mapa vacío
     * "[{"foo":"bar"}]" -> Mapa[("foo", "bar")]
     * "[
     *   {
     *    "foo": "bar",
     *    "foo2": ["bar2", "bar3"],
     *    }
     *  ]"  ->   Mapa[ ("foo", "bar") ; ("foo2", Lista[("bar2"); ("bar3")]) ]
     *</pre>
     */
    static List<Map<String, Object>> readJson(String path){
        String text = StringFileReader.getFileAsString(path);
        var parser = new JSONParser();
        String fallbackArray = "[]";
        JSONArray jsonFallbackArray = null;
        try {
            jsonFallbackArray = (JSONArray) parser.parse(fallbackArray);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        List<Map<String, Object>> result = new ArrayList<>();

        try {
            JSONArray jsonArray = (JSONArray) parser.parse(text);
            for (Object json : jsonArray){
                Map<String, Object> map = new HashMap<>();
                var jsonObject = (JSONObject) json;
                Optional.ofNullable(jsonObject.get("field"))
                        .ifPresent(o -> map.put("field", o));
                Optional.ofNullable(jsonObject.get("comparator"))
                        .ifPresent(o -> map.put("comparator", o));
                Optional.ofNullable(jsonObject.get("compareToValue"))
                        .ifPresent(o -> map.put("compareToValue", o));
                Optional.ofNullable(jsonObject.get("compareToField"))
                        .ifPresent(o -> map.put("compareToField", o));
                Optional.ofNullable(jsonObject.get("operator"))
                        .ifPresent(o -> map.put("operator", o));
                JSONArray events = Optional.ofNullable(jsonObject.get("events"))
                        .map(o -> (JSONArray)o).orElse(jsonFallbackArray);

                List<String> eventsList = new ArrayList<>();
                for (Object o : events) {
                    eventsList.add((String) o);
                }

                if(!eventsList.isEmpty()) map.put("events",eventsList);
                result.add(map);
            }

        } catch (ParseException pe) {
            System.out.println("position: " + pe.getPosition());
            System.out.println(pe);
            throw new RuntimeException("Json inválido - " + "position: " + pe.getPosition() + " - " + pe);
        }
        return result;
    }

    static List<Map<String, Object>> readJson() {
        return readJson("src/main/resources/policies.json");
    }
}
