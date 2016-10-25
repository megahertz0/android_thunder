package com.xunlei.downloadprovider.homepage.interest.ui;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter;

// compiled from: InterestHeaderView.java
public final class b implements OnClickListener {
    final /* synthetic */ Context a;

    public b(Context context) {
        this.a = context;
    }

    public final void onClick(View view) {
        ChoicenessReporter.d();
        this.a.startActivity(new Intent(this.a, InterestPickerActivity.class));
    }
}
