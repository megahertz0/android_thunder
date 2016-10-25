package com.xunlei.downloadprovider.homepage.relax;

import android.os.Bundle;
import android.os.Message;
import com.xunlei.downloadprovider.homepage.relax.RelaxDataManager.GuestureType;
import com.xunlei.downloadprovider.homepage.relax.RelaxDataManager.RelaxDataType;
import com.xunlei.downloadprovider.homepage.relax.b.a.a;
import com.xunlei.downloadprovider.model.protocol.b.d;
import com.xunlei.tdlive.im.ChatMessage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// compiled from: RelaxListFragment.java
final class c implements a {
    final /* synthetic */ RelaxListFragment a;

    c(RelaxListFragment relaxListFragment) {
        this.a = relaxListFragment;
    }

    public final void a(int i, RelaxDataType relaxDataType, GuestureType guestureType, List<d> list) {
        if (list == null) {
            list = new ArrayList();
        }
        Serializable cVar = new com.xunlei.downloadprovider.homepage.relax.b.c(i, relaxDataType, guestureType, new ArrayList(list));
        Bundle bundle = new Bundle();
        bundle.putSerializable("relaxListResult", cVar);
        Message obtainMessage = RelaxListFragment.b(this.a).obtainMessage(ChatMessage.FLAG_SYS_NOTIFY);
        obtainMessage.setData(bundle);
        obtainMessage.sendToTarget();
    }
}
