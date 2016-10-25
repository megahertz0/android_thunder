package com.xunlei.XLStat.Net;

import com.xunlei.XLStat.XLStatLog.XLStatLog;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class b {
    private String a;
    private TCPCallback b;
    private Socket c;
    private DataOutputStream d;
    private DataInputStream e;
    private byte[] f;
    private byte[] g;
    private int h;
    private String i;
    private int j;
    private int k;
    private int l;
    private int m;

    public b(TCPCallback tCPCallback) {
        this.a = "TCPConnect";
        this.f = new byte[1024];
        this.h = 30000;
        this.i = BuildConfig.VERSION_NAME;
        this.j = 0;
        this.k = -1;
        this.l = 0;
        this.m = 5;
        if (tCPCallback == null) {
            this.b = null;
        } else {
            this.b = tCPCallback;
        }
        this.c = new Socket();
    }

    public boolean a() throws Exception {
        if (this.i == null || this.i.length() == 0 || this.j >= 65536 || this.j <= 0) {
            return false;
        }
        boolean z;
        SocketAddress inetSocketAddress = new InetSocketAddress(this.i, this.j);
        try {
            XLStatLog.logThreadID("TCPConnect", "connect");
            this.c.connect(inetSocketAddress, this.h);
        } catch (Exception e) {
            XLStatLog.d(this.a, "connect", "connect failed ... ");
            e.printStackTrace();
        }
        while (!b()) {
            this.c.connect(inetSocketAddress, this.h);
            XLStatLog.d(this.a, "connect", "try to connected again ... ");
            this.l++;
            if (this.l >= this.m) {
                break;
            }
        }
        if (b()) {
            this.d = new DataOutputStream(this.c.getOutputStream());
            this.e = new DataInputStream(this.c.getInputStream());
            if (b() && this.b != null) {
                XLStatLog.d(this.a, "connect", "connect successfully.");
                this.b.tcp_connected();
            }
            z = true;
        } else {
            XLStatLog.d(this.a, "connect", "connect failed.");
            z = false;
        }
        this.l = 0;
        return z;
    }

    public boolean b() {
        return (this.c == null || this.c.isClosed()) ? false : this.c.isConnected();
    }

    public boolean a(byte[] bArr) {
        boolean z = true;
        boolean z2 = false;
        if (bArr == null || bArr.length <= 0) {
            return false;
        }
        if (this.d != null) {
            try {
                this.d.write(bArr);
                this.d.flush();
                XLStatLog.d(this.a, "send", "send data completed.");
                z2 = this.b;
                if (z2) {
                    this.b.tcp_sendCallBack(true);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return z2;
            }
        }
        XLStatLog.d(this.a, "send", "send data failed.");
        if (this.b != null) {
            this.b.tcp_sendCallBack(false);
        }
        z = false;
        return z;
    }

    public void c() throws IOException {
        if (this.e != null) {
            int i = 0;
            while (true) {
                int read = this.e.read(this.f);
                if (read > 0) {
                    i += read;
                    this.g = new byte[read];
                    System.arraycopy(this.f, 0, this.g, 0, read);
                    if (this.b != null) {
                        this.b.tcp_receive(this.g);
                    }
                    XLStatLog.i("tcplog", "tcpReceive:  ", String.valueOf(read));
                    if (i < 4) {
                        this.g = null;
                    } else {
                        return;
                    }
                }
                return;
            }
        }
    }

    public boolean d() {
        boolean z;
        XLStatLog.d(this.a, "disconnect", BuildConfig.VERSION_NAME);
        try {
            if (this.c != null) {
                if (!this.c.isInputShutdown()) {
                    this.c.shutdownInput();
                    XLStatLog.d(this.a, "disconnect", "shut down input");
                }
                if (!this.c.isOutputShutdown()) {
                    this.c.shutdownOutput();
                    XLStatLog.d(this.a, "disconnect", "shut down output");
                }
            }
            if (this.d != null) {
                this.d.close();
                XLStatLog.d(this.a, "disconnect", "out close");
            }
            if (this.e != null) {
                this.e.close();
                XLStatLog.d(this.a, "disconnect", "in close");
            }
            if (!(this.c == null || this.c.isClosed())) {
                this.c.close();
                XLStatLog.d(this.a, "disconnect", "socket close");
            }
            z = true;
            if (this.b != null) {
                this.b.tcp_disconnect();
            }
            this.d = null;
            this.e = null;
            this.c = null;
        } catch (Exception e) {
            r1 = e;
            z = false;
            try {
                Exception exception;
                exception.printStackTrace();
                if (this.b != null) {
                    this.b.tcp_disconnect();
                }
                this.d = null;
                this.e = null;
                this.c = null;
            } catch (Throwable th) {
                if (this.b != null) {
                    this.b.tcp_disconnect();
                }
            }
        }
        return z;
    }

    public void a(int i) {
        this.h = i;
    }

    public void a(String str) {
        this.i = str;
    }

    public void b(int i) {
        this.j = i;
    }
}
