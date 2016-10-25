package com.tencent.connect.auth;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.e.comm.constants.Constants.KEYS;
import com.sina.weibo.sdk.constant.WBConstants;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.BaseApi.TempRequestListener;
import com.tencent.connect.common.Constants;
import com.tencent.connect.common.UIListenerManager;
import com.tencent.open.SocialConstants;
import com.tencent.open.a.f;
import com.tencent.open.b.d;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.ServerSetting;
import com.tencent.open.utils.SystemUtils;
import com.tencent.open.utils.ThreadManager;
import com.tencent.open.utils.Util;
import com.tencent.open.yyb.TitleBar;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.umeng.a;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.vod.VodPlayerActivity;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.ref.WeakReference;
import java.net.URLDecoder;
import org.android.agoo.message.MessageService;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ProGuard
public class AuthAgent extends BaseApi {
    public static final String SECURE_LIB_ARM64_FILE_NAME = "libwbsafeedit_64";
    public static final String SECURE_LIB_ARM_FILE_NAME = "libwbsafeedit";
    public static String SECURE_LIB_FILE_NAME = null;
    public static String SECURE_LIB_NAME = null;
    public static final String SECURE_LIB_X86_64_FILE_NAME = "libwbsafeedit_x86_64";
    public static final String SECURE_LIB_X86_FILE_NAME = "libwbsafeedit_x86";
    private IUiListener a;
    private String b;
    private WeakReference<Activity> c;

    // compiled from: ProGuard
    class AnonymousClass_1 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ IUiListener b;

        // compiled from: ProGuard
        class AnonymousClass_1 implements Runnable {
            final /* synthetic */ Activity a;

            AnonymousClass_1(Activity activity) {
                this.a = activity;
            }

            public void run() {
                AuthDialog authDialog = new AuthDialog(this.a, SystemUtils.ACTION_LOGIN, AnonymousClass_1.this.a, AnonymousClass_1.this, AuthAgent.this.mToken);
                if (this.a != null && !this.a.isFinishing()) {
                    authDialog.show();
                }
            }
        }

        AnonymousClass_1(String str, IUiListener iUiListener) {
            this.a = str;
            this.b = iUiListener;
        }

        public void run() {
            SystemUtils.extractSecureLib(SECURE_LIB_FILE_NAME, SECURE_LIB_NAME, XZBDevice.DOWNLOAD_LIST_FAILED);
            Activity activity = (Activity) AuthAgent.this.get();
            if (activity != null) {
                activity.runOnUiThread(new AnonymousClass_1(activity));
            }
        }
    }

    // compiled from: ProGuard
    private class CheckLoginListener implements IUiListener {
        IUiListener a;

        public CheckLoginListener(IUiListener iUiListener) {
            this.a = iUiListener;
        }

        public void onComplete(Object obj) {
            if (obj == null) {
                f.e("openSDK_LOG.AuthAgent", "CheckLoginListener response data is null");
                return;
            }
            JSONObject jSONObject = (JSONObject) obj;
            try {
                int i = jSONObject.getInt(KEYS.RET);
                Object string = i == 0 ? MsgConstant.KEY_SUCCESS : jSONObject.getString(SocialConstants.PARAM_SEND_MSG);
                if (this.a != null) {
                    this.a.onComplete(new JSONObject().put(KEYS.RET, i).put(SocialConstants.PARAM_SEND_MSG, string));
                }
            } catch (JSONException e) {
                e.printStackTrace();
                f.e("openSDK_LOG.AuthAgent", "CheckLoginListener response data format error");
            }
        }

        public void onError(UiError uiError) {
            if (this.a != null) {
                this.a.onError(uiError);
            }
        }

        public void onCancel() {
            if (this.a != null) {
                this.a.onCancel();
            }
        }
    }

    // compiled from: ProGuard
    private class FeedConfirmListener implements IUiListener {
        IUiListener a;
        private final String c;
        private final String d;
        private final String e;

        // compiled from: ProGuard
        private abstract class ButtonListener implements OnClickListener {
            Dialog d;

            ButtonListener(Dialog dialog) {
                this.d = dialog;
            }
        }

        // compiled from: ProGuard
        class AnonymousClass_1 extends ButtonListener {
            final /* synthetic */ IUiListener a;
            final /* synthetic */ Object b;

            AnonymousClass_1(Dialog dialog, IUiListener iUiListener, Object obj) {
                this.a = iUiListener;
                this.b = obj;
                super(dialog);
            }

            public void onClick(View view) {
                FeedConfirmListener.this.a();
                if (this.d != null && this.d.isShowing()) {
                    this.d.dismiss();
                }
                if (this.a != null) {
                    this.a.onComplete(this.b);
                }
            }
        }

        // compiled from: ProGuard
        class AnonymousClass_2 extends ButtonListener {
            final /* synthetic */ IUiListener a;
            final /* synthetic */ Object b;

            AnonymousClass_2(Dialog dialog, IUiListener iUiListener, Object obj) {
                this.a = iUiListener;
                this.b = obj;
                super(dialog);
            }

            public void onClick(View view) {
                if (this.d != null && this.d.isShowing()) {
                    this.d.dismiss();
                }
                if (this.a != null) {
                    this.a.onComplete(this.b);
                }
            }
        }

        // compiled from: ProGuard
        class AnonymousClass_3 implements OnCancelListener {
            final /* synthetic */ IUiListener a;
            final /* synthetic */ Object b;

            AnonymousClass_3(IUiListener iUiListener, Object obj) {
                this.a = iUiListener;
                this.b = obj;
            }

            public void onCancel(DialogInterface dialogInterface) {
                if (this.a != null) {
                    this.a.onComplete(this.b);
                }
            }
        }

        public FeedConfirmListener(IUiListener iUiListener) {
            this.c = "sendinstall";
            this.d = "installwording";
            this.e = "http://appsupport.qq.com/cgi-bin/qzapps/mapp_addapp.cgi";
            this.a = iUiListener;
        }

        public void onComplete(Object obj) {
            Object obj2;
            Object obj3 = null;
            if (obj != null) {
                JSONObject jSONObject = (JSONObject) obj;
                if (jSONObject != null) {
                    String string;
                    Object obj4;
                    String str = a.d;
                    try {
                        if (jSONObject.getInt("sendinstall") == 1) {
                            int i = 1;
                        }
                        string = jSONObject.getString("installwording");
                        obj4 = obj3;
                    } catch (JSONException e) {
                        obj2 = null;
                        f.d("openSDK_LOG.AuthAgent", "FeedConfirmListener onComplete There is no value for sendinstall.");
                        String str2 = str;
                        obj4 = obj2;
                        string = str2;
                    }
                    obj2 = URLDecoder.decode(string);
                    f.a("openSDK_LOG.AuthAgent", new StringBuilder(" WORDING = ").append(obj2).append("xx").toString());
                    if (obj4 != null && !TextUtils.isEmpty(obj2)) {
                        a(obj2, this.a, obj);
                    } else if (this.a != null) {
                        this.a.onComplete(obj);
                    }
                }
            }
        }

        private void a(String str, IUiListener iUiListener, Object obj) {
            Drawable drawable = null;
            Activity activity = (Activity) AuthAgent.this.c.get();
            if (activity != null) {
                PackageInfo packageInfo;
                Dialog dialog = new Dialog(activity);
                dialog.requestWindowFeature(1);
                PackageManager packageManager = activity.getPackageManager();
                try {
                    packageInfo = packageManager.getPackageInfo(activity.getPackageName(), 0);
                } catch (NameNotFoundException e) {
                    e.printStackTrace();
                    packageInfo = null;
                }
                if (packageInfo != null) {
                    drawable = packageInfo.applicationInfo.loadIcon(packageManager);
                }
                OnClickListener anonymousClass_1 = new AnonymousClass_1(dialog, iUiListener, obj);
                OnClickListener anonymousClass_2 = new AnonymousClass_2(dialog, iUiListener, obj);
                Drawable colorDrawable = new ColorDrawable();
                colorDrawable.setAlpha(0);
                dialog.getWindow().setBackgroundDrawable(colorDrawable);
                dialog.setContentView(a(activity, drawable, str, anonymousClass_1, anonymousClass_2));
                dialog.setOnCancelListener(new AnonymousClass_3(iUiListener, obj));
                if (activity != null && !activity.isFinishing()) {
                    dialog.show();
                }
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private android.graphics.drawable.Drawable a(java.lang.String r8, android.content.Context r9) {
            throw new UnsupportedOperationException("Method not decompiled: com.tencent.connect.auth.AuthAgent.FeedConfirmListener.a(java.lang.String, android.content.Context):android.graphics.drawable.Drawable");
            /*
            this = this;
            r1 = 0;
            r0 = r9.getApplicationContext();
            r0 = r0.getAssets();
            r2 = r0.open(r8);	 Catch:{ IOException -> 0x0034 }
            if (r2 != 0) goto L_0x0011;
        L_0x000f:
            r0 = r1;
        L_0x0010:
            return r0;
        L_0x0011:
            r0 = ".9.png";
            r0 = r8.endsWith(r0);	 Catch:{ IOException -> 0x0034 }
            if (r0 == 0) goto L_0x0044;
        L_0x001a:
            r0 = android.graphics.BitmapFactory.decodeStream(r2);	 Catch:{ OutOfMemoryError -> 0x003c }
            r2 = r0;
        L_0x001f:
            if (r2 == 0) goto L_0x0042;
        L_0x0021:
            r3 = r2.getNinePatchChunk();	 Catch:{ IOException -> 0x0034 }
            android.graphics.NinePatch.isNinePatchChunk(r3);	 Catch:{ IOException -> 0x0034 }
            r0 = new android.graphics.drawable.NinePatchDrawable;	 Catch:{ IOException -> 0x0034 }
            r4 = new android.graphics.Rect;	 Catch:{ IOException -> 0x0034 }
            r4.<init>();	 Catch:{ IOException -> 0x0034 }
            r5 = 0;
            r0.<init>(r2, r3, r4, r5);	 Catch:{ IOException -> 0x0034 }
            goto L_0x0010;
        L_0x0034:
            r0 = move-exception;
            r6 = r0;
            r0 = r1;
            r1 = r6;
        L_0x0038:
            r1.printStackTrace();
            goto L_0x0010;
        L_0x003c:
            r0 = move-exception;
            r0.printStackTrace();	 Catch:{ IOException -> 0x0034 }
            r2 = r1;
            goto L_0x001f;
        L_0x0042:
            r0 = r1;
            goto L_0x0010;
        L_0x0044:
            r0 = android.graphics.drawable.Drawable.createFromStream(r2, r8);	 Catch:{ IOException -> 0x0034 }
            r2.close();	 Catch:{ IOException -> 0x004c }
            goto L_0x0010;
        L_0x004c:
            r1 = move-exception;
            goto L_0x0038;
            */
        }

        private View a(Context context, Drawable drawable, String str, OnClickListener onClickListener, OnClickListener onClickListener2) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            float f = displayMetrics.density;
            View relativeLayout = new RelativeLayout(context);
            View imageView = new ImageView(context);
            imageView.setImageDrawable(drawable);
            imageView.setScaleType(ScaleType.FIT_XY);
            imageView.setId(1);
            int i = (int) (60.0f * f);
            int i2 = (int) (14.0f * f);
            int i3 = (int) (18.0f * f);
            int i4 = (int) (6.0f * f);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, i);
            layoutParams.addRule(XZBDevice.Pause);
            layoutParams.setMargins(0, i3, i4, i3);
            relativeLayout.addView(imageView, layoutParams);
            imageView = new TextView(context);
            imageView.setText(str);
            imageView.setTextSize(14.0f);
            imageView.setGravity(XZBDevice.DOWNLOAD_LIST_FAILED);
            imageView.setIncludeFontPadding(false);
            imageView.setPadding(0, 0, 0, 0);
            imageView.setLines(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            imageView.setId(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
            imageView.setMinWidth((int) (185.0f * f));
            LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(1, 1);
            layoutParams2.addRule(R.styleable.Toolbar_contentInsetEnd, 1);
            layoutParams2.setMargins(0, 0, (int) (5.0f * f), 0);
            relativeLayout.addView(imageView, layoutParams2);
            imageView = new View(context);
            imageView.setBackgroundColor(Color.rgb(214, 214, 214));
            imageView.setId(XZBDevice.DOWNLOAD_LIST_FAILED);
            layoutParams2 = new RelativeLayout.LayoutParams(-2, 2);
            layoutParams2.addRule(XZBDevice.DOWNLOAD_LIST_FAILED, 1);
            layoutParams2.addRule(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, 1);
            layoutParams2.addRule(R.styleable.Toolbar_contentInsetLeft, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
            layoutParams2.setMargins(0, 0, 0, (int) (12.0f * f));
            relativeLayout.addView(imageView, layoutParams2);
            imageView = new LinearLayout(context);
            layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, 1);
            layoutParams2.addRule(R.styleable.Toolbar_contentInsetLeft, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
            layoutParams2.addRule(XZBDevice.DOWNLOAD_LIST_FAILED, XZBDevice.DOWNLOAD_LIST_FAILED);
            View button = new Button(context);
            button.setText("\u8df3\u8fc7");
            button.setBackgroundDrawable(a("buttonNegt.png", context));
            button.setTextColor(Color.rgb(R.styleable.AppCompatTheme_actionModeShareDrawable, R.styleable.AppCompatTheme_buttonBarNegativeButtonStyle, 131));
            button.setTextSize(TitleBar.BACKBTN_LEFT_MARGIN);
            button.setOnClickListener(onClickListener2);
            button.setId(XZBDevice.DOWNLOAD_LIST_ALL);
            LayoutParams layoutParams3 = new LinearLayout.LayoutParams(0, (int) (45.0f * f));
            layoutParams3.rightMargin = i2;
            layoutParams3.leftMargin = (int) (4.0f * f);
            layoutParams3.weight = 1.0f;
            imageView.addView(button, layoutParams3);
            button = new Button(context);
            button.setText("\u786e\u5b9a");
            button.setTextSize(TitleBar.BACKBTN_LEFT_MARGIN);
            button.setTextColor(Color.rgb(VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX, VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX, VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX));
            button.setBackgroundDrawable(a("buttonPost.png", context));
            button.setOnClickListener(onClickListener);
            layoutParams3 = new LinearLayout.LayoutParams(0, (int) (45.0f * f));
            layoutParams3.weight = 1.0f;
            layoutParams3.rightMargin = (int) (4.0f * f);
            imageView.addView(button, layoutParams3);
            relativeLayout.addView(imageView, layoutParams2);
            LayoutParams layoutParams4 = new FrameLayout.LayoutParams((int) (279.0f * f), (int) (163.0f * f));
            relativeLayout.setPadding(i2, 0, (int) (12.0f * f), (int) (12.0f * f));
            relativeLayout.setLayoutParams(layoutParams4);
            relativeLayout.setBackgroundColor(Color.rgb(247, 251, 247));
            Drawable paintDrawable = new PaintDrawable(Color.rgb(247, 251, 247));
            paintDrawable.setCornerRadius(f * 5.0f);
            relativeLayout.setBackgroundDrawable(paintDrawable);
            return relativeLayout;
        }

        protected void a() {
            Bundle g = AuthAgent.this.composeActivityParams();
            Activity activity = (Activity) AuthAgent.this.c.get();
            if (activity != null) {
                HttpUtils.requestAsync(AuthAgent.this.mToken, activity, "http://appsupport.qq.com/cgi-bin/qzapps/mapp_addapp.cgi", g, Constants.HTTP_POST, null);
            }
        }

        public void onError(UiError uiError) {
            if (this.a != null) {
                this.a.onError(uiError);
            }
        }

        public void onCancel() {
            if (this.a != null) {
                this.a.onCancel();
            }
        }
    }

    // compiled from: ProGuard
    private class TokenListener implements IUiListener {
        private final IUiListener b;
        private final boolean c;
        private final Context d;

        public TokenListener(Context context, IUiListener iUiListener, boolean z, boolean z2) {
            this.d = context;
            this.b = iUiListener;
            this.c = z;
            f.b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener()");
        }

        public void onComplete(Object obj) {
            f.b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onComplete");
            JSONObject jSONObject = (JSONObject) obj;
            try {
                String string = jSONObject.getString(Constants.PARAM_ACCESS_TOKEN);
                String string2 = jSONObject.getString(Constants.PARAM_EXPIRES_IN);
                String string3 = jSONObject.getString(SocialConstants.PARAM_OPEN_ID);
                if (!(string == null || AuthAgent.this.mToken == null || string3 == null)) {
                    AuthAgent.this.mToken.setAccessToken(string, string2);
                    AuthAgent.this.mToken.setOpenId(string3);
                    com.tencent.connect.a.a.d(this.d, AuthAgent.this.mToken);
                }
                string = jSONObject.getString(Constants.PARAM_PLATFORM_ID);
                if (string != null) {
                    try {
                        this.d.getSharedPreferences(Constants.PREFERENCE_PF, 0).edit().putString(Constants.PARAM_PLATFORM_ID, string).commit();
                    } catch (Throwable e) {
                        e.printStackTrace();
                        f.b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onComplete error", e);
                    }
                }
                if (this.c) {
                    CookieSyncManager.getInstance().sync();
                }
            } catch (Throwable e2) {
                e2.printStackTrace();
                f.b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onComplete error", e2);
            }
            this.b.onComplete(jSONObject);
            AuthAgent.this.releaseResource();
            f.b();
        }

        public void onError(UiError uiError) {
            f.b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onError");
            this.b.onError(uiError);
            f.b();
        }

        public void onCancel() {
            f.b("openSDK_LOG.AuthAgent", "OpenUi, TokenListener() onCancel");
            this.b.onCancel();
            f.b();
        }
    }

    static {
        SECURE_LIB_FILE_NAME = SECURE_LIB_ARM_FILE_NAME;
        SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
        String str = Build.CPU_ABI;
        if (str == null || str.equals(a.d)) {
            SECURE_LIB_FILE_NAME = SECURE_LIB_ARM_FILE_NAME;
            SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
            f.c("openSDK_LOG.AuthAgent", "is arm(default) architecture");
        } else if (str.equalsIgnoreCase("arm64-v8a")) {
            SECURE_LIB_FILE_NAME = SECURE_LIB_ARM64_FILE_NAME;
            SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
            f.c("openSDK_LOG.AuthAgent", "is arm64-v8a architecture");
        } else if (str.equalsIgnoreCase("x86")) {
            SECURE_LIB_FILE_NAME = SECURE_LIB_X86_FILE_NAME;
            SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
            f.c("openSDK_LOG.AuthAgent", "is x86 architecture");
        } else if (str.equalsIgnoreCase("x86_64")) {
            SECURE_LIB_FILE_NAME = SECURE_LIB_X86_64_FILE_NAME;
            SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
            f.c("openSDK_LOG.AuthAgent", "is x86_64 architecture");
        } else {
            SECURE_LIB_FILE_NAME = SECURE_LIB_ARM_FILE_NAME;
            SECURE_LIB_NAME = SECURE_LIB_FILE_NAME + ".so";
            f.c("openSDK_LOG.AuthAgent", "is arm(default) architecture");
        }
    }

    public AuthAgent(QQToken qQToken) {
        super(qQToken);
    }

    public int doLogin(Activity activity, String str, IUiListener iUiListener) {
        return doLogin(activity, str, iUiListener, false, null);
    }

    public int doLogin(Activity activity, String str, IUiListener iUiListener, boolean z, Fragment fragment) {
        this.b = str;
        this.c = new WeakReference(activity);
        this.a = iUiListener;
        if (a(activity, fragment, z)) {
            f.c("openSDK_LOG.AuthAgent", "OpenUi, showUi, return Constants.UI_ACTIVITY");
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), MessageService.MSG_DB_NOTIFY_CLICK, MessageService.MSG_DB_NOTIFY_REACHED, Constants.VIA_SHARE_TYPE_TEXT, MessageService.MSG_DB_READY_REPORT, MessageService.MSG_DB_READY_REPORT, MessageService.MSG_DB_READY_REPORT);
            return 1;
        }
        d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), MessageService.MSG_DB_NOTIFY_CLICK, MessageService.MSG_DB_NOTIFY_REACHED, Constants.VIA_SHARE_TYPE_TEXT, MessageService.MSG_DB_NOTIFY_REACHED, MessageService.MSG_DB_READY_REPORT, MessageService.MSG_DB_READY_REPORT);
        f.d("openSDK_LOG.AuthAgent", "doLogin startActivity fail show dialog.");
        this.a = new FeedConfirmListener(this.a);
        return a(z, this.a);
    }

    public void releaseResource() {
        this.c = null;
        this.a = null;
    }

    private int a(boolean z, IUiListener iUiListener) {
        f.c("openSDK_LOG.AuthAgent", "OpenUi, showDialog -- start");
        CookieSyncManager.createInstance(Global.getContext());
        Bundle composeCGIParams = composeCGIParams();
        if (z) {
            composeCGIParams.putString("isadd", MessageService.MSG_DB_NOTIFY_REACHED);
        }
        composeCGIParams.putString(Constants.PARAM_SCOPE, this.b);
        composeCGIParams.putString(Constants.PARAM_CLIENT_ID, this.mToken.getAppId());
        if (isOEM) {
            composeCGIParams.putString(Constants.PARAM_PLATFORM_ID, new StringBuilder("desktop_m_qq-").append(installChannel).append("-android-").append(registerChannel).append(SocializeConstants.OP_DIVIDER_MINUS).append(businessId).toString());
        } else {
            composeCGIParams.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
        }
        String str = (System.currentTimeMillis() / 1000);
        composeCGIParams.putString(com.taobao.accs.common.Constants.KEY_SECURITY_SIGN, SystemUtils.getAppSignatureMD5(Global.getContext(), str));
        composeCGIParams.putString("time", str);
        composeCGIParams.putString(WBConstants.AUTH_PARAMS_DISPLAY, "mobile");
        composeCGIParams.putString(WBConstants.AUTH_PARAMS_RESPONSE_TYPE, "token");
        composeCGIParams.putString(WBConstants.AUTH_PARAMS_REDIRECT_URL, ServerSetting.DEFAULT_REDIRECT_URI);
        composeCGIParams.putString("cancel_display", MessageService.MSG_DB_NOTIFY_REACHED);
        composeCGIParams.putString("switch", MessageService.MSG_DB_NOTIFY_REACHED);
        composeCGIParams.putString("status_userip", Util.getUserIp());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ServerSetting.getInstance().getEnvUrl(Global.getContext(), ServerSetting.DEFAULT_CGI_AUTHORIZE));
        stringBuilder.append(HttpUtils.encodeUrl(composeCGIParams));
        String toString = stringBuilder.toString();
        IUiListener tokenListener = new TokenListener(Global.getContext(), iUiListener, true, false);
        f.b("openSDK_LOG.AuthAgent", "OpenUi, showDialog TDialog");
        ThreadManager.executeOnSubThread(new AnonymousClass_1(toString, tokenListener));
        f.c("openSDK_LOG.AuthAgent", "OpenUi, showDialog -- end");
        return XZBDevice.DOWNLOAD_LIST_RECYCLE;
    }

    private boolean a(Activity activity, Fragment fragment, boolean z) {
        f.c("openSDK_LOG.AuthAgent", "startActionActivity() -- start");
        Intent targetActivityIntent = getTargetActivityIntent("com.tencent.open.agent.AgentActivity");
        if (targetActivityIntent != null) {
            Bundle composeCGIParams = composeCGIParams();
            if (z) {
                composeCGIParams.putString("isadd", MessageService.MSG_DB_NOTIFY_REACHED);
            }
            composeCGIParams.putString(Constants.PARAM_SCOPE, this.b);
            composeCGIParams.putString(Constants.PARAM_CLIENT_ID, this.mToken.getAppId());
            if (isOEM) {
                composeCGIParams.putString(Constants.PARAM_PLATFORM_ID, new StringBuilder("desktop_m_qq-").append(installChannel).append("-android-").append(registerChannel).append(SocializeConstants.OP_DIVIDER_MINUS).append(businessId).toString());
            } else {
                composeCGIParams.putString(Constants.PARAM_PLATFORM_ID, Constants.DEFAULT_PF);
            }
            composeCGIParams.putString("need_pay", MessageService.MSG_DB_NOTIFY_REACHED);
            composeCGIParams.putString(Constants.KEY_APP_NAME, SystemUtils.getAppName(Global.getContext()));
            targetActivityIntent.putExtra(Constants.KEY_ACTION, SystemUtils.ACTION_LOGIN);
            targetActivityIntent.putExtra(Constants.KEY_PARAMS, composeCGIParams);
            if (hasActivityForIntent(targetActivityIntent)) {
                this.a = new FeedConfirmListener(this.a);
                UIListenerManager.getInstance().setListenerWithRequestcode(Constants.REQUEST_LOGIN, this.a);
                if (fragment != null) {
                    f.b("openSDK_LOG.AuthAgent", "startAssitActivity fragment");
                    startAssitActivity(fragment, targetActivityIntent, Constants.REQUEST_LOGIN);
                } else {
                    f.b("openSDK_LOG.AuthAgent", "startAssitActivity activity");
                    startAssitActivity(activity, targetActivityIntent, Constants.REQUEST_LOGIN);
                }
                f.c("openSDK_LOG.AuthAgent", "startActionActivity() -- end, found activity for loginIntent");
                d.a().a(0, "LOGIN_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), a.d, Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, a.d);
                return true;
            }
        }
        d.a().a(1, "LOGIN_CHECK_SDK", Constants.DEFAULT_UIN, this.mToken.getAppId(), a.d, Long.valueOf(SystemClock.elapsedRealtime()), 0, 1, "startActionActivity fail");
        f.c("openSDK_LOG.AuthAgent", "startActionActivity() -- end, no target activity for loginIntent");
        return false;
    }

    protected void a(IUiListener iUiListener) {
        f.c("openSDK_LOG.AuthAgent", "reportDAU() -- start");
        String str = "tencent&sdk&qazxc***14969%%";
        String str2 = "qzone3.4";
        Object accessToken = this.mToken.getAccessToken();
        Object openId = this.mToken.getOpenId();
        Object appId = this.mToken.getAppId();
        Object obj = a.d;
        if (!(TextUtils.isEmpty(accessToken) || TextUtils.isEmpty(openId) || TextUtils.isEmpty(appId))) {
            obj = Util.encrypt(str + accessToken + appId + openId + str2);
        }
        if (TextUtils.isEmpty(obj)) {
            f.e("openSDK_LOG.AuthAgent", "reportDAU -- encrytoken is null");
            return;
        }
        Bundle composeCGIParams = composeCGIParams();
        composeCGIParams.putString("encrytoken", obj);
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "https://openmobile.qq.com/user/user_login_statis", composeCGIParams, Constants.HTTP_POST, null);
        f.c("openSDK_LOG.AuthAgent", "reportDAU() -- end");
    }

    protected void b(IUiListener iUiListener) {
        Bundle composeCGIParams = composeCGIParams();
        composeCGIParams.putString("reqType", "checkLogin");
        IRequestListener tempRequestListener = new TempRequestListener(new CheckLoginListener(iUiListener));
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "https://openmobile.qq.com/v3/user/get_info", composeCGIParams, Constants.HTTP_GET, tempRequestListener);
    }
}
