package com.xunlei.xiazaibao.sdk.entities;

import android.text.TextUtils;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xiazaibao.sdk.base.NoObfuscationClassBase;
import java.util.List;
import org.android.spdy.SpdyProtocol;

public class GetDeviceListResponse extends NoObfuscationClassBase {
    public List<QueryDeviceInfo> devices;
    public int ret;

    public static class QueryDeviceInfo extends NoObfuscationClassBase {
        public String aliasname;
        public String box_status;
        public String box_version;
        public String deviceid;
        public String interip;
        public int interport;
        public String intraip;
        public int intraport;
        public int onlinestatus;
        public String pid;
        public String rights;
        public String serverip;
        private int serverport;

        public String getServerPort() {
            return String.valueOf(this.serverport);
        }

        public boolean isOnline() {
            return this.onlinestatus == 1;
        }

        public long getBoxStatusValue() throws NumberFormatException {
            return Long.parseLong(this.box_status.replaceAll("^0[x|X]", BuildConfig.VERSION_NAME), SpdyProtocol.CUSTOM);
        }

        public long getRightsValue() {
            if (TextUtils.isEmpty(this.rights)) {
                return 0;
            }
            try {
                return Long.parseLong(this.rights.replaceAll("^0[x|X]", BuildConfig.VERSION_NAME), SpdyProtocol.CUSTOM);
            } catch (NumberFormatException e) {
                return 0;
            }
        }
    }

    public GetDeviceListResponse() {
        this.ret = -1;
    }
}
