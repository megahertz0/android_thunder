package android.support.v4.net;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

class ConnectivityManagerCompatGingerbread {
    ConnectivityManagerCompatGingerbread() {
    }

    public static boolean isActiveNetworkMetered(ConnectivityManager connectivityManager) {
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return true;
        }
        switch (activeNetworkInfo.getType()) {
            case SpdyAgent.ACCS_TEST_SERVER:
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
            case XZBDevice.DOWNLOAD_LIST_FAILED:
            case XZBDevice.DOWNLOAD_LIST_ALL:
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
            case R.styleable.Toolbar_contentInsetEnd:
                return true;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return false;
            default:
                return true;
        }
    }
}
