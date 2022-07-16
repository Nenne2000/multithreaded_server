package Server.Events;

import java.io.PrintWriter;

public interface IEvents {
    void Crea(String name, int seats);
    boolean Prenota(String name, int seats);
    void Aggiungi(String name,int seats);
    void Cancella(String name);
    String listaEventi();

}
