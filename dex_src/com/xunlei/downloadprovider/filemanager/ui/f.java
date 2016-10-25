package com.xunlei.downloadprovider.filemanager.ui;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.commonview.dialog.XLBaseDialog;

// compiled from: FileOperateOptionalWindow.java
public final class f extends XLBaseDialog implements OnClickListener {
    public TextView a;
    public ImageView b;
    public TextView c;
    public ImageView d;
    public TextView e;
    public ImageView f;
    public TextView g;
    public a h;
    private final String i;

    // compiled from: FileOperateOptionalWindow.java
    public static interface a {
        void a();

        void b();

        void c();

        void d();
    }

    public f(Context context) {
        super(context, 2131427871);
        this.i = getClass().getSimpleName();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int a = displayMetrics.widthPixels - (g.a(getContext(), 34.0f) * 2);
        View inflate = getLayoutInflater().inflate(2130968760, null);
        inflate.setMinimumWidth(a);
        setContentView(inflate);
        setCanceledOnTouchOutside(true);
        this.a = (TextView) findViewById(2131755939);
        this.a.setOnClickListener(this);
        this.c = (TextView) findViewById(2131755941);
        this.c.setOnClickListener(this);
        this.e = (TextView) findViewById(2131755943);
        this.e.setOnClickListener(this);
        this.g = (TextView) findViewById(2131755945);
        this.g.setOnClickListener(this);
        this.b = (ImageView) findViewById(2131755940);
        this.d = (ImageView) findViewById(2131755942);
        this.f = (ImageView) findViewById(2131755944);
    }

    public final void onClick(View view) {
        if (this.h == null) {
            dismiss();
            return;
        }
        switch (view.getId()) {
            case 2131755939:
                this.h.a();
                break;
            case 2131755941:
                this.h.b();
                break;
            case 2131755943:
                this.h.c();
                break;
            case 2131755945:
                this.h.d();
                break;
        }
        dismiss();
    }
}
