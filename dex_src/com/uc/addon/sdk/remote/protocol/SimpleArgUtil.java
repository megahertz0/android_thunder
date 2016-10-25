package com.uc.addon.sdk.remote.protocol;

public class SimpleArgUtil {
    public static SimpleArg buildArg(Object obj) {
        SimpleArg simpleArg = new SimpleArg();
        simpleArg.value = obj;
        return simpleArg;
    }
}
