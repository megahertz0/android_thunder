package com.xunlei.downloadprovider.web.sniff.widget;

import android.content.Context;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.commonview.dialog.XLBaseDialog;

// compiled from: CopySniffResourceDownloadUrlToClipBoardDialog.java
public final class d extends XLBaseDialog implements Runnable {
    public TextView a;
    public a b;
    private boolean c;
    private long d;

    // compiled from: CopySniffResourceDownloadUrlToClipBoardDialog.java
    public static interface a {
        void a();
    }

    public d(Context context) {
        super(context, 2131427871);
        this.c = false;
        this.d = 1;
        setContentView(R.layout.copy_sniff_resource_download_url_view);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = 17;
        getWindow().setAttributes(attributes);
        this.a = (TextView) findViewById(R.id.tv_content);
    }

    public final void run() {
        if (this.d <= 0 || !this.c) {
            this.a.removeCallbacks(this);
            if (this.b != null) {
                this.b.a();
                return;
            }
            return;
        }
        this.a.postDelayed(this, 1000);
        if (this.d > 0) {
            this.c = true;
            this.d--;
            return;
        }
        this.c = false;
    }

    public final void show() {
        super.show();
        if (!this.c) {
            this.c = true;
            run();
        }
    }

    public final void dismiss() {
        super.dismiss();
        this.c = false;
        this.d = 0;
    }
}
