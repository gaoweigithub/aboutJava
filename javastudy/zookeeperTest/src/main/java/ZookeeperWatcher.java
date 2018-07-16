import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ZookeeperWatcher implements Watcher {

    private AtomicInteger atomicInteger = new AtomicInteger(0);
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private ZooKeeper zooKeeper = null;

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    public ZookeeperWatcher(String connectString, int sessionTimeOut) {
        try {
            zooKeeper = new ZooKeeper(connectString, sessionTimeOut, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {
        Event.EventType type = event.getType();
        Event.KeeperState state = event.getState();
        String path = event.getPath();

        System.out.println("Watch type : " + type.toString() + " , state : " + state.toString() + " , path : " + path);

        if (state == Event.KeeperState.SyncConnected) {
            if (type == Event.EventType.None) {
                countDownLatch.countDown();
                System.out.println("[Watch事件" + atomicInteger.incrementAndGet() + "]:ZK 建立连接");
            } else if (type == Event.EventType.NodeCreated) {
                System.out.println("[Watch事件" + atomicInteger.incrementAndGet() + "]:ZK 节点建立" + path);
            } else if (type == Event.EventType.NodeDataChanged){
                System.out.println("[Watch事件" + atomicInteger.incrementAndGet() + "]:ZK 节点数据改变" + path);
            } else if (type == Event.EventType.NodeChildrenChanged){
                System.out.println("[Watch事件" + atomicInteger.incrementAndGet() + "]:ZK 子节点数据改变" + path);
            } else if (type == Event.EventType.NodeDeleted){
                System.out.println("[Watch事件" + atomicInteger.incrementAndGet() + "]:ZK 节点删除" + path);
            }
        }
    }
}
