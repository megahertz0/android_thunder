package com.xunlei.downloadprovider.download.center.widget;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.commonview.dialog.h;
import com.xunlei.downloadprovider.download.report.a;
import org.android.spdy.SpdyProtocol;

// compiled from: DownloadRemoveConfirmDialog.java
public final class t extends h {
    public t(Context context, int i, int i2, long j, String str) {
        super(context, "\u786e\u5b9a\u5220\u9664\u4e0b\u8f7d\u4efb\u52a1?", "\u53d6\u6d88", "\u786e\u5b9a");
        a.k(str);
        Object format;
        int[] iArr;
        CharSequence spannableStringBuilder;
        if (i > 0 && i2 > 0) {
            format = String.format(context.getString(R.string.alert_delete_content), new Object[]{Integer.valueOf(i), Integer.valueOf(i2)});
            iArr = new int[]{format.indexOf(String.valueOf(i)), format.lastIndexOf(String.valueOf(i2))};
            spannableStringBuilder = new SpannableStringBuilder(format);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), 0, iArr[0], com.xunlei.xllib.R.styleable.AppCompatTheme_actionModePasteDrawable);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#1294F6")), iArr[0], iArr[0] + String.valueOf(i).length(), com.xunlei.xllib.R.styleable.AppCompatTheme_actionModePasteDrawable);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), iArr[0] + String.valueOf(i).length(), iArr[1], com.xunlei.xllib.R.styleable.AppCompatTheme_actionModePasteDrawable);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#1294F6")), iArr[1], iArr[1] + String.valueOf(i2).length(), com.xunlei.xllib.R.styleable.AppCompatTheme_actionModePasteDrawable);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), iArr[1] + String.valueOf(i2).length(), format.length(), com.xunlei.xllib.R.styleable.AppCompatTheme_actionModePasteDrawable);
            this.b.setText(spannableStringBuilder);
        } else if (i > 0 && i2 <= 0) {
            format = String.format(context.getString(R.string.alert_delete_content_zero_unfinish), new Object[]{Integer.valueOf(i)});
            iArr = new int[]{format.indexOf(String.valueOf(i))};
            spannableStringBuilder = new SpannableStringBuilder(format);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), 0, iArr[0], com.xunlei.xllib.R.styleable.AppCompatTheme_actionModePasteDrawable);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#1294F6")), iArr[0], iArr[0] + String.valueOf(i).length(), com.xunlei.xllib.R.styleable.AppCompatTheme_actionModePasteDrawable);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), iArr[0] + String.valueOf(i).length(), format.length(), com.xunlei.xllib.R.styleable.AppCompatTheme_actionModePasteDrawable);
            this.b.setText(spannableStringBuilder);
        } else if (i <= 0 && i2 > 0) {
            format = String.format(context.getString(R.string.alert_delete_content_zero_finish), new Object[]{Integer.valueOf(i2)});
            iArr = new int[]{format.indexOf(String.valueOf(i2))};
            spannableStringBuilder = new SpannableStringBuilder(format);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), 0, iArr[0], com.xunlei.xllib.R.styleable.AppCompatTheme_actionModePasteDrawable);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#1294F6")), iArr[0], iArr[0] + String.valueOf(i2).length(), com.xunlei.xllib.R.styleable.AppCompatTheme_actionModePasteDrawable);
            spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), iArr[0] + String.valueOf(i2).length(), format.length(), com.xunlei.xllib.R.styleable.AppCompatTheme_actionModePasteDrawable);
            this.b.setText(spannableStringBuilder);
        }
        String b = com.xunlei.downloadprovider.download.util.a.b(j);
        Object format2 = String.format(context.getString(R.string.alert_delete_size), new Object[]{b});
        int[] iArr2 = new int[]{format2.indexOf(b)};
        CharSequence spannableStringBuilder2 = new SpannableStringBuilder(format2);
        spannableStringBuilder2.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), 0, iArr2[0], com.xunlei.xllib.R.styleable.AppCompatTheme_actionModePasteDrawable);
        spannableStringBuilder2.setSpan(new ForegroundColorSpan(Color.parseColor("#1294F6")), iArr2[0], format2.length(), com.xunlei.xllib.R.styleable.AppCompatTheme_actionModePasteDrawable);
        this.c.setVisibility(0);
        this.c.setText(spannableStringBuilder2);
        a();
        b("\u540c\u65f6\u5220\u9664\u672c\u5730\u6587\u4ef6");
    }

    public t(Context context) {
        super(context, "\u786e\u5b9a\u5220\u9664\u4e0b\u8f7d\u4efb\u52a1?", "\u53d6\u6d88", "\u786e\u5b9a");
        this.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        a();
        b(null);
    }
}
