package 集合;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;

public class TestDictionaryHashtable {
    public static void main(String[] args) {
        HashSet<String> hs = new HashSet<>();
        hs.add("1");
        hs.add("2");
        hs.add("3");
        hs.contains("1");
        Hashtable<String,Integer> sss = new Hashtable<>();
        sss.containsKey(1);

        HashMap hmap = new HashMap();
        hmap.containsKey(1);

    }
}
