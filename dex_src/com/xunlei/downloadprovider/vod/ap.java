package com.xunlei.downloadprovider.vod;

import com.xunlei.downloadprovider.vod.VodUtil.SharpnessValue;
import com.xunlei.downloadprovider.vod.c.a;
import com.xunlei.downloadprovider.vod.protocol.VodSourceType;
import com.xunlei.downloadprovider.vod.protocol.h;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

// compiled from: VodPlayerParams.java
public final class ap implements Serializable {
    public int a;
    public VodSourceType b;
    public String c;
    public a d;
    public HashSet<String> e;
    public String f;
    public int g;
    public int h;
    public SharpnessValue i;
    public List<SharpnessValue> j;
    final List<h> k;
    com.xunlei.downloadprovider.vod.a.a l;
    public String m;

    public ap() {
        this.a = 0;
        this.c = null;
        this.e = new HashSet();
        this.f = null;
        this.i = null;
        this.j = new ArrayList();
        this.k = new ArrayList();
        this.l = null;
        this.m = null;
    }

    public final void a(h hVar) {
        this.k.add(hVar);
    }

    public final h a() {
        return (this.a < 0 || this.a >= this.k.size()) ? null : (h) this.k.get(this.a);
    }

    public final boolean b() {
        return this.l != null && this.l.d > 0;
    }

    public final int c() {
        Iterator it = this.k.iterator();
        return it.hasNext() ? ((h) it.next()).o : 1;
    }

    public final String toString() {
        return new StringBuilder("VodPlayerParams{mCurEP=").append(this.a).append(", mVodSourceType=").append(this.b).append(", mSourceUrl='").append(this.c).append('\'').append(", mRealUrlList=").append(this.e).append(", mPlayOn='").append(this.f).append('\'').append(", mStartPos=").append(this.g).append(", mOperateType=").append(this.h).append(", mSharpness=").append(this.i).append(", mSharenessRange=").append(this.j).append(", mEpisodeInfoList=").append(((h) this.k.get(0)).toString()).append(", mCooperationData=").append(this.l).append(", mCopyrightPopupUrl='").append(this.m).append('\'').append('}').toString();
    }
}
