package com.xunlei.downloadprovider.member.payment.ui.widget;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.android.volley.toolbox.t;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.payment.a.k;
import com.xunlei.downloadprovider.member.payment.a.k$b;
import com.xunlei.downloadprovider.member.payment.a.n;
import com.xunlei.downloadprovider.member.payment.a.o;
import com.xunlei.downloadprovider.member.payment.ui.widget.b.c;

// compiled from: VoucherListDialog.java
final class e implements OnItemClickListener {
    final /* synthetic */ b a;

    e(b bVar) {
        this.a = bVar;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        c cVar = (c) adapterView.getItemAtPosition(i);
        if (!this.a.e.contains(cVar.b)) {
            b d = this.a.b;
            CharSequence charSequence = cVar.b;
            if (!TextUtils.isEmpty(charSequence)) {
                d.d.add(charSequence);
            }
            d.notifyDataSetChanged();
            this.a.b.notifyDataSetChanged();
            k a = k.a();
            String str = cVar.b;
            k$b fVar = new f(this, cVar);
            if (LoginHelper.c()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("http://dyact.vip.xunlei.com/cash/check_json/?");
                stringBuilder.append("cashno=").append(str).append("&");
                stringBuilder.append("userid=").append(a.a.a.j).append("&");
                stringBuilder.append("sessionid=").append(a.a.a.k);
                t tVar = new t(stringBuilder.toString(), new n(a, fVar), new o(a, fVar));
                tVar.setShouldCache(false);
                a.a(tVar);
            }
        }
    }
}
