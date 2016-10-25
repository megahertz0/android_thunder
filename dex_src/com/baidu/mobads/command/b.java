package com.baidu.mobads.command;

import android.content.Context;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.IXAdResource;
import com.baidu.mobads.interfaces.IXNonLinearAdSlot;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.j.m;

public abstract class b {
    public Context a;
    public IXNonLinearAdSlot b;
    public IXAdInstanceInfo c;
    protected IXAdResource d;
    public IXAdLogger e;

    public b(IXNonLinearAdSlot iXNonLinearAdSlot, IXAdInstanceInfo iXAdInstanceInfo, IXAdResource iXAdResource) {
        this.e = m.a().f();
        this.b = iXNonLinearAdSlot;
        this.a = iXNonLinearAdSlot.getApplicationContext();
        this.c = iXAdInstanceInfo;
        this.d = iXAdResource;
    }
}
