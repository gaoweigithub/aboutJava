package 内部类.匿名内部类;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class TalkingClock {
    public void start(int interval, final boolean beep) {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date now = new Date();
                System.out.println("time :" + now);
                if (beep) {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        };
        Timer timer = new Timer(interval, listener);
        timer.start();
    }
}
