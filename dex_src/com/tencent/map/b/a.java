package com.tencent.map.b;

import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public final class a {
    private static a a;

    static /* synthetic */ class AnonymousClass_1 {
        final /* synthetic */ a a;

        private AnonymousClass_1(a aVar) {
            this.a = aVar;
        }

        public boolean a(String str, String str2) {
            int a = a.a(this.a, str);
            if (str2.charAt(XZBDevice.DOWNLOAD_LIST_ALL) != i.a.charAt(((((a * 9) + 10) / 3) + 36) & 31)) {
                return false;
            }
            if (str2.charAt(R.styleable.Toolbar_contentInsetLeft) != i.a.charAt((((a * 5) + 11) / 5) & 31)) {
                return false;
            }
            if (str2.charAt(XZBDevice.Fail) != i.a.charAt((((a + 10) / 3) << 3) & 31)) {
                return false;
            }
            if (str2.charAt(XZBDevice.Predownload) != i.a.charAt((((a * 3) + 19) / 9) & 31)) {
                return false;
            }
            if (str2.charAt(R.styleable.Toolbar_collapseContentDescription) != i.a.charAt((((a * 3) + 39) / 8) & 31)) {
                return false;
            }
            if (str2.charAt(R.styleable.Toolbar_navigationContentDescription) != i.a.charAt((((a / 23) + 67) / 7) & 31)) {
                return false;
            }
            if (str2.charAt(R.styleable.AppCompatTheme_actionMenuTextColor) != i.a.charAt(((((a + 23) / 6) + 3) * 7) & 31)) {
                return false;
            }
            int i = 0;
            for (a = 0; a < str.length(); a++) {
                i = i.b[(i ^ i.a(str.charAt(a))) & 255] ^ ((i >> 8) & 255);
            }
            if (str2.charAt(0) != i.a.charAt(i & 31)) {
                return false;
            }
            return str2.charAt(1) == i.a.charAt((i >> 5) & 31);
        }
    }

    static {
        a = null;
    }

    private a() {
    }

    static /* synthetic */ int a(a aVar, String str) {
        int i = 0;
        int length = str.length();
        int i2 = 0;
        while (i < length) {
            i2 += i.a(str.charAt(i));
            i++;
        }
        return ((length << 7) + length) ^ i2;
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a();
            }
            aVar = a;
        }
        return aVar;
    }

    public final boolean a(String str, String str2) {
        if (!i.a(str) || !i.b(str2) || !new AnonymousClass_1().a(str, str2)) {
            return false;
        }
        int i;
        boolean z;
        int i2 = 0;
        for (i = 0; i < 27; i++) {
            i2 = i.b[(i2 ^ i.a(str2.charAt(i))) & 255] ^ ((i2 >> 8) & 255);
        }
        if (str2.charAt(R.styleable.AppCompatTheme_actionModeStyle) != i.a.charAt(i2 & 31)) {
            z = false;
        } else {
            if (str2.charAt(R.styleable.AppCompatTheme_actionModeCloseButtonStyle) != i.a.charAt((i2 >> 5) & 31)) {
                z = false;
            } else {
                i = 1;
            }
        }
        return z;
    }
}
