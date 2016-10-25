package com.baidu.mobads.g;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.v4.widget.AutoScrollHelper;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.baidu.mobads.AppActivity.ActionBarColorTheme;
import com.baidu.mobads.interfaces.utils.IXAdCommonUtils;
import com.baidu.mobads.j.m;
import com.xunlei.downloadprovider.vod.VodPlayerActivity;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class a extends RelativeLayout {
    protected c a;
    Paint b;
    int c;
    int d;
    private Context e;
    private IXAdCommonUtils f;
    private ActionBarColorTheme g;
    private TextView h;

    public class a extends View {
        public a(Context context) {
            super(context);
        }
    }

    private class b extends com.baidu.mobads.g.a.a {
        private Paint c;
        private int d;

        public b(Context context, int i) {
            super(context);
            this.d = i;
        }

        private Paint a() {
            if (this.c == null) {
                this.c = new Paint();
                this.c.setStyle(Style.STROKE);
                this.c.setColor(this.d);
                this.c.setAlpha(VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX);
                this.c.setAntiAlias(true);
                this.c.setStrokeWidth((float) ((int) a.this.f.getScreenDensity(getContext())));
            }
            return this.c;
        }

        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawLine((float) a.this.f.getPixel(getContext(), R.styleable.Toolbar_collapseIcon), (float) a.this.f.getPixel(getContext(), XZBDevice.Delete), (float) a.this.f.getPixel(getContext(), R.styleable.AppCompatTheme_actionModePasteDrawable), (float) a.this.f.getPixel(getContext(), R.styleable.AppCompatTheme_actionModeCloseDrawable), a());
            canvas.drawLine((float) a.this.f.getPixel(getContext(), R.styleable.Toolbar_collapseIcon), (float) a.this.f.getPixel(getContext(), R.styleable.AppCompatTheme_actionModeCloseDrawable), (float) a.this.f.getPixel(getContext(), R.styleable.AppCompatTheme_actionModePasteDrawable), (float) a.this.f.getPixel(getContext(), XZBDevice.Delete), a());
        }
    }

    public static interface c {
        void a();

        void b();
    }

    private class d extends com.baidu.mobads.g.a.a {
        private Paint c;
        private int d;

        public d(Context context, int i) {
            super(context);
            this.d = i;
        }

        private Paint a() {
            if (this.c == null) {
                this.c = new Paint();
                this.c.setColor(this.d);
                this.c.setAlpha(VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX);
                this.c.setAntiAlias(true);
            }
            return this.c;
        }

        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            a(canvas, R.styleable.AppCompatTheme_actionModeCloseDrawable);
            a(canvas, XZBDevice.Delete);
            a(canvas, R.styleable.Toolbar_titleTextColor);
        }

        private void a(Canvas canvas, int i) {
            canvas.drawCircle((float) a.this.f.getPixel(getContext(), R.styleable.AppCompatTheme_actionMenuTextColor), (float) a.this.f.getPixel(getContext(), i), (float) ((int) (a.this.f.getScreenDensity(getContext()) * 1.0f)), a());
        }
    }

    public a(Context context) {
        super(context);
        this.b = new Paint();
        this.c = 0;
        this.d = 0;
        this.e = context;
    }

    public a(Context context, ActionBarColorTheme actionBarColorTheme) {
        this(context);
        this.g = actionBarColorTheme;
        setBackgroundColor(this.g.getBackgroundColor());
        this.f = m.a().m();
        a();
    }

    public void a(String str) {
        if (this.h != null) {
            this.h.setText(str);
            this.h.invalidate();
        }
    }

    public void a(c cVar) {
        this.a = cVar;
    }

    protected void a() {
        int pixel = this.f.getPixel(this.e, R.styleable.AppCompatTheme_selectableItemBackground);
        View bVar = new b(this.e, this.g.getCloseColor());
        bVar.setId(132343242);
        addView(bVar, new LayoutParams(pixel, -1));
        bVar.setOnClickListener(new b(this));
        bVar = new d(this.e, this.g.getCloseColor());
        bVar.setId(132343243);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(pixel, -1);
        layoutParams.addRule(XZBDevice.Success);
        bVar.setOnClickListener(new c(this));
        addView(bVar, layoutParams);
        this.h = new TextView(this.e);
        this.h.setTextSize(1, 16.0f);
        this.h.setLines(1);
        this.h.setEllipsize(TruncateAt.END);
        this.h.setGravity(R.styleable.Toolbar_titleMarginBottom);
        this.h.setTextColor(this.g.getTitleColor());
        this.h.setText(com.umeng.a.d);
        layoutParams = new LayoutParams(this.f.getScreenRect(this.e).width() - (pixel * 2), -1);
        layoutParams.addRule(XZBDevice.Predownload);
        addView(this.h, layoutParams);
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.g.equals(ActionBarColorTheme.ACTION_BAR_WHITE_THEME)) {
            this.b.setColor(-5592406);
            this.b.setStyle(Style.STROKE);
            this.b.setStrokeWidth((float) this.f.getPixel(this.e, 1));
            canvas.drawLine(AutoScrollHelper.RELATIVE_UNSPECIFIED, (float) this.d, (float) this.c, (float) this.d, this.b);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.c = i3 - i;
        this.d = i4 - i2;
    }
}
