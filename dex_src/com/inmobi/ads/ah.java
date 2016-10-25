package com.inmobi.ads;

import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.inmobi.ads.NativeStrandAsset.AssetType;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Locale;

// compiled from: NativeStrandTextAsset.java
class ah extends NativeStrandAsset {

    // compiled from: NativeStrandTextAsset.java
    static class a extends t {
        protected int f;
        protected String g;
        protected int h;
        protected a[] i;

        // compiled from: NativeStrandTextAsset.java
        enum a {
            TEXT_STYLE_NONE(IXAdSystemUtils.NT_NONE),
            TEXT_STYLE_BOLD("bold"),
            TEXT_STYLE_ITALICISED("italic"),
            TEXT_STYLE_STRIKE_THRU("strike"),
            TEXT_STYLE_UNDERLINE("underline");
            private final String f;

            static {
                String str = IXAdSystemUtils.NT_NONE;
                a = new a("TEXT_STYLE_NONE", 0, IXAdSystemUtils.NT_NONE);
                str = "bold";
                b = new a("TEXT_STYLE_BOLD", 1, "bold");
                str = "italic";
                c = new a("TEXT_STYLE_ITALICISED", 2, "italic");
                str = "strike";
                d = new a("TEXT_STYLE_STRIKE_THRU", 3, "strike");
                str = "underline";
                e = new a("TEXT_STYLE_UNDERLINE", 4, "underline");
                g = new a[]{a, b, c, d, e};
            }

            private a(String str) {
                this.f = str;
            }
        }

        public a(int i, int i2, int i3, int i4, b bVar, a aVar, String str, String str2) {
            this(i, i2, i3, i4, bVar, aVar, str, str2, 12);
        }

        public a(int i, int i2, int i3, int i4, b bVar, a aVar, String str, String str2, int i5) {
            this(i, i2, i3, i4, bVar, aVar, str, str2, i5, "#ff000000");
        }

        public a(int i, int i2, int i3, int i4, b bVar, a aVar, String str, String str2, int i5, String str3) {
            this(i, i2, i3, i4, bVar, aVar, str, str2, i5, str3, new a[]{a.a});
        }

        public a(int i, int i2, int i3, int i4, b bVar, a aVar, String str, String str2, int i5, String str3, a[] aVarArr) {
            this(i, i2, i3, i4, bVar, aVar, str, str2, i5, Integer.MAX_VALUE, str3, aVarArr);
        }

        public a(int i, int i2, int i3, int i4, b bVar, a aVar, String str, String str2, int i5, int i6, String str3, a[] aVarArr) {
            super(i, i2, i3, i4, bVar, aVar, str, str2);
            this.f = i5;
            if (str3.length() == 0) {
                str3 = "#ff000000";
            }
            this.g = str3;
            this.h = i6;
            int min = Math.min(aVarArr.length, XZBDevice.DOWNLOAD_LIST_ALL);
            this.i = new a[min];
            System.arraycopy(aVarArr, 0, this.i, 0, min);
        }

        public int h() {
            return this.f;
        }

        public String i() {
            return this.g.toLowerCase(Locale.US);
        }

        public int j() {
            return this.h;
        }

        public a[] k() {
            return this.i;
        }

        public String g() {
            return this.e.toLowerCase(Locale.US);
        }
    }

    public ah(String str, t tVar, String str2) {
        super(str, AssetType.ASSET_TYPE_TEXT, tVar);
        this.d = str2;
    }

    public ah(String str, AssetType assetType, t tVar, String str2) {
        super(str, assetType, tVar);
        this.d = str2;
    }
}
