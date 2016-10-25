package com.a.a.c;

import android.support.v4.media.TransportMediator;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicHeaderValueParser;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;

// compiled from: URLEncodedUtils.java
public final class b {
    private static final char[] a;
    private static final BitSet b;
    private static final BitSet c;
    private static final BitSet d;
    private static final BitSet e;
    private static final BitSet f;
    private static final BitSet g;
    private static final BitSet h;

    static {
        int i;
        a = new char[]{'&'};
        b = new BitSet(256);
        c = new BitSet(256);
        d = new BitSet(256);
        e = new BitSet(256);
        f = new BitSet(256);
        g = new BitSet(256);
        h = new BitSet(256);
        for (i = R.styleable.AppCompatTheme_buttonBarNegativeButtonStyle; i <= 122; i++) {
            b.set(i);
        }
        for (i = R.styleable.AppCompatTheme_textAppearanceSearchResultTitle; i <= 90; i++) {
            b.set(i);
        }
        for (i = R.styleable.AppCompatTheme_homeAsUpIndicator; i <= 57; i++) {
            b.set(i);
        }
        b.set(R.styleable.AppCompatTheme_textColorAlertDialogListItem);
        b.set(R.styleable.AppCompatTheme_actionDropDownStyle);
        b.set(R.styleable.AppCompatTheme_dropdownListPreferredItemHeight);
        b.set(R.styleable.AppCompatTheme_dialogTheme);
        h.or(b);
        b.set(R.styleable.AppCompatTheme_actionModeCopyDrawable);
        b.set(TransportMediator.KEYCODE_MEDIA_PLAY);
        b.set(R.styleable.AppCompatTheme_actionModePopupWindowStyle);
        b.set(R.styleable.AppCompatTheme_textAppearanceLargePopupMenu);
        b.set(R.styleable.AppCompatTheme_textAppearanceSmallPopupMenu);
        c.set(R.styleable.AppCompatTheme_listDividerAlertDialog);
        c.set(R.styleable.AppCompatTheme_toolbarNavigationButtonStyle);
        c.set(R.styleable.AppCompatTheme_toolbarStyle);
        c.set(R.styleable.AppCompatTheme_actionModeShareDrawable);
        c.set(XZBDevice.FailInServer);
        c.set(R.styleable.AppCompatTheme_dialogPreferredPadding);
        c.set(R.styleable.AppCompatTheme_popupWindowStyle);
        d.or(b);
        d.or(c);
        e.or(b);
        e.set(R.styleable.AppCompatTheme_spinnerDropDownItemStyle);
        e.set(R.styleable.AppCompatTheme_toolbarNavigationButtonStyle);
        e.set(R.styleable.AppCompatTheme_toolbarStyle);
        e.set(R.styleable.AppCompatTheme_imageButtonStyle);
        e.set(XZBDevice.FailInServer);
        e.set(R.styleable.AppCompatTheme_popupWindowStyle);
        e.set(R.styleable.AppCompatTheme_dialogPreferredPadding);
        e.set(R.styleable.AppCompatTheme_actionModeShareDrawable);
        e.set(R.styleable.AppCompatTheme_listDividerAlertDialog);
        g.set(R.styleable.AppCompatTheme_toolbarNavigationButtonStyle);
        g.set(R.styleable.AppCompatTheme_spinnerDropDownItemStyle);
        g.set(R.styleable.AppCompatTheme_editTextBackground);
        g.set(R.styleable.AppCompatTheme_toolbarStyle);
        g.set(R.styleable.AppCompatTheme_imageButtonStyle);
        g.set(XZBDevice.FailInServer);
        g.set(R.styleable.AppCompatTheme_popupWindowStyle);
        g.set(R.styleable.AppCompatTheme_dialogPreferredPadding);
        g.set(R.styleable.AppCompatTheme_actionModeShareDrawable);
        g.set(R.styleable.AppCompatTheme_listDividerAlertDialog);
        g.set(R.styleable.AppCompatTheme_alertDialogStyle);
        g.set(R.styleable.AppCompatTheme_alertDialogCenterButtons);
        f.or(g);
        f.or(b);
    }

    public static List<NameValuePair> a(String str) {
        if (str == null) {
            return Collections.emptyList();
        }
        BasicHeaderValueParser basicHeaderValueParser = BasicHeaderValueParser.DEFAULT;
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(str.length());
        charArrayBuffer.append(str);
        ParserCursor parserCursor = new ParserCursor(0, charArrayBuffer.length());
        List<NameValuePair> arrayList = new ArrayList();
        while (!parserCursor.atEnd()) {
            NameValuePair parseNameValuePair = basicHeaderValueParser.parseNameValuePair(charArrayBuffer, parserCursor, a);
            if (parseNameValuePair.getName().length() > 0) {
                arrayList.add(new BasicNameValuePair(parseNameValuePair.getName(), parseNameValuePair.getValue()));
            }
        }
        return arrayList;
    }
}
