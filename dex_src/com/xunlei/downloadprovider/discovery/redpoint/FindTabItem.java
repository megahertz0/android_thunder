package com.xunlei.downloadprovider.discovery.redpoint;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.c;
import com.nostra13.universalimageloader.core.d;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.discovery.a.a;
import com.xunlei.downloadprovider.discovery.a.b;
import com.xunlei.downloadprovider.util.v;
import com.xunlei.downloadprovider.web.base.WebViewNormalActivity;
import com.xunlei.xiazaibao.BuildConfig;
import org.android.spdy.SpdyProtocol;

public class FindTabItem extends FrameLayout {
    static final /* synthetic */ boolean i;
    public ImageView a;
    public TextView b;
    public TextView c;
    public ImageView d;
    public ImageView e;
    public ImageView f;
    public String g;
    public a h;
    private d j;
    private c k;
    private View l;
    private LinearLayout m;

    static {
        i = !FindTabItem.class.desiredAssertionStatus();
    }

    public FindTabItem(Context context) {
        super(context);
        a(context);
        a();
    }

    public FindTabItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
        a();
    }

    public FindTabItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
        a();
    }

    private void a() {
        this.j = d.a();
        c.a aVar = new c.a();
        aVar.h = true;
        aVar.a();
        this.k = aVar.b();
    }

    private void a(Context context) {
        this.l = LayoutInflater.from(context).inflate(R.layout.find_item_tip_layout, null);
        this.m = (LinearLayout) this.l.findViewById(R.id.config_view);
        this.a = (ImageView) this.l.findViewById(R.id.iv_find_item_icon);
        this.b = (TextView) this.l.findViewById(R.id.tv_find_item_name);
        this.c = (TextView) this.l.findViewById(R.id.tv_find_item_tip_text);
        this.d = (ImageView) this.l.findViewById(R.id.iv_find_item_tip_pic);
        this.e = (ImageView) this.l.findViewById(R.id.iv_find_item_tip_red_point);
        this.f = (ImageView) this.l.findViewById(R.id.iv_find_item_go);
        addView(this.l);
    }

    public void setItemRedPointConfigData(a aVar) {
        boolean z = true;
        if (aVar == null) {
            this.m.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            setTipRedPointShow(false);
        } else if (aVar.a() && b.b()) {
            this.m.setVisibility(0);
            if (aVar.g != 1) {
                z = false;
            }
            setTipRedPointShow(z);
        } else {
            this.m.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            setTipRedPointShow(false);
        }
    }

    public void setItemName(String str) {
        this.b.setText(str);
    }

    public void setLeftIcon(int i) {
        this.a.setImageResource(i);
    }

    public void setConfigInfoVisibility(int i) {
        this.m.setVisibility(i);
    }

    public void setRightTipIconVisibility(int i) {
        this.d.setVisibility(i);
    }

    public void setItemTipText(String str) {
        if (TextUtils.isEmpty(str)) {
            this.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            return;
        }
        this.c.setVisibility(0);
        this.c.setText(str);
    }

    public void setItemRightTipIconFromUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            this.d.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        } else {
            this.j.a(str, this.d, this.k);
        }
    }

    public void setItemRightTipIconFromRes(int i) {
        this.d.setImageResource(i);
    }

    public void setItemLeftIconFromRes(int i) {
        this.a.setImageResource(i);
    }

    public void setTipRedPointShow(boolean z) {
        if (z) {
            this.e.setVisibility(0);
        } else {
            this.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
    }

    public void setmTvItemDividerShow(String str) {
        int i = -1;
        if (!TextUtils.isEmpty(str)) {
            if (str.equals("nearby")) {
                i = R.drawable.find_nearby_icon;
            } else if (str.equals("caomei_live")) {
                i = R.drawable.find_berrie_live;
            } else if (str.equals("activity_center")) {
                i = R.drawable.me_icon_great_activity;
            } else if (str.equals("game_center")) {
                i = R.drawable.me_icon_game;
            } else if (str.equals("snatch")) {
                i = R.drawable.find_one_yuan_snatch;
            } else if (str.equals("finance")) {
                i = R.drawable.find_finance_icon;
            } else if (str.equals("beauty")) {
                i = R.drawable.find_beauty_icon;
            } else if (str.equals("kuainiao")) {
                i = R.drawable.me_icon_kuai_niao;
            } else if (str.equals("remote_download")) {
                i = R.drawable.me_remote_download;
            }
            setItemLeftIconFromRes(i);
        } else if (!i) {
            throw new AssertionError();
        }
    }

    public void onClick() {
        WebViewNormalActivity.a(getContext(), BuildConfig.VERSION_NAME, this.h.j, this.h.b);
        v.a().a(System.currentTimeMillis(), this.g);
        this.m.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }

    public void setRightRedPointVisibility(int i) {
        this.e.setVisibility(i);
    }
}
