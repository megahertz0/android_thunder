package com.tencent.wxop.stat;

import com.umeng.a;

public class StatGameUser implements Cloneable {
    private String a;
    private String b;
    private String c;

    public StatGameUser() {
        this.a = a.d;
        this.b = a.d;
        this.c = a.d;
    }

    public StatGameUser(String str, String str2, String str3) {
        this.a = a.d;
        this.b = a.d;
        this.c = a.d;
        this.b = str;
        this.a = str2;
        this.c = str3;
    }

    public StatGameUser clone() {
        try {
            return (StatGameUser) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public String getAccount() {
        return this.b;
    }

    public String getLevel() {
        return this.c;
    }

    public String getWorldName() {
        return this.a;
    }

    public void setAccount(String str) {
        this.b = str;
    }

    public void setLevel(String str) {
        this.c = str;
    }

    public void setWorldName(String str) {
        this.a = str;
    }

    public String toString() {
        return new StringBuilder("StatGameUser [worldName=").append(this.a).append(", account=").append(this.b).append(", level=").append(this.c).append("]").toString();
    }
}
