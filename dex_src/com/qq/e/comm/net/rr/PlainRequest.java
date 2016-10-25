package com.qq.e.comm.net.rr;

import com.qq.e.comm.net.rr.Request.Method;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;

public class PlainRequest extends AbstractRequest {
    public PlainRequest(String str, Method method, byte[] bArr) {
        super(str, method, bArr);
    }

    public Response initResponse(HttpUriRequest httpUriRequest, HttpResponse httpResponse) {
        return new PlainResponse(httpResponse, httpUriRequest);
    }
}
