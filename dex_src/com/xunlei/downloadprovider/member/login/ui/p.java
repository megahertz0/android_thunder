package com.xunlei.downloadprovider.member.login.ui;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import com.xunlei.downloadprovider.member.login.LoginHelper.a;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.ByteArrayOutputStream;

// compiled from: LoginActivity.java
final class p implements a {
    final /* synthetic */ LoginActivity a;

    p(LoginActivity loginActivity) {
        this.a = loginActivity;
    }

    public final void a(int i, String str, byte[] bArr) {
        this.a.I = false;
        if (i == 16781312) {
            this.a.a(2131232938);
        }
        this.a.p.setVisibility(XZBDevice.Wait);
        if (i != 0 || str == null || bArr == null) {
            LoginActivity.D(this.a);
            return;
        }
        this.a.n.setText(com.umeng.a.d);
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        if (decodeByteArray == null) {
            this.a.D = null;
            LoginActivity.D(this.a);
            return;
        }
        this.a.D = str;
        decodeByteArray.compress(CompressFormat.JPEG, R.styleable.AppCompatTheme_buttonBarStyle, new ByteArrayOutputStream());
        this.a.o.setImageBitmap(decodeByteArray);
        this.a.F = true;
        this.a.a(false);
    }
}
