package com.mediav.ads.sdk.log;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.alipay.sdk.data.a;
import com.mediav.ads.sdk.adcore.Config;
import com.sina.weibo.sdk.component.GameManager;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class LogUploader {
    private static final int DAILY_MAX_LIMIT = 100;
    private static final String ERROR_LOG_KEY = "mvadsdkerrordaycheck";
    private static int logid;

    static {
        logid = 0;
    }

    public static synchronized void postLog(HashMap<String, String> hashMap, Context context, boolean z) {
        synchronized (LogUploader.class) {
            if (z) {
                logid++;
                hashMap.put("elogid", logid);
            }
            incLogCount(context);
            if (checkLimit(context)) {
                MVLog.d("\u4e0a\u4f20LOG");
                if (Utils.isNetEnable() && !postData(Config.ERROR_LOG_URL, hashMap) && z) {
                    LogFileManager.saveLog(hashMap);
                }
            } else {
                MVLog.d("\u4e0a\u4f20LOG\u6570\u5df2\u8d85\u8fc7\u4e0a\u9650\uff0c\u53d6\u6d88\u4e0a\u4f20");
            }
        }
    }

    private static void incLogCount(Context context) {
        String toString = new StringBuilder(ParamKey.COUNT).append(new SimpleDateFormat("yyyyMMdd").format(Long.valueOf(System.currentTimeMillis()))).toString();
        context.getSharedPreferences(ERROR_LOG_KEY, 0).edit().putInt(toString, context.getSharedPreferences(ERROR_LOG_KEY, 0).getInt(toString, 0) + 1).commit();
    }

    private static boolean checkLimit(Context context) {
        String toString = new StringBuilder(ParamKey.COUNT).append(new SimpleDateFormat("yyyyMMdd").format(Long.valueOf(System.currentTimeMillis()))).toString();
        int i = context.getSharedPreferences(ERROR_LOG_KEY, 0).getInt(toString, -1);
        if (i >= 0) {
            return i < 100;
        } else {
            Editor edit = context.getSharedPreferences(ERROR_LOG_KEY, 0).edit();
            edit.clear();
            edit.putInt(toString, 0);
            edit.commit();
            return true;
        }
    }

    public static boolean postData(String str, HashMap<String, String> hashMap) {
        HttpUriRequest httpPost = new HttpPost(str);
        List arrayList = new ArrayList();
        if (hashMap != null) {
            for (Entry entry : hashMap.entrySet()) {
                arrayList.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
            }
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(arrayList, GameManager.DEFAULT_CHARSET));
            HttpClient defaultHttpClient = new DefaultHttpClient();
            defaultHttpClient.getParams().setParameter("http.connection.timeout", Integer.valueOf(a.d));
            defaultHttpClient.getParams().setParameter("http.socket.timeout", Integer.valueOf(a.d));
            HttpResponse execute = defaultHttpClient.execute(httpPost);
            if (execute.getStatusLine().getStatusCode() == 200) {
                return true;
            }
            MVLog.d(new StringBuilder("POST\u5f02\u5e38:Code=").append(execute.getStatusLine().getStatusCode()).toString());
            return false;
        } catch (Exception e) {
            MVLog.d(new StringBuilder("POST\u5f02\u5e38:").append(e.getMessage()).toString());
        }
    }
}
