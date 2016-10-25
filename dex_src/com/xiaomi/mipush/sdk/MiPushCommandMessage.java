package com.xiaomi.mipush.sdk;

import com.alipay.sdk.util.h;
import java.util.List;

public class MiPushCommandMessage implements a {
    private static final long serialVersionUID = 1;
    private String category;
    private String command;
    private List<String> commandArguments;
    private String reason;
    private long resultCode;

    public String getCategory() {
        return this.category;
    }

    public String getCommand() {
        return this.command;
    }

    public List<String> getCommandArguments() {
        return this.commandArguments;
    }

    public String getReason() {
        return this.reason;
    }

    public long getResultCode() {
        return this.resultCode;
    }

    public void setCategory(String str) {
        this.category = str;
    }

    public void setCommand(String str) {
        this.command = str;
    }

    public void setCommandArguments(List<String> list) {
        this.commandArguments = list;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public void setResultCode(long j) {
        this.resultCode = j;
    }

    public String toString() {
        return new StringBuilder("command={").append(this.command).append("}, resultCode={").append(this.resultCode).append("}, reason={").append(this.reason).append("}, category={").append(this.category).append("}, commandArguments={").append(this.commandArguments).append(h.d).toString();
    }
}
