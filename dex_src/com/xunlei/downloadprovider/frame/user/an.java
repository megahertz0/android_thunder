package com.xunlei.downloadprovider.frame.user;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: PersonalSpaceActivity.java
final class an implements OnClickListener {
    final /* synthetic */ PersonalSpaceActivity a;

    an(PersonalSpaceActivity personalSpaceActivity) {
        this.a = personalSpaceActivity;
    }

    public final void onClick(View view) {
        this.a.m.dismiss();
        Intent intent = new Intent(this.a, ReportActivity.class);
        intent.putExtra("report_target", XZBDevice.DOWNLOAD_LIST_RECYCLE);
        intent.putExtra(SocializeConstants.TENCENT_UID, this.a.d);
        this.a.startActivity(intent);
    }
}
