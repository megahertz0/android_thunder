package com.xunlei.tdlive.d;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import cn.nodemedia.LivePlayer;
import cn.nodemedia.LivePlayer.LivePlayerDelegate;
import com.uc.addon.sdk.remote.EventIds;
import com.uc.addon.sdk.remote.TabsImpl;
import com.umeng.message.MsgConstant;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.d.a.a;
import com.xunlei.tdlive.sdk.IHost;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.q;
import com.xunlei.tdlive.util.t;
import com.xunlei.tdlive.util.w;
import com.xunlei.tdlive.util.x;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// compiled from: NMPlayer.java
public class e extends Handler implements LivePlayerDelegate, a, Runnable {
    private static e a;
    private int b;
    private SurfaceView c;
    private a d;
    private FrameLayout e;
    private String f;
    private int g;
    private boolean h;
    private boolean i;
    private Context j;
    private int k;
    private int l;
    private w m;
    private w n;
    private w o;
    private w p;
    private w q;
    private w r;
    private x s;

    static {
        a = null;
    }

    private e() {
        this.b = 0;
        this.m = new w("start_time");
        this.n = new w("duration");
        this.o = new w("connecting");
        this.p = new w("loading", true);
        this.q = new w("buffering");
        this.r = new w("error");
        this.s = new x(this);
    }

    public static a h() {
        if (a == null) {
            a = new e();
        }
        return a;
    }

    public void a(Context context, FrameLayout frameLayout, String str, a aVar) {
        if (!(this.b == 0 || this.b == 4)) {
            LivePlayer.setUIVIew(null);
            new Thread(this).start();
        }
        this.i = true;
        this.h = false;
        this.g = 0;
        this.k = 0;
        this.l = 0;
        a(aVar);
        a(frameLayout);
        this.j = context;
        LivePlayer.init(context);
        LivePlayer.setDelegate(this);
        if (com.xunlei.tdlive.modal.e.c <= 0) {
            com.xunlei.tdlive.modal.e.c = 200;
        }
        if (com.xunlei.tdlive.modal.e.b <= 0) {
            com.xunlei.tdlive.modal.e.b = 10000;
        }
        LivePlayer.setBufferTime(com.xunlei.tdlive.modal.e.c);
        LivePlayer.setMaxBufferTime(com.xunlei.tdlive.modal.e.b);
        String a = t.a(str);
        this.f = a;
        obtainMessage(1601, a).sendToTarget();
    }

    public void a() {
        this.f = null;
        this.i = false;
        XLog.d("NMPlayer", new StringBuilder("destroy state=").append(this.b).toString());
        if (!(this.b == 0 || this.b == 4)) {
            LivePlayer.setUIVIew(null);
            new Thread(this, "NMPlayerStop").start();
            if (this.d != null) {
                this.d.a(this.m.b(), (int) this.o.f(), (int) this.q.f(), (int) this.p.f(), (int) this.n.d(), "exit", JsInterface.MSG_JS_OPEN_TRANSCODE_DOWNLOADLIST);
            }
        }
        this.p.a();
        this.d = null;
        removeCallbacksAndMessages(null);
    }

    public void b() {
    }

    public void c() {
        if (this.b != 0 && this.b != 4) {
            LivePlayer.setUIVIew(this.c);
        }
    }

    public void a(Configuration configuration) {
        if (this.b != 0 && this.b != 4) {
            a(this.k, this.l);
        }
    }

    public void a(FrameLayout frameLayout) {
        XLog.d("NMPlayer", new StringBuilder("setContainer mAdjustSurface:").append(this.h).toString());
        if (!(this.e == null || this.c == null)) {
            this.e.removeView(this.c);
        }
        if (frameLayout != null) {
            this.e = frameLayout;
            this.c = new SurfaceView(frameLayout.getContext());
            this.e.addView(this.c, 1, 1);
            LivePlayer.setUIVIew(this.c);
            sendMessageDelayed(obtainMessage(1104, new String(this.k + "x" + this.l)), 100);
        }
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    public boolean d() {
        return this.i;
    }

    public boolean e() {
        return false;
    }

    public int f() {
        return 0;
    }

    public int g() {
        return -1;
    }

    public int a(int i) {
        return 0;
    }

    public void run() {
        LivePlayer.pause();
        LivePlayer.stopPlay();
        this.b = 4;
    }

    public void onEventCallback(int i, String str) {
        obtainMessage(i, str).sendToTarget();
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(new SimpleDateFormat("HH:mm:ss:SSS", Locale.US).format(new Date()));
        stringBuffer.append(new StringBuilder(" - ").append(message.what).append(" - ").toString());
        stringBuffer.append((String) message.obj);
        XLog.d("NMPlayer", stringBuffer.toString());
        int i;
        switch (message.what) {
            case IHost.HOST_NOFITY_REFRESH_LIST:
                this.b = 1;
                this.o.c();
                this.p.c();
            case IHost.HOST_NOFITY_PAGE_SELECTED:
                this.b = 2;
                this.o.d();
                q.a("t_connection", null, (int) this.o.f());
                XLog.d("NMPlayer", this.o.toString());
            case IHost.HOST_NOFITY_PAGE_DESELECTED:
                com.xunlei.tdlive.a.a(new StringBuilder("nmplay connect failed").append(message.what).append((String) message.obj).append(", stream:").append(this.f).toString());
                if (message.what != 1004) {
                    this.r.d();
                    this.o.d();
                    q.a("c_error", null, (int) this.r.e());
                    if (this.d != null) {
                        this.d.a(this.m.b(), (int) this.o.f(), (int) this.q.f(), (int) this.p.f(), (int) this.n.d(), message.what != 1002 ? "noconnect" : MsgConstant.KEY_FAIL, message.what);
                    }
                } else if (message.what == 1004) {
                    this.b = 4;
                    if (this.f != null && this.f.length() > 0) {
                        XLog.d("NMPlayer", new StringBuilder("\u5f02\u5e38\u7ed3\u675f\uff0c\u5c1d\u8bd5\u91cd\u8bd5\uff0c\u5f53\u524d\u6b21\u6570:").append(this.g).toString());
                        i = this.g;
                        this.g = i + 1;
                        if (i < 5) {
                            sendMessageDelayed(obtainMessage(1601, this.f), TabsImpl.SYNC_TIME_OUT);
                        }
                    }
                }
            case JsInterface.MSG_JS_OPEN_TRANSCODE_DOWNLOADLIST:
            case JsInterface.MSG_JS_GET_USER_INFO_CHANGE:
            case JsInterface.MSG_JS_GET_USER_ID:
            case JsInterface.MSG_JS_OPEN_WINDOW_WITH_TYPE:
            case JsInterface.MSG_JS_SHOWTOAST:
            case JsInterface.MSG_JS_SHOWTOAST_BY_TYPE:
                if (message.what != 1004) {
                    this.r.d();
                    this.o.d();
                    q.a("c_error", null, (int) this.r.e());
                    if (this.d != null) {
                        if (message.what != 1002) {
                        }
                        this.d.a(this.m.b(), (int) this.o.f(), (int) this.q.f(), (int) this.p.f(), (int) this.n.d(), message.what != 1002 ? "noconnect" : MsgConstant.KEY_FAIL, message.what);
                    }
                } else if (message.what == 1004) {
                    this.b = 4;
                    if (this.f != null) {
                    }
                }
            case EventIds.EVENT_VIEW_FILE:
                if (this.d != null) {
                    this.d.a(this.m.b(), (int) this.o.f(), (int) this.q.f(), (int) this.p.f(), (int) this.n.d(), "connect_card", EventIds.EVENT_VIEW_FILE);
                }
            case 1101:
                this.q.c();
                this.s.a(2000, new f(this));
            case 1102:
                this.b = 3;
                this.h = true;
                this.s.a();
                this.n.c();
                this.p.d();
                this.q.d();
                q.a("t_first_load", null, (int) this.p.f());
                q.a("t_buffer", null, (int) this.q.f());
                XLog.d("NMPlayer", this.p.toString());
                XLog.d("NMPlayer", this.q.toString());
            case 1104:
                if (this.h) {
                    String[] split = ((String) message.obj).split("x");
                    int intValue = Integer.valueOf(split[0]).intValue();
                    this.k = intValue;
                    i = Integer.valueOf(split[1]).intValue();
                    this.l = i;
                    a(intValue, i);
                    return;
                }
                sendMessageDelayed(obtainMessage(message.what, message.obj), 100);
            case 1601:
                if (this.b == 0 || this.b == 4) {
                    LivePlayer.stopPlay();
                    LivePlayer.startPlay((String) message.obj);
                    this.m.c();
                    return;
                }
                XLog.d("NMPlayer", new StringBuilder("mState=").append(this.b).append(", last play no stop, wait 1s").toString());
                sendMessageDelayed(obtainMessage(message.what, message.obj), 1000);
            default:
                break;
        }
    }

    private void a(int i, int i2) {
        XLog.d("NMPlayer", new StringBuilder("doVideoFix srcWidth=").append(i).append(", srcHeight=").append(i2).toString());
        if (this.e != null && this.c != null && i != 0 && i2 != 0) {
            float f;
            Rect rect = new Rect();
            this.e.getDrawingRect(rect);
            int width = rect.width();
            int height = rect.height();
            if (i < width && i2 >= height) {
                f = (float) ((((double) width) * 1.0d) / ((double) i));
            } else if (i2 >= height || i < width) {
                f = (float) ((((double) width) * 1.0d) / ((double) i));
                float f2 = (float) ((((double) height) * 1.0d) / ((double) i2));
                if (f <= f2) {
                    f = f2;
                }
            } else {
                f = (float) ((((double) height) * 1.0d) / ((double) i2));
            }
            width = (int) (((float) i) * f);
            int i3 = (int) (f * ((float) i2));
            LayoutParams layoutParams = this.c.getLayoutParams();
            layoutParams.width = width;
            layoutParams.height = i3;
            this.c.setLayoutParams(layoutParams);
        }
    }
}
