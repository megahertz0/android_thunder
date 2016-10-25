package com.xunlei.tdlive.usercenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;
import com.xunlei.tdlive.protocol.XLLiveUpdateUserInfoRequest;
import com.xunlei.tdlive.user.f;
import com.xunlei.xiazaibao.BuildConfig;

public class NickModifyActivity extends BaseActivity implements OnClickListener, JsonCallBack {
    private static final String a;
    private EditText b;
    private String c;

    static {
        a = NickModifyActivity.class.getSimpleName();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.xllive_activity_modify_nick);
        this.b = (EditText) findViewById(R.id.evInput);
        this.b.requestFocus();
        findViewById(R.id.ivClear).setOnClickListener(this);
        a(R.string.modify_nickname, R.layout.xllive_save_button);
        Intent intent = getIntent();
        if (intent != null) {
            Object stringExtra = intent.getStringExtra("EXTRA_NICKNAME");
            if (!TextUtils.isEmpty(stringExtra)) {
                this.c = stringExtra;
                this.b.setText(stringExtra);
                try {
                    this.b.setSelection(stringExtra.length());
                } catch (Exception e) {
                }
            }
        }
    }

    public static Intent a(Context context, String str) {
        Intent intent = new Intent(context, NickModifyActivity.class);
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("EXTRA_NICKNAME", str);
        }
        return intent;
    }

    private void a(int i, int i2) {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.titlebar);
        relativeLayout.findViewById(R.id.ivBack).setOnClickListener(this);
        ((TextView) findViewById(R.id.tvTitle)).setText(i);
        if (i2 != 0) {
            getLayoutInflater().inflate(i2, relativeLayout);
            TextView textView = (TextView) relativeLayout.findViewById(R.id.tvBtn);
            textView.setOnClickListener(this);
            textView.setEnabled(false);
            this.b.addTextChangedListener(new i(this, textView));
        }
    }

    private boolean a(String str) {
        return this.c == null || !this.c.equals(str);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ivClear) {
            this.b.setText(BuildConfig.VERSION_NAME);
        } else if (id == R.id.ivBack) {
            finish();
        } else if (id == R.id.tvBtn) {
            String toString = this.b.getText().toString();
            if (!TextUtils.isEmpty(toString) && a(toString)) {
                new XLLiveUpdateUserInfoRequest(f.a(this).k(), f.a(this).l()).nickName(toString).send((JsonCallBack) this);
            }
        }
    }

    public void onResponse(int i, String str, JsonWrapper jsonWrapper) {
        if (i != 0 || jsonWrapper == null) {
            b(str);
            new StringBuilder("change nick return ").append(i).append(", msg = ").append(str);
            return;
        }
        try {
            String string = jsonWrapper.getObject(SocializeProtocolConstants.PROTOCOL_KEY_DATA).getString("nickname", BuildConfig.VERSION_NAME);
            f.a().a(string, null);
            Intent intent = new Intent();
            intent.putExtra("UserInfoEditActivity.EXTRA_INFO_STRING", string);
            setResult(-1, intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();
    }

    private void b(String str) {
        showToast(str, 0, R.layout.xllive_toast_uiserinfo_edit, R.id.text, com.xunlei.xllib.R.styleable.Toolbar_maxButtonHeight);
    }
}
