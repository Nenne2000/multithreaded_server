package Server;

import Server.Events.IEvents;

import java.util.Random;

public class Admin implements Runnable{
    private final IEvents gestione_eventi;
    public Admin(IEvents events){

        this.gestione_eventi = events;
    }
    @Override
    public void run() {
        Random r = new Random();
        try {
            for(int i = 0; i < 50; i++){
                gestione_eventi.Crea("evento"  + i, 10);
                Thread.sleep(r.nextInt(100));
                gestione_eventi.Aggiungi("evento"  + i, 10);
                Thread.sleep(r.nextInt(100));
                //gestione_eventi.Cancella("evento"  + i);
            }
            //System.out.println("\nA: Stop Admin\n");
        }
        catch(InterruptedException t) {
            System.err.println("Errore thread : " + t.getMessage());
        }
    }
}
