package com.xunlei.downloadprovider.personal.settings.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xunlei.xiazaibao.R$color;
import org.apache.commons.logging.impl.SimpleLog;

public class ScaleTextView extends ViewGroup {
    public static final String a;
    private Context b;
    private String[] c;

    static {
        a = ScaleTextView.class.getSimpleName();
    }

    public ScaleTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new String[]{"10KB", "200KB", "400KB", "600KB", "800KB", "1024KB"};
        this.b = context;
        a();
    }

    public ScaleTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void a() {
        int length = this.c.length;
        for (int i = 0; i < length; i++) {
            CharSequence charSequence = this.c[i];
            View textView = new TextView(this.b);
            textView.setText(charSequence);
            textView.setTextColor(getResources().getColor(R$color.TextAppearanceEntryPrimaryTitle));
            textView.setTextSize(SimpleLog.LOG_LEVEL_DEBUG, 10.0f);
            addView(textView);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        int width = getWidth();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            int measuredWidth = childAt.getMeasuredWidth();
            int measuredHeight = childAt.getMeasuredHeight();
            if (i5 == 0) {
                childAt.layout(0, 0, measuredWidth, measuredHeight);
            } else if (i5 == childCount - 1) {
                childAt.layout(width - measuredWidth, 0, measuredWidth + width, measuredHeight);
            } else {
                int i6 = (width * i5) / (childCount - 1);
                childAt.layout(i6 - (measuredWidth / 2), 0, (measuredWidth / 2) + i6, measuredHeight);
            }
        }
    }

    protected void onMeasure(int i, int i2) {
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            getChildAt(i3).measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
        }
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(getChildAt(0).getMeasuredHeight(), 1073741824));
    }
}
