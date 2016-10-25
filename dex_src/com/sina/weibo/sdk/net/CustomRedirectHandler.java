package com.sina.weibo.sdk.net;

import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import com.sina.weibo.sdk.utils.LogUtil;
import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.client.RedirectHandler;
import org.apache.http.protocol.HttpContext;

public abstract class CustomRedirectHandler implements RedirectHandler {
    private static final int MAX_REDIRECT_COUNT = 15;
    private static final String TAG;
    int redirectCount;
    String redirectUrl;
    private String tempRedirectUrl;

    public abstract void onReceivedException();

    public abstract boolean shouldRedirectUrl(String str);

    static {
        TAG = CustomRedirectHandler.class.getCanonicalName();
    }

    public URI getLocationURI(HttpResponse httpResponse, HttpContext httpContext) throws ProtocolException {
        LogUtil.d(TAG, new StringBuilder("CustomRedirectHandler getLocationURI getRedirectUrl : ").append(this.tempRedirectUrl).toString());
        return !TextUtils.isEmpty(this.tempRedirectUrl) ? URI.create(this.tempRedirectUrl) : null;
    }

    public boolean isRedirectRequested(HttpResponse httpResponse, HttpContext httpContext) {
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == 301 || statusCode == 302) {
            this.tempRedirectUrl = httpResponse.getFirstHeader(HttpConstant.LOCATION).getValue();
            if (!TextUtils.isEmpty(this.tempRedirectUrl) && this.redirectCount < 15 && shouldRedirectUrl(this.tempRedirectUrl)) {
                this.redirectCount++;
                return true;
            }
        } else if (statusCode == 200) {
            this.redirectUrl = this.tempRedirectUrl;
        } else {
            onReceivedException();
        }
        return false;
    }

    public String getRedirectUrl() {
        return this.redirectUrl;
    }

    public int getRedirectCount() {
        return this.redirectCount;
    }
}
