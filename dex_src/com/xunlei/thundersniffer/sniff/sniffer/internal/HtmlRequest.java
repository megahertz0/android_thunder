package com.xunlei.thundersniffer.sniff.sniffer.internal;

import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.Request.Priority;
import com.android.volley.l;
import com.android.volley.r;
import com.android.volley.r.a;
import com.android.volley.r.b;
import com.android.volley.toolbox.f;
import com.umeng.message.util.HttpRequest;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.android.spdy.SpdyProtocol;

public class HtmlRequest extends Request<String> {
    static Pattern a;
    private final b<String> b;

    public HtmlRequest(int i, String str, b<String> bVar, a aVar) {
        super(i, str, aVar);
        this.b = bVar;
    }

    public HtmlRequest(String str, b<String> bVar, a aVar) {
        this(0, str, bVar, aVar);
    }

    public Map<String, String> getHeaders() throws com.android.volley.a {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(HttpRequest.e, "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        hashMap.put("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6");
        hashMap.put(HttpRequest.v, "Mozilla/5.0 (Linux; Android 4.4; Nexus 7 Build/JSS15Q) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2307.2 Mobile Safari/537.36");
        return hashMap;
    }

    public Priority getPriority() {
        return Priority.HIGH;
    }

    protected void deliverResponse(String str) {
        this.b.onResponse(str);
    }

    protected r<String> parseNetworkResponse(l lVar) {
        String str;
        Object charset;
        Object str2;
        String str3;
        int i = SpdyProtocol.SLIGHTSSL_0_RTT_MODE;
        if (lVar.b.length > 3 && lVar.b[0] == -17 && lVar.b[1] == -69 && lVar.b[2] == -65) {
            try {
                str = new String(lVar.b, 3, lVar.b.length - 3, "utf-8");
            } catch (Exception e) {
            }
            if (str == null) {
                try {
                    byte[] bArr = lVar.b;
                    if (lVar.b.length < 4096) {
                        i = lVar.b.length;
                    }
                    charset = getCharset(new String(bArr, 0, i, CharsetConvert.ISO_8859_1));
                } catch (UnsupportedEncodingException e2) {
                    str2 = new String(lVar.b);
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                if (TextUtils.isEmpty(charset)) {
                    str2 = new String(lVar.b, charset);
                    return r.a(str2, f.a(lVar));
                } else if (f.a(lVar.c).compareToIgnoreCase(CharsetConvert.ISO_8859_1) != 0) {
                    try {
                        str2 = new String(lVar.b, CharsetConvert.GBK);
                    } catch (UnsupportedEncodingException e4) {
                        str2 = new String(lVar.b, f.a(lVar.c));
                    } catch (Exception e32) {
                        e32.printStackTrace();
                    }
                    return r.a(str2, f.a(lVar));
                } else {
                    str2 = new String(lVar.b, f.a(lVar.c));
                    return r.a(str2, f.a(lVar));
                }
            }
            str3 = str;
            return r.a(str2, f.a(lVar));
        }
        str = null;
        if (str == null) {
            byte[] bArr2 = lVar.b;
            if (lVar.b.length < 4096) {
                i = lVar.b.length;
            }
            charset = getCharset(new String(bArr2, 0, i, CharsetConvert.ISO_8859_1));
            if (TextUtils.isEmpty(charset)) {
                str2 = new String(lVar.b, charset);
                return r.a(str2, f.a(lVar));
            } else if (f.a(lVar.c).compareToIgnoreCase(CharsetConvert.ISO_8859_1) != 0) {
                str2 = new String(lVar.b, f.a(lVar.c));
                return r.a(str2, f.a(lVar));
            } else {
                str2 = new String(lVar.b, CharsetConvert.GBK);
                return r.a(str2, f.a(lVar));
            }
        }
        str3 = str;
        return r.a(str2, f.a(lVar));
    }

    static {
        a = null;
    }

    public String getCharset(String str) {
        String str2 = BuildConfig.VERSION_NAME;
        if (a == null) {
            a = Pattern.compile("charset=[\"]?([^\"]+)\"");
        }
        Matcher matcher = a.matcher(str);
        return matcher.find() ? matcher.group(1) : str2;
    }
}
