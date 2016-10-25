package com.sina.weibo.sdk.api.share;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.sina.weibo.sdk.ApiUtils;
import com.sina.weibo.sdk.WeiboAppManager;
import com.sina.weibo.sdk.WeiboAppManager.WeiboInfo;
import com.sina.weibo.sdk.api.WeiboMessage;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.IWeiboHandler.Request;
import com.sina.weibo.sdk.api.share.IWeiboHandler.Response;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.cmd.WbAppActivator;
import com.sina.weibo.sdk.component.ShareRequestParam;
import com.sina.weibo.sdk.component.WeiboSdkBrowser;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.constant.WBConstants.Base;
import com.sina.weibo.sdk.constant.WBConstants.SDK;
import com.sina.weibo.sdk.constant.WBPageConstants.Scheme;
import com.sina.weibo.sdk.exception.WeiboShareException;
import com.sina.weibo.sdk.utils.AidTask;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.MD5;
import com.sina.weibo.sdk.utils.Utility;
import com.xunlei.xiazaibao.sdk.XZBDevice;

class WeiboShareAPIImpl implements IWeiboShareAPI {
    private static final String TAG;
    private String mAppKey;
    private Context mContext;
    private Dialog mDownloadConfirmDialog;
    private IWeiboDownloadListener mDownloadListener;
    private boolean mNeedDownloadWeibo;
    private WeiboInfo mWeiboInfo;

    static {
        TAG = WeiboShareAPIImpl.class.getName();
    }

    public WeiboShareAPIImpl(Context context, String str, boolean z) {
        this.mWeiboInfo = null;
        this.mNeedDownloadWeibo = true;
        this.mDownloadConfirmDialog = null;
        this.mContext = context;
        this.mAppKey = str;
        this.mNeedDownloadWeibo = z;
        this.mWeiboInfo = WeiboAppManager.getInstance(context).getWeiboInfo();
        if (this.mWeiboInfo != null) {
            LogUtil.d(TAG, this.mWeiboInfo.toString());
        } else {
            LogUtil.d(TAG, "WeiboInfo is null");
        }
        AidTask.getInstance(context).aidTaskInit(str);
    }

    public int getWeiboAppSupportAPI() {
        return (this.mWeiboInfo == null || !this.mWeiboInfo.isLegal()) ? -1 : this.mWeiboInfo.getSupportApi();
    }

    public boolean isWeiboAppInstalled() {
        return this.mWeiboInfo != null && this.mWeiboInfo.isLegal();
    }

    public boolean isWeiboAppSupportAPI() {
        return getWeiboAppSupportAPI() >= 10350;
    }

    public boolean isSupportWeiboPay() {
        return getWeiboAppSupportAPI() >= 10353;
    }

    public boolean registerApp() {
        sendBroadcast(this.mContext, WBConstants.ACTION_WEIBO_REGISTER, this.mAppKey, null, null);
        return true;
    }

    public boolean handleWeiboResponse(Intent intent, Response response) {
        String stringExtra = intent.getStringExtra(Base.APP_PKG);
        CharSequence stringExtra2 = intent.getStringExtra(WBConstants.TRAN);
        if (TextUtils.isEmpty(stringExtra)) {
            LogUtil.e(TAG, "handleWeiboResponse faild appPackage is null");
            return false;
        } else if (response instanceof Activity) {
            Activity activity = (Activity) response;
            LogUtil.d(TAG, new StringBuilder("handleWeiboResponse getCallingPackage : ").append(activity.getCallingPackage()).toString());
            if (TextUtils.isEmpty(stringExtra2)) {
                LogUtil.e(TAG, "handleWeiboResponse faild intent _weibo_transaction is null");
                return false;
            } else if (ApiUtils.validateWeiboSign(this.mContext, stringExtra) || stringExtra.equals(activity.getPackageName())) {
                response.onResponse(new SendMessageToWeiboResponse(intent.getExtras()));
                return true;
            } else {
                LogUtil.e(TAG, "handleWeiboResponse faild appPackage validateSign faild");
                return false;
            }
        } else {
            LogUtil.e(TAG, "handleWeiboResponse faild handler is not Activity");
            return false;
        }
    }

    public boolean handleWeiboRequest(Intent intent, Request request) {
        if (intent == null || request == null) {
            return false;
        }
        Object stringExtra = intent.getStringExtra(Base.APP_PKG);
        CharSequence stringExtra2 = intent.getStringExtra(WBConstants.TRAN);
        if (TextUtils.isEmpty(stringExtra)) {
            LogUtil.e(TAG, "handleWeiboRequest faild appPackage validateSign faild");
            request.onRequest(null);
            return false;
        } else if (TextUtils.isEmpty(stringExtra2)) {
            LogUtil.e(TAG, "handleWeiboRequest faild intent _weibo_transaction is null");
            request.onRequest(null);
            return false;
        } else if (ApiUtils.validateWeiboSign(this.mContext, stringExtra)) {
            request.onRequest(new ProvideMessageForWeiboRequest(intent.getExtras()));
            return true;
        } else {
            LogUtil.e(TAG, "handleWeiboRequest faild appPackage validateSign faild");
            request.onRequest(null);
            return false;
        }
    }

    public boolean launchWeibo(Activity activity) {
        boolean z = false;
        if (isWeiboAppInstalled()) {
            try {
                activity.startActivity(activity.getPackageManager().getLaunchIntentForPackage(this.mWeiboInfo.getPackageName()));
                z = true;
                return true;
            } catch (Exception e) {
                LogUtil.e(TAG, e.getMessage());
                return z;
            }
        }
        LogUtil.e(TAG, "launchWeibo faild WeiboInfo is null");
        return false;
    }

    public boolean sendRequest(Activity activity, BaseRequest baseRequest) {
        if (baseRequest == null) {
            LogUtil.e(TAG, "sendRequest faild request is null");
            return false;
        }
        try {
            if (!checkEnvironment(this.mNeedDownloadWeibo)) {
                return false;
            }
            if (baseRequest.check(this.mContext, this.mWeiboInfo, new VersionCheckHandler())) {
                WbAppActivator.getInstance(this.mContext, this.mAppKey).activateApp();
                Bundle bundle = new Bundle();
                baseRequest.toBundle(bundle);
                return launchWeiboActivity(activity, WBConstants.ACTIVITY_WEIBO, this.mWeiboInfo.getPackageName(), this.mAppKey, bundle);
            }
            LogUtil.e(TAG, "sendRequest faild request check faild");
            return false;
        } catch (Exception e) {
            LogUtil.e(TAG, e.getMessage());
            return false;
        }
    }

    public boolean sendRequest(Activity activity, BaseRequest baseRequest, AuthInfo authInfo, String str, WeiboAuthListener weiboAuthListener) {
        if (baseRequest == null) {
            LogUtil.e(TAG, "sendRequest faild request is null !");
            return false;
        } else if (!isWeiboAppInstalled() || !isWeiboAppSupportAPI()) {
            return startShareWeiboActivity(activity, str, baseRequest, weiboAuthListener);
        } else {
            if (getWeiboAppSupportAPI() >= 10351) {
                return sendRequest(activity, baseRequest);
            }
            if (!(baseRequest instanceof SendMultiMessageToWeiboRequest)) {
                return sendRequest(activity, baseRequest);
            }
            SendMultiMessageToWeiboRequest sendMultiMessageToWeiboRequest = (SendMultiMessageToWeiboRequest) baseRequest;
            BaseRequest sendMessageToWeiboRequest = new SendMessageToWeiboRequest();
            sendMessageToWeiboRequest.packageName = sendMultiMessageToWeiboRequest.packageName;
            sendMessageToWeiboRequest.transaction = sendMultiMessageToWeiboRequest.transaction;
            sendMessageToWeiboRequest.message = adapterMultiMessage2SingleMessage(sendMultiMessageToWeiboRequest.multiMessage);
            return sendRequest(activity, sendMessageToWeiboRequest);
        }
    }

    private WeiboMessage adapterMultiMessage2SingleMessage(WeiboMultiMessage weiboMultiMessage) {
        if (weiboMultiMessage == null) {
            return new WeiboMessage();
        }
        Bundle bundle = new Bundle();
        weiboMultiMessage.toBundle(bundle);
        return new WeiboMessage(bundle);
    }

    private boolean startShareWeiboActivity(Activity activity, String str, BaseRequest baseRequest, WeiboAuthListener weiboAuthListener) {
        try {
            WbAppActivator.getInstance(this.mContext, this.mAppKey).activateApp();
            Bundle bundle = new Bundle();
            String packageName = activity.getPackageName();
            ShareRequestParam shareRequestParam = new ShareRequestParam(activity);
            shareRequestParam.setToken(str);
            shareRequestParam.setAppKey(this.mAppKey);
            shareRequestParam.setAppPackage(packageName);
            shareRequestParam.setBaseRequest(baseRequest);
            shareRequestParam.setSpecifyTitle("\u5fae\u535a\u5206\u4eab");
            shareRequestParam.setAuthListener(weiboAuthListener);
            Intent intent = new Intent(activity, WeiboSdkBrowser.class);
            intent.putExtras(shareRequestParam.createRequestParamBundle());
            activity.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e) {
            return false;
        }
    }

    public boolean sendResponse(BaseResponse baseResponse) {
        if (baseResponse == null) {
            LogUtil.e(TAG, "sendResponse failed response null");
            return false;
        } else if (baseResponse.check(this.mContext, new VersionCheckHandler())) {
            Bundle bundle = new Bundle();
            baseResponse.toBundle(bundle);
            sendBroadcast(this.mContext, WBConstants.ACTION_WEIBO_RESPONSE, this.mAppKey, baseResponse.reqPackageName, bundle);
            return true;
        } else {
            LogUtil.e(TAG, "sendResponse check fail");
            return false;
        }
    }

    private void registerWeiboDownloadListener(IWeiboDownloadListener iWeiboDownloadListener) {
        this.mDownloadListener = iWeiboDownloadListener;
    }

    private boolean checkEnvironment(boolean z) throws WeiboShareException {
        if (isWeiboAppInstalled()) {
            if (!isWeiboAppSupportAPI()) {
                throw new WeiboShareException("Weibo do not support share api!");
            } else if (ApiUtils.validateWeiboSign(this.mContext, this.mWeiboInfo.getPackageName())) {
                return true;
            } else {
                throw new WeiboShareException("Weibo signature is incorrect!");
            }
        } else if (z) {
            if (this.mDownloadConfirmDialog == null) {
                this.mDownloadConfirmDialog = WeiboDownloader.createDownloadConfirmDialog(this.mContext, this.mDownloadListener);
                this.mDownloadConfirmDialog.show();
            } else if (!this.mDownloadConfirmDialog.isShowing()) {
                this.mDownloadConfirmDialog.show();
            }
            return false;
        } else {
            throw new WeiboShareException("Weibo is not installed!");
        }
    }

    public boolean launchWeiboPay(Activity activity, String str) {
        Bundle bundle = new Bundle();
        bundle.putString("rawdata", str);
        bundle.putInt(WBConstants.COMMAND_TYPE_KEY, XZBDevice.DOWNLOAD_LIST_ALL);
        bundle.putString(WBConstants.TRAN, String.valueOf(System.currentTimeMillis()));
        return launchWeiboActivity(activity, WBConstants.ACTIVITY_WEIBO_PAY, this.mWeiboInfo.getPackageName(), this.mAppKey, bundle);
    }

    public boolean launchWeiboPayLogin(Activity activity, String str) {
        boolean z = false;
        if (!Utility.isWeiBoVersionSupportNewPay(activity).booleanValue()) {
            return launchWeiboPay(activity, str);
        }
        if (activity == null) {
            LogUtil.e(TAG, "launchWeiboActivity fail, invalid arguments");
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString("rawdata", str);
        bundle.putInt(WBConstants.COMMAND_TYPE_KEY, XZBDevice.DOWNLOAD_LIST_ALL);
        bundle.putString(WBConstants.TRAN, String.valueOf(System.currentTimeMillis()));
        Intent intent = new Intent();
        intent.setPackage(this.mWeiboInfo.getPackageName());
        intent.setData(Uri.parse(Scheme.SDK_DELIVER_SCHEME));
        String packageName = activity.getPackageName();
        intent.putExtra(Base.SDK_VER, WBConstants.WEIBO_SDK_VERSION_CODE);
        intent.putExtra(Base.APP_PKG, packageName);
        intent.putExtra(Base.APP_KEY, this.mAppKey);
        intent.putExtra(SDK.FLAG, WBConstants.WEIBO_FLAG_SDK);
        intent.putExtra(WBConstants.SIGN, MD5.hexdigest(Utility.getSign(activity, packageName)));
        intent.putExtra(WBConstants.SDK_REAL_ACTION, WBConstants.ACTIVITY_WEIBO_PAY);
        intent.putExtra(WBConstants.SDK_IS_SCHEME, false);
        intent.putExtra(WBConstants.SDK_REQUESTCODE, WBConstants.SDK_ACTIVITY_FOR_RESULT_CODE);
        intent.putExtras(bundle);
        try {
            LogUtil.d(TAG, new StringBuilder("launchWeiboActivity intent=").append(intent).append(", extra=").append(intent.getExtras()).toString());
            activity.startActivityForResult(intent, WBConstants.SDK_ACTIVITY_FOR_RESULT_CODE);
            z = true;
            return true;
        } catch (ActivityNotFoundException e) {
            LogUtil.e(TAG, e.getMessage());
            return z;
        }
    }

    private boolean launchWeiboActivity(Activity activity, String str, String str2, String str3, Bundle bundle) {
        boolean z = false;
        if (activity == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            LogUtil.e(TAG, "launchWeiboActivity fail, invalid arguments");
            return false;
        }
        Intent intent = new Intent();
        intent.setPackage(str2);
        intent.setAction(str);
        String packageName = activity.getPackageName();
        intent.putExtra(Base.SDK_VER, WBConstants.WEIBO_SDK_VERSION_CODE);
        intent.putExtra(Base.APP_PKG, packageName);
        intent.putExtra(Base.APP_KEY, str3);
        intent.putExtra(SDK.FLAG, WBConstants.WEIBO_FLAG_SDK);
        intent.putExtra(WBConstants.SIGN, MD5.hexdigest(Utility.getSign(activity, packageName)));
        intent.putExtra(WBConstants.TRAN, String.valueOf(System.currentTimeMillis()));
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        try {
            LogUtil.d(TAG, new StringBuilder("launchWeiboActivity intent=").append(intent).append(", extra=").append(intent.getExtras()).toString());
            activity.startActivityForResult(intent, WBConstants.SDK_ACTIVITY_FOR_RESULT_CODE);
            z = true;
            return true;
        } catch (ActivityNotFoundException e) {
            LogUtil.e(TAG, e.getMessage());
            return z;
        }
    }

    private void sendBroadcast(Context context, String str, String str2, String str3, Bundle bundle) {
        Intent intent = new Intent(str);
        String packageName = context.getPackageName();
        intent.putExtra(Base.SDK_VER, WBConstants.WEIBO_SDK_VERSION_CODE);
        intent.putExtra(Base.APP_PKG, packageName);
        intent.putExtra(Base.APP_KEY, str2);
        intent.putExtra(SDK.FLAG, WBConstants.WEIBO_FLAG_SDK);
        intent.putExtra(WBConstants.SIGN, MD5.hexdigest(Utility.getSign(context, packageName)));
        if (!TextUtils.isEmpty(str3)) {
            intent.setPackage(str3);
        }
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        LogUtil.d(TAG, new StringBuilder("intent=").append(intent).append(", extra=").append(intent.getExtras()).toString());
        context.sendBroadcast(intent, WBConstants.ACTION_WEIBO_SDK_PERMISSION);
    }

    public void shareMessageToWeiyou(Context context, Bundle bundle) {
        Utility.openWeiboActivity(context, Scheme.SHARETOWEIYOU, bundle);
    }
}
