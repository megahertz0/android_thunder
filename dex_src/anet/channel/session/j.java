package anet.channel.session;

// compiled from: Taobao
class j implements Runnable {
    final /* synthetic */ i a;

    j(i iVar) {
        this.a = iVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        throw new UnsupportedOperationException("Method not decompiled: anet.channel.session.j.run():void");
        /*
        this = this;
        r3 = 1;
        r0 = r7.a;
        r0 = r0.mHasUnrevPing;
        if (r0 == 0) goto L_0x005c;
    L_0x0007:
        r0 = anet.channel.util.ALog.isPrintLog(r3);
        if (r0 == 0) goto L_0x0036;
    L_0x000d:
        r0 = anet.channel.session.i.a;
        r1 = "getRecvTimeOutRunnable";
        r2 = r7.a;
        r2 = r2.mSeq;
        r3 = new java.lang.Object[r3];
        r4 = 0;
        r5 = new java.lang.StringBuilder;
        r6 = "send msg time out! pingUnRcv:";
        r5.<init>(r6);
        r6 = r7.a;
        r6 = r6.mHasUnrevPing;
        r5 = r5.append(r6);
        r5 = r5.toString();
        r3[r4] = r5;
        anet.channel.util.ALog.e(r0, r1, r2, r3);
    L_0x0036:
        r0 = r7.a;	 Catch:{ Exception -> 0x0066 }
        r0 = r0.mStatus;	 Catch:{ Exception -> 0x0066 }
        r1 = anet.channel.Session.Status.CONNECTED;	 Catch:{ Exception -> 0x0066 }
        if (r0 != r1) goto L_0x005d;
    L_0x0040:
        r0 = r7.a;	 Catch:{ Exception -> 0x0066 }
        r1 = anet.channel.Session.Status.AUTH_FAIL;	 Catch:{ Exception -> 0x0066 }
        r2 = 0;
        r0.notifyStatus(r1, r2);	 Catch:{ Exception -> 0x0066 }
    L_0x0048:
        r0 = r7.a;	 Catch:{ Exception -> 0x0066 }
        r0 = r0.mSessionStat;	 Catch:{ Exception -> 0x0066 }
        if (r0 == 0) goto L_0x0057;
    L_0x004e:
        r0 = r7.a;	 Catch:{ Exception -> 0x0066 }
        r0 = r0.mSessionStat;	 Catch:{ Exception -> 0x0066 }
        r1 = "ping time out";
        r0.closeReason = r1;	 Catch:{ Exception -> 0x0066 }
    L_0x0057:
        r0 = r7.a;	 Catch:{ Exception -> 0x0066 }
        r0.close();	 Catch:{ Exception -> 0x0066 }
    L_0x005c:
        return;
    L_0x005d:
        r0 = r7.a;	 Catch:{ Exception -> 0x0066 }
        r1 = anet.channel.entity.EventType.DATA_TIMEOUT;	 Catch:{ Exception -> 0x0066 }
        r2 = 0;
        r0.handleCallbacks(r1, r2);	 Catch:{ Exception -> 0x0066 }
        goto L_0x0048;
    L_0x0066:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x005c;
        */
    }
}
