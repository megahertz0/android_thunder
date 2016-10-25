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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.umeng.socialize.media.WeiXinShareContent;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.analytics.b.c;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.control.RoundImageView;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveGetLiveListRequest;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.usercenter.UserCenterActivity;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.d;
import com.xunlei.tdlive.util.q;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.android.agoo.common.AgooConstants;
import org.android.spdy.SpdyProtocol;

// compiled from: SDKLiveListAdapter.java
public class ac extends i<String> implements OnClickListener, OnScrollListener {
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

    // compiled from: SDKLiveListAdapter.java
    class a {
        View a;
        View b;
        TextView c;
        RoundImageView d;
        TextView e;
        TextView f;
        TextView g;
        TextView h;
        TextView i;
        ImageView j;
        Drawable k;
        Drawable l;
        int m;

        a() {
        }
    }

    public ac(String str, long j, com.xunlei.tdlive.a.j.a aVar) {
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
            new XLLiveGetLiveListRequest(com.xunlei.tdlive.util.ac.j(), this.k, f.a().k(), f.a().l(), this.g, 100).send(new ad(this, z2, str));
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
            view = LayoutInflater.from(context).inflate(R.layout.xllive_live_list_item_sdk, viewGroup, false);
            a aVar2 = new a();
            view.setTag(aVar2);
            aVar2.a = view.findViewById(R.id.head);
            aVar2.b = view.findViewById(R.id.xllive_list_mask);
            aVar2.c = (TextView) view.findViewById(R.id.xllive_live_flag);
            aVar2.d = (RoundImageView) view.findViewById(R.id.useravatar);
            aVar2.e = (TextView) view.findViewById(R.id.username);
            aVar2.f = (TextView) view.findViewById(R.id.title);
            aVar2.j = (ImageView) view.findViewById(R.id.playthumb);
            aVar2.g = (TextView) view.findViewById(R.id.count);
            aVar2.h = (TextView) view.findViewById(R.id.audience);
            aVar2.i = (TextView) view.findViewById(R.id.addown);
            aVar2.k = context.getResources().getDrawable(R.drawable.xllive_live_flag);
            aVar2.k.setBounds(0, 0, aVar2.k.getMinimumWidth(), aVar2.k.getMinimumHeight());
            aVar2.l = context.getResources().getDrawable(R.drawable.xllive_live_replay_flag);
            aVar2.l.setBounds(0, 0, aVar2.l.getMinimumWidth(), aVar2.l.getMinimumHeight());
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.m = i;
        JsonWrapper a = a(i);
        if (a != null) {
            String string;
            String string2;
            CharSequence string3;
            a.putInt("position", i);
            int i2 = a.getInt(AgooConstants.MESSAGE_TYPE, 0);
            CharSequence charSequence = BuildConfig.VERSION_NAME;
            if (i2 == 1) {
                string = a.getString(WeiXinShareContent.TYPE_IMAGE, BuildConfig.VERSION_NAME);
                string2 = a.getString("minimg", BuildConfig.VERSION_NAME);
                string3 = a.getString(SetKey.TITLE, BuildConfig.VERSION_NAME);
                charSequence = a.getString("addown", "\u7acb\u5373\u4e0b\u8f7d");
                if (TextUtils.isEmpty(string3)) {
                    string3 = "\u8fc5\u96f7\u76f4\u64ad";
                }
            } else {
                string3 = a.getObject("userinfo", "{}").getString("nickname", a.getString("userid", BuildConfig.VERSION_NAME));
                string = a.getObject("userinfo", "{}").getString("avatar", BuildConfig.VERSION_NAME);
                CharSequence string4 = a.getString(WeiXinShareContent.TYPE_IMAGE, string);
                if (TextUtils.isEmpty(string4)) {
                    string2 = string;
                } else {
                    CharSequence charSequence2 = string4;
                    string2 = string;
                    CharSequence charSequence3 = charSequence2;
                }
            }
            if (i2 == 1) {
                aVar.j.setLayoutParams(new LayoutParams(this.d, (int) (((float) this.d) / 1.8f)));
                aVar.a.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) d.a(viewGroup.getContext(), 45.0f)));
                aVar.e.setTextColor(-3947581);
                aVar.e.setTextSize(10.0f);
                aVar.d.setType(1);
                aVar.d.setBorderRadius(0);
                aVar.d.setLayoutParams(new LinearLayout.LayoutParams((int) d.a(viewGroup.getContext(), 24.0f), (int) d.a(viewGroup.getContext(), 12.0f)));
                if (TextUtils.isEmpty(charSequence)) {
                    aVar.i.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                } else {
                    aVar.i.setVisibility(0);
                    aVar.i.setText(charSequence);
                }
            } else {
                aVar.j.setLayoutParams(new LayoutParams(this.d, this.d));
                aVar.a.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) d.a(viewGroup.getContext(), 45.0f)));
                aVar.e.setTextColor(-13816531);
                aVar.e.setTextSize(12.0f);
                aVar.d.setType(0);
                aVar.d.setBorderRadius(0);
                aVar.d.setLayoutParams(new LinearLayout.LayoutParams((int) d.a(viewGroup.getContext(), 24.0f), (int) d.a(viewGroup.getContext(), 24.0f)));
                aVar.i.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            }
            int i3 = a.getInt("status", 0);
            if (i3 == 1 || i3 == 3) {
                aVar.b.setVisibility(0);
                aVar.c.setVisibility(0);
                aVar.c.setText("\u76f4\u64ad");
                aVar.c.setCompoundDrawables(aVar.k, null, null, null);
                aVar.g.setText(a.getString("onlinenum", "0"));
                aVar.h.setText("\u4eba\u5728\u770b");
            } else if (i3 == 2) {
                aVar.c.setVisibility(0);
                aVar.c.setText("\u56de\u653e");
                aVar.c.setCompoundDrawables(aVar.l, null, null, null);
                aVar.g.setText(a.getString("onlinenum", "0"));
                aVar.h.setText("\u4eba\u770b\u8fc7");
            } else {
                aVar.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                aVar.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                aVar.g.setText(BuildConfig.VERSION_NAME);
                aVar.h.setText(BuildConfig.VERSION_NAME);
            }
            aVar.a.setTag(aVar);
            aVar.e.setText(string3);
            if (i2 == 1) {
                aVar.f.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                com.xunlei.tdlive.util.a.a(viewGroup.getContext()).a(aVar.d, string2);
                com.xunlei.tdlive.util.a.a(viewGroup.getContext()).a(aVar.j, string);
            } else {
                string3 = a.getString(SetKey.TITLE, BuildConfig.VERSION_NAME);
                if (TextUtils.isEmpty(string3)) {
                    aVar.f.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                } else {
                    aVar.f.setText(string3);
                    aVar.f.setVisibility(0);
                }
                com.xunlei.tdlive.util.a.a(viewGroup.getContext()).a(aVar.d, string2, com.xunlei.tdlive.util.a.a(viewGroup.getContext(), R.drawable.xllive_avatar_default));
                com.xunlei.tdlive.util.a.a(viewGroup.getContext()).a(aVar.j, string, com.xunlei.tdlive.util.a.a(viewGroup.getContext(), R.drawable.xllive_img_loding_sdk));
            }
        }
        return view;
    }

    public void onClick(View view) {
        a aVar = (a) view.getTag();
        if (aVar != null) {
            view.setEnabled(false);
            JsonWrapper a = a(aVar.m);
            if (a != null) {
                a(aVar.m, 1);
                UserCenterActivity.a(view.getContext(), a.getString("userid", BuildConfig.VERSION_NAME), a.getObject("userinfo", "{}").getString("nickname", BuildConfig.VERSION_NAME), "homelabel");
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

    public void a(int i, int i2) {
        Map hashMap = new HashMap();
        String str = i2 == 1 ? SocializeProtocolConstants.PROTOCOL_KEY_USER_ICON2 : i2 == 2 ? WeiXinShareContent.TYPE_VIDEO : null;
        JsonWrapper a = a(i);
        if (a != null) {
            hashMap.put("rn", String.valueOf(i));
            hashMap.put("hostid", a.getString("userid", BuildConfig.VERSION_NAME));
            hashMap.put("viewernum", String.valueOf(a.getLong("online", 0)));
            hashMap.put("live", a.getInt("status", -1) == 1 ? "0" : c.f);
            hashMap.put("follow", "0");
        }
        q.a("home_label_click", str, null, hashMap);
    }
}
