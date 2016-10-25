package com.qq.e.comm.managers.plugin;

import android.content.Context;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.Md5Util;
import com.qq.e.comm.util.StringUtil;
import com.qq.e.comm.util.a;
import java.io.File;

class c {
    final File a;
    final File b;
    String c;
    int d;

    public c(File file, File file2) {
        this.a = file;
        this.b = file2;
    }

    static File a(Context context) {
        return new File(context.getDir("e_qq_com_plugin", 0), "gdt_plugin.jar");
    }

    static File b(Context context) {
        return new File(context.getDir("e_qq_com_plugin", 0), "gdt_plugin.next");
    }

    static File c(Context context) {
        return new File(context.getDir("e_qq_com_plugin", 0), "gdt_plugin.jar.sig");
    }

    static File d(Context context) {
        return new File(context.getDir("e_qq_com_plugin", 0), "gdt_plugin.next.sig");
    }

    final boolean a() {
        try {
            if (!this.b.exists() || !this.a.exists()) {
                return false;
            }
            String[] split = StringUtil.readAll(this.b).split("#####");
            if (split.length != 2) {
                return false;
            }
            String str = split[1];
            int parseInteger = StringUtil.parseInteger(split[0], 0);
            a a = a.a();
            File file = this.a;
            boolean b = (file == null || !file.exists()) ? false : a.b(str, Md5Util.encode(file));
            if (!b) {
                return false;
            }
            this.c = str;
            this.d = parseInteger;
            return true;
        } catch (Throwable th) {
            GDTLogger.report("Exception while checking plugin", th);
            return false;
        }
    }
}
