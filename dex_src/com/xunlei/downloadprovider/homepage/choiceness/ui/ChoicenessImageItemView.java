package com.xunlei.downloadprovider.homepage.choiceness.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.d;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.e;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.f;
import org.android.spdy.SpdyProtocol;

public class ChoicenessImageItemView extends FrameLayout implements d<com.xunlei.downloadprovider.homepage.choiceness.a.a.a> {
    private com.xunlei.downloadprovider.homepage.choiceness.a.a.a a;

    private class a {
        TextView a;
        ImageView b;
        View c;

        private a() {
        }
    }

    public final /* synthetic */ void a(int i, f fVar, View view, e eVar) {
        com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar = (com.xunlei.downloadprovider.homepage.choiceness.a.a.a) eVar;
        this.a = aVar;
        a aVar2 = (a) getTag();
        aVar2.a.setText(aVar.k);
        if (!TextUtils.isEmpty(aVar.f)) {
            com.xunlei.downloadprovider.homepage.choiceness.a.a(aVar.f, aVar2.b);
        }
        if (aVar.a) {
            aVar2.c.setVisibility(0);
        } else {
            aVar2.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
        aVar2.b.setOnClickListener(new c(this, aVar));
    }

    public final /* bridge */ /* synthetic */ boolean a(int i, e eVar) {
        return false;
    }

    public ChoicenessImageItemView(Context context) {
        super(context);
        a(context);
    }

    public ChoicenessImageItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public ChoicenessImageItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.choiceness_image_item, this, true);
        a aVar = new a();
        aVar.a = (TextView) inflate.findViewById(R.id.item_title);
        aVar.b = (ImageView) inflate.findViewById(R.id.item_icon);
        aVar.c = inflate.findViewById(R.id.on_the_top_mask);
        setTag(aVar);
    }

    public com.xunlei.downloadprovider.homepage.choiceness.a.a.a getItemInfo() {
        return this.a;
    }
}
