package Server;

public class evento {

    private String nome;
    private int posti_totali;
    private int posti_prenotati;


    public evento(String nome, int posti_totali) {
        this.nome = nome;
        this.posti_totali = posti_totali;
        this.posti_prenotati = 0;
    }

    public String getNome() {
        return this.nome;
    }

    public int getPostiTotali() {
        return this.posti_totali;
    }

    public int getPostiRimasti() {
        return (posti_totali - posti_prenotati);
    }

    public boolean getDisponibilita(int posti) {
        return ((posti_totali - posti_prenotati) > posti);
    }

    public void addPosti(int nuovi_posti) {
        this.posti_totali += nuovi_posti;
    }

    public void prenotaPosto(int posti) {
        this.posti_prenotati = posti_prenotati+posti;
    }
}