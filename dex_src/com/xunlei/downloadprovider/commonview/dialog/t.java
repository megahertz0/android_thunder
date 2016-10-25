package com.xunlei.downloadprovider.commonview.dialog;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovidercommon.R;

// compiled from: XLUpgradeDialog.java
public final class t extends XLBaseDialog {
    View a;
    private Context b;
    private ImageView c;
    private ImageView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private OnClickListener h;

    public t(Context context) {
        super(context, R.style.bt_dialog);
        this.h = null;
        this.b = context;
        this.a = LayoutInflater.from(this.b).inflate(R.layout.xl_upgrade_dialog, null);
        this.c = (ImageView) this.a.findViewById(R.id.dlg_image_content);
        this.e = (TextView) this.a.findViewById(R.id.dlg_title);
        this.f = (TextView) this.a.findViewById(R.id.dlg_content);
        this.d = (ImageView) this.a.findViewById(R.id.dlg_close_iv);
        this.g = (TextView) this.a.findViewById(R.id.dlg_bottom_btn);
        this.g.setVisibility(0);
        if (this.h == null) {
            a(new u(this));
        }
        this.d.setOnClickListener(new v(this));
        setContentView(this.a);
    }

    public final void a(String str) {
        if (str != null) {
            this.e.setText(str);
        } else {
            this.e.setText(R.string.tips);
        }
    }

    public final void a(CharSequence charSequence) {
        this.f.setText(charSequence);
    }

    public final void b(String str) {
        if (str != null) {
            this.g.setText(str);
        }
    }

    public final void a(OnClickListener onClickListener) {
        this.h = onClickListener;
        this.g.setTag(onClickListener);
        this.g.setOnClickListener(new w(this));
    }

    public final void show() {
        super.show();
        LayoutParams layoutParams = (LayoutParams) this.a.getLayoutParams();
        layoutParams.width = this.b.getResources().getDisplayMetrics().widthPixels;
        this.a.setLayoutParams(layoutParams);
    }
}
