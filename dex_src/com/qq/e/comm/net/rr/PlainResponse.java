package com.qq.e.comm.net.rr;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;

public class PlainResponse extends AbstractResponse {
    public PlainResponse(HttpResponse httpResponse, HttpUriRequest httpUriRequest) {
        super(httpResponse, httpUriRequest);
    }
}
