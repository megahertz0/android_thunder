package com.umeng.socialize.editorpage;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.tencent.open.yyb.AppbarJsBridge;
import com.uc.addon.sdk.remote.Tabs;

public class KeyboardListenRelativeLayout extends RelativeLayout {
    public static final byte a = (byte) -3;
    public static final byte b = (byte) -2;
    public static final byte c = (byte) -1;
    private boolean d;
    private boolean e;
    private int f;
    private IOnKeyboardStateChangedListener g;

    public static interface IOnKeyboardStateChangedListener {
        void a(int i);
    }

    public KeyboardListenRelativeLayout(Context context) {
        super(context);
        this.d = false;
        this.e = false;
    }

    public KeyboardListenRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = false;
        this.e = false;
    }

    public KeyboardListenRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = false;
        this.e = false;
    }

    public void a(IOnKeyboardStateChangedListener iOnKeyboardStateChangedListener) {
        this.g = iOnKeyboardStateChangedListener;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.d) {
            this.f = this.f < i4 ? i4 : this.f;
        } else {
            this.d = true;
            this.f = i4;
            if (this.g != null) {
                this.g.a(-1);
            }
        }
        if (this.d && this.f > i4) {
            this.e = true;
            if (this.g != null) {
                this.g.a(AppbarJsBridge.Code_Java_Exception);
            }
        }
        if (this.d && this.e && this.f == i4) {
            this.e = false;
            if (this.g != null) {
                this.g.a(Tabs.TAB_CREATE_REACH_MAX_COUNT);
            }
        }
    }
}
