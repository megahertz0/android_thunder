package com.alipay.tscenter.biz.rpc.vkeydfp.result;

import java.io.Serializable;

public class BaseResult implements Serializable {
    public String resultCode;
    public boolean success;

    public BaseResult() {
        this.success = false;
    }
}
