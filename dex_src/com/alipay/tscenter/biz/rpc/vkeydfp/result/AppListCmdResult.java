package com.alipay.tscenter.biz.rpc.vkeydfp.result;

import java.io.Serializable;

public class AppListCmdResult extends AppListResult implements Serializable {
    public boolean needRetry;

    public AppListCmdResult() {
        this.needRetry = false;
    }
}
