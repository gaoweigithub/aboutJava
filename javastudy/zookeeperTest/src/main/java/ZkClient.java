import com.google.common.base.Charsets;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ZkClient {
    private static final String connectString = "127.0.0.1";
    private static final int sessionTimeout = 5000;
    private static final CountDownLatch zkCountDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
//        ZooKeeper zooKeeper = getZookeeper();
//        zooKeeper.create("/node","n2".getBytes("UTF-8"),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
//        zooKeeper.create("/node/n2","n2".getBytes("UTF-8"),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
//        zooKeeper.create("/node/n3","n3".getBytes("UTF-8"),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
//        zooKeeper.create("/node/n3/child","child".getBytes("UTF-8"),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
//
//        //创建临时节点
//        zooKeeper.create("/node/n3/tmp","tmp".getBytes("UTF-8"),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
//        //临时节点下不能添加子节点 此句会异常
////        zooKeeper.create("/node/n3/tmp/t1","t1".getBytes("UTF-8"),ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL);
//        zooKeeper.close();

        ZooKeeper zooKeeper = getZookeeper();
        byte[] data = zooKeeper.getData("/node/n2", false, null);
        System.out.println(new String(data));
        System.out.println("------------------");

        List<String> children = zooKeeper.getChildren("/node", false);
        for (String child : children) {
            System.out.println(child);
        }

        zooKeeper.delete("/node/n3/child", -1, new AsyncCallback.VoidCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx) {
                System.out.println("rc: " + rc);
                System.out.println("path: " + path);
                System.out.println("ctx: " + ctx);
            }
        }, "info");

        System.in.read();
        zooKeeper.close();
    }

    private static ZooKeeper getZookeeper() throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper(connectString, sessionTimeout, watchedEvent -> {
            Watcher.Event.KeeperState state = watchedEvent.getState();
            Watcher.Event.EventType type = watchedEvent.getType();
            if (state == Watcher.Event.KeeperState.SyncConnected && type == Watcher.Event.EventType.None) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("connect zk server");
                zkCountDownLatch.countDown();
            }
        });
        zkCountDownLatch.await();
        System.out.println("run ..." + zooKeeper);
        return zooKeeper;
    }
}
