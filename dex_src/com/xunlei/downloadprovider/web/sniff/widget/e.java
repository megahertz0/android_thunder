package com.xunlei.downloadprovider.web.sniff.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.commonview.dialog.XLBaseDialog;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;

// compiled from: CreateSniffResourceDownloadTaskDialog.java
public final class e extends XLBaseDialog implements OnClickListener {
    public ImageView a;
    public SimpleCHNTextView b;
    public TextView c;
    public TextView d;
    protected String e;
    public a f;
    Drawable g;
    Drawable h;
    private TextView i;
    private TextView j;
    private TextView k;
    private TextView l;
    private SniffCopyDialogScrollView m;
    private com.xunlei.downloadprovider.web.sniff.widget.SniffCopyDialogScrollView.a n;

    // compiled from: CreateSniffResourceDownloadTaskDialog.java
    public static interface a {
        void a();
    }

    private Drawable a(int i) {
        return VERSION.SDK_INT >= 21 ? getContext().getResources().getDrawable(i, null) : getContext().getResources().getDrawable(i);
    }

    public e(Context context) {
        super(context, 2131427871);
        this.e = getClass().getSimpleName();
        this.f = null;
        this.g = a((int) R.drawable.iv_left_filter);
        if (this.g != null) {
            this.g.setBounds(0, 0, this.g.getMinimumWidth(), this.g.getMinimumHeight());
        }
        this.h = a((int) R.drawable.iv_right_filter);
        if (this.h != null) {
            this.h.setBounds(0, 0, this.h.getMinimumWidth(), this.h.getMinimumHeight());
        }
        this.n = new f(this);
        setContentView(R.layout.sniff_result_copy_to_clipboard_dialog);
        setCanceledOnTouchOutside(true);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = 17;
        getWindow().setAttributes(attributes);
        this.a = (ImageView) findViewById(R.id.iv_type_img);
        this.k = (TextView) findViewById(R.id.tv_cancel);
        this.l = (TextView) findViewById(R.id.tv_create_task);
        this.b = (SimpleCHNTextView) findViewById(R.id.tv_filename);
        this.i = (TextView) findViewById(R.id.tv_filter_one);
        this.j = (TextView) findViewById(R.id.tv_filter_two);
        this.c = (TextView) findViewById(R.id.tv_filesize);
        this.d = (TextView) findViewById(R.id.tv_content);
        this.m = (SniffCopyDialogScrollView) findViewById(R.id.sl_content);
        this.k.setOnClickListener(this);
        this.l.setOnClickListener(this);
        this.m.setOnHorizontalScrollListener(this.n);
    }

    public final void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                String str = "sniff_3_download_click_refuse";
                Sniff.a(g.a("android_sniff", str, str));
                dismiss();
            case R.id.tv_create_task:
                if (this.f != null) {
                    this.f.a();
                    dismiss();
                }
            default:
                break;
        }
    }
}
