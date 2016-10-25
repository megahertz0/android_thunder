package com.android.volley;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.android.volley.b.a;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

public abstract class Request<T> implements Comparable<Request<T>> {
    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";
    private a mCacheEntry;
    private boolean mCanceled;
    private final int mDefaultTrafficStatsTag;
    private final r.a mErrorListener;
    private final a mEventLog;
    private final int mMethod;
    private p mRequestQueue;
    private boolean mResponseDelivered;
    private t mRetryPolicy;
    private Integer mSequence;
    private boolean mShouldCache;
    private boolean mShouldRetryServerErrors;
    private Object mTag;
    private final String mUrl;

    public enum Priority {
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE;

        static {
            LOW = new com.android.volley.Request.Priority("LOW", 0);
            NORMAL = new com.android.volley.Request.Priority("NORMAL", 1);
            HIGH = new com.android.volley.Request.Priority("HIGH", 2);
            IMMEDIATE = new com.android.volley.Request.Priority("IMMEDIATE", 3);
            a = new com.android.volley.Request.Priority[]{LOW, NORMAL, HIGH, IMMEDIATE};
        }
    }

    public abstract void deliverResponse(T t);

    public abstract r<T> parseNetworkResponse(l lVar);

    @Deprecated
    public Request(String str, r.a aVar) {
        this(-1, str, aVar);
    }

    public Request(int i, String str, r.a aVar) {
        a aVar2;
        if (a.a) {
            aVar2 = new a();
        } else {
            aVar2 = null;
        }
        this.mEventLog = aVar2;
        this.mShouldCache = true;
        this.mCanceled = false;
        this.mResponseDelivered = false;
        this.mShouldRetryServerErrors = false;
        this.mCacheEntry = null;
        this.mMethod = i;
        this.mUrl = str;
        this.mErrorListener = aVar;
        setRetryPolicy(new f());
        this.mDefaultTrafficStatsTag = findDefaultTrafficStatsTag(str);
    }

    public int getMethod() {
        return this.mMethod;
    }

    public Request<?> setTag(Object obj) {
        this.mTag = obj;
        return this;
    }

    public Object getTag() {
        return this.mTag;
    }

    public r.a getErrorListener() {
        return this.mErrorListener;
    }

    public int getTrafficStatsTag() {
        return this.mDefaultTrafficStatsTag;
    }

    private static int findDefaultTrafficStatsTag(String str) {
        if (!TextUtils.isEmpty(str)) {
            Uri parse = Uri.parse(str);
            if (parse != null) {
                String host = parse.getHost();
                if (host != null) {
                    return host.hashCode();
                }
            }
        }
        return 0;
    }

    public Request<?> setRetryPolicy(t tVar) {
        this.mRetryPolicy = tVar;
        return this;
    }

    public void addMarker(String str) {
        if (a.a) {
            this.mEventLog.a(str, Thread.currentThread().getId());
        }
    }

    void finish(String str) {
        if (this.mRequestQueue != null) {
            p pVar = this.mRequestQueue;
            synchronized (pVar.b) {
                pVar.b.remove(this);
            }
            synchronized (pVar.d) {
                Iterator it = pVar.d.iterator();
                while (it.hasNext()) {
                    it.next();
                }
            }
            if (shouldCache()) {
                synchronized (pVar.a) {
                    Queue queue = (Queue) pVar.a.remove(getCacheKey());
                    if (queue != null) {
                        if (x.b) {
                            x.a("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(queue.size()), r3);
                        }
                        pVar.c.addAll(queue);
                    }
                }
            }
        }
        if (a.a) {
            long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new o(this, str, id));
                return;
            }
            this.mEventLog.a(str, id);
            this.mEventLog.a(toString());
        }
    }

    public Request<?> setRequestQueue(p pVar) {
        this.mRequestQueue = pVar;
        return this;
    }

    public final Request<?> setSequence(int i) {
        this.mSequence = Integer.valueOf(i);
        return this;
    }

    public final int getSequence() {
        if (this.mSequence != null) {
            return this.mSequence.intValue();
        }
        throw new IllegalStateException("getSequence called before setSequence");
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getCacheKey() {
        return getUrl();
    }

    public Request<?> setCacheEntry(a aVar) {
        this.mCacheEntry = aVar;
        return this;
    }

    public a getCacheEntry() {
        return this.mCacheEntry;
    }

    public void cancel() {
        this.mCanceled = true;
    }

    public boolean isCanceled() {
        return this.mCanceled;
    }

    public Map<String, String> getHeaders() throws a {
        return Collections.emptyMap();
    }

    @Deprecated
    protected Map<String, String> getPostParams() throws a {
        return getParams();
    }

    @Deprecated
    protected String getPostParamsEncoding() {
        return getParamsEncoding();
    }

    @Deprecated
    public String getPostBodyContentType() {
        return getBodyContentType();
    }

    @Deprecated
    public byte[] getPostBody() throws a {
        Map postParams = getPostParams();
        return (postParams == null || postParams.size() <= 0) ? null : encodeParameters(postParams, getPostParamsEncoding());
    }

    public Map<String, String> getParams() throws a {
        return null;
    }

    protected String getParamsEncoding() {
        return DEFAULT_PARAMS_ENCODING;
    }

    public String getBodyContentType() {
        return new StringBuilder("application/x-www-form-urlencoded; charset=").append(getParamsEncoding()).toString();
    }

    public byte[] getBody() throws a {
        Map params = getParams();
        return (params == null || params.size() <= 0) ? null : encodeParameters(params, getParamsEncoding());
    }

    private byte[] encodeParameters(Map<String, String> map, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            for (Entry entry : map.entrySet()) {
                stringBuilder.append(URLEncoder.encode((String) entry.getKey(), str));
                stringBuilder.append('=');
                stringBuilder.append(URLEncoder.encode((String) entry.getValue(), str));
                stringBuilder.append('&');
            }
            return stringBuilder.toString().getBytes(str);
        } catch (Throwable e) {
            throw new RuntimeException(new StringBuilder("Encoding not supported: ").append(str).toString(), e);
        }
    }

    public final Request<?> setShouldCache(boolean z) {
        this.mShouldCache = z;
        return this;
    }

    public final boolean shouldCache() {
        return this.mShouldCache;
    }

    public final Request<?> setShouldRetryServerErrors(boolean z) {
        this.mShouldRetryServerErrors = z;
        return this;
    }

    public final boolean shouldRetryServerErrors() {
        return this.mShouldRetryServerErrors;
    }

    public Priority getPriority() {
        return Priority.NORMAL;
    }

    public final int getTimeoutMs() {
        return this.mRetryPolicy.a();
    }

    public t getRetryPolicy() {
        return this.mRetryPolicy;
    }

    public void markDelivered() {
        this.mResponseDelivered = true;
    }

    public boolean hasHadResponseDelivered() {
        return this.mResponseDelivered;
    }

    protected w parseNetworkError(w wVar) {
        return wVar;
    }

    public void deliverError(w wVar) {
        if (this.mErrorListener != null) {
            this.mErrorListener.onErrorResponse(wVar);
        }
    }

    public int compareTo(Request<T> request) {
        Priority priority = getPriority();
        Priority priority2 = request.getPriority();
        return priority == priority2 ? this.mSequence.intValue() - request.mSequence.intValue() : priority2.ordinal() - priority.ordinal();
    }

    public String toString() {
        return (this.mCanceled ? "[X] " : "[ ] ") + getUrl() + " " + new StringBuilder("0x").append(Integer.toHexString(getTrafficStatsTag())).toString() + " " + getPriority() + " " + this.mSequence;
    }
}
