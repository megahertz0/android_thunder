package com.tencent.connect.avatar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.alipay.sdk.util.h;
import com.qq.e.comm.constants.Constants.KEYS;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.BaseApi.TempRequestListener;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.open.b.d;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.Util;
import com.tencent.open.yyb.AppbarJsBridge;
import com.tencent.open.yyb.TitleBar;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.android.agoo.message.MessageService;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: ProGuard
public class ImageActivity extends Activity {
    RelativeLayout a;
    private QQToken b;
    private String c;
    private Handler d;
    private c e;
    private Button f;
    private Button g;
    private b h;
    private TextView i;
    private ProgressBar j;
    private int k;
    private boolean l;
    private long m;
    private int n;
    private final int o;
    private final int p;
    private Rect q;
    private String r;
    private Bitmap s;
    private final OnClickListener t;
    private final OnClickListener u;
    private final IUiListener v;
    private final IUiListener w;

    // compiled from: ProGuard
    class AnonymousClass_4 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ int b;

        AnonymousClass_4(String str, int i) {
            this.a = str;
            this.b = i;
        }

        public void run() {
            ImageActivity.this.b(this.a, this.b);
        }
    }

    // compiled from: ProGuard
    private class QQAvatarImp extends BaseApi {
        public QQAvatarImp(QQToken qQToken) {
            super(qQToken);
        }

        public void setAvator(Bitmap bitmap, IUiListener iUiListener) {
            Bundle composeCGIParams = composeCGIParams();
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.JPEG, R.styleable.AppCompatTheme_textAppearanceLargePopupMenu, byteArrayOutputStream);
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            bitmap.recycle();
            IRequestListener tempRequestListener = new TempRequestListener(iUiListener);
            composeCGIParams.putByteArray(SocialConstants.PARAM_AVATAR_URI, toByteArray);
            HttpUtils.requestAsync(this.mToken, Global.getContext(), "user/set_user_face", composeCGIParams, Constants.HTTP_POST, tempRequestListener);
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SET_AVATAR_SUCCEED, Constants.VIA_REPORT_TYPE_SET_AVATAR, Constants.VIA_ACT_TYPE_NINETEEN, MessageService.MSG_DB_READY_REPORT);
        }
    }

    // compiled from: ProGuard
    class a extends View {
        public a(Context context) {
            super(context);
        }

        public void a(Button button) {
            Drawable stateListDrawable = new StateListDrawable();
            Drawable a = ImageActivity.this.b("com.tencent.plus.blue_normal.png");
            Drawable a2 = ImageActivity.this.b("com.tencent.plus.blue_down.png");
            Drawable a3 = ImageActivity.this.b("com.tencent.plus.blue_disable.png");
            stateListDrawable.addState(View.PRESSED_ENABLED_STATE_SET, a2);
            stateListDrawable.addState(View.ENABLED_FOCUSED_STATE_SET, a);
            stateListDrawable.addState(View.ENABLED_STATE_SET, a);
            stateListDrawable.addState(View.FOCUSED_STATE_SET, a);
            stateListDrawable.addState(View.EMPTY_STATE_SET, a3);
            button.setBackgroundDrawable(stateListDrawable);
        }

        public void b(Button button) {
            Drawable stateListDrawable = new StateListDrawable();
            Drawable a = ImageActivity.this.b("com.tencent.plus.gray_normal.png");
            Drawable a2 = ImageActivity.this.b("com.tencent.plus.gray_down.png");
            Drawable a3 = ImageActivity.this.b("com.tencent.plus.gray_disable.png");
            stateListDrawable.addState(View.PRESSED_ENABLED_STATE_SET, a2);
            stateListDrawable.addState(View.ENABLED_FOCUSED_STATE_SET, a);
            stateListDrawable.addState(View.ENABLED_STATE_SET, a);
            stateListDrawable.addState(View.FOCUSED_STATE_SET, a);
            stateListDrawable.addState(View.EMPTY_STATE_SET, a3);
            button.setBackgroundDrawable(stateListDrawable);
        }
    }

    public ImageActivity() {
        this.k = 0;
        this.l = false;
        this.m = 0;
        this.n = 0;
        this.o = 640;
        this.p = 640;
        this.q = new Rect();
        this.t = new OnClickListener() {
            public void onClick(View view) {
                ImageActivity.this.j.setVisibility(0);
                ImageActivity.this.g.setEnabled(false);
                ImageActivity.this.g.setTextColor(Color.rgb(R.styleable.Toolbar_navigationContentDescription, R.styleable.Toolbar_navigationContentDescription, R.styleable.Toolbar_navigationContentDescription));
                ImageActivity.this.f.setEnabled(false);
                ImageActivity.this.f.setTextColor(Color.rgb(R.styleable.AppCompatTheme_actionModeShareDrawable, R.styleable.AppCompatTheme_alertDialogTheme, 134));
                new Thread(new Runnable() {
                    public void run() {
                        AnonymousClass_2.this.c();
                    }
                }).start();
                if (ImageActivity.this.l) {
                    ImageActivity.this.a("10657", 0);
                    return;
                }
                ImageActivity.this.a("10655", System.currentTimeMillis() - ImageActivity.this.m);
                if (ImageActivity.this.e.b) {
                    ImageActivity.this.a("10654", 0);
                }
            }
        };
        this.u = new OnClickListener() {
            public void onClick(View view) {
                ImageActivity.this.a("10656", System.currentTimeMillis() - ImageActivity.this.m);
                ImageActivity.this.setResult(0);
                ImageActivity.this.d();
            }
        };
        this.v = new IUiListener() {
            public void onError(UiError uiError) {
                ImageActivity.this.g.setEnabled(true);
                ImageActivity.this.g.setTextColor(-1);
                ImageActivity.this.f.setEnabled(true);
                ImageActivity.this.f.setTextColor(-1);
                ImageActivity.this.f.setText("\u91cd\u8bd5");
                ImageActivity.this.j.setVisibility(XZBDevice.Wait);
                ImageActivity.this.l = true;
                ImageActivity.this.a(uiError.errorMessage, 1);
                ImageActivity.this.a("10660", 0);
            }

            public void onComplete(Object obj) {
                ImageActivity.this.g.setEnabled(true);
                ImageActivity.this.g.setTextColor(-1);
                ImageActivity.this.f.setEnabled(true);
                ImageActivity.this.f.setTextColor(-1);
                ImageActivity.this.j.setVisibility(XZBDevice.Wait);
                JSONObject jSONObject = (JSONObject) obj;
                try {
                    int i = jSONObject.getInt(KEYS.RET);
                } catch (JSONException e) {
                    e.printStackTrace();
                    i = -1;
                }
                if (i == 0) {
                    ImageActivity.this.a("\u8bbe\u7f6e\u6210\u529f", 0);
                    ImageActivity.this.a("10658", 0);
                    d.a().a(ImageActivity.this.b.getOpenId(), ImageActivity.this.b.getAppId(), Constants.VIA_SET_AVATAR_SUCCEED, Constants.VIA_REPORT_TYPE_SET_AVATAR, MessageService.MSG_DB_NOTIFY_DISMISS, MessageService.MSG_DB_READY_REPORT);
                    Context context = ImageActivity.this;
                    if (!(ImageActivity.this.c == null || com.umeng.a.d.equals(ImageActivity.this.c))) {
                        Intent intent = new Intent();
                        intent.setClassName(context, ImageActivity.this.c);
                        if (context.getPackageManager().resolveActivity(intent, 0) != null) {
                            context.startActivity(intent);
                        }
                    }
                    ImageActivity.this.a(0, jSONObject.toString(), null, null);
                    ImageActivity.this.d();
                    return;
                }
                ImageActivity.this.a("\u8bbe\u7f6e\u51fa\u9519\u4e86\uff0c\u8bf7\u91cd\u65b0\u767b\u5f55\u518d\u5c1d\u8bd5\u4e0b\u5462\uff1a\uff09", 1);
                d.a().a(ImageActivity.this.b.getOpenId(), ImageActivity.this.b.getAppId(), Constants.VIA_SET_AVATAR_SUCCEED, Constants.VIA_REPORT_TYPE_SET_AVATAR, Constants.VIA_ACT_TYPE_NINETEEN, MessageService.MSG_DB_NOTIFY_REACHED);
            }

            public void onCancel() {
            }
        };
        this.w = new IUiListener() {

            // compiled from: ProGuard
            class AnonymousClass_1 implements Runnable {
                final /* synthetic */ String a;

                AnonymousClass_1(String str) {
                    this.a = str;
                }

                public void run() {
                    AnonymousClass_6.this.a.c(this.a);
                }
            }

            public void onError(UiError uiError) {
                a(0);
            }

            public void onComplete(Object obj) {
                JSONObject jSONObject = (JSONObject) obj;
                int i = -1;
                try {
                    i = jSONObject.getInt(KEYS.RET);
                    if (i == 0) {
                        ImageActivity.this.d.post(new AnonymousClass_1(jSONObject.getString("nickname")));
                        ImageActivity.this.a("10659", 0);
                    } else {
                        ImageActivity.this.a("10661", 0);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (i != 0) {
                    a(i);
                }
            }

            public void onCancel() {
            }

            private void a(int i) {
                if (ImageActivity.this.k < 2) {
                    ImageActivity.this.e();
                }
            }
        };
    }

    private Bitmap a(String str) throws IOException {
        int i = 1;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        Uri parse = Uri.parse(str);
        InputStream openInputStream = getContentResolver().openInputStream(parse);
        if (openInputStream == null) {
            return null;
        }
        try {
            BitmapFactory.decodeStream(openInputStream, null, options);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        openInputStream.close();
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        while (i2 * i3 > 4194304) {
            i2 /= 2;
            i3 /= 2;
            i *= 2;
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = i;
        try {
            return BitmapFactory.decodeStream(getContentResolver().openInputStream(parse), null, options);
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private Drawable b(String str) {
        Drawable createFromStream;
        try {
            InputStream open = getAssets().open(str);
            createFromStream = Drawable.createFromStream(open, str);
            try {
                open.close();
            } catch (IOException e) {
                IOException e2 = e;
                e2.printStackTrace();
                return createFromStream;
            }
        } catch (IOException e3) {
            IOException iOException = e3;
            createFromStream = null;
            e2 = iOException;
            e2.printStackTrace();
            return createFromStream;
        }
        return createFromStream;
    }

    private View a() {
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        LayoutParams layoutParams2 = new LayoutParams(-1, -1);
        LayoutParams layoutParams3 = new LayoutParams(-2, -2);
        this.a = new RelativeLayout(this);
        this.a.setLayoutParams(layoutParams);
        this.a.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        View relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(layoutParams3);
        this.a.addView(relativeLayout);
        this.e = new c(this);
        this.e.setLayoutParams(layoutParams2);
        this.e.setScaleType(ScaleType.MATRIX);
        relativeLayout.addView(this.e);
        this.h = new b(this);
        LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(layoutParams2);
        layoutParams4.addRule(XZBDevice.Predownload, -1);
        layoutParams4.addRule(XZBDevice.Delete, -1);
        this.h.setLayoutParams(layoutParams4);
        relativeLayout.addView(this.h);
        relativeLayout = new LinearLayout(this);
        layoutParams2 = new RelativeLayout.LayoutParams(-2, a.a(this, 80.0f));
        layoutParams2.addRule(XZBDevice.Predownload, -1);
        relativeLayout.setLayoutParams(layoutParams2);
        relativeLayout.setOrientation(0);
        relativeLayout.setGravity(R.styleable.Toolbar_maxButtonHeight);
        this.a.addView(relativeLayout);
        View imageView = new ImageView(this);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(a.a(this, 24.0f), a.a(this, 24.0f)));
        imageView.setImageDrawable(b("com.tencent.plus.logo.png"));
        relativeLayout.addView(imageView);
        this.i = new TextView(this);
        layoutParams2 = new LinearLayout.LayoutParams(layoutParams3);
        layoutParams2.leftMargin = a.a(this, 7.0f);
        this.i.setLayoutParams(layoutParams2);
        this.i.setEllipsize(TruncateAt.END);
        this.i.setSingleLine();
        this.i.setTextColor(-1);
        this.i.setTextSize(24.0f);
        this.i.setVisibility(XZBDevice.Wait);
        relativeLayout.addView(this.i);
        relativeLayout = new RelativeLayout(this);
        layoutParams2 = new RelativeLayout.LayoutParams(-1, a.a(this, 60.0f));
        layoutParams2.addRule(XZBDevice.Fail, -1);
        layoutParams2.addRule(XZBDevice.Pause, -1);
        relativeLayout.setLayoutParams(layoutParams2);
        relativeLayout.setBackgroundDrawable(b("com.tencent.plus.bar.png"));
        int a = a.a(this, TitleBar.SHAREBTN_RIGHT_MARGIN);
        relativeLayout.setPadding(a, a, a, 0);
        this.a.addView(relativeLayout);
        a aVar = new a(this);
        int a2 = a.a(this, 14.0f);
        int a3 = a.a(this, 7.0f);
        this.g = new Button(this);
        this.g.setLayoutParams(new RelativeLayout.LayoutParams(a.a(this, 78.0f), a.a(this, 45.0f)));
        this.g.setText("\u53d6\u6d88");
        this.g.setTextColor(-1);
        this.g.setTextSize(18.0f);
        this.g.setPadding(a2, a3, a2, a3);
        aVar.b(this.g);
        relativeLayout.addView(this.g);
        this.f = new Button(this);
        LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(a.a(this, 78.0f), a.a(this, 45.0f));
        layoutParams5.addRule(XZBDevice.Success, -1);
        this.f.setLayoutParams(layoutParams5);
        this.f.setTextColor(-1);
        this.f.setTextSize(18.0f);
        this.f.setPadding(a2, a3, a2, a3);
        this.f.setText("\u9009\u53d6");
        aVar.a(this.f);
        relativeLayout.addView(this.f);
        imageView = new TextView(this);
        layoutParams4 = new RelativeLayout.LayoutParams(layoutParams3);
        layoutParams4.addRule(XZBDevice.Upload, -1);
        imageView.setLayoutParams(layoutParams4);
        imageView.setText("\u79fb\u52a8\u548c\u7f29\u653e");
        imageView.setPadding(0, a.a(this, 3.0f), 0, 0);
        imageView.setTextSize(18.0f);
        imageView.setTextColor(-1);
        relativeLayout.addView(imageView);
        this.j = new ProgressBar(this);
        layoutParams = new RelativeLayout.LayoutParams(layoutParams3);
        layoutParams.addRule(XZBDevice.Predownload, -1);
        layoutParams.addRule(XZBDevice.Delete, -1);
        this.j.setLayoutParams(layoutParams);
        this.j.setVisibility(XZBDevice.Wait);
        this.a.addView(this.j);
        return this.a;
    }

    private void b() {
        try {
            this.s = a(this.r);
            if (this.s == null) {
                throw new IOException(new StringBuilder("cannot read picture: '").append(this.r).append("'!").toString());
            }
            this.e.setImageBitmap(this.s);
            this.f.setOnClickListener(this.t);
            this.g.setOnClickListener(this.u);
            this.a.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    ImageActivity.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    ImageActivity.this.q = ImageActivity.this.h.a();
                    ImageActivity.this.e.a(ImageActivity.this.q);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            String str = Constants.MSG_IMAGE_ERROR;
            a(str, 1);
            a(AppbarJsBridge.AUTHORIZE_FAIL, null, str, e.getMessage());
            d();
        }
    }

    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setRequestedOrientation(1);
        setContentView(a());
        this.d = new Handler();
        Bundle bundleExtra = getIntent().getBundleExtra(Constants.KEY_PARAMS);
        this.r = bundleExtra.getString(SocialConstants.PARAM_AVATAR_URI);
        this.c = bundleExtra.getString("return_activity");
        String string = bundleExtra.getString(SocialConstants.PARAM_APP_ID);
        String string2 = bundleExtra.getString(Constants.PARAM_ACCESS_TOKEN);
        long j = bundleExtra.getLong(Constants.PARAM_EXPIRES_IN);
        String string3 = bundleExtra.getString(SocialConstants.PARAM_OPEN_ID);
        this.n = bundleExtra.getInt("exitAnim");
        this.b = new QQToken(string);
        this.b.setAccessToken(string2, ((j - System.currentTimeMillis()) / 1000));
        this.b.setOpenId(string3);
        b();
        e();
        this.m = System.currentTimeMillis();
        a("10653", 0);
    }

    public void onBackPressed() {
        setResult(0);
        d();
    }

    protected void onDestroy() {
        super.onDestroy();
        this.e.setImageBitmap(null);
        if (this.s != null && !this.s.isRecycled()) {
            this.s.recycle();
        }
    }

    private void c() {
        float width = (float) this.q.width();
        Matrix imageMatrix = this.e.getImageMatrix();
        float[] fArr = new float[9];
        imageMatrix.getValues(fArr);
        float f = fArr[2];
        float f2 = fArr[5];
        float f3 = fArr[0];
        float f4 = 640.0f / width;
        int i = (int) ((((float) this.q.left) - f) / f3);
        if (i < 0) {
            i = 0;
        }
        int i2 = (int) ((((float) this.q.top) - f2) / f3);
        if (i2 < 0) {
            i2 = 0;
        }
        Matrix matrix = new Matrix();
        matrix.set(imageMatrix);
        matrix.postScale(f4, f4);
        int i3 = (int) (650.0f / f3);
        try {
            Bitmap createBitmap = Bitmap.createBitmap(this.s, i, i2, Math.min(this.s.getWidth() - i, i3), Math.min(this.s.getHeight() - i2, i3), matrix, true);
            Bitmap createBitmap2 = Bitmap.createBitmap(createBitmap, 0, 0, 640, 640);
            createBitmap.recycle();
            a(createBitmap2);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            String str = Constants.MSG_IMAGE_ERROR;
            a(str, 1);
            a(AppbarJsBridge.AUTHORIZE_FAIL, null, str, e.getMessage());
            d();
        }
    }

    private void a(Bitmap bitmap) {
        new QQAvatarImp(this.b).setAvator(bitmap, this.v);
    }

    private void a(String str, int i) {
        this.d.post(new AnonymousClass_4(str, i));
    }

    private void b(String str, int i) {
        Toast makeText = Toast.makeText(this, str, 1);
        LinearLayout linearLayout = (LinearLayout) makeText.getView();
        ((TextView) linearLayout.getChildAt(0)).setPadding(XZBDevice.Wait, 0, 0, 0);
        View imageView = new ImageView(this);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(a.a(this, 16.0f), a.a(this, 16.0f)));
        if (i == 0) {
            imageView.setImageDrawable(b("com.tencent.plus.ic_success.png"));
        } else {
            imageView.setImageDrawable(b("com.tencent.plus.ic_error.png"));
        }
        linearLayout.addView(imageView, 0);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(R.styleable.Toolbar_maxButtonHeight);
        makeText.setView(linearLayout);
        makeText.setGravity(R.styleable.Toolbar_maxButtonHeight, 0, 0);
        makeText.show();
    }

    private void a(int i, String str, String str2, String str3) {
        Intent intent = new Intent();
        intent.putExtra(Constants.KEY_ERROR_CODE, i);
        intent.putExtra(Constants.KEY_ERROR_MSG, str2);
        intent.putExtra(Constants.KEY_ERROR_DETAIL, str3);
        intent.putExtra(Constants.KEY_RESPONSE, str);
        setResult(-1, intent);
    }

    private void d() {
        finish();
        if (this.n != 0) {
            overridePendingTransition(0, this.n);
        }
    }

    private void e() {
        this.k++;
        new UserInfo(this, this.b).getUserInfo(this.w);
    }

    private void c(String str) {
        CharSequence d = d(str);
        if (!com.umeng.a.d.equals(d)) {
            this.i.setText(d);
            this.i.setVisibility(0);
        }
    }

    private String d(String str) {
        return str.replaceAll("&gt;", ">").replaceAll("&lt;", "<").replaceAll("&quot;", h.f).replaceAll("&#39;", "'").replaceAll("&amp;", com.alipay.sdk.sys.a.b);
    }

    public void a(String str, long j) {
        Util.reportBernoulli(this, str, j, this.b.getAppId());
    }
}
