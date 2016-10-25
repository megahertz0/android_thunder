package com.xunlei.downloadprovider.discovery;

import android.os.Message;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.xllib.R;

// compiled from: DiscoveryFragment.java
final class b implements a {
    final /* synthetic */ DiscoveryFragment a;

    b(DiscoveryFragment discoveryFragment) {
        this.a = discoveryFragment;
    }

    public final void a(Message message) {
        switch (message.what) {
            case R.styleable.AppCompatTheme_buttonStyleSmall:
                DiscoveryFragment.a(this.a, message);
            case R.styleable.AppCompatTheme_checkboxStyle:
                DiscoveryFragment.b(this.a, message);
            default:
                break;
        }
    }
}
