package com.xunlei.tdlive.user;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.xunlei.common.member.XLUserUtil;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.a.a;
import com.xunlei.tdlive.util.a.b;

// compiled from: UserHelper.java
class h extends b<View> {
    final /* synthetic */ f a;

    h(f fVar) {
        this.a = fVar;
    }

    public void a(View view, String str, Bitmap bitmap, a aVar) {
        XLUserUtil.getInstance().userSetAvatar(bitmap, null, "set-avatar");
    }

    public void a(View view, String str, Drawable drawable) {
        XLog.e("UserHelper", "load avatar failed.");
    }
}
