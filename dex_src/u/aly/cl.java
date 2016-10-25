package u.aly;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;

// compiled from: ImeiTracker.java
public final class cl extends a {
    private Context d;

    public cl(Context context) {
        super(SocializeProtocolConstants.PROTOCOL_KEY_IMEI);
        this.d = context;
    }

    public final String b() {
        TelephonyManager telephonyManager = (TelephonyManager) this.d.getSystemService("phone");
        try {
            if (t.a(this.d, "android.permission.READ_PHONE_STATE")) {
                return telephonyManager.getDeviceId();
            }
        } catch (Exception e) {
        }
        return null;
    }
}
