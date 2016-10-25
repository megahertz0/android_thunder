package com.inmobi.ads;

import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.commons.core.network.NetworkError;
import com.inmobi.commons.core.network.NetworkError.ErrorCode;
import com.inmobi.commons.core.network.c;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: AdNetworkResponse.java
final class f {
    private c a;
    private InMobiAdRequestStatus b;
    private e c;

    // compiled from: AdNetworkResponse.java
    /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[ErrorCode.values().length];
            try {
                a[ErrorCode.NETWORK_UNAVAILABLE_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[ErrorCode.HTTP_BAD_REQUEST.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[ErrorCode.HTTP_GATEWAY_TIMEOUT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[ErrorCode.HTTP_INTERNAL_SERVER_ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[ErrorCode.HTTP_NOT_IMPLEMENTED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[ErrorCode.HTTP_BAD_GATEWAY.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[ErrorCode.HTTP_SERVER_NOT_AVAILABLE.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[ErrorCode.HTTP_VERSION_NOT_SUPPORTED.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    public f(e eVar, c cVar) {
        this.c = eVar;
        this.a = cVar;
        if (this.a.c() != null) {
            e();
        }
    }

    private void e() {
        switch (AnonymousClass_1.a[this.a.c().a().ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.b = new InMobiAdRequestStatus(StatusCode.NETWORK_UNREACHABLE);
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                this.b = new InMobiAdRequestStatus(StatusCode.REQUEST_INVALID);
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                this.b = new InMobiAdRequestStatus(StatusCode.REQUEST_TIMED_OUT);
            case XZBDevice.DOWNLOAD_LIST_ALL:
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
            case R.styleable.Toolbar_contentInsetEnd:
            case R.styleable.Toolbar_contentInsetLeft:
            case XZBDevice.Wait:
                this.b = new InMobiAdRequestStatus(StatusCode.SERVER_ERROR);
            default:
                this.b = new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR);
        }
    }

    public final InMobiAdRequestStatus a() {
        return this.b;
    }

    public final e b() {
        return this.c;
    }

    public final String c() {
        return this.a.b();
    }

    public final NetworkError d() {
        return this.a.c();
    }
}
