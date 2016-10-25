package com.xunlei.downloadprovider.ad.home.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.ad.common.b;
import com.xunlei.downloadprovider.ad.home.a.c;
import com.xunlei.downloadprovider.ad.home.ui.ADItemView.AD_LAYOUT_TYPE;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.d;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.e;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.f;
import com.xunlei.downloadprovider.homepage.choiceness.ui.n;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.Set;

public class ADLongVideoItem extends FrameLayout implements ADItemView, d<com.xunlei.downloadprovider.homepage.choiceness.a.a.a> {
    private final String a;
    private String b;
    private com.xunlei.downloadprovider.ad.common.a c;

    private class a {
        TextView a;
        TextView b;
        ImageView c;

        private a() {
        }
    }

    public final /* synthetic */ void a(int i, f fVar, View view, e eVar) {
        com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar = (com.xunlei.downloadprovider.homepage.choiceness.a.a.a) eVar;
        new StringBuilder("bindView resId: ").append(aVar.d);
        if (view instanceof ADItemView) {
            ((ADItemView) view).a(aVar.d);
            com.xunlei.downloadprovider.ad.common.a a = c.a(getContext().getApplicationContext()).c.a(aVar.d);
            if (this.c != a || a == null) {
                a aVar2 = (a) getTag();
                if (aVar2 != null) {
                    aVar2.a.setText(BuildConfig.VERSION_NAME);
                    aVar2.b.setText(BuildConfig.VERSION_NAME);
                    aVar2.c.setImageResource(R.drawable.choiceness_icon_default);
                    aVar2.c.setTag(aVar2.c.getId(), null);
                }
            }
            c.a(getContext()).a(aVar, (ADItemView) view, (n) fVar);
        }
    }

    public ADLongVideoItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = c.a;
        a(context);
    }

    public ADLongVideoItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = c.a;
        a(context);
    }

    public ADLongVideoItem(Context context) {
        super(context);
        this.a = c.a;
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.choiceness_long_video_ad_item, this, true);
        a aVar = new a();
        aVar.a = (TextView) findViewById(R.id.item_title);
        aVar.b = (TextView) findViewById(R.id.item_introduction);
        aVar.c = (ImageView) findViewById(R.id.item_icon);
        setTag(aVar);
    }

    public String getViewPositionKey() {
        return this.b;
    }

    public final String a(String str) {
        this.b = str;
        return str;
    }

    public AD_LAYOUT_TYPE getADType() {
        return AD_LAYOUT_TYPE.LONG_VOD_TYPE_VIEW;
    }

    public final void a(com.xunlei.downloadprovider.ad.common.a aVar) {
        this.c = aVar;
        a aVar2 = (a) getTag();
        if (aVar2 != null) {
            aVar2.a.setText(aVar.a());
            aVar2.b.setText(aVar.b());
            if (!TextUtils.isEmpty(aVar.d())) {
                aVar2.c.setTag(aVar.d());
                com.xunlei.downloadprovider.homepage.choiceness.a.a(aVar.d(), aVar2.c);
            }
        }
        if (getParent() != null) {
            Set set = c.a(getContext()).c.c;
            if (set != null && !set.contains(this.b)) {
                com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a.a("adv_homeflow_movie_show", b.a(aVar), aVar.o().getSourceName(), aVar.j());
                set.add(this.b);
                aVar.a((View) this);
            }
        }
    }

    public View getContainer() {
        return this;
    }

    public com.xunlei.downloadprovider.homepage.choiceness.a.a.a getItemInfo() {
        return null;
    }

    public final /* synthetic */ boolean a(int i, e eVar) {
        if (this.c != null) {
            this.c.onClick(this);
            com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a.a("adv_homeflow_movie_click", b.a(this.c), this.c.o().getSourceName(), this.c.j());
        }
        return true;
    }
}
