package com.qq.e.comm.net.rr;

import com.qq.e.comm.net.rr.a.b;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;

public class S2SSResponse extends AbstractResponse {
    public S2SSResponse(HttpResponse httpResponse, HttpUriRequest httpUriRequest) {
        super(httpResponse, httpUriRequest);
    }

    public byte[] getBytesContent() throws IllegalStateException, IOException {
        try {
            return a.b(super.getBytesContent());
        } catch (b e) {
            e.printStackTrace();
            return null;
        }
    }
}
