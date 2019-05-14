package com.foundao.library.view;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 可去重的List
 * 存储的对象需要重写equals()和hashCode()方法
 * create by hysea on 2019/4/28
 */
public class DuplicateList<T> extends ArrayList<T> {

    @Override
    public boolean add(T t) {
        if (!this.contains(t)) {
            super.add(t);
        }
        return true;
    }

    @Override
    public void add(int index, T element) {
        if (!this.contains(element)) {
            super.add(index, element);
        }
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        c.removeAll(this);
        return super.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        c.removeAll(this);
        return super.addAll(index, c);
    }
}
