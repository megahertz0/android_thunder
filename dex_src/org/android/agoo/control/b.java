package org.android.agoo.control;

import com.taobao.accs.base.TaoBaseService.ExtraInfo;

// compiled from: Taobao
class b implements Runnable {
    final /* synthetic */ byte[] a;
    final /* synthetic */ String b;
    final /* synthetic */ ExtraInfo c;
    final /* synthetic */ AgooFactory d;

    b(AgooFactory agooFactory, byte[] bArr, String str, ExtraInfo extraInfo) {
        this.d = agooFactory;
        this.a = bArr;
        this.b = str;
        this.c = extraInfo;
    }

    public void run() {
        this.d.msgReceiverPreHandler(this.a, this.b, this.c, true);
    }
}
