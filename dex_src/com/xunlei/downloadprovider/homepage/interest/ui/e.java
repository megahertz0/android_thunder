package com.xunlei.downloadprovider.homepage.interest.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.xllib.a.b;
import java.util.List;

// compiled from: InterestPickerActivity.java
final class e implements OnClickListener {
    final /* synthetic */ InterestPickerActivity a;

    e(InterestPickerActivity interestPickerActivity) {
        this.a = interestPickerActivity;
    }

    public final void onClick(View view) {
        if (b.a(this.a)) {
            InterestPickerActivity.a(this.a);
            List b = InterestPickerActivity.b(this.a);
            InterestPickerActivity.d(this.a).a(LoginHelper.a().j, b, new f(this, b));
            return;
        }
        XLToast.b(this.a, XLToastType.XLTOAST_TYPE_ALARM, this.a.getString(2131231758));
    }
}
