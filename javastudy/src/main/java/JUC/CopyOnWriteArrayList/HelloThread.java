package JUC.CopyOnWriteArrayList;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class HelloThread implements Runnable {
    //同步数组 会发生修改错误
//        private static List<String> list = Collections.synchronizedList(new ArrayList<String>());
    private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();

    static {
        list.add("AA");
        list.add("BB");
        list.add("CC");
    }

    @Override
    public void run() {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            list.add("AA");
            //普通的list会发生concurrentmodificationexception异常
        }
    }
}
