package com.xunlei.downloadprovider.member.login.ui;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.member.login.LoginHelper.a;
import com.xunlei.downloadprovider.model.protocol.report.b;
import java.io.ByteArrayOutputStream;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: LoginActivity.java
final class j implements a {
    final /* synthetic */ i a;

    j(i iVar) {
        this.a = iVar;
    }

    public final void a(int i, String str, byte[] bArr) {
        if (i == 16781312) {
            LoginActivity.a(this.a.a, R.string.user_account_getverifycode_error);
        }
        if (i != 0 || str == null || bArr == null) {
            LoginActivity.t(this.a.a).setImageResource(R.drawable.bg_validcode_fail);
            LoginActivity.u(this.a.a);
        } else {
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
            if (decodeByteArray == null) {
                LoginActivity.b(this.a.a, null);
                LoginActivity.t(this.a.a).setImageResource(R.drawable.bg_validcode_fail);
                LoginActivity.u(this.a.a);
                return;
            }
            LoginActivity.b(this.a.a, str);
            decodeByteArray.compress(CompressFormat.JPEG, com.xunlei.xllib.R.styleable.AppCompatTheme_buttonBarStyle, new ByteArrayOutputStream());
            LoginActivity.t(this.a.a).setImageBitmap(decodeByteArray);
            LoginActivity.a(this.a.a, LoginActivity.q(this.a.a).getVisibility() != 8);
            LoginActivity.q(this.a.a).setVisibility(0);
            new StringBuilder("HubbleProxy---reportUserLoginVerify---").append(Thread.currentThread().getId());
            b.a("android_user_login", "user_login_verify", null);
            LoginActivity.v(this.a.a);
            LoginActivity.w(this.a.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(LoginActivity.x(this.a.a).getLayoutParams());
            layoutParams.setMargins(0, g.a(this.a.a, 18.0f), 0, 0);
            LoginActivity.x(this.a.a).setLayoutParams(layoutParams);
            this.a.a.a(false, false);
            LoginActivity.b(this.a.a, LoginActivity.y(this.a.a));
            LoginActivity.b(this.a.a, SimpleLog.LOG_LEVEL_FATAL);
        }
        LoginActivity.z(this.a.a).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }
}
