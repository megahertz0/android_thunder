package com.xunlei.tdlive.protocol;

public class ProtocolData {

    public static final class WidthDrawDetails {
        public int allow;
        public String current_gold_coin;
        public String error;
        public int exchange_money;
        public int money;
        public int pay_coin;

        public final boolean allowed() {
            return this.allow == 1;
        }
    }
}
