package com.xunlei.downloadprovider.homepage.choiceness;

import android.widget.ImageView;
import com.bumptech.glide.f.d;
import com.bumptech.glide.load.resource.a.b;
import com.xunlei.xllib.R;

// compiled from: ChoicenessUtils.java
final class e implements d<String, b> {
    final /* synthetic */ ImageView a;

    e(ImageView imageView) {
        this.a = imageView;
    }

    public final /* synthetic */ boolean a(Object obj) {
        this.a.setTag(R.id.icon, (String) obj);
        return false;
    }
}
