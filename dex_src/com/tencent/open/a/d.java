package com.tencent.open.a;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.os.StatFs;
import com.tencent.open.a.d.c;
import java.io.File;
import java.text.SimpleDateFormat;

// compiled from: ProGuard
public class d {

    // compiled from: ProGuard
    public static final class a {
        public static final boolean a(int i, int i2) {
            return i2 == (i & i2);
        }
    }

    // compiled from: ProGuard
    public static final class b {
        public static boolean a() {
            String externalStorageState = Environment.getExternalStorageState();
            return "mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState);
        }

        public static c b() {
            return !a() ? null : c.b(Environment.getExternalStorageDirectory());
        }
    }

    // compiled from: ProGuard
    public static class c {
        private File a;
        private long b;
        private long c;

        public File a() {
            return this.a;
        }

        public void a(File file) {
            this.a = file;
        }

        public long b() {
            return this.b;
        }

        public void a(long j) {
            this.b = j;
        }

        public long c() {
            return this.c;
        }

        public void b(long j) {
            this.c = j;
        }

        public static com.tencent.open.a.d.c b(File file) {
            com.tencent.open.a.d.c cVar = new com.tencent.open.a.d.c();
            cVar.a(file);
            StatFs statFs = new StatFs(file.getAbsolutePath());
            long blockSize = (long) statFs.getBlockSize();
            long availableBlocks = (long) statFs.getAvailableBlocks();
            cVar.a(((long) statFs.getBlockCount()) * blockSize);
            cVar.b(blockSize * availableBlocks);
            return cVar;
        }

        public String toString() {
            return String.format("[%s : %d / %d]", new Object[]{a().getAbsolutePath(), Long.valueOf(c()), Long.valueOf(b())});
        }
    }

    // compiled from: ProGuard
    public static final class d {
        @SuppressLint({"SimpleDateFormat"})
        public static SimpleDateFormat a(String str) {
            return new SimpleDateFormat(str);
        }
    }
}
