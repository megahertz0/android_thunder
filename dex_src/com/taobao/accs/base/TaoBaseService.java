package com.taobao.accs.base;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.taobao.accs.base.TaoBaseService.ExtHeaderType;
import com.taobao.accs.base.TaoBaseService.ExtraInfo;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.Serializable;
import java.util.Map;
import org.android.spdy.SpdyAgent;

// compiled from: Taobao
public abstract class TaoBaseService extends Service implements AccsDataListener {
    private static final String TAG = "TaoBaseService";
    private AccsAbstractDataListener mDefaultDataListener;

    // compiled from: Taobao
    public static class ConnectInfo implements Serializable {
        private static final long serialVersionUID = 8974674111758240362L;
        public boolean connected;
        public int errorCode;
        public String errordetail;
        public String host;
        public boolean isCenterHost;
        public boolean isInapp;

        public ConnectInfo(String str, boolean z, boolean z2) {
            this.host = str;
            this.isInapp = z;
            this.isCenterHost = z2;
        }

        public ConnectInfo(String str, boolean z, boolean z2, int i, String str2) {
            this.host = str;
            this.isInapp = z;
            this.isCenterHost = z2;
            this.errorCode = i;
            this.errordetail = str2;
        }
    }

    // compiled from: Taobao
    public enum ExtHeaderType {
        TYPE_BUSINESS,
        TYPE_SID,
        TYPE_USERID,
        TYPE_COOKIE,
        TYPE_TAG,
        TYPE_STATUS,
        TYPE_DELAY,
        TYPE_EXPIRE,
        TYPE_LOCATION,
        TYPE_UNIT,
        TYPE_NEED_BUSINESS_ACK;

        public static com.taobao.accs.base.TaoBaseService.ExtHeaderType valueOf(int i) {
            switch (i) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    return TYPE_BUSINESS;
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    return TYPE_SID;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    return TYPE_USERID;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    return TYPE_COOKIE;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    return TYPE_TAG;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    return TYPE_STATUS;
                case R.styleable.Toolbar_contentInsetEnd:
                    return TYPE_DELAY;
                case R.styleable.Toolbar_contentInsetLeft:
                    return TYPE_EXPIRE;
                case XZBDevice.Wait:
                    return TYPE_LOCATION;
                case XZBDevice.Pause:
                    return TYPE_UNIT;
                case XZBDevice.Stop:
                    return TYPE_NEED_BUSINESS_ACK;
                default:
                    return null;
            }
        }
    }

    // compiled from: Taobao
    public static class ExtraInfo implements Serializable {
        public Map<ExtHeaderType, String> extHeader;
        public String fromHost;
        public String fromPackage;
    }

    public TaoBaseService() {
        this.mDefaultDataListener = new AccsAbstractDataListener() {
            public void onData(String str, String str2, String str3, byte[] bArr, ExtraInfo extraInfo) {
            }

            public void onBind(String str, int i, ExtraInfo extraInfo) {
            }

            public void onUnbind(String str, int i, ExtraInfo extraInfo) {
            }

            public void onSendData(String str, String str2, int i, ExtraInfo extraInfo) {
            }

            public void onResponse(String str, String str2, int i, byte[] bArr, ExtraInfo extraInfo) {
            }
        };
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onAntiBrush(boolean z, ExtraInfo extraInfo) {
        this.mDefaultDataListener.onAntiBrush(z, extraInfo);
    }

    public void onDisconnected(ConnectInfo connectInfo) {
        this.mDefaultDataListener.onDisconnected(connectInfo);
    }

    public void onConnected(ConnectInfo connectInfo) {
        this.mDefaultDataListener.onConnected(connectInfo);
    }

    public void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return AccsAbstractDataListener.onReceiveData(this, intent, this);
    }
}
