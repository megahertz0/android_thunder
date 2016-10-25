package com.baidu.mobads.openad.interfaces.utils;

public interface IOAdTimer {

    public static interface EventHandler {
        void onTimer(int i);

        void onTimerComplete();
    }

    int getCurrentCount();

    int getRepeatCount();

    void pause();

    void reset();

    void resume();

    void setEventHandler(EventHandler eventHandler);

    void start();

    void stop();
}
