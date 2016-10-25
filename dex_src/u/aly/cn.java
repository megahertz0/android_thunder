package u.aly;

import android.content.Context;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;

// compiled from: MacTracker.java
public final class cn extends a {
    private Context d;

    public cn(Context context) {
        super(SocializeProtocolConstants.PROTOCOL_KEY_MAC);
        this.d = context;
    }

    public final String b() {
        try {
            return t.k(this.d);
        } catch (Exception e) {
            return null;
        }
    }
}
