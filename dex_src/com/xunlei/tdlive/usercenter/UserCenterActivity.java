package com.xunlei.tdlive.usercenter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.d;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xunlei.tdlive.LivePlayerActivity;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.play.view.LiveReplayActivity;
import com.xunlei.tdlive.protocol.XLLiveFollowRequest;
import com.xunlei.tdlive.protocol.XLLiveGetFollowListRequest.UserListResp.UserInfo;
import com.xunlei.tdlive.protocol.XLLiveGetLiveRecordRequest.ReocodItem;
import com.xunlei.tdlive.protocol.XLLiveGetOtherUserInfoRequest;
import com.xunlei.tdlive.protocol.XLLiveGetOtherUserInfoRequest.GetOtherUserInfoResp;
import com.xunlei.tdlive.protocol.XLLiveGetOtherUserInfoRequest.GetOtherUserInfoResp.Data;
import com.xunlei.tdlive.protocol.XLLiveGetOtherUserInfoRequest.GetOtherUserInfoResp.RoomInfo;
import com.xunlei.tdlive.protocol.XLLiveRequest;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.usercenter.d.b;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.q;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public class UserCenterActivity extends BaseActivity implements OnClickListener, OnItemClickListener, d<ListView>, ObjectCallBack, com.xunlei.tdlive.usercenter.ThreeNumLayout.a {
    private static final String a;
    private ListView b;
    private PullToRefreshListView c;
    private String d;
    private String e;
    private String f;
    private Data g;
    private UserInfoTop h;
    private XLLiveRequest i;
    private View j;
    private TextView k;
    private TextView l;
    private ThreeNumLayout m;
    private int n;
    private boolean o;
    private a p;
    private a q;
    private a r;

    class a implements com.xunlei.tdlive.usercenter.d.a, b {
        private d<?> b;
        private int c;
        private boolean d;
        private boolean e;

        public a(d<?> dVar, int i) {
            this.d = false;
            this.e = false;
            this.b = dVar;
            this.b.a((b) this);
            this.c = i;
        }

        public d<?> c() {
            return this.b;
        }

        public void a(boolean z) {
            this.d = z;
            if (z) {
                UserCenterActivity.this.b.setAdapter(this.b);
                this.b.b((int) R.styleable.AppCompatTheme_buttonStyle);
                return;
            }
            this.b.d();
        }

        public void a(int i) {
            this.b.a(i);
        }

        public void b(int i) {
            this.b.b(i);
        }

        public void b() {
            XLog.d(a, "onLoadFinish");
            if (this.d) {
                if (this.b.getCount() == 0) {
                    this.e = true;
                    b bVar = new b(UserCenterActivity.this.d, this.c);
                    bVar.a(this);
                    UserCenterActivity.this.b.setAdapter(bVar);
                } else if (this.e) {
                    this.e = false;
                    UserCenterActivity.this.b.setAdapter(this.b);
                }
                UserCenterActivity.this.c.m();
            }
        }

        public void a() {
            UserCenterActivity.this.g();
            a((int) R.styleable.AppCompatTheme_buttonStyle);
        }
    }

    public UserCenterActivity() {
        this.n = 0;
        this.o = false;
    }

    static {
        a = UserCenterActivity.class.getSimpleName();
    }

    protected void onCreate(Bundle bundle) {
        String stringExtra;
        super.onCreate(bundle);
        setContentView(R.layout.xllive_usercenter_userlist);
        Intent intent = getIntent();
        if (intent != null) {
            this.d = intent.getStringExtra("key_userid");
            stringExtra = intent.getStringExtra("EXTRA_TRACE_TAG");
        } else {
            stringExtra = null;
        }
        if (TextUtils.isEmpty(this.d)) {
            finish();
            return;
        }
        View inflate = LayoutInflater.from(this).inflate(R.layout.xllive_activity_usercenter, null);
        this.c = (PullToRefreshListView) findViewById(R.id.listUserInfo);
        this.b = (ListView) this.c.getRefreshableView();
        this.b.addHeaderView(inflate, null, false);
        this.b.setOnItemClickListener(this);
        this.c.setOnRefreshListener(this);
        this.c.setMode(Mode.PULL_FROM_END);
        a(inflate);
        f();
        b(inflate);
        a(this.d, stringExtra);
    }

    protected void onResume() {
        super.onResume();
        g();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public static void a(Context context, String str, String str2, String str3) {
        Intent intent = new Intent(context, UserCenterActivity.class);
        intent.putExtra("key_userid", str);
        intent.putExtra("EXTRA_NICKNAME", str2);
        intent.putExtra("EXTRA_TRACE_TAG", str3);
        context.startActivity(intent);
    }

    private void a(View view) {
        this.m = (ThreeNumLayout) view.findViewById(R.id.threeNum);
        this.m.addOnItemSelectListener(this);
        this.m.setSelectedWhenClick(true);
        this.h = (UserInfoTop) view.findViewById(R.id.userinfoTop);
        this.h.setAvatarClickListener(this);
        this.h.setShowSendInfo(false);
        this.h.setShowSign(true);
        this.h.setShowUuid(true);
        ImageView imageView = (ImageView) view.findViewById(R.id.ivLeft);
        imageView.setImageResource(R.drawable.xllive_ic_back_black);
        imageView.setOnClickListener(this);
        ViewStub viewStub = (ViewStub) view.findViewById(R.id.tvRight);
        viewStub.setLayoutResource(R.layout.xllive_usercenter_follow_btn);
        viewStub.setInflatedId(R.id.tvRight);
        this.k = (TextView) viewStub.inflate();
        this.k.setVisibility(XZBDevice.Wait);
        this.j = view.findViewById(R.id.lvLiveInfo);
        this.j.setVisibility(XZBDevice.Wait);
    }

    private void b(View view) {
        View findViewById = findViewById(R.id.titleBar);
        this.l = (TextView) findViewById.findViewById(R.id.tvFollow);
        findViewById.findViewById(R.id.ivBack).setOnClickListener(this);
        findViewById.setClickable(true);
        this.b.setOnScrollListener(new k(this, view, findViewById));
    }

    private void b() {
        if (this.g != null) {
            ((TextView) findViewById(R.id.titleBar).findViewById(R.id.tvTitle)).setText(this.g.user_info.nickname);
            a(this.g.isFollow());
            c();
        }
    }

    private void c() {
        if (this.g != null) {
            this.l.setOnClickListener(new e(this.g.isFollow(), String.valueOf(this.d), 1, new l(this)));
        }
    }

    private void d() {
        if (this.g != null) {
            this.k.setOnClickListener(new e(this.g.isFollow(), String.valueOf(this.d), 1, new m(this)));
        }
    }

    private void a(boolean z) {
        this.k.setSelected(z);
        this.k.setText(z ? R.string.followed : R.string.follow);
        this.l.setSelected(z);
        this.l.setText(z ? R.string.followed : R.string.follow);
        if (z != this.g.isFollow()) {
            this.g.is_follow = z ? 1 : 0;
            if (this.n == 2) {
                this.q.b(R.styleable.AppCompatTheme_buttonStyle);
            }
            g();
        }
    }

    private void e() {
        new XLLiveFollowRequest(f.a().k(), f.a().l(), this.d, true).send(new n(this));
    }

    private void b(int i) {
        int i2 = 0;
        this.n = i;
        switch (i) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.p.a(false);
                this.q.a(false);
                this.r.a(true);
                View view = this.j;
                if (!this.o) {
                    i2 = 8;
                }
                view.setVisibility(i2);
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                this.p.a(false);
                this.q.a(true);
                this.r.a(false);
                this.j.setVisibility(XZBDevice.Wait);
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                this.p.a(true);
                this.q.a(false);
                this.r.a(false);
                this.j.setVisibility(XZBDevice.Wait);
                break;
            default:
                XLog.d(a, "unknown tag");
                break;
        }
        ah.a(this.d, i);
    }

    private void f() {
        this.p = new a(new v(this, this.d, "focus"), 3);
        this.q = new a(new v(this, this.d, "fans"), 2);
        this.r = new a(new af(this, this.d, this.h.getNickName().toString(), this.h.getAvatarUrl()), 1);
        b(1);
    }

    private void g() {
        if (this.i != null) {
            this.i.cancel();
        }
        this.i = new XLLiveGetOtherUserInfoRequest(f.a().k(), f.a().l(), this.d);
        this.i.send((ObjectCallBack) this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ivLeft) {
            finish();
        } else if (id == R.id.avatar) {
        } else {
            if (id == R.id.ivBack) {
                finish();
            } else if (id == R.id.tvFollow || id == R.id.tvRight) {
                f.a().a(this, "attention_usercenter", new o(this));
            }
        }
    }

    public void onRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
        switch (this.n) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.r.a((int) R.styleable.AppCompatTheme_buttonStyle);
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                this.q.a((int) R.styleable.AppCompatTheme_buttonStyle);
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                this.p.a((int) R.styleable.AppCompatTheme_buttonStyle);
            default:
                XLog.d(a, "unknown tag");
        }
    }

    public void onResponse(int i, String str, Object obj) {
        int i2 = 0;
        if (obj instanceof GetOtherUserInfoResp) {
            GetOtherUserInfoResp getOtherUserInfoResp = (GetOtherUserInfoResp) obj;
            if (getOtherUserInfoResp.data != null) {
                int i3;
                int i4;
                this.g = getOtherUserInfoResp.data;
                this.k.setVisibility(0);
                d();
                a(getOtherUserInfoResp.data.isFollow());
                String str2 = null;
                if (getOtherUserInfoResp.data.user_info != null) {
                    String str3 = getOtherUserInfoResp.data.user_info.avatar;
                    this.f = str3;
                    this.e = getOtherUserInfoResp.data.user_info.nickname;
                    b();
                    ((af) this.r.c()).a(getOtherUserInfoResp.data.user_info.nickname, getOtherUserInfoResp.data.user_info.avatar);
                    this.h.setNickname(getOtherUserInfoResp.data.user_info.nickname);
                    this.h.setSignature(getOtherUserInfoResp.data.user_info.sign);
                    this.h.setCoinNum(getOtherUserInfoResp.data.user_info.getSend());
                    this.h.setSex(getOtherUserInfoResp.data.user_info.sex);
                    this.h.setUUID(getOtherUserInfoResp.data.user_info.uuid);
                    if (getOtherUserInfoResp.data.user_info.level != null) {
                        this.h.setLevel(getOtherUserInfoResp.data.user_info.level.current, getOtherUserInfoResp.data.user_info.level.title, getOtherUserInfoResp.data.user_info.level.getIconFullPath());
                    }
                    if (!TextUtils.isEmpty(getOtherUserInfoResp.data.user_info.avatar)) {
                        this.h.setAvatar(Uri.parse(getOtherUserInfoResp.data.user_info.avatar));
                    }
                    i3 = getOtherUserInfoResp.data.user_info.fans_num;
                    String str4 = str3;
                    i4 = getOtherUserInfoResp.data.user_info.follow_num;
                    str2 = str4;
                } else {
                    i4 = 0;
                    i3 = 0;
                }
                if (getOtherUserInfoResp.data.room_info == null || !getOtherUserInfoResp.data.room_info.isLive()) {
                    this.o = false;
                } else {
                    this.o = true;
                    a(getOtherUserInfoResp.data.room_info, 0, str2);
                }
                if (this.o && this.n == 1) {
                    this.j.setVisibility(0);
                } else {
                    this.j.setVisibility(XZBDevice.Wait);
                }
                if (getOtherUserInfoResp.data.player_info != null) {
                    i2 = getOtherUserInfoResp.data.player_info.total_record_num;
                }
                this.m.updateThreeNum(i2, i3, i4);
            }
        }
    }

    private void a(RoomInfo roomInfo, int i, String str) {
        if (roomInfo != null) {
            if (!TextUtils.isEmpty(roomInfo.image)) {
                str = roomInfo.image;
            }
            CharSequence charSequence = roomInfo.title;
            int i2 = roomInfo.online_user_num;
            View view = (ImageView) this.j.findViewById(R.id.ivLiveThumb);
            TextView textView = (TextView) this.j.findViewById(R.id.tvTitle);
            TextView textView2 = (TextView) this.j.findViewById(R.id.tvViewNum);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            view.setLayoutParams(new LayoutParams(displayMetrics.widthPixels, displayMetrics.widthPixels));
            com.xunlei.tdlive.util.a.a((Context) this).a(view, str);
            textView.setText(charSequence);
            textView2.setText(i2 + "\u4eba\u5728\u770b");
            view.setOnClickListener(new p(this, roomInfo));
        }
    }

    public void a(int i) {
        XLog.d(a, new StringBuilder("onSelect() ").append(i).toString());
        b(i);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        String str = null;
        if (this.n == 1) {
            try {
                ReocodItem reocodItem = (ReocodItem) adapterView.getAdapter().getItem(i);
                LiveReplayActivity.a(this, reocodItem.roomid, reocodItem.userid, reocodItem.play_hls_url, com.umeng.a.d, com.umeng.a.d, com.umeng.a.d, 0, 0, com.umeng.a.d);
            } catch (Exception e) {
            }
        } else if (this.n == 2 || this.n == 3) {
            try {
                UserInfo userInfo = (UserInfo) adapterView.getAdapter().getItem(i);
                if (!f.a().b(String.valueOf(userInfo.userid))) {
                    if (userInfo.isLiving()) {
                        LivePlayerActivity.a(this, userInfo.room_info.roomid, String.valueOf(userInfo.userid), userInfo.room_info.stream_pull, null, null, null, 0, 0, com.umeng.a.d);
                        return;
                    }
                    if (this.n == 2) {
                        str = "fans";
                    } else if (this.n == 3) {
                        str = "attention";
                    }
                    a(this, String.valueOf(userInfo.userid), userInfo.nickname, str);
                }
            } catch (Exception e2) {
            }
        }
    }

    private void a(String str, String str2) {
        com.xunlei.tdlive.util.q.a aVar = new com.xunlei.tdlive.util.q.a();
        aVar.a("hostid", str);
        q.a("center_page_show", str2, null, aVar.a());
    }
}
