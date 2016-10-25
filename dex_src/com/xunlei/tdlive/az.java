package com.xunlei.tdlive;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.xunlei.tdlive.a.x.a;
import com.xunlei.tdlive.play.view.ah;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.q;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: LivePlayerDialog.java
class az implements OnItemClickListener {
    final /* synthetic */ au a;

    az(au auVar) {
        this.a = auVar;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.a.getOwnerActivity() == null) {
            XLog.e("LivePlayerDialog", "activity\u4e3a\u7a7a");
            return;
        }
        a aVar = (a) au.j(this.a).getItem(i);
        if (aVar == null) {
            XLog.e("LivePlayerDialog", "item\u4e3a\u7a7a");
            return;
        }
        ah.a aVar2 = new ah.a();
        aVar2.b = aVar.a.user.nickname;
        aVar2.e = BuildConfig.VERSION_NAME;
        aVar2.c = 0;
        aVar2.f = aVar.a.user.userid;
        aVar2.i = false;
        aVar2.j = au.k(this.a);
        if (aVar2.f == null) {
            XLog.e("LivePlayerDialog", "\u7528\u6237\u53f7\u4e3a\u7a7a");
        } else if (aVar2.f.equals("0")) {
            XLog.e("LivePlayerDialog", "\u6b64\u6761\u4e3a\u901a\u77e5");
        } else {
            au.a(this.a, aVar2);
            q.e("userinfo").a("speek").a("hostid", au.h(this.a)).a("userid", aVar.a.user.userid).a("follow", aVar2.g).b(new String[0]);
        }
    }
}
