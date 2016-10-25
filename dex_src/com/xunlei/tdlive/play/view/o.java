package com.xunlei.tdlive.play.view;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.play.a.aa;
import com.xunlei.tdlive.play.a.aa.b;
import com.xunlei.tdlive.play.a.ar.a;
import com.xunlei.tdlive.util.ac;
import com.xunlei.tdlive.view.AnimationSurfaceView;
import com.xunlei.tdlive.view.GiftReminderView;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: LiveReplayDialog.java
public class o extends a {
    private NormalScreenLayout a;
    private FullScreenLayout b;
    private View c;
    private View d;
    private View e;
    private View f;
    private TextView g;
    private aa h;
    private BroadcastReceiver i;

    public o(Context context, boolean z, String str, b bVar, a aVar) {
        super(context, R.style.TransparentDialogStyle);
        this.i = new p(this);
        this.h = new aa(this, context, str, bVar, aVar);
    }

    protected void onStart() {
        super.onStart();
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            getContext().getApplicationContext().registerReceiver(this.i, intentFilter);
        } catch (Exception e) {
        }
    }

    protected void onStop() {
        super.onStop();
        try {
            getContext().getApplicationContext().unregisterReceiver(this.i);
        } catch (Exception e) {
        }
    }

    protected void a(Bundle bundle) {
        setContentView(R.layout.xllive_dialog_player);
        a((Dialog) this);
        this.c = findViewById(R.id.root);
        this.e = findViewById(R.id.close_btn);
        this.d = findViewById(R.id.movable_layout);
        this.b = (FullScreenLayout) findViewById(R.id.full_screen_layout);
        this.a = (NormalScreenLayout) findViewById(R.id.normal_screen_layout);
        this.g = (TextView) findViewById(R.id.network_tip);
        this.f = findViewById(R.id.network_tip_layout);
        this.f.setVisibility(ac.a() ? 0 : XZBDevice.Wait);
    }

    public boolean b() {
        return this.f.getVisibility() == 0;
    }

    public static void a(Dialog dialog) {
        if (dialog != null) {
            LayoutParams attributes = dialog.getWindow().getAttributes();
            attributes.x = 0;
            attributes.y = 0;
            attributes.width = -1;
            attributes.height = -1;
            dialog.getWindow().setAttributes(attributes);
        }
    }

    public boolean c() {
        return this.a.mChatEditLayout.getVisibility() != 8;
    }

    public View d() {
        return this.d;
    }

    public FullScreenLayout e() {
        return this.b;
    }

    public View f() {
        return this.c;
    }

    public View g() {
        return this.e;
    }

    public NormalScreenLayout h() {
        return this.a;
    }

    protected com.xunlei.tdlive.play.a.b a() {
        return this.h;
    }

    public GiftReminderView k() {
        return this.a.mGiftReminderView;
    }

    public AnimationSurfaceView l() {
        return this.a.mAnimationView;
    }

    public ChatListView m() {
        return this.a.mMessagesView;
    }
}
