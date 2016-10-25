package com.xunlei.common.member.act;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.tencent.tauth.Tencent;
import com.umeng.a;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.member.a.j;
import com.xunlei.common.member.a.m;
import com.xunlei.common.member.b.h;
import com.xunlei.common.member.c.p;
import org.json.JSONObject;

public class XLQQLoginActivity extends Activity {
    private String QQ_SCOPE;
    private String TAG;
    private String mQQAppId;
    private j mQQAuthListener;
    private int mTaskId;
    private Tencent mTencentInstance;

    public XLQQLoginActivity() {
        this.TAG = XLQQLoginActivity.class.getSimpleName();
        this.mTencentInstance = null;
        this.mQQAuthListener = null;
        this.QQ_SCOPE = "all";
        this.mQQAppId = a.d;
        this.mTaskId = 0;
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mTaskId = getIntent().getIntExtra("qq_task_id", 0);
        this.mQQAppId = getIntent().getStringExtra("qq_app_id");
        if (this.mTaskId == 0 || TextUtils.isEmpty(this.mQQAppId)) {
            finish();
            return;
        }
        this.mTencentInstance = Tencent.createInstance(this.mQQAppId, this);
        this.mQQAuthListener = new j(this);
        this.mTencentInstance.login((Activity) this, this.QQ_SCOPE, this.mQQAuthListener);
        XLLog.v(this.TAG, new StringBuilder("start qq login appid = ").append(this.mQQAppId).toString());
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        XLLog.v(this.TAG, new StringBuilder("-->onActivityResult ").append(i).append(" resultCode=").append(i2).toString());
        if (i == 11101 || i == 10102) {
            Tencent.onActivityResultData(i, i2, intent, this.mQQAuthListener);
        }
        super.onActivityResult(i, i2, intent);
    }

    protected void notifyToTask(int i, h hVar) {
        p a = m.a().a(this.mTaskId);
        if (a != null) {
            a.a(i, hVar);
        }
        finish();
        XLLog.v(this.TAG, "finish XLQQLoginActivity");
    }

    public void acceptQQAuthResult(int i, JSONObject jSONObject) {
        h hVar = new h(jSONObject);
        notifyToTask(i, hVar);
        if (hVar.a()) {
            this.mTencentInstance.setOpenId(hVar.a);
            this.mTencentInstance.setAccessToken(hVar.a, hVar.c);
        }
    }
}
