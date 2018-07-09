package CglibTest;

public class MixinInterfaceTest {
    interface Interface1 {
        String first();
    }

    interface Interface2 {
        String second();
    }

    static class Class1 implements Interface1 {
        @Override
        public String first() {
            return "first";
        }
    }

    static class Class2 implements Interface2 {
        @Override
        public String second() {
            return "second";
        }
    }

    interface MixinInterface extends Interface1, Interface2 {

    }
}

