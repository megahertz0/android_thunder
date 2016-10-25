package android.support.design.widget;

import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.view.View;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: TextInputLayout.java
final class bb extends ViewPropertyAnimatorListenerAdapter {
    final /* synthetic */ CharSequence a;
    final /* synthetic */ TextInputLayout b;

    bb(TextInputLayout textInputLayout, CharSequence charSequence) {
        this.b = textInputLayout;
        this.a = charSequence;
    }

    public final void onAnimationEnd(View view) {
        this.b.h.setText(this.a);
        view.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
    }
}
