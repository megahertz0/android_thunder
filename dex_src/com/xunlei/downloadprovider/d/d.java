package com.xunlei.downloadprovider.d;

import android.text.TextUtils;
import com.xunlei.xllib.R;
import com.xunlei.xllib.b.k;
import java.io.UnsupportedEncodingException;
import java.util.Map;

// compiled from: UrlQueryParser.java
public final class d {
    public static void a(Map<String, String> map, String str, String str2) throws UnsupportedEncodingException {
        if (!TextUtils.isEmpty(str)) {
            String b = k.b(str, str2);
            if (!TextUtils.isEmpty(b)) {
                int indexOf = b.indexOf("?");
                if (indexOf != -1 && !b.endsWith("?")) {
                    byte[] bytes;
                    try {
                        bytes = b.substring(indexOf + 1).getBytes(str2);
                    } catch (UnsupportedEncodingException e) {
                        bytes = null;
                    }
                    if (bytes != null && bytes.length > 0) {
                        b = null;
                        indexOf = 0;
                        int i = 0;
                        while (i < bytes.length) {
                            int i2 = i + 1;
                            byte b2 = bytes[i];
                            switch ((char) b2) {
                                case R.styleable.AppCompatTheme_actionModeFindDrawable:
                                    i = indexOf + 1;
                                    int i3 = i2 + 1;
                                    int a = a(bytes[i2]) << 4;
                                    i2 = i3 + 1;
                                    bytes[indexOf] = (byte) (a(bytes[i3]) + a);
                                    indexOf = i;
                                    i = i2;
                                    break;
                                case R.styleable.AppCompatTheme_actionModeWebSearchDrawable:
                                    String str3 = new String(bytes, 0, indexOf, str2);
                                    if (b != null) {
                                        b(map, b, str3);
                                        b = null;
                                    }
                                    indexOf = 0;
                                    i = i2;
                                    break;
                                case R.styleable.AppCompatTheme_dialogPreferredPadding:
                                    i = indexOf + 1;
                                    bytes[indexOf] = (byte) 32;
                                    indexOf = i;
                                    i = i2;
                                    break;
                                case R.styleable.AppCompatTheme_popupWindowStyle:
                                    if (b == null) {
                                        b = new String(bytes, 0, indexOf, str2);
                                        indexOf = 0;
                                        i = i2;
                                    } else {
                                        i = indexOf + 1;
                                        bytes[indexOf] = b2;
                                        indexOf = i;
                                        i = i2;
                                    }
                                    break;
                                default:
                                    i = indexOf + 1;
                                    bytes[indexOf] = b2;
                                    indexOf = i;
                                    i = i2;
                                    break;
                            }
                        }
                        if (b != null) {
                            b(map, b, new String(bytes, 0, indexOf, str2));
                        }
                    }
                }
            }
        }
    }

    private static void b(Map<String, String> map, String str, String str2) {
        map.put(str, str2);
    }

    private static byte a(byte b) {
        if (b >= 48 && b <= 57) {
            return (byte) (b - 48);
        }
        if (b < 97 || b > 102) {
            return (b < 65 || b > 70) ? (byte) 0 : (byte) ((b - 65) + 10);
        } else {
            return (byte) ((b - 97) + 10);
        }
    }
}
