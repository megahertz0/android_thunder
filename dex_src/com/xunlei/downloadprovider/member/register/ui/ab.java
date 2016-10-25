package com.xunlei.downloadprovider.member.register.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.member.login.LoginHelper;

// compiled from: RegisterSuccessActivity.java
final class ab implements OnClickListener {
    final /* synthetic */ RegisterSuccessActivity a;

    ab(RegisterSuccessActivity registerSuccessActivity) {
        this.a = registerSuccessActivity;
    }

    public final void onClick(View view) {
        this.a.j = true;
        LoginHelper.a().b(this.a.e.getText().toString().trim());
    }
}
