package com.baidu.mobads.g;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.baidu.mobads.interfaces.utils.IXAdCommonUtils;
import com.baidu.mobads.j.m;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class d extends LinearLayout {
    protected b a;
    private Context b;
    private IXAdCommonUtils c;

    class a extends TextView {
        public a(Context context, String str) {
            super(context);
            a(str);
        }

        private void a(String str) {
            setText(str);
            setTextColor(ViewCompat.MEASURED_STATE_MASK);
            setGravity(R.styleable.Toolbar_maxButtonHeight);
            setBackgroundColor(-1);
            setTextSize(19.0f);
            setLayoutParams(new LayoutParams(-1, d.this.c.getPixel(d.this.b, R.styleable.AppCompatTheme_buttonBarStyle)));
        }
    }

    public static interface b {
        void a();

        void b();

        void c();
    }

    public d(Context context) {
        super(context);
        this.b = context;
        setBackgroundColor(-2236963);
        this.c = m.a().m();
        setOrientation(1);
        View aVar = new a(context, "\u5237\u65b0");
        LayoutParams layoutParams = (LayoutParams) aVar.getLayoutParams();
        layoutParams.bottomMargin = this.c.getPixel(this.b, XZBDevice.DOWNLOAD_LIST_RECYCLE);
        addView(aVar, layoutParams);
        View aVar2 = new a(context, "\u590d\u5236\u7f51\u5740");
        layoutParams = (LayoutParams) aVar2.getLayoutParams();
        layoutParams.bottomMargin = this.c.getPixel(this.b, XZBDevice.DOWNLOAD_LIST_ALL);
        addView(aVar2, layoutParams);
        View aVar3 = new a(context, "\u53d6\u6d88");
        addView(aVar3);
        aVar.setOnClickListener(new e(this));
        aVar2.setOnClickListener(new f(this));
        aVar3.setOnClickListener(new g(this));
    }

    public void a(b bVar) {
        this.a = bVar;
    }
}
