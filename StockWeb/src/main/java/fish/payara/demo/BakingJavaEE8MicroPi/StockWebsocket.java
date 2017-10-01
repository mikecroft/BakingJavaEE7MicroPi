package fish.payara.demo.BakingJavaEE8MicroPi;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * @author Mike Croft
 */
@ServerEndpoint("/graph")
public class StockWebsocket {

    @Inject
    StockSessionManager sessionManager;

    private Session mySession;

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Opened Session" + session.getId());
        mySession = session;
        sessionManager.registerSession(session);
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("Closed Session" + session.getId());
        sessionManager.deregisterSession(session);
    }

    @OnMessage
    public String onMessage(String message, Session session) {
        return null;
    }


    @OnError
    public void onError(Throwable t) {
        System.out.println("Error ");
        t.printStackTrace();
        sessionManager.deregisterSession(mySession);
    }

}
