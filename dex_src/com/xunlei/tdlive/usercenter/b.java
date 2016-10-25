package com.xunlei.tdlive.usercenter;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.usercenter.d.a;

// compiled from: EmptyViewAdapter.java
public class b extends BaseAdapter {
    private int a;
    private String b;
    private a c;

    public b(String str, int i) {
        this.a = i;
        this.b = str;
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public int getCount() {
        return 1;
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            Context context = viewGroup.getContext();
            view = LayoutInflater.from(context).inflate(R.layout.xllive_list_empty, viewGroup, false);
            Resources resources = context.getResources();
            if (this.a == 2) {
                ah.a(view, resources.getString(R.string.others_no_funs), resources.getString(R.string.follow_ta), new e(false, this.b, 2, new c(this)));
            } else if (this.a == 3) {
                ah.a(view, resources.getString(R.string.others_no_follow), null, null);
            } else if (this.a == 1) {
                ah.a(view, resources.getString(R.string.others_no_live), null, null);
            }
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            view.setLayoutParams(new LayoutParams(displayMetrics.widthPixels, displayMetrics.widthPixels));
        }
        return view;
    }
}
