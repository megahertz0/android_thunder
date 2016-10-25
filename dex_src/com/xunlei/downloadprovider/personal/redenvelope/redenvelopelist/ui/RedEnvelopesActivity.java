package com.xunlei.downloadprovider.personal.redenvelope.redenvelopelist.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.android.volley.Request;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.commonview.UnifiedLoadingView;
import com.xunlei.downloadprovider.commonview.f;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.personal.redenvelope.redenvelopelist.b.a;
import com.xunlei.downloadprovider.personal.redenvelope.redenvelopelist.b.b;
import com.xunlei.downloadprovider.personal.redenvelope.redenvelopelist.b.c;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.List;
import org.android.agoo.message.MessageService;

public class RedEnvelopesActivity extends BaseActivity {
    UnifiedLoadingView a;
    private RecyclerView b;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968613);
        f fVar = new f((Activity) this);
        fVar.i.setText(getResources().getString(2131233028));
        fVar.k.setVisibility(0);
        this.b = (RecyclerView) findViewById(2131755239);
        this.a = (UnifiedLoadingView) findViewById(2131755240);
        if (a.a == null) {
            a.a = new a();
        }
        a aVar = a.a;
        a.a dVar = new d(this);
        Request aVar2 = new com.xunlei.downloadprovidercommon.b.a.a("http://pre.api-shoulei-ssl.xunlei.com/red_packets_cli/list?size=8&cursor=%cursor&uid=%uid".replace("%cursor", MessageService.MSG_DB_READY_REPORT).replace("%uid", String.valueOf(LoginHelper.a().j)), new b(aVar, dVar), new c(aVar, dVar));
        aVar2.setShouldCache(false);
        aVar.b.a(aVar2);
        this.b.addOnItemTouchListener(new a(this.b, new c(this)));
        this.a.setType(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        this.a.setBackgroundColor(Color.parseColor("#ffffff"));
        this.a.c();
    }

    static /* synthetic */ void a(RedEnvelopesActivity redEnvelopesActivity, List list) {
        redEnvelopesActivity.b.setLayoutManager(new LinearLayoutManager(redEnvelopesActivity, 1, false));
        redEnvelopesActivity.b.setAdapter(new com.xunlei.downloadprovider.personal.redenvelope.redenvelopelist.a.a(redEnvelopesActivity, list));
    }
}
