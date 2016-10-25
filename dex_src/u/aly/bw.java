package u.aly;

import android.content.Context;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: IDFATracker.java
public final class bw extends a {
    private Context d;

    public bw(Context context) {
        super("idfa");
        this.d = context;
    }

    public final String b() {
        String a = r.a(this.d);
        return a == null ? BuildConfig.VERSION_NAME : a;
    }
}
