package com.sina.weibo.sdk.statistic;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.MD5;
import com.sina.weibo.sdk.utils.Utility;
import com.taobao.accs.common.Constants;
import com.tencent.open.GameAppOperation;
import com.tencent.stat.DeviceInfo;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class LogReport {
    private static final int CONNECTION_TIMEOUT = 25000;
    private static final String PRIVATE_CODE = "dqwef1864il4c9m6";
    private static final int SOCKET_TIMEOUT = 20000;
    private static String UPLOADTIME;
    private static String mAid;
    private static String mAppkey;
    private static String mBaseUrl;
    private static String mChannel;
    private static String mKeyHash;
    public static LogReport mLogReport;
    private static String mPackageName;
    private static JSONObject mParams;
    private static String mVersionName;

    static {
        UPLOADTIME = "uploadtime";
        mBaseUrl = "https://api.weibo.com/2/proxy/sdk/statistic.json";
    }

    public LogReport(Context context) {
        try {
            if (mPackageName == null) {
                mPackageName = context.getPackageName();
            }
            mAppkey = StatisticConfig.getAppkey(context);
            checkAid(context);
            mKeyHash = Utility.getSign(context, mPackageName);
            mVersionName = LogBuilder.getVersion(context);
            mChannel = StatisticConfig.getChannel(context);
        } catch (Exception e) {
            LogUtil.e(WBAgent.TAG, e.toString());
        }
        initCommonParams();
    }

    private static JSONObject initCommonParams() {
        if (mParams == null) {
            mParams = new JSONObject();
        }
        try {
            mParams.put(Constants.SP_KEY_APPKEY, mAppkey);
            mParams.put(com.tencent.connect.common.Constants.PARAM_PLATFORM, SocializeConstants.OS);
            mParams.put(LogBuilder.KEY_PACKAGE_NAME, mPackageName);
            mParams.put(LogBuilder.KEY_HASH, mKeyHash);
            mParams.put(GameAppOperation.QQFAV_DATALINE_VERSION, mVersionName);
            mParams.put(LogBuilder.KEY_CHANNEL, mChannel);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mParams;
    }

    private static void checkAid(Context context) {
        if (TextUtils.isEmpty(mAid)) {
            mAid = Utility.getAid(context, mAppkey);
        }
        if (mParams == null) {
            mParams = new JSONObject();
        }
        try {
            mParams.put(DeviceInfo.TAG_ANDROID_ID, mAid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setPackageName(String str) {
        mPackageName = str;
    }

    public static String getPackageName() {
        return mPackageName;
    }

    public static synchronized void uploadAppLogs(Context context, String str) {
        synchronized (LogReport.class) {
            if (mLogReport == null) {
                mLogReport = new LogReport(context);
            }
            if (isNetworkConnected(context)) {
                List<JSONArray> validUploadLogs = LogBuilder.getValidUploadLogs(str);
                if (validUploadLogs == null) {
                    LogUtil.i(WBAgent.TAG, "applogs is null");
                } else {
                    List<JSONArray> arrayList = new ArrayList();
                    checkAid(context);
                    for (JSONArray jSONArray : validUploadLogs) {
                        HttpResponse requestHttpExecute = requestHttpExecute(mBaseUrl, com.tencent.connect.common.Constants.HTTP_POST, mParams, jSONArray);
                        if (requestHttpExecute == null || requestHttpExecute.getStatusLine().getStatusCode() != 200) {
                            arrayList.add(jSONArray);
                            LogUtil.e(WBAgent.TAG, "upload applogs error");
                        } else {
                            updateTime(context, Long.valueOf(System.currentTimeMillis()));
                        }
                    }
                    LogFileUtil.delete(LogFileUtil.getAppLogPath(LogFileUtil.ANALYTICS_FILE_NAME));
                    if (arrayList.size() > 0) {
                        for (JSONArray jSONArray2 : arrayList) {
                            LogFileUtil.writeToFile(LogFileUtil.getAppLogPath(LogFileUtil.ANALYTICS_FILE_NAME), jSONArray2.toString(), true);
                            LogUtil.d(WBAgent.TAG, "save failed_log");
                        }
                    }
                }
            } else {
                LogUtil.i(WBAgent.TAG, "network is not connected");
                LogFileUtil.writeToFile(LogFileUtil.getAppLogPath(LogFileUtil.ANALYTICS_FILE_NAME), str, true);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.apache.http.HttpResponse requestHttpExecute(java.lang.String r8, java.lang.String r9, org.json.JSONObject r10, org.json.JSONArray r11) {
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.statistic.LogReport.requestHttpExecute(java.lang.String, java.lang.String, org.json.JSONObject, org.json.JSONArray):org.apache.http.HttpResponse");
        /*
        r0 = 0;
        r3 = com.sina.weibo.sdk.net.HttpManager.getNewHttpClient();	 Catch:{ UnsupportedEncodingException -> 0x0181, ClientProtocolException -> 0x017a, IOException -> 0x0146, all -> 0x0156 }
        if (r10 != 0) goto L_0x000b;
    L_0x0007:
        r10 = initCommonParams();	 Catch:{ UnsupportedEncodingException -> 0x00bc, ClientProtocolException -> 0x017e, IOException -> 0x0175, all -> 0x016f }
    L_0x000b:
        r1 = "time";
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ JSONException -> 0x00b7 }
        r6 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r4 = r4 / r6;
        r10.put(r1, r4);	 Catch:{ JSONException -> 0x00b7 }
        r1 = "length";
        r2 = r11.length();	 Catch:{ JSONException -> 0x00b7 }
        r10.put(r1, r2);	 Catch:{ JSONException -> 0x00b7 }
        r1 = "sign";
        r2 = "aid";
        r2 = r10.getString(r2);	 Catch:{ JSONException -> 0x00b7 }
        r4 = "appkey";
        r4 = r10.getString(r4);	 Catch:{ JSONException -> 0x00b7 }
        r5 = "time";
        r6 = r10.getLong(r5);	 Catch:{ JSONException -> 0x00b7 }
        r2 = getSign(r2, r4, r6);	 Catch:{ JSONException -> 0x00b7 }
        r10.put(r1, r2);	 Catch:{ JSONException -> 0x00b7 }
        r1 = "content";
        r10.put(r1, r11);	 Catch:{ JSONException -> 0x00b7 }
        r1 = "WBAgent";
        r2 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x00b7 }
        r4 = "post content--- ";
        r2.<init>(r4);	 Catch:{ JSONException -> 0x00b7 }
        r4 = r10.toString();	 Catch:{ JSONException -> 0x00b7 }
        r2 = r2.append(r4);	 Catch:{ JSONException -> 0x00b7 }
        r2 = r2.toString();	 Catch:{ JSONException -> 0x00b7 }
        com.sina.weibo.sdk.utils.LogUtil.d(r1, r2);	 Catch:{ JSONException -> 0x00b7 }
    L_0x0061:
        r1 = "GET";
        r1 = r9.equals(r1);	 Catch:{ UnsupportedEncodingException -> 0x00bc, ClientProtocolException -> 0x017e, IOException -> 0x0175, all -> 0x016f }
        if (r1 == 0) goto L_0x00ca;
    L_0x006a:
        r1 = new java.lang.StringBuilder;	 Catch:{ UnsupportedEncodingException -> 0x00bc, ClientProtocolException -> 0x017e, IOException -> 0x0175, all -> 0x016f }
        r2 = java.lang.String.valueOf(r8);	 Catch:{ UnsupportedEncodingException -> 0x00bc, ClientProtocolException -> 0x017e, IOException -> 0x0175, all -> 0x016f }
        r1.<init>(r2);	 Catch:{ UnsupportedEncodingException -> 0x00bc, ClientProtocolException -> 0x017e, IOException -> 0x0175, all -> 0x016f }
        r2 = "?";
        r1 = r1.append(r2);	 Catch:{ UnsupportedEncodingException -> 0x00bc, ClientProtocolException -> 0x017e, IOException -> 0x0175, all -> 0x016f }
        r2 = r10.toString();	 Catch:{ UnsupportedEncodingException -> 0x00bc, ClientProtocolException -> 0x017e, IOException -> 0x0175, all -> 0x016f }
        r1 = r1.append(r2);	 Catch:{ UnsupportedEncodingException -> 0x00bc, ClientProtocolException -> 0x017e, IOException -> 0x0175, all -> 0x016f }
        r2 = r1.toString();	 Catch:{ UnsupportedEncodingException -> 0x00bc, ClientProtocolException -> 0x017e, IOException -> 0x0175, all -> 0x016f }
        r1 = new org.apache.http.client.methods.HttpGet;	 Catch:{ UnsupportedEncodingException -> 0x00bc, ClientProtocolException -> 0x017e, IOException -> 0x0175, all -> 0x016f }
        r1.<init>(r2);	 Catch:{ UnsupportedEncodingException -> 0x00bc, ClientProtocolException -> 0x017e, IOException -> 0x0175, all -> 0x016f }
        r2 = r0;
    L_0x008c:
        r0 = r3.execute(r1);	 Catch:{ UnsupportedEncodingException -> 0x012a, ClientProtocolException -> 0x0138, IOException -> 0x0178 }
        r1 = "WBAgent";
        r4 = new java.lang.StringBuilder;	 Catch:{ UnsupportedEncodingException -> 0x012a, ClientProtocolException -> 0x0138, IOException -> 0x0178 }
        r5 = "status code = ";
        r4.<init>(r5);	 Catch:{ UnsupportedEncodingException -> 0x012a, ClientProtocolException -> 0x0138, IOException -> 0x0178 }
        r5 = r0.getStatusLine();	 Catch:{ UnsupportedEncodingException -> 0x012a, ClientProtocolException -> 0x0138, IOException -> 0x0178 }
        r5 = r5.getStatusCode();	 Catch:{ UnsupportedEncodingException -> 0x012a, ClientProtocolException -> 0x0138, IOException -> 0x0178 }
        r4 = r4.append(r5);	 Catch:{ UnsupportedEncodingException -> 0x012a, ClientProtocolException -> 0x0138, IOException -> 0x0178 }
        r4 = r4.toString();	 Catch:{ UnsupportedEncodingException -> 0x012a, ClientProtocolException -> 0x0138, IOException -> 0x0178 }
        com.sina.weibo.sdk.utils.LogUtil.i(r1, r4);	 Catch:{ UnsupportedEncodingException -> 0x012a, ClientProtocolException -> 0x0138, IOException -> 0x0178 }
        if (r2 == 0) goto L_0x00b3;
    L_0x00b0:
        r2.close();	 Catch:{ IOException -> 0x016c }
    L_0x00b3:
        shutdownHttpClient(r3);
    L_0x00b6:
        return r0;
    L_0x00b7:
        r1 = move-exception;
        r1.printStackTrace();	 Catch:{ UnsupportedEncodingException -> 0x00bc, ClientProtocolException -> 0x017e, IOException -> 0x0175, all -> 0x016f }
        goto L_0x0061;
    L_0x00bc:
        r1 = move-exception;
        r2 = r0;
    L_0x00be:
        r1.printStackTrace();	 Catch:{ all -> 0x0173 }
        if (r2 == 0) goto L_0x00c6;
    L_0x00c3:
        r2.close();	 Catch:{ IOException -> 0x0163 }
    L_0x00c6:
        shutdownHttpClient(r3);
        goto L_0x00b6;
    L_0x00ca:
        r1 = "POST";
        r1 = r9.equals(r1);	 Catch:{ UnsupportedEncodingException -> 0x00bc, ClientProtocolException -> 0x017e, IOException -> 0x0175, all -> 0x016f }
        if (r1 == 0) goto L_0x0186;
    L_0x00d3:
        r1 = mAppkey;	 Catch:{ UnsupportedEncodingException -> 0x00bc, ClientProtocolException -> 0x017e, IOException -> 0x0175, all -> 0x016f }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ UnsupportedEncodingException -> 0x00bc, ClientProtocolException -> 0x017e, IOException -> 0x0175, all -> 0x016f }
        if (r1 == 0) goto L_0x00e8;
    L_0x00db:
        r1 = "WBAgent";
        r2 = "unexpected null AppKey";
        com.sina.weibo.sdk.utils.LogUtil.e(r1, r2);	 Catch:{ UnsupportedEncodingException -> 0x00bc, ClientProtocolException -> 0x017e, IOException -> 0x0175, all -> 0x016f }
        shutdownHttpClient(r3);
        goto L_0x00b6;
    L_0x00e8:
        r1 = new java.lang.StringBuilder;	 Catch:{ UnsupportedEncodingException -> 0x00bc, ClientProtocolException -> 0x017e, IOException -> 0x0175, all -> 0x016f }
        r2 = java.lang.String.valueOf(r8);	 Catch:{ UnsupportedEncodingException -> 0x00bc, ClientProtocolException -> 0x017e, IOException -> 0x0175, all -> 0x016f }
        r1.<init>(r2);	 Catch:{ UnsupportedEncodingException -> 0x00bc, ClientProtocolException -> 0x017e, IOException -> 0x0175, all -> 0x016f }
        r2 = "?source=";
        r1 = r1.append(r2);	 Catch:{ UnsupportedEncodingException -> 0x00bc, ClientProtocolException -> 0x017e, IOException -> 0x0175, all -> 0x016f }
        r2 = mAppkey;	 Catch:{ UnsupportedEncodingException -> 0x00bc, ClientProtocolException -> 0x017e, IOException -> 0x0175, all -> 0x016f }
        r1 = r1.append(r2);	 Catch:{ UnsupportedEncodingException -> 0x00bc, ClientProtocolException -> 0x017e, IOException -> 0x0175, all -> 0x016f }
        r1 = r1.toString();	 Catch:{ UnsupportedEncodingException -> 0x00bc, ClientProtocolException -> 0x017e, IOException -> 0x0175, all -> 0x016f }
        r1 = getNewHttpPost(r1, r10);	 Catch:{ UnsupportedEncodingException -> 0x00bc, ClientProtocolException -> 0x017e, IOException -> 0x0175, all -> 0x016f }
        r2 = new java.io.ByteArrayOutputStream;	 Catch:{ UnsupportedEncodingException -> 0x00bc, ClientProtocolException -> 0x017e, IOException -> 0x0175, all -> 0x016f }
        r2.<init>();	 Catch:{ UnsupportedEncodingException -> 0x00bc, ClientProtocolException -> 0x017e, IOException -> 0x0175, all -> 0x016f }
        r4 = com.sina.weibo.sdk.statistic.StatisticConfig.isNeedGizp();	 Catch:{ UnsupportedEncodingException -> 0x012a, ClientProtocolException -> 0x0138, IOException -> 0x0178 }
        if (r4 == 0) goto L_0x012c;
    L_0x0111:
        r4 = r10.toString();	 Catch:{ UnsupportedEncodingException -> 0x012a, ClientProtocolException -> 0x0138, IOException -> 0x0178 }
        r4 = gzipLogs(r4);	 Catch:{ UnsupportedEncodingException -> 0x012a, ClientProtocolException -> 0x0138, IOException -> 0x0178 }
        r2.write(r4);	 Catch:{ UnsupportedEncodingException -> 0x012a, ClientProtocolException -> 0x0138, IOException -> 0x0178 }
    L_0x011c:
        r4 = new org.apache.http.entity.ByteArrayEntity;	 Catch:{ UnsupportedEncodingException -> 0x012a, ClientProtocolException -> 0x0138, IOException -> 0x0178 }
        r5 = r2.toByteArray();	 Catch:{ UnsupportedEncodingException -> 0x012a, ClientProtocolException -> 0x0138, IOException -> 0x0178 }
        r4.<init>(r5);	 Catch:{ UnsupportedEncodingException -> 0x012a, ClientProtocolException -> 0x0138, IOException -> 0x0178 }
        r1.setEntity(r4);	 Catch:{ UnsupportedEncodingException -> 0x012a, ClientProtocolException -> 0x0138, IOException -> 0x0178 }
        goto L_0x008c;
    L_0x012a:
        r1 = move-exception;
        goto L_0x00be;
    L_0x012c:
        r4 = r10.toString();	 Catch:{ UnsupportedEncodingException -> 0x012a, ClientProtocolException -> 0x0138, IOException -> 0x0178 }
        r4 = r4.getBytes();	 Catch:{ UnsupportedEncodingException -> 0x012a, ClientProtocolException -> 0x0138, IOException -> 0x0178 }
        r2.write(r4);	 Catch:{ UnsupportedEncodingException -> 0x012a, ClientProtocolException -> 0x0138, IOException -> 0x0178 }
        goto L_0x011c;
    L_0x0138:
        r1 = move-exception;
    L_0x0139:
        r1.printStackTrace();	 Catch:{ all -> 0x0173 }
        if (r2 == 0) goto L_0x0141;
    L_0x013e:
        r2.close();	 Catch:{ IOException -> 0x0166 }
    L_0x0141:
        shutdownHttpClient(r3);
        goto L_0x00b6;
    L_0x0146:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
    L_0x0149:
        r1.printStackTrace();	 Catch:{ all -> 0x0173 }
        if (r2 == 0) goto L_0x0151;
    L_0x014e:
        r2.close();	 Catch:{ IOException -> 0x0168 }
    L_0x0151:
        shutdownHttpClient(r3);
        goto L_0x00b6;
    L_0x0156:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r0 = r1;
    L_0x015a:
        if (r2 == 0) goto L_0x015f;
    L_0x015c:
        r2.close();	 Catch:{ IOException -> 0x016a }
    L_0x015f:
        shutdownHttpClient(r3);
        throw r0;
    L_0x0163:
        r1 = move-exception;
        goto L_0x00c6;
    L_0x0166:
        r1 = move-exception;
        goto L_0x0141;
    L_0x0168:
        r1 = move-exception;
        goto L_0x0151;
    L_0x016a:
        r1 = move-exception;
        goto L_0x015f;
    L_0x016c:
        r1 = move-exception;
        goto L_0x00b3;
    L_0x016f:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x015a;
    L_0x0173:
        r0 = move-exception;
        goto L_0x015a;
    L_0x0175:
        r1 = move-exception;
        r2 = r0;
        goto L_0x0149;
    L_0x0178:
        r1 = move-exception;
        goto L_0x0149;
    L_0x017a:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        goto L_0x0139;
    L_0x017e:
        r1 = move-exception;
        r2 = r0;
        goto L_0x0139;
    L_0x0181:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        goto L_0x00be;
    L_0x0186:
        r1 = r0;
        r2 = r0;
        goto L_0x008c;
        */
    }

    private static boolean isNetworkConnected(Context context) {
        if (context == null) {
            LogUtil.e(WBAgent.TAG, "unexpected null context in isNetworkConnected");
            return false;
        } else if (context.getPackageManager().checkPermission(MsgConstant.PERMISSION_ACCESS_NETWORK_STATE, context.getPackageName()) != 0) {
            return false;
        } else {
            NetworkInfo activeNetworkInfo;
            try {
                activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            } catch (NullPointerException e) {
                activeNetworkInfo = null;
            }
            return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
        }
    }

    private static synchronized HttpPost getNewHttpPost(String str, JSONObject jSONObject) {
        HttpPost httpPost;
        synchronized (LogReport.class) {
            httpPost = new HttpPost(str);
            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            httpPost.setHeader(HttpConstant.CONNECTION, "Keep-Alive");
            httpPost.addHeader(HttpConstant.CONTENT_ENCODING, StatisticConfig.isNeedGizp() ? HttpConstant.GZIP : "charset=UTF-8");
            httpPost.addHeader(HttpConstant.ACCEPT, "*/*");
            httpPost.addHeader("Accept-Language", "en-us");
            httpPost.addHeader(HttpConstant.ACCEPT_ENCODING, HttpConstant.GZIP);
        }
        return httpPost;
    }

    private static String getSign(String str, String str2, long j) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            stringBuilder.append(str);
        }
        stringBuilder.append(str2).append(PRIVATE_CODE).append(j);
        String hexdigest = MD5.hexdigest(stringBuilder.toString());
        hexdigest = hexdigest.substring(hexdigest.length() - 6);
        String hexdigest2 = MD5.hexdigest(new StringBuilder(String.valueOf(hexdigest)).append(hexdigest.substring(0, XZBDevice.DOWNLOAD_LIST_ALL)).toString());
        return new StringBuilder(String.valueOf(hexdigest)).append(hexdigest2.substring(hexdigest2.length() - 1)).toString();
    }

    private static byte[] gzipLogs(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bytes = str.getBytes("utf-8");
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bytes);
            gZIPOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static long getTime(Context context) {
        return context.getSharedPreferences(UPLOADTIME, 0).getLong("lasttime", 0);
    }

    private static void updateTime(Context context, Long l) {
        Editor edit = context.getSharedPreferences(UPLOADTIME, 0).edit();
        edit.putLong("lasttime", l.longValue());
        edit.commit();
    }

    private static void shutdownHttpClient(HttpClient httpClient) {
        if (httpClient != null) {
            try {
                httpClient.getConnectionManager().closeExpiredConnections();
            } catch (Exception e) {
            }
        }
    }
}
