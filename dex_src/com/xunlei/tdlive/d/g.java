package com.xunlei.tdlive.d;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import cn.nodemedia.LivePublisher;
import cn.nodemedia.LivePublisher.LivePublishDelegate;
import com.uc.addon.sdk.remote.EventIds;
import com.uc.addon.sdk.remote.TabsImpl;
import com.umeng.message.MsgConstant;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.d.b.a;
import com.xunlei.tdlive.modal.e;
import com.xunlei.tdlive.sdk.IHost;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.w;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

// compiled from: NMPublisher.java
public class g extends Handler implements LivePublishDelegate, b {
    private static g a;
    private int b;
    private String c;
    private String d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private boolean j;
    private w k;
    private w l;
    private w m;
    private w n;
    private a o;
    private FrameLayout p;

    static {
        a = null;
    }

    private g() {
        this.b = 0;
        this.c = com.umeng.a.d;
        this.d = com.umeng.a.d;
        this.e = 0;
        this.f = 368;
        this.g = 640;
        this.h = 24;
        this.i = 720;
        this.j = false;
        this.k = new w("start_time");
        this.l = new w("connect_duration");
        this.m = new w("load_duration");
        this.n = new w("duration");
    }

    public static b d() {
        if (a == null) {
            a = new g();
        }
        return a;
    }

    public void a(Object obj, boolean z, int i, int i2, int i3, int i4, a aVar) {
        int i5;
        int i6;
        if (i > i2) {
            i5 = i + i2;
            i6 = i5 - i2;
            i5 -= i6;
        } else {
            i6 = i2;
            i5 = i;
        }
        if (i5 <= 0) {
            i5 = 368;
        }
        if (i6 <= 0) {
            i6 = 640;
        }
        if (i3 <= 20) {
            i3 = R.styleable.AppCompatTheme_actionModeSplitBackground;
        }
        if (i4 <= 720) {
            i4 = 720;
        }
        if (obj instanceof Context) {
            LivePublisher.init((Context) obj, z, e.f);
            obj = null;
        } else if (obj instanceof FrameLayout) {
            FrameLayout frameLayout = (FrameLayout) obj;
            LivePublisher.init(frameLayout.getContext(), z, e.f);
        } else {
            return;
        }
        LivePublisher.setDelegate(this);
        LivePublisher.setAudioParam(96000, 1);
        LivePublisher.setDenoiseEnable(true);
        if (!(this.b == 5 || this.b == 0)) {
            LivePublisher.stopPreview();
            LivePublisher.stopPublish();
            if (this.b == 1 || this.b == 4 || this.b == 6) {
                this.b = 0;
            }
        }
        this.p = obj;
        this.f = i5;
        this.g = i6;
        this.h = i3;
        this.i = i4;
        this.e = 0;
        this.o = aVar;
        this.j = true;
        obtainMessage(com.alipay.sdk.data.a.d, "\u5f00\u59cb\u63a8\u6d41").sendToTarget();
        XLog.d("NMPublisher", new StringBuilder("LivePublisher h=").append(i6).append(", w=").append(i5).append(", fps=").append(i3).append(", bitrate=").append(i4).toString());
    }

    public void a() {
        if (!(this.b == 5 || this.b == 0)) {
            LivePublisher.stopPreview();
            LivePublisher.stopPublish();
            if (this.b == 1 || this.b == 4 || this.b == 6) {
                this.b = 0;
            }
        }
        this.j = false;
        this.o = null;
        removeMessages(-2005);
    }

    public void b() {
    }

    public void c() {
    }

    public void a(String str, String str2) {
        XLog.d("NMPublisher", new StringBuilder("publish roomid:").append(str).append(", rtmpURL:").append(str2).toString());
        this.k.c();
        this.l.c();
        this.m.c();
        this.c = str;
        this.d = str2;
        LivePublisher.startPublish(str2);
    }

    public void a(Configuration configuration) {
    }

    public void onEventCallback(int i, String str) {
        obtainMessage(i, str).sendToTarget();
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        String str = com.umeng.a.d;
        int i;
        switch (message.what) {
            case -2005:
                String str2 = "\u7f51\u7edc\u5f02\u5e38,\u6b63\u5728\u91cd\u8bd5";
                try {
                    NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.p.getContext().getSystemService("connectivity")).getActiveNetworkInfo();
                    if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                        sendMessageDelayed(obtainMessage(-2005), TabsImpl.SYNC_TIME_OUT);
                        str = str2;
                    } else {
                        a(this.c, this.d);
                        str = str2;
                    }
                } catch (Exception e) {
                    str = str2;
                }
                break;
            case IHost.CLIENT_NOFITY_INIT:
                str = "\u6b63\u5728\u53d1\u5e03\u89c6\u9891";
                this.b = 2;
                this.l.d();
                break;
            case IHost.CLIENT_NOFITY_REFRESH_LIST_END:
                str = "\u89c6\u9891\u53d1\u5e03\u6210\u529f";
                this.b = 3;
                this.n.c();
                this.m.d();
                this.e = 0;
                break;
            case IHost.CLIENT_NOFITY_NO_NETWORK_ERROR:
                str = "\u89c6\u9891\u53d1\u5e03\u5931\u8d25";
                this.b = 4;
                this.m.d();
                com.xunlei.tdlive.a.a(new StringBuilder("publish failed").append(message.what).append(str).append(", stream:").append(this.d).toString());
                break;
            case 2004:
                str = "\u89c6\u9891\u53d1\u5e03\u7ed3\u675f";
                this.b = 5;
                removeMessages(com.alipay.sdk.data.a.d);
                LivePublisher.stopPublish2();
                if (this.o != null) {
                    this.o.a(this.k.b(), (int) this.l.f(), 0, (int) this.m.f(), (int) this.n.d(), "exit", 2004);
                }
                break;
            case 2005:
                str = "\u7f51\u7edc\u5f02\u5e38,\u53d1\u5e03\u4e2d\u65ad";
                this.b = 6;
                LivePublisher.stopPublish2();
                i = this.e;
                this.e = i + 1;
                if (i <= 5) {
                    sendMessageDelayed(obtainMessage(-2005), TabsImpl.SYNC_TIME_OUT);
                } else if (this.o != null) {
                    this.o.b("\u7f51\u7edc\u5f02\u5e38\uff0c\u8bf7\u68c0\u67e5\u7f51\u7edc\u8bbe\u7f6e!");
                }
                if (this.o != null) {
                    this.o.a(this.k.b(), (int) this.l.f(), 0, 0, (int) this.n.d(), MsgConstant.KEY_FAIL, 2005);
                }
                break;
            case 2006:
                str = "\u7f51\u7edc\u6d41\u6253\u5f00\u5931\u8d25\uff0c\u91cd\u8bd5";
                this.b = 6;
                this.l.d();
                i = this.e;
                this.e = i + 1;
                if (i > 5) {
                    com.xunlei.tdlive.a.a(new StringBuilder("publish failed").append(message.what).append(str).append(", stream:").append(this.d).toString());
                    if (this.o != null) {
                        this.o.b("\u76f4\u64ad\u5f02\u5e38\uff0c\u8bf7\u91cd\u65b0\u521b\u5efa\u623f\u95f4!");
                    }
                } else {
                    sendMessageDelayed(obtainMessage(-2005), TabsImpl.SYNC_TIME_OUT);
                }
                if (this.o != null) {
                    this.o.a(this.k.b(), (int) this.l.f(), 0, 0, 0, "noconnect", 2006);
                }
                break;
            case EventIds.EVENT_PAGE_START:
                str = "\u9ea6\u514b\u98ce\u9759\u97f3";
                break;
            case EventIds.EVENT_PAGE_FINISH:
                str = "\u9ea6\u514b\u98ce\u6062\u590d";
                break;
            case 2102:
                str = "\u6444\u50cf\u5934\u4f20\u8f93\u5173\u95ed";
                break;
            case 2103:
                str = "\u6444\u50cf\u5934\u4f20\u8f93\u6253\u5f00";
                break;
            case 10000:
                str = "\u9884\u89c8\u51c6\u5907\u5b8c\u6210";
                this.b = 1;
                if (this.o != null) {
                    this.o.a();
                }
                break;
            case 10002:
                String[] split = ((String) message.obj).split("x");
                a(Integer.valueOf(split[1]).intValue(), Integer.valueOf(split[0]).intValue());
                str = "\u9884\u89c8\u5927\u5c0f";
                break;
            case 10003:
                LivePublisher.setFilterEnable(false);
                if (this.o != null) {
                    this.o.a("\u5f88\u62b1\u6b49\uff0c\u60a8\u7684\u786c\u4ef6\u6027\u80fd\u4e0d\u4f73\uff0c\u6682\u65f6\u4e0d\u80fd\u5f00\u542f\u7f8e\u989c~~");
                }
                break;
            case com.alipay.sdk.data.a.d:
                if (this.b == 5 || this.b == 0) {
                    if (this.p != null) {
                        LivePublisher.setVideoParam(this.g, this.f, this.h, this.i * 1024, 1);
                    }
                    if (!(LivePublisher.startPreview(this.p, 0, 1) == 0 || this.o == null)) {
                        this.o.b("\u60a8\u8fd8\u6ca1\u6709\u6388\u6743\u6211\u4eec\u62cd\u6444\u6743\u9650\uff0c\u8bf7\u5230\u8bbe\u7f6e-\u5e94\u7528\u7ba1\u7406-\u8fc5\u96f7\u76f4\u64ad\u4e2d\u5f00\u542f~");
                    }
                } else {
                    XLog.d("NMPublisher", new StringBuilder("mState=").append(this.b).append(", last push no stop, wait 1s").toString());
                    sendMessageDelayed(obtainMessage(com.alipay.sdk.data.a.d, "\u5f00\u59cb\u63a8\u6d41"), 1000);
                }
                break;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(new SimpleDateFormat("HH:mm:ss:SSS", Locale.US).format(new Date()));
        stringBuffer.append(" ");
        stringBuffer.append(message.what);
        stringBuffer.append(" - ");
        stringBuffer.append(str);
        XLog.d("NMPublisher", stringBuffer.toString());
    }

    private void a(int i, int i2) {
        SurfaceView surfaceView = LivePublisher.getSurfaceView();
        if (surfaceView != null) {
            float f;
            Rect rect = new Rect();
            surfaceView.getDrawingRect(rect);
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
            LayoutParams layoutParams = surfaceView.getLayoutParams();
            layoutParams.width = width;
            layoutParams.height = i3;
            surfaceView.setLayoutParams(layoutParams);
        }
    }
}
