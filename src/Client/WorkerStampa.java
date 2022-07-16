package Client;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class WorkerStampa extends SwingWorker<String, Integer> {
    private final GUI gui;
    String result;
    List<String> eventList = new LinkedList<>();
    public WorkerStampa(GUI gui) {

        this.gui = gui;
    }

    @Override
    protected String doInBackground() throws Exception {
        try (Socket socket = new Socket("localhost", 8080)) {

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.println("lista_eventi");
            out.flush();

            /*GESTISCO LISTA DA STAMPARE*/
            result = in.readLine();
            //Levo le parentesi iniziali e finali e cambio il tipo di format
            result = result.replace("="," - Available seats: ");
            result = result.replace("{","");
            result = result.replace("}","");
            //Divido in  celle
            eventList.addAll(Arrays.asList(result.split(",")));
        }
        return null;
    }
    @Override
    protected void done() {
        if(eventList.isEmpty()) {
            return;
        }

        System.out.println(result);
        gui.all_events.removeAllElements();
        gui.all_events.addAll(eventList);

        gui.events_list.validate();
        gui.booking_button.setEnabled(true);
        gui.print_button.setEnabled(true);
    }
}
