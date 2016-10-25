package com.xunlei.downloadprovider.i.a;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.text.SpannableString;
import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.downloadprovider.a.b;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.b.c.e;
import com.xunlei.downloadprovider.b.c.g;
import com.xunlei.downloadprovider.businessutil.a;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.commonview.dialog.d;
import com.xunlei.downloadprovider.commonview.dialog.r;
import com.xunlei.downloadprovider.commonview.dialog.t;
import com.xunlei.downloadprovider.commonview.dialog.x;
import com.xunlei.downloadprovider.frame.MainTabActivity;
import com.xunlei.downloadprovider.loading.k;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.UpgradeAlert;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.UpgradeAlert.From;
import com.xunlei.downloadprovider.util.aa;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: Update.java
public final class c {
    public static boolean a;
    private static boolean v;
    public r b;
    private boolean c;
    private boolean d;
    private long e;
    private Activity f;
    private Context g;
    private boolean h;
    private x i;
    private d j;
    private Handler k;
    private com.xunlei.downloadprovider.model.protocol.f.c l;
    private b m;
    private RandomAccessFile n;
    private String o;
    private long p;
    private long q;
    private boolean r;
    private g s;
    private String t;
    private int u;

    static /* synthetic */ boolean a(c cVar, String str) {
        SharedPreferences sharedPreferences = cVar.f.getSharedPreferences("version_config", 0);
        if (new File(cVar.t, cVar.o).exists()) {
            if (sharedPreferences.getBoolean(str + "_pkg_" + (cVar.h ? "manual" : "auto"), false)) {
                return true;
            }
        }
        return false;
    }

    static /* synthetic */ long b(c cVar, long j) {
        long j2 = cVar.q + j;
        cVar.q = j2;
        return j2;
    }

    static /* synthetic */ void b(c cVar, int i) {
        if (cVar.j == null) {
            cVar.j = new d(cVar.f);
            cVar.u = 0;
        }
        if (i == 1) {
            cVar.j.b((CharSequence) "\u7f51\u7edc\u5f02\u5e38\uff0c\u5b89\u88c5\u5305\u4e0b\u8f7d\u672a\u6210\u529f\uff0c\u662f\u5426\u91cd\u8bd5\uff1f");
            cVar.j.d("\u91cd\u65b0\u4e0b\u8f7d");
        } else if (i == 0) {
            cVar.j.b((CharSequence) "\u5fc5\u987b\u5347\u7ea7\u624d\u80fd\u7ee7\u7eed\u4f7f\u7528\uff0c\u662f\u5426\u7ee7\u7eed\uff1f");
            cVar.j.d("\u7ee7\u7eed");
            cVar.j.setCanceledOnTouchOutside(false);
        }
        cVar.j.c("\u9000\u51fa\u7a0b\u5e8f");
        cVar.j.a(new o(cVar));
        cVar.j.b(new p(cVar, i));
        cVar.j.setOnKeyListener(new q(cVar));
        cVar.j.show();
    }

    static /* synthetic */ int s(c cVar) {
        int i = cVar.u;
        cVar.u = i + 1;
        return i;
    }

    public static boolean a(Context context) {
        return Boolean.parseBoolean(context.getResources().getString(R.string.update));
    }

    private c(Activity activity, boolean z, b bVar) {
        this.c = false;
        this.d = false;
        this.h = false;
        this.o = "MobileThunder1.apk";
        this.p = 0;
        this.r = false;
        this.u = 0;
        this.f = activity;
        this.g = activity.getApplicationContext();
        this.h = z;
        this.o = this.h ? "MobileThunder2.apk" : "MobileThunder1.apk";
        if (z) {
            this.m = null;
        } else {
            this.m = bVar;
        }
        this.t = a.d();
        this.i = new x(this.f);
        this.k = new d(this);
    }

    private void a(boolean z) {
        aa.a(BrothersApplication.a().getApplicationContext(), "update_info_manual", null);
        aa.a(BrothersApplication.a().getApplicationContext(), "update_info_auto", null);
        if (z) {
            this.f.getSharedPreferences("version_config", 0).edit().clear().commit();
            File file = new File(this.t, this.o);
            if (file.exists()) {
                file.delete();
            }
        }
    }

    private void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            XLToast.a(this.f, XLToastType.XLTOAST_TYPE_NORMAL, str);
        }
        if (this.i != null && !this.f.isFinishing()) {
            this.i.dismiss();
            this.i = null;
        }
    }

    private void a(String str, long j) {
        Editor edit = this.f.getSharedPreferences(this.f.getString(R.string.bt_sp_config_name), 0).edit();
        edit.putLong(str + "_time", j);
        edit.commit();
    }

    private int c(String str) {
        return this.f.getSharedPreferences("version_config", 0).getInt(str + "_count", 0);
    }

    private void a(String str, int i) {
        Editor edit = this.f.getSharedPreferences("version_config", 0).edit();
        edit.putInt(str + "_count", i);
        edit.commit();
    }

    private void a(String str, boolean z) {
        Editor edit = this.f.getSharedPreferences("version_config", 0).edit();
        edit.putBoolean(str + "_pkg_" + (this.h ? "manual" : "auto"), z);
        edit.commit();
    }

    private void a(int i) {
        if ((i != 2 && i != 1) || this.h || b()) {
            b(null);
            StringBuffer stringBuffer = new StringBuffer();
            if (this.r) {
                stringBuffer.append(String.format(BrothersApplication.a().getString(2131232904), new Object[]{this.l.a}));
            } else {
                stringBuffer.append(String.format(BrothersApplication.a().getString(2131232908), new Object[]{this.l.a}));
            }
            stringBuffer.append("\r\n");
            if (this.l.h != null) {
                String[] split = this.l.h.split("\n");
                if (split != null) {
                    for (int i2 = 0; i2 < split.length; i2++) {
                        if (!TextUtils.isEmpty(split[i2])) {
                            stringBuffer.append(split[i2].trim()).append("\r\n");
                        }
                    }
                }
            }
            CharSequence spannableString = new SpannableString(stringBuffer.toString());
            if (this.r) {
                d dVar = new d(this.f);
                dVar.setTitle(this.l.g);
                dVar.b(spannableString);
                dVar.setCanceledOnTouchOutside(false);
                dVar.setCancelable(false);
                dVar.c(this.l.r);
                dVar.d(this.l.s);
                dVar.a(new s(this));
                dVar.b(new t(this));
                dVar.setOnKeyListener(new u(this));
                dVar.show();
                UpgradeAlert.a(From.FORCE_UPDATE);
                return;
            }
            From from;
            t tVar = new t(this.f);
            tVar.setCanceledOnTouchOutside(false);
            this.f.getResources();
            tVar.a(this.l.g);
            tVar.a(spannableString);
            tVar.setCancelable(true);
            tVar.b(this.l.s);
            tVar.a(new v(this));
            tVar.setOnKeyListener(new e(this));
            tVar.show();
            if (this.h) {
                from = From.CONFIG_UPDATE;
            } else {
                from = From.REC_ALERT;
            }
            UpgradeAlert.a(from);
        }
    }

    private boolean b() {
        long j = ((this.l.k * 24) * 60) * 60;
        long j2 = this.l.l;
        if (j2 - this.f.getSharedPreferences(this.f.getString(R.string.bt_sp_config_name), 0).getLong(this.l.a + "_time", -1) < j) {
            return false;
        }
        int c = c(this.l.a);
        if (c >= this.l.o) {
            return false;
        }
        a(this.l.a, j2);
        a(this.l.a, c + 1);
        return true;
    }

    public final void a() {
        if (this.s != null) {
            this.s.cancel();
            this.s = null;
        }
        c();
        e();
    }

    private void c() {
        if (!(this.i == null || this.f.isFinishing())) {
            this.i.dismiss();
            this.i = null;
        }
        if (!(this.b == null || this.f.isFinishing())) {
            this.b.dismiss();
            this.b = null;
        }
        if (this.j != null && !this.f.isFinishing()) {
            this.j.dismiss();
            this.j = null;
        }
    }

    private void d() {
        if (!this.f.isFinishing()) {
            if (this.b == null) {
                this.b = new r(this.f);
                this.b.b(true);
                this.b.setTitle(2131232905);
                this.b.c("\u53d6\u6d88");
                this.b.a(new f(this));
                this.b.setOnKeyListener(new g(this));
                this.b.setCanceledOnTouchOutside(false);
            }
            this.d = false;
            this.b.show();
        }
    }

    final void a(String str) {
        if (this.s != null) {
            this.s.cancel();
        }
        new StringBuilder("start download apk file,silent=").append(this.d);
        File file = new File(this.t, this.o);
        if (file.exists()) {
            file.delete();
        }
        this.c = false;
        a(this.l.a, false);
        try {
            this.n = new RandomAccessFile(file, "rw");
            this.s = new g(str);
            this.s.a((int) XLErrorCode.ALI_AUTH_SYSTEM_ERROR, 6000);
            this.s.a(Constants.HTTP_GET, null, null, null, null, false);
            this.s.g = 1;
            this.s.d = new h(this);
            this.s.c = new i(this);
            this.s.b = new j(this);
            this.s.a = new l(this);
            new m(this).start();
        } catch (FileNotFoundException e) {
            if (!(this.d || this.b == null || this.f.isFinishing())) {
                this.b.dismiss();
            }
            this.k.obtainMessage(20008, XZBDevice.DOWNLOAD_LIST_RECYCLE, 0).sendToTarget();
            new StringBuilder("FileNotFoundException = ").append(e.getMessage()).append("===").append(this.t).append("===").append(this.o);
        }
    }

    private void e() {
        if (this.r && this.m != null) {
            this.m.a();
        }
    }

    private void f() {
        if (a) {
            g();
            return;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(805306368);
        intent.setDataAndType(Uri.fromFile(new File(this.t, this.o)), "application/vnd.android.package-archive");
        this.g.startActivity(intent);
        if (this.l != null) {
            StatReporter.reportUpdateXunlei(this.l.b, this.l.a);
        }
    }

    private void g() {
        int i = 0;
        a = false;
        aa.a(b.a(), "latest_version_updated", this.l.a);
        t tVar = new t(this.f);
        StringBuffer stringBuffer = new StringBuffer();
        if (this.r) {
            stringBuffer.append(String.format(BrothersApplication.a().getString(2131232904), new Object[]{this.l.a}));
        } else {
            stringBuffer.append(String.format(BrothersApplication.a().getString(2131232908), new Object[]{this.l.a}));
        }
        stringBuffer.append("\r\n");
        if (this.l.h != null) {
            String[] split = this.l.h.split("\n");
            if (split != null) {
                while (i < split.length) {
                    if (!TextUtils.isEmpty(split[i])) {
                        stringBuffer.append(split[i].trim()).append("\r\n");
                    }
                    i++;
                }
            }
        }
        CharSequence spannableString = new SpannableString(stringBuffer.toString());
        tVar.a(this.l.g);
        tVar.a(spannableString);
        tVar.setCancelable(true);
        tVar.b(this.l.s);
        tVar.a(new a(this));
        tVar.show();
    }

    static {
        v = false;
    }

    public static void a(Handler handler) {
        com.xunlei.downloadprovider.model.protocol.c.a();
        com.xunlei.downloadprovider.model.protocol.c.a(handler, 1);
    }

    public static void a(Activity activity, boolean z) {
        int i = 1;
        if ((!v || z) && a((Context) activity)) {
            c cVar = new c(activity, z, new n());
            if (((!aa.c(BrothersApplication.a(), "is_reported") ? 1 : 0) & b.w().equals(aa.b(BrothersApplication.a(), "latest_version"))) != 0) {
                a aVar = new a(cVar.k);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("http://upgrade.m.xunlei.com/cgi-bin/upgrade?ver=").append(b.w()).append("&prouct_id=").append(BrothersApplication.a().getString(R.string.product_id)).append("&type=update_done&flag=").append(aa.b(BrothersApplication.a(), "gray_update_flag")).append("&peerid=").append(b.d());
                new StringBuilder().append(aVar.getClass()).append("reportGrayUpdate()---sb.toString()---").append(stringBuilder.toString());
                e aVar2 = new com.xunlei.downloadprovider.b.c.a(stringBuilder.toString(), Constants.HTTP_GET, null, new a.a(aVar, (byte) 0));
                aVar2.setBpOnDataLoaderCompleteListener(new b(aVar));
                aVar.setBpFuture(aVar2);
                a.runBox(aVar);
            }
            if (k.a() > 2) {
                com.xunlei.downloadprovider.model.protocol.f.c b = b(z);
                if (!(b == null || b.a() || (z && b.c == 4))) {
                    v = true;
                    com.xunlei.downloadprovider.b.c cVar2 = new com.xunlei.downloadprovider.b.c();
                    cVar2.b = b;
                    cVar2.a = -1;
                    cVar2.c = null;
                    cVar.k.obtainMessage(10004, 0, -1, cVar2).sendToTarget();
                    return;
                }
            }
            cVar.a(false);
            if (com.xunlei.xllib.a.b.a(activity)) {
                v = true;
                if (cVar.h && !cVar.f.isFinishing()) {
                    cVar.i.a("\u66f4\u65b0\u68c0\u6d4b\u4e2d");
                    cVar.i.show();
                }
                if (!cVar.h) {
                    i = 0;
                }
                com.xunlei.downloadprovider.model.protocol.c.a();
                com.xunlei.downloadprovider.model.protocol.c.a(cVar.k, i);
            }
        }
    }

    private static com.xunlei.downloadprovider.model.protocol.f.c b(boolean z) {
        Object b = aa.b(BrothersApplication.a().getApplicationContext(), z ? "update_info_manual" : "update_info_auto");
        if (TextUtils.isEmpty(b)) {
            return null;
        }
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(b);
        } catch (JSONException e) {
            e.printStackTrace();
            jSONObject = null;
        }
        return com.xunlei.downloadprovider.model.protocol.f.c.a(jSONObject);
    }

    private static boolean a(String[] strArr) {
        if (strArr == null) {
            return false;
        }
        PackageManager packageManager = BrothersApplication.a.getPackageManager();
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            String str = strArr[i];
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
                if (packageInfo.applicationInfo == null || !packageInfo.applicationInfo.enabled) {
                    i++;
                } else {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse(new StringBuilder("market://details?id=").append(BrothersApplication.a.getPackageName()).toString()));
                    intent.setFlags(268435456);
                    intent.setPackage(str);
                    BrothersApplication.a.startActivity(intent);
                    return true;
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    static /* synthetic */ void a(boolean z, com.xunlei.downloadprovider.model.protocol.f.c cVar) {
        String str;
        Context applicationContext = BrothersApplication.a().getApplicationContext();
        if (z) {
            str = "update_info_manual";
        } else {
            str = "update_info_auto";
        }
        aa.a(applicationContext, str, cVar.b());
    }

    static /* synthetic */ void h(c cVar) {
        if (cVar.l.n != 2) {
            cVar.a(cVar.l.c);
        } else if (cVar.l != null && cVar.b()) {
            if (cVar.f instanceof MainTabActivity) {
                MainTabActivity mainTabActivity = (MainTabActivity) cVar.f;
                CharSequence charSequence = cVar.l.p;
                r rVar = new r(cVar);
                if (mainTabActivity.d != null) {
                    mainTabActivity.d.setOnClickListener(rVar);
                    mainTabActivity.d.setVisibility(0);
                }
                if (mainTabActivity.e != null) {
                    mainTabActivity.e.setText(charSequence);
                }
            }
            UpgradeAlert.a(From.REC_BAR);
        }
    }

    static /* synthetic */ void d(c cVar, String str) {
        if (!TextUtils.isEmpty(str)) {
            XLToast.a(cVar.f, XLToastType.XLTOAST_TYPE_ALARM, str);
        }
        if (cVar.i != null && !cVar.f.isFinishing()) {
            cVar.i.dismiss();
            cVar.i = null;
        }
    }

    static /* synthetic */ void m(c cVar) {
        cVar.a(cVar.l.a, -1);
        int c = cVar.c(cVar.l.a) - 1;
        if (c < 0) {
            c = 0;
        }
        cVar.a(cVar.l.a, c);
    }

    static /* synthetic */ void a(c cVar, DialogInterface dialogInterface) {
        if (cVar.l.m == 2) {
            List list = cVar.l.q;
            String[] strArr = null;
            if (list != null && list.size() > 0) {
                Collections.sort(list);
                int size = list.size();
                String[] strArr2 = new String[size];
                for (int i = 0; i < size; i++) {
                    com.xunlei.downloadprovider.model.protocol.f.c.a aVar = (com.xunlei.downloadprovider.model.protocol.f.c.a) list.get(i);
                    if (aVar != null) {
                        strArr2[i] = aVar.a;
                    }
                }
                strArr = strArr2;
            }
            if (a(strArr)) {
                Object obj = null;
                if (r0) {
                    if (cVar.c) {
                        if (cVar.h) {
                            cVar.d();
                        } else {
                            cVar.d = true;
                            XLToast.b(cVar.f, XLToastType.XLTOAST_TYPE_NORMAL, "\u540e\u53f0\u5347\u7ea7\u4e2d\uff0c\u8bf7\u7a0d\u5019");
                        }
                        cVar.a(cVar.l.f);
                    } else {
                        cVar.f();
                    }
                }
                if (!cVar.r) {
                    dialogInterface.dismiss();
                }
            }
        }
        boolean z = true;
        if (z) {
            if (cVar.c) {
                if (cVar.h) {
                    cVar.d();
                } else {
                    cVar.d = true;
                    XLToast.b(cVar.f, XLToastType.XLTOAST_TYPE_NORMAL, "\u540e\u53f0\u5347\u7ea7\u4e2d\uff0c\u8bf7\u7a0d\u5019");
                }
                cVar.a(cVar.l.f);
            } else {
                cVar.f();
            }
        }
        if (!cVar.r) {
            dialogInterface.dismiss();
        }
    }
}
