package com.android.providers.downloads;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.text.format.Formatter;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.download.proguard.ag;
import com.xunlei.download.proguard.am;
import com.xunlei.download.proguard.d;
import java.util.HashMap;
import java.util.Map;

public class SizeLimitActivity extends Activity implements OnCancelListener, OnClickListener {
    private Dialog a;
    private Map<Uri, a> b;
    private Uri c;

    static class a {
        boolean a;
        long b;

        a(boolean z, long j) {
            this.a = z;
            this.b = j;
        }
    }

    public SizeLimitActivity() {
        this.b = new HashMap();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        if (intent != null) {
            this.b.put(intent.getData(), new a(intent.getExtras().getBoolean(d.a), intent.getExtras().getLong(d.b)));
            setIntent(null);
            a();
        }
        if (this.a != null && !this.a.isShowing()) {
            this.a.show();
        }
    }

    private void a() {
        if (this.a == null) {
            if (this.b.isEmpty()) {
                finish();
                return;
            }
            this.c = (Uri) this.b.keySet().iterator().next();
            b();
        }
    }

    private void b() {
        a aVar = (a) this.b.get(this.c);
        String formatFileSize = Formatter.formatFileSize(this, aVar.b);
        String string = getString(ag.a((Context) this).d("button_queue_for_wifi"));
        boolean z = aVar.a;
        Builder builder = new Builder(this);
        if (z) {
            builder.setTitle(getString(ag.a((Context) this).d("wifi_required_title"))).setMessage(getString(ag.a((Context) this).d("wifi_required_body"), new Object[]{formatFileSize, string})).setPositiveButton(getString(ag.a((Context) this).d("button_queue_for_wifi")), this).setNegativeButton(getString(ag.a((Context) this).d("button_cancel_download")), this);
        } else {
            builder.setTitle(getString(ag.a((Context) this).d("wifi_recommended_title"))).setMessage(getString(ag.a((Context) this).d("wifi_recommended_body"), new Object[]{formatFileSize, string})).setPositiveButton(getString(ag.a((Context) this).d("button_start_now")), this).setNegativeButton(getString(ag.a((Context) this).d("button_queue_for_wifi")), this);
        }
        this.a = builder.setOnCancelListener(this).show();
    }

    public void onCancel(DialogInterface dialogInterface) {
        c();
    }

    private void c() {
        this.b.remove(this.c);
        this.a = null;
        this.c = null;
        a();
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Boolean valueOf = Boolean.valueOf(((a) this.b.get(this.c)).a);
        if (valueOf == null) {
            c();
            return;
        }
        try {
            if (valueOf.booleanValue() && i == -2) {
                getContentResolver().delete(this.c, null, null);
                c();
            }
            if (!valueOf.booleanValue() && i == -1) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(Impl.COLUMN_BYPASS_RECOMMENDED_SIZE_LIMIT, Boolean.valueOf(true));
                getContentResolver().update(this.c, contentValues, null, null);
            }
            c();
        } catch (Throwable e) {
            e.printStackTrace();
            am.a(e);
        }
    }
}
