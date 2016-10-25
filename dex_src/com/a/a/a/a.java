package com.a.a.a;

import com.android.volley.Request;
import com.android.volley.toolbox.g;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.IOException;
import java.util.Map;
import org.android.spdy.SpdyAgent;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

// compiled from: ExtHttpClientStack.java
public final class a implements g {
    protected final HttpClient a;

    public a(HttpClient httpClient) {
        this.a = httpClient;
    }

    private static void a(HttpUriRequest httpUriRequest, Map<String, String> map) {
        for (String str : map.keySet()) {
            httpUriRequest.setHeader(str, (String) map.get(str));
        }
    }

    private static HttpResponse a(HttpResponse httpResponse) throws IllegalStateException, IOException {
        HttpResponse basicHttpResponse = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion(httpResponse.getProtocolVersion().getProtocol(), httpResponse.getProtocolVersion().getMajor(), httpResponse.getProtocolVersion().getMinor()), httpResponse.getStatusLine().getStatusCode(), httpResponse.getStatusLine().getReasonPhrase()));
        HttpEntity entity = httpResponse.getEntity();
        HttpEntity basicHttpEntity = new BasicHttpEntity();
        if (entity != null) {
            basicHttpEntity.setContent(entity.getContent());
            basicHttpEntity.setContentLength(entity.getContentLength());
            Header contentEncoding = entity.getContentEncoding();
            if (contentEncoding != null) {
                basicHttpEntity.setContentEncoding(a(contentEncoding));
            }
            Header contentType = entity.getContentType();
            if (contentType != null) {
                basicHttpEntity.setContentType(a(contentType));
            }
        }
        basicHttpResponse.setEntity(basicHttpEntity);
        for (Header header : httpResponse.getAllHeaders()) {
            basicHttpResponse.addHeader(a(header));
        }
        return basicHttpResponse;
    }

    private static Header a(Header header) {
        return new BasicHeader(header.getName(), header.getValue());
    }

    private static void a(HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, Request<?> request) throws com.android.volley.a {
        byte[] body = request.getBody();
        if (body != null) {
            httpEntityEnclosingRequestBase.setEntity(new ByteArrayEntity(body));
        }
    }

    public final HttpResponse a(Request<?> request, Map<String, String> map) throws IOException, com.android.volley.a {
        HttpUriRequest httpPost;
        HttpEntityEnclosingRequestBase httpPost2;
        switch (request.getMethod()) {
            case SniffingResourceGroup.PAGETYPE_NONE:
                byte[] postBody = request.getPostBody();
                if (postBody != null) {
                    httpPost = new HttpPost(request.getUrl());
                    httpPost.addHeader("Content-Type", request.getPostBodyContentType());
                    httpPost.setEntity(new ByteArrayEntity(postBody));
                } else {
                    httpPost = new HttpGet(request.getUrl());
                }
                break;
            case SpdyAgent.ACCS_TEST_SERVER:
                httpPost = new HttpGet(request.getUrl());
                break;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                httpPost2 = new HttpPost(request.getUrl());
                httpPost2.addHeader("Content-Type", request.getBodyContentType());
                a(httpPost2, (Request) request);
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                httpPost2 = new HttpPut(request.getUrl());
                httpPost2.addHeader("Content-Type", request.getBodyContentType());
                a(httpPost2, (Request) request);
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                httpPost = new HttpDelete(request.getUrl());
                break;
            default:
                throw new IllegalStateException("Unknown request method.");
        }
        a(httpPost, (Map) map);
        a(httpPost, request.getHeaders());
        HttpParams params = httpPost.getParams();
        int timeoutMs = request.getTimeoutMs();
        HttpConnectionParams.setConnectionTimeout(params, 5000);
        HttpConnectionParams.setSoTimeout(params, timeoutMs);
        return a(this.a.execute(httpPost));
    }
}
