package com.xunlei.downloadprovider.web;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.tencent.open.SocialConstants;
import com.tencent.stat.DeviceInfo;
import com.umeng.socialize.PlatformConfig.Alipay;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.common.lixian.XLLX_NEWTASK;
import com.xunlei.common.lixian.XLLixianUtil;
import com.xunlei.common.pay.XLPayUtil;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.downloadprovider.download.report.DLCenterEntry;
import com.xunlei.downloadprovider.homepage.relax.RelaxListFragment;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.payment.external.OperType;
import com.xunlei.downloadprovider.member.payment.external.PayEntryParam;
import com.xunlei.downloadprovider.member.payment.external.PayFrom;
import com.xunlei.downloadprovider.member.payment.external.PaymentEntryActivity;
import com.xunlei.downloadprovider.search.ui.search.SearchActivity;
import com.xunlei.downloadprovider.service.DownloadEngine;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.b;
import com.xunlei.downloadprovider.web.BrowserUtil.StartFromType;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.downloadprovidershare.d;
import com.xunlei.downloadprovidershare.data.ShareBean;
import com.xunlei.tdlive.WebBrowserActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: DetailPageBrowserActivity.java
final class g implements a {
    final /* synthetic */ DetailPageBrowserActivity a;
    private String b;

    g(DetailPageBrowserActivity detailPageBrowserActivity) {
        this.a = detailPageBrowserActivity;
        this.b = com.umeng.a.d;
    }

    public final void a(Message message) {
        String string;
        String str;
        Bundle bundle;
        JSONObject jSONObject;
        Bundle data;
        String string2;
        String string3;
        JSONObject jSONObject2;
        String optString;
        String optString2;
        String optString3;
        switch (message.what) {
            case JsInterface.MSG_JS_OPEN_BROWSER_PAGE:
                string = message.getData().getString(JsInterface.URL_KEY);
                BrowserUtil.a();
                Context context = this.a;
                this.a.n;
                BrowserUtil.b(context, string, true, null);
            case JsInterface.MSG_JS_OPEN_DETAIL_PAGE:
                string = message.getData().getString(JsInterface.URL_KEY);
                str = com.umeng.a.d;
                bundle = new Bundle();
                bundle.putString(JsInterface.FROM_KEY, this.a.o);
                BrowserUtil.a();
                BrowserUtil.a(this.a, string, str, this.a.n, bundle);
                DetailPageBrowserActivity.c;
            case JsInterface.MSG_JS_OPEN_DETAIL_PAGE_CALLBACK:
                if (message.obj != null && (message.obj instanceof String)) {
                    try {
                        jSONObject = new JSONObject((String) message.obj);
                        string = jSONObject.optString(SocialConstants.PARAM_URL);
                        str = jSONObject.optString(SocialConstants.PARAM_SEND_MSG);
                        bundle = new Bundle();
                        bundle.putString("ToastMessage", str);
                        BrowserUtil.a();
                        BrowserUtil.a(this.a, string, com.umeng.a.d, bundle);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            case JsInterface.MSG_JS_HIDE_BOTTOM_TIP:
                this.a.h.a();
            case JsInterface.MSG_JS_DISABLE_BOTTOM_TIP:
                this.a.h.a = true;
            case JsInterface.MSG_JS_SHOW_BOTTOM_TIP:
                com.xunlei.downloadprovider.web.core.a d = this.a.h;
                if (!d.a && !TextUtils.isEmpty(d.c.getText().toString())) {
                    d.b.setVisibility(0);
                }
            case JsInterface.MSG_JS_OPEN_SEARCH_RESULT_PAGE:
                data = message.getData();
                str = data.getString(JsInterface.NAME_KEY);
                data.putString(JsInterface.FROM_KEY, "search_download");
                SearchActivity.a(this.a, com.umeng.a.d, str);
            case JsInterface.MSG_JS_SHOW_UMENG_SHARE:
                data = message.getData();
                str = data.getString(JsInterface.SHARE_PAGEURL_KEY);
                string2 = data.getString(JsInterface.SHARE_IMGURL_KEY);
                string3 = data.getString(JsInterface.SHARE_TITLE_KEY);
                data.getString(JsInterface.SHARE_TYPE_KEY);
                d.b().a(this.a, new ShareBean(str, string2, string3, com.umeng.a.d), this.a);
            case JsInterface.MSG_JS_GOTO_LOGIN_PAGE:
                new StringBuilder().append(getClass()).append("---JsInterface.MSG_JS_GOTO_LOGIN_PAGE---mTitle.mTitle.getText().toString()").append(this.a.e.i.getText().toString()).append("---").append(Thread.currentThread().getId());
                if (message.obj instanceof String) {
                    try {
                        jSONObject = new JSONObject((String) message.obj);
                        if (jSONObject.has("which")) {
                            this.b = new StringBuilder("_").append(jSONObject.getString("which")).toString();
                        }
                    } catch (Exception e2) {
                    }
                }
                if (this.a.k != null) {
                    LoginHelper.a().a(this.a, new h(this), (int) XZBDevice.Delete, this.a.e.i.getText().toString() + this.b);
                }
            case JsInterface.MSG_JS_CREATE_LIXIAN_TASK:
                if (message.obj instanceof String) {
                    string = (String) message.obj;
                    DownloadService a = DownloadService.a();
                    Handler e3 = this.a.y;
                    DownloadEngine downloadEngine = a.d;
                    if (!TextUtils.isEmpty(string)) {
                        try {
                            jSONObject2 = new JSONObject(string);
                            string = jSONObject2.optString(SocialConstants.PARAM_URL);
                            optString = jSONObject2.optString("cookie");
                            optString2 = jSONObject2.optString("refUrl");
                            String optString4 = jSONObject2.optString(Impl.COLUMN_CID);
                            optString3 = jSONObject2.optString(Impl.COLUMN_GCID);
                            String optString5 = jSONObject2.optString("taskName");
                            long optLong = jSONObject2.optLong("filesize");
                            XLLX_NEWTASK xllx_newtask = new XLLX_NEWTASK();
                            if (string != null) {
                                xllx_newtask.url = string;
                            }
                            if (optString != null) {
                                xllx_newtask.cookies = optString;
                            }
                            if (optString2 != null) {
                                xllx_newtask.ref_url = optString2;
                            }
                            if (optString4 != null) {
                                xllx_newtask.cid = optString4;
                            }
                            if (optString3 != null) {
                                xllx_newtask.gcid = optString3;
                            }
                            if (optString5 != null) {
                                xllx_newtask.taskname = optString5;
                            }
                            xllx_newtask.filesize = optLong;
                            XLLixianUtil.getInstance().createLixianTask(xllx_newtask, null, new b(downloadEngine, e3));
                        } catch (JSONException e4) {
                            e4.printStackTrace();
                        }
                    }
                }
            case JsInterface.MSG_JS_SHOW_UMENG_SHARE_NEW:
                ShareBean shareBean;
                bundle = message.getData();
                bundle.getString(JsInterface.SHARE_PAGEURL_KEY);
                string3 = bundle.getString(JsInterface.SHARE_IMGURL_KEY);
                CharSequence string4 = bundle.getString(JsInterface.SHARE_TITLE_KEY);
                bundle.getString(JsInterface.SHARE_TYPE_KEY);
                optString2 = bundle.getString(JsInterface.SHARE_CONTENT_KEY);
                long j = bundle.getLong(JsInterface.SHARE_IMAGE_ID);
                if (TextUtils.isEmpty(string4)) {
                    string = optString2;
                }
                str = bundle.getString(JsInterface.SHARE_FROM_KEY);
                string2 = bundle.getString(JsInterface.SHARE_WXURL_KEY);
                if (string2.endsWith("joy_picture")) {
                    str = "fun_detail";
                    if (TextUtils.isEmpty(string4)) {
                        optString = "\u8da3\u56fe";
                        shareBean = new ShareBean(str, string2, string3, optString, optString2);
                        shareBean.k = j;
                        d.b().a(this.a, shareBean, this.a);
                    }
                }
                CharSequence charSequence = string4;
                shareBean = new ShareBean(str, string2, string3, optString, optString2);
                shareBean.k = j;
                d.b().a(this.a, shareBean, this.a);
            case JsInterface.MSG_JS_GOTO_LOGIN_PAGE_AND_CALLBACK:
                if (message.obj instanceof String) {
                    DetailPageBrowserActivity.b(this.a, (String) message.obj);
                }
            case JsInterface.MSG_JS_GOTO_LOGOUT_PAGE_AND_CALLBACK:
                if (message.obj instanceof String) {
                    DetailPageBrowserActivity.c(this.a, (String) message.obj);
                }
            case JsInterface.MSG_JS_GO_TO_DOWNLOAD_LIST:
                DownloadCenterActivity.a(this.a, DLCenterEntry.browser.toString());
            case JsInterface.MSG_JS_INSTALL_APK:
                try {
                    string = (String) message.obj;
                    if (string != null) {
                        File file = new File(string);
                        if (file.exists()) {
                            Uri fromFile = Uri.fromFile(file);
                            Intent intent = new Intent("android.intent.action.VIEW");
                            intent.setDataAndType(fromFile, "application/vnd.android.package-archive");
                            intent.setFlags(268435456);
                            this.a.startActivity(intent);
                        }
                    }
                } catch (Exception e5) {
                }
            case JsInterface.MSG_JS_GET_DISPLAY_SNIFF_PAGE_URL:
                StartFromType startFromType = StartFromType.unknow;
                DetailPageBrowserActivity.c;
                new StringBuilder(" mFromKey  ").append(this.a.o);
                if (!TextUtils.isEmpty(this.a.o)) {
                    if (this.a.o.equals("home_hot_search") || this.a.o.equals("hot_search")) {
                        startFromType = StartFromType.sniff_search_hot_top_list;
                    } else if (this.a.o.equals("channel_hot_movie")) {
                        startFromType = StartFromType.sniff_channel_detail;
                    } else if (this.a.o.equals("friend_detail")) {
                        startFromType = StartFromType.sniff_friend_detail;
                    } else if (this.a.o.equals("climax")) {
                        startFromType = StartFromType.sniff_funplay_climax;
                    } else if (this.a.o.equals("search_result_page")) {
                        startFromType = StartFromType.sniff_search_result_page;
                    } else if (this.a.o.equals("sniff_home_page_hot_movies_recommend")) {
                        startFromType = StartFromType.sniff_home_page_hot_movies_recommend;
                    } else if (this.a.o.equals("home_hot_special")) {
                        startFromType = StartFromType.detail_page;
                    }
                }
                try {
                    JSONObject jSONObject3 = new JSONObject((String) message.obj);
                    BrowserUtil.a();
                    BrowserUtil.a(this.a, 0, jSONObject3.optString("display_baidu_url"), false, startFromType, true);
                } catch (JSONException e42) {
                    e42.printStackTrace();
                }
            case JsInterface.MSG_JS_GO_TO_ACTIVITY_VIP_FOR_USER_CENTER:
                if (!com.xunlei.xllib.a.b.a(this.a)) {
                    XLToast.a(this.a, XLToastType.XLTOAST_TYPE_ALARM, this.a.getString(2131232509));
                } else if (message.obj instanceof String) {
                    try {
                        jSONObject = new JSONObject((String) message.obj);
                        int optInt = jSONObject.optInt("operType");
                        OperType operType = OperType.NORMAL;
                        if (optInt == 1) {
                            operType = OperType.UPGRADE;
                        }
                        string2 = jSONObject.getString("refer");
                        this.a.u = jSONObject.getString("fresh_callback");
                        DetailPageBrowserActivity.a(this.a, string2, operType);
                    } catch (Exception e6) {
                        e6.printStackTrace();
                    }
                }
            case JsInterface.MSG_JS_VOD_FROM_THIRD_SERVER:
                if (message.obj instanceof String) {
                    try {
                        jSONObject2 = new JSONObject((String) message.obj);
                        str = jSONObject2.optString(SocializeConstants.WEIBO_ID);
                        string2 = jSONObject2.optString(WebBrowserActivity.EXTRA_TITLE);
                        optString = jSONObject2.optString(SocialConstants.PARAM_URL);
                        optString2 = jSONObject2.optString("videoType");
                        w.a();
                        string = w.a("start", str, string2, this.a.k, optString, optString2, WeiboAuthException.DEFAULT_AUTH_ERROR_CODE, "funny");
                        w.a();
                        w.b(string);
                    } catch (JSONException e422) {
                        e422.printStackTrace();
                    }
                }
            case JsInterface.MSG_JS_DETAILPAGE_PAY_FOR_CHOUJIANG:
                if (message.obj instanceof String) {
                    try {
                        this.a.s = new JSONObject((String) message.obj).optString(com.alipay.sdk.authjs.a.c, null);
                        PaymentEntryActivity.a(this.a, new PayEntryParam(PayFrom.PROMOTION_CHOU_JIANG));
                    } catch (Exception e7) {
                    }
                }
            case JsInterface.MSG_DIRECT_PAY_AND_CALLBACK:
                if (message.obj != null && (message.obj instanceof String)) {
                    try {
                        JSONObject jSONObject4 = new JSONObject((String) message.obj);
                        string2 = jSONObject4.getString("reportRefer");
                        optString3 = jSONObject4.getString("payment");
                        int i = jSONObject4.getInt("monthOrTDays");
                        int i2 = jSONObject4.getInt("orderType");
                        int i3 = jSONObject4.getInt("vasType");
                        string = jSONObject4.optString("orderVoucher");
                        str = jSONObject4.optString("orderExtraParam");
                        this.a.w = jSONObject4.getString(com.alipay.sdk.authjs.a.c);
                        XLPayUtil.getInstance().attachListener(this.a.C);
                        if ("weixin".equals(optString3)) {
                            DetailPageBrowserActivity.a(string, str, string2, i, i2, i3);
                        } else if (Alipay.Name.equals(optString3)) {
                            DetailPageBrowserActivity.a(this.a, string, str, string2, i, i2, i3);
                        }
                    } catch (JSONException e4222) {
                        e4222.printStackTrace();
                    }
                }
            case JsInterface.MSG_SIGN_IN_CALLBACK:
                DetailPageBrowserActivity.a(this.a, message);
            case JsInterface.MSG_SEND_RELAX_GOODPAGE_TO_CLIENT:
                Bundle data2 = message.getData();
                string2 = data2.getString(DeviceInfo.TAG_MID);
                string3 = data2.getString("num");
                for (int i4 = 0; i4 < RelaxListFragment.a.size(); i4++) {
                    if (((long) Integer.parseInt(string2)) == ((com.xunlei.downloadprovider.model.protocol.b.d) RelaxListFragment.a.get(i4)).a) {
                        ((com.xunlei.downloadprovider.model.protocol.b.d) RelaxListFragment.a.get(i4)).r.f = 1;
                        ((com.xunlei.downloadprovider.model.protocol.b.d) RelaxListFragment.a.get(i4)).r.d = Integer.parseInt(string3);
                        RelaxListFragment.b.notifyDataSetChanged();
                    }
                }
            default:
                break;
        }
    }
}
