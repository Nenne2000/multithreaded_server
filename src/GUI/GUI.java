package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class GUI extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    JButton booking;


    public GUI(){
        super("Booking GUI");
        booking = new JButton("Book now!");
        JLabel name = new JLabel("Event name:");
        JTextField text1 = new JTextField(3);
        JLabel seats = new JLabel("Seats:");
        JTextField text2 = new JTextField(3);

        JFrame Frame = new JFrame();
        JPanel Panel = new JPanel();
        Panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        Panel.setLayout(new GridLayout(0,1));
        Frame.add(Panel, BorderLayout.CENTER);
        Frame.setTitle("Booking Events");
        Panel.add(name);
        Panel.add(text1);
        Panel.add(seats);
        Panel.add(text2);
        Panel.add(booking);

        MyListener book_handler  = new MyListener(this);
        booking.addActionListener(book_handler);

        getContentPane().add(Panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI();
            }
        });
    }
}
