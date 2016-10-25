package com.xunlei.xiazaibao.sdk.synctasks;

import com.umeng.message.util.HttpRequest;
import com.xunlei.xiazaibao.sdk.XZBDeviceManager;
import com.xunlei.xiazaibao.sdk.base.SyncHttpTask;
import com.xunlei.xiazaibao.sdk.base.SyncHttpTask.HttpHeader;
import com.xunlei.xiazaibao.sdk.tools.XZBLog;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.apache.http.Header;

public class XZBRemoteDownloadBaseTask extends SyncHttpTask {
    private static final String TAG;
    private static int ct;
    private static int v;

    static {
        TAG = XZBRemoteDownloadBaseTask.class.getSimpleName();
        v = 2;
        ct = 22;
    }

    public static String appendCommonParams() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("&v=");
        stringBuffer.append(v);
        stringBuffer.append("&ct=");
        stringBuffer.append(ct);
        return stringBuffer.toString();
    }

    public String getUrl() {
        return null;
    }

    public Header[] getHeader() {
        return new Header[]{new HttpHeader("Cookie", getCookie()), new HttpHeader(HttpRequest.l, getContentType())};
    }

    public static String getContentType() {
        return HttpRequest.b;
    }

    public static String getCookie() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("userid=").append(XZBDeviceManager.getInstance().getUserInfo().userId);
        stringBuilder.append("; sessionid=").append(XZBDeviceManager.getInstance().getUserInfo().sessionId);
        stringBuilder.append("; clientoperationid=").append(XZBDeviceManager.getInstance().getUserInfo().businessType);
        stringBuilder.append(";");
        XZBLog.d(TAG, new StringBuilder("cookie = ").append(stringBuilder.toString()).toString());
        return stringBuilder.toString();
    }

    public static InputStream getStreamFromString(String str) {
        return new ByteArrayInputStream(str.getBytes());
    }

    public static String getBaseUrl() {
        return "http://yc.xiazaibao.xunlei.com:8182";
    }
}
