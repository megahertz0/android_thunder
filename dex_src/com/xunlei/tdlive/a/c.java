package com.xunlei.tdlive.a;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.umeng.socialize.media.WeiXinShareContent;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.control.BadgeView;
import com.xunlei.tdlive.control.RoundProgressBar;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveGetGiftListRequest;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.ac;
import com.xunlei.tdlive.util.d;
import com.xunlei.tdlive.util.r;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.HashSet;
import java.util.Iterator;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: GiftAdapter.java
public class c extends k<Runnable> implements OnItemClickListener, Runnable {
    private r b;
    private HashSet<Integer> c;
    private SparseArray<a> d;
    private SparseArray<Point> e;
    private a f;
    private b g;
    private Context h;
    private Boolean i;
    private Handler j;

    // compiled from: GiftAdapter.java
    static class a extends i<JsonWrapper> {
        private int b;

        // compiled from: GiftAdapter.java
        class a {
            RoundProgressBar a;
            ImageView b;
            TextView c;
            View d;
            Drawable e;
            BadgeView f;

            a() {
            }
        }

        public a(JsonWrapper jsonWrapper) {
            this.b = -1;
            a(jsonWrapper);
        }

        public int a(int i, boolean z) {
            if (z && this.b == i && this.b != -1) {
                this.b = -1;
            } else {
                this.b = i;
            }
            notifyDataSetChanged();
            return this.b;
        }

        public int a(int i, int i2, int i3) {
            int i4 = 0;
            JsonWrapper a = a(i);
            if (a != null) {
                a.putInt("giftnum", i2);
                a.putInt("remaintime", i3);
                a.putInt("tick", 0);
                if (a.getInt("level", 0) == 0) {
                    if (i2 <= 0) {
                        i4 = 1;
                        if (i == this.b) {
                            this.b = -1;
                            i4 = SimpleLog.LOG_LEVEL_DEBUG;
                        }
                    }
                    notifyDataSetChanged();
                }
            }
            return i4;
        }

        public boolean a() {
            int i = 0;
            boolean z = false;
            for (int i2 = 0; i2 < getCount(); i2++) {
                JsonWrapper a = a(i2);
                int i3 = a.getInt("level", 0);
                int i4 = a.getInt("giftnum", 0);
                int i5 = a.getInt("tick", 0);
                int i6 = a.getInt("remaintime", 0);
                if (i3 == 0 && i4 <= 0 && i6 > 0) {
                    a.putInt("tick", i5 + 1);
                    if (i5 + 1 >= i6) {
                        i = 1;
                        z = true;
                    } else {
                        i = 1;
                    }
                }
            }
            if (i != 0) {
                notifyDataSetChanged();
            }
            return z;
        }

        public void a(JsonWrapper jsonWrapper, boolean z, boolean z2) {
            a(jsonWrapper);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            int i2;
            LayoutParams layoutParams;
            JsonWrapper a = a(i);
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.xllive_gift_list_item, viewGroup, false);
                a aVar2 = new a();
                aVar2.a = (RoundProgressBar) view.findViewById(R.id.remain_time);
                aVar2.a.setRoundWidth(d.a(viewGroup.getContext(), 1.0f));
                aVar2.a.setCricleProgressColor(-1895825408);
                aVar2.a.setCricleColor(1291845631);
                aVar2.b = (ImageView) view.findViewById(R.id.gift_image);
                aVar2.c = (TextView) view.findViewById(R.id.gift_name);
                aVar2.d = view.findViewById(R.id.select_flag);
                aVar2.e = viewGroup.getResources().getDrawable(R.drawable.xllive_coin);
                aVar2.e.setBounds(0, 0, aVar2.e.getMinimumWidth(), aVar2.e.getMinimumHeight());
                aVar2.f = (BadgeView) view.findViewById(R.id.gift_num);
                view.setTag(aVar2);
                aVar = aVar2;
            } else {
                aVar = (a) view.getTag();
            }
            int i3 = a.getInt("giftnum", 0);
            com.xunlei.tdlive.util.a.a(viewGroup.getContext()).a(aVar.b, a.getString(WeiXinShareContent.TYPE_IMAGE, BuildConfig.VERSION_NAME));
            int i4 = a.getInt("costnum", 0);
            aVar.c.setTextColor(-1);
            aVar.c.setText(i4 == 0 ? a.getString(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_NAME, BuildConfig.VERSION_NAME) : String.valueOf(i4));
            aVar.c.setCompoundDrawables(null, null, i4 == 0 ? null : aVar.e, null);
            if (a.getInt("level", 0) == 0) {
                i2 = a.getInt("tick", 0);
                int i5 = a.getInt("remaintime", 0);
                i4 = i5 - i2;
                if (i5 > 0 && i4 > 0) {
                    aVar.a.setVisibility(0);
                    aVar.a.setProgress(i4);
                    aVar.a.setMax(i5);
                    aVar.c.setTextColor(-298434);
                    aVar.c.setText(ac.a(i4 >= 3600 ? "HH:mm:ss" : "mm:ss", (long) (i4 * 1000), i4 >= 3600 ? "00:00:00" : "00:00"));
                    aVar.c.setCompoundDrawables(null, null, null, null);
                } else if (i5 != 0 || i3 > 0) {
                    aVar.a.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                } else {
                    aVar.a.setVisibility(0);
                    aVar.a.setProgress(1);
                    aVar.a.setMax(1);
                }
                if (i3 > 0) {
                    aVar.f.setText(String.valueOf(i3));
                    aVar.f.setVisibility(0);
                } else {
                    aVar.f.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                }
            } else {
                aVar.a.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                aVar.f.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            }
            aVar.d.setVisibility(i == this.b ? 0 : MqttConnectOptions.MQTT_VERSION_3_1_1);
            i2 = view.getResources().getDisplayMetrics().widthPixels / 4;
            AbsListView.LayoutParams layoutParams2 = (AbsListView.LayoutParams) view.getLayoutParams();
            if (layoutParams2 == null) {
                layoutParams = new AbsListView.LayoutParams(i2, i2);
            } else {
                layoutParams2.width = i2;
                layoutParams2.height = i2;
            }
            view.setLayoutParams(layoutParams);
            return view;
        }
    }

    // compiled from: GiftAdapter.java
    public static interface b {
        void a(int i);

        void a(boolean z, boolean z2);

        boolean a(boolean z, JsonWrapper jsonWrapper, boolean z2);
    }

    public static void a(Context context) {
        c cVar = new c(context, null);
        cVar.b(new d(cVar, context));
    }

    public c(Context context, b bVar) {
        this.b = new r(this);
        this.d = new SparseArray();
        this.g = bVar;
        this.h = context.getApplicationContext();
        this.j = new Handler();
    }

    public void a() {
        this.j.removeCallbacksAndMessages(null);
        if (this.e != null) {
            this.e.clear();
        }
        if (this.c != null) {
            this.c.clear();
        }
        this.b.c();
        this.d.clear();
        this.g = null;
        this.e = null;
        this.c = null;
        this.f = null;
        this.h = null;
        this.j = null;
    }

    public int a(int i) {
        return i < 0 ? a(null) : a(b(i));
    }

    public int a(Point point) {
        if (this.g == null) {
            return -1;
        }
        if (point != null) {
            this.g.a(point.x);
            a b = b(point);
            if (b != null) {
                JsonWrapper a = b.a(point.y);
                if (a.getInt("level", 0) > 0 || a.getInt("giftnum", 0) > 0) {
                    this.f = b;
                    this.f.a(point.y, false);
                    this.g.a(true, a, true);
                }
            }
            return point.x;
        } else if (this.f == null) {
            return -1;
        } else {
            this.f.a(-1, false);
            this.f = null;
            this.g.a(false, null, true);
            return -1;
        }
    }

    public void a(int i, int i2, int i3) {
        Point b = b(i);
        a b2 = b(b);
        if (b2 != null) {
            int a = b2.a(b.y, i2, i3);
            if (a == 2 && this.f == b2) {
                this.f = null;
                if (this.g != null) {
                    this.g.a(false, null, false);
                }
            }
            if (a != 0) {
                b(null);
            }
        }
    }

    public Point b(int i) {
        return this.e != null ? (Point) this.e.get(i) : null;
    }

    protected a b(Point point) {
        return point != null ? a(point.x, false) : null;
    }

    protected a c(int i) {
        return b(b(i));
    }

    protected a a(int i, boolean z) {
        return z ? c(i) : (a) this.d.get(i);
    }

    public void a(Runnable runnable, boolean z, boolean z2) {
        if (b()) {
            this.i = Boolean.valueOf(true);
            this.b.c();
            if (this.g != null) {
                this.g.a(false, z);
            }
            if (z) {
                this.i = Boolean.valueOf(false);
                new Thread(new e(this)).start();
            }
            new XLLiveGetGiftListRequest(f.a().k(), f.a().l()).send(new g(this, runnable));
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ListAdapter aVar = new a(d(i));
        View gridView = new GridView(viewGroup.getContext());
        gridView.setHorizontalSpacing(0);
        gridView.setVerticalSpacing(0);
        gridView.setNumColumns(MqttConnectOptions.MQTT_VERSION_3_1_1);
        gridView.setStretchMode(SimpleLog.LOG_LEVEL_DEBUG);
        gridView.setAdapter(aVar);
        gridView.setSelector(17170445);
        gridView.setOnItemClickListener(this);
        gridView.setTag(new Integer(i));
        this.d.put(i, aVar);
        return gridView;
    }

    protected void a(int i, View view) {
        this.d.delete(i);
    }

    public void run() {
        if (this.c != null) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                a a = a(((Integer) it.next()).intValue(), false);
                if (a != null && a.a()) {
                    b(null);
                    return;
                }
            }
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.g != null) {
            this.g.a(false, null, false);
            a aVar = (a) adapterView.getAdapter();
            if (!(this.f == null || this.f == aVar)) {
                this.f.a(-1, false);
            }
            if (aVar.a(i, true) != -1) {
                if (this.g.a(true, (JsonWrapper) adapterView.getAdapter().getItem(i), false)) {
                    this.f = aVar;
                    return;
                }
                if (this.f != null) {
                    this.f.a(-1, false);
                }
                this.f = null;
                aVar.a(-1, false);
            }
        }
    }
}
