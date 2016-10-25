package com.qq.e.comm.managers;

import android.content.Context;
import android.os.Build.VERSION;
import com.qq.e.comm.constants.CustomPkgConstants;
import com.qq.e.comm.managers.plugin.PM;
import com.qq.e.comm.managers.setting.SM;
import com.qq.e.comm.managers.status.APPStatus;
import com.qq.e.comm.managers.status.DeviceStatus;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

public class GDTADManager {
    public static final ExecutorService INIT_EXECUTOR;
    private volatile Boolean a;
    private volatile Context b;
    private volatile SM c;
    private volatile PM d;
    private volatile APPStatus e;
    private volatile DeviceStatus f;

    static final class a {
        private static GDTADManager a;

        static {
            a = new GDTADManager();
        }
    }

    static {
        INIT_EXECUTOR = Executors.newSingleThreadExecutor();
    }

    private GDTADManager() {
        this.a = Boolean.valueOf(false);
    }

    public static GDTADManager getInstance() {
        return a.a;
    }

    public JSONObject buildS2SSBaseInfo() throws JSONException {
        if (!isInitialized()) {
            return null;
        }
        JSONObject a = com.qq.e.comm.a.a(this.c);
        a.put("app", com.qq.e.comm.a.a(this.e));
        a.put("c", com.qq.e.comm.a.a(this.f));
        a.put("sdk", com.qq.e.comm.a.a(this.d));
        return a;
    }

    public String getADActivityClazz() {
        return CustomPkgConstants.getADActivityName();
    }

    public Context getAppContext() {
        return this.b;
    }

    public APPStatus getAppStatus() {
        return this.e;
    }

    public DeviceStatus getDeviceStatus() {
        return this.f;
    }

    public String getDownLoadClazz() {
        return CustomPkgConstants.getDownLoadServiceName();
    }

    public PM getPM() {
        return this.d;
    }

    public SM getSM() {
        return this.c;
    }

    public synchronized boolean initWith(Context context, String str) {
        boolean z;
        if (this.a.booleanValue()) {
            z = true;
        } else {
            if (context != null) {
                if (!StringUtil.isEmpty(str)) {
                    try {
                        long nanoTime = System.nanoTime();
                        this.b = context.getApplicationContext();
                        this.c = new SM(this.b);
                        this.d = new PM(this.b);
                        this.e = new APPStatus(str, this.b);
                        this.f = new DeviceStatus(this.b);
                        if (VERSION.SDK_INT > 7) {
                            com.qq.e.comm.services.a.a().a(this.b, this.c, this.d, this.f, this.e, nanoTime);
                        }
                        this.a = Boolean.valueOf(true);
                        z = true;
                    } catch (Throwable th) {
                        GDTLogger.report("ADManager init error", th);
                        z = false;
                    }
                }
            }
            GDTLogger.e("Context And APPID should Never Be NULL while init GDTADManager");
            z = false;
        }
        return z;
    }

    public boolean isInitialized() {
        return this.a == null ? false : this.a.booleanValue();
    }
}
