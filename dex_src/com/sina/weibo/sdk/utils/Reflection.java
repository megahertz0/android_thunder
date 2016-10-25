package com.sina.weibo.sdk.utils;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflection {
    public static Object getProperty(Object obj, String str) throws Exception {
        return obj.getClass().getField(str).get(obj);
    }

    public static Object getStaticProperty(String str, String str2) throws Exception {
        Class forName = Class.forName(str);
        return forName.getField(str2).get(forName);
    }

    public static Object invokeMethod(Object obj, String str, Object[] objArr) throws Exception {
        Class cls = obj.getClass();
        Class[] clsArr = new Class[objArr.length];
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            clsArr[i] = objArr[i].getClass();
        }
        return cls.getMethod(str, clsArr).invoke(obj, objArr);
    }

    public static Object invokeStaticMethod(String str, String str2, Object[] objArr) throws Exception {
        Class forName = Class.forName(str);
        Class[] clsArr = new Class[objArr.length];
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            clsArr[i] = objArr[i].getClass();
        }
        return forName.getMethod(str2, clsArr).invoke(null, objArr);
    }

    public static Object newInstance(String str, Class<?>[] clsArr, Object[] objArr) throws Exception {
        return Class.forName(str).getConstructor(clsArr).newInstance(objArr);
    }

    public static boolean isInstance(Object obj, Class cls) {
        return cls.isInstance(obj);
    }

    public static Object getByArray(Object obj, int i) {
        return Array.get(obj, i);
    }

    public static void invokeVoidMethod(Object obj, String str, boolean z) {
        try {
            obj.getClass().getMethod(str, new Class[]{Boolean.TYPE}).invoke(obj, new Object[]{Boolean.valueOf(z)});
        } catch (SecurityException e) {
        } catch (NoSuchMethodException e2) {
        } catch (IllegalArgumentException e3) {
        } catch (IllegalAccessException e4) {
        } catch (InvocationTargetException e5) {
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object invokeMethod(java.lang.Object r1, java.lang.String r2, java.lang.Class<?>[] r3, java.lang.Object[] r4) {
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.utils.Reflection.invokeMethod(java.lang.Object, java.lang.String, java.lang.Class[], java.lang.Object[]):java.lang.Object");
        /*
        r0 = r1.getClass();	 Catch:{ SecurityException -> 0x000d, NoSuchMethodException -> 0x0013, IllegalArgumentException -> 0x0018, IllegalAccessException -> 0x001d, InvocationTargetException -> 0x0022 }
        r0 = r0.getMethod(r2, r3);	 Catch:{ SecurityException -> 0x000d, NoSuchMethodException -> 0x0013, IllegalArgumentException -> 0x0018, IllegalAccessException -> 0x001d, InvocationTargetException -> 0x0022 }
        r0 = r0.invoke(r1, r4);	 Catch:{ SecurityException -> 0x000d, NoSuchMethodException -> 0x0013, IllegalArgumentException -> 0x0018, IllegalAccessException -> 0x001d, InvocationTargetException -> 0x0022 }
    L_0x000c:
        return r0;
    L_0x000d:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x0011:
        r0 = 0;
        goto L_0x000c;
    L_0x0013:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0011;
    L_0x0018:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0011;
    L_0x001d:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0011;
    L_0x0022:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0011;
        */
    }

    public static Object invokeParamsMethod(Object obj, String str, Class<?>[] clsArr, Object[] objArr) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        Method method = obj.getClass().getMethod(str, clsArr);
        method.setAccessible(true);
        return method.invoke(obj, objArr);
    }

    public static Object invokeStaticMethod(String str, String str2, Class<?>[] clsArr, Object[] objArr) {
        try {
            return Class.forName(str).getMethod(str2, clsArr).invoke(null, objArr);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SecurityException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            return null;
        } catch (IllegalArgumentException e4) {
            e4.printStackTrace();
            return null;
        } catch (IllegalAccessException e5) {
            e5.printStackTrace();
            return null;
        } catch (InvocationTargetException e6) {
            e6.printStackTrace();
            return null;
        }
    }

    public static Object invokeStaticMethod(Class cls, String str, Class<?>[] clsArr, Object[] objArr) {
        try {
            return cls.getMethod(str, clsArr).invoke(null, objArr);
        } catch (SecurityException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return null;
        } catch (IllegalArgumentException e3) {
            e3.printStackTrace();
            return null;
        } catch (IllegalAccessException e4) {
            e4.printStackTrace();
            return null;
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            return null;
        }
    }
}
