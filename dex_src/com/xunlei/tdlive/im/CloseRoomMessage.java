package com.xunlei.tdlive.im;

public class CloseRoomMessage extends BaseMessage {
    public Data data;
    public String roomid;

    public static class Data {
        public int current_income;
        public int current_point;
        public int current_user;
        public String end_time;
        public String start_time;
        public int status;
    }

    public CloseRoomMessage() {
        this.data = new Data();
    }
}
