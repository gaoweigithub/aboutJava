package JUC.阻塞队列;

import java.util.*;

public class TestPriorityBlockingQueue2 {
    static class Student implements Comparable<Student> {
        private int order;
        private String name;

        public Student(int order, String name) {
            this.order = order;
            this.name = name;
        }

        @Override
        public int compareTo(Student o) {
            return Integer.valueOf(this.order).compareTo(Integer.valueOf(o.order));
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Student> priorityQueue = new PriorityQueue<>(10);
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int id = random.nextInt(10);
            priorityQueue.add(new Student(id, String.valueOf(id)));
        }

        for (Student s : priorityQueue) {
            System.out.println(s.name);
        }
        System.out.println("-----------------");
        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll().name);
        }

    }
}
