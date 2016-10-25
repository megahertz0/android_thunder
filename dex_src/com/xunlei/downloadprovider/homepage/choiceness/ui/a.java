package com.xunlei.downloadprovider.homepage.choiceness.ui;

import com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter;
import com.xunlei.downloadprovider.player.ab;

// compiled from: ChoicenessAutoPlayItem.java
final class a implements com.xunlei.downloadprovider.player.MediaPlayerGestureView.a {
    final /* synthetic */ ab a;
    final /* synthetic */ ChoicenessAutoPlayItem b;

    a(ChoicenessAutoPlayItem choicenessAutoPlayItem, ab abVar) {
        this.b = choicenessAutoPlayItem;
        this.a = abVar;
    }

    public final void onClick() {
        this.b.a();
        com.xunlei.downloadprovider.homepage.choiceness.a.a.a choicenessInfo = this.b.getChoicenessInfo();
        ChoicenessReporter.a(choicenessInfo.d, choicenessInfo.b, "pic", choicenessInfo.f());
        this.a.a(null);
    }
}
