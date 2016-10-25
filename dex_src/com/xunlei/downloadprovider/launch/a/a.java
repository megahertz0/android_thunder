package com.xunlei.downloadprovider.launch.a;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xunlei.downloadprovider.commonview.dialog.XLBaseDialog;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: RequiredPermissionDialog.java
public final class a extends XLBaseDialog {
    protected View a;
    protected TextView b;
    protected TextView c;
    protected TextView d;
    protected TextView e;
    protected View f;
    protected OnClickListener g;
    protected OnClickListener h;

    // compiled from: RequiredPermissionDialog.java
    public static interface a {
        void a(a aVar, View view);
    }

    public a(Context context, a aVar) {
        super(context, 2131427871);
        this.a = LayoutInflater.from(context).inflate(2130968819, null);
        setContentView(this.a);
        this.b = (TextView) this.a.findViewById(R.id.dlg_title);
        this.c = (TextView) this.a.findViewById(R.id.dlg_content);
        this.f = this.a.findViewById(R.id.dlg_btn_vertical_divider);
        this.d = (TextView) this.a.findViewById(R.id.dlg_cancel_btn);
        this.d.setOnClickListener(new b(this));
        this.e = (TextView) this.a.findViewById(R.id.dlg_confirm_btn);
        this.e.setOnClickListener(new c(this));
        b();
        aVar.a(this, (ViewGroup) this.a);
    }

    private static void a(View view, int i) {
        Drawable drawable;
        if (VERSION.SDK_INT >= 21) {
            drawable = view.getResources().getDrawable(i, null);
        } else {
            drawable = view.getResources().getDrawable(i);
        }
        if (VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public final void a(OnClickListener onClickListener) {
        this.h = onClickListener;
    }

    public final void setTitle(CharSequence charSequence) {
        if (this.b == null) {
            return;
        }
        if (charSequence != null) {
            this.b.setText(charSequence);
        } else {
            this.b.setText(R.string.tips);
        }
    }

    public final void setTitle(int i) {
        if (this.b == null) {
            return;
        }
        if (i != 0) {
            this.b.setText(i);
        } else {
            this.b.setText(R.string.tips);
        }
    }

    public final void a(CharSequence charSequence) {
        if (this.e == null) {
            return;
        }
        if (TextUtils.isEmpty(charSequence)) {
            this.e.setText(R.string.ok);
        } else {
            this.e.setText(charSequence);
        }
    }

    public final void a() {
        if (this.d != null) {
            this.d.setVisibility(XZBDevice.Wait);
            this.f.setVisibility(XZBDevice.Wait);
            b();
        }
    }

    private void b() {
        int i = 0;
        if (this.e != null && this.d != null) {
            int i2;
            if (this.e.getVisibility() == 0) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            if (this.d.getVisibility() == 0) {
                i2++;
            }
            View view = this.f;
            if (!(this.d.getVisibility() == 0 && this.e.getVisibility() == 0)) {
                i = XZBDevice.Wait;
            }
            view.setVisibility(i);
            if (i2 == 1) {
                if (this.d.getVisibility() == 0) {
                    a(this.d, R.drawable.bottom_button_selector);
                }
                if (this.e.getVisibility() == 0) {
                    a(this.e, R.drawable.bottom_button_selector);
                    return;
                }
                return;
            }
            a(this.d, R.drawable.left_button_selector);
            a(this.e, R.drawable.right_button_selector);
        }
    }
}
