package com.xunlei.downloadprovider.b.c;

import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import com.tencent.connect.common.Constants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

// compiled from: BpUrlLoader.java
public final class g extends e {
    public c a;
    public b b;
    public d c;
    public a d;
    Map<String, String> e;
    boolean f;
    public int g;
    private HttpURLConnection h;
    private String j;
    private List<byte[]> k;
    private String l;
    private String m;
    private String n;
    private int o;
    private int p;
    private boolean q;
    private Thread r;

    // compiled from: BpUrlLoader.java
    public static interface a {
        void a(int i, Map<String, List<String>> map, g gVar);
    }

    // compiled from: BpUrlLoader.java
    public static interface b {
        void a(int i, Map<String, List<String>> map, byte[] bArr);
    }

    // compiled from: BpUrlLoader.java
    public static interface c {
        void a();
    }

    // compiled from: BpUrlLoader.java
    public static interface d {
        void a(byte[] bArr, int i, g gVar);
    }

    public g(String str) {
        this.f = false;
        this.q = false;
        this.r = null;
        this.j = str;
        this.l = Constants.HTTP_GET;
        this.o = 60000;
        this.p = 300000;
        this.q = false;
        this.e = new HashMap();
    }

    public final void run() {
        new StringBuilder().append(this).append("-run");
        try {
            this.r = Thread.currentThread();
            a();
        } catch (InterruptedException e) {
        }
    }

    public final void cancel() {
        super.cancel();
        this.c = null;
        this.d = null;
        this.a = null;
        this.b = null;
        if (this.r != null && !this.r.isInterrupted()) {
            this.r.interrupt();
            this.r = null;
        }
    }

    public final void a(String str, String str2, String str3, HashMap<String, String> hashMap, List<byte[]> list, boolean z) {
        this.l = str;
        this.m = str2;
        this.n = str3;
        this.e = hashMap;
        this.k = list;
        this.f = z;
    }

    public final void a(int i, int i2) {
        if (i > 0) {
            this.o = i;
        }
        if (i2 > 0) {
            this.p = i2;
        }
    }

    private void a() throws InterruptedException {
        OutputStream outputStream;
        if (!this.q) {
            if (b()) {
                byte[] bArr;
                if (this.l.equals(Constants.HTTP_POST)) {
                    try {
                        OutputStream outputStream2 = this.h.getOutputStream();
                        try {
                            if (this.k != null) {
                                for (byte[] bArr2 : this.k) {
                                    outputStream2.write(bArr2);
                                    outputStream2.flush();
                                }
                            } else if (!TextUtils.isEmpty(this.m)) {
                                outputStream2.write(this.m.getBytes());
                            }
                            if (this.n != null) {
                                RandomAccessFile randomAccessFile = new RandomAccessFile(this.n, "rw");
                                byte[] bArr3 = new byte[4096];
                                for (int read = randomAccessFile.read(bArr3); -1 != read; read = randomAccessFile.read(bArr3)) {
                                    outputStream2.write(bArr3, 0, read);
                                }
                                randomAccessFile.close();
                            }
                            outputStream2.flush();
                            outputStream2.close();
                        } catch (IOException e) {
                            outputStream = outputStream2;
                            a(null, outputStream);
                            if (this.a == null && !this.q) {
                                this.a.a();
                            }
                        }
                    } catch (IOException e2) {
                        outputStream = null;
                        a(null, outputStream);
                        if (this.a == null) {
                        }
                    }
                }
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
                InputStream inputStream;
                try {
                    int responseCode = this.h.getResponseCode();
                    Map headerFields = this.h.getHeaderFields();
                    if (this.d != null) {
                        this.d.a(responseCode, headerFields, this);
                    }
                    if (Thread.interrupted()) {
                        bArr2 = null;
                        inputStream = null;
                    } else {
                        if (HttpConstant.GZIP.equalsIgnoreCase(this.h.getHeaderField(HttpConstant.CONTENT_ENCODING))) {
                            inputStream = new GZIPInputStream(this.h.getInputStream());
                        } else {
                            inputStream = this.h.getInputStream();
                        }
                        try {
                            new StringBuilder("contentLength = ").append(this.h.getContentLength()).append(" , Accept-Encoding = ").append(this.h.getHeaderField(HttpConstant.ACCEPT_ENCODING));
                            bArr2 = new byte[16384];
                            while (!Thread.interrupted()) {
                                int read2 = inputStream.read(bArr2);
                                if (-1 == read2) {
                                    break;
                                }
                                if (this.c != null) {
                                    this.c.a(bArr2, read2, this);
                                } else {
                                    byteArrayOutputStream.write(bArr2, 0, read2);
                                }
                                if (this.g == 0) {
                                    Thread.sleep(com.taobao.accs.common.Constants.ST_UPLOAD_MAX_COUNT);
                                }
                            }
                            bArr2 = byteArrayOutputStream.toByteArray();
                            byteArrayOutputStream.close();
                        } catch (Exception e3) {
                        }
                    }
                    a(inputStream, null);
                    if (this.b != null && !this.q) {
                        this.b.a(responseCode, headerFields, bArr2);
                    }
                } catch (Exception e4) {
                    inputStream = null;
                    a(inputStream, byteArrayOutputStream);
                    if (this.a != null && !this.q) {
                        this.a.a();
                    }
                }
            } else if (this.a != null && !this.q) {
                this.a.a();
            }
        }
    }

    private boolean b() {
        try {
            this.h = (HttpURLConnection) new URL(this.j).openConnection();
            this.h.setDoInput(true);
            this.h.setUseCaches(false);
            if (this.e != null) {
                for (String str : this.e.keySet()) {
                    try {
                        this.h.setRequestProperty(str, (String) this.e.get(str));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            this.h.setConnectTimeout(this.o);
            this.h.setReadTimeout(this.p);
            this.h.setRequestMethod(this.l);
            if (this.l.equals(Constants.HTTP_POST)) {
                this.h.setDoOutput(true);
            }
            return true;
        } catch (MalformedURLException e2) {
            c();
            return false;
        } catch (IOException e3) {
            c();
            return false;
        } catch (SecurityException e4) {
            c();
            return false;
        }
    }

    private void c() {
        if (this.h != null) {
            this.h.disconnect();
            this.h = null;
        }
    }

    private static void a(InputStream inputStream, OutputStream outputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception e) {
            }
        }
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Exception e2) {
            }
        }
    }
}
