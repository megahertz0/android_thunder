package com.taobao.accs.d;

import android.content.Context;
import android.content.SharedPreferences;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import dalvik.system.DexClassLoader;
import java.io.File;

// compiled from: Taobao
public class a {
    private static a a;
    private ClassLoader b;
    private boolean c;
    private Context d;

    // compiled from: Taobao
    private static class a extends DexClassLoader {
        private ClassLoader a;

        public a(String str, String str2, String str3, ClassLoader classLoader) {
            super(str, str2, str3, classLoader.getParent());
            this.a = classLoader;
        }

        protected Class<?> findClass(String str) throws ClassNotFoundException {
            try {
                return super.findClass(str);
            } catch (Exception e) {
                return this.a.loadClass(str);
            }
        }

        public String toString() {
            return new StringBuilder("ACCSClassLoader$InnerClassLoader$").append(hashCode()).toString();
        }
    }

    public a() {
        this.b = null;
        this.c = false;
    }

    static {
        a = null;
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a();
            }
            aVar = a;
        }
        return aVar;
    }

    public synchronized ClassLoader a(Context context) {
        if (context != null) {
            this.d = context;
        }
        if (this.b == null) {
            ALog.d("ACCSClassLoader", "create new class loader", new Object[0]);
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SP_FILE_NAME, 0);
            String string = sharedPreferences.getString(Constants.SP_KEY_UPDATE_FOLDER, null);
            ALog.d("ACCSClassLoader", new StringBuilder("baseUpdateFolder:").append(string).toString(), new Object[0]);
            if (string != null) {
                File dir = context.getDir(string, 0);
                if (dir.exists() && dir.isDirectory()) {
                    File file = new File(dir, Constants.UPDATE_DEX_FILE);
                    if (file.exists() && file.isFile() && sharedPreferences.getInt(Constants.SP_KEY_UPDATE_VERSION, Constants.SDK_VERSION_CODE) > 212) {
                        if (sharedPreferences.getBoolean(Constants.SP_KEY_UPDATE_DONE, false)) {
                            ALog.d("ACCSClassLoader", "dexopt already done", new Object[0]);
                            this.b = new a(file.getAbsolutePath(), dir.getAbsolutePath(), new File(dir.getParentFile(), "lib").getAbsolutePath(), a.class.getClassLoader());
                        } else {
                            ALog.d("ACCSClassLoader", "try dexopt", new Object[0]);
                            a(file.getAbsolutePath(), dir.getAbsolutePath());
                        }
                    }
                }
            }
        }
        if (this.b == null) {
            ALog.d("ACCSClassLoader", "get defalut class loader", new Object[0]);
            this.b = a.class.getClassLoader();
        }
        return this.b;
    }

    private synchronized void a(String str, String str2) {
        if (this.c) {
            ALog.d("ACCSClassLoader", "dexOpting, exit", new Object[0]);
        } else {
            this.c = true;
            new b(this, str, str2).start();
        }
    }
}
