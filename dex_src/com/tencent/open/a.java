package com.tencent.open;

import android.net.Uri;
import android.webkit.WebView;
import com.sina.weibo.sdk.component.GameManager;
import com.tencent.open.a.f;
import com.xunlei.downloadprovider.web.base.core.BaseJsInterface;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.android.spdy.SpdyAgent;

// compiled from: ProGuard
public class a {
    public HashMap<String, b> a;

    // compiled from: ProGuard
    public static class b {
        public void call(String str, List<String> list, com.tencent.open.a.a aVar) {
            Method[] declaredMethods = getClass().getDeclaredMethods();
            Method method = null;
            int length = declaredMethods.length;
            int i = 0;
            while (i < length) {
                try {
                    Method method2 = declaredMethods[i];
                    if (method2.getName().equals(str) && method2.getParameterTypes().length == list.size()) {
                        method = method2;
                        break;
                    }
                    i++;
                } catch (Throwable e) {
                    f.b("openSDK_LOG.JsBridge", new StringBuilder("-->handler call mehtod ex. targetMethod: ").append(method).toString(), e);
                    if (aVar != null) {
                        aVar.a();
                    }
                }
            }
            if (method != null) {
                Object invoke;
                switch (list.size()) {
                    case SpdyAgent.ACCS_TEST_SERVER:
                        invoke = method.invoke(this, new Object[0]);
                        break;
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        invoke = method.invoke(this, new Object[]{list.get(0)});
                        break;
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        invoke = method.invoke(this, new Object[]{list.get(0), list.get(1)});
                        break;
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                        invoke = method.invoke(this, new Object[]{list.get(0), list.get(1), list.get(XZBDevice.DOWNLOAD_LIST_RECYCLE)});
                        break;
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                        invoke = method.invoke(this, new Object[]{list.get(0), list.get(1), list.get(XZBDevice.DOWNLOAD_LIST_RECYCLE), list.get(XZBDevice.DOWNLOAD_LIST_FAILED)});
                        break;
                    case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        invoke = method.invoke(this, new Object[]{list.get(0), list.get(1), list.get(XZBDevice.DOWNLOAD_LIST_RECYCLE), list.get(XZBDevice.DOWNLOAD_LIST_FAILED), list.get(XZBDevice.DOWNLOAD_LIST_ALL)});
                        break;
                    default:
                        invoke = method.invoke(this, new Object[]{list.get(0), list.get(1), list.get(XZBDevice.DOWNLOAD_LIST_RECYCLE), list.get(XZBDevice.DOWNLOAD_LIST_FAILED), list.get(XZBDevice.DOWNLOAD_LIST_ALL), list.get(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED)});
                        break;
                }
                Class returnType = method.getReturnType();
                f.b("openSDK_LOG.JsBridge", new StringBuilder("-->call, result: ").append(invoke).append(" | ReturnType: ").append(returnType.getName()).toString());
                if ("void".equals(returnType.getName()) || returnType == Void.class) {
                    if (aVar != null) {
                        aVar.a(null);
                    }
                } else if (aVar != null && customCallback()) {
                    aVar.a(invoke != null ? invoke.toString() : null);
                }
            } else if (aVar != null) {
                aVar.a();
            }
        }

        public boolean customCallback() {
            return false;
        }
    }

    // compiled from: ProGuard
    public static class a {
        public WeakReference<WebView> a;
        public long b;
        protected String c;

        public a(WebView webView, long j, String str) {
            this.a = new WeakReference(webView);
            this.b = j;
            this.c = str;
        }

        public void a(Object obj) {
            WebView webView = (WebView) this.a.get();
            if (webView != null) {
                String str = "'undefined'";
                if (obj instanceof String) {
                    str = new StringBuilder("'").append(((String) obj).replace("\\", "\\\\").replace("'", "\\'")).append("'").toString();
                } else if ((obj instanceof Number) || (obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Double) || (obj instanceof Float)) {
                    str = obj.toString();
                } else if (obj instanceof Boolean) {
                    str = obj.toString();
                }
                webView.loadUrl(new StringBuilder("javascript:window.JsBridge&&JsBridge.callback(").append(this.b).append(",{'r':0,'result':").append(str).append("});").toString());
            }
        }

        public void a() {
            WebView webView = (WebView) this.a.get();
            if (webView != null) {
                webView.loadUrl(new StringBuilder("javascript:window.JsBridge&&JsBridge.callback(").append(this.b).append(",{'r':1,'result':'no such method'})").toString());
            }
        }

        public void a(String str) {
            WebView webView = (WebView) this.a.get();
            if (webView != null) {
                webView.loadUrl(new StringBuilder(BaseJsInterface.JS_PREFIX).append(str).toString());
            }
        }
    }

    public a() {
        this.a = new HashMap();
    }

    public void a(b bVar, String str) {
        this.a.put(str, bVar);
    }

    public void a(String str, String str2, List<String> list, a aVar) {
        f.a("openSDK_LOG.JsBridge", new StringBuilder("getResult---objName = ").append(str).append(" methodName = ").append(str2).toString());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            try {
                list.set(i, URLDecoder.decode((String) list.get(i), GameManager.DEFAULT_CHARSET));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        b bVar = (b) this.a.get(str);
        if (bVar != null) {
            f.b("openSDK_LOG.JsBridge", "call----");
            bVar.call(str2, list, aVar);
            return;
        }
        f.b("openSDK_LOG.JsBridge", "not call----objName NOT FIND");
        if (aVar != null) {
            aVar.a();
        }
    }

    public boolean a(WebView webView, String str) {
        f.a("openSDK_LOG.JsBridge", new StringBuilder("-->canHandleUrl---url = ").append(str).toString());
        if (str == null || !Uri.parse(str).getScheme().equals("jsbridge")) {
            return false;
        }
        ArrayList arrayList = new ArrayList(Arrays.asList((str + "/#").split("/")));
        if (arrayList.size() < 6) {
            return false;
        }
        String str2 = (String) arrayList.get(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        String str3 = (String) arrayList.get(XZBDevice.DOWNLOAD_LIST_FAILED);
        List subList = arrayList.subList(XZBDevice.DOWNLOAD_LIST_ALL, arrayList.size() - 1);
        a aVar = new a(webView, 4, str);
        webView.getUrl();
        a(str2, str3, subList, aVar);
        return true;
    }
}
