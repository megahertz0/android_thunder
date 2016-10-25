package com.taobao.accs.client;

import android.text.TextUtils;
import anet.channel.AwcnConfig;
import anet.channel.GlobalAppRuntimeInfo;
import com.taobao.accs.IProcessName;
import com.taobao.accs.common.Constants;
import com.taobao.accs.data.Message;
import com.taobao.accs.utl.ALog;
import java.util.Arrays;

// compiled from: Taobao
public class AccsConfig {
    public static String[] ACCS_CENTER_HOSTS = null;
    public static String[] ACCS_CHANNEL_HOSTS = null;
    public static String[][] ACCS_CHANNEL_IPS = null;
    private static final String TAG = "AccsConfig";
    public static ACCS_GROUP mGroup;
    public static SECURITY_TYPE mSecurityType;

    // compiled from: Taobao
    public enum ACCS_GROUP {
        TAOBAO,
        ALIYUN,
        OPEN
    }

    // compiled from: Taobao
    public enum SECURITY_TYPE {
        SECURITY_TAOBAO,
        SECURITY_OPEN,
        SECURITY_OFF
    }

    static {
        ACCS_CENTER_HOSTS = new String[]{"acs.m.taobao.com", "acs.wapa.taobao.com", "acs.waptest.taobao.com"};
        ACCS_CHANNEL_HOSTS = new String[]{Constants.SERVICE_HOST, Constants.SERVICE_HOST, "accscdn.waptest.taobao.com"};
        r0 = new String[3][];
        r0[1] = new String[]{"110.75.206.79"};
        r0[2] = new String[]{"10.125.50.231"};
        ACCS_CHANNEL_IPS = r0;
        mSecurityType = SECURITY_TYPE.SECURITY_TAOBAO;
        mGroup = ACCS_GROUP.TAOBAO;
    }

    public static void setChannelHosts(String str, String str2, String str3) {
        ALog.i(TAG, "setChannelHosts", "releaseHost", str, "prepareHost", str2, "dailyHost", str3);
        ACCS_CHANNEL_HOSTS = new String[]{str, str2, str3};
    }

    public static void setChannelIps(String[] strArr, String[] strArr2, String[] strArr3) {
        String[] strArr4;
        Object obj = null;
        ALog.i(TAG, "setChannelIps", "releaseIp", strArr, "prepareIp", strArr2, "dailyIp", strArr3);
        String[][] strArr5 = new String[3][];
        strArr5[0] = strArr == null ? null : (String[]) Arrays.copyOf(strArr, strArr.length);
        strArr5[1] = strArr2 == null ? null : (String[]) Arrays.copyOf(strArr2, strArr2.length);
        if (strArr3 != null) {
            strArr4 = (String[]) Arrays.copyOf(strArr3, strArr3.length);
        }
        strArr5[2] = strArr4;
        ACCS_CHANNEL_IPS = strArr5;
    }

    public static void setSecurityGuardOff(SECURITY_TYPE security_type) {
        if (SECURITY_TYPE.SECURITY_OFF == security_type) {
            AwcnConfig.setSecurityGuardOff(true);
        }
        mSecurityType = security_type;
        a.a = security_type.ordinal();
        if (security_type == SECURITY_TYPE.SECURITY_TAOBAO) {
            mSecurityType = SECURITY_TYPE.SECURITY_TAOBAO;
        } else if (security_type == SECURITY_TYPE.SECURITY_OFF) {
            mSecurityType = SECURITY_TYPE.SECURITY_OFF;
        } else if (security_type == SECURITY_TYPE.SECURITY_OPEN) {
            mSecurityType = SECURITY_TYPE.SECURITY_OPEN;
        }
    }

    public static void setTnetPubkey(int i, int i2) {
        ALog.i(TAG, "setTnetPubkey", "pubKey", Integer.valueOf(i), "channelPubKey", Integer.valueOf(i2));
        AwcnConfig.setTnetPublicKey(i);
        GlobalClientInfo.b = i2;
    }

    public static void setAccsCenterHosts(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            ALog.e(TAG, "setAccsCenterHost null", new Object[0]);
            return;
        }
        AwcnConfig.setAccsCenterHosts(str, str2, str3);
        ACCS_CENTER_HOSTS = new String[]{str, str2, str3};
    }

    public static void setAccsCenterIps(String[] strArr, String[] strArr2, String[] strArr3) {
        AwcnConfig.setAccsCenterIps(strArr, strArr2, strArr3);
    }

    public static void setControlFrameMaxRetry(int i) {
        Message.CONTROL_MAX_RETRY_TIMES = i;
    }

    public static void setGroup(ACCS_GROUP accs_group) {
        mGroup = accs_group;
    }

    public static void disableInappKeepAlive() {
        AwcnConfig.setCallback(null);
        AwcnConfig.setSessionAutoRecreate(false);
        AwcnConfig.setHeartbeatFactory(null);
    }

    public static void setAuthCode(String str) {
        a.c = str;
        GlobalAppRuntimeInfo.setAuthCode(str);
    }

    public static void setMainProcessName(String str) {
        a.d = str;
    }

    public static void setChannelProcessName(String str) {
        a.e = str;
    }

    public static void setCurrProcessNameImpl(IProcessName iProcessName) {
        a.f = iProcessName;
    }

    public static void setChannelReuse(boolean z) {
        GlobalClientInfo.c = z;
    }
}
