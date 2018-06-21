package JUC.内部类.测试;

public class test2 {
    public static class ssc {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        test2.ssc ssc1 = new test2.ssc();
        test2.ssc ssc2 = new test2.ssc();

        ssc1.setName("ggww");
        System.out.println(ssc2.getName());
    }
}
