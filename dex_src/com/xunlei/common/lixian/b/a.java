package com.xunlei.common.lixian.b;

import android.util.Base64;
import com.xunlei.common.lixian.XLLX_INITDATA;
import com.xunlei.common.lixian.XLLixianListener;
import com.xunlei.common.lixian.XLLixianRequestBase;
import com.xunlei.common.lixian.XLLixianTask;
import com.xunlei.common.lixian.a.d;
import com.xunlei.common.lixian.a.e;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class a extends XLLixianRequestBase {
    private long a;
    private String b;
    private String c;
    private String d;
    private int[] e;
    private boolean f;

    public a() {
        this.a = 0;
        this.b = BuildConfig.VERSION_NAME;
        this.c = BuildConfig.VERSION_NAME;
        this.d = BuildConfig.VERSION_NAME;
        this.e = new int[0];
        this.f = false;
    }

    private String a() {
        try {
            File file = new File(this.d);
            if (!file.exists()) {
                return BuildConfig.VERSION_NAME;
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            for (int read = fileInputStream.read(bArr, 0, 1024); read > 0; read = fileInputStream.read(bArr, 0, 1024)) {
                byteArrayOutputStream.write(bArr, 0, read);
            }
            byteArrayOutputStream.flush();
            fileInputStream.close();
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return BuildConfig.VERSION_NAME;
        } catch (IOException e2) {
            e2.printStackTrace();
            return BuildConfig.VERSION_NAME;
        }
    }

    private void a(long j) {
        this.a = j;
        this.f = false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.String r5, java.lang.String r6, java.lang.String r7, int[] r8) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.common.lixian.b.a.a(java.lang.String, java.lang.String, java.lang.String, int[]):void");
        /*
        this = this;
        r1 = 0;
        r4.d = r5;
        r4.b = r7;
        r4.c = r6;
        if (r8 == 0) goto L_0x001b;
    L_0x0009:
        r0 = r8.length;
        r0 = new int[r0];
        r4.e = r0;
        r0 = r1;
    L_0x000f:
        r2 = r8.length;
        if (r0 >= r2) goto L_0x001b;
    L_0x0012:
        r2 = r4.e;
        r3 = r8[r0];
        r2[r0] = r3;
        r0 = r0 + 1;
        goto L_0x000f;
    L_0x001b:
        r0 = r4.b;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x0030;
    L_0x0023:
        r0 = r4.c;
        r0 = r0.isEmpty();
        if (r0 != 0) goto L_0x0030;
    L_0x002b:
        r0 = r4.e;
        r0 = r0.length;
        if (r0 != 0) goto L_0x0075;
    L_0x0030:
        r0 = new java.io.File;	 Catch:{ IOException -> 0x0071 }
        r2 = r4.d;	 Catch:{ IOException -> 0x0071 }
        r0.<init>(r2);	 Catch:{ IOException -> 0x0071 }
        r2 = r0.exists();	 Catch:{ IOException -> 0x0071 }
        if (r2 != 0) goto L_0x003e;
    L_0x003d:
        return;
    L_0x003e:
        r0 = com.xunlei.common.lixian.a.a.e.a(r0);	 Catch:{ IOException -> 0x0071 }
        r2 = r0.c();	 Catch:{ IOException -> 0x0071 }
        r4.b = r2;	 Catch:{ IOException -> 0x0071 }
        r2 = r4.c;	 Catch:{ IOException -> 0x0071 }
        r2 = r2.isEmpty();	 Catch:{ IOException -> 0x0071 }
        if (r2 == 0) goto L_0x0056;
    L_0x0050:
        r2 = r0.a();	 Catch:{ IOException -> 0x0071 }
        r4.c = r2;	 Catch:{ IOException -> 0x0071 }
    L_0x0056:
        r2 = r4.e;	 Catch:{ IOException -> 0x0071 }
        r2 = r2.length;	 Catch:{ IOException -> 0x0071 }
        if (r2 != 0) goto L_0x0075;
    L_0x005b:
        r0 = r0.b();	 Catch:{ IOException -> 0x0071 }
        r2 = r0.size();	 Catch:{ IOException -> 0x0071 }
        r0 = new int[r2];	 Catch:{ IOException -> 0x0071 }
        r4.e = r0;	 Catch:{ IOException -> 0x0071 }
        r0 = r1;
    L_0x0068:
        if (r0 >= r2) goto L_0x0075;
    L_0x006a:
        r3 = r4.e;	 Catch:{ IOException -> 0x0071 }
        r3[r0] = r0;	 Catch:{ IOException -> 0x0071 }
        r0 = r0 + 1;
        goto L_0x0068;
    L_0x0071:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x0075:
        r4.f = r1;
        goto L_0x003d;
        */
    }

    public final boolean execute() {
        com.xunlei.common.lixian.a.a aVar = new com.xunlei.common.lixian.a.a((short) 13);
        e eVar = new e();
        try {
            XLLX_INITDATA initData = super.getInitData();
            eVar.a(initData.userJumpKey);
            eVar.a(initData.userId);
            eVar.c(initData.userVipLevel);
            eVar.c(0);
            eVar.a(this.b);
            eVar.a(this.c, "GB18030");
            eVar.a(this.c, "GB18030");
            if (this.f) {
                eVar.a(a());
            } else {
                eVar.a(BuildConfig.VERSION_NAME);
            }
            eVar.a(this.e.length);
            for (int i = 0; i < this.e.length; i++) {
                eVar.a(this.e[i]);
            }
            eVar.a(0);
            eVar.a(this.a);
            eVar.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        aVar.a(eVar.a());
        d.a().a(aVar.c(), new b(this));
        return true;
    }

    public final boolean fireEvent(XLLixianListener xLLixianListener, Object... objArr) {
        return xLLixianListener.OnCreateLixianBtTask(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), ((Long) objArr[3]).longValue(), (String) objArr[4], (int[]) objArr[5], (XLLixianTask[]) objArr[6], objArr[7]);
    }
}
