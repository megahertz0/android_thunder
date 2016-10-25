package com.android.volley.toolbox;

import com.alipay.sdk.cons.b;
import com.android.volley.Request;
import com.tencent.connect.common.Constants;
import com.xunlei.tdlive.R;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.android.spdy.SpdyAgent;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

// compiled from: HurlStack.java
public final class h implements g {
    private final a a;
    private final SSLSocketFactory b;

    // compiled from: HurlStack.java
    public static interface a {
        String a();
    }

    public h() {
        this((byte) 0);
    }

    private h(byte b) {
        this('\u0000');
    }

    private h(char c) {
        this.a = null;
        this.b = null;
    }

    public final HttpResponse a(Request<?> request, Map<String, String> map) throws IOException, com.android.volley.a {
        String a;
        String url = request.getUrl();
        HashMap hashMap = new HashMap();
        hashMap.putAll(request.getHeaders());
        hashMap.putAll(map);
        if (this.a != null) {
            a = this.a.a();
            if (a == null) {
                throw new IOException(new StringBuilder("URL blocked by rewriter: ").append(url).toString());
            }
        }
        a = url;
        URL url2 = new URL(a);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url2.openConnection();
        httpURLConnection.setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
        int timeoutMs = request.getTimeoutMs();
        httpURLConnection.setConnectTimeout(timeoutMs);
        httpURLConnection.setReadTimeout(timeoutMs);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setDoInput(true);
        if (b.a.equals(url2.getProtocol()) && this.b != null) {
            ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(this.b);
        }
        for (String str : hashMap.keySet()) {
            httpURLConnection.addRequestProperty(str, (String) hashMap.get(str));
        }
        switch (request.getMethod()) {
            case SniffingResourceGroup.PAGETYPE_NONE:
                byte[] postBody = request.getPostBody();
                if (postBody != null) {
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod(Constants.HTTP_POST);
                    httpURLConnection.addRequestProperty("Content-Type", request.getPostBodyContentType());
                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.write(postBody);
                    dataOutputStream.close();
                }
                break;
            case SpdyAgent.ACCS_TEST_SERVER:
                httpURLConnection.setRequestMethod(Constants.HTTP_GET);
                break;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                httpURLConnection.setRequestMethod(Constants.HTTP_POST);
                a(httpURLConnection, (Request) request);
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                httpURLConnection.setRequestMethod("PUT");
                a(httpURLConnection, (Request) request);
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                httpURLConnection.setRequestMethod("DELETE");
                break;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                httpURLConnection.setRequestMethod("HEAD");
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                httpURLConnection.setRequestMethod("OPTIONS");
                break;
            case R.styleable.Toolbar_contentInsetEnd:
                httpURLConnection.setRequestMethod("TRACE");
                break;
            case R.styleable.Toolbar_contentInsetLeft:
                httpURLConnection.setRequestMethod("PATCH");
                a(httpURLConnection, (Request) request);
                break;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
        ProtocolVersion protocolVersion = new ProtocolVersion("HTTP", 1, 1);
        if (httpURLConnection.getResponseCode() == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        boolean z;
        StatusLine basicStatusLine = new BasicStatusLine(protocolVersion, httpURLConnection.getResponseCode(), httpURLConnection.getResponseMessage());
        HttpResponse basicHttpResponse = new BasicHttpResponse(basicStatusLine);
        int method = request.getMethod();
        timeoutMs = basicStatusLine.getStatusCode();
        if (method != 4) {
            if (!((100 <= timeoutMs && timeoutMs < 200) || timeoutMs == 204 || timeoutMs == 304)) {
                z = true;
                if (z) {
                    basicHttpResponse.setEntity(a(httpURLConnection));
                }
                for (Entry entry : httpURLConnection.getHeaderFields().entrySet()) {
                    if (entry.getKey() != null) {
                        basicHttpResponse.addHeader(new BasicHeader((String) entry.getKey(), (String) ((List) entry.getValue()).get(0)));
                    }
                }
                return basicHttpResponse;
            }
        }
        z = false;
        if (z) {
            basicHttpResponse.setEntity(a(httpURLConnection));
        }
        for (Entry entry2 : httpURLConnection.getHeaderFields().entrySet()) {
            if (entry2.getKey() != null) {
                basicHttpResponse.addHeader(new BasicHeader((String) entry2.getKey(), (String) ((List) entry2.getValue()).get(0)));
            }
        }
        return basicHttpResponse;
    }

    private static HttpEntity a(HttpURLConnection httpURLConnection) {
        InputStream inputStream;
        HttpEntity basicHttpEntity = new BasicHttpEntity();
        try {
            inputStream = httpURLConnection.getInputStream();
        } catch (IOException e) {
            inputStream = httpURLConnection.getErrorStream();
        }
        basicHttpEntity.setContent(inputStream);
        basicHttpEntity.setContentLength((long) httpURLConnection.getContentLength());
        basicHttpEntity.setContentEncoding(httpURLConnection.getContentEncoding());
        basicHttpEntity.setContentType(httpURLConnection.getContentType());
        return basicHttpEntity;
    }

    private static void a(HttpURLConnection httpURLConnection, Request<?> request) throws IOException, com.android.volley.a {
        byte[] body = request.getBody();
        if (body != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("Content-Type", request.getBodyContentType());
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(body);
            dataOutputStream.close();
        }
    }
}
