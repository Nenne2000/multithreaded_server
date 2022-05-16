package Server;

import Server.eventi;
import Server.evento;

import java.util.List;

public class user implements Runnable {

    private eventi gestione_eventi;
    private List<String> listaTestEventi;

    public user(eventi gestione_eventi, List<String> listaTestEventi) {
        this.gestione_eventi = gestione_eventi;
        this.listaTestEventi = listaTestEventi;
    }

    public void run() {
        for(int i = 0; i < 10; i++){
            try {
                Thread.sleep(100);
                evento temp = gestione_eventi.getEventoRandom();
                if(temp == null) {
                    System.out.println("U: Non ci sono Server.eventi da prenotare");
                    return;
                }
                gestione_eventi.Prenota(temp.getNome(), 5);
            }
            catch(InterruptedException t) {
                System.err.println("Errore thread : " + t.getMessage());
            }
        }
    }

}