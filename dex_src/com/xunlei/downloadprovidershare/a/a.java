package com.xunlei.downloadprovidershare.a;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import com.xunlei.downloadprovidershare.R;

// compiled from: SharePlatformsDialog.java
public final class a extends AlertDialog implements OnClickListener {
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

    // compiled from: SharePlatformsDialog.java
    public static interface a {
        void a();

        void b();

        void c();

        void d();

        void e();

        void f();

        void g();

        void h();

        void i();

        void j();

        void k();
    }

    public a(Context context) {
        super(context);
    }

    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCanceledOnTouchOutside(true);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.share_layout, null);
        this.b = inflate.findViewById(R.id.share_weibo_btn);
        this.b.setOnClickListener(this);
        this.c = inflate.findViewById(R.id.share_friends_circle_btn);
        this.c.setOnClickListener(this);
        this.f = inflate.findViewById(R.id.share_qq_btn);
        this.f.setOnClickListener(this);
        this.e = inflate.findViewById(R.id.share_qzone_btn);
        this.e.setOnClickListener(this);
        this.d = inflate.findViewById(R.id.share_weixin_btn);
        this.d.setOnClickListener(this);
        this.g = inflate.findViewById(R.id.share_copy_url);
        this.g.setOnClickListener(this);
        this.h = inflate.findViewById(R.id.share_system_share);
        this.h.setOnClickListener(this);
        this.i = inflate.findViewById(R.id.share_qr);
        this.i.setOnClickListener(this);
        this.j = inflate.findViewById(R.id.share_upload);
        this.j.setOnClickListener(this);
        this.k = inflate.findViewById(R.id.share_download);
        this.k.setOnClickListener(this);
        this.l = inflate.findViewById(R.id.share_accuse);
        this.l.setOnClickListener(this);
        inflate.findViewById(R.id.cancel).setOnClickListener(this);
        setContentView(inflate);
        Window window = getWindow();
        window.setGravity(com.xunlei.tdlive.R.styleable.AppCompatTheme_panelMenuListTheme);
        window.setWindowAnimations(R.style.bottom_dialog_animation);
        window.setBackgroundDrawable(new ColorDrawable(-1342177280));
        LayoutParams attributes = getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        window.setAttributes(attributes);
    }

    public final void show() {
        super.show();
    }

    public final void onClick(View view) {
        if (this.a != null) {
            dismiss();
            int id = view.getId();
            if (id == R.id.cancel) {
                return;
            }
            if (id == R.id.share_weibo_btn) {
                this.a.b();
            } else if (id == R.id.share_friends_circle_btn) {
                this.a.e();
            } else if (id == R.id.share_qq_btn) {
                this.a.d();
            } else if (id == R.id.share_qzone_btn) {
                this.a.c();
            } else if (id == R.id.share_weixin_btn) {
                this.a.a();
            } else if (id == R.id.share_copy_url) {
                this.a.f();
            } else if (id == R.id.share_system_share) {
                this.a.g();
            } else if (id == R.id.share_qr) {
                this.a.h();
            } else if (id == R.id.share_upload) {
                this.a.i();
            } else if (id == R.id.share_download) {
                this.a.j();
            } else if (id == R.id.share_accuse) {
                this.a.k();
            }
        }
    }

    public final void a(int i) {
        this.i.setVisibility(i);
    }

    public final void b(int i) {
        this.k.setVisibility(i);
    }

    public final void c(int i) {
        this.j.setVisibility(i);
    }

    public final void d(int i) {
        this.l.setVisibility(i);
    }
}
