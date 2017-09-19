package fish.payara.demo.BakingJavaEE8MicroPi;

import fish.payara.micro.cdi.ClusteredCDIEventBus;
import fish.payara.micro.cdi.Inbound;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.websocket.Session;
import java.io.IOException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Mike Croft
 */
@ApplicationScoped
public class StockSessionManager {

    private HashSet<Session> sessions;

    @Inject
    private ClusteredCDIEventBus bus;

    @PostConstruct
    private void postConstruct() {
        sessions = new HashSet<>();
    }

    /**
     * Register the session to our set of Sessions
     * @param session The session to register
     */
    void registerSession(Session session) {
        sessions.add(session);
    }

    /**
     * Deregisters the session from our set of Sessions
     * @param session The session to deregister
     */
    void deregisterSession(Session session) {
        sessions.remove(session);
    }

    /**
     * Listens for CDI Events of type Stock on the CDI Event Bus
     * @param stock A Stock object from the CDI Event Bus
     */
    private void observer(@Observes @Inbound Stock stock) {
        System.out.println("received stock event");
        try {
            for (Session session : sessions) {
                System.out.println("Received " + stock.toString() + " writing to " + session.getId());
                session.getBasicRemote().sendText(stock.toString());
            }
        } catch (IOException ex) {
            Logger.getLogger(StockSessionManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}