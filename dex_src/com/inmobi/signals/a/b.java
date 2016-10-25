package com.inmobi.signals.a;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import org.json.JSONObject;

// compiled from: CellTowerInfo.java
public class b {
    private static final String a;
    private String b;
    private int c;
    private int d;

    static {
        a = b.class.getSimpleName();
    }

    @TargetApi(18)
    public b(CellInfo cellInfo, String str, String str2, int i) {
        if (cellInfo instanceof CellInfoGsm) {
            this.d = i;
            CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
            this.c = cellInfoGsm.getCellSignalStrength().getDbm();
            CellIdentityGsm cellIdentity = cellInfoGsm.getCellIdentity();
            this.b = a(str, str2, cellIdentity.getLac(), cellIdentity.getCid(), -1, InMobiClientPositioning.NO_REPEAT);
        } else if (cellInfo instanceof CellInfoCdma) {
            this.d = i;
            CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
            this.c = cellInfoCdma.getCellSignalStrength().getDbm();
            CellIdentityCdma cellIdentity2 = cellInfoCdma.getCellIdentity();
            this.b = a(str, cellIdentity2.getSystemId(), cellIdentity2.getNetworkId(), cellIdentity2.getBasestationId());
        } else if (VERSION.SDK_INT >= 18) {
            if (cellInfo instanceof CellInfoWcdma) {
                this.d = i;
                CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
                this.c = cellInfoWcdma.getCellSignalStrength().getDbm();
                CellIdentityWcdma cellIdentity3 = cellInfoWcdma.getCellIdentity();
                this.b = a(str, str2, cellIdentity3.getLac(), cellIdentity3.getCid(), cellIdentity3.getPsc(), InMobiClientPositioning.NO_REPEAT);
            }
        } else if (cellInfo instanceof CellInfoLte) {
            this.d = i;
            CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
            this.c = cellInfoLte.getCellSignalStrength().getDbm();
            CellIdentityLte cellIdentity4 = cellInfoLte.getCellIdentity();
            this.b = a(str, str2, cellIdentity4.getTac(), cellIdentity4.getCi(), -1, cellIdentity4.getPci());
        }
    }

    public String a(String str, int i, int i2, int i3) {
        return str + "#" + i + "#" + i2 + "#" + i3;
    }

    public String a(String str, String str2, int i, int i2, int i3, int i4) {
        return str + "#" + str2 + "#" + i + "#" + i2 + "#" + (i3 == -1 ? a.d : Integer.valueOf(i3)) + "#" + (i4 == Integer.MAX_VALUE ? a.d : Integer.valueOf(i4));
    }

    public void a(int i) {
        this.d = i;
    }

    public void a(String str) {
        this.b = str;
    }

    public void b(int i) {
        this.c = i;
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(SocializeConstants.WEIBO_ID, this.b);
            if (this.c != Integer.MAX_VALUE) {
                jSONObject.put("ss", this.c);
            }
            jSONObject.put("nt", this.d);
        } catch (Throwable e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Error while converting CellTowerInfo to string.", e);
        }
        return jSONObject;
    }
}
