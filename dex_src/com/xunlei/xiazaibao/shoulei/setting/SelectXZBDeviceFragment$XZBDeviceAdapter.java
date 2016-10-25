package com.xunlei.xiazaibao.shoulei.setting;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.xiazaibao.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.List;

class SelectXZBDeviceFragment$XZBDeviceAdapter extends BaseAdapter {
    private List<XZBDevice> mAdapterDatas;
    private LayoutInflater mInflater;
    final /* synthetic */ SelectXZBDeviceFragment this$0;

    class ViewHolder {
        ImageView imgIcon;
        TextView name;

        ViewHolder() {
        }
    }

    public SelectXZBDeviceFragment$XZBDeviceAdapter(SelectXZBDeviceFragment selectXZBDeviceFragment, Context context, List<XZBDevice> list) {
        this.this$0 = selectXZBDeviceFragment;
        this.mAdapterDatas = list;
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public int getCount() {
        return this.mAdapterDatas == null ? 0 : this.mAdapterDatas.size();
    }

    public Object getItem(int i) {
        return this.mAdapterDatas == null ? null : (XZBDevice) this.mAdapterDatas.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = this.mInflater.inflate(R.layout.layout_select_downloaddevice_item, viewGroup, false);
            viewHolder.imgIcon = (ImageView) view.findViewById(R.id.img_select_device_icon);
            viewHolder.name = (TextView) view.findViewById(R.id.tv_select_device_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        XZBDevice xZBDevice = (XZBDevice) this.mAdapterDatas.get(i);
        if (TextUtils.equals(xZBDevice.getDeviceName(), SelectXZBDeviceFragment.access$200(this.this$0).getDeviceName())) {
            viewHolder.imgIcon.setSelected(true);
        } else {
            viewHolder.imgIcon.setSelected(false);
        }
        viewHolder.name.setText(xZBDevice.getDeviceName());
        return view;
    }
}
