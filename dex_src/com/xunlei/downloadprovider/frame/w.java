package com.xunlei.downloadprovider.frame;

import com.xunlei.downloadprovider.member.login.LoginHelper.m;

// compiled from: MainTabActivity.java
final class w implements m {
    final /* synthetic */ MainTabActivity a;

    w(MainTabActivity mainTabActivity) {
        this.a = mainTabActivity;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a() {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.frame.w.a():void");
        /*
        this = this;
        r10 = 0;
        r12 = 0;
        r9 = 1;
        r8 = 0;
        r0 = r15.a;
        r0 = r0.c;
        r0 = r0 instanceof com.xunlei.downloadprovider.frame.user.UserCenterFragment;
        if (r0 == 0) goto L_0x000e;
    L_0x000d:
        return;
    L_0x000e:
        r0 = com.xunlei.downloadprovider.member.login.LoginHelper.a();
        r0 = r0.j;
        r0 = java.lang.String.valueOf(r0);
        r1 = r15.a;
        r1 = com.xunlei.downloadprovider.frame.user.a.b.a(r1);
        r0 = r1.e(r0);
        if (r0 != 0) goto L_0x000d;
    L_0x0024:
        r0 = com.xunlei.downloadprovider.homepage.a.a.d.b;
        if (r0 == 0) goto L_0x0038;
    L_0x0028:
        r0 = com.xunlei.downloadprovider.homepage.a.a.d.b;
        r1 = "6";
        r0 = r0.containsKey(r1);
        if (r0 == 0) goto L_0x0038;
    L_0x0033:
        r0 = r15.a;
        r0.b(r9);
    L_0x0038:
        r0 = r15.a;
        r0 = com.xunlei.downloadprovider.frame.user.a.b.a(r0);
        r1 = com.xunlei.downloadprovider.member.login.LoginHelper.a();
        r2 = r1.j;
        r13 = java.lang.String.valueOf(r2);
        r1 = android.text.TextUtils.isEmpty(r13);
        if (r1 != 0) goto L_0x000d;
    L_0x004e:
        r0 = r0.getWritableDatabase();	 Catch:{ SQLiteConstraintException -> 0x013b, SQLiteException -> 0x00f5, all -> 0x010f }
        r0.beginTransaction();	 Catch:{ SQLiteConstraintException -> 0x013e, SQLiteException -> 0x0131, all -> 0x0125 }
        r1 = "User_Vip_Continue_Tip_Table";
        r2 = 0;
        r3 = "userId = ?";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ SQLiteConstraintException -> 0x013e, SQLiteException -> 0x0131, all -> 0x0125 }
        r5 = 0;
        r4[r5] = r13;	 Catch:{ SQLiteConstraintException -> 0x013e, SQLiteException -> 0x0131, all -> 0x0125 }
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r1 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ SQLiteConstraintException -> 0x013e, SQLiteException -> 0x0131, all -> 0x0125 }
        if (r1 == 0) goto L_0x0143;
    L_0x006b:
        r2 = r1.moveToFirst();	 Catch:{ SQLiteConstraintException -> 0x00dd, SQLiteException -> 0x0137, all -> 0x012b }
        if (r2 == 0) goto L_0x0143;
    L_0x0071:
        r2 = 3;
        r10 = r1.getLong(r2);	 Catch:{ SQLiteConstraintException -> 0x00dd, SQLiteException -> 0x0137, all -> 0x012b }
        r2 = 2;
        r2 = r1.getLong(r2);	 Catch:{ SQLiteConstraintException -> 0x00dd, SQLiteException -> 0x0137, all -> 0x012b }
        r4 = r9;
    L_0x007c:
        r5 = new android.content.ContentValues;	 Catch:{ SQLiteConstraintException -> 0x00dd, SQLiteException -> 0x0137, all -> 0x012b }
        r5.<init>();	 Catch:{ SQLiteConstraintException -> 0x00dd, SQLiteException -> 0x0137, all -> 0x012b }
        r5.clear();	 Catch:{ SQLiteConstraintException -> 0x00dd, SQLiteException -> 0x0137, all -> 0x012b }
        r6 = "userId";
        r5.put(r6, r13);	 Catch:{ SQLiteConstraintException -> 0x00dd, SQLiteException -> 0x0137, all -> 0x012b }
        r6 = "viplastTipTime";
        r2 = java.lang.Long.valueOf(r2);	 Catch:{ SQLiteConstraintException -> 0x00dd, SQLiteException -> 0x0137, all -> 0x012b }
        r5.put(r6, r2);	 Catch:{ SQLiteConstraintException -> 0x00dd, SQLiteException -> 0x0137, all -> 0x012b }
        r2 = "tasklastTipTime";
        r3 = java.lang.Long.valueOf(r10);	 Catch:{ SQLiteConstraintException -> 0x00dd, SQLiteException -> 0x0137, all -> 0x012b }
        r5.put(r2, r3);	 Catch:{ SQLiteConstraintException -> 0x00dd, SQLiteException -> 0x0137, all -> 0x012b }
        r2 = "extTip1";
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ SQLiteConstraintException -> 0x00dd, SQLiteException -> 0x0137, all -> 0x012b }
        r3 = java.lang.Long.valueOf(r6);	 Catch:{ SQLiteConstraintException -> 0x00dd, SQLiteException -> 0x0137, all -> 0x012b }
        r5.put(r2, r3);	 Catch:{ SQLiteConstraintException -> 0x00dd, SQLiteException -> 0x0137, all -> 0x012b }
        if (r4 == 0) goto L_0x00d5;
    L_0x00ae:
        r2 = "User_Vip_Continue_Tip_Table";
        r3 = "userId = ?";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ SQLiteConstraintException -> 0x00dd, SQLiteException -> 0x0137, all -> 0x012b }
        r6 = 0;
        r4[r6] = r13;	 Catch:{ SQLiteConstraintException -> 0x00dd, SQLiteException -> 0x0137, all -> 0x012b }
        r0.update(r2, r5, r3, r4);	 Catch:{ SQLiteConstraintException -> 0x00dd, SQLiteException -> 0x0137, all -> 0x012b }
    L_0x00bd:
        r0.setTransactionSuccessful();	 Catch:{ SQLiteConstraintException -> 0x00dd, SQLiteException -> 0x0137, all -> 0x012b }
        if (r1 == 0) goto L_0x00c5;
    L_0x00c2:
        r1.close();
    L_0x00c5:
        if (r0 == 0) goto L_0x000d;
    L_0x00c7:
        r1 = r0.isOpen();
        if (r1 == 0) goto L_0x000d;
    L_0x00cd:
        r0.endTransaction();
        r0.close();
        goto L_0x000d;
    L_0x00d5:
        r2 = "User_Vip_Continue_Tip_Table";
        r3 = 0;
        r0.insert(r2, r3, r5);	 Catch:{ SQLiteConstraintException -> 0x00dd, SQLiteException -> 0x0137, all -> 0x012b }
        goto L_0x00bd;
    L_0x00dd:
        r2 = move-exception;
        r8 = r0;
        r0 = r1;
    L_0x00e0:
        if (r0 == 0) goto L_0x00e5;
    L_0x00e2:
        r0.close();
    L_0x00e5:
        if (r8 == 0) goto L_0x000d;
    L_0x00e7:
        r0 = r8.isOpen();
        if (r0 == 0) goto L_0x000d;
    L_0x00ed:
        r8.endTransaction();
        r8.close();
        goto L_0x000d;
    L_0x00f5:
        r0 = move-exception;
        r1 = r8;
    L_0x00f7:
        r0.printStackTrace();	 Catch:{ all -> 0x012f }
        if (r1 == 0) goto L_0x00ff;
    L_0x00fc:
        r1.close();
    L_0x00ff:
        if (r8 == 0) goto L_0x000d;
    L_0x0101:
        r0 = r8.isOpen();
        if (r0 == 0) goto L_0x000d;
    L_0x0107:
        r8.endTransaction();
        r8.close();
        goto L_0x000d;
    L_0x010f:
        r0 = move-exception;
        r1 = r8;
    L_0x0111:
        if (r1 == 0) goto L_0x0116;
    L_0x0113:
        r1.close();
    L_0x0116:
        if (r8 == 0) goto L_0x0124;
    L_0x0118:
        r1 = r8.isOpen();
        if (r1 == 0) goto L_0x0124;
    L_0x011e:
        r8.endTransaction();
        r8.close();
    L_0x0124:
        throw r0;
    L_0x0125:
        r1 = move-exception;
        r14 = r1;
        r1 = r8;
        r8 = r0;
        r0 = r14;
        goto L_0x0111;
    L_0x012b:
        r2 = move-exception;
        r8 = r0;
        r0 = r2;
        goto L_0x0111;
    L_0x012f:
        r0 = move-exception;
        goto L_0x0111;
    L_0x0131:
        r1 = move-exception;
        r14 = r1;
        r1 = r8;
        r8 = r0;
        r0 = r14;
        goto L_0x00f7;
    L_0x0137:
        r2 = move-exception;
        r8 = r0;
        r0 = r2;
        goto L_0x00f7;
    L_0x013b:
        r0 = move-exception;
        r0 = r8;
        goto L_0x00e0;
    L_0x013e:
        r1 = move-exception;
        r14 = r8;
        r8 = r0;
        r0 = r14;
        goto L_0x00e0;
    L_0x0143:
        r2 = r10;
        r4 = r12;
        goto L_0x007c;
        */
    }
}
