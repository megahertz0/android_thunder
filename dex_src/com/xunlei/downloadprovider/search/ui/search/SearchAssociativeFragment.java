package com.xunlei.downloadprovider.search.ui.search;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.xunlei.downloadprovider.frame.BaseFragment;
import com.xunlei.downloadprovider.search.bean.a.a;
import com.xunlei.downloadprovider.util.sniff.SniffConfigure;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SearchAssociativeFragment extends BaseFragment {
    h a;
    a b;
    String c;
    private ListView d;
    private View e;
    private String f;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(2130968941, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.d = (ListView) view.findViewById(2131755401);
        this.e = LayoutInflater.from(getActivity()).inflate(2130968633, this.d, false);
        this.b = new a();
        this.b.a = (TextView) this.e.findViewById(2131755340);
        this.b.b = (TextView) this.e.findViewById(2131755341);
        this.d.addHeaderView(this.e);
        this.d.addFooterView(new View(getActivity()), null, false);
        this.a = new h(getActivity());
        this.d.setAdapter(this.a);
        this.d.setOnItemClickListener(new i(this));
        if (this.b != null) {
            a(this.b.a, this.b.b, this.c);
        }
    }

    final void a(TextView textView, TextView textView2, String str) {
        if (this.e != null && this.b != null && textView2 != null && textView != null) {
            if (TextUtils.isEmpty(str)) {
                this.e.setVisibility(XZBDevice.Wait);
                this.d.setHeaderDividersEnabled(false);
                return;
            }
            a.a(textView, str, str);
            Random random = new Random();
            ArrayList arrayList = SniffConfigure.a().b().a;
            if (arrayList == null || arrayList.isEmpty()) {
                textView2.setVisibility(XZBDevice.Wait);
                return;
            }
            this.f = (String) arrayList.get(random.nextInt(arrayList.size()));
            textView2.setText(this.f);
            textView2.setVisibility(0);
            this.e.setVisibility(0);
            this.d.setHeaderDividersEnabled(true);
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    static /* synthetic */ void a(String str, List list) {
        for (a aVar : list) {
            if (str.equals(aVar.a)) {
                list.remove(aVar);
                return;
            }
        }
    }
}
