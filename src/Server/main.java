package Server;

import Server.admin;
import Server.eventi;

import java.util.LinkedList;
import java.util.List;

public class main {
    public static void main(String[] args) {

        List<String> listaEventi = new LinkedList<String>();

        String randomString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i = 0; i < 200; i++) {
            StringBuilder nomeEvento = new StringBuilder(10);
            for(int j = 0; j < 10; j++) {
                int index = (int)(randomString.length() * Math.random());
                nomeEvento.append(randomString.charAt(index));
            }
            listaEventi.add(nomeEvento.toString());
        }

        eventi gestione_eventi = new eventi();

        Thread ADMIN = new Thread(new admin(gestione_eventi, listaEventi));
        Thread CLIENTE = new Thread(new user(gestione_eventi, listaEventi));

        ADMIN.start();
        CLIENTE.start();

        try{
            ADMIN.join();
            CLIENTE.join();
        }
        catch(InterruptedException t) {
            System.err.println("Errore thread : " + t.getMessage());
        }

        gestione_eventi.ListaEventi();

    }
}
