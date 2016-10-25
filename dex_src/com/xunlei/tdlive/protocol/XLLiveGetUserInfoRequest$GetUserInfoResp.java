package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.protocol.ProtocolData.WidthDrawDetails;
import com.xunlei.tdlive.protocol.XLLiveGetUserInfoRequest.GetUserInfoResp.ExtData;
import com.xunlei.tdlive.protocol.XLLiveRequest.XLLiveRespBase;

public class XLLiveGetUserInfoRequest$GetUserInfoResp extends XLLiveRespBase {
    public Data data;

    public static class Data {
        public String avatar;
        public int current_coin;
        public WidthDrawDetails exchange_info;
        public ExtData ext;
        public int fans_num;
        public int follow_num;
        public int is_sign;
        public LevelInfo level;
        public int mail_num;
        public String nickname;
        public int sex;
        public String sign;
        public int total_record_num;
        public int used_coin;
        public int userid;
        public String uuid;

        public Data() {
            this.sex = 0;
        }

        public boolean isSigner() {
            return this.is_sign == 1;
        }

        public int getSend() {
            return this.used_coin;
        }

        public boolean sexEditable() {
            return this.ext != null && this.ext.sex_isup == 0;
        }

        public boolean voiceCallable() {
            return this.ext != null && this.ext.voicecall == 1;
        }
    }

    public static final class ExtData {
        public int sex_isup;
        public int voicecall;
    }
}
