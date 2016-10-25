package com.xunlei.common.pay.a;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.webkit.WebView;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.httpclient.AsyncHttpClient;
import com.xunlei.common.httpclient.BaseHttpClient;
import com.xunlei.common.pay.XLContractResp;
import com.xunlei.common.pay.XLContractor;
import com.xunlei.common.pay.XLContractor$XLContractType;
import com.xunlei.common.pay.XLOnPayListener;
import com.xunlei.common.pay.XLPayType;
import com.xunlei.common.pay.c.a;
import com.xunlei.common.pay.c.b;
import com.xunlei.common.pay.c.b.d;
import com.xunlei.common.pay.js.c;
import com.xunlei.common.pay.js.export.IXLPayJSHandler;
import com.xunlei.common.pay.param.XLAlipayParam;
import com.xunlei.common.pay.param.XLPayParam;
import com.xunlei.common.pay.param.XLPriceParam;
import com.xunlei.common.pay.param.XLWxPayParam;
import com.xunlei.common.stat.XLStatUtil;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.android.spdy.SpdyProtocol;

// compiled from: XLPayUtilProxy.java
public final class f {
    private static final String a;
    private static final int b = 4132;
    private static final int c = 4133;
    private static f d;
    private Context e;
    private int f;
    private String g;
    private String h;
    private String i;
    private boolean j;
    private Handler k;
    private XLStatUtil l;
    private g m;
    private BaseHttpClient n;
    private List<XLOnPayListener> o;
    @SuppressLint({"UseSparseArrays"})
    private Map<Integer, e> p;
    private Map<WebView, c> q;

    static {
        a = f.class.getSimpleName();
        d = null;
    }

    private f() {
        this.e = null;
        this.f = 0;
        this.g = "1.6.6.177628";
        this.h = "ABCDEF0123456789";
        this.i = "1.6.6.177628";
        this.j = false;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = new LinkedList();
        this.p = new HashMap();
        this.q = new HashMap();
    }

    public static f a() {
        if (d == null) {
            d = new f();
        }
        return d;
    }

    public final void a(Context context, int i, String str, String str2) {
        if (!this.j && context != null) {
            this.j = true;
            this.e = context;
            this.f = i;
            this.g = str;
            this.h = str2;
            this.n = new AsyncHttpClient(this.i);
            this.l = XLStatUtil.getInstance();
            this.l.init(this.e, this.f, this.g, this.i, this.h);
            this.m = new g(this.l);
            a(this.m);
            this.k = new Handler() {
                public final void handleMessage(Message message) {
                    f.a(f.this, message);
                }
            };
            XLLog.v(a, "xl pay util init");
        }
    }

    public final void b() {
        if (this.j) {
            this.j = false;
            this.l.uninit();
            XLLog.v(a, "xl pay util uninit");
        }
    }

    public final synchronized void a(XLOnPayListener xLOnPayListener) {
        this.o.add(xLOnPayListener);
    }

    public final synchronized void b(XLOnPayListener xLOnPayListener) {
        this.o.remove(xLOnPayListener);
    }

    public final Handler c() {
        return this.k;
    }

    public final Context d() {
        return this.e;
    }

    public final BaseHttpClient e() {
        return this.n;
    }

    public final String f() {
        return this.i;
    }

    public final int g() {
        return this.f;
    }

    public final XLStatUtil h() {
        return this.l;
    }

    public final void a(Object... objArr) {
        this.k.obtainMessage(c, objArr).sendToTarget();
    }

    private synchronized e b(int i) {
        return (e) this.p.get(Integer.valueOf(i));
    }

    public final void a(int i, int i2) {
        com.xunlei.common.pay.c.c cVar = (com.xunlei.common.pay.c.c) b(i);
        if (cVar != null) {
            cVar.a(i2);
        }
    }

    public final void a(e eVar) {
        b(eVar);
    }

    public final int a(XLWxPayParam xLWxPayParam, Object obj) {
        return a(xLWxPayParam, obj, null);
    }

    public final int a(XLWxPayParam xLWxPayParam, Object obj, XLOnPayListener xLOnPayListener) {
        if (!this.j) {
            return 0;
        }
        e cVar = new com.xunlei.common.pay.c.c();
        cVar.a();
        cVar.a((XLPayParam) xLWxPayParam);
        cVar.a(obj);
        cVar.a(xLOnPayListener);
        b(cVar);
        this.l.registerStatReq(cVar.b());
        return cVar.b();
    }

    public final int a(XLAlipayParam xLAlipayParam, Object obj) {
        return a(xLAlipayParam, obj, null);
    }

    public final int a(XLAlipayParam xLAlipayParam, Object obj, XLOnPayListener xLOnPayListener) {
        if (!this.j) {
            return 0;
        }
        e aVar = new a(268435458);
        aVar.a();
        aVar.a((XLPayParam) xLAlipayParam);
        aVar.a(obj);
        aVar.a(xLOnPayListener);
        b(aVar);
        this.l.registerStatReq(aVar.b());
        return aVar.b();
    }

    public final int b(XLAlipayParam xLAlipayParam, Object obj) {
        return b(xLAlipayParam, obj, null);
    }

    public final int b(XLAlipayParam xLAlipayParam, Object obj, XLOnPayListener xLOnPayListener) {
        if (!this.j) {
            return 0;
        }
        e aVar = new a(268435459);
        aVar.a();
        aVar.a((XLPayParam) xLAlipayParam);
        aVar.a(obj);
        aVar.a(xLOnPayListener);
        b(aVar);
        this.l.registerStatReq(aVar.b());
        return aVar.b();
    }

    public final int a(XLPriceParam xLPriceParam, Object obj) {
        if (!this.j) {
            return 0;
        }
        e bVar = new b();
        bVar.a();
        bVar.a(xLPriceParam);
        bVar.a(obj);
        b(bVar);
        this.l.registerStatReq(bVar.b());
        return bVar.b();
    }

    public final boolean a(WebView webView, Activity activity, IXLPayJSHandler iXLPayJSHandler) {
        c cVar = new c();
        boolean a = cVar.a(webView, activity, iXLPayJSHandler);
        if (a) {
            this.q.put(webView, cVar);
        }
        return a;
    }

    public final boolean a(WebView webView) {
        c cVar = (c) this.q.get(webView);
        if (cVar != null) {
            cVar.a();
            this.q.remove(webView);
        }
        return true;
    }

    private void a(Message message) {
        switch (message.what) {
            case b:
                e eVar = (e) message.obj;
                if (eVar != null) {
                    eVar.c();
                }
            case c:
                Object[] objArr = (Object[]) message.obj;
                int i;
                int i2;
                switch (((Integer) objArr[0]).intValue()) {
                    case XLPayType.XL_WX_PAY:
                        for (i = 0; i < this.o.size(); i++) {
                            ((XLOnPayListener) this.o.get(i)).onWxPay(((Integer) objArr[1]).intValue(), (String) objArr[2], objArr[3], ((Integer) objArr[4]).intValue());
                        }
                        break;
                    case XLPayType.XL_ALI_PAY:
                        for (i = 0; i < this.o.size(); i++) {
                            ((XLOnPayListener) this.o.get(i)).onAliPay(((Integer) objArr[1]).intValue(), (String) objArr[2], objArr[3], ((Integer) objArr[4]).intValue());
                        }
                        break;
                    case XLPayType.XL_NB_PAY:
                        for (i = 0; i < this.o.size(); i++) {
                            ((XLOnPayListener) this.o.get(i)).onNbPay(((Integer) objArr[1]).intValue(), (String) objArr[2], objArr[3], ((Integer) objArr[4]).intValue());
                        }
                        break;
                    case XLPayType.XL_ALIPAY_CONTRACT:
                        for (i2 = 0; i2 < this.o.size(); i2++) {
                            ((XLOnPayListener) this.o.get(i2)).onContractOperate(((Integer) objArr[1]).intValue(), (String) objArr[2], objArr[3], ((Integer) objArr[4]).intValue(), (XLContractResp) objArr[5]);
                        }
                        break;
                    case XLPayType.XL_WX_CONTRACT:
                        for (i2 = 0; i2 < this.o.size(); i2++) {
                            ((XLOnPayListener) this.o.get(i2)).onContractOperate(((Integer) objArr[1]).intValue(), (String) objArr[2], objArr[3], ((Integer) objArr[4]).intValue(), (XLContractResp) objArr[5]);
                        }
                        break;
                    case XLPayType.XL_GET_PRICE:
                        for (i2 = 0; i2 < this.o.size(); i2++) {
                            ((XLOnPayListener) this.o.get(i2)).onGetPrice(((Integer) objArr[1]).intValue(), (String) objArr[2], objArr[3], ((Integer) objArr[4]).intValue(), (String) objArr[5]);
                        }
                        break;
                }
                c(((Integer) objArr[4]).intValue());
            default:
                break;
        }
    }

    private void b(e eVar) {
        c(eVar);
        this.k.sendMessage(this.k.obtainMessage(b, eVar));
    }

    private void b(Message message) {
        Object[] objArr = (Object[]) message.obj;
        int i;
        int i2;
        switch (((Integer) objArr[0]).intValue()) {
            case XLPayType.XL_WX_PAY:
                for (i = 0; i < this.o.size(); i++) {
                    ((XLOnPayListener) this.o.get(i)).onWxPay(((Integer) objArr[1]).intValue(), (String) objArr[2], objArr[3], ((Integer) objArr[4]).intValue());
                }
                break;
            case XLPayType.XL_ALI_PAY:
                for (i = 0; i < this.o.size(); i++) {
                    ((XLOnPayListener) this.o.get(i)).onAliPay(((Integer) objArr[1]).intValue(), (String) objArr[2], objArr[3], ((Integer) objArr[4]).intValue());
                }
                break;
            case XLPayType.XL_NB_PAY:
                for (i = 0; i < this.o.size(); i++) {
                    ((XLOnPayListener) this.o.get(i)).onNbPay(((Integer) objArr[1]).intValue(), (String) objArr[2], objArr[3], ((Integer) objArr[4]).intValue());
                }
                break;
            case XLPayType.XL_ALIPAY_CONTRACT:
                for (i2 = 0; i2 < this.o.size(); i2++) {
                    ((XLOnPayListener) this.o.get(i2)).onContractOperate(((Integer) objArr[1]).intValue(), (String) objArr[2], objArr[3], ((Integer) objArr[4]).intValue(), (XLContractResp) objArr[5]);
                }
                break;
            case XLPayType.XL_WX_CONTRACT:
                for (i2 = 0; i2 < this.o.size(); i2++) {
                    ((XLOnPayListener) this.o.get(i2)).onContractOperate(((Integer) objArr[1]).intValue(), (String) objArr[2], objArr[3], ((Integer) objArr[4]).intValue(), (XLContractResp) objArr[5]);
                }
                break;
            case XLPayType.XL_GET_PRICE:
                for (i2 = 0; i2 < this.o.size(); i2++) {
                    ((XLOnPayListener) this.o.get(i2)).onGetPrice(((Integer) objArr[1]).intValue(), (String) objArr[2], objArr[3], ((Integer) objArr[4]).intValue(), (String) objArr[5]);
                }
                break;
        }
        c(((Integer) objArr[4]).intValue());
    }

    private synchronized void c(e eVar) {
        this.p.put(Integer.valueOf(eVar.b()), eVar);
    }

    private synchronized void c(int i) {
        this.p.remove(Integer.valueOf(i));
    }

    public static XLContractor a(int i) {
        switch (i) {
            case SpdyProtocol.SLIGHTSSL_0_RTT_MODE:
                return new com.xunlei.common.pay.c.a.c(a());
            case XLContractor$XLContractType.XL_CONTRACT_WX:
                return new d(a());
            default:
                return null;
        }
    }

    static /* synthetic */ void a(f fVar, Message message) {
        switch (message.what) {
            case b:
                e eVar = (e) message.obj;
                if (eVar != null) {
                    eVar.c();
                }
            case c:
                Object[] objArr = (Object[]) message.obj;
                int i;
                int i2;
                switch (((Integer) objArr[0]).intValue()) {
                    case XLPayType.XL_WX_PAY:
                        for (i = 0; i < fVar.o.size(); i++) {
                            ((XLOnPayListener) fVar.o.get(i)).onWxPay(((Integer) objArr[1]).intValue(), (String) objArr[2], objArr[3], ((Integer) objArr[4]).intValue());
                        }
                        break;
                    case XLPayType.XL_ALI_PAY:
                        for (i = 0; i < fVar.o.size(); i++) {
                            ((XLOnPayListener) fVar.o.get(i)).onAliPay(((Integer) objArr[1]).intValue(), (String) objArr[2], objArr[3], ((Integer) objArr[4]).intValue());
                        }
                        break;
                    case XLPayType.XL_NB_PAY:
                        for (i = 0; i < fVar.o.size(); i++) {
                            ((XLOnPayListener) fVar.o.get(i)).onNbPay(((Integer) objArr[1]).intValue(), (String) objArr[2], objArr[3], ((Integer) objArr[4]).intValue());
                        }
                        break;
                    case XLPayType.XL_ALIPAY_CONTRACT:
                        for (i2 = 0; i2 < fVar.o.size(); i2++) {
                            ((XLOnPayListener) fVar.o.get(i2)).onContractOperate(((Integer) objArr[1]).intValue(), (String) objArr[2], objArr[3], ((Integer) objArr[4]).intValue(), (XLContractResp) objArr[5]);
                        }
                        break;
                    case XLPayType.XL_WX_CONTRACT:
                        for (i2 = 0; i2 < fVar.o.size(); i2++) {
                            ((XLOnPayListener) fVar.o.get(i2)).onContractOperate(((Integer) objArr[1]).intValue(), (String) objArr[2], objArr[3], ((Integer) objArr[4]).intValue(), (XLContractResp) objArr[5]);
                        }
                        break;
                    case XLPayType.XL_GET_PRICE:
                        for (i2 = 0; i2 < fVar.o.size(); i2++) {
                            ((XLOnPayListener) fVar.o.get(i2)).onGetPrice(((Integer) objArr[1]).intValue(), (String) objArr[2], objArr[3], ((Integer) objArr[4]).intValue(), (String) objArr[5]);
                        }
                        break;
                }
                fVar.c(((Integer) objArr[4]).intValue());
            default:
                break;
        }
    }
}
