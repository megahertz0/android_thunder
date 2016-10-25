package com.baidu.mobads.vo.a;

import android.content.Context;
import com.baidu.mobads.command.XAdCommandExtraInfo;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.baidu.mobads.j.d;
import com.baidu.mobads.j.m;
import com.sina.weibo.sdk.component.GameManager;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.taobao.accs.common.Constants;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.common.SocializeConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public abstract class a {
    public long a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    protected Context k;
    protected d l;
    protected IXAdSystemUtils m;

    protected abstract HashMap<String, String> b();

    public a(XAdCommandExtraInfo xAdCommandExtraInfo) {
        this(xAdCommandExtraInfo.getAdInstanceInfo().getAdId(), xAdCommandExtraInfo.getAdInstanceInfo().getQueryKey(), xAdCommandExtraInfo.mProdType);
    }

    public a(String str, String str2, String str3) {
        this.b = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
        this.c = com.umeng.a.d;
        this.d = com.umeng.a.d;
        this.e = com.umeng.a.d;
        this.f = com.umeng.a.d;
        this.g = com.umeng.a.d;
        this.h = com.umeng.a.d;
        this.j = com.umeng.a.d;
        this.l = m.a().m();
        this.m = m.a().n();
        this.k = m.a().d();
        this.a = System.currentTimeMillis();
        this.b = str;
        this.c = str2;
        this.e = this.l.getAppSec(this.k);
        if (this.k != null) {
            this.d = this.k.getPackageName();
        }
        this.f = this.l.getAppId(this.k);
        this.h = this.m.getEncodedSN(this.k);
        this.i = anet.channel.strategy.dispatch.a.ANDROID;
        this.g = new StringBuilder("android_").append(com.baidu.mobads.a.a.c).append("_4.1.30").toString();
        this.j = str3;
    }

    protected HashMap<String, String> a() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("adid", this.b);
        hashMap.put("appsec", this.e);
        hashMap.put("appsid", this.f);
        hashMap.put("pack", this.d);
        hashMap.put("qk", this.c);
        hashMap.put(IXAdRequestInfo.SN, this.h);
        hashMap.put(MsgConstant.KEY_TS, this.a);
        hashMap.put(IXAdRequestInfo.V, this.g);
        hashMap.put(Constants.KEY_OS_VERSION, this.i);
        hashMap.put("prod", this.j);
        hashMap.put(IXAdRequestInfo.P_VER, "8.27");
        return hashMap;
    }

    public HashMap<String, String> c() {
        HashMap<String, String> a = a();
        Map b = b();
        if (b != null) {
            a.putAll(b);
        }
        return a;
    }

    public String toString() {
        return a(c());
    }

    protected String a(HashMap<String, String> hashMap) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            d m = m.a().m();
            StringBuilder stringBuilder2 = new StringBuilder();
            for (String a : hashMap.keySet()) {
                String a2;
                String str = (String) hashMap.get(a2);
                if (a2 != null && str != null) {
                    a2 = a(a2);
                    str = a(str);
                    stringBuilder.append(a2 + "=" + str + com.alipay.sdk.sys.a.b);
                    stringBuilder2.append(str + MiPushClient.ACCEPT_TIME_SEPARATOR);
                }
            }
            stringBuilder2.append("mobads,");
            stringBuilder.append(new StringBuilder("vd=").append(m.getMD5(stringBuilder2.toString())).append(com.alipay.sdk.sys.a.b).toString());
            return stringBuilder.toString();
        } catch (Exception e) {
            return com.umeng.a.d;
        }
    }

    protected String a(String str) {
        try {
            return URLEncoder.encode(str, GameManager.DEFAULT_CHARSET).replaceAll("\\+", "%20").replaceAll("\\%21", "!").replaceAll("\\%27", "'").replaceAll("\\%28", SocializeConstants.OP_OPEN_PAREN).replaceAll("\\%29", SocializeConstants.OP_CLOSE_PAREN).replaceAll("\\%7E", "~");
        } catch (Exception e) {
            return str;
        }
    }
}
