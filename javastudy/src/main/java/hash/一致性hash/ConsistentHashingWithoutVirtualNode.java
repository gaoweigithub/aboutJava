package hash.一致性hash;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 无虚拟节点的一致性hash算法
 * https://www.cnblogs.com/xrq730/p/5186728.html
 */
public class ConsistentHashingWithoutVirtualNode {
    private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111", "192.168.0.3:111", "192.168.0.4:111"};
    private static SortedMap<Integer, String> sortedMap = new TreeMap<>();

    static {
        for (int i = 0; i < servers.length; i++) {
            int hash = getHash(servers[i]);
            System.out.println("[" + servers[i] + "]加入集合中, 其Hash值为" + hash);
            sortedMap.put(hash, servers[i]);
        }
        System.out.println();
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

    /**
     * 得到应当路由到的结点
     *
     * @param node
     * @return
     */
    private static String getServer(String node) {
        int hash = getHash(node);
        // 得到大于该Hash值的所有Map
        SortedMap<Integer, String> subMap = sortedMap.tailMap(hash);
        //环状处理 : 如果所有节点的hash都比目标的小则直接取第一个
        if (subMap.size() == 0) {
            return sortedMap.get(sortedMap.firstKey());
        }
        // 第一个Key就是顺时针过去离node最近的那个结点
        Integer i = subMap.firstKey();
        return subMap.get(i);
    }

    public static void main(String[] args) {
        String[] nodes = {"127.0.0.1:1111", "221.226.0.1:2222", "10.211.0.1:3333"};
        for (String node : nodes) {
            System.out.println("[" + node + "]的hash值为" +
                    getHash(node) + ", 被路由到结点[" + getServer(node) + "]");
        }
    }
}
