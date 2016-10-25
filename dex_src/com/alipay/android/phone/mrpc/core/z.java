package com.alipay.android.phone.mrpc.core;

import android.os.Looper;
import com.alipay.android.phone.mrpc.core.a.d;
import com.alipay.android.phone.mrpc.core.a.e;
import com.alipay.android.phone.mrpc.core.a.f;
import com.alipay.mobile.framework.service.annotation.OperationType;
import com.alipay.mobile.framework.service.annotation.ResetCookie;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public final class z {
    private static final ThreadLocal<Object> a;
    private static final ThreadLocal<Map<String, Object>> b;
    private byte c;
    private AtomicInteger d;
    private x e;

    static {
        a = new ThreadLocal();
        b = new ThreadLocal();
    }

    public z(x xVar) {
        this.c = (byte) 0;
        this.e = xVar;
        this.d = new AtomicInteger();
    }

    public final Object a(Method method, Object[] objArr) {
        boolean z = true;
        if (Looper.myLooper() == null || Looper.myLooper() != Looper.getMainLooper()) {
            Object obj = null;
        } else {
            boolean z2 = true;
        }
        if (z2) {
            throw new IllegalThreadStateException("can't in main thread call rpc .");
        }
        OperationType operationType = (OperationType) method.getAnnotation(OperationType.class);
        if (method.getAnnotation(ResetCookie.class) == null) {
            z = false;
        }
        Type genericReturnType = method.getGenericReturnType();
        method.getAnnotations();
        a.set(null);
        b.set(null);
        if (operationType == null) {
            throw new IllegalStateException("OperationType must be set.");
        }
        String value = operationType.value();
        int incrementAndGet = this.d.incrementAndGet();
        try {
            if (this.c == null) {
                f eVar = new e(incrementAndGet, value, objArr);
                if (b.get() != null) {
                    eVar.a(b.get());
                }
                byte[] bArr = (byte[]) new j(this.e.a(), method, incrementAndGet, value, eVar.a(), z).a();
                b.set(null);
                obj = new d(genericReturnType, bArr).a();
                if (genericReturnType != Void.TYPE) {
                    a.set(obj);
                }
            }
            return a.get();
        } catch (RpcException e) {
            e.setOperationType(value);
            throw e;
        }
    }
}
