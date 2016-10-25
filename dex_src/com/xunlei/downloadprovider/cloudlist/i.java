package com.xunlei.downloadprovider.cloudlist;

import com.xunlei.common.lixian.XLLixianBtTask;
import com.xunlei.common.lixian.XLLixianListener;
import com.xunlei.downloadprovider.cloudlist.a.b;
import com.xunlei.tdlive.R;

// compiled from: CloudLixianBTSubFileObatainer.java
public final class i extends a {
    private final XLLixianBtTask e;
    private final XLLixianListener f;

    public i(XLLixianBtTask xLLixianBtTask) {
        this.f = new j(this);
        this.e = xLLixianBtTask;
    }

    public final void a(Object obj) {
        if (this.e != null) {
            this.e.obtainSubTasks(0, this.a.size(), R.styleable.Toolbar_navigationIcon, 0, obj, this.f);
            return;
        }
        a(-1, obj);
    }

    private final void a(int i, Object obj) {
        if (this.b != null) {
            b bVar = this.b;
            this.a.size();
            bVar.a(i, obj, false);
        }
    }
}
