package com.xunlei.downloadprovider.web.sniff;

import com.xunlei.downloadprovider.util.sniff.SniffConfigure;
import com.xunlei.downloadprovider.util.sniff.f;
import com.xunlei.downloadprovider.web.sniff.widget.SuffixListView.a;
import com.xunlei.downloadprovider.web.x;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.List;
import org.android.spdy.SpdyProtocol;

class SnifferResultsFragment$a implements a {
    final /* synthetic */ SnifferResultsFragment a;

    SnifferResultsFragment$a(SnifferResultsFragment snifferResultsFragment) {
        this.a = snifferResultsFragment;
    }

    public final void a(x xVar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(xVar.a);
        stringBuilder.append(" ");
        stringBuilder.append(xVar.b);
        String toString = stringBuilder.toString();
        if (SnifferResultsFragment.b(this.a) != null) {
            String str;
            SnifferResultsFragment.a(this.a, SpdyProtocol.PUBKEY_PSEQ_OPEN);
            SnifferResultsFragment.c(this.a);
            List list = null;
            if (SniffConfigure.a().b() != null) {
                list = SniffConfigure.a().b().a;
            }
            String str2 = BuildConfig.VERSION_NAME;
            if (list == null || !list.contains(f.e(toString))) {
                str = str2;
                str2 = toString;
            } else {
                str = f.e(toString);
                str2 = f.d(toString);
            }
            new StringBuilder("onSuffixClick keyword: ").append(str2).append(" suffix: ").append(str);
            SnifferResultsFragment.b(this.a).a(str2, str);
        }
    }
}
