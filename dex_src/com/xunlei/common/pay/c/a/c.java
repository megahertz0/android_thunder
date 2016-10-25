package com.xunlei.common.pay.c.a;

import com.xunlei.common.pay.XLContractor;
import com.xunlei.common.pay.a.e;
import com.xunlei.common.pay.a.f;
import com.xunlei.common.pay.param.XLPayParam;

// compiled from: XLAliPayContractor.java
public final class c implements XLContractor {
    private f a;

    public c(f fVar) {
        this.a = null;
        this.a = fVar;
    }

    public final int userContract(XLPayParam xLPayParam, Object obj) {
        e bVar = new b();
        bVar.a();
        bVar.a(xLPayParam);
        bVar.a(obj);
        this.a.a(bVar);
        this.a.h().registerStatReq(bVar.b());
        return bVar.b();
    }

    public final int userQuery(XLPayParam xLPayParam, Object obj) {
        e dVar = new d();
        dVar.a();
        dVar.a(xLPayParam);
        dVar.a(obj);
        this.a.a(dVar);
        this.a.h().registerStatReq(dVar.b());
        return dVar.b();
    }

    public final int userDisContract(XLPayParam xLPayParam, Object obj) {
        e aVar = new a();
        aVar.a();
        aVar.a(xLPayParam);
        aVar.a(obj);
        this.a.a(aVar);
        this.a.h().registerStatReq(aVar.b());
        return aVar.b();
    }
}
