package com.xunlei.downloadprovider.filemanager.ui;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.uc.addon.sdk.remote.EventIds;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.commonview.dialog.XLBaseDialog;
import com.xunlei.downloadprovider.filemanager.model.b;
import com.xunlei.downloadprovider.filemanager.model.i;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;

// compiled from: FileRenameWindow.java
public final class g extends XLBaseDialog implements OnClickListener {
    private Handler a;
    private File b;
    private i c;
    private a d;
    private EditText e;
    private TextView f;
    private EditText g;
    private TextView h;
    private TextView i;

    // compiled from: FileRenameWindow.java
    public static class a {
        public boolean a;
        public String b;
        public String c;
        public i d;

        public a() {
            this.a = false;
        }
    }

    public g(Context context, i iVar, Handler handler) {
        super(context, 2131427871);
        this.d = new a();
        this.c = iVar;
        this.a = handler;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int a = displayMetrics.widthPixels - (com.xunlei.downloadprovider.a.g.a(getContext(), 34.0f) * 2);
        View inflate = getLayoutInflater().inflate(2130968761, null);
        inflate.setMinimumWidth(a);
        setContentView(inflate);
        this.e = (EditText) findViewById(2131755946);
        this.f = (TextView) findViewById(2131755947);
        this.g = (EditText) findViewById(2131755948);
        this.h = (TextView) findViewById(R.id.cancel);
        this.i = (TextView) findViewById(R.id.ok);
        this.h.setOnClickListener(this);
        this.i.setOnClickListener(this);
        setCanceledOnTouchOutside(true);
        this.b = new File(this.c.g);
        this.d.b = this.c.g;
        this.d.d = this.c;
        String name = this.b.getName();
        if (this.b.isDirectory()) {
            this.e.setText(name);
            this.f.setVisibility(XZBDevice.Wait);
            this.g.setVisibility(XZBDevice.Wait);
            LayoutParams layoutParams = (LayoutParams) this.e.getLayoutParams();
            layoutParams.weight = 1.0f;
            this.e.setLayoutParams(layoutParams);
            return;
        }
        CharSequence substring;
        CharSequence substring2;
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf != -1) {
            substring = name.substring(0, lastIndexOf);
            substring2 = name.substring(lastIndexOf + 1);
        } else {
            String str = name;
            name = com.umeng.a.d;
            String str2 = str;
        }
        this.e.setText(substring);
        this.g.setText(substring2);
        this.f.setVisibility(0);
        this.g.setVisibility(0);
        layoutParams = (LayoutParams) this.e.getLayoutParams();
        layoutParams.weight = 0.66f;
        this.e.setLayoutParams(layoutParams);
        layoutParams = (LayoutParams) this.g.getLayoutParams();
        layoutParams.weight = 0.34f;
        this.g.setLayoutParams(layoutParams);
    }

    public final void onClick(View view) {
        if (view.getId() == 2131755949) {
            if (this.b.isDirectory()) {
                Object toString = this.e.getText().toString();
                if (TextUtils.isEmpty(toString)) {
                    XLToast.a(getContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u8bf7\u8f93\u5165\u6587\u4ef6\u540d\uff01");
                    return;
                }
                this.d.c = this.c.b() + toString;
                if (new File(this.d.c).exists()) {
                    XLToast.a(getContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u6587\u4ef6\u5df2\u5b58\u5728\uff01");
                    return;
                } else {
                    this.a.obtainMessage(1101).sendToTarget();
                    b.a(this.d, this.a);
                }
            } else {
                String toString2 = this.e.getText().toString();
                Object toString3 = this.g.getText().toString();
                if (TextUtils.isEmpty(toString2)) {
                    XLToast.a(getContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u8bf7\u8f93\u5165\u6587\u4ef6\u540d\uff01");
                    return;
                }
                if (TextUtils.isEmpty(toString3)) {
                    toString2 = toString2.trim();
                } else {
                    toString2 = toString2.trim() + "." + toString3.trim();
                }
                this.d.c = this.c.b() + toString2;
                if (new File(this.d.c).exists()) {
                    XLToast.a(getContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u6587\u4ef6\u5df2\u5b58\u5728\uff01");
                    return;
                } else {
                    this.a.obtainMessage(EventIds.EVENT_VIEW_FILE).sendToTarget();
                    b.b(this.d, this.a);
                }
            }
        }
        dismiss();
    }
}
