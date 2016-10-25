package com.alipay.android.phone.mrpc.core;

import anet.channel.util.HttpConstant;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;
import org.android.spdy.SpdyAgent;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

public final class j extends a {
    private g g;

    public j(g gVar, Method method, int i, String str, byte[] bArr, boolean z) {
        super(method, i, str, bArr, "application/x-www-form-urlencoded", z);
        this.g = gVar;
    }

    public final Object a() {
        Throwable e;
        Throwable th;
        t oVar = new o(this.g.a());
        oVar.a(this.b);
        oVar.a(this.e);
        oVar.a(this.f);
        oVar.a(SocializeConstants.WEIBO_ID, String.valueOf(this.d));
        oVar.a("operationType", this.c);
        oVar.a(HttpConstant.GZIP, String.valueOf(this.g.d()));
        oVar.a(new BasicHeader("uuid", UUID.randomUUID().toString()));
        List<Header> b = this.g.c().b();
        if (!(b == null || b.isEmpty())) {
            for (Header header : b) {
                oVar.a(header);
            }
        }
        new StringBuilder("threadid = ").append(Thread.currentThread().getId()).append("; ").append(oVar.toString());
        try {
            u uVar = (u) this.g.b().a(oVar).get();
            if (uVar != null) {
                return uVar.b();
            }
            throw new RpcException(Integer.valueOf(XZBDevice.Pause), "response is null");
        } catch (Throwable e2) {
            throw new RpcException(Integer.valueOf(XZBDevice.Upload), a.d, e2);
        } catch (Throwable e22) {
            th = e22;
            e22 = th.getCause();
            if (e22 == null || !(e22 instanceof HttpException)) {
                throw new RpcException(Integer.valueOf(XZBDevice.Pause), a.d, th);
            }
            HttpException httpException = (HttpException) e22;
            int code = httpException.getCode();
            switch (code) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    code = XZBDevice.DOWNLOAD_LIST_RECYCLE;
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    code = XZBDevice.DOWNLOAD_LIST_FAILED;
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    code = XZBDevice.DOWNLOAD_LIST_ALL;
                    break;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    code = XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
                    break;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    code = R.styleable.Toolbar_contentInsetEnd;
                    break;
                case R.styleable.Toolbar_contentInsetEnd:
                    code = R.styleable.Toolbar_contentInsetLeft;
                    break;
                case R.styleable.Toolbar_contentInsetLeft:
                    code = XZBDevice.Wait;
                    break;
                case XZBDevice.Wait:
                    code = XZBDevice.Delete;
                    break;
                case XZBDevice.Pause:
                    code = R.styleable.Toolbar_titleMarginBottom;
                    break;
            }
            throw new RpcException(Integer.valueOf(code), httpException.getMsg());
        } catch (Throwable e222) {
            throw new RpcException(Integer.valueOf(XZBDevice.Upload), a.d, e222);
        }
    }
}
