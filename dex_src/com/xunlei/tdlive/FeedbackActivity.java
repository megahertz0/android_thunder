package com.xunlei.tdlive;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.base.c;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveFeedbackRequest;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;
import com.xunlei.tdlive.user.f;

public class FeedbackActivity extends BaseActivity implements OnClickListener, JsonCallBack {
    EditText a;
    EditText b;
    EditText c;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.xllive_activity_feedback);
        setTitle("\u610f\u89c1\u53cd\u9988");
        setLeftVisible(true);
        setLeftClickListener(this);
        setLeftDrawable(getResources().getDrawable(R.drawable.xllive_ic_back));
        findViewById(R.id.commit).setOnClickListener(this);
        this.a = (EditText) findViewById(R.id.qq);
        this.b = (EditText) findViewById(R.id.phone);
        this.c = (EditText) findViewById(R.id.content);
    }

    public void onClick(View view) {
        if (view == this.mTitleBarLeft) {
            finish();
        } else if (R.id.commit != view.getId()) {
        } else {
            if (this.c.getText().toString().length() <= 0) {
                showToast("\u8bf7\u586b\u4e0a\u60a8\u8981\u53cd\u9988\u7684\u95ee\u9898", 0, R.layout.xllive_common_toast, R.id.text, R.styleable.Toolbar_titleMarginBottom);
                return;
            }
            String trim = this.b.getText().toString().trim();
            if (trim.length() <= 0 || a(trim)) {
                new XLLiveFeedbackRequest(f.a((Context) this).k(), f.a((Context) this).l(), this.a.getText().toString().trim(), trim, this.c.getText().toString()).send(this);
            } else {
                showToast("\u60a8\u8f93\u5165\u7684\u624b\u673a\u53f7\u4f4d\u6570\u4e0d\u6b63\u786e", 0, R.layout.xllive_common_toast, R.id.text, R.styleable.Toolbar_titleMarginBottom);
            }
        }
    }

    private boolean a(String str) {
        if (str.length() != 11) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public void onResponse(int i, String str, JsonWrapper jsonWrapper) {
        if (!isFinishing() && !isDestroyed()) {
            if (i == 0) {
                new c(this, "\u63d0\u793a", "\u8c22\u8c22\u4eb2\uff0c\u60a8\u7684\u53cd\u9988\u5df2\u7ecf\u63d0\u4ea4\u6210\u529f!", "\u786e\u5b9a", new String[0]).b(new f(this));
            } else {
                new c(this, "\u63d0\u793a", "\u63d0\u4ea4\u5931\u8d25, \u8bf7\u91cd\u8bd5!", "\u597d\u7684", new String[0]).b(new g(this));
            }
        }
    }
}
