package com.xunlei.tdlive;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.tdlive.modal.e;
import com.xunlei.tdlive.user.f;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: MainActivity.java
class dp implements OnClickListener {
    final /* synthetic */ MainActivity a;

    dp(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    public void onClick(View view) {
        int intValue;
        Object tag = view.getTag();
        if (tag instanceof Integer) {
            intValue = ((Integer) tag).intValue();
        } else {
            intValue = 0;
        }
        if (intValue == R.drawable.xllive_tab_home_selector) {
            MainActivity.a(this.a, SimpleLog.LOG_LEVEL_DEBUG);
        } else if (intValue == R.drawable.xllive_tab_user_selector) {
            MainActivity.a(this.a, 1);
        }
        if (intValue == R.drawable.xllive_tab_user_selector) {
            this.a.putInt(new StringBuilder("LAST_USER_MAIL_NUMBER_").append(f.a(this.a).k()).toString(), e.a);
            View findViewById = view.findViewById(16908296);
            if (findViewById != null) {
                findViewById.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            }
        }
    }
}
