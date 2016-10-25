package com.xunlei.tdlive.a;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.downloadprovider.member.payment.external.PayBaseConstants;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.LevelInfo;
import com.xunlei.tdlive.protocol.XLLiveGetContributRankRequest;
import com.xunlei.tdlive.protocol.XLLiveGetTopRankRequest;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.usercenter.e;
import com.xunlei.tdlive.util.d;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import com.xunlei.xiazaibao.BuildConfig;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: RankListAdapter.java
public class s extends i<String> implements OnClickListener {
    private String b;
    private int c;
    private String d;
    private boolean e;

    // compiled from: RankListAdapter.java
    private static class b {
        TextView d;
        ImageView e;
        ImageView f;
        TextView g;
        TextView h;
        TextView i;
        int j;

        private b() {
        }
    }

    // compiled from: RankListAdapter.java
    private static final class a extends b {
        ImageView a;
        View b;
        View c;

        private a() {
            super();
        }
    }

    public s(int i, String str, String str2, com.xunlei.tdlive.a.j.a aVar) {
        this.e = true;
        this.c = i;
        this.d = str;
        this.b = str2;
        this.a = aVar;
    }

    public void a(String str, boolean z, boolean z2) {
        try {
            this.e = f.a().b();
        } catch (Exception e) {
        }
        if (b()) {
            if (this.a != null) {
                this.a.a(str, false, z2);
            }
            t tVar = new t(this, str);
            String k = f.a().k();
            String l = f.a().l();
            if (this.c == 0) {
                new XLLiveGetTopRankRequest(k, l, this.d).send(tVar);
            } else {
                new XLLiveGetContributRankRequest(k, l, this.b, this.d).send(tVar);
            }
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        b bVar;
        int i2;
        int itemViewType = getItemViewType(i);
        JsonWrapper a = a(i);
        Context context = viewGroup.getContext();
        int[] iArr = new int[]{R.drawable.xllive_rank1, R.drawable.xllive_rank2, R.drawable.xllive_rank3};
        int[] iArr2 = new int[]{R.drawable.xllive_rank_item1, R.drawable.xllive_rank_item2, R.drawable.xllive_rank_item3};
        int[] iArr3 = new int[]{-199990, -918792, -592405};
        int[] iArr4 = new int[]{-69184, -918792, -592405};
        int[] iArr5 = new int[]{R.drawable.xllive_rank_frame1, R.drawable.xllive_rank_frame2, R.drawable.xllive_rank_frame3};
        int[] iArr6 = new int[]{R.drawable.xllive_rank_cover1, R.drawable.xllive_rank_cover2, R.drawable.xllive_rank_cover3};
        if (view == null || view.getTag() == null) {
            View inflate;
            b aVar;
            if (itemViewType == 0) {
                inflate = LayoutInflater.from(context).inflate(R.layout.xllive_contribute_rank_top_item, viewGroup, false);
                aVar = new a();
                aVar.a = (ImageView) inflate.findViewById(R.id.avatar_frame);
                aVar.b = inflate.findViewById(R.id.bkg);
                aVar.c = inflate.findViewById(R.id.mask);
                inflate.setTag(aVar);
            } else {
                View inflate2 = LayoutInflater.from(context).inflate(R.layout.xllive_rank_list_item, viewGroup, false);
                b bVar2 = new b();
                inflate2.setTag(bVar2);
                inflate = inflate2;
                aVar = bVar2;
            }
            aVar.d = (TextView) inflate.findViewById(R.id.order);
            aVar.e = (ImageView) inflate.findViewById(R.id.order_item);
            aVar.f = (ImageView) inflate.findViewById(R.id.useravatar);
            aVar.g = (TextView) inflate.findViewById(R.id.nickname);
            aVar.h = (TextView) inflate.findViewById(R.id.sign);
            aVar.i = (TextView) inflate.findViewById(R.id.flow);
            bVar = aVar;
            view = inflate;
        } else {
            bVar = (b) view.getTag();
        }
        bVar.j = i;
        if (itemViewType == 1) {
            if (i < 3) {
                bVar.e.setImageDrawable(context.getResources().getDrawable(iArr2[i]));
            } else {
                bVar.e.setImageBitmap(null);
            }
            if (i < 3) {
                i2 = iArr3[i];
                inflate2 = view;
            } else if (i % 2 == 0) {
                i2 = -1;
                inflate2 = view;
            } else {
                i2 = -263173;
                inflate2 = view;
            }
            inflate2.setBackgroundColor(i2);
        }
        if (a != null) {
            if (itemViewType == 1) {
                LayoutParams layoutParams = (LayoutParams) bVar.f.getLayoutParams();
                int a2;
                if (i < 3) {
                    bVar.d.setText(BuildConfig.VERSION_NAME);
                    try {
                        bVar.d.setBackground(context.getResources().getDrawable(iArr[i]));
                    } catch (NoSuchMethodError e) {
                        try {
                            bVar.d.setBackgroundDrawable(context.getResources().getDrawable(iArr[i]));
                        } catch (Exception e2) {
                        }
                    }
                    a2 = (int) d.a(context, 69.0f);
                    layoutParams.width = a2;
                    layoutParams.height = a2;
                    layoutParams.rightMargin = 0;
                    layoutParams.topMargin = (int) d.a(context, 3.0f);
                    layoutParams.bottomMargin = (int) d.a(context, 3.0f);
                } else {
                    bVar.d.setText((i + 1));
                    try {
                        bVar.d.setBackground(null);
                    } catch (NoSuchMethodError e3) {
                        try {
                            bVar.d.setBackgroundDrawable(null);
                        } catch (Exception e4) {
                        }
                    }
                    a2 = (int) d.a(context, 36.0f);
                    layoutParams.width = a2;
                    layoutParams.height = a2;
                    layoutParams.rightMargin = (int) d.a(context, 15.0f);
                    layoutParams.topMargin = 0;
                    layoutParams.bottomMargin = 0;
                }
                bVar.f.setLayoutParams(layoutParams);
            } else {
                i2 = 0;
                if (i == 0) {
                    i2 = (int) d.a(context, 128.0f);
                } else if (i == 1 || i == 2) {
                    i2 = (int) d.a(context, 105.0f);
                }
                view.setLayoutParams(new AbsListView.LayoutParams(-1, i2));
                ((a) bVar).e.setLayoutParams(new RelativeLayout.LayoutParams(i2, i2));
            }
            if (this.c == 0) {
                if (this.d.equals(PayBaseConstants.PAY_DAY)) {
                    bVar.h.setText(new StringBuilder("\u672c\u65e5").append(a.getString("total_point", "0")).append("\u4eba\u6c14").toString());
                } else if (this.d.equals("week")) {
                    bVar.h.setText(new StringBuilder("\u672c\u5468").append(a.getString("total_point", "0")).append("\u4eba\u6c14").toString());
                } else {
                    bVar.h.setText(new StringBuilder("\u672c\u6708").append(a.getString("total_point", "0")).append("\u4eba\u6c14").toString());
                }
            } else if (this.d.equals(PayBaseConstants.PAY_DAY)) {
                bVar.h.setText(new StringBuilder("\u672c\u65e5\u8d21\u732e ").append(a.getString("costnum", "0")).append("\u4eba\u6c14").toString());
            } else if (this.d.equals("week")) {
                bVar.h.setText(new StringBuilder("\u672c\u5468\u8d21\u732e  ").append(a.getString("costnum", "0")).append("\u4eba\u6c14").toString());
            } else {
                bVar.h.setText(new StringBuilder("\u603b\u8d21\u732e  ").append(a.getString("costnum", "0")).append("\u4eba\u6c14").toString());
            }
            LevelInfo levelInfo = new LevelInfo();
            levelInfo.icon = a.getObject("user_info", "{}").getObject("level", "{}").getString(SocializeProtocolConstants.PROTOCOL_KEY_USER_ICON2, BuildConfig.VERSION_NAME);
            levelInfo.icon2 = a.getObject("user_info", "{}").getObject("level", "{}").getString("icon2", BuildConfig.VERSION_NAME);
            levelInfo.title = a.getObject("user_info", "{}").getObject("level", "{}").getString(SetKey.TITLE, BuildConfig.VERSION_NAME);
            levelInfo.current = a.getObject("user_info", "{}").getObject("level", "{}").getInt("current", 0);
            CharSequence string = a.getObject("user_info", "{}").getString("nickname", BuildConfig.VERSION_NAME);
            String string2 = a.getObject("user_info", "{}").getString("avatar", BuildConfig.VERSION_NAME);
            bVar.g.setText(string);
            com.xunlei.tdlive.util.a.a(viewGroup.getContext()).a(bVar.g, levelInfo.getIconFullPath());
            com.xunlei.tdlive.util.a.a(viewGroup.getContext()).a(bVar.f, string2);
            String k = f.a().k();
            if (this.c != 0 || k.equals(a.getObject("user_info", "{}").getString("userid", BuildConfig.VERSION_NAME))) {
                bVar.i.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
            } else {
                boolean z = a.getObject("user_info", "{}").getInt("is_follow", 0) != 0;
                String string3 = a.getObject("user_info", "{}").getString("userid", BuildConfig.VERSION_NAME);
                TextView textView = bVar.i;
                bVar.i.setVisibility(0);
                bVar.i.setOnClickListener(new e(z, string3, 7, "attention_ranklis", new u(this, textView)));
                bVar.i.setTag(bVar);
                bVar.i.setSelected(z);
                TextView textView2 = bVar.i;
                if (z) {
                    i2 = R.string.followed;
                } else {
                    i2 = R.string.follow;
                }
                textView2.setText(i2);
            }
            if (itemViewType == 0) {
                a aVar2 = (a) bVar;
                if (i < 3) {
                    aVar2.a.setImageResource(iArr5[i]);
                    aVar2.b.setBackgroundColor(iArr4[i]);
                }
                FrameLayout.LayoutParams layoutParams2;
                int a3;
                if (i == 0) {
                    bVar.h.setBackgroundResource(R.drawable.xllive_rank_bkg);
                    bVar.h.setTextColor(-1);
                    layoutParams2 = (FrameLayout.LayoutParams) aVar2.a.getLayoutParams();
                    layoutParams2.width = (int) d.a(context, 100.0f);
                    layoutParams2.height = (int) d.a(context, 92.0f);
                    layoutParams2 = (FrameLayout.LayoutParams) aVar2.f.getLayoutParams();
                    a3 = (int) d.a(context, 62.0f);
                    layoutParams2.height = a3;
                    layoutParams2.width = a3;
                } else if (i == 1 || i == 2) {
                    bVar.h.setBackgroundColor(0);
                    layoutParams2 = (FrameLayout.LayoutParams) aVar2.a.getLayoutParams();
                    layoutParams2.width = (int) d.a(context, 90.0f);
                    layoutParams2.height = (int) d.a(context, 62.0f);
                    layoutParams2 = (FrameLayout.LayoutParams) aVar2.f.getLayoutParams();
                    a3 = (int) d.a(context, 46.0f);
                    layoutParams2.height = a3;
                    layoutParams2.width = a3;
                    ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#fc966d"));
                    ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(-16777216);
                    String toString = bVar.h.getText().toString();
                    CharSequence spannableStringBuilder = new SpannableStringBuilder(toString);
                    spannableStringBuilder.setSpan(foregroundColorSpan2, 0, SimpleLog.LOG_LEVEL_DEBUG, com.xunlei.xllib.R.styleable.AppCompatTheme_actionModeCopyDrawable);
                    spannableStringBuilder.setSpan(foregroundColorSpan, SimpleLog.LOG_LEVEL_DEBUG, toString.length() - 2, com.xunlei.xllib.R.styleable.AppCompatTheme_actionModeCopyDrawable);
                    spannableStringBuilder.setSpan(foregroundColorSpan2, toString.length() - 2, toString.length(), com.xunlei.xllib.R.styleable.AppCompatTheme_actionModeCopyDrawable);
                    bVar.h.setText(spannableStringBuilder);
                }
                if (i < 3) {
                    aVar2.c.setBackgroundResource(iArr6[i]);
                }
                com.xunlei.tdlive.util.a.a(viewGroup.getContext()).a(bVar.e, string2);
            }
        }
        return view;
    }

    public int getViewTypeCount() {
        return SimpleLog.LOG_LEVEL_DEBUG;
    }

    public int getItemViewType(int i) {
        return (i >= 3 || this.c != 0) ? 1 : 0;
    }

    public void onClick(View view) {
        f.a().a(view.getContext(), "attention_ranklist", new v(this, view));
    }
}
