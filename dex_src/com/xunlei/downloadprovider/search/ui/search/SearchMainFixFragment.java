package com.xunlei.downloadprovider.search.ui.search;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.xunlei.downloadprovider.frame.BaseCacheViewFragment;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.f;
import com.xunlei.downloadprovider.search.a.a;
import com.xunlei.downloadprovider.search.b.c;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.List;

public class SearchMainFixFragment extends BaseCacheViewFragment implements OnClickListener {
    private View a;
    private GridView b;
    private t c;
    private View d;
    private View e;
    private ListView f;
    private k g;
    private c h;
    private LinearLayout i;

    public SearchMainFixFragment() {
        this.h = c.a();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.c = new t(getActivity());
        this.g = new k(getActivity());
        this.g.a = 8;
    }

    public View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130968948, viewGroup, false);
        this.i = (LinearLayout) inflate.findViewById(2131755365);
        this.f = (ListView) inflate.findViewById(2131756865);
        LayoutInflater.from(getActivity()).inflate(2130968950, null);
        this.a = inflate.findViewById(2131756123);
        this.a.findViewById(2131756869).setOnClickListener(this);
        this.b = (GridView) this.a.findViewById(2131756870);
        this.b.setAdapter(this.c);
        this.b.setOnItemClickListener(new l(this));
        this.d = inflate.findViewById(2131756867);
        this.e = inflate.findViewById(2131756868);
        this.f.addFooterView(new View(getActivity()), null, false);
        this.f.setAdapter(this.g);
        this.f.setOnItemClickListener(new n(this));
        return inflate;
    }

    public void onResume() {
        super.onResume();
        a();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case 2131756869:
                a.a().c();
                this.c.a(null);
                FragmentActivity activity = getActivity();
                if (!(this.i == null || activity == null)) {
                    this.i.setBackgroundColor(getActivity().getResources().getColor(R.color.common_content_bkg_color));
                }
                b();
                f.b("histroy", "delete", com.umeng.a.d);
            default:
                break;
        }
    }

    private void a() {
        List b = a.a().b();
        Activity activity = getActivity();
        if (b.size() == 0) {
            if (!(activity == null || this.i == null)) {
                this.i.setBackgroundColor(getActivity().getResources().getColor(R.color.common_content_bkg_color));
            }
        } else if (!(this.i == null || activity == null)) {
            this.i.setBackgroundColor(activity.getResources().getColor(R.color.white));
        }
        this.c.a(b);
        b();
    }

    private void b() {
        if (this.d != null) {
            if (this.g.isEmpty()) {
                this.d.setVisibility(XZBDevice.Wait);
                this.e.setVisibility(XZBDevice.Wait);
            } else {
                this.d.setVisibility(0);
                this.e.setVisibility(0);
            }
        }
        if (this.a == null) {
            return;
        }
        if (this.c.isEmpty()) {
            this.a.setVisibility(XZBDevice.Wait);
        } else {
            this.a.setVisibility(0);
        }
    }
}
