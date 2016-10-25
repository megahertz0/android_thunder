package com.umeng.message;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.taobao.accs.common.Constants;
import com.tencent.bugly.Bugly;
import com.umeng.a;
import com.umeng.common.UmLog;
import com.umeng.message.proguard.k;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import java.util.Calendar;
import org.android.agoo.common.b;
import org.android.agoo.message.MessageService;

public class MessageSharedPrefs {
    private static final String a;
    private static MessageSharedPrefs c = null;
    private static final String d = "tempkey";
    private static final String e = "tempvalue";
    private Context b;

    static {
        a = MessageSharedPrefs.class.getName();
    }

    private MessageSharedPrefs(Context context) {
        this.b = context;
    }

    public static synchronized MessageSharedPrefs getInstance(Context context) {
        MessageSharedPrefs messageSharedPrefs;
        synchronized (MessageSharedPrefs.class) {
            if (c == null) {
                c = new MessageSharedPrefs(context);
            }
            messageSharedPrefs = c;
        }
        return messageSharedPrefs;
    }

    public void setAppLaunchLogSentAt(long j) {
        setKeyAndValue(MsgConstant.KEY_LAUNCH_LOG_SENT_MARK, Calendar.getInstance().getTimeInMillis());
    }

    public long getAppLaunchLogSentAt() {
        String tempValue = getTempValue(MsgConstant.KEY_LAUNCH_LOG_SENT_MARK, MessageService.MSG_DB_READY_REPORT);
        return (tempValue == null || tempValue.equals(a.d)) ? 0 : Long.getLong(tempValue, 0).longValue();
    }

    public boolean hasAppLaunchLogSentToday() {
        Calendar instance = Calendar.getInstance();
        try {
            instance.setTimeInMillis(k.a(this.b).f());
        } catch (Exception e) {
            e.printStackTrace();
            e.toString();
        }
        Calendar instance2 = Calendar.getInstance();
        return instance.get(R.styleable.Toolbar_contentInsetEnd) == instance2.get(R.styleable.Toolbar_contentInsetEnd) && instance.get(1) == instance2.get(1);
    }

    public void setDisplayNotificationNumber(int i) {
        setKeyAndValue(MsgConstant.KEY_NOTIFICATION_NUMBER, String.valueOf(i));
    }

    public int getDisplayNotificationNumber() {
        return Integer.valueOf(getTempValue(MsgConstant.KEY_NOTIFICATION_NUMBER, MessageService.MSG_DB_NOTIFY_REACHED)).intValue();
    }

    public void setMessageAppKey(String str) {
        setKeyAndValue(MsgConstant.KEY_UMENG_MESSAGE_APP_KEY, str);
    }

    public void removeMessageAppKey() {
        removeKeyAndValue(MsgConstant.KEY_UMENG_MESSAGE_APP_KEY);
    }

    public String getMessageAppKey() {
        return getTempValue(MsgConstant.KEY_UMENG_MESSAGE_APP_KEY, a.d);
    }

    public void setMessageAppSecret(String str) {
        setKeyAndValue(MsgConstant.KEY_UMENG_MESSAGE_APP_SECRET, str);
    }

    public void removeMessageAppSecret() {
        removeKeyAndValue(MsgConstant.KEY_UMENG_MESSAGE_APP_SECRET);
    }

    public String getMessageAppSecret() {
        return getTempValue(MsgConstant.KEY_UMENG_MESSAGE_APP_SECRET, a.d);
    }

    public void setMessageChannel(String str) {
        setKeyAndValue(MsgConstant.KEY_UMENG_MESSAGE_APP_CHANNEL, str);
    }

    public String getMessageChannel() {
        return getTempValue(MsgConstant.KEY_UMENG_MESSAGE_APP_CHANNEL, a.d);
    }

    public void setAppLaunchLogSendPolicy(int i) {
        setKeyAndValue(MsgConstant.KEY_APP_LAUNCH_LOG_SEND_POLICY, String.valueOf(i));
    }

    public void setTagSendPolicy(int i) {
        setKeyAndValue(MsgConstant.KEY_TAG_SEND_POLICY, String.valueOf(i));
    }

    public int getAppLaunchLogSendPolicy() {
        return Integer.valueOf(getTempValue(MsgConstant.KEY_APP_LAUNCH_LOG_SEND_POLICY, WeiboAuthException.DEFAULT_AUTH_ERROR_CODE)).intValue();
    }

    public int getTagSendPolicy() {
        return Integer.valueOf(getTempValue(MsgConstant.KEY_TAG_SEND_POLICY, WeiboAuthException.DEFAULT_AUTH_ERROR_CODE)).intValue();
    }

    public void addAlias(String str, String str2, int i, int i2, String str3) {
        String str4 = "alias=? and type=? and exclusive=?";
        String[] strArr = new String[]{str, str2, String.valueOf(i)};
        ContentResolver contentResolver = this.b.getContentResolver();
        com.umeng.message.provider.a.a(this.b);
        Cursor query = contentResolver.query(com.umeng.message.provider.a.d, new String[]{"error"}, str4, strArr, "time desc");
        ContentValues contentValues;
        ContentResolver contentResolver2;
        if (query == null) {
            contentValues = new ContentValues();
            contentValues.put("time", Long.valueOf(System.currentTimeMillis()));
            contentValues.put(JsInterface.FUNPLAY_AD_TRPE, str2);
            contentValues.put(MsgConstant.KEY_ALIAS, str);
            contentValues.put("exclusive", Integer.valueOf(i));
            contentValues.put("error", Integer.valueOf(i2));
            contentValues.put(Constants.SHARED_MESSAGE_ID_FILE, str3);
            contentResolver2 = this.b.getContentResolver();
            com.umeng.message.provider.a.a(this.b);
            contentResolver2.insert(com.umeng.message.provider.a.d, contentValues);
        } else if (query.getCount() > 0) {
            query.moveToFirst();
            contentValues = new ContentValues();
            contentValues.put("time", Long.valueOf(System.currentTimeMillis()));
            contentValues.put(JsInterface.FUNPLAY_AD_TRPE, str2);
            contentValues.put(MsgConstant.KEY_ALIAS, str);
            contentValues.put("exclusive", Integer.valueOf(i));
            contentValues.put("error", Integer.valueOf(i2));
            contentValues.put(Constants.SHARED_MESSAGE_ID_FILE, str3);
            contentResolver2 = this.b.getContentResolver();
            com.umeng.message.provider.a.a(this.b);
            contentResolver2.update(com.umeng.message.provider.a.d, contentValues, str4, strArr);
        } else {
            contentValues = new ContentValues();
            contentValues.put("time", Long.valueOf(System.currentTimeMillis()));
            contentValues.put(JsInterface.FUNPLAY_AD_TRPE, str2);
            contentValues.put(MsgConstant.KEY_ALIAS, str);
            contentValues.put("exclusive", Integer.valueOf(i));
            contentValues.put("error", Integer.valueOf(i2));
            contentValues.put(Constants.SHARED_MESSAGE_ID_FILE, str3);
            contentResolver2 = this.b.getContentResolver();
            com.umeng.message.provider.a.a(this.b);
            contentResolver2.insert(com.umeng.message.provider.a.d, contentValues);
        }
        if (query != null) {
            query.close();
        }
    }

    public void removeAlias(int i, String str, String str2) {
        String[] strArr = new String[]{str2, str, String.valueOf(i)};
        ContentResolver contentResolver = this.b.getContentResolver();
        com.umeng.message.provider.a.a(this.b);
        contentResolver.delete(com.umeng.message.provider.a.d, "type=? and alias=? and exclusive=? ", strArr);
    }

    public String getLastAlias(int i, String str) {
        String str2;
        String str3 = a.d;
        String[] strArr = new String[]{str, String.valueOf(i)};
        ContentResolver contentResolver = this.b.getContentResolver();
        com.umeng.message.provider.a.a(this.b);
        Cursor query = contentResolver.query(com.umeng.message.provider.a.d, new String[]{MsgConstant.KEY_ALIAS}, "type=? and exclusive=?", strArr, "time desc");
        if (query == null || query.getCount() <= 0) {
            str2 = str3;
        } else {
            query.moveToFirst();
            str2 = query.getString(query.getColumnIndex(MsgConstant.KEY_ALIAS));
        }
        if (query != null) {
            query.close();
        }
        return str2;
    }

    public boolean isAliasSet(int i, String str, String str2) {
        boolean z;
        String[] strArr = new String[]{str2, str, String.valueOf(i), MessageService.MSG_DB_READY_REPORT};
        ContentResolver contentResolver = this.b.getContentResolver();
        com.umeng.message.provider.a.a(this.b);
        Cursor query = contentResolver.query(com.umeng.message.provider.a.d, new String[]{JsInterface.FUNPLAY_AD_TRPE, MsgConstant.KEY_ALIAS}, "type=? and alias=? and exclusive=? and error=?", strArr, null);
        if (query != null && query.getCount() > 0) {
            query.moveToFirst();
            String string = query.getString(query.getColumnIndex(JsInterface.FUNPLAY_AD_TRPE));
            String string2 = query.getString(query.getColumnIndex(MsgConstant.KEY_ALIAS));
            if (string.equalsIgnoreCase(str2) && string2.equalsIgnoreCase(str)) {
                z = true;
                if (query != null) {
                    query.close();
                }
                return z;
            }
        }
        z = false;
        if (query != null) {
            query.close();
        }
        return z;
    }

    public void addTags(String... strArr) {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            String format = String.format("UMENG_TAG_%s", new Object[]{strArr[i]});
            if (!Boolean.valueOf(getTempValue(format, Bugly.SDK_IS_DEV)).booleanValue()) {
                setKeyAndValue(format, "true");
                setKeyAndValue("UMENG_TAG_COUNT", String.valueOf(getTagCount() + 1));
            }
        }
    }

    public void removeTags(String... strArr) {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            String format = String.format("UMENG_TAG_%s", new Object[]{strArr[i]});
            if (Boolean.valueOf(getTempValue(format, Bugly.SDK_IS_DEV)).booleanValue()) {
                removeKeyAndValue(format);
                setKeyAndValue("UMENG_TAG_COUNT", String.valueOf(getTagCount() - 1));
            }
        }
    }

    public boolean isTagSet(String str) {
        return Boolean.valueOf(getTempValue(String.format("UMENG_TAG_%s", new Object[]{str}), Bugly.SDK_IS_DEV)).booleanValue();
    }

    public int getTagCount() {
        return Integer.valueOf(getTempValue("UMENG_TAG_COUNT", MessageService.MSG_DB_READY_REPORT)).intValue();
    }

    public void setTagRemain(int i) {
        setKeyAndValue("UMENG_TAG_REMAIN", String.valueOf(i));
    }

    public int getTagRemain() {
        return Integer.valueOf(getTempValue("UMENG_TAG_REMAIN", "64")).intValue();
    }

    public void resetTags() {
    }

    void a(int i, int i2, int i3, int i4) {
        setKeyAndValue(MsgConstant.KEY_NO_DISTURB_START_HOUR, String.valueOf(i));
        setKeyAndValue(MsgConstant.KEY_NO_DISTURB_START_HOUR, String.valueOf(i2));
        setKeyAndValue(MsgConstant.KEY_NO_DISTURB_START_HOUR, String.valueOf(i3));
        setKeyAndValue(MsgConstant.KEY_NO_DISTURB_START_HOUR, String.valueOf(i4));
    }

    int a() {
        return Integer.valueOf(getTempValue(MsgConstant.KEY_NO_DISTURB_START_HOUR, com.tencent.connect.common.Constants.VIA_REPORT_TYPE_SHARE_TO_TROOPBAR)).intValue();
    }

    int b() {
        return Integer.valueOf(getTempValue(MsgConstant.KEY_NO_DISTURB_START_MINUTE, MessageService.MSG_DB_READY_REPORT)).intValue();
    }

    int c() {
        return Integer.valueOf(getTempValue(MsgConstant.KEY_NO_DISTURB_END_HOUR, MsgConstant.MESSAGE_NOTIFY_ARRIVAL)).intValue();
    }

    int d() {
        return Integer.valueOf(getTempValue(MsgConstant.KEY_NO_DISTURB_END_MINUTE, MessageService.MSG_DB_READY_REPORT)).intValue();
    }

    void e() {
        setKeyAndValue(MsgConstant.KEY_ENEABLED, "true");
    }

    void f() {
        setKeyAndValue(MsgConstant.KEY_ENEABLED, Bugly.SDK_IS_DEV);
    }

    boolean g() {
        return getTempValue(MsgConstant.KEY_ENEABLED, Bugly.SDK_IS_DEV).equalsIgnoreCase("true");
    }

    boolean a(String str) {
        return getTempValue(MsgConstant.KEY_DEVICE_TOKEN, a.d).equalsIgnoreCase(str);
    }

    public void setIsEnabled(boolean z) {
        setKeyAndValue(MsgConstant.KEY_ISENABLED, String.valueOf(z));
    }

    public boolean isEnabled() {
        String tempValue = getTempValue(MsgConstant.KEY_ISENABLED, a.d);
        return tempValue.equalsIgnoreCase("true") || tempValue.equalsIgnoreCase(a.d);
    }

    public boolean hasTransferedCacheFileDataToSQL() {
        return getTempValue(MsgConstant.KEY_CACHE_FILE_TRANSFER_TO_SQL, Bugly.SDK_IS_DEV).equalsIgnoreCase("true");
    }

    public boolean finishTransferedCacheFileDataToSQL() {
        return getTempValue(MsgConstant.KEY_CACHE_FILE_TRANSFER_TO_SQL, "true").equalsIgnoreCase("true");
    }

    public <U extends UmengMessageService> void setPushIntentServiceClass(Class<U> cls) {
        if (cls == null) {
            removeKeyAndValue(MsgConstant.KEY_PUSH_INTENT_SERVICE_CLASSNAME);
        } else {
            setKeyAndValue(MsgConstant.KEY_PUSH_INTENT_SERVICE_CLASSNAME, cls.getName());
        }
    }

    public String getPushIntentServiceClass() {
        String tempValue = getTempValue(MsgConstant.KEY_PUSH_INTENT_SERVICE_CLASSNAME, a.d);
        if (tempValue.equalsIgnoreCase(a.d)) {
            return a.d;
        }
        try {
            Class.forName(tempValue);
            return tempValue;
        } catch (ClassNotFoundException e) {
            return a.d;
        }
    }

    public boolean hasMessageResourceDownloaded(String str) {
        return getTempValue(new StringBuilder(MsgConstant.KEY_MSG_RESOURCE_DOWNLOAD_PREFIX).append(str).toString(), Bugly.SDK_IS_DEV).equals("true");
    }

    public void setMessageResourceDownloaded(String str) {
        setKeyAndValue(new StringBuilder(MsgConstant.KEY_MSG_RESOURCE_DOWNLOAD_PREFIX).append(str).toString(), "true");
    }

    public void removeMessageResouceRecord(String str) {
        removeKeyAndValue(new StringBuilder(MsgConstant.KEY_MSG_RESOURCE_DOWNLOAD_PREFIX).append(str).toString());
    }

    public void setLastMessageMsgID(String str) {
        setKeyAndValue(MsgConstant.KEY_LAST_MSG_ID, str);
    }

    public String getLastMessageMsgID() {
        return getTempValue(MsgConstant.KEY_LAST_MSG_ID, a.d);
    }

    public void setMuteDuration(int i) {
        setKeyAndValue(MsgConstant.KEY_MUTE_DURATION, String.valueOf(i));
    }

    public int getMuteDuration() {
        return Integer.valueOf(getTempValue(MsgConstant.KEY_MUTE_DURATION, "60")).intValue();
    }

    public void setSerialNo(int i) {
        setKeyAndValue(MsgConstant.KEY_SERIA_NO, String.valueOf(i));
    }

    public int getSerialNo() {
        return Integer.valueOf(getTempValue(MsgConstant.KEY_SERIA_NO, MessageService.MSG_DB_NOTIFY_REACHED)).intValue();
    }

    public boolean getNotificaitonOnForeground() {
        return getTempValue(MsgConstant.KEY_SET_NOTIFICATION_ON_FOREGROUND, "true").equals("true");
    }

    public void setNotificaitonOnForeground(boolean z) {
        setKeyAndValue(MsgConstant.KEY_SET_NOTIFICATION_ON_FOREGROUND, String.valueOf(z));
    }

    public String getResourcePackageName() {
        return getTempValue(MsgConstant.KEY_SET_RESOURCE_PACKAGENAME, a.d);
    }

    public void setResourcePackageName(String str) {
        setKeyAndValue(MsgConstant.KEY_SET_RESOURCE_PACKAGENAME, str);
    }

    public int getNotificationPlayVibrate() {
        return Integer.valueOf(getTempValue(MsgConstant.KEY_SET_NOTIFICATION_PLAY_VIBRATE, MessageService.MSG_DB_READY_REPORT)).intValue();
    }

    public void setNotificationPlayVibrate(int i) {
        setKeyAndValue(MsgConstant.KEY_SET_NOTIFICATION_PLAY_VIBRATE, String.valueOf(i));
    }

    public int getNotificationPlayLights() {
        return Integer.valueOf(getTempValue(MsgConstant.KEY_SET_NOTIFICATION_PLAY_LIGHTS, MessageService.MSG_DB_READY_REPORT)).intValue();
    }

    public void setNotificationPlayLights(int i) {
        setKeyAndValue(MsgConstant.KEY_SET_NOTIFICATION_PLAY_LIGHTS, String.valueOf(i));
    }

    public int getNotificationPlaySound() {
        return Integer.valueOf(getTempValue(MsgConstant.KEY_SET_NOTIFICATION_PLAY_SOUND, MessageService.MSG_DB_READY_REPORT)).intValue();
    }

    public void setNotificationPlaySound(int i) {
        setKeyAndValue(MsgConstant.KEY_SET_NOTIFICATION_PLAY_SOUND, String.valueOf(i));
    }

    public void setAppVersion(String str) {
        if (str == null) {
            removeKeyAndValue(b.PROPERTY_APP_VERSION);
        } else {
            setKeyAndValue(b.PROPERTY_APP_VERSION, str);
        }
    }

    public String getAppVersion() {
        return getTempValue(b.PROPERTY_APP_VERSION, a.d);
    }

    public void setDeviceToken(String str) {
        if (str == null) {
            removeKeyAndValue(MsgConstant.KEY_DEVICE_TOKEN);
        } else {
            setKeyAndValue(MsgConstant.KEY_DEVICE_TOKEN, str);
        }
    }

    public String getDeviceToken() {
        return getTempValue(MsgConstant.KEY_DEVICE_TOKEN, a.d);
    }

    public void setLocationInterval(int i) {
        if (i < 2 || i > 1800) {
            UmLog.d("LBS", "The interval of LBS should not be smaller than 2 seconds");
        } else {
            setKeyAndValue(MsgConstant.KEY_LOCATION_INTERVAL, String.valueOf(i));
        }
    }

    public int getLocationInterval() {
        return Integer.valueOf(getTempValue(MsgConstant.KEY_LOCATION_INTERVAL, "600")).intValue();
    }

    public void setHasResgister(boolean z) {
        setKeyAndValue(MsgConstant.KEY_HASREGISTER, String.valueOf(z));
    }

    public boolean getHasRegister() {
        return getTempValue(MsgConstant.KEY_HASREGISTER, Bugly.SDK_IS_DEV).equalsIgnoreCase("true");
    }

    public String getTempValue(String str, String str2) {
        new ContentValues().put(d, str);
        String[] strArr = new String[]{str};
        ContentResolver contentResolver = this.b.getContentResolver();
        com.umeng.message.provider.a.a(this.b);
        Cursor query = contentResolver.query(com.umeng.message.provider.a.c, new String[]{e}, "tempkey=?", strArr, null);
        if (query != null) {
            if (query.moveToFirst()) {
                str2 = query.getString(query.getColumnIndex(e));
            }
            if (query != null) {
                query.close();
            }
        }
        return str2;
    }

    public void setKeyAndValue(String str, String str2) {
        String str3 = "tempkey=?";
        String[] strArr = new String[]{str};
        ContentResolver contentResolver = this.b.getContentResolver();
        com.umeng.message.provider.a.a(this.b);
        Cursor query = contentResolver.query(com.umeng.message.provider.a.c, new String[]{e}, str3, strArr, null);
        ContentValues contentValues;
        ContentResolver contentResolver2;
        if (query == null) {
            contentValues = new ContentValues();
            contentValues.put(d, str);
            contentValues.put(e, str2);
            contentResolver2 = this.b.getContentResolver();
            com.umeng.message.provider.a.a(this.b);
            contentResolver2.insert(com.umeng.message.provider.a.c, contentValues);
        } else if (query.moveToFirst()) {
            contentValues = new ContentValues();
            contentValues.put(e, str2);
            contentResolver2 = this.b.getContentResolver();
            com.umeng.message.provider.a.a(this.b);
            contentResolver2.update(com.umeng.message.provider.a.c, contentValues, str3, strArr);
        } else {
            contentValues = new ContentValues();
            contentValues.put(d, str);
            contentValues.put(e, str2);
            contentResolver2 = this.b.getContentResolver();
            com.umeng.message.provider.a.a(this.b);
            contentResolver2.insert(com.umeng.message.provider.a.c, contentValues);
        }
        if (query != null) {
            query.close();
        }
    }

    public void removeKeyAndValue(String str) {
        new ContentValues().put(d, str);
        ContentResolver contentResolver = this.b.getContentResolver();
        com.umeng.message.provider.a.a(this.b);
        Cursor query = contentResolver.query(com.umeng.message.provider.a.c, new String[]{e}, null, null, null);
        if (query != null) {
            String[] strArr = new String[]{str};
            ContentResolver contentResolver2 = this.b.getContentResolver();
            com.umeng.message.provider.a.a(this.b);
            contentResolver2.delete(com.umeng.message.provider.a.c, "tempkey=?", strArr);
        }
        if (query != null) {
            query.close();
        }
    }
}
