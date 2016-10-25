package com.xunlei.XLStat.Net;

public interface TCPCallback {
    void tcp_connected();

    void tcp_disconnect();

    void tcp_receive(byte[] bArr);

    boolean tcp_sendCallBack(boolean z);
}
