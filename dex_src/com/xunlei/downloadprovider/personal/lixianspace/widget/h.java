package com.xunlei.downloadprovider.personal.lixianspace.widget;

// compiled from: LixianSpaceListWidget.java
final class h implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ LixianSpaceListWidget b;

    h(LixianSpaceListWidget lixianSpaceListWidget, boolean z) {
        this.b = lixianSpaceListWidget;
        this.a = z;
    }

    public final void run() {
        if (LixianSpaceListWidget.m(this.b) || this.a) {
            LixianSpaceListWidget.f(this.b).setFooterNeedShow(false);
        } else {
            LixianSpaceListWidget.f(this.b).setFooterNeedShow(true);
        }
    }
}
