package com.xunlei.downloadprovider.download.taskDetail;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.commonview.dialog.XLBaseDialog;
import com.xunlei.downloadprovider.download.util.n;
import com.xunlei.xllib.a.d;
import org.android.spdy.SpdyProtocol;

// compiled from: TaskDetailDeleteDialog.java
public final class ao extends XLBaseDialog {
    Button a;
    OnClickListener b;
    OnClickListener c;
    OnClickListener d;
    com.xunlei.downloadprovider.download.tasklist.a.a e;
    a f;
    private Button g;
    private Button h;
    private Button i;
    private Button j;
    private View k;
    private View l;

    // compiled from: TaskDetailDeleteDialog.java
    public static interface a extends OnClickListener {
        void onClick(View view);
    }

    final void a(com.xunlei.downloadprovider.download.tasklist.a.a aVar) {
        if (n.c(aVar)) {
            if (this.h != null) {
                this.h.setVisibility(0);
                this.l.setVisibility(0);
            }
            if (this.i != null) {
                this.i.setVisibility(0);
                this.k.setVisibility(0);
                return;
            }
            return;
        }
        if (this.h != null) {
            this.h.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.l.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
        if (this.i != null) {
            this.i.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.k.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
    }

    public ao(Context context) {
        this(context, (byte) 0);
    }

    private ao(Context context, byte b) {
        super(context, 2131427870);
    }

    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.download_center_detail_delete_dialog);
        ((Button) findViewById(R.id.tv_cancel)).setOnClickListener(new ap(this));
        this.a = (Button) findViewById(R.id.delete_Button);
        this.a.setOnClickListener(new aq(this));
        this.h = (Button) findViewById(R.id.delete_bt_Button);
        this.h.setOnClickListener(new ar(this));
        this.j = (Button) findViewById(R.id.copy_download_url_Button);
        this.j.setOnClickListener(new as(this));
        this.i = (Button) findViewById(R.id.add_bt_Button);
        this.i.setOnClickListener(new at(this));
        this.k = findViewById(R.id.add_bt_line);
        this.l = findViewById(R.id.delete_bt_line);
        if (this.e != null) {
            a(this.e);
            if (n.c(this.e)) {
                this.a.setText("\u5168\u90e8\u5220\u9664");
            } else {
                this.a.setText("\u5220\u9664\u4efb\u52a1");
            }
        }
        this.g = (Button) findViewById(R.id.save_to_xzb_Button);
        if (this.f != null) {
            this.g.setOnClickListener(this.f);
        }
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
        this.e = null;
    }
}
