package com.xunlei.downloadprovider.personal.playrecord.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.e;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.XLUserUtil;
import com.xunlei.common.yunbo.XLYB_INITDATA;
import com.xunlei.common.yunbo.XLYB_VODINFO;
import com.xunlei.common.yunbo.XLYunboListener;
import com.xunlei.common.yunbo.XLYunboUtil;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.cloudlist.CloudListBTFileActivity;
import com.xunlei.downloadprovider.cloudlist.CommixturePlayRecord;
import com.xunlei.downloadprovider.cloudlist.CommixturePlayRecord.LastPlayTime;
import com.xunlei.downloadprovider.cloudlist.CommixturePlayRecord.RecodeType;
import com.xunlei.downloadprovider.commonview.ErrorView;
import com.xunlei.downloadprovider.commonview.UnifiedLoadingView;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.commonview.dialog.q;
import com.xunlei.downloadprovider.commonview.dialog.x;
import com.xunlei.downloadprovider.homepage.a.a.d;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.payment.external.PayEntryParam;
import com.xunlei.downloadprovider.member.payment.external.PayFrom;
import com.xunlei.downloadprovider.member.payment.external.PaymentEntryActivity;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.personal.playrecord.PlayRecordFragment$OntainState;
import com.xunlei.downloadprovider.personal.playrecord.PlayRecordFragment$a;
import com.xunlei.downloadprovider.personal.playrecord.PlayRecordFragment$b;
import com.xunlei.downloadprovider.personal.playrecord.PlayRecordFragment$c;
import com.xunlei.downloadprovider.url.DownData;
import com.xunlei.downloadprovider.util.b.c;
import com.xunlei.downloadprovider.util.b.f;
import com.xunlei.downloadprovider.vod.VodUtil;
import com.xunlei.downloadprovider.vod.protocol.VodSourceType;
import com.xunlei.downloadprovider.vod.protocol.VodVideoFormat;
import com.xunlei.downloadprovider.vod.protocol.i;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

public class PlayRecordListWidget extends RelativeLayout implements OnClickListener {
    private static x U;
    private UnifiedLoadingView A;
    private PlayRecordFragment$a B;
    private OnClickListener C;
    private int D;
    private com.xunlei.downloadprovider.a.h.a E;
    private com.xunlei.downloadprovider.a.h.b F;
    private final XLYunboListener G;
    private final e<ListView> H;
    private boolean I;
    private final com.handmark.pulltorefresh.library.PullToRefreshBase.a J;
    private final f K;
    private int L;
    private q M;
    private ImageView N;
    private TextView O;
    private TextView P;
    private TextView Q;
    private TextView R;
    private View S;
    private View T;
    private Object V;
    public List<CommixturePlayRecord> a;
    public final Set<String> b;
    public final Set<String> c;
    public final Set<String> d;
    public boolean e;
    public boolean f;
    public PlayRecordFragment$b g;
    public boolean h;
    public Set<String> i;
    private final String j;
    private final b k;
    private final int l;
    private Object m;
    private Object n;
    private boolean o;
    private boolean p;
    private PlayRecordFragment$OntainState q;
    private ErrorView r;
    private View s;
    private View t;
    private Button u;
    private TextView v;
    private TextView w;
    private TextView x;
    private ImageView y;
    private PullToRefreshListView z;

    static /* synthetic */ class AnonymousClass_1 {
        public static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;
        static final /* synthetic */ int[] c;

        static {
            c = new int[LastPlayTime.values().length];
            try {
                c[LastPlayTime.today.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                c[LastPlayTime.yesterday.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                c[LastPlayTime.threedaysago.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            b = new int[PlayRecordFragment$OntainState.values().length];
            try {
                b[PlayRecordFragment$OntainState.obtaining.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                b[PlayRecordFragment$OntainState.refreshing.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                b[PlayRecordFragment$OntainState.auto_refreshing.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            a = new int[RecodeType.values().length];
            try {
                a[RecodeType.vodInfo.ordinal()] = 1;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[RecodeType.playRecord.ordinal()] = 2;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    static class a implements Comparator<CommixturePlayRecord> {
        a() {
        }

        public final /* synthetic */ int compare(Object obj, Object obj2) {
            CommixturePlayRecord commixturePlayRecord = (CommixturePlayRecord) obj2;
            long a = ((CommixturePlayRecord) obj).a();
            long a2 = commixturePlayRecord.a();
            if (a < a2) {
                return 1;
            }
            return a > a2 ? -1 : 0;
        }
    }

    private final class b extends BaseAdapter {
        private int b;
        private int c;

        private b() {
            this.b = g.a(BrothersApplication.a().getApplicationContext(), 80.0f);
            this.c = com.xunlei.downloadprovider.a.b.u() - g.a(BrothersApplication.a().getApplicationContext(), 92.0f);
        }

        public final /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public final View getView(int i, View view, ViewGroup viewGroup) {
            PlayRecordFragment$c playRecordFragment$c;
            LastPlayTime lastPlayTime;
            int i2;
            String str;
            boolean a;
            CharSequence d;
            TextView textView;
            BrothersApplication a2;
            if (view == null || view.getTag() == null) {
                view = LayoutInflater.from(BrothersApplication.a().getApplicationContext()).inflate(R.layout.cloud_list_item, null);
                PlayRecordFragment$c playRecordFragment$c2 = new PlayRecordFragment$c();
                playRecordFragment$c2.e = view.findViewById(R.id.cloud_list_item_btn_right);
                playRecordFragment$c2.f = (TextView) view.findViewById(R.id.cloud_list_item_btn_right_text);
                playRecordFragment$c2.b = (ImageView) view.findViewById(R.id.cloud_list_item_icon);
                playRecordFragment$c2.d = (TextView) view.findViewById(R.id.cloud_list_item_filesize);
                playRecordFragment$c2.c = (TextView) view.findViewById(R.id.cloud_list_item_name);
                PlayRecordListWidget.this = view.findViewById(R.id.cloud_list_item_layout);
                playRecordFragment$c2.g = view.findViewById(R.id.cloud_list_item_btn_select);
                playRecordFragment$c2.h = (ImageView) view.findViewById(R.id.cloud_list_item_btn_select_icon);
                playRecordFragment$c2.i = view.findViewById(R.id.cloud_list_item_head);
                playRecordFragment$c2.j = (RelativeLayout) view.findViewById(R.id.rl_cloud_title_layout);
                playRecordFragment$c2.k = (TextView) view.findViewById(com.xunlei.xllib.R.id.title);
                playRecordFragment$c2.l = view.findViewById(R.id.view_0);
                playRecordFragment$c2.n = view.findViewById(R.id.view_1);
                playRecordFragment$c2.m = (ImageView) view.findViewById(com.xunlei.xllib.R.id.image);
                playRecordFragment$c2.o = (TextView) view.findViewById(R.id.divider_line);
                view.setTag(playRecordFragment$c2);
                playRecordFragment$c = playRecordFragment$c2;
            } else {
                playRecordFragment$c = (PlayRecordFragment$c) view.getTag();
            }
            CommixturePlayRecord a3 = a(i);
            int[] iArr = AnonymousClass_1.c;
            switch (AnonymousClass_1.a[a3.c.ordinal()]) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    if ("0".equals(PlayRecordListWidget.this.playtime) || BuildConfig.VERSION_NAME.equals(PlayRecordListWidget.this.playtime)) {
                        lastPlayTime = LastPlayTime.threedaysago;
                    } else {
                        lastPlayTime = CommixturePlayRecord.a(CommixturePlayRecord.a(PlayRecordListWidget.this.playtime));
                    }
                    break;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    if (0 != a3.b.c) {
                        lastPlayTime = CommixturePlayRecord.a(a3.b.c);
                    }
                    lastPlayTime = LastPlayTime.threedaysago;
                    break;
                default:
                    lastPlayTime = LastPlayTime.threedaysago;
                    break;
            }
            switch (iArr[lastPlayTime.ordinal()]) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    a3.d = "\u4eca\u5929";
                    break;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    a3.d = "\u6628\u5929";
                    break;
                case MqttConnectOptions.MQTT_VERSION_3_1:
                    a3.d = "3\u5929\u524d";
                    break;
            }
            if (i == 0) {
                playRecordFragment$c.o.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            }
            if (i != 0) {
                if (i < 0) {
                    i2 = 0;
                } else {
                    CommixturePlayRecord a4 = a(i);
                    CommixturePlayRecord a5 = a(i - 1);
                    if (a4 == null || a4 == null) {
                        i2 = 0;
                    } else {
                        String str2 = a4.d;
                        str = a5.d;
                        if (str == null || str2 == null) {
                            i2 = 0;
                        } else if (str2.equals(str)) {
                            i2 = 0;
                        }
                    }
                }
                if (i2 == 0) {
                    playRecordFragment$c.o.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    playRecordFragment$c.j.setVisibility(0);
                    playRecordFragment$c.l.setVisibility(0);
                    playRecordFragment$c.n.setVisibility(0);
                    playRecordFragment$c.m.setVisibility(0);
                    playRecordFragment$c.k.setVisibility(0);
                    playRecordFragment$c.k.setText(a3.d);
                } else {
                    playRecordFragment$c.o.setVisibility(0);
                    playRecordFragment$c.j.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    playRecordFragment$c.l.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    playRecordFragment$c.n.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    playRecordFragment$c.m.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    playRecordFragment$c.k.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                }
                if (a3 != null) {
                    PlayRecordListWidget.this.setBackgroundResource(R.drawable.cloud_list_item_bg_selector);
                    switch (AnonymousClass_1.a[a3.c.ordinal()]) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (PlayRecordListWidget.b(PlayRecordListWidget.this.src_url)) {
                                playRecordFragment$c.a();
                            } else {
                                playRecordFragment$c.a(BrothersApplication.a().getString(R.string.cloud_list_btn_open), -1);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            if (a3.b() != 0 || "shortVideo".equals(a3.c())) {
                                playRecordFragment$c.a(BrothersApplication.a().getString(R.string.cloud_list_btn_play), R.drawable.download_item_play_icon);
                            } else {
                                playRecordFragment$c.a();
                            }
                            break;
                    }
                }
                if (PlayRecordListWidget.this.e) {
                    playRecordFragment$c.e.setVisibility(0);
                    playRecordFragment$c.g.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    playRecordFragment$c.h.setImageBitmap(null);
                } else {
                    playRecordFragment$c.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    playRecordFragment$c.g.setVisibility(0);
                    if (a3 != null) {
                        switch (AnonymousClass_1.a[a3.c.ordinal()]) {
                            case SimpleLog.LOG_LEVEL_TRACE:
                                a = PlayRecordListWidget.a(PlayRecordListWidget.this, PlayRecordListWidget.this.src_url, PlayRecordListWidget.this.url_hash);
                                break;
                            case SimpleLog.LOG_LEVEL_DEBUG:
                                a = PlayRecordListWidget.this.d.contains(String.valueOf(PlayRecordListWidget.this));
                                break;
                            default:
                                a = false;
                                break;
                        }
                        playRecordFragment$c.h.setImageResource(a ? R.drawable.big_selected : R.drawable.big_unselected);
                    }
                }
                if (a3 != null) {
                    d = a3.d();
                    try {
                        d = com.xunlei.downloadprovider.util.c.a.d(d);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e2) {
                        e2.printStackTrace();
                    }
                    playRecordFragment$c.c.setText(d);
                    playRecordFragment$c.c.invalidate();
                    playRecordFragment$c.c.requestLayout();
                    switch (AnonymousClass_1.a[a3.c.ordinal()]) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (PlayRecordListWidget.b(PlayRecordListWidget.this.src_url)) {
                                PlayRecordListWidget.b(a3, playRecordFragment$c.b);
                            } else {
                                playRecordFragment$c.b.setImageResource(com.xunlei.downloadprovidershare.R.drawable.ic_dl_bt);
                            }
                            if (a3.e() <= 0) {
                                textView = playRecordFragment$c.d;
                                d = com.xunlei.downloadprovider.d.a.a(a3.e(), SimpleLog.LOG_LEVEL_DEBUG);
                            } else {
                                playRecordFragment$c.d.setText(R.string.cloud_list_unknow_size);
                            }
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            PlayRecordListWidget.b(a3, playRecordFragment$c.b);
                            if (a3.b.l == a3.b.k || a3.b.k <= 0) {
                                textView = playRecordFragment$c.d;
                                long j = (long) a3.b.k;
                                a2 = BrothersApplication.a();
                                str = DateUtils.formatElapsedTime(j / 1000);
                                int parseInt = Integer.parseInt(str.substring(str.length() - 1, str.length()));
                                if (str.startsWith("00:0") || parseInt > 0) {
                                    d = a2.getString(R.string.pr_last_see) + " " + str;
                                } else {
                                    d = a2.getString(R.string.pr_less_one_min);
                                }
                            } else {
                                playRecordFragment$c.d.setText(R.string.pr_replay);
                            }
                            break;
                    }
                    textView.setText(d);
                }
                if (a3 != null) {
                    OnClickListener nVar = new n(this, a3, i);
                    OnLongClickListener oVar = new o(this, a3);
                    playRecordFragment$c.e.setOnClickListener(nVar);
                    PlayRecordListWidget.this.setOnClickListener(nVar);
                    PlayRecordListWidget.this.setOnLongClickListener(oVar);
                }
                return view;
            }
            Object obj = 1;
            if (i2 == 0) {
                playRecordFragment$c.o.setVisibility(0);
                playRecordFragment$c.j.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                playRecordFragment$c.l.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                playRecordFragment$c.n.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                playRecordFragment$c.m.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                playRecordFragment$c.k.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                playRecordFragment$c.o.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                playRecordFragment$c.j.setVisibility(0);
                playRecordFragment$c.l.setVisibility(0);
                playRecordFragment$c.n.setVisibility(0);
                playRecordFragment$c.m.setVisibility(0);
                playRecordFragment$c.k.setVisibility(0);
                playRecordFragment$c.k.setText(a3.d);
            }
            if (a3 != null) {
                PlayRecordListWidget.this.setBackgroundResource(R.drawable.cloud_list_item_bg_selector);
                switch (AnonymousClass_1.a[a3.c.ordinal()]) {
                    case SimpleLog.LOG_LEVEL_TRACE:
                        if (PlayRecordListWidget.b(PlayRecordListWidget.this.src_url)) {
                            playRecordFragment$c.a();
                        } else {
                            playRecordFragment$c.a(BrothersApplication.a().getString(R.string.cloud_list_btn_open), -1);
                        }
                        break;
                    case SimpleLog.LOG_LEVEL_DEBUG:
                        if (a3.b() != 0) {
                        }
                        playRecordFragment$c.a(BrothersApplication.a().getString(R.string.cloud_list_btn_play), R.drawable.download_item_play_icon);
                        break;
                }
            }
            if (PlayRecordListWidget.this.e) {
                playRecordFragment$c.e.setVisibility(0);
                playRecordFragment$c.g.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                playRecordFragment$c.h.setImageBitmap(null);
            } else {
                playRecordFragment$c.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                playRecordFragment$c.g.setVisibility(0);
                if (a3 != null) {
                    switch (AnonymousClass_1.a[a3.c.ordinal()]) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            a = PlayRecordListWidget.a(PlayRecordListWidget.this, PlayRecordListWidget.this.src_url, PlayRecordListWidget.this.url_hash);
                            break;
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            a = PlayRecordListWidget.this.d.contains(String.valueOf(PlayRecordListWidget.this));
                            break;
                        default:
                            a = false;
                            break;
                    }
                    if (a) {
                    }
                    playRecordFragment$c.h.setImageResource(a ? R.drawable.big_selected : R.drawable.big_unselected);
                }
            }
            if (a3 != null) {
                d = a3.d();
                d = com.xunlei.downloadprovider.util.c.a.d(d);
                playRecordFragment$c.c.setText(d);
                playRecordFragment$c.c.invalidate();
                playRecordFragment$c.c.requestLayout();
                switch (AnonymousClass_1.a[a3.c.ordinal()]) {
                    case SimpleLog.LOG_LEVEL_TRACE:
                        if (PlayRecordListWidget.b(PlayRecordListWidget.this.src_url)) {
                            PlayRecordListWidget.b(a3, playRecordFragment$c.b);
                        } else {
                            playRecordFragment$c.b.setImageResource(com.xunlei.downloadprovidershare.R.drawable.ic_dl_bt);
                        }
                        if (a3.e() <= 0) {
                            playRecordFragment$c.d.setText(R.string.cloud_list_unknow_size);
                        } else {
                            textView = playRecordFragment$c.d;
                            d = com.xunlei.downloadprovider.d.a.a(a3.e(), SimpleLog.LOG_LEVEL_DEBUG);
                        }
                        break;
                    case SimpleLog.LOG_LEVEL_DEBUG:
                        PlayRecordListWidget.b(a3, playRecordFragment$c.b);
                        if (a3.b.l == a3.b.k) {
                        }
                        textView = playRecordFragment$c.d;
                        long j2 = (long) a3.b.k;
                        a2 = BrothersApplication.a();
                        str = DateUtils.formatElapsedTime(j2 / 1000);
                        int parseInt2 = Integer.parseInt(str.substring(str.length() - 1, str.length()));
                        if (str.startsWith("00:0")) {
                        }
                        d = a2.getString(R.string.pr_last_see) + " " + str;
                        break;
                }
                textView.setText(d);
            }
            if (a3 != null) {
                OnClickListener nVar2 = new n(this, a3, i);
                OnLongClickListener oVar2 = new o(this, a3);
                playRecordFragment$c.e.setOnClickListener(nVar2);
                PlayRecordListWidget.this.setOnClickListener(nVar2);
                PlayRecordListWidget.this.setOnLongClickListener(oVar2);
            }
            return view;
        }

        public final long getItemId(int i) {
            return 0;
        }

        private CommixturePlayRecord a(int i) {
            return (i < 0 || i >= PlayRecordListWidget.this.size()) ? null : (CommixturePlayRecord) PlayRecordListWidget.this.get(i);
        }

        public final int getCount() {
            return PlayRecordListWidget.this.size();
        }
    }

    static /* synthetic */ int a(PlayRecordListWidget playRecordListWidget, int i) {
        int i2 = playRecordListWidget.D + i;
        playRecordListWidget.D = i2;
        return i2;
    }

    static /* synthetic */ void d(PlayRecordListWidget playRecordListWidget, CommixturePlayRecord commixturePlayRecord) {
        String str = null;
        if (playRecordListWidget.g != null && commixturePlayRecord != null) {
            StatReporter.reportOverallDownload("space_tongbu");
            switch (AnonymousClass_1.a[commixturePlayRecord.c.ordinal()]) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    Context context = playRecordListWidget.getContext();
                    XLYB_VODINFO xlyb_vodinfo = commixturePlayRecord.a;
                    Object obj = xlyb_vodinfo.src_url;
                    if (!TextUtils.isEmpty(obj)) {
                        try {
                            str = com.xunlei.downloadprovider.util.c.a.e(obj);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        } catch (IllegalArgumentException e2) {
                            e2.printStackTrace();
                        }
                        if (b(str)) {
                            str = com.xunlei.downloadprovider.util.c.a.c(str);
                        }
                        if (com.xunlei.xllib.a.b.a(context) && !TextUtils.isEmpty(str)) {
                            DownData downData = new DownData();
                            downData.a = xlyb_vodinfo.filename;
                            downData.c = xlyb_vodinfo.cid;
                            downData.d = xlyb_vodinfo.gcid;
                            downData.e = str;
                            downData.b = str;
                            com.xunlei.downloadprovider.model.g gVar = new com.xunlei.downloadprovider.model.g(29, downData.e, downData.s);
                            gVar.d = "space/space_tongbu";
                            if (playRecordListWidget.g != null) {
                                playRecordListWidget.g.a(downData, gVar);
                            }
                        }
                    }
                case SimpleLog.LOG_LEVEL_DEBUG:
                    Context context2 = playRecordListWidget.getContext();
                    com.xunlei.downloadprovider.vod.b.b.a aVar = commixturePlayRecord.b;
                    Object obj2 = BuildConfig.VERSION_NAME;
                    if (!TextUtils.isEmpty(aVar.n)) {
                        obj2 = aVar.n;
                    } else if (!TextUtils.isEmpty(aVar.g)) {
                        obj2 = aVar.g;
                    }
                    if (TextUtils.isEmpty(obj2)) {
                        XLToast.a(context2, XLToastType.XLTOAST_TYPE_ALARM, "\u6682\u4e0d\u80fd\u4e0b\u8f7d");
                        return;
                    }
                    String e3;
                    try {
                        e3 = com.xunlei.downloadprovider.util.c.a.e(obj2);
                    } catch (UnsupportedEncodingException e4) {
                        e4.printStackTrace();
                        e3 = null;
                    } catch (IllegalArgumentException e5) {
                        e5.printStackTrace();
                        e3 = null;
                    }
                    if (aVar.m == 100) {
                        com.xunlei.downloadprovider.util.b.b bVar = new com.xunlei.downloadprovider.util.b.b();
                        bVar.d = aVar.p;
                        bVar.f = true;
                        bVar.b = aVar.n;
                        bVar.c = aVar.q;
                        bVar.a = aVar.b;
                        c.a().a(playRecordListWidget.V, playRecordListWidget.getContext(), bVar.a, bVar.b, bVar.c, bVar.d, bVar.e, 1, bVar.f, bVar.g, playRecordListWidget.K);
                        return;
                    }
                    DownData downData2 = new DownData();
                    downData2.a = aVar.b;
                    downData2.c = aVar.h;
                    downData2.d = aVar.i;
                    downData2.e = e3;
                    downData2.b = e3;
                    com.xunlei.downloadprovider.model.g gVar2 = new com.xunlei.downloadprovider.model.g(29, downData2.e, downData2.s);
                    if (playRecordListWidget.g != null) {
                        playRecordListWidget.g.a(downData2, gVar2);
                    }
                default:
                    break;
            }
        }
    }

    public PlayRecordListWidget(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = PlayRecordListWidget.class.getSimpleName();
        this.k = new b();
        this.a = new ArrayList();
        this.b = new HashSet();
        this.c = new HashSet();
        this.d = new HashSet();
        this.l = 20;
        this.m = null;
        this.n = null;
        this.o = false;
        this.p = false;
        this.e = false;
        this.f = false;
        this.q = PlayRecordFragment$OntainState.idle;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.g = null;
        this.D = 0;
        this.E = new a(this);
        this.F = new com.xunlei.downloadprovider.a.h.b(this.E);
        this.G = new g(this);
        this.H = new j(this);
        this.J = new k(this);
        this.K = new l(this);
        this.L = 0;
        this.M = null;
        this.V = new Object();
        h();
    }

    public PlayRecordListWidget(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = PlayRecordListWidget.class.getSimpleName();
        this.k = new b();
        this.a = new ArrayList();
        this.b = new HashSet();
        this.c = new HashSet();
        this.d = new HashSet();
        this.l = 20;
        this.m = null;
        this.n = null;
        this.o = false;
        this.p = false;
        this.e = false;
        this.f = false;
        this.q = PlayRecordFragment$OntainState.idle;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.g = null;
        this.D = 0;
        this.E = new a(this);
        this.F = new com.xunlei.downloadprovider.a.h.b(this.E);
        this.G = new g(this);
        this.H = new j(this);
        this.J = new k(this);
        this.K = new l(this);
        this.L = 0;
        this.M = null;
        this.V = new Object();
        h();
    }

    public PlayRecordListWidget(Context context) {
        super(context);
        this.j = PlayRecordListWidget.class.getSimpleName();
        this.k = new b();
        this.a = new ArrayList();
        this.b = new HashSet();
        this.c = new HashSet();
        this.d = new HashSet();
        this.l = 20;
        this.m = null;
        this.n = null;
        this.o = false;
        this.p = false;
        this.e = false;
        this.f = false;
        this.q = PlayRecordFragment$OntainState.idle;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.g = null;
        this.D = 0;
        this.E = new a(this);
        this.F = new com.xunlei.downloadprovider.a.h.b(this.E);
        this.G = new g(this);
        this.H = new j(this);
        this.J = new k(this);
        this.K = new l(this);
        this.L = 0;
        this.M = null;
        this.V = new Object();
        h();
    }

    private final void h() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService("layout_inflater");
        layoutInflater.inflate(R.layout.cloud_vod_list_layout, this);
        this.A = (UnifiedLoadingView) findViewById(R.id.progress_load_root);
        this.t = findViewById(R.id.cloud_list_login_warn);
        this.u = (Button) findViewById(R.id.get_contact_need_login_btn);
        this.u.setVisibility(0);
        this.v = (TextView) findViewById(R.id.thunder_browser_error_page_title);
        this.w = (TextView) findViewById(R.id.common_error_text_detail);
        this.x = (TextView) findViewById(R.id.common_error_text_detail2);
        this.y = (ImageView) findViewById(R.id.common_icon);
        this.z = (PullToRefreshListView) findViewById(R.id.vod_list_view);
        this.s = layoutInflater.inflate(R.layout.cloud_list_empty_view, null);
        TextView textView = (TextView) this.s.findViewById(R.id.cloud_list_empty_title);
        textView.setText(R.string.cloud_list_vod_empty_vip);
        textView.setVisibility(0);
        this.z.setMode(Mode.PULL_FROM_START);
        this.z.setOnRefreshListener(this.H);
        this.z.setAdapter(this.k);
        this.z.setOnLastItemVisibleListener(this.J);
        this.z.setGravity(com.xunlei.xllib.R.styleable.AppCompatTheme_homeAsUpIndicator);
        this.r = (ErrorView) findViewById(R.id.layout_no_network_error_view);
        this.r.setErrorType(SimpleLog.LOG_LEVEL_DEBUG);
        this.r.setActionButtonListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_no_network_error_view:
                if (com.xunlei.c.a.b.a(BrothersApplication.a())) {
                    this.r.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    this.C.onClick(view);
                    return;
                }
                this.r.setVisibility(0);
            case R.id.get_contact_need_login_btn:
                LoginHelper.a();
                if (!LoginHelper.c() || TextUtils.isEmpty(XLUserUtil.getInstance().getCurrentUser().getStringValue(USERINFOKEY.UserID))) {
                    LoginHelper.a().a(getContext(), new f(this), SpdyProtocol.PUBKEY_SEQ_ADASH, getContext().getResources().getString(R.string.cloud_list_tab_sync_vod));
                    return;
                }
                int i;
                Object obj = BuildConfig.VERSION_NAME;
                if (d.b.containsKey(com.xunlei.analytics.b.c.c)) {
                    obj = ((d) d.b.get(com.xunlei.analytics.b.c.c)).h;
                }
                if (TextUtils.isEmpty(obj)) {
                    if (!(TextUtils.isEmpty(this.u.getText()) || this.u.getText().equals("\u5f00\u901a\u4f1a\u5458"))) {
                        StatReporter.reportVip_ContinueClick("space_play_middle");
                    }
                    i = 0;
                } else {
                    BrowserUtil.a();
                    BrowserUtil.a(getContext(), obj, "\u7eed\u8d39");
                    obj = 1;
                }
                if (i == 0) {
                    PayFrom payFrom = PayFrom.PLAY_LIST;
                    if (!this.u.getText().toString().contains("\u5f00\u901a")) {
                        payFrom = PayFrom.PLAY_LIST_RENEWTIP;
                    }
                    PayEntryParam payEntryParam = new PayEntryParam(payFrom);
                    payEntryParam.c = d.a;
                    PaymentEntryActivity.a(getContext(), payEntryParam);
                }
            default:
                break;
        }
    }

    private final void i() {
        long j = 0;
        this.c.clear();
        this.b.clear();
        this.d.clear();
        if (this.q == PlayRecordFragment$OntainState.refreshing || this.q == PlayRecordFragment$OntainState.auto_refreshing || !this.I) {
            this.D = 0;
        } else if (this.a.size() > 0) {
            long j2;
            List list = this.a;
            for (int size = list.size() - 1; size >= 0; size--) {
                CommixturePlayRecord commixturePlayRecord = (CommixturePlayRecord) list.get(size);
                if (commixturePlayRecord.c != RecodeType.vodInfo) {
                    j2 = commixturePlayRecord.b.c;
                    break;
                }
            }
            j2 = 0;
            j = j2;
        }
        new h(this, j).start();
    }

    private void a(com.xunlei.downloadprovider.vod.b.b.a aVar) {
        if (com.xunlei.xllib.a.b.a(getContext())) {
            switch (aVar.m) {
                case com.xunlei.xllib.R.styleable.AppCompatTheme_buttonStyle:
                    Context context = getContext();
                    x xVar = new x(context);
                    U = xVar;
                    xVar.a(context.getString(R.string.play_record_loading));
                    U.setOnCancelListener(new e(this));
                    U.show();
                    com.xunlei.downloadprovider.util.b.b bVar = new com.xunlei.downloadprovider.util.b.b();
                    bVar.d = aVar.p;
                    bVar.f = true;
                    bVar.b = aVar.n;
                    bVar.c = aVar.q;
                    bVar.a = aVar.b;
                    bVar.g = BuildConfig.VERSION_NAME;
                    c.a().a(this.V, getContext(), bVar.a, bVar.b, bVar.c, bVar.d, bVar.e, SimpleLog.LOG_LEVEL_DEBUG, bVar.f, bVar.g, this.K);
                    return;
                default:
                    if (aVar.f != null) {
                        VodSourceType vodSourceType;
                        i iVar = new i();
                        iVar.a = aVar.b;
                        iVar.b = aVar.h;
                        iVar.c = aVar.i;
                        iVar.d = aVar.j;
                        iVar.e = aVar.f;
                        com.xunlei.downloadprovider.vod.b.b.a();
                        switch (aVar.d) {
                            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                                vodSourceType = VodSourceType.space_his;
                                break;
                            case SimpleLog.LOG_LEVEL_TRACE:
                                vodSourceType = VodSourceType.normal;
                                break;
                            case SimpleLog.LOG_LEVEL_DEBUG:
                                vodSourceType = VodSourceType.webpage;
                                break;
                            case MqttConnectOptions.MQTT_VERSION_3_1:
                                vodSourceType = VodSourceType.third_server;
                                break;
                            default:
                                vodSourceType = VodSourceType.normal;
                                break;
                        }
                        iVar.h = vodSourceType;
                        iVar.f = aVar.e;
                        iVar.g = VodVideoFormat.flv;
                        VodUtil.a();
                        VodUtil.a(getContext(), iVar);
                        return;
                    }
                    return;
            }
        }
        XLToast.a(getContext(), XLToastType.XLTOAST_TYPE_ALARM, getContext().getString(R.string.qrcode_no_net));
    }

    private static boolean b(String str) {
        return !TextUtils.isEmpty(str) ? str.trim().toLowerCase().startsWith("bt://") : false;
    }

    private final int getCurrentRequestTaskID() {
        return this.I ? this.D : 0;
    }

    public final void a() {
        CommixturePlayRecord commixturePlayRecord;
        if (this.B != null) {
            com.xunlei.xllib.b.d.a(this.a);
            this.B.a();
        }
        if (!(this.h || this.g == null || this.I)) {
            this.g.a(BrothersApplication.a().getString(R.string.cloud_list_toast_update_finish));
        }
        List<CommixturePlayRecord> list = this.a;
        Collections.sort(list, new a());
        List arrayList = new ArrayList();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (CommixturePlayRecord commixturePlayRecord2 : list) {
            linkedHashSet.add(commixturePlayRecord2.d());
        }
        int size = linkedHashSet.size();
        int size2 = list.size();
        for (int i = 0; i < size2 && arrayList.size() != size; i++) {
            commixturePlayRecord2 = (CommixturePlayRecord) list.get(i);
            String d = commixturePlayRecord2.d();
            if (linkedHashSet.contains(d)) {
                linkedHashSet.remove(d);
                arrayList.add(commixturePlayRecord2);
            }
        }
        this.a = arrayList;
        if (this.k.getCount() == 0) {
            this.z.setEmptyView(this.s);
            return;
        }
        this.k.notifyDataSetChanged();
        if (this.I) {
            this.I = false;
            if (this.a.size() != this.L) {
                this.L = this.a.size();
            }
            this.I = false;
        } else {
            this.L = 0;
            this.z.setSelection(0);
        }
        if (this.g != null) {
            this.g.c();
        }
    }

    public final boolean a(String str, String str2) {
        boolean z = false;
        if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || this.b.contains(str) || this.c.contains(str2))) {
            z = this.b.add(str);
            if (z) {
                z = this.c.add(str2);
            }
            if (!z) {
                this.b.remove(str);
                this.c.remove(str2);
            }
        }
        return z;
    }

    public final void b() {
        this.b.clear();
        this.c.clear();
    }

    public final void c() {
        if (com.xunlei.c.a.b.a(BrothersApplication.a())) {
            LoginHelper.a();
            if (!LoginHelper.c() || TextUtils.isEmpty(XLUserUtil.getInstance().getCurrentUser().getStringValue(USERINFOKEY.UserID))) {
                if (getListCount() == 0) {
                    this.z.setMode(Mode.DISABLED);
                    this.t.setVisibility(0);
                    this.y.setImageResource(R.drawable.bg_not_login);
                    this.v.setText(R.string.tips_not_login);
                    this.v.setVisibility(0);
                    this.w.setText(BuildConfig.VERSION_NAME);
                    this.w.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    this.x.setText(BuildConfig.VERSION_NAME);
                    this.x.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    this.u.setText(R.string.cloud_list_btn_login);
                    this.u.setOnClickListener(this);
                    return;
                }
                this.t.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.u.setOnClickListener(null);
                return;
            } else if (LoginHelper.a().f()) {
                if (!d.b.containsKey(com.xunlei.analytics.b.c.c)) {
                    this.t.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    return;
                } else if (getListCount() == 0) {
                    this.t.setVisibility(0);
                    this.y.setImageResource(R.drawable.bg_sync_list);
                    this.v.setText(R.string.cloud_list_lixian_pay_title);
                    this.v.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    this.w.setText(((d) d.b.get(com.xunlei.analytics.b.c.c)).d);
                    this.w.setVisibility(0);
                    this.x.setText(BuildConfig.VERSION_NAME);
                    this.x.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    this.u.setText(((d) d.b.get(com.xunlei.analytics.b.c.c)).e);
                    this.u.setOnClickListener(this);
                    this.u.setBackgroundResource(R.drawable.common_red_button_selector);
                    return;
                } else {
                    this.t.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    this.u.setOnClickListener(null);
                    return;
                }
            } else if (d.b.containsKey(com.xunlei.analytics.b.c.c)) {
                if (getListCount() == 0) {
                    this.t.setVisibility(0);
                    this.y.setImageResource(R.drawable.bg_vip_sync);
                    this.v.setText(R.string.cloud_list_lixian_pay_title);
                    this.v.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    this.w.setText(((d) d.b.get(com.xunlei.analytics.b.c.c)).d);
                    this.w.setVisibility(0);
                    this.x.setText(BuildConfig.VERSION_NAME);
                    this.x.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    this.u.setText(((d) d.b.get(com.xunlei.analytics.b.c.c)).e);
                    this.u.setOnClickListener(this);
                    this.u.setBackgroundResource(R.drawable.common_red_button_selector);
                    return;
                }
                this.t.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.u.setOnClickListener(null);
                return;
            } else if (getListCount() == 0) {
                this.t.setVisibility(0);
                this.y.setImageResource(R.drawable.bg_vip_sync);
                this.v.setText(R.string.cloud_list_lixian_pay_title);
                this.v.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.w.setText(R.string.cloud_list_vod_empty);
                this.w.setVisibility(0);
                this.x.setText(BuildConfig.VERSION_NAME);
                this.x.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.u.setText(R.string.cloud_list_btn_pay);
                this.u.setOnClickListener(this);
                this.u.setBackgroundResource(R.drawable.common_red_button_selector);
                return;
            } else {
                this.t.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.u.setOnClickListener(null);
                return;
            }
        }
        this.r.setVisibility(0);
    }

    public final void setOnListOperateCallback(PlayRecordFragment$b playRecordFragment$b) {
        this.g = playRecordFragment$b;
    }

    public final int getDeleteTaskCount() {
        return (this.b.size() > this.c.size() ? this.c.size() : this.b.size()) + this.d.size();
    }

    public final int getListCount() {
        return this.k.getCount();
    }

    public final void d() {
        this.h = true;
        LoginHelper.a();
        if (!LoginHelper.c() || TextUtils.isEmpty(XLUserUtil.getInstance().getCurrentUser().getStringValue(USERINFOKEY.UserID))) {
            a();
            return;
        }
        this.o = true;
        this.n = new Object();
        if (!(this.b.isEmpty() || this.c.isEmpty())) {
            int i;
            int i2;
            int deleteTaskCount = getDeleteTaskCount();
            XLYB_VODINFO[] xlyb_vodinfoArr = new XLYB_VODINFO[deleteTaskCount];
            for (i = 0; i < deleteTaskCount; i++) {
                xlyb_vodinfoArr[i] = new XLYB_VODINFO();
            }
            int i3 = 0;
            for (String str : this.b) {
                if (i3 < deleteTaskCount) {
                    i2 = i3 + 1;
                    xlyb_vodinfoArr[i3].src_url = str;
                    i = i2;
                } else {
                    i = i3;
                }
                i3 = i;
            }
            i3 = 0;
            for (String str2 : this.c) {
                if (i3 < deleteTaskCount) {
                    i2 = i3 + 1;
                    xlyb_vodinfoArr[i3].url_hash = str2;
                    i = i2;
                } else {
                    i = i3;
                }
                i3 = i;
            }
            if (deleteTaskCount == 1) {
                XLYunboUtil.getInstance().deleteVideo(xlyb_vodinfoArr[0].src_url, xlyb_vodinfoArr[0].url_hash, 0, this.n, this.G);
            } else {
                XLYunboUtil.getInstance().deleteVideos(xlyb_vodinfoArr, 0, this.n, this.G);
            }
        }
        a();
    }

    public final void e() {
        this.e = !this.e;
        ((Activity) getContext()).runOnUiThread(new b(this));
    }

    public final void setListViewMode(Mode mode) {
        if (mode != null) {
            this.z.setMode(mode);
        }
    }

    private synchronized void a(CommixturePlayRecord commixturePlayRecord) {
        String a;
        this.M = null;
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.cloud_vod_list_item_long_click, null);
        this.N = (ImageView) inflate.findViewById(R.id.cloud_list_item_icon);
        this.O = (TextView) inflate.findViewById(R.id.long_cloud_list_item_name);
        this.P = (TextView) inflate.findViewById(R.id.long_cloud_list_item_filesize);
        this.Q = (TextView) inflate.findViewById(R.id.cloud_list_btn_download);
        this.R = (TextView) inflate.findViewById(R.id.cloud_list_btn_delete);
        this.S = inflate.findViewById(R.id.view_divider_one);
        this.T = inflate.findViewById(R.id.view_divider_two);
        this.R.setVisibility(0);
        this.T.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        if (!TextUtils.isEmpty(commixturePlayRecord.d())) {
            this.O.setText(commixturePlayRecord.d());
        }
        if (commixturePlayRecord.e() > 0) {
            a = com.xunlei.downloadprovider.d.a.a(commixturePlayRecord.e(), SimpleLog.LOG_LEVEL_DEBUG);
        } else {
            a = getContext().getResources().getString(R.string.cloud_list_info_dialog_content_size_unkown);
        }
        this.P.setText(String.format(BrothersApplication.a().getString(R.string.cloud_list_info_dialog_content_size), new Object[]{a}));
        switch (AnonymousClass_1.a[commixturePlayRecord.c.ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
                if (b(commixturePlayRecord.a.src_url)) {
                    this.N.setImageResource(com.xunlei.downloadprovidershare.R.drawable.ic_dl_bt);
                } else {
                    b(commixturePlayRecord, this.N);
                }
                break;
            case SimpleLog.LOG_LEVEL_DEBUG:
                b(commixturePlayRecord, this.N);
                break;
        }
        if (commixturePlayRecord.b() == 0) {
            this.Q.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.S.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        } else {
            a = commixturePlayRecord.c();
            if (a == null || !a.equals("shortVideo")) {
                this.P.setVisibility(0);
                this.Q.setVisibility(0);
            } else {
                this.Q.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.S.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.P.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
                LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(com.xunlei.xllib.R.styleable.Toolbar_titleMarginTop, -1);
                this.O.setLayoutParams(layoutParams);
            }
        }
        this.Q.setOnClickListener(new c(this, commixturePlayRecord));
        this.R.setOnClickListener(new d(this, commixturePlayRecord));
        if (this.M == null) {
            this.M = new q(getContext());
            this.M.setContentView(inflate);
            this.M.d(BrothersApplication.a().getString(R.string.cloud_list_info_dialog_btn));
            this.M.setCanceledOnTouchOutside(true);
        }
        if (this.M != null) {
            this.M.show();
        }
    }

    private static void b(CommixturePlayRecord commixturePlayRecord, ImageView imageView) {
        switch (commixturePlayRecord.b()) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
            case SimpleLog.LOG_LEVEL_TRACE:
            case SimpleLog.LOG_LEVEL_DEBUG:
            case MqttConnectOptions.MQTT_VERSION_3_1:
                if (TextUtils.isEmpty(commixturePlayRecord.d())) {
                    imageView.setImageResource(com.xunlei.downloadprovidershare.R.drawable.ic_dl_video);
                } else {
                    imageView.setImageResource(XLFileTypeUtil.c(commixturePlayRecord.d()));
                }
            default:
                break;
        }
    }

    public void setOnRefreshClickListener(OnClickListener onClickListener) {
        this.C = onClickListener;
    }

    public final boolean f() {
        if (!(this.z == null || this.z.getRefreshableView() == null)) {
            try {
                return this.z.k();
            } catch (NullPointerException e) {
            } catch (NoSuchMethodError e2) {
            }
        }
        return false;
    }

    public final void a(PlayRecordFragment$a playRecordFragment$a) {
        this.f = false;
        XLYB_INITDATA xlyb_initdata = new XLYB_INITDATA();
        xlyb_initdata.userId = LoginHelper.a().j;
        xlyb_initdata.userSessionId = LoginHelper.a().k;
        xlyb_initdata.userVipLevel = LoginHelper.a().e;
        XLYunboUtil.getInstance().init(BrothersApplication.a().getApplicationContext(), xlyb_initdata);
        this.f = true;
        this.B = playRecordFragment$a;
        i();
    }

    static /* synthetic */ void a(PlayRecordListWidget playRecordListWidget, CommixturePlayRecord commixturePlayRecord) {
        if (!playRecordListWidget.a.contains(commixturePlayRecord)) {
            playRecordListWidget.a.add(commixturePlayRecord);
        }
    }

    static /* synthetic */ void a(PlayRecordListWidget playRecordListWidget, boolean z) {
        new StringBuilder("func requestVodListData : start . mObtainState = ").append(playRecordListWidget.q);
        if (playRecordListWidget.q == PlayRecordFragment$OntainState.idle) {
            if (z) {
                playRecordListWidget.q = PlayRecordFragment$OntainState.refreshing;
                playRecordListWidget.b();
                playRecordListWidget.p = false;
            } else {
                playRecordListWidget.q = PlayRecordFragment$OntainState.obtaining;
            }
            playRecordListWidget.m = new Object();
            XLYunboUtil.getInstance().obtainVideoList(com.xunlei.xllib.R.styleable.Toolbar_navigationIcon, playRecordListWidget.getCurrentRequestTaskID(), MqttConnectOptions.MQTT_VERSION_3_1_1, SimpleLog.LOG_LEVEL_DEBUG, playRecordListWidget.m, playRecordListWidget.G);
        }
    }

    static /* synthetic */ void a(PlayRecordListWidget playRecordListWidget, Message message) {
        Set set = (Set) message.obj;
        if (message.arg1 == 0) {
            if (!set.isEmpty()) {
                Collection hashSet = new HashSet();
                for (CommixturePlayRecord commixturePlayRecord : playRecordListWidget.a) {
                    switch (AnonymousClass_1.a[commixturePlayRecord.c.ordinal()]) {
                        case SimpleLog.LOG_LEVEL_DEBUG:
                            if (set.contains(String.valueOf(commixturePlayRecord.b.a))) {
                                hashSet.add(commixturePlayRecord);
                                String c = commixturePlayRecord.c();
                                if (c != null && c.equals("shortVideo")) {
                                    com.xunlei.downloadprovider.vod.b.b.a().c(commixturePlayRecord.b.p);
                                }
                            }
                        default:
                            break;
                    }
                }
                playRecordListWidget.a.removeAll(hashSet);
                playRecordListWidget.d.clear();
            }
            playRecordListWidget.A.b();
        }
        playRecordListWidget.d();
    }

    static /* synthetic */ void i(PlayRecordListWidget playRecordListWidget) {
        playRecordListWidget.A.b();
        playRecordListWidget.z.setVisibility(0);
        switch (AnonymousClass_1.b[playRecordListWidget.q.ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
                XLToast.a(playRecordListWidget.getContext(), XLToastType.XLTOAST_TYPE_NORMAL, BrothersApplication.a().getString(R.string.cloud_list_toast_no_more));
                break;
            case SimpleLog.LOG_LEVEL_DEBUG:
            case MqttConnectOptions.MQTT_VERSION_3_1:
                if (playRecordListWidget.g != null) {
                    playRecordListWidget.g.a(BrothersApplication.a().getString(R.string.cloud_list_toast_update_finish));
                }
                break;
        }
        playRecordListWidget.z.m();
        if (playRecordListWidget.e) {
            playRecordListWidget.z.setMode(Mode.DISABLED);
        } else {
            playRecordListWidget.z.setMode(Mode.PULL_FROM_START);
        }
        playRecordListWidget.q = PlayRecordFragment$OntainState.idle;
        playRecordListWidget.p = true;
    }

    static /* synthetic */ void k(PlayRecordListWidget playRecordListWidget) {
        playRecordListWidget.A.b();
        playRecordListWidget.z.setVisibility(0);
        playRecordListWidget.z.m();
        if (playRecordListWidget.e) {
            playRecordListWidget.z.setMode(Mode.DISABLED);
        } else {
            playRecordListWidget.z.setMode(Mode.PULL_FROM_START);
        }
        playRecordListWidget.q = PlayRecordFragment$OntainState.idle;
    }

    static /* synthetic */ boolean a(PlayRecordListWidget playRecordListWidget, String str, String str2) {
        return !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && playRecordListWidget.b.contains(str) && playRecordListWidget.c.contains(str2);
    }

    static /* synthetic */ boolean o(PlayRecordListWidget playRecordListWidget) {
        return !playRecordListWidget.p && playRecordListWidget.a.size() * g.a(BrothersApplication.a().getApplicationContext(), 80.0f) < com.xunlei.downloadprovider.a.b.u() - g.a(BrothersApplication.a().getApplicationContext(), 92.0f);
    }

    static /* synthetic */ void g() {
        if (U != null && U.isShowing()) {
            U.setOnCancelListener(null);
            U.dismiss();
            U = null;
        }
    }

    static /* synthetic */ boolean b(PlayRecordListWidget playRecordListWidget, String str, String str2) {
        boolean z = false;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && playRecordListWidget.b.contains(str) && playRecordListWidget.c.contains(str2)) {
            z = playRecordListWidget.b.remove(str);
            if (z) {
                z = playRecordListWidget.c.remove(str2);
            }
            if (!z) {
                playRecordListWidget.b.add(str);
                playRecordListWidget.c.add(str2);
            }
        }
        return z;
    }

    static /* synthetic */ void a(PlayRecordListWidget playRecordListWidget, XLYB_VODINFO xlyb_vodinfo) {
        if (xlyb_vodinfo != null && b(xlyb_vodinfo.src_url)) {
            Intent intent = new Intent();
            intent.setClass(playRecordListWidget.getContext(), CloudListBTFileActivity.class);
            intent.putExtra("intent_source_key", com.xunlei.xllib.R.styleable.AppCompatTheme_checkboxStyle);
            intent.putExtra("intent_task", xlyb_vodinfo);
            intent.putExtra("intent_title", xlyb_vodinfo.filename);
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
            if (playRecordListWidget.g != null) {
                playRecordListWidget.g.a(intent);
            }
        }
    }

    static /* synthetic */ void b(PlayRecordListWidget playRecordListWidget, XLYB_VODINFO xlyb_vodinfo) {
        if (playRecordListWidget.g != null && xlyb_vodinfo != null) {
            i iVar = new i();
            iVar.a = xlyb_vodinfo.filename;
            iVar.b = xlyb_vodinfo.cid;
            iVar.c = xlyb_vodinfo.gcid;
            iVar.d = xlyb_vodinfo.filesize;
            iVar.e = xlyb_vodinfo.src_url;
            iVar.h = VodSourceType.vod_history;
            iVar.g = VodVideoFormat.flv;
            playRecordListWidget.g.a(iVar);
        }
    }

    static /* synthetic */ void b(PlayRecordListWidget playRecordListWidget, CommixturePlayRecord commixturePlayRecord) {
        if (commixturePlayRecord != null) {
            switch (commixturePlayRecord.b()) {
                case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                    com.xunlei.downloadprovider.vod.b.b.a aVar = commixturePlayRecord.b;
                    if (new File(aVar.f).exists()) {
                        VodUtil.a();
                        VodUtil.a(BrothersApplication.a(), aVar.f, "space_his", VodSourceType.space_his);
                    } else {
                        XLToast.a(BrothersApplication.a(), XLToastType.XLTOAST_TYPE_ALARM, BrothersApplication.a().getString(R.string.task_detail_file_noexist));
                    }
                    StatReporter.reportVodListLocalPlay();
                    StatReporter.reportOverallPlay("cloud_play_record", "tnative");
                case SimpleLog.LOG_LEVEL_TRACE:
                    playRecordListWidget.a(commixturePlayRecord.b);
                    StatReporter.reportVodListVodPlay();
                    StatReporter.reportOverallPlay("cloud_play_record", "tcloud");
                case SimpleLog.LOG_LEVEL_DEBUG:
                case MqttConnectOptions.MQTT_VERSION_3_1:
                    playRecordListWidget.a(commixturePlayRecord.b);
                    StatReporter.reportVodListOnlinePlay();
                    StatReporter.reportOverallPlay("cloud_play_record", "tonline");
                default:
                    break;
            }
        }
    }
}
