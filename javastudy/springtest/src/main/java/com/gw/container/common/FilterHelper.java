package com.gw.container.common;

import com.gw.annotations.AfterFilter;
import com.gw.annotations.BeforeFilter;
import com.gw.common.Node;
import com.gw.custome.PackageScanner;
import com.gw.starter.BaseStarter;

public class FilterHelper extends BaseStarter {
    private static Node<BaseFilter> beforeFilters = null;
    private static Node<BaseFilter> afterFilters = null;

    public static void addBeforeFilter(BaseFilter filter) {
        if (beforeFilters == null) {
            beforeFilters = new Node<>(filter);
            return;
        }
        appendBySort(beforeFilters, filter);
    }

    public static void addAfterFilter(BaseFilter filter) {
        if (afterFilters == null) {
            afterFilters = new Node<>(filter);
            return;
        }
        appendBySort(afterFilters, filter);
    }

    private static void appendBySort(Node<BaseFilter> root, BaseFilter filter) {

        Node<BaseFilter> now = root;
        while (now != null) {
            if (now.getData().getSort() <= filter.getSort() && (now.getNext() == null || now.getNext().getData().getSort() > filter.getSort())) {
                if (now.getNext() == null) {
                    //直接放now后面
                    now.setNext(new Node<>(filter));
                    return;
                } else if (now.getNext() != null) {
                    Node tmp = now.getNext();
                    now.setNext(new Node<>(filter));
                    now.getNext().setNext(tmp);
                    return;
                }
            } else {
                now = now.getNext();
            }
        }
    }

    private static void addFilter() {
        new PackageScanner() {
            @Override
            public void dealClass(Class<?> klass) {
                if (klass.isAnnotationPresent(BeforeFilter.class) && BaseFilter.class.isAssignableFrom(klass)) {
                    try {
                        addBeforeFilter((BaseFilter) klass.newInstance());
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                } else if (klass.isAnnotationPresent(AfterFilter.class) && BaseFilter.class.isAssignableFrom(klass)) {
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

    public static Node<BaseFilter> getBeforeFilters() {
        return beforeFilters;
    }

    public static Node<BaseFilter> getAfterFilters() {
        return afterFilters;
    }

    @Override
    public void _process() {
        addFilter();
    }
}
