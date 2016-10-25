package com.umeng.fb;

import com.umeng.fb.model.Store;
import com.umeng.fb.net.a;

class FeedbackAgent$3 implements Runnable {
    final /* synthetic */ FeedbackAgent a;

    FeedbackAgent$3(FeedbackAgent feedbackAgent) {
        this.a = feedbackAgent;
    }

    public void run() {
        new a(FeedbackAgent.a(this.a)).a(Store.getInstance(FeedbackAgent.a(this.a)).getUserInfo().toJson());
    }
}
