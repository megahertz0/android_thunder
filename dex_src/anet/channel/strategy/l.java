package anet.channel.strategy;

import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.util.ALog;
import anet.channel.util.h;
import com.xunlei.tdlive.R;
import java.io.File;
import java.io.Serializable;
import java.util.Arrays;

// compiled from: Taobao
class l {
    private static File a;

    l() {
    }

    static {
        a = null;
    }

    public static void a() {
        try {
            if (GlobalAppRuntimeInfo.getContext() != null) {
                File file = new File(GlobalAppRuntimeInfo.getContext().getExternalFilesDir(null), "awcn_strategy");
                a = file;
                if (!a(file)) {
                    ALog.e("awcn.StrategySerializeHelper", "create directory failed!!!", null, "dir", a.getAbsolutePath());
                }
                if (!GlobalAppRuntimeInfo.isTargetProcess()) {
                    String currentProcess = GlobalAppRuntimeInfo.getCurrentProcess();
                    File file2 = new File(a, currentProcess.substring(currentProcess.indexOf(R.styleable.AppCompatTheme_toolbarStyle) + 1));
                    a = file2;
                    if (!a(file2)) {
                        ALog.e("awcn.StrategySerializeHelper", "create directory failed!!!", null, "dir", a.getAbsolutePath());
                    }
                }
                d();
            }
        } catch (Throwable th) {
            ALog.e("awcn.StrategySerializeHelper", "StrategySerializeHelper initialize failed!!!", null, th, new Object[0]);
        }
    }

    private static boolean a(File file) {
        return (file == null || file.exists()) ? true : file.mkdir();
    }

    public static File a(String str) {
        a(a);
        return new File(a, str);
    }

    static synchronized void b() {
        synchronized (l.class) {
            ALog.i("awcn.StrategySerializeHelper", "clear start.", null, new Object[0]);
            if (a != null) {
                File[] listFiles = a.listFiles();
                if (listFiles != null) {
                    for (File file : listFiles) {
                        file.delete();
                    }
                    ALog.i("awcn.StrategySerializeHelper", "clear end.", null, new Object[0]);
                }
            }
        }
    }

    static synchronized File[] c() {
        File[] fileArr;
        synchronized (l.class) {
            if (a == null) {
                fileArr = null;
            } else {
                fileArr = a.listFiles();
                if (fileArr != null) {
                    Arrays.sort(fileArr, new m());
                }
            }
        }
        return fileArr;
    }

    static synchronized void d() {
        int i = 0;
        synchronized (l.class) {
            File[] c = c();
            if (c != null) {
                for (int i2 = 0; i2 < c.length; i2++) {
                    File file = c[i2];
                    if (System.currentTimeMillis() - file.lastModified() >= 604800000) {
                        file.delete();
                    } else if (!file.getName().equalsIgnoreCase("config")) {
                        int i3 = i + 1;
                        if (((long) i) > 10) {
                            file.delete();
                        }
                        i = i3;
                    }
                }
            }
        }
    }

    static synchronized void a(Serializable serializable, String str) {
        synchronized (l.class) {
            h.a(serializable, a(str));
        }
    }

    static synchronized <T> T b(String str) {
        T a;
        synchronized (l.class) {
            a = h.a(a(str));
        }
        return a;
    }
}
