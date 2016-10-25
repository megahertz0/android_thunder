package com.xunlei.downloadprovider.web.browser;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.xunlei.downloadprovider.R;

// compiled from: BrowserTitleBarFragment.java
final class y implements OnEditorActionListener {
    final /* synthetic */ BrowserTitleBarFragment a;

    y(BrowserTitleBarFragment browserTitleBarFragment) {
        this.a = browserTitleBarFragment;
    }

    public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 3) {
            return false;
        }
        Object trim = textView.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            Toast.makeText(this.a.getActivity(), R.string.browser_input_empty_tip, 0).show();
        } else if (BrowserTitleBarFragment.a(this.a) != null) {
            BrowserTitleBarFragment.a(this.a).a(trim);
        }
        return true;
    }
}
