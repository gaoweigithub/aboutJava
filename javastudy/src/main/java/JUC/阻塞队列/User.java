package JUC.阻塞队列;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class User implements Delayed {
    private long delayed;
    private TimeUnit timeUnit;
    private long exeTimestamp;
    private Integer userID;

    public User(long delayed, TimeUnit timeUnit, Integer userID) {
        this.delayed = delayed;
        this.timeUnit = timeUnit;
        this.userID = userID;
        this.exeTimestamp = System.currentTimeMillis() + timeUnit.toMillis(delayed);
    }

    public Integer getUserID() {
        return userID;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.exeTimestamp - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        User target = (User) o;
        if (this.userID > target.userID) {
            return 1;
        } else if (this.userID.equals(target.userID)) {
            return 0;
        }
        return -1;
    }
}
