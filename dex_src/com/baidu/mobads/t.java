package com.baidu.mobads;

import com.baidu.mobads.g.d.b;
import com.baidu.mobads.vo.a.c;

class t implements b {
    final /* synthetic */ AppActivity a;

    t(AppActivity appActivity) {
        this.a = appActivity;
    }

    public void a() {
        c c = this.a.A;
        c.D++;
        this.a.g();
        this.a.runBottomViewExitAnimation(this.a.C, this.a.mBottomView);
    }

    public void b() {
        c c = this.a.A;
        c.E++;
        this.a.copyCurrentPageUrl();
        this.a.runBottomViewExitAnimation(this.a.C, this.a.mBottomView);
    }

    public void c() {
        c c = this.a.A;
        c.F++;
        this.a.runBottomViewExitAnimation(this.a.C, this.a.mBottomView);
    }
}
