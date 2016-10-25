package com.baidu.mobads.j;

import android.content.Context;
import com.baidu.mobads.am;
import com.baidu.mobads.e.a;
import com.baidu.mobads.interfaces.IXAdContainerFactory;
import com.baidu.mobads.interfaces.error.IXAdErrorCode;
import com.baidu.mobads.interfaces.utils.IBase64;
import com.baidu.mobads.interfaces.utils.IXAdActivityUtils;
import com.baidu.mobads.interfaces.utils.IXAdBitmapUtils;
import com.baidu.mobads.interfaces.utils.IXAdConstants;
import com.baidu.mobads.interfaces.utils.IXAdIOUtils;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.interfaces.utils.IXAdPackageUtils;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.baidu.mobads.interfaces.utils.IXAdURIUitls;
import com.baidu.mobads.interfaces.utils.IXAdViewUtils;
import com.baidu.mobads.openad.c.d;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloaderManager;

public class m {
    private static final m o;
    private g a;
    private l b;
    private IBase64 c;
    private IXAdLogger d;
    private IXAdViewUtils e;
    private IXAdBitmapUtils f;
    private IXAdURIUitls g;
    private IXAdIOUtils h;
    private IXAdPackageUtils i;
    private IXAdActivityUtils j;
    private IXAdSystemUtils k;
    private d l;
    private IXAdConstants m;
    private IXAdErrorCode n;
    private Context p;
    private IXAdContainerFactory q;

    static {
        o = new m();
    }

    public static m a() {
        return o;
    }

    private m() {
        this.c = new a();
        this.d = j.a();
        this.b = new l();
        this.e = new s();
        this.f = new c();
        this.g = new r();
        this.k = new n();
        this.l = new d();
        this.h = new i();
        this.i = new k();
        this.j = new b();
        this.m = new am();
        this.n = new a(this.d);
    }

    public void a(Context context) {
        if (this.p == null) {
            this.p = context;
        }
        this.a = new g(this.p);
    }

    public void a(IXAdContainerFactory iXAdContainerFactory) {
        if (iXAdContainerFactory == null) {
            this.q = iXAdContainerFactory;
        }
    }

    public g b() {
        return this.a;
    }

    public IXAdContainerFactory c() {
        return this.q;
    }

    public Context d() {
        return this.p;
    }

    public IBase64 e() {
        return this.c;
    }

    public IXAdLogger f() {
        return this.d;
    }

    public l g() {
        return this.b;
    }

    public IXAdBitmapUtils h() {
        return this.f;
    }

    public IXAdURIUitls i() {
        return this.g;
    }

    public IXAdViewUtils j() {
        return this.e;
    }

    public IXAdIOUtils k() {
        return this.h;
    }

    public IXAdPackageUtils l() {
        return this.i;
    }

    public d m() {
        return this.l;
    }

    public IXAdSystemUtils n() {
        return this.k;
    }

    public IXAdActivityUtils o() {
        return this.j;
    }

    public IXAdConstants p() {
        return this.m;
    }

    public IXAdErrorCode q() {
        return this.n;
    }

    public IOAdDownloaderManager b(Context context) {
        return d.a(context);
    }
}
