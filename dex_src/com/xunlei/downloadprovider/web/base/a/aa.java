package com.xunlei.downloadprovider.web.base.a;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.xunlei.xllib.R;

// compiled from: RecommendHeaderViewHolder.java
final class aa implements OnCheckedChangeListener {
    final /* synthetic */ z a;

    aa(z zVar) {
        this.a = zVar;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z.a(this.a) != null) {
            z.a(this.a).a(z.b(this.a), R.styleable.Toolbar_maxButtonHeight, Boolean.valueOf(z));
        }
    }
}
