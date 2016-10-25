package com.xunlei.common.member.act;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.JsResult;
import com.xunlei.common.member.act.XLXmBindActivity.XLWebChromeClient;

class XLXmBindActivity$XLWebChromeClient$1 implements OnClickListener {
    final /* synthetic */ XLWebChromeClient this$1;
    final /* synthetic */ JsResult val$result;

    XLXmBindActivity$XLWebChromeClient$1(XLWebChromeClient xLWebChromeClient, JsResult jsResult) {
        this.this$1 = xLWebChromeClient;
        this.val$result = jsResult;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.val$result.confirm();
    }
}
