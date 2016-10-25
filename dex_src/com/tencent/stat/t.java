package com.tencent.stat;

import android.database.Cursor;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.json.JSONObject;

class t implements Runnable {
    final /* synthetic */ n a;

    t(n nVar) {
        this.a = nVar;
    }

    public void run() {
        try {
            Cursor query = n.b(this.a).getReadableDatabase().query("config", null, null, null, null, null, null);
            while (query.moveToNext()) {
                try {
                    int i = query.getInt(0);
                    String string = query.getString(1);
                    String string2 = query.getString(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                    int i2 = query.getInt(XZBDevice.DOWNLOAD_LIST_FAILED);
                    b bVar = new b(i);
                    bVar.a = i;
                    bVar.b = new JSONObject(string);
                    bVar.c = string2;
                    bVar.d = i2;
                    StatConfig.a(bVar);
                } catch (Throwable th) {
                    th = th;
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Throwable th2) {
            th = th2;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
    }
}
