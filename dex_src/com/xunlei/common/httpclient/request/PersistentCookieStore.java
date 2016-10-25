package com.xunlei.common.httpclient.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.android.spdy.SpdyProtocol;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;

public class PersistentCookieStore implements CookieStore {
    private static final String COOKIE_NAME_PREFIX = "cookie_";
    private static final String COOKIE_NAME_STORE = "names";
    private static final String COOKIE_PREFS = "CookiePrefsFile";
    private final SharedPreferences cookiePrefs;
    private final ConcurrentHashMap<String, Cookie> cookies;

    public PersistentCookieStore(Context context) {
        int i = 0;
        this.cookiePrefs = context.getSharedPreferences(COOKIE_PREFS, 0);
        this.cookies = new ConcurrentHashMap();
        String string = this.cookiePrefs.getString(COOKIE_NAME_STORE, null);
        if (string != null) {
            String[] split = TextUtils.split(string, ",");
            int length = split.length;
            while (i < length) {
                String str = split[i];
                String string2 = this.cookiePrefs.getString(new StringBuilder(COOKIE_NAME_PREFIX).append(str).toString(), null);
                if (string2 != null) {
                    Cookie decodeCookie = decodeCookie(string2);
                    if (decodeCookie != null) {
                        this.cookies.put(str, decodeCookie);
                    }
                }
                i++;
            }
            clearExpired(new Date());
        }
    }

    public void addCookie(Cookie cookie) {
        String str = cookie.getName() + cookie.getDomain();
        if (cookie.isExpired(new Date())) {
            this.cookies.remove(str);
        } else {
            this.cookies.put(str, cookie);
        }
        Editor edit = this.cookiePrefs.edit();
        edit.putString(COOKIE_NAME_STORE, TextUtils.join(",", this.cookies.keySet()));
        edit.putString(new StringBuilder(COOKIE_NAME_PREFIX).append(str).toString(), encodeCookie(new d(cookie)));
        edit.commit();
    }

    public void clear() {
        Editor edit = this.cookiePrefs.edit();
        Iterator it = this.cookies.keySet().iterator();
        while (it.hasNext()) {
            edit.remove(new StringBuilder(COOKIE_NAME_PREFIX).append((String) it.next()).toString());
        }
        edit.remove(COOKIE_NAME_STORE);
        edit.commit();
        this.cookies.clear();
    }

    public boolean clearExpired(Date date) {
        Editor edit = this.cookiePrefs.edit();
        boolean z = false;
        for (Entry entry : this.cookies.entrySet()) {
            boolean z2;
            String str = (String) entry.getKey();
            if (((Cookie) entry.getValue()).isExpired(date)) {
                this.cookies.remove(str);
                edit.remove(new StringBuilder(COOKIE_NAME_PREFIX).append(str).toString());
                Object obj = 1;
            } else {
                z2 = z;
            }
            z = z2;
        }
        if (z) {
            edit.putString(COOKIE_NAME_STORE, TextUtils.join(",", this.cookies.keySet()));
        }
        edit.commit();
        return z;
    }

    public List<Cookie> getCookies() {
        return new ArrayList(this.cookies.values());
    }

    protected String encodeCookie(d dVar) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(byteArrayOutputStream).writeObject(dVar);
            return byteArrayToHexString(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected Cookie decodeCookie(String str) {
        try {
            return ((d) new ObjectInputStream(new ByteArrayInputStream(hexStringToByteArray(str))).readObject()).a();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    protected String byteArrayToHexString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length << 1);
        for (byte b : bArr) {
            int i = b & 255;
            if (i < 16) {
                stringBuffer.append('0');
            }
            stringBuffer.append(Integer.toHexString(i));
        }
        return stringBuffer.toString().toUpperCase();
    }

    protected byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), SpdyProtocol.CUSTOM) << 4) + Character.digit(str.charAt(i + 1), SpdyProtocol.CUSTOM));
        }
        return bArr;
    }
}
