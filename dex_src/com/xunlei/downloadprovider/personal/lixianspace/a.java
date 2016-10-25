package com.xunlei.downloadprovider.personal.lixianspace;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import org.android.spdy.SpdyProtocol;

// compiled from: LixianSpaceFragment.java
final class a implements OnClickListener {
    final /* synthetic */ LixianSpaceFragment a;

    a(LixianSpaceFragment lixianSpaceFragment) {
        this.a = lixianSpaceFragment;
    }

    public final void onClick(View view) {
        int i = 0;
        switch (view.getId()) {
            case R.id.batch_delete:
                LixianSpaceFragment.c(this.a);
            case R.id.selection:
                LixianSpaceFragment.a(this.a, !LixianSpaceFragment.h(this.a));
                Drawable drawable = this.a.getResources().getDrawable(LixianSpaceFragment.h(this.a) ? R.drawable.popup_menu_ic_uparrow : R.drawable.popup_menu_ic_downarrow);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                LixianSpaceFragment.i(this.a).setCompoundDrawables(drawable, null, null, null);
                View j = LixianSpaceFragment.j(this.a);
                if (!LixianSpaceFragment.h(this.a)) {
                    i = 8;
                }
                j.setVisibility(i);
            case R.id.editbar_left:
                LixianSpaceFragment.f(this.a);
            case R.id.editbar_right:
                LixianSpaceFragment.e(this.a);
            case com.xunlei.downloadprovidershare.R.id.titlebar_left:
                LixianSpaceFragment.a(this.a).finish();
            case com.xunlei.downloadprovidershare.R.id.titlebar_right_iv:
                if (!LixianSpaceFragment.b(this.a)) {
                    LixianSpaceFragment.c(this.a);
                }
            case R.id.btn_go_to_load_or_pay:
                LoginHelper.a();
                if (LoginHelper.c()) {
                    LixianSpaceFragment.g(this.a);
                } else {
                    LoginHelper.a().a(this.a.getActivity(), new b(this), SpdyProtocol.PUBKEY_SEQ_ADASH, this.a.getResouceString(R.string.cloud_list_tab_lixian));
                }
            case R.id.cloud_list_bottom_delete_bar:
                LixianSpaceFragment.d(this.a);
            default:
                break;
        }
    }
}
