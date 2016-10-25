package android.support.design.widget;

import android.support.v4.view.ViewCompat;
import android.support.v4.widget.AutoScrollHelper;
import com.taobao.accs.common.Constants;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: FloatingActionButtonIcs.java
class x extends u {
    private boolean n;

    x(VisibilityAwareImageButton visibilityAwareImageButton, ah ahVar) {
        super(visibilityAwareImageButton, ahVar);
    }

    boolean f() {
        return true;
    }

    final void g() {
        float rotation = this.k.getRotation();
        if (this.a != null) {
            ag agVar = this.a;
            float f = -rotation;
            if (agVar.l != f) {
                agVar.l = f;
                agVar.invalidateSelf();
            }
        }
        if (this.d != null) {
            j jVar = this.d;
            rotation = -rotation;
            if (rotation != jVar.j) {
                jVar.j = rotation;
                jVar.invalidateSelf();
            }
        }
    }

    final void c() {
        if (!this.n && this.k.getVisibility() == 0) {
            if (!ViewCompat.isLaidOut(this.k) || this.k.isInEditMode()) {
                this.k.a(XZBDevice.Wait, false);
                return;
            }
            this.k.animate().cancel();
            this.k.animate().scaleX(AutoScrollHelper.RELATIVE_UNSPECIFIED).scaleY(AutoScrollHelper.RELATIVE_UNSPECIFIED).alpha(AutoScrollHelper.RELATIVE_UNSPECIFIED).setDuration(Constants.ST_UPLOAD_MAX_COUNT).setInterpolator(a.c).setListener(new y(this));
        }
    }

    final void d() {
        if (!this.n && this.k.getVisibility() == 0) {
            return;
        }
        if (!ViewCompat.isLaidOut(this.k) || this.k.isInEditMode()) {
            this.k.a(0, false);
            this.k.setAlpha(1.0f);
            this.k.setScaleY(1.0f);
            this.k.setScaleX(1.0f);
            return;
        }
        this.k.animate().cancel();
        if (this.k.getVisibility() != 0) {
            this.k.setAlpha(AutoScrollHelper.RELATIVE_UNSPECIFIED);
            this.k.setScaleY(AutoScrollHelper.RELATIVE_UNSPECIFIED);
            this.k.setScaleX(AutoScrollHelper.RELATIVE_UNSPECIFIED);
        }
        this.k.animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setDuration(Constants.ST_UPLOAD_MAX_COUNT).setInterpolator(a.d).setListener(new z(this));
    }
}
