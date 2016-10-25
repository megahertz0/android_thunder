package com.xunlei.downloadprovider.personal.playrecord;

import android.content.Intent;
import com.xunlei.downloadprovider.model.g;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.downloadprovider.url.DownData;
import com.xunlei.downloadprovider.vod.VodUtil;
import com.xunlei.downloadprovider.web.BrowserUtil.StartFromType;
import com.xunlei.xllib.R;
import com.xunlei.xllib.b.d;
import java.util.List;

// compiled from: PlayRecordFragment.java
final class i implements PlayRecordFragment$b {
    final /* synthetic */ PlayRecordFragment a;

    i(PlayRecordFragment playRecordFragment) {
        this.a = playRecordFragment;
    }

    public final void a(List<DownData> list, StartFromType startFromType) {
        if ((this.a.getActivity() instanceof ThunderTask) && !d.a(list)) {
            ((ThunderTask) this.a.getActivity()).createTasks(list, PlayRecordFragment.h(this.a), R.styleable.AppCompatTheme_actionModeBackground, startFromType);
            list.get(0);
        }
    }

    public final void a(DownData downData, g gVar) {
        if (this.a.getActivity() instanceof ThunderTask) {
            ((ThunderTask) this.a.getActivity()).createTask(downData, PlayRecordFragment.h(this.a), gVar, false);
        }
    }

    public final void a(com.xunlei.downloadprovider.vod.protocol.i iVar) {
        VodUtil.a();
        VodUtil.a(this.a.getActivity(), iVar);
    }

    public final void a() {
        PlayRecordFragment.j(this.a);
    }

    public final void a(boolean z) {
        PlayRecordFragment.a(this.a, z);
    }

    public final void a(CharSequence charSequence) {
        if (!PlayRecordFragment.k(this.a)) {
            if (PlayRecordFragment.l(this.a)) {
                PlayRecordFragment.m(this.a);
            } else {
                PlayRecordFragment.a(this.a, charSequence);
            }
        }
    }

    public final void b() {
        PlayRecordFragment.n(this.a);
    }

    public final void a(Intent intent) {
        if (this.a.getActivity() != null) {
            this.a.getActivity().startActivity(intent);
        }
    }

    public final void c() {
        this.a.a();
    }
}
