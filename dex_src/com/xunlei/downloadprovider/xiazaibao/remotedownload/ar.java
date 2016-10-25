package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.downloadprovider.commonview.dialog.x;
import com.xunlei.downloadprovider.download.center.widget.t;
import com.xunlei.xiazaibao.shoulei.XZBShouleiUtil;
import java.util.ArrayList;
import java.util.List;

// compiled from: XZBTaskControl.java
final class ar implements OnClickListener {
    final /* synthetic */ am$a a;
    final /* synthetic */ String b;
    final /* synthetic */ List c;
    final /* synthetic */ t d;
    final /* synthetic */ am e;

    ar(am amVar, am$a com_xunlei_downloadprovider_xiazaibao_remotedownload_am_a, String str, List list, t tVar) {
        this.e = amVar;
        this.a = com_xunlei_downloadprovider_xiazaibao_remotedownload_am_a;
        this.b = str;
        this.c = list;
        this.d = tVar;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (this.a != null) {
            this.a.a();
        }
        am.a(this.e, new x(am.a(this.e)));
        am.b(this.e).a("\u5220\u9664\u4e2d...");
        am.b(this.e).show();
        XZBShouleiUtil.getInstance().deleteTask(am.c(this.e), new ArrayList(this.c), this.d.a.isChecked(), this.b, new as(this));
        dialogInterface.dismiss();
    }
}
