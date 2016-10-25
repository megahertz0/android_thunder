package com.xunlei.downloadprovider.personal.settings;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.commonview.f;
import com.xunlei.downloadprovider.web.sniff.SniffSuffixTypeDataManager;
import com.xunlei.downloadprovider.web.sniff.SniffSuffixTypeDataManager.SuffixDataType;
import com.xunlei.downloadprovider.web.sniff.widget.SniffSuffixSettingItemView;
import com.xunlei.xiazaibao.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashSet;

public class SniffSettingActivity extends BaseActivity {
    private f a;
    private SniffSuffixSettingItemView b;
    private SniffSuffixSettingItemView c;
    private SniffSuffixSettingItemView d;
    private SniffSuffixSettingItemView e;
    private SniffSuffixSettingItemView f;
    private SniffSuffixSettingItemView g;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968620);
    }

    protected void onResume() {
        super.onResume();
        SniffSuffixTypeDataManager a = SniffSuffixTypeDataManager.a();
        a.n = com.xunlei.downloadprovider.util.sniff.f.b();
        if (a.n == null || a.n.size() == 0) {
            if (a.n == null) {
                a.n = new HashSet();
            }
            if (a.n.size() == 0) {
                for (Object obj : SniffSuffixTypeDataManager.g) {
                    a.n.add(obj);
                }
            }
            com.xunlei.downloadprovider.util.sniff.f.a(a.n);
        }
        a.b();
        a.c();
        a.d();
        a.e();
        a.f();
        a.g();
        ((TextView) findViewById(R.id.titlebar_title)).setText(2131232541);
        findViewById(R.id.titlebar_left).setOnClickListener(new z(this));
        this.a = new f((Activity) this);
        this.a.j.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        this.b = (SniffSuffixSettingItemView) findViewById(2131755284);
        this.c = (SniffSuffixSettingItemView) findViewById(2131755285);
        this.d = (SniffSuffixSettingItemView) findViewById(2131755286);
        this.e = (SniffSuffixSettingItemView) findViewById(2131755287);
        this.f = (SniffSuffixSettingItemView) findViewById(2131755288);
        this.g = (SniffSuffixSettingItemView) findViewById(2131755289);
        this.b.a(SuffixDataType.video, SniffSuffixTypeDataManager.a().h);
        this.c.a(SuffixDataType.torrent, SniffSuffixTypeDataManager.a().i);
        this.d.a(SuffixDataType.zip, SniffSuffixTypeDataManager.a().j);
        this.e.a(SuffixDataType.music, SniffSuffixTypeDataManager.a().k);
        this.f.a(SuffixDataType.app, SniffSuffixTypeDataManager.a().l);
        this.g.a(SuffixDataType.doc, SniffSuffixTypeDataManager.a().m);
    }
}
