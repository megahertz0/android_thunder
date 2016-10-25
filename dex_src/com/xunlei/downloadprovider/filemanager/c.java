package com.xunlei.downloadprovider.filemanager;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.util.List;

// compiled from: FileManagerBaseActivity.java
final class c implements OnClickListener {
    final /* synthetic */ List a;
    final /* synthetic */ FileManagerBaseActivity b;

    c(FileManagerBaseActivity fileManagerBaseActivity, List list) {
        this.b = fileManagerBaseActivity;
        this.a = list;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        FileManagerBaseActivity.a(this.b, this.a);
    }
}
