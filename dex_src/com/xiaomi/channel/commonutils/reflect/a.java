package com.xiaomi.channel.commonutils.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class a {
    private static final Map<Class<?>, Class<?>> a;

    public static class a<T> {
        public final Class<? extends T> a;
        public final T b;
    }

    static {
        Map hashMap = new HashMap();
        a = hashMap;
        hashMap.put(Boolean.class, Boolean.TYPE);
        a.put(Byte.class, Byte.TYPE);
        a.put(Character.class, Character.TYPE);
        a.put(Short.class, Short.TYPE);
        a.put(Integer.class, Integer.TYPE);
        a.put(Float.class, Float.TYPE);
        a.put(Long.class, Long.TYPE);
        a.put(Double.class, Double.TYPE);
        a.put(Boolean.TYPE, Boolean.TYPE);
        a.put(Byte.TYPE, Byte.TYPE);
        a.put(Character.TYPE, Character.TYPE);
        a.put(Short.TYPE, Short.TYPE);
        a.put(Integer.TYPE, Integer.TYPE);
        a.put(Float.TYPE, Float.TYPE);
        a.put(Long.TYPE, Long.TYPE);
        a.put(Double.TYPE, Double.TYPE);
    }

    public static <T> T a(Class<? extends Object> cls, Object obj, String str) {
        Field field = null;
        while (field == null) {
            try {
                Class superclass;
                field = superclass.getDeclaredField(str);
                field.setAccessible(true);
                continue;
            } catch (NoSuchFieldException e) {
                superclass = superclass.getSuperclass();
                continue;
            }
            if (superclass == null) {
                throw new NoSuchFieldException();
            }
        }
        field.setAccessible(true);
        return field.get(obj);
    }

    public static <T> T a(Class<? extends Object> cls, String str) {
        try {
            return a((Class) cls, null, str);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static <T> T a(Class<?> cls, String str, Object... objArr) {
        return a((Class) cls, str, a(objArr)).invoke(null, b(objArr));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> T a(java.lang.Object r1, java.lang.String r2) {
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.channel.commonutils.reflect.a.a(java.lang.Object, java.lang.String):T");
        /*
        r0 = r1.getClass();	 Catch:{ NoSuchFieldException -> 0x0009, IllegalAccessException -> 0x000f }
        r0 = a(r0, r1, r2);	 Catch:{ NoSuchFieldException -> 0x0009, IllegalAccessException -> 0x000f }
    L_0x0008:
        return r0;
    L_0x0009:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x000d:
        r0 = 0;
        goto L_0x0008;
    L_0x000f:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x000d;
        */
    }

    public static <T> T a(Object obj, String str, Object... objArr) {
        try {
            return b(obj, str, objArr);
        } catch (Exception e) {
            new StringBuilder("Meet exception when call Method '").append(str).append("' in ").append(obj);
            return null;
        }
    }

    public static <T> T a(String str, String str2, Object... objArr) {
        try {
            return a(Class.forName(str), str2, objArr);
        } catch (Exception e) {
            new StringBuilder("Meet exception when call Method '").append(str2).append("' in ").append(str);
            return null;
        }
    }

    private static Method a(Class<?> cls, String str, Class<?>... clsArr) {
        while (true) {
            Method a = a(cls.getDeclaredMethods(), str, (Class[]) clsArr);
            if (a == null) {
                if (cls.getSuperclass() == null) {
                    break;
                }
                Class superclass = cls.getSuperclass();
            } else {
                a.setAccessible(true);
                return a;
            }
        }
        throw new NoSuchMethodException();
    }

    private static Method a(Method[] methodArr, String str, Class<?>[] clsArr) {
        if (str == null) {
            throw new NullPointerException("Method name must not be null.");
        }
        int length = methodArr.length;
        for (int i = 0; i < length; i++) {
            Method method = methodArr[i];
            if (method.getName().equals(str) && a(method.getParameterTypes(), (Class[]) clsArr)) {
                return method;
            }
        }
        return null;
    }

    private static boolean a(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr == null) {
            return clsArr2 == null || clsArr2.length == 0;
        } else {
            if (clsArr2 == null) {
                return clsArr.length == 0;
            } else {
                if (clsArr.length != clsArr2.length) {
                    return false;
                }
                int i = 0;
                while (i < clsArr.length) {
                    if (!clsArr[i].isAssignableFrom(clsArr2[i])) {
                        if (!a.containsKey(clsArr[i]) || !((Class) a.get(clsArr[i])).equals(a.get(clsArr2[i]))) {
                            return false;
                        }
                    }
                    i++;
                }
                return true;
            }
        }
    }

    private static Class<?>[] a(Object... objArr) {
        if (objArr == null || objArr.length <= 0) {
            return null;
        }
        Class<?>[] clsArr = new Class<?>[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            Class cls;
            int i2;
            Object obj = objArr[i];
            Class<?>[] clsArr2;
            if (obj != null && (obj instanceof a)) {
                cls = ((a) obj).a;
                i2 = i;
                clsArr2 = clsArr;
            } else if (obj == null) {
                cls = null;
                i2 = i;
                clsArr2 = clsArr;
            } else {
                cls = obj.getClass();
                i2 = i;
                clsArr2 = clsArr;
            }
            r4[i2] = cls;
        }
        return clsArr;
    }

    public static <T> T b(Object obj, String str, Object... objArr) {
        return a(obj.getClass(), str, a(objArr)).invoke(obj, b(objArr));
    }

    private static Object[] b(Object... objArr) {
        if (objArr == null || objArr.length <= 0) {
            return null;
        }
        Object[] objArr2 = new Object[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            if (obj == null || !(obj instanceof a)) {
                objArr2[i] = obj;
            } else {
                objArr2[i] = ((a) obj).b;
            }
        }
        return objArr2;
    }
}
