package com.xunlei.downloadprovider.web.browser;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alipay.sdk.app.statistic.c;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.xunlei.downloadprovider.model.o;
import com.xunlei.downloadprovider.model.p;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InputAutoCompleteView extends LinearLayout {
    static final String[] a;
    static final String[] b;
    static final String[] c;
    private static final String l;
    ArrayList<o> d;
    ArrayList<o> e;
    a f;
    Context g;
    EditText h;
    ListView i;
    List<String> j;
    View k;
    private b m;

    private class a extends com.xunlei.downloadprovider.e.a.a.a {
        private LayoutInflater b;

        public final /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public a(Context context) {
            this.b = LayoutInflater.from(context);
        }

        public final int getCount() {
            return InputAutoCompleteView.this.e != null ? InputAutoCompleteView.this.e.size() - 1 : 0;
        }

        public final o a(int i) {
            return (o) InputAutoCompleteView.this.e.get(i);
        }

        public final long getItemId(int i) {
            return (long) i;
        }

        public final View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            if (view == null) {
                view = this.b.inflate(2130968635, null);
                a aVar2 = new a(this);
                aVar2.b = (TextView) view.findViewById(2131755349);
                aVar2.d = (TextView) view.findViewById(R.id.name);
                aVar2.c = (TextView) view.findViewById(2131755347);
                aVar2.e = (ImageView) view.findViewById(2131755345);
                view.setTag(aVar2);
                aVar = aVar2;
            } else {
                aVar = (a) view.getTag();
            }
            aVar.e.setOnClickListener(new ag(this, i));
            CharSequence charSequence = InputAutoCompleteView.this;
            InputAutoCompleteView.this = i;
            aVar.e.setVisibility(0);
            view.setOnClickListener(new ah(this, charSequence));
            if (charSequence.equals(WeiboAuthException.DEFAULT_AUTH_ERROR_CODE)) {
                aVar.c.setText(a(i).b);
                aVar.c.setVisibility(0);
                aVar.d.setVisibility(XZBDevice.Wait);
                aVar.b.setVisibility(XZBDevice.Wait);
            } else {
                aVar.c.setVisibility(XZBDevice.Wait);
                aVar.d.setText(charSequence);
                aVar.d.setVisibility(0);
                aVar.b.setText(a(i).b);
                aVar.b.setVisibility(0);
            }
            return view;
        }

        static /* synthetic */ void a(a aVar, int i) {
            if (i >= 0 && i < InputAutoCompleteView.this.e.size()) {
                o oVar = (o) InputAutoCompleteView.this.e.remove(i);
                Iterator it = InputAutoCompleteView.this.d.iterator();
                while (it.hasNext()) {
                    o oVar2 = (o) it.next();
                    if (InputAutoCompleteView.this.equalsIgnoreCase(InputAutoCompleteView.this)) {
                        break;
                    }
                }
                Object obj = null;
                if (obj != null) {
                    InputAutoCompleteView.this.d.remove(obj);
                    InputAutoCompleteView.this.getContext();
                    p.a().b(InputAutoCompleteView.this);
                }
                aVar.notifyDataSetChanged();
            }
        }
    }

    public static interface b {
        void a();

        void a(String str, boolean z);
    }

    static {
        l = InputAutoCompleteView.class.getSimpleName();
        a = new String[]{"wap", "www", "http://", "https://"};
        b = new String[]{"com", "com.cn", "cn", "edu.cn", c.a, "org"};
        c = new String[]{"apk", "avi", "mp4", "mp3", "rmvb", "rm", "mpg", "mpeg", "html"};
    }

    public InputAutoCompleteView(Context context) {
        super(context);
        this.d = new ArrayList();
        this.e = new ArrayList();
        this.f = null;
        this.g = null;
        this.g = context;
    }

    public InputAutoCompleteView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = new ArrayList();
        this.e = new ArrayList();
        this.f = null;
        this.g = null;
        this.g = context;
    }

    public void setUIClient(b bVar) {
        this.m = bVar;
    }

    public final void a() {
        this.d = (ArrayList) getPrivateMark();
    }

    private List<o> getPrivateMark() {
        try {
            List<o> b = p.a().b();
            List<o> arrayList = new ArrayList();
            for (o oVar : b) {
                if (!TextUtils.isEmpty(oVar.b) && !TextUtils.isEmpty(oVar.a)) {
                    arrayList.add(oVar);
                }
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }

    public void setViewHeight(boolean z) {
        float f = getResources().getDisplayMetrics().density;
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.common_title_height);
        if (z) {
            layoutParams.setMargins(0, 0, 0, (int) (f * 55.0f));
        } else {
            layoutParams.setMargins(0, dimensionPixelOffset, 0, 0);
        }
        this.k.setLayoutParams(layoutParams);
    }
}
