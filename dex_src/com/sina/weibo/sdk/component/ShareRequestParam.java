package com.sina.weibo.sdk.component;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.MusicObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.VideoObject;
import com.sina.weibo.sdk.api.VoiceObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.BaseRequest;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.constant.WBConstants.Base;
import com.sina.weibo.sdk.constant.WBConstants.Response;
import com.sina.weibo.sdk.constant.WBConstants.SDK;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.utils.Base64;
import com.sina.weibo.sdk.utils.MD5;
import com.sina.weibo.sdk.utils.Utility;
import com.uc.addon.sdk.remote.Tabs;
import com.umeng.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

public class ShareRequestParam extends BrowserRequestParamBase {
    public static final String REQ_PARAM_AID = "aid";
    public static final String REQ_PARAM_KEY_HASH = "key_hash";
    public static final String REQ_PARAM_PACKAGENAME = "packagename";
    public static final String REQ_PARAM_PICINFO = "picinfo";
    public static final String REQ_PARAM_SOURCE = "source";
    public static final String REQ_PARAM_TITLE = "title";
    public static final String REQ_PARAM_TOKEN = "access_token";
    public static final String REQ_PARAM_VERSION = "version";
    public static final String REQ_UPLOAD_PIC_PARAM_IMG = "img";
    public static final String RESP_UPLOAD_PIC_PARAM_CODE = "code";
    public static final String RESP_UPLOAD_PIC_PARAM_DATA = "data";
    public static final int RESP_UPLOAD_PIC_SUCC_CODE = 1;
    private static final String SHARE_URL = "http://service.weibo.com/share/mobilesdk.php";
    public static final String UPLOAD_PIC_URL = "http://service.weibo.com/share/mobilesdk_uppic.php";
    private String mAppKey;
    private String mAppPackage;
    private WeiboAuthListener mAuthListener;
    private String mAuthListenerKey;
    private byte[] mBase64ImgData;
    private BaseRequest mBaseRequest;
    private String mHashKey;
    private String mShareContent;
    private String mToken;

    public static class UploadPicResult {
        private int code;
        private String picId;

        private UploadPicResult() {
            this.code = -2;
        }

        public int getCode() {
            return this.code;
        }

        public String getPicId() {
            return this.picId;
        }

        public static com.sina.weibo.sdk.component.ShareRequestParam.UploadPicResult parse(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            com.sina.weibo.sdk.component.ShareRequestParam.UploadPicResult uploadPicResult = new com.sina.weibo.sdk.component.ShareRequestParam.UploadPicResult();
            try {
                JSONObject jSONObject = new JSONObject(str);
                uploadPicResult.code = jSONObject.optInt(RESP_UPLOAD_PIC_PARAM_CODE, Tabs.TAB_CREATE_REACH_MAX_COUNT);
                uploadPicResult.picId = jSONObject.optString(RESP_UPLOAD_PIC_PARAM_DATA, a.d);
                return uploadPicResult;
            } catch (JSONException e) {
                return uploadPicResult;
            }
        }
    }

    public ShareRequestParam(Context context) {
        super(context);
        this.mLaucher = BrowserLauncher.SHARE;
    }

    protected void onSetupRequestParam(Bundle bundle) {
        this.mAppKey = bundle.getString(REQ_PARAM_SOURCE);
        this.mAppPackage = bundle.getString(REQ_PARAM_PACKAGENAME);
        this.mHashKey = bundle.getString(REQ_PARAM_KEY_HASH);
        this.mToken = bundle.getString(REQ_PARAM_TOKEN);
        this.mAuthListenerKey = bundle.getString(AuthRequestParam.EXTRA_KEY_LISTENER);
        if (!TextUtils.isEmpty(this.mAuthListenerKey)) {
            this.mAuthListener = WeiboCallbackManager.getInstance(this.mContext).getWeiboAuthListener(this.mAuthListenerKey);
        }
        handleSharedMessage(bundle);
        this.mUrl = buildUrl(a.d);
    }

    private void handleSharedMessage(Bundle bundle) {
        WeiboMultiMessage weiboMultiMessage = new WeiboMultiMessage();
        weiboMultiMessage.toObject(bundle);
        StringBuilder stringBuilder = new StringBuilder();
        if (weiboMultiMessage.textObject instanceof TextObject) {
            stringBuilder.append(weiboMultiMessage.textObject.text);
        }
        if (weiboMultiMessage.imageObject instanceof ImageObject) {
            ImageObject imageObject = weiboMultiMessage.imageObject;
            handleMblogPic(imageObject.imagePath, imageObject.imageData);
        }
        if (weiboMultiMessage.mediaObject instanceof TextObject) {
            stringBuilder.append(((TextObject) weiboMultiMessage.mediaObject).text);
        }
        if (weiboMultiMessage.mediaObject instanceof ImageObject) {
            imageObject = (ImageObject) weiboMultiMessage.mediaObject;
            handleMblogPic(imageObject.imagePath, imageObject.imageData);
        }
        if (weiboMultiMessage.mediaObject instanceof WebpageObject) {
            stringBuilder.append(" ").append(((WebpageObject) weiboMultiMessage.mediaObject).actionUrl);
        }
        if (weiboMultiMessage.mediaObject instanceof MusicObject) {
            stringBuilder.append(" ").append(((MusicObject) weiboMultiMessage.mediaObject).actionUrl);
        }
        if (weiboMultiMessage.mediaObject instanceof VideoObject) {
            stringBuilder.append(" ").append(((VideoObject) weiboMultiMessage.mediaObject).actionUrl);
        }
        if (weiboMultiMessage.mediaObject instanceof VoiceObject) {
            stringBuilder.append(" ").append(((VoiceObject) weiboMultiMessage.mediaObject).actionUrl);
        }
        this.mShareContent = stringBuilder.toString();
    }

    private void handleMblogPic(String str, byte[] bArr) {
        Throwable th;
        try {
            if (!TextUtils.isEmpty(str)) {
                File file = new File(str);
                if (file.exists() && file.canRead() && file.length() > 0) {
                    byte[] bArr2 = new byte[((int) file.length())];
                    FileInputStream fileInputStream = null;
                    FileInputStream fileInputStream2;
                    try {
                        fileInputStream2 = new FileInputStream(file);
                        try {
                            fileInputStream2.read(bArr2);
                            this.mBase64ImgData = Base64.encodebyte(bArr2);
                            try {
                                fileInputStream2.close();
                                return;
                            } catch (Exception e) {
                            }
                        } catch (IOException e2) {
                            if (fileInputStream2 != null) {
                                fileInputStream2.close();
                            }
                            if (bArr != null) {
                            }
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            fileInputStream = fileInputStream2;
                            th = th3;
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            throw th;
                        }
                    } catch (IOException e3) {
                        fileInputStream2 = null;
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (Exception e4) {
                            }
                        }
                        if (bArr != null) {
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e5) {
                            }
                        }
                        throw th;
                    }
                }
            }
        } catch (SecurityException e6) {
        }
        if (bArr != null && bArr.length > 0) {
            this.mBase64ImgData = Base64.encodebyte(bArr);
        }
    }

    public void onCreateRequestParamBundle(Bundle bundle) {
        if (this.mBaseRequest != null) {
            this.mBaseRequest.toBundle(bundle);
        }
        if (!TextUtils.isEmpty(this.mAppPackage)) {
            this.mHashKey = MD5.hexdigest(Utility.getSign(this.mContext, this.mAppPackage));
        }
        bundle.putString(REQ_PARAM_TOKEN, this.mToken);
        bundle.putString(REQ_PARAM_SOURCE, this.mAppKey);
        bundle.putString(REQ_PARAM_PACKAGENAME, this.mAppPackage);
        bundle.putString(REQ_PARAM_KEY_HASH, this.mHashKey);
        bundle.putString(Base.APP_PKG, this.mAppPackage);
        bundle.putString(Base.APP_KEY, this.mAppKey);
        bundle.putInt(SDK.FLAG, WBConstants.WEIBO_FLAG_SDK);
        bundle.putString(WBConstants.SIGN, this.mHashKey);
        if (this.mAuthListener != null) {
            WeiboCallbackManager instance = WeiboCallbackManager.getInstance(this.mContext);
            this.mAuthListenerKey = instance.genCallbackKey();
            instance.setWeiboAuthListener(this.mAuthListenerKey, this.mAuthListener);
            bundle.putString(AuthRequestParam.EXTRA_KEY_LISTENER, this.mAuthListenerKey);
        }
    }

    public void execRequest(Activity activity, int i) {
        if (i == 3) {
            sendSdkCancleResponse(activity);
            WeiboSdkBrowser.closeBrowser(activity, this.mAuthListenerKey, null);
        }
    }

    public boolean hasImage() {
        return this.mBase64ImgData != null && this.mBase64ImgData.length > 0;
    }

    public WeiboParameters buildUploadPicParam(WeiboParameters weiboParameters) {
        if (hasImage()) {
            weiboParameters.put(REQ_UPLOAD_PIC_PARAM_IMG, new String(this.mBase64ImgData));
        }
        return weiboParameters;
    }

    public String buildUrl(String str) {
        Builder buildUpon = Uri.parse(SHARE_URL).buildUpon();
        buildUpon.appendQueryParameter(REQ_PARAM_TITLE, this.mShareContent);
        buildUpon.appendQueryParameter(REQ_PARAM_VERSION, WBConstants.WEIBO_SDK_VERSION_CODE);
        if (!TextUtils.isEmpty(this.mAppKey)) {
            buildUpon.appendQueryParameter(REQ_PARAM_SOURCE, this.mAppKey);
        }
        if (!TextUtils.isEmpty(this.mToken)) {
            buildUpon.appendQueryParameter(REQ_PARAM_TOKEN, this.mToken);
        }
        Object aid = Utility.getAid(this.mContext, this.mAppKey);
        if (!TextUtils.isEmpty(aid)) {
            buildUpon.appendQueryParameter(REQ_PARAM_AID, aid);
        }
        if (!TextUtils.isEmpty(this.mAppPackage)) {
            buildUpon.appendQueryParameter(REQ_PARAM_PACKAGENAME, this.mAppPackage);
        }
        if (!TextUtils.isEmpty(this.mHashKey)) {
            buildUpon.appendQueryParameter(REQ_PARAM_KEY_HASH, this.mHashKey);
        }
        if (!TextUtils.isEmpty(str)) {
            buildUpon.appendQueryParameter(REQ_PARAM_PICINFO, str);
        }
        return buildUpon.build().toString();
    }

    private void sendSdkResponse(Activity activity, int i, String str) {
        Bundle extras = activity.getIntent().getExtras();
        if (extras != null) {
            Intent intent = new Intent(WBConstants.ACTIVITY_REQ_SDK);
            intent.setFlags(AccessibilityNodeInfoCompat.ACTION_SET_SELECTION);
            intent.setPackage(extras.getString(Base.APP_PKG));
            intent.putExtras(extras);
            intent.putExtra(Base.APP_PKG, activity.getPackageName());
            intent.putExtra(Response.ERRCODE, i);
            intent.putExtra(Response.ERRMSG, str);
            try {
                activity.startActivityForResult(intent, WBConstants.SDK_ACTIVITY_FOR_RESULT_CODE);
            } catch (ActivityNotFoundException e) {
            }
        }
    }

    public void sendSdkCancleResponse(Activity activity) {
        sendSdkResponse(activity, RESP_UPLOAD_PIC_SUCC_CODE, "send cancel!!!");
    }

    public void sendSdkOkResponse(Activity activity) {
        sendSdkResponse(activity, 0, "send ok!!!");
    }

    public void sendSdkErrorResponse(Activity activity, String str) {
        sendSdkResponse(activity, XZBDevice.DOWNLOAD_LIST_RECYCLE, str);
    }

    public void setBaseRequest(BaseRequest baseRequest) {
        this.mBaseRequest = baseRequest;
    }

    public String getAppPackage() {
        return this.mAppPackage;
    }

    public void setAppPackage(String str) {
        this.mAppPackage = str;
    }

    public String getToken() {
        return this.mToken;
    }

    public void setToken(String str) {
        this.mToken = str;
    }

    public String getAppKey() {
        return this.mAppKey;
    }

    public void setAppKey(String str) {
        this.mAppKey = str;
    }

    public String getHashKey() {
        return this.mHashKey;
    }

    public String getShareContent() {
        return this.mShareContent;
    }

    public byte[] getBase64ImgData() {
        return this.mBase64ImgData;
    }

    public WeiboAuthListener getAuthListener() {
        return this.mAuthListener;
    }

    public String getAuthListenerKey() {
        return this.mAuthListenerKey;
    }

    public void setAuthListener(WeiboAuthListener weiboAuthListener) {
        this.mAuthListener = weiboAuthListener;
    }
}
