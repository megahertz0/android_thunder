package com.sina.weibo.sdk.statistic;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import com.sina.weibo.sdk.utils.LogUtil;
import com.umeng.a;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.android.spdy.SpdyAgent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class LogBuilder {
    private static /* synthetic */ int[] $SWITCH_TABLE$com$sina$weibo$sdk$statistic$LogType = null;
    private static final String APPKEY = "WEIBO_APPKEY";
    private static final String CHANNEL = "WEIBO_CHANNEL";
    public static final String KEY_AID = "aid";
    public static final String KEY_APPKEY = "appkey";
    public static final String KEY_CHANNEL = "channel";
    private static final String KEY_DURATION = "duration";
    public static final String KEY_END_TIME = "endtime";
    private static final String KEY_EVENT_ID = "event_id";
    private static final String KEY_EXTEND = "extend";
    public static final String KEY_HASH = "key_hash";
    public static final String KEY_PACKAGE_NAME = "packagename";
    private static final String KEY_PAGE_ID = "page_id";
    public static final String KEY_PLATFORM = "platform";
    public static final String KEY_START_TIME = "starttime";
    private static final String KEY_TIME = "time";
    public static final String KEY_TYPE = "type";
    public static final String KEY_VERSION = "version";
    private static final int MAX_COUNT = 500;
    public static final long MAX_INTERVAL = 86400000;

    static /* synthetic */ int[] $SWITCH_TABLE$com$sina$weibo$sdk$statistic$LogType() {
        int[] iArr = $SWITCH_TABLE$com$sina$weibo$sdk$statistic$LogType;
        if (iArr == null) {
            iArr = new int[LogType.values().length];
            try {
                iArr[LogType.ACTIVITY.ordinal()] = 5;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[LogType.EVENT.ordinal()] = 4;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[LogType.FRAGMENT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[LogType.SESSION_END.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[LogType.SESSION_START.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            $SWITCH_TABLE$com$sina$weibo$sdk$statistic$LogType = iArr;
        }
        return iArr;
    }

    LogBuilder() {
    }

    public static String getAppKey(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            if (applicationInfo != null) {
                Object obj = applicationInfo.metaData.get(APPKEY);
                if (obj != null) {
                    LogUtil.i(WBAgent.TAG, new StringBuilder("APPKEY: ").append(String.valueOf(obj)).toString());
                    return String.valueOf(obj);
                }
                LogUtil.e(WBAgent.TAG, "Could not read WEIBO_APPKEY meta-data from AndroidManifest.xml.");
            }
        } catch (Exception e) {
            LogUtil.e(WBAgent.TAG, new StringBuilder("Could not read WEIBO_APPKEY meta-data from AndroidManifest.xml.").append(e).toString());
        }
        return null;
    }

    public static String getChannel(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
            if (applicationInfo != null) {
                String string = applicationInfo.metaData.getString(CHANNEL);
                if (string != null) {
                    LogUtil.i(WBAgent.TAG, new StringBuilder("CHANNEL: ").append(string.trim()).toString());
                    return string.trim();
                }
                LogUtil.e(WBAgent.TAG, "Could not read WEIBO_CHANNEL meta-data from AndroidManifest.xml.");
            }
        } catch (Exception e) {
            LogUtil.e(WBAgent.TAG, new StringBuilder("Could not read WEIBO_CHANNEL meta-data from AndroidManifest.xml.").append(e).toString());
        }
        return null;
    }

    public static String getVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            LogUtil.i(WBAgent.TAG, new StringBuilder("versionName: ").append(packageInfo.versionName).toString());
            return packageInfo.versionName;
        } catch (NameNotFoundException e) {
            LogUtil.e(WBAgent.TAG, new StringBuilder("Could not read versionName from AndroidManifest.xml.").append(e).toString());
            return null;
        }
    }

    public static String getPageLogs(List<PageLog> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (PageLog pageLog : list) {
            stringBuilder.append(getLogInfo(pageLog).toString()).append(MiPushClient.ACCEPT_TIME_SEPARATOR);
        }
        return stringBuilder.toString();
    }

    private static JSONObject getLogInfo(PageLog pageLog) {
        JSONObject jSONObject = new JSONObject();
        try {
            switch ($SWITCH_TABLE$com$sina$weibo$sdk$statistic$LogType()[pageLog.getType().ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    jSONObject.put(KEY_TYPE, 0);
                    jSONObject.put(KEY_TIME, pageLog.getStartTime() / 1000);
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    jSONObject.put(KEY_TYPE, 1);
                    jSONObject.put(KEY_TIME, pageLog.getEndTime() / 1000);
                    jSONObject.put(KEY_DURATION, pageLog.getDuration() / 1000);
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    jSONObject.put(KEY_TYPE, XZBDevice.DOWNLOAD_LIST_RECYCLE);
                    jSONObject.put(KEY_PAGE_ID, pageLog.getPage_id());
                    jSONObject.put(KEY_TIME, pageLog.getStartTime() / 1000);
                    jSONObject.put(KEY_DURATION, pageLog.getDuration() / 1000);
                    break;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    jSONObject.put(KEY_TYPE, XZBDevice.DOWNLOAD_LIST_FAILED);
                    jSONObject.put(KEY_PAGE_ID, pageLog.getPage_id());
                    jSONObject.put(KEY_TIME, pageLog.getStartTime() / 1000);
                    addEventData(jSONObject, (EventLog) pageLog);
                    break;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    jSONObject.put(KEY_TYPE, XZBDevice.DOWNLOAD_LIST_ALL);
                    jSONObject.put(KEY_PAGE_ID, pageLog.getPage_id());
                    jSONObject.put(KEY_TIME, pageLog.getStartTime() / 1000);
                    jSONObject.put(KEY_DURATION, pageLog.getDuration() / 1000);
                    break;
            }
        } catch (Exception e) {
            LogUtil.e(WBAgent.TAG, new StringBuilder("get page log error.").append(e).toString());
        }
        return jSONObject;
    }

    private static JSONObject addEventData(JSONObject jSONObject, EventLog eventLog) {
        try {
            jSONObject.put(KEY_EVENT_ID, eventLog.getEvent_id());
            if (eventLog.getExtend() != null) {
                Map extend = eventLog.getExtend();
                StringBuilder stringBuilder = new StringBuilder();
                int i = 0;
                for (String str : extend.keySet()) {
                    if (i >= 10) {
                        break;
                    } else if (!TextUtils.isEmpty((CharSequence) extend.get(str))) {
                        if (stringBuilder.length() > 0) {
                            stringBuilder.append("|");
                        }
                        stringBuilder.append(str).append(":").append((String) extend.get(str));
                        i++;
                    }
                }
                jSONObject.put(KEY_EXTEND, stringBuilder.toString());
            }
        } catch (Exception e) {
            LogUtil.e(WBAgent.TAG, new StringBuilder("add event log error.").append(e).toString());
        }
        return jSONObject;
    }

    public static List<JSONArray> getValidUploadLogs(String str) {
        Object buildUploadLogs = buildUploadLogs(str);
        if (TextUtils.isEmpty(buildUploadLogs)) {
            return null;
        }
        List<JSONArray> arrayList = new ArrayList();
        JSONArray jSONArray = new JSONArray();
        long currentTimeMillis = System.currentTimeMillis();
        try {
            JSONArray jSONArray2 = new JSONObject(buildUploadLogs).getJSONArray("applogs");
            int i = 0;
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                JSONObject jSONObject = jSONArray2.getJSONObject(i2);
                if (isDataValid(currentTimeMillis, jSONObject.getLong(KEY_TIME) * 1000)) {
                    if (i < 500) {
                        jSONArray.put(jSONObject);
                        i++;
                    } else {
                        arrayList.add(jSONArray);
                        jSONArray = new JSONArray();
                        i = 0;
                    }
                }
            }
            if (jSONArray.length() > 0) {
                arrayList.add(jSONArray);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    private static String buildUploadLogs(String str) {
        Object appLogs = LogFileUtil.getAppLogs(LogFileUtil.getAppLogPath(LogFileUtil.ANALYTICS_FILE_NAME));
        if (TextUtils.isEmpty(appLogs) && TextUtils.isEmpty(str)) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{applogs:[");
        if (!TextUtils.isEmpty(appLogs)) {
            stringBuilder.append(appLogs);
        }
        if (!TextUtils.isEmpty(str)) {
            stringBuilder.append(str);
        }
        if (stringBuilder.charAt(stringBuilder.length() - 1) == ',') {
            stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), a.d);
        }
        stringBuilder.append("]}");
        return stringBuilder.toString();
    }

    private static boolean isDataValid(long j, long j2) {
        return j - j2 < 86400000;
    }
}
