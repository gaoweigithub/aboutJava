package JUC.内部类.非静态内部类;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class TalkingClock {
    private int interval;
    private boolean beep;

    public TalkingClock(int interval, boolean beep) {
        this.interval = interval;
        this.beep = beep;
    }

    public void start() {
        ActionListener listener = new TimePrinter();
        Timer timer = new Timer(interval, listener);
        timer.start();
    }

    class TimePrinter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Date now = new Date();
            System.out.println("date:" + now);
            if (beep) {
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }
}
