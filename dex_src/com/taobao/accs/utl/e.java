package com.taobao.accs.utl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import com.taobao.accs.client.GlobalClientInfo;
import com.taobao.accs.common.Constants;

// compiled from: Taobao
public class e {
    public static final int MAX_FAIL_TIMES = 3;

    public static void a() {
        try {
            if (c() > 0) {
                Editor edit = GlobalClientInfo.getContext().getSharedPreferences(Constants.SP_LOAD_SO_FILE_NAME, 0).edit();
                edit.clear();
                edit.apply();
                ALog.i("LoadSoFailUtil", "loadSoSuccess", "fail times", Integer.valueOf(r0));
            }
        } catch (Throwable th) {
            ALog.e("LoadSoFailUtil", "loadSoSuccess", th, new Object[0]);
        }
    }

    public static void b() {
        try {
            Context context = GlobalClientInfo.getContext();
            SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.SP_LOAD_SO_FILE_NAME, 0);
            int i = sharedPreferences.getInt(Constants.SP_KEY_LOAD_SO_TIMES, 0) + 1;
            if (i > 0) {
                Editor edit = sharedPreferences.edit();
                edit.putInt(Constants.SP_KEY_LOAD_SO_TIMES, i);
                edit.commit();
            }
            ALog.e("LoadSoFailUtil", "loadSoFail", "times", Integer.valueOf(i));
            if (VERSION.SDK_INT == 15) {
                UtilityImpl.killService(context);
            }
        } catch (Throwable th) {
            ALog.e("LoadSoFailUtil", "loadSoFail", th, new Object[0]);
        }
    }

    public static int c() {
        int i;
        try {
            i = GlobalClientInfo.getContext().getSharedPreferences(Constants.SP_LOAD_SO_FILE_NAME, 0).getInt(Constants.SP_KEY_LOAD_SO_TIMES, 0);
            try {
                ALog.i("LoadSoFailUtil", "getSoFailTimes", "times", Integer.valueOf(i));
            } catch (Throwable th) {
                Throwable th2 = th;
                ALog.e("LoadSoFailUtil", "getSoFailTimes", th2, new Object[0]);
                return i;
            }
        } catch (Throwable th3) {
            th2 = th3;
            i = 0;
            ALog.e("LoadSoFailUtil", "getSoFailTimes", th2, new Object[0]);
            return i;
        }
        return i;
    }
}
