package com.xunlei.downloadprovider.commonview.dialog;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.os.Build.VERSION;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovidercommon.R;
import org.android.spdy.SpdyProtocol;

// compiled from: XLCheckBoxDialog.java
public class h extends XLBaseDialog {
    public CheckBox a;
    public TextView b;
    public TextView c;
    public OnClickListener d;
    public OnClickListener e;
    private View f;
    private TextView g;
    private TextView h;
    private TextView i;
    private a j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private Context p;

    // compiled from: XLCheckBoxDialog.java
    public static interface a {
    }

    public h(Context context) {
        super(context, R.style.bt_dialog);
        this.g = null;
        this.a = null;
        this.b = null;
        this.c = null;
        this.h = null;
        this.i = null;
        this.d = null;
        this.e = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.p = context;
        this.o = context.getString(R.string.no_more_warn);
        b();
    }

    public h(Context context, String str, String str2, String str3) {
        this(context);
        this.k = str;
        this.m = str2;
        this.n = str3;
        b();
    }

    public final void a(String str) {
        this.b.setText(str);
    }

    public final void b(String str) {
        this.o = str;
        if (this.o != null) {
            this.a.setVisibility(0);
            this.a.setText(this.o);
            return;
        }
        this.a.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }

    public final void a() {
        this.a.setChecked(true);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    private void b() {
        this.f = LayoutInflater.from(this.p).inflate(R.layout.xl_checkbox_dialog, null);
        this.g = (TextView) this.f.findViewById(R.id.cb_xl_dlg_title_txt);
        this.b = (TextView) this.f.findViewById(R.id.cb_xl_dlg_content);
        this.c = (TextView) this.f.findViewById(R.id.cb_xl_dlg_size);
        this.a = (CheckBox) this.f.findViewById(R.id.cb_xl_dlg_cb);
        if (VERSION.SDK_INT < 17) {
            this.a.setPadding(this.a.getPaddingLeft() + g.a(this.p, 22.0f), this.a.getPaddingTop(), this.a.getPaddingRight(), this.a.getPaddingBottom());
        }
        this.h = (TextView) this.f.findViewById(R.id.cb_xl_dlg_left_btn);
        this.i = (TextView) this.f.findViewById(R.id.cb_xl_dlg_right_btn);
        setContentView(this.f);
        if (this.k != null) {
            this.b.setText(this.k);
        }
        if (this.l != null) {
            this.c.setVisibility(0);
            this.c.setText(this.l);
        }
        if (this.o != null) {
            this.a.setText(this.o);
        } else {
            this.a.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
        if (this.m != null) {
            this.h.setText(this.m);
        }
        if (this.n != null) {
            this.i.setText(this.n);
        }
        this.h.setOnClickListener(new i(this));
        this.i.setOnClickListener(new j(this));
        setCancelable(true);
        setCanceledOnTouchOutside(false);
    }
}
