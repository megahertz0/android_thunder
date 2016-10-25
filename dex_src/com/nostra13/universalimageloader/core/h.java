package com.nostra13.universalimageloader.core;

import com.nostra13.universalimageloader.core.assist.c;
import com.nostra13.universalimageloader.core.c.a;
import com.nostra13.universalimageloader.core.d.b;
import java.util.concurrent.locks.ReentrantLock;

// compiled from: ImageLoadingInfo.java
final class h {
    final String a;
    final String b;
    final a c;
    final c d;
    final c e;
    final a f;
    final b g;
    final ReentrantLock h;

    public h(String str, a aVar, c cVar, String str2, c cVar2, a aVar2, ReentrantLock reentrantLock) {
        this.a = str;
        this.c = aVar;
        this.d = cVar;
        this.e = cVar2;
        this.f = aVar2;
        this.g = null;
        this.h = reentrantLock;
        this.b = str2;
    }
}
