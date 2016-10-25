package com.taobao.accs.net;

import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.Session;
import anet.channel.SessionCenter;
import com.taobao.accs.common.Constants;
import com.taobao.accs.data.Message;
import com.taobao.accs.data.Message.b;
import com.taobao.accs.ut.monitor.TrafficsMonitor.a;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.h;
import com.tencent.bugly.crashreport.crash.BuglyBroadcastRecevier;
import com.tencent.mm.sdk.modelbase.BaseResp.ErrCode;
import com.tencent.open.yyb.AppbarJsBridge;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.tdlive.sdk.IHost;
import org.android.agoo.intent.IntentUtil;

// compiled from: Taobao
class j implements Runnable {
    final /* synthetic */ Message a;
    final /* synthetic */ i b;

    j(i iVar, Message message) {
        this.b = iVar;
        this.a = message;
    }

    public void run() {
        if (this.a != null) {
            if (this.a.getNetPermanceMonitor() != null) {
                this.a.getNetPermanceMonitor().onTakeFromQueue();
            }
            int type = this.a.getType();
            try {
                boolean z;
                if (ALog.isPrintLog(Level.D)) {
                    ALog.d("InAppConnection", new StringBuilder("send:").append(b.b(type)).append(" dataId:").append(this.a.dataId).toString(), new Object[0]);
                }
                if (type != 1) {
                    ALog.e("InAppConnection", new StringBuilder("skip msg type").append(b.b(type)).toString(), new Object[0]);
                    z = true;
                } else if (this.a.host == null) {
                    this.b.c.a(this.a, (int) AppbarJsBridge.AUTHORIZE_FAIL);
                    z = true;
                } else {
                    Session session = SessionCenter.getInstance().get(this.a.host.toString(), BuglyBroadcastRecevier.UPLOADLIMITED);
                    SessionCenter.getInstance().setDataReceiveCb(this.b);
                    if (session != null) {
                        byte[] build = this.a.build(this.b.b, this.b.a);
                        if (ALog.isPrintLog(Level.I)) {
                            String str = "InAppConnection";
                            String str2 = "send data ";
                            Object[] objArr = new Object[8];
                            objArr[0] = "len";
                            objArr[1] = Integer.valueOf(build == null ? 0 : build.length);
                            objArr[2] = Constants.KEY_DATA_ID;
                            objArr[3] = this.a.getDataId();
                            objArr[4] = IntentUtil.AGOO_COMMAND;
                            objArr[5] = this.a.command;
                            objArr[6] = " host";
                            objArr[7] = this.a.host;
                            ALog.i(str, str2, objArr);
                        }
                        this.a.setSendTime(System.currentTimeMillis());
                        if (build.length <= 16384 || this.a.command.intValue() == 102) {
                            this.b.c.a(this.a);
                            if (this.a.isAck) {
                                this.b.e.put(Integer.valueOf(this.a.getIntDataId()), this.a);
                            }
                            session.sendCustomFrame(this.a.getIntDataId(), build, Impl.STATUS_SUCCESS);
                            if (this.a.getNetPermanceMonitor() != null) {
                                this.a.getNetPermanceMonitor().onSendData();
                            }
                            this.b.a(this.a.getDataId(), (long) this.a.timeout);
                            this.b.c.a(new a(this.a.serviceId, GlobalAppRuntimeInfo.isAppBackground(), this.a.host.toString(), (long) build.length));
                            z = true;
                        } else {
                            this.b.c.a(this.a, (int) ErrCode.ERR_AUTH_DENIED);
                            z = true;
                        }
                    } else {
                        z = false;
                    }
                }
                if (!z) {
                    if (type == 1) {
                        if (this.a.isTimeOut() || !this.b.a(this.a, IHost.CLIENT_NOFITY_INIT)) {
                            this.b.c.a(this.a, (int) com.tencent.connect.common.Constants.ERROR_FILE_EXISTED);
                        }
                        if (this.a.retryTimes == 1 && this.a.getNetPermanceMonitor() != null) {
                            com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.COUNT_POINT_RESEND, "total_accs", 0.0d);
                        }
                    } else {
                        this.b.c.a(this.a, (int) com.tencent.connect.common.Constants.ERROR_FILE_EXISTED);
                    }
                }
                if (ALog.isPrintLog(Level.D)) {
                    ALog.d("InAppConnection", new StringBuilder("sendSucc").append(z).append(" dataId:").append(this.a.getDataId()).toString(), new Object[0]);
                }
            } catch (Throwable th) {
                try {
                    com.taobao.accs.utl.b.a(h.NAMESPACE, BaseMonitor.ALARM_POINT_REQ_ERROR, this.a.serviceId, com.umeng.a.d, this.b.a + th.toString());
                    ALog.e("InAppConnection", "sendMessage", th, new Object[0]);
                    if (ALog.isPrintLog(Level.D)) {
                        ALog.d("InAppConnection", new StringBuilder("sendSucc").append(true).append(" dataId:").append(this.a.getDataId()).toString(), new Object[0]);
                    }
                } catch (Throwable th2) {
                    if (ALog.isPrintLog(Level.D)) {
                        ALog.d("InAppConnection", new StringBuilder("sendSucc").append(true).append(" dataId:").append(this.a.getDataId()).toString(), new Object[0]);
                    }
                }
            }
        }
    }
}
