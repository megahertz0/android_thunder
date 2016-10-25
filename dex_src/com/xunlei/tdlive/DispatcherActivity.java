package com.xunlei.tdlive;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.tencent.open.SocialConstants;
import com.umeng.a;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.protocol.XLLiveRequestSimple;
import com.xunlei.tdlive.user.f;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashMap;
import org.android.spdy.SpdyAgent;

public class DispatcherActivity extends BaseActivity {
    private static HashMap<String, Class<?>> a;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        finish();
        try {
            if (!a((Context) this, getIntent().getData(), 0)) {
                startActivity(new Intent(this, MainActivity.class).setAction("android.intent.action.MAIN").addCategory("android.intent.category.LAUNCHER").addFlags(268468224));
            }
        } catch (Exception e) {
        }
    }

    public static boolean a(Context context, Uri uri, int i) {
        try {
            if (!"tdlive".equals(uri.getScheme()) && !"xllive".equals(uri.getScheme())) {
                return false;
            }
            String queryParameter = uri.getQueryParameter(JsInterface.FUNPLAY_AD_TRPE);
            return a(context, Integer.valueOf(queryParameter).intValue(), uri.getQueryParameter(WebBrowserActivity.EXTRA_TITLE), uri.getQueryParameter(SocialConstants.PARAM_URL), i);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean a(Context context, int i, String str, String str2, int i2) {
        if (context == null || i < 0 || i > 3 || str2 == null || str2.length() <= 0) {
            return false;
        }
        if ((str == null || str.length() <= 0) && i != 1) {
            str = context.getResources().getString(R.string.xllive_app_name);
        }
        switch (i) {
            case SpdyAgent.ACCS_TEST_SERVER:
                RecommandActivity.a(context, str2, str, i2);
                return true;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                WebBrowserActivity.start(context, str2, str, false, i2);
                return true;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return a(context, new StringBuilder("tdlive://room?url=").append(str2).toString(), i2);
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return b(context, i, str, str2, i2);
            default:
                return true;
        }
    }

    public static boolean a(Context context, String str, int i) {
        return b(context, 0, a.d, str, i);
    }

    public static boolean b(Context context, int i, String str, String str2, int i2) {
        if (a == null) {
            HashMap hashMap = new HashMap();
            a = hashMap;
            hashMap.put("feedback", FeedbackActivity.class);
            a.put("recharge", RechargeActivity.class);
            a.put("webview", WebBrowserActivity.class);
            a.put("rank", RankActivity.class);
        }
        try {
            Uri parse = Uri.parse(str2);
            String host = parse.getHost();
            Class cls = (Class) a.get(host);
            if (cls != null) {
                Intent addFlags = new Intent(context, cls).addFlags(i2);
                try {
                    for (String str3 : parse.getQueryParameterNames()) {
                        String queryParameter = parse.getQueryParameter(str3);
                        if (queryParameter != null) {
                            addFlags.putExtra(str3, queryParameter);
                        }
                    }
                } catch (Exception e) {
                }
                context.startActivity(addFlags);
            } else if ("room".equals(host)) {
                return b(context, parse, i2);
            }
            return true;
        } catch (Exception e2) {
            return false;
        }
    }

    public static boolean b(Context context, Uri uri, int i) {
        String queryParameter = uri.getQueryParameter("roomid");
        String queryParameter2 = uri.getQueryParameter(SocialConstants.PARAM_URL);
        String queryParameter3 = uri.getQueryParameter("from");
        if (queryParameter != null && queryParameter.length() > 0) {
            queryParameter2 = new StringBuilder("http://list.live.xunlei.com/room?type=getinfo&roomid=").append(queryParameter).toString();
        }
        if (queryParameter2 == null || queryParameter2.length() <= 0) {
            return false;
        }
        new XLLiveRequestSimple(f.a(context).k(), f.a(context).l(), queryParameter2).send(new e(i, queryParameter3, context));
        return true;
    }
}
