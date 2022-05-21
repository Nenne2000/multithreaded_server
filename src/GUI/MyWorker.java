package GUI;

import javax.swing.*;

public class MyWorker extends SwingWorker {

    GUI gui;
    public MyWorker(GUI gui){
        super();
        this.gui = gui;
    }

    //TODO
    @Override
    protected String doInBackground() throws InterruptedException {
        while(true){
            synchronized (gui){
                gui.wait();
            }
            publish();
        }
    }
}
