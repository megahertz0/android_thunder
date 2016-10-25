package com.xunlei.downloadprovidershare.a;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import com.xunlei.downloadprovidershare.R;

// compiled from: SharePlatformsForPlayerDialog.java
public final class b extends AlertDialog implements OnClickListener {
    public a a;
    private View b;
    private View c;
    private View d;
    private View e;
    private View f;
    private View g;
    private View h;
    private View i;
    private View j;
    private View k;
    private View l;
    private View m;
    private View n;
    private View o;

    // compiled from: SharePlatformsForPlayerDialog.java
    public static interface a {
        void a();

        void b();

        void c();

        void d();

        void e();

        void f();

        void g();
    }

    public b(Context context) {
        super(context);
    }

    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.share_for_player, null);
        this.b = inflate.findViewById(R.id.container);
        this.b.setOnClickListener(new c(this));
        this.c = inflate.findViewById(R.id.share_weibo_btn_for_player);
        this.c.setOnClickListener(this);
        this.d = inflate.findViewById(R.id.share_friends_circle_btn_for_player);
        this.d.setOnClickListener(this);
        this.g = inflate.findViewById(R.id.share_qq_btn_for_player);
        this.g.setOnClickListener(this);
        this.f = inflate.findViewById(R.id.share_qzone_btn_for_player);
        this.f.setOnClickListener(this);
        this.e = inflate.findViewById(R.id.share_weixin_btn_for_player);
        this.e.setOnClickListener(this);
        this.h = inflate.findViewById(R.id.share_copy_url_for_player);
        this.h.setOnClickListener(this);
        this.i = inflate.findViewById(R.id.share_system_share_for_player);
        this.i.setOnClickListener(this);
        this.j = inflate.findViewById(R.id.share_qr);
        this.j.setOnClickListener(this);
        this.k = inflate.findViewById(R.id.share_download);
        this.k.setOnClickListener(this);
        this.l = inflate.findViewById(R.id.share_accuse);
        this.l.setOnClickListener(this);
        this.n = inflate.findViewById(R.id.share_more_share_btn_container);
        this.o = inflate.findViewById(R.id.share_player_land_more_btn_contaner);
        this.m = inflate.findViewById(R.id.share_more_for_player);
        this.m.setOnClickListener(new d(this));
        setContentView(inflate);
        getWindow().setGravity(com.xunlei.tdlive.R.styleable.Toolbar_maxButtonHeight);
    }

    public final void show() {
        super.show();
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        getWindow().setAttributes(attributes);
    }

    public final void onClick(View view) {
        if (this.a != null) {
            dismiss();
            int id = view.getId();
            if (id == R.id.cancel) {
                return;
            }
            if (id == R.id.share_weibo_btn_for_player) {
                this.a.b();
            } else if (id == R.id.share_friends_circle_btn_for_player) {
                this.a.e();
            } else if (id == R.id.share_qq_btn_for_player) {
                this.a.d();
            } else if (id == R.id.share_qzone_btn_for_player) {
                this.a.c();
            } else if (id == R.id.share_weixin_btn_for_player) {
                this.a.a();
            } else if (id == R.id.share_copy_url_for_player) {
                this.a.f();
            } else if (id == R.id.share_system_share_for_player) {
                this.a.g();
            }
        }
    }
}
