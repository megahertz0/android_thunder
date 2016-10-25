package com.xunlei.downloadprovider.filemanager.model;

import com.umeng.a;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.android.spdy.SpdyAgent;

// compiled from: XLFile.java
public class i extends SelectableItem {
    public long f;
    public String g;
    public long h;
    public long i;
    public EFileCategoryType j;
    protected String k;

    public i() {
        this.f = -1;
    }

    public /* synthetic */ int compareTo(Object obj) {
        Object obj2 = 1;
        Object obj3 = -1;
        i iVar = (i) obj;
        String a;
        String a2;
        int b;
        switch (AnonymousClass_1.a[b.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                a = a();
                a = a == null ? a.d : a.toLowerCase();
                a2 = iVar.a();
                b = b(a, a2 == null ? a.d : a2.toLowerCase());
                if (b == 0) {
                    b = (int) (iVar.h - this.h);
                }
                return b == 0 ? 1 : b;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                a = a();
                a = a == null ? a.d : a.toLowerCase();
                a2 = iVar.a();
                b = b(a, a2 == null ? a.d : a2.toLowerCase());
                if (b == 0) {
                    b = (int) (this.h - iVar.h);
                }
                return b == 0 ? -1 : b;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                int i;
                b = (int) (this.h - iVar.h);
                if (b != 0) {
                    i = b;
                }
                return i;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                int i2;
                b = (int) (iVar.h - this.h);
                if (b != 0) {
                    i2 = b;
                }
                return i2;
            default:
                return 0;
        }
    }

    public i a(String str) {
        this.g = str;
        File file = new File(str);
        if (file.canRead() && file.exists()) {
            this.h = file.lastModified();
            this.i = file.length();
            this.j = XLFileTypeUtil.a(str);
        }
        return this;
    }

    public void a(String str, String str2) {
        if (str.endsWith("/")) {
            this.g = str + str2;
        } else {
            this.g = str + "/" + str2;
        }
        this.k = str2;
    }

    public final EFileCategoryType d() {
        if (this.j == null) {
            this.j = XLFileTypeUtil.a(this.g);
        }
        return this.j;
    }

    public final int e() {
        EFileCategoryType[] values = EFileCategoryType.values();
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(this.j)) {
                return i;
            }
        }
        return EFileCategoryType.E_OTHER_CATEGORY.ordinal();
    }

    public String b() {
        int lastIndexOf = this.g.lastIndexOf("/");
        return lastIndexOf == -1 ? null : this.g.substring(0, lastIndexOf + 1);
    }

    public String c() {
        return this.g;
    }

    public String a() {
        if (this.k != null) {
            return this.k;
        }
        int lastIndexOf = this.g.lastIndexOf("/");
        if (lastIndexOf == -1) {
            return null;
        }
        String substring = this.g.substring(lastIndexOf + 1);
        this.k = substring;
        return substring;
    }

    public final synchronized String f() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(this.h));
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(24);
        stringBuffer.append("id:").append(this.f).append(" path:").append(this.g).append(" size:").append(this.i).append(" lastmodify:").append(this.h).append(" type:").append(this.j).append(" selected:").append(this.a);
        return stringBuffer.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof i) {
            i iVar = (i) obj;
            if (!(this.f == -1 || iVar.f == -1 || this.f != iVar.f) || this.g.equals(iVar.g)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return this.g != null ? (int) (((long) this.g.hashCode()) + this.f) : (int) (((long) super.hashCode()) + this.f);
    }

    public final boolean g() {
        return this instanceof h;
    }

    private static int b(String str, String str2) {
        char[] toCharArray = str.toCharArray();
        char[] toCharArray2 = str2.toCharArray();
        int min = Math.min(toCharArray.length, toCharArray2.length);
        int i = 0;
        while (i < min) {
            char c = toCharArray[i];
            char c2 = toCharArray2[i];
            if (c == c2) {
                i++;
            } else if (c >= '\u4e00' && c <= '\u9fbb' && c2 >= '\u4e00' && c2 <= '\u9fbb') {
                return c - c2;
            } else {
                if (c < '\u4e00' || c > '\u9fbb' || (c2 >= '\u4e00' && c2 <= '\u9fbb')) {
                    return ((c < '\u4e00' || c > '\u9fbb') && c2 >= '\u4e00' && c2 <= '\u9fbb') ? 1 : c - c2;
                } else {
                    return -1;
                }
            }
        }
        return toCharArray.length - toCharArray2.length;
    }
}
