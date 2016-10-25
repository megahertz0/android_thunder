package com.umeng.socialize.bean;

import java.io.Serializable;

public class UMFriend implements Serializable {
    private static final long a = 1;
    private int b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private PinYin h;
    private long i;
    private boolean j;
    public char mGroup;

    public static class PinYin implements Serializable {
        private static final long serialVersionUID = 1;
        public String mInitial;
        public String mTotalPinyin;
    }

    public UMFriend() {
        this.j = true;
    }

    public int getId() {
        return this.b;
    }

    public void setId(int i) {
        this.b = i;
    }

    public String getFid() {
        return this.c;
    }

    public String getLinkName() {
        return this.e;
    }

    public void setLinkName(String str) {
        this.e = str;
    }

    public void setFid(String str) {
        this.c = str;
    }

    public String getName() {
        return this.d;
    }

    public void setName(String str) {
        this.d = str;
    }

    public String getIcon() {
        return this.f;
    }

    public void setIcon(String str) {
        this.f = str;
    }

    public String getUsid() {
        return this.g;
    }

    public void setUsid(String str) {
        this.g = str;
    }

    public long getLastAtTime() {
        return this.i;
    }

    public void setLastAtTime(long j) {
        this.i = j;
    }

    public boolean isAlive() {
        return this.j;
    }

    public void setAlive(boolean z) {
        this.j = z;
    }

    public PinYin getPinyin() {
        return this.h;
    }

    public void setPinyin(PinYin pinYin) {
        this.h = pinYin;
        if (pinYin != null) {
            this.mGroup = pinYin.mInitial.charAt(0);
        }
    }

    public final boolean isUpdate(UMFriend uMFriend) {
        if (uMFriend == null) {
            return false;
        }
        if (uMFriend.getLastAtTime() > this.i) {
            return true;
        }
        if (uMFriend.isAlive() != isAlive()) {
            return true;
        }
        if (uMFriend.getName().equals(this.d)) {
            return (uMFriend.getIcon() == null || uMFriend.getIcon().equals(this.f)) ? false : true;
        } else {
            return true;
        }
    }

    public char upGroup() {
        if (this.mGroup == '\u0000' && this.h != null) {
            this.mGroup = this.h.mInitial.charAt(0);
        }
        if (this.mGroup != '\u0000') {
            char c = this.mGroup;
            if ('@' < c && c < '[') {
                return c;
            }
            if (('`' < c && c < '{') || c == "\u5e38".charAt(0)) {
                return c;
            }
        }
        return "\u7b26".charAt(0);
    }

    public boolean isEquals(String str) {
        return str != null && str.equals(Character.valueOf(this.mGroup));
    }
}
