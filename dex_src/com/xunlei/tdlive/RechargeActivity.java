package com.xunlei.tdlive;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.taobao.accs.common.Constants;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.PlatformConfig.Alipay;
import com.xunlei.common.pay.XLContractResp;
import com.xunlei.common.pay.XLOnPayListener;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.protocol.BannerInfo;
import com.xunlei.tdlive.protocol.GetPayPageBannerInfoRequest;
import com.xunlei.tdlive.protocol.XLLiveGetProductPayInfoListRequest;
import com.xunlei.tdlive.protocol.XLLiveGetProductPayInfoListRequest.PayInfo;
import com.xunlei.tdlive.protocol.XLLiveGetProductPayInfoListRequest.PayList;
import com.xunlei.tdlive.protocol.XLLiveGetUserInfoRequest;
import com.xunlei.tdlive.protocol.XLLiveGetUserInfoRequest.LType;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;
import com.xunlei.tdlive.user.e;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.ac;
import com.xunlei.tdlive.util.d;
import com.xunlei.tdlive.util.q;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.List;
import org.android.spdy.SpdyAgent;

public class RechargeActivity extends BaseActivity implements OnClickListener, XLOnPayListener, ObjectCallBack {
    private static final String a;
    private ListView b;
    private b c;
    private a d;
    private View e;
    private View f;
    private ImageView g;
    private View h;
    private TextView i;
    private PayInfo j;
    private int k;
    private long l;

    class a implements OnClickListener {
        int a;

        a() {
            this.a = R.id.wxpay;
        }

        public boolean a() {
            return this.a == R.id.wxpay;
        }

        public boolean b() {
            return this.a == R.id.alipay;
        }

        public void onClick(View view) {
            this.a = view.getId();
            if (a()) {
                RechargeActivity.this.f.setSelected(false);
                RechargeActivity.this.e.setSelected(true);
            } else if (b()) {
                RechargeActivity.this.f.setSelected(true);
                RechargeActivity.this.e.setSelected(false);
            }
        }
    }

    private class b extends BaseAdapter {
        private List<PayInfo> b;

        public void a(PayList payList) {
            this.b = payList.data;
            notifyDataSetChanged();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            c cVar;
            if (view == null || view.getTag() == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.xllive_recharge_list_item, viewGroup, false);
                c cVar2 = new c();
                view.setTag(cVar2);
                RechargeActivity.this = (TextView) view.findViewById(R.id.count);
                cVar2.b = (TextView) view.findViewById(R.id.price);
                cVar2.c = (TextView) view.findViewById(R.id.extra);
                cVar2.d = view.findViewById(R.id.line);
                cVar2.b.setOnClickListener(RechargeActivity.this);
                cVar = cVar2;
            } else {
                cVar = (c) view.getTag();
            }
            cVar.b.setTag(Integer.valueOf(i));
            a(i, cVar);
            if (i == getCount() - 1) {
                cVar.d.setVisibility(XZBDevice.Wait);
            } else {
                cVar.d.setVisibility(0);
            }
            return view;
        }

        public int getCount() {
            return this.b == null ? 0 : this.b.size();
        }

        public Object getItem(int i) {
            return this.b.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        private void a(int i, c cVar) {
            PayInfo payInfo = (PayInfo) this.b.get(i);
            RechargeActivity.this.setText(Integer.toString(payInfo.coin + payInfo.add));
            cVar.b.setText(String.format("\uffe5%d", new Object[]{Integer.valueOf(payInfo.money)}));
            if (!TextUtils.isEmpty(payInfo.desc)) {
                cVar.c.setVisibility(0);
                cVar.c.setText(payInfo.desc);
            } else if (payInfo.add > 0) {
                cVar.c.setVisibility(0);
                cVar.c.setText(RechargeActivity.this.getResources().getString(R.string.send_n_coins, new Object[]{Integer.valueOf(payInfo.add)}));
            } else {
                cVar.c.setVisibility(XZBDevice.Wait);
            }
        }
    }

    private static final class c {
        TextView a;
        TextView b;
        TextView c;
        View d;

        private c() {
        }
    }

    public RechargeActivity() {
        this.d = new a();
        this.k = 0;
    }

    static {
        a = RechargeActivity.class.getSimpleName();
    }

    protected void onCreate(Bundle bundle) {
        int i = 0;
        super.onCreate(bundle);
        setContentView(R.layout.xllive_activity_recharge);
        setTitle("\u5145\u503c\u4e2d\u5fc3");
        setLeftVisible(true);
        setLeftClickListener(this);
        setLeftDrawable(getResources().getDrawable(R.drawable.xllive_ic_back));
        this.e = findViewById(R.id.wxpay);
        this.e.setSelected(true);
        this.e.setOnClickListener(this.d);
        this.f = findViewById(R.id.alipay);
        this.f.setOnClickListener(this.d);
        this.b = (ListView) findViewById(R.id.goods_list);
        ListView listView = this.b;
        ListAdapter bVar = new b();
        this.c = bVar;
        listView.setAdapter(bVar);
        this.i = (TextView) findViewById(R.id.tvCoinsLeft);
        this.i.setText(R.string.querying);
        this.h = findViewById(R.id.topStub);
        this.g = (ImageView) findViewById(R.id.rechargeWebEntry);
        this.g.setVisibility(XZBDevice.Wait);
        this.g.setLayoutParams(new LayoutParams(-1, d.a(this).x / 5));
        c();
        e.a().a(this, !ac.j(), true);
        e.a().a(this);
        Intent intent = getIntent();
        if (intent != null) {
            i = intent.getIntExtra("RechargeActivity.EXTRA_SOURCE_TAG", 0);
        }
        d(i);
    }

    protected void onDestroy() {
        super.onDestroy();
        e.a().b(this);
        e.a().b();
    }

    protected void onResume() {
        super.onResume();
        hideLoadingDialog();
        a();
        b();
    }

    public static void a(Context context, int i) {
        Intent intent = new Intent(context, RechargeActivity.class);
        intent.putExtra("RechargeActivity.EXTRA_SOURCE_TAG", i);
        context.startActivity(intent);
    }

    private void a() {
        new XLLiveGetProductPayInfoListRequest(f.a().k(), f.a().l()).send(this);
    }

    private void b() {
        String k = f.a().k();
        new XLLiveGetUserInfoRequest(k, f.a().l(), k, LType.XL).send(new dt(this));
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.left) {
            finish();
        } else if (id == R.id.price) {
            PayInfo payInfo = (PayInfo) this.c.getItem(((Integer) view.getTag()).intValue());
            this.j = new PayInfo();
            this.j.money = payInfo.money;
            this.j.coin = payInfo.coin;
            this.j.add = payInfo.add;
            c(payInfo.coin);
        }
    }

    private void a(int i) {
        XLog.d(a, "wxPay");
        showLoadingDialog("\u52a0\u8f7d\u4e2d...", false);
        e.a().a(this, Integer.parseInt(f.a().k()), 0, Constants.SDK_VERSION_CODE, ac.j() ? "android_live" : "android_live_sdk", i, "sence-wx-pay");
    }

    private void b(int i) {
        XLog.d(a, Alipay.Name);
        e.a().a(this, Integer.parseInt(f.a().k()), 1, Constants.SDK_VERSION_CODE, ac.j() ? "android_live" : "android_live_sdk", i, "sence-ali-pay");
    }

    private void c(int i) {
        if (!ac.a()) {
            showToast(R.string.recharge_failed_for_network, 0, R.layout.xllive_common_toast, R.id.text, R.styleable.Toolbar_titleMarginBottom);
        } else if (f.a().b()) {
            XLog.d(a, new StringBuilder("recharge coins: ").append(i).toString());
            if (this.d.a()) {
                this.k = 1;
                a(i);
            } else if (this.d.b()) {
                this.k = 2;
                b(i);
            }
        } else {
            showToast(R.string.recharge_failed_for_not_login, 0, R.layout.xllive_common_toast, R.id.text, R.styleable.Toolbar_titleMarginBottom);
        }
    }

    private void a(int i, String str) {
        if (this.j != null) {
            a(com.xunlei.tdlive.user.d.a(i), this.j.money, this.j.coin + this.j.add, i);
            if (com.xunlei.tdlive.user.d.a(i)) {
                String format;
                b();
                if (this.j.add > 0) {
                    format = String.format("\u60a8\u5df2\u6210\u529f\u5145\u503c%d\u91d1\u5e01\uff0c\n\u8d60\u9001%d\u91d1\u5e01", new Object[]{Integer.valueOf(this.j.coin), Integer.valueOf(this.j.add)});
                } else {
                    format = String.format("\u60a8\u5df2\u6210\u529f\u5145\u503c%d\u91d1\u5e01", new Object[]{Integer.valueOf(this.j.coin)});
                }
                new com.xunlei.tdlive.base.c(this, format, null, "\u786e\u5b9a", new String[0]).show();
            } else if (com.xunlei.tdlive.user.d.a(i, this.k)) {
                new com.xunlei.tdlive.base.c(this, getResources().getString(R.string.recharge_canceled), null, "\u786e\u5b9a", new String[0]).show();
            } else if (ac.a()) {
                showToast(str, 0, R.layout.xllive_common_toast, R.id.text, R.styleable.Toolbar_titleMarginBottom);
            } else {
                showToast(R.string.recharge_failed_for_network, 0, R.layout.xllive_common_toast, R.id.text, R.styleable.Toolbar_titleMarginBottom);
            }
        }
    }

    public void onWxPay(int i, String str, Object obj, int i2) {
        XLog.d(a, new StringBuilder("onWxPlay err = ").append(i).append(", errDesc = ").append(str).append(", taskId = ").append(i2).toString());
        if (i == 104) {
            showToast(getResources().getString(R.string.pay_failed_for_not_install, new Object[]{"\u5fae\u4fe1"}), 0, R.layout.xllive_common_toast, R.id.text, R.styleable.Toolbar_titleMarginBottom);
        }
        hideLoadingDialog();
        a(i, str);
    }

    public void onAliPay(int i, String str, Object obj, int i2) {
        XLog.d(a, new StringBuilder("onAliPay err = ").append(i).append(", errDesc = ").append(str).append(", taskId = ").append(i2).toString());
        a(i, str);
    }

    public void onNbPay(int i, String str, Object obj, int i2) {
        XLog.d(a, new StringBuilder("onNbPay err = ").append(i).append(", errDesc = ").append(str).append(", taskId = ").append(i2).toString());
        a(i, str);
    }

    public void onGetPrice(int i, String str, Object obj, int i2, String str2) {
        XLog.d(a, new StringBuilder("onGetPrice err = ").append(i).append(", errDesc = ").append(str).append(", taskId = ").append(i2).toString());
    }

    public void onContractOperate(int i, String str, Object obj, int i2, XLContractResp xLContractResp) {
        XLog.d(a, new StringBuilder("onContractOperate err = ").append(i).append(", errDesc = ").append(str).append(", taskId = ").append(i2).toString());
    }

    public void onResponse(int i, String str, Object obj) {
        if (i != 0) {
            XLog.w(a, new StringBuilder("XLLiveGetProductPayInfoListRequest ret = ").append(i).append(", msg = ").append(str).toString());
        } else if (obj instanceof PayList) {
            this.c.a((PayList) obj);
        }
    }

    private void d(int i) {
        String str;
        int i2 = 1;
        Object obj;
        switch (i) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                str = "centerhome";
                obj = null;
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                str = "gift";
                obj = null;
                break;
            default:
                str = null;
                int i3 = 1;
                break;
        }
        this.l = System.currentTimeMillis();
        com.xunlei.tdlive.util.q.a aVar = new com.xunlei.tdlive.util.q.a();
        aVar.a("payid", this.l);
        String str2 = "if_activity_show";
        if (i3 == 0) {
            i2 = 0;
        }
        aVar.a(str2, i2);
        q.a("pay_page_show", str, null, aVar.a());
    }

    private void a(boolean z, int i, int i2, int i3) {
        String str = z ? MsgConstant.KEY_SUCCESS : MsgConstant.KEY_FAIL;
        String str2 = null;
        if (this.k == 1) {
            str2 = "webchat";
        } else if (this.k == 2) {
            str2 = "zhifubao";
        }
        com.xunlei.tdlive.util.q.a aVar = new com.xunlei.tdlive.util.q.a();
        aVar.a("payid", this.l).a("amt", i).a("gold_num", i2).a("errorcode", i3);
        q.a("pay_result", str, str2, aVar.a());
    }

    private void c() {
        new GetPayPageBannerInfoRequest(f.a().k(), f.a().l()).send(new du(this));
    }

    private void a(BannerInfo bannerInfo) {
        if (bannerInfo == null) {
            this.h.setVisibility(0);
            this.g.setVisibility(XZBDevice.Wait);
            return;
        }
        this.h.setVisibility(XZBDevice.Wait);
        this.g.setVisibility(0);
        com.xunlei.tdlive.util.a.a((Context) this).a(this.g, bannerInfo.image);
        this.g.setOnClickListener(new dv(this, bannerInfo));
    }
}
