package com.taobao.accs.antibrush;

import com.taobao.accs.utl.ALog;

// compiled from: Taobao
class a implements Runnable {
    final /* synthetic */ AntiBrush a;

    a(AntiBrush antiBrush) {
        this.a = antiBrush;
    }

    public void run() {
        ALog.e("AntiBrush", "anti bursh timeout", new Object[0]);
        AntiBrush.onResult(AntiBrush.access$000(this.a), false);
    }
}
