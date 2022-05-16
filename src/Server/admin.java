package Server;

import java.util.List;
import java.util.Random;

public class admin implements Runnable {

    private eventi gestione_eventi;
    private List<String> listaTestEventi;

    public admin(eventi gestione_eventi, List<String> listaTestEventi) {
        this.gestione_eventi = gestione_eventi;
        this.listaTestEventi = listaTestEventi;
    }

    public void run() {
        Random r = new Random();
        try {
            for(int i = 0; i < 10; i++){
                gestione_eventi.Crea(listaTestEventi.get(i), 3);
                Thread.sleep(r.nextInt(100));
                gestione_eventi.Aggiungi(listaTestEventi.get(i), 10);
                Thread.sleep(r.nextInt(100));
                gestione_eventi.Cancella(listaTestEventi.get(i));
            }
            //System.out.println("\nA: Stop Admin\n");
        }
        catch(InterruptedException t) {
            System.err.println("Errore thread : " + t.getMessage());
        }
    }
}