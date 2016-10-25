package com.xunlei.downloadprovider.xiazaibao.setting;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.List;

// compiled from: SelectXZBDeviceFragment.java
class e$a extends BaseAdapter {
    final /* synthetic */ e a;
    private List<XZBDevice> b;
    private LayoutInflater c;

    // compiled from: SelectXZBDeviceFragment.java
    class a {
        TextView a;
        ImageView b;

        a() {
        }
    }

    public e$a(e eVar, Context context, List<XZBDevice> list) {
        this.a = eVar;
        this.b = list;
        this.c = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public final int getCount() {
        return this.b == null ? 0 : this.b.size();
    }

    public final Object getItem(int i) {
        return this.b == null ? null : (XZBDevice) this.b.get(i);
    }

    public final long getItemId(int i) {
        return (long) i;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a();
            view = this.c.inflate(R.layout.layout_select_downloaddevice_item, viewGroup, false);
            aVar.b = (ImageView) view.findViewById(R.id.img_select_device_icon);
            aVar.a = (TextView) view.findViewById(R.id.tv_select_device_name);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        XZBDevice xZBDevice = (XZBDevice) this.b.get(i);
        if (TextUtils.equals(xZBDevice.getDeviceName(), e.d(this.a).getDeviceName())) {
            aVar.b.setSelected(true);
        } else {
            aVar.b.setSelected(false);
        }
        aVar.a.setText(xZBDevice.getDeviceName());
        return view;
    }
}
