package com.xunlei.downloadprovider.frame.user.account.ui;

import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.xllib.a.b;

// compiled from: UserAccountNicknameActivity.java
final class j implements OnClickListener {
    final /* synthetic */ UserAccountNicknameActivity a;

    j(UserAccountNicknameActivity userAccountNicknameActivity) {
        this.a = userAccountNicknameActivity;
    }

    public final void onClick(View view) {
        if (b.a(this.a.getBaseContext())) {
            this.a.f = true;
            Editable text = this.a.b.getText();
            new StringBuilder("newNickname==>").append(text);
            this.a.e.b(text.toString());
            return;
        }
        XLToast.b(this.a.getApplicationContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u65e0\u7f51\u7edc\u8fde\u63a5");
    }
}
