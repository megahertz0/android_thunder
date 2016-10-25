package com.xiaomi.stats;

import com.xiaomi.smack.p;
import com.xunlei.xllib.R;
import java.net.UnknownHostException;

final class c {

    static class a {
        com.xiaomi.push.thrift.a a;
        String b;

        a() {
        }
    }

    static a a(Exception exception) {
        Throwable a;
        e(exception);
        if ((exception instanceof p) && ((p) exception).a() != null) {
            a = ((p) exception).a();
        }
        a aVar = new a();
        String message = a.getMessage();
        if (a.getCause() != null) {
            message = a.getCause().getMessage();
        }
        message = a.getClass().getSimpleName() + ":" + message;
        int a2 = com.xiaomi.smack.c.a(a);
        if (a2 != 0) {
            aVar.a = com.xiaomi.push.thrift.a.a(a2 + com.xiaomi.push.thrift.a.i.a());
        }
        if (aVar.a == null) {
            aVar.a = com.xiaomi.push.thrift.a.q;
        }
        if (aVar.a == com.xiaomi.push.thrift.a.q) {
            aVar.b = message;
        }
        return aVar;
    }

    static a b(Exception exception) {
        Throwable a;
        e(exception);
        if ((exception instanceof p) && ((p) exception).a() != null) {
            a = ((p) exception).a();
        }
        a aVar = new a();
        String message = a.getMessage();
        if (a.getCause() != null) {
            message = a.getCause().getMessage();
        }
        int a2 = com.xiaomi.smack.c.a(a);
        message = a.getClass().getSimpleName() + ":" + message;
        if (a2 != 0) {
            aVar.a = com.xiaomi.push.thrift.a.a(a2 + com.xiaomi.push.thrift.a.s.a());
            if (aVar.a == com.xiaomi.push.thrift.a.D) {
                Throwable cause = a.getCause();
                if (cause != null && (cause instanceof UnknownHostException)) {
                    aVar.a = com.xiaomi.push.thrift.a.C;
                }
            }
        } else {
            aVar.a = com.xiaomi.push.thrift.a.B;
        }
        if (aVar.a == com.xiaomi.push.thrift.a.A || aVar.a == com.xiaomi.push.thrift.a.B || aVar.a == com.xiaomi.push.thrift.a.D) {
            aVar.b = message;
        }
        return aVar;
    }

    static a c(Exception exception) {
        Throwable a;
        e(exception);
        if ((exception instanceof p) && ((p) exception).a() != null) {
            a = ((p) exception).a();
        }
        a aVar = new a();
        String message = a.getMessage();
        if (a.getCause() != null) {
            message = a.getCause().getMessage();
        }
        int a2 = com.xiaomi.smack.c.a(a);
        String str = a.getClass().getSimpleName() + ":" + message;
        switch (a2) {
            case R.styleable.AppCompatTheme_radioButtonStyle:
                aVar.a = com.xiaomi.push.thrift.a.I;
                break;
            case R.styleable.AppCompatTheme_seekBarStyle:
                aVar.a = com.xiaomi.push.thrift.a.J;
                break;
            case R.styleable.AppCompatTheme_spinnerStyle:
                aVar.a = com.xiaomi.push.thrift.a.K;
                break;
            case 199:
                aVar.a = com.xiaomi.push.thrift.a.L;
                break;
            case 499:
                aVar.a = com.xiaomi.push.thrift.a.O;
                if (message.startsWith("Terminal binding condition encountered: item-not-found")) {
                    aVar.a = com.xiaomi.push.thrift.a.N;
                }
                break;
            default:
                aVar.a = com.xiaomi.push.thrift.a.M;
                break;
        }
        if (aVar.a == com.xiaomi.push.thrift.a.L || aVar.a == com.xiaomi.push.thrift.a.M || aVar.a == com.xiaomi.push.thrift.a.O) {
            aVar.b = str;
        }
        return aVar;
    }

    static a d(Exception exception) {
        Throwable a;
        e(exception);
        if ((exception instanceof p) && ((p) exception).a() != null) {
            a = ((p) exception).a();
        }
        a aVar = new a();
        String message = a.getMessage();
        int a2 = com.xiaomi.smack.c.a(a);
        String str = a.getClass().getSimpleName() + ":" + message;
        switch (a2) {
            case R.styleable.AppCompatTheme_radioButtonStyle:
                aVar.a = com.xiaomi.push.thrift.a.U;
                break;
            case R.styleable.AppCompatTheme_seekBarStyle:
                aVar.a = com.xiaomi.push.thrift.a.V;
                break;
            case R.styleable.AppCompatTheme_spinnerStyle:
                aVar.a = com.xiaomi.push.thrift.a.W;
                break;
            case 199:
                aVar.a = com.xiaomi.push.thrift.a.X;
                break;
            case 499:
                aVar.a = com.xiaomi.push.thrift.a.aa;
                if (message.startsWith("Terminal binding condition encountered: item-not-found")) {
                    aVar.a = com.xiaomi.push.thrift.a.Z;
                }
                break;
            default:
                aVar.a = com.xiaomi.push.thrift.a.Y;
                break;
        }
        if (aVar.a == com.xiaomi.push.thrift.a.X || aVar.a == com.xiaomi.push.thrift.a.Y || aVar.a == com.xiaomi.push.thrift.a.aa) {
            aVar.b = str;
        }
        return aVar;
    }

    private static void e(Exception exception) {
        if (exception == null) {
            throw new NullPointerException();
        }
    }
}
