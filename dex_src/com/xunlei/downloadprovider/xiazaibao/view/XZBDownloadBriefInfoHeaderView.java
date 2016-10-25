package com.xunlei.downloadprovider.xiazaibao.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.c.a.b;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.xiazaibao.view.XZBDownloadBriefInfoHeaderView.StatusInfo.TasksStatus;
import com.xunlei.xllib.b.e;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class XZBDownloadBriefInfoHeaderView extends FrameLayout {
    private Context a;
    private TextView b;
    private TextView c;
    private View d;
    private ImageView e;
    private View f;
    private View g;
    private View h;
    private TextView i;
    private TextView j;
    private TextView k;
    private StatusInfo l;

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[TasksStatus.values().length];
            try {
                a[TasksStatus.Init.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[TasksStatus.NoTasks.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[TasksStatus.TasksDownloading.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[TasksStatus.TasksPaused.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[TasksStatus.TasksFinished.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[TasksStatus.TasksError.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    public static class StatusInfo {
        public boolean a;
        public String b;
        public String c;
        public TasksStatus d;

        public enum TasksStatus {
            Init,
            NoTasks,
            TasksPaused,
            TasksFinished,
            TasksDownloading,
            TasksError;

            static {
                Init = new com.xunlei.downloadprovider.xiazaibao.view.XZBDownloadBriefInfoHeaderView.StatusInfo.TasksStatus("Init", 0);
                NoTasks = new com.xunlei.downloadprovider.xiazaibao.view.XZBDownloadBriefInfoHeaderView.StatusInfo.TasksStatus("NoTasks", 1);
                TasksPaused = new com.xunlei.downloadprovider.xiazaibao.view.XZBDownloadBriefInfoHeaderView.StatusInfo.TasksStatus("TasksPaused", 2);
                TasksFinished = new com.xunlei.downloadprovider.xiazaibao.view.XZBDownloadBriefInfoHeaderView.StatusInfo.TasksStatus("TasksFinished", 3);
                TasksDownloading = new com.xunlei.downloadprovider.xiazaibao.view.XZBDownloadBriefInfoHeaderView.StatusInfo.TasksStatus("TasksDownloading", 4);
                TasksError = new com.xunlei.downloadprovider.xiazaibao.view.XZBDownloadBriefInfoHeaderView.StatusInfo.TasksStatus("TasksError", 5);
                a = new com.xunlei.downloadprovider.xiazaibao.view.XZBDownloadBriefInfoHeaderView.StatusInfo.TasksStatus[]{Init, NoTasks, TasksPaused, TasksFinished, TasksDownloading, TasksError};
            }
        }

        public StatusInfo() {
            this.a = false;
        }
    }

    public XZBDownloadBriefInfoHeaderView(Context context) {
        super(context);
        this.a = context;
        b();
    }

    public XZBDownloadBriefInfoHeaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
        b();
    }

    public XZBDownloadBriefInfoHeaderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = context;
        b();
    }

    @TargetApi(21)
    public XZBDownloadBriefInfoHeaderView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        b();
    }

    private void b() {
        View inflate = LayoutInflater.from(this.a).inflate(R.layout.layout_xzb_download_brief_info_header_view, this);
        this.b = (TextView) inflate.findViewById(R.id.speedTextView);
        this.c = (TextView) inflate.findViewById(R.id.speedUnitTextView);
        this.d = inflate.findViewById(R.id.speedup_tip_container);
        this.f = inflate.findViewById(R.id.speedInfo);
        this.g = inflate.findViewById(R.id.pauseInfo);
        this.h = inflate.findViewById(R.id.pause_tip_text);
        this.i = (TextView) inflate.findViewById(R.id.pause_tip_no_net_text);
        this.e = (ImageView) inflate.findViewById(R.id.tip_icon);
        this.j = (TextView) findViewById(R.id.pauseStatusTextView);
        this.k = (TextView) findViewById(R.id.pause_tip_error_text);
        if (!isInEditMode()) {
            setDownloadSpeed(0);
        }
    }

    public void setDownloadSpeed(long j) {
        if (this.b != null) {
            CharSequence charSequence;
            String[] a = e.a(j, 1, e.b, false);
            String str = a[0];
            CharSequence charSequence2 = a[1];
            if (j == 0) {
                str = "0.0";
                charSequence2 = e.b[1];
            }
            int indexOf = charSequence.indexOf(".");
            if (indexOf == -1) {
                indexOf = charSequence.length();
            }
            if (indexOf < 3) {
                indexOf = 3 - indexOf;
                charSequence = "000".substring(0, indexOf) + charSequence;
            } else {
                indexOf = 0;
            }
            CharSequence spannableStringBuilder = new SpannableStringBuilder(charSequence);
            if (indexOf > 0) {
                spannableStringBuilder.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(R.color.white_opacity_30)), 0, indexOf, com.xunlei.xllib.R.styleable.AppCompatTheme_actionModePasteDrawable);
            }
            this.b.setText(spannableStringBuilder);
            this.c.setText(charSequence2);
            a();
        }
    }

    public final void a() {
        StatusInfo statusInfo = getStatusInfo();
        this.i.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        if (statusInfo.d != null) {
            this.g.setVisibility(0);
            this.f.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            if (b.a(getContext())) {
                switch (AnonymousClass_1.a[statusInfo.d.ordinal()]) {
                    case SimpleLog.LOG_LEVEL_TRACE:
                        this.j.setText("\u4efb\u52a1\u52a0\u8f7d\u4e2d...");
                        this.h.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        this.k.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        return;
                    case SimpleLog.LOG_LEVEL_DEBUG:
                        this.j.setText(R.string.download_center_head_title_notask);
                        this.h.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        this.k.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        return;
                    case MqttConnectOptions.MQTT_VERSION_3_1:
                        this.g.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        this.k.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        this.f.setVisibility(0);
                        if (statusInfo.a) {
                            this.e.setVisibility(0);
                            this.d.setVisibility(0);
                            return;
                        }
                        this.e.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
                        this.d.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
                        return;
                    case MqttConnectOptions.MQTT_VERSION_3_1_1:
                        this.j.setText(R.string.download_center_head_title_taskpaused);
                        this.h.setVisibility(0);
                        this.k.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        return;
                    case SimpleLog.LOG_LEVEL_ERROR:
                        this.j.setText(R.string.download_center_head_title_taskfinished);
                        this.h.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        this.k.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        return;
                    case SimpleLog.LOG_LEVEL_FATAL:
                        this.j.setText(getStatusInfo().b);
                        this.k.setText(getStatusInfo().c);
                        this.h.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                        this.k.setVisibility(0);
                        return;
                    default:
                        return;
                }
            }
            this.i.setVisibility(0);
            this.j.setText(R.string.download_center_head_title_nonet);
            this.i.setText(R.string.download_center_check_net_tip);
            this.h.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.k.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            return;
        }
        this.g.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.f.setVisibility(0);
    }

    public StatusInfo getStatusInfo() {
        if (this.l == null) {
            this.l = new StatusInfo();
        }
        return this.l;
    }
}
