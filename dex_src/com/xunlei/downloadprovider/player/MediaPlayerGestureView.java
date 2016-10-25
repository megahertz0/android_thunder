package com.xunlei.downloadprovider.player;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.xunlei.common.yunbo.XLYunboVodStatus;
import com.xunlei.downloadprovider.R;
import com.xunlei.xllib.a.c;
import com.xunlei.xllib.b.j;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class MediaPlayerGestureView extends FrameLayout implements t {
    private ObjectAnimator A;
    private OnGestureListener B;
    ab a;
    private GestureDetector b;
    private b c;
    private float d;
    private float e;
    private int f;
    private int g;
    private int h;
    private float i;
    private View j;
    private ImageView k;
    private ProgressBar l;
    private TextView m;
    private int n;
    private int o;
    private AudioManager p;
    private View q;
    private ImageView r;
    private ProgressBar s;
    private c t;
    private int u;
    private View v;
    private ProgressBar w;
    private boolean x;
    private a y;
    private ObjectAnimator z;

    public static interface a {
        void onClick();
    }

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;

        static {
            b = new int[MediaPlayerState.values().length];
            try {
                b[MediaPlayerState.PREPARED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                b[MediaPlayerState.IDLE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            a = new int[b.values().length];
            try {
                a[b.a.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[b.b.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[b.c.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[b.d.ordinal()] = 4;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    enum b {
        IDLE,
        POSITION,
        VOLUME,
        LIGHT;

        static {
            a = new b("IDLE", 0);
            b = new b("POSITION", 1);
            c = new b("VOLUME", 2);
            d = new b("LIGHT", 3);
            e = new b[]{a, b, c, d};
        }
    }

    static /* synthetic */ boolean a(MediaPlayerGestureView mediaPlayerGestureView, float f, float f2) {
        DisplayMetrics displayMetrics = mediaPlayerGestureView.getResources().getDisplayMetrics();
        return f >= 50.0f && f <= ((float) (displayMetrics.widthPixels - 50)) && f2 >= 50.0f && f2 <= ((float) (displayMetrics.heightPixels - 50));
    }

    public MediaPlayerGestureView(Context context) {
        super(context);
        this.c = b.a;
        this.d = -1.0f;
        this.e = -1.0f;
        this.B = new o(this);
        b(context);
        a(context);
    }

    public MediaPlayerGestureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = b.a;
        this.d = -1.0f;
        this.e = -1.0f;
        this.B = new o(this);
        b(context);
        a(context);
    }

    public MediaPlayerGestureView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = b.a;
        this.d = -1.0f;
        this.e = -1.0f;
        this.B = new o(this);
        b(context);
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.thunder_media_player_gesture_view, this, true);
        this.j = findViewById(R.id.position_layout);
        this.m = (TextView) findViewById(R.id.position_view);
        this.k = (ImageView) findViewById(R.id.position_icon);
        this.l = (ProgressBar) findViewById(R.id.position_progress);
        this.q = findViewById(R.id.volume_layout);
        this.r = (ImageView) findViewById(R.id.volume_icon);
        this.s = (ProgressBar) findViewById(R.id.volume_progress);
        this.s.setMax(this.n);
        this.v = findViewById(R.id.light_layout);
        this.w = (ProgressBar) findViewById(R.id.light_progress);
        this.w.setMax(XLYunboVodStatus.ALL);
        this.b = new GestureDetector(context, this.B);
        this.b.setIsLongpressEnabled(false);
    }

    private void b(Context context) {
        this.p = (AudioManager) context.getSystemService("audio");
        this.n = this.p.getStreamMaxVolume(MqttConnectOptions.MQTT_VERSION_3_1);
        this.t = c.a(context);
        if (this.t != null) {
            this.u = this.t.a();
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if ((motionEvent.getAction() & 255) == 1) {
            boolean z = true;
        } else {
            Object obj = null;
        }
        if (!this.b.onTouchEvent(motionEvent) && r0) {
            new StringBuilder("onTouchUp--mState=").append(this.c);
            new StringBuilder("animationToResetState--mState=").append(this.c);
            switch (AnonymousClass_1.a[this.c.ordinal()]) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    if (this.y == null) {
                        MediaPlayerControllerView mediaPlayerControllerView = this.a.o;
                        if (!mediaPlayerControllerView.b) {
                            mediaPlayerControllerView.a();
                            if (mediaPlayerControllerView.k && mediaPlayerControllerView.a.t()) {
                                mediaPlayerControllerView.b();
                            }
                        } else if (mediaPlayerControllerView.k) {
                            mediaPlayerControllerView.c();
                        }
                    } else {
                        this.y.onClick();
                    }
                    break;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    if (this.f > 0) {
                        b(this.j);
                        this.a.a(this.h);
                    }
                    break;
                case MqttConnectOptions.MQTT_VERSION_3_1:
                    b(this.q);
                    this.o = this.p.getStreamVolume(MqttConnectOptions.MQTT_VERSION_3_1);
                    break;
                case MqttConnectOptions.MQTT_VERSION_3_1_1:
                    b(this.v);
                    if (this.t != null) {
                        this.u = this.t.a();
                    }
                    break;
            }
            this.c = b.a;
        }
        return true;
    }

    public void setMediaDuration(int i) {
        this.f = i;
        this.i = ((float) i) / ((float) getWidth());
        this.l.setMax(i);
    }

    private void setTouchDownPosition(int i) {
        this.g = i;
    }

    public void setShouldDetectorGestureMove(boolean z) {
        this.x = z;
    }

    private void a(int i) {
        if (i < 0) {
            this.k.setImageResource(R.drawable.player_gesture_go_back_icon);
        } else {
            this.k.setImageResource(R.drawable.player_gesture_go_forward_icon);
        }
    }

    private void b(int i) {
        if (i == 0) {
            this.r.setImageResource(R.drawable.player_gesture_silent_icon);
        } else {
            this.r.setImageResource(R.drawable.player_gesture_volume_icon);
        }
    }

    public final void a(MediaPlayerState mediaPlayerState, MediaPlayerState mediaPlayerState2) {
        switch (AnonymousClass_1.b[mediaPlayerState2.ordinal()]) {
            case SimpleLog.LOG_LEVEL_DEBUG:
                a();
            default:
                break;
        }
    }

    private void a(View view) {
        new StringBuilder("showStateLayout--layout=").append(view);
        if (this.z != null) {
            this.z.cancel();
            this.z = null;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{1.0f});
        ofFloat.setDuration(200);
        view.setVisibility(0);
        ofFloat.start();
        this.A = ofFloat;
    }

    private void b(View view) {
        new StringBuilder("startHideAnimation--layout=").append(view);
        if (this.A != null) {
            this.A.cancel();
            this.A = null;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, new float[]{0.0f});
        ofFloat.setDuration(500);
        ofFloat.addListener(new p(this, view));
        ofFloat.start();
        this.z = ofFloat;
    }

    public final void a() {
        this.c = b.a;
        this.v.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.q.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.j.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }

    public void setClickToDetailListener(a aVar) {
        this.y = aVar;
    }

    static /* synthetic */ void b(MediaPlayerGestureView mediaPlayerGestureView, int i) {
        mediaPlayerGestureView.c = b.b;
        if (mediaPlayerGestureView.f > 0) {
            mediaPlayerGestureView.a.o.c();
            mediaPlayerGestureView.a(i);
            mediaPlayerGestureView.j.setAlpha(1.0f);
            mediaPlayerGestureView.a(mediaPlayerGestureView.j);
        }
    }

    static /* synthetic */ void f(MediaPlayerGestureView mediaPlayerGestureView) {
        mediaPlayerGestureView.a.o.c();
        mediaPlayerGestureView.c = b.c;
        mediaPlayerGestureView.b(mediaPlayerGestureView.o);
        mediaPlayerGestureView.q.setAlpha(1.0f);
        mediaPlayerGestureView.a(mediaPlayerGestureView.q);
    }

    static /* synthetic */ void g(MediaPlayerGestureView mediaPlayerGestureView) {
        mediaPlayerGestureView.a.o.c();
        mediaPlayerGestureView.c = b.d;
        mediaPlayerGestureView.v.setAlpha(1.0f);
        mediaPlayerGestureView.a(mediaPlayerGestureView.v);
    }

    static /* synthetic */ void a(MediaPlayerGestureView mediaPlayerGestureView, int i, int i2) {
        String b = j.b((long) i);
        Object obj = b + " / " + j.b((long) i2);
        CharSequence spannableStringBuilder = new SpannableStringBuilder(obj);
        int indexOf = obj.indexOf(b);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#1294f6")), indexOf, b.length() + indexOf, com.xunlei.xllib.R.styleable.AppCompatTheme_actionModePasteDrawable);
        mediaPlayerGestureView.m.setText(spannableStringBuilder);
    }
}
