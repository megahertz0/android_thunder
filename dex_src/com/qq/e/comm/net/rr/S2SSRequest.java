package com.qq.e.comm.net.rr;

import com.qq.e.comm.net.rr.Request.Method;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;

public class S2SSRequest extends AbstractRequest {
    public S2SSRequest(String str, byte[] bArr) {
        super(str, Method.POST, bArr);
    }

    public byte[] getPostData() throws Exception {
        return a.a(super.getPostData());
    }

    public Response initResponse(HttpUriRequest httpUriRequest, HttpResponse httpResponse) {
        return new S2SSResponse(httpResponse, httpUriRequest);
    }
}
