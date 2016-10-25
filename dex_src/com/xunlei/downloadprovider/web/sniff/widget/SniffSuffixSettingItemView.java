package com.xunlei.downloadprovider.web.sniff.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.umeng.a;
import com.xunlei.downloadprovider.commonview.DisableScrollGridView;
import com.xunlei.downloadprovider.web.sniff.SniffSuffixTypeDataManager.SuffixDataType;
import com.xunlei.downloadprovider.web.sniff.b;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xllib.b.d;
import java.util.ArrayList;
import java.util.List;
import org.android.spdy.SpdyAgent;

public class SniffSuffixSettingItemView extends FrameLayout {
    public TextView a;
    public DisableScrollGridView b;
    private View c;
    private List<b> d;
    private SuffixDataType e;

    public SniffSuffixSettingItemView(Context context) {
        super(context);
        a(context);
    }

    public SniffSuffixSettingItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public SniffSuffixSettingItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        this.c = LayoutInflater.from(context).inflate(2130968986, null);
        this.a = (TextView) this.c.findViewById(2131757014);
        this.b = (DisableScrollGridView) this.c.findViewById(2131757015);
        addView(this.c);
        this.b.setAdapter(new a(this, (byte) 0));
        this.b.setOnItemClickListener(new g(this));
    }

    public final void a(SuffixDataType suffixDataType, List<b> list) {
        this.e = suffixDataType;
        CharSequence charSequence = a.d;
        switch (AnonymousClass_1.a[suffixDataType.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                charSequence = "\u89c6\u9891";
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                charSequence = "\u79cd\u5b50\u6587\u4ef6";
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                charSequence = "\u538b\u7f29\u5305";
                break;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                charSequence = "\u97f3\u4e50\u6587\u4ef6";
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                charSequence = "\u5e94\u7528";
                break;
            case R.styleable.Toolbar_contentInsetEnd:
                charSequence = "\u6587\u6863";
                break;
        }
        this.a.setText(charSequence);
        if (this.d == null) {
            this.d = new ArrayList();
        }
        if (!d.a(this.d)) {
            this.d.clear();
        }
        this.d.addAll(list);
    }
}
