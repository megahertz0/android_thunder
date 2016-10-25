package com.xunlei.downloadprovider.search.ui.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.frame.BaseCacheViewFragment;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.f;
import com.xunlei.downloadprovider.search.a.a;
import com.xunlei.downloadprovider.search.b.c;
import com.xunlei.downloadprovider.search.ui.widget.HistoryView;
import com.xunlei.xiazaibao.BuildConfig;
import org.android.spdy.SpdyProtocol;

public class SearchMainFragment extends BaseCacheViewFragment implements OnClickListener {
    private View a;
    private HistoryView b;
    private t c;
    private View d;
    private View e;
    private ListView f;
    private k g;
    private c h;

    public SearchMainFragment() {
        this.h = c.a();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.c = new t(getActivity());
        this.g = new k(getActivity());
        this.g.a = 8;
    }

    public View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.search_main_fragment, viewGroup, false);
        this.f = (ListView) inflate.findViewById(R.id.hot_listview);
        View inflate2 = LayoutInflater.from(getActivity()).inflate(R.layout.search_main_fragment_header, null);
        this.a = inflate2.findViewById(R.id.record_layout);
        this.a.findViewById(R.id.delete_btn).setOnClickListener(this);
        this.b = (HistoryView) this.a.findViewById(R.id.history_view);
        this.b.setAdapter(this.c);
        this.b.setOnItemClickListener(new p(this));
        this.d = inflate2.findViewById(R.id.list_title);
        this.e = inflate2.findViewById(R.id.hot_bottom_line);
        this.f.addHeaderView(inflate2, null, false);
        this.f.addFooterView(new View(getActivity()), null, false);
        this.f.setAdapter(this.g);
        this.f.setOnItemClickListener(new r(this));
        return inflate;
    }

    public void onResume() {
        super.onResume();
        a();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.delete_btn:
                a.a().c();
                this.c.a(null);
                b();
                f.b("histroy", "delete", BuildConfig.VERSION_NAME);
            default:
                break;
        }
    }

    private void a() {
        this.c.a(a.a().b());
        b();
    }

    private void b() {
        if (this.d != null) {
            if (this.g.isEmpty()) {
                this.d.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                this.d.setVisibility(0);
                this.e.setVisibility(0);
            }
        }
        if (this.a == null) {
            return;
        }
        if (this.c.isEmpty()) {
            this.a.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        } else {
            this.a.setVisibility(0);
        }
    }
}
