package com.xunlei.downloadprovider.search.ui.search;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xunlei.downloadprovider.util.sniff.SniffConfigure;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// compiled from: SearchAssociativeAdapter.java
public final class h extends com.xunlei.downloadprovider.search.ui.home.a<com.xunlei.downloadprovider.search.bean.a.a> {
    private String a;

    // compiled from: SearchAssociativeAdapter.java
    static class a {
        TextView a;
        TextView b;

        a() {
        }

        public static void a(TextView textView, String str, String str2) {
            if (TextUtils.isEmpty(str2)) {
                textView.setText(com.umeng.a.d);
            } else if (TextUtils.isEmpty(str)) {
                textView.setText(str2);
            } else {
                int indexOf = str2.indexOf(str);
                if (indexOf < 0) {
                    textView.setText(str2);
                    return;
                }
                CharSequence spannableStringBuilder = new SpannableStringBuilder(str2);
                spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#9c9c9c")), indexOf, str.length() + indexOf, R.styleable.AppCompatTheme_actionModeCopyDrawable);
                textView.setText(spannableStringBuilder);
            }
        }
    }

    public h(Context context) {
        super(context);
    }

    public final void a(String str, List<com.xunlei.downloadprovider.search.bean.a.a> list) {
        this.a = str;
        if (!(list == null || list.isEmpty())) {
            ArrayList arrayList = SniffConfigure.a().b().a;
            if (!(arrayList == null || arrayList.isEmpty())) {
                int size = arrayList.size();
                Random random = new Random();
                for (com.xunlei.downloadprovider.search.bean.a.a aVar : list) {
                    aVar.f = (String) arrayList.get(random.nextInt(size));
                }
            }
        }
        super.a(list);
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(this.e).inflate(2130968633, viewGroup, false);
            aVar = new a();
            aVar.a = (TextView) view.findViewById(2131755340);
            aVar.b = (TextView) view.findViewById(2131755341);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        com.xunlei.downloadprovider.search.bean.a.a aVar2 = (com.xunlei.downloadprovider.search.bean.a.a) getItem(i);
        a.a(aVar.a, this.a, aVar2.a);
        CharSequence charSequence = aVar2.f;
        if (TextUtils.isEmpty(charSequence)) {
            aVar.b.setVisibility(XZBDevice.Wait);
        } else {
            aVar.b.setText(charSequence);
            aVar.b.setVisibility(0);
        }
        return view;
    }
}
