package com.xunlei.downloadprovider.download.center.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.j;
import com.xunlei.downloadprovider.download.tasklist.a.h;
import com.xunlei.downloadprovider.download.util.g;
import com.xunlei.downloadprovider.download.util.n;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.service.downloads.task.d;
import com.xunlei.mediaserver.Utility;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.b.e;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class DownloadBriefInfoHeaderView extends FrameLayout {
    StatusInfo a;
    public View b;
    public int c;
    private TextView d;
    private TextView e;
    private TextView f;
    private View g;
    private TextView h;
    private ImageView i;
    private int j;
    private View k;
    private View l;
    private View m;
    private TextView n;
    private TextView o;
    private TextView p;
    private View q;
    private View r;
    private OnClickListener s;
    private OnClickListener t;
    private Context u;
    private j v;
    private String w;
    private boolean x;
    private a y;

    public static interface a {
        void a();

        void b();
    }

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[b.a().length];
            try {
                a[b.b - 1] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[b.h - 1] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[b.e - 1] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[b.c - 1] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[b.d - 1] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[b.f - 1] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[b.g - 1] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[b.a - 1] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    public static class StatusInfo {
        public boolean a;
        public boolean b;
        public boolean c;
        public boolean d;
        public boolean e;
        public boolean f;
        boolean g;
        public boolean h;
        boolean i;
        boolean j;
        public String k;
        public TasksStatus l;

        public enum TasksStatus {
            NoTasks,
            TasksPaused,
            TasksFailed,
            TasksFinished;

            static {
                NoTasks = new com.xunlei.downloadprovider.download.center.widget.DownloadBriefInfoHeaderView.StatusInfo.TasksStatus("NoTasks", 0);
                TasksPaused = new com.xunlei.downloadprovider.download.center.widget.DownloadBriefInfoHeaderView.StatusInfo.TasksStatus("TasksPaused", 1);
                TasksFailed = new com.xunlei.downloadprovider.download.center.widget.DownloadBriefInfoHeaderView.StatusInfo.TasksStatus("TasksFailed", 2);
                TasksFinished = new com.xunlei.downloadprovider.download.center.widget.DownloadBriefInfoHeaderView.StatusInfo.TasksStatus("TasksFinished", 3);
                a = new com.xunlei.downloadprovider.download.center.widget.DownloadBriefInfoHeaderView.StatusInfo.TasksStatus[]{NoTasks, TasksPaused, TasksFailed, TasksFinished};
            }
        }

        public StatusInfo() {
            this.a = false;
            this.b = false;
            this.c = false;
            this.d = false;
            this.e = false;
            this.f = false;
            this.g = false;
            this.h = false;
            this.i = true;
            this.j = false;
            this.k = BuildConfig.VERSION_NAME;
        }

        public final void a(boolean z, boolean z2) {
            this.i = z;
            this.j = z2;
        }
    }

    enum b {
        ;

        public static int[] a() {
            return (int[]) i.clone();
        }

        static {
            a = 1;
            b = 2;
            c = 3;
            d = 4;
            e = 5;
            f = 6;
            g = 7;
            h = 8;
            i = new int[]{a, b, c, d, e, f, g, h};
        }
    }

    public DownloadBriefInfoHeaderView(Context context) {
        super(context);
        this.j = -1;
        this.c = 0;
        this.x = true;
        a(context);
    }

    public DownloadBriefInfoHeaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = -1;
        this.c = 0;
        this.x = true;
        a(context);
    }

    public DownloadBriefInfoHeaderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = -1;
        this.c = 0;
        this.x = true;
        a(context);
    }

    @TargetApi(21)
    public DownloadBriefInfoHeaderView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.j = -1;
        this.c = 0;
        this.x = true;
        a(context);
    }

    private void a(Context context) {
        this.u = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_download_brief_info_header_view, this);
        this.d = (TextView) inflate.findViewById(R.id.speedTextView);
        this.e = (TextView) inflate.findViewById(R.id.speedUnitTextView);
        this.b = inflate.findViewById(R.id.login_tip_container);
        this.f = (TextView) inflate.findViewById(R.id.login_tip_text);
        this.g = inflate.findViewById(R.id.downloading_tip_container);
        this.h = (TextView) inflate.findViewById(R.id.downloadingTipTextView);
        this.k = inflate.findViewById(R.id.speedInfo);
        this.l = inflate.findViewById(R.id.pauseInfo);
        this.m = inflate.findViewById(R.id.pause_tip_text);
        this.n = (TextView) inflate.findViewById(R.id.exception_tip_text);
        this.o = (TextView) inflate.findViewById(R.id.pause_tip_no_net_text);
        this.i = (ImageView) inflate.findViewById(R.id.tip_icon);
        this.p = (TextView) findViewById(R.id.pauseStatusTextView);
        this.q = findViewById(R.id.open_vip_tip_1);
        this.r = findViewById(R.id.click_container);
        if (!isInEditMode()) {
            setDownloadSpeed(0);
        }
    }

    public void setDownloadSpeed(long j) {
        if (this.d != null) {
            String[] a = e.a(j, 1, e.b, false);
            CharSequence charSequence = a[0];
            CharSequence charSequence2 = a[1];
            if (j == 0) {
                charSequence = "0.0";
                charSequence2 = e.b[1];
            }
            this.d.setText(charSequence);
            this.e.setText(charSequence2);
            a();
        }
    }

    public void setSpeedTipIcon(int i) {
        if (i <= 0) {
            this.i.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            return;
        }
        this.i.setVisibility(0);
        if (this.j != i) {
            this.i.setImageResource(i);
            this.j = i;
        }
    }

    public final void a(boolean z, boolean z2) {
        StatusInfo statusInfo = getStatusInfo();
        statusInfo.a = z;
        statusInfo.b = z2;
        a();
    }

    public final void a() {
        int i = 0;
        StatusInfo statusInfo = getStatusInfo();
        this.o.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.r.setOnClickListener(null);
        if (statusInfo.l != null) {
            this.l.setVisibility(0);
            this.k.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.q.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            if (!statusInfo.i) {
                this.o.setVisibility(0);
                this.p.setText(R.string.download_center_head_title_nonet);
                this.o.setText(R.string.download_center_check_net_tip);
                this.m.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.n.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.q.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else if (statusInfo.l == TasksStatus.NoTasks) {
                this.p.setText(R.string.download_center_head_title_notask);
                this.m.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.n.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                if (!statusInfo.a || (statusInfo.a && !statusInfo.b)) {
                    this.q.setVisibility(0);
                    this.q.setOnClickListener(getLoginInfoClickListener());
                }
            } else if (statusInfo.l == TasksStatus.TasksFinished) {
                this.p.setText(R.string.download_center_head_title_taskfinished);
                this.m.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.n.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                if (!statusInfo.a || (statusInfo.a && !statusInfo.b)) {
                    this.q.setVisibility(0);
                    this.q.setOnClickListener(getLoginInfoClickListener());
                }
            } else if (statusInfo.l == TasksStatus.TasksFailed) {
                this.p.setText(R.string.download_center_head_title_taskException);
                this.m.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.n.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                if (com.xunlei.xllib.a.b.e(getContext())) {
                    this.o.setVisibility(0);
                    this.o.setText(getResources().getString(R.string.download_center_mobile_net_tip));
                    this.n.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                } else {
                    this.o.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    this.n.setVisibility(0);
                    d.a();
                    int i2 = d.m().a;
                    d.a();
                    i2 -= d.m().b;
                    this.n.setText(getResources().getString(R.string.download_center_exception_tip, new Object[]{Integer.valueOf(i2)}));
                }
            } else {
                this.n.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.p.setText(R.string.download_center_head_title_taskpaused);
                if (com.xunlei.xllib.a.b.e(getContext())) {
                    this.o.setVisibility(0);
                    this.o.setText(getResources().getString(R.string.download_center_mobile_net_tip));
                    this.m.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                } else {
                    this.o.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    this.m.setVisibility(0);
                }
            }
        } else {
            this.l.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.q.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.k.setVisibility(0);
        }
        if (this.k.getVisibility() == 0) {
            com.xunlei.downloadprovider.download.tasklist.a.a a;
            if (statusInfo.i) {
                if (statusInfo.i && statusInfo.j) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                if (i2 != 0) {
                    i2 = 1;
                    if (!statusInfo.a && statusInfo.b && statusInfo.h) {
                        setSpeedTipIcon(R.drawable.ic_download_accelerate);
                    } else {
                        setSpeedTipIcon(-1);
                        setNetworkTypeIcon(statusInfo);
                    }
                    if (i2 == 0) {
                        a(b.e);
                    } else if (statusInfo.a && statusInfo.e) {
                        c();
                        a(b.f);
                        return;
                    } else if (!statusInfo.a && !statusInfo.b && statusInfo.e && !LoginHelper.a().l()) {
                        c();
                        a(b.f);
                        return;
                    } else if (!statusInfo.a && statusInfo.c && statusInfo.f) {
                        c();
                        a(b.g);
                        return;
                    } else if (statusInfo.a || !statusInfo.b) {
                        a = h.a().a(g.a().d);
                        if (!(a == null || !a.mIsEnteredHighSpeedTrial || a.mTaskStatus != 2 || a.mVipChannelStatus == 16 || n.a(a, n.g(a)))) {
                            i = 1;
                        }
                        if (i != 0) {
                            a(b.h);
                            return;
                        } else if (statusInfo.d) {
                            d.a();
                            if (d.j() > 0) {
                                b();
                            }
                            a(b.c);
                            return;
                        } else {
                            b();
                            a(b.d);
                            return;
                        }
                    } else if (statusInfo.h) {
                        a(b.b);
                        return;
                    } else {
                        a(b.a);
                        return;
                    }
                }
            }
            i2 = 0;
            if (!statusInfo.a) {
            }
            setSpeedTipIcon(-1);
            setNetworkTypeIcon(statusInfo);
            if (i2 == 0) {
                if (statusInfo.a) {
                }
                if (!statusInfo.a) {
                }
                if (!statusInfo.a) {
                }
                if (statusInfo.a) {
                }
                a = h.a().a(g.a().d);
                i = 1;
                if (i != 0) {
                    a(b.h);
                    return;
                } else if (statusInfo.d) {
                    d.a();
                    if (d.j() > 0) {
                        b();
                    }
                    a(b.c);
                    return;
                } else {
                    b();
                    a(b.d);
                    return;
                }
            }
            a(b.e);
        }
    }

    private void a(int i) {
        this.c = i;
        if (i != 0) {
            switch (AnonymousClass_1.a[i - 1]) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    this.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    this.g.setVisibility(0);
                    this.h.setText(R.string.download_center_speedup_for_you);
                    setSpeedTipIcon(R.drawable.ic_download_accelerate);
                    return;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    this.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    this.g.setVisibility(0);
                    this.h.setText(R.string.download_center_freetrial_tip);
                    setSpeedTipIcon(R.drawable.ic_download_accelerate);
                    return;
                case MqttConnectOptions.MQTT_VERSION_3_1:
                    this.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    this.g.setVisibility(0);
                    this.h.setText(R.string.download_center_mobile_net_download_tip);
                    return;
                case MqttConnectOptions.MQTT_VERSION_3_1_1:
                    this.b.setVisibility(0);
                    this.b.setOnClickListener(getLoginInfoClickListener());
                    this.r.setOnClickListener(getLoginInfoClickListener());
                    this.f.setText(R.string.download_center_open_vip_tip);
                    this.g.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    return;
                case SimpleLog.LOG_LEVEL_ERROR:
                    this.b.setVisibility(0);
                    this.b.setOnClickListener(getLoginInfoClickListener());
                    this.r.setOnClickListener(getLoginInfoClickListener());
                    this.f.setText(R.string.download_center_open_vip_tip_low_speed);
                    this.g.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    return;
                case SimpleLog.LOG_LEVEL_FATAL:
                    this.b.setVisibility(0);
                    this.b.setOnClickListener(getKuaiNiaoClickListener());
                    this.r.setOnClickListener(getKuaiNiaoClickListener());
                    this.f.setText(R.string.download_center_kuainiao_tip_normal);
                    this.g.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    return;
                case SimpleLog.LOG_LEVEL_OFF:
                    this.b.setVisibility(0);
                    this.b.setOnClickListener(getKuaiNiaoClickListener());
                    this.r.setOnClickListener(getKuaiNiaoClickListener());
                    this.f.setText(R.string.download_center_kuainiao_tip_normal);
                    this.g.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    return;
            }
        }
        this.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.g.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }

    private void setNetworkTypeIcon(StatusInfo statusInfo) {
        if (statusInfo.i && statusInfo.k != null) {
            if ("2g".equals(statusInfo.k)) {
                setSpeedTipIcon(R.drawable.ic_download_net_2g);
            } else if (Utility.NETWORK_3G.equals(statusInfo.k)) {
                setSpeedTipIcon(R.drawable.ic_download_net_3g);
            } else if (Utility.NETWORK_4G.equals(statusInfo.k)) {
                setSpeedTipIcon(R.drawable.ic_download_net_4g);
            }
        }
    }

    private void b() {
        if (this.x) {
            this.x = false;
            com.xunlei.downloadprovider.download.report.a.f();
        }
    }

    private void c() {
        if (this.v == null) {
            this.v = new j(this.u, "tip_kuai_niao_show");
        }
        if (this.w == null) {
            this.w = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        }
        if (TextUtils.isEmpty(this.v.b(new StringBuilder("tip_kuai_niao_show").append(this.w).toString(), BuildConfig.VERSION_NAME))) {
            com.xunlei.downloadprovider.download.report.a.g();
            this.v.a(new StringBuilder("tip_kuai_niao_show").append(this.w).toString(), "has_show");
        }
    }

    private OnClickListener getLoginInfoClickListener() {
        if (this.s == null) {
            this.s = new a(this);
        }
        return this.s;
    }

    private OnClickListener getKuaiNiaoClickListener() {
        if (this.t == null) {
            this.t = new b(this);
        }
        return this.t;
    }

    public StatusInfo getStatusInfo() {
        if (this.a == null) {
            this.a = new StatusInfo();
        }
        return this.a;
    }

    public void setActionListener(a aVar) {
        this.y = aVar;
    }
}
