package com.xunlei.common.httpclient;

import android.content.Context;
import anet.channel.util.HttpConstant;
import com.alipay.sdk.cons.b;
import com.xunlei.common.httpclient.handler.HttpResponseHandler;
import com.xunlei.common.httpclient.request.c;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.SyncBasicHttpContext;

public class AsyncHttpClient implements BaseHttpClient {
    private static final int DEFAULT_MAX_CONNECTIONS = 10;
    private static final int DEFAULT_MAX_RETRIES = 5;
    private static final int DEFAULT_SOCKET_BUFFER_SIZE = 8192;
    private static final int DEFAULT_SOCKET_TIMEOUT = 30000;
    private static final String ENCODING_GZIP = "gzip";
    private static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
    private static final String VERSION = "1.4.3";
    private static int maxConnections;
    private static int socketTimeout;
    private Map<String, String> clientHeaderMap;
    private DefaultHttpClient httpClient;
    private HttpContext httpContext;
    private String mAccSDKVersion;
    private Map<Context, List<WeakReference<Future<?>>>> requestMap;
    private ThreadPoolExecutor threadPool;

    final class AnonymousClass_3 extends HttpResponseHandler {
        private /* synthetic */ BaseHttpClientListener a;
        private /* synthetic */ AsyncHttpClient b;

        AnonymousClass_3(AsyncHttpClient asyncHttpClient, BaseHttpClientListener baseHttpClientListener) {
            this.a = baseHttpClientListener;
        }

        public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
            this.a.onSuccess(i, headerArr, bArr);
        }

        public final void onFailure(Throwable th, byte[] bArr) {
            this.a.onFailure(th, bArr);
        }
    }

    final class AnonymousClass_4 extends HttpResponseHandler {
        private /* synthetic */ BaseHttpClientListener a;
        private /* synthetic */ AsyncHttpClient b;

        AnonymousClass_4(AsyncHttpClient asyncHttpClient, BaseHttpClientListener baseHttpClientListener) {
            this.a = baseHttpClientListener;
        }

        public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
            this.a.onSuccess(i, headerArr, bArr);
        }

        public final void onFailure(Throwable th, byte[] bArr) {
            this.a.onFailure(th, bArr);
        }
    }

    static class a extends HttpEntityWrapper {
        public a(HttpEntity httpEntity) {
            super(httpEntity);
        }

        public final InputStream getContent() throws IOException {
            return new GZIPInputStream(this.wrappedEntity.getContent());
        }

        public final long getContentLength() {
            return -1;
        }
    }

    static {
        maxConnections = 10;
        socketTimeout = 30000;
    }

    public AsyncHttpClient(String str) {
        this.mAccSDKVersion = "1.0.0.1";
        this.mAccSDKVersion = str;
        initHttpParam();
    }

    public AsyncHttpClient() {
        this.mAccSDKVersion = "1.0.0.1";
        initHttpParam();
    }

    private void initHttpParam() {
        HttpParams basicHttpParams = new BasicHttpParams();
        ConnManagerParams.setTimeout(basicHttpParams, (long) socketTimeout);
        ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(maxConnections));
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, DEFAULT_MAX_CONNECTIONS);
        HttpConnectionParams.setSoTimeout(basicHttpParams, socketTimeout);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, socketTimeout);
        HttpConnectionParams.setTcpNoDelay(basicHttpParams, true);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, DEFAULT_SOCKET_BUFFER_SIZE);
        HttpProtocolParams.setUseExpectContinue(basicHttpParams, false);
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUserAgent(basicHttpParams, String.format(new StringBuilder("android-async-http/xl-acc-sdk/version-").append(this.mAccSDKVersion).toString(), new Object[]{VERSION}));
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme(HttpConstant.HTTP, PlainSocketFactory.getSocketFactory(), 80));
        ClientConnectionManager threadSafeClientConnManager = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
        this.httpContext = new SyncBasicHttpContext(new BasicHttpContext());
        this.httpClient = new DefaultHttpClient(threadSafeClientConnManager, basicHttpParams);
        this.httpClient.addRequestInterceptor(new HttpRequestInterceptor() {
            public final void process(HttpRequest httpRequest, HttpContext httpContext) {
                if (!httpRequest.containsHeader(HEADER_ACCEPT_ENCODING)) {
                    httpRequest.addHeader(HEADER_ACCEPT_ENCODING, ENCODING_GZIP);
                }
                for (String str : AsyncHttpClient.this.clientHeaderMap.keySet()) {
                    httpRequest.addHeader(str, (String) AsyncHttpClient.this.clientHeaderMap.get(str));
                }
            }
        });
        this.httpClient.addResponseInterceptor(new HttpResponseInterceptor() {
            private /* synthetic */ AsyncHttpClient a;

            public final void process(HttpResponse httpResponse, HttpContext httpContext) {
                HttpEntity entity = httpResponse.getEntity();
                if (entity != null) {
                    Header contentEncoding = entity.getContentEncoding();
                    if (contentEncoding != null) {
                        for (HeaderElement headerElement : contentEncoding.getElements()) {
                            if (headerElement.getName().equalsIgnoreCase(ENCODING_GZIP)) {
                                httpResponse.setEntity(new a(httpResponse.getEntity()));
                                return;
                            }
                        }
                    }
                }
            }
        });
        this.httpClient.setHttpRequestRetryHandler(new c(5));
        this.threadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        this.requestMap = new WeakHashMap();
        this.clientHeaderMap = new HashMap();
    }

    public HttpClient getHttpClient() {
        return this.httpClient;
    }

    public HttpContext getHttpContext() {
        return this.httpContext;
    }

    public void setCookieStore(CookieStore cookieStore) {
        this.httpContext.setAttribute("http.cookie-store", cookieStore);
    }

    public void setThreadPool(ThreadPoolExecutor threadPoolExecutor) {
        this.threadPool = threadPoolExecutor;
    }

    public void setUserAgent(String str) {
        HttpProtocolParams.setUserAgent(this.httpClient.getParams(), str);
    }

    public void setTimeout(int i) {
        HttpParams params = this.httpClient.getParams();
        ConnManagerParams.setTimeout(params, (long) i);
        HttpConnectionParams.setSoTimeout(params, i);
        HttpConnectionParams.setConnectionTimeout(params, i);
    }

    public void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme(b.a, sSLSocketFactory, 443));
    }

    public void addHeader(String str, String str2) {
        this.clientHeaderMap.put(str, str2);
    }

    public void setBasicAuth(String str, String str2) {
        setBasicAuth(str, str2, AuthScope.ANY);
    }

    public void setBasicAuth(String str, String str2, AuthScope authScope) {
        this.httpClient.getCredentialsProvider().setCredentials(authScope, new UsernamePasswordCredentials(str, str2));
    }

    public void cancelRequests(Context context, boolean z) {
        List<WeakReference> list = (List) this.requestMap.get(context);
        if (list != null) {
            for (WeakReference weakReference : list) {
                Future future = (Future) weakReference.get();
                if (future != null) {
                    future.cancel(z);
                }
            }
        }
        this.requestMap.remove(context);
    }

    public void get(String str, HttpResponseHandler httpResponseHandler) {
        get(null, str, null, httpResponseHandler);
    }

    public void get(String str, com.xunlei.common.httpclient.request.b bVar, HttpResponseHandler httpResponseHandler) {
        get(null, str, bVar, httpResponseHandler);
    }

    public void get(Context context, String str, HttpResponseHandler httpResponseHandler) {
        get(context, str, null, httpResponseHandler);
    }

    public void get(Context context, String str, com.xunlei.common.httpclient.request.b bVar, HttpResponseHandler httpResponseHandler) {
        try {
            sendRequest(this.httpClient, this.httpContext, new HttpGet(getUrlWithQueryString(str, bVar)), null, httpResponseHandler, context);
        } catch (Throwable e) {
            e.printStackTrace();
            httpResponseHandler.sendFailureMessage(e, com.umeng.a.d.getBytes());
        }
    }

    public void get(Context context, String str, Header[] headerArr, com.xunlei.common.httpclient.request.b bVar, HttpResponseHandler httpResponseHandler) {
        try {
            HttpUriRequest httpGet = new HttpGet(getUrlWithQueryString(str, bVar));
            if (headerArr != null) {
                httpGet.setHeaders(headerArr);
            }
            sendRequest(this.httpClient, this.httpContext, httpGet, null, httpResponseHandler, context);
        } catch (Throwable e) {
            e.printStackTrace();
            httpResponseHandler.sendFailureMessage(e, com.umeng.a.d.getBytes());
        }
    }

    public void post(String str, HttpResponseHandler httpResponseHandler) {
        post(null, str, null, httpResponseHandler);
    }

    public void post(String str, com.xunlei.common.httpclient.request.b bVar, HttpResponseHandler httpResponseHandler) {
        post(null, str, bVar, httpResponseHandler);
    }

    public void post(Context context, String str, com.xunlei.common.httpclient.request.b bVar, HttpResponseHandler httpResponseHandler) {
        post(context, str, paramsToEntity(bVar), null, httpResponseHandler);
    }

    public void post(Context context, String str, HttpEntity httpEntity, String str2, HttpResponseHandler httpResponseHandler) {
        HttpPost httpPost = (HttpPost) newHttpMethod("Post", str, null);
        if (httpPost == null) {
            httpResponseHandler.sendFailureMessage(new IllegalArgumentException("the url is invlid."), "the url is invlid.".getBytes());
            return;
        }
        sendRequest(this.httpClient, this.httpContext, addEntityToRequestBase(httpPost, httpEntity), str2, httpResponseHandler, context);
    }

    public void post(Context context, String str, Header[] headerArr, com.xunlei.common.httpclient.request.b bVar, String str2, HttpResponseHandler httpResponseHandler) {
        HttpPost httpPost = (HttpPost) newHttpMethod("Post", str, bVar);
        if (httpPost == null) {
            httpResponseHandler.sendFailureMessage(new IllegalArgumentException("the url is invlid."), "the url is invlid.".getBytes());
            return;
        }
        if (bVar != null) {
            httpPost.setEntity(paramsToEntity(bVar));
        }
        if (headerArr != null) {
            httpPost.setHeaders(headerArr);
        }
        sendRequest(this.httpClient, this.httpContext, httpPost, str2, httpResponseHandler, context);
    }

    public void post(Context context, String str, Header[] headerArr, HttpEntity httpEntity, String str2, HttpResponseHandler httpResponseHandler) {
        HttpPost httpPost = (HttpPost) newHttpMethod("Post", str, null);
        if (httpPost == null) {
            httpResponseHandler.sendFailureMessage(new IllegalArgumentException("the url is invlid."), "the url is invlid.".getBytes());
            return;
        }
        HttpUriRequest addEntityToRequestBase = addEntityToRequestBase(httpPost, httpEntity);
        if (headerArr != null) {
            addEntityToRequestBase.setHeaders(headerArr);
        }
        sendRequest(this.httpClient, this.httpContext, addEntityToRequestBase, str2, httpResponseHandler, context);
    }

    public void put(String str, HttpResponseHandler httpResponseHandler) {
        put(null, str, null, httpResponseHandler);
    }

    public void put(String str, com.xunlei.common.httpclient.request.b bVar, HttpResponseHandler httpResponseHandler) {
        put(null, str, bVar, httpResponseHandler);
    }

    public void put(Context context, String str, com.xunlei.common.httpclient.request.b bVar, HttpResponseHandler httpResponseHandler) {
        put(context, str, paramsToEntity(bVar), null, httpResponseHandler);
    }

    public void put(Context context, String str, HttpEntity httpEntity, String str2, HttpResponseHandler httpResponseHandler) {
        HttpPut httpPut = (HttpPut) newHttpMethod("Put", str, null);
        if (httpPut == null) {
            httpResponseHandler.sendFailureMessage(new IllegalArgumentException("the url is invlid."), "the url is invlid.".getBytes());
            return;
        }
        sendRequest(this.httpClient, this.httpContext, addEntityToRequestBase(httpPut, httpEntity), str2, httpResponseHandler, context);
    }

    public void put(Context context, String str, Header[] headerArr, HttpEntity httpEntity, String str2, HttpResponseHandler httpResponseHandler) {
        HttpPut httpPut = (HttpPut) newHttpMethod("Put", str, null);
        if (httpPut == null) {
            httpResponseHandler.sendFailureMessage(new IllegalArgumentException("the url is invlid."), "the url is invlid.".getBytes());
            return;
        }
        HttpUriRequest addEntityToRequestBase = addEntityToRequestBase(httpPut, httpEntity);
        if (headerArr != null) {
            addEntityToRequestBase.setHeaders(headerArr);
        }
        sendRequest(this.httpClient, this.httpContext, addEntityToRequestBase, str2, httpResponseHandler, context);
    }

    public void delete(String str, HttpResponseHandler httpResponseHandler) {
        delete(null, str, httpResponseHandler);
    }

    public void delete(Context context, String str, HttpResponseHandler httpResponseHandler) {
        HttpDelete httpDelete = (HttpDelete) newHttpMethod("Delete", str, null);
        if (httpDelete == null) {
            httpResponseHandler.sendFailureMessage(new IllegalArgumentException("the url is invlid."), "the url is invlid.".getBytes());
        } else {
            sendRequest(this.httpClient, this.httpContext, httpDelete, null, httpResponseHandler, context);
        }
    }

    public void delete(Context context, String str, Header[] headerArr, HttpResponseHandler httpResponseHandler) {
        HttpDelete httpDelete = (HttpDelete) newHttpMethod("Delete", str, null);
        if (httpDelete == null) {
            httpResponseHandler.sendFailureMessage(new IllegalArgumentException("the url is invlid."), "the url is invlid.".getBytes());
            return;
        }
        if (headerArr != null) {
            httpDelete.setHeaders(headerArr);
        }
        sendRequest(this.httpClient, this.httpContext, httpDelete, null, httpResponseHandler, context);
    }

    protected void sendRequest(DefaultHttpClient defaultHttpClient, HttpContext httpContext, HttpUriRequest httpUriRequest, String str, HttpResponseHandler httpResponseHandler, Context context) {
        if (str != null) {
            httpUriRequest.addHeader("Content-Type", str);
        }
        Future submit = this.threadPool.submit(new com.xunlei.common.httpclient.request.a(defaultHttpClient, httpContext, httpUriRequest, httpResponseHandler));
        if (context != null) {
            List list = (List) this.requestMap.get(context);
            if (list == null) {
                list = new LinkedList();
                this.requestMap.put(context, list);
            }
            list.add(new WeakReference(submit));
        }
    }

    public static String getUrlWithQueryString(String str, com.xunlei.common.httpclient.request.b bVar) {
        if (bVar == null) {
            return str;
        }
        String b = bVar.b();
        return str.indexOf("?") == -1 ? str + "?" + b : str + com.alipay.sdk.sys.a.b + b;
    }

    private HttpRequest newHttpMethod(String str, String str2, com.xunlei.common.httpclient.request.b bVar) {
        String urlWithQueryString = getUrlWithQueryString(str2, bVar);
        try {
            if ("Get".equalsIgnoreCase(str)) {
                return new HttpGet(urlWithQueryString);
            }
            if ("Post".equalsIgnoreCase(str)) {
                return new HttpPost(urlWithQueryString);
            }
            if ("Put".equalsIgnoreCase(str)) {
                return new HttpPut(urlWithQueryString);
            }
            return "Delete".equalsIgnoreCase(str) ? new HttpDelete(urlWithQueryString) : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private HttpEntity paramsToEntity(com.xunlei.common.httpclient.request.b bVar) {
        return bVar != null ? bVar.a() : null;
    }

    private HttpEntityEnclosingRequestBase addEntityToRequestBase(HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, HttpEntity httpEntity) {
        if (httpEntity != null) {
            httpEntityEnclosingRequestBase.setEntity(httpEntity);
        }
        return httpEntityEnclosingRequestBase;
    }

    public void post(Context context, String str, Header[] headerArr, byte[] bArr, BaseHttpClientListener baseHttpClientListener) {
        HttpEntity byteArrayEntity;
        if (bArr != null) {
            byteArrayEntity = new ByteArrayEntity(bArr);
        } else {
            byteArrayEntity = null;
        }
        post(context, str, headerArr, byteArrayEntity, null, new AnonymousClass_3(this, baseHttpClientListener));
    }

    public void get(Context context, String str, Header[] headerArr, BaseHttpClientListener baseHttpClientListener) {
        get(context, str, headerArr, null, new AnonymousClass_4(this, baseHttpClientListener));
    }

    public void setHttpClientTimeout(int i) {
        setTimeout(i);
    }
}
