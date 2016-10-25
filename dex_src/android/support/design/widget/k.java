package android.support.design.widget;

import android.graphics.Outline;

// compiled from: CircularBorderDrawableLollipop.java
final class k extends j {
    k() {
    }

    public final void getOutline(Outline outline) {
        copyBounds(this.b);
        outline.setOval(this.b);
    }
}
