package com.tencent.open.yyb;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.alipay.sdk.packet.d;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.open.a.f;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.yyb.a.a;
import java.util.regex.Pattern;

// compiled from: ProGuard
public class AppbarAgent extends BaseApi {
    public static final String TO_APPBAR_DETAIL = "siteIndex";
    public static final String TO_APPBAR_NEWS = "myMessage";
    public static final String TO_APPBAR_SEND_BLOG = "newThread";
    public static final String wx_appid = "wx8e8dc60535c9cd93";
    private Bundle a;
    private String b;

    public AppbarAgent(QQToken qQToken) {
        super(qQToken);
    }

    public void startAppbarLabel(Activity activity, String str) {
        if (TextUtils.isEmpty(str)) {
            Toast.makeText(activity, Constants.MSG_PARAM_ERROR, 0).show();
            return;
        }
        this.a = new Bundle();
        this.a.putString(d.l, new StringBuilder("label/").append(str).toString());
        startAppbar(activity, "sId");
    }

    public void startAppbarThread(Activity activity, String str) {
        if (d(str)) {
            this.b = str;
            startAppbar(activity, "toThread");
            return;
        }
        Toast.makeText(activity, Constants.MSG_PARAM_ERROR, 0).show();
    }

    public void startAppbar(Activity activity, String str) {
        if (a(str)) {
            String c = c(str);
            Object b = b();
            if (TextUtils.isEmpty(b) || SystemUtils.compareVersion(b, "4.2") < 0) {
                a(activity, c);
                return;
            }
            String str2 = c + a();
            f.a("openSDK_LOG.AppbarAgent", new StringBuilder("-->(AppbarAgent)startAppbar : yybUrl = ").append(str2).toString());
            try {
                Intent intent = new Intent();
                intent.setClassName("com.tencent.android.qqdownloader", "com.tencent.assistant.activity.ExportBrowserActivity");
                intent.putExtra("com.tencent.assistant.BROWSER_URL", str2);
                activity.startActivity(intent);
                activity.overridePendingTransition(17432576, 17432577);
                return;
            } catch (Throwable e) {
                f.b("openSDK_LOG.AppbarAgent", "-->(AppbarAgent)startAppbar : ExportBrowserActivity not found, start H5", e);
                a(activity, c);
            }
        }
        Toast.makeText(activity, Constants.MSG_PARAM_ERROR, 0).show();
    }

    private boolean a(String str) {
        return TO_APPBAR_DETAIL.equals(str) || TO_APPBAR_NEWS.equals(str) || TO_APPBAR_SEND_BLOG.equals(str) || "sId".equals(str) || "toThread".equals(str);
    }

    private void a(Activity activity, String str) {
        if (this.mToken != null) {
            Intent intent = new Intent(activity, AppbarActivity.class);
            intent.putExtra(SocialConstants.PARAM_APP_ID, this.mToken.getAppId());
            if (!(this.mToken.getAccessToken() == null || this.mToken.getOpenId() == null)) {
                a aVar = new a();
                aVar.b = this.mToken.getAccessToken();
                aVar.c = Long.parseLong(this.mToken.getAppId());
                aVar.a = this.mToken.getOpenId();
                a.a(activity, str, this.mToken.getOpenId(), this.mToken.getAccessToken(), this.mToken.getAppId());
            }
            intent.putExtra(SocialConstants.PARAM_URL, str);
            f.a("openSDK_LOG.AppbarAgent", new StringBuilder("-->(AppbarAgent)startAppbar H5 : url = ").append(str).toString());
            try {
                activity.startActivityForResult(intent, Constants.REQUEST_APPBAR);
            } catch (Throwable e) {
                f.b("openSDK_LOG.AppbarAgent", "-->(AppbarAgent)startAppbar : activity not found, start H5", e);
            }
        }
    }

    private Bundle b(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("pkgName", Global.getContext().getPackageName());
        if (!(TO_APPBAR_DETAIL.equals(str) || TO_APPBAR_SEND_BLOG.equals(str))) {
            if (TO_APPBAR_NEWS.equals(str)) {
                bundle.putString(SocialConstants.PARAM_SOURCE, "myapp");
            } else if ("sId".equals(str)) {
                if (this.a != null) {
                    bundle.putAll(this.a);
                }
            } else if ("toThread".equals(str)) {
                str = String.format("sId/t/%s", new Object[]{this.b});
            }
        }
        bundle.putString("route", str);
        return bundle;
    }

    private String c(String str) {
        StringBuilder stringBuilder = new StringBuilder("http://m.wsq.qq.com/direct?");
        stringBuilder.append(a(b(str)));
        return stringBuilder.toString();
    }

    private String a() {
        Bundle bundle = new Bundle();
        if (!(this.mToken == null || this.mToken.getAppId() == null || this.mToken.getAccessToken() == null || this.mToken.getOpenId() == null)) {
            bundle.putString("qOpenAppId", this.mToken.getAppId());
            bundle.putString("qOpenId", this.mToken.getOpenId());
            bundle.putString("qAccessToken", this.mToken.getAccessToken());
        }
        bundle.putString("qPackageName", Global.getContext().getPackageName());
        return new StringBuilder(com.alipay.sdk.sys.a.b).append(a(bundle)).toString();
    }

    private String a(Bundle bundle) {
        if (bundle == null || bundle.isEmpty()) {
            return com.umeng.a.d;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String toString : bundle.keySet()) {
            stringBuilder.append(toString).append("=").append(bundle.get(toString)).append(com.alipay.sdk.sys.a.b);
        }
        String toString2 = stringBuilder.toString();
        if (toString2.endsWith(com.alipay.sdk.sys.a.b)) {
            toString2 = toString2.substring(0, toString2.length() - 1);
        }
        f.a("openSDK_LOG.AppbarAgent", new StringBuilder("-->encodeParams, result: ").append(toString2).toString());
        return toString2;
    }

    private String b() {
        try {
            PackageInfo packageInfo = Global.getContext().getPackageManager().getPackageInfo("com.tencent.android.qqdownloader", 0);
            return packageInfo == null ? null : packageInfo.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean d(String str) {
        return !TextUtils.isEmpty(str) && Pattern.matches("^[1-9][0-9]*$", str);
    }
}
