package com.xunlei.tdlive.usercenter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;
import com.xunlei.tdlive.protocol.XLLiveUpdateAvatarRequest.AvatarResp;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.a;
import java.io.File;

public class UserInfoEditActivity extends BaseActivity implements OnClickListener, ObjectCallBack {
    public static int a;
    public static int b;
    public static int c;
    public static int d;
    private static final String e;
    private TextView f;
    private TextView g;
    private TextView h;
    private ImageView i;
    private boolean j;
    private File k;
    private int l;
    private boolean m;
    private Dialog n;

    public UserInfoEditActivity() {
        this.j = false;
        this.m = true;
    }

    static {
        e = UserInfoEditActivity.class.getSimpleName();
        a = 101;
        b = 102;
        c = 103;
        d = 104;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.xllive_activity_userinfo_edit);
        findViewById(R.id.lvAvatar).setOnClickListener(this);
        findViewById(R.id.lvNickname).setOnClickListener(this);
        findViewById(R.id.lvSignature).setOnClickListener(this);
        findViewById(R.id.lvSex).setOnClickListener(this);
        this.i = (ImageView) findViewById(R.id.ivAvatar);
        this.f = (TextView) findViewById(R.id.tvNickName);
        this.g = (TextView) findViewById(R.id.tvSignature);
        this.h = (TextView) findViewById(R.id.tvSex);
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra("UserInfoEditActivity.EXTRA_INFO_AVATAR");
            CharSequence stringExtra2 = intent.getStringExtra("UserInfoEditActivity.EXTRA_INFO_NICKNAME");
            CharSequence stringExtra3 = intent.getStringExtra("UserInfoEditActivity.EXTRA_INFO_SIGN");
            this.l = intent.getIntExtra("UserInfoEditActivity.EXTRA_INFO_SEX", 0);
            this.m = this.l <= 0;
            a.a(this).a(this.i, stringExtra, a.a(this, R.drawable.xllive_avatar_default));
            this.f.setText(stringExtra2);
            this.g.setText(stringExtra3);
            this.h.setText(a.a(this.l));
        }
    }

    public static Intent a(Context context, String str, String str2, String str3, int i) {
        Intent intent = new Intent(context, UserInfoEditActivity.class);
        intent.putExtra("UserInfoEditActivity.EXTRA_INFO_AVATAR", str);
        intent.putExtra("UserInfoEditActivity.EXTRA_INFO_NICKNAME", str2);
        intent.putExtra("UserInfoEditActivity.EXTRA_INFO_SIGN", str3);
        intent.putExtra("UserInfoEditActivity.EXTRA_INFO_SEX", i);
        return intent;
    }

    protected void onResume() {
        super.onResume();
        setLeftVisible(true);
        setLeftClickListener(this);
        Resources resources = getResources();
        setLeftDrawable(resources.getDrawable(R.drawable.xllive_ic_back));
        setTitle(resources.getString(R.string.edit_info));
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.left) {
            onBackPressed();
        } else if (id == R.id.lvAvatar) {
            q.a((Activity) this, d);
        } else if (id == R.id.lvNickname) {
            startActivityForResult(NickModifyActivity.a((Context) this, this.f.getText().toString()), a);
        } else if (id == R.id.lvSignature) {
            startActivityForResult(SignatureModifyActivity.a((Context) this, this.g.getText().toString()), b);
        } else if (id != R.id.lvSex) {
        } else {
            if (this.m) {
                startActivityForResult(SexModifyActivity.a((Context) this, this.l), c);
            } else {
                showToast("\u60a8\u5df2\u7ecf\u8bbe\u7f6e\u8fc7\u4e00\u6b21\u6027\u522b\uff0c\u65e0\u6cd5\u518d\u8bbe\u7f6e", 0, R.layout.xllive_toast_uiserinfo_edit, R.id.text, com.xunlei.xllib.R.styleable.Toolbar_maxButtonHeight);
            }
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            this.j = true;
            CharSequence stringExtra = intent.getStringExtra("UserInfoEditActivity.EXTRA_INFO_STRING");
            if (i == a) {
                this.f.setText(stringExtra);
            } else if (i == b) {
                this.g.setText(stringExtra);
            } else if (i == c) {
                this.m = false;
                this.h.setText(stringExtra);
            } else if (i == d) {
                this.k = q.a(i2, intent, (ObjectCallBack) this);
                if (this.k == null) {
                    XLog.d(e, "no file selected");
                    return;
                }
                this.n = ah.a(this, "\u4e0a\u4f20\u4e2d...", true);
                this.n.show();
            }
        }
    }

    public void onBackPressed() {
        setResult(this.j ? -1 : 0);
        finish();
    }

    private void a(String str) {
        showToast(str, 0, R.layout.xllive_toast_uiserinfo_edit, R.id.text, com.xunlei.xllib.R.styleable.Toolbar_maxButtonHeight);
    }

    public void onResponse(int i, String str, Object obj) {
        if (i == 0 && (obj instanceof AvatarResp)) {
            AvatarResp avatarResp = (AvatarResp) obj;
            if (!(avatarResp.data == null || TextUtils.isEmpty(avatarResp.data.avatar))) {
                f.a().a(avatarResp.data.avatar);
                a.a(this).a(this.i, this.k.getPath(), a.b(this));
            }
        } else {
            a(getResources().getString(R.string.change_avatar_failed));
        }
        if (this.n != null) {
            this.n.dismiss();
            this.n = null;
        }
    }
}
