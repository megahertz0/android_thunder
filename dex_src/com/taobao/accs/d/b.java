package com.taobao.accs.d;

import android.content.SharedPreferences.Editor;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;

// compiled from: Taobao
class b extends Thread {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ a c;

    b(a aVar, String str, String str2) {
        this.c = aVar;
        this.a = str;
        this.b = str2;
    }

    public void run() {
        a aVar = new a(this.a, this.b, null, a.class.getClassLoader());
        ALog.d("ACCSClassLoader", "dexOpt done", new Object[0]);
        a.a(this.c, false);
        Editor edit = a.a(this.c).getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
        edit.putBoolean(Constants.SP_KEY_UPDATE_DONE, true);
        edit.apply();
    }
}
