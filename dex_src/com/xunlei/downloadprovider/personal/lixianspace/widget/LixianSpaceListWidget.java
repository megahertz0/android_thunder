package com.xunlei.downloadprovider.personal.lixianspace.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.e;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xunlei.analytics.b.c;
import com.xunlei.common.lixian.XLLX_INITDATA;
import com.xunlei.common.lixian.XLLX_TASKDETAIL;
import com.xunlei.common.lixian.XLLixianBtTask;
import com.xunlei.common.lixian.XLLixianListener;
import com.xunlei.common.lixian.XLLixianTask;
import com.xunlei.common.lixian.XLLixianUtil;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.cloudlist.CloudListBTFileActivity;
import com.xunlei.downloadprovider.cloudlist.k;
import com.xunlei.downloadprovider.commonview.ErrorView;
import com.xunlei.downloadprovider.commonview.UnifiedLoadingView;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.commonview.dialog.q;
import com.xunlei.downloadprovider.homepage.a.a.d;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.payment.external.PayEntryParam;
import com.xunlei.downloadprovider.member.payment.external.PayFrom;
import com.xunlei.downloadprovider.member.payment.external.PaymentEntryActivity;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.personal.lixianspace.LixianSpaceFragment$OntainState;
import com.xunlei.downloadprovider.personal.lixianspace.LixianSpaceFragment$a;
import com.xunlei.downloadprovider.personal.lixianspace.LixianSpaceFragment$c;
import com.xunlei.downloadprovider.personal.lixianspace.LixianSpaceFragment$d;
import com.xunlei.downloadprovider.personal.lixianspace.widget.LixianSpaceListWidget.LastCommitTime;
import com.xunlei.downloadprovider.vod.protocol.VodSourceType;
import com.xunlei.downloadprovider.vod.protocol.VodVideoFormat;
import com.xunlei.downloadprovider.vod.protocol.i;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.mediaserver.Utility;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

public class LixianSpaceListWidget extends RelativeLayout {
    private TextView A;
    private ImageView B;
    private LixianSpaceFragment$c C;
    private OnClickListener D;
    private ErrorView E;
    private final OnClickListener F;
    private final e<ListView> G;
    private final com.handmark.pulltorefresh.library.PullToRefreshBase.a H;
    private Handler I;
    private HashMap<Long, String> J;
    private q K;
    private ImageView L;
    private TextView M;
    private TextView N;
    private TextView O;
    private TextView P;
    private TextView Q;
    private View R;
    public final List<XLLixianTask> a;
    public final Set<Long> b;
    public Object c;
    public boolean d;
    public boolean e;
    public boolean f;
    public UnifiedLoadingView g;
    public LixianSpaceFragment$d h;
    public final XLLixianListener i;
    private final String j;
    private final a k;
    private final List<XLLixianTask> l;
    private final Set<Long> m;
    private int n;
    private Object o;
    private LixianSpaceFragment$OntainState p;
    private boolean q;
    private boolean r;
    private boolean s;
    private View t;
    private Button u;
    private TextView v;
    private TextView w;
    private TextView x;
    private ImageView y;
    private PullToRefreshListView z;

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;

        static {
            b = new int[LixianSpaceFragment$OntainState.values().length];
            try {
                b[LixianSpaceFragment$OntainState.auto_refreshing.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                b[LixianSpaceFragment$OntainState.obtaining.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                b[LixianSpaceFragment$OntainState.refreshing.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                b[LixianSpaceFragment$OntainState.auto_obtaining.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            a = new int[LastCommitTime.values().length];
            try {
                a[LastCommitTime.today.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[LastCommitTime.yesterday.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[LastCommitTime.threedaysago.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    public enum LastCommitTime {
        today,
        yesterday,
        threedaysago,
        none;

        static {
            today = new com.xunlei.downloadprovider.personal.lixianspace.widget.LixianSpaceListWidget.LastCommitTime("today", 0);
            yesterday = new com.xunlei.downloadprovider.personal.lixianspace.widget.LixianSpaceListWidget.LastCommitTime("yesterday", 1);
            threedaysago = new com.xunlei.downloadprovider.personal.lixianspace.widget.LixianSpaceListWidget.LastCommitTime("threedaysago", 2);
            none = new com.xunlei.downloadprovider.personal.lixianspace.widget.LixianSpaceListWidget.LastCommitTime(Utility.NETWORK_NONE, 3);
            a = new com.xunlei.downloadprovider.personal.lixianspace.widget.LixianSpaceListWidget.LastCommitTime[]{today, yesterday, threedaysago, none};
        }
    }

    private final class a extends BaseAdapter {
        private int b;
        private int c;

        private a() {
            this.b = g.a(BrothersApplication.a().getApplicationContext(), 80.0f);
            this.c = com.xunlei.downloadprovider.a.b.u() - g.a(BrothersApplication.a().getApplicationContext(), 92.0f);
        }

        public final /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public final View getView(int i, View view, ViewGroup viewGroup) {
            LixianSpaceFragment$a lixianSpaceFragment$a;
            XLLX_TASKDETAIL detailInfo;
            if (view == null || view.getTag() == null) {
                view = LayoutInflater.from(BrothersApplication.a().getApplicationContext()).inflate(R.layout.cloud_list_item, null);
                lixianSpaceFragment$a = new LixianSpaceFragment$a();
                lixianSpaceFragment$a.e = view.findViewById(R.id.cloud_list_item_btn_right);
                lixianSpaceFragment$a.f = (TextView) view.findViewById(R.id.cloud_list_item_btn_right_text);
                lixianSpaceFragment$a.b = (ImageView) view.findViewById(R.id.cloud_list_item_icon);
                lixianSpaceFragment$a.d = (TextView) view.findViewById(R.id.cloud_list_item_filesize);
                lixianSpaceFragment$a.c = (TextView) view.findViewById(R.id.cloud_list_item_name);
                LixianSpaceListWidget.this = view.findViewById(R.id.cloud_list_item_layout);
                lixianSpaceFragment$a.g = view.findViewById(R.id.cloud_list_item_btn_select);
                lixianSpaceFragment$a.h = (ImageView) view.findViewById(R.id.cloud_list_item_btn_select_icon);
                lixianSpaceFragment$a.i = view.findViewById(R.id.cloud_list_item_head);
                lixianSpaceFragment$a.j = (RelativeLayout) view.findViewById(R.id.rl_cloud_title_layout);
                lixianSpaceFragment$a.k = (TextView) view.findViewById(com.xunlei.xllib.R.id.title);
                lixianSpaceFragment$a.l = view.findViewById(R.id.view_0);
                lixianSpaceFragment$a.n = view.findViewById(R.id.view_1);
                lixianSpaceFragment$a.m = (ImageView) view.findViewById(com.xunlei.xllib.R.id.image);
                lixianSpaceFragment$a.o = (TextView) view.findViewById(R.id.divider_line);
                view.setTag(lixianSpaceFragment$a);
            } else {
                lixianSpaceFragment$a = (LixianSpaceFragment$a) view.getTag();
            }
            if (i == 0) {
                lixianSpaceFragment$a.o.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            }
            XLLixianTask a = a(i);
            if (a != null) {
                int i2;
                detailInfo = a.getDetailInfo();
                if (i != 0) {
                    if (i < 0) {
                        i2 = 0;
                    } else {
                        XLLX_TASKDETAIL detailInfo2 = a(i).getDetailInfo();
                        XLLX_TASKDETAIL detailInfo3 = a(i - 1).getDetailInfo();
                        if (detailInfo2 == null || detailInfo3 == null) {
                            i2 = 0;
                        } else {
                            String str = (String) LixianSpaceListWidget.this.J.get(Long.valueOf(detailInfo2.taskid));
                            String str2 = (String) LixianSpaceListWidget.this.J.get(Long.valueOf(detailInfo3.taskid));
                            if (str2 == null || str == null) {
                                i2 = 0;
                            } else if (str.equals(str2)) {
                                i2 = 0;
                            }
                        }
                    }
                    if (i2 == 0) {
                        lixianSpaceFragment$a.o.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        lixianSpaceFragment$a.j.setVisibility(0);
                        lixianSpaceFragment$a.l.setVisibility(0);
                        lixianSpaceFragment$a.n.setVisibility(0);
                        lixianSpaceFragment$a.m.setVisibility(0);
                        lixianSpaceFragment$a.k.setVisibility(0);
                        lixianSpaceFragment$a.k.setText((CharSequence) LixianSpaceListWidget.this.J.get(Long.valueOf(detailInfo.taskid)));
                    } else {
                        lixianSpaceFragment$a.o.setVisibility(0);
                        lixianSpaceFragment$a.j.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        lixianSpaceFragment$a.l.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        lixianSpaceFragment$a.n.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        lixianSpaceFragment$a.m.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        lixianSpaceFragment$a.k.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    }
                }
                Object obj = 1;
                if (i2 == 0) {
                    lixianSpaceFragment$a.o.setVisibility(0);
                    lixianSpaceFragment$a.j.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    lixianSpaceFragment$a.l.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    lixianSpaceFragment$a.n.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    lixianSpaceFragment$a.m.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    lixianSpaceFragment$a.k.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                } else {
                    lixianSpaceFragment$a.o.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    lixianSpaceFragment$a.j.setVisibility(0);
                    lixianSpaceFragment$a.l.setVisibility(0);
                    lixianSpaceFragment$a.n.setVisibility(0);
                    lixianSpaceFragment$a.m.setVisibility(0);
                    lixianSpaceFragment$a.k.setVisibility(0);
                    lixianSpaceFragment$a.k.setText((CharSequence) LixianSpaceListWidget.this.J.get(Long.valueOf(detailInfo.taskid)));
                }
            } else {
                detailInfo = null;
            }
            if (!(a == null || detailInfo == null)) {
                LixianSpaceListWidget.this.setBackgroundResource(R.drawable.cloud_list_item_bg_selector);
                if (a.isBtTask()) {
                    lixianSpaceFragment$a.a(BrothersApplication.a().getString(R.string.cloud_list_btn_open), -1);
                } else {
                    switch (detailInfo.filetype.getClassType()) {
                        case com.xunlei.xllib.R.styleable.AppCompatTheme_buttonStyle:
                            lixianSpaceFragment$a.a(BrothersApplication.a().getString(R.string.cloud_list_btn_download_play), -1);
                            break;
                        default:
                            lixianSpaceFragment$a.a(BrothersApplication.a().getString(R.string.cloud_list_btn_download), R.drawable.download_item_resume_icon);
                            break;
                    }
                }
            }
            if (!(a == null || detailInfo == null)) {
                if (LixianSpaceListWidget.this.e) {
                    lixianSpaceFragment$a.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    if (a == null || !LixianSpaceListWidget.this.b.contains(Long.valueOf(a.getTaskId()))) {
                        lixianSpaceFragment$a.h.setImageResource(R.drawable.big_unselected);
                    } else {
                        lixianSpaceFragment$a.h.setImageResource(R.drawable.big_selected);
                    }
                    lixianSpaceFragment$a.g.setVisibility(0);
                } else {
                    lixianSpaceFragment$a.e.setVisibility(0);
                    lixianSpaceFragment$a.g.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    lixianSpaceFragment$a.h.setImageBitmap(null);
                }
                obj = k.a(detailInfo.taskname);
                lixianSpaceFragment$a.c.setText(obj);
                lixianSpaceFragment$a.c.invalidate();
                lixianSpaceFragment$a.c.requestLayout();
                if (a.isBtTask()) {
                    lixianSpaceFragment$a.b.setImageResource(com.xunlei.downloadprovidershare.R.drawable.ic_dl_bt);
                } else {
                    lixianSpaceFragment$a.b.setImageResource(XLFileTypeUtil.d(obj));
                }
                if (detailInfo.filesize > 0) {
                    lixianSpaceFragment$a.d.setText(com.xunlei.downloadprovider.d.a.a(detailInfo.filesize, SimpleLog.LOG_LEVEL_DEBUG));
                } else {
                    lixianSpaceFragment$a.d.setText(R.string.cloud_list_unknow_size);
                }
            }
            if (!(a == null || detailInfo == null)) {
                OnClickListener lVar = new l(this, a, lixianSpaceFragment$a, detailInfo, i);
                OnLongClickListener mVar = new m(this, a, detailInfo);
                lixianSpaceFragment$a.e.setOnClickListener(lVar);
                LixianSpaceListWidget.this.setOnClickListener(lVar);
                LixianSpaceListWidget.this.setOnLongClickListener(mVar);
            }
            return view;
        }

        public final long getItemId(int i) {
            return (long) i;
        }

        private XLLixianTask a(int i) {
            return (i < 0 || i >= LixianSpaceListWidget.this.size()) ? null : (XLLixianTask) LixianSpaceListWidget.this.get(i);
        }

        public final int getCount() {
            return LixianSpaceListWidget.this.size();
        }
    }

    static class b implements Comparator<XLLixianTask> {
        b() {
        }

        public final /* synthetic */ int compare(Object obj, Object obj2) {
            XLLixianTask xLLixianTask = (XLLixianTask) obj2;
            long j = ((XLLixianTask) obj).getDetailInfo().commit_time;
            long j2 = xLLixianTask.getDetailInfo().commit_time;
            if (j < j2) {
                return 1;
            }
            return j > j2 ? -1 : 0;
        }
    }

    static /* synthetic */ void a(LixianSpaceListWidget lixianSpaceListWidget, XLLixianTask xLLixianTask, String str, long j) {
        lixianSpaceListWidget.K = null;
        View inflate = LayoutInflater.from(lixianSpaceListWidget.getContext()).inflate(R.layout.cloud_vod_list_item_long_click, null);
        lixianSpaceListWidget.L = (ImageView) inflate.findViewById(R.id.cloud_list_item_icon);
        lixianSpaceListWidget.M = (TextView) inflate.findViewById(R.id.long_cloud_list_item_name);
        lixianSpaceListWidget.N = (TextView) inflate.findViewById(R.id.long_cloud_list_item_filesize);
        lixianSpaceListWidget.O = (TextView) inflate.findViewById(R.id.cloud_list_btn_download);
        lixianSpaceListWidget.P = (TextView) inflate.findViewById(R.id.cloud_list_btn_delete);
        lixianSpaceListWidget.Q = (TextView) inflate.findViewById(R.id.cloud_list_btn_open);
        lixianSpaceListWidget.R = inflate.findViewById(R.id.view_divider_two);
        lixianSpaceListWidget.O.setVisibility(0);
        lixianSpaceListWidget.P.setVisibility(0);
        if (!TextUtils.isEmpty(str)) {
            lixianSpaceListWidget.M.setText(str);
        }
        if (j > 0) {
            lixianSpaceListWidget.N.setText(String.format(BrothersApplication.a().getString(R.string.cloud_list_info_dialog_content_size), new Object[]{com.xunlei.downloadprovider.d.a.a(j, SimpleLog.LOG_LEVEL_DEBUG)}));
        } else {
            lixianSpaceListWidget.N.setText(R.string.cloud_list_unknow_size);
        }
        if (xLLixianTask.isBtTask()) {
            lixianSpaceListWidget.L.setImageResource(com.xunlei.downloadprovidershare.R.drawable.ic_dl_bt);
            lixianSpaceListWidget.Q.setVisibility(0);
        } else {
            lixianSpaceListWidget.L.setImageResource(XLFileTypeUtil.d(str));
            lixianSpaceListWidget.Q.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            lixianSpaceListWidget.R.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
        XLLX_TASKDETAIL detailInfo = xLLixianTask.getDetailInfo();
        lixianSpaceListWidget.Q.setOnClickListener(new j(lixianSpaceListWidget, xLLixianTask, detailInfo));
        lixianSpaceListWidget.O.setOnClickListener(new k(lixianSpaceListWidget, xLLixianTask, detailInfo));
        lixianSpaceListWidget.P.setOnClickListener(new c(lixianSpaceListWidget, detailInfo));
        if (lixianSpaceListWidget.K == null) {
            lixianSpaceListWidget.K = new q(lixianSpaceListWidget.getContext());
            lixianSpaceListWidget.K.setContentView(inflate);
            lixianSpaceListWidget.K.d(BrothersApplication.a().getString(R.string.cloud_list_info_dialog_btn));
            lixianSpaceListWidget.K.setCanceledOnTouchOutside(true);
        }
        if (lixianSpaceListWidget.K != null) {
            lixianSpaceListWidget.K.show();
        }
    }

    static /* synthetic */ void a(LixianSpaceListWidget lixianSpaceListWidget, String str) {
        if (lixianSpaceListWidget.d) {
            lixianSpaceListWidget.d = false;
            XLToast.a(lixianSpaceListWidget.getContext(), XLToastType.XLTOAST_TYPE_NORMAL, BrothersApplication.a().getString(R.string.cloud_list_toast_delete_suc));
        } else {
            switch (AnonymousClass_1.b[lixianSpaceListWidget.p.ordinal()]) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    if (!(lixianSpaceListWidget.s || lixianSpaceListWidget.a.size() <= 0 || lixianSpaceListWidget.h == null)) {
                        lixianSpaceListWidget.s = true;
                        lixianSpaceListWidget.h.a(BrothersApplication.a().getString(R.string.cloud_list_toast_update_finish));
                    }
                    break;
                case SimpleLog.LOG_LEVEL_DEBUG:
                case MqttConnectOptions.MQTT_VERSION_3_1:
                    if (com.xunlei.xllib.a.b.a(lixianSpaceListWidget.getContext())) {
                        XLToast.a(lixianSpaceListWidget.getContext(), XLToastType.XLTOAST_TYPE_NORMAL, str);
                    } else if (lixianSpaceListWidget.h != null) {
                        lixianSpaceListWidget.h.a(BrothersApplication.a().getString(R.string.cloud_list_toast_network_error));
                    }
                    break;
                default:
                    break;
            }
        }
        lixianSpaceListWidget.a(true);
        if (lixianSpaceListWidget.C != null) {
            lixianSpaceListWidget.C.a();
            lixianSpaceListWidget.C = null;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void c(com.xunlei.downloadprovider.personal.lixianspace.widget.LixianSpaceListWidget r11, com.xunlei.common.lixian.XLLixianTask r12, com.xunlei.common.lixian.XLLX_TASKDETAIL r13) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.personal.lixianspace.widget.LixianSpaceListWidget.c(com.xunlei.downloadprovider.personal.lixianspace.widget.LixianSpaceListWidget, com.xunlei.common.lixian.XLLixianTask, com.xunlei.common.lixian.XLLX_TASKDETAIL):void");
        /*
        r10 = 2;
        r2 = 1;
        r3 = 0;
        r6 = -1;
        r0 = 0;
        r1 = r11.h;
        if (r1 == 0) goto L_0x00a4;
    L_0x000a:
        if (r12 == 0) goto L_0x00a4;
    L_0x000c:
        if (r13 == 0) goto L_0x00a4;
    L_0x000e:
        r1 = r12.isBtTask();
        if (r1 == 0) goto L_0x00b1;
    L_0x0014:
        r1 = r13.url;	 Catch:{ UnsupportedEncodingException -> 0x00a5, IllegalArgumentException -> 0x00ab }
        r0 = com.xunlei.downloadprovider.util.c.a.e(r1);	 Catch:{ UnsupportedEncodingException -> 0x00a5, IllegalArgumentException -> 0x00ab }
    L_0x001a:
        r0 = com.xunlei.downloadprovider.util.c.a.c(r0);
    L_0x001e:
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 != 0) goto L_0x00a4;
    L_0x0024:
        if (r12 == 0) goto L_0x0163;
    L_0x0026:
        r1 = r12.getDetailInfo();
        if (r1 == 0) goto L_0x0163;
    L_0x002c:
        r1 = r12.getDetailInfo();
        r1 = r1.filetype;
        r1 = r1.getClassType();
        r4 = 100;
        if (r1 != r4) goto L_0x0163;
    L_0x003a:
        r1 = r2;
    L_0x003b:
        if (r1 == 0) goto L_0x0166;
    L_0x003d:
        com.xunlei.downloadprovider.model.protocol.report.StatReporter.reportLixianListVideoDownload();
    L_0x0040:
        r1 = "space_lixian";
        com.xunlei.downloadprovider.model.protocol.report.StatReporter.reportOverallDownload(r1);
        r1 = new com.xunlei.downloadprovider.url.DownData;
        r1.<init>();
        r2 = r13.taskname;
        r1.a = r2;
        r2 = r13.cid;
        r1.c = r2;
        r2 = r13.gcid;
        r1.d = r2;
        r2 = r13.filesize;
        r1.r = r2;
        r1.e = r0;
        r1.b = r0;
        r2 = r13.ref_url;
        r1.s = r2;
        r2 = "magnet:?";
        r0 = r0.contains(r2);
        if (r0 == 0) goto L_0x008f;
    L_0x006c:
        r0 = r1.a;
        r2 = ".torrent";
        r0 = r0.endsWith(r2);
        if (r0 != 0) goto L_0x008f;
    L_0x0077:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r2 = r1.a;
        r0 = r0.append(r2);
        r2 = ".torrent";
        r0 = r0.append(r2);
        r0 = r0.toString();
        r1.a = r0;
    L_0x008f:
        r0 = new com.xunlei.downloadprovider.model.g;
        r2 = 28;
        r3 = r1.e;
        r4 = r1.s;
        r0.<init>(r2, r3, r4);
        r2 = "space/space_lixian";
        r0.d = r2;
        r2 = r11.h;
        r2.a(r1, r0);
    L_0x00a4:
        return;
    L_0x00a5:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x001a;
    L_0x00ab:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x001a;
    L_0x00b1:
        r1 = com.xunlei.downloadprovider.service.DownloadService.a();
        if (r1 == 0) goto L_0x0199;
    L_0x00b7:
        r1 = r13.url;	 Catch:{ UnsupportedEncodingException -> 0x0137, IllegalArgumentException -> 0x013d }
        r1 = com.xunlei.downloadprovider.util.c.a.e(r1);	 Catch:{ UnsupportedEncodingException -> 0x0137, IllegalArgumentException -> 0x013d }
    L_0x00bd:
        r4 = com.xunlei.downloadprovider.service.DownloadService.a();
        r4 = r4.a(r1);
        r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r1 != 0) goto L_0x00dd;
    L_0x00c9:
        r1 = r13.taskname;
        r4 = r13.cid;
        r8 = r13.filesize;
        r5 = r13.gcid;
        r1 = com.xunlei.downloadprovider.service.downloads.b.c.a(r4, r8, r5, r1);
        r4 = com.xunlei.downloadprovider.service.DownloadService.a();
        r4 = r4.a(r1);
    L_0x00dd:
        r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r1 == 0) goto L_0x0196;
    L_0x00e1:
        com.xunlei.downloadprovider.service.downloads.task.d.a();
        r1 = com.xunlei.downloadprovider.service.downloads.task.d.d(r4);
    L_0x00e8:
        r6 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r6 == 0) goto L_0x0144;
    L_0x00ec:
        com.xunlei.downloadprovider.download.tasklist.list.xzb.e.a();
        r6 = com.xunlei.downloadprovider.download.tasklist.list.xzb.e.b();
        if (r6 == r10) goto L_0x00fe;
    L_0x00f5:
        com.xunlei.downloadprovider.download.tasklist.list.xzb.e.a();
        r6 = com.xunlei.downloadprovider.download.tasklist.list.xzb.e.b();
        if (r6 != 0) goto L_0x0103;
    L_0x00fe:
        r6 = r11.h;
        r6.a(r4, r1);
    L_0x0103:
        com.xunlei.downloadprovider.download.tasklist.list.xzb.e.a();
        r1 = com.xunlei.downloadprovider.download.tasklist.list.xzb.e.b();
        if (r1 == r10) goto L_0x0115;
    L_0x010c:
        com.xunlei.downloadprovider.download.tasklist.list.xzb.e.a();
        r1 = com.xunlei.downloadprovider.download.tasklist.list.xzb.e.b();
        if (r1 != r2) goto L_0x001e;
    L_0x0115:
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 == 0) goto L_0x0194;
    L_0x011b:
        r1 = r13.ref_url;
    L_0x011d:
        r4 = new com.xunlei.downloadprovider.download.tasklist.list.xzb.a;
        r4.<init>();
        r4.a = r1;
        r1 = r13.taskname;
        r4.b = r1;
        r1 = com.xunlei.downloadprovider.download.tasklist.list.xzb.e.a();
        r5 = r11.getContext();
        r6 = com.xunlei.downloadprovider.download.tasklist.list.xzb.report.XZBReporter.SaveToXZBEntry.other;
        r1.a(r5, r4, r6);
        goto L_0x001e;
    L_0x0137:
        r1 = move-exception;
        r1.printStackTrace();
        r1 = r0;
        goto L_0x00bd;
    L_0x013d:
        r1 = move-exception;
        r1.printStackTrace();
        r1 = r0;
        goto L_0x00bd;
    L_0x0144:
        r1 = r13.lixian_url;
        r1 = android.text.TextUtils.isEmpty(r1);
        if (r1 != 0) goto L_0x0154;
    L_0x014c:
        r1 = r13.lixian_url;
    L_0x014e:
        r0 = com.xunlei.downloadprovider.util.c.a.e(r1);	 Catch:{ UnsupportedEncodingException -> 0x0157, IllegalArgumentException -> 0x015d }
        goto L_0x001e;
    L_0x0154:
        r1 = r13.url;
        goto L_0x014e;
    L_0x0157:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x001e;
    L_0x015d:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x001e;
    L_0x0163:
        r1 = r3;
        goto L_0x003b;
    L_0x0166:
        if (r12 == 0) goto L_0x016e;
    L_0x0168:
        r1 = r12.getDetailInfo();
        if (r1 != 0) goto L_0x0176;
    L_0x016e:
        r1 = r3;
    L_0x016f:
        if (r1 == 0) goto L_0x018f;
    L_0x0171:
        com.xunlei.downloadprovider.model.protocol.report.StatReporter.reportLixianListApkDownload();
        goto L_0x0040;
    L_0x0176:
        r1 = r12.getDetailInfo();
        r1 = r1.taskname;
        r1 = com.xunlei.downloadprovider.cloudlist.k.a(r1);
        if (r1 == 0) goto L_0x018d;
    L_0x0182:
        r4 = ".apk";
        r1 = r1.endsWith(r4);
        if (r1 == 0) goto L_0x018d;
    L_0x018b:
        r1 = r2;
        goto L_0x016f;
    L_0x018d:
        r1 = r3;
        goto L_0x016f;
    L_0x018f:
        com.xunlei.downloadprovider.model.protocol.report.StatReporter.reportLixianListOtherDownload();
        goto L_0x0040;
    L_0x0194:
        r1 = r0;
        goto L_0x011d;
    L_0x0196:
        r1 = r0;
        goto L_0x00e8;
    L_0x0199:
        r4 = r6;
        r1 = r0;
        goto L_0x00e8;
        */
    }

    static /* synthetic */ void v(LixianSpaceListWidget lixianSpaceListWidget) {
        if (lixianSpaceListWidget.d) {
            lixianSpaceListWidget.d = false;
            XLToast.a(lixianSpaceListWidget.getContext(), XLToastType.XLTOAST_TYPE_NORMAL, BrothersApplication.a().getString(R.string.cloud_list_toast_delete_suc));
        } else {
            switch (AnonymousClass_1.b[lixianSpaceListWidget.p.ordinal()]) {
                case SimpleLog.LOG_LEVEL_TRACE:
                case MqttConnectOptions.MQTT_VERSION_3_1:
                    if (!(lixianSpaceListWidget.s || lixianSpaceListWidget.h == null)) {
                        lixianSpaceListWidget.s = true;
                        lixianSpaceListWidget.h.a(BrothersApplication.a().getString(R.string.cloud_list_toast_update_finish));
                    }
                    break;
                case SimpleLog.LOG_LEVEL_DEBUG:
                case MqttConnectOptions.MQTT_VERSION_3_1_1:
                    XLToast.a(lixianSpaceListWidget.getContext(), XLToastType.XLTOAST_TYPE_NORMAL, BrothersApplication.a().getString(R.string.cloud_list_toast_no_more));
                    break;
                default:
                    break;
            }
        }
        lixianSpaceListWidget.r = true;
        lixianSpaceListWidget.a(true);
        if (lixianSpaceListWidget.C != null) {
            lixianSpaceListWidget.C.a();
            lixianSpaceListWidget.C = null;
        }
    }

    public LixianSpaceListWidget(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = LixianSpaceListWidget.class.getSimpleName();
        this.k = new a();
        this.l = new ArrayList();
        this.a = new ArrayList();
        this.m = new HashSet();
        this.b = new HashSet();
        this.n = 20;
        this.o = null;
        this.c = null;
        this.p = LixianSpaceFragment$OntainState.idle;
        this.q = false;
        this.d = false;
        this.r = false;
        this.e = false;
        this.f = false;
        this.s = false;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.g = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.h = null;
        this.F = new a(this);
        this.G = new d(this);
        this.H = new e(this);
        this.i = new f(this);
        this.I = new Handler();
        this.J = new HashMap();
        this.K = null;
        f();
    }

    public LixianSpaceListWidget(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = LixianSpaceListWidget.class.getSimpleName();
        this.k = new a();
        this.l = new ArrayList();
        this.a = new ArrayList();
        this.m = new HashSet();
        this.b = new HashSet();
        this.n = 20;
        this.o = null;
        this.c = null;
        this.p = LixianSpaceFragment$OntainState.idle;
        this.q = false;
        this.d = false;
        this.r = false;
        this.e = false;
        this.f = false;
        this.s = false;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.g = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.h = null;
        this.F = new a(this);
        this.G = new d(this);
        this.H = new e(this);
        this.i = new f(this);
        this.I = new Handler();
        this.J = new HashMap();
        this.K = null;
        f();
    }

    public LixianSpaceListWidget(Context context) {
        super(context);
        this.j = LixianSpaceListWidget.class.getSimpleName();
        this.k = new a();
        this.l = new ArrayList();
        this.a = new ArrayList();
        this.m = new HashSet();
        this.b = new HashSet();
        this.n = 20;
        this.o = null;
        this.c = null;
        this.p = LixianSpaceFragment$OntainState.idle;
        this.q = false;
        this.d = false;
        this.r = false;
        this.e = false;
        this.f = false;
        this.s = false;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.g = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.h = null;
        this.F = new a(this);
        this.G = new d(this);
        this.H = new e(this);
        this.i = new f(this);
        this.I = new Handler();
        this.J = new HashMap();
        this.K = null;
        f();
    }

    private final void f() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService("layout_inflater");
        layoutInflater.inflate(R.layout.cloud_lixian_list_layout, this);
        this.g = (UnifiedLoadingView) findViewById(R.id.progress_load_root);
        this.t = findViewById(R.id.cloud_list_login_warn);
        this.u = (Button) findViewById(R.id.get_contact_need_login_btn);
        this.u.setVisibility(0);
        this.v = (TextView) findViewById(R.id.thunder_browser_error_page_title);
        this.w = (TextView) findViewById(R.id.common_error_text_detail);
        this.x = (TextView) findViewById(R.id.common_error_text_detail2);
        this.y = (ImageView) findViewById(R.id.common_icon);
        this.z = (PullToRefreshListView) findViewById(R.id.lixian_list_view);
        View inflate = layoutInflater.inflate(R.layout.cloud_list_empty_view, null);
        this.B = (ImageView) inflate.findViewById(R.id.cloud_list_empty_icon);
        this.A = (TextView) inflate.findViewById(R.id.cloud_list_empty_title);
        this.A.setText(R.string.cloud_list_lixian_empty);
        this.A.setVisibility(0);
        this.g.setOnClickListener(this.F);
        this.z.setMode(Mode.PULL_FROM_START);
        this.z.setOnRefreshListener(this.G);
        this.z.setAdapter(this.k);
        this.z.setEmptyView(inflate);
        this.z.setGravity(com.xunlei.xllib.R.styleable.AppCompatTheme_homeAsUpIndicator);
        this.z.setOnLastItemVisibleListener(this.H);
        this.E = (ErrorView) findViewById(R.id.layout_no_network_error_view);
        this.E.setErrorType(SimpleLog.LOG_LEVEL_DEBUG);
        this.E.setActionButtonListener(new g(this));
    }

    private final void a(LixianSpaceFragment$OntainState lixianSpaceFragment$OntainState) {
        if (this.p == LixianSpaceFragment$OntainState.idle) {
            this.p = lixianSpaceFragment$OntainState;
            if (this.p == LixianSpaceFragment$OntainState.refreshing) {
                this.r = false;
                this.s = false;
                this.b.clear();
            }
            this.o = new Object();
            XLLixianUtil.getInstance().obtainLixianTasks(getCurrentRequestTaskID(), true, 0, this.n, this.o, this.i);
        }
    }

    private final void a(boolean z) {
        if (this.q || this.p == LixianSpaceFragment$OntainState.refreshing || this.p == LixianSpaceFragment$OntainState.auto_refreshing) {
            this.m.clear();
            this.a.clear();
            this.q = false;
        }
        for (XLLixianTask xLLixianTask : this.l) {
            if (!this.a.contains(xLLixianTask) && !this.m.contains(Long.valueOf(xLLixianTask.getTaskId()))) {
                this.m.add(Long.valueOf(xLLixianTask.getTaskId()));
                this.a.add(xLLixianTask);
            }
        }
        this.p = LixianSpaceFragment$OntainState.idle;
        a();
        if (z || !g()) {
            if (this.e) {
                this.z.setMode(Mode.DISABLED);
            } else {
                this.z.setMode(Mode.PULL_FROM_START);
            }
            this.g.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.z.setVisibility(0);
            return;
        }
        a(LixianSpaceFragment$OntainState.auto_refreshing);
        this.z.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.B.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.A.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.g.setVisibility(0);
        this.g.setType(SimpleLog.LOG_LEVEL_DEBUG);
        this.g.a();
    }

    private final long getCurrentRequestTaskID() {
        return (this.l.isEmpty() || this.p == LixianSpaceFragment$OntainState.refreshing) ? 0 : ((XLLixianTask) this.l.get(this.l.size() - 1)).getTaskId();
    }

    private final boolean g() {
        if (!this.r) {
            if (this.l.size() * g.a(BrothersApplication.a().getApplicationContext(), 80.0f) < com.xunlei.downloadprovider.a.b.u() - g.a(BrothersApplication.a().getApplicationContext(), 92.0f)) {
                return true;
            }
        }
        return false;
    }

    public final void a() {
        if (this.a.size() == 0) {
            if (this.l.size() == 0) {
                this.B.setImageResource(com.xunlei.downloadprovidershare.R.drawable.bg_page_empty);
                this.A.setText(R.string.cloud_list_lixian_empty);
                this.A.setVisibility(0);
            } else {
                this.B.setImageResource(com.xunlei.downloadprovidershare.R.drawable.bg_page_empty);
                this.A.setVisibility(0);
            }
        }
        Collections.sort(this.a, new b());
        this.k.notifyDataSetChanged();
    }

    public static LastCommitTime a(XLLixianTask xLLixianTask) {
        if (xLLixianTask == null) {
            return LastCommitTime.threedaysago;
        }
        XLLX_TASKDETAIL detailInfo = xLLixianTask.getDetailInfo();
        if (0 == detailInfo.commit_time) {
            return LastCommitTime.threedaysago;
        }
        Date date = new Date(detailInfo.commit_time * 1000);
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance2.set(1, instance.get(1));
        instance2.set(SimpleLog.LOG_LEVEL_DEBUG, instance.get(SimpleLog.LOG_LEVEL_DEBUG));
        instance2.set(SimpleLog.LOG_LEVEL_ERROR, instance.get(SimpleLog.LOG_LEVEL_ERROR));
        instance2.set(SpdyProtocol.PUBKEY_PSEQ_OPEN, 0);
        instance2.set(com.xunlei.xllib.R.styleable.Toolbar_titleMargins, 0);
        instance2.set(com.xunlei.xllib.R.styleable.Toolbar_titleMarginStart, 0);
        Calendar instance3 = Calendar.getInstance();
        instance3.set(1, instance.get(1));
        instance3.set(SimpleLog.LOG_LEVEL_DEBUG, instance.get(SimpleLog.LOG_LEVEL_DEBUG));
        instance3.set(SimpleLog.LOG_LEVEL_ERROR, instance.get(SimpleLog.LOG_LEVEL_ERROR) - 1);
        instance3.set(SpdyProtocol.PUBKEY_PSEQ_OPEN, 0);
        instance3.set(com.xunlei.xllib.R.styleable.Toolbar_titleMargins, 0);
        instance3.set(com.xunlei.xllib.R.styleable.Toolbar_titleMarginStart, 0);
        instance.setTime(date);
        if (instance.after(instance2)) {
            return LastCommitTime.today;
        }
        return (instance.before(instance2) && instance.after(instance3)) ? LastCommitTime.yesterday : LastCommitTime.threedaysago;
    }

    public final void b() {
        this.l.clear();
        this.a.clear();
        this.m.clear();
        this.b.clear();
        this.f = false;
        a();
    }

    public final void c() {
        if (com.xunlei.c.a.b.a(BrothersApplication.a())) {
            LoginHelper.a();
            if (LoginHelper.c()) {
                if (LoginHelper.a().f()) {
                    if (!d.b.containsKey(c.d)) {
                        this.t.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        return;
                    } else if (getListCount() == 0) {
                        this.t.setVisibility(0);
                        this.y.setImageResource(R.drawable.bg_sync_list);
                        this.v.setText(R.string.cloud_list_lixian_pay_title);
                        this.v.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        this.w.setText(((d) d.b.get(c.d)).d);
                        this.w.setVisibility(0);
                        this.x.setText(BuildConfig.VERSION_NAME);
                        this.x.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        this.u.setText(((d) d.b.get(c.d)).e);
                        this.u.setOnClickListener(this.F);
                        this.u.setBackgroundResource(R.drawable.common_red_button_selector);
                        return;
                    } else {
                        this.t.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        this.u.setOnClickListener(null);
                        return;
                    }
                } else if (!d.b.containsKey(c.d)) {
                    this.t.setVisibility(0);
                    this.y.setImageResource(R.drawable.bg_vip_sync);
                    this.v.setText(R.string.cloud_list_lixian_pay_title);
                    this.v.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    this.w.setText(R.string.cloud_list_lixian_pay_text);
                    this.w.setVisibility(0);
                    this.x.setText(BuildConfig.VERSION_NAME);
                    this.x.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    this.u.setText(R.string.cloud_list_btn_pay);
                    this.u.setOnClickListener(this.F);
                    this.u.setBackgroundResource(R.drawable.common_red_button_selector);
                    return;
                } else if (getListCount() == 0) {
                    this.t.setVisibility(0);
                    this.y.setImageResource(R.drawable.bg_vip_sync);
                    this.v.setText(R.string.cloud_list_lixian_pay_title);
                    this.v.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    this.w.setText(((d) d.b.get(c.d)).d);
                    this.w.setVisibility(0);
                    this.x.setText(BuildConfig.VERSION_NAME);
                    this.x.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    this.u.setText(((d) d.b.get(c.d)).e);
                    this.u.setOnClickListener(this.F);
                    this.u.setBackgroundResource(R.drawable.common_red_button_selector);
                    return;
                } else {
                    this.t.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    this.u.setOnClickListener(null);
                    return;
                }
            } else if (getListCount() == 0) {
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
                this.u.setOnClickListener(this.F);
                return;
            } else {
                this.t.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.u.setOnClickListener(null);
                return;
            }
        }
        this.E.setVisibility(0);
    }

    public final void a(LixianSpaceFragment$c lixianSpaceFragment$c) {
        b();
        XLLX_INITDATA xllx_initdata = new XLLX_INITDATA();
        xllx_initdata.peerId = com.xunlei.downloadprovider.a.b.d();
        xllx_initdata.userId = LoginHelper.a().j;
        xllx_initdata.userJumpKey = LoginHelper.a().n;
        xllx_initdata.userName = LoginHelper.a().e();
        xllx_initdata.userSessionId = LoginHelper.a().k;
        xllx_initdata.userVipLevel = (byte) LoginHelper.a().e;
        XLLixianUtil.getInstance().init(BrothersApplication.a().getApplicationContext(), xllx_initdata);
        this.f = true;
        this.C = lixianSpaceFragment$c;
        LoginHelper.a();
        if (LoginHelper.c() && LoginHelper.a().f()) {
            this.g.setType(SimpleLog.LOG_LEVEL_DEBUG);
            this.g.a();
            this.z.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            a(LixianSpaceFragment$OntainState.refreshing);
        } else if (this.C != null) {
            this.C.a();
            this.C = null;
        }
    }

    public final void setOnCloudListOperateListener(LixianSpaceFragment$d lixianSpaceFragment$d) {
        this.h = lixianSpaceFragment$d;
    }

    public final int getDeleteTaskCount() {
        return this.b.size();
    }

    public final int getListCount() {
        return this.k.getCount();
    }

    public final void d() {
        this.e = !this.e;
        this.b.clear();
        ((Activity) getContext()).runOnUiThread(new i(this));
    }

    public final void setListViewMode(Mode mode) {
        if (mode != null) {
            this.z.setMode(mode);
        }
    }

    public void setOnRefreshClickListener(OnClickListener onClickListener) {
        this.D = onClickListener;
    }

    public final boolean e() {
        if (!(this.z == null || this.z.getRefreshableView() == null)) {
            try {
                return this.z.k();
            } catch (NullPointerException e) {
            } catch (NoSuchMethodError e2) {
            }
        }
        return false;
    }

    static /* synthetic */ boolean a(LixianSpaceListWidget lixianSpaceListWidget) {
        Object obj = BuildConfig.VERSION_NAME;
        if (d.b.containsKey(c.c)) {
            obj = ((d) d.b.get(c.c)).h;
        }
        if (TextUtils.isEmpty(obj)) {
            if (!(TextUtils.isEmpty(lixianSpaceListWidget.u.getText()) || lixianSpaceListWidget.u.getText().equals("\u5f00\u901a\u4f1a\u5458"))) {
                StatReporter.reportVip_ContinueClick("space_lixian_middle");
            }
            return false;
        }
        BrowserUtil.a();
        BrowserUtil.a(lixianSpaceListWidget.getContext(), obj, "\u7eed\u8d39");
        return true;
    }

    static /* synthetic */ void b(LixianSpaceListWidget lixianSpaceListWidget) {
        PayFrom payFrom = PayFrom.LIXIAN_SPACE;
        if (!lixianSpaceListWidget.u.getText().toString().contains("\u5f00\u901a")) {
            payFrom = PayFrom.LIXIAN_SPACE_RENEWTIP;
        }
        PayEntryParam payEntryParam = new PayEntryParam(payFrom);
        payEntryParam.c = d.a;
        PaymentEntryActivity.a(lixianSpaceListWidget.getContext(), payEntryParam);
    }

    static /* synthetic */ void a(LixianSpaceListWidget lixianSpaceListWidget, XLLixianTask xLLixianTask, XLLX_TASKDETAIL xllx_taskdetail) {
        if (xLLixianTask != null && xllx_taskdetail != null && xLLixianTask.isBtTask() && (xLLixianTask instanceof XLLixianBtTask)) {
            XLLixianBtTask xLLixianBtTask = (XLLixianBtTask) xLLixianTask;
            Intent intent = new Intent();
            intent.setClass(lixianSpaceListWidget.getContext(), CloudListBTFileActivity.class);
            intent.putExtra("intent_source_key", com.xunlei.xllib.R.styleable.AppCompatTheme_buttonStyleSmall);
            intent.putExtra("intent_task", xLLixianBtTask);
            intent.putExtra("intent_title", xllx_taskdetail.taskname);
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
            if (lixianSpaceListWidget.h != null) {
                lixianSpaceListWidget.h.a(intent);
            }
        }
    }

    static /* synthetic */ void b(LixianSpaceListWidget lixianSpaceListWidget, XLLixianTask xLLixianTask, XLLX_TASKDETAIL xllx_taskdetail) {
        if (lixianSpaceListWidget.h != null && xLLixianTask != null && xllx_taskdetail != null) {
            i iVar = new i();
            iVar.a = xllx_taskdetail.taskname;
            iVar.b = xllx_taskdetail.cid;
            iVar.c = xllx_taskdetail.gcid;
            iVar.d = xllx_taskdetail.filesize;
            iVar.e = xllx_taskdetail.url;
            iVar.h = VodSourceType.lixian;
            iVar.g = VodVideoFormat.flv;
            lixianSpaceListWidget.h.a(iVar);
        }
    }
}
