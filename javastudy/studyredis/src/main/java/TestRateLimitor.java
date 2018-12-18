import com.alibaba.fastjson.JSON;
import elasticsearch.ESHotelIndexEnum;
import elasticsearch.ESRestClient;
import redis.clients.jedis.Jedis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestRateLimitor {
    final private static int _max = 10;
    final private static String _key_format = "ServiceThreadKey_%s";
    final private static Jedis redis = new Jedis("127.0.0.1", 6379);
    final private static ESRestClient esClient = new ESRestClient("http://10.102.14.42:9200/");
    final private static Random random = new Random();

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(20);
        executor.scheduleAtFixedRate(() -> {
            try {
                query();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 10, 50, TimeUnit.MILLISECONDS);
    }

    /**
     * 限流方法
     */
    private static void query() throws Exception {
        if (getToken()) {
            InterQueryData interQueryData = new InterQueryData();
            interQueryData.setServiceName("testService");
            interQueryData.setTime(new Date());
            interQueryData.setThreadName(Thread.currentThread().getName());
            interQueryData.setSpent(random.nextInt(100) + 100);
            esClient.IndexInsert(ESHotelIndexEnum.gaowei, JSON.toJSONStringWithDateFormat(interQueryData, "yyyy-MM-dd HH:mm:ss"));
        }
    }

    private static boolean getToken() {
        long nowSecond = new Date().getTime() / 1000;
        String key = String.format(_key_format, nowSecond);
        if (redis.incr(key) <= _max) {
            //success
            return true;
        } else {
            //error
            return false;
        }
    }

    static class InterQueryData {
        private String serviceName;
        private Date time;
        private int spent;
        private String threadName;
        private boolean success;

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public Date getTime() {
            return time;
        }

        public void setTime(Date time) {
            this.time = time;
        }

        public int getSpent() {
            return spent;
        }

        public void setSpent(int spent) {
            this.spent = spent;
        }

        public String getThreadName() {
            return threadName;
        }

        public void setThreadName(String threadName) {
            this.threadName = threadName;
        }
    }
}
