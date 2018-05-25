package 内部类.非静态内部类;

import javax.swing.*;

public class InnerClassTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        TalkingClock clock = new TalkingClock(1000, true);
        clock.start();
        JOptionPane.showMessageDialog(null, "quit?");
        System.exit(0);
    }
}
