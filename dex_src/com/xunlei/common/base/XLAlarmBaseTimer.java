package com.xunlei.common.base;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.xunlei.common.base.XLAlarmBaseTimer.TimerItem;
import com.xunlei.common.base.XLAlarmBaseTimer.TimerListener;
import java.util.HashMap;
import java.util.Map;

public class XLAlarmBaseTimer {
    public static final String MY_ALARM_TIMER_ACTION_BASE = "com.xunlei.xlkdemo.timer.action_";
    public static final String MY_ALARM_TIMER_ID = "alarm_timer_id";
    public static final String MY_ALARM_TIMER_INTENT_CATEGORY = "com.xunlei.xlkdemo.timer.CATEGORY";
    private static boolean mInited;
    private static XLAlarmBaseTimer mTimer;
    private Context mContext;
    private Map<String, TimerItem> mTimerCollection;
    private BroadcastReceiver mTimerRecever;

    public static class TimerItem {
        private int mExpireTime;
        private TimerListener mListener;
        private boolean mLoop;
        private PendingIntent mPendingIntent;
        private int mTimerId;

        public TimerItem(int i, int i2, boolean z, TimerListener timerListener) {
            this.mTimerId = 0;
            this.mLoop = false;
            this.mListener = null;
            this.mExpireTime = 0;
            this.mPendingIntent = null;
            this.mTimerId = i;
            this.mExpireTime = i2;
            this.mLoop = z;
            this.mListener = timerListener;
        }

        public static String getAction(int i) {
            return new StringBuilder(MY_ALARM_TIMER_ACTION_BASE).append(String.valueOf(i)).toString();
        }

        public int getTimerId() {
            return this.mTimerId;
        }

        public void start() {
            Intent intent = new Intent(MY_ALARM_TIMER_ACTION_BASE);
            intent.addCategory(MY_ALARM_TIMER_INTENT_CATEGORY);
            intent.putExtra(MY_ALARM_TIMER_ID, this.mTimerId);
            this.mPendingIntent = PendingIntent.getBroadcast(mTimer.mContext, this.mTimerId, intent, 134217728);
            ((AlarmManager) mTimer.mContext.getSystemService("alarm")).setRepeating(0, System.currentTimeMillis() + ((long) this.mExpireTime), (long) this.mExpireTime, this.mPendingIntent);
        }

        public void kill() {
            ((AlarmManager) mTimer.mContext.getSystemService("alarm")).cancel(this.mPendingIntent);
        }

        public void tick() {
            if (this.mListener != null) {
                this.mListener.onTimerTick(this.mTimerId);
            }
        }

        public boolean isLoop() {
            return this.mLoop;
        }

        public int getExpireTime() {
            return this.mExpireTime;
        }
    }

    static {
        mTimer = null;
        mInited = false;
    }

    private XLAlarmBaseTimer() {
        this.mTimerCollection = new HashMap();
        this.mContext = null;
        this.mTimerRecever = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                TimerItem timerItem = (TimerItem) XLAlarmBaseTimer.this.mTimerCollection.get(intent.getAction() + intent.getIntExtra(MY_ALARM_TIMER_ID, 0));
                if (timerItem != null) {
                    timerItem.tick();
                    if (!timerItem.isLoop()) {
                        mTimer.unRegisterTimer(timerItem.getTimerId());
                    }
                }
            }
        };
    }

    public static void init(Context context) {
        if (!mInited) {
            mInited = true;
            XLAlarmBaseTimer xLAlarmBaseTimer = new XLAlarmBaseTimer();
            mTimer = xLAlarmBaseTimer;
            xLAlarmBaseTimer.mContext = context;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addCategory(MY_ALARM_TIMER_INTENT_CATEGORY);
            intentFilter.addAction(MY_ALARM_TIMER_ACTION_BASE);
            intentFilter.setPriority(Integer.MAX_VALUE);
            mTimer.mContext.registerReceiver(mTimer.mTimerRecever, intentFilter);
        }
    }

    public static void unInit() {
        if (mInited) {
            mInited = false;
            mTimer.mContext.unregisterReceiver(mTimer.mTimerRecever);
        }
    }

    public static synchronized XLAlarmBaseTimer getInstance() {
        XLAlarmBaseTimer xLAlarmBaseTimer;
        synchronized (XLAlarmBaseTimer.class) {
            xLAlarmBaseTimer = mTimer;
        }
        return xLAlarmBaseTimer;
    }

    public synchronized void registerTimer(int i, int i2, boolean z, TimerListener timerListener) {
        TimerItem timerItem = new TimerItem(i, i2, z, timerListener);
        this.mTimerCollection.put(TimerItem.getAction(i), timerItem);
        timerItem.start();
    }

    public synchronized void unRegisterTimer(int i) {
        TimerItem timerItem = (TimerItem) this.mTimerCollection.get(TimerItem.getAction(i));
        if (timerItem != null) {
            timerItem.kill();
            this.mTimerCollection.remove(TimerItem.getAction(i));
        }
    }

    public void clearAllTimer() {
        for (String str : this.mTimerCollection.keySet()) {
            ((TimerItem) this.mTimerCollection.get(str)).kill();
        }
        this.mTimerCollection.clear();
    }
}
