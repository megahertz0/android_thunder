package com.xunlei.XLStat.g;

import android.content.Context;
import com.xunlei.XLStat.XLStatLog.XLStatLog;
import com.xunlei.XLStat.g.c.b;
import com.xunlei.XLStat.g.c.c;
import com.xunlei.XLStat.g.c.d;
import java.io.IOException;
import java.util.HashMap;

public class a {
    private static String a;

    static {
        a = "AnalysisXML";
    }

    public static boolean a(String str, HashMap<String, b> hashMap, HashMap<Integer, Integer> hashMap2, HashMap<String, Integer> hashMap3, com.xunlei.XLStat.g.c.a aVar, d dVar, c cVar, Context context) {
        XLStatLog.d(a, "parshXML", new StringBuilder("filePath: ").append(str).toString());
        try {
            new b().a(str, hashMap, hashMap2, hashMap3, aVar, dVar, cVar, context);
            return true;
        } catch (IOException e) {
            XLStatLog.d(a, "parshXML", "parse xml error ... ");
            e.printStackTrace();
            return false;
        }
    }
}
