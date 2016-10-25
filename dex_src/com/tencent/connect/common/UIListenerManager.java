package com.tencent.connect.common;

import android.content.Intent;
import com.baidu.mobads.openad.d.b;
import com.tencent.open.a.f;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.uc.addon.sdk.remote.protocol.CommandConstant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ProGuard
public class UIListenerManager {
    private static final String TAG = "openSDK_LOG.UIListenerManager";
    private static UIListenerManager mInstance;
    private Map<String, ApiTask> mListenerMap;

    // compiled from: ProGuard
    public class ApiTask {
        public IUiListener mListener;
        public int mRequestCode;

        public ApiTask(int i, IUiListener iUiListener) {
            this.mRequestCode = i;
            this.mListener = iUiListener;
        }
    }

    static {
        mInstance = null;
    }

    public static UIListenerManager getInstance() {
        if (mInstance == null) {
            mInstance = new UIListenerManager();
        }
        return mInstance;
    }

    private UIListenerManager() {
        this.mListenerMap = Collections.synchronizedMap(new HashMap());
        if (this.mListenerMap == null) {
            this.mListenerMap = Collections.synchronizedMap(new HashMap());
        }
    }

    public Object setListenerWithRequestcode(int i, IUiListener iUiListener) {
        String actionFromRequestcode = SystemUtils.getActionFromRequestcode(i);
        if (actionFromRequestcode == null) {
            f.e(TAG, new StringBuilder("setListener action is null! rquestCode=").append(i).toString());
            return null;
        }
        ApiTask apiTask;
        synchronized (this.mListenerMap) {
            apiTask = (ApiTask) this.mListenerMap.put(actionFromRequestcode, new ApiTask(i, iUiListener));
        }
        return apiTask == null ? null : apiTask.mListener;
    }

    public Object setListnerWithAction(String str, IUiListener iUiListener) {
        int requestCodeFromCallback = SystemUtils.getRequestCodeFromCallback(str);
        if (requestCodeFromCallback == -1) {
            f.e(TAG, new StringBuilder("setListnerWithAction fail, action = ").append(str).toString());
            return null;
        }
        ApiTask apiTask;
        synchronized (this.mListenerMap) {
            apiTask = (ApiTask) this.mListenerMap.put(str, new ApiTask(requestCodeFromCallback, iUiListener));
        }
        return apiTask == null ? null : apiTask.mListener;
    }

    public IUiListener getListnerWithRequestCode(int i) {
        String actionFromRequestcode = SystemUtils.getActionFromRequestcode(i);
        if (actionFromRequestcode != null) {
            return getListnerWithAction(actionFromRequestcode);
        }
        f.e(TAG, new StringBuilder("getListner action is null! rquestCode=").append(i).toString());
        return null;
    }

    public IUiListener getListnerWithAction(String str) {
        if (str == null) {
            f.e(TAG, "getListnerWithAction action is null!");
            return null;
        }
        ApiTask apiTask;
        synchronized (this.mListenerMap) {
            apiTask = (ApiTask) this.mListenerMap.get(str);
            this.mListenerMap.remove(str);
        }
        return apiTask == null ? null : apiTask.mListener;
    }

    public void handleDataToListener(Intent intent, IUiListener iUiListener) {
        f.c(TAG, "handleDataToListener");
        if (intent == null) {
            iUiListener.onCancel();
            return;
        }
        String stringExtra = intent.getStringExtra(Constants.KEY_ACTION);
        String stringExtra2;
        if (SystemUtils.ACTION_LOGIN.equals(stringExtra)) {
            int intExtra = intent.getIntExtra(Constants.KEY_ERROR_CODE, 0);
            if (intExtra == 0) {
                stringExtra2 = intent.getStringExtra(Constants.KEY_RESPONSE);
                if (stringExtra2 != null) {
                    try {
                        iUiListener.onComplete(Util.parseJson(stringExtra2));
                        return;
                    } catch (Throwable e) {
                        iUiListener.onError(new UiError(-4, Constants.MSG_JSON_ERROR, stringExtra2));
                        f.b(TAG, "OpenUi, onActivityResult, json error", e);
                    }
                }
                f.b(TAG, "OpenUi, onActivityResult, onComplete");
                iUiListener.onComplete(new JSONObject());
                return;
            }
            f.e(TAG, new StringBuilder("OpenUi, onActivityResult, onError = ").append(intExtra).toString());
            iUiListener.onError(new UiError(intExtra, intent.getStringExtra(Constants.KEY_ERROR_MSG), intent.getStringExtra(Constants.KEY_ERROR_DETAIL)));
        } else if (CommandConstant.COMMAND_SHARE.equals(stringExtra)) {
            stringExtra = intent.getStringExtra("result");
            stringExtra2 = intent.getStringExtra("response");
            if ("cancel".equals(stringExtra)) {
                iUiListener.onCancel();
            } else if ("error".equals(stringExtra)) {
                iUiListener.onError(new UiError(-6, "unknown error", stringExtra2));
            } else if (b.COMPLETE.equals(stringExtra)) {
                try {
                    if (stringExtra2 == null) {
                        stringExtra = "{\"ret\": 0}";
                    } else {
                        stringExtra = stringExtra2;
                    }
                    iUiListener.onComplete(new JSONObject(stringExtra));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    iUiListener.onError(new UiError(-4, "json error", stringExtra2));
                }
            }
        }
    }

    private IUiListener buildListener(int i, IUiListener iUiListener) {
        if (i == 11101) {
            f.e(TAG, "\u767b\u5f55\u7684\u63a5\u53e3\u56de\u8c03\u4e0d\u80fd\u91cd\u65b0\u6784\u5efa\uff0c\u6682\u65f6\u65e0\u6cd5\u63d0\u4f9b\uff0c\u5148\u8bb0\u5f55\u4e0b\u6765\u8fd9\u79cd\u60c5\u51b5\u662f\u5426\u5b58\u5728");
        } else if (i == 11105) {
            f.e(TAG, "Social Api \u7684\u63a5\u53e3\u56de\u8c03\u9700\u8981\u4f7f\u7528param\u6765\u91cd\u65b0\u6784\u5efa\uff0c\u6682\u65f6\u65e0\u6cd5\u63d0\u4f9b\uff0c\u5148\u8bb0\u5f55\u4e0b\u6765\u8fd9\u79cd\u60c5\u51b5\u662f\u5426\u5b58\u5728");
        } else if (i == 11106) {
            f.e(TAG, "Social Api \u7684H5\u63a5\u53e3\u56de\u8c03\u9700\u8981\u4f7f\u7528param\u6765\u91cd\u65b0\u6784\u5efa\uff0c\u6682\u65f6\u65e0\u6cd5\u63d0\u4f9b\uff0c\u5148\u8bb0\u5f55\u4e0b\u6765\u8fd9\u79cd\u60c5\u51b5\u662f\u5426\u5b58\u5728");
        }
        return iUiListener;
    }

    public boolean onActivityResult(int i, int i2, Intent intent, IUiListener iUiListener) {
        IUiListener iUiListener2;
        f.c(TAG, new StringBuilder("onActivityResult req=").append(i).append(" res=").append(i2).toString());
        IUiListener listnerWithRequestCode = getListnerWithRequestCode(i);
        if (listnerWithRequestCode != null) {
            iUiListener2 = listnerWithRequestCode;
        } else if (iUiListener != null) {
            iUiListener2 = buildListener(i, iUiListener);
        } else {
            f.e(TAG, "onActivityResult can't find the listener");
            return false;
        }
        if (i2 != -1) {
            iUiListener2.onCancel();
        } else if (intent == null) {
            iUiListener2.onError(new UiError(-6, "onActivityResult intent data is null.", "onActivityResult intent data is null."));
            return true;
        } else {
            String stringExtra = intent.getStringExtra(Constants.KEY_ACTION);
            int intExtra;
            if (SystemUtils.ACTION_LOGIN.equals(stringExtra)) {
                intExtra = intent.getIntExtra(Constants.KEY_ERROR_CODE, 0);
                if (intExtra == 0) {
                    stringExtra = intent.getStringExtra(Constants.KEY_RESPONSE);
                    if (stringExtra != null) {
                        try {
                            iUiListener2.onComplete(Util.parseJson(stringExtra));
                        } catch (Throwable e) {
                            iUiListener2.onError(new UiError(-4, Constants.MSG_JSON_ERROR, stringExtra));
                            f.b(TAG, "OpenUi, onActivityResult, json error", e);
                        }
                    } else {
                        f.b(TAG, "OpenUi, onActivityResult, onComplete");
                        iUiListener2.onComplete(new JSONObject());
                    }
                } else {
                    f.e(TAG, new StringBuilder("OpenUi, onActivityResult, onError = ").append(intExtra).toString());
                    iUiListener2.onError(new UiError(intExtra, intent.getStringExtra(Constants.KEY_ERROR_MSG), intent.getStringExtra(Constants.KEY_ERROR_DETAIL)));
                }
            } else if (CommandConstant.COMMAND_SHARE.equals(stringExtra)) {
                stringExtra = intent.getStringExtra("result");
                stringExtra = intent.getStringExtra("response");
                if ("cancel".equals(stringExtra)) {
                    iUiListener2.onCancel();
                } else if ("error".equals(stringExtra)) {
                    iUiListener2.onError(new UiError(-6, "unknown error", stringExtra));
                } else if (b.COMPLETE.equals(stringExtra)) {
                    try {
                        if (stringExtra == null) {
                            stringExtra = "{\"ret\": 0}";
                        } else {
                            stringExtra = stringExtra;
                        }
                        iUiListener2.onComplete(new JSONObject(stringExtra));
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                        iUiListener2.onError(new UiError(-4, "json error", stringExtra));
                    }
                }
            } else {
                intExtra = intent.getIntExtra(Constants.KEY_ERROR_CODE, 0);
                if (intExtra == 0) {
                    stringExtra = intent.getStringExtra(Constants.KEY_RESPONSE);
                    if (stringExtra != null) {
                        try {
                            iUiListener2.onComplete(Util.parseJson(stringExtra));
                        } catch (JSONException e3) {
                            iUiListener2.onError(new UiError(-4, Constants.MSG_JSON_ERROR, stringExtra));
                        }
                    } else {
                        iUiListener2.onComplete(new JSONObject());
                    }
                } else {
                    iUiListener2.onError(new UiError(intExtra, intent.getStringExtra(Constants.KEY_ERROR_MSG), intent.getStringExtra(Constants.KEY_ERROR_DETAIL)));
                }
            }
        }
        return true;
    }
}
