package com.google.gson.internal.bind.util;

import com.alipay.sdk.util.h;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import org.android.spdy.SpdyAgent;

public class ISO8601Utils {
    private static final TimeZone TIMEZONE_UTC;
    private static final String UTC_ID = "UTC";

    static {
        TIMEZONE_UTC = TimeZone.getTimeZone(UTC_ID);
    }

    public static String format(Date date) {
        return format(date, false, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z) {
        return format(date, z, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z, TimeZone timeZone) {
        Calendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder stringBuilder = new StringBuilder((timeZone.getRawOffset() == 0 ? 1 : R.styleable.Toolbar_contentInsetEnd) + ((z ? 4 : 0) + 19));
        padInt(stringBuilder, gregorianCalendar.get(1), XZBDevice.DOWNLOAD_LIST_ALL);
        stringBuilder.append('-');
        padInt(stringBuilder, gregorianCalendar.get(XZBDevice.DOWNLOAD_LIST_RECYCLE) + 1, XZBDevice.DOWNLOAD_LIST_RECYCLE);
        stringBuilder.append('-');
        padInt(stringBuilder, gregorianCalendar.get(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED), XZBDevice.DOWNLOAD_LIST_RECYCLE);
        stringBuilder.append('T');
        padInt(stringBuilder, gregorianCalendar.get(XZBDevice.Success), XZBDevice.DOWNLOAD_LIST_RECYCLE);
        stringBuilder.append(':');
        padInt(stringBuilder, gregorianCalendar.get(XZBDevice.Fail), XZBDevice.DOWNLOAD_LIST_RECYCLE);
        stringBuilder.append(':');
        padInt(stringBuilder, gregorianCalendar.get(XZBDevice.Upload), XZBDevice.DOWNLOAD_LIST_RECYCLE);
        if (z) {
            stringBuilder.append('.');
            padInt(stringBuilder, gregorianCalendar.get(XZBDevice.Predownload), XZBDevice.DOWNLOAD_LIST_FAILED);
        }
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int abs = Math.abs((offset / 60000) / 60);
            int abs2 = Math.abs((offset / 60000) % 60);
            stringBuilder.append(offset < 0 ? '-' : '+');
            padInt(stringBuilder, abs, XZBDevice.DOWNLOAD_LIST_RECYCLE);
            stringBuilder.append(':');
            padInt(stringBuilder, abs2, XZBDevice.DOWNLOAD_LIST_RECYCLE);
        } else {
            stringBuilder.append('Z');
        }
        return stringBuilder.toString();
    }

    public static Date parse(String str, ParsePosition parsePosition) throws ParseException {
        String substring;
        Throwable th;
        String message;
        try {
            int index = parsePosition.getIndex();
            int i = index + 4;
            int parseInt = parseInt(str, index, i);
            if (checkOffset(str, i, '-')) {
                index = i + 1;
            } else {
                index = i;
            }
            i = index + 2;
            int parseInt2 = parseInt(str, index, i);
            if (checkOffset(str, i, '-')) {
                index = i + 1;
            } else {
                index = i;
            }
            i = index + 2;
            int parseInt3 = parseInt(str, index, i);
            boolean checkOffset = checkOffset(str, i, 'T');
            if (checkOffset || str.length() > i) {
                int parseInt4;
                int i2;
                int i3;
                if (checkOffset) {
                    index = i + 1;
                    i = index + 2;
                    parseInt4 = parseInt(str, index, i);
                    if (checkOffset(str, i, ':')) {
                        index = i + 1;
                    } else {
                        index = i;
                    }
                    i = index + 2;
                    index = parseInt(str, index, i);
                    if (checkOffset(str, i, ':')) {
                        i++;
                    }
                    if (str.length() > i) {
                        char charAt = str.charAt(i);
                        if (!(charAt == 'Z' || charAt == '+' || charAt == '-')) {
                            i2 = i + 2;
                            i = parseInt(str, i, i2);
                            if (i > 59 && i < 63) {
                                i = 59;
                            }
                            if (checkOffset(str, i2, '.')) {
                                i3 = i2 + 1;
                                i2 = indexOfNonDigit(str, i3 + 1);
                                int min = Math.min(i2, i3 + 3);
                                int parseInt5 = parseInt(str, i3, min);
                                switch (min - i3) {
                                    case SpdyAgent.ACCS_ONLINE_SERVER:
                                        parseInt5 *= 100;
                                        break;
                                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                                        parseInt5 *= 10;
                                        break;
                                }
                                i3 = parseInt4;
                                parseInt4 = i;
                                i = i2;
                                i2 = index;
                                index = parseInt5;
                            } else {
                                i3 = parseInt4;
                                parseInt4 = i;
                                i = i2;
                                i2 = index;
                                index = 0;
                            }
                        }
                    }
                    i2 = index;
                    i3 = parseInt4;
                    index = 0;
                    parseInt4 = 0;
                } else {
                    index = 0;
                    parseInt4 = 0;
                    i2 = 0;
                    i3 = 0;
                }
                if (str.length() <= i) {
                    throw new IllegalArgumentException("No time zone indicator");
                }
                TimeZone timeZone;
                char charAt2 = str.charAt(i);
                if (charAt2 == 'Z') {
                    timeZone = TIMEZONE_UTC;
                    i++;
                } else if (charAt2 == '+' || charAt2 == '-') {
                    substring = str.substring(i);
                    i += substring.length();
                    if ("+0000".equals(substring) || "+00:00".equals(substring)) {
                        timeZone = TIMEZONE_UTC;
                    } else {
                        String toString = new StringBuilder("GMT").append(substring).toString();
                        timeZone = TimeZone.getTimeZone(toString);
                        String id = timeZone.getID();
                        if (!(id.equals(toString) || id.replace(":", a.d).equals(toString))) {
                            throw new IndexOutOfBoundsException(new StringBuilder("Mismatching time zone indicator: ").append(toString).append(" given, resolves to ").append(timeZone.getID()).toString());
                        }
                    }
                } else {
                    throw new IndexOutOfBoundsException(new StringBuilder("Invalid time zone indicator '").append(charAt2).append("'").toString());
                }
                Calendar gregorianCalendar = new GregorianCalendar(timeZone);
                gregorianCalendar.setLenient(false);
                gregorianCalendar.set(1, parseInt);
                gregorianCalendar.set(XZBDevice.DOWNLOAD_LIST_RECYCLE, parseInt2 - 1);
                gregorianCalendar.set(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, parseInt3);
                gregorianCalendar.set(XZBDevice.Success, i3);
                gregorianCalendar.set(XZBDevice.Fail, i2);
                gregorianCalendar.set(XZBDevice.Upload, parseInt4);
                gregorianCalendar.set(XZBDevice.Predownload, index);
                parsePosition.setIndex(i);
                return gregorianCalendar.getTime();
            }
            Calendar gregorianCalendar2 = new GregorianCalendar(parseInt, parseInt2 - 1, parseInt3);
            parsePosition.setIndex(i);
            return gregorianCalendar2.getTime();
        } catch (Throwable e) {
            th = e;
            if (str == null) {
                substring = null;
            } else {
                substring = new StringBuilder(h.f).append(str).append("'").toString();
            }
            message = th.getMessage();
            if (message == null || message.isEmpty()) {
                message = new StringBuilder(SocializeConstants.OP_OPEN_PAREN).append(th.getClass().getName()).append(SocializeConstants.OP_CLOSE_PAREN).toString();
            }
            ParseException parseException = new ParseException(new StringBuilder("Failed to parse date [").append(substring).append("]: ").append(message).toString(), parsePosition.getIndex());
            parseException.initCause(th);
            throw parseException;
        } catch (Throwable e2) {
            th = e2;
            if (str == null) {
                substring = new StringBuilder(h.f).append(str).append("'").toString();
            } else {
                substring = null;
            }
            message = th.getMessage();
            message = new StringBuilder(SocializeConstants.OP_OPEN_PAREN).append(th.getClass().getName()).append(SocializeConstants.OP_CLOSE_PAREN).toString();
            ParseException parseException2 = new ParseException(new StringBuilder("Failed to parse date [").append(substring).append("]: ").append(message).toString(), parsePosition.getIndex());
            parseException2.initCause(th);
            throw parseException2;
        } catch (Throwable e22) {
            th = e22;
            if (str == null) {
                substring = null;
            } else {
                substring = new StringBuilder(h.f).append(str).append("'").toString();
            }
            message = th.getMessage();
            message = new StringBuilder(SocializeConstants.OP_OPEN_PAREN).append(th.getClass().getName()).append(SocializeConstants.OP_CLOSE_PAREN).toString();
            ParseException parseException22 = new ParseException(new StringBuilder("Failed to parse date [").append(substring).append("]: ").append(message).toString(), parsePosition.getIndex());
            parseException22.initCause(th);
            throw parseException22;
        }
    }

    private static boolean checkOffset(String str, int i, char c) {
        return i < str.length() && str.charAt(i) == c;
    }

    private static int parseInt(String str, int i, int i2) throws NumberFormatException {
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        int i3;
        int i4 = 0;
        if (i < i2) {
            i3 = i + 1;
            i4 = Character.digit(str.charAt(i), XZBDevice.Stop);
            if (i4 < 0) {
                throw new NumberFormatException(new StringBuilder("Invalid number: ").append(str.substring(i, i2)).toString());
            }
            i4 = -i4;
        } else {
            i3 = i;
        }
        while (i3 < i2) {
            int i5 = i3 + 1;
            i3 = Character.digit(str.charAt(i3), XZBDevice.Stop);
            if (i3 < 0) {
                throw new NumberFormatException(new StringBuilder("Invalid number: ").append(str.substring(i, i2)).toString());
            }
            i4 = (i4 * 10) - i3;
            i3 = i5;
        }
        return -i4;
    }

    private static void padInt(StringBuilder stringBuilder, int i, int i2) {
        String toString = Integer.toString(i);
        for (int length = i2 - toString.length(); length > 0; length--) {
            stringBuilder.append('0');
        }
        stringBuilder.append(toString);
    }

    private static int indexOfNonDigit(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt < '0' || charAt > '9') {
                return i;
            }
            i++;
        }
        return str.length();
    }
}
