package com.xunlei.downloadprovider.homepage.choiceness.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter;
import com.xunlei.downloadprovider.homepage.choiceness.a.a.a;

// compiled from: ChoicenessAutoPlayItem.java
final class b implements OnClickListener {
    final /* synthetic */ ChoicenessAutoPlayItem a;

    b(ChoicenessAutoPlayItem choicenessAutoPlayItem) {
        this.a = choicenessAutoPlayItem;
    }

    public final void onClick(View view) {
        this.a.a();
        a choicenessInfo = this.a.getChoicenessInfo();
        ChoicenessReporter.a(choicenessInfo.d, choicenessInfo.b, "pic", choicenessInfo.f());
    }
}
