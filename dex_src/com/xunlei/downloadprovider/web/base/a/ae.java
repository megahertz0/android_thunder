package com.xunlei.downloadprovider.web.base.a;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.a;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.ad.home.ui.s;
import com.xunlei.downloadprovider.web.base.model.t;
import java.util.ArrayList;
import java.util.List;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: ShortMovieDetailMultiTypeAdapter.java
public final class ae extends a<af> {
    public List<t> a;
    private final Activity b;
    private final LayoutInflater c;
    private a d;

    public final /* synthetic */ void onBindViewHolder(RecyclerView.t tVar, int i) {
        af afVar = (af) tVar;
        t tVar2 = (t) this.a.get(i);
        if (tVar2 == null) {
            throw new IllegalArgumentException("itemData may not be null");
        }
        afVar.a(tVar2);
    }

    public final /* synthetic */ RecyclerView.t onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate;
        switch (i) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                inflate = this.c.inflate(R.layout.layout_base_info, null);
                inflate.setLayoutParams(new LayoutParams(-1, -2));
                return new a(inflate, this.d);
            case SimpleLog.LOG_LEVEL_TRACE:
            case SpdyProtocol.PUBKEY_SEQ_OPEN:
                inflate = this.c.inflate(R.layout.comment_header, null);
                inflate.setLayoutParams(new LayoutParams(-1, -2));
                return new i(inflate);
            case SimpleLog.LOG_LEVEL_DEBUG:
                inflate = this.c.inflate(R.layout.comment_item_layout, null);
                inflate.setLayoutParams(new LayoutParams(-1, -2));
                return new j(inflate, this.d);
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
                inflate = this.c.inflate(R.layout.comment_empty_layout, null);
                inflate.setLayoutParams(new LayoutParams(-1, -2));
                return new f(inflate);
            case SimpleLog.LOG_LEVEL_ERROR:
                inflate = this.c.inflate(R.layout.comment_error_layout, null);
                inflate.setLayoutParams(new LayoutParams(-1, -2));
                return new g(inflate, this.d);
            case SimpleLog.LOG_LEVEL_FATAL:
                inflate = this.c.inflate(R.layout.recommend_header, null);
                inflate.setLayoutParams(new LayoutParams(-1, -2));
                return new z(inflate, this.d);
            case SimpleLog.LOG_LEVEL_OFF:
                inflate = this.c.inflate(R.layout.recommend_video_item_layout, null);
                inflate.setLayoutParams(new LayoutParams(-1, -2));
                return new ac(inflate, this.d);
            case SpdyProtocol.PUBKEY_SEQ_ADASH:
                inflate = this.c.inflate(R.layout.recommend_footer, null);
                inflate.setLayoutParams(new LayoutParams(-1, -2));
                return new x(inflate, this.d);
            case SpdyProtocol.PUBKEY_PSEQ_ADASH:
                inflate = this.c.inflate(com.xunlei.downloadprovidershare.R.layout.common_pull_up_refresh_item, null);
                inflate.setLayoutParams(new LayoutParams(-1, -2));
                return new w(inflate);
            case SpdyProtocol.PUBKEY_PSEQ_OPEN:
                inflate = this.c.inflate(R.layout.comment_loading_layout, null);
                inflate.setLayoutParams(new LayoutParams(-1, -2));
                return new v(inflate);
            case com.xunlei.xllib.R.styleable.Toolbar_titleMargins:
                inflate = this.c.inflate(R.layout.layout_ad_base_info, null);
                inflate.setLayoutParams(new LayoutParams(-1, -2));
                return new s(inflate, this.d);
            default:
                return null;
        }
    }

    public ae(Activity activity, a aVar) {
        this.b = activity;
        this.c = LayoutInflater.from(activity);
        this.d = aVar;
    }

    public final int getItemCount() {
        return this.a != null ? this.a.size() : 0;
    }

    public final int getItemViewType(int i) {
        return i < getItemCount() ? ((t) this.a.get(i)).a : -1;
    }

    public final long getItemId(int i) {
        return (long) i;
    }

    public final void a(int i, List<t> list) {
        if (list != null && list.size() > 0) {
            if (this.a == null) {
                this.a = new ArrayList();
            }
            this.a.addAll(i, list);
            notifyItemInserted(i);
        }
    }

    public final void a(List<t> list) {
        if (list != null && list.size() > 0) {
            if (this.a == null) {
                this.a = new ArrayList();
            }
            this.a.addAll(list);
            notifyDataSetChanged();
        }
    }

    public final void a(int i, t tVar) {
        if (tVar != null) {
            if (this.a == null) {
                this.a = new ArrayList();
            }
            this.a.add(i, tVar);
            notifyItemInserted(i);
        }
    }

    public final void a(t tVar) {
        if (tVar != null) {
            if (this.a == null) {
                this.a = new ArrayList();
            }
            this.a.add(tVar);
            notifyItemInserted(this.a.size() - 1);
        }
    }

    public final void b(t tVar) {
        if (this.a != null && tVar != null) {
            int i;
            int itemCount = getItemCount();
            for (int i2 = 0; i2 < itemCount; i2++) {
                if (((t) this.a.get(i2)) == tVar) {
                    i = i2;
                    break;
                }
            }
            i = -1;
            if (i >= 0) {
                notifyItemChanged(i);
            }
        }
    }

    public final void b(List<t> list) {
        int i = 0;
        if (list != null && list.size() != 0 && this.a != null && this.a.size() != 0) {
            int size = list.size();
            t tVar = (t) list.get(0);
            int i2 = 0;
            while (i2 < this.a.size()) {
                if (this.a.get(i2) == tVar) {
                    break;
                }
                i2++;
            }
            i2 = -1;
            if (i2 >= 0) {
                while (i < size) {
                    this.a.remove(i2);
                    i++;
                }
                notifyItemRangeRemoved(i2, size);
            }
        }
    }

    public final void c(t tVar) {
        if (this.a != null && this.a.size() != 0) {
            int i = 0;
            while (i < this.a.size()) {
                if (this.a.get(i) == tVar) {
                    break;
                }
                i++;
            }
            i = -1;
            if (i >= 0 && this.a != null && i <= this.a.size() - 1) {
                this.a.remove(i);
                notifyItemRemoved(i);
            }
        }
    }

    public final int a() {
        int itemCount = getItemCount();
        for (int i = 0; i < itemCount; i++) {
            if (((t) this.a.get(i)).a == 1) {
                return i;
            }
        }
        return -1;
    }

    public final int b() {
        int itemCount = getItemCount();
        for (int i = 0; i < itemCount; i++) {
            if (((t) this.a.get(i)).a == 10) {
                return i;
            }
        }
        return -1;
    }
}
