package android.support.design.widget;

import android.os.Build.VERSION;

// compiled from: ViewUtils.java
final class br implements d {
    br() {
    }

    public final bf a() {
        return new bf(VERSION.SDK_INT >= 12 ? new bk() : new bi());
    }
}
