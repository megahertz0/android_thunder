package com.xunlei.tdlive;

import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.xunlei.tdlive.util.XLog;

// compiled from: LivePlayerDialog.java
class ba implements OnGlobalLayoutListener {
    final /* synthetic */ au a;

    ba(au auVar) {
        this.a = auVar;
    }

    public void onGlobalLayout() {
        int measuredHeight = this.a.getWindow().getDecorView().getMeasuredHeight();
        int bottomLayoutHeight = this.a.a.getBottomLayoutHeight();
        LayoutParams layoutParams = au.l(this.a).getLayoutParams();
        if (au.m(this.a)) {
            if ((measuredHeight < bottomLayoutHeight ? 1 : null) != null) {
                layoutParams.height -= bottomLayoutHeight - measuredHeight;
                XLog.d("LivePlayerDialog", new StringBuilder("onGlobalLayout newH=").append(layoutParams.height).toString());
                au.l(this.a).setLayoutParams(layoutParams);
                au.l(this.a).postDelayed(new bb(this), 1);
            }
        } else if (au.l(this.a) != null) {
            int i = (int) (((float) measuredHeight) / 3.0f);
            XLog.d("LivePlayerDialog", new StringBuilder("onGlobalLayout \u5168\u5c4f").append(measuredHeight).toString());
            if (layoutParams.height != i) {
                layoutParams.height = i;
                au.l(this.a).setLayoutParams(layoutParams);
                au.l(this.a).postDelayed(new bc(this), 1);
            }
        }
    }
}
