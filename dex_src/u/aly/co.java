package u.aly;

import android.os.Build;
import android.os.Build.VERSION;

// compiled from: SerialTracker.java
public final class co extends a {
    public co() {
        super("serial");
    }

    public final String b() {
        return VERSION.SDK_INT >= 9 ? Build.SERIAL : null;
    }
}
