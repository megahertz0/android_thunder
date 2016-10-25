package com.xunlei.downloadprovider.commonview.dialog;

import android.content.Context;
import com.xunlei.downloadprovider.commonview.SimpleLoadingPageView;
import com.xunlei.downloadprovidercommon.R;

// compiled from: XLWaitingDialog.java
public class x extends XLBaseDialog {
    public static final String a;
    private SimpleLoadingPageView b;

    static {
        a = x.class.getSimpleName();
    }

    public x(Context context) {
        super(context, R.style.unified_loading_dialog);
        this.b = new SimpleLoadingPageView(context);
        setContentView(this.b);
    }

    public void show() {
        super.show();
        this.b.a();
    }

    public final void a(String str) {
        this.b.setTip(str);
    }
}
