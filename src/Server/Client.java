package Server;

import Events.events_manager;

import java.util.LinkedList;
import java.util.List;

public class Client {
    public static void main(String[] args) {

        List<String> eventList = new LinkedList<>();

        String randomString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i = 0; i < 200; i++) {
            StringBuilder eventName = new StringBuilder(10);
            for(int j = 0; j < 10; j++) {
                int index = (int)(randomString.length() * Math.random());
                eventName.append(randomString.charAt(index));
            }
            eventList.add(eventName.toString());
        }

        events_manager manager = new events_manager();

        Thread ADMIN = new Thread(new admin(manager, eventList));
        Thread USER = new Thread(new user(manager, eventList));

        ADMIN.start();
        USER.start();

        try{
            ADMIN.join();
            USER.join();
        }
        catch(InterruptedException t) {
            System.err.println("Thread error: " + t.getMessage());
        }

        manager.printEventList();

    }
}
