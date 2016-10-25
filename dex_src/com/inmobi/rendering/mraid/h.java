package com.inmobi.rendering.mraid;

import android.content.Intent;
import android.support.v4.internal.view.SupportMenu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.URLUtil;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.rendering.InMobiAdActivity;
import com.inmobi.rendering.RenderView;
import com.inmobi.rendering.RenderingProperties;
import com.inmobi.rendering.RenderingProperties.PlacementType;
import com.xunlei.tdlive.R;

// compiled from: MraidExpandProcessor.java
public final class h {
    private static final String a;
    private RenderView b;
    private boolean c;
    private ViewGroup d;
    private int e;

    static {
        a = h.class.getSimpleName();
    }

    public h(RenderView renderView) {
        this.b = renderView;
        this.c = false;
    }

    public final void a(String str, String str2) {
        if (this.d == null) {
            this.d = (ViewGroup) this.b.getParent();
            this.e = this.d.indexOfChild(this.b);
        }
        if (this.b == null) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Please check if the MRAID processor was initialized correctly.");
            return;
        }
        int a;
        d expandProperties = this.b.getExpandProperties();
        this.c = URLUtil.isValidUrl(str2);
        if (this.c) {
            RenderView renderView = new RenderView(this.b.getRenderViewContext(), new RenderingProperties(PlacementType.INLINE));
            renderView.a(this.b.getListener(), this.b.getRenderingConfig(), this.b.getMraidConfig());
            renderView.setOriginalRenderView(this.b);
            renderView.loadUrl(str2);
            a = InMobiAdActivity.a(renderView);
            if (expandProperties != null) {
                renderView.setUseCustomClose(this.b.e());
            }
        } else {
            b();
            a = InMobiAdActivity.a(this.b);
        }
        Intent intent = new Intent(this.b.getRenderViewContext(), InMobiAdActivity.class);
        intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", R.styleable.AppCompatTheme_checkboxStyle);
        intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_RENDERVIEW_INDEX", a);
        a.a(this.b.getRenderViewContext(), intent);
    }

    private void b() {
        View frameLayout = new FrameLayout(this.b.getRenderViewContext());
        LayoutParams layoutParams = new LayoutParams(this.b.getWidth(), this.b.getHeight());
        frameLayout.setId(SupportMenu.USER_MASK);
        this.d.addView(frameLayout, this.e, layoutParams);
        this.d.removeView(this.b);
    }

    public final void a() {
        if (this.b.getOriginalRenderView() == null) {
            View findViewById = this.d.getRootView().findViewById(SupportMenu.USER_MASK);
            ((ViewGroup) this.b.getParent()).removeView(this.b);
            ((ViewGroup) findViewById.getParent()).removeView(findViewById);
            this.d.addView(this.b, this.e, new RelativeLayout.LayoutParams(this.d.getWidth(), this.d.getHeight()));
            this.b.m();
        }
    }
}
