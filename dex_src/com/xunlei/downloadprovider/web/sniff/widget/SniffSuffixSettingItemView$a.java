package com.xunlei.downloadprovider.web.sniff.widget;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.web.sniff.b;
import com.xunlei.xllib.b.d;

private class SniffSuffixSettingItemView$a extends BaseAdapter {
    final /* synthetic */ SniffSuffixSettingItemView a;

    private SniffSuffixSettingItemView$a(SniffSuffixSettingItemView sniffSuffixSettingItemView) {
        this.a = sniffSuffixSettingItemView;
    }

    public final /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public final int getCount() {
        return d.a(SniffSuffixSettingItemView.a(this.a)) ? 0 : SniffSuffixSettingItemView.a(this.a).size();
    }

    private b a(int i) {
        return d.a(SniffSuffixSettingItemView.a(this.a)) ? null : (b) SniffSuffixSettingItemView.a(this.a).get(i);
    }

    public final boolean isEnabled(int i) {
        return (SniffSuffixSettingItemView.a(this.a) == null || SniffSuffixSettingItemView.a(this.a).size() < i || SniffSuffixSettingItemView.a(this.a).get(i) == null) ? false : true;
    }

    public final long getItemId(int i) {
        return (long) i;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        SniffSuffixSettingItemView$b sniffSuffixSettingItemView$b;
        if (view == null) {
            SniffSuffixSettingItemView$b sniffSuffixSettingItemView$b2 = new SniffSuffixSettingItemView$b(this.a);
            view = LayoutInflater.from(this.a.getContext()).inflate(R.layout.sniff_suffix_setting_list_item, null);
            sniffSuffixSettingItemView$b2.a = (ImageView) view.findViewById(R.id.suffix_setting_item_icon);
            sniffSuffixSettingItemView$b2.b = (TextView) view.findViewById(R.id.suffix_setting_item_name);
            view.setTag(sniffSuffixSettingItemView$b2);
            sniffSuffixSettingItemView$b = sniffSuffixSettingItemView$b2;
        } else {
            sniffSuffixSettingItemView$b = (SniffSuffixSettingItemView$b) view.getTag();
        }
        b a = a(i);
        if (a != null) {
            if (!TextUtils.isEmpty(a.a)) {
                sniffSuffixSettingItemView$b.b.setText(a.a);
            }
            if (a.b) {
                sniffSuffixSettingItemView$b.a.setImageResource(R.drawable.sniff_suffix_select_enable);
                sniffSuffixSettingItemView$b.b.setTextColor(-15559434);
            } else {
                sniffSuffixSettingItemView$b.a.setImageResource(R.drawable.sniff_suffix_select_disable);
                sniffSuffixSettingItemView$b.b.setTextColor(-6513508);
            }
        }
        return view;
    }
}
