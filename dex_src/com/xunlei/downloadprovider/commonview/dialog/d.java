package com.xunlei.downloadprovider.commonview.dialog;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.umeng.a;
import com.xunlei.downloadprovidercommon.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: XLAlertDialog.java
public class d extends XLBaseDialog {
    protected View a;
    protected TextView b;
    protected TextView c;
    protected TextView d;
    protected TextView e;
    protected View f;
    protected OnClickListener g;
    protected OnClickListener h;

    public d(Context context, int i) {
        super(context, i);
        this.a = LayoutInflater.from(context).inflate(R.layout.xl_alert_dialog, null);
        setContentView(this.a);
        a(context);
    }

    public d(Context context) {
        super(context, R.style.bt_dialog);
        this.a = LayoutInflater.from(context).inflate(R.layout.xl_alert_dialog, null);
        setContentView(this.a);
        a(context);
    }

    public final void a(OnClickListener onClickListener) {
        this.g = onClickListener;
    }

    public final void b(OnClickListener onClickListener) {
        this.h = onClickListener;
    }

    public void setTitle(CharSequence charSequence) {
        if (this.b == null) {
            return;
        }
        if (charSequence != null) {
            this.b.setText(charSequence);
        } else {
            this.b.setText(R.string.tips);
        }
    }

    public void setTitle(int i) {
        if (this.b == null) {
            return;
        }
        if (i != 0) {
            this.b.setText(i);
        } else {
            this.b.setText(R.string.tips);
        }
    }

    @Deprecated
    public final void a(CharSequence charSequence) {
        b(charSequence);
    }

    public final void a() {
        if (this.c != null) {
            this.c.setText(2131231835);
        }
    }

    public final void b(CharSequence charSequence) {
        if (this.c != null) {
            TextView textView = this.c;
            if (charSequence == null) {
                charSequence = a.d;
            }
            textView.setText(charSequence);
        }
    }

    public final void c(CharSequence charSequence) {
        if (this.d == null) {
            return;
        }
        if (TextUtils.isEmpty(charSequence)) {
            this.d.setText(R.string.cancel);
        } else {
            this.d.setText(charSequence);
        }
    }

    public final void b() {
        if (this.e != null) {
            this.e.setText(2131231834);
        }
    }

    public final void d(CharSequence charSequence) {
        if (this.e == null) {
            return;
        }
        if (TextUtils.isEmpty(charSequence)) {
            this.e.setText(R.string.ok);
        } else {
            this.e.setText(charSequence);
        }
    }

    public final void a(boolean z) {
        if (this.d != null) {
            View view;
            View view2;
            int i;
            if (z) {
                this.d.setVisibility(XZBDevice.Wait);
                view = this.f;
            } else {
                this.d.setVisibility(0);
                view = this.f;
                if (this.e.getVisibility() == 0) {
                    view2 = view;
                    i = 0;
                    view2.setVisibility(i);
                    d();
                }
            }
            view2 = view;
            i = 8;
            view2.setVisibility(i);
            d();
        }
    }

    public final void b(boolean z) {
        if (this.e != null) {
            View view;
            View view2;
            int i;
            if (z) {
                this.e.setVisibility(XZBDevice.Wait);
                view = this.f;
            } else {
                this.e.setVisibility(0);
                view = this.f;
                if (this.d.getVisibility() == 0) {
                    view2 = view;
                    i = 0;
                    view2.setVisibility(i);
                    d();
                }
            }
            view2 = view;
            i = 8;
            view2.setVisibility(i);
            d();
        }
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

    private void d() {
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

    protected void a(Context context) {
        this.b = (TextView) this.a.findViewById(R.id.dlg_title);
        this.c = (TextView) this.a.findViewById(R.id.dlg_content);
        this.f = this.a.findViewById(R.id.dlg_btn_vertical_divider);
        this.d = (TextView) this.a.findViewById(R.id.dlg_cancel_btn);
        this.d.setOnClickListener(new e(this));
        this.e = (TextView) this.a.findViewById(R.id.dlg_confirm_btn);
        this.e.setOnClickListener(new f(this));
        d();
    }

    public final TextView c() {
        return this.c;
    }
}
