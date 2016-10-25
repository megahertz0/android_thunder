package com.baidu.mobads.production.c;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public class a {
    public static int a(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return 0;
        }
        activeNetworkInfo = connectivityManager.getNetworkInfo(1);
        if (activeNetworkInfo != null && a(activeNetworkInfo.getState())) {
            return R.styleable.AppCompatTheme_buttonStyle;
        }
        if (VERSION.SDK_INT >= 13) {
            activeNetworkInfo = connectivityManager.getNetworkInfo(XZBDevice.Pause);
            if (activeNetworkInfo != null && a(activeNetworkInfo.getState())) {
                return R.styleable.AppCompatTheme_buttonStyleSmall;
            }
        }
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
        if (networkInfo == null || !a(networkInfo.getState())) {
            return com.xunlei.downloadprovider.b.c.a.DATA_TOO_LARGE_ERROR;
        }
        switch (((TelephonyManager) context.getSystemService("phone")).getNetworkType()) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
            case XZBDevice.DOWNLOAD_LIST_ALL:
            case R.styleable.Toolbar_contentInsetLeft:
            case XZBDevice.Success:
                return XZBDevice.DOWNLOAD_LIST_RECYCLE;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
            case R.styleable.Toolbar_contentInsetEnd:
            case XZBDevice.Wait:
            case XZBDevice.Pause:
            case XZBDevice.Stop:
            case XZBDevice.Fail:
            case XZBDevice.Predownload:
            case XZBDevice.Delete:
                return XZBDevice.DOWNLOAD_LIST_FAILED;
            case XZBDevice.Upload:
                return XZBDevice.DOWNLOAD_LIST_ALL;
            default:
                return 1;
        }
    }

    private static boolean a(State state) {
        return state != null && (state == State.CONNECTED || state == State.CONNECTING);
    }
}
