package com.xunlei.downloadprovider.util;

import android.content.Context;
import com.xunlei.downloadprovider.util.h.b;
import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

// compiled from: FileManager.java
public final class h {
    private static h b;
    public Context a;
    private String c;
    private String d;

    // compiled from: FileManager.java
    public static class b implements FileFilter {
        private Pattern a;
        private boolean b;

        public b(String str) {
            this.b = false;
            this.a = Pattern.compile(str);
        }

        public boolean c(File file) {
            return true;
        }

        public boolean a(File file) {
            return true;
        }

        private boolean a(String str) {
            try {
                return this.a.matcher(str).matches();
            } catch (Exception e) {
                return false;
            }
        }

        public void b(File file) {
        }

        public boolean accept(File file) {
            if (this.b || !c(file) || !a(file.getName()) || !a(file)) {
                return false;
            }
            b(file);
            return true;
        }
    }

    // compiled from: FileManager.java
    public static class a extends b {
        public int c;

        public final boolean c(File file) {
            return !file.isDirectory();
        }

        public a(String str) {
            super(str);
            this.c = 0;
        }

        public void b(File file) {
            this.c = (int) (((long) this.c) + file.length());
            if (file.exists()) {
                file.getAbsolutePath();
                file.delete();
            }
        }
    }

    static {
        b = null;
    }

    private h(Context context) {
        this.a = null;
        this.a = context;
        File file = new File(com.xunlei.downloadprovider.businessutil.a.d() + "Cache/Thumb/");
        if (!(file.exists() && file.isDirectory())) {
            file.mkdirs();
        }
        if (this.c == null) {
            StringBuilder stringBuilder = new StringBuilder();
            if (this.d == null) {
                this.d = com.xunlei.downloadprovider.businessutil.a.d() + "xlshare/";
            }
            this.c = stringBuilder.append(this.d).append("app/").toString();
        }
        File file2 = new File(this.c);
        if ((!file2.exists() || !file2.isDirectory()) && !file2.mkdirs()) {
            new File(com.xunlei.downloadprovider.businessutil.a.d() + "xlshare/app/").mkdirs();
        }
    }

    public static h a(Context context) {
        if (b == null) {
            b = new h(context);
        }
        return b;
    }

    public static File[] a(String str, FileFilter fileFilter) {
        if (str == null) {
            return null;
        }
        File file = new File(str);
        return file.isDirectory() ? file.listFiles(fileFilter) : null;
    }
}
