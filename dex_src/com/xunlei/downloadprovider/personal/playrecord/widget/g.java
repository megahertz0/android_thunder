package com.xunlei.downloadprovider.personal.playrecord.widget;

import android.text.TextUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.xunlei.common.member.XLUserInfo.USERINFOKEY;
import com.xunlei.common.member.XLUserUtil;
import com.xunlei.common.yunbo.XLYB_VODINFO;
import com.xunlei.common.yunbo.XLYunboListener;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.cloudlist.CommixturePlayRecord;
import com.xunlei.downloadprovider.cloudlist.CommixturePlayRecord.RecodeType;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.personal.playrecord.PlayRecordFragment$OntainState;
import com.xunlei.xllib.a.b;
import java.util.Collection;
import java.util.HashSet;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: PlayRecordListWidget.java
final class g extends XLYunboListener {
    final /* synthetic */ PlayRecordListWidget a;

    g(PlayRecordListWidget playRecordListWidget) {
        this.a = playRecordListWidget;
    }

    public final boolean OnObtainVideoList(int i, String str, int i2, XLYB_VODINFO[] xlyb_vodinfoArr, int i3, Object obj) {
        PlayRecordListWidget.d(this.a);
        new StringBuilder("OnObtainPlayList : result = ").append(i).append(" vodlist =").append(xlyb_vodinfoArr).append(" , totleCount = ").append(i3);
        if (obj == PlayRecordListWidget.e(this.a)) {
            PlayRecordListWidget.f(this.a);
            if (i == 0) {
                if (xlyb_vodinfoArr == null || xlyb_vodinfoArr.length <= 0) {
                    PlayRecordListWidget.i(this.a);
                } else {
                    PlayRecordListWidget.a(this.a, xlyb_vodinfoArr.length);
                    if (PlayRecordListWidget.g(this.a) != null) {
                        PlayRecordListWidget.g(this.a).a();
                        PlayRecordListWidget.h(this.a);
                    }
                    LoginHelper.a();
                    if (LoginHelper.c() && !TextUtils.isEmpty(XLUserUtil.getInstance().getCurrentUser().getStringValue(USERINFOKEY.UserID)) && LoginHelper.a().f()) {
                        int length = xlyb_vodinfoArr.length;
                        for (int i4 = 0; i4 < length; i4++) {
                            XLYB_VODINFO xlyb_vodinfo = xlyb_vodinfoArr[i4];
                            CommixturePlayRecord commixturePlayRecord = new CommixturePlayRecord();
                            xlyb_vodinfo.tasktype = 1;
                            commixturePlayRecord.a = xlyb_vodinfo;
                            commixturePlayRecord.c = RecodeType.vodInfo;
                            PlayRecordListWidget.a(this.a, commixturePlayRecord);
                        }
                    }
                }
                PlayRecordListWidget.b(this.a, PlayRecordListWidget.j(this.a));
            } else {
                PlayRecordListWidget.k(this.a);
            }
        }
        PlayRecordListWidget.a(this.a).m();
        PlayRecordListWidget.a(this.a).setMode(Mode.PULL_FROM_START);
        PlayRecordListWidget.b(this.a).b();
        PlayRecordListWidget.a(this.a).setVisibility(0);
        PlayRecordListWidget.c(this.a);
        PlayRecordListWidget.a(this.a, PlayRecordFragment$OntainState.idle);
        return true;
    }

    public final boolean OnDeleteVideo(int i, String str, int i2, Object obj) {
        PlayRecordListWidget.d(this.a);
        new StringBuilder("OnDeletePlayHistory : result = ").append(i).append(" userData = ").append(obj);
        if (obj == PlayRecordListWidget.l(this.a)) {
            PlayRecordListWidget.m(this.a);
            if (i == 0) {
                Collection hashSet = new HashSet();
                for (CommixturePlayRecord commixturePlayRecord : PlayRecordListWidget.n(this.a)) {
                    switch (AnonymousClass_1.a[commixturePlayRecord.c.ordinal()]) {
                        case SimpleLog.LOG_LEVEL_TRACE:
                            if (PlayRecordListWidget.a(this.a, commixturePlayRecord.a.src_url, commixturePlayRecord.a.url_hash)) {
                                hashSet.add(commixturePlayRecord);
                            }
                        default:
                            break;
                    }
                }
                PlayRecordListWidget.n(this.a).removeAll(hashSet);
                PlayRecordListWidget.c(this.a);
                if (PlayRecordListWidget.o(this.a)) {
                    PlayRecordListWidget.a(this.a, false);
                } else {
                    PlayRecordListWidget.c(this.a);
                    PlayRecordListWidget.b(this.a).b();
                    PlayRecordListWidget.a(this.a).setVisibility(0);
                    XLToast.a(this.a.getContext(), XLToastType.XLTOAST_TYPE_NORMAL, BrothersApplication.a().getString(R.string.cloud_list_toast_delete_suc));
                }
                if (this.a.e) {
                    this.a.e();
                    if (this.a.g != null) {
                        this.a.g.a(this.a.e);
                    }
                }
                this.a.F.post(new m(this.a, this.a.p));
            } else {
                this.a.A.b();
                this.a.z.setVisibility(0);
                if (b.a(BrothersApplication.a().getApplicationContext())) {
                    XLToast.a(this.a.getContext(), XLToastType.XLTOAST_TYPE_NORMAL, BrothersApplication.a().getString(R.string.cloud_list_toast_delete_fail));
                } else {
                    XLToast.a(this.a.getContext(), XLToastType.XLTOAST_TYPE_NORMAL, BrothersApplication.a().getString(R.string.cloud_list_toast_delete_fail_net));
                }
            }
            if (this.a.o) {
                this.a.b();
            }
        }
        this.a.A.b();
        return true;
    }
}
