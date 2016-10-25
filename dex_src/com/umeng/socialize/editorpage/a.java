package com.umeng.socialize.editorpage;

import com.umeng.socialize.editorpage.KeyboardListenRelativeLayout.IOnKeyboardStateChangedListener;
import com.umeng.socialize.utils.Log;

// compiled from: ShareActivity.java
class a implements IOnKeyboardStateChangedListener {
    final /* synthetic */ ShareActivity a;

    a(ShareActivity shareActivity) {
        this.a = shareActivity;
    }

    public void a(int i) {
        ShareActivity.a(this.a, i);
        Log.d("ShareActivity", new StringBuilder("onKeyboardStateChanged  now state is ").append(i).toString());
    }
}
