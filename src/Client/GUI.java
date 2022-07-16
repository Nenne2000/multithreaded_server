package Client;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class GUI extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
     JButton booking_button, print_button;
     JTextField event_name,seats;
     JLabel name_label, seats_label, result_label, result;
     DefaultListModel<String> all_events;
     JList events_list;

    public GUI() {
        super("Booking Station");

        /*SEZIONE PULSANTI*/
        booking_button = new JButton("Book Now!");
        print_button = new JButton("See open events");

        MyListener2 prenota_handler  = new MyListener2(this);
        booking_button.addActionListener(prenota_handler);

        MyListener1 stampa_handler = new MyListener1(this);
        print_button.addActionListener(stampa_handler);

        /*SEZIONE CASELLE DI TESTO*/
        name_label = new JLabel("Event name:");
        event_name = new JTextField("");
        event_name.setPreferredSize(new Dimension(100,35));

        seats_label = new JLabel("Seats:");
        seats = new JTextField("");
        seats.setPreferredSize(new Dimension(30,35));

        /*SEZIONE RISPOSTA SERVER*/
        result_label = new JLabel("Request status:");
        result = new JLabel("");
        //Stampa lista eventi
        all_events = new DefaultListModel<>();
        events_list = new JList(all_events);

        /*PANNELLO PRINCIPALE GUI*/
        JPanel base_panel = new JPanel();
        base_panel.add(name_label);
        base_panel.add(event_name);
        base_panel.add(seats_label);
        base_panel.add(seats);
        base_panel.add(booking_button);
        base_panel.add(print_button);

        /*PANNELLI RISPOSTA SERVER*/
        JPanel server_response1 = new JPanel();
        server_response1.add(result_label);
        server_response1.add(result);

        JPanel server_response2 = new JPanel();
        JScrollPane bar = new JScrollPane(server_response2);
        bar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        server_response2.add(events_list);
        bar.setPreferredSize(new Dimension(150,300));

        JPanel whole_gui = new JPanel();
        whole_gui.add(base_panel);
        whole_gui.add(server_response1);
        whole_gui.add(bar);
        whole_gui.setPreferredSize(new Dimension(500,400));

        getContentPane().add(whole_gui);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    public static void main(String[] args) {
        //delego il lavoro sulla gui subito ad un altro thread
        SwingUtilities.invokeLater(GUI::new);
    }
}
