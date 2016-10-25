package org.android.spdy;

import java.util.Random;
import java.util.TreeSet;

public class SpdyBytePool {
    private static volatile SpdyBytePool gInstance;
    private static Object lock;
    private static Random rand;
    private TreeSet<SpdyByteArray> pool;
    private long reused;
    private SpdyByteArray std;

    static {
        lock = new Object();
        gInstance = null;
        rand = new Random();
    }

    private SpdyBytePool() {
        this.pool = null;
        this.std = new SpdyByteArray();
        this.reused = 0;
        this.pool = new TreeSet();
    }

    public static SpdyBytePool getInstance() {
        if (gInstance == null) {
            synchronized (lock) {
                if (gInstance == null) {
                    gInstance = new SpdyBytePool();
                }
            }
        }
        return gInstance;
    }

    SpdyByteArray getSpdyByteArray(int i) {
        SpdyByteArray spdyByteArray;
        synchronized (lock) {
            this.std.length = i;
            spdyByteArray = (SpdyByteArray) this.pool.ceiling(this.std);
            if (spdyByteArray == null) {
                spdyByteArray = new SpdyByteArray(i);
            } else {
                this.pool.remove(spdyByteArray);
                this.reused += (long) i;
            }
        }
        spduLog.Logi("libeasy", new StringBuilder("getSpdyByteArray: ").append(spdyByteArray).toString());
        spduLog.Logi("libeasy", new StringBuilder("reused: ").append(this.reused).toString());
        return spdyByteArray;
    }

    void recycle(SpdyByteArray spdyByteArray) {
        synchronized (lock) {
            this.pool.add(spdyByteArray);
            while (this.pool.size() > 100) {
                if (rand.nextBoolean()) {
                    this.pool.pollFirst();
                } else {
                    this.pool.pollLast();
                }
            }
        }
    }
}
