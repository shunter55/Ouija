package server.data;

import server.ServerWebSocket;

public class Client {

    public int id;
    public ServerWebSocket webSocket;

    public Client(int id, ServerWebSocket webSocket) {
        this.id = id;
        this.webSocket = webSocket;
    }

}
