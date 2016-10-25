package com.xunlei.common.httpclient.request;

import com.xunlei.common.base.XLLog;
import com.xunlei.common.httpclient.handler.HttpResponseHandler;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.protocol.HttpContext;

// compiled from: AsyncHttpRequest.java
public final class a implements Runnable {
    private final AbstractHttpClient a;
    private final HttpContext b;
    private final HttpUriRequest c;
    private final HttpResponseHandler d;

    public a(AbstractHttpClient abstractHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, HttpResponseHandler httpResponseHandler) {
        this.a = abstractHttpClient;
        this.b = httpContext;
        this.c = httpUriRequest;
        this.d = httpResponseHandler;
    }

    public final void run() {
        try {
            if (this.d != null) {
                this.d.sendStartMessage();
            }
            XLLog.v("AsyncHttpRequest", new StringBuilder("thread name:").append(Thread.currentThread().getName()).toString());
            b();
            if (this.d != null) {
                this.d.sendFinishMessage();
            }
        } catch (Throwable e) {
            e.printStackTrace();
            if (this.d != null) {
                this.d.sendFinishMessage();
                this.d.sendFailureMessage(e, null);
            }
        }
    }

    private void a() throws IOException {
        if (!Thread.currentThread().isInterrupted()) {
            try {
                HttpResponse execute = this.a.execute(this.c, this.b);
                if (!Thread.currentThread().isInterrupted() && this.d != null) {
                    this.d.sendResponseMessage(execute);
                }
            } catch (IOException e) {
                e.printStackTrace();
                XLLog.e("AsyncHttpRequest", new StringBuilder("IOException real error = ").append(e.getMessage()).toString());
                if (!Thread.currentThread().isInterrupted()) {
                    throw e;
                }
            } catch (Throwable th) {
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b() throws java.net.ConnectException {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.common.httpclient.request.a.b():void");
        /*
        this = this;
        r3 = 0;
        r0 = 0;
        r1 = r0;
        r2 = r3;
    L_0x0004:
        r0 = java.lang.Thread.currentThread();	 Catch:{ UnknownHostException -> 0x0057, SocketException -> 0x006c, SocketTimeoutException -> 0x0081, SSLException -> 0x0096, IOException -> 0x00c7, NullPointerException -> 0x00cd }
        r0 = r0.isInterrupted();	 Catch:{ UnknownHostException -> 0x0057, SocketException -> 0x006c, SocketTimeoutException -> 0x0081, SSLException -> 0x0096, IOException -> 0x00c7, NullPointerException -> 0x00cd }
        if (r0 != 0) goto L_0x002b;
    L_0x000e:
        r0 = r8.a;	 Catch:{ IOException -> 0x002c }
        r4 = r8.c;	 Catch:{ IOException -> 0x002c }
        r5 = r8.b;	 Catch:{ IOException -> 0x002c }
        r0 = r0.execute(r4, r5);	 Catch:{ IOException -> 0x002c }
        r4 = java.lang.Thread.currentThread();	 Catch:{ IOException -> 0x002c }
        r4 = r4.isInterrupted();	 Catch:{ IOException -> 0x002c }
        if (r4 != 0) goto L_0x002b;
    L_0x0022:
        r4 = r8.d;	 Catch:{ IOException -> 0x002c }
        if (r4 == 0) goto L_0x002b;
    L_0x0026:
        r4 = r8.d;	 Catch:{ IOException -> 0x002c }
        r4.sendResponseMessage(r0);	 Catch:{ IOException -> 0x002c }
    L_0x002b:
        return;
    L_0x002c:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0055 }
        r4 = "AsyncHttpRequest";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0055 }
        r6 = "IOException real error = ";
        r5.<init>(r6);	 Catch:{ all -> 0x0055 }
        r6 = r0.getMessage();	 Catch:{ all -> 0x0055 }
        r5 = r5.append(r6);	 Catch:{ all -> 0x0055 }
        r5 = r5.toString();	 Catch:{ all -> 0x0055 }
        com.xunlei.common.base.XLLog.e(r4, r5);	 Catch:{ all -> 0x0055 }
        r4 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0055 }
        r4 = r4.isInterrupted();	 Catch:{ all -> 0x0055 }
        if (r4 != 0) goto L_0x002b;
    L_0x0054:
        throw r0;	 Catch:{ all -> 0x0055 }
    L_0x0055:
        r0 = move-exception;
        throw r0;	 Catch:{ UnknownHostException -> 0x0057, SocketException -> 0x006c, SocketTimeoutException -> 0x0081, SSLException -> 0x0096, IOException -> 0x00c7, NullPointerException -> 0x00cd }
    L_0x0057:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = r8.d;
        if (r1 == 0) goto L_0x002b;
    L_0x005f:
        r1 = r8.d;
        r2 = "can't resolve host";
        r2 = r2.getBytes();
        r1.sendFailureMessage(r0, r2);
        goto L_0x002b;
    L_0x006c:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = r8.d;
        if (r1 == 0) goto L_0x002b;
    L_0x0074:
        r1 = r8.d;
        r2 = "can't resolve host";
        r2 = r2.getBytes();
        r1.sendFailureMessage(r0, r2);
        goto L_0x002b;
    L_0x0081:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = r8.d;
        if (r1 == 0) goto L_0x002b;
    L_0x0089:
        r1 = r8.d;
        r2 = "socket time out";
        r2 = r2.getBytes();
        r1.sendFailureMessage(r0, r2);
        goto L_0x002b;
    L_0x0096:
        r0 = move-exception;
        if (r2 == 0) goto L_0x00aa;
    L_0x0099:
        r1 = r8.d;
        if (r1 == 0) goto L_0x002b;
    L_0x009d:
        r1 = r8.d;
        r2 = "ssl socket error";
        r2 = r2.getBytes();
        r1.sendFailureMessage(r0, r2);
        goto L_0x002b;
    L_0x00aa:
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        android.os.SystemClock.sleep(r4);
        r0 = 1;
        r2 = "AsyncHttpRequest";
        r4 = "ssl socket hanshake error, retry once!";
        com.xunlei.common.base.XLLog.e(r2, r4);
        r7 = r1;
        r1 = r0;
        r0 = r7;
    L_0x00bc:
        if (r1 != 0) goto L_0x00ed;
    L_0x00be:
        r1 = new java.net.ConnectException;
        r1.<init>();
        r1.initCause(r0);
        throw r1;
    L_0x00c7:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = r3;
        goto L_0x00bc;
    L_0x00cd:
        r0 = move-exception;
        r0.printStackTrace();
        r1 = new java.io.IOException;
        r2 = new java.lang.StringBuilder;
        r4 = "NPE in HttpClient";
        r2.<init>(r4);
        r0 = r0.getMessage();
        r0 = r2.append(r0);
        r0 = r0.toString();
        r1.<init>(r0);
        r0 = r1;
        r1 = r3;
        goto L_0x00bc;
    L_0x00ed:
        r2 = r1;
        r1 = r0;
        goto L_0x0004;
        */
    }
}
