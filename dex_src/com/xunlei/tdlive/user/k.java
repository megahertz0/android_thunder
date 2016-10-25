package com.xunlei.tdlive.user;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.mm.sdk.modelmsg.SendAuth.Req;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.common.register.XLRegErrorCode;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.user.k.c;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.f;
import com.xunlei.tdlive.util.f$a;
import com.xunlei.tdlive.util.f.e;
import com.xunlei.tdlive.util.f.h;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: WxHelper.java
public class k extends e<JsonWrapper> {
    private static k c;
    private a a;
    private b b;
    private c d;

    // compiled from: WxHelper.java
    public static interface a {
        void a(String str);
    }

    // compiled from: WxHelper.java
    public static interface b {
        void a(c cVar);
    }

    // compiled from: WxHelper.java
    public static final class c {
        public String a;
        public String b;
        public String c;
        public String d;

        public final String toString() {
            return new StringBuilder("WxUserInfo: ").append(this.b).append(", openid = ").append(this.a).append(", unionid = ").append(this.d).toString();
        }
    }

    public /* synthetic */ Object onParseResult(String str) {
        return a(str);
    }

    private k() {
    }

    public static k a() {
        if (c == null) {
            c = new k();
        }
        return c;
    }

    public void a(Context context, a aVar, b bVar) {
        this.d = null;
        this.a = aVar;
        this.b = bVar;
        IWXAPI createWXAPI = WXAPIFactory.createWXAPI(context, "wx18eada9ea7fbf76c", true);
        Req req = new Req();
        req.scope = "snsapi_userinfo";
        req.state = "transaction_xl_wx_bind";
        req.transaction = "transaction_xl_wx_bind";
        XLog.d("WxHelper", new StringBuilder("getUnionId ret = ").append(createWXAPI.sendReq(req)).toString());
    }

    private void a(String str, String str2) {
        new f().a(com.xunlei.tdlive.util.f.c.a, String.format("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s", new Object[]{str, str2}), null, new l(this));
    }

    public void a(int i, String str) {
        if (i == 0) {
            new f().a(com.xunlei.tdlive.util.f.c.a, String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", new Object[]{"wx18eada9ea7fbf76c", "6a56279bf3bcfadf414246e106bb98ad", str}), null, this);
            return;
        }
        XLog.i("WxHelper", new StringBuilder("get wx code failed. err = ").append(i).toString());
        if (this.a != null) {
            this.a.a(null);
        }
    }

    public JsonWrapper a(String str) {
        if (str.length() > 300) {
            XLog.d("WxHelper", new StringBuilder("JsonRequestCallBack.onParseResult result=").append(str.substring(0, XLRegErrorCode.REG_NOT_ENOUGH_PARAM)).toString());
        } else {
            XLog.d("WxHelper", new StringBuilder("JsonRequestCallBack.onParseResult result=").append(str).toString());
        }
        return new JsonWrapper(str);
    }

    public void onSuccess(h<JsonWrapper> hVar) {
        String string = ((JsonWrapper) hVar.a).getString("unionid", BuildConfig.VERSION_NAME);
        String string2 = ((JsonWrapper) hVar.a).getString(SocializeProtocolConstants.PROTOCOL_KEY_OPENID, BuildConfig.VERSION_NAME);
        String string3 = ((JsonWrapper) hVar.a).getString(XiaomiOAuthConstants.EXTRA_ACCESS_TOKEN_2, BuildConfig.VERSION_NAME);
        XLog.i("WxHelper", new StringBuilder("get unionid = ").append(string).append(", openid = ").append(string2).append(", accessToken = ").append(string3).toString());
        if (!(this.b == null || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3))) {
            a(string3, string2);
        }
        if (this.a != null) {
            this.a.a(string);
        }
    }

    public void onFailure(f$a com_xunlei_tdlive_util_f_a, String str) {
        if (this.a != null) {
            this.a.a(null);
        }
        XLog.i("WxHelper", new StringBuilder("get union id error: ").append(str).append(", err = ").append(com_xunlei_tdlive_util_f_a).toString());
    }
}
