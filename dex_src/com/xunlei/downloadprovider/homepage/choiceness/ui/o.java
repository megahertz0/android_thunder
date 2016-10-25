package com.xunlei.downloadprovider.homepage.choiceness.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

// compiled from: HomeChoicenessFragment.java
final class o extends BroadcastReceiver {
    final /* synthetic */ HomeChoicenessFragment a;

    o(HomeChoicenessFragment homeChoicenessFragment) {
        this.a = homeChoicenessFragment;
    }

    public final void onReceive(Context context, Intent intent) {
        if (this.a.l != null) {
            this.a.l.a();
        }
    }
}
