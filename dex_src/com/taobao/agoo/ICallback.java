package com.taobao.agoo;

// compiled from: Taobao
public abstract class ICallback {
    public String extra;

    public abstract void onFailure(String str, String str2);

    public abstract void onSuccess();
}
