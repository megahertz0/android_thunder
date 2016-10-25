package com.xunlei.downloadprovider.member.register.view;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.xunlei.downloadprovider.commonview.dialog.XLBaseDialog;
import com.xunlei.tdlive.R;

// compiled from: VerifyCodeDialog.java
public final class e extends XLBaseDialog {
    public a a;
    public ImageView b;
    public EditText c;
    public View d;
    private View e;
    private View f;
    private View g;
    private Handler h;
    private Context i;

    // compiled from: VerifyCodeDialog.java
    public static interface a {
        void a();

        void a(String str);
    }

    public e(Context context) {
        super(context, 2131427871);
        this.h = new f(this);
        this.i = context;
        View inflate = LayoutInflater.from(context).inflate(2130969028, null);
        this.e = inflate;
        this.d = inflate.findViewById(2131756458);
        this.b = (ImageView) inflate.findViewById(2131757148);
        this.b.setOnClickListener(new g(this));
        this.c = (EditText) inflate.findViewById(2131757147);
        setContentView(this.e);
        this.g = this.e.findViewById(R.id.cancel);
        this.g.setOnClickListener(new h(this));
        this.f = findViewById(2131757150);
        this.f.setOnClickListener(new i(this));
        setCanceledOnTouchOutside(false);
    }

    public final void show() {
        super.show();
        this.h.sendEmptyMessageDelayed(100000, 500);
    }

    public final void a() {
        this.c.setText(com.umeng.a.d);
    }
}
