package com.xunlei.downloadprovider.member.payment.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.a;
import android.widget.ImageView;
import android.widget.TextView;
import com.taobao.accs.data.Message;
import com.umeng.socialize.PlatformConfig.Alipay;
import com.xunlei.common.pay.XLPayUtil;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.member.payment.a.j;
import com.xunlei.downloadprovider.member.payment.external.PayUtil;
import com.xunlei.downloadprovider.member.payment.external.f;
import com.xunlei.downloadprovider.member.payment.ui.a.c;
import com.xunlei.downloadprovider.member.payment.ui.a.c.b;
import com.xunlei.xiazaibao.R;

public class PayProblemActivity extends BaseActivity implements b {
    private static Handler h;
    private TextView a;
    private ImageView b;
    private RecyclerView c;
    private TextView d;
    private j e;
    private int f;
    private f g;

    public PayProblemActivity() {
        this.g = new aj(this);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968908);
        XLPayUtil.getInstance().attachListener(this.g);
        this.a = (TextView) findViewById(R.id.titlebar_title);
        this.a.setText(getResources().getString(2131231848));
        this.b = (ImageView) findViewById(R.id.titlebar_left);
        this.b.setOnClickListener(new ai(this));
        this.c = (RecyclerView) findViewById(2131756747);
        this.c.setLayoutManager(new LinearLayoutManager(this));
        this.d = (TextView) findViewById(2131756748);
        a cVar = new c(getResources().getStringArray(2131623943), getResources().getStringArray(2131623942), this);
        this.c.setAdapter(cVar);
        cVar.b = this;
        this.e = j.a();
        this.f = getIntent().getIntExtra("extra_vastype", 1);
    }

    public static void a(Context context, int i) {
        Intent intent = new Intent(context, PayProblemActivity.class);
        intent.putExtra("extra_vastype", i);
        context.startActivity(intent);
    }

    protected void onDestroy() {
        super.onDestroy();
        XLPayUtil.getInstance().detachListener(this.g);
    }

    static {
        h = new al();
    }

    public final void a() {
        XLPayUtil.getInstance().userGetXLContractor(Message.FLAG_ERR).userDisContract(PayUtil.b(1, this.f, 0), Alipay.Name);
    }

    static /* synthetic */ void b(PayProblemActivity payProblemActivity) {
        payProblemActivity.d.setVisibility(0);
        h.postDelayed(new ak(payProblemActivity), 2000);
    }
}
