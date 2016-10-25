package com.xunlei.downloadprovider.download.center.widget;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.TextView;
import com.xunlei.download.DownloadManager.TaskType;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.downloadprovider.commonview.dialog.XLBaseDialog;
import com.xunlei.xllib.a.d;
import org.android.spdy.SpdyProtocol;

// compiled from: DownloadTaskMoreOperationDialog.java
public final class v extends XLBaseDialog {
    com.xunlei.downloadprovider.download.a.a a;
    public a b;
    private View c;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private com.xunlei.downloadprovider.download.tasklist.a.a i;
    private t j;
    private String k;

    // compiled from: DownloadTaskMoreOperationDialog.java
    public static interface a extends OnClickListener {
        void onClick(View view);
    }

    public v(Context context, com.xunlei.downloadprovider.download.tasklist.a.a aVar, com.xunlei.downloadprovider.download.a.a aVar2, String str) {
        super(context, 2131427870);
        this.i = aVar;
        this.a = aVar2;
        this.k = str;
    }

    protected final void onCreate(Bundle bundle) {
        Window window;
        LayoutParams attributes;
        super.onCreate(bundle);
        setContentView(R.layout.download_task_more_operation_view_layout);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        this.c = findViewById(com.xunlei.downloadprovidershare.R.id.container);
        this.c.setOnClickListener(new w(this));
        this.d = (TextView) findViewById(R.id.shareButton);
        this.e = (TextView) findViewById(R.id.saveToXZBButton);
        this.f = (TextView) findViewById(R.id.deleteButton);
        this.g = (TextView) findViewById(R.id.openwithButton);
        this.h = (TextView) findViewById(R.id.downloadingPlayButton);
        this.d.setOnClickListener(new x(this));
        if (this.b != null) {
            this.e.setOnClickListener(this.b);
        }
        findViewById(R.id.playActionLayout).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        View findViewById = findViewById(R.id.openwithActionLayout);
        if (!(this.i == null || this.i.mTaskStatus != 8 || this.i.mTaskType == TaskType.BT)) {
            EFileCategoryType a;
            boolean z;
            com.xunlei.downloadprovider.download.tasklist.a.a aVar = this.i;
            if (aVar.mLocalFileName != null) {
                a = XLFileTypeUtil.a(aVar.mLocalFileName);
            } else {
                a = XLFileTypeUtil.a(aVar.mTitle);
            }
            if (a == EFileCategoryType.E_VIDEO_CATEGORY) {
                z = true;
            } else {
                int i = 0;
            }
            if (z) {
                findViewById.setVisibility(0);
                this.g.setOnClickListener(new y(this));
                this.f.setOnClickListener(new z(this));
                ((Button) findViewById(R.id.cancelButton)).setOnClickListener(new ac(this));
                window = getWindow();
                attributes = window.getAttributes();
                attributes.gravity = 81;
                attributes.width = d.a(getContext().getApplicationContext());
                attributes.height = -2;
                attributes.flags &= -1025;
                attributes.flags |= 2048;
                attributes.windowAnimations = 2131427601;
                window.setAttributes(attributes);
            }
        }
        findViewById.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.f.setOnClickListener(new z(this));
        ((Button) findViewById(R.id.cancelButton)).setOnClickListener(new ac(this));
        window = getWindow();
        attributes = window.getAttributes();
        attributes.gravity = 81;
        attributes.width = d.a(getContext().getApplicationContext());
        attributes.height = -2;
        attributes.flags &= -1025;
        attributes.flags |= 2048;
        attributes.windowAnimations = 2131427601;
        window.setAttributes(attributes);
    }
}
