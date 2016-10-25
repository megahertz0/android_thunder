package com.google.zxing.c.a.a.a;

import com.google.zxing.common.a;
import com.google.zxing.e;
import com.google.zxing.j;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: GeneralAppIdDecoder.java
public final class s {
    private final a a;
    private final m b;
    private final StringBuilder c;

    s(a aVar) {
        this.b = new m();
        this.c = new StringBuilder();
        this.a = aVar;
    }

    final String a(StringBuilder stringBuilder, int i) throws j, e {
        String str = null;
        while (true) {
            q a = a(i, str);
            str = r.a(a.a);
            if (str != null) {
                stringBuilder.append(str);
            }
            if (a.c) {
                str = String.valueOf(a.b);
            } else {
                str = null;
            }
            if (i == a.d) {
                return stringBuilder.toString();
            }
            i = a.d;
        }
    }

    final int a(int i, int i2) {
        return a(this.a, i, i2);
    }

    public static int a(a aVar, int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            if (aVar.a(i + i4)) {
                i3 |= 1 << ((i2 - i4) - 1);
            }
        }
        return i3;
    }

    final o a(int i, String str) throws e {
        l lVar;
        o oVar;
        this.c.setLength(0);
        if (str != null) {
            this.c.append(str);
        }
        this.b.a = i;
        boolean z;
        do {
            int i2 = this.b.a;
            if (this.b.b == a.b) {
                boolean z2 = true;
            } else {
                int i3 = 0;
            }
            if (z2) {
                while (true) {
                    int a;
                    int i4;
                    char c;
                    q nVar;
                    i3 = this.b.a;
                    if (i3 + 5 <= this.a.b) {
                        a = a(i3, (int) XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                        if (a >= 5 && a < 16) {
                            z2 = true;
                            if (z2) {
                                if (!b(this.b.a)) {
                                    this.b.a(XZBDevice.DOWNLOAD_LIST_FAILED);
                                    this.b.b = a.a;
                                } else if (a(this.b.a)) {
                                    if (this.b.a + 5 < this.a.b) {
                                        this.b.a = this.a.b;
                                    } else {
                                        this.b.a(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                                    }
                                    this.b.b = a.c;
                                }
                                lVar = new l();
                            } else {
                                i4 = this.b.a;
                                a = a(i4, (int) XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                                if (a == 15) {
                                    if (a >= 5) {
                                    }
                                    a = a(i4, (int) R.styleable.Toolbar_contentInsetEnd);
                                    if (a >= 32) {
                                    }
                                    switch (a) {
                                        case R.styleable.AppCompatTheme_toolbarStyle:
                                            c = '*';
                                            break;
                                        case R.styleable.AppCompatTheme_toolbarNavigationButtonStyle:
                                            c = ',';
                                            break;
                                        case R.styleable.AppCompatTheme_popupMenuStyle:
                                            c = '-';
                                            break;
                                        case R.styleable.AppCompatTheme_popupWindowStyle:
                                            c = '.';
                                            break;
                                        case R.styleable.AppCompatTheme_editTextColor:
                                            c = '/';
                                            break;
                                        default:
                                            throw new IllegalStateException(new StringBuilder("Decoding invalid alphanumeric value: ").append(a).toString());
                                    }
                                    nVar = new n(i4 + 6, c);
                                } else {
                                    nVar = new n(i4 + 5, '$');
                                }
                                this.b.a = nVar.d;
                                if (nVar.a()) {
                                    this.c.append(nVar.a);
                                } else {
                                    lVar = new l(new o(this.b.a, this.c.toString()), true);
                                }
                            }
                            z = lVar.b;
                        } else if (i3 + 6 <= this.a.b) {
                            i3 = a(i3, (int) R.styleable.Toolbar_contentInsetEnd);
                            if (i3 >= 16 && i3 < 63) {
                                z2 = true;
                                if (z2) {
                                    i4 = this.b.a;
                                    a = a(i4, (int) XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                                    if (a == 15) {
                                        nVar = new n(i4 + 5, '$');
                                    } else if (a >= 5 || a >= 15) {
                                        a = a(i4, (int) R.styleable.Toolbar_contentInsetEnd);
                                        if (a >= 32 || a >= 58) {
                                            switch (a) {
                                                case R.styleable.AppCompatTheme_toolbarStyle:
                                                    c = '*';
                                                    break;
                                                case R.styleable.AppCompatTheme_toolbarNavigationButtonStyle:
                                                    c = ',';
                                                    break;
                                                case R.styleable.AppCompatTheme_popupMenuStyle:
                                                    c = '-';
                                                    break;
                                                case R.styleable.AppCompatTheme_popupWindowStyle:
                                                    c = '.';
                                                    break;
                                                case R.styleable.AppCompatTheme_editTextColor:
                                                    c = '/';
                                                    break;
                                                default:
                                                    throw new IllegalStateException(new StringBuilder("Decoding invalid alphanumeric value: ").append(a).toString());
                                            }
                                            nVar = new n(i4 + 6, c);
                                        } else {
                                            nVar = new n(i4 + 6, (char) (a + 33));
                                        }
                                    } else {
                                        nVar = new n(i4 + 5, (char) ((a + 48) - 5));
                                    }
                                    this.b.a = nVar.d;
                                    if (nVar.a()) {
                                        lVar = new l(new o(this.b.a, this.c.toString()), true);
                                    } else {
                                        this.c.append(nVar.a);
                                    }
                                } else {
                                    if (!b(this.b.a)) {
                                        this.b.a(XZBDevice.DOWNLOAD_LIST_FAILED);
                                        this.b.b = a.a;
                                    } else if (a(this.b.a)) {
                                        if (this.b.a + 5 < this.a.b) {
                                            this.b.a(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                                        } else {
                                            this.b.a = this.a.b;
                                        }
                                        this.b.b = a.c;
                                    }
                                    lVar = new l();
                                }
                                z = lVar.b;
                            }
                        }
                    }
                    i3 = 0;
                    if (z2) {
                        i4 = this.b.a;
                        a = a(i4, (int) XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                        if (a == 15) {
                            nVar = new n(i4 + 5, '$');
                        } else {
                            if (a >= 5) {
                            }
                            a = a(i4, (int) R.styleable.Toolbar_contentInsetEnd);
                            if (a >= 32) {
                            }
                            switch (a) {
                                case R.styleable.AppCompatTheme_toolbarStyle:
                                    c = '*';
                                    break;
                                case R.styleable.AppCompatTheme_toolbarNavigationButtonStyle:
                                    c = ',';
                                    break;
                                case R.styleable.AppCompatTheme_popupMenuStyle:
                                    c = '-';
                                    break;
                                case R.styleable.AppCompatTheme_popupWindowStyle:
                                    c = '.';
                                    break;
                                case R.styleable.AppCompatTheme_editTextColor:
                                    c = '/';
                                    break;
                                default:
                                    throw new IllegalStateException(new StringBuilder("Decoding invalid alphanumeric value: ").append(a).toString());
                            }
                            nVar = new n(i4 + 6, c);
                        }
                        this.b.a = nVar.d;
                        if (nVar.a()) {
                            lVar = new l(new o(this.b.a, this.c.toString()), true);
                        } else {
                            this.c.append(nVar.a);
                        }
                    } else {
                        if (!b(this.b.a)) {
                            this.b.a(XZBDevice.DOWNLOAD_LIST_FAILED);
                            this.b.b = a.a;
                        } else if (a(this.b.a)) {
                            if (this.b.a + 5 < this.a.b) {
                                this.b.a(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                            } else {
                                this.b.a = this.a.b;
                            }
                            this.b.b = a.c;
                        }
                        lVar = new l();
                    }
                    z = lVar.b;
                }
            } else {
                if (this.b.b == a.c) {
                    z2 = true;
                } else {
                    i3 = 0;
                }
                if (z2) {
                    lVar = b();
                    z = lVar.b;
                } else {
                    lVar = a();
                    z = lVar.b;
                }
            }
            if (i2 != this.b.a) {
                boolean z3 = true;
            } else {
                i2 = 0;
            }
            if (!z3 && !z) {
                oVar = lVar.a;
                return (oVar == null && oVar.c) ? new o(this.b.a, this.c.toString(), oVar.b) : new o(this.b.a, this.c.toString());
            }
        } while (!z);
        oVar = lVar.a;
        if (oVar == null) {
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.zxing.c.a.a.a.l a() throws com.google.zxing.e {
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.c.a.a.a.s.a():com.google.zxing.c.a.a.a.l");
        /*
        this = this;
        r7 = 4;
        r6 = 10;
        r1 = 0;
        r2 = 1;
    L_0x0005:
        r0 = r8.b;
        r3 = r0.a;
        r0 = r3 + 7;
        r4 = r8.a;
        r4 = r4.b;
        if (r0 <= r4) goto L_0x0062;
    L_0x0011:
        r0 = r3 + 4;
        r3 = r8.a;
        r3 = r3.b;
        if (r0 > r3) goto L_0x0060;
    L_0x0019:
        r0 = r2;
    L_0x001a:
        if (r0 == 0) goto L_0x00de;
    L_0x001c:
        r0 = r8.b;
        r3 = r0.a;
        r0 = r3 + 7;
        r4 = r8.a;
        r4 = r4.b;
        if (r0 <= r4) goto L_0x0089;
    L_0x0028:
        r3 = r8.a(r3, r7);
        if (r3 != 0) goto L_0x007d;
    L_0x002e:
        r0 = new com.google.zxing.c.a.a.a.p;
        r3 = r8.a;
        r3 = r3.b;
        r0.<init>(r3, r6, r6);
    L_0x0037:
        r3 = r8.b;
        r4 = r0.d;
        r3.a = r4;
        r3 = r0.a;
        if (r3 != r6) goto L_0x009e;
    L_0x0041:
        r3 = r2;
    L_0x0042:
        if (r3 == 0) goto L_0x00b3;
    L_0x0044:
        r1 = r0.a();
        if (r1 == 0) goto L_0x00a0;
    L_0x004a:
        r0 = new com.google.zxing.c.a.a.a.o;
        r1 = r8.b;
        r1 = r1.a;
        r3 = r8.c;
        r3 = r3.toString();
        r0.<init>(r1, r3);
    L_0x0059:
        r1 = new com.google.zxing.c.a.a.a.l;
        r1.<init>(r0, r2);
        r0 = r1;
    L_0x005f:
        return r0;
    L_0x0060:
        r0 = r1;
        goto L_0x001a;
    L_0x0062:
        r0 = r3;
    L_0x0063:
        r4 = r3 + 3;
        if (r0 >= r4) goto L_0x0074;
    L_0x0067:
        r4 = r8.a;
        r4 = r4.a(r0);
        if (r4 == 0) goto L_0x0071;
    L_0x006f:
        r0 = r2;
        goto L_0x001a;
    L_0x0071:
        r0 = r0 + 1;
        goto L_0x0063;
    L_0x0074:
        r0 = r8.a;
        r3 = r3 + 3;
        r0 = r0.a(r3);
        goto L_0x001a;
    L_0x007d:
        r0 = new com.google.zxing.c.a.a.a.p;
        r4 = r8.a;
        r4 = r4.b;
        r3 = r3 + -1;
        r0.<init>(r4, r3, r6);
        goto L_0x0037;
    L_0x0089:
        r0 = 7;
        r0 = r8.a(r3, r0);
        r4 = r0 + -8;
        r4 = r4 / 11;
        r0 = r0 + -8;
        r5 = r0 % 11;
        r0 = new com.google.zxing.c.a.a.a.p;
        r3 = r3 + 7;
        r0.<init>(r3, r4, r5);
        goto L_0x0037;
    L_0x009e:
        r3 = r1;
        goto L_0x0042;
    L_0x00a0:
        r1 = new com.google.zxing.c.a.a.a.o;
        r3 = r8.b;
        r3 = r3.a;
        r4 = r8.c;
        r4 = r4.toString();
        r0 = r0.b;
        r1.<init>(r3, r4, r0);
        r0 = r1;
        goto L_0x0059;
    L_0x00b3:
        r3 = r8.c;
        r4 = r0.a;
        r3.append(r4);
        r3 = r0.a();
        if (r3 == 0) goto L_0x00d5;
    L_0x00c0:
        r1 = new com.google.zxing.c.a.a.a.o;
        r0 = r8.b;
        r0 = r0.a;
        r3 = r8.c;
        r3 = r3.toString();
        r1.<init>(r0, r3);
        r0 = new com.google.zxing.c.a.a.a.l;
        r0.<init>(r1, r2);
        goto L_0x005f;
    L_0x00d5:
        r3 = r8.c;
        r0 = r0.b;
        r3.append(r0);
        goto L_0x0005;
    L_0x00de:
        r0 = r8.b;
        r3 = r0.a;
        r0 = r3 + 1;
        r4 = r8.a;
        r4 = r4.b;
        if (r0 <= r4) goto L_0x00ff;
    L_0x00ea:
        r0 = r1;
    L_0x00eb:
        if (r0 == 0) goto L_0x00f8;
    L_0x00ed:
        r0 = r8.b;
        r1 = com.google.zxing.c.a.a.a.m.a.b;
        r0.b = r1;
        r0 = r8.b;
        r0.a(r7);
    L_0x00f8:
        r0 = new com.google.zxing.c.a.a.a.l;
        r0.<init>();
        goto L_0x005f;
    L_0x00ff:
        r0 = r1;
    L_0x0100:
        if (r0 >= r7) goto L_0x0119;
    L_0x0102:
        r4 = r0 + r3;
        r5 = r8.a;
        r5 = r5.b;
        if (r4 >= r5) goto L_0x0119;
    L_0x010a:
        r4 = r8.a;
        r5 = r3 + r0;
        r4 = r4.a(r5);
        if (r4 == 0) goto L_0x0116;
    L_0x0114:
        r0 = r1;
        goto L_0x00eb;
    L_0x0116:
        r0 = r0 + 1;
        goto L_0x0100;
    L_0x0119:
        r0 = r2;
        goto L_0x00eb;
        */
    }

    private l b() throws e {
        while (true) {
            int a;
            boolean z;
            int i;
            q nVar;
            char c;
            int i2 = this.b.a;
            if (i2 + 5 <= this.a.b) {
                a = a(i2, (int) XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                if (a >= 5 && a < 16) {
                    z = true;
                    if (z) {
                        if (!b(this.b.a)) {
                            this.b.a(XZBDevice.DOWNLOAD_LIST_FAILED);
                            this.b.b = a.a;
                        } else if (a(this.b.a)) {
                            if (this.b.a + 5 >= this.a.b) {
                                this.b.a(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                            } else {
                                this.b.a = this.a.b;
                            }
                            this.b.b = a.b;
                        }
                        return new l();
                    }
                    i = this.b.a;
                    a = a(i, (int) XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                    if (a != 15) {
                        nVar = new n(i + 5, '$');
                    } else {
                        if (a >= 5) {
                        }
                        a = a(i, (int) R.styleable.Toolbar_contentInsetLeft);
                        if (a < 64) {
                        }
                        if (a >= 90) {
                        }
                        switch (a(i, (int) XZBDevice.Wait)) {
                            case 232:
                                c = '!';
                                break;
                            case 233:
                                c = '\"';
                                break;
                            case 234:
                                c = '%';
                                break;
                            case 235:
                                c = '&';
                                break;
                            case 236:
                                c = '\'';
                                break;
                            case 237:
                                c = '(';
                                break;
                            case 238:
                                c = ')';
                                break;
                            case 239:
                                c = '*';
                                break;
                            case SocializeConstants.MASK_USER_CENTER_HIDE_AREA:
                                c = '+';
                                break;
                            case 241:
                                c = ',';
                                break;
                            case 242:
                                c = '-';
                                break;
                            case 243:
                                c = '.';
                                break;
                            case 244:
                                c = '/';
                                break;
                            case 245:
                                c = ':';
                                break;
                            case 246:
                                c = ';';
                                break;
                            case 247:
                                c = '<';
                                break;
                            case 248:
                                c = '=';
                                break;
                            case 249:
                                c = '>';
                                break;
                            case 250:
                                c = '?';
                                break;
                            case 251:
                                c = '_';
                                break;
                            case 252:
                                c = ' ';
                                break;
                            default:
                                throw e.a();
                        }
                        nVar = new n(i + 8, c);
                    }
                    this.b.a = nVar.d;
                    if (!nVar.a()) {
                        return new l(new o(this.b.a, this.c.toString()), true);
                    }
                    this.c.append(nVar.a);
                } else if (i2 + 7 <= this.a.b) {
                    a = a(i2, (int) R.styleable.Toolbar_contentInsetLeft);
                    if (a >= 64 && a < 116) {
                        z = true;
                        if (z) {
                            if (!b(this.b.a)) {
                                this.b.a(XZBDevice.DOWNLOAD_LIST_FAILED);
                                this.b.b = a.a;
                            } else if (a(this.b.a)) {
                                if (this.b.a + 5 >= this.a.b) {
                                    this.b.a = this.a.b;
                                } else {
                                    this.b.a(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                                }
                                this.b.b = a.b;
                            }
                            return new l();
                        }
                        i = this.b.a;
                        a = a(i, (int) XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                        if (a != 15) {
                            if (a >= 5) {
                            }
                            a = a(i, (int) R.styleable.Toolbar_contentInsetLeft);
                            if (a < 64) {
                            }
                            if (a >= 90) {
                            }
                            switch (a(i, (int) XZBDevice.Wait)) {
                                case 232:
                                    c = '!';
                                    break;
                                case 233:
                                    c = '\"';
                                    break;
                                case 234:
                                    c = '%';
                                    break;
                                case 235:
                                    c = '&';
                                    break;
                                case 236:
                                    c = '\'';
                                    break;
                                case 237:
                                    c = '(';
                                    break;
                                case 238:
                                    c = ')';
                                    break;
                                case 239:
                                    c = '*';
                                    break;
                                case SocializeConstants.MASK_USER_CENTER_HIDE_AREA:
                                    c = '+';
                                    break;
                                case 241:
                                    c = ',';
                                    break;
                                case 242:
                                    c = '-';
                                    break;
                                case 243:
                                    c = '.';
                                    break;
                                case 244:
                                    c = '/';
                                    break;
                                case 245:
                                    c = ':';
                                    break;
                                case 246:
                                    c = ';';
                                    break;
                                case 247:
                                    c = '<';
                                    break;
                                case 248:
                                    c = '=';
                                    break;
                                case 249:
                                    c = '>';
                                    break;
                                case 250:
                                    c = '?';
                                    break;
                                case 251:
                                    c = '_';
                                    break;
                                case 252:
                                    c = ' ';
                                    break;
                                default:
                                    throw e.a();
                            }
                            nVar = new n(i + 8, c);
                        } else {
                            nVar = new n(i + 5, '$');
                        }
                        this.b.a = nVar.d;
                        if (!nVar.a()) {
                            return new l(new o(this.b.a, this.c.toString()), true);
                        }
                        this.c.append(nVar.a);
                    } else if (i2 + 8 <= this.a.b) {
                        i2 = a(i2, (int) XZBDevice.Wait);
                        if (i2 >= 232 && i2 < 253) {
                            z = true;
                            if (z) {
                                i = this.b.a;
                                a = a(i, (int) XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                                if (a != 15) {
                                    nVar = new n(i + 5, '$');
                                } else if (a >= 5 || a >= 15) {
                                    a = a(i, (int) R.styleable.Toolbar_contentInsetLeft);
                                    if (a < 64 && a < 90) {
                                        nVar = new n(i + 7, (char) (a + 1));
                                    } else if (a >= 90 || a >= 116) {
                                        switch (a(i, (int) XZBDevice.Wait)) {
                                            case 232:
                                                c = '!';
                                                break;
                                            case 233:
                                                c = '\"';
                                                break;
                                            case 234:
                                                c = '%';
                                                break;
                                            case 235:
                                                c = '&';
                                                break;
                                            case 236:
                                                c = '\'';
                                                break;
                                            case 237:
                                                c = '(';
                                                break;
                                            case 238:
                                                c = ')';
                                                break;
                                            case 239:
                                                c = '*';
                                                break;
                                            case SocializeConstants.MASK_USER_CENTER_HIDE_AREA:
                                                c = '+';
                                                break;
                                            case 241:
                                                c = ',';
                                                break;
                                            case 242:
                                                c = '-';
                                                break;
                                            case 243:
                                                c = '.';
                                                break;
                                            case 244:
                                                c = '/';
                                                break;
                                            case 245:
                                                c = ':';
                                                break;
                                            case 246:
                                                c = ';';
                                                break;
                                            case 247:
                                                c = '<';
                                                break;
                                            case 248:
                                                c = '=';
                                                break;
                                            case 249:
                                                c = '>';
                                                break;
                                            case 250:
                                                c = '?';
                                                break;
                                            case 251:
                                                c = '_';
                                                break;
                                            case 252:
                                                c = ' ';
                                                break;
                                            default:
                                                throw e.a();
                                        }
                                        nVar = new n(i + 8, c);
                                    } else {
                                        nVar = new n(i + 7, (char) (a + 7));
                                    }
                                } else {
                                    nVar = new n(i + 5, (char) ((a + 48) - 5));
                                }
                                this.b.a = nVar.d;
                                if (!nVar.a()) {
                                    return new l(new o(this.b.a, this.c.toString()), true);
                                }
                                this.c.append(nVar.a);
                            } else {
                                if (!b(this.b.a)) {
                                    this.b.a(XZBDevice.DOWNLOAD_LIST_FAILED);
                                    this.b.b = a.a;
                                } else if (a(this.b.a)) {
                                    if (this.b.a + 5 >= this.a.b) {
                                        this.b.a(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                                    } else {
                                        this.b.a = this.a.b;
                                    }
                                    this.b.b = a.b;
                                }
                                return new l();
                            }
                        }
                    }
                }
            }
            Object obj = null;
            if (z) {
                if (!b(this.b.a)) {
                    this.b.a(XZBDevice.DOWNLOAD_LIST_FAILED);
                    this.b.b = a.a;
                } else if (a(this.b.a)) {
                    if (this.b.a + 5 >= this.a.b) {
                        this.b.a = this.a.b;
                    } else {
                        this.b.a(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                    }
                    this.b.b = a.b;
                }
                return new l();
            }
            i = this.b.a;
            a = a(i, (int) XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
            if (a != 15) {
                if (a >= 5) {
                }
                a = a(i, (int) R.styleable.Toolbar_contentInsetLeft);
                if (a < 64) {
                }
                if (a >= 90) {
                }
                switch (a(i, (int) XZBDevice.Wait)) {
                    case 232:
                        c = '!';
                        break;
                    case 233:
                        c = '\"';
                        break;
                    case 234:
                        c = '%';
                        break;
                    case 235:
                        c = '&';
                        break;
                    case 236:
                        c = '\'';
                        break;
                    case 237:
                        c = '(';
                        break;
                    case 238:
                        c = ')';
                        break;
                    case 239:
                        c = '*';
                        break;
                    case SocializeConstants.MASK_USER_CENTER_HIDE_AREA:
                        c = '+';
                        break;
                    case 241:
                        c = ',';
                        break;
                    case 242:
                        c = '-';
                        break;
                    case 243:
                        c = '.';
                        break;
                    case 244:
                        c = '/';
                        break;
                    case 245:
                        c = ':';
                        break;
                    case 246:
                        c = ';';
                        break;
                    case 247:
                        c = '<';
                        break;
                    case 248:
                        c = '=';
                        break;
                    case 249:
                        c = '>';
                        break;
                    case 250:
                        c = '?';
                        break;
                    case 251:
                        c = '_';
                        break;
                    case 252:
                        c = ' ';
                        break;
                    default:
                        throw e.a();
                }
                nVar = new n(i + 8, c);
            } else {
                nVar = new n(i + 5, '$');
            }
            this.b.a = nVar.d;
            if (!nVar.a()) {
                return new l(new o(this.b.a, this.c.toString()), true);
            }
            this.c.append(nVar.a);
        }
    }

    private boolean a(int i) {
        if (i + 1 > this.a.b) {
            return false;
        }
        int i2 = 0;
        while (i2 < 5 && i2 + i < this.a.b) {
            if (i2 == 2) {
                if (!this.a.a(i + 2)) {
                    return false;
                }
            } else if (this.a.a(i + i2)) {
                return false;
            }
            i2++;
        }
        return true;
    }

    private boolean b(int i) {
        if (i + 3 > this.a.b) {
            return false;
        }
        for (int i2 = i; i2 < i + 3; i2++) {
            if (this.a.a(i2)) {
                return false;
            }
        }
        return true;
    }
}
