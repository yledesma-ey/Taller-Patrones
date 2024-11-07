package org.ey;

import org.ey.dao.PortfolioDAO;
import org.ey.enums.PortfolioStatus;

import java.util.List;
import java.util.Map;

public class PolicyProcessor {
    final PortfolioDAO dao;
    boolean useSimplePolicies;

    public PolicyProcessor(final PortfolioDAO dao, boolean useSimplePolicies){
        this.dao = dao;
        this.useSimplePolicies = useSimplePolicies;
    }

    public final PortfolioDAO getDao() {
        return dao;
    }

    public void setUseSimplePolicies(boolean flag) {
        this.useSimplePolicies = flag;
    }

    // ### EJEMPLO. EN LOS TEST SE USARÁ EL METODO "process" ###
    public void processEjemplo(List<Map<String, Object>> policies, List<Map<String, String>> movements){
        System.out.println(movements);
        System.out.println(policies);

        movements.forEach(
                movement -> {
                    var id = movement.get("carteraId");
                    var oldStatus = dao.getPortfolioStatus(Long.parseLong(id));
                    var newHardcodedStatus =  PortfolioStatus.VIP;
                    //Ejemplo. El estado nuevo debe ser el resultado de procesar reglas de políticas.
                    System.out.println("Cartera Id: " + id +
                            ", Status Viejo: " + oldStatus + ", Status Nuevo: " + newHardcodedStatus);
                }
        );

    }

    public void process(List<Map<String, Object>> policies, List<Map<String, String>> movements){
        // TODO COMPLETAR
    }
}
