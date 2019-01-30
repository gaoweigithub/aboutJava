package com.gw.container.starter;

import com.gw.annotations.AfterFilter;
import com.gw.annotations.BeforeFilter;
import com.gw.container.common.BaseFilter;
import com.gw.custome.PackageScanner;

import java.util.TreeMap;

@StarterOrderAnnotation(order = 2)
public class FilterHelperV2 extends BaseStarter {
    private final static TreeMap<Integer, BaseFilter> beforeFilters = new TreeMap<>();

    public static TreeMap<Integer, BaseFilter> getBeforeFilters() {
        return beforeFilters;
    }

    public static TreeMap<Integer, BaseFilter> getAfterFilters() {
        return afterFilters;
    }

    private final static TreeMap<Integer, BaseFilter> afterFilters = new TreeMap<>();

    public static void addBeforeFilter(BaseFilter filter) {
        beforeFilters.put(filter.getSort(), filter);
//        appendBySort(beforeFilters, filter);
    }

    public static void addAfterFilter(BaseFilter filter) {
        afterFilters.put(filter.getSort(), filter);
//        appendBySort(afterFilters, filter);
    }

//    private static void appendBySort(Node<BaseFilter> root, BaseFilter filter) {
//
//        Node<BaseFilter> now = root;
//        while (now != null) {
//            if (now.getData().getSort() <= filter.getSort() && (now.getNext() == null || now.getNext().getData().getSort() > filter.getSort())) {
//                if (now.getNext() == null) {
//                    //直接放now后面
//                    now.setNext(new Node<>(filter));
//                    return;
//                } else if (now.getNext() != null) {
//                    Node tmp = now.getNext();
//                    now.setNext(new Node<>(filter));
//                    now.getNext().setNext(tmp);
//                    return;
//                }
//            } else {
//                now = now.getNext();
//            }
//        }
//    }

    private static void addFilter() {
        new PackageScanner() {
            @Override
            public void dealClass(Class<?> klass) {
                if (klass.isAnnotationPresent(BeforeFilter.class)
                        && !klass.isAnnotationPresent(Deprecated.class)
                        && BaseFilter.class.isAssignableFrom(klass)) {
                    try {
                        addBeforeFilter((BaseFilter) klass.newInstance());
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else if (klass.isAnnotationPresent(AfterFilter.class)
                        && !klass.isAnnotationPresent(Deprecated.class)
                        && BaseFilter.class.isAssignableFrom(klass)) {
                    try {
                        addAfterFilter((BaseFilter) klass.newInstance());
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.packageScanner("com.gw.container.filters");
    }


    @Override
    public void _process() {
        addFilter();
    }
}