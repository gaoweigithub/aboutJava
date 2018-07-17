package lock.zkclient;

import org.I0Itec.zkclient.ZkClient;

import java.util.List;

public class zkClientWay {
    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient("127.0.0.1",5000);
        zkClient.createPersistent("/test/zkclient",true);
        zkClient.writeData("/test","test");
        System.out.println("/test value is "+zkClient.readData("/test"));
        zkClient.createPersistent("/test/zkclient/c1");
        zkClient.createPersistent("/test/zkclient/c2");
        List<String> children = zkClient.getChildren("/test/zkclient");
        for (String s : children){
            System.out.println(s);
        }
        System.out.println("size : "+ zkClient.countChildren("/test/zkclient"));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        zkClient.deleteRecursive("/test");
    }
}
