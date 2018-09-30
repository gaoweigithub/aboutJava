import com.google.common.base.Strings;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

public class testWach {
    public static void main(String[] args) throws InterruptedException {
        adder("mykey",3);
    }

    static void adder(String key, int incrBy) throws InterruptedException {
        Jedis redis = new Jedis("127.0.0.1", 6379);
        redis.watch(key);
        String v = redis.get(key);
        Thread.sleep(10000);
        Transaction transaction = redis.multi();
        int val = 0;
        if (!Strings.isNullOrEmpty(v)) {
            val = Integer.parseInt(v);
        }
        transaction.set(key, String.valueOf(val + 1));
        transaction.exec();
    }

}
