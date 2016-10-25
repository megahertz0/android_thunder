package com.xunlei.downloadprovider.qrcode.a;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sina.weibo.sdk.component.GameManager;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.a.h.b;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.downloadprovider.commonview.dialog.XLBaseDialog;
import com.xunlei.downloadprovider.model.protocol.c;
import com.xunlei.downloadprovider.model.protocol.g.k;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.net.URLDecoder;
import org.android.spdy.SpdyAgent;

// compiled from: QRCodeResultURLDialog.java
public final class i extends XLBaseDialog {
    private TextView a;
    private ImageView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private k h;
    private String i;
    private b j;
    private Handler k;
    private a l;
    private String m;

    public i(Context context, Handler handler, k kVar) {
        super(context, 2131427871);
        this.l = new j(this);
        this.m = getClass().getSimpleName();
        setCanceledOnTouchOutside(true);
        this.k = handler;
        this.h = kVar;
        a();
        k kVar2 = this.h;
        if (TextUtils.isEmpty(kVar2.a)) {
            this.c.setVisibility(XZBDevice.Wait);
        } else {
            this.c.setText(kVar2.a);
        }
        if (kVar2.d > 0) {
            this.d.setText(new StringBuilder("\u6587\u4ef6\u5927\u5c0f: ").append(com.xunlei.downloadprovider.d.a.a(kVar2.d, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE)).toString());
        } else {
            this.d.setText("\u6587\u4ef6\u5927\u5c0f: \u672a\u77e5");
        }
        d(kVar2.b);
        this.e.setMovementMethod(null);
        this.e.setText(kVar2.b);
        switch (kVar2.i) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.f.setText(2131232050);
                this.f.setOnClickListener(new n(this, kVar2));
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                b(kVar2.b);
                break;
            default:
                c(kVar2.b);
                break;
        }
        this.f.setText(2131232050);
    }

    public i(Context context, Handler handler, String str, int i) {
        String decode;
        super(context, 2131427871);
        this.l = new j(this);
        this.m = getClass().getSimpleName();
        setCanceledOnTouchOutside(true);
        this.i = str;
        this.k = handler;
        a();
        String str2 = this.i;
        String str3 = null;
        try {
            decode = URLDecoder.decode(str2, GameManager.DEFAULT_CHARSET);
            if (decode.startsWith("fileName=")) {
                boolean z = true;
            } else {
                int i2 = i;
            }
            try {
                if (decode.startsWith("fileName=") && decode.contains(";h")) {
                    str3 = decode.substring(0, decode.indexOf(";h") + 1);
                }
            } catch (Exception e) {
            }
        } catch (Exception e2) {
            i2 = i;
        }
        decode = com.xunlei.downloadprovider.util.c.a.a(str2);
        d(decode);
        this.c.setText(decode);
        this.d.setVisibility(XZBDevice.Wait);
        this.e.setVisibility(XZBDevice.Wait);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(XZBDevice.Delete, -1);
        this.c.setLayoutParams(layoutParams);
        if (z) {
            if (str3 == null || str3.equals(com.umeng.a.d)) {
                a(decode);
            } else {
                a(str3 + decode);
            }
        } else if (z) {
            b(decode);
        } else {
            c(str2);
            if (XLFileTypeUtil.a(decode) == EFileCategoryType.E_OTHER_CATEGORY) {
                c.a();
                c.a(decode, this.j, com.umeng.a.d);
            }
        }
    }

    private void a() {
        this.j = new b(this.l);
        setContentView(2130968920);
        this.a = (TextView) findViewById(2131756771);
        this.b = (ImageView) findViewById(2131756773);
        this.c = (TextView) findViewById(2131756775);
        this.d = (TextView) findViewById(2131756776);
        this.e = (TextView) findViewById(2131756777);
        this.f = (TextView) findViewById(2131756673);
        this.g = (TextView) findViewById(2131756672);
        this.g.setOnClickListener(new k(this));
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = 17;
        getWindow().setAttributes(attributes);
        setOnDismissListener(new l(this));
    }

    private void a(String str) {
        this.f.setText(2131232050);
        this.f.setOnClickListener(new m(this, str));
    }

    private void b(String str) {
        this.f.setText(2131232051);
        this.f.setOnClickListener(new o(this, str));
    }

    private void c(String str) {
        this.f.setText(2131232049);
        this.f.setOnClickListener(new p(this, str));
    }

    private void d(String str) {
        if (XLFileTypeUtil.a(str) == EFileCategoryType.E_OTHER_CATEGORY || str.endsWith("html") || str.endsWith("HTML") || str.endsWith("htm") || str.endsWith("HTM")) {
            this.b.setImageResource(2130838959);
            this.a.setText(getContext().getResources().getText(2131232786));
            return;
        }
        int d = XLFileTypeUtil.d(str);
        this.a.setText(getContext().getResources().getText(2131232785));
        this.b.setImageResource(d);
    }
}
