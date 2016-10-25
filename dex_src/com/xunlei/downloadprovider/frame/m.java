package com.xunlei.downloadprovider.frame;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.taobao.accs.common.Constants;
import com.xunlei.downloadprovider.commonview.dialog.XLAlarmDialog;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.login.ui.LoginActivity;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.tdlive.R;
import org.android.agoo.message.MessageService;

// compiled from: MainTabActivity.java
final class m implements OnClickListener {
    final /* synthetic */ XLAlarmDialog a;
    final /* synthetic */ MainTabActivity b;

    m(MainTabActivity mainTabActivity, XLAlarmDialog xLAlarmDialog) {
        this.b = mainTabActivity;
        this.a = xLAlarmDialog;
    }

    public final void onClick(View view) {
        this.a.dismiss();
        StatReporter.reportAppExitClick(Constants.KEY_SECURITY_SIGN, MainTabActivity.g(this.b), MainTabActivity.h(this.b), MainTabActivity.i(this.b), MessageService.MSG_DB_READY_REPORT);
        LoginHelper.a();
        if (LoginHelper.c()) {
            BrowserUtil.a();
            BrowserUtil.a(this.b, "http://m.sjzhushou.com/v2/store/task_list.html?sign=1", "\u4efb\u52a1", R.styleable.AppCompatTheme_textAppearanceLargePopupMenu, null);
            return;
        }
        Intent intent = new Intent(this.b.getApplicationContext(), LoginActivity.class);
        intent.putExtra("login_from", this.b.getClass().getSimpleName() + "_appExit_sign");
        intent.putExtra("to_signpage", "to_signpage");
        intent.putExtra("login_type", 1);
        this.b.startActivity(intent);
    }
}
