package com.xunlei.tdlive.play.a;

import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

// compiled from: ReplayDialogPresenter.java
class ag implements OnGlobalLayoutListener {
    final /* synthetic */ af a;

    ag(af afVar) {
        this.a = afVar;
    }

    public void onGlobalLayout() {
        int measuredHeight = this.a.b.g().getDecorView().getMeasuredHeight();
        int bottomLayoutHeight = aa.b(this.a.b).getBottomLayoutHeight();
        LayoutParams layoutParams = this.a.a.getLayoutParams();
        if (aa.k(this.a.b).c()) {
            if ((measuredHeight < bottomLayoutHeight ? 1 : null) != null) {
                layoutParams.height -= bottomLayoutHeight - measuredHeight;
                this.a.a.setLayoutParams(layoutParams);
                this.a.a.postDelayed(new ah(this), 1);
            }
        } else if (this.a.a != null) {
            int i = (int) (((float) measuredHeight) / 3.0f);
            if (layoutParams.height != i) {
                layoutParams.height = i;
                this.a.a.setLayoutParams(layoutParams);
                this.a.a.postDelayed(new ai(this), 1);
            }
        }
    }
}
