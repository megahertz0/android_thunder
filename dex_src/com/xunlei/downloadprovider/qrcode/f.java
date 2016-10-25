package com.xunlei.downloadprovider.qrcode;

import android.content.Intent;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.qrcode.a.e.a;
import com.xunlei.tdlive.R;
import com.xunlei.xllib.a.b;

// compiled from: CameraActivity.java
final class f implements a {
    final /* synthetic */ CameraActivity a;

    f(CameraActivity cameraActivity) {
        this.a = cameraActivity;
    }

    public final void a() {
        if (b.a(BrothersApplication.a().getApplicationContext())) {
            CameraActivity.i(this.a);
            StatReporter.reportBindByQrCodeClick(LoginHelper.a().j);
            if (CameraActivity.a(this.a) && CameraActivity.j(this.a)) {
                Intent intent = new Intent();
                intent.putExtra("bind_key", CameraActivity.k(this.a));
                this.a.setResult(R.styleable.Toolbar_contentInsetEnd, intent);
                this.a.finish();
                return;
            }
            return;
        }
        XLToast.a(this.a, XLToastType.XLTOAST_TYPE_ALARM, this.a.getString(2131232288));
    }
}
