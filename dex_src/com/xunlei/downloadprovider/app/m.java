package com.xunlei.downloadprovider.app;

import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.xunlei.tdlive.sdk.IHost;

// compiled from: GuideActivity.java
final class m extends Handler {
    final /* synthetic */ GuideActivity a;

    m(GuideActivity guideActivity) {
        this.a = guideActivity;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case IHost.HOST_NOFITY_REFRESH_LIST:
                int b = GuideActivity.b(this.a);
                Animation loadAnimation;
                Animation loadAnimation2;
                if (message.arg1 == 0) {
                    loadAnimation = AnimationUtils.loadAnimation(this.a.getApplicationContext(), 2131034156);
                    loadAnimation2 = AnimationUtils.loadAnimation(this.a.getApplicationContext(), 2131034155);
                    this.a.e.setInAnimation(loadAnimation);
                    this.a.e.setOutAnimation(loadAnimation2);
                    if (b < this.a.l) {
                        this.a.e.showNext();
                    }
                } else {
                    loadAnimation = AnimationUtils.loadAnimation(this.a.getApplicationContext(), 2131034154);
                    loadAnimation2 = AnimationUtils.loadAnimation(this.a.getApplicationContext(), 2131034157);
                    this.a.e.setInAnimation(loadAnimation);
                    this.a.e.setOutAnimation(loadAnimation2);
                    if (b > this.a.l) {
                        this.a.e.showPrevious();
                    }
                }
                break;
        }
        super.handleMessage(message);
    }
}
