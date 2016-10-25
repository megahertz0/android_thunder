package com.xunlei.downloadprovider.xiazaibao.setting;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.xunlei.xiazaibao.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.sdk.XZBDeviceManager;
import com.xunlei.xiazaibao.shoulei.DownloadPathMsg;
import com.xunlei.xiazaibao.shoulei.DownloadPathType;
import com.xunlei.xiazaibao.shoulei.XZBShouleiUtil;
import java.util.List;

// compiled from: SelectXZBDeviceFragment.java
public final class e extends Fragment {
    private TextView a;
    private ImageView b;
    private ListView c;
    private List<XZBDevice> d;
    private a e;
    private Activity f;
    private XZBDevice g;

    public static e a() {
        Bundle bundle = new Bundle();
        e eVar = new e();
        eVar.setArguments(bundle);
        return eVar;
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f = getActivity();
        View inflate = layoutInflater.inflate(R.layout.fragment_select_xzbdevice, viewGroup, false);
        this.b = (ImageView) inflate.findViewById(R.id.titlebar_left);
        this.b.setOnClickListener(new f(this));
        this.a = (TextView) inflate.findViewById(R.id.titlebar_title);
        this.a.setText(2131232667);
        this.g = XZBShouleiUtil.getInstance().getDefaultDevice();
        DownloadPathMsg downloadPathTypeMsg = XZBShouleiUtil.getInstance().getDownloadPathTypeMsg();
        DownloadPathType downloadPathType = downloadPathTypeMsg.getDownloadPathType();
        if (downloadPathType == DownloadPathType.XZB || downloadPathType == DownloadPathType.MOBILE_XZB) {
            XZBDevice downloadPathDetailMsg = downloadPathTypeMsg.getDownloadPathDetailMsg();
            if (downloadPathDetailMsg != null) {
                this.g = downloadPathDetailMsg;
            }
        }
        this.c = (ListView) inflate.findViewById(R.id.ls_downloaddevice_setting_xzbdevice);
        this.d = XZBDeviceManager.getInstance().getDeviceList();
        this.e = new a(this, this.f, this.d);
        this.c.setAdapter(this.e);
        this.c.setOnItemClickListener(new g(this));
        return inflate;
    }
}
