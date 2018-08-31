package lock.Curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.io.UnsupportedEncodingException;

public class crud {
    static final String connectString = "127.0.0.1:2181";
    static final int sessionTimeOut = 50000;

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(connectString).sessionTimeoutMs(sessionTimeOut).retryPolicy(retryPolicy).build();
        curatorFramework.start();
        //创建节点
        curatorFramework.create().creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT).forPath("/curator/node", "node".getBytes("UTF-8"));
        //获取数据
        System.out.println(new String(curatorFramework.getData().forPath("/curator/node"), "UTF-8"));

        curatorFramework.setData().forPath("/curator", "curator".getBytes("UTF-8"));

        curatorFramework.create().creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT).forPath("/curator/tmp/t1/t2", null);
        curatorFramework.delete().deletingChildrenIfNeeded().forPath("/curator/tmp");

        Stat stat = curatorFramework.checkExists().forPath("/curator");

        System.out.println(stat);
        curatorFramework.close();
    }
}
