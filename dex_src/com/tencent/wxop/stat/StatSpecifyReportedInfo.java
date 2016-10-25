package com.tencent.wxop.stat;

public class StatSpecifyReportedInfo {
    private String a;
    private String b;
    private String c;
    private boolean d;
    private boolean e;

    public StatSpecifyReportedInfo() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = false;
        this.e = false;
    }

    public String getAppKey() {
        return this.a;
    }

    public String getInstallChannel() {
        return this.b;
    }

    public String getVersion() {
        return this.c;
    }

    public boolean isImportant() {
        return this.e;
    }

    public boolean isSendImmediately() {
        return this.d;
    }

    public void setAppKey(String str) {
        this.a = str;
    }

    public void setImportant(boolean z) {
        this.e = z;
    }

    public void setInstallChannel(String str) {
        this.b = str;
    }

    public void setSendImmediately(boolean z) {
        this.d = z;
    }

    public void setVersion(String str) {
        this.c = str;
    }

    public String toString() {
        return new StringBuilder("StatSpecifyReportedInfo [appKey=").append(this.a).append(", installChannel=").append(this.b).append(", version=").append(this.c).append(", sendImmediately=").append(this.d).append(", isImportant=").append(this.e).append("]").toString();
    }
}
