package com.xunlei.downloadprovider.filemanager;

import com.xunlei.downloadprovider.filemanager.model.b;
import com.xunlei.downloadprovider.filemanager.model.i;
import com.xunlei.downloadprovider.filemanager.ui.FileManagerDirView.c;
import java.util.Collection;
import java.util.List;

// compiled from: FileManagerDirActivity.java
final class g implements c {
    final /* synthetic */ FileManagerDirActivity a;

    g(FileManagerDirActivity fileManagerDirActivity) {
        this.a = fileManagerDirActivity;
    }

    public final void a(int i, String str, List<i> list) {
        Object a = this.a.j.a(str);
        if (i != 1) {
            this.a.o.setText(new StringBuilder("\u5df2\u9009\u62e9\u4e86").append(b.a((Collection) list)).append("\u9879").toString());
            if (this.a.j.getSelectFiles().size() > 0) {
                this.a.u.setEnabled(true);
            } else {
                this.a.u.setEnabled(false);
            }
        } else {
            b.b((Collection) list);
            String[] split = a.split("/");
            this.a.o.setText(split[split.length - 1]);
        }
        this.a.k.setText(a);
    }
}
