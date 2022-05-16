package Server;

import java.util.*;

import javax.sound.sampled.AudioPermission;

public class eventi {

    private List<evento> listaEventi;

    public eventi(){
        this.listaEventi = new LinkedList<evento>();
    }

    public evento getEventoRandom() {
        Random rand = new Random();
        int limit = listaEventi.size();
        if (limit == 0) return null;
        int evento = rand.nextInt(limit);

        evento temp;
        synchronized(listaEventi) {
            temp = listaEventi.get(evento);
        }
        return temp;
    }

    public void Crea(String nome, int posti) {
        evento aux = null;
        for(int i = 0; i < this.listaEventi.size(); i++) {
            aux = listaEventi.get(i);
            if(aux.getNome().equals(nome)) break;
            aux = null;
        }

        if(aux != null)
            System.out.println("A: L'Server.evento " + nome + " esiste gia, cambiare nome!");

        else {
            evento temp = new evento(nome, posti);
            this.listaEventi.add(temp);
            System.out.println("A: EVENTO " + nome + " AGGIUNTO\n");
        }
    }

    public synchronized void Aggiungi(String nome, int nuovi_posti) {
        evento aux = null;
        for(int i = 0; i < this.listaEventi.size(); i++) {
            aux = listaEventi.get(i);
            if(aux.getNome().equals(nome)) break;
            aux = null;
        }

        if(aux == null)
            System.out.println("A: L'Server.evento " + nome + " non esiste!");
        else{
            aux.addPosti(nuovi_posti);
            System.out.println("A: AGGIUNTO POSTI A " + nome);
            notifyAll();
        }
    }

    public synchronized void Prenota(String nome, int posti) {
        evento aux = null;
        for(int i = 0; i < this.listaEventi.size(); i++) {
            aux = listaEventi.get(i);
            if(aux.getNome().equals(nome)) break;
            aux = null;
        }

        if(aux.getDisponibilita(posti)) {
            aux.prenotaPosto(posti);
            System.out.println("U: PRENOTAZIONE OK PER: " + nome);
            return;
        }

        else {

            boolean disp = false;
            while (!aux.getDisponibilita(posti)) {
                disp = false;
                try{
                    System.out.println("U: ASPETTO PER: " + nome);
                    this.wait();
                    for(int i = 0; i < this.listaEventi.size(); i++) {
                        aux = listaEventi.get(i);
                        if(aux.getNome().equals(nome)) break;
                        aux = null;
                    }
                }
                catch(InterruptedException t) {
                    System.err.println("U: Errore wait : " + t.getMessage());
                }
                if(aux == null){
                    System.out.println("U: L'EVENTO CHE ASPETTAVO E' STATO CANCELLATO");
                    //notifyAll();
                    return;
                }
                disp = true;
            }

            if(disp) {
                aux.prenotaPosto(posti);
                System.out.println("U: PRENOTAZIONE OK PER: " + nome);
                return;
            }

        }
    }

    public synchronized void Cancella(String nome) {
        for(int i = 0; i < this.listaEventi.size(); i++) {
            evento aux = listaEventi.get(i);
            if(aux.getNome().equals(nome)) {
                this.listaEventi.remove(i);
                System.out.println("A: EVENTO " + nome + " CANCELLATO");
                break;
            }
        }

        notifyAll();
    }

    public void ListaEventi() {
        if(listaEventi.size() == 0)
            System.out.println("\nNessun Server.evento disponibile\n");
        else{
            for(int i = 0; i < this.listaEventi.size(); i++) {
                evento aux = listaEventi.get(i);
                System.out.println("Evento: " + aux.getNome() + " - Posti totali: " + aux.getPostiTotali() + " - Posti disponibili: " + aux.getPostiRimasti() + "\n");
            }
        }
    }
}