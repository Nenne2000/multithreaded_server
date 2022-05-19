package Events;

import java.util.*;


public class events_manager {

    private final List<event> eventList;

    public events_manager(){
        this.eventList = new LinkedList<>();
    }

    public event getRandomEvent() {
        Random rand = new Random();
        int limit = eventList.size();
        if (limit == 0) return null;
        int event = rand.nextInt(limit);

        event temp;
        synchronized(eventList) {
            temp = eventList.get(event);
        }
        return temp;
    }

    public void createEvent(String name, int seats) {
        event aux = null;
        for (event e : this.eventList) {
            aux = e;
            if (aux.getName().equals(name)) break;
            aux = null;
        }

        if(aux != null)
            System.out.println("A: event " + name + " already exist, change the name!");

        else {
            event temp = new event(name, seats);
            this.eventList.add(temp);
            System.out.println("A: event " + name + " added\n");
        }
    }

    public synchronized void addEvent(String name, int newSeats) {
        event aux = null;
        for (event e : this.eventList) {
            aux = e;
            if (aux.getName().equals(name)) break;
            aux = null;
        }

        if(aux == null)
            System.out.println("A: event " + name + " doesn't exist!");
        else{
            aux.addSeats(newSeats);
            System.out.println("A: added seats to " + name);
            notifyAll();
        }
    }

    public synchronized void bookSeats(String name, int seats) {
        event aux = null;
        for (event e : this.eventList) {
            aux = e;
            if (aux.getName().equals(name)) break;
            aux = null;
        }

        if(aux.getAvailability(seats)) {
            aux.bookSeats(seats);
            System.out.println("U: booking ok for: " + name);
            return;
        }

        else {

            boolean availability = false;
            while (!aux.getAvailability(seats)) {
                availability = false;
                try{
                    System.out.println("U: waiting for: " + name);
                    this.wait();
                    for (event e : this.eventList) {
                        aux = e;
                        if (aux.getName().equals(name)) break;
                        aux = null;
                    }
                }
                catch(InterruptedException t) {
                    System.err.println("U: wait error: " + t.getMessage());
                }
                if(aux == null){
                    System.out.println("U: the event was canceled");
                    //notifyAll();
                    return;
                }
                availability = true;
            }

            if(availability) {
                aux.bookSeats(seats);
                System.out.println("U: booking ok for: " + name);
                return;
            }

        }
    }

    public synchronized void DeleteEvent(String nome) {
        for(int i = 0; i < this.eventList.size(); i++) {
            event aux = eventList.get(i);
            if(aux.getName().equals(nome)) {
                this.eventList.remove(i);
                System.out.println("A: event " + nome + " deleted");
                break;
            }
        }

        notifyAll();
    }

    public void printEventList() {
        if(eventList.size() == 0)
            System.out.println("\n no events available \n");
        else{
            for (event e : this.eventList) {
                System.out.println("event: " + e.getName() + " - total seats: " + e.getTotalSeats() + " - available seats: " + e.getLeftSeats() + "\n");
            }
        }
    }
}