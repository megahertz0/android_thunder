package com.a.a.b;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.message.BasicHeader;

// compiled from: MultipartEntity.java
public final class a implements HttpEntity {
    private static final char[] e;
    String a;
    ByteArrayOutputStream b;
    boolean c;
    boolean d;

    static {
        e = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    }

    public a() {
        int i = 0;
        this.a = null;
        this.b = new ByteArrayOutputStream();
        this.c = false;
        this.d = false;
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        while (i < 30) {
            stringBuffer.append(e[random.nextInt(e.length)]);
            i++;
        }
        this.a = stringBuffer.toString();
    }

    public final void a() {
        if (!this.d) {
            try {
                this.b.write(new StringBuilder("--").append(this.a).append("\r\n").toString().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.d = true;
    }

    private void b() {
        if (!this.c) {
            try {
                this.b.write(new StringBuilder("\r\n--").append(this.a).append("--\r\n").toString().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.c = true;
        }
    }

    public final void a(String str, String str2, InputStream inputStream, boolean z) {
        a(str, str2, inputStream, "application/octet-stream", z);
    }

    public final void a(String str, String str2, InputStream inputStream, String str3, boolean z) {
        a();
        try {
            String toString = new StringBuilder("Content-Type: ").append(str3).append("\r\n").toString();
            this.b.write(new StringBuilder("Content-Disposition: form-data; name=\"").append(str).append("\"; filename=\"").append(str2).append("\"\r\n").toString().getBytes());
            this.b.write(toString.getBytes());
            this.b.write("Content-Transfer-Encoding: binary\r\n\r\n".getBytes());
            byte[] bArr = new byte[4096];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                this.b.write(bArr, 0, read);
            }
            if (z) {
                b();
            } else {
                this.b.write(new StringBuilder("\r\n--").append(this.a).append("\r\n").toString().getBytes());
            }
            this.b.flush();
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
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

    public final long getContentLength() {
        b();
        return (long) this.b.toByteArray().length;
    }

    public final Header getContentType() {
        return new BasicHeader("Content-Type", new StringBuilder("multipart/form-data; boundary=").append(this.a).toString());
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
        outputStream.write(this.b.toByteArray());
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
        return new ByteArrayInputStream(this.b.toByteArray());
    }
}
