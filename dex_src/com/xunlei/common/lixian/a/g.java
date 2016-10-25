package com.xunlei.common.lixian.a;

import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.common.lixian.a.a.b;
import com.xunlei.xllib.R;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class g {
    private String a;
    private Class b;
    private int c;
    private Object d;
    private /* synthetic */ f e;

    public g(f fVar, String str, Class cls, int i, Object obj) {
        this.a = str;
        this.b = cls;
        this.c = i;
        a(obj);
    }

    private static ByteBuffer a(Map map) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        a(map, byteArrayOutputStream);
        byteArrayOutputStream.close();
        return ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
    }

    private void a(int i) {
        this.c = i;
    }

    public static void a(Number number, OutputStream outputStream) {
        outputStream.write(R.styleable.AppCompatTheme_radioButtonStyle);
        outputStream.write(number.toString().getBytes(CharsetConvert.UTF_8));
        outputStream.write(R.styleable.AppCompatTheme_buttonStyleSmall);
    }

    public static void a(Object obj, OutputStream outputStream) {
        Object a = obj instanceof b ? ((b) obj).a() : obj;
        if (a instanceof String) {
            a((String) a, outputStream);
        } else if (a instanceof byte[]) {
            a((byte[]) a, outputStream);
        } else if (a instanceof Number) {
            Number number = (Number) a;
            outputStream.write(R.styleable.AppCompatTheme_radioButtonStyle);
            outputStream.write(number.toString().getBytes(CharsetConvert.UTF_8));
            outputStream.write(R.styleable.AppCompatTheme_buttonStyleSmall);
        } else if (a instanceof List) {
            List<Object> list = (List) a;
            outputStream.write(R.styleable.AppCompatTheme_ratingBarStyleSmall);
            for (Object a2 : list) {
                a(a2, outputStream);
            }
            outputStream.write(R.styleable.AppCompatTheme_buttonStyleSmall);
        } else if (a2 instanceof Map) {
            a((Map) a2, outputStream);
        } else {
            throw new IllegalArgumentException(new StringBuilder("Cannot bencode: ").append(a2.getClass()).toString());
        }
    }

    public static void a(String str, OutputStream outputStream) {
        a(str.getBytes(CharsetConvert.UTF_8), outputStream);
    }

    public static void a(List list, OutputStream outputStream) {
        outputStream.write(R.styleable.AppCompatTheme_ratingBarStyleSmall);
        for (Object obj : list) {
            a(obj, outputStream);
        }
        outputStream.write(R.styleable.AppCompatTheme_buttonStyleSmall);
    }

    public static void a(Map map, OutputStream outputStream) {
        outputStream.write(R.styleable.AppCompatTheme_buttonStyle);
        List<String> arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        for (String str : arrayList) {
            Object obj = map.get(str);
            a(str, outputStream);
            a(obj, outputStream);
        }
        outputStream.write(R.styleable.AppCompatTheme_buttonStyleSmall);
    }

    public static void a(byte[] bArr, OutputStream outputStream) {
        outputStream.write(Integer.toString(bArr.length).getBytes(CharsetConvert.UTF_8));
        outputStream.write(R.styleable.AppCompatTheme_toolbarStyle);
        outputStream.write(bArr);
    }

    private int e() {
        return Integer.valueOf(this.d.toString()).intValue();
    }

    private long f() {
        return Long.valueOf(this.d.toString()).longValue();
    }

    private int g() {
        return Short.valueOf(this.d.toString()).shortValue();
    }

    private short h() {
        return (short) Byte.valueOf(this.d.toString()).byteValue();
    }

    private String i() {
        return this.d.toString();
    }

    public final Object a() {
        return this.d;
    }

    public final boolean a(Object obj) {
        if (obj != null && !this.b.equals(obj.getClass())) {
            return false;
        }
        this.d = obj;
        return true;
    }

    public final int b() {
        return this.c;
    }

    public final Class c() {
        return this.b;
    }

    public final String d() {
        return this.a;
    }
}
