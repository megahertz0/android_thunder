package com.xunlei.downloadprovider.homepage.interest.ui;

import android.view.View;
import android.view.View.OnClickListener;

// compiled from: InterestPickerActivity.java
final class d implements OnClickListener {
    final /* synthetic */ InterestPickerActivity a;

    d(InterestPickerActivity interestPickerActivity) {
        this.a = interestPickerActivity;
    }

    public final void onClick(View view) {
        this.a.onBackPressed();
    }
}
