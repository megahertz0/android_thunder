package com.xunlei.downloadprovider.personal.redenvelope.redenvelopelist.a;

import android.content.Context;
import android.support.v7.widget.RecyclerView.t;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.xunlei.downloadprovider.personal.redenvelope.redenvelopelist.b.d;
import java.util.List;

// compiled from: RedEnvelopesAdapter.java
public final class a extends android.support.v7.widget.RecyclerView.a<b> {
    private final LayoutInflater a;
    private List<d> b;

    public final /* synthetic */ void onBindViewHolder(t tVar, int i) {
        b bVar = (b) tVar;
        if (this.b != null) {
            d dVar = (d) this.b.get(i);
            if (dVar != null) {
                int i2 = dVar.a;
                String str = dVar.b;
                String str2 = dVar.d;
                int i3 = dVar.c;
                bVar.b.setText(str + i2);
                b.a(str2, bVar.a);
                bVar.c.setText(com.xunlei.downloadprovider.personal.redenvelope.a.a.replace("%s", String.valueOf(i3)));
                com.xunlei.downloadprovider.personal.redenvelope.a.a(bVar.c, (long) i3);
            }
            bVar.itemView.setTag(this.b.get(i));
        }
    }

    public a(Context context, List<d> list) {
        this.a = LayoutInflater.from(context);
        this.b = list;
    }

    public final int getItemCount() {
        return this.b != null ? this.b.size() : 0;
    }

    public final int getItemViewType(int i) {
        return super.getItemViewType(i);
    }

    public final /* synthetic */ t onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new b(this.a.inflate(2130968926, null));
    }
}
