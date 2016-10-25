package com.xunlei.downloadprovider.member.payment.a;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.toolbox.t;
import com.umeng.a;
import com.xunlei.downloadprovider.a.j;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.member.payment.bean.PayConfigurationParam;
import com.xunlei.downloadprovider.member.payment.external.PayUtil;
import com.xunlei.downloadprovider.member.payment.external.d;
import com.xunlei.downloadprovider.search.b.b;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

// compiled from: PayNetworkHelper.java
public class f extends e {
    private static f b;
    private Handler a;
    private ArrayList<PayConfigurationParam> c;
    private b<ArrayList<PayConfigurationParam>> d;

    private f() {
        this.a = new Handler(Looper.getMainLooper());
        this.c = null;
    }

    public static f a() {
        if (b == null) {
            synchronized (f.class) {
                if (b == null) {
                    b = new f();
                }
            }
        }
        return b;
    }

    private void b() {
        Request tVar = new t("http://act.vip.xunlei.com/payguide/android/1.0.0.js", new g(this), new h(this));
        tVar.setShouldCache(false);
        a(tVar);
    }

    private void a(ArrayList<PayConfigurationParam> arrayList) {
        this.a.post(new i(this, arrayList));
    }

    public final void a(b<ArrayList<PayConfigurationParam>> bVar) {
        Object obj = 1;
        this.d = bVar;
        Object b = new j(BrothersApplication.a(), "pay_configuration_param").b("pay_save_time", a.d);
        if (!TextUtils.isEmpty(b) && PayUtil.a(PayUtil.c(b), new Date())) {
            obj = null;
        }
        if (obj != null) {
            b();
        } else if (this.c == null) {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(BrothersApplication.a().getFilesDir().getPath() + "/pay_config.ser"));
                Key a = com.xunlei.downloadprovider.member.payment.external.a.a();
                String str = (String) objectInputStream.readObject();
                Cipher instance = Cipher.getInstance("DESede");
                instance.init(XZBDevice.DOWNLOAD_LIST_RECYCLE, a);
                this.c = d.a(new String(instance.doFinal(com.xunlei.downloadprovider.member.payment.external.a.a(str))));
                objectInputStream.close();
                a(this.c);
            } catch (Exception e) {
                e.printStackTrace();
                b();
            }
        } else {
            a(this.c);
        }
    }

    static /* synthetic */ void a(String str) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(BrothersApplication.a().getFilesDir().getPath() + "/pay_config.ser"));
            KeyGenerator instance = KeyGenerator.getInstance("DESede");
            instance.init(168);
            new ObjectOutputStream(new FileOutputStream(BrothersApplication.a().getFilesDir().getPath() + "pay_common_info.txt")).writeObject(instance.generateKey());
            Key a = com.xunlei.downloadprovider.member.payment.external.a.a();
            Cipher instance2 = Cipher.getInstance("DESede");
            instance2.init(1, a);
            objectOutputStream.writeObject(com.xunlei.downloadprovider.member.payment.external.a.a(instance2.doFinal(str.getBytes("UTF8"))));
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
