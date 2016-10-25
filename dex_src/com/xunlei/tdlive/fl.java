package com.xunlei.tdlive;

import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.util.y.a;

// compiled from: WebBrowserActivity.java
class fl implements a {
    final /* synthetic */ String a;
    final /* synthetic */ fk b;

    fl(fk fkVar, String str) {
        this.b = fkVar;
        this.a = str;
    }

    public void a(int i, String str) {
        if (i == 0) {
            this.b.a.callJS(this.a, new JsonWrapper("{\"result\":0}"));
        } else if (i == 1) {
            this.b.a.callJS(this.a, new JsonWrapper("{\"result\":1}"));
        } else {
            this.b.a.callJS(this.a, new JsonWrapper("{\"result\":2}"));
        }
    }
}
