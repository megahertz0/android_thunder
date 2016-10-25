package com.xunlei.downloadprovider.i.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import java.io.File;

// compiled from: Update.java
class c$a implements OnClickListener {
    final /* synthetic */ c a;

    c$a(c cVar) {
        this.a = cVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(new File(c.j(this.a), c.k(this.a))), "application/vnd.android.package-archive");
        c.e(this.a).startActivity(intent);
        if (c.a(this.a) != null) {
            StatReporter.reportUpdateXunlei(c.a(this.a).b, c.a(this.a).a);
        }
    }
}
