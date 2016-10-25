package com.bumptech.glide;

public enum MemoryCategory {
    LOW(0.5f),
    NORMAL(1.0f),
    HIGH(1.5f);
    private float a;

    static {
        LOW = new MemoryCategory("LOW", 0, 0.5f);
        NORMAL = new MemoryCategory("NORMAL", 1, 1.0f);
        HIGH = new MemoryCategory("HIGH", 2, 1.5f);
        b = new MemoryCategory[]{LOW, NORMAL, HIGH};
    }

    private MemoryCategory(float f) {
        this.a = f;
    }

    public final float getMultiplier() {
        return this.a;
    }
}
