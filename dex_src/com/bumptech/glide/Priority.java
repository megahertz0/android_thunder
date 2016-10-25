package com.bumptech.glide;

public enum Priority {
    IMMEDIATE,
    HIGH,
    NORMAL,
    LOW,
    priority;

    static {
        IMMEDIATE = new Priority("IMMEDIATE", 0);
        HIGH = new Priority("HIGH", 1);
        NORMAL = new Priority("NORMAL", 2);
        LOW = new Priority("LOW", 3);
        priority = new Priority("priority", 4);
        a = new Priority[]{IMMEDIATE, HIGH, NORMAL, LOW, priority};
    }
}
