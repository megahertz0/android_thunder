package com.xunlei.tdlive.usercenter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.common.register.XLRegErrorCode;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.RechargeActivity;
import com.xunlei.tdlive.SettingActivity;
import com.xunlei.tdlive.WebBrowserActivity;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.base.i;
import com.xunlei.tdlive.h;
import com.xunlei.tdlive.modal.e;
import com.xunlei.tdlive.protocol.XLLiveGetUserInfoRequest;
import com.xunlei.tdlive.protocol.XLLiveGetUserInfoRequest$GetUserInfoResp;
import com.xunlei.tdlive.protocol.XLLiveGetUserInfoRequest.GetUserInfoResp.Data;
import com.xunlei.tdlive.protocol.XLLiveGetUserInfoRequest.LType;
import com.xunlei.tdlive.protocol.XLLiveRequest;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;
import com.xunlei.tdlive.protocol.XLLiveUpdateAvatarRequest;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.usercenter.ThreeNumLayout.a;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.withdraw.MyInComeActivity;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import org.android.spdy.SpdyProtocol;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: UserCenterFragment.java
public class q extends i implements OnClickListener, ObjectCallBack, a {
    private h k;
    private UserInfoTop l;
    private XLLiveRequest m;
    private View n;
    private ThreeNumLayout o;
    private Data p;
    private Dialog q;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.xllive_activity_scrollable_usercenter, viewGroup, false);
        ViewStub viewStub = (ViewStub) inflate.findViewById(R.id.stub);
        viewStub.setLayoutResource(R.layout.xllive_usercenter_self);
        viewStub.inflate();
        a(inflate);
        return inflate;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof h) {
            this.k = (h) activity;
        }
    }

    public void onResume() {
        super.onResume();
        a(false);
    }

    public void a(boolean z) {
        super.a(z);
        c();
        if (this.k != null) {
            this.k.a("usercenter");
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        if (this.m != null) {
            this.m.cancel();
        }
    }

    private void a(View view) {
        this.l = (UserInfoTop) view.findViewById(R.id.userinfoTop);
        this.l.setAvatarClickListener(this);
        this.l.setNickname(f.a().m());
        this.l.setSignature(f.a().n());
        this.l.setShowSendInfo(true);
        this.l.setShowSign(true);
        this.l.setShowUuid(true);
        this.n = view.findViewById(R.id.lvLiveInfo);
        this.n.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.o = (ThreeNumLayout) view.findViewById(R.id.threeNum);
        this.o.addOnItemSelectListener(this);
        this.o.setShowRedFlag(true);
        view.findViewById(R.id.lvSettings).setOnClickListener(this);
        view.findViewById(R.id.lvMyCoin).setOnClickListener(this);
        view.findViewById(R.id.lvMyMessagebox).setOnClickListener(this);
        view.findViewById(R.id.lvUserLevel).setOnClickListener(this);
        view.findViewById(R.id.lvPlayRecord).setOnClickListener(this);
        ImageView imageView = (ImageView) view.findViewById(R.id.ivLeft);
        imageView.setImageResource(R.drawable.xllive_userinfo_edit_selector);
        imageView.setOnClickListener(this);
        ViewStub viewStub = (ViewStub) view.findViewById(R.id.tvRight);
        viewStub.setLayoutResource(R.layout.xllive_usercenter_recharge_btn);
        viewStub.setInflatedId(R.id.tvRight);
        TextView textView = (TextView) viewStub.inflate();
        textView.setText(R.string.charge);
        textView.setOnClickListener(this);
    }

    private void c() {
        if (this.m != null) {
            this.m.cancel();
        }
        this.m = new XLLiveGetUserInfoRequest(f.a().k(), f.a().l(), f.a().k(), LType.XL);
        this.m.send(this);
    }

    private void a(int i, int i2, int i3) {
        this.o.updateThreeNum(i, i2, i3);
    }

    private void f(int i) {
        TextView textView = (TextView) ((ViewGroup) b(R.id.lvMySelf)).findViewById(R.id.tvGain);
        if (i > 0) {
            textView.setVisibility(0);
            textView.setText(String.valueOf(i));
            return;
        }
        textView.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
    }

    private void g(int i) {
        String k = f.a(getActivity()).k();
        TextView textView = (TextView) ((ViewGroup) b(R.id.lvMySelf)).findViewById(R.id.tvMessageNum);
        if (i > 0) {
            int c = c(new StringBuilder("MESSAGE_COUNT_").append(k).toString(), 0);
            if (i - c > 0) {
                textView.setText(String.valueOf(i - c));
            } else {
                textView.setText(BuildConfig.VERSION_NAME);
            }
        }
        e.a = i;
        b(new StringBuilder("LAST_USER_MAIL_NUMBER_").append(k).toString(), e.a);
    }

    private void c(String str) {
        ((TextView) b(R.id.tvLevel)).setText(str);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ivLeft) {
            if (this.p != null) {
                startActivityForResult(UserInfoEditActivity.a(getActivity(), this.p.avatar, this.p.nickname, this.p.sign, this.p.sexEditable() ? -this.p.sex : this.p.sex), 202);
            }
        } else if (id == R.id.tvRight) {
            startActivity(new Intent(getActivity(), RechargeActivity.class));
        } else if (id == R.id.avatar) {
            d();
            a((Fragment) this, (int) XLRegErrorCode.REG_SUCCEED);
        } else if (id == R.id.lvSettings) {
            startActivityForResult(new Intent(getActivity(), SettingActivity.class), XLRegErrorCode.REG_EXIST);
        } else if (id == R.id.lvMyCoin) {
            startActivity(new Intent(getActivity(), MyInComeActivity.class));
        } else if (id == R.id.lvMyMessagebox) {
            startActivity(new Intent(getActivity(), MsgBoxActivity.class));
        } else if (id == R.id.lvUserLevel) {
            WebBrowserActivity.start(getActivity(), "http://h5.live.xunlei.com/active/realname/level.html", "\u6211\u7684\u7b49\u7ea7", false);
        } else if (id == R.id.lvPlayRecord) {
            WebBrowserActivity.start(getActivity(), "http://h5.live.xunlei.com/active/playlist/index.html", "\u5f00\u64ad\u8bb0\u5f55", false);
        }
    }

    public static void a(Activity activity, int i) {
        new com.xunlei.tdlive.base.a(activity, "\u7f16\u8f91\u5934\u50cf", "\u53d6\u6d88", new String[]{"\u62cd\u7167", "\u4ece\u672c\u5730\u76f8\u518c\u9009\u62e9"}).b(new r(activity, i));
    }

    public static void a(Fragment fragment, int i) {
        new com.xunlei.tdlive.base.a(fragment.getActivity(), "\u7f16\u8f91\u5934\u50cf", "\u53d6\u6d88", new String[]{"\u62cd\u7167", "\u4ece\u672c\u5730\u76f8\u518c\u9009\u62e9"}).b(new s(fragment, i));
    }

    public static File a(int i, Intent intent, ObjectCallBack objectCallBack) {
        String str = null;
        if (i != -1 || intent == null) {
            return null;
        }
        ArrayList stringArrayListExtra = intent.getStringArrayListExtra("images");
        if (stringArrayListExtra != null && stringArrayListExtra.size() > 0) {
            str = (String) stringArrayListExtra.get(0);
        }
        File file = new File(URI.create(str));
        XLLiveUpdateAvatarRequest xLLiveUpdateAvatarRequest = new XLLiveUpdateAvatarRequest(f.a().k(), f.a().l(), file);
        if (xLLiveUpdateAvatarRequest.checkParams()) {
            xLLiveUpdateAvatarRequest.send(objectCallBack);
        } else {
            XLog.d("UserCenterFragment", "image for avatar is invalid");
        }
        if (str != null) {
            XLog.d("UserCenterFragment", new StringBuilder("begin to change avatar to ").append(str).toString());
        }
        return file;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 200) {
            if (a(i2, intent, new t(this)) == null) {
                XLog.d("UserCenterFragment", "no file selected");
                return;
            }
            this.q = ah.a(getActivity(), "\u4e0a\u4f20\u4e2d...", true);
            this.q.show();
        } else if (i == 201) {
            if (i2 == 1 && this.k != null) {
                this.k.a();
            }
        } else if (i == 202 && i2 == -1) {
            c();
        }
    }

    private void d() {
        com.xunlei.tdlive.util.q.a aVar = new com.xunlei.tdlive.util.q.a();
        aVar.a("userid", f.a().k());
        com.xunlei.tdlive.util.q.a("userinfo", "center", null, aVar.a());
    }

    public void onResponse(int i, String str, Object obj) {
        XLog.d("UserCenterFragment", new StringBuilder("get userinfo ret = ").append(i).append(", msg = ").append(str).toString());
        if (i == 0 && (obj instanceof XLLiveGetUserInfoRequest$GetUserInfoResp)) {
            XLLiveGetUserInfoRequest$GetUserInfoResp xLLiveGetUserInfoRequest$GetUserInfoResp = (XLLiveGetUserInfoRequest$GetUserInfoResp) obj;
            this.p = xLLiveGetUserInfoRequest$GetUserInfoResp.data;
            if (this.l != null && xLLiveGetUserInfoRequest$GetUserInfoResp.data != null) {
                this.l.setNickname(xLLiveGetUserInfoRequest$GetUserInfoResp.data.nickname);
                this.l.setSignature(xLLiveGetUserInfoRequest$GetUserInfoResp.data.sign);
                this.l.setCoinNum(xLLiveGetUserInfoRequest$GetUserInfoResp.data.getSend());
                this.l.setSex(xLLiveGetUserInfoRequest$GetUserInfoResp.data.sex);
                this.l.setUUID(xLLiveGetUserInfoRequest$GetUserInfoResp.data.uuid);
                if (xLLiveGetUserInfoRequest$GetUserInfoResp.data.level != null) {
                    this.l.setLevel(xLLiveGetUserInfoRequest$GetUserInfoResp.data.level.current, xLLiveGetUserInfoRequest$GetUserInfoResp.data.level.title, xLLiveGetUserInfoRequest$GetUserInfoResp.data.level.getIconFullPath());
                }
                if (!TextUtils.isEmpty(xLLiveGetUserInfoRequest$GetUserInfoResp.data.avatar)) {
                    this.l.setAvatar(Uri.parse(xLLiveGetUserInfoRequest$GetUserInfoResp.data.avatar));
                }
                a(xLLiveGetUserInfoRequest$GetUserInfoResp.data.total_record_num, xLLiveGetUserInfoRequest$GetUserInfoResp.data.fans_num, xLLiveGetUserInfoRequest$GetUserInfoResp.data.follow_num);
                if (xLLiveGetUserInfoRequest$GetUserInfoResp.data.exchange_info != null) {
                    try {
                        f(Integer.parseInt(xLLiveGetUserInfoRequest$GetUserInfoResp.data.exchange_info.current_gold_coin));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                try {
                    g(xLLiveGetUserInfoRequest$GetUserInfoResp.data.mail_num);
                    c(xLLiveGetUserInfoRequest$GetUserInfoResp.data.level.title);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    private void d(String str) {
        FragmentActivity activity = getActivity();
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).showToast(str, 0, R.layout.xllive_toast_uiserinfo_edit, R.id.text, com.xunlei.xllib.R.styleable.Toolbar_maxButtonHeight);
        }
    }

    public void a(int i) {
        String m;
        String str = null;
        if (i == 1) {
            m = f.a().m();
            str = f.a().o();
        } else {
            m = null;
        }
        y.a(getActivity(), f.a().k(), i, m, str);
    }
}
