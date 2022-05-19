package Events;

public interface IEvents {
    event getRandomEvent();
    void createEvent(String name, int seats);
    void addEvent(String name, int newSeats);
    void bookSeats(String name, int seats);
    void DeleteEvent(String nome);
    void printEventList();
}
