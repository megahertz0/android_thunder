package com.xunlei.downloadprovider.player;

// compiled from: VideoInfo.java
public final class ai {
    String a;
    String b;
    public String c;
    public String d;
    public boolean e;
    boolean f;
    public int g;
    public a h;
    public String i;
    public String j;
    public String k;

    public ai(String str, String str2, String str3) {
        this(str, str2, str3, null);
    }

    public ai(String str, String str2, String str3, String str4) {
        this.f = true;
        this.c = str;
        this.a = str2;
        this.b = str3;
        this.i = str4;
    }

    public final String toString() {
        return new StringBuilder("VideoInfo{startPosition=").append(this.g).append(", sourceUrl='").append(this.a).append('\'').append(", title='").append(this.b).append('\'').append(", movieId='").append(this.c).append('\'').append(", gcid='").append(this.d).append('\'').append(", playSilence=").append(this.e).append(", shouldInsertRecord=").append(this.f).append('}').toString();
    }
}
