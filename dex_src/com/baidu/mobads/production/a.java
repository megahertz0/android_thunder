package com.baidu.mobads.production;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.baidu.mobads.an;
import com.baidu.mobads.h.g;
import com.baidu.mobads.h.q;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotState;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdContainer;
import com.baidu.mobads.interfaces.IXAdContainerContext;
import com.baidu.mobads.interfaces.IXAdContainerFactory;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.IXAdProdInfo;
import com.baidu.mobads.interfaces.IXAdResponseInfo;
import com.baidu.mobads.interfaces.IXNonLinearAdSlot;
import com.baidu.mobads.interfaces.error.XAdErrorCode;
import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.interfaces.utils.IXAdURIUitls;
import com.baidu.mobads.j.m;
import com.baidu.mobads.openad.b.b;
import com.baidu.mobads.openad.d.c;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;
import com.baidu.mobads.vo.d;
import com.tencent.open.SocialConstants;
import com.umeng.message.MsgConstant;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class a extends c implements IXNonLinearAdSlot {
    public static IXAdContainerFactory a;
    private static final String[] w;
    public Boolean b;
    public IXAdInstanceInfo d;
    public RelativeLayout e;
    public Context f;
    protected int g;
    public IXAdContainer h;
    protected String i;
    protected t j;
    protected com.baidu.mobads.openad.f.a k;
    public d l;
    public SlotState m;
    public int n;
    protected int o;
    public SlotType p;
    protected HashMap<String, String> q;
    public AtomicBoolean r;
    public final IXAdLogger s;
    protected long t;
    protected long u;
    protected long v;
    private IXAdResponseInfo x;
    private String y;
    private IOAdEventListener z;

    public abstract void a(com.baidu.mobads.openad.e.d dVar, t tVar, int i);

    public abstract void c();

    public abstract void c(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap);

    public abstract void d();

    public abstract void d(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap);

    static {
        w = new String[]{MsgConstant.PERMISSION_READ_PHONE_STATE, "android.permission.ACCESS_COARSE_LOCATION"};
    }

    public a(Context context) {
        this.b = Boolean.valueOf(false);
        this.d = null;
        this.g = 0;
        this.m = SlotState.IDEL;
        this.n = 5000;
        this.o = 0;
        this.q = new HashMap();
        this.r = new AtomicBoolean();
        this.z = new b(this);
        this.s = m.a().f();
        new Thread(new e(this, context)).start();
    }

    private void c(Context context) {
        new Handler(Looper.getMainLooper()).postDelayed(new f(this, context), 2000);
    }

    public a(Context context, String str, SlotType slotType) {
        this(context);
        this.f = context;
        c(str);
        setId(str);
        this.p = slotType;
    }

    public SlotState getSlotState() {
        return this.m;
    }

    public void setActivity(Context context) {
        this.f = context;
        this.t = System.currentTimeMillis();
        a();
        this.r.set(false);
        d();
        b.a(getApplicationContext());
        com.baidu.mobads.c.a.a().a(getApplicationContext());
        m.a().a(getApplicationContext());
        this.k = new com.baidu.mobads.openad.f.a(this.n);
        this.k.setEventHandler(new i(this));
        b(this.f);
        q.a(this.f).a();
    }

    public void setAdSlotBase(RelativeLayout relativeLayout) {
        this.e = relativeLayout;
    }

    public void setId(String str) {
        this.y = str;
    }

    public void a() {
    }

    public String getId() {
        return this.y;
    }

    public SlotType getType() {
        return this.p;
    }

    private void a(XAdErrorCode xAdErrorCode, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(SocialConstants.PARAM_SEND_MSG, xAdErrorCode);
        dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_ERROR, hashMap));
        m.a().q().printErrorMessage(xAdErrorCode, str);
    }

    protected void a(Context context) {
        if (BaiduXAdSDKContext.mApkLoader == null) {
            synchronized (g.class) {
                if (BaiduXAdSDKContext.mApkLoader == null) {
                    BaiduXAdSDKContext.mApkLoader = new g(context.getApplicationContext());
                }
            }
        }
        BaiduXAdSDKContext.mApkLoader.a(new j(this));
    }

    public void a(String str) {
        this.s.i("XAbstractAdProdTemplate", new StringBuilder("doubleCheck:").append(str).append(", bfp=").append(this.b).append(", apk=").append(BaiduXAdSDKContext.isRemoteLoadSuccess).toString());
        if (BaiduXAdSDKContext.isRemoteLoadSuccess.booleanValue()) {
            m.a().a(getAdContainerFactory());
        }
        if (BaiduXAdSDKContext.isRemoteLoadSuccess.booleanValue() && this.b.booleanValue()) {
            IXAdResponseInfo adResponseInfo = getAdResponseInfo();
            if (adResponseInfo != null) {
                b(adResponseInfo);
            }
            a(getAdResponseInfo());
        }
    }

    private void b(IXAdResponseInfo iXAdResponseInfo) {
        this.s.i("XAbstractAdProdTemplate", "try2CachingVideoAdCreativeAsset");
        IXAdURIUitls i = m.a().i();
        IXAdInstanceInfo primaryAdInstanceInfo = iXAdResponseInfo.getPrimaryAdInstanceInfo();
        String videoUrl = primaryAdInstanceInfo.getVideoUrl();
        Boolean isHttpProtocol = i.isHttpProtocol(videoUrl);
        this.s.i("XAbstractAdProdTemplate", new StringBuilder("try2CachingVideoAdCreativeAsset, should cache=").append(isHttpProtocol).toString());
        if (isHttpProtocol.booleanValue()) {
            primaryAdInstanceInfo.setLocalCreativeURL(null);
            String str = getApplicationContext().getFilesDir().getPath() + File.separator + "__bidu_cache_dir" + File.separator;
            String adId = primaryAdInstanceInfo.getAdId();
            com.baidu.mobads.j.g b = m.a().b();
            b.a(str);
            b.a(videoUrl, str, adId, new k(this, Looper.getMainLooper(), primaryAdInstanceInfo));
        }
    }

    public boolean a(d dVar) {
        this.s.i("XAbstractAdProdTemplate", "doRequest()");
        new Thread(new l(this, dVar)).start();
        return true;
    }

    public void b(d dVar) {
        this.l = dVar;
        g();
        this.b = Boolean.valueOf(false);
        String b = this.i == null ? dVar.b() : this.i;
        this.j = new t();
        try {
            this.j.a(new b());
        } catch (ClassNotFoundException e) {
            this.j.a(new com.baidu.mobads.openad.b.d());
        }
        com.baidu.mobads.c.a.b = b;
        com.baidu.mobads.openad.e.d dVar2 = new com.baidu.mobads.openad.e.d(b, com.umeng.a.d);
        dVar2.e = 1;
        this.j.addEventListener("URLLoader.Load.Complete", this.z);
        this.j.addEventListener("URLLoader.Load.Error", this.z);
        a(dVar2, this.j, this.n);
    }

    protected void a(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        h();
        c(iXAdContainer, hashMap);
        dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_LOADED));
    }

    protected void b(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        this.m = SlotState.PLAYING;
        d(iXAdContainer, hashMap);
        dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_STARTED));
    }

    public void e(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
    }

    protected void b(String str) {
        com.baidu.mobads.c.a.a().a(str);
        dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_ERROR));
    }

    protected void a(IXAdResponseInfo iXAdResponseInfo) {
        this.s.i("XAbstractAdProdTemplate", "handleAllReady");
        this.g++;
        this.d = iXAdResponseInfo.getPrimaryAdInstanceInfo();
        Context applicationContext = getApplicationContext();
        IXAdContainerContext anVar = new an(applicationContext, getActivity(), this.l.d(), this.e, new o(applicationContext, this), iXAdResponseInfo, null);
        if (Looper.myLooper() == Looper.getMainLooper()) {
            a(anVar);
        } else {
            new Handler(Looper.getMainLooper()).post(new m(this, anVar));
        }
    }

    protected void a(IXAdResponseInfo iXAdResponseInfo, IXAdInstanceInfo iXAdInstanceInfo) {
        this.d = iXAdInstanceInfo;
    }

    public Context getApplicationContext() {
        Activity activity = getActivity();
        return activity == null ? this.f : activity.getApplicationContext();
    }

    public Activity getActivity() {
        if (this.f instanceof Activity) {
            return (Activity) this.f;
        }
        return (this.e == null || !(this.e.getContext() instanceof Activity)) ? null : (Activity) this.e.getContext();
    }

    public IXAdContainer getCurrentXAdContainer() {
        return this.h;
    }

    public IXAdContainerFactory getAdContainerFactory() {
        return a;
    }

    public static IXAdContainerFactory b() {
        return a;
    }

    protected void a(IXAdContainerContext iXAdContainerContext) {
        try {
            this.s.i("XAbstractAdProdTemplate", "processAllReadyOnUIThread()");
            this.u = System.currentTimeMillis();
            this.h = b(iXAdContainerContext);
            this.v = System.currentTimeMillis();
            if (this.h == null) {
                this.s.e("XAbstractAdProdTemplate", "processAllReadyOnUIThread(), mAdContainer is null");
                dispatchEvent(new com.baidu.mobads.f.a(IXAdEvent.AD_STOPPED));
                return;
            }
            this.s.i("XAbstractAdProdTemplate", "processAllReadyOnUIThread(), mAdContainer be created");
            this.q.put("start", this.t);
            this.q.put("container_before_created", this.u);
            this.q.put("container_after_created", this.v);
            this.h.setParameters(this.q);
            com.baidu.mobads.a.a.c = this.h.getRemoteVersion();
            this.s.i("XAbstractAdProdTemplate", new StringBuilder("processAllReadyOnUIThread(), mAdContainer be created, hasCalledLoadAtAppSide=").append(this.r.get()).toString());
            if (this.r.get()) {
                this.h.load();
            }
            c();
            c(this.f);
        } catch (Exception e) {
            this.s.e(m.a().q().genCompleteErrorMessage(XAdErrorCode.PERMISSION_PROBLEM, e.getMessage()));
            com.baidu.mobads.c.a.a().a(new StringBuilder("process all ready on UI Thread exception: ").append(e.toString()).toString());
        }
    }

    private IXAdContainer b(IXAdContainerContext iXAdContainerContext) {
        IXAdContainer iXAdContainer = null;
        this.s.i("XAbstractAdProdTemplate", "createAdContainer");
        if (a != null) {
            iXAdContainer = a.createXAdContainer(iXAdContainerContext, null);
            if (iXAdContainer != null) {
                this.s.i("XAbstractAdProdTemplate", new StringBuilder("createAdContainer() apk.version=").append(a.getRemoteVersion()).toString());
            }
        }
        return iXAdContainer;
    }

    public IXAdProdInfo getProdInfo() {
        return this.l.d();
    }

    public void setParameter(HashMap<String, String> hashMap) {
        this.q = hashMap;
    }

    public HashMap<String, String> getParameter() {
        return this.q;
    }

    public int getDuration() {
        return -1;
    }

    public int getPlayheadTime() {
        return -1;
    }

    public IXAdInstanceInfo getCurrentAdInstance() {
        return this.d;
    }

    public ViewGroup getProdBase() {
        return this.e;
    }

    public void load() {
        if (this.h != null) {
            this.h.load();
        } else {
            this.r.set(true);
        }
    }

    public void resize() {
        if (this.h != null && getApplicationContext() != null) {
            new Handler(getApplicationContext().getMainLooper()).post(new n(this));
        }
    }

    public void pause() {
        e();
    }

    protected void e() {
        if (this.h != null && getApplicationContext() != null) {
            this.m = SlotState.PAUSED;
            new Handler(getApplicationContext().getMainLooper()).post(new c(this));
        }
    }

    public void start() {
        if (this.h != null) {
            this.h.start();
        }
    }

    public void resume() {
        f();
    }

    protected void f() {
        if (this.h != null && getApplicationContext() != null) {
            this.m = SlotState.PLAYING;
            new Handler(getApplicationContext().getMainLooper()).post(new d(this));
        }
    }

    public void stop() {
        m.a().f().i("XAbstractAdProdTemplate", "stop");
        if (this.h != null) {
            this.h.stop();
            this.h = null;
        }
    }

    public void g() {
        if (this.j != null) {
            this.j.removeAllListeners();
            this.j.a();
        }
    }

    protected void h() {
        if (this.k != null) {
            this.k.stop();
        }
        this.k = null;
    }

    public void i() {
        if (this.k != null) {
            this.k.start();
        }
    }

    public void j() {
        if (this.h != null) {
            this.h.onAttachedToWindow();
        }
    }

    @SuppressLint({"MissingSuperCall"})
    public void k() {
        if (this.h != null) {
            this.h.onDetachedFromWindow();
        }
    }

    public void a(int i) {
        if (this.h != null) {
            this.h.onWindowVisibilityChanged(i);
        }
    }

    public void a(boolean z) {
        if (this.h != null) {
            this.h.onWindowFocusChanged(z);
        }
    }

    public boolean a(int i, KeyEvent keyEvent) {
        return this.h != null ? this.h.processKeyEvent(i, keyEvent).booleanValue() : false;
    }

    public void c(String str) {
        if (TextUtils.isEmpty(str)) {
            this.s.e("\u4ee3\u7801\u4f4did(adPlaceId)\u4e0d\u53ef\u4ee5\u4e3a\u7a7a");
        }
    }

    public void b(Context context) {
        try {
            com.baidu.mobads.j.d m = m.a().m();
            a(context, MsgConstant.PERMISSION_INTERNET);
            a(context, MsgConstant.PERMISSION_ACCESS_NETWORK_STATE);
            if (m.isOldPermissionModel()) {
                a(context, MsgConstant.PERMISSION_READ_PHONE_STATE);
                a(context, MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE);
                return;
            }
            int i;
            ArrayList arrayList = new ArrayList();
            for (i = 0; i < w.length; i++) {
                if (!m.checkSelfPermission(context, w[i])) {
                    arrayList.add(w[i]);
                }
            }
            i = arrayList.size();
            if (i > 0) {
                m.a(context, (String[]) arrayList.toArray(new String[i]), 1234323329);
            }
        } catch (Exception e) {
            this.s.e(m.a().q().genCompleteErrorMessage(XAdErrorCode.PERMISSION_PROBLEM, e.getMessage()));
            com.baidu.mobads.c.a.a().a(new StringBuilder("check permission exception: ").append(e.toString()).toString());
        }
    }

    public void a(Context context, String str) {
        if (!m.a().m().hasPermission(context, str)) {
            String toString = new StringBuilder("Cannot request an ad without necessary permissions!  Open manifest.xml and just before the final </manifest> tag add:  <uses-permission android:name=\"").append(str).append("\" />").toString();
            this.s.e("BaiduMobAds SDK", toString);
            throw new SecurityException(toString);
        }
    }

    public void l() {
        if (this.h != null) {
            this.h.destroy();
        }
        BaiduXAdSDKContext.exit();
    }

    public IXAdResponseInfo getAdResponseInfo() {
        return this.x;
    }

    public void setAdResponseInfo(IXAdResponseInfo iXAdResponseInfo) {
        this.x = iXAdResponseInfo;
    }

    public Boolean isAdServerRequestingSuccess() {
        return this.b;
    }
}
