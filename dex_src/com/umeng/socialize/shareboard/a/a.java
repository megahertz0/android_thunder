package com.umeng.socialize.shareboard.a;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.ResContainer;
import com.umeng.socialize.shareboard.SnsPlatform;
import java.util.ArrayList;
import java.util.List;
import org.android.agoo.common.AgooConstants;

// compiled from: SNSPlatformAdapter.java
public class a extends com.umeng.socialize.shareboard.b.a {
    private List<SnsPlatform> a;
    private Context b;
    private com.umeng.socialize.shareboard.a c;

    public a(Context context, List<SnsPlatform> list, com.umeng.socialize.shareboard.a aVar) {
        this.a = new ArrayList();
        this.a = list;
        this.b = context;
        this.c = aVar;
    }

    private void a(View view, SnsPlatform snsPlatform) {
        ((ImageView) view.findViewById(ResContainer.getResourceId(this.b, AgooConstants.MESSAGE_ID, "umeng_socialize_shareboard_image"))).setImageResource(ResContainer.getResourceId(this.b, "drawable", snsPlatform.mIcon));
        ((TextView) view.findViewById(ResContainer.getResourceId(this.b, AgooConstants.MESSAGE_ID, "umeng_socialize_shareboard_pltform_name"))).setText(ResContainer.getString(this.b, snsPlatform.mShowWord));
    }

    private void a(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
        if (snsPlatform != null && this.c.a() != null) {
            this.c.a().onclick(snsPlatform, share_media);
        }
    }

    public Object a(int i) {
        return this.a == null ? null : (SnsPlatform) this.a.get(i);
    }

    public int a() {
        return this.a == null ? 0 : this.a.size();
    }

    public View a(int i, ViewGroup viewGroup) {
        SnsPlatform snsPlatform = (SnsPlatform) this.a.get(i);
        View inflate = View.inflate(this.b, ResContainer.getResourceId(this.b, "layout", "umeng_socialize_shareboard_item"), null);
        a(inflate, snsPlatform);
        inflate.setOnClickListener(new b(this, snsPlatform));
        inflate.setOnTouchListener(new c(this, inflate));
        inflate.setFocusable(true);
        return inflate;
    }
}
