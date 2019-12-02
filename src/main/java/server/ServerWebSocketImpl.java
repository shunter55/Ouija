package server;

import java.io.IOException;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public class ServerWebSocketImpl implements ServerWebSocket {

    private ClientManager clientManager;

    private int clientId = -1;

    public ServerWebSocketImpl(ClientManager clientManager) {
        this.clientManager = clientManager;
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        System.out.println("Close: " + reason);
        clientManager.removeClient(clientId);
    }

    @OnWebSocketError
    public void onError(Throwable t) {
        System.out.println("Error: " + t.getMessage());
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        System.out.println("Connect: " + session.getRemoteAddress().getAddress() + " - " + session.getLocalAddress());
        clientId = clientManager.addClient(this);

        try {
            session.getRemote().sendString("You are connected as client: " + clientId);
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
    }

    @OnWebSocketMessage
    public void onMessage(String message) {
        System.out.println("Message: " + message);
    }

}
