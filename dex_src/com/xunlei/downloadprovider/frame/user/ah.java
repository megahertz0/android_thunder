package com.xunlei.downloadprovider.frame.user;

import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.a;
import android.support.v7.widget.RecyclerView.t;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xunlei.downloadprovider.R;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: PersonalItemAdapter.java
public final class ah extends a<aj> {
    final List<ai> a;
    private final a b;

    public final /* synthetic */ void onBindViewHolder(t tVar, int i) {
        aj ajVar = (aj) tVar;
        ai aiVar = null;
        if (this.a != null && this.a.size() > i) {
            aiVar = (ai) this.a.get(i);
        }
        ajVar.a(aiVar);
    }

    public ah(a aVar) {
        this.a = new ArrayList(1);
        this.b = aVar;
    }

    public final int getItemCount() {
        return this.a == null ? 0 : this.a.size();
    }

    public final int getItemViewType(int i) {
        return i >= this.a.size() ? -1 : ((ai) this.a.get(i)).a;
    }

    public final void a(List<ai> list) {
        if (!list.isEmpty()) {
            this.a.clear();
            this.a.addAll(list);
            notifyDataSetChanged();
        }
    }

    public final void a(ai aiVar) {
        if (this.a != null && this.a.size() != 0 && aiVar != null) {
            int size = this.a.size() - 1;
            while (size >= 0) {
                if (this.a.get(size) == aiVar) {
                    break;
                }
                size--;
            }
            size = -1;
            if (size >= 0) {
                this.a.remove(size);
                notifyItemRemoved(size);
            }
        }
    }

    public final void b(ai aiVar) {
        if (this.a != null && aiVar != null) {
            int i;
            int itemCount = getItemCount();
            for (int i2 = 0; i2 < itemCount; i2++) {
                if (((ai) this.a.get(i2)) == aiVar) {
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

    public final /* synthetic */ t onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                return new v(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_history_comment_item, viewGroup, false), this.b);
            case SimpleLog.LOG_LEVEL_TRACE:
                View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(com.xunlei.downloadprovidershare.R.layout.common_pull_up_refresh_item, null);
                inflate.setLayoutParams(new LayoutParams(-1, -2));
                return new af(inflate);
            default:
                throw new IllegalArgumentException("Invalid view holder type!!!");
        }
    }
}
