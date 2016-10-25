package com.google.zxing.a;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.a.b.a;
import com.google.zxing.common.d;
import com.google.zxing.e;
import com.google.zxing.j;
import com.google.zxing.l;
import com.google.zxing.n;
import com.google.zxing.o;
import java.util.List;
import java.util.Map;

// compiled from: AztecReader.java
public final class b implements l {
    public final n a(com.google.zxing.b bVar, Map<DecodeHintType, ?> map) throws j, e {
        o[] oVarArr;
        d a;
        j e;
        j jVar;
        n nVar;
        List list;
        String str;
        e eVar;
        Object obj = null;
        a aVar = new a(bVar.a());
        try {
            a a2 = aVar.a(false);
            oVarArr = a2.e;
            try {
                a = new com.google.zxing.a.a.a().a(a2);
                Object obj2 = null;
            } catch (j e2) {
                e = e2;
                jVar = e;
                a = null;
                if (a == null) {
                    a2 = aVar.a(true);
                    oVarArr = a2.e;
                    a = new com.google.zxing.a.a.a().a(a2);
                }
                if (map != null) {
                    map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
                }
                nVar = new n(a.b, a.a, oVarArr, BarcodeFormat.AZTEC);
                list = a.c;
                if (list != null) {
                    nVar.a(ResultMetadataType.BYTE_SEGMENTS, list);
                }
                str = a.d;
                if (str != null) {
                    nVar.a(ResultMetadataType.ERROR_CORRECTION_LEVEL, str);
                }
                return nVar;
            } catch (e e3) {
                e e4 = e3;
                obj2 = null;
                e eVar2 = e4;
                a = null;
                eVar = eVar2;
                if (a == null) {
                    a2 = aVar.a(true);
                    oVarArr = a2.e;
                    a = new com.google.zxing.a.a.a().a(a2);
                }
                if (map != null) {
                    map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
                }
                nVar = new n(a.b, a.a, oVarArr, BarcodeFormat.AZTEC);
                list = a.c;
                if (list != null) {
                    nVar.a(ResultMetadataType.BYTE_SEGMENTS, list);
                }
                str = a.d;
                if (str != null) {
                    nVar.a(ResultMetadataType.ERROR_CORRECTION_LEVEL, str);
                }
                return nVar;
            }
        } catch (j e5) {
            e = e5;
            oVarArr = null;
            jVar = e;
            a = null;
            if (a == null) {
                a2 = aVar.a(true);
                oVarArr = a2.e;
                a = new com.google.zxing.a.a.a().a(a2);
            }
            if (map != null) {
                map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
            }
            nVar = new n(a.b, a.a, oVarArr, BarcodeFormat.AZTEC);
            list = a.c;
            if (list != null) {
                nVar.a(ResultMetadataType.BYTE_SEGMENTS, list);
            }
            str = a.d;
            if (str != null) {
                nVar.a(ResultMetadataType.ERROR_CORRECTION_LEVEL, str);
            }
            return nVar;
        } catch (e e6) {
            e4 = e6;
            oVarArr = null;
            obj2 = null;
            e eVar22 = e4;
            a = null;
            eVar = eVar22;
            if (a == null) {
                a2 = aVar.a(true);
                oVarArr = a2.e;
                a = new com.google.zxing.a.a.a().a(a2);
            }
            if (map != null) {
                map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
            }
            nVar = new n(a.b, a.a, oVarArr, BarcodeFormat.AZTEC);
            list = a.c;
            if (list != null) {
                nVar.a(ResultMetadataType.BYTE_SEGMENTS, list);
            }
            str = a.d;
            if (str != null) {
                nVar.a(ResultMetadataType.ERROR_CORRECTION_LEVEL, str);
            }
            return nVar;
        }
        if (a == null) {
            try {
                a2 = aVar.a(true);
                oVarArr = a2.e;
                a = new com.google.zxing.a.a.a().a(a2);
            } catch (j e7) {
                e = e7;
                if (jVar != null) {
                    throw jVar;
                } else if (eVar == null) {
                    throw eVar;
                } else {
                    throw e;
                }
            } catch (e e8) {
                e = e8;
                if (jVar != null) {
                    throw jVar;
                } else if (eVar == null) {
                    throw e;
                } else {
                    throw eVar;
                }
            }
        }
        if (map != null) {
            map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        }
        nVar = new n(a.b, a.a, oVarArr, BarcodeFormat.AZTEC);
        list = a.c;
        if (list != null) {
            nVar.a(ResultMetadataType.BYTE_SEGMENTS, list);
        }
        str = a.d;
        if (str != null) {
            nVar.a(ResultMetadataType.ERROR_CORRECTION_LEVEL, str);
        }
        return nVar;
    }

    public final void a() {
    }
}
