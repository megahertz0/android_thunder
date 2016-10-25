package com.xunlei.downloadprovider.web.base.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;
import com.android.volley.f;
import com.umeng.message.proguard.j;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.common.pay.XLOnPayListener;
import com.xunlei.common.pay.XLPayUtil;
import com.xunlei.common.pay.param.XLAlipayParam;
import com.xunlei.common.pay.param.XLPayParam;
import com.xunlei.common.pay.param.XLWxPayParam;
import com.xunlei.download.proguard.c;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.a.h.b;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.model.g;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff.SniffStartFrom;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.downloadprovider.url.DownData;
import com.xunlei.downloadprovider.util.sniff.SniffConfigure;
import com.xunlei.downloadprovider.util.sniff.SniffConfigure$a;
import com.xunlei.downloadprovider.vod.VodUtil;
import com.xunlei.downloadprovider.vod.ap;
import com.xunlei.downloadprovider.vod.protocol.VodSourceType;
import com.xunlei.downloadprovider.vod.protocol.VodVideoFormat;
import com.xunlei.downloadprovider.vod.protocol.h;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.downloadprovider.web.BrowserUtil.StartFromType;
import com.xunlei.downloadprovider.web.base.LongVideoDetailActivity;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From;
import com.xunlei.downloadprovider.web.base.WebViewNormalActivity;
import com.xunlei.downloadprovidershare.d;
import com.xunlei.downloadprovidershare.data.ShareBean;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.android.agoo.common.AgooConstants;
import org.android.spdy.SpdyProtocol;
import org.android.spdy.SpdyRequest;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class DefaultJsInterface extends BaseJsInterface {
    private List<p> mMessageInterceptorList;
    private final a mMessageListener;
    private final Handler mOperateHandler;
    private XLOnPayListener mPayListener;

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[MethodName.values().length];
            try {
                a[MethodName.xlGetUserInfo.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[MethodName.xlGetNetworkInfo.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[MethodName.xlCheckAppInstalled.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[MethodName.xlShowToast.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[MethodName.xlOpenUrl.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[MethodName.xlGetAppMetaData.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[MethodName.xlAddPlayRecord.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[MethodName.xlAddTask.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[MethodName.xlSocialShare.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[MethodName.xlHttpRequestForward.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                a[MethodName.xlReportStatistics.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                a[MethodName.xlShowLoading.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                a[MethodName.xlHideLoading.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                a[MethodName.xlGetPageFrom.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                a[MethodName.xlVideoPlay.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                a[MethodName.xlLogout.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                a[MethodName.xlOpenLocalPage.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                a[MethodName.xlOpenApp.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                a[MethodName.xlPay.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
            try {
                a[MethodName.xlGetSniffConfig.ordinal()] = 20;
            } catch (NoSuchFieldError e20) {
            }
            try {
                a[MethodName.xlStartSniff.ordinal()] = 21;
            } catch (NoSuchFieldError e21) {
            }
        }
    }

    public DefaultJsInterface(Context context, CustomWebView customWebView) {
        super(context, customWebView);
        this.mMessageInterceptorList = new ArrayList();
        this.mMessageListener = new g(this);
        this.mOperateHandler = new b(this.mMessageListener);
        this.mPayListener = new o(this);
    }

    public final void addInterceptor(p pVar) {
        this.mMessageInterceptorList.add(pVar);
    }

    public final void removeInterceptor(p pVar) {
        this.mMessageInterceptorList.remove(pVar);
    }

    public final void clearInterceptors() {
        this.mMessageInterceptorList.clear();
    }

    private boolean interceptMessage(MethodName methodName, JSONObject jSONObject, String str) throws JSONException {
        if (!this.mMessageInterceptorList.isEmpty()) {
            for (p pVar : this.mMessageInterceptorList) {
                if (pVar.handleMessage(methodName, jSONObject, str)) {
                    return true;
                }
            }
        }
        return false;
    }

    protected final boolean handleMessage(MethodName methodName, JSONObject jSONObject, String str) throws JSONException {
        if (interceptMessage(methodName, jSONObject, str)) {
            return true;
        }
        new StringBuilder("handleMessage methodName=").append(methodName);
        switch (AnonymousClass_1.a[methodName.ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
                xlGetUserInfo(jSONObject, str);
                return true;
            case SimpleLog.LOG_LEVEL_DEBUG:
                xlGetNetworkInfo(str);
                return true;
            case MqttConnectOptions.MQTT_VERSION_3_1:
                xlCheckAppInstalled(jSONObject, str);
                return true;
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
                xlShowToast(jSONObject);
                return true;
            case SimpleLog.LOG_LEVEL_ERROR:
                xlOpenUrl(jSONObject);
                return true;
            case SimpleLog.LOG_LEVEL_FATAL:
                xlGetAppMetaData(str);
                return true;
            case SimpleLog.LOG_LEVEL_OFF:
                xlAddPlayRecord(jSONObject, str);
                return true;
            case SpdyProtocol.PUBKEY_SEQ_ADASH:
                xlAddTask(jSONObject, null);
                return true;
            case SpdyProtocol.PUBKEY_PSEQ_ADASH:
                xlSocialShare(jSONObject, str);
                return true;
            case SpdyProtocol.PUBKEY_SEQ_OPEN:
                xlHttpRequestForward(jSONObject, str);
                return true;
            case SpdyProtocol.PUBKEY_PSEQ_OPEN:
                xlReportStatistics(jSONObject, str);
                return true;
            case R.styleable.Toolbar_titleMargins:
                xlShowLoading();
                return true;
            case R.styleable.Toolbar_titleMarginStart:
                xlHideLoading();
                return true;
            case R.styleable.Toolbar_titleMarginEnd:
                callbackWithOneParam(str, "from", getWebView().getFrom());
                return true;
            case R.styleable.Toolbar_titleMarginTop:
                xlVideoPlay(jSONObject, str);
                return true;
            case SpdyProtocol.CUSTOM:
                xlLogout(jSONObject, str);
                return true;
            case R.styleable.Toolbar_maxButtonHeight:
                xlOpenLocalPage(jSONObject, str);
                return true;
            case R.styleable.Toolbar_collapseIcon:
                xlOpenApp(jSONObject, str);
                return true;
            case R.styleable.Toolbar_collapseContentDescription:
                xlPay(jSONObject, str);
                return true;
            case R.styleable.Toolbar_navigationIcon:
                xlGetSniffConfig(str);
                return true;
            case R.styleable.Toolbar_navigationContentDescription:
                xlStartSniff(jSONObject);
                return true;
            default:
                return false;
        }
    }

    private void xlGetSniffConfig(String str) {
        callbackGetSniffConfigure(str);
    }

    private void callbackGetSniffConfigure(String str) {
        SniffConfigure$a b = SniffConfigure.a().b();
        if (b != null) {
            Collection collection = b.a;
            if (collection != null) {
                Map hashMap = new HashMap();
                hashMap.put("keyword_suffix", new JSONArray(collection));
                callback(str, hashMap);
            }
        }
    }

    private void xlStartSniff(JSONObject jSONObject) {
        String optString = jSONObject.optString(SetKey.KEYWORD);
        String optString2 = jSONObject.optString("suffix");
        Object optString3 = jSONObject.optString("from");
        boolean optBoolean = jSONObject.optBoolean("autoSniffer", true);
        optString2 = optString + " " + optString2;
        SniffStartFrom sniffStartFrom = null;
        if (!TextUtils.isEmpty(optString3)) {
            try {
                sniffStartFrom = SniffStartFrom.valueOf(optString3);
            } catch (IllegalArgumentException e) {
            }
        }
        BrowserUtil.a();
        BrowserUtil.a(getContext(), 0, optString2, false, StartFromType.from_website, optBoolean, sniffStartFrom);
    }

    private void xlLogout(JSONObject jSONObject, String str) {
        LoginHelper.a().a(new h(this, str));
    }

    private void xlHideLoading() {
        getWebView().d();
    }

    private void xlShowLoading() {
        getWebView().a();
    }

    private void xlVideoPlay(JSONObject jSONObject, String str) throws JSONException {
        String string = jSONObject.getString(SHubBatchQueryKeys.url);
        String optString = jSONObject.optString(SetKey.TITLE);
        ap apVar = new ap();
        h hVar = new h();
        hVar.c = string;
        hVar.q = null;
        hVar.o = 2;
        hVar.x = VodVideoFormat.mp4;
        hVar.a = optString;
        apVar.b = VodSourceType.normal;
        apVar.a(hVar);
        VodUtil.a();
        VodUtil.a(getContext(), apVar);
    }

    private void xlGetNetworkInfo(String str) {
        callbackWithOneParam(str, "status", String.valueOf(getNetworkType()));
    }

    private int getNetworkType() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return 0;
        }
        int type = activeNetworkInfo.getType();
        if (type == 1) {
            type = 1;
        } else if (type == 0) {
            Object obj = SimpleLog.LOG_LEVEL_DEBUG;
        } else {
            type = 0;
        }
        return type;
    }

    private void xlCheckAppInstalled(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject != null) {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray("pkgNameList");
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.getString(i));
            }
            if (!arrayList.isEmpty()) {
                callback(str, checkAppInstalled(arrayList));
            }
        }
    }

    private Map<String, Object> checkAppInstalled(ArrayList<String> arrayList) {
        HashMap hashMap = new HashMap();
        if (arrayList == null || arrayList.isEmpty()) {
            return hashMap;
        }
        List<PackageInfo> installedPackages = getContext().getPackageManager().getInstalledPackages(SpdyProtocol.SLIGHTSSL_1_RTT_MODE);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            boolean z;
            String str = (String) it.next();
            for (PackageInfo packageInfo : installedPackages) {
                if (str.equals(packageInfo.packageName)) {
                    z = true;
                    break;
                }
            }
            z = false;
            hashMap.put(str, Boolean.valueOf(z));
        }
        return hashMap;
    }

    private void xlShowToast(JSONObject jSONObject) throws JSONException {
        if (jSONObject != null) {
            Object string = jSONObject.getString(j.C);
            if (!TextUtils.isEmpty(string)) {
                switch (jSONObject.optInt(AgooConstants.MESSAGE_TYPE)) {
                    case SimpleLog.LOG_LEVEL_TRACE:
                        XLToast.b(getContext(), XLToastType.XLTOAST_TYPE_SUC, string);
                    default:
                        Toast.makeText(getContext(), string, 0).show();
                }
            }
        }
    }

    private void xlOpenUrl(JSONObject jSONObject) throws JSONException {
        if (jSONObject != null) {
            String optString = jSONObject.optString(SetKey.TITLE);
            String string = jSONObject.getString(SHubBatchQueryKeys.url);
            int i = jSONObject.getInt("openType");
            String optString2 = jSONObject.optString("from");
            switch (i) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    WebViewNormalActivity.a(getContext(), optString2, string, optString);
                case SimpleLog.LOG_LEVEL_DEBUG:
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(Uri.parse(string));
                    getContext().startActivity(intent);
                case MqttConnectOptions.MQTT_VERSION_3_1:
                    boolean optBoolean = jSONObject.optBoolean("autoSniffer", true);
                    SniffStartFrom sniffStartFrom = null;
                    if (!TextUtils.isEmpty(optString2)) {
                        try {
                            sniffStartFrom = SniffStartFrom.valueOf(optString2);
                        } catch (IllegalArgumentException e) {
                        }
                    }
                    BrowserUtil.a();
                    BrowserUtil.a(getContext(), 0, string, false, StartFromType.from_website, optBoolean, sniffStartFrom);
                case MqttConnectOptions.MQTT_VERSION_3_1_1:
                    LongVideoDetailActivity.a(getContext(), optString2, string, optString);
                case SimpleLog.LOG_LEVEL_ERROR:
                    BrowserUtil.a();
                    BrowserUtil.a(getContext(), string, optString);
                default:
                    break;
            }
        }
    }

    private void xlGetAppMetaData(String str) {
        Map hashMap = new HashMap();
        hashMap.put("versionCode", Integer.valueOf(com.xunlei.downloadprovider.a.b.x()));
        hashMap.put("appVersion", com.xunlei.downloadprovider.a.b.w());
        hashMap.put("systemVersion", Integer.valueOf(com.xunlei.downloadprovider.a.b.i()));
        hashMap.put("productID", com.xunlei.downloadprovider.a.b.h());
        hashMap.put("peerID", com.xunlei.downloadprovider.a.b.d());
        hashMap.put("partnerID", com.xunlei.downloadprovider.a.b.g());
        callback(str, hashMap);
    }

    private void xlAddPlayRecord(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject != null) {
            String string = jSONObject.getString("pageUrl");
            String string2 = jSONObject.getString(SetKey.TITLE);
            jSONObject.getString("coverImageUrl");
            jSONObject.getString("createTime");
            int i = jSONObject.getInt("totalVideoLength");
            h hVar = new h();
            hVar.c = string;
            hVar.a = string2;
            hVar.r = i * 1000;
            hVar.o = 2;
            hVar.x = VodVideoFormat.flv;
            VodUtil.a();
            VodUtil.a(hVar, VodSourceType.webpage);
        }
    }

    public final void xlAddTask(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject != null) {
            try {
                if (getContext() instanceof ThunderTask) {
                    String string = jSONObject.getString(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_NAME);
                    String string2 = jSONObject.getString(SHubBatchQueryKeys.url);
                    String optString = jSONObject.optString(SetKey.REFURL);
                    if (TextUtils.isEmpty(optString) && getWebView() != null) {
                        optString = getWebView().getUrl();
                    }
                    if (TextUtils.isEmpty(string2)) {
                        XLToast.a(getContext(), XLToastType.XLTOAST_TYPE_ALARM, getContext().getResources().getString(com.xunlei.downloadprovider.R.string.empty_download_url));
                        return;
                    }
                    DownData downData = new DownData();
                    downData.a = string;
                    downData.e = string2;
                    g gVar = new g();
                    gVar.b = string2;
                    gVar.a = 2;
                    gVar.d = str;
                    gVar.c = optString;
                    ((ThunderTask) getContext()).createTask(downData, this.mOperateHandler, gVar, false);
                    StatReporter.reportOverallDownload("break_shortvideo_hot");
                }
            } catch (JSONException e) {
                XLToast.a(getContext(), XLToastType.XLTOAST_TYPE_ALARM, getContext().getResources().getString(com.xunlei.downloadprovider.R.string.invalid_json_data));
                throw e;
            }
        }
    }

    private void xlSocialShare(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject != null) {
            SHARE_MEDIA share_media;
            int i = jSONObject.getInt("sharePlatform");
            String string = jSONObject.getString("shareHeadline");
            String string2 = jSONObject.getString("shareText");
            ShareBean shareBean = new ShareBean(jSONObject.getString("shareUrl"), jSONObject.getString("shareImageUrl"), string, string2);
            switch (i) {
                case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                    share_media = null;
                    break;
                case SimpleLog.LOG_LEVEL_TRACE:
                    share_media = SHARE_MEDIA.WEIXIN;
                    break;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    share_media = SHARE_MEDIA.WEIXIN_CIRCLE;
                    break;
                case MqttConnectOptions.MQTT_VERSION_3_1:
                    share_media = SHARE_MEDIA.QZONE;
                    break;
                case MqttConnectOptions.MQTT_VERSION_3_1_1:
                    share_media = SHARE_MEDIA.SINA;
                    break;
                default:
                    share_media = null;
                    break;
            }
            if (share_media == null) {
                d.b().a((Activity) getContext(), shareBean, new i(this, str));
            } else {
                d.b().a((Activity) getContext(), shareBean, share_media, new j(this, str));
            }
        }
    }

    private void xlGetUserInfo(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject != null) {
            int i = jSONObject.getInt("forceLogin");
            String optString = jSONObject.optString(AgooConstants.MESSAGE_FROM_PKG);
            if (i == 1) {
                i = 1;
            } else {
                Object obj = null;
            }
            LoginHelper a = LoginHelper.a();
            if (i == 0 || LoginHelper.c()) {
                callbackGetUserInfo(str);
            } else {
                a.a(getContext(), new k(this, str), 1, new StringBuilder("_").append(optString).toString());
            }
        }
    }

    private void callbackGetUserInfo(String str) {
        LoginHelper a = LoginHelper.a();
        Map hashMap = new HashMap();
        Object obj = null;
        if (LoginHelper.c()) {
            hashMap.put("isLogin", Integer.valueOf(1));
            Map hashMap2 = new HashMap();
            hashMap2.put("sessionID", a.k);
            hashMap2.put("avatarURL", a.l);
            hashMap2.put("nickName", a.i);
            hashMap2.put("userID", Long.valueOf(a.j));
            hashMap2.put("vipType", Integer.valueOf(a.h));
            hashMap2.put("vipLevel", Integer.valueOf(a.e));
            hashMap2.put("vipExpirationDate", a.n());
            obj = new JSONObject(hashMap2);
        } else {
            hashMap.put("isLogin", Integer.valueOf(0));
        }
        hashMap.put("userInfo", obj);
        callback(str, hashMap);
    }

    private void xlHttpRequestForward(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject != null) {
            String string = jSONObject.getString(SHubBatchQueryKeys.url);
            String string2 = jSONObject.getString(c.b);
            int i = jSONObject.getInt("timeout");
            String optString = jSONObject.optString("postContent");
            String optString2 = jSONObject.optString("contentEncoding");
            int i2 = -1;
            if (SpdyRequest.POST_METHOD.equalsIgnoreCase(string2)) {
                i2 = 1;
            } else if (SpdyRequest.GET_METHOD.equalsIgnoreCase(string2)) {
                i2 = 0;
            }
            n nVar = new n(this, i2, string, new l(this, str), new m(this, str), optString, optString2);
            nVar.setShouldCache(false);
            nVar.setRetryPolicy(new f(i, 1, 1.0f));
            add(nVar);
        }
    }

    private void httpRequestCallback(String str, boolean z, int i, String str2) {
        Map hashMap = new HashMap();
        hashMap.put("isSuccess", Boolean.valueOf(z));
        hashMap.put("responseText", str2);
        hashMap.put("status", Integer.valueOf(i));
        callback(str, hashMap);
    }

    private void xlReportStatistics(JSONObject jSONObject, String str) {
        ThunderReporter.a(jSONObject.toString());
    }

    private void xlOpenLocalPage(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject != null) {
            int i = jSONObject.getInt("openType");
            jSONObject.optString("from");
            int optInt = jSONObject.optInt("vodType", -1);
            String optString = jSONObject.optString("vodTypeName");
            switch (i) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    Object string = jSONObject.getString(AgooConstants.MESSAGE_ID);
                    String string2 = jSONObject.getString(SHubBatchQueryKeys.gcid);
                    if (!TextUtils.isEmpty(string)) {
                        ShortMovieDetailActivity.a(getContext(), optInt, optString, From.SHARE_PAGE, string, string2, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME, 0, -1);
                    }
                default:
                    break;
            }
        }
    }

    private void xlOpenApp(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject != null) {
            int i = jSONObject.getInt("openType");
            Object optString = jSONObject.optString("pkgName");
            if (!TextUtils.isEmpty(optString)) {
                switch (i) {
                    case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                        Intent launchIntentForPackage = getContext().getPackageManager().getLaunchIntentForPackage(optString);
                        if (launchIntentForPackage != null) {
                            getContext().startActivity(launchIntentForPackage);
                        }
                    default:
                        break;
                }
            }
        }
    }

    private void xlPay(JSONObject jSONObject, String str) throws JSONException {
        if (jSONObject != null) {
            int i = jSONObject.getInt("payType");
            String string = jSONObject.getString("reportRefer");
            int i2 = jSONObject.getInt("monthOrTDays");
            int i3 = jSONObject.getInt("vasType");
            int optInt = jSONObject.optInt("orderType");
            String optString = jSONObject.optString("orderVoucher");
            String optString2 = jSONObject.optString("orderExtraParam");
            String optString3 = jSONObject.optString("accessToken");
            String optString4 = jSONObject.optString("paramExt1");
            String optString5 = jSONObject.optString("paramExt2");
            String optString6 = jSONObject.optString("paramOther1");
            String optString7 = jSONObject.optString("paramOther2");
            String optString8 = jSONObject.optString("paramOther4");
            XLPayUtil.getInstance().attachListener(this.mPayListener);
            switch (i) {
                case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                    break;
                case SimpleLog.LOG_LEVEL_TRACE:
                    gotoWxpay(str, optString, optString2, string, i2, optInt, i3, optString3, optString4, optString5, optString6, optString7, optString8);
                case SimpleLog.LOG_LEVEL_DEBUG:
                    gotoAlipay(str, optString, optString2, string, i2, optInt, i3, optString3, optString4, optString5, optString6, optString7, optString8);
                default:
                    break;
            }
        }
    }

    private int gotoAlipay(String str, String str2, String str3, String str4, int i, int i2, int i3, String str5, String str6, String str7, String str8, String str9, String str10) {
        XLAlipayParam xLAlipayParam = new XLAlipayParam();
        pkgXLPayParam(xLAlipayParam, str2, str3, str4, i, i2, i3, str5, str6, str7, str8, str9, str10);
        xLAlipayParam.mActivity = (Activity) getContext();
        return XLPayUtil.getInstance().userAliPay(xLAlipayParam, str);
    }

    private int gotoWxpay(String str, String str2, String str3, String str4, int i, int i2, int i3, String str5, String str6, String str7, String str8, String str9, String str10) {
        XLWxPayParam xLWxPayParam = new XLWxPayParam();
        pkgXLPayParam(xLWxPayParam, str2, str3, str4, i, i2, i3, str5, str6, str7, str8, str9, str10);
        xLWxPayParam.mAppId = "wx3e6556568beeebdd";
        return XLPayUtil.getInstance().userWxPay(xLWxPayParam, str);
    }

    private void pkgXLPayParam(XLPayParam xLPayParam, String str, String str2, String str3, int i, int i2, int i3, String str4, String str5, String str6, String str7, String str8, String str9) {
        xLPayParam.mMonth = i;
        xLPayParam.mReferFrom = str3;
        xLPayParam.mOrderType = i2;
        xLPayParam.mSource = "shoulei_android";
        xLPayParam.mVasType = i3;
        xLPayParam.mOrderExtraParam = str2;
        xLPayParam.mUserId = (int) LoginHelper.a().j;
        xLPayParam.mAccessToken = str4;
        xLPayParam.mParamExt1 = str5;
        xLPayParam.mParamExt2 = str6;
        xLPayParam.mParamOther1 = str7;
        xLPayParam.mParamOther2 = str8;
        xLPayParam.mParamOther4 = str9;
        xLPayParam.mSessionId = LoginHelper.a().k;
    }
}
