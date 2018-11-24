import java.util.concurrent.atomic.AtomicInteger;

public class hahah {
    public static void main(String[] args) {
        String str = "a,b,c,,";
        String[] arr = str.split(",");
        for (String s : arr){
            System.out.println(s);
        }
        System.out.println(arr.length);
        AtomicInteger atomicInteger = new AtomicInteger(1);

    }
}
