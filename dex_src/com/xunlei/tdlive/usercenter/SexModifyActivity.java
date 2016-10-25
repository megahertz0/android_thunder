package com.xunlei.tdlive.usercenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.base.c;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;
import com.xunlei.tdlive.protocol.XLLiveUpdateUserInfoRequest;
import com.xunlei.tdlive.user.f;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

public class SexModifyActivity extends BaseActivity implements OnClickListener, JsonCallBack {
    private int a;
    private ImageView b;
    private ImageView c;
    private TextView d;

    public SexModifyActivity() {
        this.a = 0;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent != null) {
            this.a = intent.getIntExtra("EXTRA_SEX", 0);
        }
        setContentView(R.layout.xllive_activity_modify_sex);
        findViewById(R.id.lvMan).setOnClickListener(this);
        findViewById(R.id.lvWoman).setOnClickListener(this);
        this.b = (ImageView) findViewById(R.id.ivManSel);
        this.c = (ImageView) findViewById(R.id.ivWomanSel);
        a(R.string.modify_sex, R.layout.xllive_save_button);
        a(this.a);
    }

    public static Intent a(Context context, int i) {
        Intent intent = new Intent(context, SexModifyActivity.class);
        intent.putExtra("EXTRA_SEX", i);
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
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ivClear) {
            return;
        }
        if (id == R.id.ivBack) {
            finish();
        } else if (id == R.id.tvBtn) {
            String[] strArr = new String[]{"\u786e\u5b9a"};
            new c(this, "\u63d0\u793a", String.format("\u60a8\u9009\u62e9\u7684\u6027\u522b\u662f\uff1a%s\n\u53ea\u80fd\u7f16\u8f91\u4e00\u6b21\u6027\u522b\uff0c\u786e\u5b9a\u4fee\u6539\u5417\uff1f", new Object[]{a.a(this.a)}), "\u53d6\u6d88", strArr).b(new j(this));
        } else if (id == R.id.lvMan) {
            a(1);
        } else if (id == R.id.lvWoman) {
            a((int) SimpleLog.LOG_LEVEL_DEBUG);
        }
    }

    private void a(int i) {
        if (i != this.a) {
            this.a = i;
            this.d.setEnabled(true);
            if (this.a == 1) {
                this.b.setVisibility(0);
                this.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                return;
            } else if (this.a == 2) {
                this.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.c.setVisibility(0);
                return;
            } else {
                this.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                return;
            }
        }
        this.d.setEnabled(false);
    }

    private void a() {
        new XLLiveUpdateUserInfoRequest(f.a().k(), f.a().l()).sex(this.a).send((JsonCallBack) this);
    }

    public void onResponse(int i, String str, JsonWrapper jsonWrapper) {
        if (i == 0) {
            Intent intent = new Intent();
            intent.putExtra("UserInfoEditActivity.EXTRA_INFO_STRING", a.a(this.a));
            setResult(-1, intent);
            f.a(this).a(this.a);
            finish();
            return;
        }
        showToast("\u4fdd\u5b58\u5931\u8d25\uff0c\u8bf7\u91cd\u65b0\u5c1d\u8bd5", 0, R.layout.xllive_toast_uiserinfo_edit, R.id.text, com.xunlei.xllib.R.styleable.Toolbar_maxButtonHeight);
    }
}
