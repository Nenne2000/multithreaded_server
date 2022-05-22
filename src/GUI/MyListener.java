package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyListener implements ActionListener{

    private GUI gui;
    private MyWorker worker;

    public MyListener(GUI gui){
        this.gui = gui;
        this.worker = new MyWorker(gui);
        worker.execute();
    }

    //TODO
    @Override
    public void actionPerformed(ActionEvent e) {
        synchronized (gui){
            gui.notify();
        }
    }
}
