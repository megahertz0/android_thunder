package com.inmobi.rendering.mraid;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.os.Handler;
import android.provider.Settings.System;
import android.support.v4.view.ViewCompat;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.URLUtil;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import anet.channel.util.HttpConstant;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.rendering.RenderView;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Hashtable;
import java.util.Iterator;
import org.android.spdy.SpdyAgent;

@SuppressLint({"ClickableViewAccessibility"})
public final class MraidMediaProcessor {
    private static final String a;
    private RenderView b;
    private g c;
    private RingerModeChangeReceiver d;
    private a e;
    private HeadphonesPluggedChangeReceiver f;
    private f g;
    private b h;
    private boolean i;
    private Hashtable<String, g> j;

    /* synthetic */ class AnonymousClass_4 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[d.values().length];
            try {
                a[d.c.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[d.a.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[d.f.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[d.b.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public final class HeadphonesPluggedChangeReceiver extends BroadcastReceiver {
        private String b;

        public HeadphonesPluggedChangeReceiver(String str) {
            this.b = str;
        }

        public final void onReceive(Context context, Intent intent) {
            boolean z = true;
            if (intent != null && "android.intent.action.HEADSET_PLUG".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("state", 0);
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Headphone plugged state changed: ").append(intExtra).toString());
                MraidMediaProcessor mraidMediaProcessor = MraidMediaProcessor.this;
                String str = this.b;
                if (1 != intExtra) {
                    z = false;
                }
                mraidMediaProcessor.b(str, z);
            }
        }
    }

    public enum MediaContentType {
        MEDIA_CONTENT_TYPE_AUDIO,
        MEDIA_CONTENT_TYPE_AUDIO_VIDEO
    }

    public final class RingerModeChangeReceiver extends BroadcastReceiver {
        private String b;

        public RingerModeChangeReceiver(String str) {
            this.b = str;
        }

        public final void onReceive(Context context, Intent intent) {
            if (intent != null && "android.media.RINGER_MODE_CHANGED".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("android.media.EXTRA_RINGER_MODE", XZBDevice.DOWNLOAD_LIST_RECYCLE);
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Ringer mode action changed: ").append(intExtra).toString());
                MraidMediaProcessor.this.a(this.b, 2 != intExtra);
            }
        }
    }

    public final class a extends ContentObserver {
        private Context b;
        private int c;
        private String d;

        public a(String str, Context context, Handler handler) {
            super(handler);
            this.d = str;
            this.b = context;
            this.c = -1;
        }

        public final void onChange(boolean z) {
            super.onChange(z);
            if (this.b != null) {
                int streamVolume = ((AudioManager) this.b.getSystemService("audio")).getStreamVolume(XZBDevice.DOWNLOAD_LIST_FAILED);
                if (streamVolume != this.c) {
                    this.c = streamVolume;
                    MraidMediaProcessor.this.a(this.d, streamVolume);
                }
            }
        }
    }

    static {
        a = MraidMediaProcessor.class.getSimpleName();
    }

    public MraidMediaProcessor(RenderView renderView) {
        this.j = new Hashtable();
        this.b = renderView;
        this.g = new f();
        this.h = new b();
        this.i = false;
    }

    public final boolean a() {
        return this.i;
    }

    public final void a(f fVar) {
        this.g = fVar;
        this.i = true;
    }

    public final void a(b bVar) {
        this.h = bVar;
    }

    public final void a(String str, String str2, MediaContentType mediaContentType, Activity activity) {
        if (b(str, str2, mediaContentType, activity)) {
            f fVar = this.g;
            b bVar = this.h;
            this.b.setAdActiveFlag(true);
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Media player state: ").append(this.c.c).toString());
            if (str2.length() != 0) {
                this.c.a(str, str2, fVar, bVar);
            } else {
                this.c.a(str, this.c.g, fVar, bVar);
            }
            if (MediaContentType.MEDIA_CONTENT_TYPE_AUDIO_VIDEO == mediaContentType && str2.startsWith(HttpConstant.HTTP) && !str2.endsWith("mp4") && !str2.endsWith("avi") && !str2.endsWith("m4v")) {
                this.c.c((int) XZBDevice.DOWNLOAD_LIST_FAILED);
            } else if (MediaContentType.MEDIA_CONTENT_TYPE_AUDIO == mediaContentType && str2.startsWith(HttpConstant.HTTP) && !str2.endsWith("mp3")) {
                this.c.c((int) XZBDevice.DOWNLOAD_LIST_FAILED);
            } else {
                this.j.put(this.g.a, this.c);
                if (d.d == this.c.c) {
                    this.c.b();
                    return;
                }
                ViewGroup viewGroup;
                LayoutParams layoutParams;
                if (fVar.a()) {
                    viewGroup = (ViewGroup) activity.findViewById(16908290);
                    layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                    layoutParams.addRule(XZBDevice.Upload);
                    this.c.setLayoutParams(layoutParams);
                    ViewGroup relativeLayout = new RelativeLayout(activity);
                    relativeLayout.setOnTouchListener(new OnTouchListener() {
                        public boolean onTouch(View view, MotionEvent motionEvent) {
                            return true;
                        }
                    });
                    relativeLayout.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
                    relativeLayout.addView(this.c);
                    viewGroup.addView(relativeLayout, new LayoutParams(-1, -1));
                    this.c.a(relativeLayout);
                    this.c.requestFocus();
                    this.c.setOnKeyListener(new OnKeyListener() {
                        public boolean onKey(View view, int i, KeyEvent keyEvent) {
                            if (4 != i || keyEvent.getAction() != 0) {
                                return false;
                            }
                            MraidMediaProcessor.this.c.a(true);
                            return true;
                        }
                    });
                } else {
                    viewGroup = (ViewGroup) activity.findViewById(16908290);
                    ViewGroup relativeLayout2 = new RelativeLayout(activity);
                    LayoutParams layoutParams2 = new FrameLayout.LayoutParams(bVar.c, bVar.d);
                    layoutParams2.leftMargin = bVar.a;
                    layoutParams2.topMargin = bVar.b;
                    layoutParams2.width = bVar.c;
                    layoutParams2.height = bVar.d;
                    layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                    layoutParams.addRule(XZBDevice.Stop);
                    layoutParams.addRule(XZBDevice.Fail);
                    layoutParams.addRule(XZBDevice.Pause);
                    layoutParams.addRule(XZBDevice.Success);
                    this.c.setLayoutParams(layoutParams);
                    this.c.a(relativeLayout2);
                    relativeLayout2.addView(this.c);
                    viewGroup.addView(relativeLayout2, layoutParams2);
                    this.c.clearFocus();
                }
                this.c.a(new c() {
                    public void a(g gVar) {
                        Logger.a(InternalLogLevel.INTERNAL, a, ">>> onPlayerCompleted");
                        MraidMediaProcessor.this.b.setAdActiveFlag(false);
                        View f = gVar.f();
                        if (f != null) {
                            ((ViewGroup) f.getParent()).removeView(f);
                        }
                        gVar.a(null);
                        synchronized (this) {
                            if (MraidMediaProcessor.this.c != null && gVar.f.equalsIgnoreCase(MraidMediaProcessor.this.c.f)) {
                                MraidMediaProcessor.this.j.remove(MraidMediaProcessor.this.c.f);
                                MraidMediaProcessor.this.c = null;
                            }
                        }
                    }

                    public void b(g gVar) {
                        Logger.a(InternalLogLevel.INTERNAL, a, ">>> onPlayerPrepared");
                    }
                });
                this.c.a();
            }
        }
    }

    public final void a(String str, String str2) {
        g d = d(str2);
        if (d == null) {
            this.b.a(str, "Invalid property ID", "pauseMedia");
            return;
        }
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Media player state: ").append(d.c).toString());
        if (d.c == d.b || !(d.a == d.c || d.d == d.c)) {
            d.pause();
        } else if (d.h) {
            this.b.a(str, "Invalid player state", "pauseMedia");
        } else {
            this.g.d = false;
            d.e = this.g;
        }
    }

    public final void a(String str, String str2, int i) {
        g d = d(str2);
        if (d == null) {
            this.b.a(str, "Invalid property ID", "seekMedia");
            return;
        }
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Media player state: ").append(d.c).toString());
        if (d.g == d.c || d.a == d.c || d.d == d.c) {
            this.b.a(str, "Invalid player state", "seekMedia");
        } else {
            d.a(i * 1000);
        }
    }

    public final void b(String str, String str2) {
        g d = d(str2);
        if (d == null) {
            this.b.a(str, "Invalid property ID", "muteMedia");
            return;
        }
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Media player state: ").append(d.c).toString());
        if (d.g == d.c || d.a == d.c || d.d == d.c) {
            this.b.a(str, "Invalid player state", "muteMedia");
        } else {
            d.d();
        }
    }

    public final void c(String str, String str2) {
        g d = d(str2);
        if (d == null) {
            this.b.a(str, "Invalid property ID", "unMuteMedia");
            return;
        }
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Media player state: ").append(d.c).toString());
        if (d.g == d.c || d.a == d.c || d.d == d.c) {
            this.b.a(str, "Invalid player state", "unMuteMedia");
        } else {
            d.e();
        }
    }

    public final boolean d(String str, String str2) {
        g d = d(str2);
        if (d == null) {
            this.b.a(str, "Invalid property ID", "isMediaMuted");
            return false;
        }
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Media player state: ").append(d.c).toString());
        if (d.g != d.c && d.a != d.c && d.d != d.c) {
            return d.b;
        }
        this.b.a(str, "Invalid player state", "isMediaMuted");
        return false;
    }

    public final void b(String str, String str2, int i) {
        g d = d(str2);
        if (d == null) {
            this.b.a(str, "Invalid property ID", "setMediaVolume");
            return;
        }
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Media player state: ").append(d.c).toString());
        if (d.g == d.c || d.d == d.c) {
            this.b.a(str, "Invalid player state", "setMediaVolume");
        } else {
            d.b(i);
        }
    }

    public final int e(String str, String str2) {
        g d = d(str2);
        if (d == null) {
            this.b.a(str, "Invalid property ID", "getMediaVolume");
            return 0;
        }
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Media player state: ").append(d.c).toString());
        if (d.g != d.c) {
            return !d.b ? d.a : 0;
        } else {
            this.b.a(str, "Invalid player state", "getMediaVolume");
            return 0;
        }
    }

    public final void a(String str, String str2, boolean z) {
        g d = d(str2);
        if (d == null) {
            this.b.a(str, "Invalid property ID", "closeMedia");
            return;
        }
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Media player state: ").append(d.c).toString());
        if (d.g == d.c || d.d == d.c) {
            this.b.a(str, "Invalid player state", "closeMedia");
        } else {
            d.a(z);
        }
    }

    public final void f(String str, String str2) {
        g d = d(str2);
        if (d == null) {
            this.b.a(str, "Invalid property ID", "hideMedia");
        } else if (d.g == d.c) {
            this.b.a(str, "Invalid player state", "hideMedia");
        } else if (d.d == d.c) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Media player is already hidden");
        } else {
            d.c();
        }
    }

    public final void g(String str, String str2) {
        g d = d(str2);
        if (d == null) {
            this.b.a(str, "Invalid property ID", "showMedia");
        } else if (d.g == d.c) {
            this.b.a(str, "Invalid player state", "showMedia");
        } else if (!this.g.a.equalsIgnoreCase(str2)) {
            this.b.a(str, "Show failed. There is already a video playing", "showMedia");
        } else if (d.e == d.c) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Media player is already showing");
        } else {
            this.j.remove(str2);
            this.c = d;
            d.b();
        }
    }

    public final void b() {
        if (this.c != null) {
            this.j.put(this.c.f, this.c);
        }
        Iterator it = this.j.keySet().iterator();
        while (it.hasNext()) {
            g gVar = (g) this.j.get(it.next());
            it.remove();
            gVar.a(true);
        }
        this.j.clear();
        this.c = null;
    }

    public final void c() {
        if (this.c != null && d.g != this.c.c) {
            this.j.put(this.c.f, this.c);
            this.c.c();
        }
    }

    public final boolean d() {
        return 2 != ((AudioManager) com.inmobi.commons.a.a.b().getSystemService("audio")).getRingerMode();
    }

    public final void a(String str) {
        if (this.d == null) {
            this.d = new RingerModeChangeReceiver(str);
            com.inmobi.commons.a.a.b().registerReceiver(this.d, new IntentFilter("android.media.RINGER_MODE_CHANGED"));
        }
    }

    public final void e() {
        if (this.d != null) {
            com.inmobi.commons.a.a.b().unregisterReceiver(this.d);
            this.d = null;
        }
    }

    public final void b(String str) {
        if (this.e == null) {
            Context b = com.inmobi.commons.a.a.b();
            this.e = new a(str, b, new Handler());
            b.getContentResolver().registerContentObserver(System.CONTENT_URI, true, this.e);
        }
    }

    public final void f() {
        if (this.e != null) {
            com.inmobi.commons.a.a.b().getContentResolver().unregisterContentObserver(this.e);
            this.e = null;
        }
    }

    public final boolean g() {
        return ((AudioManager) com.inmobi.commons.a.a.b().getSystemService("audio")).isWiredHeadsetOn();
    }

    public final void c(String str) {
        if (this.f == null) {
            this.f = new HeadphonesPluggedChangeReceiver(str);
            com.inmobi.commons.a.a.b().registerReceiver(this.f, new IntentFilter("android.intent.action.HEADSET_PLUG"));
        }
    }

    public final void h() {
        if (this.f != null) {
            com.inmobi.commons.a.a.b().unregisterReceiver(this.f);
            this.f = null;
        }
    }

    private boolean b(String str, String str2, MediaContentType mediaContentType, Activity activity) {
        if (this.c == null || !this.c.f.equalsIgnoreCase(this.g.a)) {
            return a(str, str2, this.g.a, mediaContentType, activity);
        }
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Reusing media player (").append(this.c.f).append(") from the pool").toString());
        if (!this.c.f.equalsIgnoreCase(this.g.a)) {
            return false;
        }
        if (str2.length() == 0 || this.c.g.equalsIgnoreCase(str2)) {
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Player state: ").append(this.c.c).toString());
            switch (AnonymousClass_4.a[this.c.c.ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    this.c.start();
                    j();
                    return false;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    if (this.c.h) {
                        this.c.start();
                    } else {
                        this.g.d = true;
                        this.c.e = this.g;
                    }
                    j();
                    return false;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    if (!this.g.f) {
                        return false;
                    }
                    this.c.start();
                    j();
                    return false;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    j();
                    return false;
                default:
                    return false;
            }
        }
        this.c.a(str, str2, this.g, this.h);
        this.c.g();
        return false;
    }

    private void j() {
        if (!this.g.a()) {
            RelativeLayout relativeLayout = (RelativeLayout) this.c.f();
            if (relativeLayout != null) {
                relativeLayout.setOnTouchListener(null);
                relativeLayout.setBackgroundColor(0);
                LayoutParams layoutParams = new FrameLayout.LayoutParams(this.h.c, this.h.d);
                FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) relativeLayout.getLayoutParams();
                if (-99999 == this.h.a || -99999 == this.h.b) {
                    layoutParams.leftMargin = layoutParams2.leftMargin;
                    layoutParams.topMargin = layoutParams2.topMargin;
                } else {
                    layoutParams.leftMargin = this.h.a;
                    layoutParams.topMargin = this.h.b;
                }
                relativeLayout.setLayoutParams(layoutParams);
            }
        }
    }

    private boolean a(String str, String str2, String str3, MediaContentType mediaContentType, Activity activity) {
        if ((str2.length() != 0 && !URLUtil.isValidUrl(str2)) || (str2.length() == 0 && !this.j.containsKey(str3))) {
            String str4;
            RenderView renderView = this.b;
            String toString = new StringBuilder("Invalid ID (").append(str3).append("); no playback URL for this ID").toString();
            if (MediaContentType.MEDIA_CONTENT_TYPE_AUDIO_VIDEO == mediaContentType) {
                str4 = "playVideo";
            } else {
                str4 = "playAudio";
            }
            renderView.a(str, toString, str4);
            return false;
        } else if (this.j.size() == 5) {
            this.b.a(str, "Cannot create media player - limit on number of media players reached", MediaContentType.MEDIA_CONTENT_TYPE_AUDIO_VIDEO == mediaContentType ? "playVideo" : "playAudio");
            return false;
        } else {
            g gVar = (g) this.j.remove(str3);
            if (gVar == null) {
                if (this.c != null && this.g.a()) {
                    Logger.a(InternalLogLevel.INTERNAL, a, "Only a single instance of full-screen media playback is allowed. Releasing the current active player ...");
                    this.j.remove(this.c.f);
                    this.c.a(false);
                }
                Logger.a(InternalLogLevel.INTERNAL, a, "Creating a new media player instance!");
                this.c = new g(activity, this.b);
            } else {
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Reusing media player (").append(str3).append(") from the pool").toString());
                this.c = gVar;
            }
            if (str2.length() == 0 && gVar != null) {
                this.c.a(str, gVar.g, gVar.e, gVar.d);
                this.c.d = gVar.d;
            }
            return true;
        }
    }

    private g d(String str) {
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Checking for media player with ID: ").append(str).toString());
        if (this.c == null || !(str == null || str.length() == 0)) {
            g gVar = (g) this.j.get(str);
            if (gVar != null) {
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Returning media render view with ID: ").append(str).append(" (state: ").append(gVar.c).append(SocializeConstants.OP_CLOSE_PAREN).toString());
                return gVar;
            }
            Logger.a(InternalLogLevel.INTERNAL, a, "No media render view found!");
            return gVar;
        } else if ("anonymous".equalsIgnoreCase(this.g.a)) {
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Returning media render view with ID: ").append(this.g.a).append(" (state: ").append(this.c.c).append(SocializeConstants.OP_CLOSE_PAREN).toString());
            return this.c;
        } else {
            Logger.a(InternalLogLevel.INTERNAL, a, "Cannot find ID to look up the media render view");
            return null;
        }
    }

    private void a(String str, boolean z) {
        if (this.b != null) {
            this.b.a(str, new StringBuilder("fireDeviceMuteChangeEvent(").append(z).append(");").toString());
        }
    }

    private void a(String str, int i) {
        if (this.b != null) {
            this.b.a(str, new StringBuilder("fireDeviceVolumeChangeEvent(").append(i).append(");").toString());
        }
    }

    private void b(String str, boolean z) {
        if (this.b != null) {
            this.b.a(str, new StringBuilder("fireHeadphonePluggedEvent(").append(z).append(");").toString());
        }
    }
}
