package com.tencent.bugly;

import java.util.Map;

// compiled from: BUGLY
public class BuglyStrategy {
    private String a;
    private String b;
    private String c;
    private long d;
    private String e;
    private String f;
    private boolean g;
    private boolean h;
    private boolean i;
    private long j;
    private boolean k;
    private Class<?> l;
    private boolean m;
    private boolean n;
    private boolean o;
    private a p;

    // compiled from: BUGLY
    public static class a {
        public static final int CRASHTYPE_ANR = 4;
        public static final int CRASHTYPE_BLOCK = 7;
        public static final int CRASHTYPE_COCOS2DX_JS = 5;
        public static final int CRASHTYPE_COCOS2DX_LUA = 6;
        public static final int CRASHTYPE_JAVA_CATCH = 1;
        public static final int CRASHTYPE_JAVA_CRASH = 0;
        public static final int CRASHTYPE_NATIVE = 2;
        public static final int CRASHTYPE_U3D = 3;
        public static final int MAX_USERDATA_KEY_LENGTH = 100;
        public static final int MAX_USERDATA_VALUE_LENGTH = 30000;

        public synchronized Map<String, String> onCrashHandleStart(int i, String str, String str2, String str3) {
            return null;
        }

        public synchronized byte[] onCrashHandleStart2GetExtraDatas(int i, String str, String str2, String str3) {
            return null;
        }
    }

    public BuglyStrategy() {
        this.g = true;
        this.h = true;
        this.i = false;
        this.j = 3000;
        this.k = true;
        this.l = null;
        this.m = true;
        this.n = true;
        this.o = false;
    }

    public synchronized void setBuglyLogUpload(boolean z) {
        this.m = z;
    }

    public synchronized BuglyStrategy setRecordUserInfoOnceADay(boolean z) {
        this.o = z;
        return this;
    }

    public synchronized boolean isBuglyLogUpload() {
        return this.m;
    }

    public synchronized boolean recordUserInfoOnceADay() {
        return this.o;
    }

    public boolean isReplaceOldChannel() {
        return this.n;
    }

    public void setReplaceOldChannel(boolean z) {
        this.n = z;
    }

    public synchronized String getAppVersion() {
        return this.a == null ? com.tencent.bugly.crashreport.common.info.a.a().i : this.a;
    }

    public synchronized BuglyStrategy setAppVersion(String str) {
        this.a = str;
        return this;
    }

    public synchronized BuglyStrategy setUserInfoActivity(Class<?> cls) {
        this.l = cls;
        return this;
    }

    public synchronized Class<?> getUserInfoActivity() {
        return this.l;
    }

    public synchronized String getAppChannel() {
        return this.b == null ? com.tencent.bugly.crashreport.common.info.a.a().j : this.b;
    }

    public synchronized BuglyStrategy setAppChannel(String str) {
        this.b = str;
        return this;
    }

    public synchronized String getAppPackageName() {
        return this.c == null ? com.tencent.bugly.crashreport.common.info.a.a().c : this.c;
    }

    public synchronized BuglyStrategy setAppPackageName(String str) {
        this.c = str;
        return this;
    }

    public synchronized long getAppReportDelay() {
        return this.d;
    }

    public synchronized BuglyStrategy setAppReportDelay(long j) {
        this.d = j;
        return this;
    }

    public synchronized String getLibBuglySOFilePath() {
        return this.e;
    }

    public synchronized BuglyStrategy setLibBuglySOFilePath(String str) {
        this.e = str;
        return this;
    }

    public synchronized String getDeviceID() {
        return this.f;
    }

    public synchronized BuglyStrategy setDeviceID(String str) {
        this.f = str;
        return this;
    }

    public synchronized boolean isEnableNativeCrashMonitor() {
        return this.g;
    }

    public synchronized BuglyStrategy setEnableNativeCrashMonitor(boolean z) {
        this.g = z;
        return this;
    }

    public synchronized BuglyStrategy setEnableUserInfo(boolean z) {
        this.k = z;
        return this;
    }

    public synchronized boolean isEnableUserInfo() {
        return this.k;
    }

    public synchronized boolean isEnableANRCrashMonitor() {
        return this.h;
    }

    public synchronized BuglyStrategy setEnableANRCrashMonitor(boolean z) {
        this.h = z;
        return this;
    }

    public synchronized boolean isEnableBlockCrashMonitor() {
        return this.i;
    }

    public synchronized BuglyStrategy setEnableBlockCrashMonitor(boolean z) {
        this.i = z;
        return this;
    }

    public long getBlockThresholdTime() {
        return this.j;
    }

    public void setBlockThresholdTime(long j) {
        this.j = j;
    }

    public synchronized a getCrashHandleCallback() {
        return this.p;
    }

    public synchronized BuglyStrategy setCrashHandleCallback(a aVar) {
        this.p = aVar;
        return this;
    }
}
