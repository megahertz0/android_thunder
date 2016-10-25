package com.xunlei.downloadprovider.web.browser;

import android.text.Editable;
import android.text.TextWatcher;
import com.xunlei.downloadprovider.model.o;
import com.xunlei.downloadprovider.web.browser.BrowserTitleBarFragment.a;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.Iterator;
import org.android.spdy.SpdyProtocol;
import org.eclipse.paho.client.mqttv3.MqttTopic;

// compiled from: BrowserTitleBarFragment.java
final class ab implements TextWatcher {
    final /* synthetic */ a a;

    ab(a aVar) {
        this.a = aVar;
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void afterTextChanged(Editable editable) {
        if (this.a.b != null && this.a.a) {
            String[] strArr;
            int length;
            int i;
            String str;
            InputAutoCompleteView inputAutoCompleteView = this.a.b;
            inputAutoCompleteView.e.clear();
            CharSequence toLowerCase = inputAutoCompleteView.h.getText().toString().toLowerCase();
            if (toLowerCase != null) {
                for (String str2 : inputAutoCompleteView.j) {
                    if (toLowerCase.equals(str2)) {
                        CharSequence charSequence = BuildConfig.VERSION_NAME;
                        inputAutoCompleteView.h.setText(charSequence);
                        toLowerCase = charSequence;
                        break;
                    }
                }
            }
            if (toLowerCase.startsWith("w") || toLowerCase.startsWith("h")) {
                strArr = InputAutoCompleteView.a;
                length = strArr.length;
                for (i = 0; i < length; i++) {
                    str = strArr[i];
                    if (str.startsWith(toLowerCase) && !str.equals(toLowerCase)) {
                        inputAutoCompleteView.e.add(new o("-1", str));
                    }
                }
            }
            if (!toLowerCase.startsWith(".")) {
                i = toLowerCase.lastIndexOf("://");
                if (i >= 0) {
                    i += 3;
                } else {
                    i = 0;
                }
                int lastIndexOf = toLowerCase.lastIndexOf(MqttTopic.TOPIC_LEVEL_SEPARATOR);
                if (!toLowerCase.endsWith(".")) {
                    length = toLowerCase.lastIndexOf(".");
                    int length2 = toLowerCase.length();
                    if (length >= 0) {
                        String[] strArr2;
                        if (lastIndexOf >= i) {
                            strArr2 = InputAutoCompleteView.c;
                        } else {
                            strArr2 = InputAutoCompleteView.b;
                        }
                        str = toLowerCase.substring(length + 1, length2);
                        int length3 = strArr2.length;
                        for (lastIndexOf = 0; lastIndexOf < length3; lastIndexOf++) {
                            String str3 = strArr2[lastIndexOf];
                            if (str3.startsWith(str) && !str3.equals(str)) {
                                inputAutoCompleteView.e.add(new o("-1", toLowerCase.substring(0, length + 1) + str3));
                            }
                        }
                    }
                } else if (lastIndexOf >= i) {
                    strArr = InputAutoCompleteView.c;
                    length = strArr.length;
                    for (i = 0; i < length; i++) {
                        inputAutoCompleteView.e.add(new o("-1", toLowerCase + strArr[i]));
                    }
                } else if (!toLowerCase.endsWith("www.")) {
                    strArr = InputAutoCompleteView.b;
                    length = strArr.length;
                    for (i = 0; i < length; i++) {
                        inputAutoCompleteView.e.add(new o("-1", toLowerCase + strArr[i]));
                    }
                }
            }
            Iterator it = inputAutoCompleteView.d.iterator();
            while (it.hasNext()) {
                o oVar = (o) it.next();
                if (oVar.b.contains(toLowerCase) || oVar.a.contains(toLowerCase)) {
                    inputAutoCompleteView.e.add(oVar);
                }
            }
            if (inputAutoCompleteView.e.size() != 0) {
                inputAutoCompleteView.e.add(new o("-1", BuildConfig.VERSION_NAME));
            }
            if (inputAutoCompleteView.e.size() > 0) {
                inputAutoCompleteView.i.setVisibility(0);
                inputAutoCompleteView.setVisibility(0);
            } else {
                inputAutoCompleteView.i.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            }
            inputAutoCompleteView.f.notifyDataSetChanged();
        }
    }
}
