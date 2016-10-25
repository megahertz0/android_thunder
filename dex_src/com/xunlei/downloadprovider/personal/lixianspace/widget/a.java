package com.xunlei.downloadprovider.personal.lixianspace.widget;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import org.android.spdy.SpdyProtocol;

// compiled from: LixianSpaceListWidget.java
final class a implements OnClickListener {
    final /* synthetic */ LixianSpaceListWidget a;

    a(LixianSpaceListWidget lixianSpaceListWidget) {
        this.a = lixianSpaceListWidget;
    }

    public final void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_contact_need_login_btn:
                LoginHelper.a();
                if (!LoginHelper.c()) {
                    LoginHelper.a().a(this.a.getContext(), new b(this), SpdyProtocol.PUBKEY_SEQ_ADASH, this.a.getContext().getResources().getString(R.string.cloud_list_tab_lixian));
                } else if (!LixianSpaceListWidget.a(this.a)) {
                    LixianSpaceListWidget.b(this.a);
                }
            default:
                break;
        }
    }
}
