package com.xunlei.thundersniffer.sniff;

public enum SniffingResource$Category {
    NONE,
    BT,
    VIDEO,
    AUDIO,
    DOCUMENT,
    ARCHIVE,
    SOFTWARE;

    static {
        NONE = new SniffingResource$Category("NONE", 0);
        BT = new SniffingResource$Category("BT", 1);
        VIDEO = new SniffingResource$Category("VIDEO", 2);
        AUDIO = new SniffingResource$Category("AUDIO", 3);
        DOCUMENT = new SniffingResource$Category("DOCUMENT", 4);
        ARCHIVE = new SniffingResource$Category("ARCHIVE", 5);
        SOFTWARE = new SniffingResource$Category("SOFTWARE", 6);
        a = new SniffingResource$Category[]{NONE, BT, VIDEO, AUDIO, DOCUMENT, ARCHIVE, SOFTWARE};
    }
}
