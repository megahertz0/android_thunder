package com.umeng.analytics;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.taobao.accs.common.Constants;
import com.umeng.analytics.h.b;
import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Locale;
import u.aly.t;
import u.aly.u;
import u.aly.v;

// compiled from: StoreHelper.java
public final class h {
    private static h a = null;
    private static Context b = null;
    private static String c = null;
    private static long e = 0;
    private static long f = 0;
    private static final String g = "mobclick_agent_user_";
    private static final String h = "mobclick_agent_online_setting_";
    private static final String i = "mobclick_agent_header_";
    private static final String j = "mobclick_agent_update_";
    private static final String k = "mobclick_agent_state_";
    private static final String l = "mobclick_agent_cached_";
    private a d;

    // compiled from: StoreHelper.java
    public static class a {
        private final int a;
        private File b;
        private FilenameFilter c;

        public a(Context context) {
            this(context, ".um");
        }

        public a(Context context, String str) {
            this.a = 10;
            this.c = new FilenameFilter() {
                public boolean accept(File file, String str) {
                    return str.startsWith("um");
                }
            };
            this.b = new File(context.getFilesDir(), str);
            if (!this.b.exists() || !this.b.isDirectory()) {
                this.b.mkdir();
            }
        }

        public boolean a() {
            File[] listFiles = this.b.listFiles();
            return listFiles != null && listFiles.length > 0;
        }

        public void a(b bVar) {
            File[] listFiles = this.b.listFiles(this.c);
            if (listFiles != null && listFiles.length > 0) {
                bVar.a(this.b);
                int length = listFiles.length;
                for (int i = 0; i < length; i++) {
                    try {
                        if (bVar.b(listFiles[i])) {
                            listFiles[i].delete();
                        }
                    } catch (Throwable th) {
                        listFiles[i].delete();
                    }
                }
                bVar.c(this.b);
            }
        }

        public void a(byte[] bArr) {
            int i = 0;
            if (bArr != null && bArr.length != 0) {
                try {
                    u.a(new File(this.b, String.format(Locale.US, "um_cache_%d.env", new Object[]{Long.valueOf(System.currentTimeMillis())})), bArr);
                } catch (Exception e) {
                }
                File[] listFiles = this.b.listFiles(this.c);
                if (listFiles != null && listFiles.length >= 10) {
                    Arrays.sort(listFiles);
                    int length = listFiles.length - 10;
                    while (i < length) {
                        listFiles[i].delete();
                        i++;
                    }
                }
            }
        }

        public void b() {
            File[] listFiles = this.b.listFiles(this.c);
            if (listFiles != null && listFiles.length > 0) {
                for (File file : listFiles) {
                    file.delete();
                }
            }
        }

        public int c() {
            File[] listFiles = this.b.listFiles(this.c);
            return (listFiles == null || listFiles.length <= 0) ? 0 : listFiles.length;
        }
    }

    // compiled from: StoreHelper.java
    public static interface b {
        void a(File file);

        boolean b(File file);

        void c(File file);
    }

    static {
        a = null;
        e = 1209600000;
        f = 2097152;
    }

    public h(Context context) {
        this.d = new a(context);
        b = context.getApplicationContext();
        c = context.getPackageName();
    }

    public static synchronized h a(Context context) {
        h hVar;
        synchronized (h.class) {
            if (a == null) {
                a = new h(context);
            }
            hVar = a;
        }
        return hVar;
    }

    private static boolean a(File file) {
        return file.exists() && file.length() > f;
    }

    public final void a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            Editor edit = l().edit();
            edit.putString("au_p", str);
            edit.putString("au_u", str2);
            edit.commit();
        }
    }

    public final String[] a() {
        SharedPreferences l = l();
        String string = l.getString("au_p", null);
        String string2 = l.getString("au_u", null);
        if (string == null || string2 == null) {
            return null;
        }
        return new String[]{string, string2};
    }

    public final void b() {
        l().edit().remove("au_p").remove("au_u").commit();
    }

    public final String c() {
        SharedPreferences sharedPreferences = b.getSharedPreferences("umeng_general_config", 0);
        return sharedPreferences != null ? sharedPreferences.getString(Constants.SP_KEY_APPKEY, null) : null;
    }

    public final void a(String str) {
        SharedPreferences sharedPreferences = b.getSharedPreferences("umeng_general_config", 0);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString(Constants.SP_KEY_APPKEY, str).commit();
        }
    }

    public final String d() {
        SharedPreferences sharedPreferences = b.getSharedPreferences("umeng_general_config", 0);
        return sharedPreferences != null ? sharedPreferences.getString(LogBuilder.KEY_CHANNEL, null) : null;
    }

    public final void b(String str) {
        SharedPreferences sharedPreferences = b.getSharedPreferences("umeng_general_config", 0);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString(LogBuilder.KEY_CHANNEL, str).commit();
        }
    }

    public final byte[] e() {
        Exception e;
        Throwable th;
        byte[] bArr = null;
        String n = n();
        File file = new File(b.getFilesDir(), n);
        if (a(file)) {
            file.delete();
            return null;
        } else if (!file.exists()) {
            return null;
        } else {
            InputStream openFileInput;
            try {
                openFileInput = b.openFileInput(n);
                try {
                    bArr = u.b(openFileInput);
                    u.c(openFileInput);
                    return bArr;
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Exception e3) {
                e = e3;
                openFileInput = null;
                try {
                    e.printStackTrace();
                    u.c(openFileInput);
                    return bArr;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                openFileInput = null;
                th = th3;
                u.c(openFileInput);
                throw th;
            }
        }
    }

    public final void a(byte[] bArr) {
        try {
            u.a(new File(b.getFilesDir(), n()), bArr);
        } catch (Throwable e) {
            v.a(e);
        }
    }

    public final void f() {
        b.deleteFile(m());
        b.deleteFile(n());
    }

    public final void b(byte[] bArr) {
        this.d.a(bArr);
    }

    public final boolean g() {
        return this.d.a();
    }

    public final a h() {
        return this.d;
    }

    private SharedPreferences l() {
        return b.getSharedPreferences(new StringBuilder(g).append(c).toString(), 0);
    }

    public final SharedPreferences i() {
        return b.getSharedPreferences(new StringBuilder(i).append(c).toString(), 0);
    }

    public final SharedPreferences j() {
        return b.getSharedPreferences(new StringBuilder(j).append(c).toString(), 0);
    }

    public final SharedPreferences k() {
        return b.getSharedPreferences(new StringBuilder(k).append(c).toString(), 0);
    }

    private String m() {
        return new StringBuilder(i).append(c).toString();
    }

    private String n() {
        return new StringBuilder(l).append(c).append(t.a(b)).toString();
    }
}
