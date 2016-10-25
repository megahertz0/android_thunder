package com.google.zxing.datamatrix.a;

import com.google.zxing.common.d;
import com.google.zxing.e;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.android.spdy.SpdyAgent;

// compiled from: DecodedBitStreamParser.java
final class c {
    private static final char[] a;
    private static final char[] b;
    private static final char[] c;
    private static final char[] d;
    private static final char[] e;

    // compiled from: DecodedBitStreamParser.java
    private enum a {
        ;

        public static int[] a() {
            return (int[]) h.clone();
        }

        static {
            a = 1;
            b = 2;
            c = 3;
            d = 4;
            e = 5;
            f = 6;
            g = 7;
            h = new int[]{a, b, c, d, e, f, g};
        }
    }

    static {
        a = new char[]{'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        b = new char[]{'!', '\"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_'};
        c = new char[]{'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        d = b;
        e = new char[]{'`', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '{', '|', '}', '~', '\u007f'};
    }

    static d a(byte[] bArr) throws e {
        String toString;
        List list;
        com.google.zxing.common.c cVar = new com.google.zxing.common.c(bArr);
        StringBuilder stringBuilder = new StringBuilder(100);
        StringBuilder stringBuilder2 = new StringBuilder(0);
        Collection arrayList = new ArrayList(1);
        int i = a.b;
        do {
            if (i == a.b) {
                i = a(cVar, stringBuilder, stringBuilder2);
            } else {
                switch (d.a[i - 1]) {
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        a(cVar, stringBuilder);
                        break;
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        b(cVar, stringBuilder);
                        break;
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                        c(cVar, stringBuilder);
                        break;
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                        d(cVar, stringBuilder);
                        break;
                    case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        a(cVar, stringBuilder, arrayList);
                        break;
                    default:
                        throw e.a();
                }
                i = a.b;
            }
            if (i != a.a) {
            }
            if (stringBuilder2.length() > 0) {
                stringBuilder.append(stringBuilder2);
            }
            toString = stringBuilder.toString();
            if (arrayList.isEmpty()) {
                Collection collection = arrayList;
            } else {
                list = null;
            }
            return new d(bArr, toString, list, null);
        } while (cVar.a() > 0);
        if (stringBuilder2.length() > 0) {
            stringBuilder.append(stringBuilder2);
        }
        toString = stringBuilder.toString();
        if (arrayList.isEmpty()) {
            Collection collection2 = arrayList;
        } else {
            list = null;
        }
        return new d(bArr, toString, list, null);
    }

    private static int a(com.google.zxing.common.c cVar, StringBuilder stringBuilder, StringBuilder stringBuilder2) throws e {
        int i = 0;
        do {
            int a = cVar.a(XZBDevice.Wait);
            if (a == 0) {
                throw e.a();
            } else if (a <= 128) {
                if (i != 0) {
                    i = a + 128;
                } else {
                    i = a;
                }
                stringBuilder.append((char) (i - 1));
                return a.b;
            } else if (a == 129) {
                return a.a;
            } else {
                if (a <= 229) {
                    a -= 130;
                    if (a < 10) {
                        stringBuilder.append('0');
                    }
                    stringBuilder.append(a);
                } else if (a == 230) {
                    return a.c;
                } else {
                    if (a == 231) {
                        return a.g;
                    }
                    if (a == 232) {
                        stringBuilder.append('\u001d');
                    } else if (!(a == 233 || a == 234)) {
                        if (a == 235) {
                            Object obj = 1;
                        } else if (a == 236) {
                            stringBuilder.append("[)>\u001e05\u001d");
                            stringBuilder2.insert(0, "\u001e\u0004");
                        } else if (a == 237) {
                            stringBuilder.append("[)>\u001e06\u001d");
                            stringBuilder2.insert(0, "\u001e\u0004");
                        } else if (a == 238) {
                            return a.e;
                        } else {
                            if (a == 239) {
                                return a.d;
                            }
                            if (a == 240) {
                                return a.f;
                            }
                            if (!(a == 241 || a < 242 || (a == 254 && cVar.a() == 0))) {
                                throw e.a();
                            }
                        }
                    }
                }
            }
        } while (cVar.a() > 0);
        return a.b;
    }

    private static void a(com.google.zxing.common.c cVar, StringBuilder stringBuilder) throws e {
        int[] iArr = new int[3];
        Object obj = null;
        Object obj2 = null;
        while (cVar.a() != 8) {
            int a = cVar.a(XZBDevice.Wait);
            if (a != 254) {
                a(a, cVar.a(XZBDevice.Wait), iArr);
                for (a = 0; a < 3; a++) {
                    int i = iArr[a];
                    int i2;
                    switch (i2) {
                        case SpdyAgent.ACCS_TEST_SERVER:
                            if (i < 3) {
                                i2 = i + 1;
                            } else if (i < a.length) {
                                char c = a[i];
                                if (obj2 != null) {
                                    stringBuilder.append((char) (c + 128));
                                    obj2 = null;
                                } else {
                                    stringBuilder.append(c);
                                }
                            } else {
                                throw e.a();
                            }
                        case SpdyAgent.ACCS_ONLINE_SERVER:
                            if (obj2 != null) {
                                stringBuilder.append((char) (i + 128));
                                obj2 = null;
                            } else {
                                stringBuilder.append((char) i);
                            }
                            i2 = 0;
                            break;
                        case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                            if (i < b.length) {
                                char c2 = b[i];
                                if (obj2 != null) {
                                    stringBuilder.append((char) (c2 + 128));
                                    obj2 = null;
                                } else {
                                    stringBuilder.append(c2);
                                }
                            } else if (i == 27) {
                                stringBuilder.append('\u001d');
                            } else if (i == 30) {
                                obj2 = 1;
                            } else {
                                throw e.a();
                            }
                            i2 = 0;
                            break;
                        case XZBDevice.DOWNLOAD_LIST_FAILED:
                            if (obj2 != null) {
                                stringBuilder.append((char) (i + 224));
                                obj2 = null;
                            } else {
                                stringBuilder.append((char) (i + 96));
                            }
                            i2 = 0;
                            break;
                        default:
                            throw e.a();
                    }
                }
                if (cVar.a() <= 0) {
                    return;
                }
            }
            return;
        }
    }

    private static void b(com.google.zxing.common.c cVar, StringBuilder stringBuilder) throws e {
        int[] iArr = new int[3];
        Object obj = null;
        Object obj2 = null;
        while (cVar.a() != 8) {
            int a = cVar.a(XZBDevice.Wait);
            if (a != 254) {
                a(a, cVar.a(XZBDevice.Wait), iArr);
                for (a = 0; a < 3; a++) {
                    int i = iArr[a];
                    int i2;
                    char c;
                    switch (i2) {
                        case SpdyAgent.ACCS_TEST_SERVER:
                            if (i < 3) {
                                i2 = i + 1;
                            } else if (i < c.length) {
                                char c2 = c[i];
                                if (obj2 != null) {
                                    stringBuilder.append((char) (c2 + 128));
                                    obj2 = null;
                                } else {
                                    stringBuilder.append(c2);
                                }
                            } else {
                                throw e.a();
                            }
                        case SpdyAgent.ACCS_ONLINE_SERVER:
                            if (obj2 != null) {
                                stringBuilder.append((char) (i + 128));
                                obj2 = null;
                            } else {
                                stringBuilder.append((char) i);
                            }
                            i2 = 0;
                            break;
                        case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                            if (i < d.length) {
                                c = d[i];
                                if (obj2 != null) {
                                    stringBuilder.append((char) (c + 128));
                                    obj2 = null;
                                } else {
                                    stringBuilder.append(c);
                                }
                            } else if (i == 27) {
                                stringBuilder.append('\u001d');
                            } else if (i == 30) {
                                obj2 = 1;
                            } else {
                                throw e.a();
                            }
                            i2 = 0;
                            break;
                        case XZBDevice.DOWNLOAD_LIST_FAILED:
                            if (i < e.length) {
                                c = e[i];
                                if (obj2 != null) {
                                    stringBuilder.append((char) (c + 128));
                                    obj2 = null;
                                } else {
                                    stringBuilder.append(c);
                                }
                                i2 = 0;
                            } else {
                                throw e.a();
                            }
                        default:
                            throw e.a();
                    }
                }
                if (cVar.a() <= 0) {
                    return;
                }
            }
            return;
        }
    }

    private static void c(com.google.zxing.common.c cVar, StringBuilder stringBuilder) throws e {
        int[] iArr = new int[3];
        while (cVar.a() != 8) {
            int a = cVar.a(XZBDevice.Wait);
            if (a != 254) {
                a(a, cVar.a(XZBDevice.Wait), iArr);
                for (a = 0; a < 3; a++) {
                    int i = iArr[a];
                    if (i == 0) {
                        stringBuilder.append('\r');
                    } else if (i == 1) {
                        stringBuilder.append('*');
                    } else if (i == 2) {
                        stringBuilder.append('>');
                    } else if (i == 3) {
                        stringBuilder.append(' ');
                    } else if (i < 14) {
                        stringBuilder.append((char) (i + 44));
                    } else if (i < 40) {
                        stringBuilder.append((char) (i + 51));
                    } else {
                        throw e.a();
                    }
                }
                if (cVar.a() <= 0) {
                    return;
                }
            }
            return;
        }
    }

    private static void a(int i, int i2, int[] iArr) {
        int i3 = ((i << 8) + i2) - 1;
        int i4 = i3 / 1600;
        iArr[0] = i4;
        i3 -= i4 * 1600;
        i4 = i3 / 40;
        iArr[1] = i4;
        iArr[2] = i3 - (i4 * 40);
    }

    private static void d(com.google.zxing.common.c cVar, StringBuilder stringBuilder) {
        while (cVar.a() > 16) {
            for (int i = 0; i < 4; i++) {
                int a = cVar.a(R.styleable.Toolbar_contentInsetEnd);
                if (a == 31) {
                    a = 8 - cVar.b;
                    if (a != 8) {
                        cVar.a(a);
                        return;
                    }
                    return;
                }
                if ((a & 32) == 0) {
                    a |= 64;
                }
                stringBuilder.append((char) a);
            }
            if (cVar.a() <= 0) {
                return;
            }
        }
    }

    private static void a(com.google.zxing.common.c cVar, StringBuilder stringBuilder, Collection<byte[]> collection) throws e {
        int a;
        int i = cVar.a + 1;
        int i2 = i + 1;
        i = a(cVar.a(XZBDevice.Wait), i);
        if (i == 0) {
            a = cVar.a() / 8;
        } else if (i >= 250) {
            a = ((i - 249) * 250) + a(cVar.a(XZBDevice.Wait), i2);
            i2++;
        } else {
            a = i;
        }
        if (a < 0) {
            throw e.a();
        }
        Object obj = new Object[a];
        i = 0;
        while (i < a) {
            if (cVar.a() < 8) {
                throw e.a();
            }
            int i3 = i2 + 1;
            obj[i] = (byte) a(cVar.a(XZBDevice.Wait), i2);
            i++;
            i2 = i3;
        }
        collection.add(obj);
        try {
            stringBuilder.append(new String(obj, "ISO8859_1"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(new StringBuilder("Platform does not support required encoding: ").append(e).toString());
        }
    }

    private static int a(int i, int i2) {
        int i3 = i - (((i2 * 149) % 255) + 1);
        return i3 >= 0 ? i3 : i3 + 256;
    }
}
