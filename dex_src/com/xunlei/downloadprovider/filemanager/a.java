package com.xunlei.downloadprovider.filemanager;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import java.util.Collection;

// compiled from: FileManagerBaseActivity.java
final class a implements OnClickListener {
    final /* synthetic */ Collection a;
    final /* synthetic */ FileManagerBaseActivity b;

    a(FileManagerBaseActivity fileManagerBaseActivity, Collection collection) {
        this.b = fileManagerBaseActivity;
        this.a = collection;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        FileManagerBaseActivity.a(this.b, this.a);
    }
}
