package com.xunlei.tdlive.withdraw;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xunlei.tdlive.R;

// compiled from: WithdrawFinish.java
public class k extends a implements OnClickListener {
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        double d = getArguments().getDouble("KEY_WITHDRAW_NUM");
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.xllive_withdraw_finish, viewGroup, false);
        inflate.findViewById(R.id.ok).setOnClickListener(this);
        TextView textView = (TextView) inflate.findViewById(R.id.tvWithdrawNum);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tvWithdrawAccount);
        Object a = this.k.a("nickname");
        if (a != null && (a instanceof String)) {
            textView2.setText(getResources().getString(R.string.widthdraw_account, new Object[]{a.toString()}));
        }
        a(textView, getResources().getString(R.string.widthdraw_num_without_unit, new Object[]{Double.valueOf(d)}));
        return inflate;
    }

    private void a(TextView textView, String str) {
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(R.color.gray31);
        ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(R.color.golden);
        CharSequence spannableStringBuilder = new SpannableStringBuilder(str);
        int indexOf = str.indexOf(": ");
        spannableStringBuilder.setSpan(foregroundColorSpan, 0, indexOf + 1, com.xunlei.xllib.R.styleable.AppCompatTheme_actionModeCopyDrawable);
        spannableStringBuilder.setSpan(foregroundColorSpan2, indexOf + 1, str.length(), com.xunlei.xllib.R.styleable.Toolbar_collapseIcon);
        textView.setText(spannableStringBuilder);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.ok) {
            this.k.a();
        }
    }
}
