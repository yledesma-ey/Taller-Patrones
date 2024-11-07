package org.ey.utils;

import java.util.List;
import java.util.Map;

/**
 * Expone los metodos de manipulacion de archivos, ocultando la implementación
 */
public class UtilsProxy {

    /**Lee json de políticas, La estructura del json se refleja en el elemento devuelto.
     * EL value del Map puede ser String o List(en el caso del elemento "eventos")
     */
    public static List<Map<String, Object>> readPolicies() {
        return JsonPolicyReader.readJson();
    }

    public static List<Map<String, Object>> readPolicies(String path) {
        return JsonPolicyReader.readJson(path);
    }

    /**Lee archivo de movimientos de cartera, cada movimiento es un map de sus 5 elementos
     */
    public static  List<Map<String, String>> readMovements(){
        return MovementsReader.readMovements();
    }

    public static  List<Map<String, String>> readMovements(String path){
        return MovementsReader.readMovements(path);
    }
}
