package com.xunlei.tdlive.play.a;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.xunlei.tdlive.a.x.a;
import com.xunlei.tdlive.play.view.ah;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: ReplayDialogPresenter.java
class ae implements OnItemClickListener {
    final /* synthetic */ aa a;

    ae(aa aaVar) {
        this.a = aaVar;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.a.f() == null) {
            XLog.e("ReplayDialogPresenter", "activity\u4e3a\u7a7a");
            return;
        }
        a aVar = (a) aa.f(this.a).getItem(i);
        if (aVar == null) {
            XLog.e("ReplayDialogPresenter", "item\u4e3a\u7a7a");
            return;
        }
        ah.a aVar2 = new ah.a();
        aVar2.b = aVar.a.user.nickname;
        aVar2.e = BuildConfig.VERSION_NAME;
        aVar2.c = 0;
        aVar2.f = aVar.a.user.userid;
        aVar2.i = false;
        aVar2.j = aa.l(this.a);
        if (aVar2.f == null) {
            XLog.e("ReplayDialogPresenter", "\u7528\u6237\u53f7\u4e3a\u7a7a");
        } else if (aVar2.f.equals("0")) {
            XLog.e("ReplayDialogPresenter", "\u6b64\u6761\u4e3a\u901a\u77e5");
        } else {
            aa.a(this.a, aVar2);
        }
    }
}
