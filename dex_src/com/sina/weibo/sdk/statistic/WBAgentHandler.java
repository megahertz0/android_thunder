package com.sina.weibo.sdk.statistic;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import com.sina.weibo.sdk.utils.LogUtil;
import com.umeng.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

class WBAgentHandler {
    private static int MAX_CACHE_SIZE;
    private static List<PageLog> mActivePages;
    private static WBAgentHandler mInstance;
    private static Map<String, PageLog> mPages;
    private static Timer mTimer;

    class AnonymousClass_1 implements Runnable {
        private final /* synthetic */ Context val$context;

        AnonymousClass_1(Context context) {
            this.val$context = context;
        }

        public void run() {
            LogReport.uploadAppLogs(this.val$context, WBAgentHandler.this.getLogsInMemory());
        }
    }

    class AnonymousClass_2 implements Runnable {
        private final /* synthetic */ String val$content;

        AnonymousClass_2(String str) {
            this.val$content = str;
        }

        public void run() {
            LogFileUtil.writeToFile(LogFileUtil.getAppLogPath(LogFileUtil.ANALYTICS_FILE_NAME), this.val$content, true);
        }
    }

    class AnonymousClass_3 extends TimerTask {
        private final /* synthetic */ Context val$context;

        AnonymousClass_3(Context context) {
            this.val$context = context;
        }

        public void run() {
            LogReport.uploadAppLogs(this.val$context, WBAgentHandler.this.getLogsInMemory());
        }
    }

    static {
        MAX_CACHE_SIZE = 5;
    }

    public static synchronized WBAgentHandler getInstance() {
        WBAgentHandler wBAgentHandler;
        synchronized (WBAgentHandler.class) {
            if (mInstance == null) {
                mInstance = new WBAgentHandler();
            }
            wBAgentHandler = mInstance;
        }
        return wBAgentHandler;
    }

    private WBAgentHandler() {
        mActivePages = new ArrayList();
        mPages = new HashMap();
        LogUtil.i(TAG, "init handler");
    }

    public void onPageStart(String str) {
        if (!StatisticConfig.ACTIVITY_DURATION_OPEN) {
            PageLog pageLog = new PageLog(str);
            pageLog.setType(LogType.FRAGMENT);
            synchronized (mPages) {
                mPages.put(str, pageLog);
            }
            LogUtil.d(TAG, new StringBuilder(String.valueOf(str)).append(", ").append(pageLog.getStartTime() / 1000).toString());
        }
    }

    public void onPageEnd(String str) {
        if (!StatisticConfig.ACTIVITY_DURATION_OPEN) {
            if (mPages.containsKey(str)) {
                PageLog pageLog = (PageLog) mPages.get(str);
                pageLog.setDuration(System.currentTimeMillis() - pageLog.getStartTime());
                synchronized (mActivePages) {
                    mActivePages.add(pageLog);
                }
                synchronized (mPages) {
                    mPages.remove(str);
                }
                LogUtil.d(TAG, new StringBuilder(String.valueOf(str)).append(", ").append(pageLog.getStartTime() / 1000).append(", ").append(pageLog.getDuration() / 1000).toString());
            } else {
                LogUtil.e(TAG, "please call onPageStart before onPageEnd");
            }
            if (mActivePages.size() >= MAX_CACHE_SIZE) {
                saveActivePages(mActivePages);
                mActivePages.clear();
            }
        }
    }

    public void onResume(Context context) {
        if (LogReport.getPackageName() == null) {
            LogReport.setPackageName(context.getPackageName());
        }
        if (mTimer == null) {
            mTimer = timerTask(context, 500, StatisticConfig.getUploadInterval());
        }
        long currentTimeMillis = System.currentTimeMillis();
        String name = context.getClass().getName();
        checkNewSession(context, currentTimeMillis);
        if (StatisticConfig.ACTIVITY_DURATION_OPEN) {
            PageLog pageLog = new PageLog(name, currentTimeMillis);
            pageLog.setType(LogType.ACTIVITY);
            synchronized (mPages) {
                mPages.put(name, pageLog);
            }
        }
        LogUtil.d(TAG, new StringBuilder(String.valueOf(name)).append(", ").append(currentTimeMillis / 1000).toString());
    }

    public void onPause(Context context) {
        long currentTimeMillis = System.currentTimeMillis();
        String name = context.getClass().getName();
        LogUtil.i(TAG, new StringBuilder("update last page endtime:").append(currentTimeMillis / 1000).toString());
        PageLog.updateSession(context, null, Long.valueOf(0), Long.valueOf(currentTimeMillis));
        if (StatisticConfig.ACTIVITY_DURATION_OPEN) {
            if (mPages.containsKey(name)) {
                PageLog pageLog = (PageLog) mPages.get(name);
                pageLog.setDuration(currentTimeMillis - pageLog.getStartTime());
                synchronized (mActivePages) {
                    mActivePages.add(pageLog);
                }
                synchronized (mPages) {
                    mPages.remove(name);
                }
                LogUtil.d(TAG, new StringBuilder(String.valueOf(name)).append(", ").append(pageLog.getStartTime() / 1000).append(", ").append(pageLog.getDuration() / 1000).toString());
            } else {
                LogUtil.e(TAG, "please call onResume before onPause");
            }
            if (mActivePages.size() >= MAX_CACHE_SIZE) {
                saveActivePages(mActivePages);
                mActivePages.clear();
            }
        }
        checkAppStatus(context);
    }

    public void onEvent(String str, String str2, Map<String, String> map) {
        EventLog eventLog = new EventLog(str, str2, map);
        eventLog.setType(LogType.EVENT);
        synchronized (mActivePages) {
            mActivePages.add(eventLog);
        }
        if (map == null) {
            LogUtil.d(TAG, new StringBuilder("event--- page:").append(str).append(" ,event name:").append(str2).toString());
        } else {
            LogUtil.d(TAG, new StringBuilder("event--- page:").append(str).append(" ,event name:").append(str2).append(" ,extend:").append(map.toString()).toString());
        }
        if (mActivePages.size() >= MAX_CACHE_SIZE) {
            saveActivePages(mActivePages);
            mActivePages.clear();
        }
    }

    public void uploadAppLogs(Context context) {
        long currentTimeMillis = System.currentTimeMillis() - LogReport.getTime(context);
        if (LogReport.getTime(context) <= 0 || currentTimeMillis >= 30000) {
            WBAgentExecutor.execute(new AnonymousClass_1(context));
            return;
        }
        timerTask(context, 30000 - currentTimeMillis, 0);
    }

    public void onStop(Context context) {
        checkAppStatus(context);
    }

    private void checkAppStatus(Context context) {
        if (isBackground(context)) {
            saveActivePages(mActivePages);
            mActivePages.clear();
        }
    }

    private boolean isBackground(Context context) {
        for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.processName.equals(context.getPackageName())) {
                if (runningAppProcessInfo.importance == 400) {
                    LogUtil.i(TAG, new StringBuilder("\u540e\u53f0:").append(runningAppProcessInfo.processName).toString());
                    return true;
                }
                LogUtil.i(TAG, new StringBuilder("\u524d\u53f0:").append(runningAppProcessInfo.processName).toString());
                return false;
            }
        }
        return false;
    }

    public void onKillProcess() {
        LogUtil.i(TAG, "save applogs and close timer and shutdown thread executor");
        saveActivePages(mActivePages);
        mInstance = null;
        closeTimer();
        WBAgentExecutor.shutDownExecutor();
    }

    private void checkNewSession(Context context, long j) {
        if (PageLog.isNewSession(context, j)) {
            PageLog pageLog = new PageLog(context);
            pageLog.setType(LogType.SESSION_END);
            PageLog pageLog2 = new PageLog(context, j);
            pageLog2.setType(LogType.SESSION_START);
            synchronized (mActivePages) {
                if (pageLog.getEndTime() > 0) {
                    mActivePages.add(pageLog);
                } else {
                    LogUtil.d(TAG, "is a new install");
                }
                mActivePages.add(pageLog2);
            }
            LogUtil.d(TAG, new StringBuilder("last session--- starttime:").append(pageLog.getStartTime()).append(" ,endtime:").append(pageLog.getEndTime()).toString());
            LogUtil.d(TAG, new StringBuilder("is a new session--- starttime:").append(pageLog2.getStartTime()).toString());
            return;
        }
        LogUtil.i(TAG, "is not a new session");
    }

    private synchronized void saveActivePages(List<PageLog> list) {
        WBAgentExecutor.execute(new AnonymousClass_2(LogBuilder.getPageLogs(list)));
    }

    private synchronized String getLogsInMemory() {
        String str;
        str = a.d;
        if (mActivePages.size() > 0) {
            str = LogBuilder.getPageLogs(mActivePages);
            mActivePages.clear();
        }
        return str;
    }

    private Timer timerTask(Context context, long j, long j2) {
        Timer timer = new Timer();
        TimerTask anonymousClass_3 = new AnonymousClass_3(context);
        if (j2 == 0) {
            timer.schedule(anonymousClass_3, j);
        } else {
            timer.schedule(anonymousClass_3, j, j2);
        }
        return timer;
    }

    private void closeTimer() {
        if (mTimer != null) {
            mTimer.cancel();
        }
    }
}
