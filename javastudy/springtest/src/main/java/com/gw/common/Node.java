package com.gw.common;

import java.util.Iterator;

public class Node<T> implements Iterable<Node<T>> {
    private T data;
    private Node<T> next;

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public Iterator<Node<T>> iterator() {
        final Node ttptr = this;
        class iter implements Iterator<Node<T>>     //方法内部类
        {
            Node ptr = ttptr;
            @Override
            public boolean hasNext() {
                return ptr != null;
            }

            @Override
            public Node next() {
                Node dd = ptr;
                ptr = ptr.getNext();
                return dd;
            }
        }
        return new iter();     //安装Iterable接口的约定，返回迭代器
    }
}
