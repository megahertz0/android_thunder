package com.xunlei.downloadprovider.filemanager.model;

import com.tencent.open.SocialConstants;
import java.io.File;

// compiled from: XLDir.java
public final class h extends i {
    public static final String[] e;
    public int c;
    public boolean d;

    public h() {
        this.d = false;
    }

    static {
        e = new String[]{"camera", SocialConstants.PARAM_AVATAR_URI, "screenshot"};
    }

    public final i a(String str) {
        return super.a(str);
    }

    public final String toString() {
        return super.toString() + " count:" + this.c;
    }

    public final void a(String str, String str2) {
        if (str.endsWith("/")) {
            this.g = str + str2;
        } else {
            this.g = str + "/" + str2;
        }
    }

    public final String a() {
        if (this.k != null) {
            return this.k;
        }
        File file = new File(this.g);
        if (file.isDirectory()) {
            String a = super.a();
            this.k = a;
            return a;
        }
        a = file.getParentFile().getName();
        this.k = a;
        return a;
    }

    public final String b() {
        File file = new File(this.g);
        if (file.isDirectory()) {
            return super.b();
        }
        file = file.getParentFile().getParentFile();
        if (file == null) {
            return "/";
        }
        String absolutePath = file.getAbsolutePath();
        return !absolutePath.endsWith("/") ? absolutePath + "/" : absolutePath;
    }

    public final String c() {
        if (this.g == null) {
            return null;
        }
        return new File(this.g).isDirectory() ? this.g : this.g.substring(0, this.g.lastIndexOf("/") + 1);
    }
}
