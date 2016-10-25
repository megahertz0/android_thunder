package com.xunlei.downloadprovider.commonview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.umeng.a;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class ErrorView extends LinearLayout {
    private View a;
    private ImageView b;
    private TextView c;
    private TextView d;
    private Button e;
    private int f;

    public ErrorView(Context context) {
        super(context);
        this.f = 2;
        a(context);
    }

    public ErrorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = 2;
        a(context);
    }

    public ErrorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = 2;
        a(context);
    }

    private void a(Context context) {
        setGravity(R.styleable.Toolbar_maxButtonHeight);
        setBackgroundColor(-1);
        this.a = LayoutInflater.from(context).inflate(com.xunlei.downloadprovidercommon.R.layout.error_layout, this);
        this.b = (ImageView) this.a.findViewById(com.xunlei.downloadprovidercommon.R.id.iv_icon);
        this.c = (TextView) this.a.findViewById(com.xunlei.downloadprovidercommon.R.id.tv_title);
        this.d = (TextView) this.a.findViewById(com.xunlei.downloadprovidercommon.R.id.tv_detail);
        this.e = (Button) this.a.findViewById(com.xunlei.downloadprovidercommon.R.id.btn_action);
    }

    public void setErrorType(int i) {
        this.f = i;
        if (i == 0) {
            this.b.setImageResource(com.xunlei.downloadprovidercommon.R.drawable.bg_page_empty);
            this.c.setText(com.xunlei.downloadprovidercommon.R.string.page_empty);
            this.d.setVisibility(XZBDevice.Wait);
        } else if (i == 1) {
            this.b.setImageResource(com.xunlei.downloadprovidercommon.R.drawable.bg_page_gone);
            this.c.setText(com.xunlei.downloadprovidercommon.R.string.page_gone);
            this.d.setVisibility(XZBDevice.Wait);
        } else if (i == 2) {
            this.b.setImageResource(com.xunlei.downloadprovidercommon.R.drawable.bg_invalid_network);
            this.c.setText(com.xunlei.downloadprovidercommon.R.string.invalid_network);
            this.d.setText(com.xunlei.downloadprovidercommon.R.string.click_to_refresh);
            this.d.setVisibility(0);
        } else {
            this.b.setImageDrawable(null);
            this.c.setText(a.d);
            this.d.setVisibility(XZBDevice.Wait);
            this.f = -1;
        }
    }

    public final void a(Drawable drawable, String str, String str2) {
        if (drawable == null) {
            this.b.setVisibility(XZBDevice.Wait);
        } else {
            this.b.setImageDrawable(drawable);
            this.b.setVisibility(0);
        }
        this.c.setText(str);
        if (TextUtils.isEmpty(str2)) {
            this.d.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
            return;
        }
        this.d.setText(str2);
        this.d.setVisibility(0);
    }

    public final void a(String str, OnClickListener onClickListener) {
        if (TextUtils.isEmpty(str)) {
            this.e.setVisibility(XZBDevice.Wait);
            return;
        }
        this.e.setText(str);
        this.e.setOnClickListener(onClickListener);
    }

    public void setActionButtonListener(OnClickListener onClickListener) {
        this.e.setOnClickListener(onClickListener);
    }
}
