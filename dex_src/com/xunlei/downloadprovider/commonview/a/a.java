package com.xunlei.downloadprovider.commonview.a;

import android.app.Activity;
import com.xunlei.downloadprovidercommon.R;

// compiled from: AnimationManager.java
public final class a {
    public static void a(Activity activity) {
        activity.overridePendingTransition(R.anim.translate_between_interface_right_in, R.anim.translate_between_interface_left_out);
    }

    public static void b(Activity activity) {
        activity.overridePendingTransition(R.anim.translate_between_interface_left_in, R.anim.translate_between_interface_right_out);
    }

    public static void c(Activity activity) {
        activity.overridePendingTransition(R.anim.translate_between_interface_bottom_in, R.anim.translate_alpha_out);
    }

    public static void d(Activity activity) {
        activity.overridePendingTransition(R.anim.translate_alpha_in, R.anim.translate_between_interface_bottom_out);
    }
}
