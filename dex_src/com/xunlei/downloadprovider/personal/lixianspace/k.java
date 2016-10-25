package com.xunlei.downloadprovider.personal.lixianspace;

import android.content.Intent;
import android.text.TextUtils;
import com.xunlei.downloadprovider.model.g;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.downloadprovider.url.DownData;
import com.xunlei.downloadprovider.vod.VodUtil;
import com.xunlei.downloadprovider.vod.protocol.i;
import com.xunlei.xllib.R;

// compiled from: LixianSpaceFragment.java
final class k implements LixianSpaceFragment$d {
    final /* synthetic */ LixianSpaceFragment a;

    k(LixianSpaceFragment lixianSpaceFragment) {
        this.a = lixianSpaceFragment;
    }

    public final void a(DownData downData, g gVar) {
        if (this.a.getActivity() instanceof ThunderTask) {
            ((ThunderTask) this.a.getActivity()).createTask(downData, LixianSpaceFragment.r(this.a), gVar, false);
            if (!TextUtils.isEmpty(downData.e)) {
                LixianSpaceFragment.s(this.a).add(downData.e);
            }
        }
    }

    public final void a(i iVar) {
        VodUtil.a();
        VodUtil.a(this.a.getActivity(), iVar);
    }

    public final void a(long j, TaskInfo taskInfo) {
        if ((this.a.getActivity() instanceof ThunderTask) && j != -1) {
            ((ThunderTask) this.a.getActivity()).handleTaskOperator(R.styleable.AppCompatTheme_buttonStyleSmall, 102409, j, taskInfo);
        }
    }

    public final void a() {
        LixianSpaceFragment.d(this.a);
    }

    public final void a(boolean z) {
        LixianSpaceFragment.b(this.a, z);
    }

    public final void a(CharSequence charSequence) {
        if (!LixianSpaceFragment.b(this.a)) {
            if (LixianSpaceFragment.t(this.a)) {
                LixianSpaceFragment.u(this.a);
            } else {
                LixianSpaceFragment.a(this.a, charSequence);
            }
        }
    }

    public final void b() {
        LixianSpaceFragment.v(this.a);
    }

    public final void a(Intent intent) {
        if (this.a.getActivity() != null) {
            this.a.getActivity().startActivity(intent);
        }
    }
}
