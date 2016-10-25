package com.xunlei.downloadprovider.web.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.ExploreByTouchHelper;
import android.text.TextUtils;
import com.sina.weibo.sdk.component.GameManager;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import com.tencent.open.SocialConstants;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.editorpage.ShareActivity;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.a.f;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.download.create.DownloadBtFileExplorerActivity;
import com.xunlei.downloadprovider.frame.user.ag;
import com.xunlei.downloadprovider.frame.user.bo;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.model.protocol.a.b;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.url.DownData;
import com.xunlei.downloadprovider.util.ac;
import com.xunlei.downloadprovider.vod.VodPlayerActivity;
import com.xunlei.downloadprovider.vod.VodUtil;
import com.xunlei.downloadprovider.vod.protocol.VodSourceType;
import com.xunlei.downloadprovider.vod.protocol.VodVideoFormat;
import com.xunlei.downloadprovider.vod.protocol.i;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.downloadprovider.web.CopyrightIntermediatePageActivity;
import com.xunlei.downloadprovider.web.base.core.BaseJsInterface;
import com.xunlei.downloadprovider.web.core.ThunderWebView.CurrentShowState;
import com.xunlei.downloadprovider.web.s;
import com.xunlei.downloadprovider.web.w;
import com.xunlei.tdlive.WebBrowserActivity;
import com.xunlei.tdlive.sdk.IHost;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: DefaultJsCallbackListener.java
public class c implements a {
    private static final String f;
    ThunderWebView a;
    p b;
    a c;
    public int d;
    com.xunlei.downloadprovider.web.core.b.a e;
    private Context g;
    private a h;
    private s i;
    private ExecutorService j;

    static {
        f = c.class.getSimpleName();
    }

    public c(ThunderWebView thunderWebView, Context context) {
        this.d = 0;
        this.i = null;
        this.a = thunderWebView;
        if (context != null) {
            this.g = context;
        } else {
            this.g = this.a.getContext();
        }
        this.e = new com.xunlei.downloadprovider.web.core.b.a();
    }

    public final void a(Message message) {
        DownData downData;
        JSONException e;
        new StringBuilder("handleMessage : msg.what = ").append(message.what);
        JSONObject jSONObject;
        Bundle data;
        String string;
        String string2;
        JSONObject jSONObject2;
        String optString;
        Bundle bundle;
        String a;
        String string3;
        i iVar;
        switch (message.what) {
            case IHost.HOST_NOFITY_REFRESH_LIST:
                if ((this.b instanceof p) && (message.obj instanceof String)) {
                    try {
                        jSONObject = new JSONObject(String.valueOf(message.obj));
                        downData = new DownData();
                        downData.a = jSONObject.optString(SelectCountryActivity.EXTRA_COUNTRY_NAME);
                        downData.b = jSONObject.optString(SocialConstants.PARAM_URL);
                        downData.e = jSONObject.optString(SocialConstants.PARAM_URL);
                        downData.q = jSONObject.optString(ShareActivity.KEY_PIC);
                        downData.r = 0;
                        downData.f = 0;
                        if (!jSONObject.has("refUrl") || jSONObject.isNull("refUrl")) {
                            downData.s = null;
                        } else {
                            downData.s = jSONObject.optString("refUrl");
                        }
                        downData.v = jSONObject.optString("suggestDef");
                        downData.w = jSONObject.optInt(ParamKey.COUNT);
                        downData.x = jSONObject.optInt("remoteCount");
                        downData.y = b.a(jSONObject, "definitionList");
                        downData.z = b.a(jSONObject, "remoteDefinitionList");
                        downData.A = true;
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                        downData = null;
                    }
                    this.b.a(downData);
                }
            case IHost.HOST_NOFITY_PAGE_SELECTED:
                this.a.a(message.getData().getString("titleurl"));
            case IHost.HOST_NOFITY_PAGE_DESELECTED:
                b(message.getData());
            case JsInterface.MSG_JS_OPENWINDOW_WITH_DOWNLOADLISTEX:
                message.getData().getString(SocialConstants.PARAM_URL);
                if (this.b == null) {
                }
            case JsInterface.MSG_JS_OPEN_TRANSCODE_DOWNLOADLIST:
                a(message.getData());
            case JsInterface.MSG_JS_SHOWTOAST:
                data = message.getData();
                if (data != null) {
                    string = data.getString(SocialConstants.PARAM_SEND_MSG);
                    if (string == null) {
                        return;
                    }
                    if (string.contains("\u6210\u529f")) {
                        XLToast.b(this.g, XLToastType.XLTOAST_TYPE_SUC, string);
                    } else {
                        XLToast.b(this.g, XLToastType.XLTOAST_TYPE_ALARM, string);
                    }
                }
            case JsInterface.MSG_JS_SHOWTOAST_BY_TYPE:
                data = message.getData();
                if (data != null) {
                    string2 = data.getString(SocialConstants.PARAM_SEND_MSG);
                    if (string2 == null) {
                        return;
                    }
                    if (data.getInt(JsInterface.FUNPLAY_AD_TRPE) == 1) {
                        XLToast.b(this.g, XLToastType.XLTOAST_TYPE_SUC, string2);
                    } else {
                        XLToast.b(this.g, XLToastType.XLTOAST_TYPE_ALARM, string2);
                    }
                }
            case JsInterface.MSG_JS_CALLBACK_ON_PAGE_CLICK:
                data = message.getData();
                if (data != null) {
                    Object string4 = data.getString("tab");
                    if (TextUtils.isEmpty(string4)) {
                        h.a("invalid");
                    } else {
                        h.a(string4);
                    }
                    try {
                        h.a(Integer.parseInt(data.getString("isRecommend")));
                    } catch (Exception e3) {
                        h.a(ExploreByTouchHelper.INVALID_ID);
                    }
                    new StringBuilder("setClickMessage--tab:").append(h.a()).append(" recommand:").append(h.b());
                }
            case JsInterface.MSG_JS_VOD_PLAY:
                if (message.obj instanceof String) {
                    string = (String) message.obj;
                    if ((this.d & 2048) == 2048) {
                        StatReporter.reportChannelDownOrPlay("play");
                    }
                    VodUtil.a();
                    VodUtil.b(this.g, string);
                    if (this.c != null) {
                        this.c.a(message);
                    }
                }
            case JsInterface.MSG_JS_OPEN_DETAIL_PAGE_CALLBACK:
                if (message.obj != null && (message.obj instanceof String)) {
                    try {
                        jSONObject2 = new JSONObject((String) message.obj);
                        string = jSONObject2.optString(SocialConstants.PARAM_URL);
                        optString = jSONObject2.optString(SocialConstants.PARAM_SEND_MSG);
                        string2 = jSONObject2.optString(WebBrowserActivity.EXTRA_TITLE);
                        bundle = new Bundle();
                        bundle.putString("ToastMessage", optString);
                        BrowserUtil.a();
                        BrowserUtil.a(this.g, string, string2, bundle);
                    } catch (JSONException e22) {
                        e22.printStackTrace();
                    }
                }
            case JsInterface.MSG_JS_ADDTASKS_NEW:
                string = (String) message.obj;
                Context context = this.g;
                p pVar = this.b;
                List b;
                Intent intent;
                if ("on".equals(com.xunlei.downloadprovider.util.c.a(string))) {
                    a = g.a(string);
                    Object b2 = com.xunlei.downloadprovider.util.c.b(string);
                    if (TextUtils.isEmpty(b2)) {
                        b = g.b(string);
                        if (pVar != null) {
                            pVar.a(b);
                            return;
                        }
                        return;
                    }
                    intent = new Intent();
                    intent.setClass(context, CopyrightIntermediatePageActivity.class);
                    intent.putExtra("intent_key_opt_type", 902);
                    intent.putExtra("intent_key_add_tasks_json", string);
                    intent.putExtra("intent_key_source_url", a);
                    intent.putExtra("intent_key_popup_url", b2);
                    if (context instanceof Activity) {
                        context.startActivity(intent);
                        com.xunlei.downloadprovider.commonview.a.a.c((Activity) context);
                        return;
                    }
                    intent.setFlags(268435456);
                    context.startActivity(intent);
                    return;
                }
                Object a2 = g.a(string);
                boolean a3 = com.xunlei.downloadprovider.a.a.a(BrothersApplication.a().getApplicationContext(), VodPlayerActivity.class);
                if (TextUtils.isEmpty(a2) || a3) {
                    b = g.b(string);
                    if (pVar != null) {
                        pVar.a(b);
                        return;
                    }
                    return;
                }
                intent = new Intent();
                intent.setClass(context, CopyrightIntermediatePageActivity.class);
                intent.putExtra("intent_key_opt_type", 902);
                intent.putExtra("intent_key_add_tasks_json", string);
                intent.putExtra("intent_key_source_url", a2);
                if (context instanceof Activity) {
                    context.startActivity(intent);
                    com.xunlei.downloadprovider.commonview.a.a.c((Activity) context);
                    return;
                }
                intent.setFlags(268435456);
                context.startActivity(intent);
            case JsInterface.MSG_JS_HIDE_LOADING_VIEW:
                this.a.setCurShowView(CurrentShowState.show_webview);
                if (this.b instanceof p) {
                    this.b.b();
                }
            case JsInterface.MSG_JS_SHOW_TITLE_BAR:
            case JsInterface.MSG_JS_SET_HOT_KEY:
            case JsInterface.MSG_JS_ON_SEARCH_RESULT_CALLBACK:
                if (this.h != null) {
                    this.h.a(message);
                }
            case JsInterface.MSG_JS_COLLECT_WEBSITE:
                ac.a((String) message.obj);
            case JsInterface.MSG_JS_CANCEL_WEBSITE:
                ac.c((String) message.obj);
            case JsInterface.MSG_JS_UPDATE_WEBSITE:
                ac.b((String) message.obj);
            case JsInterface.MSG_JS_ADDTASK_BY_URL:
                if ((message.getData() instanceof Bundle) && (this.b instanceof p)) {
                    data = message.getData();
                    DownData downData2 = new DownData();
                    downData2.a = data.getString(SelectCountryActivity.EXTRA_COUNTRY_NAME);
                    downData2.b = data.getString(SocialConstants.PARAM_URL);
                    downData2.e = data.getString(SocialConstants.PARAM_URL);
                    downData2.r = 0;
                    downData2.f = 0;
                    downData2.s = null;
                    this.b.a(downData2);
                }
            case JsInterface.MSG_JS_RECEIVER_ERROR:
                new StringBuilder("handle MSG_JS_RECEIVER_ERROR , mWebView = ").append(this.a);
                if (this.a instanceof ThunderWebView) {
                    this.a.setCurShowView(CurrentShowState.show_error);
                }
            case JsInterface.MSG_JS_JUMP_TO_RESOURCE_CHANNEL:
                if (!(this.b instanceof p)) {
                }
            case JsInterface.MSG_JS_SHOW_LOADING_VIEW:
                this.a.setCurShowView(CurrentShowState.show_loading);
            case JsInterface.MSG_JS_LAYER_STATE:
                if (message.arg1 == 0) {
                    this.a.setLayerState(false);
                } else {
                    this.a.setLayerState(true);
                }
            case JsInterface.MSG_JS_REPORT_UMENG:
                if (message.obj instanceof String) {
                    StatReporter.reportByJSON((String) message.obj);
                }
            case JsInterface.MSG_JS_CREATE_TASK:
                if (this.b instanceof p) {
                    try {
                        jSONObject = new JSONObject((String) message.obj);
                        downData = new DownData();
                        try {
                            string2 = jSONObject.getString(SelectCountryActivity.EXTRA_COUNTRY_NAME);
                            a = jSONObject.getString(SocialConstants.PARAM_URL);
                            string3 = jSONObject.getString(DownloadBtFileExplorerActivity.EXTRA_KEY_NAME_TASK_ID);
                            optString = jSONObject.getString(JsInterface.KEY_APK_NAME);
                            ag agVar = new ag();
                            agVar.b = a;
                            agVar.e = string2;
                            agVar.c = optString;
                            agVar.a = string3;
                            bo.a();
                            bo.a(optString);
                            bo.a();
                            bo.a(agVar);
                            downData.a = string2;
                            downData.b = a;
                            downData.e = a;
                            downData.r = 0;
                            downData.f = 0;
                            downData.s = null;
                        } catch (JSONException e4) {
                            e = e4;
                            e.printStackTrace();
                            this.b.a(downData);
                        }
                    } catch (JSONException e222) {
                        JSONException jSONException = e222;
                        downData = null;
                        e = jSONException;
                        e.printStackTrace();
                        this.b.a(downData);
                    }
                    this.b.a(downData);
                }
            case JsInterface.MSG_JS_LOAD_COMPLETE:
                string2 = message.getData().getString(JsInterface.URL_KEY);
                if (this.e != null && this.a != null && this.a.d) {
                    com.xunlei.downloadprovider.web.core.b.a aVar = this.e;
                    if (string2 != null && aVar.a != null) {
                        string = (String) aVar.a.get(string2);
                        if (string != null) {
                            long currentTimeMillis = System.currentTimeMillis();
                            w.a();
                            string = w.a("PageLoad", "finish", string, string2, String.valueOf(currentTimeMillis));
                            w.a();
                            w.a(string, aVar.b);
                            aVar.a.remove(string2);
                        }
                    }
                }
            case JsInterface.MSG_JS_VOD_YUNBO:
                try {
                    jSONObject2 = new JSONObject((String) message.obj);
                    iVar = new i();
                    iVar.a = jSONObject2.optString(WebBrowserActivity.EXTRA_TITLE, com.umeng.a.d);
                    iVar.b = jSONObject2.optString(Impl.COLUMN_GCID, com.umeng.a.d);
                    iVar.c = jSONObject2.optString(Impl.COLUMN_CID, com.umeng.a.d);
                    iVar.d = jSONObject2.optLong("fileSize", 0);
                    iVar.e = jSONObject2.getString(SocialConstants.PARAM_URL);
                    iVar.h = VodSourceType.normal;
                    iVar.g = VodVideoFormat.flv;
                    VodUtil.a();
                    VodUtil.a(this.g, iVar);
                } catch (JSONException e2222) {
                    e2222.printStackTrace();
                }
            case JsInterface.MSG_JS_VOD_FROM_THIRD_SERVER:
                try {
                    jSONObject2 = new JSONObject((String) message.obj);
                    iVar = new i();
                    iVar.g = VodVideoFormat.flv;
                    iVar.h = VodSourceType.third_server;
                    iVar.a = jSONObject2.optString(WebBrowserActivity.EXTRA_TITLE, com.umeng.a.d);
                    iVar.e = jSONObject2.getString(SocialConstants.PARAM_URL);
                    iVar.f = 2;
                    VodUtil.a();
                    VodUtil.a(BrothersApplication.a(), iVar);
                } catch (Exception e5) {
                    e5.printStackTrace();
                }
                if (this.c != null) {
                    this.c.a(message);
                }
            case JsInterface.MSG_JS_REFRESH_USER_INFO:
                string = (String) message.obj;
                if (!TextUtils.isEmpty(string)) {
                    LoginHelper.a();
                    if (LoginHelper.c()) {
                        try {
                            LoginHelper.a().a(new d(this, new JSONObject(string).optString(com.alipay.sdk.authjs.a.c, null)));
                            LoginHelper.a().s();
                        } catch (JSONException e22222) {
                            e22222.printStackTrace();
                        }
                    }
                }
            case 4999:
                Handler handler = this.a.b;
                Object obj = message.obj;
                bundle = message.getData();
                if (bundle != null) {
                    string3 = bundle.getString("jsMethodForClimax");
                    if (!TextUtils.isEmpty(string3)) {
                        this.a.a(string3);
                    } else if (obj == null) {
                        a(null, bundle, -1, handler);
                    } else {
                        a(obj, bundle, 1, handler);
                    }
                }
            case 100220:
                string = (String) message.obj;
                JsInterface.logForJS(new StringBuilder("show : ").append(System.currentTimeMillis()).toString());
                this.a.a(string);
            default:
                if (this.c != null) {
                    this.c.a(message);
                }
        }
    }

    private void a(Object obj, Bundle bundle, int i, Handler handler) {
        if (this.j == null) {
            this.j = Executors.newCachedThreadPool();
        }
        this.j.execute(new a(this, obj, bundle, i, handler));
    }

    private void a(Bundle bundle) {
        if (bundle instanceof Bundle) {
            String string = bundle.getString("pageUrl");
            ArrayList stringArrayList = bundle.getStringArrayList("fileNameList");
            ArrayList stringArrayList2 = bundle.getStringArrayList("cidList");
            ArrayList stringArrayList3 = bundle.getStringArrayList("gcidList");
            ArrayList stringArrayList4 = bundle.getStringArrayList("fileSizeList");
            ArrayList stringArrayList5 = bundle.getStringArrayList("fileFormatList");
            ArrayList stringArrayList6 = bundle.getStringArrayList("downloadUrlList");
            ArrayList stringArrayList7 = bundle.getStringArrayList("downloadCountList");
            ArrayList stringArrayList8 = bundle.getStringArrayList("updateTimeList");
            if (stringArrayList == null) {
                XLToast.a(this.g, XLToastType.XLTOAST_TYPE_NORMAL, "\u6570\u636e\u4e3a\u7a7a");
                return;
            }
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (i < stringArrayList.size()) {
                DownData downData = new DownData();
                downData.a = (String) stringArrayList.get(i);
                downData.s = string;
                if (stringArrayList2 != null && i < stringArrayList2.size()) {
                    downData.c = (String) stringArrayList2.get(i);
                    if (stringArrayList3 != null && i < stringArrayList3.size()) {
                        downData.d = (String) stringArrayList3.get(i);
                        if (stringArrayList4 != null && i < stringArrayList4.size()) {
                            downData.r = com.xunlei.downloadprovider.d.a.a((String) stringArrayList4.get(i));
                            if (stringArrayList5 != null && i < stringArrayList5.size()) {
                                downData.h = (String) stringArrayList5.get(i);
                                if (stringArrayList6 != null && i < stringArrayList6.size()) {
                                    downData.e = (String) stringArrayList6.get(i);
                                    downData.b = downData.e;
                                    if (stringArrayList7 != null && i < stringArrayList7.size()) {
                                        downData.j = com.xunlei.downloadprovider.d.a.b((String) stringArrayList7.get(i));
                                        if (stringArrayList8 != null && i < stringArrayList8.size()) {
                                            downData.l = f.a((String) stringArrayList8.get(i));
                                            arrayList.add(downData);
                                            i++;
                                        } else {
                                            return;
                                        }
                                    }
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
        }
    }

    private void b(Bundle bundle) {
        if (bundle instanceof Bundle) {
            String string = bundle.getString(SocialConstants.PARAM_URL);
            String string2 = bundle.getString("isCollection");
            ArrayList stringArrayList = bundle.getStringArrayList("fileNameList");
            ArrayList stringArrayList2 = bundle.getStringArrayList("fileCidList");
            ArrayList stringArrayList3 = bundle.getStringArrayList("fileGcidList");
            ArrayList stringArrayList4 = bundle.getStringArrayList("fileSizeList");
            ArrayList stringArrayList5 = bundle.getStringArrayList("fileFormatList");
            ArrayList stringArrayList6 = bundle.getStringArrayList("fileCaptionList");
            ArrayList stringArrayList7 = bundle.getStringArrayList("fileHotList");
            ArrayList stringArrayList8 = bundle.getStringArrayList("updateTimeList");
            if (stringArrayList == null) {
                XLToast.a(this.g, XLToastType.XLTOAST_TYPE_NORMAL, "\u6570\u636e\u4e3a\u7a7a");
                return;
            }
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (i < stringArrayList.size()) {
                DownData downData = new DownData();
                downData.a = (String) stringArrayList.get(i);
                downData.s = string;
                if (stringArrayList2 != null && i < stringArrayList2.size()) {
                    downData.c = (String) stringArrayList2.get(i);
                    if (stringArrayList3 != null && i < stringArrayList3.size()) {
                        downData.d = (String) stringArrayList3.get(i);
                        if (stringArrayList4 != null && i < stringArrayList4.size()) {
                            downData.r = com.xunlei.downloadprovider.d.a.a((String) stringArrayList4.get(i));
                            if (stringArrayList5 != null && i < stringArrayList5.size()) {
                                downData.h = (String) stringArrayList5.get(i);
                                if (stringArrayList6 != null && i < stringArrayList6.size()) {
                                    downData.i = (String) stringArrayList6.get(i);
                                    if (stringArrayList7 != null && i < stringArrayList7.size()) {
                                        downData.j = com.xunlei.downloadprovider.d.a.b((String) stringArrayList7.get(i));
                                        if (stringArrayList8 != null && i < stringArrayList8.size()) {
                                            downData.l = f.a((String) stringArrayList8.get(i));
                                            downData.k = Boolean.parseBoolean(string2);
                                            arrayList.add(downData);
                                            i++;
                                        } else {
                                            return;
                                        }
                                    }
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
        }
    }

    private static String b(String str, String str2, String str3, int i, String str4) {
        new StringBuilder("pageUrl: ").append(str).append(", realUrl: ").append(str2).append(", callback: ").append(str3).append(", rtn: ").append(i).append(", content: ").append(str4);
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(str3)) {
            JSONObject jSONObject = new JSONObject();
            stringBuilder.append(BaseJsInterface.JS_PREFIX);
            stringBuilder.append(str3);
            stringBuilder.append(SocializeConstants.OP_OPEN_PAREN);
            String str5 = com.umeng.a.d;
            try {
                Object obj;
                if (TextUtils.isEmpty(str4)) {
                    obj = com.umeng.a.d;
                    i = -1;
                } else {
                    try {
                        obj = URLEncoder.encode(str4.trim().replaceAll(" ", "%20"), GameManager.DEFAULT_CHARSET);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        String str6 = str5;
                    }
                }
                jSONObject.putOpt("pageContent", obj);
                jSONObject.putOpt("pageUrl", str);
                jSONObject.putOpt("realUrl", str2);
                jSONObject.putOpt("isRobot", Integer.valueOf(1));
                jSONObject.putOpt("rtn", Integer.valueOf(i));
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
            stringBuilder.append(jSONObject.toString());
            stringBuilder.append(SocializeConstants.OP_CLOSE_PAREN);
        }
        return stringBuilder.toString();
    }

    public final void a(String str) {
        if (this.e != null && this.a != null && this.a.d) {
            com.xunlei.downloadprovider.web.core.b.a aVar = this.e;
            if (str != null && aVar.a != null) {
                aVar.a.remove(str);
            }
        }
    }
}
