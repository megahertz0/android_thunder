package com.qq.e.ads.appwall;

import android.content.Context;
import com.qq.e.comm.a;
import com.qq.e.comm.managers.GDTADManager;
import com.qq.e.comm.pi.AWI;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;

public class APPWall {
    private AWI a;

    public APPWall(Context context, String str, String str2) {
        if (StringUtil.isEmpty(str) || StringUtil.isEmpty(str2) || context == null) {
            GDTLogger.e(String.format("APPWall ADView Contructor paras error,appid=%s,posId=%s,context=%s", new Object[]{str, str2, context}));
        } else if (!a.a(context)) {
            GDTLogger.e("Required Activity/Service/Permission Not Declared in AndroidManifest.xml");
        } else if (GDTADManager.getInstance().initWith(context, str)) {
            try {
                this.a = GDTADManager.getInstance().getPM().getPOFactory().getAppWallView(context, str, str2);
            } catch (Throwable e) {
                GDTLogger.e("Exception while init APPWall plugin", e);
            }
        } else {
            GDTLogger.e("Fail to init ADManager");
        }
    }

    public void doShowAppWall() {
        if (this.a != null) {
            this.a.showAppWall();
        }
    }

    public void prepare() {
        if (this.a != null) {
            this.a.prepare();
        }
    }

    public void setScreenOrientation(int i) {
        if (this.a != null) {
            this.a.setScreenOrientation(i);
        }
    }
}
