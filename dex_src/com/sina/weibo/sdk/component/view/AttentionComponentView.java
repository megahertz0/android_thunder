package com.sina.weibo.sdk.component.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.cmd.WbAppActivator;
import com.sina.weibo.sdk.component.WeiboSdkBrowser;
import com.sina.weibo.sdk.component.WidgetRequestParam;
import com.sina.weibo.sdk.component.WidgetRequestParam.WidgetRequestCallback;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.NetUtils;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.ResourceManager;
import com.tencent.connect.common.Constants;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.json.JSONException;
import org.json.JSONObject;

public class AttentionComponentView extends FrameLayout {
    private static final String ALREADY_ATTEND_EN = "Following";
    private static final String ALREADY_ATTEND_ZH_CN = "\u5df2\u5173\u6ce8";
    private static final String ALREADY_ATTEND_ZH_TW = "\u5df2\u95dc\u6ce8";
    private static final String ATTEND_EN = "Follow";
    private static final String ATTEND_ZH_CN = "\u5173\u6ce8";
    private static final String ATTEND_ZH_TW = "\u95dc\u6ce8";
    private static final String ATTENTION_H5 = "http://widget.weibo.com/relationship/followsdk.php";
    private static final String FRIENDSHIPS_SHOW_URL = "https://api.weibo.com/2/friendships/show.json";
    private static final String TAG;
    private FrameLayout flButton;
    private RequestParam mAttentionParam;
    private TextView mButton;
    private volatile boolean mIsLoadingState;
    private ProgressBar pbLoading;

    public static class RequestParam {
        private String mAccessToken;
        private String mAppKey;
        private String mAttentionScreenName;
        private String mAttentionUid;
        private WeiboAuthListener mAuthlistener;

        private RequestParam() {
        }

        public static com.sina.weibo.sdk.component.view.AttentionComponentView.RequestParam createRequestParam(String str, String str2, String str3, String str4, WeiboAuthListener weiboAuthListener) {
            com.sina.weibo.sdk.component.view.AttentionComponentView.RequestParam requestParam = new com.sina.weibo.sdk.component.view.AttentionComponentView.RequestParam();
            requestParam.mAppKey = str;
            requestParam.mAccessToken = str2;
            requestParam.mAttentionUid = str3;
            requestParam.mAttentionScreenName = str4;
            requestParam.mAuthlistener = weiboAuthListener;
            return requestParam;
        }

        public static com.sina.weibo.sdk.component.view.AttentionComponentView.RequestParam createRequestParam(String str, String str2, String str3, WeiboAuthListener weiboAuthListener) {
            com.sina.weibo.sdk.component.view.AttentionComponentView.RequestParam requestParam = new com.sina.weibo.sdk.component.view.AttentionComponentView.RequestParam();
            requestParam.mAppKey = str;
            requestParam.mAttentionUid = str2;
            requestParam.mAttentionScreenName = str3;
            requestParam.mAuthlistener = weiboAuthListener;
            return requestParam;
        }

        private boolean hasAuthoriz() {
            return !TextUtils.isEmpty(this.mAccessToken);
        }
    }

    static {
        TAG = AttentionComponentView.class.getName();
    }

    public AttentionComponentView(Context context) {
        super(context);
        this.mIsLoadingState = false;
        init(context);
    }

    public AttentionComponentView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mIsLoadingState = false;
        init(context);
    }

    public AttentionComponentView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsLoadingState = false;
        init(context);
    }

    private void init(Context context) {
        Drawable createStateListDrawable = ResourceManager.createStateListDrawable(context, "common_button_white.9.png", "common_button_white_highlighted.9.png");
        this.flButton = new FrameLayout(context);
        this.flButton.setBackgroundDrawable(createStateListDrawable);
        this.flButton.setPadding(0, ResourceManager.dp2px(getContext(), R.styleable.Toolbar_contentInsetEnd), ResourceManager.dp2px(getContext(), XZBDevice.DOWNLOAD_LIST_RECYCLE), ResourceManager.dp2px(getContext(), R.styleable.Toolbar_contentInsetEnd));
        this.flButton.setLayoutParams(new LayoutParams(ResourceManager.dp2px(getContext(), R.styleable.AppCompatTheme_textAppearanceSearchResultSubtitle), -2));
        addView(this.flButton);
        this.mButton = new TextView(getContext());
        this.mButton.setIncludeFontPadding(false);
        this.mButton.setSingleLine(true);
        this.mButton.setTextSize(XZBDevice.DOWNLOAD_LIST_RECYCLE, 13.0f);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.mButton.setLayoutParams(layoutParams);
        this.flButton.addView(this.mButton);
        this.pbLoading = new ProgressBar(getContext(), null, 16842873);
        this.pbLoading.setVisibility(XZBDevice.Wait);
        layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        this.pbLoading.setLayoutParams(layoutParams);
        this.flButton.addView(this.pbLoading);
        this.flButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                AttentionComponentView.this.execAttented();
            }
        });
        showFollowButton(false);
    }

    public void setAttentionParam(RequestParam requestParam) {
        this.mAttentionParam = requestParam;
        if (requestParam.hasAuthoriz()) {
            loadAttentionState(requestParam);
        }
    }

    private void startLoading() {
        this.flButton.setEnabled(false);
        this.mButton.setVisibility(XZBDevice.Wait);
        this.pbLoading.setVisibility(0);
    }

    private void stopLoading() {
        this.flButton.setEnabled(true);
        this.mButton.setVisibility(0);
        this.pbLoading.setVisibility(XZBDevice.Wait);
    }

    private void showFollowButton(boolean z) {
        stopLoading();
        if (z) {
            this.mButton.setText(ResourceManager.getString(getContext(), ALREADY_ATTEND_EN, ALREADY_ATTEND_ZH_CN, ALREADY_ATTEND_ZH_TW));
            this.mButton.setTextColor(-13421773);
            this.mButton.setCompoundDrawablesWithIntrinsicBounds(ResourceManager.getDrawable(getContext(), "timeline_relationship_icon_attention.png"), null, null, null);
            this.flButton.setEnabled(false);
            return;
        }
        this.mButton.setText(ResourceManager.getString(getContext(), ATTEND_EN, ATTEND_ZH_CN, ATTEND_ZH_TW));
        this.mButton.setTextColor(-32256);
        this.mButton.setCompoundDrawablesWithIntrinsicBounds(ResourceManager.getDrawable(getContext(), "timeline_relationship_icon_addattention.png"), null, null, null);
        this.flButton.setEnabled(true);
    }

    private void loadAttentionState(RequestParam requestParam) {
        if (!this.mIsLoadingState) {
            WbAppActivator.getInstance(getContext(), requestParam.mAppKey).activateApp();
            this.mIsLoadingState = true;
            startLoading();
            WeiboParameters weiboParameters = new WeiboParameters(requestParam.mAppKey);
            weiboParameters.put(Constants.PARAM_ACCESS_TOKEN, requestParam.mAccessToken);
            weiboParameters.put("target_id", requestParam.mAttentionUid);
            weiboParameters.put("target_screen_name", requestParam.mAttentionScreenName);
            NetUtils.internalHttpRequest(getContext(), FRIENDSHIPS_SHOW_URL, weiboParameters, Constants.HTTP_GET, new RequestListener() {

                class AnonymousClass_1 implements Runnable {
                    private final /* synthetic */ JSONObject val$target;

                    AnonymousClass_1(JSONObject jSONObject) {
                        this.val$target = jSONObject;
                    }

                    public void run() {
                        if (this.val$target != null) {
                            AttentionComponentView.this.showFollowButton(this.val$target.optBoolean("followed_by", false));
                        }
                        AttentionComponentView.this.mIsLoadingState = false;
                    }
                }

                public void onWeiboException(WeiboException weiboException) {
                    LogUtil.d(TAG, new StringBuilder("error : ").append(weiboException.getMessage()).toString());
                    AttentionComponentView.this.mIsLoadingState = false;
                }

                public void onComplete(String str) {
                    LogUtil.d(TAG, new StringBuilder("json : ").append(str).toString());
                    try {
                        AttentionComponentView.this.getHandler().post(new AnonymousClass_1(new JSONObject(str).optJSONObject(com.taobao.accs.common.Constants.KEY_TARGET)));
                    } catch (JSONException e) {
                    }
                }
            });
        }
    }

    private void execAttented() {
        WidgetRequestParam widgetRequestParam = new WidgetRequestParam(getContext());
        widgetRequestParam.setUrl(ATTENTION_H5);
        widgetRequestParam.setSpecifyTitle(ResourceManager.getString(getContext(), ATTEND_EN, ATTEND_ZH_CN, ATTEND_ZH_TW));
        widgetRequestParam.setAppKey(this.mAttentionParam.mAppKey);
        widgetRequestParam.setAttentionFuid(this.mAttentionParam.mAttentionUid);
        widgetRequestParam.setAuthListener(this.mAttentionParam.mAuthlistener);
        widgetRequestParam.setToken(this.mAttentionParam.mAccessToken);
        widgetRequestParam.setWidgetRequestCallback(new WidgetRequestCallback() {
            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onWebViewResult(java.lang.String r5) {
                throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.component.view.AttentionComponentView.AnonymousClass_3.onWebViewResult(java.lang.String):void");
                /*
                this = this;
                r0 = com.sina.weibo.sdk.utils.Utility.parseUri(r5);
                r1 = "result";
                r0 = r0.getString(r1);
                r1 = android.text.TextUtils.isEmpty(r0);
                if (r1 != 0) goto L_0x0022;
            L_0x0011:
                r0 = java.lang.Integer.parseInt(r0);	 Catch:{ NumberFormatException -> 0x0030 }
                r0 = (long) r0;	 Catch:{ NumberFormatException -> 0x0030 }
                r2 = 1;
                r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
                if (r2 != 0) goto L_0x0023;
            L_0x001c:
                r0 = com.sina.weibo.sdk.component.view.AttentionComponentView.this;	 Catch:{ NumberFormatException -> 0x0030 }
                r1 = 1;
                r0.showFollowButton(r1);	 Catch:{ NumberFormatException -> 0x0030 }
            L_0x0022:
                return;
            L_0x0023:
                r2 = 0;
                r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
                if (r0 != 0) goto L_0x0022;
            L_0x0029:
                r0 = com.sina.weibo.sdk.component.view.AttentionComponentView.this;	 Catch:{ NumberFormatException -> 0x0030 }
                r1 = 0;
                r0.showFollowButton(r1);	 Catch:{ NumberFormatException -> 0x0030 }
                goto L_0x0022;
            L_0x0030:
                r0 = move-exception;
                goto L_0x0022;
                */
            }
        });
        Bundle createRequestParamBundle = widgetRequestParam.createRequestParamBundle();
        Intent intent = new Intent(getContext(), WeiboSdkBrowser.class);
        intent.putExtras(createRequestParamBundle);
        getContext().startActivity(intent);
    }

    private void requestAsync(Context context, String str, WeiboParameters weiboParameters, String str2, RequestListener requestListener) {
        NetUtils.internalHttpRequest(context, str, weiboParameters, str2, requestListener);
    }
}
