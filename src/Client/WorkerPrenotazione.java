package Client;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class WorkerPrenotazione extends SwingWorker<String, Integer> {
    private final GUI gui;
    private String result;
    public WorkerPrenotazione(GUI gui) {

        this.gui = gui;
    }

    @Override
    protected String doInBackground() throws Exception {

        try (Socket socket = new Socket("localhost", 8080)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line = gui.event_name.getText() + "-" + gui.seats.getText();
            out.println(line);
            out.flush();
            result = in.readLine();
        }

        return result;
    }

    @Override
    protected void done() {
        gui.event_name.setText("");
        gui.seats.setText("");
        gui.result.setVisible(true);
        gui.result.setText(result);
        gui.booking_button.setEnabled(true);
        gui.print_button.setEnabled(true);
    }
}
