package hash.一致性hash;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * 带虚拟节点的一致性hash算法
 */
public class ConsistentHashingWithVertualNode {
    /**
     * 待添加入Hash环的服务器列表
     */
    private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111",
            "192.168.0.3:111", "192.168.0.4:111"};
    /**
     * 真实结点列表,考虑到服务器上线、下线的场景，即添加、删除的场景会比较频繁，这里使用LinkedList会更好
     */
    private static List<String> realNodes = new LinkedList<>();

    private static SortedMap<Integer, String> virtualNodes = new TreeMap<>();

    private static Map<String, Integer> mapCounter = new HashMap<>();
    /**
     * 虚拟节点的数目，这里写死，为了演示需要，一个真实结点对应5个虚拟节点
     */
    private static final int VIRTUAL_NODES = 500;

    static {
        for (String server : servers) {
            realNodes.add(server);
            mapCounter.put(server, 0);
        }
        for (String realNode : realNodes) {
            for (int i = 0; i < VIRTUAL_NODES; i++) {
                String virtualNodeName = realNode + "&&VN" + String.valueOf(i);
                int hash = getHash(virtualNodeName);
                System.out.println("虚拟节点[" + virtualNodeName + "]被添加, hash值为" + hash);
                virtualNodes.put(hash, virtualNodeName);
            }
        }
    }


    /**
     * 使用FNV1_32_HASH算法计算服务器的Hash值,这里不使用重写hashCode的方法，最终效果没区别
     *
     * @param str
     * @return
     */
    private static int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash ^ str.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;
        if (hash < 0) {
            hash = Math.abs(hash);
        }
        return hash;
    }

    private static String getServer(String node) {
        int hash = getHash(node);
        SortedMap<Integer, String> subMap = virtualNodes.tailMap(hash);
        //环状处理 : 如果所有节点的hash都比目标的小则直接取第一个
        Integer i;
        String virtualNode;
        if (subMap.size() == 0) {
            i = virtualNodes.firstKey();
            virtualNode = virtualNodes.get(i);
        } else {
            i = subMap.firstKey();
            virtualNode = subMap.get(i);
        }

        String realServer = virtualNode.substring(0, virtualNode.indexOf("&&"));
        mapCounter.put(realServer, mapCounter.get(realServer) + 1);
        return realServer;
    }

    public static void main(String[] args) {
//        String[] nodes = {"127.0.0.1:1111", "221.226.0.1:2222", "10.211.0.1:3333"};
//        for (int i = 0; i < nodes.length; i++) {
//            System.out.println("[" + nodes[i] + "]的hash值为" +
//                    getHash(nodes[i]) + ", 被路由到结点[" + getServer(nodes[i]) + "]");
//        }
        for (int i = 0; i < 100000; i++) {
            System.out.println("[" + i + "]的hash值为" +
                    getHash(String.valueOf(i)) + ", 被路由到结点[" + getServer(String.valueOf(i)) + "]");
        }
        System.out.println(JSON.toJSONString(mapCounter));

    }
}
