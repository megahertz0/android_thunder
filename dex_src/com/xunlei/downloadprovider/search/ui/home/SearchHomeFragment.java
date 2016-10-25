package com.xunlei.downloadprovider.search.ui.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.download.report.DLCenterEntry;
import com.xunlei.downloadprovider.frame.BaseViewPagerFragment;
import com.xunlei.downloadprovider.frame.MainTabActivity;
import com.xunlei.downloadprovider.model.protocol.d.a.a;
import com.xunlei.downloadprovider.search.ui.widget.HomeTitleBar;
import com.xunlei.downloadprovider.util.v;
import org.android.spdy.SpdyProtocol;

public class SearchHomeFragment extends BaseViewPagerFragment implements a {
    private static Class<?>[] f;
    private static String[] g;
    private HomeTitleBar h;

    static {
        f = new Class[]{HotSearchFixFragment.class, SearchWebsiteFragment.class};
        g = new String[]{"\u70ed\u641c", "\u7f51\u7ad9"};
    }

    protected View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.search_home_fragment, viewGroup, false);
        a(inflate);
        return inflate;
    }

    protected final void a(View view) {
        super.a(view);
        this.h = (HomeTitleBar) view.findViewById(R.id.title_bar);
        this.h.setDlCenterEntry(DLCenterEntry.search);
        this.h.setOnTitleClickListener(new h(this));
    }

    protected final String[] b() {
        return g;
    }

    protected final Class<?>[] c() {
        return f;
    }

    public void onResume() {
        super.onResume();
        this.h.a();
        Bundle extras = getExtras();
        if (extras != null) {
            if (extras.getInt("entry_from", 0) == 1000) {
                a(0);
                c(0);
            }
            extras.remove("entry_from");
        }
        if (isAdded()) {
            v.a().a(System.currentTimeMillis(), "search");
            ((MainTabActivity) this.mActivity).a("search", SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
    }

    public void onPause() {
        this.h.b();
        super.onPause();
    }

    public final void a(String str, String str2) {
        if (!TextUtils.isEmpty(str2) && this.h != null) {
            this.h.setHint(str2);
        }
    }

    protected final void a(int i, View view) {
    }
}
