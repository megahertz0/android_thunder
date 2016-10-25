package anet.channel.entity;

import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: Taobao
public enum ENV {
    ONLINE(0),
    PREPARE(1),
    TEST(2);
    private int envMode;

    public final int getEnvMode() {
        return this.envMode;
    }

    public final void setEnvMode(int i) {
        this.envMode = i;
    }

    public static ENV valueOf(int i) {
        switch (i) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return PREPARE;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return TEST;
            default:
                return ONLINE;
        }
    }

    private ENV(int i) {
        this.envMode = i;
    }
}
