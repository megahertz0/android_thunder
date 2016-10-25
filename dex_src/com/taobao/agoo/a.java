package com.taobao.agoo;

import android.content.Intent;

// compiled from: Taobao
class a extends Thread {
    final /* synthetic */ Intent a;
    final /* synthetic */ BaseNotifyClickActivity b;

    a(BaseNotifyClickActivity baseNotifyClickActivity, Intent intent) {
        this.b = baseNotifyClickActivity;
        this.a = intent;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.agoo.a.run():void");
        /*
        this = this;
        r6 = 0;
        r0 = r7.a;
        if (r0 == 0) goto L_0x00c7;
    L_0x0005:
        r0 = "com.taobao.taobao";
        r1 = r7.b;	 Catch:{ Exception -> 0x00d2 }
        r1 = r1.getPackageName();	 Catch:{ Exception -> 0x00d2 }
        r0 = r0.equals(r1);	 Catch:{ Exception -> 0x00d2 }
        if (r0 == 0) goto L_0x00c8;
    L_0x0014:
        r0 = r7.b;	 Catch:{ Exception -> 0x00d2 }
        r1 = r7.a;	 Catch:{ Exception -> 0x00d2 }
        r0 = r0.parseMsgFromNotifyListener(r1);	 Catch:{ Exception -> 0x00d2 }
    L_0x001c:
        r1 = com.taobao.accs.utl.ALog.Level.I;	 Catch:{ Exception -> 0x00d2 }
        r1 = com.taobao.accs.utl.ALog.isPrintLog(r1);	 Catch:{ Exception -> 0x00d2 }
        if (r1 == 0) goto L_0x003d;
    L_0x0024:
        r1 = "accs.BaseNotifyClickActivity";
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00d2 }
        r3 = "parse msg: ";
        r2.<init>(r3);	 Catch:{ Exception -> 0x00d2 }
        r2 = r2.append(r0);	 Catch:{ Exception -> 0x00d2 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x00d2 }
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x00d2 }
        com.taobao.accs.utl.ALog.i(r1, r2, r3);	 Catch:{ Exception -> 0x00d2 }
    L_0x003d:
        r1 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x00d2 }
        if (r1 != 0) goto L_0x00c7;
    L_0x0043:
        r1 = r7.b;	 Catch:{ Exception -> 0x00d2 }
        r1 = r1.msgSource;	 Catch:{ Exception -> 0x00d2 }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ Exception -> 0x00d2 }
        if (r1 != 0) goto L_0x00c7;
    L_0x004f:
        r1 = r7.b;	 Catch:{ Exception -> 0x00d2 }
        r1 = r1.notifyManager;	 Catch:{ Exception -> 0x00d2 }
        if (r1 != 0) goto L_0x0061;
    L_0x0057:
        r1 = r7.b;	 Catch:{ Exception -> 0x00d2 }
        r2 = new org.android.agoo.control.NotifManager;	 Catch:{ Exception -> 0x00d2 }
        r2.<init>();	 Catch:{ Exception -> 0x00d2 }
        r1.notifyManager = r2;	 Catch:{ Exception -> 0x00d2 }
    L_0x0061:
        r1 = r7.b;	 Catch:{ Exception -> 0x00d2 }
        r1 = r1.agooFactory;	 Catch:{ Exception -> 0x00d2 }
        if (r1 != 0) goto L_0x0089;
    L_0x0069:
        r1 = r7.b;	 Catch:{ Exception -> 0x00d2 }
        r2 = new org.android.agoo.control.AgooFactory;	 Catch:{ Exception -> 0x00d2 }
        r2.<init>();	 Catch:{ Exception -> 0x00d2 }
        r1.agooFactory = r2;	 Catch:{ Exception -> 0x00d2 }
        r1 = r7.b;	 Catch:{ Exception -> 0x00d2 }
        r1 = r1.agooFactory;	 Catch:{ Exception -> 0x00d2 }
        r2 = r7.b;	 Catch:{ Exception -> 0x00d2 }
        r2 = r2.getApplicationContext();	 Catch:{ Exception -> 0x00d2 }
        r3 = r7.b;	 Catch:{ Exception -> 0x00d2 }
        r3 = r3.notifyManager;	 Catch:{ Exception -> 0x00d2 }
        r4 = 0;
        r1.init(r2, r3, r4);	 Catch:{ Exception -> 0x00d2 }
    L_0x0089:
        r1 = r7.b;	 Catch:{ Exception -> 0x00d2 }
        r1 = r1.agooFactory;	 Catch:{ Exception -> 0x00d2 }
        r2 = "UTF-8";
        r2 = r0.getBytes(r2);	 Catch:{ Exception -> 0x00d2 }
        r3 = r7.b;	 Catch:{ Exception -> 0x00d2 }
        r3 = r3.msgSource;	 Catch:{ Exception -> 0x00d2 }
        r4 = 0;
        r5 = 0;
        r1 = r1.msgReceiverPreHandler(r2, r3, r4, r5);	 Catch:{ Exception -> 0x00d2 }
        r2 = new android.content.Intent;	 Catch:{ Exception -> 0x00d2 }
        r2.<init>();	 Catch:{ Exception -> 0x00d2 }
        r2.putExtras(r1);	 Catch:{ Exception -> 0x00d2 }
        r1 = r7.b;	 Catch:{ Exception -> 0x00d2 }
        r1.onMessage(r2);	 Catch:{ Exception -> 0x00d2 }
        r1 = r7.b;	 Catch:{ Exception -> 0x00d2 }
        r1 = r1.agooFactory;	 Catch:{ Exception -> 0x00d2 }
        r3 = "UTF-8";
        r0 = r0.getBytes(r3);	 Catch:{ Exception -> 0x00d2 }
        r3 = "2";
        r1.saveMsg(r0, r3);	 Catch:{ Exception -> 0x00d2 }
        r0 = r7.b;	 Catch:{ Exception -> 0x00d2 }
        r0.reportClickNotifyMsg(r2);	 Catch:{ Exception -> 0x00d2 }
    L_0x00c7:
        return;
    L_0x00c8:
        r0 = r7.b;	 Catch:{ Exception -> 0x00d2 }
        r1 = r7.a;	 Catch:{ Exception -> 0x00d2 }
        r0 = r0.parseMsgFromChannel(r1);	 Catch:{ Exception -> 0x00d2 }
        goto L_0x001c;
    L_0x00d2:
        r0 = move-exception;
        r1 = "accs.BaseNotifyClickActivity";
        r2 = new java.lang.StringBuilder;
        r3 = "buildMessage exception: ";
        r2.<init>(r3);
        r0 = r2.append(r0);
        r0 = r0.toString();
        r2 = new java.lang.Object[r6];
        com.taobao.accs.utl.ALog.e(r1, r0, r2);
        goto L_0x00c7;
        */
    }
}
