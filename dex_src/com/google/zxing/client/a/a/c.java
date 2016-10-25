package com.google.zxing.client.a.a;

import android.os.IBinder;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// compiled from: FlashlightManager.java
public final class c {
    private static final String a;
    private static final Object b;
    private static final Method c;

    static {
        Object obj;
        Method method = null;
        a = c.class.getSimpleName();
        Class a = a("android.os.ServiceManager");
        if (a == null) {
            obj = null;
        } else {
            Method a2 = a(a, "getService", String.class);
            if (a2 == null) {
                obj = null;
            } else {
                if (a(a2, null, "hardware") == null) {
                    obj = null;
                } else {
                    Class a3 = a("android.os.IHardwareService$Stub");
                    if (a3 == null) {
                        obj = null;
                    } else {
                        Method a4 = a(a3, "asInterface", IBinder.class);
                        if (a4 == null) {
                            obj = null;
                        } else {
                            obj = a(a4, null, obj);
                        }
                    }
                }
            }
        }
        b = obj;
        if (obj != null) {
            method = a(obj.getClass(), "setFlashlightEnabled", Boolean.TYPE);
        }
        c = method;
    }

    private c() {
    }

    private static Class<?> a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            return null;
        } catch (RuntimeException e2) {
            return null;
        }
    }

    private static Method a(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            return null;
        } catch (RuntimeException e2) {
            return null;
        }
    }

    private static Object a(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            new StringBuilder("Unexpected error while invoking ").append(method);
            return null;
        } catch (InvocationTargetException e2) {
            new StringBuilder("Unexpected error while invoking ").append(method);
            e2.getCause();
            return null;
        } catch (RuntimeException e3) {
            new StringBuilder("Unexpected error while invoking ").append(method);
            return null;
        }
    }

    public static void a() {
        if (b != null) {
            a(c, b, Boolean.valueOf(false));
        }
    }
}
