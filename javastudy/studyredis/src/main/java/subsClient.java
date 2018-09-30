import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class subsClient  extends JedisPubSub{
    @Override
    public void onMessage(String channel, String message) {
        System.out.println("onMessage()," + channel + "=" + message);
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println("onPMessage()," + pattern + "=" + channel + "="
                + message);
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            subsClient consumer = new subsClient();
            Jedis redis = new Jedis("127.0.0.1",6379);
            redis.psubscribe(consumer,"gaowei.*");
        });
        thread1.start();
    }
}
