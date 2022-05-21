package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;

public class GUI extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    JButton booking;


    public GUI(){
        super("swing");
        booking = new JButton("book");
        JLabel a = new JLabel("book your event");

        JFrame Frame = new JFrame();
        JPanel Panel = new JPanel();
        Panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        Panel.setLayout(new GridLayout(0,1));
        Frame.add(Panel, BorderLayout.CENTER);
        Frame.setTitle("Booking Events");
        Panel.add(a);
        Panel.add(booking);
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
