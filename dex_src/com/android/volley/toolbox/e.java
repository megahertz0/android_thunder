package com.android.volley.toolbox;

import com.android.volley.Request;
import com.xunlei.tdlive.R;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import org.android.spdy.SpdyAgent;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

// compiled from: HttpClientStack.java
public final class e implements g {
    protected final HttpClient a;

    // compiled from: HttpClientStack.java
    public static final class a extends HttpEntityEnclosingRequestBase {
        public a(String str) {
            setURI(URI.create(str));
        }

        public final String getMethod() {
            return "PATCH";
        }
    }

    public e(HttpClient httpClient) {
        this.a = httpClient;
    }

    private static void a(HttpUriRequest httpUriRequest, Map<String, String> map) {
        for (String str : map.keySet()) {
            httpUriRequest.setHeader(str, (String) map.get(str));
        }
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
            case XZBDevice.DOWNLOAD_LIST_ALL:
                httpPost = new HttpHead(request.getUrl());
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                httpPost = new HttpOptions(request.getUrl());
                break;
            case R.styleable.Toolbar_contentInsetEnd:
                httpPost = new HttpTrace(request.getUrl());
                break;
            case R.styleable.Toolbar_contentInsetLeft:
                httpPost2 = new a(request.getUrl());
                httpPost2.addHeader("Content-Type", request.getBodyContentType());
                a(httpPost2, (Request) request);
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
        return this.a.execute(httpPost);
    }
}
