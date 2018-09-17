package 新特性.lamda;

import java.awt.event.ActionEvent;
import java.util.EventListener;
import java.util.function.BinaryOperator;

public class functionInterface {
    public static interface ActionListener extends EventListener {
        void actionPerformed(ActionEvent event);
        void actionHaa(String name);
    }

    public static void main(String[] args) {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("aaa");
            }

            @Override
            public void actionHaa(String name) {
                System.out.println("aaa");
            }
        };

        BinaryOperator b = (o, o2) -> Integer.valueOf(o.toString()) * Integer.valueOf(o2.toString());
        System.out.println(b.apply(1,2));
    }
}
