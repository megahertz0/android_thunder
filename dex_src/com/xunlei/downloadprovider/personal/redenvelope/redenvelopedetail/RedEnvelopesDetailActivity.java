package com.xunlei.downloadprovider.personal.redenvelope.redenvelopedetail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.Request;
import com.xunlei.downloadprovider.commonview.UnifiedLoadingView;
import com.xunlei.downloadprovider.commonview.f;
import com.xunlei.downloadprovider.personal.redenvelope.redenvelopedetail.a.a;
import com.xunlei.downloadprovider.personal.redenvelope.redenvelopedetail.a.b;
import com.xunlei.downloadprovider.personal.redenvelope.redenvelopedetail.a.c;
import com.xunlei.downloadprovider.personal.redenvelope.redenvelopedetail.a.d;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class RedEnvelopesDetailActivity extends Activity {
    private ImageView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private int g;
    private UnifiedLoadingView h;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968614);
        this.a = (ImageView) findViewById(2131755242);
        this.b = (TextView) findViewById(2131755243);
        this.c = (TextView) findViewById(2131755244);
        this.d = (TextView) findViewById(2131755249);
        this.e = (TextView) findViewById(2131755251);
        this.f = (TextView) findViewById(2131755253);
        this.h = (UnifiedLoadingView) findViewById(2131755240);
        this.h.setType(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        this.h.setBackgroundColor(Color.parseColor("#ffffff"));
        this.h.c();
        this.g = new Intent().getIntExtra("extra_red_id", -1);
        if (a.a == null) {
            a.a = new a();
        }
        a aVar = a.a;
        a.a aVar2 = new a(this);
        Request aVar3 = new com.xunlei.downloadprovidercommon.b.a.a("http://api-shoulei-ssl.xunlei.com/red_packets_cli/info?id=%id".replace("%id", String.valueOf(this.g)), new b(aVar, aVar2), new c(aVar, aVar2));
        aVar3.setShouldCache(false);
        aVar.b.a(aVar3);
    }

    public static void a(Context context, int i) {
        Intent intent = new Intent(context, RedEnvelopesDetailActivity.class);
        intent.putExtra("extra_red_id", i);
        context.startActivity(intent);
    }

    static /* synthetic */ void a(RedEnvelopesDetailActivity redEnvelopesDetailActivity, d dVar) {
        if (dVar != null) {
            f fVar = new f((Activity) redEnvelopesDetailActivity);
            fVar.i.setText("\u6e38\u620f\u793c\u5305");
            fVar.k.setVisibility(0);
            com.xunlei.downloadprovider.homepage.choiceness.a.a(dVar.e, redEnvelopesDetailActivity.a);
            redEnvelopesDetailActivity.b.setText(dVar.c);
            redEnvelopesDetailActivity.c.setText(com.xunlei.downloadprovider.personal.redenvelope.a.a.replace("%s", dVar.d));
            com.xunlei.downloadprovider.personal.redenvelope.a.a(redEnvelopesDetailActivity.c, (long) dVar.d);
            redEnvelopesDetailActivity.d.setText(dVar.j);
            redEnvelopesDetailActivity.e.setText(String.valueOf(dVar.g));
            redEnvelopesDetailActivity.f.setText(dVar.m);
        }
    }
}
