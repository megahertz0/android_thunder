package com.xunlei.downloadprovider.web.sniff.widget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.util.sniff.f;
import com.xunlei.downloadprovider.web.sniff.SniffSuffixTypeDataManager;
import com.xunlei.downloadprovider.web.sniff.SniffSuffixTypeDataManager$OperSniffSuffixSet;
import com.xunlei.downloadprovider.web.sniff.b;

// compiled from: SniffSuffixSettingItemView.java
final class g implements OnItemClickListener {
    final /* synthetic */ SniffSuffixSettingItemView a;

    g(SniffSuffixSettingItemView sniffSuffixSettingItemView) {
        this.a = sniffSuffixSettingItemView;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        b bVar = (b) adapterView.getItemAtPosition(i);
        SniffSuffixSettingItemView$b sniffSuffixSettingItemView$b = (SniffSuffixSettingItemView$b) view.getTag();
        if (bVar != null) {
            SniffSuffixTypeDataManager a = SniffSuffixTypeDataManager.a();
            String str = bVar.a;
            if (a.n == null) {
                a.n = f.b();
            }
            if (a.n.contains(str)) {
                int i2;
                a = SniffSuffixTypeDataManager.a();
                if (a.n == null) {
                    a.n = f.b();
                }
                if (a.n.size() == 2 && a.n.contains("torrent")) {
                    i2 = 1;
                } else {
                    i2 = a.n.size();
                }
                if (i2 == 1) {
                    XLToast.b(this.a.getContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u81f3\u5c11\u9009\u62e91\u9879");
                    return;
                }
                sniffSuffixSettingItemView$b.a.setImageResource(R.drawable.sniff_suffix_select_disable);
                sniffSuffixSettingItemView$b.b.setTextColor(-6513508);
                SniffSuffixTypeDataManager.a().a(bVar.a, SniffSuffixTypeDataManager$OperSniffSuffixSet.delete);
                return;
            }
            sniffSuffixSettingItemView$b.a.setImageResource(R.drawable.sniff_suffix_select_enable);
            sniffSuffixSettingItemView$b.b.setTextColor(-15559434);
            SniffSuffixTypeDataManager.a().a(bVar.a, SniffSuffixTypeDataManager$OperSniffSuffixSet.add);
        }
    }
}
