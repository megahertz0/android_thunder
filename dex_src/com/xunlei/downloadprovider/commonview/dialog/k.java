package com.xunlei.downloadprovider.commonview.dialog;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovidercommon.R;

// compiled from: XLImageButtonDialog.java
public final class k extends XLBaseDialog {
    public TextView a;
    public TextView b;
    View c;
    private Context d;
    private ImageView e;
    private ImageView f;
    private TextView g;
    private OnClickListener h;

    public k(Context context) {
        super(context, R.style.bt_dialog);
        this.h = null;
        this.d = context;
        this.c = LayoutInflater.from(this.d).inflate(R.layout.xl_image_button_dialog, null);
        this.e = (ImageView) this.c.findViewById(R.id.dlg_image_content);
        this.g = (TextView) this.c.findViewById(R.id.dlg_title);
        this.a = (TextView) this.c.findViewById(R.id.dlg_content);
        this.f = (ImageView) this.c.findViewById(R.id.dlg_close_iv);
        this.b = (TextView) this.c.findViewById(R.id.dlg_bottom_btn);
        this.b.setVisibility(0);
        if (this.h == null) {
            a(new l(this));
        }
        this.f.setOnClickListener(new m(this));
        setContentView(this.c);
    }

    public final void a(OnClickListener onClickListener) {
        this.h = onClickListener;
        this.b.setTag(onClickListener);
        this.b.setOnClickListener(new n(this));
    }

    public final void show() {
        super.show();
        LayoutParams layoutParams = (LayoutParams) this.c.getLayoutParams();
        layoutParams.width = this.d.getResources().getDisplayMetrics().widthPixels;
        this.c.setLayoutParams(layoutParams);
        this.c.getViewTreeObserver().addOnGlobalLayoutListener(new o(this, (ImageView) findViewById(R.id.dlg_light_bg_iv), findViewById(R.id.dlg_content_rlay)));
    }
}
