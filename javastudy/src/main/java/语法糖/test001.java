package 语法糖;

import java.util.HashSet;

public class test001 {
    public static void main(String[] args) {
        try (haha dd = new haha()) {
            dd.getDd().add(1);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("finally");
        }
    }

    public static class haha implements AutoCloseable {

        private HashSet<Integer> dd = new HashSet<>();

        @Override
        public void close() throws Exception {
            dd = null;
            System.out.println("close");
        }

        public HashSet<Integer> getDd() {
            return dd;
        }
    }
}
