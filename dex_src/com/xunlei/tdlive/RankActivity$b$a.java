package com.xunlei.tdlive;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.xunlei.downloadprovider.member.payment.external.PayBaseConstants;
import com.xunlei.tdlive.RankActivity.b;
import com.xunlei.tdlive.a.j.a;
import com.xunlei.tdlive.a.s;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.usercenter.UserCenterActivity;
import com.xunlei.xiazaibao.BuildConfig;
import org.android.spdy.SpdyProtocol;

class RankActivity$b$a extends PagerAdapter implements OnItemClickListener, a {
    s[] a;
    ListView[] b;
    final /* synthetic */ b c;

    public RankActivity$b$a(b bVar) {
        this.c = bVar;
        this.a = new s[3];
        this.b = new ListView[3];
        this.a[0] = new s(b.a(bVar), PayBaseConstants.PAY_DAY, b.b(bVar), this);
        this.a[1] = new s(b.a(bVar), "week", b.b(bVar), this);
        this.a[2] = new s(b.a(bVar), b.a(bVar) == 0 ? PayBaseConstants.PAY_MONTH : "sum", b.b(bVar), this);
    }

    public int getCount() {
        return this.a.length;
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
        this.b[i] = null;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View listView = new ListView(viewGroup.getContext());
        listView.setAdapter(this.a[i]);
        listView.setDividerHeight(0);
        listView.setSelector(17170445);
        listView.setOnItemClickListener(this);
        listView.setVerticalScrollBarEnabled(false);
        viewGroup.addView(listView, -1, -1);
        this.b[i] = listView;
        this.a[i].a(BuildConfig.VERSION_NAME);
        return listView;
    }

    public <T> void a(T t, boolean z, boolean z2) {
        int i = SpdyProtocol.PUBKEY_SEQ_ADASH;
        if (!z) {
            b.d(this.c).setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        } else if (this.a[b.c(this.c).getCurrentItem()] != null) {
            View d = b.d(this.c);
            if (this.a[b.c(this.c).getCurrentItem()].isEmpty()) {
                i = 0;
            }
            d.setVisibility(i);
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        JsonWrapper jsonWrapper = (JsonWrapper) adapterView.getAdapter().getItem(i);
        if (jsonWrapper != null && b.a(this.c) == 0) {
            jsonWrapper = jsonWrapper.getObject("user_info", "{}");
            UserCenterActivity.a(adapterView.getContext(), jsonWrapper.getString("userid", BuildConfig.VERSION_NAME), jsonWrapper.getString("nickname", BuildConfig.VERSION_NAME), "ranklist");
        }
    }
}
