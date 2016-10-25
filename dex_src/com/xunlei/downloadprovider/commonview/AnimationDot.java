package com.xunlei.downloadprovider.commonview;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.TextView;
import com.umeng.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class AnimationDot extends TextView {
    private Handler a;
    private int b;
    private String c;

    static /* synthetic */ int b(AnimationDot animationDot) {
        int i = animationDot.b + 1;
        animationDot.b = i;
        return i;
    }

    public AnimationDot(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = a.d;
        c();
    }

    public AnimationDot(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = a.d;
        c();
    }

    public AnimationDot(Context context) {
        super(context);
        this.c = a.d;
        c();
    }

    private void c() {
        this.b = 0;
        if (this.a == null) {
            this.a = new a(this);
        }
    }

    public void setFrontText(String str) {
        if (str != null) {
            this.c = str;
        }
    }

    public final void a() {
        setVisibility(0);
        this.a.removeMessages(506428);
        this.a.sendEmptyMessage(506428);
    }

    public final void b() {
        if (this.a != null) {
            this.a.removeMessages(506428);
        }
        setVisibility(XZBDevice.Wait);
    }
}
