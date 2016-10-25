package com.xunlei.downloadprovider.qrcode.a;

import android.content.Context;
import android.os.Handler;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.umeng.message.MsgConstant;
import com.xunlei.downloadprovider.a.h.b;
import com.xunlei.downloadprovider.commonview.dialog.XLBaseDialog;
import com.xunlei.downloadprovider.model.protocol.c;
import com.xunlei.downloadprovider.model.protocol.g.k;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: QRCodeResultQueryDialog.java
public final class a extends XLBaseDialog {
    private ProgressBar a;
    private ImageView b;
    private TextView c;
    private String d;
    private b e;
    private long f;
    private Handler g;
    private k h;
    private com.xunlei.downloadprovider.a.h.a i;

    static /* synthetic */ void a(a aVar, int i) {
        int i2 = 0;
        aVar.a.setVisibility(XZBDevice.Wait);
        aVar.b.setVisibility(0);
        if (com.xunlei.xllib.a.b.h(aVar.getContext()) || com.xunlei.xllib.a.b.g(aVar.getContext())) {
            i2 = 1;
        }
        if (i2 == 0) {
            aVar.c.setText("\u65e0\u7f51\u7edc\u8fde\u63a5\uff0c\u8bf7\u68c0\u67e5\u7f51\u7edc\u540e\u91cd\u8bd5");
        } else if (504 == i) {
            aVar.c.setText("\u670d\u52a1\u5668\u5fd9,\u8bf7\u7a0d\u5019\u91cd\u8bd5");
        } else {
            aVar.c.setText("\u4fe1\u606f\u52a0\u8f7d\u5931\u8d25");
        }
        StatReporter.reportQRXunleiDecode(MsgConstant.KEY_FAIL, System.currentTimeMillis() - aVar.f, "scanCode");
    }

    public a(Context context, Handler handler, String str) {
        super(context, 2131427871);
        this.i = new b(this);
        setContentView(2130968918);
        setCanceledOnTouchOutside(true);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = 80;
        getWindow().setAttributes(attributes);
        getWindow().setWindowAnimations(2131427524);
        this.a = (ProgressBar) findViewById(2131756768);
        this.b = (ImageView) findViewById(2131756769);
        this.c = (TextView) findViewById(2131756770);
        this.d = str;
        this.g = handler;
        this.e = new b(this.i);
        setOnCancelListener(new c(this));
        setOnDismissListener(new d(this));
    }

    public final void a() {
        c.a();
        c.b(this.d, this.e, com.umeng.a.d);
        this.f = System.currentTimeMillis();
    }

    public final void b() {
        this.a.setVisibility(XZBDevice.Wait);
        this.b.setVisibility(0);
        this.c.setText("\u65e0\u7f51\u7edc\uff0c\u8bf7\u68c0\u67e5\u7f51\u7edc\u540e\u91cd\u8bd5");
    }

    static /* synthetic */ void a(a aVar, com.xunlei.downloadprovider.b.c cVar) {
        aVar.setTag(Boolean.valueOf(true));
        aVar.h = (k) cVar.b;
        aVar.dismiss();
        StatReporter.reportQRXunleiDecode("sucess", System.currentTimeMillis() - aVar.f, "scanCode");
    }
}
