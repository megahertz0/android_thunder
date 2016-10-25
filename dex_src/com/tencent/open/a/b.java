package com.tencent.open.a;

import com.tencent.open.a.d.d;
import com.umeng.message.MsgConstant;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

// compiled from: ProGuard
public class b {
    private static SimpleDateFormat a;
    private String b;
    private int c;
    private int d;
    private int e;
    private long f;
    private File g;
    private int h;
    private String i;
    private long j;

    static {
        a = d.a("yy.MM.dd.HH");
    }

    public b(File file, int i, int i2, int i3, String str, long j, int i4, String str2, long j2) {
        this.b = "Tracer.File";
        this.c = Integer.MAX_VALUE;
        this.d = Integer.MAX_VALUE;
        this.e = 4096;
        this.f = 10000;
        this.h = 10;
        this.i = MsgConstant.CACHE_LOG_FILE_EXT;
        this.j = Long.MAX_VALUE;
        a(file);
        b(i);
        a(i2);
        c(i3);
        a(str);
        a(j);
        d(i4);
        b(str2);
        b(j2);
    }

    public File a() {
        return c(System.currentTimeMillis());
    }

    private File c(long j) {
        File b = b();
        try {
            return new File(b, c(d(j)));
        } catch (Throwable th) {
            th.printStackTrace();
            return b;
        }
    }

    private String d(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return new SimpleDateFormat("yy.MM.dd.HH").format(instance.getTime());
    }

    private String c(String str) {
        return new StringBuilder("com.tencent.mobileqq_connectSdk.").append(str).append(MsgConstant.CACHE_LOG_FILE_EXT).toString();
    }

    public File b() {
        File e = e();
        e.mkdirs();
        return e;
    }

    public String c() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public void a(int i) {
        this.c = i;
    }

    public void b(int i) {
        this.d = i;
    }

    public int d() {
        return this.e;
    }

    public void c(int i) {
        this.e = i;
    }

    public void a(long j) {
        this.f = j;
    }

    public File e() {
        return this.g;
    }

    public void a(File file) {
        this.g = file;
    }

    public int f() {
        return this.h;
    }

    public void d(int i) {
        this.h = i;
    }

    public void b(String str) {
        this.i = str;
    }

    public void b(long j) {
        this.j = j;
    }
}
