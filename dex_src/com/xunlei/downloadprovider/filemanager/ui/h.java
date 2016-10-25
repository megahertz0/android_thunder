package com.xunlei.downloadprovider.filemanager.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.commonview.dialog.XLBaseDialog;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.io.IOException;

// compiled from: NewFileWindow.java
public final class h extends XLBaseDialog implements OnClickListener {
    private static String j;
    private static String k;
    public String a;
    private TextView b;
    private EditText c;
    private TextView d;
    private EditText e;
    private TextView f;
    private TextView g;
    private boolean h;
    private String i;

    static {
        j = "^[a-zA-Z0-9._\u2e80-\ufffd]+$";
        k = "^[a-zA-Z0-9_]+$";
    }

    public h(Context context, String str) {
        String str2;
        super(context, 2131427871);
        this.h = true;
        this.i = str;
        if (this.i.endsWith(File.separator)) {
            str2 = this.i;
        } else {
            str2 = this.i + File.separator;
        }
        this.i = str2;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int a = displayMetrics.widthPixels - (g.a(getContext(), 34.0f) * 2);
        View inflate = getLayoutInflater().inflate(2130968761, null);
        inflate.setMinimumWidth(a);
        setContentView(inflate);
        this.b = (TextView) findViewById(R.id.title);
        this.c = (EditText) findViewById(2131755946);
        this.d = (TextView) findViewById(2131755947);
        this.e = (EditText) findViewById(2131755948);
        this.f = (TextView) findViewById(R.id.cancel);
        this.g = (TextView) findViewById(R.id.ok);
        this.f.setOnClickListener(this);
        this.g.setOnClickListener(this);
        setCanceledOnTouchOutside(true);
        this.c.setHint(2131231405);
        LayoutParams layoutParams;
        if (this.h) {
            this.b.setText(2131231406);
            this.d.setVisibility(XZBDevice.Wait);
            this.e.setVisibility(XZBDevice.Wait);
            layoutParams = (LayoutParams) this.c.getLayoutParams();
            layoutParams.weight = 1.0f;
            this.c.setLayoutParams(layoutParams);
        } else {
            this.b.setText(2131231407);
            this.d.setVisibility(0);
            this.e.setVisibility(0);
            layoutParams = (LayoutParams) this.c.getLayoutParams();
            layoutParams.weight = 0.66f;
            this.c.setLayoutParams(layoutParams);
            layoutParams = (LayoutParams) this.e.getLayoutParams();
            layoutParams.weight = 0.34f;
            this.e.setLayoutParams(layoutParams);
        }
        this.c.requestFocus();
    }

    public final void onClick(View view) {
        if (view.getId() == 2131755949) {
            String trim;
            File file;
            if (this.h) {
                trim = this.c.getText().toString().trim();
                if (TextUtils.isEmpty(trim)) {
                    XLToast.d(getContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u8bf7\u8f93\u5165\u6587\u4ef6\u5939\u540d\u79f0");
                    return;
                } else if (trim.length() > 20) {
                    XLToast.d(getContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u6587\u4ef6\u5939\u540d\u79f0\u4e0d\u80fd\u8d85\u8fc720\u4e2a\u5b57\u7b26");
                    return;
                } else if (trim.matches(j)) {
                    trim = this.i + trim;
                    if (!trim.endsWith(File.separator)) {
                        trim = trim + File.separator;
                    }
                    file = new File(trim);
                    if (file.exists()) {
                        XLToast.d(getContext(), XLToastType.XLTOAST_TYPE_ALARM, file.isDirectory() ? "\u6587\u4ef6\u5939\u5df2\u5b58\u5728" : "\u6587\u4ef6\u5df2\u5b58\u5728");
                        return;
                    } else if (file.mkdirs()) {
                        this.a = trim;
                    } else {
                        XLToast.d(getContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u65b0\u5efa\u6587\u4ef6\u5939\u5931\u8d25");
                    }
                } else {
                    XLToast.d(getContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u6587\u4ef6\u5939\u540d\u79f0\u4e0d\u5408\u6cd5");
                    return;
                }
            }
            trim = this.c.getText().toString().trim();
            Object trim2 = this.e.getText().toString().trim();
            if (TextUtils.isEmpty(trim)) {
                XLToast.d(getContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u8bf7\u8f93\u5165\u6587\u4ef6\u540d");
                return;
            } else if (trim.length() > 20) {
                XLToast.d(getContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u6587\u4ef6\u540d\u4e0d\u80fd\u8d85\u8fc720\u4e2a\u5b57\u7b26");
                return;
            } else if (!trim.matches(j)) {
                XLToast.d(getContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u6587\u4ef6\u540d\u4e0d\u5408\u6cd5");
                return;
            } else if (trim2.length() > 8) {
                XLToast.d(getContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u6587\u4ef6\u540d\u4e0d\u80fd\u8d85\u8fc78\u4e2a\u5b57\u7b26");
                return;
            } else if (trim2.matches(k)) {
                if (TextUtils.isEmpty(trim2)) {
                    trim = this.i + trim;
                } else {
                    trim = this.i + trim + "." + trim2;
                }
                file = new File(trim);
                if (file.exists()) {
                    XLToast.d(getContext(), XLToastType.XLTOAST_TYPE_ALARM, file.isDirectory() ? "\u6587\u4ef6\u5939\u5df2\u5b58\u5728" : "\u6587\u4ef6\u5df2\u5b58\u5728");
                    return;
                }
                try {
                    if (file.createNewFile()) {
                        this.a = trim;
                    } else {
                        XLToast.d(getContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u65b0\u5efa\u6587\u4ef6\u5931\u8d25");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    XLToast.d(getContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u65b0\u5efa\u6587\u4ef6\u5931\u8d25");
                }
            } else {
                XLToast.d(getContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u975e\u6cd5\u540e\u7f00\u540d");
                return;
            }
        }
        dismiss();
    }
}
