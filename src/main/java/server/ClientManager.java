package server;

import server.data.Client;

import java.util.HashMap;
import java.util.Map;

public class ClientManager {

    private Map<Integer, Client> clients;
    private int nextId;

    public ClientManager() {
        clients = new HashMap<>();
        nextId = 1;
    }

    /**
     * Add a new client.
     * @param webSocket ServerWebSocket responsible for the connection.
     * @return the uniqueId of the Client.
     */
    public int addClient(ServerWebSocket webSocket) {
        int id = getUniqueId();
        clients.put(id, new Client(id, webSocket));
        return id;
    }

    /**
     * Get the Client with the given id.
     * @param id The unique id of the client.
     * @return The client with the given id.
     */
    public Client getClient(int id) {
        return clients.get(id);
    }

    public void removeClient(int clientId) {
        if (clients.containsKey(clientId))
            clients.remove(clientId);
    }

    /**
     * Return a unique id.
     * @return A unique id.
     */
    private int getUniqueId() {
        return nextId++;
    }

}
