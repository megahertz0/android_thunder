package com.baidu.mobads.i;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
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
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.j.m;
import com.taobao.accs.utl.UtilityImpl;
import com.xunlei.downloadprovider.vod.VodPlayerActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class a {
    private static Method d;
    private static Method e;
    private static Method f;
    private static Class<?> g;
    private static char[] n;
    private Context a;
    private TelephonyManager b;
    private a c;
    private WifiManager h;
    private b i;
    private long j;
    private String k;
    private int l;
    private String m;

    private class a {
        public int a;
        public int b;
        public int c;
        public int d;
        public char e;

        private a() {
            this.a = -1;
            this.b = -1;
            this.c = -1;
            this.d = -1;
            this.e = '\u0000';
        }

        private boolean b() {
            return this.a >= 0 && this.b > 0;
        }

        public String a() {
            if (!b()) {
                return null;
            }
            StringBuffer stringBuffer = new StringBuffer(128);
            stringBuffer.append(this.e);
            stringBuffer.append(IXAdRequestInfo.HEIGHT);
            if (this.c != 460) {
                stringBuffer.append(this.c);
            }
            stringBuffer.append(String.format(Locale.CHINA, "h%xh%xh%x", new Object[]{Integer.valueOf(this.d), Integer.valueOf(this.a), Integer.valueOf(this.b)}));
            return stringBuffer.toString();
        }
    }

    protected class b {
        public List<ScanResult> a;
        private long c;

        public b(List<ScanResult> list) {
            this.a = null;
            this.c = 0;
            this.a = list;
            this.c = System.currentTimeMillis();
            b();
        }

        public int a() {
            return this.a == null ? 0 : this.a.size();
        }

        public String a(int i) {
            if (a() <= 0) {
                return null;
            }
            Object obj;
            Object obj2;
            Object obj3;
            String toString;
            boolean a = a.this.c();
            if (a) {
                i--;
                obj = null;
            } else {
                int i2 = 1;
            }
            StringBuffer stringBuffer = new StringBuffer(512);
            int size = this.a.size();
            int i3 = 0;
            int i4 = 0;
            int i5 = 1;
            Object obj4 = obj;
            while (i3 < size) {
                if (((ScanResult) this.a.get(i3)).level != 0) {
                    String str = ((ScanResult) this.a.get(i3)).BSSID;
                    i2 = ((ScanResult) this.a.get(i3)).level;
                    str = str.replace(":", com.umeng.a.d);
                    if (a.this.k == null || !str.equals(a.this.k)) {
                        if (i4 < i) {
                            stringBuffer.append(IXAdRequestInfo.HEIGHT);
                            stringBuffer.append(str);
                            stringBuffer.append("m");
                            stringBuffer.append(StrictMath.abs(i2));
                            i2 = i4 + 1;
                            obj2 = null;
                        } else {
                            i2 = i4;
                            obj2 = obj3;
                        }
                        if (i2 > i && obj4 != null) {
                            break;
                        }
                        obj3 = obj4;
                    } else {
                        a.this.l = StrictMath.abs(i2);
                        i2 = i4;
                        obj2 = obj3;
                        i5 = 1;
                    }
                } else {
                    i2 = i4;
                    obj2 = obj3;
                    obj3 = obj4;
                }
                i3++;
                obj4 = obj3;
                obj3 = obj2;
                i4 = i2;
            }
            obj2 = obj3;
            if (a) {
                toString = new StringBuilder(IXAdRequestInfo.HEIGHT).append(a.this.k).append("km").append(a.this.l).toString();
            } else {
                toString = null;
            }
            return obj2 == null ? toString + stringBuffer.toString() : toString;
        }

        private void b() {
            if (a() > 0) {
                int i = 1;
                for (int size = this.a.size() - 1; size > 0 && r2 != null; size--) {
                    int i2 = 0;
                    Object obj = null;
                    while (i2 < size) {
                        Object obj2;
                        if (((ScanResult) this.a.get(i2)).level < ((ScanResult) this.a.get(i2 + 1)).level) {
                            ScanResult scanResult = (ScanResult) this.a.get(i2 + 1);
                            this.a.set(i2 + 1, this.a.get(i2));
                            this.a.set(i2, scanResult);
                            int i3 = 1;
                        } else {
                            obj2 = obj;
                        }
                        i2++;
                        obj = obj2;
                    }
                }
            }
        }

        private boolean c() {
            long currentTimeMillis = System.currentTimeMillis() - this.c;
            return currentTimeMillis < 0 || currentTimeMillis > 500;
        }
    }

    static {
        d = null;
        e = null;
        f = null;
        g = null;
        n = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_.".toCharArray();
    }

    public a(Context context) {
        String str;
        this.a = null;
        this.b = null;
        this.c = new a();
        this.h = null;
        this.i = null;
        this.j = 0;
        this.k = null;
        this.l = 0;
        this.m = null;
        this.a = context.getApplicationContext();
        String packageName = this.a.getPackageName();
        try {
            this.b = (TelephonyManager) this.a.getSystemService("phone");
            str = (String) m.a().m().a(this.b, m.a().e().decodeStr("uvNYwANvpyP-iyfb"), new Object[0]);
        } catch (Exception e) {
            str = null;
        }
        this.m = new StringBuilder(com.alipay.sdk.sys.a.b).append(packageName).append(com.alipay.sdk.sys.a.b).append(str).toString();
        this.h = (WifiManager) this.a.getSystemService(UtilityImpl.NET_TYPE_WIFI);
    }

    public String a() {
        try {
            return a((int) XZBDevice.Stop);
        } catch (Exception e) {
            return null;
        }
    }

    private String a(int i) {
        String a;
        if (i < 3) {
            i = 3;
        }
        try {
            a b = b();
            if (b == null || !b.b()) {
                a(this.b.getCellLocation());
            } else {
                this.c = b;
            }
            a = this.c.a();
        } catch (Exception e) {
            a = null;
        }
        if (a == null) {
            a = "Z";
        }
        try {
            if (this.i == null || this.i.c()) {
                this.i = new b(this.h.getScanResults());
            }
            String a2 = this.i.a(i);
        } catch (Exception e2) {
            a2 = null;
        }
        if (a2 != null) {
            a = a + a2;
        }
        return a.equals("Z") ? null : a(a + anet.channel.strategy.dispatch.a.TIMESTAMP + System.currentTimeMillis() + this.m);
    }

    private void a(CellLocation cellLocation) {
        int i = 0;
        if (cellLocation != null && this.b != null) {
            a aVar = new a();
            String networkOperator = this.b.getNetworkOperator();
            if (networkOperator != null && networkOperator.length() > 0) {
                try {
                    if (networkOperator.length() >= 3) {
                        int intValue = Integer.valueOf(networkOperator.substring(0, XZBDevice.DOWNLOAD_LIST_FAILED)).intValue();
                        if (intValue < 0) {
                            intValue = this.c.c;
                        }
                        aVar.c = intValue;
                    }
                    String substring = networkOperator.substring(XZBDevice.DOWNLOAD_LIST_FAILED);
                    if (substring != null) {
                        char[] toCharArray = substring.toCharArray();
                        while (i < toCharArray.length && Character.isDigit(toCharArray[i])) {
                            i++;
                        }
                    }
                    i = Integer.valueOf(substring.substring(0, i)).intValue();
                    if (i < 0) {
                        i = this.c.d;
                    }
                    aVar.d = i;
                } catch (Exception e) {
                }
            }
            if (cellLocation instanceof GsmCellLocation) {
                aVar.a = ((GsmCellLocation) cellLocation).getLac();
                aVar.b = ((GsmCellLocation) cellLocation).getCid();
                aVar.e = 'g';
            } else if (cellLocation instanceof CdmaCellLocation) {
                aVar.e = 'w';
                if (g == null) {
                    try {
                        Class forName = Class.forName("android.telephony.cdma.CdmaCellLocation");
                        g = forName;
                        d = forName.getMethod("getBaseStationId", new Class[0]);
                        e = g.getMethod("getNetworkId", new Class[0]);
                        f = g.getMethod("getSystemId", new Class[0]);
                    } catch (Exception e2) {
                        g = null;
                    }
                }
                if (g != null && g.isInstance(cellLocation)) {
                    try {
                        i = ((Integer) f.invoke(cellLocation, new Object[0])).intValue();
                        if (i < 0) {
                            i = this.c.d;
                        }
                        aVar.d = i;
                        aVar.b = ((Integer) d.invoke(cellLocation, new Object[0])).intValue();
                        aVar.a = ((Integer) e.invoke(cellLocation, new Object[0])).intValue();
                    } catch (Exception e3) {
                    }
                }
            }
            if (aVar.b()) {
                this.c = aVar;
            }
        }
    }

    private a b() {
        if (Integer.valueOf(VERSION.SDK_INT).intValue() < 17) {
            return null;
        }
        try {
            List<CellInfo> allCellInfo = this.b.getAllCellInfo();
            if (allCellInfo == null || allCellInfo.size() <= 0) {
                return null;
            }
            a aVar = null;
            for (CellInfo cellInfo : allCellInfo) {
                try {
                } catch (Exception e) {
                    return aVar;
                } catch (NoSuchMethodError e2) {
                    return null;
                }
                if (cellInfo.isRegistered()) {
                    a a = a(cellInfo);
                    if (a != null) {
                        try {
                            if (!a.b()) {
                                a = null;
                            }
                            return a;
                        } catch (Exception e3) {
                            return a;
                        } catch (NoSuchMethodError e22) {
                            return null;
                        }
                    }
                    aVar = a;
                }
            }
            return aVar;
        } catch (Exception e4) {
            return null;
        } catch (NoSuchMethodError e222) {
            return null;
        }
    }

    private a a(CellInfo cellInfo) {
        int intValue = Integer.valueOf(VERSION.SDK_INT).intValue();
        if (intValue < 17) {
            return null;
        }
        a aVar = new a();
        Object obj = null;
        int i;
        if (cellInfo instanceof CellInfoGsm) {
            CellIdentityGsm cellIdentity = ((CellInfoGsm) cellInfo).getCellIdentity();
            aVar.c = b(cellIdentity.getMcc());
            aVar.d = b(cellIdentity.getMnc());
            aVar.a = b(cellIdentity.getLac());
            aVar.b = b(cellIdentity.getCid());
            aVar.e = 'g';
            i = 1;
        } else if (cellInfo instanceof CellInfoCdma) {
            CellIdentityCdma cellIdentity2 = ((CellInfoCdma) cellInfo).getCellIdentity();
            aVar.d = b(cellIdentity2.getSystemId());
            aVar.a = b(cellIdentity2.getNetworkId());
            aVar.b = b(cellIdentity2.getBasestationId());
            aVar.e = 'w';
            i = 1;
        } else if (cellInfo instanceof CellInfoLte) {
            CellIdentityLte cellIdentity3 = ((CellInfoLte) cellInfo).getCellIdentity();
            aVar.c = b(cellIdentity3.getMcc());
            aVar.d = b(cellIdentity3.getMnc());
            aVar.a = b(cellIdentity3.getTac());
            aVar.b = b(cellIdentity3.getCi());
            aVar.e = 'g';
            i = 1;
        }
        if (intValue >= 18 && r0 == null) {
            try {
                if (cellInfo instanceof CellInfoWcdma) {
                    CellIdentityWcdma cellIdentity4 = ((CellInfoWcdma) cellInfo).getCellIdentity();
                    aVar.c = b(cellIdentity4.getMcc());
                    aVar.d = b(cellIdentity4.getMnc());
                    aVar.a = b(cellIdentity4.getLac());
                    aVar.b = b(cellIdentity4.getCid());
                    aVar.e = 'g';
                }
            } catch (Exception e) {
            }
        }
        return aVar;
    }

    private int b(int i) {
        return i == Integer.MAX_VALUE ? -1 : i;
    }

    private boolean c() {
        String str = null;
        this.k = null;
        this.l = 0;
        WifiInfo connectionInfo = this.h.getConnectionInfo();
        if (connectionInfo == null) {
            return false;
        }
        try {
            String bssid = connectionInfo.getBSSID();
            if (bssid != null) {
                str = bssid.replace(":", com.umeng.a.d);
            }
            if (str.length() != 12) {
                return false;
            }
            this.k = new String(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static String a(String str) {
        int i = 0;
        if (str == null) {
            return null;
        }
        byte[] bytes = str.getBytes();
        byte nextInt = (byte) new Random().nextInt(VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX);
        byte nextInt2 = (byte) new Random().nextInt(VodPlayerActivity.SYSTEM_BRIGHTNESS_MAX);
        byte[] bArr = new byte[(bytes.length + 2)];
        int length = bytes.length;
        int i2 = 0;
        while (i < length) {
            int i3 = i2 + 1;
            bArr[i2] = (byte) (bytes[i] ^ nextInt);
            i++;
            i2 = i3;
        }
        i = i2 + 1;
        bArr[i2] = nextInt;
        bArr[i] = nextInt2;
        return a(bArr);
    }

    private static String a(byte[] bArr) {
        char[] cArr = new char[(((bArr.length + 2) / 3) * 4)];
        int i = 0;
        int i2 = 0;
        while (i2 < bArr.length) {
            int i3;
            Object obj;
            int i4 = (bArr[i2] & 255) << 8;
            if (i2 + 1 < bArr.length) {
                i4 |= bArr[i2 + 1] & 255;
                int i5 = 1;
            } else {
                Object obj2 = null;
            }
            i4 <<= 8;
            if (i2 + 2 < bArr.length) {
                i4 |= bArr[i2 + 2] & 255;
                i3 = 1;
            } else {
                obj = null;
            }
            cArr[i + 3] = n[obj != null ? 63 - (i4 & 63) : 64];
            i3 = i4 >> 6;
            int i6 = i + 2;
            char[] cArr2 = n;
            if (obj2 != null) {
                i4 = 63 - (i3 & 63);
            } else {
                i4 = 64;
            }
            cArr[i6] = cArr2[i4];
            i4 = i3 >> 6;
            cArr[i + 1] = n[63 - (i4 & 63)];
            cArr[i + 0] = n[63 - ((i4 >> 6) & 63)];
            i2 += 3;
            i += 4;
        }
        return new String(cArr);
    }
}
