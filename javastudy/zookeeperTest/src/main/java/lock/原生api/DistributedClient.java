package lock.原生api;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class DistributedClient {
    private static final String root = "/root";
    private static final String lock = "lock_";
    private String thisPath;
    private String waitPath;
    private static final int sessionTimeOut = 5000;
    private ZooKeeper zooKeeper;
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public void doTask() throws KeeperException, InterruptedException {
        System.out.println(Thread.currentThread().getName() + "获取到锁,开始执行任务...");
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            zooKeeper.delete(root + "/" + this.thisPath, -1);
        }
        System.out.println(Thread.currentThread().getName() + "执行完成");
    }

    public void lock(String connectionString) throws IOException, KeeperException, InterruptedException {
        try {
            zooKeeper = new ZooKeeper(connectionString, sessionTimeOut, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        if (event.getType() == Event.EventType.None) {
                            countDownLatch.countDown();
                        } else if (event.getType() == Event.EventType.NodeDeleted) {
                            try {
                                List<String> children1 = zooKeeper.getChildren(root, this);
                                Collections.sort(children1);
                                int index = children1.indexOf(thisPath);
                                if (index == 0) {
                                    doTask();
                                } else if (index > 0) {
                                    waitPath = children1.get(index - 1);
                                    if (zooKeeper.exists(root + "/" + waitPath, true) == null) {
                                        doTask();
                                    }
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (KeeperException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.thisPath = zooKeeper.create(root + "/" + lock, new byte[0], ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(Thread.currentThread().getName() + "create" + this.thisPath);
        this.thisPath = thisPath.substring(thisPath.lastIndexOf('/') + 1);
        final List<String> children = zooKeeper.getChildren(root, false);
        if (children.size() == 1) {
            if (children.get(0).equals(this.thisPath)) {
                doTask();
            }
        } else {
            Collections.sort(children);
            if (children.get(0).equals(this.thisPath)) {
                doTask();
            } else {
                int index = children.indexOf(this.thisPath);
                if (index >= 0) {
                    this.waitPath = children.get(index - 1);
                    zooKeeper.exists(root + "/" + waitPath, true);
                }
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        for (int i =0 ;i < 10 ;i ++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    DistributedClient distributedClient = new DistributedClient();
                    try {
                        Thread.sleep(new Random().nextInt(1000));
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    try {
                        distributedClient.lock("127.0.0.1");
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        Thread.sleep(60000);
    }
}


























