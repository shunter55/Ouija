package server;

import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

/**
 * Jetty Server create a Handler.
 */
public class ServerHandler extends WebSocketHandler {

    /**
     * Jetty Server create a WebSocket.
     */
    private class ServerCreator implements WebSocketCreator {
        private ClientManager clientManager;

        public ServerCreator(ClientManager clientManager) {
            this.clientManager = clientManager;
        }

        @Override
        public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp) {
            return new ServerWebSocketImpl(clientManager);
        }
    }

    private ClientManager clientManager;

    public ServerHandler(ClientManager clientManager) {
        this.clientManager = clientManager;
    }

    @Override
    public void configure(WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(10000);
        factory.setCreator(new ServerCreator(clientManager));
    }

}


