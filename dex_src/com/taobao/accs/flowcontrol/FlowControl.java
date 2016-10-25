package com.taobao.accs.flowcontrol;

import android.content.Context;
import android.text.TextUtils;
import com.taobao.accs.flowcontrol.FlowControl.FlowControlInfo;
import com.taobao.accs.utl.ALog;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

// compiled from: Taobao
public class FlowControl {
    public static final int DELAY_MAX = -1;
    public static final int DELAY_MAX_BRUSH = -1000;
    public static final int HIGH_FLOW_CTRL = 2;
    public static final int HIGH_FLOW_CTRL_BRUSH = 3;
    public static final int LOW_FLOW_CTRL = 1;
    public static final int NO_FLOW_CTRL = 0;
    public static final String SERVICE_ALL = "ALL";
    public static final String SERVICE_ALL_BRUSH = "ALL_BRUSH";
    public static final int STATUS_FLOW_CTRL_ALL = 420;
    public static final int STATUS_FLOW_CTRL_BRUSH = 422;
    public static final int STATUS_FLOW_CTRL_CUR = 421;
    private Context a;
    private FlowCtrlInfoHolder b;

    // compiled from: Taobao
    public static class FlowControlInfo implements Serializable {
        private static final long serialVersionUID = -2259991484877844919L;
        public String bizId;
        public long delayTime;
        public long expireTime;
        public String serviceId;
        public long startTime;
        public int status;

        public FlowControlInfo(String str, String str2, int i, long j, long j2, long j3) {
            this.serviceId = str;
            this.bizId = str2;
            this.status = i;
            this.delayTime = j;
            if (j2 <= 0) {
                j2 = 0;
            }
            this.expireTime = j2;
            if (j3 <= 0) {
                j3 = 0;
            }
            this.startTime = j3;
        }

        public boolean isExpired() {
            return System.currentTimeMillis() - (this.startTime + this.expireTime) > 0;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("flow ctrl serviceId:").append(this.serviceId).append(" bizId:").append(this.bizId).append(" status:").append(this.status).append(" delayTime:").append(this.delayTime).append(" startTime:").append(this.startTime).append(" expireTime:").append(this.expireTime);
            return stringBuffer.toString();
        }
    }

    // compiled from: Taobao
    public static class FlowCtrlInfoHolder implements Serializable {
        private static final long serialVersionUID = 6307563052429742524L;
        Map<String, FlowControlInfo> flowCtrlMap;

        public FlowCtrlInfoHolder() {
            this.flowCtrlMap = null;
        }

        public void put(String str, String str2, FlowControlInfo flowControlInfo) {
            if (!TextUtils.isEmpty(str2)) {
                str = str + "_" + str2;
            }
            if (this.flowCtrlMap == null) {
                this.flowCtrlMap = new HashMap();
            }
            this.flowCtrlMap.put(str, flowControlInfo);
        }

        public FlowControlInfo get(String str, String str2) {
            if (this.flowCtrlMap == null) {
                return null;
            }
            if (!TextUtils.isEmpty(str2)) {
                str = str + "_" + str2;
            }
            return (FlowControlInfo) this.flowCtrlMap.get(str);
        }
    }

    public FlowControl(Context context) {
        this.a = context;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int a(java.util.Map<com.taobao.accs.base.TaoBaseService.ExtHeaderType, java.lang.String> r13, java.lang.String r14) {
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.flowcontrol.FlowControl.a(java.util.Map, java.lang.String):int");
        /*
        this = this;
        r6 = 0;
        r5 = 0;
        if (r13 == 0) goto L_0x00b8;
    L_0x0005:
        r2 = com.taobao.accs.base.TaoBaseService.ExtHeaderType.TYPE_STATUS;	 Catch:{ Throwable -> 0x00e2 }
        r2 = r13.get(r2);	 Catch:{ Throwable -> 0x00e2 }
        r2 = (java.lang.String) r2;	 Catch:{ Throwable -> 0x00e2 }
        r3 = com.taobao.accs.base.TaoBaseService.ExtHeaderType.TYPE_DELAY;	 Catch:{ Throwable -> 0x00e2 }
        r3 = r13.get(r3);	 Catch:{ Throwable -> 0x00e2 }
        r3 = (java.lang.String) r3;	 Catch:{ Throwable -> 0x00e2 }
        r4 = com.taobao.accs.base.TaoBaseService.ExtHeaderType.TYPE_EXPIRE;	 Catch:{ Throwable -> 0x00e2 }
        r4 = r13.get(r4);	 Catch:{ Throwable -> 0x00e2 }
        r0 = r4;
        r0 = (java.lang.String) r0;	 Catch:{ Throwable -> 0x00e2 }
        r8 = r0;
        r4 = com.taobao.accs.base.TaoBaseService.ExtHeaderType.TYPE_BUSINESS;	 Catch:{ Throwable -> 0x00e2 }
        r4 = r13.get(r4);	 Catch:{ Throwable -> 0x00e2 }
        r4 = (java.lang.String) r4;	 Catch:{ Throwable -> 0x00e2 }
        r9 = android.text.TextUtils.isEmpty(r2);	 Catch:{ Throwable -> 0x00e2 }
        if (r9 == 0) goto L_0x0052;
    L_0x002d:
        r5 = 0;
    L_0x002e:
        r2 = android.text.TextUtils.isEmpty(r3);	 Catch:{ Throwable -> 0x00e2 }
        if (r2 == 0) goto L_0x005b;
    L_0x0034:
        r6 = 0;
    L_0x0036:
        r2 = android.text.TextUtils.isEmpty(r8);	 Catch:{ Throwable -> 0x00e2 }
        if (r2 == 0) goto L_0x0064;
    L_0x003c:
        r8 = 0;
    L_0x003e:
        r2 = 420; // 0x1a4 float:5.89E-43 double:2.075E-321;
        if (r5 == r2) goto L_0x004a;
    L_0x0042:
        r2 = 421; // 0x1a5 float:5.9E-43 double:2.08E-321;
        if (r5 == r2) goto L_0x004a;
    L_0x0046:
        r2 = 422; // 0x1a6 float:5.91E-43 double:2.085E-321;
        if (r5 != r2) goto L_0x0050;
    L_0x004a:
        r2 = r12.a(r6, r8);	 Catch:{ Throwable -> 0x00e2 }
        if (r2 != 0) goto L_0x006d;
    L_0x0050:
        r2 = 0;
    L_0x0051:
        return r2;
    L_0x0052:
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ Throwable -> 0x00e2 }
        r5 = r2.intValue();	 Catch:{ Throwable -> 0x00e2 }
        goto L_0x002e;
    L_0x005b:
        r2 = java.lang.Long.valueOf(r3);	 Catch:{ Throwable -> 0x00e2 }
        r6 = r2.longValue();	 Catch:{ Throwable -> 0x00e2 }
        goto L_0x0036;
    L_0x0064:
        r2 = java.lang.Long.valueOf(r8);	 Catch:{ Throwable -> 0x00e2 }
        r8 = r2.longValue();	 Catch:{ Throwable -> 0x00e2 }
        goto L_0x003e;
    L_0x006d:
        monitor-enter(r12);	 Catch:{ Throwable -> 0x00e2 }
        r2 = r12.b;	 Catch:{ all -> 0x00df }
        if (r2 != 0) goto L_0x0079;
    L_0x0072:
        r2 = new com.taobao.accs.flowcontrol.FlowControl$FlowCtrlInfoHolder;	 Catch:{ all -> 0x00df }
        r2.<init>();	 Catch:{ all -> 0x00df }
        r12.b = r2;	 Catch:{ all -> 0x00df }
    L_0x0079:
        r2 = 0;
        r3 = 420; // 0x1a4 float:5.89E-43 double:2.075E-321;
        if (r5 != r3) goto L_0x00c0;
    L_0x007e:
        r2 = new com.taobao.accs.flowcontrol.FlowControl$FlowControlInfo;	 Catch:{ all -> 0x00df }
        r3 = "ALL";
        r4 = "";
        r10 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x00df }
        r2.<init>(r3, r4, r5, r6, r8, r10);	 Catch:{ all -> 0x00df }
        r3 = r12.b;	 Catch:{ all -> 0x00df }
        r4 = "ALL";
        r8 = "";
        r3.put(r4, r8, r2);	 Catch:{ all -> 0x00df }
    L_0x0098:
        if (r2 == 0) goto L_0x00b7;
    L_0x009a:
        r3 = "FlowControl";
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00df }
        r8 = "updateFlowCtrlInfo ";
        r4.<init>(r8);	 Catch:{ all -> 0x00df }
        r2 = r2.toString();	 Catch:{ all -> 0x00df }
        r2 = r4.append(r2);	 Catch:{ all -> 0x00df }
        r2 = r2.toString();	 Catch:{ all -> 0x00df }
        r4 = 0;
        r4 = new java.lang.Object[r4];	 Catch:{ all -> 0x00df }
        com.taobao.accs.utl.ALog.e(r3, r2, r4);	 Catch:{ all -> 0x00df }
    L_0x00b7:
        monitor-exit(r12);	 Catch:{ all -> 0x00df }
    L_0x00b8:
        r2 = 0;
        r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x010a;
    L_0x00be:
        r2 = 1;
        goto L_0x0051;
    L_0x00c0:
        r3 = 422; // 0x1a6 float:5.91E-43 double:2.085E-321;
        if (r5 != r3) goto L_0x00f0;
    L_0x00c4:
        r2 = new com.taobao.accs.flowcontrol.FlowControl$FlowControlInfo;	 Catch:{ all -> 0x00df }
        r3 = "ALL_BRUSH";
        r4 = "";
        r10 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x00df }
        r2.<init>(r3, r4, r5, r6, r8, r10);	 Catch:{ all -> 0x00df }
        r3 = r12.b;	 Catch:{ all -> 0x00df }
        r4 = "ALL_BRUSH";
        r8 = "";
        r3.put(r4, r8, r2);	 Catch:{ all -> 0x00df }
        goto L_0x0098;
    L_0x00df:
        r2 = move-exception;
        monitor-exit(r12);	 Catch:{ Throwable -> 0x00e2 }
        throw r2;	 Catch:{ Throwable -> 0x00e2 }
    L_0x00e2:
        r2 = move-exception;
        r3 = "FlowControl";
        r4 = "updateFlowCtrlInfo";
        r8 = 0;
        r8 = new java.lang.Object[r8];
        com.taobao.accs.utl.ALog.e(r3, r4, r2, r8);
        goto L_0x00b8;
    L_0x00f0:
        r3 = 421; // 0x1a5 float:5.9E-43 double:2.08E-321;
        if (r5 != r3) goto L_0x0098;
    L_0x00f4:
        r3 = android.text.TextUtils.isEmpty(r14);	 Catch:{ all -> 0x00df }
        if (r3 != 0) goto L_0x0098;
    L_0x00fa:
        r2 = new com.taobao.accs.flowcontrol.FlowControl$FlowControlInfo;	 Catch:{ all -> 0x00df }
        r10 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x00df }
        r3 = r14;
        r2.<init>(r3, r4, r5, r6, r8, r10);	 Catch:{ all -> 0x00df }
        r3 = r12.b;	 Catch:{ all -> 0x00df }
        r3.put(r14, r4, r2);	 Catch:{ all -> 0x00df }
        goto L_0x0098;
    L_0x010a:
        r2 = 0;
        r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1));
        if (r2 != 0) goto L_0x0113;
    L_0x0110:
        r2 = 0;
        goto L_0x0051;
    L_0x0113:
        r2 = 422; // 0x1a6 float:5.91E-43 double:2.085E-321;
        if (r2 != r5) goto L_0x011a;
    L_0x0117:
        r2 = 3;
        goto L_0x0051;
    L_0x011a:
        r2 = 2;
        goto L_0x0051;
        */
    }

    private boolean a(long j, long j2) {
        if (j != 0 && j2 > 0) {
            return true;
        }
        ALog.e("FlowControl", "error flow ctrl info", new Object[0]);
        return false;
    }

    public long a(String str, String str2) {
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        if (this.b == null || this.b.flowCtrlMap == null || TextUtils.isEmpty(str)) {
            return 0;
        }
        long j5;
        synchronized (this) {
            FlowControlInfo flowControlInfo = this.b.get(SERVICE_ALL, null);
            FlowControlInfo flowControlInfo2 = this.b.get(SERVICE_ALL_BRUSH, null);
            FlowControlInfo flowControlInfo3 = this.b.get(str, null);
            FlowControlInfo flowControlInfo4 = this.b.get(str, str2);
            if (flowControlInfo != null) {
                j2 = flowControlInfo.isExpired() ? 0 : flowControlInfo.delayTime;
            }
            if (flowControlInfo2 != null) {
                j3 = flowControlInfo2.isExpired() ? 0 : flowControlInfo2.delayTime;
            }
            if (flowControlInfo3 != null) {
                j4 = flowControlInfo3.isExpired() ? 0 : flowControlInfo3.delayTime;
            }
            if (flowControlInfo4 != null) {
                j = flowControlInfo4.isExpired() ? 0 : flowControlInfo4.delayTime;
            }
            if (j2 == -1 || j == -1 || j4 == -1) {
                j5 = -1;
            } else if (j3 == -1) {
                j5 = -1000;
            } else {
                if (j2 > j) {
                    j5 = j2;
                } else {
                    j5 = j;
                }
                if (j5 <= j4) {
                    j5 = j4;
                }
            }
            if ((flowControlInfo4 != null && flowControlInfo4.isExpired()) || (flowControlInfo != null && flowControlInfo.isExpired())) {
                a();
            }
        }
        ALog.e("FlowControl", new StringBuilder("getFlowCtrlDelay service ").append(str).append(" biz ").append(str2).append(" result:").append(j5).append(" global:").append(j2).append(" serviceDelay:").append(j4).append(" bidDelay:").append(j).toString(), new Object[0]);
        return j5;
    }

    private void a() {
        if (this.b != null && this.b.flowCtrlMap != null) {
            synchronized (this) {
                Iterator it = this.b.flowCtrlMap.entrySet().iterator();
                while (it.hasNext()) {
                    if (((FlowControlInfo) ((Entry) it.next()).getValue()).isExpired()) {
                        it.remove();
                    }
                }
            }
        }
    }
}
