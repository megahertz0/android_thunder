package com.sina.weibo.sdk.register.mobile;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.component.WeiboSdkBrowser;
import com.sina.weibo.sdk.component.view.ResizeableLayout;
import com.sina.weibo.sdk.component.view.ResizeableLayout.SizeChangeListener;
import com.sina.weibo.sdk.component.view.TitleBar;
import com.sina.weibo.sdk.component.view.TitleBar.ListenerOnTitleBtnClicked;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.NetUtils;
import com.sina.weibo.sdk.net.RequestListener;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.utils.LogUtil;
import com.sina.weibo.sdk.utils.NetworkHelper;
import com.sina.weibo.sdk.utils.ResourceManager;
import com.sina.weibo.sdk.utils.UIUtils;
import com.tencent.connect.common.Constants;
import com.tencent.open.GameAppOperation;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Locale;
import org.json.JSONObject;

public class MobileRegisterActivity extends Activity implements OnClickListener, OnFocusChangeListener, SizeChangeListener {
    private static final String APPKEY_NOT_SET_CN = "\u60a8\u7684app_key\u6ca1\u6709\u8bbe\u7f6e";
    private static final String APPKEY_NOT_SET_EN = "your appkey not set";
    private static final String APPKEY_NOT_SET_TW = "\u60a8\u7684app_key\u6c92\u6709\u8a2d\u7f6e";
    private static final String CANCEL_EN = "Cancel";
    private static final String CANCEL_ZH_CN = "\u53d6\u6d88";
    private static final String CANCEL_ZH_TW = "\u53d6\u6d88";
    private static final String CHINA_CN = "\u4e2d\u56fd";
    private static final String CHINA_EN = "China";
    private static final String CHINA_TW = "\u4e2d\u570b";
    private static final String CODE_LENGTH_CN = "\u4f60\u7684\u9a8c\u8bc1\u7801\u4e0d\u662f6\u4f4d\u6570";
    private static final String CODE_LENGTH_EN = "Your code isn\u2019t 6-digit long";
    private static final String CODE_LENGTH_TW = "\u4f60\u7684\u9a57\u8b49\u78bc\u4e0d\u662f6\u4f4d\u6578";
    private static final int DEFAULT_BG_COLOR = -855310;
    private static final int DEFAULT_CLEAR_BTN = 22;
    private static final int DEFAULT_TEXT_PADDING = 12;
    private static final int DEFAULT_TIPS_TEXT_SIZE = 13;
    private static final int DEFAULT__RIGHT_TRIANGLE = 13;
    private static final int EMPTY_VIEW_TEXT_COLOR = -4342339;
    private static final int GET_CODE_BTN_ID = 3;
    private static final String GET_CODE_CN = "\u83b7\u53d6\u9a8c\u8bc1\u7801";
    private static final String GET_CODE_EN = "Get code";
    private static final String GET_CODE_TW = "\u7372\u53d6\u9a57\u8b49\u78bc";
    private static final String HELP_INFO_CN = "\u8bf7\u786e\u8ba4\u56fd\u5bb6\u548c\u5730\u533a\u5e76\u586b\u5199\u624b\u673a\u53f7\u7801";
    private static final String HELP_INFO_EN = "Confirm your country/region and enter your mobile number";
    private static final String HELP_INFO_TW = "\u8acb\u78ba\u8a8d\u570b\u5bb6\u548c\u5730\u5340\u5e76\u586b\u5beb\u624b\u6a5f\u865f";
    private static final String INPUT_AUTH_CODE_CN = "\u8bf7\u8f93\u5165\u9a8c\u8bc1\u7801";
    private static final String INPUT_AUTH_CODE_EN = "Verification code";
    private static final String INPUT_AUTH_CODE_TW = "\u8acb\u8f38\u5165\u9a57\u8b49\u78bc";
    private static final String INPUT_PHONE_NUM_CN = "\u8bf7\u8f93\u5165\u624b\u673a\u53f7\u7801";
    private static final String INPUT_PHONE_NUM_EN = "Your mobile number";
    private static final String INPUT_PHONE_NUM_TW = "\u8acb\u8f38\u5165\u624b\u6a5f\u865f";
    private static final int LINK_TEXT_COLOR = -8224126;
    private static final int MIAN_LINK_TEXT_COLOR = -11502161;
    private static final String NETWORK_ERROR_CN = "\u60a8\u7684\u7f51\u7edc\u4e0d\u53ef\u7528\uff0c\u8bf7\u7a0d\u540e";
    private static final String NETWORK_ERROR_EN = "your network is  disabled  try again later";
    private static final String NETWORK_ERROR_TW = "\u60a8\u7684\u7db2\u7d61\u4e0d\u53ef\u7528\uff0c\u8acb\u7a0d\u5f8c";
    private static final String OK_EN = "OK";
    private static final String OK_ZH_CN = "\u786e\u5b9a";
    private static final String OK_ZH_TW = "\u78ba\u5b9a";
    private static final String PHONE_ERROR_CN = "\u60a8\u7684\u624b\u673a\u53f7\u4e0d\u662f11\u4f4d\u6570";
    private static final String PHONE_ERROR_EN = "Your phone number isn\u2019t 11-digit long";
    private static final String PHONE_ERROR_TW = "\u60a8\u7684\u624b\u6a5f\u865f\u4e0d\u662f11\u4f4d\u6578";
    private static final int PHONE_NUM_CLEAR_BTN_ID = 4;
    public static final String REGISTER_TITLE = "register_title";
    private static final int RESIZEABLE_INPUTMETHODHIDE = 0;
    private static final int RESIZEABLE_INPUTMETHODSHOW = 1;
    public static final String RESPONSE_EXPIRES = "expires";
    public static final String RESPONSE_OAUTH_TOKEN = "oauth_token";
    private static final int SELECT_COUNTRY_REQUEST_CODE = 0;
    private static final String SEND_MSG = "http://api.weibo.com/oauth2/sms_authorize/send";
    private static final String SEND_SUBMIT = "http://api.weibo.com/oauth2/sms_authorize/submit";
    private static final String SERVER_ERROR_CN = "\u670d\u52a1\u5668\u5fd9,\u8bf7\u7a0d\u540e\u518d\u8bd5";
    private static final String SERVER_ERROR_EN = "the server is busy, please  wait";
    private static final String SERVER_ERROR_TW = "\u670d\u52d9\u5668\u5fd9,\u8acb\u7a0d\u5f8c\u518d\u8a66";
    private static final String SINA_NOTICE_EN = "By clicking ok, you hereby agree to Weibo Online Service Agreement and Privacy Policy";
    private static final String SINA_NOTICE_ZH_CN = "\u70b9\u51fb\u201c\u786e\u5b9a\u201d\u8868\u793a\u4f60\u540c\u610f\u670d\u52a1\u4f7f\u7528\u534f\u8bae\u548c\u9690\u79c1\u6761\u6b3e\u3002";
    private static final String SINA_NOTICE_ZH_TW = "\u9ede\u64ca\u201c\u78ba\u5b9a\u201d\u6a19\u793a\u4f60\u540c\u610f\u670d\u52d9\u4f7f\u7528\u5354\u8b70\u548c\u96b1\u79c1\u689d\u6b3e\u3002";
    private static final String SINA_PRIVATE_URL = "http://m.weibo.cn/reg/privacyagreement?from=h5&wm=3349";
    private static final String SINA_PROTOCOL_URL = "http://weibo.cn/dpool/ttt/h5/regagreement.php?from=h5";
    private static final String SINA_SERVICE_EN = "Service By Sina WeiBo";
    private static final String SINA_SERVICE_ZH_CN = "\u6b64\u670d\u52a1\u7531\u5fae\u535a\u63d0\u4f9b";
    private static final String SINA_SERVICE_ZH_TW = "\u6b64\u670d\u52d9\u7531\u5fae\u535a\u63d0\u4f9b";
    private static final String TAG;
    private static final int TITLE_BAR_ID = 1;
    private static final String TITLE_CN = "\u9a8c\u8bc1\u7801\u767b\u5f55";
    private static final String TITLE_EN = "Login";
    private static final String TITLE_TW = "\u9a57\u8b49\u78bc\u767b\u9304";
    private static final int TRIANGLE_ID = 2;
    private static final String WAIT_CN = "\u6b63\u5728\u5904\u7406\u4e2d.....";
    private static final String WAIT_EN = "please wait .... ";
    private static final String WAIT_TW = "\u6b63\u5728\u8655\u7406\u4e2d.....";
    private String cfrom;
    private String mAppkey;
    private Button mBtnRegist;
    private EditText mCheckCode;
    private CountDownTimer mCountDownTimer;
    private TextView mCountryCode;
    private String mCountryCodeStr;
    private RelativeLayout mCountryLayout;
    private TextView mCountryName;
    private String mCountryNameStr;
    private Button mGetCodeBtn;
    private TextView mInfoText;
    private InputHandler mInputHandler;
    private String mKeyHash;
    private ProgressDialog mLoadingDlg;
    private int mMaxHeight;
    private String mPackageName;
    private EditText mPhoneNum;
    private ImageView mPhoneNumClearBtn;
    private ScrollView mRegistScrollview;
    private LinearLayout mRegiter_llt;
    private String mSpecifyTitle;
    private TextView mTips;
    private TitleBar titleBar;

    class AnonymousClass_1 extends CountDownTimer {
        AnonymousClass_1(long j, long j2) {
            super(j, j2);
        }

        public void onTick(long j) {
            MobileRegisterActivity.this.mGetCodeBtn.setText(new StringBuilder(String.valueOf(ResourceManager.getString(MobileRegisterActivity.this.getApplicationContext(), GET_CODE_EN, GET_CODE_CN, GET_CODE_TW))).append(SocializeConstants.OP_OPEN_PAREN).append(j / 1000).append("s)").toString());
        }

        public void onFinish() {
            MobileRegisterActivity.this.mGetCodeBtn.setText(ResourceManager.getString(MobileRegisterActivity.this.getApplicationContext(), GET_CODE_EN, GET_CODE_CN, GET_CODE_TW));
            MobileRegisterActivity.this.enableGetCodeBtn();
        }
    }

    class AnonymousClass_4 implements RequestListener {
        private final /* synthetic */ String val$phoneNum;

        AnonymousClass_4(String str) {
            this.val$phoneNum = str;
        }

        public void onWeiboException(WeiboException weiboException) {
            LogUtil.d(TAG, new StringBuilder("get onWeiboException ").append(weiboException.getMessage()).toString());
            CharSequence string = ResourceManager.getString(MobileRegisterActivity.this.getApplicationContext(), SERVER_ERROR_EN, SERVER_ERROR_CN, SERVER_ERROR_TW);
            try {
                JSONObject jSONObject = new JSONObject(weiboException.getMessage());
                if (!TextUtils.isEmpty(jSONObject.optString("error_description"))) {
                    string = jSONObject.optString("error_description");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            MobileRegisterActivity.this.mTips.setVisibility(SELECT_COUNTRY_REQUEST_CODE);
            MobileRegisterActivity.this.mTips.setText(string);
            MobileRegisterActivity.this.dismiss();
        }

        public void onComplete(String str) {
            MobileRegisterActivity.this.dismiss();
            LogUtil.d(TAG, new StringBuilder("get onComplete : ").append(str).toString());
            if (str != null) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString(ParamKey.UID, jSONObject.optString(ParamKey.UID));
                    bundle.putString(Oauth2AccessToken.KEY_PHONE_NUM, this.val$phoneNum);
                    bundle.putString(Constants.PARAM_ACCESS_TOKEN, jSONObject.optString(RESPONSE_OAUTH_TOKEN));
                    bundle.putString(Constants.PARAM_EXPIRES_IN, jSONObject.optString(RESPONSE_EXPIRES));
                    intent.putExtras(bundle);
                    MobileRegisterActivity.this.setResult(-1, intent);
                    MobileRegisterActivity.this.finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class CodeTextWatcher implements TextWatcher {
        private CodeTextWatcher() {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(MobileRegisterActivity.this.mPhoneNum.getText().toString()) || TextUtils.isEmpty(MobileRegisterActivity.this.mCheckCode.getText().toString())) {
                MobileRegisterActivity.this.disableRegisterBtn();
            } else {
                MobileRegisterActivity.this.enableRegisterBtn();
            }
        }
    }

    private class InputHandler extends Handler {
        private InputHandler() {
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case SELECT_COUNTRY_REQUEST_CODE:
                    MobileRegisterActivity.this.mInfoText.setVisibility(SELECT_COUNTRY_REQUEST_CODE);
                    MobileRegisterActivity.this.mCountryLayout.setVisibility(SELECT_COUNTRY_REQUEST_CODE);
                case TITLE_BAR_ID:
                    MobileRegisterActivity.this.mInfoText.setVisibility(XZBDevice.Wait);
                    MobileRegisterActivity.this.mCountryLayout.setVisibility(XZBDevice.Wait);
                default:
                    break;
            }
        }
    }

    private class PhoneNumTextWatcher implements TextWatcher {
        private PhoneNumTextWatcher() {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (TextUtils.isEmpty(MobileRegisterActivity.this.mPhoneNum.getText().toString())) {
                MobileRegisterActivity.this.mPhoneNumClearBtn.setVisibility(PHONE_NUM_CLEAR_BTN_ID);
            } else {
                MobileRegisterActivity.this.mPhoneNumClearBtn.setVisibility(SELECT_COUNTRY_REQUEST_CODE);
            }
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            if (TextUtils.isEmpty(MobileRegisterActivity.this.mPhoneNum.getText().toString()) || TextUtils.isEmpty(MobileRegisterActivity.this.mCheckCode.getText().toString())) {
                MobileRegisterActivity.this.disableRegisterBtn();
            } else {
                MobileRegisterActivity.this.enableRegisterBtn();
            }
        }
    }

    private class WBSdkUrlClickSpan extends ClickableSpan {
        private Context context;
        private String url;

        public WBSdkUrlClickSpan(Context context, String str) {
            this.context = context;
            this.url = str;
        }

        public void onClick(View view) {
            Intent intent = new Intent(this.context, WeiboSdkBrowser.class);
            Bundle bundle = new Bundle();
            bundle.putString("key_url", this.url);
            intent.putExtras(bundle);
            MobileRegisterActivity.this.startActivity(intent);
        }

        public void updateDrawState(TextPaint textPaint) {
            textPaint.setColor(MIAN_LINK_TEXT_COLOR);
            textPaint.setUnderlineText(false);
        }
    }

    public MobileRegisterActivity() {
        this.mInputHandler = new InputHandler();
        this.mMaxHeight = 0;
    }

    static {
        TAG = MobileRegisterActivity.class.getName();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            UIUtils.showToast(getApplicationContext(), (CharSequence) "Pass wrong params!!", (int) SELECT_COUNTRY_REQUEST_CODE);
            finish();
        }
        this.mAppkey = extras.getString(com.taobao.accs.common.Constants.KEY_APP_KEY);
        this.mPackageName = extras.getString(LogBuilder.KEY_PACKAGE_NAME);
        this.mKeyHash = extras.getString(LogBuilder.KEY_HASH);
        if (TextUtils.isEmpty(this.mAppkey)) {
            UIUtils.showToast(getApplicationContext(), ResourceManager.getString(this, APPKEY_NOT_SET_EN, APPKEY_NOT_SET_CN, APPKEY_NOT_SET_TW), (int) SELECT_COUNTRY_REQUEST_CODE);
            finish();
        }
        String string = extras.getString(REGISTER_TITLE);
        if (TextUtils.isEmpty(string)) {
            string = ResourceManager.getString(this, TITLE_EN, TITLE_CN, TITLE_TW);
        }
        this.mSpecifyTitle = string;
        this.mCountryCodeStr = Country.CHINA_CODE;
        this.mCountryNameStr = ResourceManager.getString(this, CHINA_EN, CHINA_CN, CHINA_TW);
        initView();
        this.mCountDownTimer = new AnonymousClass_1(60000, 1000);
    }

    private void initView() {
        View resizeableLayout = new ResizeableLayout(this);
        resizeableLayout.setLayoutParams(new LayoutParams(-1, -1));
        resizeableLayout.setBackgroundColor(DEFAULT_BG_COLOR);
        this.titleBar = new TitleBar(this);
        this.titleBar.setId(TITLE_BAR_ID);
        this.titleBar.setLeftBtnText(ResourceManager.getString(this, CANCEL_EN, CANCEL_ZH_TW, CANCEL_ZH_TW));
        this.titleBar.setTitleBarText(this.mSpecifyTitle);
        this.titleBar.setTitleBarClickListener(new ListenerOnTitleBtnClicked() {
            public void onLeftBtnClicked() {
                MobileRegisterActivity.this.setResult(SELECT_COUNTRY_REQUEST_CODE);
                MobileRegisterActivity.this.finish();
            }
        });
        resizeableLayout.addView(this.titleBar);
        View view = new View(this);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, ResourceManager.dp2px(this, TRIANGLE_ID));
        view.setBackgroundDrawable(ResourceManager.getNinePatchDrawable(this, "weibosdk_common_shadow_top.9.png"));
        layoutParams.addRule(GET_CODE_BTN_ID, TITLE_BAR_ID);
        view.setLayoutParams(layoutParams);
        resizeableLayout.addView(view);
        this.mRegistScrollview = new ScrollView(this);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.topMargin = ResourceManager.dp2px(this, R.styleable.AppCompatTheme_spinnerDropDownItemStyle);
        this.mRegistScrollview.setBackgroundColor(DEFAULT_BG_COLOR);
        this.mRegistScrollview.setLayoutParams(layoutParams2);
        this.mRegiter_llt = new LinearLayout(this);
        layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        this.mRegiter_llt.setOrientation(TITLE_BAR_ID);
        this.mRegiter_llt.setLayoutParams(layoutParams2);
        this.mInfoText = new TextView(this);
        this.mInfoText.setTextSize(TRIANGLE_ID, 13.0f);
        this.mInfoText.setHeight(ResourceManager.dp2px(this, R.styleable.AppCompatTheme_listDividerAlertDialog));
        this.mInfoText.setGravity(R.styleable.Toolbar_maxButtonHeight);
        this.mInfoText.setTextColor(LINK_TEXT_COLOR);
        this.mInfoText.setText(ResourceManager.getString(this, HELP_INFO_EN, HELP_INFO_CN, HELP_INFO_TW));
        this.mInfoText.setFocusable(true);
        this.mInfoText.setFocusableInTouchMode(true);
        this.mRegiter_llt.addView(this.mInfoText);
        this.mCountryLayout = new RelativeLayout(this);
        layoutParams2 = new RelativeLayout.LayoutParams(-1, ResourceManager.dp2px(this, R.styleable.AppCompatTheme_homeAsUpIndicator));
        this.mCountryLayout.setBackgroundDrawable(ResourceManager.createStateListDrawable(this, "login_country_background.9.png", "login_country_background_highlighted.9.png"));
        this.mCountryLayout.setLayoutParams(layoutParams2);
        this.mCountryCode = new TextView(this);
        this.mCountryCode.setTextSize(TRIANGLE_ID, 17.0f);
        this.mCountryCode.setText(Country.CHINA_CODE);
        this.mCountryCode.setTextColor(-11382190);
        this.mCountryCode.setGravity(GET_CODE_BTN_ID);
        this.mCountryCode.setGravity(R.styleable.Toolbar_titleMarginBottom);
        layoutParams2 = new RelativeLayout.LayoutParams(-2, ResourceManager.dp2px(this, R.styleable.AppCompatTheme_homeAsUpIndicator));
        layoutParams2.leftMargin = ResourceManager.dp2px(this, XZBDevice.Delete);
        layoutParams2.addRule(XZBDevice.Pause);
        this.mCountryCode.setLayoutParams(layoutParams2);
        view = new ImageView(this);
        view.setId(TRIANGLE_ID);
        view.setImageDrawable(ResourceManager.getDrawable(this, "triangle.png"));
        layoutParams = new RelativeLayout.LayoutParams(ResourceManager.dp2px(this, DEFAULT__RIGHT_TRIANGLE), ResourceManager.dp2px(this, DEFAULT__RIGHT_TRIANGLE));
        layoutParams.rightMargin = ResourceManager.dp2px(this, XZBDevice.Delete);
        layoutParams.addRule(XZBDevice.Success);
        layoutParams.addRule(XZBDevice.Delete);
        view.setLayoutParams(layoutParams);
        this.mCountryName = new TextView(this);
        this.mCountryName.setTextSize(TRIANGLE_ID, 17.0f);
        this.mCountryName.setTextColor(-11382190);
        this.mCountryName.setText(this.mCountryNameStr);
        this.mCountryName.setGravity(R.styleable.Toolbar_titleMarginBottom);
        layoutParams = new RelativeLayout.LayoutParams(-2, ResourceManager.dp2px(this, R.styleable.AppCompatTheme_homeAsUpIndicator));
        layoutParams.rightMargin = ResourceManager.dp2px(this, 118);
        layoutParams.addRule(SELECT_COUNTRY_REQUEST_CODE, TRIANGLE_ID);
        layoutParams.addRule(XZBDevice.Delete);
        this.mCountryName.setLayoutParams(layoutParams);
        this.mCountryLayout.addView(this.mCountryCode);
        this.mCountryLayout.addView(this.mCountryName);
        this.mCountryLayout.addView(view);
        this.mRegiter_llt.addView(this.mCountryLayout);
        view = new LinearLayout(this);
        layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = ResourceManager.dp2px(this, XZBDevice.Stop);
        view.setLayoutParams(layoutParams);
        view.setOrientation(TITLE_BAR_ID);
        View relativeLayout = new RelativeLayout(this);
        LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, ResourceManager.dp2px(this, R.styleable.AppCompatTheme_buttonBarStyle));
        layoutParams3.gravity = 16;
        relativeLayout.setBackgroundDrawable(ResourceManager.getNinePatchDrawable(this, "login_top_background.9.png"));
        relativeLayout.setLayoutParams(layoutParams3);
        this.mPhoneNumClearBtn = new ImageView(this);
        this.mPhoneNumClearBtn.setId(PHONE_NUM_CLEAR_BTN_ID);
        this.mPhoneNumClearBtn.setImageDrawable(ResourceManager.createStateListDrawable(this, "search_clear_btn_normal.png", "search_clear_btn_down.png"));
        layoutParams3 = new RelativeLayout.LayoutParams(ResourceManager.dp2px(this, DEFAULT_CLEAR_BTN), ResourceManager.dp2px(this, DEFAULT_CLEAR_BTN));
        layoutParams3.rightMargin = ResourceManager.dp2px(this, XZBDevice.Delete);
        layoutParams3.addRule(XZBDevice.Success);
        layoutParams3.addRule(XZBDevice.Delete);
        this.mPhoneNumClearBtn.setVisibility(PHONE_NUM_CLEAR_BTN_ID);
        this.mPhoneNumClearBtn.setLayoutParams(layoutParams3);
        relativeLayout.addView(this.mPhoneNumClearBtn);
        this.mPhoneNum = new EditText(this);
        this.mPhoneNum.setTextSize(TRIANGLE_ID, 16.0f);
        this.mPhoneNum.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.mPhoneNum.setHint(ResourceManager.getString(this, INPUT_PHONE_NUM_EN, INPUT_PHONE_NUM_CN, INPUT_PHONE_NUM_TW));
        this.mPhoneNum.setHintTextColor(EMPTY_VIEW_TEXT_COLOR);
        this.mPhoneNum.setBackgroundDrawable(null);
        this.mPhoneNum.setSelected(false);
        layoutParams3 = new RelativeLayout.LayoutParams(-1, ResourceManager.dp2px(this, R.styleable.AppCompatTheme_buttonBarStyle));
        layoutParams3.topMargin = ResourceManager.dp2px(this, SELECT_COUNTRY_REQUEST_CODE);
        layoutParams3.bottomMargin = ResourceManager.dp2px(this, SELECT_COUNTRY_REQUEST_CODE);
        layoutParams3.leftMargin = ResourceManager.dp2px(this, SELECT_COUNTRY_REQUEST_CODE);
        layoutParams3.rightMargin = ResourceManager.dp2px(this, SELECT_COUNTRY_REQUEST_CODE);
        layoutParams3.addRule(SELECT_COUNTRY_REQUEST_CODE, PHONE_NUM_CLEAR_BTN_ID);
        this.mPhoneNum.setLayoutParams(layoutParams3);
        relativeLayout.addView(this.mPhoneNum);
        View relativeLayout2 = new RelativeLayout(this);
        LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, ResourceManager.dp2px(this, R.styleable.AppCompatTheme_buttonBarStyle));
        relativeLayout2.setBackgroundDrawable(ResourceManager.getNinePatchDrawable(this, "login_bottom_background.9.png"));
        relativeLayout2.setLayoutParams(layoutParams4);
        this.mGetCodeBtn = new Button(this);
        this.mGetCodeBtn.setId(GET_CODE_BTN_ID);
        this.mGetCodeBtn.setBackgroundDrawable(ResourceManager.createStateListDrawable(this, "get_code_button.9.png", "get_code_button_highlighted.9.png"));
        layoutParams4 = new RelativeLayout.LayoutParams(-2, ResourceManager.dp2px(this, R.styleable.AppCompatTheme_actionModeBackground));
        layoutParams4.rightMargin = ResourceManager.dp2px(this, DEFAULT_TEXT_PADDING);
        layoutParams4.addRule(XZBDevice.Success);
        layoutParams4.addRule(XZBDevice.Delete);
        this.mGetCodeBtn.setPadding(R.styleable.Toolbar_collapseIcon, SELECT_COUNTRY_REQUEST_CODE, R.styleable.Toolbar_collapseIcon, SELECT_COUNTRY_REQUEST_CODE);
        this.mGetCodeBtn.setLayoutParams(layoutParams4);
        this.mGetCodeBtn.setText(ResourceManager.getString(this, GET_CODE_EN, GET_CODE_CN, GET_CODE_TW));
        this.mGetCodeBtn.setTextSize(15.0f);
        enableGetCodeBtn();
        relativeLayout2.addView(this.mGetCodeBtn);
        this.mCheckCode = new EditText(this);
        this.mCheckCode.setTextSize(TRIANGLE_ID, 16.0f);
        this.mCheckCode.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.mCheckCode.setHintTextColor(EMPTY_VIEW_TEXT_COLOR);
        this.mCheckCode.setHint(ResourceManager.getString(this, INPUT_AUTH_CODE_EN, INPUT_AUTH_CODE_CN, INPUT_AUTH_CODE_TW));
        this.mCheckCode.setBackgroundDrawable(null);
        layoutParams4 = new RelativeLayout.LayoutParams(-1, ResourceManager.dp2px(this, R.styleable.AppCompatTheme_homeAsUpIndicator));
        layoutParams4.addRule(SELECT_COUNTRY_REQUEST_CODE, GET_CODE_BTN_ID);
        this.mCheckCode.setLayoutParams(layoutParams4);
        relativeLayout2.addView(this.mCheckCode);
        view.addView(relativeLayout);
        view.addView(relativeLayout2);
        this.mRegiter_llt.addView(view);
        this.mGetCodeBtn.setOnClickListener(this);
        this.mTips = new TextView(this);
        this.mTips.setTextSize(TRIANGLE_ID, 13.0f);
        this.mTips.setTextColor(-2014941);
        this.mTips.setText(a.d);
        this.mTips.setVisibility(PHONE_NUM_CLEAR_BTN_ID);
        layoutParams2 = new LinearLayout.LayoutParams(-1, ResourceManager.dp2px(this, R.styleable.AppCompatTheme_actionModeShareDrawable));
        layoutParams2.leftMargin = ResourceManager.dp2px(this, DEFAULT_TEXT_PADDING);
        this.mTips.setGravity(R.styleable.Toolbar_titleMarginBottom);
        this.mTips.setLayoutParams(layoutParams2);
        this.mRegiter_llt.addView(this.mTips);
        this.mBtnRegist = genOKBtn();
        disableRegisterBtn();
        this.mRegiter_llt.addView(this.mBtnRegist);
        view = genSinaServiceTv();
        relativeLayout = genProtocalInfoTv();
        this.mRegiter_llt.addView(view);
        this.mRegiter_llt.addView(relativeLayout);
        this.mRegistScrollview.addView(this.mRegiter_llt);
        resizeableLayout.addView(this.mRegistScrollview);
        initLoadingDlg();
        this.mPhoneNum.setInputType(TRIANGLE_ID);
        this.mPhoneNum.addTextChangedListener(new PhoneNumTextWatcher());
        this.mCheckCode.setInputType(TRIANGLE_ID);
        this.mCheckCode.addTextChangedListener(new CodeTextWatcher());
        this.mPhoneNumClearBtn.setOnClickListener(this);
        this.mPhoneNum.setOnFocusChangeListener(this);
        this.mBtnRegist.setOnClickListener(this);
        this.mCountryLayout.setOnClickListener(this);
        resizeableLayout.setSizeChangeListener(this);
        setContentView(resizeableLayout);
    }

    private Button genOKBtn() {
        Button button = new Button(this);
        button.setBackgroundDrawable(ResourceManager.createStateListDrawable(this, "common_button_big_blue.9.png", "common_button_big_blue_highlighted.9.png", "common_button_big_blue_disable.9.png"));
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, ResourceManager.dp2px(this, R.styleable.AppCompatTheme_dropdownListPreferredItemHeight));
        int dp2px = ResourceManager.dp2px(this, DEFAULT_TEXT_PADDING);
        layoutParams.rightMargin = dp2px;
        layoutParams.leftMargin = dp2px;
        button.setText(ResourceManager.getString(this, OK_EN, OK_ZH_CN, OK_ZH_TW));
        button.setTextSize(17.0f);
        button.setLayoutParams(layoutParams);
        return button;
    }

    private TextView genSinaServiceTv() {
        TextView textView = new TextView(this);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = ResourceManager.dp2px(this, DEFAULT_TEXT_PADDING);
        layoutParams.leftMargin = ResourceManager.dp2px(this, DEFAULT_TEXT_PADDING);
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(13.0f);
        textView.setGravity(GET_CODE_BTN_ID);
        textView.setTextColor(LINK_TEXT_COLOR);
        textView.setText(ResourceManager.getString(this, SINA_SERVICE_EN, SINA_SERVICE_ZH_CN, SINA_SERVICE_ZH_TW));
        return textView;
    }

    private TextView genProtocalInfoTv() {
        CharSequence charSequence;
        int indexOf;
        int length;
        int indexOf2;
        int length2;
        TextView textView = new TextView(this);
        textView.setTextSize(TRIANGLE_ID, 13.0f);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = ResourceManager.dp2px(this, XZBDevice.Wait);
        layoutParams.leftMargin = ResourceManager.dp2px(this, DEFAULT_TEXT_PADDING);
        layoutParams.rightMargin = ResourceManager.dp2px(this, DEFAULT_TEXT_PADDING);
        textView.setLayoutParams(layoutParams);
        textView.setTextSize(13.0f);
        textView.setGravity(GET_CODE_BTN_ID);
        textView.setTextColor(LINK_TEXT_COLOR);
        Locale language = ResourceManager.getLanguage();
        String str = "zh_CN";
        String str2;
        String str3;
        if (Locale.SIMPLIFIED_CHINESE.equals(language)) {
            charSequence = SINA_NOTICE_ZH_CN;
            str2 = "\u670d\u52a1\u4f7f\u7528\u534f\u8bae";
            str3 = "\u9690\u79c1\u6761\u6b3e";
            indexOf = SINA_NOTICE_ZH_CN.indexOf(str2);
            length = indexOf + str2.length();
            indexOf2 = SINA_NOTICE_ZH_CN.indexOf(str3);
            length2 = str3.length() + indexOf2;
        } else if (Locale.TRADITIONAL_CHINESE.equals(language)) {
            charSequence = SINA_NOTICE_ZH_TW;
            str = "zh_HK";
            str2 = "\u670d\u52d9\u4f7f\u7528\u5354\u8b70";
            str3 = "\u96b1\u79c1\u689d\u6b3e";
            indexOf = SINA_NOTICE_ZH_TW.indexOf(str2);
            length = indexOf + str2.length();
            indexOf2 = SINA_NOTICE_ZH_TW.indexOf(str3);
            length2 = str3.length() + indexOf2;
        } else {
            charSequence = SINA_NOTICE_EN;
            str = "en_US";
            str2 = "Service Agreement";
            str3 = "Privacy Policy";
            indexOf = SINA_NOTICE_EN.indexOf(str2);
            length = indexOf + str2.length();
            indexOf2 = SINA_NOTICE_EN.indexOf(str3);
            length2 = str3.length() + indexOf2;
        }
        CharSequence spannableStringBuilder = new SpannableStringBuilder(charSequence);
        if (!(indexOf == -1 || length == -1)) {
            spannableStringBuilder.setSpan(new WBSdkUrlClickSpan(this, new StringBuilder("http://weibo.cn/dpool/ttt/h5/regagreement.php?from=h5&lang=").append(str).toString()), indexOf, length, R.styleable.AppCompatTheme_actionModeCopyDrawable);
        }
        if (!(indexOf2 == -1 || length2 == -1)) {
            spannableStringBuilder.setSpan(new WBSdkUrlClickSpan(this, new StringBuilder("http://m.weibo.cn/reg/privacyagreement?from=h5&wm=3349&lang=").append(str).toString()), indexOf2, length2, R.styleable.AppCompatTheme_actionModeCopyDrawable);
        }
        textView.setText(spannableStringBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setFocusable(false);
        return textView;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        switch (i) {
            case SELECT_COUNTRY_REQUEST_CODE:
                if (intent != null) {
                    this.mCountryCodeStr = intent.getStringExtra(com.taobao.accs.common.Constants.KEY_HTTP_CODE);
                    this.mCountryNameStr = intent.getStringExtra(SelectCountryActivity.EXTRA_COUNTRY_NAME);
                    this.mCountryCode.setText(this.mCountryCodeStr);
                    this.mCountryName.setText(this.mCountryNameStr);
                }
            default:
                break;
        }
    }

    public void onFocusChange(View view, boolean z) {
        if (view == this.mPhoneNum && !z) {
            if (verifyPhoneNum(this.mPhoneNum.getText().toString())) {
                this.mTips.setVisibility(PHONE_NUM_CLEAR_BTN_ID);
                return;
            }
            this.mTips.setText(ResourceManager.getString(this, PHONE_ERROR_EN, PHONE_ERROR_CN, PHONE_ERROR_TW));
            this.mTips.setVisibility(SELECT_COUNTRY_REQUEST_CODE);
        }
    }

    private boolean doCheckOnGetMsg(String str) {
        if (!NetworkHelper.isNetworkAvailable(this)) {
            showNetFail();
            return false;
        } else if (verifyPhoneNum(str)) {
            this.mTips.setVisibility(PHONE_NUM_CLEAR_BTN_ID);
            return true;
        } else {
            this.mTips.setVisibility(SELECT_COUNTRY_REQUEST_CODE);
            this.mTips.setText(ResourceManager.getString(getApplicationContext(), PHONE_ERROR_EN, PHONE_ERROR_CN, PHONE_ERROR_TW));
            return false;
        }
    }

    private boolean verifyPhoneNum(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (Country.CHINA_CODE.equals(this.mCountryCodeStr)) {
            return str.trim().length() == 11;
        } else {
            return true;
        }
    }

    private boolean doCheckOnSubmit(String str) {
        if (!NetworkHelper.isNetworkAvailable(this)) {
            showNetFail();
            return false;
        } else if (verifyCheckCode(str)) {
            this.mTips.setVisibility(PHONE_NUM_CLEAR_BTN_ID);
            return true;
        } else {
            this.mTips.setVisibility(SELECT_COUNTRY_REQUEST_CODE);
            this.mTips.setText(ResourceManager.getString(getApplicationContext(), CODE_LENGTH_EN, CODE_LENGTH_CN, CODE_LENGTH_TW));
            UIUtils.showToast(getApplicationContext(), ResourceManager.getString(getApplicationContext(), CODE_LENGTH_EN, CODE_LENGTH_CN, CODE_LENGTH_TW), (int) SELECT_COUNTRY_REQUEST_CODE);
            return false;
        }
    }

    private boolean verifyCheckCode(String str) {
        return !TextUtils.isEmpty(str) && str.length() == 6;
    }

    private void disableGetCodeBtn() {
        this.mGetCodeBtn.setEnabled(false);
        this.mGetCodeBtn.setTextColor(EMPTY_VIEW_TEXT_COLOR);
    }

    private void enableGetCodeBtn() {
        this.mGetCodeBtn.setEnabled(true);
        this.mGetCodeBtn.setTextColor(MIAN_LINK_TEXT_COLOR);
    }

    private void disableRegisterBtn() {
        this.mBtnRegist.setTextColor(1308622847);
        this.mBtnRegist.setEnabled(false);
    }

    private void enableRegisterBtn() {
        this.mBtnRegist.setEnabled(true);
        this.mBtnRegist.setTextColor(-1);
    }

    private void showNetFail() {
        UIUtils.showToast(getApplicationContext(), ResourceManager.getString(getApplicationContext(), NETWORK_ERROR_EN, NETWORK_ERROR_CN, NETWORK_ERROR_TW), (int) SELECT_COUNTRY_REQUEST_CODE);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        setResult(SELECT_COUNTRY_REQUEST_CODE);
        finish();
        return true;
    }

    public void dismiss() {
        if (this.mLoadingDlg != null && this.mLoadingDlg.isShowing()) {
            this.mLoadingDlg.dismiss();
        }
    }

    private void initLoadingDlg() {
        this.mLoadingDlg = new ProgressDialog(this);
        this.mLoadingDlg.setCanceledOnTouchOutside(false);
        this.mLoadingDlg.requestWindowFeature(TITLE_BAR_ID);
        this.mLoadingDlg.setMessage(ResourceManager.getString(this, WAIT_EN, WAIT_CN, WAIT_TW));
    }

    public void getMsg(String str, String str2) {
        WeiboParameters weiboParameters = new WeiboParameters(this.mAppkey);
        weiboParameters.put(com.taobao.accs.common.Constants.SP_KEY_APPKEY, this.mAppkey);
        weiboParameters.put(LogBuilder.KEY_PACKAGE_NAME, this.mPackageName);
        weiboParameters.put(LogBuilder.KEY_HASH, this.mKeyHash);
        String str3 = "phone";
        if (!Country.CHINA_CODE.equals(str2)) {
            str = new StringBuilder(String.valueOf(str2)).append(str).toString();
        }
        weiboParameters.put(str3, str);
        weiboParameters.put(GameAppOperation.QQFAV_DATALINE_VERSION, WBConstants.WEIBO_SDK_VERSION_CODE);
        NetUtils.internalHttpRequest(this, SEND_MSG, weiboParameters, Constants.HTTP_GET, new RequestListener() {
            public void onWeiboException(WeiboException weiboException) {
                LogUtil.d(TAG, new StringBuilder("get onWeiboException ").append(weiboException.getMessage()).toString());
                CharSequence string = ResourceManager.getString(MobileRegisterActivity.this.getApplicationContext(), SERVER_ERROR_EN, SERVER_ERROR_CN, SERVER_ERROR_TW);
                try {
                    JSONObject jSONObject = new JSONObject(weiboException.getMessage());
                    if (!TextUtils.isEmpty(jSONObject.optString("error_description"))) {
                        string = jSONObject.optString("error_description");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                UIUtils.showToast(MobileRegisterActivity.this.getApplicationContext(), string, (int) TITLE_BAR_ID);
            }

            public void onComplete(String str) {
                LogUtil.d(TAG, new StringBuilder("get onComplete : ").append(str).toString());
                if (str != null) {
                    try {
                        MobileRegisterActivity.this.cfrom = (String) new JSONObject(str).get("cfrom");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void submit(String str, String str2) {
        WeiboParameters weiboParameters = new WeiboParameters(this.mAppkey);
        weiboParameters.put(com.taobao.accs.common.Constants.SP_KEY_APPKEY, this.mAppkey);
        weiboParameters.put(LogBuilder.KEY_PACKAGE_NAME, this.mPackageName);
        weiboParameters.put(LogBuilder.KEY_HASH, this.mKeyHash);
        weiboParameters.put("phone", str);
        weiboParameters.put(GameAppOperation.QQFAV_DATALINE_VERSION, WBConstants.WEIBO_SDK_VERSION_CODE);
        weiboParameters.put(com.taobao.accs.common.Constants.KEY_HTTP_CODE, str2);
        weiboParameters.put("cfrom", this.cfrom);
        this.mLoadingDlg.show();
        NetUtils.internalHttpRequest(this, SEND_SUBMIT, weiboParameters, Constants.HTTP_GET, new AnonymousClass_4(str));
    }

    public void onClick(View view) {
        String toString;
        String toString2;
        if (view == this.mGetCodeBtn) {
            toString = this.mPhoneNum.getText().toString();
            toString2 = this.mCountryCode.getText().toString();
            if (doCheckOnGetMsg(toString)) {
                this.mCountDownTimer.start();
                disableGetCodeBtn();
                getMsg(toString, toString2);
            }
        } else if (view == this.mPhoneNumClearBtn) {
            this.mPhoneNum.setText(a.d);
        } else if (view == this.mBtnRegist) {
            toString = this.mPhoneNum.getText().toString();
            toString2 = this.mCheckCode.getText().toString();
            if (doCheckOnSubmit(toString2)) {
                submit(toString, toString2);
            }
        } else if (view == this.mCountryLayout) {
            this.mTips.setVisibility(PHONE_NUM_CLEAR_BTN_ID);
            Intent intent = new Intent();
            intent.setClass(this, SelectCountryActivity.class);
            startActivityForResult(intent, SELECT_COUNTRY_REQUEST_CODE);
        }
    }

    public void onSizeChanged(int i, int i2, int i3, int i4) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        if (displayMetrics.widthPixels <= displayMetrics.heightPixels) {
            this.mMaxHeight = this.mMaxHeight < i2 ? i2 : this.mMaxHeight;
            int i5 = SELECT_COUNTRY_REQUEST_CODE;
            if (i2 < i4) {
                i5 = 1;
            } else if (i2 > i4 && i2 < this.mMaxHeight) {
                i5 = 1;
            } else if (i2 == i4 && i2 != this.mMaxHeight) {
                i5 = 1;
            }
            this.mInputHandler.sendEmptyMessage(i5);
        }
    }
}
