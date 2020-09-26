package io.xerousso.courageous.client;

import net.minecraft.util.StringIdentifiable;

public enum PotteryStates implements StringIdentifiable {

    BASE("base"),
    STILL("still"),
    TURNING("turning");

    private final String name;

    private PotteryStates(String name) {
        this.name = name;
    }

    public String toString() {
        return this.asString();
    }

    public String asString() {
        return this.name;
    }

}
