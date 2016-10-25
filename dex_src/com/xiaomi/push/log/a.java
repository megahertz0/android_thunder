package com.xiaomi.push.log;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.ag;
import com.xunlei.download.proguard.c;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.android.spdy.SpdyProtocol;

class a {
    private static String b;
    @SuppressLint({"SimpleDateFormat"})
    private final SimpleDateFormat a;
    private String c;
    private String d;
    private boolean e;
    private int f;
    private int g;
    private ArrayList<File> h;

    static {
        b = "/MiPushLog";
    }

    a() {
        this.a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.g = 2097152;
        this.h = new ArrayList();
    }

    private void a(BufferedReader bufferedReader, BufferedWriter bufferedWriter, Pattern pattern) {
        char[] cArr = new char[4096];
        int read = bufferedReader.read(cArr);
        int i = 0;
        while (read != -1 && i != 1) {
            boolean z;
            String str = new String(cArr, 0, read);
            Matcher matcher = pattern.matcher(str);
            int i2 = 0;
            int i3 = 0;
            while (i2 < read && matcher.find(i2)) {
                i2 = matcher.start();
                String substring = str.substring(i2, this.c.length() + i2);
                if (this.e) {
                    if (substring.compareTo(this.d) > 0) {
                        z = true;
                        break;
                    }
                } else if (substring.compareTo(this.c) >= 0) {
                    this.e = true;
                    i3 = i2;
                }
                int indexOf = str.indexOf(SpdyProtocol.PUBKEY_SEQ_OPEN, i2);
                i2 = indexOf != -1 ? i2 + indexOf : i2 + this.c.length();
            }
            i2 = read;
            read = i;
            if (this.e) {
                i2 -= i3;
                this.f += i2;
                if (z) {
                    bufferedWriter.write(cArr, i3, i2);
                    return;
                }
                bufferedWriter.write(cArr, i3, i2);
                if (this.f > this.g) {
                    return;
                }
            }
            boolean z2 = z;
            read = bufferedReader.read(cArr);
        }
    }

    private void b(File file) {
        Reader reader = null;
        Pattern compile = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");
        try {
            Writer bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            try {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("model :").append(Build.MODEL);
                stringBuilder.append("; os :").append(VERSION.INCREMENTAL);
                stringBuilder.append("; uid :").append(ag.e());
                stringBuilder.append("; lng :").append(Locale.getDefault().toString());
                stringBuilder.append("; sdk :19");
                stringBuilder.append("\n");
                bufferedWriter.write(stringBuilder.toString());
                this.f = 0;
                Iterator it = this.h.iterator();
                Reader reader2 = null;
                while (it.hasNext()) {
                    try {
                        reader = new BufferedReader(new InputStreamReader(new FileInputStream((File) it.next())));
                        a(reader, bufferedWriter, compile);
                        reader.close();
                        reader2 = reader;
                    } catch (FileNotFoundException e) {
                        e = e;
                        reader = reader2;
                    } catch (IOException e2) {
                        e = e2;
                        reader = reader2;
                    } catch (Throwable th) {
                        th = th;
                        reader = reader2;
                    }
                }
                com.xiaomi.channel.commonutils.file.a.a(bufferedWriter);
                com.xiaomi.channel.commonutils.file.a.a(reader2);
            } catch (FileNotFoundException e3) {
                e = e3;
            } catch (IOException e4) {
                e = e4;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    a a(File file) {
        if (file.exists()) {
            this.h.add(file);
        }
        return this;
    }

    a a(Date date, Date date2) {
        if (date.after(date2)) {
            this.c = this.a.format(date2);
            this.d = this.a.format(date);
        } else {
            this.c = this.a.format(date);
            this.d = this.a.format(date2);
        }
        return this;
    }

    File a(Context context, Date date, Date date2, File file) {
        File filesDir;
        if ("com.xiaomi.xmsf".equalsIgnoreCase(context.getPackageName())) {
            filesDir = context.getFilesDir();
            a(new File(filesDir, "xmsf.log.1"));
            a(new File(filesDir, "xmsf.log"));
        } else {
            filesDir = new File(context.getExternalFilesDir(null) + b);
            a(new File(filesDir, "log0.txt"));
            a(new File(filesDir, "log1.txt"));
        }
        if (!filesDir.isDirectory()) {
            return null;
        }
        filesDir = new File(file, date.getTime() + c.q + date2.getTime() + ".zip");
        if (filesDir.exists()) {
            return null;
        }
        a(date, date2);
        long currentTimeMillis = System.currentTimeMillis();
        File file2 = new File(file, "log.txt");
        b(file2);
        b.c(new StringBuilder("LOG: filter cost = ").append(System.currentTimeMillis() - currentTimeMillis).toString());
        if (file2.exists()) {
            currentTimeMillis = System.currentTimeMillis();
            com.xiaomi.channel.commonutils.file.a.a(filesDir, file2);
            b.c(new StringBuilder("LOG: zip cost = ").append(System.currentTimeMillis() - currentTimeMillis).toString());
            file2.delete();
            if (filesDir.exists()) {
                return filesDir;
            }
        }
        return null;
    }

    void a(int i) {
        if (i != 0) {
            this.g = i;
        }
    }
}
