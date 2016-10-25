package com.xunlei.downloadprovider.homepage.relax;

import android.os.Message;
import com.xunlei.downloadprovider.homepage.relax.b.c;
import com.xunlei.tdlive.im.ChatMessage;

// compiled from: RelaxListFragment.java
final class a implements com.xunlei.downloadprovider.a.h.a {
    final /* synthetic */ RelaxListFragment a;

    a(RelaxListFragment relaxListFragment) {
        this.a = relaxListFragment;
    }

    public final void a(Message message) {
        switch (message.what) {
            case ChatMessage.FLAG_SYS_NOTIFY:
                RelaxListFragment.a(this.a, (c) message.getData().getSerializable("relaxListResult"));
            case 3109:
                RelaxListFragment.a(this.a, message);
            case 3110:
                RelaxListFragment.a(message);
            default:
                break;
        }
    }
}
