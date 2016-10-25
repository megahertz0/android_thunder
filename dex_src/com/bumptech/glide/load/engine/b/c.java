package com.bumptech.glide.load.engine.b;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// compiled from: DiskCacheWriteLocker.java
final class c {
    final Map<com.bumptech.glide.load.b, a> a;
    final b b;

    // compiled from: DiskCacheWriteLocker.java
    private static class a {
        final Lock a;
        int b;

        private a() {
            this.a = new ReentrantLock();
        }
    }

    // compiled from: DiskCacheWriteLocker.java
    private static class b {
        final Queue<a> a;

        private b() {
            this.a = new ArrayDeque();
        }

        final a a() {
            a aVar;
            synchronized (this.a) {
                aVar = (a) this.a.poll();
            }
            return aVar == null ? new a() : aVar;
        }
    }

    c() {
        this.a = new HashMap();
        this.b = new b();
    }

    final void a(com.bumptech.glide.load.b bVar) {
        a aVar;
        synchronized (this) {
            try {
                aVar = (a) this.a.get(bVar);
                if (aVar == null || aVar.b <= 0) {
                    int i;
                    StringBuilder append = new StringBuilder("Cannot release a lock that is not held, key: ").append(bVar).append(", interestedThreads: ");
                    if (aVar == null) {
                        i = 0;
                    } else {
                        i = aVar.b;
                    }
                    throw new IllegalArgumentException(append.append(i).toString());
                }
                int i2 = aVar.b - 1;
                aVar.b = i2;
                if (i2 == 0) {
                    a aVar2 = (a) this.a.remove(bVar);
                    if (aVar2.equals(aVar)) {
                        b bVar2 = this.b;
                        synchronized (bVar2.a) {
                            if (bVar2.a.size() < 10) {
                                bVar2.a.offer(aVar2);
                            }
                        }
                    } else {
                        throw new IllegalStateException(new StringBuilder("Removed the wrong lock, expected to remove: ").append(aVar).append(", but actually removed: ").append(aVar2).append(", key: ").append(bVar).toString());
                    }
                }
            } catch (Throwable th) {
            }
        }
        aVar.a.unlock();
    }
}
