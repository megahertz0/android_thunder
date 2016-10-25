package com.xunlei.downloadprovider.web.sniff;

import android.text.SpannableString;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.util.sniff.SniffConfigure;
import com.xunlei.downloadprovider.web.sniff.r.b;
import com.xunlei.downloadprovider.web.x;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.b.d;
import java.util.ArrayList;
import java.util.List;

private abstract class SnifferResultsFragment$c {
    final /* synthetic */ SnifferResultsFragment a;

    abstract void a();

    private SnifferResultsFragment$c(SnifferResultsFragment snifferResultsFragment) {
        this.a = snifferResultsFragment;
    }

    protected final void b() {
        if (SnifferResultsFragment.u(this.a) != null && SnifferResultsFragment.u(this.a).getVisibility() == 0) {
            int childCount = SnifferResultsFragment.u(this.a).getChildCount();
            for (int i = 0; i < childCount; i++) {
                SnifferResultsFragment.u(this.a).getChildAt(i).clearAnimation();
            }
        }
    }

    protected final SpannableString a(int i) {
        return a(i, false, null, null);
    }

    protected final SpannableString a(int i, boolean z, String str, String str2) {
        if (i == 0 || i <= SnifferResultsFragment.B(this.a)) {
            SnifferResultsFragment.x(this.a).setNumberText(0);
            SpannableString spannableString = new SpannableString(this.a.getResources().getString(R.string.sniff_result_empty_prompt));
            SnifferResultsFragment.g(this.a);
            return spannableString;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.a.getActivity().getResources().getString(R.string.sniff_result_prefix));
        stringBuilder.append(i - SnifferResultsFragment.B(this.a));
        if (z) {
            if (!(str == null || str.trim().equals(BuildConfig.VERSION_NAME))) {
                if (str2 == null || str2.trim().equals("\u8fc5\u96f7\u4e0b\u8f7d")) {
                    stringBuilder.append(new StringBuilder("\u4e2a \"").append(str).append("\" ").toString());
                } else {
                    stringBuilder.append(new StringBuilder("\u4e2a \"").append(str).append(" ").append(str2).append("\" ").toString());
                }
            }
            stringBuilder.append(this.a.getActivity().getResources().getString(R.string.sniff_result_page_suffix));
        } else {
            stringBuilder.append(this.a.getActivity().getResources().getString(R.string.sniff_result_suffix));
        }
        SnifferResultsFragment.x(this.a).setNumberText(i - SnifferResultsFragment.B(this.a));
        spannableString = new SpannableString(stringBuilder.toString());
        SnifferResultsFragment.g(this.a);
        return spannableString;
    }

    protected static ArrayList<x> a(String str, List<String> list, String str2) {
        if (d.a(list)) {
            return null;
        }
        if (str2 == null) {
            str2 = BuildConfig.VERSION_NAME;
        }
        ArrayList<x> arrayList = new ArrayList();
        StringBuilder stringBuilder = new StringBuilder();
        for (String str3 : list) {
            if (!str3.trim().equals(str2.trim())) {
                x xVar = new x();
                xVar.a = str;
                xVar.b = str3;
                stringBuilder.append(str);
                stringBuilder.append(" ");
                stringBuilder.append(str3);
                arrayList.add(xVar);
            }
        }
        return arrayList;
    }

    protected static b a(String str, String str2) {
        List list = null;
        if (SniffConfigure.a().b() != null) {
            list = SniffConfigure.a().b().a;
        }
        return new b(a(str, list, str2));
    }
}
