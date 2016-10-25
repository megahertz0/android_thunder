package com.sina.weibo.sdk.cmd;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import com.sina.weibo.sdk.WeiboAppManager;
import com.sina.weibo.sdk.WeiboAppManager.WeiboInfo;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.NetUtils;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.MD5;
import com.sina.weibo.sdk.utils.NetworkHelper;
import com.sina.weibo.sdk.utils.ResourceManager;
import com.sina.weibo.sdk.utils.SDKNotification.SDKNotificationBuilder;
import com.tencent.connect.common.Constants;
import com.umeng.a;
import com.xunlei.tdlive.R;
import java.io.File;
import java.util.List;

class AppInstallCmdExecutor implements CmdExecutor<AppInstallCmd> {
    private static final int MESSAGE_DO_CMD = 1;
    private static final int MESSAGE_QUIT_LOOP = 2;
    private static final String TAG;
    private static final String WB_APK_FILE_DIR;
    private boolean isStarted;
    private Context mContext;
    private InstallHandler mHandler;
    private Looper mLooper;
    private HandlerThread thread;

    private class InstallHandler extends Handler {
        public InstallHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case MESSAGE_DO_CMD:
                    AppInstallCmdExecutor.this.handleCmd((AppInstallCmd) message.obj);
                case MESSAGE_QUIT_LOOP:
                    AppInstallCmdExecutor.this.mLooper.quit();
                    AppInstallCmdExecutor.this.isStarted = false;
                default:
                    break;
            }
        }
    }

    private static final class NOTIFICATION_CONSTANTS {
        private static final int NOTIFICATIONID = 1;
        private static final String WEIBO = "Weibo";
        private static final String WEIBO_ZH_CN = "\u5fae\u535a";
        private static final String WEIBO_ZH_TW = "\u5fae\u535a";

        private NOTIFICATION_CONSTANTS() {
        }
    }

    public AppInstallCmdExecutor(Context context) {
        this.isStarted = false;
        this.mContext = context.getApplicationContext();
    }

    static {
        WB_APK_FILE_DIR = Environment.getExternalStorageDirectory() + "/Android/org_share_data/";
        TAG = AppInstallCmdExecutor.class.getName();
    }

    private void handleCmd(AppInstallCmd appInstallCmd) {
        if (needActivate(this.mContext, appInstallCmd)) {
            String str = WB_APK_FILE_DIR;
            Object downloadUrl = appInstallCmd.getDownloadUrl();
            long appVersion = appInstallCmd.getAppVersion();
            Pair walkDir = walkDir(this.mContext, str, appInstallCmd);
            if (walkDir != null && walkDir.second != null && ((long) ((Integer) walkDir.first).intValue()) >= appVersion) {
                showNotification(this.mContext, appInstallCmd, ((File) walkDir.second).getAbsolutePath());
            } else if (NetworkHelper.isWifiValid(this.mContext) && !TextUtils.isEmpty(downloadUrl)) {
                Object obj = a.d;
                try {
                    String internalGetRedirectUri = NetUtils.internalGetRedirectUri(this.mContext, downloadUrl, Constants.HTTP_GET, new WeiboParameters(a.d));
                    String generateSaveFileName = generateSaveFileName(internalGetRedirectUri);
                    if (TextUtils.isEmpty(generateSaveFileName) || !generateSaveFileName.endsWith(".apk")) {
                        LogUtil.e(TAG, "redirectDownloadUrl is illeagle");
                        if (!TextUtils.isEmpty(obj)) {
                            showNotification(this.mContext, appInstallCmd, obj);
                            return;
                        }
                        return;
                    }
                    Object internalDownloadFile = NetUtils.internalDownloadFile(this.mContext, internalGetRedirectUri, str, generateSaveFileName);
                    if (!TextUtils.isEmpty(internalDownloadFile)) {
                        showNotification(this.mContext, appInstallCmd, internalDownloadFile);
                    }
                } catch (WeiboException e) {
                    try {
                        e.printStackTrace();
                        if (!TextUtils.isEmpty(obj)) {
                            showNotification(this.mContext, appInstallCmd, obj);
                        }
                    } catch (Throwable th) {
                        if (!TextUtils.isEmpty(obj)) {
                            showNotification(this.mContext, appInstallCmd, obj);
                        }
                    }
                }
            }
        }
    }

    private static boolean needActivate(Context context, AppInstallCmd appInstallCmd) {
        List<String> appPackage = appInstallCmd.getAppPackage();
        if (appPackage == null || appPackage.size() == 0 || TextUtils.isEmpty(appInstallCmd.getAppSign()) || TextUtils.isEmpty(appInstallCmd.getDownloadUrl()) || TextUtils.isEmpty(appInstallCmd.getNotificationText())) {
            return false;
        }
        if (appPackage.contains("com.sina.weibo")) {
            WeiboInfo weiboInfo = WeiboAppManager.getInstance(context).getWeiboInfo();
            return weiboInfo == null || !weiboInfo.isLegal();
        } else {
            for (String str : appPackage) {
                if (checkApkInstalled(context, str)) {
                    return false;
                }
            }
            return true;
        }
    }

    private static boolean checkApkInstalled(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, MESSAGE_DO_CMD) != null;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public void start() {
        if (!this.isStarted) {
            this.isStarted = true;
            this.thread = new HandlerThread(a.d);
            this.thread.start();
            this.mLooper = this.thread.getLooper();
            this.mHandler = new InstallHandler(this.mLooper);
        }
    }

    public void stop() {
        if (this.thread == null || this.mHandler == null) {
            LogUtil.w(TAG, "no thread running. please call start method first!");
            return;
        }
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.what = 2;
        this.mHandler.sendMessage(obtainMessage);
    }

    public boolean doExecutor(AppInstallCmd appInstallCmd) {
        if (this.thread == null || this.mHandler == null) {
            throw new RuntimeException("no thread running. please call start method first!");
        }
        if (appInstallCmd != null) {
            Message obtainMessage = this.mHandler.obtainMessage();
            obtainMessage.what = 1;
            obtainMessage.obj = appInstallCmd;
            this.mHandler.sendMessage(obtainMessage);
        }
        return false;
    }

    private static Pair<Integer, File> walkDir(Context context, String str, AppInstallCmd appInstallCmd) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            return null;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return null;
        }
        File file2;
        int length = listFiles.length;
        int i = 0;
        int i2 = 0;
        Object obj = null;
        while (i < length) {
            int i3;
            File file3 = listFiles[i];
            String name = file3.getName();
            if (file3.isFile() && name.endsWith(".apk")) {
                PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(file3.getAbsolutePath(), R.styleable.AppCompatTheme_imageButtonStyle);
                if (isSpecifiedApk(packageArchiveInfo, appInstallCmd.getAppPackage(), appInstallCmd.getAppSign()) && packageArchiveInfo.versionCode > i2) {
                    i3 = packageArchiveInfo.versionCode;
                    i++;
                    i2 = i3;
                    file2 = file3;
                }
            }
            file3 = file2;
            i3 = i2;
            i++;
            i2 = i3;
            file2 = file3;
        }
        return new Pair(Integer.valueOf(i2), file2);
    }

    private static boolean isSpecifiedApk(PackageInfo packageInfo, List<String> list, String str) {
        boolean z;
        for (String str2 : list) {
            if (checkPackageName(packageInfo, str2)) {
                z = true;
                break;
            }
        }
        Object obj = null;
        return z && checkApkSign(packageInfo, str);
    }

    private static boolean checkPackageName(PackageInfo packageInfo, String str) {
        return packageInfo == null ? false : str.equals(packageInfo.packageName);
    }

    private static boolean checkApkSign(PackageInfo packageInfo, String str) {
        if (packageInfo == null) {
            return false;
        }
        if (packageInfo.signatures == null) {
            return VERSION.SDK_INT < 11;
        } else {
            String str2 = a.d;
            for (int i = 0; i < packageInfo.signatures.length; i++) {
                byte[] toByteArray = packageInfo.signatures[i].toByteArray();
                if (toByteArray != null) {
                    str2 = MD5.hexdigest(toByteArray);
                }
            }
            return str2 != null ? str2.equals(str) : false;
        }
    }

    private static String generateSaveFileName(String str) {
        String str2 = a.d;
        int lastIndexOf = str.lastIndexOf("/");
        return lastIndexOf != -1 ? str.substring(lastIndexOf + 1, str.length()) : str2;
    }

    private static void showNotification(Context context, AppInstallCmd appInstallCmd, String str) {
        SDKNotificationBuilder.buildUpon().setNotificationContent(appInstallCmd.getNotificationText()).setNotificationPendingIntent(buildInstallApkIntent(context, str)).setNotificationTitle(getNotificationTitle(context, appInstallCmd.getNotificationTitle())).setTickerText(appInstallCmd.getNotificationText()).build(context).show(MESSAGE_DO_CMD);
    }

    private static PendingIntent buildInstallApkIntent(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return PendingIntent.getActivity(context, 0, new Intent(), R.styleable.Toolbar_titleMarginBottom);
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(new File(str)), "application/vnd.android.package-archive");
        return PendingIntent.getActivity(context, 0, intent, R.styleable.Toolbar_titleMarginBottom);
    }

    private static String getNotificationTitle(Context context, String str) {
        return TextUtils.isEmpty(str) ? ResourceManager.getString(context, "Weibo", "\u5fae\u535a", "\u5fae\u535a") : str;
    }
}
