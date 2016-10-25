package com.tencent.open.web.security;

import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebView;
import com.sina.weibo.sdk.component.GameManager;
import com.tencent.open.a;
import com.tencent.open.a.f;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// compiled from: ProGuard
public class b extends a {
    public void a(String str, String str2, List<String> list, a.a aVar) {
        f.a("openSDK_LOG.SecureJsBridge", new StringBuilder("-->getResult, objectName: ").append(str).append(" | methodName: ").append(str2).toString());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.set(i, URLDecoder.decode((String) list.get(i), GameManager.DEFAULT_CHARSET));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        com.tencent.open.a.b bVar = (com.tencent.open.a.b) this.a.get(str);
        if (bVar != null) {
            f.b("openSDK_LOG.SecureJsBridge", "-->handler != null");
            bVar.call(str2, list, aVar);
            return;
        }
        f.b("openSDK_LOG.SecureJsBridge", "-->handler == null");
        if (aVar != null) {
            aVar.a();
        }
    }

    public boolean a(WebView webView, String str) {
        List list = null;
        f.a("openSDK_LOG.SecureJsBridge", new StringBuilder("-->canHandleUrl---url = ").append(str).toString());
        if (str == null) {
            return false;
        }
        if (!Uri.parse(str).getScheme().equals("jsbridge")) {
            return false;
        }
        ArrayList arrayList = new ArrayList(Arrays.asList((str + "/#").split("/")));
        if (arrayList.size() < 7) {
            return false;
        }
        String str2 = (String) arrayList.get(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        String str3 = (String) arrayList.get(XZBDevice.DOWNLOAD_LIST_FAILED);
        String str4 = (String) arrayList.get(XZBDevice.DOWNLOAD_LIST_ALL);
        String str5 = (String) arrayList.get(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
        f.a("openSDK_LOG.SecureJsBridge", new StringBuilder("-->canHandleUrl, objectName: ").append(str2).append(" | methodName: ").append(str3).append(" | snStr: ").append(str4).toString());
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
            return false;
        }
        try {
            a.a cVar = new c(webView, Long.parseLong(str4), str, str5);
            list = arrayList.subList(R.styleable.Toolbar_contentInsetEnd, arrayList.size() - 1);
            a(str2, str3, list, cVar);
            return true;
        } catch (Exception e) {
            return list;
        }
    }
}
