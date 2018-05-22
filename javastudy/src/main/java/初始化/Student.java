package 初始化;

import java.util.Date;
import java.util.Random;

/**
 * 初始化顺序
 * 1，初始化块
 * 2，构造函数
 *
 * @author gw33973
 */
public class Student {
    {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Random random = new Random(new Date().getTime());
        setId(random.nextInt());
        System.out.println("init block:" + getId());
    }

    /**
     * @param id
     */
    public Student(int id) {
        this.id = id;
        System.out.println("constructor:" + getId());
    }

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
