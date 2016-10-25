package com.xunlei.downloadprovider.vod;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.umeng.a;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovidercommon.a.c;
import com.xunlei.downloadprovidercommon.a.d;

// compiled from: VodPlayerActivity.java
final class q extends ClickableSpan {
    final /* synthetic */ VodPlayerActivity a;

    q(VodPlayerActivity vodPlayerActivity) {
        this.a = vodPlayerActivity;
    }

    public final void onClick(View view) {
        this.a.mClickableSpanClicked = true;
        view.postInvalidate();
        String str = a.d;
        String str2 = a.d;
        LoginHelper.a();
        if (LoginHelper.c()) {
            str = LoginHelper.a().j;
            str2 = LoginHelper.a().h;
        }
        c a = com.xunlei.downloadprovidercommon.a.a.a("android_player", "bxbb_buffer_qp_click");
        a.a("product_type", str2);
        a.a("userid", str);
        d.a(a);
        this.a.handleSpeedUpEvent(this.a.mTaskInfo.mTaskId, "v_an_shoulei_hytq_bxbb_buffer");
    }

    public final void updateDrawState(TextPaint textPaint) {
        if (this.a.mClickableSpanClicked) {
            textPaint.setColor(Color.parseColor("#2985cc"));
        } else {
            textPaint.setColor(this.a.getResources().getColor(2131689507));
        }
        textPaint.setUnderlineText(false);
    }
}
