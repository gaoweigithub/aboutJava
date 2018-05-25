package ActionListen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class TimePrinter implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Date now = new Date();
        System.out.println("time is" + now);
        Toolkit.getDefaultToolkit().beep();
    }
}
