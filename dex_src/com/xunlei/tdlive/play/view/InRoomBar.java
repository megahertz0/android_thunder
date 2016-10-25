package com.xunlei.tdlive.play.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.common.register.XLRegErrorCode;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.im.ChatMessage;
import com.xunlei.tdlive.im.InRoomMessage;
import com.xunlei.tdlive.modal.e;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.r;
import java.util.ArrayDeque;
import java.util.Queue;
import org.android.spdy.SpdyProtocol;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class InRoomBar extends FrameLayout implements Runnable {
    private boolean a;
    private TextView b;
    private TextView c;
    private ImageView d;
    private Queue<a> e;
    private r f;
    private View g;
    private View h;

    private static final class a {
        InRoomMessage a;
        int b;

        private a() {
        }

        public final int a() {
            this.b -= 500;
            return this.b;
        }

        public final boolean b() {
            if (TextUtils.isEmpty(this.a.userid)) {
                return true;
            }
            int codePointCount = this.a.userid.codePointCount(0, this.a.userid.length());
            for (int i = 0; i < codePointCount; i++) {
                if (!Character.isDigit(this.a.userid.codePointAt(i))) {
                    return true;
                }
            }
            return false;
        }

        public final boolean c() {
            return this.a.level.current >= e.s;
        }
    }

    public InRoomBar(Context context) {
        super(context);
        this.a = true;
        a();
    }

    public InRoomBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = true;
        a();
    }

    public InRoomBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = true;
        a();
    }

    @TargetApi(21)
    public InRoomBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.a = true;
        a();
    }

    private void a() {
        inflate(getContext(), R.layout.xllive_in_roombar, this);
        this.h = findViewById(R.id.bkgRoot);
        this.e = new ArrayDeque();
        this.b = (TextView) findViewById(R.id.nickname);
        this.c = (TextView) findViewById(R.id.inroomdesc);
        this.d = (ImageView) findViewById(R.id.userLevel);
        this.g = findViewById(R.id.topLine);
    }

    public void run() {
        if (this.a) {
            new StringBuilder("size =  ").append(this.e.size());
        }
        if (!b()) {
            while (this.e.size() >= 11 && ((a) this.e.peek()).b <= 0) {
                this.e.poll();
            }
            a aVar = (a) this.e.peek();
            if (aVar != null && aVar.a() <= 0) {
                this.e.poll();
                if (!b()) {
                    a((a) this.e.peek(), false);
                }
            }
            if (this.a && aVar != null) {
                new StringBuilder("userid = ").append(aVar.a.userid).append(", delay =  ").append(aVar.b);
            }
        }
    }

    private boolean b() {
        if (!this.e.isEmpty()) {
            return false;
        }
        d();
        return true;
    }

    public void addMessage(InRoomMessage inRoomMessage) {
        if (this.a) {
            new StringBuilder("add msg: ").append(inRoomMessage.userid);
        }
        if (inRoomMessage.level.current >= e.r || f.a(getContext()).b(inRoomMessage.userid)) {
            if (this.f == null) {
                this.f = new r(500, this);
                this.f.b();
            }
            a aVar = new a();
            aVar.a = inRoomMessage;
            aVar.b = a(aVar, 0);
            if (this.e.isEmpty()) {
                a(aVar, true);
            }
            this.e.add(aVar);
            c();
        }
    }

    private void c() {
        int size = this.e.size();
        int i = 1;
        for (a aVar : this.e) {
            Object obj;
            if (obj != null) {
                obj = null;
            } else {
                aVar.b = a(aVar, size);
            }
        }
    }

    private int a(a aVar, int i) {
        if (i < 11) {
            return i >= 3 ? !aVar.c() ? ChatMessage.FLAG_SYS_NOTIFY : XiaomiOAuthConstants.SCOPE_MI_CLOUD_CONTACT : aVar.c() ? 3000 : XiaomiOAuthConstants.SCOPE_MI_CLOUD_CONTACT;
        } else {
            if (aVar.c()) {
                return XiaomiOAuthConstants.SCOPE_MI_CLOUD_CONTACT;
            }
            return aVar.b() ? -1 : XLRegErrorCode.REG_API_ERROR;
        }
    }

    private void d() {
        setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
        if (this.f != null) {
            this.f.c();
            this.f = null;
        }
    }

    private void a(a aVar, boolean z) {
        InRoomMessage inRoomMessage = aVar.a;
        setVisibility(0);
        if (inRoomMessage.level != null) {
            com.xunlei.tdlive.util.a.a(getContext()).a(this.d, inRoomMessage.level.getIconFullPath());
        } else {
            this.d.setImageBitmap(null);
        }
        this.b.setText(inRoomMessage.nickname);
        if (aVar.c()) {
            this.g.setVisibility(0);
            this.h.setBackgroundResource(R.drawable.xllive_in_room_bkg);
            this.b.setTextColor(Color.parseColor("#00eefd"));
            this.c.setText("\u95ea\u4eae\u767b\u573a");
        } else {
            this.g.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.h.setBackgroundResource(R.drawable.xllive_in_room_bkg_normal);
            this.b.setTextColor(Color.parseColor("#ffffff"));
            this.c.setText("\u8fdb\u573a");
        }
        if (z) {
            Animation a = a(-1.0f, 0.0f);
            a.setDuration(500);
            startAnimation(a);
        }
    }

    private TranslateAnimation a(float f, float f2) {
        return new TranslateAnimation(1, f, 1, f2, 1, 0.0f, 1, 0.0f);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f != null) {
            this.f.c();
        }
    }
}
