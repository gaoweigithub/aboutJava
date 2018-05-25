package 内部类.匿名内部类;

import javax.swing.*;

public class AnonymousInnerClassTest {
    public static void main(String[] args) {
        TalkingClock clock = new TalkingClock();
        clock.start(1000, true);

        JOptionPane.showMessageDialog(null, "quit?");
        System.exit(0);
    }
}
