package com.google.zxing.pdf417.encoder;

import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.r;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;
import org.android.agoo.message.MessageService;
import org.android.spdy.SpdyAgent;

// compiled from: PDF417HighLevelEncoder.java
public final class f {
    private static final byte[] a;
    private static final byte[] b;
    private static final byte[] c;
    private static final byte[] d;
    private static final Charset e;

    static {
        byte b;
        byte b2 = (byte) 0;
        a = new byte[]{(byte) 48, (byte) 49, (byte) 50, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 38, (byte) 13, (byte) 9, (byte) 44, (byte) 58, (byte) 35, (byte) 45, (byte) 46, (byte) 36, (byte) 47, (byte) 43, (byte) 37, (byte) 42, (byte) 61, (byte) 94, (byte) 0, (byte) 32, (byte) 0, (byte) 0, (byte) 0};
        b = new byte[]{(byte) 59, (byte) 60, (byte) 62, (byte) 64, (byte) 91, (byte) 92, (byte) 93, (byte) 95, (byte) 96, (byte) 126, (byte) 33, (byte) 13, (byte) 9, (byte) 44, (byte) 58, (byte) 10, (byte) 45, (byte) 46, (byte) 36, (byte) 47, (byte) 34, (byte) 124, (byte) 42, (byte) 40, (byte) 41, (byte) 63, (byte) 123, (byte) 125, (byte) 39, (byte) 0};
        c = new byte[128];
        d = new byte[128];
        e = Charset.forName("ISO-8859-1");
        Arrays.fill(c, (byte) -1);
        for (b = (byte) 0; b < a.length; b = (byte) (b + 1)) {
            byte b3 = a[b];
            if (b3 > null) {
                c[b3] = b;
            }
        }
        Arrays.fill(d, (byte) -1);
        while (b2 < b.length) {
            b = b[b2];
            if (b > null) {
                d[b] = b2;
            }
            b2 = (byte) (b2 + 1);
        }
    }

    public static String a(String str, Compaction compaction, Charset charset) throws r {
        int value;
        StringBuilder stringBuilder = new StringBuilder(str.length());
        if (charset == null) {
            charset = e;
        } else if (!e.equals(charset)) {
            CharacterSetECI characterSetECIByName = CharacterSetECI.getCharacterSetECIByName(charset.name());
            if (characterSetECIByName != null) {
                value = characterSetECIByName.getValue();
                if (value >= 0 && value < 900) {
                    stringBuilder.append('\u039f');
                    stringBuilder.append((char) value);
                } else if (value < 810900) {
                    stringBuilder.append('\u039e');
                    stringBuilder.append((char) ((value / 900) - 1));
                    stringBuilder.append((char) (value % 900));
                } else if (value < 811800) {
                    stringBuilder.append('\u039d');
                    stringBuilder.append((char) (810900 - value));
                } else {
                    throw new r(new StringBuilder("ECI number not in valid range from 0..811799, but was ").append(value).toString());
                }
            }
        }
        int length = str.length();
        if (compaction == Compaction.TEXT) {
            a(str, 0, length, stringBuilder, 0);
        } else if (compaction == Compaction.BYTE) {
            byte[] bytes = str.getBytes(charset);
            a(bytes, bytes.length, 1, stringBuilder);
        } else if (compaction == Compaction.NUMERIC) {
            stringBuilder.append('\u0386');
            a(str, 0, length, stringBuilder);
        } else {
            value = 0;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int a = a(str, i);
                if (a >= 13) {
                    stringBuilder.append('\u0386');
                    i2 = XZBDevice.DOWNLOAD_LIST_RECYCLE;
                    a(str, i, a, stringBuilder);
                    i += a;
                    value = 0;
                } else {
                    int b = b(str, i);
                    if (b >= 5 || a == length) {
                        if (i2 != 0) {
                            stringBuilder.append('\u0384');
                            value = 0;
                            i2 = 0;
                        }
                        value = a(str, i, b, stringBuilder, value);
                        i += b;
                    } else {
                        a = a(str, i, charset);
                        if (a == 0) {
                            a = 1;
                        }
                        byte[] bytes2 = str.substring(i, i + a).getBytes(charset);
                        if (bytes2.length == 1 && i2 == 0) {
                            a(bytes2, 1, 0, stringBuilder);
                        } else {
                            a(bytes2, bytes2.length, i2, stringBuilder);
                            value = 0;
                            i2 = 1;
                        }
                        i = a + i;
                    }
                }
            }
        }
        return stringBuilder.toString();
    }

    private static int a(CharSequence charSequence, int i, int i2, StringBuilder stringBuilder, int i3) {
        StringBuilder stringBuilder2 = new StringBuilder(i2);
        int i4 = 0;
        while (true) {
            char charAt = charSequence.charAt(i + i4);
            int length;
            int i5;
            int i6;
            Object obj;
            char charAt2;
            switch (i3) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    if (b(charAt)) {
                        if (charAt == ' ') {
                            stringBuilder2.append('\u001a');
                        } else {
                            stringBuilder2.append((char) (charAt - 65));
                        }
                    } else if (c(charAt)) {
                        stringBuilder2.append('\u001b');
                        i3 = 1;
                    } else if (d(charAt)) {
                        i3 = XZBDevice.DOWNLOAD_LIST_RECYCLE;
                        stringBuilder2.append('\u001c');
                    } else {
                        stringBuilder2.append('\u001d');
                        stringBuilder2.append((char) d[charAt]);
                    }
                    i4++;
                    if (i4 < i2) {
                        length = stringBuilder2.length();
                        i5 = 0;
                        i6 = 0;
                        while (i6 < length) {
                            if (i6 % 2 == 0) {
                                obj = null;
                            } else {
                                i4 = 1;
                            }
                            if (obj == null) {
                                charAt2 = stringBuilder2.charAt(i6);
                            } else {
                                charAt2 = (char) ((i5 * 30) + stringBuilder2.charAt(i6));
                                stringBuilder.append(charAt2);
                            }
                            i6++;
                            charAt = charAt2;
                        }
                        if (length % 2 != 0) {
                            stringBuilder.append((char) ((i5 * 30) + 29));
                        }
                        return i3;
                    }
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    if (c(charAt)) {
                        if (charAt == ' ') {
                            stringBuilder2.append('\u001a');
                        } else {
                            stringBuilder2.append((char) (charAt - 97));
                        }
                    } else if (b(charAt)) {
                        stringBuilder2.append('\u001b');
                        stringBuilder2.append((char) (charAt - 65));
                    } else if (d(charAt)) {
                        i3 = XZBDevice.DOWNLOAD_LIST_RECYCLE;
                        stringBuilder2.append('\u001c');
                    } else {
                        stringBuilder2.append('\u001d');
                        stringBuilder2.append((char) d[charAt]);
                    }
                    i4++;
                    if (i4 < i2) {
                        length = stringBuilder2.length();
                        i5 = 0;
                        i6 = 0;
                        while (i6 < length) {
                            if (i6 % 2 == 0) {
                                i4 = 1;
                            } else {
                                obj = null;
                            }
                            if (obj == null) {
                                charAt2 = (char) ((i5 * 30) + stringBuilder2.charAt(i6));
                                stringBuilder.append(charAt2);
                            } else {
                                charAt2 = stringBuilder2.charAt(i6);
                            }
                            i6++;
                            charAt = charAt2;
                        }
                        if (length % 2 != 0) {
                            stringBuilder.append((char) ((i5 * 30) + 29));
                        }
                        return i3;
                    }
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    if (d(charAt)) {
                        stringBuilder2.append((char) c[charAt]);
                    } else if (b(charAt)) {
                        stringBuilder2.append('\u001c');
                        i3 = 0;
                    } else if (c(charAt)) {
                        stringBuilder2.append('\u001b');
                        i3 = 1;
                    } else if ((i + i4) + 1 >= i2 || !e(charSequence.charAt((i + i4) + 1))) {
                        stringBuilder2.append('\u001d');
                        stringBuilder2.append((char) d[charAt]);
                    } else {
                        i3 = XZBDevice.DOWNLOAD_LIST_FAILED;
                        stringBuilder2.append('\u0019');
                    }
                    i4++;
                    if (i4 < i2) {
                        length = stringBuilder2.length();
                        i5 = 0;
                        i6 = 0;
                        while (i6 < length) {
                            if (i6 % 2 == 0) {
                                obj = null;
                            } else {
                                i4 = 1;
                            }
                            if (obj == null) {
                                charAt2 = stringBuilder2.charAt(i6);
                            } else {
                                charAt2 = (char) ((i5 * 30) + stringBuilder2.charAt(i6));
                                stringBuilder.append(charAt2);
                            }
                            i6++;
                            charAt = charAt2;
                        }
                        if (length % 2 != 0) {
                            stringBuilder.append((char) ((i5 * 30) + 29));
                        }
                        return i3;
                    }
                default:
                    if (e(charAt)) {
                        stringBuilder2.append((char) d[charAt]);
                        i4++;
                        if (i4 < i2) {
                            length = stringBuilder2.length();
                            i5 = 0;
                            i6 = 0;
                            while (i6 < length) {
                                if (i6 % 2 == 0) {
                                    i4 = 1;
                                } else {
                                    obj = null;
                                }
                                if (obj == null) {
                                    charAt2 = (char) ((i5 * 30) + stringBuilder2.charAt(i6));
                                    stringBuilder.append(charAt2);
                                } else {
                                    charAt2 = stringBuilder2.charAt(i6);
                                }
                                i6++;
                                charAt = charAt2;
                            }
                            if (length % 2 != 0) {
                                stringBuilder.append((char) ((i5 * 30) + 29));
                            }
                            return i3;
                        }
                    } else {
                        stringBuilder2.append('\u001d');
                        i3 = 0;
                    }
                    break;
            }
        }
    }

    private static void a(byte[] bArr, int i, int i2, StringBuilder stringBuilder) {
        int i3;
        Object obj = 1;
        if (i == 1 && i2 == 0) {
            stringBuilder.append('\u0391');
        } else {
            if (i % 6 != 0) {
                obj = null;
            }
            if (obj != null) {
                stringBuilder.append('\u039c');
            } else {
                stringBuilder.append('\u0385');
            }
        }
        if (i >= 6) {
            char[] cArr = new char[5];
            i3 = 0;
            while ((i + 0) - i3 >= 6) {
                int i4;
                long j = 0;
                for (i4 = 0; i4 < 6; i4++) {
                    j = (j << 8) + ((long) (bArr[i3 + i4] & 255));
                }
                for (i4 = 0; i4 < 5; i4++) {
                    cArr[i4] = (char) ((int) (j % 900));
                    j /= 900;
                }
                for (int i5 = XZBDevice.DOWNLOAD_LIST_ALL; i5 >= 0; i5--) {
                    stringBuilder.append(cArr[i5]);
                }
                i3 += 6;
            }
        } else {
            i3 = 0;
        }
        while (i3 < i + 0) {
            stringBuilder.append((char) (bArr[i3] & 255));
            i3++;
        }
    }

    private static void a(String str, int i, int i2, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder((i2 / 3) + 1);
        BigInteger valueOf = BigInteger.valueOf(900);
        BigInteger valueOf2 = BigInteger.valueOf(0);
        int i3 = 0;
        while (i3 < i2) {
            stringBuilder2.setLength(0);
            int min = Math.min(R.styleable.AppCompatTheme_listDividerAlertDialog, i2 - i3);
            BigInteger bigInteger = new BigInteger(new StringBuilder(MessageService.MSG_DB_NOTIFY_REACHED).append(str.substring(i + i3, (i + i3) + min)).toString());
            do {
                stringBuilder2.append((char) bigInteger.mod(valueOf).intValue());
                bigInteger = bigInteger.divide(valueOf);
            } while (!bigInteger.equals(valueOf2));
            for (int length = stringBuilder2.length() - 1; length >= 0; length--) {
                stringBuilder.append(stringBuilder2.charAt(length));
            }
            i3 += min;
        }
    }

    private static boolean a(char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean b(char c) {
        return c == ' ' || (c >= 'A' && c <= 'Z');
    }

    private static boolean c(char c) {
        return c == ' ' || (c >= 'a' && c <= 'z');
    }

    private static boolean d(char c) {
        return c[c] != -1;
    }

    private static boolean e(char c) {
        return d[c] != -1;
    }

    private static int a(CharSequence charSequence, int i) {
        int i2 = 0;
        int length = charSequence.length();
        if (i < length) {
            char charAt = charSequence.charAt(i);
            while (a(charAt) && i < length) {
                i2++;
                i++;
                if (i < length) {
                    charAt = charSequence.charAt(i);
                }
            }
        }
        return i2;
    }

    private static int b(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = i;
        while (i2 < length) {
            char charAt = charSequence.charAt(i2);
            int i3 = 0;
            while (i3 < 13 && a(r3) && i2 < length) {
                i3++;
                int i4 = i2 + 1;
                if (i4 < length) {
                    charAt = charSequence.charAt(i4);
                    i2 = i4;
                } else {
                    i2 = i4;
                }
            }
            if (i3 >= 13) {
                return (i2 - i) - i3;
            }
            if (i3 <= 0) {
                Object obj;
                char charAt2 = charSequence.charAt(i2);
                if (charAt2 == '\t' || charAt2 == '\n' || charAt2 == '\r' || (charAt2 >= ' ' && charAt2 <= '~')) {
                    obj = 1;
                } else {
                    obj = null;
                }
                if (obj == null) {
                    break;
                }
                i2++;
            }
        }
        return i2 - i;
    }

    private static int a(String str, int i, Charset charset) throws r {
        CharsetEncoder newEncoder = charset.newEncoder();
        int length = str.length();
        int i2 = i;
        while (i2 < length) {
            char charAt = str.charAt(i2);
            int i3 = 0;
            while (i3 < 13 && a(r1)) {
                i3++;
                int i4 = i2 + i3;
                if (i4 >= length) {
                    break;
                }
                charAt = str.charAt(i4);
            }
            if (i3 >= 13) {
                return i2 - i;
            }
            char charAt2 = str.charAt(i2);
            if (newEncoder.canEncode(charAt2)) {
                i2++;
            } else {
                throw new r(new StringBuilder("Non-encodable character detected: ").append(charAt2).append(" (Unicode: ").append(charAt2).append(')').toString());
            }
        }
        return i2 - i;
    }
}
