package com.xunlei.downloadprovider.frame.user;

import android.os.Message;
import com.xunlei.common.yunbo.XLYunboMassage;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.model.j;
import com.xunlei.downloadprovider.model.j.b;
import com.xunlei.xllib.R;

// compiled from: UserCenterFragment.java
final class az implements a {
    final /* synthetic */ UserCenterFragment a;

    az(UserCenterFragment userCenterFragment) {
        this.a = userCenterFragment;
    }

    public final void a(Message message) {
        switch (message.what) {
            case R.styleable.AppCompatTheme_buttonStyle:
                if (this.a.isAdded()) {
                    UserCenterFragment.c(this.a);
                }
            case R.styleable.AppCompatTheme_checkedTextViewStyle:
                if (this.a.isAdded()) {
                    UserCenterFragment.d(this.a).s();
                }
            case R.styleable.AppCompatTheme_ratingBarStyleSmall:
                if (this.a.isAdded()) {
                    UserCenterFragment.e(this.a);
                }
            case XLYunboMassage.MSG_TASKCREATED:
                UserCenterFragment.a(this.a, (b) message.obj);
                UserCenterFragment.a(this.a);
            case XLYunboMassage.MSG_TASKCANCELED:
                UserCenterFragment.a(this.a, (j.a) message.obj);
                UserCenterFragment.b(this.a);
            default:
                break;
        }
    }
}
