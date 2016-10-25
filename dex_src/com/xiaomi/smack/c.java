package com.xiaomi.smack;

import com.xunlei.xllib.R;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class c {
    public static int a(Throwable th) {
        Throwable a = (!(th instanceof p) || ((p) th).a() == null) ? th : ((p) th).a();
        String message = a.getMessage();
        if (a.getCause() != null) {
            message = a.getCause().getMessage();
        }
        return a instanceof SocketTimeoutException ? 105 : a instanceof SocketException ? message.indexOf("Network is unreachable") != -1 ? R.styleable.AppCompatTheme_checkboxStyle : message.indexOf("Connection refused") != -1 ? R.styleable.AppCompatTheme_checkedTextViewStyle : message.indexOf("Connection timed out") != -1 ? 105 : message.endsWith("EACCES (Permission denied)") ? R.styleable.AppCompatTheme_buttonStyleSmall : message.indexOf("Connection reset by peer") != -1 ? R.styleable.AppCompatTheme_seekBarStyle : message.indexOf("Broken pipe") != -1 ? R.styleable.AppCompatTheme_spinnerStyle : message.indexOf("No route to host") != -1 ? R.styleable.AppCompatTheme_editTextStyle : message.endsWith("EINVAL (Invalid argument)") ? R.styleable.AppCompatTheme_ratingBarStyle : 199 : a instanceof UnknownHostException ? R.styleable.AppCompatTheme_ratingBarStyleIndicator : th instanceof p ? 399 : 0;
    }
}
