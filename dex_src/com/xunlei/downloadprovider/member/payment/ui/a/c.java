package com.xunlei.downloadprovider.member.payment.ui.a;

import android.content.Context;
import android.support.v7.widget.RecyclerView.a;
import android.support.v7.widget.RecyclerView.t;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: PayProblemAdapter.java
public final class c extends a<a> {
    Context a;
    public b b;
    private String[] c;
    private String[] d;
    private final int e;

    // compiled from: PayProblemAdapter.java
    public static interface b {
        void a();
    }

    public final /* synthetic */ void onBindViewHolder(t tVar, int i) {
        a aVar = (a) tVar;
        aVar.a.setText(this.c[i]);
        if (i == 2) {
            a(this.d[2], XZBDevice.Success, R.styleable.Toolbar_maxButtonHeight, aVar.b, i);
        } else if (i == this.d.length - 1) {
            a(this.d[this.d.length - 1], R.styleable.Toolbar_contentInsetLeft, R.styleable.Toolbar_collapseContentDescription, aVar.b, i);
        } else {
            aVar.b.setText(this.d[i]);
        }
    }

    public c(String[] strArr, String[] strArr2, Context context) {
        this.c = strArr;
        this.d = strArr2;
        this.a = context;
        this.e = this.a.getResources().getColor(2131689733);
    }

    public final int getItemCount() {
        return this.c.length;
    }

    private void a(String str, int i, int i2, TextView textView, int i3) {
        CharSequence spannableString = new SpannableString(str);
        if (i3 == 2) {
            spannableString.setSpan(new d(this), i, i2, R.styleable.AppCompatTheme_actionModeCopyDrawable);
        }
        if (i3 == this.d.length - 1) {
            spannableString.setSpan(new e(this), i, i2, R.styleable.AppCompatTheme_actionModeCopyDrawable);
        }
        spannableString.setSpan(new ForegroundColorSpan(this.e), i, i2, R.styleable.AppCompatTheme_actionModeCopyDrawable);
        textView.append(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public final /* synthetic */ t onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new a(this, LayoutInflater.from(this.a).inflate(2130968903, viewGroup, false));
    }
}
