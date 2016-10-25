package com.xunlei.downloadprovider.personal.lixianspace.widget;

import com.xunlei.common.lixian.XLLX_TASKRESPSTATUS;
import com.xunlei.common.lixian.XLLixianListener;
import com.xunlei.common.lixian.XLLixianTask;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.personal.lixianspace.LixianSpaceFragment$OntainState;
import com.xunlei.xllib.a.b;
import java.util.Collection;
import java.util.HashSet;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: LixianSpaceListWidget.java
final class f extends XLLixianListener {
    final /* synthetic */ LixianSpaceListWidget a;

    f(LixianSpaceListWidget lixianSpaceListWidget) {
        this.a = lixianSpaceListWidget;
    }

    public final boolean OnDeleteTasksToTrash(int i, String str, int i2, XLLX_TASKRESPSTATUS[] xllx_taskrespstatusArr, Object obj) {
        this.a.j;
        new StringBuilder("OnDeleteTasksToTrash : result = ").append(i).append(" taskStatus =").append(xllx_taskrespstatusArr);
        if (obj == this.a.c) {
            this.a.c = null;
            if (i != 0) {
                this.a.d = false;
                this.a.g.b();
                this.a.z.setVisibility(0);
                if (b.a(BrothersApplication.a().getApplicationContext())) {
                    XLToast.a(this.a.getContext(), XLToastType.XLTOAST_TYPE_NORMAL, BrothersApplication.a().getString(R.string.cloud_list_toast_delete_fail));
                } else {
                    XLToast.a(this.a.getContext(), XLToastType.XLTOAST_TYPE_NORMAL, BrothersApplication.a().getString(R.string.cloud_list_toast_delete_fail_net));
                }
            } else if (xllx_taskrespstatusArr == null || xllx_taskrespstatusArr.length <= 0) {
                this.a.d = false;
                this.a.g.b();
                this.a.z.setVisibility(0);
                if (b.a(BrothersApplication.a().getApplicationContext())) {
                    XLToast.a(this.a.getContext(), XLToastType.XLTOAST_TYPE_NORMAL, BrothersApplication.a().getString(R.string.cloud_list_toast_delete_fail));
                } else {
                    XLToast.a(this.a.getContext(), XLToastType.XLTOAST_TYPE_NORMAL, BrothersApplication.a().getString(R.string.cloud_list_toast_delete_fail_net));
                }
            } else {
                Collection hashSet = new HashSet();
                int length = xllx_taskrespstatusArr.length;
                for (int i3 = 0; i3 < length; i3++) {
                    XLLX_TASKRESPSTATUS xllx_taskrespstatus = xllx_taskrespstatusArr[i3];
                    if (xllx_taskrespstatus.status == 0) {
                        hashSet.add(Long.valueOf(xllx_taskrespstatus.taskid));
                    }
                }
                Collection hashSet2 = new HashSet();
                for (XLLixianTask xLLixianTask : this.a.l) {
                    if (hashSet.contains(Long.valueOf(xLLixianTask.getTaskId()))) {
                        hashSet2.add(xLLixianTask);
                    }
                }
                this.a.l.removeAll(hashSet2);
                this.a.a.removeAll(hashSet2);
                this.a.m.removeAll(hashSet);
                this.a.a();
                if (this.a.g()) {
                    this.a.a(LixianSpaceFragment$OntainState.auto_obtaining);
                } else {
                    this.a.g.b();
                    this.a.z.setVisibility(0);
                    this.a.d = false;
                    XLToast.a(this.a.getContext(), XLToastType.XLTOAST_TYPE_NORMAL, BrothersApplication.a().getString(R.string.cloud_list_toast_delete_suc));
                }
                if (this.a.e) {
                    this.a.d();
                    if (this.a.h != null) {
                        this.a.h.a(this.a.e);
                    }
                }
                this.a.I.post(new h(this.a, this.a.r));
            }
        } else {
            this.a.d = false;
        }
        return true;
    }

    public final boolean OnObtainLixianTasks(int i, String str, int i2, XLLixianTask[] xLLixianTaskArr, int i3, Object obj) {
        if (obj == this.a.o) {
            this.a.o = null;
            if (i == 0) {
                if (xLLixianTaskArr == null || xLLixianTaskArr.length <= 0) {
                    this.a.z.m();
                    LixianSpaceListWidget.v(this.a);
                } else {
                    if (this.a.p == LixianSpaceFragment$OntainState.refreshing) {
                        this.a.l.clear();
                    }
                    int length = xLLixianTaskArr.length;
                    for (int i4 = 0; i4 < length; i4++) {
                        XLLixianTask xLLixianTask = xLLixianTaskArr[i4];
                        long j = xLLixianTask.getDetailInfo().taskid;
                        switch (AnonymousClass_1.a[LixianSpaceListWidget.a(xLLixianTask).ordinal()]) {
                            case SimpleLog.LOG_LEVEL_TRACE:
                                this.a.J.put(Long.valueOf(j), "\u4eca\u5929");
                                break;
                            case SimpleLog.LOG_LEVEL_DEBUG:
                                this.a.J.put(Long.valueOf(j), "\u6628\u5929");
                                break;
                            case MqttConnectOptions.MQTT_VERSION_3_1:
                                this.a.J.put(Long.valueOf(j), "3\u5929\u524d");
                                break;
                            default:
                                break;
                        }
                        this.a.l.add(xLLixianTask);
                    }
                    this.a.a(false);
                    if (this.a.g()) {
                        this.a.p = LixianSpaceFragment$OntainState.idle;
                        this.a.a(LixianSpaceFragment$OntainState.auto_refreshing);
                    } else {
                        if (this.a.d) {
                            this.a.d = false;
                            this.a.g.b();
                            this.a.z.setVisibility(0);
                            XLToast.a(this.a.getContext(), XLToastType.XLTOAST_TYPE_NORMAL, BrothersApplication.a().getString(R.string.cloud_list_toast_delete_suc));
                        } else if (!this.a.s) {
                            if ((this.a.p == LixianSpaceFragment$OntainState.refreshing || this.a.p == LixianSpaceFragment$OntainState.auto_refreshing) && this.a.h != null) {
                                this.a.s = true;
                                this.a.h.a(BrothersApplication.a().getString(R.string.cloud_list_toast_update_finish));
                            }
                        }
                        this.a.a(false);
                    }
                }
                this.a.I.post(new h(this.a, this.a.r));
            } else if (i == 7) {
                LixianSpaceListWidget.a(this.a, BrothersApplication.a().getString(R.string.cloud_list_toast_user_locked));
            } else {
                LixianSpaceListWidget.a(this.a, BrothersApplication.a().getString(R.string.cloud_list_toast_obtain_fail));
            }
            if (this.a.C != null) {
                if (this.a.g != null) {
                    this.a.g.b();
                }
                this.a.C.a();
                this.a.C = null;
            }
        }
        this.a.z.m();
        return true;
    }
}
