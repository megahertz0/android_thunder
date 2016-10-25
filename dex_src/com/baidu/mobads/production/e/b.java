package com.baidu.mobads.production.e;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdContainer;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.utils.IXAdCommonUtils;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.j.m;
import com.baidu.mobads.openad.e.d;
import com.baidu.mobads.production.t;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashMap;

public class b extends com.baidu.mobads.production.a {
    private boolean A;
    private double B;
    private int C;
    private int D;
    private int E;
    private ViewGroup F;
    private RelativeLayout G;
    protected final IXAdLogger w;
    IXAdCommonUtils x;
    private a y;
    private RelativeLayout z;

    static class a {
        Activity a;
        View b;
        int c;
        int d;

        a() {
        }
    }

    public /* synthetic */ IXAdRequestInfo getAdRequestInfo() {
        return m();
    }

    public b(Activity activity, String str, boolean z, double d) {
        super(activity, str, SlotType.SLOT_TYPE_FRONTLINK);
        this.A = true;
        this.B = 0.5d;
        this.w = m.a().f();
        this.C = 0;
        this.D = 0;
        this.E = 0;
        setActivity(activity);
        this.x = m.a().m();
        this.C = this.x.getPixel(activity, R.styleable.AppCompatTheme_panelMenuListTheme);
        this.D = this.x.getPixel(activity, R.styleable.AppCompatTheme_panelMenuListTheme);
        this.E = this.x.getStatusBarHeight(activity);
        this.B = d;
        this.A = z;
        this.y = new a(getApplicationContext());
        this.y.d(str);
        m().a(this.D);
        m().b(this.C);
        a(activity);
        request();
    }

    protected void d() {
        this.n = 4200;
    }

    public void request() {
        a(this.y);
    }

    protected void a(d dVar, t tVar, int i) {
        tVar.a(dVar, (double) i);
    }

    protected void c(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        start();
    }

    protected void d(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        View adView = iXAdContainer.getAdView();
        a aVar = new a();
        aVar.b = adView;
        aVar.a = getActivity();
        aVar.d = 80;
        aVar.c = 80;
        a(aVar);
    }

    protected void e(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        super.e(iXAdContainer, hashMap);
        if (this.F != null && this.G != null) {
            this.F.removeView(this.G);
            this.F = null;
            this.G = null;
            l();
        }
    }

    public void l() {
        if (this.F != null && this.G != null) {
            this.F.removeView(this.G);
            this.F = null;
            this.G = null;
            super.l();
        }
    }

    private void a(Activity activity) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.D, this.C);
        if (this.A) {
            layoutParams.addRule(XZBDevice.Pause);
        } else {
            layoutParams.addRule(XZBDevice.Success);
        }
        int height = this.x.getScreenRect(activity).height();
        int i = (int) (((double) height) * this.B);
        height = (height - this.C) - this.E;
        if (i <= height) {
            height = i;
        }
        layoutParams.topMargin = height;
        this.G = new RelativeLayout(activity);
        this.z = new RelativeLayout(activity);
        this.z.setBackgroundColor(0);
        setAdSlotBase(this.z);
        this.G.addView(this.z, layoutParams);
        this.G.setBackgroundColor(0);
        this.F = (ViewGroup) activity.getWindow().getDecorView();
        this.F.addView(this.G, new LayoutParams(-1, -1));
    }

    void a(a aVar) {
        Context context = aVar.a;
        View view = this.z;
        IXAdCommonUtils m = m.a().m();
        Rect screenRect = m.getScreenRect(context);
        this.h.getAdView().setOnTouchListener(new c(this, screenRect.width(), screenRect.height(), view, m.getPixel(context, aVar.c), m.getPixel(context, aVar.d)));
    }

    public com.baidu.mobads.vo.d m() {
        return this.y;
    }

    public void c() {
        if (this.h != null) {
            this.h.load();
        } else {
            this.w.e("container is null");
        }
    }
}
