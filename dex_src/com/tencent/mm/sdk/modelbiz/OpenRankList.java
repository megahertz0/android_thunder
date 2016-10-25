package com.tencent.mm.sdk.modelbiz;

import com.tencent.mm.sdk.modelbase.BaseReq;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class OpenRankList {

    public static class Req extends BaseReq {
        public boolean checkArgs() {
            return true;
        }

        public int getType() {
            return XZBDevice.Success;
        }
    }
}
