package server.data;

import org.eclipse.jetty.websocket.api.Session;
import server.ServerWebSocket;

public class Client {

    public int id;
    public ServerWebSocket webSocket;

    public Client(int id, ServerWebSocket webSocket) {
        this.id = id;
        this.webSocket = webSocket;
    }

}
