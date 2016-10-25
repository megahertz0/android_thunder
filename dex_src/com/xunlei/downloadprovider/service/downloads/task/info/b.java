package com.xunlei.downloadprovider.service.downloads.task.info;

import java.io.Serializable;

// compiled from: TaskCountsStatistics.java
public final class b implements Serializable {
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;

    public b() {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
    }

    public final int a() {
        return (this.c + this.d) + this.e;
    }

    public final String toString() {
        return new StringBuilder("TaskCountsStatistics{Total=").append(this.a).append(", Success=").append(this.b).append(", Running=").append(this.c).append(", Paused=").append(this.d).append(", Failed=").append(this.e).append('}').toString();
    }
}
