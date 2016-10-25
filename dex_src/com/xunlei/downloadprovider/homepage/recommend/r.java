package com.xunlei.downloadprovider.homepage.recommend;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.xunlei.downloadprovider.commonview.dialog.d;
import com.xunlei.downloadprovidershare.R;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: XLOfficialAccountRecruitHeaderView.java
final class r implements OnClickListener {
    final /* synthetic */ Context a;
    final /* synthetic */ ListView b;
    final /* synthetic */ View c;

    r(Context context, ListView listView, View view) {
        this.a = context;
        this.b = listView;
        this.c = view;
    }

    public final void onClick(View view) {
        d dVar = new d(this.a);
        dVar.setCanceledOnTouchOutside(false);
        dVar.setTitle("\u5f00\u901a\u63d0\u793a");
        dVar.a(true);
        dVar.d("\u786e\u8ba4");
        dVar.b(new s(this));
        TextView c = dVar.c();
        c.setAutoLinkMask(SimpleLog.LOG_LEVEL_FATAL);
        c.setLinkTextColor(this.a.getResources().getColor(R.color.common_blue));
        c.setGravity(com.xunlei.xllib.R.styleable.Toolbar_maxButtonHeight);
        c.setText(new SpannableString(this.a.getString(com.xunlei.downloadprovider.R.string.open_xl_official_account_msg)));
        Spannable spannable = (Spannable) c.getText();
        spannable.setSpan(new XLOfficialAccountRecruitHeaderView$1$2(this), 0, spannable.length(), com.xunlei.xllib.R.styleable.Toolbar_maxButtonHeight);
        dVar.show();
        q.c();
        VideoFeedReporter.e();
    }
}
