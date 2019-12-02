package game;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import server.ClientManager;
import server.ServerHandler;
import server.WebSocketServer;
import server.data.Client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Ouija extends Game {

    private WebSocketServer server;
    private int time = 0;

    public Ouija(WebSocketServer server) {
        super(1000);
        this.server = server;
    }

    @Override
    void loop() {
        for (Client client : server.clientManager.clients()) {
            System.out.println("sending to client: " + client.id);
            client.webSocket.send("time: " + time++ + "<br />");
        }
    }

    public static void main(String[] args) throws Exception {
        WebSocketServer server = new WebSocketServer(8080);
        System.out.println("a");
        Game ouija = new Ouija(server);
        System.out.println("b");
        ouija.start();
        System.out.println("c");
        server.start();
    }

}
