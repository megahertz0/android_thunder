package com.ta.utdid2.aid;

import android.content.Context;
import com.sina.weibo.sdk.component.GameManager;
import com.ta.utdid2.android.utils.DebugUtils;
import com.ta.utdid2.android.utils.NetworkUtils;
import com.umeng.a;
import com.ut.device.AidCallback;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.http.client.methods.HttpPost;

public class AidRequester {
    private static final String AIDFUNCNAME = "/get_aid/";
    private static final String AIDSERVER = "http://hydra.alibaba.com/";
    private static final String NAME_AID = "&aid=";
    private static final String NAME_ID = "&id=";
    private static final String NAME_RESULT_ACTION = "action";
    private static final String NAME_RESULT_AID = "aid";
    private static final String NAME_RESULT_ISERROR = "isError";
    private static final String NAME_RESULT_STATUS = "status";
    private static final String NAME_RESUTL_DATA = "data";
    private static final String NAME_TOKEN = "auth[token]=";
    private static final String NAME_TYPE = "&type=";
    private static final String RSP_ACTION_CHANGED = "changed";
    private static final String RSP_ACTION_NEW = "new";
    private static final String RSP_ACTION_UNCHANGED = "unchanged";
    private static final String RSP_ISERROR_FALSE = "false";
    private static final String RSP_ISERROR_TRUE = "true";
    private static final String RSP_STATUS_INVALID_APP = "404";
    private static final String RSP_STATUS_INVALID_TOKEN = "401";
    private static final String RSP_STATUS_OK = "200";
    private static final int SESSION_TIME_OUT = 1000;
    private static final String TAG;
    private static final String TYPE_UTDID = "utdid";
    private static final int WEAK_SESSION_TIME_OUT = 3000;
    private static AidRequester sAidRequester;
    private Context mContext;
    private Object mLock;

    class PostRestThread extends Thread {
        String mAppName;
        AidCallback mCallback;
        String mOldAid;
        HttpPost mPost;
        String mRspLine;
        String mToken;

        public PostRestThread(HttpPost httpPost) {
            this.mRspLine = a.d;
            this.mToken = a.d;
            this.mPost = httpPost;
        }

        public PostRestThread(HttpPost httpPost, AidCallback aidCallback, String str, String str2, String str3) {
            this.mRspLine = a.d;
            this.mToken = a.d;
            this.mPost = httpPost;
            this.mCallback = aidCallback;
            this.mOldAid = str;
            this.mAppName = str2;
            this.mToken = str3;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.aid.AidRequester.PostRestThread.run():void");
            /*
            this = this;
            r1 = 0;
            r5 = 1002; // 0x3ea float:1.404E-42 double:4.95E-321;
            r0 = r6.mCallback;
            if (r0 == 0) goto L_0x0010;
        L_0x0007:
            r0 = r6.mCallback;
            r2 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
            r3 = r6.mOldAid;
            r0.onAidEventChanged(r2, r3);
        L_0x0010:
            r0 = new org.apache.http.impl.client.DefaultHttpClient;
            r0.<init>();
            r2 = r6.mPost;	 Catch:{ Exception -> 0x0061 }
            r0 = r0.execute(r2);	 Catch:{ Exception -> 0x0061 }
        L_0x001b:
            if (r0 == 0) goto L_0x0075;
        L_0x001d:
            r2 = new java.io.BufferedReader;	 Catch:{ Exception -> 0x0079 }
            r3 = new java.io.InputStreamReader;	 Catch:{ Exception -> 0x0079 }
            r0 = r0.getEntity();	 Catch:{ Exception -> 0x0079 }
            r0 = r0.getContent();	 Catch:{ Exception -> 0x0079 }
            r4 = "UTF-8";
            r4 = java.nio.charset.Charset.forName(r4);	 Catch:{ Exception -> 0x0079 }
            r3.<init>(r0, r4);	 Catch:{ Exception -> 0x0079 }
            r2.<init>(r3);	 Catch:{ Exception -> 0x0079 }
            r1 = r2;
        L_0x0037:
            if (r1 == 0) goto L_0x00a9;
        L_0x0039:
            r0 = r1.readLine();	 Catch:{ Exception -> 0x0096 }
            if (r0 != 0) goto L_0x008c;
        L_0x003f:
            if (r1 == 0) goto L_0x004b;
        L_0x0041:
            r1.close();	 Catch:{ IOException -> 0x00ad }
            r0 = com.ta.utdid2.android.utils.DebugUtils.DBG;	 Catch:{ IOException -> 0x00ad }
            if (r0 == 0) goto L_0x004b;
        L_0x0048:
            TAG;	 Catch:{ IOException -> 0x00ad }
        L_0x004b:
            r0 = r6.mCallback;
            if (r0 != 0) goto L_0x00b8;
        L_0x004f:
            r0 = com.ta.utdid2.aid.AidRequester.this;
            r1 = r0.mLock;
            monitor-enter(r1);
            r0 = com.ta.utdid2.aid.AidRequester.this;	 Catch:{ all -> 0x00b5 }
            r0 = r0.mLock;	 Catch:{ all -> 0x00b5 }
            r0.notifyAll();	 Catch:{ all -> 0x00b5 }
            monitor-exit(r1);	 Catch:{ all -> 0x00b5 }
        L_0x0060:
            return;
        L_0x0061:
            r0 = move-exception;
            r2 = r6.mCallback;
            if (r2 == 0) goto L_0x006d;
        L_0x0066:
            r2 = r6.mCallback;
            r3 = r6.mOldAid;
            r2.onAidEventChanged(r5, r3);
        L_0x006d:
            TAG;
            r0.toString();
            r0 = r1;
            goto L_0x001b;
        L_0x0075:
            TAG;	 Catch:{ Exception -> 0x0079 }
            goto L_0x0037;
        L_0x0079:
            r0 = move-exception;
            r2 = r6.mCallback;
            if (r2 == 0) goto L_0x0085;
        L_0x007e:
            r2 = r6.mCallback;
            r3 = r6.mOldAid;
            r2.onAidEventChanged(r5, r3);
        L_0x0085:
            TAG;
            r0.toString();
            goto L_0x0037;
        L_0x008c:
            r2 = com.ta.utdid2.android.utils.DebugUtils.DBG;	 Catch:{ Exception -> 0x0096 }
            if (r2 == 0) goto L_0x0093;
        L_0x0090:
            TAG;	 Catch:{ Exception -> 0x0096 }
        L_0x0093:
            r6.mRspLine = r0;	 Catch:{ Exception -> 0x0096 }
            goto L_0x0039;
        L_0x0096:
            r0 = move-exception;
            r2 = r6.mCallback;
            if (r2 == 0) goto L_0x00a2;
        L_0x009b:
            r2 = r6.mCallback;
            r3 = r6.mOldAid;
            r2.onAidEventChanged(r5, r3);
        L_0x00a2:
            TAG;
            r0.toString();
            goto L_0x003f;
        L_0x00a9:
            TAG;	 Catch:{ Exception -> 0x0096 }
            goto L_0x003f;
        L_0x00ad:
            r0 = move-exception;
            TAG;
            r0.toString();
            goto L_0x004b;
        L_0x00b5:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x00b5 }
            throw r0;
        L_0x00b8:
            r0 = r6.mRspLine;
            r1 = r6.mOldAid;
            r0 = com.ta.utdid2.aid.AidRequester.getAidFromJsonRsp(r0, r1);
            r1 = r6.mCallback;
            r2 = 1001; // 0x3e9 float:1.403E-42 double:4.946E-321;
            r1.onAidEventChanged(r2, r0);
            r1 = com.ta.utdid2.aid.AidRequester.this;
            r1 = r1.mContext;
            r2 = r6.mAppName;
            r3 = r6.mToken;
            com.ta.utdid2.aid.AidStorageController.setAidValueToSP(r1, r2, r0, r3);
            goto L_0x0060;
            */
        }

        public String getResponseLine() {
            return this.mRspLine;
        }
    }

    static {
        TAG = AidRequester.class.getName();
        sAidRequester = null;
    }

    public static synchronized AidRequester getInstance(Context context) {
        AidRequester aidRequester;
        synchronized (AidRequester.class) {
            if (sAidRequester == null) {
                sAidRequester = new AidRequester(context);
            }
            aidRequester = sAidRequester;
        }
        return aidRequester;
    }

    public AidRequester(Context context) {
        this.mLock = new Object();
        this.mContext = context;
    }

    public void postRestAsync(String str, String str2, String str3, String str4, AidCallback aidCallback) {
        String postUrl = getPostUrl(str, str2, str3, str4);
        if (DebugUtils.DBG) {
            new StringBuilder("url:").append(postUrl).append("; len:").append(postUrl.length());
        }
        new PostRestThread(new HttpPost(postUrl), aidCallback, str4, str, str2).start();
    }

    public String postRest(String str, String str2, String str3, String str4) {
        String postUrl = getPostUrl(str, str2, str3, str4);
        int i = NetworkUtils.isConnectedToWeakNetwork(this.mContext) ? WEAK_SESSION_TIME_OUT : SESSION_TIME_OUT;
        if (DebugUtils.DBG) {
            new StringBuilder("url:").append(postUrl).append("; timeout:").append(i);
        }
        PostRestThread postRestThread = new PostRestThread(new HttpPost(postUrl));
        postRestThread.start();
        try {
            synchronized (this.mLock) {
                this.mLock.wait((long) i);
            }
        } catch (Exception e) {
            e.toString();
        }
        String responseLine = postRestThread.getResponseLine();
        boolean z = DebugUtils.DBG;
        return getAidFromJsonRsp(responseLine, str4);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getAidFromJsonRsp(java.lang.String r3, java.lang.String r4) {
        throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.aid.AidRequester.getAidFromJsonRsp(java.lang.String, java.lang.String):java.lang.String");
        /*
        r0 = android.text.TextUtils.isEmpty(r3);
        if (r0 != 0) goto L_0x004d;
    L_0x0006:
        r0 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x008f, Exception -> 0x0094 }
        r0.<init>(r3);	 Catch:{ JSONException -> 0x008f, Exception -> 0x0094 }
        r1 = "data";
        r1 = r0.has(r1);	 Catch:{ JSONException -> 0x008f, Exception -> 0x0094 }
        if (r1 == 0) goto L_0x004e;
    L_0x0014:
        r1 = "data";
        r0 = r0.getJSONObject(r1);	 Catch:{ JSONException -> 0x008f, Exception -> 0x0094 }
        r1 = "action";
        r1 = r0.has(r1);	 Catch:{ JSONException -> 0x008f, Exception -> 0x0094 }
        if (r1 == 0) goto L_0x004d;
    L_0x0024:
        r1 = "aid";
        r1 = r0.has(r1);	 Catch:{ JSONException -> 0x008f, Exception -> 0x0094 }
        if (r1 == 0) goto L_0x004d;
    L_0x002d:
        r1 = "action";
        r1 = r0.getString(r1);	 Catch:{ JSONException -> 0x008f, Exception -> 0x0094 }
        r2 = "new";
        r2 = r1.equalsIgnoreCase(r2);	 Catch:{ JSONException -> 0x008f, Exception -> 0x0094 }
        if (r2 != 0) goto L_0x0046;
    L_0x003d:
        r2 = "changed";
        r1 = r1.equalsIgnoreCase(r2);	 Catch:{ JSONException -> 0x008f, Exception -> 0x0094 }
        if (r1 == 0) goto L_0x004d;
    L_0x0046:
        r1 = "aid";
        r4 = r0.getString(r1);	 Catch:{ JSONException -> 0x008f, Exception -> 0x0094 }
    L_0x004d:
        return r4;
    L_0x004e:
        r1 = "isError";
        r1 = r0.has(r1);	 Catch:{ JSONException -> 0x008f, Exception -> 0x0094 }
        if (r1 == 0) goto L_0x004d;
    L_0x0057:
        r1 = "status";
        r1 = r0.has(r1);	 Catch:{ JSONException -> 0x008f, Exception -> 0x0094 }
        if (r1 == 0) goto L_0x004d;
    L_0x0060:
        r1 = "isError";
        r1 = r0.getString(r1);	 Catch:{ JSONException -> 0x008f, Exception -> 0x0094 }
        r2 = "status";
        r0 = r0.getString(r2);	 Catch:{ JSONException -> 0x008f, Exception -> 0x0094 }
        r2 = "true";
        r1 = r1.equalsIgnoreCase(r2);	 Catch:{ JSONException -> 0x008f, Exception -> 0x0094 }
        if (r1 == 0) goto L_0x004d;
    L_0x0077:
        r1 = "404";
        r1 = r0.equalsIgnoreCase(r1);	 Catch:{ JSONException -> 0x008f, Exception -> 0x0094 }
        if (r1 != 0) goto L_0x0089;
    L_0x0080:
        r1 = "401";
        r0 = r0.equalsIgnoreCase(r1);	 Catch:{ JSONException -> 0x008f, Exception -> 0x0094 }
        if (r0 == 0) goto L_0x004d;
    L_0x0089:
        r0 = com.ta.utdid2.android.utils.DebugUtils.DBG;	 Catch:{ JSONException -> 0x008f, Exception -> 0x0094 }
        r4 = "";
        goto L_0x004d;
    L_0x008f:
        r0 = move-exception;
        r0.toString();
        goto L_0x004d;
    L_0x0094:
        r0 = move-exception;
        r0.toString();
        goto L_0x004d;
        */
    }

    private static String getPostUrl(String str, String str2, String str3, String str4) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            str3 = URLEncoder.encode(str3, GameManager.DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return stringBuilder.append(AIDSERVER).append(str).append("/get_aid/?auth[token]=").append(str2).append("&type=utdid&id=").append(str3).append(NAME_AID).append(str4).toString();
    }
}
