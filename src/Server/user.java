package Server;

import Events.event;
import Events.events_manager;

import java.util.List;

public class user implements Runnable {

    private final events_manager manager;
    private final List<String> eventList;

    public user(events_manager manager, List<String> eventList) {
        this.manager = manager;
        this.eventList = eventList;
    }

    public void run() {
        for(int i = 0; i < 10; i++){
            try {
                Thread.sleep(100);
                event temp = manager.getRandomEvent();
                if(temp == null) {
                    System.out.println("U: No events available for booking");
                    return;
                }
                manager.bookSeats(temp.getName(), 5);
            }
            catch(InterruptedException t) {
                System.err.println("Thread error: " + t.getMessage());
            }
        }
    }

}