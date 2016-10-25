package com.xunlei.tdlive.withdraw;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.d;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xunlei.download.proguard.c;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.protocol.XLLiveGetPayRecordRequest;
import com.xunlei.tdlive.protocol.XLLiveGetPayRecordRequest.Record;
import com.xunlei.tdlive.protocol.XLLiveGetPayRecordRequest.RecordListResp;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;
import com.xunlei.tdlive.user.f;
import java.util.List;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttTopic;

// compiled from: WithdrawRecordPage.java
public class l extends a implements OnItemClickListener, d<ListView>, com.xunlei.tdlive.usercenter.d.b {
    private a m;
    private TextView n;
    private PullToRefreshListView o;

    // compiled from: WithdrawRecordPage.java
    private class a extends com.xunlei.tdlive.usercenter.d<Record> implements ObjectCallBack {
        private int e;
        private XLLiveGetPayRecordRequest f;
        private SparseBooleanArray g;
        private int h;

        public a(Context context) {
            super(context);
            this.e = 0;
            this.g = new SparseBooleanArray();
            this.h = 0;
        }

        public void d(int i) {
            this.g.put(i, !this.g.get(i));
            notifyDataSetChanged();
        }

        public View a(Context context, Record record, ViewGroup viewGroup) {
            b bVar = new b();
            View inflate = LayoutInflater.from(l.this.getActivity()).inflate(R.layout.xllive_withdraw_item_with_error, viewGroup, false);
            inflate.setTag(bVar);
            bVar.a = (TextView) inflate.findViewById(R.id.tvWidthdrawNum);
            bVar.b = (TextView) inflate.findViewById(R.id.tvWidthdrawTime);
            bVar.c = (TextView) inflate.findViewById(R.id.tvWidthdrawResult);
            l.this = (TextView) inflate.findViewById(R.id.tvError);
            return inflate;
        }

        public void a(View view, Context context, Record record) {
            CharSequence toString;
            b bVar = (b) view.getTag();
            String format = String.format("%.2f\u5143", new Object[]{Float.valueOf(((float) record.money) / 100.0f)});
            if (record.type == 1) {
                toString = new StringBuilder(MqttTopic.SINGLE_LEVEL_WILDCARD).append(format).toString();
                bVar.a.setTextColor(a().getResources().getColor(R.color.golden));
            } else {
                toString = new StringBuilder(c.q).append(format).toString();
                bVar.a.setTextColor(-16777216);
            }
            bVar.a.setText(toString);
            bVar.b.setText(record.status_time);
            bVar.c.setText(record.info);
            if (TextUtils.isEmpty(record.error)) {
                a(bVar.c, 1);
                l.this.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                return;
            }
            int i;
            boolean z = this.g.get(this.h);
            a(bVar.c, z ? SimpleLog.LOG_LEVEL_DEBUG : MqttConnectOptions.MQTT_VERSION_3_1);
            TextView textView = l.this;
            if (z) {
                i = 0;
            } else {
                i = 8;
            }
            textView.setVisibility(i);
            l.this.setText(record.error);
        }

        private void a(TextView textView, int i) {
            switch (i) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                case SimpleLog.LOG_LEVEL_DEBUG:
                    textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.xllive_expand_arrow_up, 0);
                case MqttConnectOptions.MQTT_VERSION_3_1:
                    textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.xllive_expand_arrow_down, 0);
                default:
                    break;
            }
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            this.h = i;
            return super.getView(i, view, viewGroup);
        }

        public void a(int i) {
            if (b()) {
                this.f = new XLLiveGetPayRecordRequest(f.a().k(), f.a().l(), this.e);
                this.f.send(this);
            }
        }

        public void d() {
            if (this.f != null) {
                this.f.cancel();
            }
        }

        public void onResponse(int i, String str, Object obj) {
            if (l.this.c()) {
                List list = null;
                if (i == 0 && (obj instanceof RecordListResp)) {
                    RecordListResp recordListResp = (RecordListResp) obj;
                    if (recordListResp.data != null) {
                        l.this.f(recordListResp.data.sum);
                        list = recordListResp.data.lists;
                    }
                }
                if (list != null && list.size() > 0) {
                    this.e++;
                    a(list);
                }
                c();
                e();
                return;
            }
            c();
        }

        public void b(int i) {
        }
    }

    // compiled from: WithdrawRecordPage.java
    private static final class b {
        TextView a;
        TextView b;
        TextView c;
        TextView d;

        private b() {
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LinearLayout linearLayout = (LinearLayout) layoutInflater.inflate(R.layout.xllive_withdraw_record, viewGroup, false);
        this.o = (PullToRefreshListView) linearLayout.findViewById(R.id.list);
        ListView listView = (ListView) this.o.getRefreshableView();
        listView.setOnItemClickListener(this);
        this.o.setOnRefreshListener(this);
        this.o.setMode(Mode.PULL_FROM_START);
        this.n = (TextView) linearLayout.findViewById(R.id.tvWithdrawTotal);
        f(0);
        this.m = new a(getActivity());
        this.m.a(this);
        listView.setAdapter(this.m);
        this.m.a(0);
        listView.setEmptyView(linearLayout.findViewById(R.id.lvEmpty));
        return linearLayout;
    }

    public void onRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
        this.m.a(0);
    }

    public void b() {
        this.o.m();
    }

    private void f(int i) {
        this.n.setText(getResources().getString(R.string.withdraw_history_total, new Object[]{Float.valueOf(((float) i) / 100.0f)}));
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (!TextUtils.isEmpty(((Record) adapterView.getAdapter().getItem(i)).error)) {
            this.m.d(i - 1);
        }
    }
}
