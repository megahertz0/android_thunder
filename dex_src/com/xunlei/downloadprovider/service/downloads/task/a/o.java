package com.xunlei.downloadprovider.service.downloads.task.a;

import android.database.Observable;
import com.xunlei.downloadprovider.service.downloads.task.k;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// compiled from: TaskObservable.java
public final class o extends Observable<k> {
    public final void a(Collection<Long> collection) {
        if (!this.mObservers.isEmpty()) {
            Iterator it = new ArrayList(this.mObservers).iterator();
            while (it.hasNext()) {
                ((k) it.next()).a(collection);
            }
        }
    }

    public final void b(Collection<Long> collection) {
        if (!this.mObservers.isEmpty()) {
            Iterator it = new ArrayList(this.mObservers).iterator();
            while (it.hasNext()) {
                ((k) it.next()).b(collection);
            }
        }
    }
}
