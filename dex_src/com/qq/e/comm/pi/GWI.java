package com.qq.e.comm.pi;

import android.view.View;
import com.qq.e.ads.appwall.GridAPPWallListener;
import com.qq.e.ads.cfg.DownAPPConfirmPolicy;

public interface GWI {
    GridAPPWallListener getAdListener();

    void setAdListener(GridAPPWallListener gridAPPWallListener);

    void setDownAPPConfirmPolicy(DownAPPConfirmPolicy downAPPConfirmPolicy);

    void show();

    void showRelativeTo(int i, int i2);

    void showRelativeTo(View view);
}
