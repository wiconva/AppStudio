package app.WS;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class WSController {
    public static void main(String[] args) {
        try {
            HttpServer servidor = HttpServer.create(new InetSocketAddress(51000),0);

            servidor.createContext("/WS/getClients", WSClient::getAllClient);
            servidor.createContext("/WS/getClient",WSClient::getClient);
            servidor.createContext("/WS/createClient", WSClient::createClient);
            servidor.start();

        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }
}
