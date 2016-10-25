package com.qq.e.comm.net;

import com.qq.e.comm.net.rr.Request;
import com.qq.e.comm.net.rr.Response;
import java.util.concurrent.Future;

public interface NetworkClient {

    public enum Priority {
        High(1),
        Mid(2),
        Low(3);
        private int a;

        static {
            High = new com.qq.e.comm.net.NetworkClient.Priority("High", 0, 1);
            Mid = new com.qq.e.comm.net.NetworkClient.Priority("Mid", 1, 2);
            Low = new com.qq.e.comm.net.NetworkClient.Priority("Low", 2, 3);
            b = new com.qq.e.comm.net.NetworkClient.Priority[]{High, Mid, Low};
        }

        private Priority(int i) {
            this.a = i;
        }

        public final int value() {
            return this.a;
        }
    }

    Future<Response> submit(Request request);

    Future<Response> submit(Request request, Priority priority);

    void submit(Request request, NetworkCallBack networkCallBack);

    void submit(Request request, Priority priority, NetworkCallBack networkCallBack);
}
