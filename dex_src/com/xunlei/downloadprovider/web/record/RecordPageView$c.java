package com.xunlei.downloadprovider.web.record;

import android.os.Handler;
import android.os.Message;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.xllib.b.d;
import java.util.List;
import org.apache.commons.logging.impl.SimpleLog;

private class RecordPageView$c extends Handler {
    final /* synthetic */ RecordPageView a;

    private RecordPageView$c(RecordPageView recordPageView) {
        this.a = recordPageView;
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 25000:
                List list = (List) message.obj;
                if (d.a(list)) {
                    LoginHelper.a();
                    if (LoginHelper.c()) {
                        RecordPageView.f(this.a).clear();
                        RecordPageView.g(this.a);
                        if (!RecordPageView.d(this.a).equals("favor")) {
                            return;
                        }
                        if (RecordPageView.h(this.a) == 0) {
                            RecordPageView.a(this.a, new aa());
                            RecordPageView.j(this.a).a(RecordPageView.i(this.a), SimpleLog.LOG_LEVEL_DEBUG);
                            return;
                        }
                        RecordPageView.a(this.a, new aa());
                        RecordPageView.j(this.a).a(RecordPageView.i(this.a), RecordPageView.h(this.a));
                        return;
                    }
                    RecordPageView.f(this.a).clear();
                    RecordPageView.f(this.a).addAll(list);
                    RecordPageView.g(this.a);
                    return;
                }
                RecordPageView.f(this.a).clear();
                RecordPageView.f(this.a).addAll(list);
                RecordPageView.g(this.a);
                LoginHelper.a();
                if (LoginHelper.c() && RecordPageView.d(this.a).equals("favor") && list.size() > 0) {
                    RecordPageView.a(this.a, new aa());
                    RecordPageView.j(this.a).a(RecordPageView.i(this.a), RecordPageView.h(this.a));
                }
            default:
                break;
        }
    }
}
