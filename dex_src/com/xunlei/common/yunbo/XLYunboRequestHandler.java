package com.xunlei.common.yunbo;

import android.content.Context;
import com.umeng.message.util.HttpRequest;
import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;

public class XLYunboRequestHandler {
    private static final String HEADER_KEY_CONTENTTYPE = "Content-Type";
    private static final String HEADER_KEY_COOKIE = "Cookie";
    private static final String HEADER_KEY_REFERER = "Referer";
    private static XLYunboRequestHandler s_instance;

    static {
        s_instance = new XLYunboRequestHandler();
    }

    private XLYunboRequestHandler() {
    }

    public static XLYunboRequestHandler getHandler() {
        return s_instance;
    }

    private Header[] getHeader() {
        Header[] headerArr = new Header[3];
        headerArr[0] = new BasicHeader(HEADER_KEY_CONTENTTYPE, HttpRequest.b);
        headerArr[1] = new BasicHeader(HEADER_KEY_REFERER, "www.xunlei.com");
        XLYB_INITDATA initData = XLYunboUtil.getInstance().getInitData();
        StringBuffer stringBuffer = new StringBuffer(BuildConfig.VERSION_NAME);
        stringBuffer.append("sessionid=").append(initData.userSessionId);
        stringBuffer.append("; userid=").append(initData.userId);
        stringBuffer.append("; isvip=").append(initData.userVipLevel);
        headerArr[2] = new BasicHeader(HEADER_KEY_COOKIE, stringBuffer.toString());
        return headerArr;
    }

    public void post(String str, byte[] bArr, BaseHttpClientListener baseHttpClientListener) {
        Context context = XLYunboUtil.getInstance().getContext();
        if (bArr == null) {
            XLYunboUtil.getInstance().getHttpClient().post(context, str, getHeader(), null, baseHttpClientListener);
            return;
        }
        XLYunboUtil.getInstance().getHttpClient().post(context, str, getHeader(), bArr, baseHttpClientListener);
    }

    public void post(String str, HttpEntity httpEntity, BaseHttpClientListener baseHttpClientListener) {
        Context context = XLYunboUtil.getInstance().getContext();
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            httpEntity.writeTo(byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        XLYunboUtil.getInstance().getHttpClient().post(context, str, getHeader(), byteArrayOutputStream.toByteArray(), baseHttpClientListener);
    }

    public void get(String str, BaseHttpClientListener baseHttpClientListener) {
        XLYunboUtil.getInstance().getHttpClient().get(XLYunboUtil.getInstance().getContext(), str, getHeader(), baseHttpClientListener);
    }
}
