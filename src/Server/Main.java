package Server;

import Server.Events.ConcurrentEvents;
import Server.Events.IEvents;


public class Main {
    public static void main(String []args){

        IEvents database_eventi = new ConcurrentEvents();

        //new Thread(new Admin(database_eventi)).start();
        Thread ADMIN = new Thread(new Admin(database_eventi));
        ADMIN.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Server.ServerStart(database_eventi);
            }
        }).start();
    }
}
