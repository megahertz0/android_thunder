package com.xunlei.tdlive.a;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.umeng.socialize.media.WeiXinShareContent;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveGetLiveListRequest;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.usercenter.UserCenterActivity;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.ac;
import com.xunlei.tdlive.util.q;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import org.android.agoo.common.AgooConstants;
import org.android.spdy.SpdyProtocol;

// compiled from: LiveListAdapter.java
public class l extends i<String> implements OnClickListener, OnScrollListener {
    private ArrayList<Long> b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private long i;
    private long j;
    private String k;
    private com.xunlei.tdlive.util.a.a l;
    private com.xunlei.tdlive.util.a.a m;

    // compiled from: LiveListAdapter.java
    class a {
        View a;
        TextView b;
        ImageView c;
        TextView d;
        TextView e;
        TextView f;
        TextView g;
        ImageView h;
        LinearLayout i;
        Drawable j;
        Drawable k;
        int l;

        a() {
        }
    }

    public l(String str, long j, com.xunlei.tdlive.a.j.a aVar) {
        this.b = new ArrayList();
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.j = 0;
        this.i = j;
        this.a = aVar;
        this.k = str;
    }

    public int a() {
        return this.c;
    }

    public int d() {
        return this.h;
    }

    public void a(String str, boolean z, boolean z2) {
        if ((!z2 || SystemClock.uptimeMillis() - this.j >= this.i) && b()) {
            if (this.a != null) {
                this.a.a(str, false, z2);
            }
            if (z2) {
                this.e++;
                this.g = this.e;
            } else {
                this.g = this.f;
            }
            XLog.d("LiveListAdapter", new StringBuilder("load page:").append(this.g).append(", loadmore:").append(z2).append(", mTotalPage:").append(this.e).append(", mCurPage:").append(this.f).toString());
            new XLLiveGetLiveListRequest(ac.j(), this.k, f.a().k(), f.a().l(), this.g, 100).send(new m(this, z2, str));
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (this.d == 0) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) viewGroup.getContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            this.d = displayMetrics.widthPixels;
        }
        if (view == null || view.getTag() == null) {
            Context context = viewGroup.getContext();
            view = LayoutInflater.from(context).inflate(R.layout.xllive_live_list_item, viewGroup, false);
            a aVar2 = new a();
            view.setTag(aVar2);
            aVar2.a = view.findViewById(R.id.head);
            aVar2.b = (TextView) view.findViewById(R.id.xllive_live_flag);
            aVar2.c = (ImageView) view.findViewById(R.id.useravatar);
            aVar2.d = (TextView) view.findViewById(R.id.username);
            aVar2.e = (TextView) view.findViewById(R.id.title);
            aVar2.h = (ImageView) view.findViewById(R.id.playthumb);
            aVar2.f = (TextView) view.findViewById(R.id.count);
            aVar2.g = (TextView) view.findViewById(R.id.audience);
            aVar2.i = (LinearLayout) view.findViewById(R.id.tagcontainer);
            aVar2.h.setLayoutParams(new LayoutParams(this.d, this.d));
            aVar2.j = context.getResources().getDrawable(R.drawable.xllive_live_flag);
            aVar2.j.setBounds(0, 0, aVar2.j.getMinimumWidth(), aVar2.j.getMinimumHeight());
            aVar2.k = context.getResources().getDrawable(R.drawable.xllive_live_replay_flag);
            aVar2.k.setBounds(0, 0, aVar2.k.getMinimumWidth(), aVar2.k.getMinimumHeight());
            aVar2.a.setOnClickListener(this);
            Drawable drawable = context.getResources().getDrawable(R.drawable.xllive_replay_flag);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            aVar2.b.setTag(drawable);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.l = i;
        JsonWrapper a = a(i);
        if (a != null) {
            a.putInt("position", i);
            int i2 = a.getInt("status", 0);
            if (i2 == 1 || i2 == 3) {
                aVar.b.setVisibility(0);
                aVar.b.setText("\u76f4\u64ad");
                aVar.b.setCompoundDrawables(aVar.j, null, null, null);
                aVar.f.setText(a.getString("onlinenum", "0"));
                aVar.g.setText("\u4eba\u5728\u770b");
            } else if (i2 == 2) {
                aVar.b.setVisibility(0);
                aVar.b.setText("\u56de\u653e");
                aVar.b.setCompoundDrawables(aVar.k, null, null, null);
                aVar.f.setText(a.getString("onlinenum", "0"));
                aVar.g.setText("\u4eba\u770b\u8fc7");
            } else {
                aVar.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                aVar.f.setText(BuildConfig.VERSION_NAME);
                aVar.g.setText(BuildConfig.VERSION_NAME);
            }
            aVar.a.setTag(aVar);
            aVar.d.setText(a.getObject("userinfo", "{}").getString("nickname", a.getString("userid", BuildConfig.VERSION_NAME)));
            CharSequence string = a.getString(SetKey.TITLE, BuildConfig.VERSION_NAME);
            if (TextUtils.isEmpty(string)) {
                aVar.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                aVar.e.setText(string);
                aVar.e.setVisibility(0);
            }
            String string2 = a.getObject("userinfo", "{}").getString("avatar", BuildConfig.VERSION_NAME);
            String string3 = a.getString(WeiXinShareContent.TYPE_IMAGE, string2);
            if (TextUtils.isEmpty(string3)) {
                string3 = string2;
            }
            if (this.l == null) {
                this.l = com.xunlei.tdlive.util.a.a(viewGroup.getContext(), R.drawable.xllive_avatar_default);
            }
            if (this.m == null) {
                this.m = com.xunlei.tdlive.util.a.a(viewGroup.getContext(), R.drawable.xllive_img_loding);
            }
            com.xunlei.tdlive.util.a.a(viewGroup.getContext()).a(aVar.c, string2, this.l);
            com.xunlei.tdlive.util.a.a(viewGroup.getContext()).a(aVar.h, string3, this.m);
            aVar.i.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
        return view;
    }

    public void onClick(View view) {
        a aVar = (a) view.getTag();
        if (aVar != null) {
            view.setEnabled(false);
            JsonWrapper a = a(aVar.l);
            if (a != null) {
                UserCenterActivity.a(view.getContext(), a.getString("userid", BuildConfig.VERSION_NAME), a.getObject("userinfo", "{}").getString("nickname", BuildConfig.VERSION_NAME), "homelabel");
                q.e("home_label_click").a(SocializeProtocolConstants.PROTOCOL_KEY_USER_ICON2).a("viewid", q.e("zb_content_read").d("viewid")).a("roomid", a.getString("roomid", BuildConfig.VERSION_NAME)).a("hostid", a.getString("userid", BuildConfig.VERSION_NAME)).a("viewernum", a.getInt("onlinenum", 0)).a("rn", a.getInt("position", 0)).a("grayid", a()).a("hosttype", a.getObject("seq2", "{}").getInt("hot_level", 0)).a("follow", a.getObject("seq2", "{}").getInt("is_follow", 0)).a("recommend", a.getObject("seq2", "{}").getInt("is_recommend", 0)).a("sign", a.getObject("seq2", "{}").getInt("is_sign", 0)).a("isdl", a.getInt(AgooConstants.MESSAGE_TYPE, 0)).a("livestat", a.getInt("status", 0) == 2 ? "replay" : "live").b(new String[0]);
            }
            view.setEnabled(true);
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        this.h = i;
        if (this.h == 0 && this.f < this.b.size()) {
            if (SystemClock.uptimeMillis() - ((Long) this.b.get(this.f)).longValue() >= this.i) {
                XLog.d("LiveListAdapter", new StringBuilder("Page:").append(this.f).append(" dead line").toString());
                a(BuildConfig.VERSION_NAME);
            }
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.h > 0) {
            this.f = i / 100;
            if (i3 - i <= i2 * 5) {
                c(null);
            }
        }
    }
}
