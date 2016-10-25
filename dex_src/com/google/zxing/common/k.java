package com.google.zxing.common;

import com.google.zxing.DecodeHintType;
import java.nio.charset.Charset;
import java.util.Map;

// compiled from: StringUtils.java
public final class k {
    private static final String a;
    private static final boolean b;

    static {
        a = Charset.defaultCharset().name();
        boolean z = "SJIS".equalsIgnoreCase(a) || "EUC_JP".equalsIgnoreCase(a);
        b = z;
    }

    public static String a(byte[] bArr, Map<DecodeHintType, ?> map) {
        Object obj;
        if (map != null) {
            String str = (String) map.get(DecodeHintType.CHARACTER_SET);
            if (str != null) {
                return str;
            }
        }
        int length = bArr.length;
        Object obj2 = 1;
        Object obj3 = 1;
        Object obj4 = 1;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        Object obj5 = null;
        Object obj6 = null;
        int i8 = 0;
        Object obj7 = (bArr.length > 3 && bArr[0] == -17 && bArr[1] == -69 && bArr[2] == -65) ? 1 : null;
        int i9 = 0;
        int i10 = 0;
        while (i9 < length) {
            if (obj2 == null && obj3 == null && obj4 == null) {
                break;
            }
            int i11;
            Object obj8;
            int i12;
            Object obj9;
            int i13;
            int i14;
            Object obj10;
            int i15;
            int i16 = bArr[i9] & 255;
            if (obj4 != null) {
                if (i10 > 0) {
                    if ((i16 & 128) != 0) {
                        i11 = i10 - 1;
                        obj8 = obj4;
                        if (obj2 != null) {
                            if (i16 <= 127 && i16 < 160) {
                                i12 = i8;
                                obj9 = null;
                                if (obj3 == null) {
                                    i10 = i13;
                                    i13 = i14;
                                    i14 = i7;
                                    i7 = i6;
                                    i6 = i5;
                                    i5 = i4;
                                    obj10 = obj3;
                                } else if (i4 > 0) {
                                    if (i16 != 128) {
                                    }
                                    i15 = i13;
                                    i13 = i14;
                                    i14 = i7;
                                    i7 = i6;
                                    i6 = i5;
                                    i5 = i4;
                                    obj10 = null;
                                    i10 = i15;
                                } else {
                                    if (i16 >= 64) {
                                    }
                                    i15 = i13;
                                    i13 = i14;
                                    i14 = i7;
                                    i7 = i6;
                                    i6 = i5;
                                    i5 = i4;
                                    obj10 = null;
                                    i10 = i15;
                                }
                                i9++;
                                obj2 = obj9;
                                obj3 = obj10;
                                i8 = i12;
                                i4 = i5;
                                obj4 = obj8;
                                i5 = i6;
                                i6 = i7;
                                i7 = i14;
                                i14 = i13;
                                i13 = i10;
                                i10 = i11;
                            } else if (i16 > 159 && (i16 < 192 || i16 == 215 || i16 == 247)) {
                                i12 = i8 + 1;
                                obj9 = obj2;
                                if (obj3 == null) {
                                    i10 = i13;
                                    i13 = i14;
                                    i14 = i7;
                                    i7 = i6;
                                    i6 = i5;
                                    i5 = i4;
                                    obj10 = obj3;
                                } else if (i4 > 0) {
                                    if (i16 >= 64 || i16 == 127 || i16 > 252) {
                                        i15 = i13;
                                        i13 = i14;
                                        i14 = i7;
                                        i7 = i6;
                                        i6 = i5;
                                        i5 = i4;
                                        obj10 = null;
                                        i10 = i15;
                                    } else {
                                        i10 = i4 - 1;
                                        obj10 = obj3;
                                        i15 = i14;
                                        i14 = i7;
                                        i7 = i6;
                                        i6 = i5;
                                        i5 = i10;
                                        i10 = i13;
                                        i13 = i15;
                                    }
                                } else if (i16 != 128 || i16 == 160 || i16 > 239) {
                                    i15 = i13;
                                    i13 = i14;
                                    i14 = i7;
                                    i7 = i6;
                                    i6 = i5;
                                    i5 = i4;
                                    obj10 = null;
                                    i10 = i15;
                                } else if (i16 > 160 && i16 < 224) {
                                    i5++;
                                    i10 = i6 + 1;
                                    if (i10 > i14) {
                                        obj5 = null;
                                        i6 = i5;
                                        i7 = i10;
                                        i5 = i4;
                                        obj10 = obj3;
                                        i15 = i13;
                                        i13 = i10;
                                        i10 = i15;
                                    } else {
                                        i6 = i5;
                                        i5 = i4;
                                        obj10 = obj3;
                                        i15 = i10;
                                        i10 = i13;
                                        i13 = i14;
                                        obj5 = null;
                                        i7 = i15;
                                    }
                                } else if (i16 > 127) {
                                    i4++;
                                    i10 = i7 + 1;
                                    if (i10 > i13) {
                                        i13 = i14;
                                        r6 = null;
                                        i14 = i10;
                                        i6 = i5;
                                        i5 = i4;
                                        obj10 = obj3;
                                    } else {
                                        r6 = null;
                                        i6 = i5;
                                        i5 = i4;
                                        obj10 = obj3;
                                        i15 = i14;
                                        i14 = i10;
                                        i10 = i13;
                                        i13 = i15;
                                    }
                                } else {
                                    r6 = null;
                                    i6 = i5;
                                    i5 = i4;
                                    obj10 = obj3;
                                    i15 = i13;
                                    i13 = i14;
                                    obj5 = null;
                                    i10 = i15;
                                }
                                i9++;
                                obj2 = obj9;
                                obj3 = obj10;
                                i8 = i12;
                                i4 = i5;
                                obj4 = obj8;
                                i5 = i6;
                                i6 = i7;
                                i7 = i14;
                                i14 = i13;
                                i13 = i10;
                                i10 = i11;
                            }
                        }
                        i12 = i8;
                        obj9 = obj2;
                        if (obj3 == null) {
                            i10 = i13;
                            i13 = i14;
                            i14 = i7;
                            i7 = i6;
                            i6 = i5;
                            i5 = i4;
                            obj10 = obj3;
                        } else if (i4 > 0) {
                            if (i16 >= 64) {
                            }
                            i15 = i13;
                            i13 = i14;
                            i14 = i7;
                            i7 = i6;
                            i6 = i5;
                            i5 = i4;
                            obj10 = null;
                            i10 = i15;
                        } else {
                            if (i16 != 128) {
                            }
                            i15 = i13;
                            i13 = i14;
                            i14 = i7;
                            i7 = i6;
                            i6 = i5;
                            i5 = i4;
                            obj10 = null;
                            i10 = i15;
                        }
                        i9++;
                        obj2 = obj9;
                        obj3 = obj10;
                        i8 = i12;
                        i4 = i5;
                        obj4 = obj8;
                        i5 = i6;
                        i6 = i7;
                        i7 = i14;
                        i14 = i13;
                        i13 = i10;
                        i10 = i11;
                    }
                } else if ((i16 & 128) != 0) {
                    if ((i16 & 64) != 0) {
                        i11 = i10 + 1;
                        if ((i16 & 32) == 0) {
                            i++;
                            obj8 = obj4;
                        } else {
                            i11++;
                            if ((i16 & 16) == 0) {
                                i2++;
                                obj8 = obj4;
                            } else {
                                i10 = i11 + 1;
                                if ((i16 & 8) == 0) {
                                    i3++;
                                    i11 = i10;
                                    obj8 = obj4;
                                }
                            }
                        }
                        if (obj2 != null) {
                            if (i16 <= 127) {
                            }
                            i12 = i8 + 1;
                            obj9 = obj2;
                            if (obj3 == null) {
                                i10 = i13;
                                i13 = i14;
                                i14 = i7;
                                i7 = i6;
                                i6 = i5;
                                i5 = i4;
                                obj10 = obj3;
                            } else if (i4 > 0) {
                                if (i16 != 128) {
                                }
                                i15 = i13;
                                i13 = i14;
                                i14 = i7;
                                i7 = i6;
                                i6 = i5;
                                i5 = i4;
                                obj10 = null;
                                i10 = i15;
                            } else {
                                if (i16 >= 64) {
                                }
                                i15 = i13;
                                i13 = i14;
                                i14 = i7;
                                i7 = i6;
                                i6 = i5;
                                i5 = i4;
                                obj10 = null;
                                i10 = i15;
                            }
                            i9++;
                            obj2 = obj9;
                            obj3 = obj10;
                            i8 = i12;
                            i4 = i5;
                            obj4 = obj8;
                            i5 = i6;
                            i6 = i7;
                            i7 = i14;
                            i14 = i13;
                            i13 = i10;
                            i10 = i11;
                        }
                        i12 = i8;
                        obj9 = obj2;
                        if (obj3 == null) {
                            i10 = i13;
                            i13 = i14;
                            i14 = i7;
                            i7 = i6;
                            i6 = i5;
                            i5 = i4;
                            obj10 = obj3;
                        } else if (i4 > 0) {
                            if (i16 >= 64) {
                            }
                            i15 = i13;
                            i13 = i14;
                            i14 = i7;
                            i7 = i6;
                            i6 = i5;
                            i5 = i4;
                            obj10 = null;
                            i10 = i15;
                        } else {
                            if (i16 != 128) {
                            }
                            i15 = i13;
                            i13 = i14;
                            i14 = i7;
                            i7 = i6;
                            i6 = i5;
                            i5 = i4;
                            obj10 = null;
                            i10 = i15;
                        }
                        i9++;
                        obj2 = obj9;
                        obj3 = obj10;
                        i8 = i12;
                        i4 = i5;
                        obj4 = obj8;
                        i5 = i6;
                        i6 = i7;
                        i7 = i14;
                        i14 = i13;
                        i13 = i10;
                        i10 = i11;
                    }
                }
                i11 = i10;
                obj8 = null;
                if (obj2 != null) {
                    if (i16 <= 127) {
                    }
                    i12 = i8 + 1;
                    obj9 = obj2;
                    if (obj3 == null) {
                        i10 = i13;
                        i13 = i14;
                        i14 = i7;
                        i7 = i6;
                        i6 = i5;
                        i5 = i4;
                        obj10 = obj3;
                    } else if (i4 > 0) {
                        if (i16 != 128) {
                        }
                        i15 = i13;
                        i13 = i14;
                        i14 = i7;
                        i7 = i6;
                        i6 = i5;
                        i5 = i4;
                        obj10 = null;
                        i10 = i15;
                    } else {
                        if (i16 >= 64) {
                        }
                        i15 = i13;
                        i13 = i14;
                        i14 = i7;
                        i7 = i6;
                        i6 = i5;
                        i5 = i4;
                        obj10 = null;
                        i10 = i15;
                    }
                    i9++;
                    obj2 = obj9;
                    obj3 = obj10;
                    i8 = i12;
                    i4 = i5;
                    obj4 = obj8;
                    i5 = i6;
                    i6 = i7;
                    i7 = i14;
                    i14 = i13;
                    i13 = i10;
                    i10 = i11;
                }
                i12 = i8;
                obj9 = obj2;
                if (obj3 == null) {
                    i10 = i13;
                    i13 = i14;
                    i14 = i7;
                    i7 = i6;
                    i6 = i5;
                    i5 = i4;
                    obj10 = obj3;
                } else if (i4 > 0) {
                    if (i16 >= 64) {
                    }
                    i15 = i13;
                    i13 = i14;
                    i14 = i7;
                    i7 = i6;
                    i6 = i5;
                    i5 = i4;
                    obj10 = null;
                    i10 = i15;
                } else {
                    if (i16 != 128) {
                    }
                    i15 = i13;
                    i13 = i14;
                    i14 = i7;
                    i7 = i6;
                    i6 = i5;
                    i5 = i4;
                    obj10 = null;
                    i10 = i15;
                }
                i9++;
                obj2 = obj9;
                obj3 = obj10;
                i8 = i12;
                i4 = i5;
                obj4 = obj8;
                i5 = i6;
                i6 = i7;
                i7 = i14;
                i14 = i13;
                i13 = i10;
                i10 = i11;
            }
            i11 = i10;
            obj8 = obj4;
            if (obj2 != null) {
                if (i16 <= 127) {
                }
                i12 = i8 + 1;
                obj9 = obj2;
                if (obj3 == null) {
                    i10 = i13;
                    i13 = i14;
                    i14 = i7;
                    i7 = i6;
                    i6 = i5;
                    i5 = i4;
                    obj10 = obj3;
                } else if (i4 > 0) {
                    if (i16 != 128) {
                    }
                    i15 = i13;
                    i13 = i14;
                    i14 = i7;
                    i7 = i6;
                    i6 = i5;
                    i5 = i4;
                    obj10 = null;
                    i10 = i15;
                } else {
                    if (i16 >= 64) {
                    }
                    i15 = i13;
                    i13 = i14;
                    i14 = i7;
                    i7 = i6;
                    i6 = i5;
                    i5 = i4;
                    obj10 = null;
                    i10 = i15;
                }
                i9++;
                obj2 = obj9;
                obj3 = obj10;
                i8 = i12;
                i4 = i5;
                obj4 = obj8;
                i5 = i6;
                i6 = i7;
                i7 = i14;
                i14 = i13;
                i13 = i10;
                i10 = i11;
            }
            i12 = i8;
            obj9 = obj2;
            if (obj3 == null) {
                i10 = i13;
                i13 = i14;
                i14 = i7;
                i7 = i6;
                i6 = i5;
                i5 = i4;
                obj10 = obj3;
            } else if (i4 > 0) {
                if (i16 >= 64) {
                }
                i15 = i13;
                i13 = i14;
                i14 = i7;
                i7 = i6;
                i6 = i5;
                i5 = i4;
                obj10 = null;
                i10 = i15;
            } else {
                if (i16 != 128) {
                }
                i15 = i13;
                i13 = i14;
                i14 = i7;
                i7 = i6;
                i6 = i5;
                i5 = i4;
                obj10 = null;
                i10 = i15;
            }
            i9++;
            obj2 = obj9;
            obj3 = obj10;
            i8 = i12;
            i4 = i5;
            obj4 = obj8;
            i5 = i6;
            i6 = i7;
            i7 = i14;
            i14 = i13;
            i13 = i10;
            i10 = i11;
        }
        if (obj4 == null || i10 <= 0) {
            obj = obj4;
        } else {
            obj = null;
        }
        if (obj3 != null && i4 > 0) {
            obj3 = null;
        }
        if (obj != null && (obj7 != null || (i + i2) + i3 > 0)) {
            return "UTF8";
        }
        if (obj3 != null && (b || i14 >= 3 || i13 >= 3)) {
            return "SJIS";
        }
        if (obj2 != null && obj3 != null) {
            return (!(i14 == 2 && i5 == 2) && i8 * 10 < length) ? "ISO8859_1" : "SJIS";
        } else {
            if (obj2 != null) {
                return "ISO8859_1";
            }
            if (obj3 != null) {
                return "SJIS";
            }
            return obj != null ? "UTF8" : a;
        }
    }
}
