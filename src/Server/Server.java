package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
    protected int serverPort = 8080;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;
    protected Thread runningThread= null;
    //add thread pool instead a single thread creation on demand
    //protected ThreadPoolExecutor threadPool;

    public Server(int port){
        this.serverPort = port;
    }
    public void run(){
        synchronized(this){
            this.runningThread = Thread.currentThread();
        }
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port 8080", e);
        }
        while(! isStopped()){
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if(isStopped()) {
                    System.out.println("Server Stopped.") ;
                    return;
                }
                throw new RuntimeException(
                        "Error accepting client connection", e);
            }
            try {
                /*
                this.threadPool.execute(
                        new WorkerRunnable(clientSocket, "Thread Pooled Server"));
                 */
                new Thread(
                        new WorkerRunnable(
                                clientSocket, "Multithreaded Server")
                ).start();
            } catch (Exception e) {
//log exception and go on to next request.
            }
        }
        System.out.println("Server Stopped.");
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }
    public synchronized void stop(){
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }
}