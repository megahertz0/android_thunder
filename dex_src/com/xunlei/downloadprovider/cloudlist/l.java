package com.xunlei.downloadprovider.cloudlist;

import com.xunlei.common.yunbo.XLYB_VODINFO;
import com.xunlei.common.yunbo.XLYunboListener;
import com.xunlei.common.yunbo.XLYunboUtil;
import com.xunlei.downloadprovider.cloudlist.a.b;
import com.xunlei.tdlive.R;

// compiled from: CloudVodBTSubFileObtaner.java
public final class l extends a {
    final XLYB_VODINFO e;
    private final XLYunboListener f;

    public l(XLYB_VODINFO xlyb_vodinfo) {
        this.f = new m(this);
        this.e = xlyb_vodinfo;
    }

    public final void a(Object obj) {
        if (this.e != null) {
            XLYunboUtil.getInstance().obtainBtSubfileList(this.e.src_url, R.styleable.Toolbar_navigationIcon, this.a.size(), obj, this.f);
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
