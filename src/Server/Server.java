package Server;

import Server.Events.IEvents;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

public class Server {
    static int portNumber = 8080;
    public static void ServerStart(IEvents events_database) {

        var pool = Executors.newCachedThreadPool();
        ServerSocket server = null;

        try {
            server = new ServerSocket(portNumber);
            server.setReuseAddress(true);
            while (true) {
                Socket client = server.accept();

                pool.execute(new RequestHandler(client,events_database));
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            if (server != null) {
                pool.shutdown();
                try {
                    server.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
