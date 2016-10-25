package com.xunlei.downloadprovider.homepage.interest.ui;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

// compiled from: InterestPickerActivity.java
final class g implements OnCheckedChangeListener {
    final /* synthetic */ InterestPickerActivity a;

    g(InterestPickerActivity interestPickerActivity) {
        this.a = interestPickerActivity;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        boolean z2 = true;
        if (z) {
            InterestPickerActivity.e(this.a).setEnabled(true);
            return;
        }
        for (int i = 1; i <= 15; i++) {
            if (InterestPickerActivity.f(this.a)[i].isChecked()) {
                break;
            }
        }
        z2 = false;
        InterestPickerActivity.e(this.a).setEnabled(z2);
    }
}
