package com.xunlei.downloadprovider.homepage.choiceness.ui;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter;
import com.xunlei.downloadprovider.homepage.choiceness.b;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.d;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.e;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.f;
import com.xunlei.tdlive.sdk.XLLiveSDK;
import org.android.spdy.SpdyProtocol;

public class ChoicenessLivestreamItemView extends FrameLayout implements d<com.xunlei.downloadprovider.homepage.choiceness.a.a.a> {
    private com.xunlei.downloadprovider.homepage.choiceness.a.a.a a;

    private static class a {
        ImageView a;
        TextView b;
        TextView c;
        ImageView d;
        ImageView e;
        TextView f;
        TextView g;

        private a() {
        }
    }

    public final /* synthetic */ void a(int i, f fVar, View view, e eVar) {
        com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar = (com.xunlei.downloadprovider.homepage.choiceness.a.a.a) eVar;
        this.a = aVar;
        a aVar2 = (a) getTag();
        String str = aVar.y;
        ImageView imageView = aVar2.a;
        com.nostra13.universalimageloader.core.c.a aVar3 = new com.nostra13.universalimageloader.core.c.a();
        aVar3.a = 2130838392;
        aVar3.h = true;
        com.nostra13.universalimageloader.core.d.a().a(str, imageView, aVar3.a().b(), new b(imageView));
        aVar2.b.setText(aVar.x);
        aVar2.c.setText(String.valueOf(aVar.z));
        com.xunlei.downloadprovider.homepage.choiceness.a.a(aVar.f, aVar2.d);
        aVar2.f.setText(aVar.k);
        if (aVar.B != 0) {
            aVar2.g.setVisibility(0);
            aVar2.g.setText(getResources().getString(R.string.choiceness_live_like_count, new Object[]{Long.valueOf(r2)}));
            return;
        }
        aVar2.g.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }

    public final /* synthetic */ boolean a(int i, e eVar) {
        com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar = (com.xunlei.downloadprovider.homepage.choiceness.a.a.a) eVar;
        ChoicenessReporter.a(i, aVar);
        XLLiveSDK.getInstance(getContext()).play(getContext(), aVar.A);
        return true;
    }

    public ChoicenessLivestreamItemView(Context context) {
        this(context, null);
    }

    public ChoicenessLivestreamItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ChoicenessLivestreamItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View inflate = LayoutInflater.from(context).inflate(R.layout.choiceness_livestream_item, this, true);
        a aVar = new a();
        aVar.a = (ImageView) inflate.findViewById(R.id.iv_avatar);
        aVar.b = (TextView) inflate.findViewById(R.id.tv_nickname);
        aVar.c = (TextView) inflate.findViewById(R.id.tv_live_num);
        aVar.d = (ImageView) inflate.findViewById(R.id.iv_cover);
        aVar.e = (ImageView) inflate.findViewById(R.id.iv_livestream_status);
        ((AnimationDrawable) aVar.e.getDrawable()).start();
        aVar.f = (TextView) inflate.findViewById(com.xunlei.downloadprovidershare.R.id.tv_title);
        aVar.g = (TextView) inflate.findViewById(R.id.tv_like_num);
        setTag(aVar);
    }

    public com.xunlei.downloadprovider.homepage.choiceness.a.a.a getItemInfo() {
        return this.a;
    }
}
