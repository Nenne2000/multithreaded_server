package Server;

import Events.events_manager;

import java.util.List;
import java.util.Random;

public class admin implements Runnable {

    private final events_manager manager;
    private final List<String> eventList;

    public admin(events_manager manager, List<String> eventList) {
        this.manager = manager;
        this.eventList = eventList;
    }

    public void run() {
        Random r = new Random();
        try {
            for(int i = 0; i < 10; i++){
                manager.createEvent(eventList.get(i), 3);
                Thread.sleep(r.nextInt(100));
                manager.addEvent(eventList.get(i), 10);
                Thread.sleep(r.nextInt(100));
                manager.DeleteEvent(eventList.get(i));
            }
            //System.out.println("\nA: Stop Admin\n");
        }
        catch(InterruptedException t) {
            System.err.println("Thread error: " + t.getMessage());
        }
    }
}