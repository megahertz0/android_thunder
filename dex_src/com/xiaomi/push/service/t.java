package com.xiaomi.push.service;

import android.os.Process;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.network.Host;
import com.xiaomi.push.protobuf.a.a;
import com.xiaomi.stats.e;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class t {
    private static final Pattern a;
    private static long b;
    private static ThreadPoolExecutor c;

    static {
        a = Pattern.compile("([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})");
        b = 0;
        c = new ThreadPoolExecutor(1, 1, 20, TimeUnit.SECONDS, new LinkedBlockingQueue());
    }

    public static void a() {
        long currentTimeMillis = System.currentTimeMillis();
        if ((c.getActiveCount() <= 0 || currentTimeMillis - b >= 1800000) && e.a().c()) {
            a d = ag.a().d();
            if (d != null && d.l() > 0) {
                b = currentTimeMillis;
                a(d.k(), true);
            }
        }
    }

    public static void a(List<String> list, boolean z) {
        c.execute(new u(list, z));
    }

    public static void b() {
        Object c = c("/proc/self/net/tcp");
        if (!TextUtils.isEmpty(c)) {
            b.a(new StringBuilder("dump tcp for uid = ").append(Process.myUid()).toString());
            b.a(c);
        }
        c = c("/proc/self/net/tcp6");
        if (!TextUtils.isEmpty(c)) {
            b.a(new StringBuilder("dump tcp6 for uid = ").append(Process.myUid()).toString());
            b.a(c);
        }
    }

    private static boolean b(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            b.a(new StringBuilder("ConnectivityTest: begin to connect to ").append(str).toString());
            Socket socket = new Socket();
            socket.connect(Host.b(str, 5222), 5000);
            socket.setTcpNoDelay(true);
            b.a(new StringBuilder("ConnectivityTest: connect to ").append(str).append(" in ").append(System.currentTimeMillis() - currentTimeMillis).toString());
            socket.close();
            return true;
        } catch (Throwable th) {
            b.d(new StringBuilder("ConnectivityTest: could not connect to:").append(str).append(" exception: ").append(th.getClass().getSimpleName()).append(" description: ").append(th.getMessage()).toString());
            return false;
        }
    }

    private static String c(String str) {
        try {
            Reader bufferedReader = new BufferedReader(new FileReader(new File(str)));
            try {
                StringBuilder stringBuilder = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        stringBuilder.append("\n");
                        stringBuilder.append(readLine);
                    } else {
                        String toString = stringBuilder.toString();
                        com.xiaomi.channel.commonutils.file.a.a(bufferedReader);
                        return toString;
                    }
                }
            } catch (Exception e) {
            } catch (Throwable th) {
                Throwable th2 = th;
                com.xiaomi.channel.commonutils.file.a.a(bufferedReader);
                throw th2;
            }
        } catch (Exception e2) {
            bufferedReader = null;
            com.xiaomi.channel.commonutils.file.a.a(bufferedReader);
            return null;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            bufferedReader = null;
            th2 = th4;
            com.xiaomi.channel.commonutils.file.a.a(bufferedReader);
            throw th2;
        }
    }
}
