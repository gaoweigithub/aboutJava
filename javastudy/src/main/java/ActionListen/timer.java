package ActionListen;


import javax.swing.*;

public class timer {
    public static void main(String[] args) {
        TimePrinter printer = new TimePrinter();
        Timer timer = new Timer(1000, printer);
        timer.start();

        JOptionPane.showMessageDialog(null, "quit program?");
        System.exit(0);
    }
}
