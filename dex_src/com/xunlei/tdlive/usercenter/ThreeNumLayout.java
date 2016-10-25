package com.xunlei.tdlive.usercenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.user.f;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class ThreeNumLayout extends FrameLayout implements OnClickListener {
    private TextView a;
    private TextView b;
    private TextView c;
    private ViewGroup d;
    private ViewGroup e;
    private ViewGroup f;
    private boolean g;
    private boolean h;
    private a i;

    public static interface a {
        void a(int i);
    }

    public ThreeNumLayout(Context context) {
        super(context);
        this.g = false;
        this.h = false;
        a();
    }

    public ThreeNumLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = false;
        this.h = false;
        a();
    }

    public ThreeNumLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = false;
        this.h = false;
        a();
    }

    public void setSelectedWhenClick(boolean z) {
        this.g = z;
        if (this.g) {
            a(R.id.lvLive);
        }
    }

    public void setShowRedFlag(boolean z) {
        this.h = z;
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.xllive_three_nums, this);
        this.a = (TextView) findViewById(R.id.tvLiveNum);
        this.b = (TextView) findViewById(R.id.tvFunsNum);
        this.c = (TextView) findViewById(R.id.tvFollowNum);
        this.d = (ViewGroup) findViewById(R.id.lvLive);
        this.d.setOnClickListener(this);
        this.e = (ViewGroup) findViewById(R.id.lvFuns);
        this.e.setOnClickListener(this);
        this.f = (ViewGroup) findViewById(R.id.lvFollow);
        this.f.setOnClickListener(this);
    }

    public void addOnItemSelectListener(a aVar) {
        this.i = aVar;
    }

    public void updateThreeNum(int i, int i2, int i3) {
        boolean z = true;
        this.a.setText(String.valueOf(i));
        this.c.setText(String.valueOf(i3));
        this.b.setText(String.valueOf(i2));
        if (this.h) {
            String k = f.a().k();
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("user_fans_num", 0);
            int i4 = sharedPreferences.getInt(k, 0);
            if (i2 != i4) {
                if (i2 <= i4) {
                    z = false;
                }
                showRedFlag(z, XZBDevice.DOWNLOAD_LIST_RECYCLE);
                Editor edit = sharedPreferences.edit();
                edit.putInt(k, i2);
                edit.remove(a(k));
                edit.apply();
            } else if (i2 > 0) {
                if (sharedPreferences.getBoolean(a(k), false)) {
                    z = false;
                }
                showRedFlag(z, XZBDevice.DOWNLOAD_LIST_RECYCLE);
            } else {
                showRedFlag(false, XZBDevice.DOWNLOAD_LIST_RECYCLE);
            }
        }
    }

    private String a(String str) {
        return str + "_click";
    }

    public void showRedFlag(boolean z, int i) {
        ((ImageView) findViewById(R.id.ivRedFlag)).setVisibility(z ? 0 : XZBDevice.Wait);
        if (z) {
            this.b.setTag(Boolean.valueOf(z));
        }
    }

    public void onClick(View view) {
        int i = 1;
        int id = view.getId();
        if (id != R.id.lvLive) {
            if (id != R.id.lvFuns) {
                i = id == R.id.lvFollow ? XZBDevice.DOWNLOAD_LIST_FAILED : 0;
            } else if (!this.h || this.b.getTag() == null) {
                i = 2;
            } else {
                showRedFlag(false, XZBDevice.DOWNLOAD_LIST_RECYCLE);
                String k = f.a().k();
                Editor edit = getContext().getSharedPreferences("user_fans_num", 0).edit();
                edit.putBoolean(a(k), true);
                edit.apply();
                i = 2;
            }
        }
        if (this.g) {
            a(id);
        }
        if (this.i != null) {
            this.i.a(i);
        }
    }

    private void a(int i) {
        if (i == R.id.lvLive) {
            this.d.setSelected(true);
            this.e.setSelected(false);
            this.f.setSelected(false);
        } else if (i == R.id.lvFuns) {
            this.d.setSelected(false);
            this.e.setSelected(true);
            this.f.setSelected(false);
        } else if (i == R.id.lvFollow) {
            this.d.setSelected(false);
            this.e.setSelected(false);
            this.f.setSelected(true);
        }
    }
}
