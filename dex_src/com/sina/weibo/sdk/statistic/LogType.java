package com.sina.weibo.sdk.statistic;

enum LogType {
    SESSION_START,
    SESSION_END,
    FRAGMENT,
    EVENT,
    ACTIVITY;

    static {
        SESSION_START = new LogType("SESSION_START", 0);
        SESSION_END = new LogType("SESSION_END", 1);
        FRAGMENT = new LogType("FRAGMENT", 2);
        EVENT = new LogType("EVENT", 3);
        ACTIVITY = new LogType("ACTIVITY", 4);
        ENUM$VALUES = new LogType[]{SESSION_START, SESSION_END, FRAGMENT, EVENT, ACTIVITY};
    }
}
