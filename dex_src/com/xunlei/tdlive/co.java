package com.xunlei.tdlive;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import cn.nodemedia.LivePublisher;
import com.tencent.open.SocialConstants;
import com.uc.addon.sdk.remote.TabsImpl;
import com.umeng.a;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xunlei.tdlive.base.h;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.ac;
import com.xunlei.tdlive.util.q;
import com.xunlei.tdlive.util.y;
import java.util.HashMap;
import java.util.Map;

// compiled from: LivePublishDialog.java
public class co extends h implements OnClickListener {
    b a;
    EditText b;
    InputMethodManager c;
    a d;
    View e;
    private View f;
    private View g;
    private View h;
    private View i;
    private View j;
    private View k;
    private View l;
    private String m;
    private boolean n;

    // compiled from: LivePublishDialog.java
    public static interface b {
        void a();

        void a(String str, String str2);
    }

    public co(Context context) {
        super(context, R.style.TransparentDialogStyle);
        this.m = a.d;
        this.c = (InputMethodManager) context.getSystemService("input_method");
    }

    public void a(b bVar) {
        this.a = bVar;
    }

    public void a(String str, boolean z) {
        TextView textView = (TextView) findViewById(R.id.start_plublish);
        textView.setText(str);
        textView.setEnabled(z);
    }

    protected void onCreate(Bundle bundle) {
        boolean z;
        super.onCreate(bundle);
        setContentView(R.layout.xllive_dialog_publish);
        Map hashMap = new HashMap();
        hashMap.put("network", String.valueOf(ac.b()));
        q.a("live_prepare", null, null, hashMap);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.x = 0;
        attributes.y = 0;
        attributes.width = -1;
        attributes.height = -1;
        getWindow().setAttributes(attributes);
        getWindow().getDecorView().setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        this.e = findViewById(R.id.root);
        this.b = (EditText) findViewById(R.id.publish_title);
        this.b.setOnEditorActionListener(new cp(this));
        this.b.addTextChangedListener(new cq(this));
        this.b.setOnFocusChangeListener(new cr(this));
        findViewById(R.id.root).setOnClickListener(this);
        findViewById(R.id.cancel_plublish).setOnClickListener(this);
        findViewById(R.id.start_plublish).setOnClickListener(this);
        this.f = findViewById(R.id.xllive_share_wx);
        this.h = findViewById(R.id.xllive_share_wb);
        this.g = findViewById(R.id.xllive_share_tl);
        this.i = findViewById(R.id.xllive_share_qz);
        this.j = findViewById(R.id.xllive_share_qq);
        this.k = findViewById(R.id.xllive_camera_switch);
        this.l = findViewById(R.id.xllive_start_publish_filter_beauty);
        this.f.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.k.setOnClickListener(this);
        this.l.setOnClickListener(this);
        View view = this.l;
        if (LivePublisher.getFilterState() == 1) {
            z = true;
        } else {
            z = false;
        }
        view.setSelected(z);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    public void onBackPressed() {
        this.a.a();
        dismiss();
    }

    public void dismiss() {
        super.dismiss();
        if (this.d != null) {
            this.d.dismiss();
            this.d = null;
        }
    }

    public void onClick(View view) {
        boolean z = true;
        int id = view.getId();
        if (id == R.id.root) {
            this.c.hideSoftInputFromWindow(this.b.getWindowToken(), 0);
        } else if (id == R.id.xllive_camera_switch) {
            a(this.k);
        } else if (id == R.id.xllive_start_publish_filter_beauty) {
            boolean z2;
            if (this.l.isSelected()) {
                z2 = false;
            } else {
                z2 = true;
            }
            LivePublisher.setFilterEnable(z2);
            View view2 = this.l;
            if (LivePublisher.getFilterState() != 1) {
                z = false;
            }
            view2.setSelected(z);
        } else if (id == R.id.cancel_plublish) {
            onBackPressed();
        } else if (id == R.id.start_plublish) {
            this.a.a(this.b.getText().toString(), this.m);
        } else if (id == R.id.xllive_share_wb) {
            a(this.h, SHARE_MEDIA.SINA);
        } else if (id == R.id.xllive_share_wx) {
            a(this.f, SHARE_MEDIA.WEIXIN);
        } else if (id == R.id.xllive_share_tl) {
            a(this.g, SHARE_MEDIA.WEIXIN_CIRCLE);
        } else if (id == R.id.xllive_share_qz) {
            a(this.i, SHARE_MEDIA.QZONE);
        } else if (id == R.id.xllive_share_qq) {
            a(this.j, SHARE_MEDIA.QQ);
        }
    }

    public void a(View view) {
        if (!this.n) {
            this.n = true;
            b(view);
            LivePublisher.switchCamera();
        }
    }

    private void b(View view) {
        Animation rotateAnimation = new RotateAnimation(0.0f, 180.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setDuration(300);
        rotateAnimation.setFillAfter(false);
        rotateAnimation.setAnimationListener(new cs(this));
        view.startAnimation(rotateAnimation);
    }

    private void a(View view, SHARE_MEDIA share_media) {
        Context ownerActivity = getOwnerActivity();
        ct ctVar = new ct(this, share_media, ownerActivity, view);
        String k = f.a(ownerActivity).k();
        String m = f.a(ownerActivity).m();
        String o = f.a(ownerActivity).o();
        String a = y.a(k);
        y.a(getOwnerActivity(), share_media, m, o, a, ctVar);
        q.e("zb_share").a("livestart").a("hostid", k).a(SocialConstants.PARAM_URL, a);
    }

    private void a(Activity activity, View view, String str) {
        com.xunlei.tdlive.play.view.ac acVar = new com.xunlei.tdlive.play.view.ac(activity);
        com.xunlei.tdlive.play.view.b.a aVar = new com.xunlei.tdlive.play.view.b.a();
        aVar.a(view);
        aVar.a(str);
        acVar.a(aVar);
        acVar.b();
        acVar.a(TabsImpl.SYNC_TIME_OUT);
    }

    public void a() {
        if (this.d == null) {
            this.d = new a(this, getOwnerActivity());
            this.d.setOwnerActivity(getOwnerActivity());
        }
        this.d.show();
        show();
    }
}
