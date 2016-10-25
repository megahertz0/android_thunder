package com.inmobi.commons.core.network;

import anet.channel.util.HttpConstant;
import com.inmobi.commons.core.c.a;
import com.inmobi.commons.core.network.NetworkError.ErrorCode;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.c;
import com.sina.weibo.sdk.component.GameManager;
import com.taobao.accs.common.Constants;
import com.xunlei.downloadprovider.web.core.JsInterface;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

// compiled from: NetworkConnection.java
class b {
    private static final String a;
    private NetworkRequest b;
    private HttpURLConnection c;

    static {
        a = b.class.getName();
    }

    public b(NetworkRequest networkRequest) {
        this.b = networkRequest;
    }

    public c a() {
        c cVar;
        this.b.a();
        if (c.a()) {
            try {
                String j = this.b.j();
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Url: ").append(j).toString());
                this.c = a(j);
                if (!this.b.m()) {
                    this.c.setInstanceFollowRedirects(false);
                }
                if (this.b.n() == RequestType.POST) {
                    b(this.b.l());
                }
                return b();
            } catch (IOException e) {
                IOException iOException = e;
                cVar = new c(this.b);
                cVar.a(new NetworkError(ErrorCode.NETWORK_IO_ERROR, iOException.getLocalizedMessage()));
                iOException.printStackTrace();
                return cVar;
            } catch (IllegalArgumentException e2) {
                IllegalArgumentException illegalArgumentException = e2;
                cVar = new c(this.b);
                cVar.a(new NetworkError(ErrorCode.HTTP_BAD_REQUEST, new StringBuilder("The URL is malformed:").append(ErrorCode.HTTP_BAD_REQUEST.toString()).toString()));
                illegalArgumentException.printStackTrace();
                return cVar;
            } catch (SecurityException e3) {
                SecurityException securityException = e3;
                cVar = new c(this.b);
                cVar.a(new NetworkError(ErrorCode.UNKNOWN_ERROR, securityException.getLocalizedMessage()));
                securityException.printStackTrace();
                Map hashMap = new HashMap();
                hashMap.put(JsInterface.FUNPLAY_AD_TRPE, "SecurityException");
                hashMap.put(Constants.SHARED_MESSAGE_ID_FILE, securityException.getMessage());
                a.a().a("root", "ExceptionCaught", hashMap);
                return cVar;
            }
        }
        cVar = new c(this.b);
        cVar.a(new NetworkError(ErrorCode.NETWORK_UNAVAILABLE_ERROR, "Network unavailable."));
        return cVar;
    }

    private HttpURLConnection a(String str) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        a(httpURLConnection);
        return httpURLConnection;
    }

    private void a(HttpURLConnection httpURLConnection) throws ProtocolException {
        httpURLConnection.setConnectTimeout(this.b.o());
        httpURLConnection.setReadTimeout(this.b.p());
        httpURLConnection.setUseCaches(false);
        if (this.b.i() != null) {
            for (String str : this.b.i().keySet()) {
                httpURLConnection.setRequestProperty(str, (String) this.b.i().get(str));
            }
        }
        RequestType n = this.b.n();
        httpURLConnection.setRequestMethod(n.toString());
        if (n != RequestType.GET) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
        }
    }

    private void b(String str) throws IOException, SecurityException {
        this.c.setRequestProperty(HttpConstant.CONTENT_LENGTH, Integer.toString(str.length()));
        this.c.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        try {
            Closeable bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.c.getOutputStream()));
            try {
                bufferedWriter.write(str);
                c.a(bufferedWriter);
            } catch (Throwable th) {
                Throwable th2 = th;
                c.a(bufferedWriter);
                throw th2;
            }
        } catch (Throwable th3) {
            th2 = th3;
            bufferedWriter = null;
            c.a(bufferedWriter);
            throw th2;
        }
    }

    private c b() {
        c cVar = new c(this.b);
        try {
            int responseCode = this.c.getResponseCode();
            Logger.a(InternalLogLevel.INTERNAL, a, this.b.h() + "Response code: " + responseCode);
            Closeable closeable = null;
            if (responseCode != 200) {
                ErrorCode fromValue = ErrorCode.fromValue(responseCode);
                if (fromValue == null) {
                    fromValue = ErrorCode.UNKNOWN_ERROR;
                }
                cVar.a(new NetworkError(fromValue, new StringBuilder("HTTP:").append(responseCode).toString()));
                cVar.a(this.c.getHeaderFields());
            } else if (!this.b.g() || ((long) this.c.getContentLength()) <= this.b.f()) {
                InputStream inputStream = this.c.getInputStream();
                byte[] a = c.a(inputStream);
                if (a.length == 0) {
                    cVar.a(com.umeng.a.d);
                } else {
                    if (this.b.q()) {
                        a = this.b.a(a);
                        if (a == null) {
                            cVar.a(new NetworkError(ErrorCode.INVALID_ENCRYPTED_RESPONSE_RECEIVED, "Unable to decrypt the server response."));
                        }
                    }
                    if (a != null && this.b.r()) {
                        a = c.a(a);
                        if (a == null) {
                            cVar.a(new NetworkError(ErrorCode.GZIP_DECOMPRESSION_FAILED, "Failed to uncompress gzip response"));
                        }
                    }
                    if (a != null) {
                        cVar.a(new String(a, GameManager.DEFAULT_CHARSET));
                    }
                }
                cVar.a(this.c.getHeaderFields());
            } else {
                cVar.a(new NetworkError(ErrorCode.RESPONSE_EXCEEDS_SPECIFIED_SIZE_LIMIT, "Response size greater than specified max response size"));
            }
            c.a(closeable);
            this.c.disconnect();
        } catch (SocketTimeoutException e) {
            cVar.a(new NetworkError(ErrorCode.HTTP_GATEWAY_TIMEOUT, ErrorCode.HTTP_GATEWAY_TIMEOUT.toString()));
            e.printStackTrace();
        } catch (IOException e2) {
            cVar.a(new NetworkError(ErrorCode.NETWORK_IO_ERROR, ErrorCode.NETWORK_IO_ERROR.toString()));
            e2.printStackTrace();
        } catch (OutOfMemoryError e3) {
            cVar.a(new NetworkError(ErrorCode.OUT_OF_MEMORY_ERROR, ErrorCode.OUT_OF_MEMORY_ERROR.toString()));
            e3.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e4) {
            cVar.a(new NetworkError(ErrorCode.UNKNOWN_ERROR, ErrorCode.UNKNOWN_ERROR.toString()));
            e4.printStackTrace();
            Map hashMap = new HashMap();
            hashMap.put(JsInterface.FUNPLAY_AD_TRPE, "ArrayIndexOutOfBoundsException");
            hashMap.put(Constants.SHARED_MESSAGE_ID_FILE, e4.getMessage());
            a.a().a("root", "ExceptionCaught", hashMap);
        }
        return cVar;
    }
}
