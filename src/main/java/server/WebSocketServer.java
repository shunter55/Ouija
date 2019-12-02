package server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.websocket.server.WebSocketHandler;

/**
 * Creates and Starts a Jetty Web Socket Server.
 */
public class WebSocketServer {
    private ClientManager clientManager;
    private Server server;

    public WebSocketServer(int port) {
        server = new Server(port);

        clientManager = new ClientManager();

        WebSocketHandler context = new ServerHandler(clientManager);
        server.setHandler(context);
    }

    public void start() throws Exception {
        server.start();
        server.join();
    }

}
