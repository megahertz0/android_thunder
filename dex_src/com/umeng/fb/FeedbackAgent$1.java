package com.umeng.fb;

import com.umeng.fb.net.a;

class FeedbackAgent$1 extends Thread {
    final /* synthetic */ FeedbackAgent a;

    FeedbackAgent$1(FeedbackAgent feedbackAgent) {
        this.a = feedbackAgent;
    }

    public void run() {
        new a(FeedbackAgent.a(this.a)).a();
    }
}
