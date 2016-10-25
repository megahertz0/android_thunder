package com.inmobi.rendering.mraid;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v4.widget.ExploreByTouchHelper;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.MediaController;
import android.widget.VideoView;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.rendering.RenderView;
import com.umeng.socialize.common.SocializeConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import org.android.spdy.SpdyAgent;

// compiled from: MediaRenderView.java
final class g extends VideoView implements OnCompletionListener, OnErrorListener, OnPreparedListener {
    private static final String k;
    int a;
    boolean b;
    d c;
    b d;
    f e;
    String f;
    String g;
    boolean h;
    int i;
    int j;
    private MediaPlayer l;
    private a m;
    private RenderView n;
    private Bitmap o;
    private ViewGroup p;
    private c q;
    private b r;
    private e s;
    private String t;
    private boolean u;
    private boolean v;

    // compiled from: MediaRenderView.java
    static interface c {
        void a(g gVar);

        void b(g gVar);
    }

    // compiled from: MediaRenderView.java
    static class a extends MediaController {
        Context a;

        public a(Context context) {
            super(context);
            this.a = context;
        }

        public void show(int i) {
            super.show(i);
            if (VERSION.SDK_INT < 19) {
                try {
                    Field declaredField = MediaController.class.getDeclaredField("mAnchor");
                    declaredField.setAccessible(true);
                    View view = (View) declaredField.get(this);
                    Field declaredField2 = MediaController.class.getDeclaredField("mDecor");
                    declaredField2.setAccessible(true);
                    View view2 = (View) declaredField2.get(this);
                    Field declaredField3 = MediaController.class.getDeclaredField("mDecorLayoutParams");
                    declaredField3.setAccessible(true);
                    LayoutParams layoutParams = (LayoutParams) declaredField3.get(this);
                    Field declaredField4 = MediaController.class.getDeclaredField("mWindowManager");
                    declaredField4.setAccessible(true);
                    WindowManager windowManager = (WindowManager) declaredField4.get(this);
                    int[] iArr = new int[2];
                    view.getLocationOnScreen(iArr);
                    view2.measure(MeasureSpec.makeMeasureSpec(view.getWidth(), ExploreByTouchHelper.INVALID_ID), MeasureSpec.makeMeasureSpec(view.getHeight(), ExploreByTouchHelper.INVALID_ID));
                    view2.setPadding(0, 0, 0, 0);
                    layoutParams.verticalMargin = 0.0f;
                    layoutParams.horizontalMargin = 0.0f;
                    layoutParams.width = view.getWidth();
                    layoutParams.gravity = 8388659;
                    layoutParams.x = iArr[0];
                    layoutParams.y = (view.getHeight() + iArr[1]) - view2.getMeasuredHeight();
                    windowManager.updateViewLayout(view2, layoutParams);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // compiled from: MediaRenderView.java
    static final class b extends Handler {
        private final WeakReference<g> a;

        public b(g gVar) {
            this.a = new WeakReference(gVar);
        }

        public final void handleMessage(Message message) {
            g gVar = (g) this.a.get();
            if (gVar != null) {
                switch (message.what) {
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        if (d.b == gVar.c) {
                            int round = Math.round(((float) gVar.getCurrentPosition()) / 1000.0f);
                            int round2 = Math.round(((float) gVar.getDuration()) / 1000.0f);
                            if (gVar.i != round) {
                                gVar.a(round, round2);
                                gVar.i = round;
                                gVar.j = round;
                            }
                            sendEmptyMessageDelayed(1, 1000);
                        } else {
                            return;
                        }
                }
            }
            super.handleMessage(message);
        }
    }

    // compiled from: MediaRenderView.java
    enum d {
        INITIALIZED,
        PLAYING,
        PAUSED,
        HIDDEN,
        SHOWING,
        COMPLETED,
        RELEASED;

        static {
            a = new d("INITIALIZED", 0);
            b = new d("PLAYING", 1);
            c = new d("PAUSED", 2);
            d = new d("HIDDEN", 3);
            e = new d("SHOWING", 4);
            f = new d("COMPLETED", 5);
            g = new d("RELEASED", 6);
            h = new d[]{a, b, c, d, e, f, g};
        }
    }

    // compiled from: MediaRenderView.java
    final class e extends BroadcastReceiver {
        private final String b;

        e() {
            this.b = e.class.getSimpleName();
        }

        public final void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                Logger.a(InternalLogLevel.INTERNAL, this.b, "Screen OFF");
                if (d.b == g.this.c) {
                    g.this.v = true;
                    g.this.pause();
                }
            } else if ("android.intent.action.SCREEN_ON".equals(intent.getAction())) {
                Logger.a(InternalLogLevel.INTERNAL, this.b, "Screen ON");
                if (g.this.v && d.c == g.this.c) {
                    g.this.v = false;
                    g.this.a();
                }
            }
        }
    }

    static {
        k = g.class.getSimpleName();
    }

    public g(Context context, RenderView renderView) {
        super(context);
        this.n = renderView;
        setZOrderOnTop(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setDrawingCacheEnabled(true);
        this.a = 100;
        this.i = -1;
        this.j = 0;
        this.c = d.a;
        this.u = false;
        this.b = false;
        this.v = false;
    }

    protected final void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        Logger.a(InternalLogLevel.INTERNAL, k, new StringBuilder(">>> onWindowVisibilityChanged (").append(i).append(SocializeConstants.OP_CLOSE_PAREN).toString());
    }

    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        getHolder().setSizeFromLayout();
    }

    @TargetApi(16)
    protected final void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        Logger.a(InternalLogLevel.INTERNAL, k, new StringBuilder(">>> onVisibilityChanged (").append(i).append(SocializeConstants.OP_CLOSE_PAREN).toString());
        if (i != 0) {
            return;
        }
        if (VERSION.SDK_INT >= 16) {
            setBackground(new BitmapDrawable(com.inmobi.commons.a.a.b().getResources(), this.o));
        } else {
            setBackgroundDrawable(new BitmapDrawable(this.o));
        }
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        Logger.a(InternalLogLevel.INTERNAL, k, ">>> onCompletion");
        this.c = d.f;
        this.u = true;
        a("ended");
        this.r.removeMessages(1);
        if (this.e.f) {
            synchronized (this) {
                if (!k()) {
                    this.j = 0;
                    start();
                }
            }
        } else if (this.e.b()) {
            a(false);
        }
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        Logger.a(InternalLogLevel.INTERNAL, k, new StringBuilder(">>> onError (").append(i).append(", ").append(i2).append(SocializeConstants.OP_CLOSE_PAREN).toString());
        a(false);
        int i3 = -1;
        if (100 == i) {
            i3 = XZBDevice.DOWNLOAD_LIST_RECYCLE;
        }
        c(i3);
        return false;
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        Logger.a(InternalLogLevel.INTERNAL, k, ">>> onPrepared");
        mediaPlayer.setOnVideoSizeChangedListener(new OnVideoSizeChangedListener() {
            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
                Logger.a(InternalLogLevel.INTERNAL, k, ">>> onVideoSizeChanged");
                if (g.this.m == null && g.this.e.e) {
                    g.this.m = new a(g.this.getContext());
                    g.this.m.setAnchorView(g.this);
                    g.this.setMediaController(g.this.m);
                    g.this.requestLayout();
                    g.this.requestFocus();
                }
            }
        });
        this.l = mediaPlayer;
        a(this.j * 1000);
        this.h = true;
        this.q.b(this);
        g();
    }

    public final void a(String str, String str2, f fVar, b bVar) {
        this.t = str;
        this.r = new b(this);
        this.g = b(str2.trim());
        this.e = fVar;
        this.f = fVar.a;
        this.d = bVar;
        if (this.o == null) {
            this.o = Bitmap.createBitmap(R.styleable.Toolbar_subtitleTextColor, R.styleable.Toolbar_subtitleTextColor, Config.ARGB_8888);
            this.o = c(this.g);
        }
    }

    public final void start() {
        Logger.a(InternalLogLevel.INTERNAL, k, new StringBuilder("Media render view state: ").append(this.c).toString());
        if (d.b != this.c) {
            Logger.a(InternalLogLevel.INTERNAL, k, "Start media playback");
            a(this.j * 1000);
            this.c = d.b;
            super.start();
            if (this.h) {
                a("play");
            }
            this.r.sendEmptyMessage(1);
        }
    }

    public final void pause() {
        Logger.a(InternalLogLevel.INTERNAL, k, new StringBuilder("Media render view state: ").append(this.c).toString());
        if (d.c != this.c) {
            Logger.a(InternalLogLevel.INTERNAL, k, "Pause media playback");
            this.r.removeMessages(1);
            super.pause();
            this.c = d.c;
            a("pause");
        }
    }

    public final void a() {
        setVideoPath(this.g);
        setOnCompletionListener(this);
        setOnPreparedListener(this);
        setOnErrorListener(this);
        if (this.m == null && this.e.e && VERSION.SDK_INT >= 19) {
            this.m = new a(getContext());
            this.m.setAnchorView(this);
            setMediaController(this.m);
        }
        if (this.s == null) {
            this.s = new e();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            com.inmobi.commons.a.a.b().registerReceiver(this.s, intentFilter);
        }
    }

    public final void a(int i) {
        if (i < getDuration()) {
            this.j = i;
            seekTo(i);
        }
    }

    public final void b() {
        if (d.e != this.c) {
            this.p.setVisibility(0);
            setVisibility(0);
            requestFocus();
            this.c = d.e;
            a("showing");
        }
    }

    public final void c() {
        if (d.d != this.c) {
            setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
            this.p.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
            this.c = d.d;
            a("hidden");
        }
    }

    public final void d() {
        if (this.l != null && !this.b) {
            this.b = true;
            this.l.setVolume(AutoScrollHelper.RELATIVE_UNSPECIFIED, AutoScrollHelper.RELATIVE_UNSPECIFIED);
            h();
        }
    }

    public final void e() {
        if (this.l != null && this.b) {
            b(this.a);
        }
    }

    public final void b(int i) {
        boolean z = false;
        if (this.l == null) {
            return;
        }
        if (!this.b && i == this.a) {
            return;
        }
        if (this.b && i == 0) {
            this.a = 0;
            return;
        }
        if (i == 0) {
            z = true;
        }
        this.b = z;
        this.a = i;
        float log = 1.0f - ((float) (Math.log((double) (101 - i)) / Math.log(101.0d)));
        this.l.setVolume(log, log);
        h();
    }

    public final void a(boolean z) {
        Logger.a(InternalLogLevel.INTERNAL, k, new StringBuilder("Media render view state: ").append(this.c).toString());
        Logger.a(InternalLogLevel.INTERNAL, k, "Release the media render view");
        if (this.s != null) {
            com.inmobi.commons.a.a.b().unregisterReceiver(this.s);
            this.s = null;
        }
        this.c = d.g;
        a(z, this.i != -1 ? this.i : Math.round((float) (getCurrentPosition() / 1000)));
        stopPlayback();
        this.r.removeMessages(1);
        j();
        super.setMediaController(null);
        this.m = null;
        if (this.q != null) {
            this.q.a(this);
        }
    }

    public final ViewGroup f() {
        return this.p;
    }

    public final void a(ViewGroup viewGroup) {
        this.p = viewGroup;
    }

    public final void a(c cVar) {
        this.q = cVar;
    }

    final void g() {
        if (d.e == this.c) {
            this.c = this.u ? d.f : d.c;
            if (!this.h) {
                return;
            }
            if (VERSION.SDK_INT < 21) {
                super.start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                super.pause();
                return;
            }
            super.start();
            super.pause();
        } else if (d.a == this.c) {
            if (this.e.g) {
                d();
            }
            if (this.e.d) {
                start();
            } else if (!this.h) {
            } else {
                if (VERSION.SDK_INT < 21) {
                    super.start();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e2) {
                    }
                    super.pause();
                    return;
                }
                super.start();
                super.pause();
            }
        }
    }

    private void j() {
        if (this.p != null) {
            ViewGroup viewGroup = (ViewGroup) this.p.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.p);
            }
            viewGroup = (ViewGroup) getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this);
            }
            setBackgroundColor(0);
            this.p = null;
        }
    }

    private boolean k() {
        return d.c == this.c || d.d == this.c;
    }

    final void a(boolean z, int i) {
        if (this.n != null) {
            this.n.a(this.t, new StringBuilder("fireMediaCloseEvent('").append(this.e.a).append("',").append(z).append(MiPushClient.ACCEPT_TIME_SEPARATOR).append(i).append(");").toString());
        }
    }

    final void a(String str) {
        if (this.n != null) {
            this.n.a(this.t, new StringBuilder("fireMediaTrackingEvent('").append(str).append("','").append(this.e.a).append("');").toString());
        }
    }

    final void h() {
        int i = this.b ? 0 : this.a;
        if (this.n != null) {
            this.n.a(this.t, new StringBuilder("fireMediaVolumeChangeEvent('").append(this.e.a).append("',").append(i).append(MiPushClient.ACCEPT_TIME_SEPARATOR).append(this.b).append(");").toString());
        }
    }

    final void c(int i) {
        if (this.n != null) {
            this.n.a(this.t, new StringBuilder("fireMediaErrorEvent('").append(this.e.a).append("',").append(i).append(");").toString());
        }
    }

    final void a(int i, int i2) {
        if (this.n != null) {
            this.n.a(this.t, new StringBuilder("fireMediaTimeUpdateEvent('").append(this.e.a).append("',").append(i).append(MiPushClient.ACCEPT_TIME_SEPARATOR).append(i2).append(");").toString());
        }
    }

    private String b(String str) {
        String str2 = com.umeng.a.d;
        byte[] bytes = str.getBytes();
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            if ((b & 128) > 0) {
                stringBuilder.append("%").append(a(b));
            } else {
                stringBuilder.append((char) b);
            }
        }
        try {
            return new String(stringBuilder.toString().getBytes(), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str2;
        }
    }

    private Bitmap c(String str) {
        try {
            return (Bitmap) Class.forName("android.media.ThumbnailUtils").getDeclaredMethod("createVideoThumbnail", new Class[]{String.class, Integer.TYPE}).invoke(null, new Object[]{str, Integer.valueOf(1)});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            return null;
        } catch (IllegalAccessException e4) {
            e4.printStackTrace();
            return null;
        }
    }

    private String a(byte b) {
        char[] cArr = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        return new String(new char[]{cArr[(b >> 4) & 15], cArr[b & 15]});
    }
}
