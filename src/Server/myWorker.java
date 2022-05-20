package Server;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;
public class myWorker implements Runnable{
    protected Socket clientSocket = null;
    protected String serverText = null;
    public myWorker(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText = serverText;
    }
    public void run() {
        try {
            InputStream input = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();
            output.write("ciao").getBytes());
                    output.close();
            input.close();
            System.out.println("Request processed: " + time);
        } catch (IOException e) {
//report exception somewhere.
            e.printStackTrace();
        }
    }
}