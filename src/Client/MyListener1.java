package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyListener1 implements ActionListener {
    private final GUI gui;
    public MyListener1(GUI gui){

        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.booking_button.setEnabled(false);
        gui.print_button.setEnabled(false);
        new WorkerStampa(gui).execute();
    }
}
