package com.qq.e.comm.net.rr;

import anet.channel.util.HttpConstant;
import com.sina.weibo.sdk.component.GameManager;
import com.umeng.a;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;

public abstract class AbstractResponse implements Response {
    private final HttpResponse a;
    private final HttpUriRequest b;

    public AbstractResponse(HttpResponse httpResponse, HttpUriRequest httpUriRequest) {
        this.a = httpResponse;
        this.b = httpUriRequest;
    }

    public void close() throws IllegalStateException, IOException {
        if (!(this.b == null || this.b.isAborted())) {
            this.b.abort();
        }
        if (this.a != null) {
            this.a.getEntity().getContent().close();
        }
    }

    public byte[] getBytesContent() throws IllegalStateException, IOException {
        if (200 != getStatusCode()) {
            return null;
        }
        InputStream streamContent = getStreamContent();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = streamContent.read(bArr);
            if (read <= 0) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    public int getStatusCode() {
        return this.a.getStatusLine().getStatusCode();
    }

    public InputStream getStreamContent() throws IllegalStateException, IOException {
        InputStream content = this.a.getEntity().getContent();
        Header contentEncoding = this.a.getEntity().getContentEncoding();
        return (contentEncoding == null || !contentEncoding.getValue().contains(HttpConstant.GZIP)) ? content : new GZIPInputStream(content);
    }

    public String getStringContent() throws IOException {
        return getStringContent(GameManager.DEFAULT_CHARSET);
    }

    public String getStringContent(String str) throws IOException {
        String str2 = null;
        byte[] bytesContent = getBytesContent();
        if (bytesContent == null) {
            return null;
        }
        if (bytesContent.length == 0) {
            return a.d;
        }
        try {
            str2 = EntityUtils.getContentCharSet(this.a.getEntity());
        } catch (Throwable th) {
        }
        if (str2 != null) {
            str = str2;
        }
        return new String(bytesContent, str);
    }
}
