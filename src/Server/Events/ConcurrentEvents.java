package Server.Events;

import java.util.concurrent.ConcurrentHashMap;
public class ConcurrentEvents implements IEvents {
    private final ConcurrentHashMap<String,Integer> events;

    public ConcurrentEvents(){

        events = new ConcurrentHashMap<>();
    }

    @Override
    public void Crea(String name, int seats) {
        if(name == null) throw new NullPointerException();

        if(seats<=0) return;

        events.putIfAbsent(name,seats);
    }

    @Override
    public boolean Prenota(String name, int seats) {
        if(name == null) throw new NullPointerException();
        
        if(seats<=0) return false;
        
        synchronized (events){
            while(events.get(name) != null && events.get(name) < seats){
                try {
                    events.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            return events.computeIfPresent(name, (key, value) -> value - seats) != null;
        }
    }

    @Override
    public void Aggiungi(String name, int seats) {
        if(name == null) throw new NullPointerException();

        if(seats<=0) return;

        synchronized (events) {
            events.computeIfPresent(name, (key, value) -> value + seats);
            events.notifyAll();
        }
    }

    @Override
    public void Cancella(String name) {
        if(name == null)
            throw new NullPointerException();

        events.remove(name);
    }
    @Override
    public String listaEventi() {
        String ris = events.toString();
        return ris;
    }


}
