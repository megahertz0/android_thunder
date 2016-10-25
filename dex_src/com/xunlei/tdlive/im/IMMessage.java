package com.xunlei.tdlive.im;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.tdlive.modal.JsonWrapper;
import org.android.agoo.common.AgooConstants;

public class IMMessage {
    private JsonWrapper a;
    private String b;
    private int c;

    public static class Builder {
        private static long sId;
        private JsonWrapper mParams;
        private int mQos;
        private String mTopic;

        static {
            sId = 1;
        }

        public Builder(String str) {
            this(SocializeProtocolConstants.PROTOCOL_KEY_DEFAULT, str);
        }

        public Builder(String str, String str2) {
            this(str, str2, 1);
        }

        public Builder(String str, String str2, int i) {
            this.mQos = i;
            this.mTopic = str;
            this.mParams = new JsonWrapper("{}");
            this.mParams.putString("cmd", str2);
        }

        public com.xunlei.tdlive.im.IMMessage.Builder putParam(String str, int i) {
            this.mParams.putInt(str, i);
            return this;
        }

        public com.xunlei.tdlive.im.IMMessage.Builder putParam(String str, long j) {
            this.mParams.putLong(str, j);
            return this;
        }

        public com.xunlei.tdlive.im.IMMessage.Builder putParam(String str, String str2) {
            this.mParams.putString(str, str2);
            return this;
        }

        public IMMessage build() {
            IMMessage iMMessage = new IMMessage();
            iMMessage.c = this.mQos;
            iMMessage.b = this.mTopic;
            iMMessage.a = this.mParams;
            long j = sId;
            sId = 1 + j;
            iMMessage.a.putLong("msgid", j);
            iMMessage.a.putLong(AgooConstants.MESSAGE_TIME, System.currentTimeMillis());
            return iMMessage;
        }
    }

    private IMMessage() {
    }

    public String a() {
        return this.b;
    }

    public int b() {
        return this.c;
    }

    public String c() {
        return this.a.toString();
    }
}
