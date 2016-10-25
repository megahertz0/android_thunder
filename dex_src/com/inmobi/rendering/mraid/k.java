package com.inmobi.rendering.mraid;

import android.support.v4.internal.view.SupportMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import com.inmobi.rendering.CustomView;
import com.inmobi.rendering.CustomView.SwitchIconType;
import com.inmobi.rendering.RenderView;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: MraidResizeProcessor.java
public final class k {
    private static final String a;
    private RenderView b;
    private ViewGroup c;
    private int d;

    static {
        a = k.class.getSimpleName();
    }

    public k(RenderView renderView) {
        this.b = renderView;
    }

    public final void a() {
        if (this.c == null) {
            this.c = (ViewGroup) this.b.getParent();
            this.d = this.c.indexOfChild(this.b);
        }
        m resizeProperties = this.b.getResizeProperties();
        c();
        a(resizeProperties);
    }

    private void c() {
        View frameLayout = new FrameLayout(this.b.getRenderViewContext());
        LayoutParams layoutParams = new LayoutParams(this.b.getWidth(), this.b.getHeight());
        frameLayout.setId(SupportMenu.USER_MASK);
        this.c.addView(frameLayout, this.d, layoutParams);
        this.c.removeView(this.b);
    }

    private void a(m mVar) {
        float c = DisplayInfo.a().c();
        int i = (int) ((((float) mVar.b) * c) + 0.5f);
        int i2 = (int) ((c * ((float) mVar.c)) + 0.5f);
        FrameLayout frameLayout = (FrameLayout) this.c.getRootView().findViewById(16908290);
        View frameLayout2 = new FrameLayout(this.b.getRenderViewContext());
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        ViewGroup relativeLayout = new RelativeLayout(this.b.getRenderViewContext());
        LayoutParams layoutParams2 = new FrameLayout.LayoutParams(i, i2);
        LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(i, i2);
        frameLayout2.setId(65534);
        if (this.b.getParent() != null) {
            ((ViewGroup) this.b.getParent()).removeAllViews();
        }
        relativeLayout.addView(this.b, layoutParams3);
        a(relativeLayout, mVar.a);
        frameLayout2.addView(relativeLayout, layoutParams2);
        frameLayout.addView(frameLayout2, layoutParams);
        a(frameLayout, frameLayout2, mVar);
        frameLayout2.setBackgroundColor(0);
    }

    private void a(ViewGroup viewGroup, String str) {
        float c = DisplayInfo.a().c();
        View customView = new CustomView(this.b.getRenderViewContext(), c, SwitchIconType.CLOSE_TRANSPARENT);
        customView.setId(65531);
        customView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                k.this.b.k();
            }
        });
        viewGroup.addView(customView, a(str, c));
    }

    private RelativeLayout.LayoutParams a(String str, float f) {
        String a = a(str);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (50.0f * f), (int) (50.0f * f));
        if (a.equals("top-right") || a.equals("bottom-right")) {
            layoutParams.addRule(XZBDevice.Success);
        }
        if (a.equals("bottom-right") || a.equals("bottom-left") || a.equals("bottom-center")) {
            layoutParams.addRule(XZBDevice.Fail);
            layoutParams.addRule(XZBDevice.DOWNLOAD_LIST_ALL);
        }
        if (a.equals("bottom-center") || a.equals("top-center") || a.equals("center")) {
            layoutParams.addRule(XZBDevice.Upload);
        }
        if (a.equals("top-center")) {
            layoutParams.addRule(XZBDevice.Stop);
        }
        return layoutParams;
    }

    private String a(String str) {
        if (str == null || str.length() == 0) {
            return "top-right";
        }
        return (str.equals("top-left") || str.equals("top-right") || str.equals("bottom-left") || str.equals("bottom-right") || str.equals("top-center") || str.equals("bottom-center") || str.equals("center")) ? str : "top-right";
    }

    private void a(FrameLayout frameLayout, FrameLayout frameLayout2, m mVar) {
        float c = DisplayInfo.a().c();
        int i = (int) ((((float) mVar.b) * c) + 0.5f);
        int i2 = (int) ((((float) mVar.c) * c) + 0.5f);
        int i3 = (int) ((((float) mVar.d) * c) + 0.5f);
        int i4 = (int) ((c * ((float) mVar.e)) + 0.5f);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        this.c.getLocationOnScreen(iArr);
        frameLayout.getLocationOnScreen(iArr2);
        iArr[1] = iArr[1] - iArr2[1];
        iArr[0] = iArr[0] - iArr2[0];
        iArr[0] = i3 + iArr[0];
        iArr[1] = i4 + iArr[1];
        if (!mVar.f) {
            if (i > frameLayout.getWidth() - iArr[0]) {
                iArr[0] = frameLayout.getWidth() - i;
            }
            if (i2 > frameLayout.getHeight() - iArr[1]) {
                iArr[1] = frameLayout.getHeight() - i2;
            }
            if (iArr[0] < 0) {
                iArr[0] = 0;
            }
            if (iArr[1] < 0) {
                iArr[1] = 0;
            }
        }
        LayoutParams layoutParams = new FrameLayout.LayoutParams(i, i2);
        layoutParams.leftMargin = iArr[0];
        layoutParams.topMargin = iArr[1];
        layoutParams.gravity = 8388611;
        frameLayout2.setLayoutParams(layoutParams);
    }

    public final void b() {
        ViewGroup viewGroup = (ViewGroup) this.b.getParent();
        View findViewById = viewGroup.getRootView().findViewById(65534);
        View findViewById2 = this.c.getRootView().findViewById(SupportMenu.USER_MASK);
        ((ViewGroup) findViewById.getParent()).removeView(findViewById);
        ((ViewGroup) findViewById2.getParent()).removeView(findViewById2);
        viewGroup.removeView(this.b);
        this.c.addView(this.b, this.d, new RelativeLayout.LayoutParams(this.c.getWidth(), this.c.getHeight()));
        this.b.m();
    }
}
