package com.alipay.apmobilesecuritysdk.face;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.a.a;
import com.alipay.apmobilesecuritysdk.f.d;
import com.alipay.apmobilesecuritysdk.f.g;
import com.alipay.apmobilesecuritysdk.f.h;
import com.alipay.apmobilesecuritysdk.f.i;
import com.alipay.apmobilesecuritysdk.face.APSecuritySdk.InitResultListener;
import com.alipay.apmobilesecuritysdk.face.APSecuritySdk.TokenResult;
import com.alipay.sdk.cons.b;
import com.tencent.open.SocialConstants;
import com.umeng.message.MsgConstant;
import java.util.HashMap;
import java.util.Map;
import org.android.agoo.message.MessageService;

public class APSecuritySdk {
    private static APSecuritySdk a;
    private static Object c;
    private Context b;

    class AnonymousClass_1 implements Runnable {
        final /* synthetic */ Map a;
        final /* synthetic */ InitResultListener b;

        AnonymousClass_1(Map map, InitResultListener initResultListener) {
            this.a = map;
            this.b = initResultListener;
        }

        public void run() {
            new a(APSecuritySdk.this.b).a(this.a);
            if (this.b != null) {
                this.b.onResult(APSecuritySdk.this.getTokenResult());
            }
        }
    }

    public static interface InitResultListener {
        void onResult(TokenResult tokenResult);
    }

    public class TokenResult {
        public String apdid;
        public String apdidToken;
        public String clientKey;
        public String umidToken;
    }

    static {
        c = new Object();
    }

    private APSecuritySdk(Context context) {
        this.b = context;
    }

    public static APSecuritySdk getInstance(Context context) {
        if (a == null) {
            synchronized (c) {
                if (a == null) {
                    a = new APSecuritySdk(context);
                }
            }
        }
        return a;
    }

    public static String getUtdid(Context context) {
        return com.umeng.a.d;
    }

    public String getApdidToken() {
        return a.a(this.b, com.umeng.a.d);
    }

    public String getSdkName() {
        return "security-sdk-token";
    }

    public String getSdkVersion() {
        return "3.2.0-20160621";
    }

    public synchronized TokenResult getTokenResult() {
        TokenResult tokenResult;
        tokenResult = new TokenResult();
        try {
            tokenResult.apdidToken = a.a(this.b, com.umeng.a.d);
            tokenResult.clientKey = h.c(this.b);
            tokenResult.apdid = a.a(this.b);
            tokenResult.umidToken = com.alipay.apmobilesecuritysdk.e.a.a();
        } catch (Throwable th) {
        }
        return tokenResult;
    }

    public void initToken(int i, Map<String, String> map, InitResultListener initResultListener) {
        Object obj;
        com.alipay.apmobilesecuritysdk.b.a.a().a(i);
        String a = h.a(this.b);
        String c = com.alipay.apmobilesecuritysdk.b.a.a().c();
        if (com.alipay.b.a.a.a.a.b(a) && !com.alipay.b.a.a.a.a.a(a, c)) {
            com.alipay.apmobilesecuritysdk.f.a.a(this.b);
            d.a(this.b);
            g.a(this.b);
            i.h();
        }
        if (!com.alipay.b.a.a.a.a.a(a, c)) {
            h.a(this.b, c);
        }
        a = com.alipay.b.a.a.a.a.a(map, MsgConstant.KEY_UTDID, com.umeng.a.d);
        c = com.alipay.b.a.a.a.a.a(map, b.c, com.umeng.a.d);
        String a2 = com.alipay.b.a.a.a.a.a(map, "userId", com.umeng.a.d);
        if (com.alipay.b.a.a.a.a.a(a)) {
            obj = com.umeng.a.d;
        }
        Map hashMap = new HashMap();
        hashMap.put(MsgConstant.KEY_UTDID, obj);
        hashMap.put(b.c, c);
        hashMap.put("userId", a2);
        hashMap.put(SocialConstants.PARAM_APPNAME, com.umeng.a.d);
        hashMap.put("appKeyClient", com.umeng.a.d);
        hashMap.put("appchannel", com.umeng.a.d);
        hashMap.put("rpcVersion", MessageService.MSG_DB_NOTIFY_DISMISS);
        com.alipay.apmobilesecuritysdk.g.b.a().a(new AnonymousClass_1(hashMap, initResultListener));
    }
}
