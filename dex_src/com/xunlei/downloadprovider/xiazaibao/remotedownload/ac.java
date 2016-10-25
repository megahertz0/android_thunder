package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xunlei.common.register.XLRegErrorCode;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.downloadprovider.download.util.a;
import com.xunlei.downloadprovider.xlui.widget.ZHTextView;
import com.xunlei.xiazaibao.sdk.entities.DownloadLixianChannel;
import com.xunlei.xiazaibao.sdk.entities.DownloadVipChannel;
import com.xunlei.xllib.b.j;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: RemoteDownloadTaskViewHolder.java
public class ac extends g {
    private static final String g;
    private TextView A;
    private FrameLayout B;
    private OnClickListener C;
    private OnClickListener D;
    private OnLongClickListener E;
    private OnClickListener F;
    private OnClickListener G;
    private OnClickListener H;
    al a;
    RelativeLayout f;
    private View h;
    private ImageView i;
    private ZHTextView j;
    private TextView k;
    private TextView l;
    private TextView m;
    private TextView n;
    private TextView o;
    private ImageView p;
    private ProgressBar q;
    private FrameLayout r;
    private ImageView s;
    private TextView t;
    private LinearLayout u;
    private TextView v;
    private View w;
    private RelativeLayout x;
    private LinearLayout y;
    private LinearLayout z;

    // compiled from: RemoteDownloadTaskViewHolder.java
    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[EFileCategoryType.values().length];
            try {
                a[EFileCategoryType.E_VIDEO_CATEGORY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[EFileCategoryType.E_MUSIC_CATEGORY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[EFileCategoryType.E_BOOK_CATEGORY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[EFileCategoryType.E_SOFTWARE_CATEGORY.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[EFileCategoryType.E_PICTURE_CATEGORY.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[EFileCategoryType.E_ZIP_CATEGORY.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[EFileCategoryType.E_TORRENT_CATEGORY.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[EFileCategoryType.E_OTHER_CATEGORY.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    static {
        g = ac.class.getSimpleName();
    }

    private ac(View view) {
        super(view);
        this.C = new ad(this);
        this.D = new ae(this);
        this.E = new af(this);
        this.F = new ag(this);
        this.G = new ai(this);
        this.H = new ak(this);
        this.h = view;
        this.i = (ImageView) view.findViewById(R.id.iconImageView);
        this.j = (ZHTextView) view.findViewById(R.id.titleTextView);
        this.l = (TextView) view.findViewById(R.id.speedTextView);
        this.k = (TextView) view.findViewById(R.id.tagSize);
        this.n = (TextView) view.findViewById(R.id.remainTextView);
        this.m = (TextView) view.findViewById(R.id.speedupSpeedTextView);
        this.o = (TextView) view.findViewById(R.id.statusTextView);
        this.q = (ProgressBar) view.findViewById(R.id.progressBar);
        this.r = (FrameLayout) view.findViewById(R.id.edit_mode_select_layout);
        this.s = (ImageView) view.findViewById(R.id.edit_mode_select_btn);
        this.t = (TextView) view.findViewById(R.id.operateButton);
        this.u = (LinearLayout) view.findViewById(R.id.tagPlay);
        this.v = (TextView) view.findViewById(R.id.tagEpisode);
        this.w = view.findViewById(R.id.vipspeedupButton);
        this.x = (RelativeLayout) view.findViewById(R.id.runningStatusLayout);
        this.y = (LinearLayout) view.findViewById(R.id.statusLayout);
        this.z = (LinearLayout) view.findViewById(R.id.unfinishedButtonLayout);
        this.f = (RelativeLayout) view.findViewById(R.id.layout_gotosetting_downloadpath);
        this.A = (TextView) view.findViewById(R.id.xzb_operate);
        this.p = (ImageView) view.findViewById(R.id.statusImageView);
        this.A.setOnClickListener(this.C);
        this.B = (FrameLayout) view.findViewById(R.id.iv_close);
        this.B.setOnClickListener(this.D);
        a();
        view.setOnLongClickListener(this.E);
        view.setOnClickListener(this.H);
    }

    public static ac a(Context context) {
        return new ac(View.inflate(context, R.layout.layout_xzb_task_list_item, null));
    }

    public final void a() {
        this.f.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }

    public final void a(al alVar) {
        if (alVar != null) {
            int d;
            long speed;
            CharSequence string;
            this.a = alVar;
            this.j.setText(alVar.getName());
            this.l.setText(a.a(alVar.getSpeed()));
            this.k.setText(a.b(alVar.getSize()));
            this.q.setProgress(alVar.getProgress() / 100);
            this.u.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.v.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            if (this.a.getType() == 2) {
                this.i.setImageResource(com.xunlei.downloadprovidershare.R.drawable.ic_dl_bt);
            } else {
                switch (AnonymousClass_1.a[XLFileTypeUtil.a(this.a.getName()).ordinal()]) {
                    case SimpleLog.LOG_LEVEL_TRACE:
                        d = XLFileTypeUtil.d(this.a.getName());
                        break;
                    case SimpleLog.LOG_LEVEL_DEBUG:
                        d = com.xunlei.downloadprovidershare.R.drawable.ic_dl_music;
                        break;
                    case MqttConnectOptions.MQTT_VERSION_3_1:
                        d = com.xunlei.downloadprovidershare.R.drawable.ic_dl_text;
                        break;
                    case MqttConnectOptions.MQTT_VERSION_3_1_1:
                        d = com.xunlei.downloadprovidershare.R.drawable.ic_dl_apk;
                        break;
                    case SimpleLog.LOG_LEVEL_ERROR:
                        d = com.xunlei.downloadprovidershare.R.drawable.ic_dl_image;
                        break;
                    case SimpleLog.LOG_LEVEL_FATAL:
                        d = com.xunlei.downloadprovidershare.R.drawable.ic_dl_rar;
                        break;
                    case SimpleLog.LOG_LEVEL_OFF:
                        d = com.xunlei.downloadprovidershare.R.drawable.ic_dl_torrent;
                        break;
                    case SpdyProtocol.PUBKEY_SEQ_ADASH:
                        d = com.xunlei.downloadprovidershare.R.drawable.ic_dl_other;
                        break;
                    default:
                        d = com.xunlei.downloadprovidershare.R.drawable.ic_dl_other;
                        break;
                }
                this.i.setImageResource(d);
            }
            DownloadVipChannel vipChannel = this.a.getVipChannel();
            if (vipChannel != null) {
                speed = vipChannel.getSpeed() + 0;
            } else {
                speed = 0;
            }
            DownloadLixianChannel lixianChannel = this.a.getLixianChannel();
            if (lixianChannel != null) {
                speed += lixianChannel.getSpeed();
            }
            if (speed > 0) {
                this.m.setText(String.format("(+ %s)", new Object[]{a.a(speed)}));
                this.m.setVisibility(0);
            } else {
                this.m.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            }
            this.w.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            speed = this.a.getRemainTime();
            if (speed > 0) {
                if (speed > 86400) {
                    string = this.h.getContext().getResources().getString(R.string.download_item_task_remain_time_more);
                } else {
                    Resources resources = this.h.getContext().getResources();
                    Object[] objArr = new Object[1];
                    if (speed > 356400) {
                        speed = 356400;
                    }
                    objArr[0] = j.a("HH:mm:ss", speed * 1000);
                    string = resources.getString(R.string.download_item_task_remain_time, objArr);
                }
                this.n.setVisibility(0);
                this.n.setText(string);
            } else {
                this.n.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            }
            d = this.a.getState();
            CharSequence a = a.a(this.a.getSpeed());
            this.t.setEnabled(true);
            switch (d) {
                case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                    this.o.setText(a);
                    this.o.setTextColor(this.h.getContext().getResources().getColor(R.color.DownloadTaskItemStatusTextColor));
                    this.t.setText(R.string.download_item_button_pause);
                    this.t.setOnClickListener(this.G);
                    this.z.setVisibility(0);
                    this.q.setProgressDrawable(a((int) R.drawable.download_task_progress));
                    this.q.setVisibility(0);
                    this.y.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    this.x.setVisibility(0);
                    this.p.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    break;
                case SpdyProtocol.PUBKEY_SEQ_ADASH:
                case com.xunlei.xllib.R.styleable.Toolbar_titleMarginEnd:
                case com.xunlei.xllib.R.styleable.AppCompatTheme_actionModeFindDrawable:
                    this.o.setText(R.string.download_item_task_status_linking);
                    this.o.setTextColor(this.h.getContext().getResources().getColor(R.color.DownloadTaskItemStatusTextColor));
                    this.t.setText(R.string.download_item_button_pause);
                    this.t.setOnClickListener(this.G);
                    this.z.setVisibility(0);
                    this.q.setProgressDrawable(a((int) R.drawable.download_task_progress_disable));
                    this.q.setVisibility(0);
                    this.y.setVisibility(0);
                    this.x.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    this.p.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    break;
                case SpdyProtocol.PUBKEY_PSEQ_ADASH:
                case SpdyProtocol.PUBKEY_SEQ_OPEN:
                    this.o.setText(R.string.download_item_task_status_paused);
                    this.o.setTextColor(this.h.getContext().getResources().getColor(R.color.DownloadTaskItemStatusTextColor));
                    this.t.setText(R.string.download_item_button_start);
                    this.t.setOnClickListener(this.F);
                    this.z.setVisibility(0);
                    this.q.setProgressDrawable(a((int) R.drawable.download_task_progress_disable));
                    this.q.setVisibility(0);
                    this.y.setVisibility(0);
                    this.x.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    this.p.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    break;
                case SpdyProtocol.PUBKEY_PSEQ_OPEN:
                    this.o.setText(R.string.download_item_task_status_success);
                    this.o.setTextColor(this.h.getContext().getResources().getColor(R.color.DownloadTaskItemStatusTextColor));
                    this.z.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
                    this.q.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    this.y.setVisibility(0);
                    this.x.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    this.p.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    break;
                case com.xunlei.xllib.R.styleable.Toolbar_titleMargins:
                case com.xunlei.xllib.R.styleable.AppCompatTheme_actionModeWebSearchDrawable:
                    TextView textView = this.o;
                    switch (this.a.getFailCode()) {
                        case com.xunlei.xllib.R.styleable.AppCompatTheme_buttonStyleSmall:
                            string = "\u7f51\u7edc\u4e0d\u53ef\u8fbe";
                            break;
                        case SpdyProtocol.CDN:
                            string = "\u8d44\u6e90\u4e0d\u8db3";
                            break;
                        case XLRegErrorCode.REG_EXIST:
                            string = "URL\u975e\u6cd5";
                            break;
                        case 202:
                            string = "\u4efb\u52a1\u91cd\u590d";
                            break;
                        case 205:
                            string = "\u5b58\u50a8\u8def\u5f84\u9519\u8bef";
                            break;
                        case 440:
                            string = "\u7f51\u7edc\u8d85\u65f6";
                            break;
                        case 4225:
                            string = "\u654f\u611f\u8d44\u6e90";
                            break;
                        case 20482:
                            string = "\u7535\u9a74URL\u975e\u6cd5";
                            break;
                        default:
                            string = "\u4e0b\u8f7d\u5931\u8d25";
                            break;
                    }
                    textView.setText(string);
                    this.o.setTextColor(this.h.getContext().getResources().getColor(R.color.DownloadTaskItemErrorStatusTextColor));
                    this.p.setVisibility(0);
                    this.t.setText(R.string.download_item_button_retry);
                    this.t.setOnClickListener(this.F);
                    this.z.setVisibility(0);
                    this.q.setProgressDrawable(a((int) R.drawable.download_task_progress_disable));
                    this.q.setVisibility(0);
                    this.y.setVisibility(0);
                    this.x.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    break;
            }
            if (this.c) {
                this.r.setVisibility(0);
                this.t.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
                b(this.a.a);
                return;
            }
            this.r.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.t.setVisibility(0);
        }
    }

    private Drawable a(int i) {
        return VERSION.SDK_INT >= 21 ? this.h.getResources().getDrawable(i, null) : this.h.getResources().getDrawable(i);
    }

    private void b(boolean z) {
        if (z) {
            this.s.setImageResource(R.drawable.big_selected);
        } else {
            this.s.setImageResource(R.drawable.big_unselected);
        }
    }

    static /* synthetic */ void c(ac acVar) {
        com.xunlei.downloadprovider.xiazaibao.view.j jVar = new com.xunlei.downloadprovider.xiazaibao.view.j(acVar.h.getContext(), acVar.a, acVar.d.g);
        jVar.a = acVar.e;
        jVar.show();
    }

    static /* synthetic */ void d(ac acVar) {
        if (acVar.e != null) {
            acVar.e.a();
        }
    }

    static /* synthetic */ void a(ac acVar, int i, boolean z) {
        if (acVar.e != null) {
            acVar.e.a(i, z);
        }
    }
}
