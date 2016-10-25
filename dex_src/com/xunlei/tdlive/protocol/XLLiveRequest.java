package com.xunlei.tdlive.protocol;

import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import com.google.gson.Gson;
import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveRequest.RequestErrorHandler;
import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;
import com.xunlei.tdlive.protocol.test.IRequestFilter;
import com.xunlei.tdlive.protocol.test.MockManager;
import com.xunlei.tdlive.protocol.test.MockRequest;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.ac;
import com.xunlei.tdlive.util.f;
import com.xunlei.tdlive.util.f.b;
import com.xunlei.tdlive.util.f.c;
import com.xunlei.tdlive.util.f.e;
import com.xunlei.tdlive.util.f.h;
import com.xunlei.tdlive.util.g;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.NameValuePair;

public abstract class XLLiveRequest {
    public static final int ERROR_ALEARDY_FOLLOW = -1004;
    private static final int ERROR_INVALID_SESSION = -400;
    private static final int ERROR_INVALID_VERSION = -403;
    public static final int ERROR_NOT_FOLLOW = -1005;
    private static final int ERROR_UNKNOWN = -1;
    private static final int MAX_LOG_LENGTH = 1024;
    public static final String TAG = "XLLiveRequest";
    private static IDNSCache sDnsCache;
    private static IRequestFilter sFilter;
    private static Map<String, String> sGlobalCookies;
    private static INetworkHandler sNetworHandler;
    private static long sRequestId;
    private b mHandler;
    private f mHttpUtils;
    private long mId;
    private JsonCallBack mJsonCallBack;
    private ObjectCallBack mObjectCallBack;
    private String mSessionId;
    private String mUserId;

    public static interface JsonCallBack {
        void onResponse(int i, String str, JsonWrapper jsonWrapper);
    }

    public static interface ObjectCallBack {
        void onResponse(int i, String str, Object obj);
    }

    public static class XLLiveRespBase {
        public String message;
        public int result;
    }

    public static interface IDNSCache {
        String did();

        String disc();

        String hit(String str);
    }

    class JsonRequestCallBack extends e<JsonWrapper> {
        JsonRequestCallBack() {
        }

        public JsonWrapper onParseResult(String str) {
            if (str.length() >= 1024) {
                XLog.d(TAG, new StringBuilder("JsonRequestCallBack.onParseResult id = ").append(XLLiveRequest.this.mId).append(", result=").append(str.substring(0, JsInterface.MSG_JS_SET_HOT_KEY)).toString());
            } else {
                XLog.d(TAG, new StringBuilder("JsonRequestCallBack.onParseResult id = ").append(XLLiveRequest.this.mId).append(", result=").append(str).toString());
            }
            return new JsonWrapper(str);
        }

        public void onSuccess(h<JsonWrapper> hVar) {
            if (XLLiveRequest.this.mJsonCallBack != null) {
                String string;
                if (hVar.b != 200) {
                    hVar.a = new JsonWrapper("{}");
                    ((JsonWrapper) hVar.a).putInt("result", hVar.b);
                    ((JsonWrapper) hVar.a).putString(Constants.SHARED_MESSAGE_ID_FILE, hVar.c);
                } else if (hVar.a == null) {
                    hVar.a = new JsonWrapper("{}");
                }
                int i = ((JsonWrapper) hVar.a).getInt("result", (int) ERROR_UNKNOWN);
                if (i == -400 && sNetworHandler != null) {
                    sNetworHandler.onSessionInavlid();
                } else if (i == -403 && sNetworHandler != null) {
                    sNetworHandler.onInvalidVersion(((JsonWrapper) hVar.a).getString(Constants.SHARED_MESSAGE_ID_FILE, "\u5f53\u524d\u7248\u672c\u4e0d\u53ef\u7528\uff0c\u8bf7\u66f4\u65b0~"), ((JsonWrapper) hVar.a).getObject(SocializeConstants.JSON_DATA, "{}").getString(SocialConstants.PARAM_URL, a.d));
                }
                RequestErrorHandler errorHandler = XLLiveRequest.this.getErrorHandler();
                CharSequence charSequence = a.d;
                if (errorHandler != null) {
                    charSequence = errorHandler.getMsg(i);
                }
                if (TextUtils.isEmpty(charSequence)) {
                    string = ((JsonWrapper) hVar.a).getString(Constants.SHARED_MESSAGE_ID_FILE, "\u672a\u77e5\u9519\u8bef");
                } else {
                    CharSequence charSequence2 = charSequence;
                }
                XLLiveRequest.this.mJsonCallBack.onResponse(i, string, (JsonWrapper) hVar.a);
            }
            XLLiveRequest.this.mHandler = null;
        }

        public void onFailure(f.a aVar, String str) {
            XLog.d(TAG, new StringBuilder("JsonRequestCallBack.onFailure error=").append(aVar.toString()).append(", msg=").append(str).toString());
            com.xunlei.tdlive.a.a(new StringBuilder("request failed, url:").append(XLLiveRequest.this.onGetURL()).append(", msg:").append(aVar.toString()).toString());
            if (XLLiveRequest.this.mJsonCallBack != null) {
                JsonWrapper jsonWrapper = new JsonWrapper("{}");
                jsonWrapper.putInt("result", aVar.a() == 0 ? -1 : aVar.a());
                jsonWrapper.putString(Constants.SHARED_MESSAGE_ID_FILE, "\u7f51\u7edc\u5f02\u5e38");
                XLLiveRequest.this.mJsonCallBack.onResponse(jsonWrapper.getInt("result", (int) ERROR_UNKNOWN), jsonWrapper.getString(Constants.SHARED_MESSAGE_ID_FILE, "\u672a\u77e5\u9519\u8bef"), jsonWrapper);
            }
            XLLiveRequest.this.mHandler = null;
        }
    }

    class ObjectRequestCallBack extends e<Object> {
        ObjectRequestCallBack() {
        }

        public Object onParseResult(String str) {
            try {
                if (str.length() >= 1024) {
                    XLog.d(TAG, new StringBuilder("ObjectRequestCallBack.onParseResult id = ").append(XLLiveRequest.this.mId).append(", result=").append(str.substring(0, JsInterface.MSG_JS_SET_HOT_KEY)).toString());
                } else {
                    XLog.d(TAG, new StringBuilder("ObjectRequestCallBack.onParseResult id = ").append(XLLiveRequest.this.mId).append(", result=").append(str).toString());
                }
                return new Gson().fromJson(str, XLLiveRequest.this.onGetObjectClass());
            } catch (Exception e) {
                XLog.e(TAG, new StringBuilder("e:").append(e.toString()).append(", object:").append(str).toString());
                com.xunlei.tdlive.a.a(new StringBuilder("request failed, url:").append(XLLiveRequest.this.onGetURL()).append(", msg:").append(e.toString()).toString());
                return null;
            }
        }

        public void onSuccess(h<Object> hVar) {
            if (XLLiveRequest.this.mObjectCallBack != null) {
                if (hVar.a == null) {
                    com.xunlei.tdlive.a.a(new StringBuilder("request failed, url:").append(XLLiveRequest.this.onGetURL()).append(", msg:unknown error, responseInfo.result is null").toString());
                    XLLiveRequest.this.mObjectCallBack.onResponse(ERROR_UNKNOWN, "\u672a\u77e5\u9519\u8bef", null);
                } else if (hVar.a instanceof XLLiveRespBase) {
                    String str;
                    XLLiveRespBase xLLiveRespBase = (XLLiveRespBase) hVar.a;
                    if (xLLiveRespBase.result == -400 && sNetworHandler != null) {
                        sNetworHandler.onSessionInavlid();
                    }
                    RequestErrorHandler errorHandler = XLLiveRequest.this.getErrorHandler();
                    CharSequence charSequence = a.d;
                    if (errorHandler != null) {
                        charSequence = errorHandler.getMsg(xLLiveRespBase.result);
                    }
                    if (TextUtils.isEmpty(str)) {
                        str = xLLiveRespBase.message;
                    }
                    XLLiveRequest.this.mObjectCallBack.onResponse(xLLiveRespBase.result, str, hVar.a);
                } else {
                    XLLiveRequest.this.mObjectCallBack.onResponse(0, a.d, hVar.a);
                }
            }
            XLLiveRequest.this.mHandler = null;
        }

        public void onFailure(f.a aVar, String str) {
            XLog.d(TAG, new StringBuilder("ObjectRequestCallBack.onFailure error=").append(aVar.toString()).append(", msg=").append(str).toString());
            com.xunlei.tdlive.a.a(new StringBuilder("request failed, url:").append(XLLiveRequest.this.onGetURL()).append(", msg:").append(aVar.toString()).toString());
            if (XLLiveRequest.this.mObjectCallBack != null) {
                XLLiveRequest.this.mObjectCallBack.onResponse(aVar.a() == 0 ? ERROR_UNKNOWN : aVar.a(), "\u7f51\u7edc\u5f02\u5e38", null);
            }
            XLLiveRequest.this.mHandler = null;
        }
    }

    public static interface RequestErrorHandler {
        String getMsg(int i);
    }

    public abstract String onGetURL();

    static {
        sRequestId = 0;
    }

    public static void setDNSCahce(IDNSCache iDNSCache) {
        sDnsCache = iDNSCache;
    }

    public static IDNSCache getDNSCahce() {
        return sDnsCache;
    }

    public static void setFilter(IRequestFilter iRequestFilter) {
        sFilter = iRequestFilter;
    }

    public static void addGlobalCookie(String str, String str2) {
        if (sGlobalCookies == null) {
            sGlobalCookies = new HashMap();
        }
        sGlobalCookies.put(str, str2);
    }

    public static void setNetworkHandler(INetworkHandler iNetworkHandler) {
        sNetworHandler = iNetworkHandler;
    }

    public XLLiveRequest(String str, String str2) {
        this.mHttpUtils = new f();
        this.mUserId = str;
        this.mSessionId = str2;
        long j = sRequestId + 1;
        sRequestId = j;
        this.mId = j;
    }

    public boolean tryLock() {
        return this.mHandler == null;
    }

    public void send(JsonCallBack jsonCallBack) {
        this.mJsonCallBack = jsonCallBack;
        send();
    }

    public void send(ObjectCallBack objectCallBack) {
        this.mObjectCallBack = objectCallBack;
        send();
    }

    private void send() {
        f.f fVar;
        CharSequence hit;
        if (sFilter == null || !sFilter.filter(getClass())) {
            Object mockData = MockManager.instance().getMockData(getClass());
            if (mockData == null) {
                String str;
                String str2;
                if (!ac.a()) {
                    if (sNetworHandler != null) {
                        sNetworHandler.onNoConnection();
                    } else {
                        XLog.w(TAG, "request when no connection.");
                    }
                }
                String onGetURL = onGetURL();
                String str3 = a.d;
                String str4 = a.d;
                String str5 = ac.j() ? "1001" : "1003";
                if (ac.j()) {
                    str = "TYUIG@#!*1234ftyu";
                } else {
                    str = "39YIW&**6W#BLM=";
                }
                try {
                    com.a.a.c.a aVar = new com.a.a.c.a(onGetURL);
                    List arrayList;
                    if (aVar.b != null) {
                        arrayList = new ArrayList(aVar.b);
                    } else {
                        arrayList = new ArrayList();
                    }
                    Collections.sort(r0, new Comparator<NameValuePair>() {
                        public int compare(NameValuePair nameValuePair, NameValuePair nameValuePair2) {
                            return nameValuePair.getName().compareTo(nameValuePair2.getName());
                        }
                    });
                    StringBuilder stringBuilder = new StringBuilder();
                    for (NameValuePair nameValuePair : r0) {
                        if (stringBuilder.length() > 0) {
                            stringBuilder.append(com.alipay.sdk.sys.a.b);
                        }
                        stringBuilder.append(nameValuePair.getName()).append("=").append(nameValuePair.getValue());
                    }
                    str = g.a(stringBuilder.insert(0, str5).append(str).toString());
                    try {
                        CharSequence charSequence = aVar.a;
                    } catch (Exception e) {
                        str2 = str;
                        str = str2;
                        str2 = str4;
                        str4 = onGetCookie() + "appid=" + str5 + "; sign=" + str + com.alipay.sdk.util.h.b;
                        fVar = new f.f();
                        fVar.a("Cookie", str4);
                        onAddBodyParams(fVar);
                        hit = sDnsCache.hit(str2);
                        fVar.a(HttpConstant.HOST, str2);
                        str2 = onGetURL.replace(str2, hit);
                        this.mHandler = this.mHttpUtils.a(useHttpPost() ? c.b : c.a, str2, fVar, this.mObjectCallBack != null ? new JsonRequestCallBack() : new ObjectRequestCallBack());
                        XLog.d(TAG, new StringBuilder("send id = ").append(this.mId).append(", url = ").append(str2).append(", cookie = ").append(str4).toString());
                    }
                } catch (Exception e2) {
                    str2 = str3;
                    str = str2;
                    str2 = str4;
                    str4 = onGetCookie() + "appid=" + str5 + "; sign=" + str + com.alipay.sdk.util.h.b;
                    fVar = new f.f();
                    fVar.a("Cookie", str4);
                    onAddBodyParams(fVar);
                    hit = sDnsCache.hit(str2);
                    fVar.a(HttpConstant.HOST, str2);
                    str2 = onGetURL.replace(str2, hit);
                    if (useHttpPost()) {
                    }
                    if (this.mObjectCallBack != null) {
                    }
                    this.mHandler = this.mHttpUtils.a(useHttpPost() ? c.b : c.a, str2, fVar, this.mObjectCallBack != null ? new JsonRequestCallBack() : new ObjectRequestCallBack());
                    XLog.d(TAG, new StringBuilder("send id = ").append(this.mId).append(", url = ").append(str2).append(", cookie = ").append(str4).toString());
                }
                str4 = onGetCookie() + "appid=" + str5 + "; sign=" + str + com.alipay.sdk.util.h.b;
                fVar = new f.f();
                fVar.a("Cookie", str4);
                onAddBodyParams(fVar);
                if (!(sDnsCache == null || str2 == null || str2.length() <= 0)) {
                    hit = sDnsCache.hit(str2);
                    if (hit != null && hit.length() > 0) {
                        fVar.a(HttpConstant.HOST, str2);
                        str2 = onGetURL.replace(str2, hit);
                        if (useHttpPost()) {
                        }
                        if (this.mObjectCallBack != null) {
                        }
                        this.mHandler = this.mHttpUtils.a(useHttpPost() ? c.b : c.a, str2, fVar, this.mObjectCallBack != null ? new JsonRequestCallBack() : new ObjectRequestCallBack());
                        XLog.d(TAG, new StringBuilder("send id = ").append(this.mId).append(", url = ").append(str2).append(", cookie = ").append(str4).toString());
                    }
                }
                str2 = onGetURL;
                if (useHttpPost()) {
                }
                if (this.mObjectCallBack != null) {
                }
                this.mHandler = this.mHttpUtils.a(useHttpPost() ? c.b : c.a, str2, fVar, this.mObjectCallBack != null ? new JsonRequestCallBack() : new ObjectRequestCallBack());
                XLog.d(TAG, new StringBuilder("send id = ").append(this.mId).append(", url = ").append(str2).append(", cookie = ").append(str4).toString());
            } else if (this.mObjectCallBack != null) {
                new MockRequest(mockData).send(this.mObjectCallBack);
            } else if (this.mJsonCallBack != null) {
                new MockRequest(mockData).send(this.mJsonCallBack);
            }
        }
    }

    public void cancel() {
        if (this.mHandler != null) {
            this.mHandler.a();
            this.mHandler = null;
        }
    }

    protected boolean useHttpPost() {
        return false;
    }

    protected void onAddBodyParams(f.f fVar) {
    }

    protected String onGetCookie() {
        String toString = new StringBuilder("userid=").append(this.mUserId).append("; sessionid=").append(this.mSessionId).append("; appver=").append(ac.d()).append(ac.j() ? a.d : ":55").append("; appcode=").append(ac.j() ? ac.e() : R.styleable.AppCompatTheme_dividerVertical).append("; os=android; osver=").append(ac.c()).append("; model=").append(ac.a(false)).append("; deviceid=").append(ac.g()).append("; channel=").append(ac.j() ? ac.d("UMENG_CHANNEL") : "ThunderSDK").append("; ").toString();
        if (sGlobalCookies == null) {
            return toString;
        }
        String str = toString;
        for (Entry entry : sGlobalCookies.entrySet()) {
            str = str + ((String) entry.getKey()) + "=" + ((String) entry.getValue()) + "; ";
        }
        return str;
    }

    protected Class<?> onGetObjectClass() {
        return XLLiveRespBase.class;
    }

    public RequestErrorHandler getErrorHandler() {
        return null;
    }
}
