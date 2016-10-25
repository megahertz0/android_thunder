package com.xunlei.downloadprovider.discovery;

import android.app.Activity;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.analytics.b.c;
import com.xunlei.common.accelerator.bean.KnParams;
import com.xunlei.common.accelerator.bean.XLAccelBandInfo;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.h;
import com.xunlei.downloadprovider.a.h.b;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.discovery.kuainiao.KuaiNiaoActivity;
import com.xunlei.downloadprovider.discovery.kuainiao.e;
import com.xunlei.downloadprovider.discovery.kuainiao.e.a;
import com.xunlei.downloadprovider.discovery.redpoint.FindTabItem;
import com.xunlei.downloadprovider.frame.BaseFragment;
import com.xunlei.downloadprovider.frame.MainTabActivity;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.LoginHelper.d;
import com.xunlei.downloadprovider.member.login.LoginHelper.g;
import com.xunlei.downloadprovider.member.login.LoginHelper.p;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter$d;
import com.xunlei.downloadprovider.util.r;
import com.xunlei.downloadprovider.util.r$e;
import com.xunlei.downloadprovider.util.v;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.HashMap;
import java.util.Map;
import org.android.spdy.SpdyProtocol;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DiscoveryFragment extends BaseFragment implements OnClickListener, a, r$e {
    private static final String a;
    private final int b;
    private final int c;
    private ScrollView d;
    private FindTabItem e;
    private FindTabItem f;
    private FindTabItem g;
    private FindTabItem h;
    private FindTabItem i;
    private FindTabItem j;
    private FindTabItem k;
    private boolean l;
    private LinearLayout m;
    private DiscoveryEmptyView n;
    private h.a o;
    private b p;
    private Map<String, com.xunlei.downloadprovider.discovery.a.a> q;
    private boolean r;
    private boolean s;
    private a t;
    private d u;
    private final g v;
    private final p w;

    static {
        a = DiscoveryFragment.class.getSimpleName();
    }

    public DiscoveryFragment() {
        this.b = 101;
        this.c = 102;
        this.o = new b(this);
        this.p = new b(this.o);
        this.t = new c(this);
        this.u = new d(this);
        this.v = new f(this);
        this.w = new g(this);
        LoginHelper.a().a(this.u);
        LoginHelper.a().a(this.v);
        LoginHelper.a().a(this.w);
        r.c().b().add(this);
    }

    private void a(boolean z) {
        if (this.k == null || !z) {
            com.xunlei.downloadprovider.discovery.a.b.a(false);
            this.k.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            LayoutParams layoutParams = (LayoutParams) this.j.getLayoutParams();
            layoutParams.setMargins(0, com.xunlei.downloadprovider.a.g.a(getActivity() == null ? BrothersApplication.a() : getActivity(), 9.0f), 0, 0);
            this.j.setLayoutParams(layoutParams);
            if (this.q != null) {
                int i;
                if (this.q.size() == 1) {
                    i = 1;
                } else {
                    i = 0;
                }
                if ((i & this.q.containsKey("kuainiao")) == 0) {
                    return;
                }
            }
            d();
            return;
        }
        com.xunlei.downloadprovider.discovery.a.b.a(true);
        this.k.setVisibility(0);
        e();
        layoutParams = (LayoutParams) this.j.getLayoutParams();
        layoutParams.setMargins(0, 1, 0, 0);
        this.j.setLayoutParams(layoutParams);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        if (this.mPageRoot == null) {
            this.mPageRoot = (ViewGroup) layoutInflater.inflate(R.layout.find_tab_view, viewGroup, false);
        }
        ViewParent parent = this.mPageRoot.getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).removeView(this.mPageRoot);
        }
        this.d = (ScrollView) findViewById(R.id.sv_layout);
        this.m = (LinearLayout) findViewById(R.id.ll_empty_layout);
        this.n = (DiscoveryEmptyView) findViewById(R.id.empty_layout);
        this.f = (FindTabItem) findViewById(R.id.great_activity_layout);
        this.f.setOnClickListener(this);
        this.g = (FindTabItem) findViewById(R.id.game_layout);
        this.g.setOnClickListener(this);
        this.e = (FindTabItem) findViewById(R.id.one_yuan_snatch_layout);
        this.e.setOnClickListener(this);
        this.i = (FindTabItem) findViewById(R.id.finance_layout);
        this.i.setOnClickListener(this);
        this.h = (FindTabItem) findViewById(R.id.beauty);
        this.h.setOnClickListener(this);
        this.k = (FindTabItem) findViewById(R.id.kuai_niao_layout);
        this.k.setOnClickListener(this);
        this.j = (FindTabItem) findViewById(R.id.remote_download_layout);
        this.j.setOnClickListener(this);
        LoginHelper.a().s();
        e.a().a(this);
        return this.mPageRoot;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onResume() {
        super.onResume();
        if (isAdded()) {
            v.a().a(System.currentTimeMillis(), "find");
            ((MainTabActivity) this.mActivity).a(false);
        }
        c();
        HashMap hashMap = new HashMap();
        hashMap.put("nearby", a("nearby"));
        hashMap.put("activity", a("activity_center"));
        hashMap.put("game_center", a("game_center"));
        hashMap.put("yiyuan", a("snatch"));
        hashMap.put("finance", a("finance"));
        hashMap.put("beauty", a("beauty"));
        hashMap.put("remote", a("remote_download"));
        hashMap.put("kuainiao_status", e.a().a ? c.f : "0");
        ThunderReporter$d.a(hashMap);
    }

    public void onCreateTask(boolean z, int i) {
        super.onCreateTask(z, i);
    }

    public void onPause() {
        super.onPause();
    }

    public void onDestroy() {
        e.a().b(this);
        super.onDestroy();
    }

    private void c() {
        try {
            Map map;
            if (!(this.q == null || this.q.isEmpty())) {
                this.q.clear();
            }
            com.xunlei.downloadprovider.discovery.a.a aVar = r.c().i;
            if (aVar.k == null) {
                map = null;
            } else {
                Map hashMap = new HashMap();
                JSONArray jSONArray = aVar.k.getJSONArray("items");
                if (jSONArray != null && jSONArray.length() > 0) {
                    int length = jSONArray.length();
                    for (int i = 0; i < length; i++) {
                        JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                        com.xunlei.downloadprovider.discovery.a.a aVar2 = new com.xunlei.downloadprovider.discovery.a.a();
                        aVar2.b = jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_NAME);
                        String string = jSONObject.getString("bus_name");
                        aVar2.c = string;
                        aVar2.d = jSONObject.optString("cof_text");
                        aVar2.e = jSONObject.optString("pic_url");
                        aVar2.j = jSONObject.optString("jump_url");
                        aVar2.f = jSONObject.optInt("status");
                        aVar2.g = jSONObject.optInt("phase");
                        if (!(string.equals("neaby") || string.equals("berry_live"))) {
                            if (string.equals("activity_center")) {
                                aVar2.h = 2130838655;
                                aVar2.i = true;
                            } else if (string.equals("game_center")) {
                                aVar2.h = 2130838654;
                                aVar2.i = true;
                            } else if (string.equals("snatch")) {
                                aVar2.h = 2130838224;
                                aVar2.i = false;
                            } else if (string.equals("finance")) {
                                aVar2.h = 2130838222;
                                aVar2.i = false;
                            } else if (string.equals("beautiful_photo")) {
                                aVar2.h = 2130838223;
                                aVar2.i = false;
                            } else if (string.equals("kuainiao")) {
                                aVar2.h = 2130838658;
                                aVar2.i = true;
                            } else if (string.equals("remote_download")) {
                                aVar2.h = 2130838222;
                                aVar2.i = false;
                            }
                            hashMap.put(aVar2.c, aVar2);
                        }
                        aVar2.h = 2130838223;
                        aVar2.i = true;
                        hashMap.put(aVar2.c, aVar2);
                    }
                }
                map = hashMap;
            }
            this.q = map;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (this.q == null || this.q.isEmpty()) {
            d();
            return;
        }
        this.d.setVisibility(0);
        this.m.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        a(this.f, "activity_center");
        if (com.xunlei.downloadprovider.i.a.d()) {
            this.g.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        } else {
            a(this.g, "game_center");
        }
        a(this.e, "snatch");
        a(this.i, "finance");
        a(this.h, "beauty");
        a(this.k, "kuainiao");
        a(this.j, "remote_download");
        if (!this.r) {
            d();
        } else if (!this.s) {
            a(false);
        } else if (com.xunlei.downloadprovider.discovery.a.b.a()) {
            a(true);
        } else {
            e.a();
            e.c();
        }
    }

    private void d() {
        this.d.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.m.setVisibility(0);
        this.n.setOnRefreshListener(this.t);
        this.n.setResult("\u672a\u83b7\u53d6\u5230\u6570\u636e\uff0c\u8bf7\u5237\u65b0\u91cd\u8bd5");
    }

    private void a(FindTabItem findTabItem, String str) {
        int i;
        com.xunlei.downloadprovider.discovery.a.a aVar = (com.xunlei.downloadprovider.discovery.a.a) this.q.get(str);
        if (aVar == null || aVar.f != 1 || TextUtils.isEmpty(aVar.j) || TextUtils.isEmpty(aVar.b)) {
            i = 0;
        } else {
            i = 1;
        }
        this.r |= i;
        if (i != 0) {
            if (v.a().b()) {
                aVar.a = v.a().a(str);
            }
            findTabItem.h = aVar;
            findTabItem.setItemRedPointConfigData(aVar.a);
            if (str.equals("kuainiao")) {
                findTabItem.setItemRightTipIconFromRes(R.drawable.member_type_kuai_niao);
                findTabItem.setConfigInfoVisibility(0);
            }
            findTabItem.setmTvItemDividerShow(str);
            findTabItem.setItemName(findTabItem.h.b);
            findTabItem.g = str;
            if (!TextUtils.isEmpty(aVar.b)) {
                findTabItem.setItemName(aVar.b);
            }
            findTabItem.setItemTipText(aVar.d);
            findTabItem.setItemRightTipIconFromUrl(aVar.e);
            if (str.equals("kuainiao")) {
                this.s = true;
                return;
            } else {
                findTabItem.setVisibility(0);
                return;
            }
        }
        if (str.equals("kuainiao")) {
            this.s = false;
        }
        findTabItem.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.great_activity_layout:
                this.f.onClick();
                ThunderReporter$d.a("activity", a("activity_center"));
            case R.id.game_layout:
                this.g.onClick();
                ThunderReporter$d.a("game_center", a("game_center"));
            case R.id.one_yuan_snatch_layout:
                this.e.onClick();
                ThunderReporter$d.a("yiyuan", a("snatch"));
            case R.id.finance_layout:
                this.i.onClick();
                ThunderReporter$d.a("finance", a("finance"));
            case R.id.beauty:
                this.h.onClick();
                ThunderReporter$d.a("beauty", a("beauty"));
            case R.id.kuai_niao_layout:
                Editor edit = BrothersApplication.a().getSharedPreferences("find_fragment_KuaiNiao_RedPoint", 0).edit();
                edit.putBoolean("findNeedShowKuaiNiaoRedPoint", false);
                edit.commit();
                if (com.xunlei.downloadprovider.discovery.a.b.b()) {
                    this.k.setRightRedPointVisibility(0);
                } else {
                    this.k.setRightRedPointVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                }
                if (this.mActivity != null) {
                    this.mActivity.startActivity(KuaiNiaoActivity.a(this.mActivity, false));
                }
                ThunderReporter$d.a("kuainiao", a("kuainiao"));
            case R.id.remote_download_layout:
                this.j.onClick();
                ThunderReporter$d.a("remote", a("remote_download"));
            default:
                break;
        }
    }

    private static String a(String str) {
        if (str.equals("kuainiao")) {
            return com.xunlei.downloadprovider.discovery.a.b.b() ? "point" : "0";
        } else {
            String str2 = BuildConfig.VERSION_NAME;
            com.xunlei.downloadprovider.discovery.redpoint.a a = v.a().a(str);
            if (a == null) {
                return "0";
            }
            if (!a.a()) {
                return "0";
            }
            if (!TextUtils.isEmpty(a.d)) {
                str2 = str2 + "word:";
            }
            if (!TextUtils.isEmpty(a.e)) {
                str2 = str2 + "pic:";
            }
            return str2 + "point";
        }
    }

    private void e() {
        if (this.k != null) {
            int i;
            if (!this.l) {
                LoginHelper.a();
                if (!(LoginHelper.c() && LoginHelper.a().k() && LoginHelper.a().f())) {
                    i = 0;
                    if (i == 0) {
                        this.k.setRightTipIconVisibility(0);
                    } else {
                        this.k.setRightTipIconVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    }
                }
            }
            Object obj = 1;
            if (i == 0) {
                this.k.setRightTipIconVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                this.k.setRightTipIconVisibility(0);
            }
        }
    }

    public final void a(int i, XLAccelBandInfo xLAccelBandInfo) {
        this.p.obtainMessage(com.xunlei.xllib.R.styleable.AppCompatTheme_checkboxStyle, i, -1, xLAccelBandInfo).sendToTarget();
    }

    public final void a(int i, int i2, KnParams knParams) {
        this.p.obtainMessage(com.xunlei.xllib.R.styleable.AppCompatTheme_buttonStyleSmall, i, i2, knParams).sendToTarget();
    }

    public final void a() {
        if (getActivity() != null && isAdded()) {
            getActivity().runOnUiThread(new h(this));
        }
    }

    static /* synthetic */ void a(DiscoveryFragment discoveryFragment, Message message) {
        boolean z = true;
        new StringBuilder("get_user_info: errorCode --> ").append(message.arg1).append(", isKuaiNiaoVip --> ").append(message.arg2);
        int i = message.arg1;
        int i2 = message.arg2;
        KnParams knParams = (KnParams) message.obj;
        if (knParams == null) {
            discoveryFragment.l = i2 == 1;
        } else {
            if (!(knParams.getOther_IsVip() == 1 && i2 == 1)) {
                z = false;
            }
            discoveryFragment.l = z;
        }
        discoveryFragment.e();
    }

    static /* synthetic */ void b(DiscoveryFragment discoveryFragment, Message message) {
        if (!com.xunlei.downloadprovider.discovery.a.b.a()) {
            boolean z;
            new StringBuilder("get_band_info: errorCode --> ").append(message.arg1).append(", xbi --> ").append(message.obj);
            int i = message.arg1;
            Object obj = message.obj;
            if (i != 0 || obj == null) {
                z = false;
            } else {
                z = true;
            }
            discoveryFragment.a(z);
        }
    }

    static /* synthetic */ void a(DiscoveryFragment discoveryFragment, int i, int i2) {
        if (i2 == 0 && i == 0) {
            com.xunlei.downloadprovider.homepage.a.a.d.b.clear();
            LoginHelper.a().s();
            e.a().b();
            discoveryFragment.l = false;
            discoveryFragment.e();
        }
    }
}
