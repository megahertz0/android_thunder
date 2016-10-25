package com.bumptech.glide.manager;

import com.bumptech.glide.f.b;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

// compiled from: RequestTracker.java
public final class n {
    public final Set<b> a;
    public final List<b> b;
    public boolean c;

    public n() {
        this.a = Collections.newSetFromMap(new WeakHashMap());
        this.b = new ArrayList();
    }
}
