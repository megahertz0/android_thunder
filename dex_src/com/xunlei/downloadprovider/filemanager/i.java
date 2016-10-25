package com.xunlei.downloadprovider.filemanager;

import android.content.Context;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.commonview.dialog.d;
import com.xunlei.downloadprovider.filemanager.model.SelectableItem;
import com.xunlei.downloadprovider.filemanager.model.b;
import com.xunlei.downloadprovider.filemanager.ui.f.a;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import java.util.Collection;

// compiled from: FileManagerDirActivity.java
final class i implements a {
    final /* synthetic */ FileManagerDirActivity a;

    i(FileManagerDirActivity fileManagerDirActivity) {
        this.a = fileManagerDirActivity;
    }

    public final void a() {
        if (this.a.x != -1) {
            this.a.j.onItemClick(this.a.j, null, this.a.x, 0);
            StatReporter.reportClick(XLErrorCode.ALI_AUTH_USER_CANCLE, "longClickOpenfile", this.a.d());
        }
    }

    public final void b() {
        if (this.a.x != -1 && this.a.x < this.a.j.getData().size()) {
            Collection data = this.a.j.getData();
            b.b(data);
            SelectableItem selectableItem = (com.xunlei.downloadprovider.filemanager.model.i) data.get(this.a.x);
            b.a(selectableItem);
            if (selectableItem.g()) {
                this.a.a(data);
            } else {
                Context context = this.a;
                if (b.a(data) == 0) {
                    XLToast.a(context, XLToastType.XLTOAST_TYPE_ALARM, "\u8bf7\u81f3\u5c11\u9009\u62e9\u4e00\u4e2a\u6587\u4ef6");
                } else {
                    d dVar = new d(context);
                    dVar.setTitle((CharSequence) "\u63d0\u793a");
                    dVar.a(String.format("\u786e\u5b9a\u5220\u9664%s\u4e2a\u6587\u4ef6\u5417?", new Object[]{String.valueOf(r2)}));
                    dVar.b(new a(context, data));
                    dVar.a(new b(context));
                    dVar.show();
                }
            }
            StatReporter.reportClick(XLErrorCode.ALI_AUTH_USER_CANCLE, "longClickDelete", this.a.d());
        }
    }

    public final void c() {
        if (this.a.x != -1 && this.a.x < this.a.j.getData().size()) {
            this.a.a((com.xunlei.downloadprovider.filemanager.model.i) this.a.j.getData().get(this.a.x), this.a.g);
            StatReporter.reportClick(XLErrorCode.ALI_AUTH_USER_CANCLE, "longClickRename", this.a.d());
        }
    }

    public final void d() {
        if (this.a.x != -1 && this.a.x < this.a.j.getData().size()) {
            this.a.a((com.xunlei.downloadprovider.filemanager.model.i) this.a.j.getData().get(this.a.x), EFileCategoryType.E_XLDIR_CATEGORY.ordinal());
            StatReporter.reportClick(XLErrorCode.ALI_AUTH_USER_CANCLE, "longClickDetail", this.a.d());
        }
    }
}
