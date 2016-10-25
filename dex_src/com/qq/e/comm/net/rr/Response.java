package com.qq.e.comm.net.rr;

import java.io.IOException;
import java.io.InputStream;

public interface Response {
    void close() throws IllegalStateException, IOException;

    byte[] getBytesContent() throws IllegalStateException, IOException;

    int getStatusCode();

    InputStream getStreamContent() throws IllegalStateException, IOException;

    String getStringContent() throws IllegalStateException, IOException;

    String getStringContent(String str) throws IllegalStateException, IOException;
}
