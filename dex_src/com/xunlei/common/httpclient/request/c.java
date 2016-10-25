package com.xunlei.common.httpclient.request;

import android.os.SystemClock;
import com.tencent.connect.common.Constants;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Iterator;
import javax.net.ssl.SSLException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.protocol.HttpContext;

// compiled from: RetryHandler.java
public final class c implements HttpRequestRetryHandler {
    private static final int a = 1000;
    private static HashSet<Class<?>> b;
    private static HashSet<Class<?>> c;
    private final int d;

    static {
        b = new HashSet();
        c = new HashSet();
        b.add(NoHttpResponseException.class);
        b.add(UnknownHostException.class);
        b.add(SocketException.class);
        b.add(SSLException.class);
        c.add(InterruptedIOException.class);
    }

    public c(int i) {
        this.d = 5;
    }

    public final boolean retryRequest(IOException iOException, int i, HttpContext httpContext) {
        boolean z = true;
        Boolean bool = (Boolean) httpContext.getAttribute("http.request_sent");
        if (bool == null || !bool.booleanValue()) {
            Object obj = null;
        } else {
            boolean z2 = true;
        }
        if (i > this.d) {
            obj = null;
        } else if (a(c, iOException)) {
            obj = null;
        } else if (a(b, iOException)) {
            z2 = true;
        } else if (z2) {
            z2 = true;
        } else {
            z2 = true;
        }
        if (!z2) {
            z = z2;
        } else if (((HttpUriRequest) httpContext.getAttribute("http.request")).getMethod().equals(Constants.HTTP_POST)) {
            z = false;
        }
        if (z) {
            SystemClock.sleep(1000);
        } else {
            iOException.printStackTrace();
        }
        return z;
    }

    private static boolean a(HashSet<Class<?>> hashSet, Throwable th) {
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            if (((Class) it.next()).isInstance(th)) {
                return true;
            }
        }
        return false;
    }
}
