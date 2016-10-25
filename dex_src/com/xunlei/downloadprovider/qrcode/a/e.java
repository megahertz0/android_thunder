package com.xunlei.downloadprovider.qrcode.a;

import android.content.Context;
import android.os.Handler;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import com.xunlei.downloadprovider.commonview.dialog.XLBaseDialog;

// compiled from: QRCodeResultTextDialog.java
public final class e extends XLBaseDialog {
    public TextView a;
    protected String b;
    public a c;
    private TextView d;
    private TextView e;
    private String f;
    private Handler g;

    // compiled from: QRCodeResultTextDialog.java
    public static interface a {
        void a();
    }

    public e(Context context, Handler handler, String str) {
        super(context, 2131427871);
        this.b = getClass().getSimpleName();
        this.c = null;
        setContentView(2130968919);
        setCanceledOnTouchOutside(true);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = 17;
        getWindow().setAttributes(attributes);
        this.d = (TextView) findViewById(2131756670);
        this.a = (TextView) findViewById(2131756673);
        if (str.contains("\u548cPC\u7aef")) {
            this.a.setText("\u786e\u5b9a");
        }
        this.e = (TextView) findViewById(2131756672);
        this.f = str;
        this.g = handler;
        this.d.setText(this.f);
        this.a.setOnClickListener(new f(this));
        this.e.setOnClickListener(new g(this));
        setOnDismissListener(new h(this));
    }
}
