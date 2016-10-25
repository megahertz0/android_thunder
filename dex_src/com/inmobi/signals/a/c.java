package com.inmobi.signals.a;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import com.inmobi.commons.a.a;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.d;
import com.inmobi.signals.o;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

@TargetApi(17)
// compiled from: CellularInfoUtil.java
public class c {
    private static final String a;

    static {
        a = c.class.getSimpleName();
    }

    public static Map<String, String> a() {
        Map<String, String> hashMap = new HashMap();
        if (!o.a().e().n()) {
            return hashMap;
        }
        int m = o.a().e().m();
        boolean a = a(m, XZBDevice.DOWNLOAD_LIST_RECYCLE);
        boolean a2 = a(m, 1);
        a aVar = new a();
        TelephonyManager telephonyManager = (TelephonyManager) a.b().getSystemService("phone");
        if (!a) {
            int[] a3 = a(telephonyManager.getNetworkOperator());
            aVar.a(a3[0]);
            aVar.b(a3[1]);
            aVar.a(telephonyManager.getNetworkCountryIso());
        }
        if (!a2) {
            int[] a4 = a(telephonyManager.getSimOperator());
            aVar.c(a4[0]);
            aVar.d(a4[1]);
        }
        hashMap.put("s-ho", aVar.b());
        hashMap.put("s-co", aVar.a());
        hashMap.put("s-iso", aVar.c());
        return hashMap;
    }

    private static boolean a(int i, int i2) {
        return (i & i2) == i2;
    }

    private static int[] a(String str) {
        int[] iArr = new int[]{-1, -1};
        if (!(str == null || str.equals(com.umeng.a.d))) {
            try {
                int parseInt = Integer.parseInt(str.substring(0, XZBDevice.DOWNLOAD_LIST_FAILED));
                int parseInt2 = Integer.parseInt(str.substring(XZBDevice.DOWNLOAD_LIST_FAILED));
                iArr[0] = parseInt;
                iArr[1] = parseInt2;
            } catch (Throwable e) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Error while collecting cell info.", e);
            } catch (Throwable e2) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Error while collecting cell info.", e2);
            }
        }
        return iArr;
    }

    public static b b() {
        return (o.a().e().p() && f()) ? g() : null;
    }

    public static Map<String, String> c() {
        b b = b();
        Map hashMap = new HashMap();
        if (b != null) {
            hashMap.put("c-sc", b.a().toString());
        }
        return hashMap;
    }

    private static boolean f() {
        boolean z;
        boolean z2;
        if (d.a("signals", "android.permission.ACCESS_COARSE_LOCATION")) {
            int i = 1;
        } else {
            z = false;
        }
        if (d.a("signals", "android.permission.ACCESS_FINE_LOCATION")) {
            int i2 = 1;
        } else {
            z2 = false;
        }
        return z || z2;
    }

    private static b g() {
        TelephonyManager telephonyManager = (TelephonyManager) a.b().getSystemService("phone");
        int[] a = a(telephonyManager.getNetworkOperator());
        String valueOf = String.valueOf(a[0]);
        String valueOf2 = String.valueOf(a[1]);
        if (VERSION.SDK_INT >= 17) {
            List allCellInfo = telephonyManager.getAllCellInfo();
            if (allCellInfo != null) {
                CellInfo cellInfo = null;
                for (int i = 0; i < allCellInfo.size(); i++) {
                    cellInfo = (CellInfo) allCellInfo.get(i);
                    if (cellInfo.isRegistered()) {
                        break;
                    }
                }
                CellInfo cellInfo2 = cellInfo;
                if (cellInfo2 != null) {
                    return new b(cellInfo2, valueOf, valueOf2, telephonyManager.getNetworkType());
                }
            }
        }
        CellLocation cellLocation = telephonyManager.getCellLocation();
        if (cellLocation == null || a[0] == -1) {
            return null;
        }
        b bVar = new b();
        if (cellLocation instanceof CdmaCellLocation) {
            CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
            bVar.b(InMobiClientPositioning.NO_REPEAT);
            bVar.a(telephonyManager.getNetworkType());
            bVar.a(bVar.a(valueOf, cdmaCellLocation.getSystemId(), cdmaCellLocation.getNetworkId(), cdmaCellLocation.getBaseStationId()));
            return bVar;
        }
        GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
        bVar.b(InMobiClientPositioning.NO_REPEAT);
        bVar.a(telephonyManager.getNetworkType());
        bVar.a(bVar.a(valueOf, valueOf2, gsmCellLocation.getLac(), gsmCellLocation.getCid(), gsmCellLocation.getPsc(), InMobiClientPositioning.NO_REPEAT));
        return bVar;
    }

    public static Map<String, String> d() {
        List e = e();
        Map hashMap = new HashMap();
        if (!(e == null || e.isEmpty())) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(((b) e.get(e.size() - 1)).a());
            hashMap.put("v-sc", jSONArray.toString());
        }
        return hashMap;
    }

    public static List<b> e() {
        if (!h() || !o.a().e().o()) {
            return null;
        }
        TelephonyManager telephonyManager = (TelephonyManager) a.b().getSystemService("phone");
        List<b> arrayList = new ArrayList();
        int[] a = a(telephonyManager.getNetworkOperator());
        String valueOf = String.valueOf(a[0]);
        String valueOf2 = String.valueOf(a[1]);
        if (VERSION.SDK_INT >= 17) {
            List<CellInfo> allCellInfo = telephonyManager.getAllCellInfo();
            if (allCellInfo != null) {
                for (CellInfo cellInfo : allCellInfo) {
                    if (!cellInfo.isRegistered()) {
                        arrayList.add(new b(cellInfo, valueOf, valueOf2, telephonyManager.getNetworkType()));
                    }
                }
                return arrayList;
            }
        }
        List neighboringCellInfo = telephonyManager.getNeighboringCellInfo();
        if (neighboringCellInfo == null || neighboringCellInfo.isEmpty()) {
            return null;
        }
        Iterator it = neighboringCellInfo.iterator();
        if (!it.hasNext()) {
            return null;
        }
        NeighboringCellInfo neighboringCellInfo2 = (NeighboringCellInfo) it.next();
        b bVar = new b();
        int networkType = neighboringCellInfo2.getNetworkType();
        bVar.a(networkType);
        if (neighboringCellInfo2.getRssi() == 99) {
            bVar.b(InMobiClientPositioning.NO_REPEAT);
        } else if (a(networkType)) {
            bVar.b(neighboringCellInfo2.getRssi() - 116);
        } else {
            bVar.b((neighboringCellInfo2.getRssi() * 2) - 113);
        }
        bVar.a(bVar.a(valueOf, valueOf2, neighboringCellInfo2.getLac(), neighboringCellInfo2.getCid(), -1, InMobiClientPositioning.NO_REPEAT));
        arrayList.add(bVar);
        return arrayList;
    }

    private static boolean a(int i) {
        switch (i) {
            case XZBDevice.DOWNLOAD_LIST_FAILED:
            case XZBDevice.Wait:
            case XZBDevice.Pause:
            case XZBDevice.Stop:
            case XZBDevice.Delete:
                return true;
            default:
                return false;
        }
    }

    private static boolean h() {
        return d.a("signals", "android.permission.ACCESS_COARSE_LOCATION");
    }
}
