package u.aly;

import android.content.Context;
import android.provider.Settings.Secure;

// compiled from: AndroidIdTracker.java
public final class p extends a {
    private Context d;

    public p(Context context) {
        super("android_id");
        this.d = context;
    }

    public final String b() {
        try {
            return Secure.getString(this.d.getContentResolver(), "android_id");
        } catch (Exception e) {
            return null;
        }
    }
}
