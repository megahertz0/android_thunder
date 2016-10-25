package com.xunlei.downloadprovider.xiazaibao.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.commonview.dialog.XLBaseDialog;
import com.xunlei.downloadprovider.xiazaibao.remotedownload.al;
import com.xunlei.downloadprovider.xiazaibao.remotedownload.d;
import com.xunlei.downloadprovider.xiazaibao.remotedownload.g.a;
import org.android.spdy.SpdyProtocol;

// compiled from: XZBTaskMoreOperationDialog.java
public final class j extends XLBaseDialog {
    public a a;
    private View b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private al g;
    private d h;

    public j(Context context, al alVar, d dVar) {
        super(context, 2131427870);
        this.g = alVar;
        this.h = dVar;
    }

    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.xzb_task_more_operation_view_layout);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        this.b = findViewById(com.xunlei.downloadprovidershare.R.id.container);
        this.b.setOnClickListener(new k(this));
        this.c = (TextView) findViewById(R.id.shareButton);
        this.d = (TextView) findViewById(R.id.deleteButton);
        this.e = (TextView) findViewById(R.id.openwithButton);
        this.f = (TextView) findViewById(R.id.downloadingPlayButton);
        findViewById(R.id.shareActionLayout).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.c.setOnClickListener(new l(this));
        findViewById(R.id.playActionLayout).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        findViewById(R.id.openwithActionLayout).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.d.setOnClickListener(new m(this));
        ((Button) findViewById(R.id.cancelButton)).setOnClickListener(new o(this));
        Window window = getWindow();
        LayoutParams attributes = window.getAttributes();
        attributes.gravity = 81;
        attributes.width = com.xunlei.xllib.a.d.a(getContext().getApplicationContext());
        attributes.height = -2;
        attributes.flags &= -1025;
        attributes.flags |= 2048;
        attributes.windowAnimations = 2131427601;
        window.setAttributes(attributes);
    }
}
