package com.xunlei.downloadprovider.player.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import com.xunlei.common.register.XLRegErrorCode;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.player.ab;
import com.xunlei.downloadprovider.player.b;

public class MediaTestItemView extends FrameLayout implements b {
    private ab a;
    private String b;

    public MediaTestItemView(Context context) {
        super(context);
        a(context);
    }

    public MediaTestItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public MediaTestItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.item_test_view, this, true);
        findViewById(R.id.test_btn).setOnClickListener(new a(this));
    }

    public final void a(ab abVar) {
        this.a = abVar;
        abVar.a(this, -1, XLRegErrorCode.REG_INVALID_VERIFY);
    }

    public final void b(ab abVar) {
        abVar.c(false);
        removeView(abVar.f);
        this.a = null;
    }

    public void setPlayUrl(String str) {
        this.b = str;
    }

    public ab getMediaPlayer() {
        return this.a;
    }
}
