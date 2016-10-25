package com.bumptech.glide.load.a;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import anet.channel.util.HttpConstant;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.b.e;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

// compiled from: HttpUrlFetcher.java
public final class f implements c<InputStream> {
    private static final b a;
    private final e b;
    private final b c;
    private HttpURLConnection d;
    private InputStream e;
    private volatile boolean f;

    // compiled from: HttpUrlFetcher.java
    static interface b {
        HttpURLConnection a(URL url) throws IOException;
    }

    // compiled from: HttpUrlFetcher.java
    private static class a implements b {
        private a() {
        }

        public final HttpURLConnection a(URL url) throws IOException {
            return (HttpURLConnection) url.openConnection();
        }
    }

    static {
        a = new a();
    }

    public f(e eVar) {
        this(eVar, a);
    }

    private f(e eVar, b bVar) {
        this.b = eVar;
        this.c = bVar;
    }

    private InputStream a(URL url, int i, URL url2, Map<String, String> map) throws IOException {
        URL url3 = url;
        while (i < 5) {
            if (url2 != null) {
                try {
                    if (url3.toURI().equals(url2.toURI())) {
                        throw new IOException("In re-direct loop");
                    }
                } catch (URISyntaxException e) {
                }
            }
            this.d = this.c.a(url3);
            for (Entry entry : map.entrySet()) {
                this.d.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
            }
            if (TextUtils.isEmpty(this.d.getRequestProperty(HttpConstant.ACCEPT_ENCODING))) {
                this.d.setRequestProperty(HttpConstant.ACCEPT_ENCODING, "identity");
            }
            this.d.setConnectTimeout(e.REQUEST_MERGE_PERIOD);
            this.d.setReadTimeout(e.REQUEST_MERGE_PERIOD);
            this.d.setUseCaches(false);
            this.d.setDoInput(true);
            this.d.connect();
            if (this.f) {
                return null;
            }
            int responseCode = this.d.getResponseCode();
            if (responseCode / 100 == 2) {
                HttpURLConnection httpURLConnection = this.d;
                if (TextUtils.isEmpty(httpURLConnection.getContentEncoding())) {
                    this.e = com.bumptech.glide.h.b.a(httpURLConnection.getInputStream(), (long) httpURLConnection.getContentLength());
                } else {
                    if (Log.isLoggable("HttpUrlFetcher", XZBDevice.DOWNLOAD_LIST_FAILED)) {
                        new StringBuilder("Got non empty content encoding: ").append(httpURLConnection.getContentEncoding());
                    }
                    this.e = httpURLConnection.getInputStream();
                }
                return this.e;
            } else if (responseCode / 100 == 3) {
                Object headerField = this.d.getHeaderField(HttpConstant.LOCATION);
                if (TextUtils.isEmpty(headerField)) {
                    throw new IOException("Received empty or null redirect url");
                }
                i++;
                url2 = url3;
                url3 = new URL(url3, headerField);
            } else if (responseCode == -1) {
                throw new IOException("Unable to retrieve response code from HttpUrlConnection.");
            } else {
                throw new IOException(new StringBuilder("Request failed ").append(responseCode).append(": ").append(this.d.getResponseMessage()).toString());
            }
        }
        throw new IOException("Too many (> 5) redirects!");
    }

    public final void a() {
        if (this.e != null) {
            try {
                this.e.close();
            } catch (IOException e) {
            }
        }
        if (this.d != null) {
            this.d.disconnect();
        }
    }

    public final String b() {
        return this.b.a();
    }

    public final void c() {
        this.f = true;
    }

    public final /* synthetic */ Object a(Priority priority) throws Exception {
        e eVar = this.b;
        if (eVar.e == null) {
            if (TextUtils.isEmpty(eVar.d)) {
                String str = eVar.c;
                if (TextUtils.isEmpty(str)) {
                    str = eVar.a.toString();
                }
                eVar.d = Uri.encode(str, "@#&=*+-_.,:!?()/~'%");
            }
            eVar.e = new URL(eVar.d);
        }
        return a(eVar.e, 0, null, this.b.b.a());
    }
}
