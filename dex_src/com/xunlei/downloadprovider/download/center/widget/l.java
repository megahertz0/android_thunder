package com.xunlei.downloadprovider.download.center.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.businessutil.b;
import com.xunlei.downloadprovider.commonview.dialog.XLBaseDialog;
import com.xunlei.downloadprovider.download.create.CreateBtTask;
import com.xunlei.downloadprovider.download.create.CreateUrlTask;
import com.xunlei.downloadprovider.qrcode.CameraActivity;
import com.xunlei.xllib.a.d;
import java.io.File;

// compiled from: DownloadCreateMoreTaskDialog.java
public final class l extends XLBaseDialog {
    private TextView a;
    private TextView b;
    private TextView c;

    public l(Context context) {
        this(context, (byte) 0);
    }

    private l(Context context, byte b) {
        super(context, 2131427870);
        this.a = null;
        this.b = null;
        this.c = null;
    }

    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_create_homepage);
        this.a = (TextView) findViewById(R.id.tv_create_homepage_qrcode);
        this.b = (TextView) findViewById(R.id.tv_create_homepage_link);
        this.c = (TextView) findViewById(R.id.tv_create_homepage_bt);
        ((TextView) findViewById(R.id.tv_cancel)).setOnClickListener(new m(this));
        this.a.setOnClickListener(new n(this));
        this.b.setOnClickListener(new o(this));
        this.c.setOnClickListener(new p(this));
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        Window window = getWindow();
        LayoutParams attributes = window.getAttributes();
        attributes.gravity = 81;
        attributes.width = d.a(getContext().getApplicationContext());
        attributes.height = -2;
        attributes.flags &= -1025;
        attributes.flags |= 2048;
        attributes.windowAnimations = 2131427601;
        window.setAttributes(attributes);
    }

    private static String a() {
        String string = b.a().a.getSharedPreferences("settingstate", 0).getString("last_torrent_open_path", null);
        if (string == null) {
            return null;
        }
        try {
            if (!new File(string).exists()) {
                string = null;
            }
            return string;
        } catch (Exception e) {
            return null;
        }
    }

    static /* synthetic */ void a(l lVar) {
        Intent intent = new Intent();
        intent.setClass(lVar.getContext(), CameraActivity.class);
        CameraActivity.b = 2;
        lVar.getContext().startActivity(intent);
    }

    static /* synthetic */ void b(l lVar) {
        Intent intent = new Intent();
        intent.setClass(lVar.getContext(), CreateUrlTask.class);
        lVar.getContext().startActivity(intent);
    }

    static /* synthetic */ void c(l lVar) {
        String a = a();
        Intent intent = new Intent();
        intent.setClass(lVar.getContext(), CreateBtTask.class);
        if (a != null) {
            intent.putExtra("last_torrent_open_path", a);
        }
        lVar.getContext().startActivity(intent);
    }
}
