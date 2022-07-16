package Server;

import Server.Events.IEvents;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RequestHandler implements Runnable {

        private final Socket openSocket;
        private final IEvents database_eventi;

        public RequestHandler(Socket socket, IEvents eventList) {
            this.database_eventi = eventList;
            this.openSocket = socket;
        }

        public void run() {
            PrintWriter out = null;
            BufferedReader in = null;
            try {
                out = new PrintWriter(openSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(openSocket.getInputStream()));

                String line;
                while ((line = in.readLine()) != null) {
                    if ("lista_eventi".equals(line)) out.println(database_eventi.listaEventi());
                    else {
                        var parsed_req = line.split("-");
                        //System.out.println(parsed_req[0] + " e " + parsed_req[1]);
                        if (database_eventi.Prenota(parsed_req[0], Integer.valueOf(parsed_req[1]))) out.println("PRENOTAZIONE OK");

                        else out.println("ERRORE PRENOTAZIONE");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            finally {
                try {
                    out.close();
                    in.close();
                    openSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
}
