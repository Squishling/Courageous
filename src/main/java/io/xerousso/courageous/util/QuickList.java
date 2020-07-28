package io.xerousso.courageous.util;

import java.util.ArrayList;
import java.util.Collection;

public class QuickList<E> extends ArrayList<E> {

    public QuickList<E> append(E e) {
        add(e);
        return this;
    }

    public QuickList<E> append(int index, E e) {
        add(index, e);
        return this;
    }

    public QuickList<E> appendAll(Collection<? extends E> collection) {
        addAll(collection);
        return this;
    }

    public QuickList<E> appendAll(int index, Collection<? extends E> collection) {
        addAll(index, collection);
        return this;
    }

    protected QuickList<E> takeRange(int fromIndex, int toIndex) {
        removeRange(fromIndex, toIndex);
        return this;
    }

    public QuickList<E> take(int index) {
        remove(index);
        return this;
    }

    public QuickList<E> take(Object o) {
        remove(o);
        return this;
    }

    public QuickList<E> takeAll(Collection<?> c) {
        removeAll(c);
        return this;
    }

}
