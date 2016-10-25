package com.google.zxing.pdf417.encoder;

public enum Compaction {
    AUTO,
    TEXT,
    BYTE,
    NUMERIC;

    static {
        AUTO = new Compaction("AUTO", 0);
        TEXT = new Compaction("TEXT", 1);
        BYTE = new Compaction("BYTE", 2);
        NUMERIC = new Compaction("NUMERIC", 3);
        a = new Compaction[]{AUTO, TEXT, BYTE, NUMERIC};
    }
}
