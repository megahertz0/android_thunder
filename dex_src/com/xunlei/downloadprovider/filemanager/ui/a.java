package com.xunlei.downloadprovider.filemanager.ui;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.downloadprovider.a.h;
import com.xunlei.downloadprovider.a.h.b;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.downloadprovider.commonview.dialog.XLBaseDialog;
import com.xunlei.downloadprovider.filemanager.FileManagerDirActivity;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;

// compiled from: FileDetailsWindow.java
public final class a extends XLBaseDialog implements OnClickListener {
    private static final int h;
    public TextView a;
    public TextView b;
    public TextView c;
    public TextView d;
    public LinearLayout e;
    public LinearLayout f;
    public b g;
    private TextView i;
    private TextView j;
    private TextView k;
    private Activity l;
    private com.xunlei.downloadprovider.a.h.a m;

    static {
        h = h.a();
    }

    public a(Context context) {
        super(context, 2131427871);
        this.m = new c(this);
        this.l = (Activity) context;
        setContentView(getLayoutInflater().inflate(2130968759, null));
        setCanceledOnTouchOutside(true);
        this.g = new b(this.m);
        this.a = (TextView) findViewById(2131755925);
        this.b = (TextView) findViewById(2131755927);
        this.c = (TextView) findViewById(2131755930);
        this.d = (TextView) findViewById(2131755933);
        this.e = (LinearLayout) findViewById(R.id.dlg_2btn_layout);
        this.i = (TextView) findViewById(2131755934);
        this.j = (TextView) findViewById(2131755935);
        this.i.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.f = (LinearLayout) findViewById(R.id.dlg_1btn_layout);
        this.f.setVisibility(XZBDevice.Wait);
        this.k = (TextView) findViewById(R.id.dlg_bottom_btn);
        this.k.setOnClickListener(this);
    }

    public final void onClick(View view) {
        switch (view.getId()) {
            case 2131755934:
                String toString = this.b.getText().toString();
                if (toString != null) {
                    File file = new File(toString);
                    if (!file.isDirectory()) {
                        File parentFile = file.getParentFile();
                        if (parentFile != null) {
                            toString = parentFile.getAbsolutePath();
                        } else {
                            toString = "/";
                        }
                    }
                    FileManagerDirActivity.a(this.l, toString);
                    EFileCategoryType a = XLFileTypeUtil.a(this.a.getText().toString());
                    if (a != null) {
                        StatReporter.reportClick(XLErrorCode.ALI_AUTH_USER_CANCLE, "longClickGotoDir", com.xunlei.downloadprovider.filemanager.model.b.a(a.ordinal()));
                    }
                }
                dismiss();
            case 2131755935:
                dismiss();
            case R.id.dlg_bottom_btn:
                dismiss();
            default:
                break;
        }
    }
}
