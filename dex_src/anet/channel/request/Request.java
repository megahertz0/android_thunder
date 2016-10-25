package anet.channel.request;

import android.text.TextUtils;
import anet.channel.request.Request.Method;
import anet.channel.statist.RequestStatistic;
import anet.channel.util.StringUtils;
import com.tencent.connect.common.Constants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

// compiled from: Taobao
public class Request {
    public static final String DEFAULT_CHARSET = "UTF-8";
    private String bizId;
    private BodyEntry body;
    private String charset;
    private int connectTimeout;
    private String f_refer;
    private Map<String, String> headers;
    private String host;
    private boolean isHostnameVerifyEnable;
    private boolean isRedirectEnable;
    private Method method;
    private Map<String, String> params;
    private int readTimeout;
    private int redirectTimes;
    public final RequestStatistic rs;
    private String seq;
    private URL url;
    private String urlString;

    // compiled from: Taobao
    public static class Builder {
        private String bizId;
        private BodyEntry body;
        private String charset;
        private int connectTimeout;
        private String f_refer;
        private Map<String, String> headers;
        private boolean isHostnameVerifyEnable;
        private boolean isRedirectEnable;
        private Method method;
        private Map<String, String> params;
        private int readTimeout;
        private RequestStatistic rs;
        private String seq;
        private String url;

        public Builder() {
            this.method = Method.GET;
            this.isRedirectEnable = true;
            this.isHostnameVerifyEnable = true;
            this.connectTimeout = 0;
            this.readTimeout = 0;
            this.rs = null;
        }

        public anet.channel.request.Request.Builder setUrl(String str) {
            this.url = str;
            return this;
        }

        public anet.channel.request.Request.Builder setMethod(Method method) {
            this.method = method;
            return this;
        }

        public anet.channel.request.Request.Builder setHeaders(Map<String, String> map) {
            this.headers = map;
            return this;
        }

        public anet.channel.request.Request.Builder addHeader(String str, String str2) {
            if (this.headers == null) {
                this.headers = new HashMap();
            }
            this.headers.put(str, str2);
            return this;
        }

        public anet.channel.request.Request.Builder setParams(Map<String, String> map) {
            this.params = map;
            return this;
        }

        public anet.channel.request.Request.Builder addParam(String str, String str2) {
            if (this.params == null) {
                this.params = new HashMap();
            }
            this.params.put(str, str2);
            return this;
        }

        public anet.channel.request.Request.Builder setCharset(String str) {
            this.charset = str;
            return this;
        }

        public anet.channel.request.Request.Builder setBody(BodyEntry bodyEntry) {
            this.body = bodyEntry;
            return this;
        }

        public anet.channel.request.Request.Builder setRedirectEnable(boolean z) {
            this.isRedirectEnable = z;
            return this;
        }

        public anet.channel.request.Request.Builder setHostnameVerifyEnable(boolean z) {
            this.isHostnameVerifyEnable = z;
            return this;
        }

        public anet.channel.request.Request.Builder setBizId(String str) {
            this.bizId = str;
            return this;
        }

        public anet.channel.request.Request.Builder setSeq(String str) {
            this.seq = str;
            return this;
        }

        public anet.channel.request.Request.Builder setReadTimeout(int i) {
            this.readTimeout = i;
            return this;
        }

        public anet.channel.request.Request.Builder setConnectTimeout(int i) {
            this.connectTimeout = i;
            return this;
        }

        public anet.channel.request.Request.Builder setRequestStatistic(RequestStatistic requestStatistic) {
            this.rs = requestStatistic;
            return this;
        }

        public Request build() {
            return new Request();
        }
    }

    // compiled from: Taobao
    public enum Method {
        GET(Constants.HTTP_GET),
        POST(Constants.HTTP_POST);
        private String httpMethod;

        private Method(String str) {
            this.httpMethod = str;
        }

        public final String toString() {
            return this.httpMethod;
        }
    }

    private Request(Builder builder) {
        this.method = Method.GET;
        this.isRedirectEnable = true;
        this.isHostnameVerifyEnable = true;
        this.redirectTimes = 0;
        this.connectTimeout = 10000;
        this.readTimeout = 10000;
        this.method = builder.method;
        this.headers = builder.headers;
        this.params = builder.params;
        this.body = builder.body;
        this.charset = builder.charset;
        this.isRedirectEnable = builder.isRedirectEnable;
        this.isHostnameVerifyEnable = builder.isHostnameVerifyEnable;
        this.urlString = builder.url;
        this.bizId = builder.bizId;
        this.seq = builder.seq;
        this.connectTimeout = builder.connectTimeout;
        this.readTimeout = builder.readTimeout;
        this.rs = builder.rs != null ? builder.rs : new RequestStatistic(getHost(), this.bizId);
        this.f_refer = builder.f_refer;
        formatUrl();
    }

    public String getUrlString() {
        return this.urlString;
    }

    public URL getUrl() {
        try {
            if (this.url == null) {
                this.url = new URL(this.urlString);
            }
        } catch (MalformedURLException e) {
        }
        return this.url;
    }

    public void setDnsOptimize(String str, int i) {
        if (i != 0 && str != null) {
            this.urlString = this.urlString.replaceFirst(getHost(), StringUtils.buildString(str, ":", String.valueOf(i)));
            this.rs.setIPAndPort(str, i);
        }
    }

    public boolean isRedirectAllow() {
        return this.redirectTimes < 10;
    }

    public void redirectToUrl(String str) {
        this.urlString = str;
        this.url = null;
        this.host = null;
        this.redirectTimes++;
    }

    public String getHost() {
        if (this.host == null) {
            String[] parseURL = StringUtils.parseURL(this.urlString);
            if (parseURL != null) {
                this.host = parseURL[1];
            }
        }
        return this.host;
    }

    public Method getMethod() {
        return this.method;
    }

    public Map<String, String> getHeaders() {
        if (this.headers == null) {
            this.headers = new HashMap();
        }
        return this.headers;
    }

    public void addHeader(String str, String str2) {
        if (this.headers == null) {
            this.headers = new HashMap();
        }
        this.headers.put(str, str2);
    }

    public String getfReferParam() {
        return this.f_refer;
    }

    public String getContentEncoding() {
        return this.charset != null ? this.charset : DEFAULT_CHARSET;
    }

    public boolean isRedirectEnable() {
        return this.isRedirectEnable;
    }

    public boolean isHostnameVerifyEnable() {
        return this.isHostnameVerifyEnable;
    }

    public int postBody(OutputStream outputStream) throws IOException {
        return this.body != null ? this.body.writeTo(outputStream) : 0;
    }

    public byte[] getBody() {
        if (this.body == null) {
            return null;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream(128);
        try {
            postBody(byteArrayOutputStream);
        } catch (IOException e) {
        }
        return byteArrayOutputStream.toByteArray();
    }

    public String getBizId() {
        return this.bizId;
    }

    public String getSeq() {
        return this.seq;
    }

    public int getReadTimeout() {
        return this.readTimeout;
    }

    public int getConnectTimeout() {
        return this.connectTimeout;
    }

    private String formatUrl() {
        String encodeQueryParams = StringUtils.encodeQueryParams(this.params, getContentEncoding());
        if (!TextUtils.isEmpty(encodeQueryParams)) {
            if (this.method == Method.GET || (this.method == Method.POST && this.body != null)) {
                StringBuilder stringBuilder = new StringBuilder(this.urlString);
                if (stringBuilder.indexOf("?") == -1) {
                    stringBuilder.append('?');
                } else if (this.urlString.charAt(this.urlString.length() - 1) != '&') {
                    stringBuilder.append('&');
                }
                stringBuilder.append(encodeQueryParams);
                this.urlString = stringBuilder.toString();
            } else {
                try {
                    this.body = new ByteArrayEntry(encodeQueryParams.getBytes(getContentEncoding()));
                    getHeaders().put("Content-Type", new StringBuilder("application/x-www-form-urlencoded; charset=").append(getContentEncoding()).toString());
                } catch (UnsupportedEncodingException e) {
                }
            }
        }
        return this.urlString;
    }
}
