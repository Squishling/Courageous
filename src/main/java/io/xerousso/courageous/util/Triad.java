package io.xerousso.courageous.util;

import java.util.Objects;

public class Triad<A, B, C> {

    private A first;
    private B second;
    private C third;

    public Triad(A first, B second, C third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    public C getThird() {
        return third;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triad<?, ?, ?> triad = (Triad<?, ?, ?>) o;
        return Objects.equals(first, triad.first) &&
                Objects.equals(second, triad.second) &&
                Objects.equals(third, triad.third);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, third);
    }

}
