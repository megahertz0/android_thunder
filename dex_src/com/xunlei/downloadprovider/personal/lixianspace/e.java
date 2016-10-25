package com.xunlei.downloadprovider.personal.lixianspace;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.xunlei.common.lixian.XLLixianUtil;
import com.xunlei.downloadprovider.personal.lixianspace.widget.LixianSpaceListWidget;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: LixianSpaceFragment.java
final class e implements OnClickListener {
    final /* synthetic */ boolean a;
    final /* synthetic */ LixianSpaceFragment b;

    e(LixianSpaceFragment lixianSpaceFragment) {
        this.b = lixianSpaceFragment;
        this.a = true;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        LixianSpaceListWidget m = LixianSpaceFragment.m(this.b);
        boolean z = this.a;
        if (!m.d) {
            m.g.setType(SimpleLog.LOG_LEVEL_DEBUG);
            m.g.a();
            m.d = true;
            m.c = new Object();
            if (!m.b.isEmpty()) {
                int size = m.b.size();
                long[] jArr = new long[size];
                int i2 = 0;
                for (Long l : m.b) {
                    int i3 = i2 + 1;
                    jArr[i2] = l.longValue();
                    i2 = i3;
                }
                if (size == 1) {
                    XLLixianUtil.getInstance().deleteTaskToTrash(jArr[0], m.c, m.i);
                } else {
                    XLLixianUtil.getInstance().deleteTasksToTrash(jArr, m.c, m.i);
                }
            }
            if (z) {
                m.b.clear();
            }
        }
        LixianSpaceFragment.z(this.b).dismiss();
        LixianSpaceFragment.A(this.b);
    }
}
