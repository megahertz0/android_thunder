package com.xunlei.common.httpclient.request;

import com.umeng.message.util.HttpRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;

// compiled from: SimpleMultipartEntity.java
final class e implements HttpEntity {
    private static final char[] a;
    private String b;
    private ByteArrayOutputStream c;
    private boolean d;
    private boolean e;

    static {
        a = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    }

    public e() {
        int i = 0;
        this.b = null;
        this.c = new ByteArrayOutputStream();
        this.d = false;
        this.e = false;
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        while (i < 30) {
            stringBuffer.append(a[random.nextInt(a.length)]);
            i++;
        }
        this.b = stringBuffer.toString();
    }

    private void a() {
        if (!this.e) {
            b();
        }
        this.e = true;
    }

    private void b() {
        try {
            this.c.write(new StringBuilder("--").append(this.b).append("\r\n").toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void c() {
        if (!this.d) {
            try {
                this.c.write(new StringBuilder("--").append(this.b).append("--\r\n").toString().getBytes());
                this.c.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.d = true;
        }
    }

    private void a(String str, String str2, String str3) {
        b();
        try {
            this.c.write(new StringBuilder("Content-Disposition: form-data; name=\"").append(str).append("\"\r\n").toString().getBytes());
            this.c.write(new StringBuilder("Content-Type: ").append(str3).append("\r\n\r\n").toString().getBytes());
            this.c.write(str2.getBytes());
            this.c.write("\r\n".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final void a(String str, String str2) {
        String str3 = "text/plain; charset=UTF-8";
        b();
        try {
            this.c.write(new StringBuilder("Content-Disposition: form-data; name=\"").append(str).append("\"\r\n").toString().getBytes());
            this.c.write(new StringBuilder("Content-Type: ").append(str3).append("\r\n\r\n").toString().getBytes());
            this.c.write(str2.getBytes());
            this.c.write("\r\n".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final void a(String str, String str2, InputStream inputStream, boolean z) {
        a(str, str2, inputStream, "application/octet-stream");
    }

    public final void a(String str, String str2, InputStream inputStream, String str3) {
        b();
        try {
            String toString = new StringBuilder("Content-Type: ").append(str3).append("\r\n").toString();
            this.c.write(new StringBuilder("Content-Disposition: form-data; name=\"").append(str).append("\"; filename=\"").append(str2).append("\"\r\n").toString().getBytes());
            this.c.write(toString.getBytes());
            this.c.write("Content-Transfer-Encoding: binary\r\n\r\n".getBytes());
            byte[] bArr = new byte[4096];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    this.c.write(bArr, 0, read);
                } else {
                    this.c.write("\r\n".getBytes());
                    try {
                        inputStream.close();
                        return;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e2) {
            try {
                e2.printStackTrace();
                try {
                    inputStream.close();
                } catch (IOException e22) {
                    e22.printStackTrace();
                }
            } catch (Throwable th) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    private void a(String str, File file, boolean z) {
        try {
            a(str, file.getName(), new FileInputStream(file), z);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public final long getContentLength() {
        c();
        return (long) this.c.toByteArray().length;
    }

    public final Header getContentType() {
        return new BasicHeader(HttpRequest.l, new StringBuilder("multipart/form-data; boundary=").append(this.b).toString());
    }

    public final boolean isChunked() {
        return false;
    }

    public final boolean isRepeatable() {
        return false;
    }

    public final boolean isStreaming() {
        return false;
    }

    public final void writeTo(OutputStream outputStream) throws IOException {
        c();
        outputStream.write(this.c.toByteArray());
    }

    public final Header getContentEncoding() {
        return null;
    }

    public final void consumeContent() throws IOException, UnsupportedOperationException {
        if (isStreaming()) {
            throw new UnsupportedOperationException("Streaming entity does not implement #consumeContent()");
        }
    }

    public final InputStream getContent() throws IOException, UnsupportedOperationException {
        c();
        return new ByteArrayInputStream(this.c.toByteArray());
    }
}
