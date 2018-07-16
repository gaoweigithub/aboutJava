import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.UnsupportedEncodingException;

public class ZKClient2 {
    public static void main(String[] args) throws KeeperException, InterruptedException, UnsupportedEncodingException {
        ZookeeperWatcher zookeeperWatcher = new ZookeeperWatcher("127.0.0.1", 5000);
        if (zookeeperWatcher.getZooKeeper() == null) {
            System.out.println("初始化失败!");
            return;
        }
        ZooKeeper zooKeeper = zookeeperWatcher.getZooKeeper();
        zooKeeper.exists("/watcher", true);
        System.out.println("main create :" + zooKeeper.create("/watcher", "watcher".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT));
        zooKeeper.delete("/watcher", -1);
        zooKeeper.create("/watcher", "watcher".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        System.out.println("------------------1");
        zooKeeper.exists("/watcher", true);
        zooKeeper.delete("/watcher", -1);

        System.out.println("-----------------2");
        zooKeeper.exists("/watcher", true);
        zooKeeper.create("/watcher", "watcher".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);


        System.out.println("------------------3");
        zooKeeper.exists("/watcher", true);
        zooKeeper.setData("/watcher", "world".getBytes(), -1);

        System.out.println("------------------4");
        zooKeeper.exists("/watcher/child", true);
        zooKeeper.getChildren("/watcher", zookeeperWatcher);
        zooKeeper.create("/watcher/child", "child".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }
}
