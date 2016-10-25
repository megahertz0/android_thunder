package com.sina.weibo.sdk.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.taobao.accs.utl.UtilityImpl;
import org.apache.http.HttpHost;

public class NetStateManager {
    public static NetState CUR_NETSTATE;
    private static Context mContext;

    public enum NetState {
        Mobile,
        WIFI,
        NOWAY;

        static {
            Mobile = new com.sina.weibo.sdk.net.NetStateManager.NetState("Mobile", 0);
            WIFI = new com.sina.weibo.sdk.net.NetStateManager.NetState("WIFI", 1);
            NOWAY = new com.sina.weibo.sdk.net.NetStateManager.NetState("NOWAY", 2);
            ENUM$VALUES = new com.sina.weibo.sdk.net.NetStateManager.NetState[]{Mobile, WIFI, NOWAY};
        }
    }

    public class NetStateReceive extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            mContext = context;
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                WifiManager wifiManager = (WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI);
                WifiInfo connectionInfo = wifiManager.getConnectionInfo();
                if (!wifiManager.isWifiEnabled() || -1 == connectionInfo.getNetworkId()) {
                    CUR_NETSTATE = Mobile;
                }
            }
        }
    }

    static {
        CUR_NETSTATE = NetState.Mobile;
    }

    public static HttpHost getAPN() {
        Cursor query;
        HttpHost httpHost = null;
        Uri parse = Uri.parse("content://telephony/carriers/preferapn");
        if (mContext != null) {
            query = mContext.getContentResolver().query(parse, null, null, null, null);
        } else {
            query = null;
        }
        if (query != null && query.moveToFirst()) {
            String string = query.getString(query.getColumnIndex("proxy"));
            if (string != null && string.trim().length() > 0) {
                httpHost = new HttpHost(string, 80);
            }
            query.close();
        }
        return httpHost;
    }
}
