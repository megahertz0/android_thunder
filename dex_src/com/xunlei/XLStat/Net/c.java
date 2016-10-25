package com.xunlei.XLStat.Net;

import com.xunlei.XLStat.XLStatLog.XLStatLog;
import com.xunlei.XLStat.j.b;
import com.xunlei.XLStat.j.e;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.IOException;
import org.apache.commons.logging.impl.SimpleLog;

public class c implements TCPCallback {
    public boolean a;
    private String b;
    private boolean c;
    private boolean d;
    private boolean e;
    private b f;
    private String g;
    private int h;
    private int i;
    private byte[] j;

    public c(String str, int i) {
        this.b = "TCPConnectHelper";
        this.g = BuildConfig.VERSION_NAME;
        this.h = 0;
        this.i = 1000;
        this.c = false;
        this.d = false;
        this.e = false;
        this.g = str;
        this.h = i;
        this.j = new byte[1024];
    }

    public boolean a(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return false;
        }
        this.c = false;
        this.d = false;
        this.f = new b(this);
        this.f.b(this.h);
        this.f.a(this.g);
        this.f.a(this.i);
        try {
            this.d = this.f.a();
            XLStatLog.d(this.b, "sendData", new StringBuilder("connect result: ").append(this.d).toString());
            if (this.d) {
                this.c = this.f.a(bArr);
                try {
                    this.f.c();
                    byte[] bArr2 = new byte[2];
                    if (this.a) {
                        XLStatLog.d(this.b + "wang", "sendData", new StringBuilder("receive buffer: ").append(e.a(this.j)).toString());
                    }
                    if (this.j != null && this.j.length >= 4) {
                        System.arraycopy(this.j, SimpleLog.LOG_LEVEL_DEBUG, bArr2, 0, SimpleLog.LOG_LEVEL_DEBUG);
                        short a = b.a(bArr2);
                        XLStatLog.i(this.b, "sendData", new StringBuilder("result bytes: ").append(e.a(bArr2)).append("  result short: ").append(a).toString());
                        if (a == (short) 0) {
                            this.f.d();
                            this.c = true;
                            this.f = null;
                            return true;
                        }
                        this.c = false;
                    }
                } catch (IOException e) {
                    XLStatLog.d(this.b, "sendData", "receive data Exception ... ");
                    e.printStackTrace();
                }
                this.e = this.f.d();
                XLStatLog.d(this.b, "sendData", new StringBuilder("disconnect result: ").append(this.e).append("  send result: ").append(this.c).toString());
                if (this.d && this.c) {
                    XLStatLog.d(this.b, "sendData", "send data successfully");
                    this.c = true;
                } else {
                    XLStatLog.d(this.b, "sendData", "send data failed ... ");
                    this.c = false;
                }
            } else {
                XLStatLog.e(this.b, "sendData", "connect failed");
                this.c = false;
            }
            this.f = null;
            return this.c;
        } catch (Exception e2) {
            this.c = false;
            e2.printStackTrace();
            XLStatLog.e(this.b, "sendData", "connect error");
            return this.c;
        }
    }

    public void tcp_connected() {
        this.d = true;
    }

    public void tcp_receive(byte[] bArr) {
        this.j = new byte[bArr.length];
        System.arraycopy(bArr, 0, this.j, 0, bArr.length);
        XLStatLog.d(this.b, "tcp_receive", new StringBuilder("receive data: ").append(e.a(this.j)).toString());
    }

    public void tcp_disconnect() {
    }

    public boolean tcp_sendCallBack(boolean z) {
        return this.c;
    }
}
