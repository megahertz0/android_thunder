package com.xunlei.downloadprovider.d;

import android.text.TextUtils;
import java.io.File;
import java.io.IOException;

// compiled from: FileUtil.java
public final class c {
    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            return true;
        }
        try {
            return file.mkdirs();
        } catch (SecurityException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean b(String str) {
        return TextUtils.isEmpty(str) ? false : new File(str).exists();
    }

    public static String c(String str) {
        if (str == null) {
            return null;
        }
        int lastIndexOf = str.lastIndexOf(".");
        return lastIndexOf > 0 ? str.substring(lastIndexOf + 1, str.length()).toLowerCase() : null;
    }

    public static String d(String str) {
        if (str == null) {
            return null;
        }
        int lastIndexOf = str.lastIndexOf("/");
        return lastIndexOf > 0 ? str.substring(lastIndexOf + 1, str.length()).toLowerCase() : str;
    }

    public static boolean a(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        if (!str.endsWith("/")) {
            str = str + "/";
        }
        if (!str2.endsWith("/")) {
            str2 = str2 + "/";
        }
        return str.equalsIgnoreCase(str2);
    }

    public static boolean a(File file) throws IOException {
        if (file.getParent() != null) {
            file = new File(file.getParentFile().getCanonicalFile(), file.getName());
        }
        return !file.getCanonicalFile().equals(file.getAbsoluteFile());
    }
}
