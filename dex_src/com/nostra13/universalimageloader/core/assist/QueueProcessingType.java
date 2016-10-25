package com.nostra13.universalimageloader.core.assist;

public enum QueueProcessingType {
    FIFO,
    LIFO;

    static {
        FIFO = new QueueProcessingType("FIFO", 0);
        LIFO = new QueueProcessingType("LIFO", 1);
        a = new QueueProcessingType[]{FIFO, LIFO};
    }
}
