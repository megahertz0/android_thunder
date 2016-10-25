package com.baidu.mobads.h;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.Base64;
import anet.channel.security.ISecurity;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.j.m;
import com.sina.weibo.sdk.component.GameManager;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

public class b extends File {
    private e a;
    private Class<?> b;
    private Context c;
    private PublicKey d;
    private IXAdLogger e;

    class a implements FilenameFilter {
        a() {
        }

        public boolean accept(File file, String str) {
            return true;
        }
    }

    public b(String str, Context context) {
        this(str, context, null);
    }

    public b(String str, Context context, e eVar) {
        super(str);
        this.b = null;
        this.c = null;
        this.e = m.a().f();
        this.c = context;
        this.a = eVar;
        if (eVar != null) {
            try {
                this.d = c("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDBcp8gg3O7bjdnz+pSxg+JH/mbcKfm7dEjcRqVNAFwG7bTpLwDQh40bZJzrcBKQWbD6kArR6TPuQUCMQ09/y55Vk1P2Kq7vJGGisFpjlqv2qlg8drLdhXkLQUt/SeZVJgT+CNxVbuzxAF61EEf8M0MHi1I2dm6n6lOA6fomiCD9wIDAQAB");
            } catch (Exception e) {
                this.d = null;
            }
        }
    }

    protected void a() {
        if (this.a != null) {
            String a = a(new File(getAbsolutePath()));
            String b = b(this.a.d());
            if (!b.equalsIgnoreCase(a)) {
                throw new com.baidu.mobads.h.g.a(new StringBuilder("doCheckApkIntegrity failed, md5sum: ").append(a).append(", checksum in json info: ").append(b).toString());
            }
        }
    }

    protected Class<?> b() {
        if (this.b == null) {
            File file = new File(getAbsolutePath());
            this.b = b(file);
            file.delete();
        }
        return this.b;
    }

    protected void a(String str) {
        renameTo(new File(str));
    }

    protected double c() {
        return this.a == null ? 0.0d : this.a.b();
    }

    private String b(String str) {
        if (this.d != null) {
            byte[] decode = Base64.decode(str, 0);
            try {
                Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                instance.init(XZBDevice.DOWNLOAD_LIST_RECYCLE, this.d);
                return new String(instance.doFinal(decode), GameManager.DEFAULT_CHARSET).trim();
            } catch (Throwable e) {
                this.e.e("ErrorWhileVerifySigNature", e);
            }
        }
        return null;
    }

    private static PublicKey c(String str) {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("NoSuchAlgorithmException");
        } catch (InvalidKeySpecException e2) {
            throw new Exception("InvalidKeySpecException");
        } catch (NullPointerException e3) {
            throw new Exception("NullPointerException");
        }
    }

    private String a(File file) {
        String str = com.umeng.a.d;
        try {
            InputStream fileInputStream = new FileInputStream(file);
            try {
                MessageDigest instance = MessageDigest.getInstance(ISecurity.SIGN_ALGORITHM_MD5);
                do {
                } while (new DigestInputStream(fileInputStream, instance).read(new byte[4096]) != -1);
                byte[] digest = instance.digest();
                int i = 0;
                while (i < digest.length) {
                    String str2 = str + Integer.toString((digest[i] & 255) + 256, R.styleable.Toolbar_titleMarginBottom).substring(1);
                    i++;
                    str = str2;
                }
                try {
                    fileInputStream.close();
                } catch (Exception e) {
                    this.e.e("XAdLocalApkFile", e.getMessage());
                }
            } catch (Exception e2) {
                e = e2;
                this.e.e("XAdLocalApkFile", e.getMessage());
                str = com.umeng.a.d;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                return str;
            }
        } catch (Exception e3) {
            e = e3;
            fileInputStream = null;
            try {
                Exception e4;
                this.e.e("XAdLocalApkFile", e4.getMessage());
                str = com.umeng.a.d;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception e5) {
                        this.e.e("XAdLocalApkFile", e5.getMessage());
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th2;
            }
            return str;
        } catch (Throwable th3) {
            th2 = th3;
            fileInputStream = null;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception e52) {
                    this.e.e("XAdLocalApkFile", e52.getMessage());
                }
            }
            throw th2;
        }
        return str;
    }

    private void d() {
        File[] listFiles = this.c.getFilesDir().listFiles(new a());
        int i = 0;
        while (listFiles != null && i < listFiles.length) {
            if (listFiles[i].getAbsolutePath().contains("__xadsdk__remote__final__")) {
                new StringBuilder("clearDexCacheFiles-->").append(i).append("--").append(listFiles[i].getAbsolutePath());
                listFiles[i].delete();
            }
            i++;
        }
    }

    @TargetApi(14)
    private Class<?> b(File file) {
        Class<?> forName;
        this.e.d("XAdLocalApkFile", new StringBuilder("Android version:").append(VERSION.RELEASE).toString());
        try {
            synchronized (g.class) {
                String absolutePath = file.getAbsolutePath();
                ClassLoader classLoader = getClass().getClassLoader();
                String absolutePath2 = this.c.getFilesDir().getAbsolutePath();
                ClassLoader dexClassLoader = new DexClassLoader(absolutePath, absolutePath2, null, classLoader);
                this.e.i("XAdLocalApkFile", new StringBuilder("dexPath=").append(absolutePath).append(", cl=").append(classLoader).append(", dir=").append(absolutePath2).append(", loader=").append(dexClassLoader).append(", len=").append(file.length()).append(", list=").append(file.list()).toString());
                forName = Class.forName("com.baidu.mobads.container.AllInOneXAdContainerFactory", true, dexClassLoader);
            }
            d();
        } catch (Exception e) {
            absolutePath = e.getMessage();
            this.e.e("XAdLocalApkFile", absolutePath);
            d();
            forName = null;
        }
        this.e.i("XAdLocalApkFile", new StringBuilder("jar.path=").append(file.getAbsolutePath()).append(", clz=").append(forName).toString());
        return forName;
    }
}
