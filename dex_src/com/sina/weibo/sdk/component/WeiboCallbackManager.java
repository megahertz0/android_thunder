package com.sina.weibo.sdk.component;

import android.content.Context;
import android.text.TextUtils;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.component.WidgetRequestParam.WidgetRequestCallback;
import java.util.HashMap;
import java.util.Map;

public class WeiboCallbackManager {
    private static WeiboCallbackManager sInstance;
    private Context mContext;
    private Map<String, WeiboAuthListener> mWeiboAuthListenerMap;
    private Map<String, WidgetRequestCallback> mWidgetRequestCallbackMap;

    private WeiboCallbackManager(Context context) {
        this.mContext = context;
        this.mWeiboAuthListenerMap = new HashMap();
        this.mWidgetRequestCallbackMap = new HashMap();
    }

    public static synchronized WeiboCallbackManager getInstance(Context context) {
        WeiboCallbackManager weiboCallbackManager;
        synchronized (WeiboCallbackManager.class) {
            if (sInstance == null) {
                sInstance = new WeiboCallbackManager(context);
            }
            weiboCallbackManager = sInstance;
        }
        return weiboCallbackManager;
    }

    public synchronized WeiboAuthListener getWeiboAuthListener(String str) {
        WeiboAuthListener weiboAuthListener;
        if (TextUtils.isEmpty(str)) {
            weiboAuthListener = null;
        } else {
            weiboAuthListener = (WeiboAuthListener) this.mWeiboAuthListenerMap.get(str);
        }
        return weiboAuthListener;
    }

    public synchronized void setWeiboAuthListener(String str, WeiboAuthListener weiboAuthListener) {
        if (!(TextUtils.isEmpty(str) || weiboAuthListener == null)) {
            this.mWeiboAuthListenerMap.put(str, weiboAuthListener);
        }
    }

    public synchronized void removeWeiboAuthListener(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mWeiboAuthListenerMap.remove(str);
        }
    }

    public synchronized WidgetRequestCallback getWidgetRequestCallback(String str) {
        WidgetRequestCallback widgetRequestCallback;
        if (TextUtils.isEmpty(str)) {
            widgetRequestCallback = null;
        } else {
            widgetRequestCallback = (WidgetRequestCallback) this.mWidgetRequestCallbackMap.get(str);
        }
        return widgetRequestCallback;
    }

    public synchronized void setWidgetRequestCallback(String str, WidgetRequestCallback widgetRequestCallback) {
        if (!(TextUtils.isEmpty(str) || widgetRequestCallback == null)) {
            this.mWidgetRequestCallbackMap.put(str, widgetRequestCallback);
        }
    }

    public synchronized void removeWidgetRequestCallback(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mWidgetRequestCallbackMap.remove(str);
        }
    }

    public String genCallbackKey() {
        return String.valueOf(System.currentTimeMillis());
    }
}
