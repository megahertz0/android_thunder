package com.xunlei.downloadprovider.xiazaibao.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.commonview.dialog.XLBaseDialog;
import com.xunlei.downloadprovider.download.util.n;
import com.xunlei.xllib.a.d;
import org.android.spdy.SpdyProtocol;

// compiled from: XZBDetailDeleteDialog.java
public final class a extends XLBaseDialog {
    public OnClickListener a;
    private Button b;
    private Button c;
    private Button d;
    private Button e;
    private OnClickListener f;
    private OnClickListener g;
    private com.xunlei.downloadprovider.download.tasklist.a.a h;

    public a(Context context) {
        this(context, (byte) 0);
    }

    private a(Context context, byte b) {
        super(context, 2131427870);
    }

    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.download_center_detail_delete_dialog);
        ((Button) findViewById(R.id.tv_cancel)).setOnClickListener(new b(this));
        this.c = (Button) findViewById(R.id.delete_Button);
        this.c.setOnClickListener(new c(this));
        this.d = (Button) findViewById(R.id.delete_bt_Button);
        this.d.setOnClickListener(new d(this));
        this.e = (Button) findViewById(R.id.add_bt_Button);
        this.e.setOnClickListener(new e(this));
        if (this.h != null) {
            if (n.c(this.h)) {
                if (this.d != null) {
                    this.d.setVisibility(0);
                }
                if (this.e != null) {
                    this.e.setVisibility(0);
                }
            } else {
                if (this.d != null) {
                    this.d.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                }
                if (this.e != null) {
                    this.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                }
            }
            if (n.c(this.h)) {
                this.c.setText("\u5168\u90e8\u5220\u9664");
            } else {
                this.c.setText("\u5220\u9664\u4efb\u52a1");
            }
        }
        this.b = (Button) findViewById(R.id.save_to_xzb_Button);
        this.b.setOnClickListener(new f(this));
        this.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        Window window = getWindow();
        LayoutParams attributes = window.getAttributes();
        attributes.gravity = 81;
        attributes.width = d.a(getContext().getApplicationContext());
        attributes.height = -2;
        attributes.flags &= -1025;
        attributes.flags |= 2048;
        attributes.windowAnimations = 2131427601;
        window.setAttributes(attributes);
    }

    public final void dismiss() {
        super.dismiss();
        this.h = null;
    }
}
