package com.xunlei.downloadprovider.personal.settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.commonview.dialog.XLAlarmDialog;
import com.xunlei.downloadprovider.commonview.f;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.e;
import com.xunlei.downloadprovider.frame.BaseFragment;
import com.xunlei.downloadprovider.frame.user.UserFeedBackUmActivity;
import com.xunlei.downloadprovider.frame.user.bx;
import com.xunlei.downloadprovider.i.a.c;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.util.aa;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.shoulei.XZBShouleiUtil;
import com.xunlei.xllib.a.b;
import org.json.JSONException;
import org.json.JSONObject;

public class SettingsIndexFragment extends BaseFragment implements OnClickListener {
    private f a;
    private View b;
    private View c;
    private View d;
    private View e;
    private View f;
    private View g;
    private TextView h;
    private ImageView i;
    private View j;
    private View k;
    private TextView l;
    private View m;
    private View n;
    private TextView o;
    private ViewGroup p;
    private View q;
    private c r;
    private LinearLayout s;
    private XLAlarmDialog t;
    private View u;
    private View v;
    private com.xunlei.downloadprovider.model.protocol.f.c w;

    public void onCreate(Bundle bundle) {
        JSONObject jSONObject;
        super.onCreate(bundle);
        try {
            jSONObject = new JSONObject(aa.b(this.mActivity, "update_info_manual"));
        } catch (JSONException e) {
            e.printStackTrace();
            jSONObject = null;
        }
        this.w = com.xunlei.downloadprovider.model.protocol.f.c.a(jSONObject);
        if (this.w == null || this.w.a()) {
            c.a(new s(this));
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mPageRoot = (ViewGroup) layoutInflater.inflate(2130968782, viewGroup, false);
        this.a = new f(this.mPageRoot);
        this.a.g.setVisibility(0);
        this.a.i.setText(2131232552);
        this.a.g.setOnClickListener(new t(this));
        this.n = findViewById(2131756913);
        this.n.setOnClickListener(this);
        this.o = (TextView) findViewById(2131756914);
        this.b = findViewById(2131756926);
        this.c = findViewById(2131756925);
        this.d = findViewById(2131756928);
        this.e = findViewById(2131756927);
        this.f = findViewById(2131756916);
        this.f.setOnClickListener(this);
        findViewById(2131756921).setOnClickListener(this);
        this.g = findViewById(2131756917);
        this.h = (TextView) findViewById(2131756918);
        this.i = (ImageView) findViewById(2131756919);
        if (XZBShouleiUtil.getInstance().getDefaultDevice() != null) {
            e.a();
            if (e.f()) {
                this.h.setVisibility(0);
                this.i.setVisibility(0);
            }
        }
        this.g.setOnClickListener(new u(this));
        this.s = (LinearLayout) findViewById(2131756929);
        this.s.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.j = findViewById(2131756922);
        this.j.setOnClickListener(this);
        this.k = findViewById(2131756934);
        this.k.setOnClickListener(this);
        this.m = findViewById(2131756938);
        this.u = findViewById(2131756937);
        LoginHelper.a();
        if (LoginHelper.c()) {
            this.m.setOnClickListener(this);
        } else if (new bx().a) {
            this.m.setOnClickListener(this);
        } else {
            this.m.setVisibility(XZBDevice.Wait);
            this.u.setVisibility(XZBDevice.Wait);
        }
        this.l = (TextView) findViewById(2131756935);
        String resouceString = getResouceString(2131232454);
        this.l.setText(resouceString + getResouceString(2131231136, new Object[]{getResouceString(R.string.version)}));
        this.p = (ViewGroup) findViewById(2131756930);
        this.q = findViewById(2131756931);
        this.v = findViewById(2131756933);
        this.p.setOnClickListener(new v(this));
        a();
        return this.mPageRoot;
    }

    private void a() {
        if (c.a(this.mActivity)) {
            this.p.setVisibility(0);
            if (this.w == null || this.w.a() || this.w.c <= 0 || this.w.c == 4) {
                this.v.setVisibility(XZBDevice.Wait);
                return;
            } else {
                this.v.setVisibility(0);
                return;
            }
        }
        this.p.setVisibility(XZBDevice.Wait);
    }

    public void onResume() {
        super.onResume();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case 2131756913:
                ChooseSDcardActivity.a(this.mActivity);
            case 2131756916:
                startActivityWithAnimation(TaskSettingActivity.class);
                StatReporter.reportClick(5001, "download", null);
            case 2131756921:
                startActivityWithAnimation(SniffSettingActivity.class);
            case 2131756922:
                startActivityWithAnimation(GeneralSettingActivity.class);
                StatReporter.reportClick(5001, "general", null);
            case 2131756925:
            case 2131756926:
                startActivityWithAnimation(SpeedDetectionActivity.class);
                StatReporter.reportClick(5001, "networkCheck", null);
            case 2131756929:
                this.mActivity.startActivity(new Intent(this.mActivity, UserFeedBackUmActivity.class));
            case 2131756934:
                startActivityWithAnimation(AboutBoxActivity.class);
                StatReporter.reportClick(5001, "about", null);
            case 2131756938:
                Context activity = getActivity();
                if (this.t != null) {
                    try {
                        if (this.t.isShowing()) {
                            this.t.dismiss();
                        }
                        this.t = null;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (activity instanceof Activity) {
                    this.t = new XLAlarmDialog(activity);
                    this.t.setTitle("\u9000\u51fa\u767b\u5f55");
                    this.t.setConfirmButtonText("\u9000\u51fa");
                    this.t.setRightBtnTextColor(activity.getResources().getColor(R.color.global_text_color_2));
                    this.t.setCancelButtonText("\u53d6\u6d88");
                    this.t.setContent("\u9000\u51fa\u767b\u5f55\u540e\uff0c\u60a8\u5c06\u4e0d\u80fd\u8fdb\u884c\u8bc4\u8bba\u3001\u65e0\u6cd5\u4eab\u53d7\u4e91\u7aef\u670d\u52a1\uff0c\u786e\u8ba4\u9000\u51fa\uff1f");
                    this.t.setOnClickConfirmButtonListener(new w(this, activity));
                    this.t.setOnClickCancelButtonListener(new y(this));
                    this.t.show();
                }
            default:
                break;
        }
    }

    static /* synthetic */ void h(SettingsIndexFragment settingsIndexFragment) {
        if (b.a(settingsIndexFragment.getActivity())) {
            if (settingsIndexFragment.r != null) {
                settingsIndexFragment.r.a();
            }
            c.a(settingsIndexFragment.mActivity, true);
            return;
        }
        XLToast.a(settingsIndexFragment.getApplicationContext(), XLToastType.XLTOAST_TYPE_ALARM, settingsIndexFragment.getString(2131232509), XZBDevice.DOWNLOAD_LIST_RECYCLE);
    }
}
