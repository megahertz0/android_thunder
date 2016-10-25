package com.baidu.mobads.production.f;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.internal.view.SupportMenu;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.baidu.mobads.AdSize;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdContainer;
import com.baidu.mobads.interfaces.IXAdInstanceInfo.CreativeType;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.j.m;
import com.baidu.mobads.openad.e.d;
import com.baidu.mobads.production.a;
import com.baidu.mobads.production.t;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashMap;

public class b extends a implements a {
    private CountDownTimer A;
    private f B;
    private boolean C;
    private boolean D;
    private Activity E;
    private Boolean F;
    public final String w;
    protected final IXAdLogger x;
    private RelativeLayout y;
    private TextView z;

    public /* synthetic */ IXAdRequestInfo getAdRequestInfo() {
        return p();
    }

    public b(Context context, RelativeLayout relativeLayout, Boolean bool, AdSize adSize, String str) {
        super(context);
        this.w = "html5_intersitial";
        this.C = false;
        this.D = false;
        this.x = m.a().f();
        setId(str);
        setActivity(context);
        setAdSlotBase(relativeLayout);
        this.p = SlotType.SLOT_TYPE_INTERSTITIAL;
        this.F = bool;
        this.B = new f(getApplicationContext(), getActivity(), this.p, Boolean.valueOf(true));
        this.B.c(SlotType.SLOT_TYPE_INTERSTITIAL.getValue());
        this.B.c(adSize.getValue());
        this.B.d(str);
        c(str);
    }

    public void c() {
    }

    protected void d() {
        this.n = 8000;
    }

    public void request() {
        super.a(this.B);
    }

    protected void a(d dVar, t tVar, int i) {
        tVar.a(dVar, new StringBuilder("{'ad':[{'id':99999999,'url':'").append(this.B.b()).append("', type='").append(CreativeType.HTML.getValue()).append("'}],'n':1}").toString());
    }

    public void start() {
        super.start();
    }

    public void m() {
    }

    public void a(int i, int i2) {
        if (!this.C && !this.D) {
            this.B.a(i);
            this.B.b(i2);
            load();
        }
    }

    public void a(Activity activity) {
    }

    public void a(Activity activity, RelativeLayout relativeLayout) {
        try {
            this.x.d("showInterstitialAdInit");
            if (this.C && !this.D) {
                this.D = true;
                this.C = false;
                this.E = activity;
                start();
                q();
                this.e.setBackgroundColor(0);
                View relativeLayout2 = new RelativeLayout(activity);
                relativeLayout2.setBackgroundColor(0);
                relativeLayout.addView(relativeLayout2, new LayoutParams(-1, -1));
                this.e.addView(this.h.getAdView(), new LayoutParams(-1, -1));
                relativeLayout2.addView(this.e, new LayoutParams(-1, -1));
                this.h.getAdView().setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
            } else if (this.D) {
                this.x.w("interstitial ad is showing now");
            } else if (!this.C) {
                this.x.w("interstitial ad is not ready");
            }
        } catch (Throwable e) {
            this.x.d(e);
        }
    }

    public void n() {
        new Handler(Looper.getMainLooper()).post(new c(this));
    }

    private boolean s() {
        return o();
    }

    protected boolean o() {
        return AdSize.InterstitialForVideoBeforePlay.getValue() == this.B.getApt();
    }

    protected void c(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        this.C = true;
    }

    protected void d(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        n();
    }

    public com.baidu.mobads.vo.d p() {
        return this.B;
    }

    protected void q() {
        if (this.E != null) {
            this.E.runOnUiThread(new d(this));
        }
    }

    public boolean r() {
        return this.C;
    }

    protected void e(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        q();
        this.D = false;
    }

    private View t() {
        if (this.y == null) {
            this.y = new RelativeLayout(this.f);
            this.y.setBackgroundColor(Color.argb(R.styleable.AppCompatTheme_dialogTheme, 0, 0, 0));
            this.z = new TextView(this.f);
            this.z.setTextColor(SupportMenu.CATEGORY_MASK);
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.addRule(XZBDevice.Upload);
            this.y.addView(this.z, layoutParams);
        }
        this.A = new e(this, 6000, 1000).start();
        return this.y;
    }

    private void u() {
        if (!(this.y == null || this.y.getParent() == null)) {
            ((ViewGroup) this.y.getParent()).removeView(this.y);
        }
        if (this.A != null) {
            this.x.d("cancel countDownTimer before it finished");
            try {
                this.A.cancel();
            } catch (Throwable e) {
                this.x.d(e);
            }
        }
    }

    private LayoutParams v() {
        int screenDensity = (int) (20.0f * m.a().m().getScreenDensity(this.f));
        LayoutParams layoutParams = new LayoutParams(screenDensity, screenDensity);
        layoutParams.addRule(XZBDevice.Success);
        layoutParams.addRule(XZBDevice.Stop);
        return layoutParams;
    }

    public boolean a(int i, KeyEvent keyEvent) {
        return true;
    }
}
