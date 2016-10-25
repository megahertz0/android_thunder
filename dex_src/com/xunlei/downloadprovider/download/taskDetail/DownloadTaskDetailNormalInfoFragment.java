package com.xunlei.downloadprovider.download.taskDetail;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.c$a;
import com.xunlei.downloadprovider.download.taskDetail.a.h;
import com.xunlei.downloadprovider.download.taskDetail.widget.DownloadTaskNameAndIconView;
import com.xunlei.downloadprovider.download.taskDetail.widget.TaskDetailRedEnvelopeView;
import com.xunlei.downloadprovider.download.taskDetail.widget.TaskDetailShareBar;
import com.xunlei.downloadprovider.download.taskDetail.widget.TaskDetailSniffInfo;
import com.xunlei.downloadprovider.download.taskDetail.widget.TaskDetailSpeedInfoView;
import com.xunlei.downloadprovider.download.util.n;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.downloadprovider.service.downloads.task.b.c;
import com.xunlei.downloadprovider.util.r;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import java.util.List;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class DownloadTaskDetailNormalInfoFragment extends Fragment {
    private static final String k;
    ListView a;
    com.xunlei.downloadprovider.download.a.a b;
    boolean c;
    boolean d;
    a e;
    c f;
    com.xunlei.downloadprovider.download.tasklist.a.a g;
    a h;
    long i;
    boolean j;
    private List<h> l;
    private boolean m;
    private boolean n;
    private boolean o;
    private int[][] p;

    class a extends BaseAdapter {
        a() {
        }

        public final Object getItem(int i) {
            return DownloadTaskDetailNormalInfoFragment.this.g;
        }

        public final long getItemId(int i) {
            return 0;
        }

        public final View getView(int i, View view, ViewGroup viewGroup) {
            int itemViewType = getItemViewType(i);
            if (itemViewType == 0) {
                return DownloadTaskDetailNormalInfoFragment.a(DownloadTaskDetailNormalInfoFragment.this, view);
            }
            if (itemViewType == 1) {
                return DownloadTaskDetailNormalInfoFragment.b(DownloadTaskDetailNormalInfoFragment.this, view);
            }
            if (itemViewType == 2) {
                return DownloadTaskDetailNormalInfoFragment.c(DownloadTaskDetailNormalInfoFragment.this, view);
            }
            if (itemViewType == 3) {
                return DownloadTaskDetailNormalInfoFragment.d(DownloadTaskDetailNormalInfoFragment.this, view);
            }
            if (itemViewType == 4) {
                return DownloadTaskDetailNormalInfoFragment.e(DownloadTaskDetailNormalInfoFragment.this, view);
            }
            return itemViewType == 6 ? DownloadTaskDetailNormalInfoFragment.f(DownloadTaskDetailNormalInfoFragment.this, view) : DownloadTaskDetailNormalInfoFragment.g(DownloadTaskDetailNormalInfoFragment.this, view);
        }

        public final int getViewTypeCount() {
            return SimpleLog.LOG_LEVEL_OFF;
        }

        public final int getItemViewType(int i) {
            if (i == 0) {
                return 0;
            }
            return DownloadTaskDetailNormalInfoFragment.this.p[DownloadTaskDetailNormalInfoFragment.h(DownloadTaskDetailNormalInfoFragment.this)][i];
        }

        public final int getCount() {
            int i = MqttConnectOptions.MQTT_VERSION_3_1;
            if (DownloadTaskDetailNormalInfoFragment.this.g == null) {
                return MqttConnectOptions.MQTT_VERSION_3_1;
            }
            if (DownloadTaskDetailNormalInfoFragment.this.m) {
                i = MqttConnectOptions.MQTT_VERSION_3_1_1;
            }
            if (DownloadTaskDetailNormalInfoFragment.this.f()) {
                i++;
            }
            if (DownloadTaskDetailNormalInfoFragment.this.e()) {
                i++;
            }
            return DownloadTaskDetailNormalInfoFragment.this.d() ? i + 1 : i;
        }
    }

    private static class b {
        public View a;
        TextView b;
        TextView c;
        ImageView d;
        View e;
        TextView f;

        private b() {
        }
    }

    public DownloadTaskDetailNormalInfoFragment() {
        this.d = false;
        this.l = null;
        this.i = 0;
        this.j = false;
        this.m = true;
        this.n = true;
        this.o = true;
    }

    static /* synthetic */ boolean a(DownloadTaskDetailNormalInfoFragment downloadTaskDetailNormalInfoFragment, long j) {
        boolean z;
        StringBuilder stringBuilder = new StringBuilder("isCurrentImageAdRequestValid: ");
        if (j == downloadTaskDetailNormalInfoFragment.i) {
            z = true;
        } else {
            z = false;
        }
        stringBuilder.append(z);
        return j == downloadTaskDetailNormalInfoFragment.i;
    }

    static /* synthetic */ View b(DownloadTaskDetailNormalInfoFragment downloadTaskDetailNormalInfoFragment, View view) {
        if (view == null) {
            view = View.inflate(downloadTaskDetailNormalInfoFragment.getActivity(), R.layout.layout_task_detail_play_downing, null);
            b bVar = new b();
            bVar.a = view.findViewById(R.id.play_downloading_container);
            bVar.b = (TextView) view.findViewById(R.id.play_downloading_tip);
            bVar.c = (TextView) view.findViewById(com.xunlei.xllib.R.id.text);
            bVar.d = (ImageView) view.findViewById(com.xunlei.xllib.R.id.icon);
            bVar.e = view.findViewById(R.id.play_downloading_btn);
            bVar.f = (TextView) view.findViewById(R.id.re_download);
            view.setTag(bVar);
        }
        b bVar2 = (b) view.getTag();
        bVar2.a.setOnClickListener(new aj(downloadTaskDetailNormalInfoFragment));
        bVar2.f.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        bVar2.e.setVisibility(0);
        if (!com.xunlei.downloadprovider.download.util.a.a(downloadTaskDetailNormalInfoFragment.g)) {
            bVar2.c.setText("\u8fb9\u4e0b\u8fb9\u64ad");
            bVar2.b.setText("\u5df2\u4e0b\u8f7d\u90e8\u5206\u53ef\u64ad\u653e");
            bVar2.d.setImageResource(R.drawable.download_detail_play);
        } else if (n.d(downloadTaskDetailNormalInfoFragment.g)) {
            if (n.d(downloadTaskDetailNormalInfoFragment.g)) {
                bVar2.c.setText("\u5b89\u88c5");
                bVar2.d.setImageResource(R.drawable.download_detail_install);
                com.xunlei.downloadprovider.download.tasklist.a.a aVar = downloadTaskDetailNormalInfoFragment.g;
                int i = R.string.download_item_button_open;
                c$a a = com.xunlei.downloadprovider.a.c.a(downloadTaskDetailNormalInfoFragment.getActivity(), aVar.mLocalFileName);
                if (a != null) {
                    i = aVar.h;
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    if (aVar.i == 0 || elapsedRealtime - aVar.i >= 3000) {
                        i = com.xunlei.downloadprovider.a.c.a(downloadTaskDetailNormalInfoFragment.getActivity(), a);
                        aVar.h = i;
                        aVar.i = elapsedRealtime;
                    }
                    if (i == 4 || i == 5) {
                        i = R.string.download_item_button_open;
                        bVar2.d.setImageResource(R.drawable.download_detail_open);
                    } else {
                        i = R.string.download_item_button_install;
                        bVar2.d.setImageResource(R.drawable.download_detail_install);
                    }
                }
                bVar2.c.setText(i);
            } else if (n.a(downloadTaskDetailNormalInfoFragment.g)) {
                bVar2.c.setText("\u64ad\u653e");
                bVar2.d.setImageResource(R.drawable.download_detail_play);
            } else {
                bVar2.c.setText("\u6253\u5f00");
                bVar2.d.setImageResource(R.drawable.download_detail_open);
            }
            bVar2.b.setText("\u6587\u4ef6\u4e0b\u8f7d\u5b8c\u6210");
        } else {
            bVar2.b.setText("\u6587\u4ef6\u5df2\u79fb\u9664");
            bVar2.f.setVisibility(0);
            bVar2.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
        return view;
    }

    static /* synthetic */ View g(DownloadTaskDetailNormalInfoFragment downloadTaskDetailNormalInfoFragment, View view) {
        View bVar;
        h hVar;
        if (!an.a().b(0) && downloadTaskDetailNormalInfoFragment.j && b()) {
            String str = "adv_download_detail_pv";
            com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a.a(g.a("android_advertise", str, str).a("net_type", com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a.a(), MqttConnectOptions.MQTT_VERSION_3_1));
            an.a().a(0);
        }
        new StringBuilder("getImageAdView convertView == null: ").append(view == null);
        if (view == null) {
            bVar = new com.xunlei.downloadprovider.download.taskDetail.widget.b(downloadTaskDetailNormalInfoFragment.getActivity());
        } else {
            bVar = view;
        }
        com.xunlei.downloadprovider.download.taskDetail.widget.a aVar = (com.xunlei.downloadprovider.download.taskDetail.widget.a) bVar;
        if (downloadTaskDetailNormalInfoFragment.l == null || downloadTaskDetailNormalInfoFragment.l.isEmpty()) {
            hVar = null;
        } else {
            hVar = (h) downloadTaskDetailNormalInfoFragment.l.get(0);
        }
        if (!b() || hVar == null) {
            aVar.a();
        } else if (!an.a().b(1) && downloadTaskDetailNormalInfoFragment.j && b()) {
            aVar.a(hVar);
            String d = hVar.d();
            str = hVar.p();
            String a = hVar.a();
            String j = hVar.j();
            new StringBuilder("reportTaskDetailImageAdShow ad_type: ").append(d).append(" advid: ").append(str).append(" material: ").append(a).append(" background: ").append(j);
            String str2 = "adv_download_detail_show";
            com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.a.a(g.a("android_advertise", str2, str2).a("ad_type", d, MqttConnectOptions.MQTT_VERSION_3_1).a("advid", str, MqttConnectOptions.MQTT_VERSION_3_1).a("material", a, MqttConnectOptions.MQTT_VERSION_3_1).a("background", j, MqttConnectOptions.MQTT_VERSION_3_1));
            an.a().a(1);
        }
        return bVar;
    }

    static {
        k = DownloadTaskDetailNormalInfoFragment.class.getSimpleName();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        boolean z;
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.task_detail_normal_info_layout, null);
        this.m = r.c().m.a();
        r.c cVar = r.c().m;
        if (cVar.a == null || cVar.a.optInt("show_download_url") != 1) {
            z = false;
        } else {
            z = true;
        }
        this.n = z;
        this.p = new int[][]{new int[]{0, 3, 5}, new int[]{0, 3, 6, 5}, new int[]{0, 3, 4, 5}, new int[]{0, 3, 6, 4, 5}, new int[]{0, 1, 3, 5}, new int[]{0, 1, 3, 6, 5}, new int[]{0, 1, 3, 4, 5}, new int[]{0, 1, 3, 6, 4, 5}, new int[]{0, 3, 5, 2}, new int[]{0, 3, 6, 5, 2}, new int[]{0, 3, 4, 5, 2}, new int[]{0, 3, 6, 4, 5, 2}, new int[]{0, 1, 3, 5, 2}, new int[]{0, 1, 3, 6, 5, 2}, new int[]{0, 1, 3, 4, 5, 2}, new int[]{0, 1, 3, 6, 4, 5, 2}};
        this.a = (ListView) inflate.findViewById(R.id.task_detail_list_view);
        this.h = new a();
        this.a.setAdapter(this.h);
        this.l = new ArrayList();
        return inflate;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.xunlei.downloadprovider.download.tasklist.a.a r4) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.download.taskDetail.DownloadTaskDetailNormalInfoFragment.a(com.xunlei.downloadprovider.download.tasklist.a.a):void");
        /*
        this = this;
        r0 = 1;
        r3.g = r4;
        r3.c = r0;
        r1 = r3.h;
        if (r1 == 0) goto L_0x000e;
    L_0x0009:
        r1 = r3.h;
        r1.notifyDataSetChanged();
    L_0x000e:
        if (r4 == 0) goto L_0x004e;
    L_0x0010:
        r1 = r4.mCreateOrigin;
        if (r1 == 0) goto L_0x004f;
    L_0x0014:
        r1 = r4.mCreateOrigin;
        r2 = "sniff/sniff";
        r1 = r1.equals(r2);
        if (r1 != 0) goto L_0x002a;
    L_0x001f:
        r1 = r4.mCreateOrigin;
        r2 = "manual/sniff_choose_download";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x004f;
    L_0x002a:
        r1 = r4.mWebsiteName;
        r1 = android.text.TextUtils.isEmpty(r1);
        if (r1 != 0) goto L_0x004f;
    L_0x0032:
        r3.d = r0;
        r0 = new java.lang.StringBuilder;
        r1 = " search key word: ";
        r0.<init>(r1);
        r1 = r4.mSniffKeyword;
        r0 = r0.append(r1);
        r1 = "   isSniff:  ";
        r0 = r0.append(r1);
        r1 = r3.d;
        r0.append(r1);
    L_0x004e:
        return;
    L_0x004f:
        r0 = 0;
        goto L_0x0032;
        */
    }

    public void onDestroy() {
        super.onDestroy();
        a();
        com.xunlei.downloadprovider.download.taskDetail.a.b.b();
        an.b();
    }

    private boolean d() {
        return this.g != null && this.g.s;
    }

    private boolean e() {
        return this.d && this.n;
    }

    private boolean f() {
        return n.a(this.g) || n.b(this.g);
    }

    static boolean b() {
        return r.c().e.a().h;
    }

    final void a() {
        this.i = -1;
        this.l.clear();
        if (this.h != null) {
            this.h.notifyDataSetChanged();
        }
        an.a().a.clear();
    }

    static /* synthetic */ View a(DownloadTaskDetailNormalInfoFragment downloadTaskDetailNormalInfoFragment, View view) {
        View downloadTaskNameAndIconView;
        if (view == null) {
            downloadTaskNameAndIconView = new DownloadTaskNameAndIconView(downloadTaskDetailNormalInfoFragment.getActivity());
        } else {
            downloadTaskNameAndIconView = view;
        }
        ((DownloadTaskNameAndIconView) downloadTaskNameAndIconView).a(downloadTaskDetailNormalInfoFragment.g);
        return downloadTaskNameAndIconView;
    }

    static /* synthetic */ View c(DownloadTaskDetailNormalInfoFragment downloadTaskDetailNormalInfoFragment, View view) {
        View taskDetailShareBar;
        if (view == null) {
            taskDetailShareBar = new TaskDetailShareBar(downloadTaskDetailNormalInfoFragment.getActivity());
        } else {
            taskDetailShareBar = view;
        }
        ((TaskDetailShareBar) taskDetailShareBar).setCurrentTask(downloadTaskDetailNormalInfoFragment.g);
        return taskDetailShareBar;
    }

    static /* synthetic */ View d(DownloadTaskDetailNormalInfoFragment downloadTaskDetailNormalInfoFragment, View view) {
        View taskDetailSpeedInfoView;
        if (view == null) {
            taskDetailSpeedInfoView = new TaskDetailSpeedInfoView(downloadTaskDetailNormalInfoFragment.getActivity());
        } else {
            taskDetailSpeedInfoView = view;
        }
        TaskDetailSpeedInfoView taskDetailSpeedInfoView2 = (TaskDetailSpeedInfoView) taskDetailSpeedInfoView;
        taskDetailSpeedInfoView2.setNeedFold(true);
        taskDetailSpeedInfoView2.setControl(downloadTaskDetailNormalInfoFragment.b);
        if (downloadTaskDetailNormalInfoFragment.c) {
            taskDetailSpeedInfoView2.a(downloadTaskDetailNormalInfoFragment.g);
            taskDetailSpeedInfoView2.setTaskCountInfo(downloadTaskDetailNormalInfoFragment.f);
            downloadTaskDetailNormalInfoFragment.c = false;
        }
        taskDetailSpeedInfoView2.b(downloadTaskDetailNormalInfoFragment.g);
        return taskDetailSpeedInfoView;
    }

    static /* synthetic */ View e(DownloadTaskDetailNormalInfoFragment downloadTaskDetailNormalInfoFragment, View view) {
        View taskDetailSniffInfo;
        TaskDetailSniffInfo taskDetailSniffInfo2;
        if (view == null) {
            taskDetailSniffInfo = new TaskDetailSniffInfo(downloadTaskDetailNormalInfoFragment.getActivity());
            taskDetailSniffInfo2 = (TaskDetailSniffInfo) taskDetailSniffInfo;
            taskDetailSniffInfo2.setClickWebSiteListener(new ak(downloadTaskDetailNormalInfoFragment));
            taskDetailSniffInfo2.setClickSearchAgainListener(new al(downloadTaskDetailNormalInfoFragment));
        } else {
            taskDetailSniffInfo = view;
        }
        taskDetailSniffInfo2 = (TaskDetailSniffInfo) taskDetailSniffInfo;
        taskDetailSniffInfo2.setCurrentTask(downloadTaskDetailNormalInfoFragment.g);
        if (taskDetailSniffInfo2.f) {
            com.xunlei.downloadprovider.download.tasklist.a.a aVar = taskDetailSniffInfo2.e;
            if (TextUtils.isEmpty(aVar.mSniffKeyword)) {
                taskDetailSniffInfo2.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                taskDetailSniffInfo2.c.setVisibility(0);
                taskDetailSniffInfo2.d.setText(aVar.mSniffKeyword);
            }
            if (TextUtils.isEmpty(aVar.mWebsiteName)) {
                taskDetailSniffInfo2.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                taskDetailSniffInfo2.b.setVisibility(0);
                taskDetailSniffInfo2.b.setText(new StringBuilder("\u4e0b\u8f7d\u7f51\u5740:").append(aVar.mWebsiteName).toString());
            }
            taskDetailSniffInfo2.a.setText(aVar.mRefUrl);
        }
        return taskDetailSniffInfo;
    }

    static /* synthetic */ View f(DownloadTaskDetailNormalInfoFragment downloadTaskDetailNormalInfoFragment, View view) {
        return view == null ? new TaskDetailRedEnvelopeView(downloadTaskDetailNormalInfoFragment.getActivity()) : view;
    }

    static /* synthetic */ int h(DownloadTaskDetailNormalInfoFragment downloadTaskDetailNormalInfoFragment) {
        int i;
        int i2 = 1;
        StringBuilder append = new StringBuilder().append(BuildConfig.VERSION_NAME + (downloadTaskDetailNormalInfoFragment.m ? 1 : 0));
        if (downloadTaskDetailNormalInfoFragment.f()) {
            i = 1;
        } else {
            i = 0;
        }
        append = new StringBuilder().append(append.append(i).toString());
        if (downloadTaskDetailNormalInfoFragment.e()) {
            i = 1;
        } else {
            i = 0;
        }
        StringBuilder append2 = new StringBuilder().append(append.append(i).toString());
        if (!downloadTaskDetailNormalInfoFragment.d()) {
            i2 = 0;
        }
        return Integer.valueOf(append2.append(i2).toString(), SimpleLog.LOG_LEVEL_DEBUG).intValue();
    }
}
