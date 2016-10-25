package com.xunlei.tdlive;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.umeng.common.inter.ITagManager;
import com.xunlei.common.register.XLRegisterListener;
import com.xunlei.common.register.XLRegisterUtil;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.util.ac;
import com.xunlei.tdlive.util.q;
import com.xunlei.tdlive.util.q.a;
import com.xunlei.tdlive.util.r;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import org.android.spdy.SpdyProtocol;

public class RegisterActivity extends BaseActivity implements OnClickListener {
    private int a;
    private r b;
    private String c;
    private String d;
    private EditText e;
    private EditText f;
    private EditText g;
    private EditText h;
    private ImageView i;
    private View j;
    private View k;
    private TextView l;
    private TextView m;
    private TextView n;
    private ImageView o;
    private int p;
    private XLRegisterListener q;

    public RegisterActivity() {
        this.a = 0;
        this.c = BuildConfig.VERSION_NAME;
        this.d = BuildConfig.VERSION_NAME;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.p = 0;
        this.q = new dw(this);
    }

    static /* synthetic */ int b(RegisterActivity registerActivity) {
        int i = registerActivity.a;
        registerActivity.a = i + 1;
        return i;
    }

    private void a(boolean z, int i) {
        a aVar = new a();
        aVar.a("errorcode", i);
        q.a("zb_resign_result", z ? "success" : ITagManager.FAIL, null, aVar.a());
    }

    private void f(String str) {
        this.p = XLRegisterUtil.getInstance().getVerifyCodeByType(str);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.xllive_reg_activity);
        this.j = findViewById(R.id.lvImageVerify);
        this.h = (EditText) this.j.findViewById(R.id.evImageVerify);
        this.i = (ImageView) this.j.findViewById(R.id.ivImageVerify);
        this.k = findViewById(R.id.lineBelowImageVerify);
        this.o = (ImageView) findViewById(R.id.ivShowPassword);
        this.o.setOnClickListener(this);
        this.e = (EditText) findViewById(R.id.evPhoneNum);
        this.f = (EditText) findViewById(R.id.evPassword);
        this.g = (EditText) findViewById(R.id.evMsgVerify);
        this.m = (TextView) findViewById(R.id.tvGetMsgVerify);
        this.l = (TextView) findViewById(R.id.btnRegister);
        this.n = (TextView) findViewById(R.id.tvError);
        this.m.setOnClickListener(this);
        this.l.setOnClickListener(this);
        ((TextView) findViewById(R.id.tvUserProtocol)).setOnClickListener(this);
        findViewById(R.id.btnClearPhoneNum).setOnClickListener(this);
        findViewById(R.id.ivShowPassword).setOnClickListener(this);
        this.i.setOnClickListener(this);
        findViewById(R.id.ivBack).setOnClickListener(this);
        ((TextView) findViewById(R.id.tvTitle)).setText(R.string.register_xl_account);
        XLRegisterUtil.getInstance().init(getApplicationContext(), R.styleable.AppCompatTheme_colorControlNormal, "xllive", ac.d(), ac.h(), "e91aa83000867e97cbe6dd7f9944b5cf");
        XLRegisterUtil.getInstance().attachListener(this.q);
        a(false);
    }

    public void a(boolean z) {
        int i = 0;
        if (!z) {
            i = SpdyProtocol.PUBKEY_SEQ_ADASH;
        }
        this.j.setVisibility(i);
        this.k.setVisibility(i);
    }

    public void a(String str, String str2, byte[] bArr) {
        this.c = str;
        this.d = str2;
        j();
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        if (decodeByteArray != null) {
            a(true);
            this.i.setImageBitmap(decodeByteArray);
        }
    }

    public String a() {
        return this.d;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.h.getText().toString();
    }

    public String d() {
        return this.e.getText().toString();
    }

    public String e() {
        return this.f.getText().toString();
    }

    public String f() {
        return this.g.getText().toString();
    }

    public void a(String str) {
        this.n.setVisibility(0);
        this.n.setText(str);
    }

    public void a(int i) {
        this.n.setVisibility(0);
        this.n.setText(i);
    }

    public void b(String str) {
        this.g.setText(str);
    }

    public void g() {
        this.a = 1;
        this.b = new r(1000, new dz(this));
        this.b.b();
        i();
    }

    private void j() {
        this.a = 0;
        i();
    }

    public void h() {
        this.a = 0;
        if (this.b != null) {
            this.b.c();
            this.b = null;
        }
    }

    public void i() {
        if (this.a == 0) {
            this.m.setEnabled(true);
            this.m.setText("\u83b7\u53d6\u9a8c\u8bc1\u7801");
            return;
        }
        this.m.setEnabled(false);
        this.m.setText(String.valueOf(60 - this.a) + "\u79d2\u540e\u91cd\u8bd5");
    }

    protected void onDestroy() {
        super.onDestroy();
        h();
        XLRegisterUtil.getInstance().dettachListener(this.q);
        XLRegisterUtil.getInstance().uninit();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnRegister) {
            g("resign");
            if (!d(d())) {
                a(R.string.error_invalid_phonenum);
            } else if (!e(f())) {
                a(R.string.msg_input_hint);
            } else if (TextUtils.isEmpty(e())) {
                a(R.string.passwd_cannot_empty);
            } else {
                XLRegisterUtil.getInstance().phoneFastRegister(d(), f(), e(), "xllive");
            }
        } else if (id == R.id.tvGetMsgVerify) {
            g("auth");
            if (!d(d())) {
                a(R.string.msg_input_hint);
            } else if (XLRegisterUtil.getInstance().checkIsNeedVerifyCode() != 1 || c(c())) {
                XLRegisterUtil.getInstance().sendPhoneMessage(d(), 1, this, b(), c(), a());
            } else {
                a(R.string.error_need_image_verify_code);
            }
        } else if (id == R.id.ivBack) {
            g("back");
            finish();
        } else if (id == R.id.tvUserProtocol) {
            WebBrowserActivity.start(this, "http://h5.live.xunlei.com/android/tos.html", "\u7528\u6237\u534f\u8bae", false);
        } else if (id == R.id.ivShowPassword) {
            if (view.isSelected()) {
                view.setSelected(false);
                this.f.setTransformationMethod(PasswordTransformationMethod.getInstance());
                this.f.setSelection(this.f.length());
                return;
            }
            view.setSelected(true);
            this.f.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            this.f.setSelection(this.f.length());
        } else if (id == R.id.btnClearPhoneNum) {
            this.e.setText(null);
        } else if (id == R.id.ivImageVerify) {
            f(this.d);
        }
    }

    private void g(String str) {
        q.a("zb_resign_page_click", str, null);
    }

    public boolean c(String str) {
        return !TextUtils.isEmpty(str);
    }

    public boolean d(String str) {
        return str == null ? false : str.matches("1[3|5|7|8|][0-9]{9}");
    }

    public boolean e(String str) {
        return str == null ? false : str.matches("[0-9]+");
    }
}
