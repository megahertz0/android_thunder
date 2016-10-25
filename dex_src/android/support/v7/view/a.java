package android.support.v7.view;

import android.content.Context;
import android.support.v7.appcompat.R;

// compiled from: ActionBarPolicy.java
public final class a {
    public Context a;

    public static a a(Context context) {
        return new a(context);
    }

    private a(Context context) {
        this.a = context;
    }

    public final boolean a() {
        return this.a.getApplicationInfo().targetSdkVersion >= 16 ? this.a.getResources().getBoolean(R.bool.abc_action_bar_embed_tabs) : this.a.getResources().getBoolean(R.bool.abc_action_bar_embed_tabs_pre_jb);
    }
}
