package com.xiaomi.push.thrift;

import com.tencent.connect.common.Constants;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public enum a {
    TCP_CONN_FAIL(1),
    TCP_CONN_TIME(2),
    PING_RTT(3),
    CHANNEL_CON_FAIL(4),
    CHANNEL_CON_OK(5),
    ICMP_PING_FAIL(6),
    ICMP_PING_OK(7),
    CHANNEL_ONLINE_RATE(8),
    GSLB_REQUEST_SUCCESS(10000),
    GSLB_TCP_NOACCESS(10101),
    GSLB_TCP_NETUNREACH(10102),
    GSLB_TCP_CONNREFUSED(10103),
    GSLB_TCP_NOROUTETOHOST(10104),
    GSLB_TCP_TIMEOUT(10105),
    GSLB_TCP_INVALARG(10106),
    GSLB_TCP_UKNOWNHOST(10107),
    GSLB_TCP_ERR_OTHER(10199),
    GSLB_ERR(10999),
    CONN_SUCCESS(20000),
    CONN_TCP_NOACCESS(20101),
    CONN_TCP_NETUNREACH(20102),
    CONN_TCP_CONNREFUSED(20103),
    CONN_TCP_NOROUTETOHOST(20104),
    CONN_TCP_TIMEOUT(20105),
    CONN_TCP_INVALARG(20106),
    CONN_TCP_UKNOWNHOST(20107),
    CONN_TCP_ERR_OTHER(20199),
    CONN_XMPP_ERR(20399),
    CONN_BOSH_UNKNOWNHOST(20407),
    CONN_BOSH_ERR(20499),
    BIND_SUCCESS(30000),
    BIND_TCP_READ_TIMEOUT_DEPRECTED(30101),
    BIND_TCP_CONNRESET_DEPRECTED(30102),
    BIND_TCP_BROKEN_PIPE_DEPRECTED(30103),
    BIND_TCP_READ_TIMEOUT(30108),
    BIND_TCP_CONNRESET(30109),
    BIND_TCP_BROKEN_PIPE(30110),
    BIND_TCP_ERR(30199),
    BIND_XMPP_ERR(30399),
    BIND_BOSH_ITEM_NOT_FOUND(30401),
    BIND_BOSH_ERR(30499),
    BIND_TIMEOUT(30501),
    BIND_INVALID_SIG(30502),
    CHANNEL_TCP_READTIMEOUT_DEPRECTED(40101),
    CHANNEL_TCP_CONNRESET_DEPRECTED(40102),
    CHANNEL_TCP_BROKEN_PIPE_DEPRECTED(40103),
    CHANNEL_TCP_READTIMEOUT(40108),
    CHANNEL_TCP_CONNRESET(40109),
    CHANNEL_TCP_BROKEN_PIPE(40110),
    CHANNEL_TCP_ERR(40199),
    CHANNEL_XMPPEXCEPTION(40399),
    CHANNEL_BOSH_ITEMNOTFIND(40401),
    CHANNEL_BOSH_EXCEPTION(40499),
    CHANNEL_TIMER_DELAYED(50001),
    CHANNEL_STATS_COUNTER(8000);
    private final int ad;

    static {
        a = new a("TCP_CONN_FAIL", 0, 1);
        b = new a("TCP_CONN_TIME", 1, 2);
        c = new a("PING_RTT", 2, 3);
        d = new a("CHANNEL_CON_FAIL", 3, 4);
        e = new a("CHANNEL_CON_OK", 4, 5);
        f = new a("ICMP_PING_FAIL", 5, 6);
        g = new a("ICMP_PING_OK", 6, 7);
        h = new a("CHANNEL_ONLINE_RATE", 7, 8);
        i = new a("GSLB_REQUEST_SUCCESS", 8, 10000);
        j = new a("GSLB_TCP_NOACCESS", 9, 10101);
        k = new a("GSLB_TCP_NETUNREACH", 10, 10102);
        l = new a("GSLB_TCP_CONNREFUSED", 11, 10103);
        m = new a("GSLB_TCP_NOROUTETOHOST", 12, 10104);
        n = new a("GSLB_TCP_TIMEOUT", 13, 10105);
        o = new a("GSLB_TCP_INVALARG", 14, 10106);
        p = new a("GSLB_TCP_UKNOWNHOST", 15, 10107);
        q = new a("GSLB_TCP_ERR_OTHER", 16, 10199);
        r = new a("GSLB_ERR", 17, 10999);
        s = new a("CONN_SUCCESS", 18, 20000);
        t = new a("CONN_TCP_NOACCESS", 19, 20101);
        u = new a("CONN_TCP_NETUNREACH", 20, 20102);
        v = new a("CONN_TCP_CONNREFUSED", 21, 20103);
        w = new a("CONN_TCP_NOROUTETOHOST", 22, 20104);
        x = new a("CONN_TCP_TIMEOUT", 23, 20105);
        y = new a("CONN_TCP_INVALARG", 24, 20106);
        z = new a("CONN_TCP_UKNOWNHOST", 25, 20107);
        A = new a("CONN_TCP_ERR_OTHER", 26, 20199);
        B = new a("CONN_XMPP_ERR", 27, 20399);
        C = new a("CONN_BOSH_UNKNOWNHOST", 28, 20407);
        D = new a("CONN_BOSH_ERR", 29, 20499);
        E = new a("BIND_SUCCESS", 30, 30000);
        F = new a("BIND_TCP_READ_TIMEOUT_DEPRECTED", 31, 30101);
        G = new a("BIND_TCP_CONNRESET_DEPRECTED", 32, 30102);
        H = new a("BIND_TCP_BROKEN_PIPE_DEPRECTED", 33, 30103);
        I = new a("BIND_TCP_READ_TIMEOUT", 34, 30108);
        J = new a("BIND_TCP_CONNRESET", 35, 30109);
        K = new a("BIND_TCP_BROKEN_PIPE", 36, 30110);
        L = new a("BIND_TCP_ERR", 37, 30199);
        M = new a("BIND_XMPP_ERR", 38, 30399);
        N = new a("BIND_BOSH_ITEM_NOT_FOUND", 39, 30401);
        O = new a("BIND_BOSH_ERR", 40, 30499);
        P = new a("BIND_TIMEOUT", 41, 30501);
        Q = new a("BIND_INVALID_SIG", 42, 30502);
        R = new a("CHANNEL_TCP_READTIMEOUT_DEPRECTED", 43, 40101);
        S = new a("CHANNEL_TCP_CONNRESET_DEPRECTED", 44, 40102);
        T = new a("CHANNEL_TCP_BROKEN_PIPE_DEPRECTED", 45, 40103);
        U = new a("CHANNEL_TCP_READTIMEOUT", 46, 40108);
        V = new a("CHANNEL_TCP_CONNRESET", 47, 40109);
        W = new a("CHANNEL_TCP_BROKEN_PIPE", 48, 40110);
        X = new a("CHANNEL_TCP_ERR", 49, 40199);
        Y = new a("CHANNEL_XMPPEXCEPTION", 50, 40399);
        Z = new a("CHANNEL_BOSH_ITEMNOTFIND", 51, 40401);
        aa = new a("CHANNEL_BOSH_EXCEPTION", 52, 40499);
        ab = new a("CHANNEL_TIMER_DELAYED", 53, 50001);
        ac = new a("CHANNEL_STATS_COUNTER", 54, 8000);
        ae = new a[]{a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z, A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z, aa, ab, ac};
    }

    private a(int i) {
        this.ad = i;
    }

    public static a a(int i) {
        switch (i) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return a;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return b;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return c;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                return d;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                return e;
            case R.styleable.Toolbar_contentInsetEnd:
                return f;
            case R.styleable.Toolbar_contentInsetLeft:
                return g;
            case XZBDevice.Wait:
                return h;
            case 8000:
                return ac;
            case 10000:
                return i;
            case 10101:
                return j;
            case Constants.REQUEST_APPBAR:
                return k;
            case Constants.REQUEST_QQ_SHARE:
                return l;
            case Constants.REQUEST_QZONE_SHARE:
                return m;
            case Constants.REQUEST_QQ_FAVORITES:
                return n;
            case Constants.REQUEST_SEND_TO_MY_COMPUTER:
                return o;
            case Constants.REQUEST_SHARE_TO_TROOP_BAR:
                return p;
            case 10199:
                return q;
            case 10999:
                return r;
            case com.alipay.sdk.data.a.d:
                return s;
            case 20101:
                return t;
            case 20102:
                return u;
            case 20103:
                return v;
            case 20104:
                return w;
            case 20105:
                return x;
            case 20106:
                return y;
            case 20107:
                return z;
            case 20199:
                return A;
            case 20399:
                return B;
            case 20407:
                return C;
            case 20499:
                return D;
            case com.tencent.bugly.BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH:
                return E;
            case 30101:
                return F;
            case 30102:
                return G;
            case 30103:
                return H;
            case 30108:
                return I;
            case 30109:
                return J;
            case 30110:
                return K;
            case 30199:
                return L;
            case 30399:
                return M;
            case 30401:
                return N;
            case 30499:
                return O;
            case 30501:
                return P;
            case 30502:
                return Q;
            case 40101:
                return R;
            case 40102:
                return S;
            case 40103:
                return T;
            case 40108:
                return U;
            case 40109:
                return V;
            case 40110:
                return W;
            case 40199:
                return X;
            case 40399:
                return Y;
            case 40401:
                return Z;
            case 40499:
                return aa;
            case 50001:
                return ab;
            default:
                return null;
        }
    }

    public final int a() {
        return this.ad;
    }
}
