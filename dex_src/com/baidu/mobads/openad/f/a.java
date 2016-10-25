package com.baidu.mobads.openad.f;

import com.baidu.mobads.j.m;
import com.baidu.mobads.openad.interfaces.utils.IOAdTimer;
import com.baidu.mobads.openad.interfaces.utils.IOAdTimer.EventHandler;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicInteger;

public class a implements IOAdTimer {
    private static String c;
    protected int a;
    private EventHandler b;
    private int d;
    private int e;
    private int f;
    private Timer g;
    private AtomicInteger h;

    static /* synthetic */ int f(a aVar) {
        int i = aVar.e;
        aVar.e = i - 1;
        return i;
    }

    static {
        c = "OAdTimer";
    }

    public a(int i) {
        this(i, 300);
    }

    public a(int i, int i2) {
        this.a = 300;
        this.a = i2;
        int i3 = i / this.a;
        m.a().f().i(c, new StringBuilder("RendererTimer(duration=").append(i3).append(SocializeConstants.OP_CLOSE_PAREN).toString());
        this.d = i3;
        this.e = i3;
        this.g = new Timer();
        this.h = new AtomicInteger(-1);
    }

    public void setEventHandler(EventHandler eventHandler) {
        this.b = eventHandler;
    }

    public void start() {
        m.a().f().i(c, "start");
        this.h.set(0);
        this.g.scheduleAtFixedRate(new b(this), 0, (long) this.a);
    }

    public void stop() {
        m.a().f().i(c, "stop");
        this.h.set(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        if (this.g != null) {
            this.g.purge();
            this.g.cancel();
            this.g = null;
        }
    }

    public void pause() {
        m.a().f().i(c, "pause");
        this.h.set(1);
    }

    public void resume() {
        m.a().f().i(c, "resume");
        this.h.set(0);
    }

    public int getCurrentCount() {
        return this.f;
    }

    public int getRepeatCount() {
        return this.d;
    }

    public void reset() {
        m.a().f().i(c, "reset");
        this.h.set(-1);
        this.e = this.d;
    }
}
