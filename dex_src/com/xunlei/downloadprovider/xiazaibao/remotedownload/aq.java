package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

// compiled from: XZBTaskControl.java
final class aq implements OnClickListener {
    final /* synthetic */ am$a a;
    final /* synthetic */ String b;
    final /* synthetic */ am c;

    aq(am amVar, am$a com_xunlei_downloadprovider_xiazaibao_remotedownload_am_a, String str) {
        this.c = amVar;
        this.a = com_xunlei_downloadprovider_xiazaibao_remotedownload_am_a;
        this.b = str;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        if (this.a != null) {
            this.a.b();
        }
        dialogInterface.dismiss();
    }
}
