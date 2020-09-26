package io.xerousso.courageous.util;

import java.util.Objects;

public class Tetrad<A, B, C, D> {

    private A first;
    private B second;
    private C third;
    private D fourth;

    public Tetrad(A first, B second, C third, D fourth) {
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
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

    public D getFourth() {
        return fourth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tetrad<?, ?, ?, ?> tetrad = (Tetrad<?, ?, ?, ?>) o;
        return Objects.equals(first, tetrad.first) &&
                Objects.equals(second, tetrad.second) &&
                Objects.equals(third, tetrad.third) &&
                Objects.equals(fourth, tetrad.fourth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, third, fourth);
    }

}
