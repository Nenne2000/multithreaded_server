package Events;

public class event {

    private final String name;
    private int eventSeats;
    private int reservedSeats;


    public event(String name, int eventSeats) {
        this.name = name;
        this.eventSeats = eventSeats;
        this.reservedSeats = 0;
    }

    public String getName() {
        return this.name;
    }

    public int getTotalSeats() {
        return this.eventSeats;
    }

    public int getLeftSeats() {
        return (eventSeats - reservedSeats);
    }

    public boolean getAvailability(int seats) {
        return ((eventSeats - reservedSeats) > seats);
    }

    public void addSeats(int newSeats) {
        this.eventSeats += newSeats;
    }

    public void bookSeats(int seats) {
        this.reservedSeats = reservedSeats + seats;
    }
}