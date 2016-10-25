package com.xunlei.common.accelerator.utils;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

public abstract class CountDownTimer {
    private static final int MSG = 1;
    private boolean mCancelled;
    private final long mCountdownInterval;
    private Handler mHandler;
    private final long mMillisInFuture;
    private long mStopTimeInFuture;

    public abstract void onFinish();

    public abstract void onTick(long j);

    public CountDownTimer(long j, long j2) {
        this.mCancelled = false;
        this.mHandler = new Handler() {
            public void handleMessage(Message message) {
                synchronized (CountDownTimer.this) {
                    if (CountDownTimer.this.mCancelled) {
                        return;
                    }
                    long access$100 = CountDownTimer.this.mStopTimeInFuture - SystemClock.elapsedRealtime();
                    if (access$100 <= 0) {
                        CountDownTimer.this.onFinish();
                    } else if (access$100 < CountDownTimer.this.mCountdownInterval) {
                        sendMessageDelayed(obtainMessage(MSG), access$100);
                    } else {
                        long elapsedRealtime = SystemClock.elapsedRealtime();
                        CountDownTimer.this.onTick(access$100);
                        access$100 = (CountDownTimer.this.mCountdownInterval + elapsedRealtime) - SystemClock.elapsedRealtime();
                        while (access$100 < 0) {
                            access$100 += CountDownTimer.this.mCountdownInterval;
                        }
                        sendMessageDelayed(obtainMessage(MSG), access$100);
                    }
                }
            }
        };
        this.mMillisInFuture = j;
        this.mCountdownInterval = j2;
    }

    public final synchronized void cancel() {
        this.mCancelled = true;
        this.mHandler.removeMessages(MSG);
    }

    public final synchronized CountDownTimer start() {
        CountDownTimer countDownTimer;
        this.mCancelled = false;
        if (this.mMillisInFuture <= 0) {
            onFinish();
            countDownTimer = this;
        } else {
            this.mStopTimeInFuture = SystemClock.elapsedRealtime() + this.mMillisInFuture;
            this.mHandler.sendMessage(this.mHandler.obtainMessage(MSG));
            countDownTimer = this;
        }
        return countDownTimer;
    }
}
