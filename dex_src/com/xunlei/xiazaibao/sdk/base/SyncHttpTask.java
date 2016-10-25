package com.xunlei.xiazaibao.sdk.base;

import android.net.Uri;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.android.spdy.SpdyRequest;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;

public abstract class SyncHttpTask {
    private static final int TIME_OUT = 15000;
    private HttpURLConnection httpconn;
    private HttpResponse response;

    public static class HttpHeader implements Header {
        private String m_name;
        private String m_value;

        public HttpHeader(String str, String str2) {
            this.m_name = str;
            this.m_value = str2;
        }

        public String getName() {
            return this.m_name;
        }

        public String getValue() {
            return this.m_value;
        }

        public HeaderElement[] getElements() throws ParseException {
            return null;
        }
    }

    public static class HttpResponse {
        private InputStream bodyStream;
        private byte[] bodybytes;
        private Header[] headers;
        private String msg;
        private int statusCode;

        public HttpResponse() {
            this.statusCode = 0;
            this.msg = BuildConfig.VERSION_NAME;
            this.headers = null;
            this.bodybytes = null;
            this.bodyStream = null;
        }

        public int getStatusCode() {
            return this.statusCode;
        }

        public String getMsg() {
            return this.msg;
        }

        public Header[] getHeaders() {
            return this.headers;
        }

        public String getStringBody() {
            if (this.bodybytes == null) {
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = this.bodyStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    this.bodybytes = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return this.bodybytes != null ? new String(this.bodybytes) : BuildConfig.VERSION_NAME;
        }

        public byte[] getBodybytes() {
            return this.bodybytes;
        }

        public InputStream getStreamBody() {
            return this.bodyStream;
        }
    }

    public abstract Header[] getHeader();

    public abstract String getUrl();

    public SyncHttpTask() {
        this.response = new HttpResponse();
        this.httpconn = null;
    }

    public void writeBody(OutputStream outputStream) {
    }

    public boolean autoRelease() {
        return true;
    }

    public HttpResponse httpGet() {
        try {
            this.httpconn = (HttpURLConnection) new URL(Uri.encode(getUrl(), "@#&=*+-_.,:!?()/~'%")).openConnection();
            this.httpconn.setConnectTimeout(TIME_OUT);
            this.httpconn.setReadTimeout(TIME_OUT);
            Header[] header = getHeader();
            if (header != null) {
                int length = header.length;
                for (int i = 0; i < length; i++) {
                    Header header2 = header[i];
                    this.httpconn.setRequestProperty(header2.getName(), header2.getValue());
                }
            }
            this.response.statusCode = this.httpconn.getResponseCode();
            Map headerFields = this.httpconn.getHeaderFields();
            this.response.headers = new Header[headerFields.size()];
            int i2 = 0;
            for (Entry entry : headerFields.entrySet()) {
                String str = BuildConfig.VERSION_NAME;
                for (String str2 : (List) entry.getValue()) {
                    str = str + str2 + "; ";
                }
                this.response.headers[i2] = new HttpHeader((String) entry.getKey(), str.substring(0, str.length() - 2));
                i2++;
            }
            this.response.msg = this.httpconn.getResponseMessage();
            this.response.bodyStream = this.httpconn.getInputStream();
            if (autoRelease()) {
                this.response.getStringBody();
                release();
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.response.statusCode = -1;
            this.response.msg = e.getMessage();
        }
        return this.response;
    }

    public HttpResponse httpPost() {
        try {
            this.httpconn = (HttpURLConnection) new URL(Uri.encode(getUrl(), "@#&=*+-_.,:!?()/~'%")).openConnection();
            this.httpconn.setDoOutput(true);
            this.httpconn.setRequestMethod(SpdyRequest.POST_METHOD);
            this.httpconn.setConnectTimeout(30000);
            this.httpconn.setReadTimeout(30000);
            this.httpconn.setInstanceFollowRedirects(true);
            Header[] header = getHeader();
            if (header != null) {
                for (Header header2 : header) {
                    this.httpconn.setRequestProperty(header2.getName(), header2.getValue());
                }
            }
            this.httpconn.connect();
            OutputStream outputStream = this.httpconn.getOutputStream();
            writeBody(outputStream);
            outputStream.flush();
            outputStream.close();
            this.response.statusCode = this.httpconn.getResponseCode();
            this.response.msg = this.httpconn.getResponseMessage();
            this.response.bodyStream = this.httpconn.getInputStream();
            if (autoRelease()) {
                this.response.getStringBody();
                release();
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.response.statusCode = -1;
            this.response.msg = e.getMessage();
        }
        return this.response;
    }

    public HttpResponse getResponse() {
        return this.response;
    }

    public void release() {
        try {
            if (this.response.bodyStream != null) {
                this.response.bodyStream.close();
            }
            if (this.httpconn != null) {
                this.httpconn.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
