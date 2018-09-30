package testRedisLock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.Random;

public class RedisLock {
    public static final String LOCKED = "TRUE";
    public static final long ONE_MILLI_NANOS = 1000000L;
    public static final long DEFAULT_TIME_OUT = 3000;
    public static JedisPool pool;
    public static final Random r = new Random();
    public static final int EXPIRE = 5;

    private Jedis jedis;
    private String key;
    private boolean locked = false;

    public RedisLock(String key) {
        this.key = key;
        this.jedis = new Jedis("127.0.0.1", 6379, 60000);
    }

    public boolean lock(long timeout) {
        long nano = System.nanoTime();
        timeout *= ONE_MILLI_NANOS;
        try {
            while ((System.nanoTime() - nano) < timeout) {
                if (jedis.setnx(key, LOCKED) == 1) {
                    jedis.expire(key, EXPIRE);
                    locked = true;
                    return locked;
                }
                Thread.sleep(3, r.nextInt(500));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean lock_2(long timeout) {
        long nano = System.nanoTime();
        timeout *= ONE_MILLI_NANOS;
        try {
            while ((System.nanoTime() - nano) < timeout) {
                Transaction t = jedis.multi();
                t.getSet(key, LOCKED);
                t.expire(key, EXPIRE);
                String ret = (String) t.exec().get(0);
//                System.out.println(Thread.currentThread() + " ========== " + ret);
                if (ret == null || ret.equalsIgnoreCase("UNLOCK")) {
                    return true;
                }
                Thread.sleep(3, r.nextInt(500));
            }
        } catch (Exception e) {

        }
        return false;
    }

    public boolean lock_3(long timeout) {
        long nano = System.nanoTime();
        timeout *= ONE_MILLI_NANOS;
        try {
            while ((System.nanoTime() - nano) < timeout) {
                jedis.watch(key);
                String value = jedis.get(key);
                if (value == null || value.equalsIgnoreCase("UNLOCK")) {
                    Transaction t = jedis.multi();
//                    t.setnx(key, LOCKED);
//                    t.expire(key, EXPIRE);
                    t.setex(key, EXPIRE, LOCKED);
                    List<Object> rr = t.exec();
                    System.out.println("result:" + rr.size());
                    if (rr != null && rr.size() > 0) {
                        System.out.println(Thread.currentThread().toString() + "get lock");
                        return true;
                    } else {
                        System.out.println(Thread.currentThread().toString() + "no get lock");
                    }
                }
                jedis.unwatch();
                Thread.sleep(3, r.nextInt(500));
            }
        } catch (Exception e) {

        }
        return false;
    }

//    public boolean lock() {
//        return lock(DEFAULT_TIME_OUT);
//    }

    public void unlock() {
        if (locked) {
            jedis.del(key);
        }
    }
}
