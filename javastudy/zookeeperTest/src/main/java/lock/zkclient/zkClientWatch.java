package lock.zkclient;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

import java.io.IOException;
import java.util.List;

public class zkClientWatch {
    public static void main(String[] args) throws IOException {
        ZkClient zkClient = new ZkClient("127.0.0.1", 5000);
        zkClient.subscribeChildChanges("/node", new IZkChildListener() {
            @Override
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println("subscribechildren change：" + s + "," + list);
            }
        });
        zkClient.subscribeDataChanges("/node", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("subscribeDataChange : " + s + "," + o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("subscribeDataChange : " + s);
            }
        });

        System.in.read();
    }
}
