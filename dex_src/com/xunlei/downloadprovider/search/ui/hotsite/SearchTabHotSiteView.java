package com.xunlei.downloadprovider.search.ui.hotsite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.connect.common.Constants;
import com.xunlei.downloadprovider.b.c.e;
import com.xunlei.downloadprovider.model.i;
import com.xunlei.downloadprovider.search.ui.hotsite.SearchTabHotSiteView.d;
import com.xunlei.downloadprovider.search.ui.widget.AutoListView;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class SearchTabHotSiteView extends RelativeLayout implements OnClickListener {
    private static b h;
    public Context a;
    final SQLiteDatabase b;
    private View c;
    private AutoListView d;
    private List<a> e;
    private c f;
    private com.xunlei.downloadprovider.a.h.b g;
    private TextView i;
    private LinearLayout j;
    private Button k;

    public enum InitType {
        initHot,
        initHistory,
        initHotList,
        initHotSite;

        static {
            initHot = new com.xunlei.downloadprovider.search.ui.hotsite.SearchTabHotSiteView.InitType("initHot", 0);
            initHistory = new com.xunlei.downloadprovider.search.ui.hotsite.SearchTabHotSiteView.InitType("initHistory", 1);
            initHotList = new com.xunlei.downloadprovider.search.ui.hotsite.SearchTabHotSiteView.InitType("initHotList", 2);
            initHotSite = new com.xunlei.downloadprovider.search.ui.hotsite.SearchTabHotSiteView.InitType("initHotSite", 3);
            a = new com.xunlei.downloadprovider.search.ui.hotsite.SearchTabHotSiteView.InitType[]{initHot, initHistory, initHotList, initHotSite};
        }
    }

    class a {
        String a;
        String b;
        String c;
        String d;
        int e;

        a() {
        }
    }

    public class b extends com.xunlei.downloadprovider.e.a.a.a {
        d a;
        int b;
        private LayoutInflater d;

        public final /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public b(Context context) {
            this.a = null;
            this.d = LayoutInflater.from(context);
        }

        public final int getCount() {
            return SearchTabHotSiteView.this.e.size();
        }

        public final a a(int i) {
            return (a) SearchTabHotSiteView.this.e.get(i);
        }

        public final long getItemId(int i) {
            return 0;
        }

        public final View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                StringBuilder append = new StringBuilder().append(getClass()).append("---getView---");
                int i2 = this.b;
                this.b = i2 + 1;
                append.append(i2).append("---").append(Thread.currentThread().getId());
                this.a = new d();
                view = this.d.inflate(2130968795, null);
                this.a.a = (TextView) view.findViewById(2131756129);
                this.a.b = (TextView) view.findViewById(2131756130);
                SearchTabHotSiteView.this = (TextView) view.findViewById(2131756131);
                this.a.d = (TextView) view.findViewById(2131756132);
                this.a.e = (ImageView) view.findViewById(2131756134);
                this.a.f = (LinearLayout) view.findViewById(2131756133);
                this.a.f.setOnClickListener(new a(this, i));
                view.setTag(this.a);
            } else {
                this.a = (d) view.getTag();
            }
            this.a.a.setText(((a) SearchTabHotSiteView.this.e.get(i)).a);
            if (i == 0) {
                this.a.a.setBackgroundResource(2130838363);
            }
            if (i == 1) {
                this.a.a.setBackgroundResource(2130838364);
            }
            if (i == 2) {
                this.a.a.setBackgroundResource(2130838365);
            }
            this.a.b.setText(((a) SearchTabHotSiteView.this.e.get(i)).b);
            SearchTabHotSiteView.this.setText(SearchTabHotSiteView.this + "\u4e07\u96f7\u53cb\u6765\u8fc7");
            this.a.d.setText(((a) SearchTabHotSiteView.this.e.get(i)).d);
            if (((a) SearchTabHotSiteView.this.e.get(i)).e == 1) {
                this.a.e.setBackgroundResource(2130838176);
            } else {
                this.a.e.setBackgroundResource(2130838172);
            }
            return view;
        }
    }

    public static interface c {
    }

    public final class d {
        public TextView a;
        public TextView b;
        public TextView c;
        public TextView d;
        public ImageView e;
        public LinearLayout f;
    }

    public final void setFinishInitTabViewListener(c cVar) {
        this.f = cVar;
    }

    public SearchTabHotSiteView(Context context) {
        super(context);
        this.b = new i().getWritableDatabase();
        this.g = new c(this);
        this.a = context;
        this.c = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(2130968955, this);
        this.k = (Button) findViewById(2131755659);
        this.k.setOnClickListener(this);
        this.j = (LinearLayout) findViewById(2131756872);
        if (com.xunlei.xllib.a.b.a(this.a)) {
            this.j.setVisibility(XZBDevice.Wait);
        } else {
            this.j.setVisibility(0);
        }
        new StringBuilder().append(getClass()).append("---NetHelper.isNetworkAvailable(this.mContext)---").append(com.xunlei.xllib.a.b.a(this.a));
        this.i = (TextView) this.c.findViewById(2131756873);
        this.d = (AutoListView) this.c.findViewById(2131756875);
        this.e = new ArrayList();
        h = new b(getContext());
        this.d.setOnItemClickListener(new f(this));
        b();
    }

    public final void onClick(View view) {
        switch (view.getId()) {
            case 2131755659:
                if (com.xunlei.xllib.a.b.a(this.a)) {
                    this.j.setVisibility(XZBDevice.Wait);
                } else {
                    this.j.setVisibility(0);
                }
                b();
            default:
                break;
        }
    }

    private void b() {
        this.i.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        com.xunlei.downloadprovider.b.a aVar = new a(this.g);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://m.sjzhushou.com/xlconfig/hotsites");
        new StringBuilder().append(aVar.getClass()).append("---sb.toString()---").append(stringBuilder.toString());
        e aVar2 = new com.xunlei.downloadprovider.b.c.a(stringBuilder.toString(), Constants.HTTP_GET, null, new a(aVar, (byte) 0));
        aVar2.setBpOnDataLoaderCompleteListener(new b(aVar));
        aVar.setBpFuture(aVar2);
        a.runBox(aVar);
    }
}
