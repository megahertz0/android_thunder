package com.xunlei.downloadprovider.model.protocol.g;

import com.xunlei.common.register.XLRegErrorCode;
import com.xunlei.common.yunbo.XLYunboMassage;
import com.xunlei.download.proguard.ai;
import com.xunlei.downloadprovider.b.c;
import com.xunlei.downloadprovider.util.c.a;
import java.net.URI;
import java.net.URLEncoder;
import org.apache.commons.logging.impl.SimpleLog;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.client.RedirectHandler;
import org.apache.http.protocol.HttpContext;

// compiled from: UrlBox.java
public final class b implements RedirectHandler {
    final /* synthetic */ a a;

    public b(a aVar) {
        this.a = aVar;
    }

    public final boolean isRedirectRequested(HttpResponse httpResponse, HttpContext httpContext) {
        switch (httpResponse.getStatusLine().getStatusCode()) {
            case XLRegErrorCode.REG_FORMAT_ERR_PARAM:
            case 303:
            case ai.a:
                return true;
            case 302:
                String a = a.a(httpResponse);
                if (a == null || !a.startsWith("http://")) {
                    return true;
                }
                String substring = a.substring(SimpleLog.LOG_LEVEL_OFF);
                if (-1 != substring.indexOf("http://") || -1 != substring.indexOf(URLEncoder.encode("http://"))) {
                    return true;
                }
                if (!com.xunlei.downloadprovider.url.b.e(a) && !com.xunlei.downloadprovider.url.b.d(a) && !com.xunlei.downloadprovider.url.b.b(a) && !com.xunlei.downloadprovider.url.b.g(a) && !com.xunlei.downloadprovider.url.b.a(a)) {
                    return true;
                }
                if (this.a.mListener != null) {
                    c cVar = new c();
                    cVar.b = a;
                    cVar.a = this.a.getRunnerId();
                    cVar.c = this.a.mUserData;
                    this.a.mListener.obtainMessage(XLYunboMassage.MSG_TASKFINISHED, 0, -1, cVar).sendToTarget();
                    this.a.mListener = null;
                }
                com.xunlei.downloadprovider.b.a.cancel(this.a.getRunnerId());
                return true;
            default:
                return false;
        }
    }

    public final URI getLocationURI(HttpResponse httpResponse, HttpContext httpContext) throws ProtocolException {
        String a = a.a(httpResponse);
        return a == null ? null : URI.create(a);
    }
}
