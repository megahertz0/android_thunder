package com.xunlei.tdlive.usercenter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.d;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xunlei.tdlive.LivePlayerActivity;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.base.i;
import com.xunlei.tdlive.play.view.LiveReplayActivity;
import com.xunlei.tdlive.protocol.XLLiveGetFollowListRequest.UserListResp.UserInfo;
import com.xunlei.tdlive.protocol.XLLiveGetLiveRecordRequest.ReocodItem;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.usercenter.d.b;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.xiazaibao.BuildConfig;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: UserListFragment2.java
public class y extends i implements OnItemClickListener, OnItemLongClickListener, d<ListView>, b {
    private PullToRefreshListView k;
    private ListView l;
    private d<?> m;
    private String n;
    private ViewGroup o;
    private int p;
    private boolean q;
    private String r;
    private String s;
    private boolean t;

    public y() {
        this.q = false;
        this.t = false;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i;
        this.o = new FrameLayout(getActivity());
        RelativeLayout relativeLayout = (RelativeLayout) layoutInflater.inflate(R.layout.xllive_usercenter_userlist, this.o, false);
        LayoutParams layoutParams = (LayoutParams) relativeLayout.getLayoutParams();
        layoutParams.topMargin = 30;
        relativeLayout.setLayoutParams(layoutParams);
        this.o.addView(relativeLayout);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.n = arguments.getString("key_userid");
            i = arguments.getInt("KEY_TAG");
            this.p = i;
            if (i == 1) {
                this.r = arguments.getString("KEY_NICKNAME");
                this.s = arguments.getString("KEY_IMAGE_URL");
            }
        } else {
            XLog.w("UserListFragment2", "no args");
            i = 0;
        }
        this.k = (PullToRefreshListView) relativeLayout.findViewById(R.id.listUserInfo);
        this.l = (ListView) this.k.getRefreshableView();
        this.k.setOnRefreshListener(this);
        this.k.setOnItemClickListener(this);
        ((ListView) this.k.getRefreshableView()).setOnItemLongClickListener(this);
        this.k.setMode(Mode.PULL_FROM_END);
        f(i);
        return this.o;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.t = true;
    }

    public void onDetach() {
        super.onDetach();
        this.t = false;
    }

    public void onResume() {
        super.onResume();
        if (this.m.getCount() == 0) {
            this.m.a(com.xunlei.xllib.R.styleable.AppCompatTheme_buttonStyle);
        }
    }

    private void f(int i) {
        switch (i) {
            case SimpleLog.LOG_LEVEL_TRACE:
                this.m = new af(getActivity(), this.n, this.r, this.s);
                break;
            case SimpleLog.LOG_LEVEL_DEBUG:
                this.m = new v(getActivity(), this.n, "fans");
                break;
            case MqttConnectOptions.MQTT_VERSION_3_1:
                this.m = new v(getActivity(), this.n, "focus");
                break;
        }
        this.m.a(this);
        this.l.setAdapter(this.m);
        ah.a(this.n, i);
    }

    public void onRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
        this.m.a(com.xunlei.xllib.R.styleable.AppCompatTheme_buttonStyle);
    }

    public static void a(Context context, String str, int i, String str2, String str3) {
        String str4 = null;
        switch (i) {
            case SimpleLog.LOG_LEVEL_TRACE:
                str4 = "\u6211\u7684\u76f4\u64ad";
                break;
            case SimpleLog.LOG_LEVEL_DEBUG:
                str4 = "\u7c89\u4e1d";
                break;
            case MqttConnectOptions.MQTT_VERSION_3_1:
                str4 = "\u5173\u6ce8";
                break;
        }
        Bundle bundle = new Bundle();
        bundle.putString("key_userid", str);
        bundle.putInt("KEY_TAG", i);
        bundle.putString("KEY_NICKNAME", str2);
        bundle.putString("KEY_IMAGE_URL", str3);
        ShellActivity.a(context, y.class, str4, bundle);
    }

    private void c() {
        LayoutInflater.from(getActivity()).inflate(R.layout.xllive_list_empty, this.o, true);
        View findViewById = this.o.findViewById(R.id.lvEmpty);
        Resources resources = getActivity().getResources();
        if (this.p == 1) {
            ah.a(findViewById, resources.getString(R.string.myself_no_live), resources.getString(R.string.live_now), new z(this));
        } else if (this.p == 3) {
            ah.a(findViewById, resources.getString(R.string.myself_no_follow), resources.getString(R.string.back_home), new aa(this));
        } else if (this.p == 2) {
            ah.a(findViewById, resources.getString(R.string.myself_no_funs), resources.getString(R.string.live_now), new ab(this));
        }
        this.l.setEmptyView(findViewById);
    }

    public void b() {
        if (this.t) {
            if (!this.q) {
                this.q = true;
                c();
            }
            this.k.m();
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        String str = null;
        if (this.p == 1) {
            try {
                ReocodItem reocodItem = (ReocodItem) adapterView.getAdapter().getItem(i);
                LiveReplayActivity.a(getActivity(), reocodItem.roomid, reocodItem.userid, reocodItem.play_hls_url, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME, BuildConfig.VERSION_NAME, 0, 0, BuildConfig.VERSION_NAME);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (this.p == 2 || this.p == 3) {
            UserInfo userInfo = (UserInfo) adapterView.getAdapter().getItem(i);
            if (!f.a().b(String.valueOf(userInfo.userid))) {
                if (userInfo.isLiving()) {
                    LivePlayerActivity.a(getActivity(), userInfo.room_info.roomid, String.valueOf(userInfo.userid), userInfo.room_info.stream_pull, userInfo.avatar, userInfo.avatar, null, 0, 0, BuildConfig.VERSION_NAME);
                    return;
                }
                if (this.p == 2) {
                    str = "fans";
                } else if (this.p == 3) {
                    str = "attention";
                }
                UserCenterActivity.a(getActivity(), String.valueOf(userInfo.userid), userInfo.nickname, str);
            }
        }
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.p != 1) {
            return false;
        }
        int i2 = i - 1;
        ReocodItem reocodItem = (ReocodItem) this.m.getItem(i2);
        view.setSelected(true);
        ah.a(view, new ac(this, reocodItem, i2), new ae(this, view));
        return true;
    }
}
