package com.qq.e.comm.net;

import anet.channel.util.HttpConstant;
import com.qq.e.comm.net.NetworkClient.Priority;
import com.qq.e.comm.net.rr.Request;
import com.qq.e.comm.net.rr.Request.Method;
import com.qq.e.comm.net.rr.Response;
import com.qq.e.comm.util.GDTLogger;
import com.sina.weibo.sdk.component.GameManager;
import com.tencent.bugly.BuglyStrategy.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.android.spdy.SpdyAgent;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public class NetworkClientImpl implements NetworkClient {
    private static final HttpClient a;
    private static final NetworkClient b;
    private final ExecutorService c;
    private PriorityBlockingQueue<Runnable> d;

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[Method.values().length];
            try {
                a[Method.POST.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[Method.GET.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    class NetFutureTask<T> extends FutureTask<T> implements Comparable<NetFutureTask<T>> {
        private final Priority a;

        public NetFutureTask(NetworkClientImpl networkClientImpl, Callable<T> callable, Priority priority) {
            super(callable);
            this.a = priority;
        }

        public int compareTo(NetFutureTask<T> netFutureTask) {
            return netFutureTask == null ? 1 : this.a.value() - netFutureTask.a.value();
        }
    }

    static class TaskCallable implements Callable<Response> {
        private Request a;
        private NetworkCallBack b;

        public TaskCallable(Request request) {
            this(request, null);
        }

        public TaskCallable(Request request, NetworkCallBack networkCallBack) {
            this.a = request;
            this.b = networkCallBack;
        }

        private void a(HttpRequestBase httpRequestBase) {
            for (Entry entry : this.a.getHeaders().entrySet()) {
                httpRequestBase.setHeader((String) entry.getKey(), (String) entry.getValue());
            }
            httpRequestBase.setHeader("User-Agent", new StringBuilder("GDTADNetClient-[").append(System.getProperty("http.agent")).append("]").toString());
            httpRequestBase.addHeader(HttpConstant.ACCEPT_ENCODING, HttpConstant.GZIP);
            HttpParams params = httpRequestBase.getParams();
            if (params == null) {
                params = new BasicHttpParams();
            }
            if (this.a.getConnectionTimeOut() > 0) {
                HttpConnectionParams.setConnectionTimeout(params, this.a.getConnectionTimeOut());
            }
            if (this.a.getSocketTimeOut() > 0) {
                HttpConnectionParams.setSoTimeout(params, this.a.getSocketTimeOut());
            }
            httpRequestBase.setParams(params);
        }

        public Response call() throws Exception {
            Response response = null;
            try {
                HttpUriRequest httpPost;
                HttpClient a = a;
                switch (AnonymousClass_1.a[this.a.getMethod().ordinal()]) {
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        httpPost = new HttpPost(this.a.getUrlWithParas());
                        a(httpPost);
                        byte[] postData = this.a.getPostData();
                        if (postData != null && postData.length > 0) {
                            httpPost.setEntity(new ByteArrayEntity(postData));
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        httpPost = new HttpGet(this.a.getUrlWithParas());
                        a(httpPost);
                        break;
                    default:
                        httpPost = null;
                        break;
                }
                response = this.a.initResponse(httpPost, a.execute(httpPost));
                Response response2 = null;
            } catch (Exception e) {
                Throwable e2 = e;
            }
            if (e2 == null) {
                if (this.b != null) {
                    this.b.onResponse(this.a, response);
                }
                if (this.a.isAutoClose()) {
                    response.close();
                }
            } else if (this.b != null) {
                GDTLogger.w("NetworkClientException", e2);
                this.b.onException(e2);
                if (response != null) {
                    response.close();
                }
            } else {
                throw e2;
            }
            return response;
        }
    }

    static {
        b = new NetworkClientImpl();
        HttpParams basicHttpParams = new BasicHttpParams();
        ConnManagerParams.setTimeout(basicHttpParams, StatisticConfig.MIN_UPLOAD_INTERVAL);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, a.MAX_USERDATA_VALUE_LENGTH);
        HttpConnectionParams.setSoTimeout(basicHttpParams, a.MAX_USERDATA_VALUE_LENGTH);
        ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(3));
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, XZBDevice.Stop);
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(basicHttpParams, GameManager.DEFAULT_CHARSET);
        HttpProtocolParams.setUserAgent(basicHttpParams, new StringBuilder("GDTADNetClient-[").append(System.getProperty("http.agent")).append("]").toString());
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme(HttpConstant.HTTP, PlainSocketFactory.getSocketFactory(), 80));
        a = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
    }

    private NetworkClientImpl() {
        this.d = new PriorityBlockingQueue(15);
        this.c = new ThreadPoolExecutor(5, 10, 180, TimeUnit.SECONDS, this.d);
    }

    public static NetworkClient getInstance() {
        return b;
    }

    public Future<Response> submit(Request request) {
        return submit(request, Priority.Mid);
    }

    public Future<Response> submit(Request request, Priority priority) {
        Object netFutureTask = new NetFutureTask(this, new TaskCallable(request), priority);
        this.c.execute(netFutureTask);
        GDTLogger.d(new StringBuilder("QueueSize:").append(this.d.size()).toString());
        return netFutureTask;
    }

    public void submit(Request request, NetworkCallBack networkCallBack) {
        submit(request, Priority.Mid, networkCallBack);
    }

    public void submit(Request request, Priority priority, NetworkCallBack networkCallBack) {
        this.c.execute(new NetFutureTask(this, new TaskCallable(request, networkCallBack), priority));
        GDTLogger.d(new StringBuilder("QueueSize:").append(this.d.size()).toString());
    }
}
