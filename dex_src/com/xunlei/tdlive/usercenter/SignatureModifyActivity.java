package com.xunlei.tdlive.usercenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.xunlei.tdlive.util.ac;
import com.xunlei.xiazaibao.BuildConfig;

public class SignatureModifyActivity extends BaseActivity implements TextWatcher, OnClickListener, JsonCallBack {
    private static final String a;
    private EditText b;
    private TextView c;
    private TextView d;
    private String e;

    static {
        a = SignatureModifyActivity.class.getSimpleName();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.xllive_activity_modify_signature);
        this.c = (TextView) findViewById(R.id.leftNum);
        this.b = (EditText) findViewById(R.id.evInput);
        this.b.requestFocus();
        a(R.string.modify_signature, R.layout.xllive_save_button);
        this.b.addTextChangedListener(this);
        Intent intent = getIntent();
        if (intent != null) {
            Object stringExtra = intent.getStringExtra("EXTRA_SIGNATURE");
            if (!TextUtils.isEmpty(stringExtra)) {
                this.e = stringExtra;
                this.b.setText(stringExtra);
                this.b.setSelection(stringExtra.length());
            }
        }
    }

    public static Intent a(Context context, String str) {
        Intent intent = new Intent(context, SignatureModifyActivity.class);
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("EXTRA_SIGNATURE", str);
        }
        return intent;
    }

    private void a(int i, int i2) {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.titlebar);
        relativeLayout.findViewById(R.id.ivBack).setOnClickListener(this);
        ((TextView) findViewById(R.id.tvTitle)).setText(i);
        if (i2 != 0) {
            getLayoutInflater().inflate(i2, relativeLayout);
            this.d = (TextView) relativeLayout.findViewById(R.id.tvBtn);
            this.d.setOnClickListener(this);
            this.d.setEnabled(false);
        }
    }

    private boolean a(String str) {
        return this.e == null || !this.e.equals(str);
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
                new XLLiveUpdateUserInfoRequest(f.a(this).k(), f.a(this).l()).sign(toString).send((JsonCallBack) this);
            }
        }
    }

    private void b(String str) {
        showToast(str, 0, R.layout.xllive_toast_uiserinfo_edit, R.id.text, com.xunlei.xllib.R.styleable.Toolbar_maxButtonHeight);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
        this.c.setText(String.valueOf(30 - ac.b(editable.toString())));
        TextView textView = this.d;
        boolean z = editable.length() > 0 && a(editable.toString());
        textView.setEnabled(z);
    }

    public void onResponse(int i, String str, JsonWrapper jsonWrapper) {
        if (i != 0 || jsonWrapper == null) {
            b(str);
            new StringBuilder("change sign return ").append(i).append(", msg = ").append(str);
            return;
        }
        try {
            String string = jsonWrapper.getObject(SocializeProtocolConstants.PROTOCOL_KEY_DATA).getString("sign", BuildConfig.VERSION_NAME);
            f.a().a(null, string);
            Intent intent = new Intent();
            intent.putExtra("UserInfoEditActivity.EXTRA_INFO_STRING", string);
            setResult(-1, intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();
    }
}
