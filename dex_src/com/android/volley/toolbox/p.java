package com.android.volley.toolbox;

import com.android.volley.Request;
import com.android.volley.l;
import com.android.volley.r;
import com.android.volley.r.a;
import com.android.volley.r.b;
import com.android.volley.x;
import java.io.UnsupportedEncodingException;

// compiled from: JsonRequest.java
public abstract class p<T> extends Request<T> {
    protected static final String PROTOCOL_CHARSET = "utf-8";
    private static final String PROTOCOL_CONTENT_TYPE;
    private final b<T> mListener;
    private final String mRequestBody;

    public abstract r<T> parseNetworkResponse(l lVar);

    static {
        PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", new Object[]{PROTOCOL_CHARSET});
    }

    public p(String str, String str2, b<T> bVar, a aVar) {
        this(-1, str, str2, bVar, aVar);
    }

    public p(int i, String str, String str2, b<T> bVar, a aVar) {
        super(i, str, aVar);
        this.mListener = bVar;
        this.mRequestBody = str2;
    }

    protected void deliverResponse(T t) {
        this.mListener.onResponse(t);
    }

    public String getPostBodyContentType() {
        return getBodyContentType();
    }

    public byte[] getPostBody() {
        return getBody();
    }

    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    public byte[] getBody() {
        try {
            return this.mRequestBody == null ? null : this.mRequestBody.getBytes(PROTOCOL_CHARSET);
        } catch (UnsupportedEncodingException e) {
            x.e("Unsupported Encoding while trying to get the bytes of %s using %s", this.mRequestBody, PROTOCOL_CHARSET);
            return null;
        }
    }
}
