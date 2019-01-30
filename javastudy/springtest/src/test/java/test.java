import org.junit.Test;

import java.util.TreeMap;
import java.util.regex.Pattern;

public class test {
    @Test
    public void testAnnotation() {
//        Pattern pattern = Pattern.compile("^[\\u4e00-\\u9fa5,，.a-zA-Z0-9]*$");
//        System.out.println(pattern.matcher("高威").matches());
//        System.out.println(pattern.matcher("高威1").matches());
//        System.out.println(pattern.matcher("高威g").matches());
//        System.out.println(pattern.matcher("高威,").matches());
//        System.out.println(pattern.matcher("高威，`").matches());
//        System.out.println(pattern.matcher("").matches());
//
//        GenerateRequestJsonObjFilter filter1 = new GenerateRequestJsonObjFilter();filter1.setSort(1);
//        GenerateRequestJsonObjFilter filter2 = new GenerateRequestJsonObjFilter();filter2.setSort(3);
//        GenerateRequestJsonObjFilter filter3 = new GenerateRequestJsonObjFilter();filter3.setSort(2);
//        GenerateRequestJsonObjFilter filter4 = new GenerateRequestJsonObjFilter();filter4.setSort(5);
//        GenerateRequestJsonObjFilter filter5 = new GenerateRequestJsonObjFilter();filter5.setSort(4);
//        GenerateRequestJsonObjFilter filter6 = new GenerateRequestJsonObjFilter();filter6.setSort(4);
//
//        FilterHelper.addBeforeFilter(filter1);
//        FilterHelper.addBeforeFilter(filter2);
//        FilterHelper.addBeforeFilter(filter3);
//        FilterHelper.addBeforeFilter(filter4);
//        FilterHelper.addBeforeFilter(filter5);
//        FilterHelper.addBeforeFilter(filter6);
//
//        for (Node<BaseFilter> afterFilter : FilterHelper.getBeforeFilters()) {
//            System.out.println(afterFilter.getData().getSort());
//        }

//        new PackageScanner() {
//            @Override
//            public void dealClass(Class<?> klass) {
//                if (klass.isAnnotationPresent(BeforeFilter.class) ){
//                    System.out.println(klass.getName());
//                }
//            }
//        }.packageScanner("com.gw");

        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(9, "9");
        treeMap.put(1, "1");
        treeMap.put(8, "8");
        treeMap.put(2, "1");
        treeMap.put(7, "9");
        treeMap.put(3, "3");
        treeMap.put(6, "6");
        treeMap.put(4, "4");
        treeMap.put(5, "5");

        treeMap.entrySet().forEach(i -> {
            System.out.println("key:" + i.getKey() + "  value:" + i.getValue());
        });
    }
}
