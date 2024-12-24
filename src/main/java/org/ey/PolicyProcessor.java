package org.ey;

import org.ey.dao.PortfolioDAO;
import org.ey.enums.PortfolioStatus;
import org.ey.enums.ResolutionEvent;
import org.ey.policy.PolicyFactory;
import org.ey.policy.Policy;
import org.ey.portfolio.*;

import java.util.ArrayList;
import java.util.Collections;
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
        for (Map<String, String> movement : movements) {
            System.out.println("Movimiento: " + movement);
            List<ResolutionEvent> allEvents = new ArrayList<>(ResolutionEvent.getAllEventsForProcess());

            String carteraId = movement.get("carteraId");

            for (Map<String, Object> policyData : policies) {
                Policy policy = PolicyFactory.createPolicy(policyData);
                policy.apply(Collections.singletonList(movement), policyData, allEvents);
            }

            if (!allEvents.isEmpty()) {
                String eventToApply = allEvents.get(0).name();
                System.out.println("Evento a aplicar: " + eventToApply);
                ResolutionEvent resultEvent = ResolutionEvent.valueOf(eventToApply);

                // Obtiene el estado actual del portafolio desde el DAO
                PortfolioStatus currentStatus = dao.getPortfolioStatus(Long.parseLong(carteraId));

                // Obtiene la instancia de PortfolioState correspondiente
                PortfolioState currentState = PortfolioStateFactory.getState(currentStatus);

                // Calcula el siguiente estado
                PortfolioStatus nextStatus = currentState.nextState(resultEvent);

                // Guarda el nuevo estado en la base de datos
                dao.savePortfolioStatus(Long.valueOf(carteraId), nextStatus);
            }

        }
    }
}
