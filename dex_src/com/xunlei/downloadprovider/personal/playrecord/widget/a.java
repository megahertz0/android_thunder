package com.xunlei.downloadprovider.personal.playrecord.widget;

import android.os.Message;
import android.text.TextUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.XLUserUtil;
import com.xunlei.common.pay.XLPayErrorCode;
import com.xunlei.downloadprovider.cloudlist.CommixturePlayRecord;
import com.xunlei.downloadprovider.cloudlist.CommixturePlayRecord.RecodeType;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.tdlive.im.ChatMessage;
import com.xunlei.xllib.b.d;
import java.io.File;
import java.util.List;

// compiled from: PlayRecordListWidget.java
final class a implements com.xunlei.downloadprovider.a.h.a {
    final /* synthetic */ PlayRecordListWidget a;

    a(PlayRecordListWidget playRecordListWidget) {
        this.a = playRecordListWidget;
    }

    public final void a(Message message) {
        switch (message.what) {
            case ChatMessage.FLAG_SYS_NOTIFY:
                if (message.obj instanceof List) {
                    List<com.xunlei.downloadprovider.vod.b.b.a> list = (List) message.obj;
                    if (!d.a(list)) {
                        for (com.xunlei.downloadprovider.vod.b.b.a aVar : list) {
                            if (aVar.d != 1) {
                                if (aVar.d != 0 || new File(aVar.f).exists()) {
                                    CommixturePlayRecord commixturePlayRecord = new CommixturePlayRecord();
                                    commixturePlayRecord.b = aVar;
                                    commixturePlayRecord.c = RecodeType.playRecord;
                                    PlayRecordListWidget.a(this.a, commixturePlayRecord);
                                }
                            }
                        }
                    }
                }
                LoginHelper.a();
                if (LoginHelper.c() && !TextUtils.isEmpty(XLUserUtil.getInstance().getCurrentUser().getStringValue(USERINFOKEY.UserID)) && LoginHelper.a().f()) {
                    PlayRecordListWidget.a(this.a, true);
                    return;
                }
                PlayRecordListWidget.a(this.a).m();
                PlayRecordListWidget.a(this.a).setMode(Mode.PULL_FROM_START);
                PlayRecordListWidget.b(this.a).b();
                PlayRecordListWidget.a(this.a).setVisibility(0);
                PlayRecordListWidget.c(this.a);
            case XLPayErrorCode.XLP_GATE_PARAM_ERROR:
                PlayRecordListWidget.a(this.a, message);
            default:
                break;
        }
    }
}
