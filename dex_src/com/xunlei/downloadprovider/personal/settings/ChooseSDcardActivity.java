package com.xunlei.downloadprovider.personal.settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import android.view.View;
import com.xunlei.downloadprovider.a.k;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.app.BrothersApplication.d;
import com.xunlei.downloadprovider.businessutil.a;
import com.xunlei.downloadprovider.businessutil.b;
import com.xunlei.downloadprovider.commonview.f;
import com.xunlei.downloadprovider.personal.settings.ui.SDCardViewHolder;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.reflect.Method;

public class ChooseSDcardActivity extends BaseActivity {
    private View a;
    private View b;
    private SDCardViewHolder c;
    private SDCardViewHolder d;
    private String e;
    private String f;
    private String g;
    private StorageManager h;
    private Method i;
    private d j;

    public ChooseSDcardActivity() {
        this.e = k.b();
        this.f = k.c();
        this.g = getClass().getSimpleName();
        this.j = new j(this);
    }

    public static void a(Context context) {
        context.startActivity(new Intent(context, ChooseSDcardActivity.class));
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968650);
        this.h = (StorageManager) getSystemService("storage");
        try {
            this.i = this.h.getClass().getMethod("getVolumePaths", new Class[0]);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        new f((Activity) this).i.setText(2131232462);
        this.a = findViewById(2131755440);
        this.b = findViewById(2131755437);
        a();
        BrothersApplication.a().a(this.j);
    }

    public void onResume() {
        super.onResume();
        b();
    }

    public void onPause() {
        super.onPause();
    }

    protected void onDestroy() {
        b.a().g();
        BrothersApplication.a().b(this.j);
        super.onDestroy();
    }

    private void a() {
        boolean z = true;
        new StringBuilder("primary:").append(this.e);
        new StringBuilder("slave:").append(this.f);
        if (TextUtils.isEmpty(this.e) && TextUtils.isEmpty(this.f)) {
            this.a.setVisibility(0);
            this.b.setVisibility(XZBDevice.Wait);
            return;
        }
        boolean z2;
        this.a.setVisibility(XZBDevice.Wait);
        this.b.setVisibility(0);
        int c = b.a().c();
        if (c == 2 && TextUtils.isEmpty(this.f)) {
            b.a().a(1);
            z2 = true;
        } else {
            int i = c;
        }
        this.c = (SDCardViewHolder) findViewById(2131755438);
        this.c.a(this.e, 1, z2);
        this.d = (SDCardViewHolder) findViewById(2131755439);
        if (com.xunlei.downloadprovider.a.b.i() >= 19) {
            this.d.setAllowChoosePath(false);
            this.d.k = true;
            this.d.l = false;
        }
        SDCardViewHolder sDCardViewHolder = this.d;
        String str = this.f;
        if (!z2) {
            z = false;
        }
        sDCardViewHolder.a(str, XZBDevice.DOWNLOAD_LIST_RECYCLE, z);
        this.c.setOnSelecteChangedListener(new k(this));
        this.d.setOnSelecteChangedListener(new l(this));
        b();
    }

    private void b() {
        String a = a.a(1, true);
        String a2 = a.a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE, true);
        if (this.c != null) {
            this.c.setSDCardPath(this.e + a);
        }
        if (this.d != null) {
            this.d.setSDCardPath(this.f + a2);
        }
    }
}
