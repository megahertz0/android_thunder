package com.xunlei.common.lixian.a.a;

import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.common.lixian.a.g;
import com.xunlei.xllib.R;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class c {
    private static ByteBuffer a(Map map) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        g.a(map, byteArrayOutputStream);
        byteArrayOutputStream.close();
        return ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
    }

    public static void a(Number number, OutputStream outputStream) {
        outputStream.write(R.styleable.AppCompatTheme_radioButtonStyle);
        outputStream.write(number.toString().getBytes(CharsetConvert.UTF_8));
        outputStream.write(R.styleable.AppCompatTheme_buttonStyleSmall);
    }

    public static void a(Object obj, OutputStream outputStream) {
        Object a = obj instanceof b ? ((b) obj).a() : obj;
        if (a instanceof String) {
            g.a((String) a, outputStream);
        } else if (a instanceof byte[]) {
            g.a((byte[]) a, outputStream);
        } else if (a instanceof Number) {
            Number number = (Number) a;
            outputStream.write(R.styleable.AppCompatTheme_radioButtonStyle);
            outputStream.write(number.toString().getBytes(CharsetConvert.UTF_8));
            outputStream.write(R.styleable.AppCompatTheme_buttonStyleSmall);
        } else if (a instanceof List) {
            List<Object> list = (List) a;
            outputStream.write(R.styleable.AppCompatTheme_ratingBarStyleSmall);
            for (Object a2 : list) {
                g.a(a2, outputStream);
            }
            outputStream.write(R.styleable.AppCompatTheme_buttonStyleSmall);
        } else if (a2 instanceof Map) {
            g.a((Map) a2, outputStream);
        } else {
            throw new IllegalArgumentException(new StringBuilder("Cannot bencode: ").append(a2.getClass()).toString());
        }
    }

    public static void a(String str, OutputStream outputStream) {
        g.a(str.getBytes(CharsetConvert.UTF_8), outputStream);
    }

    public static void a(List list, OutputStream outputStream) {
        outputStream.write(R.styleable.AppCompatTheme_ratingBarStyleSmall);
        for (Object obj : list) {
            g.a(obj, outputStream);
        }
        outputStream.write(R.styleable.AppCompatTheme_buttonStyleSmall);
    }

    public static void a(Map map, OutputStream outputStream) {
        outputStream.write(R.styleable.AppCompatTheme_buttonStyle);
        List<String> arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        for (String str : arrayList) {
            Object obj = map.get(str);
            g.a(str, outputStream);
            g.a(obj, outputStream);
        }
        outputStream.write(R.styleable.AppCompatTheme_buttonStyleSmall);
    }

    public static void a(byte[] bArr, OutputStream outputStream) {
        outputStream.write(Integer.toString(bArr.length).getBytes(CharsetConvert.UTF_8));
        outputStream.write(R.styleable.AppCompatTheme_toolbarStyle);
        outputStream.write(bArr);
    }
}
