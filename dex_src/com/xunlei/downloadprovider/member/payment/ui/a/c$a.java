package com.xunlei.downloadprovider.member.payment.ui.a;

import android.support.v7.widget.RecyclerView.t;
import android.view.View;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;

// compiled from: PayProblemAdapter.java
class c$a extends t {
    TextView a;
    TextView b;
    final /* synthetic */ c c;

    public c$a(c cVar, View view) {
        this.c = cVar;
        super(view);
        this.a = (TextView) view.findViewById(R.id.pay_problem_title_tv);
        this.b = (TextView) view.findViewById(R.id.pay_problem_content_tv);
    }
}
