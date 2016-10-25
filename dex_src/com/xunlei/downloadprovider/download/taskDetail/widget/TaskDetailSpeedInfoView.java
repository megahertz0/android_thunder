package com.xunlei.downloadprovider.download.taskDetail.widget;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.umeng.message.proguard.j;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.f;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.download.util.DownloadError;
import com.xunlei.downloadprovider.download.util.DownloadError.FailureCode;
import com.xunlei.downloadprovider.download.util.DownloadError.SpeedupFailureCode;
import com.xunlei.downloadprovider.download.util.n;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.service.downloads.task.b.c;
import com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.a.b;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class TaskDetailSpeedInfoView extends FrameLayout implements a {
    private a a;
    private com.xunlei.downloadprovider.download.tasklist.a.a b;
    private com.xunlei.downloadprovider.download.a.a c;
    private boolean d;
    private boolean e;
    private boolean f;
    private c g;

    private static class a {
        public TextView A;
        public View B;
        public View C;
        public View D;
        public View E;
        public ImageView F;
        public TextView G;
        public ZHTextViewExpandable H;
        public ImageView I;
        public View J;
        public View K;
        public View L;
        public View a;
        public View b;
        public TextView c;
        public TextView d;
        public View e;
        public View f;
        public TextView g;
        public TextView h;
        public View i;
        public View j;
        public View k;
        public TextView l;
        public TextView m;
        public TextView n;
        public TextView o;
        public TextView p;
        public TextView q;
        public View r;
        public TextView s;
        public View t;
        public View u;
        public TextView v;
        public TextView w;
        public TextView x;
        public TextView y;
        public View z;

        private a() {
        }
    }

    static /* synthetic */ void d(TaskDetailSpeedInfoView taskDetailSpeedInfoView) {
        taskDetailSpeedInfoView.e = false;
        taskDetailSpeedInfoView.a.F.setImageResource(R.drawable.detail_speed_arrow_up);
        taskDetailSpeedInfoView.a.D.setVisibility(0);
    }

    public void setControl(com.xunlei.downloadprovider.download.a.a aVar) {
        this.c = aVar;
    }

    public void setNeedFold(boolean z) {
        this.d = z;
    }

    public void setTaskCountInfo(c cVar) {
        this.g = cVar;
        new StringBuilder("taskCountInfo :  ").append(cVar);
    }

    public TaskDetailSpeedInfoView(Context context) {
        super(context);
        this.d = false;
        this.e = false;
        this.f = true;
        a(context);
    }

    public TaskDetailSpeedInfoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = false;
        this.e = false;
        this.f = true;
        a(context);
    }

    public TaskDetailSpeedInfoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = false;
        this.e = false;
        this.f = true;
        a(context);
    }

    private void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_task_detail_speed_info, this);
        this.a = new a();
        this.a.a = inflate.findViewById(R.id.speedInfo_all_data);
        this.a.b = inflate.findViewById(R.id.view_when_hide_all_data);
        this.a.d = (TextView) inflate.findViewById(R.id.speed);
        this.a.e = inflate.findViewById(R.id.speed_container);
        this.a.c = (TextView) inflate.findViewById(R.id.speed_text);
        this.a.f = inflate.findViewById(R.id.fail_reason_container);
        this.a.g = (TextView) inflate.findViewById(R.id.fail_reason);
        this.a.h = (TextView) inflate.findViewById(R.id.downloaded_size);
        this.a.i = inflate.findViewById(R.id.download_size_container);
        this.a.u = inflate.findViewById(R.id.max_speed_container);
        this.a.v = (TextView) inflate.findViewById(R.id.download_max_speed);
        this.a.y = (TextView) inflate.findViewById(R.id.download_create_time);
        this.a.x = (TextView) inflate.findViewById(R.id.download_save_time);
        this.a.w = (TextView) inflate.findViewById(R.id.download_aver_speed);
        this.a.q = (TextView) inflate.findViewById(R.id.download_linked_resource);
        this.a.r = inflate.findViewById(R.id.linked_resource_container);
        this.a.l = (TextView) inflate.findViewById(R.id.pause);
        this.a.l.setOnClickListener(new f(this));
        this.a.k = inflate.findViewById(R.id.speedUp);
        this.a.k.setOnClickListener(new g(this));
        this.a.n = (TextView) inflate.findViewById(R.id.continueTask);
        this.a.n.setOnClickListener(new h(this));
        this.a.m = (TextView) inflate.findViewById(R.id.retry);
        this.a.m.setOnClickListener(new i(this));
        this.a.D = inflate.findViewById(R.id.task_detail_info);
        this.a.D.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.a.n = (TextView) inflate.findViewById(R.id.continueTask);
        this.a.p = (TextView) inflate.findViewById(R.id.download_time_remain);
        this.a.o = (TextView) inflate.findViewById(R.id.download_time_remain_text);
        this.a.C = inflate.findViewById(R.id.line);
        this.a.B = inflate.findViewById(R.id.downloading_speed_info_container);
        this.a.B.setOnClickListener(new j(this));
        this.a.A = (TextView) inflate.findViewById(R.id.finish_time);
        this.a.z = inflate.findViewById(R.id.finish_time_container);
        this.a.t = inflate.findViewById(R.id.progressContainer);
        this.a.s = (TextView) inflate.findViewById(R.id.progress);
        this.a.E = inflate.findViewById(R.id.arrow_layout);
        this.a.E.setVisibility(0);
        this.a.F = (ImageView) inflate.findViewById(R.id.arrow_icon);
        this.a.F.setVisibility(0);
        this.a.H = (ZHTextViewExpandable) inflate.findViewById(R.id.task_url_content);
        this.a.I = (ImageView) inflate.findViewById(R.id.right_arrow_icon);
        this.a.I.setOnClickListener(new k(this));
        this.a.H.setOnClickListener(new l(this));
        this.a.J = inflate.findViewById(R.id.container_need_fold);
        this.a.F.setOnClickListener(new m(this));
        this.a.G = (TextView) inflate.findViewById(R.id.vip_remain_time);
        this.a.K = inflate.findViewById(R.id.detail_expand_space_view);
        this.a.L = inflate.findViewById(R.id.bottom_expand_4_bt);
        this.a.j = inflate.findViewById(R.id.operate_container);
        a();
    }

    private void a() {
        this.a.K.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.a.i.setVisibility(0);
        this.a.u.setVisibility(0);
        this.a.c.setText("\u4e0b\u8f7d\u901f\u5ea6");
        this.a.o.setText("\u5269\u4f59\u65f6\u95f4");
    }

    public final void a(com.xunlei.downloadprovider.download.tasklist.a.a aVar) {
        if (aVar != null) {
            a();
            boolean b = n.b(aVar);
            boolean c = n.c(aVar);
            this.a.H.setListener(this);
            if (c) {
                this.a.L.setVisibility(0);
                this.a.E.setVisibility(0);
                this.a.F.setImageResource(R.drawable.detail_speed_arrow_down);
                this.e = true;
            } else if (this.d) {
                this.a.E.setVisibility(0);
                this.a.F.setImageResource(R.drawable.detail_speed_arrow_down);
                this.a.D.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.e = true;
            } else {
                this.a.E.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.a.D.setVisibility(0);
                this.a.J.setVisibility(0);
                if (b) {
                    this.a.K.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                } else {
                    this.a.K.setVisibility(0);
                }
                this.a.i.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.e = false;
            }
            this.a.H.setMaxLine(SimpleLog.LOG_LEVEL_DEBUG);
            if (b) {
                this.a.j.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.a.c.setText("\u8d44\u6e90\u5927\u5c0f");
                this.a.o.setText("\u6700\u5feb\u901f\u5ea6");
                this.a.i.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.a.u.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.f = false;
                return;
            }
            this.a.j.setVisibility(0);
            this.f = true;
        }
    }

    public final void b(com.xunlei.downloadprovider.download.tasklist.a.a aVar) {
        if (aVar != null) {
            CharSequence string;
            long j;
            int i;
            com.xunlei.downloadprovider.download.tasklist.a.a aVar2;
            a aVar3;
            com.xunlei.downloadprovider.download.tasklist.a.a aVar4;
            this.b = aVar;
            TaskRunningInfo taskRunningInfo = this.b;
            a aVar5 = this.a;
            aVar5.f.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            if (taskRunningInfo.mTaskStatus != 8) {
                if (2 == taskRunningInfo.mTaskStatus) {
                    aVar5.d.setVisibility(0);
                    if (taskRunningInfo.mDownloadSpeed < 1) {
                        aVar5.d.setText(R.string.download_item_task_status_linking);
                    } else if (taskRunningInfo.mHasLixianSpeedup || taskRunningInfo.mHasVipChannelSpeedup || taskRunningInfo.mVipAcceleratedChannelSpeed > 100) {
                        String a = com.xunlei.downloadprovider.download.util.a.a(taskRunningInfo.mDownloadSpeed);
                        SpeedupFailureCode b = DownloadError.b(taskRunningInfo);
                        String format;
                        CharSequence spannableString;
                        if (b == null || taskRunningInfo.mVipAcceleratedChannelSpeed >= 100) {
                            format = String.format("(+ %s)", new Object[]{com.xunlei.downloadprovider.download.util.a.a(taskRunningInfo.mVipAcceleratedChannelSpeed)});
                            a = a + format;
                            spannableString = new SpannableString(a);
                            spannableString.setSpan(new ForegroundColorSpan(getContext().getResources().getColor(com.xunlei.downloadprovidershare.R.color.common_blue_button_normal)), a.length() - format.length(), a.length(), com.xunlei.xllib.R.styleable.AppCompatTheme_actionModePasteDrawable);
                            spannableString.setSpan(new AbsoluteSizeSpan(g.a(getContext(), 10.0f)), a.length() - format.length(), a.length(), com.xunlei.xllib.R.styleable.AppCompatTheme_actionModePasteDrawable);
                            aVar5.d.setText(spannableString);
                        } else {
                            if (b == SpeedupFailureCode.SENSITIVE_RESOURCE_LIMITED) {
                                format = getResources().getString(R.string.download_item_task_speedup_fail_sensitive_resource_limited);
                            } else {
                                format = getResources().getString(R.string.download_item_task_speedup_fail);
                            }
                            format = new StringBuilder(j.s).append(format).append(j.t).toString();
                            a = a + format;
                            spannableString = new SpannableString(a);
                            spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.DownloadTaskItemSpeedupErrorStatusTextColor)), a.length() - format.length(), a.length(), com.xunlei.xllib.R.styleable.AppCompatTheme_actionModePasteDrawable);
                            spannableString.setSpan(new AbsoluteSizeSpan(g.a(getContext(), 12.0f)), a.length() - format.length(), a.length(), com.xunlei.xllib.R.styleable.AppCompatTheme_actionModePasteDrawable);
                            aVar5.d.setText(spannableString);
                        }
                    } else {
                        if (taskRunningInfo.mDownloadSpeed < 1) {
                            string = getContext().getString(R.string.download_item_task_status_linking);
                        } else {
                            string = com.xunlei.downloadprovider.download.util.a.a(taskRunningInfo.mDownloadSpeed);
                        }
                        aVar5.d.setText(string);
                    }
                } else if (taskRunningInfo.mTaskStatus == 16) {
                    aVar5.f.setVisibility(0);
                    aVar5.d.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    Context context = getContext();
                    FailureCode a2 = DownloadError.a(taskRunningInfo);
                    string = String.valueOf(context.getString(R.string.download_item_task_status_failed));
                    if (a2 != null) {
                        switch (AnonymousClass_1.a[a2.ordinal()]) {
                            case SimpleLog.LOG_LEVEL_TRACE:
                                string = String.valueOf(context.getString(R.string.download_item_task_fail_insufficient_space));
                                break;
                            case SimpleLog.LOG_LEVEL_DEBUG:
                                string = String.valueOf(context.getString(R.string.download_item_task_fail_insufficient_space));
                                break;
                            case MqttConnectOptions.MQTT_VERSION_3_1:
                                string = String.valueOf(context.getString(R.string.download_item_task_fail_torrent_not_exist));
                                break;
                            case MqttConnectOptions.MQTT_VERSION_3_1_1:
                                string = String.valueOf(context.getString(R.string.download_item_task_fail_torrent_invalid));
                                break;
                            case SimpleLog.LOG_LEVEL_ERROR:
                                string = String.valueOf(context.getString(R.string.download_item_task_fail_bt_part_subtask_download_failure));
                                break;
                            case SimpleLog.LOG_LEVEL_FATAL:
                                string = String.valueOf(context.getString(R.string.download_item_task_fail_bt_all_subtask_download_failure));
                                break;
                            case SimpleLog.LOG_LEVEL_OFF:
                                string = String.valueOf(context.getString(R.string.download_item_task_fail_bt_subfile_download_failure));
                                break;
                            case SpdyProtocol.PUBKEY_SEQ_ADASH:
                                string = String.valueOf(context.getString(R.string.download_item_task_fail_bt_task_download_failure));
                                break;
                            case SpdyProtocol.PUBKEY_PSEQ_ADASH:
                                string = String.valueOf(context.getString(R.string.download_item_task_fail_bt_file_parse_failure));
                                break;
                            case SpdyProtocol.PUBKEY_SEQ_OPEN:
                                string = String.valueOf(context.getString(R.string.download_item_task_fail_magnet_link_parse_failure));
                                break;
                            case SpdyProtocol.PUBKEY_PSEQ_OPEN:
                                string = String.valueOf(context.getString(R.string.download_item_task_fail_emule_link_parse_failure));
                                break;
                            case com.xunlei.xllib.R.styleable.Toolbar_titleMargins:
                                string = String.valueOf(context.getString(R.string.download_item_task_fail_file_error));
                                break;
                            case com.xunlei.xllib.R.styleable.Toolbar_titleMarginStart:
                                string = String.valueOf(context.getString(R.string.download_item_task_file_not_exist));
                                break;
                            case com.xunlei.xllib.R.styleable.Toolbar_titleMarginEnd:
                                string = String.valueOf(context.getString(R.string.download_item_task_fail_get_resource_name_failure));
                                break;
                            case com.xunlei.xllib.R.styleable.Toolbar_titleMarginTop:
                                string = String.valueOf(context.getString(R.string.download_item_task_fail_resource_server_connection_failure));
                                break;
                            case SpdyProtocol.CUSTOM:
                                string = String.valueOf(context.getString(R.string.download_item_task_fail_resource_server_connection_interruption));
                                break;
                            case com.xunlei.xllib.R.styleable.Toolbar_maxButtonHeight:
                                string = String.valueOf(context.getString(R.string.download_item_task_fail_sensitive_resource_download_limited));
                                break;
                            case com.xunlei.xllib.R.styleable.Toolbar_collapseIcon:
                                string = String.valueOf(context.getString(R.string.download_item_task_fail_continuingly_task_failure));
                                break;
                            case com.xunlei.xllib.R.styleable.Toolbar_collapseContentDescription:
                                string = String.valueOf(context.getString(R.string.download_item_task_fail_download_information_update_failure));
                                break;
                            case com.xunlei.xllib.R.styleable.Toolbar_navigationIcon:
                                string = String.valueOf(context.getString(R.string.download_item_task_fail_task_parameter_error));
                                break;
                            case com.xunlei.xllib.R.styleable.Toolbar_navigationContentDescription:
                                string = String.valueOf(context.getString(R.string.download_item_task_fail_task_count_more_than_upper_limit));
                                break;
                            case com.xunlei.xllib.R.styleable.Toolbar_logoDescription:
                                string = String.valueOf(context.getString(R.string.download_item_task_fail_task_already_exists));
                                break;
                            case com.xunlei.xllib.R.styleable.Toolbar_titleTextColor:
                                string = String.valueOf(context.getString(R.string.download_item_task_fail_task_deleted));
                                break;
                        }
                    }
                    aVar5.g.setText(string);
                } else {
                    aVar5.d.setVisibility(0);
                    aVar5.d.setText("--");
                }
            }
            com.xunlei.downloadprovider.download.tasklist.a.a aVar6 = this.b;
            a aVar7 = this.a;
            if (aVar6.mDownloadedSize != 0) {
                try {
                    j = (long) ((((float) aVar6.mDownloadDurationTime) * ((((float) (aVar6.mDownloadedSize - aVar6.mOriginReceivedSize)) * 1.0f) / ((float) aVar6.mDownloadedSize))) / 1000.0f);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (j > 0) {
                    string = "--";
                } else {
                    i = (int) j;
                    getContext();
                    string = f.a(i);
                    if (string.contains(com.xunlei.download.proguard.c.q)) {
                        string = "--";
                    }
                }
                aVar7.x.setText(string);
                this.a.y.setText(f.b(this.b.mCreateTime));
                aVar2 = this.b;
                aVar3 = this.a;
                if (aVar2.mDownloadedSize > 0 || aVar2.mDownloadDurationTime <= 0) {
                    j = 0;
                } else {
                    j = (long) (((((float) aVar2.mDownloadedSize) * 1.0f) / ((float) aVar2.mDownloadDurationTime)) * 1000.0f);
                }
                if (this.g == null) {
                    this.g = new c();
                    this.g.a = aVar2.mDownloadSpeed;
                } else if (aVar2.mDownloadSpeed > this.g.a) {
                    this.g.a = aVar2.mDownloadSpeed;
                }
                if (this.g.a < j) {
                    this.g.a = j + (j / 3);
                }
                new StringBuilder(" High speed: ").append(this.g.a);
                if (this.g.a != 0) {
                    string = "--";
                } else {
                    string = com.xunlei.downloadprovider.d.a.a(this.g.a) + "/s";
                }
                aVar3.v.setText(string);
                if (n.b(aVar2) && this.e) {
                    aVar3.p.setText(string);
                }
                aVar4 = this.b;
                aVar7 = this.a;
                string = BuildConfig.VERSION_NAME;
                if (aVar4.mDownloadedSize > 0 && aVar4.mDownloadDurationTime > 0) {
                    string = com.xunlei.downloadprovider.d.a.a((long) (((((float) aVar4.mDownloadedSize) * 1.0f) / ((float) aVar4.mDownloadDurationTime)) * 1000.0f)) + "/s";
                }
                if (TextUtils.isEmpty(string)) {
                    string = "--";
                }
                aVar7.w.setText(string);
                aVar6 = this.b;
                aVar5 = this.a;
                if (2 != aVar6.mTaskStatus) {
                    aVar5.r.setVisibility(0);
                    aVar5.q.setText(String.valueOf(aVar6.mResLinkUsed) + MqttTopic.TOPIC_LEVEL_SEPARATOR + String.valueOf(aVar6.mResLinkTotal));
                } else {
                    aVar5.r.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                }
                aVar2 = this.b;
                aVar3 = this.a;
                if (8 != aVar2.mTaskStatus) {
                    if (2 != aVar2.mTaskStatus) {
                        j = -1;
                        if (aVar2.mDownloadSpeed > 0) {
                            j = (aVar2.mFileSize - aVar2.mDownloadedSize) / aVar2.mDownloadSpeed;
                        }
                        if (j != -1 || j > 360000) {
                            string = getContext().getString(R.string.task_detail_infinite);
                        } else {
                            i = (int) j;
                            StringBuilder stringBuilder = new StringBuilder();
                            int i2 = i / 3600;
                            i -= i2 * 3600;
                            int i3 = i / 60;
                            int i4 = i - (i3 * 60);
                            stringBuilder.append(i2 < 10 ? new StringBuilder("0").append(String.valueOf(i2)).toString() : String.valueOf(i2));
                            stringBuilder.append(":");
                            stringBuilder.append(i3 < 10 ? new StringBuilder("0").append(String.valueOf(i3)).toString() : String.valueOf(i3));
                            stringBuilder.append(":");
                            stringBuilder.append(i4 < 10 ? new StringBuilder("0").append(String.valueOf(i4)).toString() : String.valueOf(i4));
                            string = stringBuilder.toString();
                        }
                    } else {
                        string = "--";
                    }
                    aVar3.p.setText(string);
                }
                aVar4 = this.b;
                aVar7 = this.a;
                if (aVar4.mFileSize <= 0) {
                    string = com.xunlei.downloadprovider.download.util.a.b(aVar4.mFileSize);
                } else {
                    string = getContext().getString(R.string.download_item_task_unknown_filesize);
                }
                aVar7.h.setText(string);
                if (8 == aVar4.mTaskStatus) {
                    aVar7.t.setVisibility(0);
                    if (aVar4.mFileSize != 0) {
                        aVar7.s.setText("--");
                    } else {
                        aVar7.s.setText(((int) ((((float) aVar4.mDownloadedSize) / ((float) aVar4.mFileSize)) * 100.0f)) + "%");
                    }
                } else {
                    aVar7.t.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                }
                if (n.b(aVar4) && this.e) {
                    aVar7.d.setText(string);
                }
                aVar6 = this.b;
                aVar5 = this.a;
                aVar5.k.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                if (!n.f(aVar6) && aVar6.mVipAcceleratedChannelSpeed <= 0) {
                    if (!((aVar6.mTaskStatus != 2 && aVar6.mTaskStatus != 1) || aVar6.mHasVipChannelSpeedup || aVar6.mHasLixianSpeedup)) {
                        aVar5.k.setVisibility(0);
                    }
                }
                aVar6 = this.b;
                aVar5 = this.a;
                aVar5.l.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                aVar5.m.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                aVar5.n.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                if (aVar6.mTaskStatus != 2 || aVar6.mTaskStatus == 1) {
                    aVar5.l.setVisibility(0);
                    aVar5.l.setText("\u6682\u505c");
                } else if (aVar6.mTaskStatus == 4) {
                    aVar5.n.setVisibility(0);
                    aVar5.n.setText("\u7ee7\u7eed");
                } else if (aVar6.mTaskStatus == 16) {
                    aVar5.m.setVisibility(0);
                }
                aVar6 = this.b;
                aVar5 = this.a;
                if (aVar6.mTaskStatus == 8) {
                    aVar5.z.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                } else {
                    aVar5.z.setVisibility(0);
                    aVar5.A.setText(f.b(aVar6.mLastModifiedTime));
                }
                aVar7 = this.a;
                LoginHelper.a();
                if ((!LoginHelper.c() && LoginHelper.a().f()) || aVar.mTaskStatus != 2) {
                    aVar7.G.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                } else if (aVar.mDownloadSpeed != 0 || (aVar.mIsEnteredHighSpeedTrial && !n.a(aVar))) {
                    aVar7.G.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                } else {
                    aVar7.G.setVisibility(0);
                    if (b.g(getContext())) {
                        j = aVar.mDownloadSpeed * 2;
                    } else {
                        j = (long) Math.max(0.7d * ((double) this.g.a), (double) aVar.mDownloadSpeed);
                    }
                    if (j > 0) {
                        j = (aVar.mFileSize - aVar.mDownloadedSize) / j;
                    } else {
                        j = 0;
                    }
                    if (j <= 0 || j > 86400) {
                        aVar7.G.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    } else {
                        aVar7.G.setText(new StringBuilder("(\u4f1a\u5458\u4e0b\u8f7d\u5269\u4f59\u6587\u4ef6\u5e73\u5747\u8017\u65f6:").append(f.e(j)).append(j.t).toString());
                    }
                }
                aVar5 = this.a;
                string = aVar.getTaskDownloadUrl().toLowerCase();
                if (string.startsWith("thunder:")) {
                    string = string.substring(SpdyProtocol.PUBKEY_SEQ_ADASH);
                } else if (string.startsWith("thunder")) {
                    string = string.substring(SimpleLog.LOG_LEVEL_OFF);
                }
                aVar5.H.setText(string);
                if (n.b(aVar) && this.f) {
                    this.a.c.setText("\u8d44\u6e90\u5927\u5c0f");
                    this.a.o.setText("\u6700\u5feb\u901f\u5ea6");
                    this.a.j.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    this.a.i.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    this.a.u.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                    return;
                }
            }
            j = 0;
            if (j > 0) {
                i = (int) j;
                getContext();
                string = f.a(i);
                if (string.contains(com.xunlei.download.proguard.c.q)) {
                    string = "--";
                }
            } else {
                string = "--";
            }
            aVar7.x.setText(string);
            this.a.y.setText(f.b(this.b.mCreateTime));
            aVar2 = this.b;
            aVar3 = this.a;
            if (aVar2.mDownloadedSize > 0) {
            }
            j = 0;
            if (this.g == null) {
                this.g = new c();
                this.g.a = aVar2.mDownloadSpeed;
            } else if (aVar2.mDownloadSpeed > this.g.a) {
                this.g.a = aVar2.mDownloadSpeed;
            }
            if (this.g.a < j) {
                this.g.a = j + (j / 3);
            }
            new StringBuilder(" High speed: ").append(this.g.a);
            if (this.g.a != 0) {
                string = com.xunlei.downloadprovider.d.a.a(this.g.a) + "/s";
            } else {
                string = "--";
            }
            aVar3.v.setText(string);
            aVar3.p.setText(string);
            aVar4 = this.b;
            aVar7 = this.a;
            string = BuildConfig.VERSION_NAME;
            string = com.xunlei.downloadprovider.d.a.a((long) (((((float) aVar4.mDownloadedSize) * 1.0f) / ((float) aVar4.mDownloadDurationTime)) * 1000.0f)) + "/s";
            if (TextUtils.isEmpty(string)) {
                string = "--";
            }
            aVar7.w.setText(string);
            aVar6 = this.b;
            aVar5 = this.a;
            if (2 != aVar6.mTaskStatus) {
                aVar5.r.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                aVar5.r.setVisibility(0);
                aVar5.q.setText(String.valueOf(aVar6.mResLinkUsed) + MqttTopic.TOPIC_LEVEL_SEPARATOR + String.valueOf(aVar6.mResLinkTotal));
            }
            aVar2 = this.b;
            aVar3 = this.a;
            if (8 != aVar2.mTaskStatus) {
                if (2 != aVar2.mTaskStatus) {
                    string = "--";
                } else {
                    j = -1;
                    if (aVar2.mDownloadSpeed > 0) {
                        j = (aVar2.mFileSize - aVar2.mDownloadedSize) / aVar2.mDownloadSpeed;
                    }
                    if (j != -1) {
                    }
                    string = getContext().getString(R.string.task_detail_infinite);
                }
                aVar3.p.setText(string);
            }
            aVar4 = this.b;
            aVar7 = this.a;
            if (aVar4.mFileSize <= 0) {
                string = getContext().getString(R.string.download_item_task_unknown_filesize);
            } else {
                string = com.xunlei.downloadprovider.download.util.a.b(aVar4.mFileSize);
            }
            aVar7.h.setText(string);
            if (8 == aVar4.mTaskStatus) {
                aVar7.t.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                aVar7.t.setVisibility(0);
                if (aVar4.mFileSize != 0) {
                    aVar7.s.setText(((int) ((((float) aVar4.mDownloadedSize) / ((float) aVar4.mFileSize)) * 100.0f)) + "%");
                } else {
                    aVar7.s.setText("--");
                }
            }
            aVar7.d.setText(string);
            aVar6 = this.b;
            aVar5 = this.a;
            aVar5.k.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            aVar5.k.setVisibility(0);
            aVar6 = this.b;
            aVar5 = this.a;
            aVar5.l.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            aVar5.m.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            aVar5.n.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            if (aVar6.mTaskStatus != 2) {
            }
            aVar5.l.setVisibility(0);
            aVar5.l.setText("\u6682\u505c");
            aVar6 = this.b;
            aVar5 = this.a;
            if (aVar6.mTaskStatus == 8) {
                aVar5.z.setVisibility(0);
                aVar5.A.setText(f.b(aVar6.mLastModifiedTime));
            } else {
                aVar5.z.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            }
            aVar7 = this.a;
            LoginHelper.a();
            if (!LoginHelper.c()) {
            }
            if (aVar.mDownloadSpeed != 0) {
            }
            aVar7.G.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            aVar5 = this.a;
            string = aVar.getTaskDownloadUrl().toLowerCase();
            if (string.startsWith("thunder:")) {
                string = string.substring(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else if (string.startsWith("thunder")) {
                string = string.substring(SimpleLog.LOG_LEVEL_OFF);
            }
            aVar5.H.setText(string);
            if (n.b(aVar)) {
            }
        }
    }

    public final void a(boolean z) {
        if (z) {
            this.a.a.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.a.b.setVisibility(0);
            return;
        }
        this.a.a.setVisibility(0);
        this.a.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }

    public final void a(int i) {
        if (i > 2) {
            this.a.I.setImageResource(R.drawable.detail_speed_arrow_down);
        }
        this.a.H.setListener(null);
    }

    static /* synthetic */ void e(TaskDetailSpeedInfoView taskDetailSpeedInfoView) {
        taskDetailSpeedInfoView.e = true;
        taskDetailSpeedInfoView.a.F.setImageResource(R.drawable.detail_speed_arrow_down);
        taskDetailSpeedInfoView.a.D.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }
}
