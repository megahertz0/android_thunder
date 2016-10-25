package com.baidu.mobads.production;

import com.baidu.mobads.c.a;
import com.baidu.mobads.interfaces.download.activate.IXActivateListener;
import com.baidu.mobads.interfaces.download.activate.IXAppInfo;

class h implements IXActivateListener {
    final /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    public void onAppActivation(IXAppInfo iXAppInfo) {
        a.a().a(this.a.a.a, iXAppInfo);
    }
}
