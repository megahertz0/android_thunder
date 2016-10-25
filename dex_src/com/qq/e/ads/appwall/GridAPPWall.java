package com.qq.e.ads.appwall;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.qq.e.comm.a;
import com.qq.e.comm.constants.ErrorCode.OtherError;
import com.qq.e.comm.managers.GDTADManager;
import com.qq.e.comm.pi.GWI;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.StringUtil;
import com.taobao.accs.ErrorCode;
import com.taobao.accs.common.Constants;

public final class GridAPPWall {
    private GWI a;

    public GridAPPWall(Activity activity, String str, String str2, GridAPPWallListener gridAPPWallListener) {
        if (StringUtil.isEmpty(str) || StringUtil.isEmpty(str2) || activity == null) {
            GDTLogger.e(String.format("GridAPPWall ADView Contructor paras error,appid=%s,posId=%s,context=%s", new Object[]{str, str2, activity}));
        } else if (a.a((Context) activity)) {
            try {
                if (GDTADManager.getInstance().initWith(activity.getApplicationContext(), str)) {
                    this.a = GDTADManager.getInstance().getPM().getPOFactory().createGridAppWallView(activity, str, str2);
                    if (this.a != null) {
                        this.a.setAdListener(gridAPPWallListener);
                        return;
                    }
                    GDTLogger.e("Fail to INIT GDT SDK");
                    a(gridAPPWallListener, ErrorCode.DM_APPKEY_INVALID);
                    return;
                }
                GDTLogger.e("Fail to Init GDT AD SDK,report logcat info filter by gdt_ad_mob");
                a(gridAPPWallListener, Constants.COMMAND_STOP_FOR_ELECTION);
            } catch (Throwable e) {
                GDTLogger.e("Fail to init new appwall plugin", e);
                a(gridAPPWallListener, ErrorCode.DM_DEVICEID_INVALID);
            } catch (Throwable e2) {
                GDTLogger.e("Unknown Exception", e2);
                a(gridAPPWallListener, OtherError.UNKNOWN_ERROR);
            }
        } else {
            GDTLogger.e("Required Activity/Service/Permission Not Declared in AndroidManifest.xml");
            a(gridAPPWallListener, OtherError.ANDROID_PERMMISON_ERROR);
        }
    }

    private static void a(GridAPPWallListener gridAPPWallListener, int i) {
        if (gridAPPWallListener != null) {
            gridAPPWallListener.onNoAD(i);
        }
    }

    public final void show() {
        if (this.a != null) {
            this.a.show();
        }
    }

    public final void showRelativeTo(int i, int i2) {
        if (this.a != null) {
            this.a.showRelativeTo(i, i2);
        }
    }

    public final void showRelativeTo(View view) {
        if (this.a != null) {
            this.a.showRelativeTo(view);
        }
    }
}
