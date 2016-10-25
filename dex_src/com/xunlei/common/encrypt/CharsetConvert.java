package com.xunlei.common.encrypt;

import com.xunlei.xllib.R;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.Character.UnicodeBlock;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class CharsetConvert {
    public static final String GBK = "GBK";
    public static final String ISO_8859_1 = "ISO-8859-1";
    public static final String US_ASCII = "US-ASCII";
    public static final String UTF_16 = "UTF-16";
    public static final String UTF_16BE = "UTF-16BE";
    public static final String UTF_16LE = "UTF-16LE";
    public static final String UTF_8 = "UTF-8";

    public static String inputStreamToGBK(InputStream inputStream) throws IOException {
        return inputStreamconvertToCharset(inputStream, GBK);
    }

    public static String inputStreamToGBK(byte[] bArr) throws IOException {
        return inputStreamToGBK(new ByteArrayInputStream(bArr));
    }

    public static String inputStreamToUTF8(InputStream inputStream) throws IOException {
        return inputStreamconvertToCharset(inputStream, UTF_8);
    }

    public static String inputStreamToUTF8(byte[] bArr) throws IOException {
        return inputStreamToUTF8(new ByteArrayInputStream(bArr));
    }

    public static String StringToGBK(String str) throws IOException {
        return strconvertToCharset(str, GBK);
    }

    public static String utf8ToUnicode(String str) {
        char[] toCharArray = str.toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            UnicodeBlock of = UnicodeBlock.of(toCharArray[i]);
            if (of == UnicodeBlock.BASIC_LATIN) {
                stringBuffer.append(toCharArray[i]);
            } else if (of == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
                stringBuffer.append((char) (toCharArray[i] - 65248));
            } else {
                stringBuffer.append(new StringBuilder("\\u").append(Integer.toHexString((short) toCharArray[i])).toString().toLowerCase());
            }
        }
        return stringBuffer.toString();
    }

    public static String unicodeToUtf8(String str) {
        int length = str.length();
        StringBuffer stringBuffer = new StringBuffer(length);
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            char charAt = str.charAt(i);
            if (charAt == '\\') {
                int i3 = i2 + 1;
                char charAt2 = str.charAt(i2);
                if (charAt2 == 'u') {
                    i2 = 0;
                    i = i3;
                    i3 = 0;
                    while (i3 < 4) {
                        int i4 = i + 1;
                        char charAt3 = str.charAt(i);
                        switch (charAt3) {
                            case R.styleable.AppCompatTheme_homeAsUpIndicator:
                            case R.styleable.AppCompatTheme_actionButtonStyle:
                            case R.styleable.AppCompatTheme_buttonBarStyle:
                            case R.styleable.AppCompatTheme_buttonBarButtonStyle:
                            case R.styleable.AppCompatTheme_selectableItemBackground:
                            case R.styleable.AppCompatTheme_selectableItemBackgroundBorderless:
                            case R.styleable.AppCompatTheme_borderlessButtonStyle:
                            case R.styleable.AppCompatTheme_dividerVertical:
                            case R.styleable.AppCompatTheme_dividerHorizontal:
                            case R.styleable.AppCompatTheme_activityChooserViewStyle:
                                i2 = ((i2 << 4) + charAt3) - 48;
                                break;
                            case R.styleable.AppCompatTheme_textAppearanceSearchResultTitle:
                            case R.styleable.AppCompatTheme_textAppearanceSearchResultSubtitle:
                            case R.styleable.AppCompatTheme_textColorSearchUrl:
                            case R.styleable.AppCompatTheme_searchViewStyle:
                            case R.styleable.AppCompatTheme_listPreferredItemHeight:
                            case R.styleable.AppCompatTheme_listPreferredItemHeightSmall:
                                i2 = (((i2 << 4) + 10) + charAt3) - 65;
                                break;
                            case R.styleable.AppCompatTheme_buttonBarNegativeButtonStyle:
                            case R.styleable.AppCompatTheme_buttonBarNeutralButtonStyle:
                            case R.styleable.AppCompatTheme_autoCompleteTextViewStyle:
                            case R.styleable.AppCompatTheme_buttonStyle:
                            case R.styleable.AppCompatTheme_buttonStyleSmall:
                            case R.styleable.AppCompatTheme_checkboxStyle:
                                i2 = (((i2 << 4) + 10) + charAt3) - 97;
                                break;
                            default:
                                throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
                        }
                        i3++;
                        i = i4;
                    }
                    stringBuffer.append((char) i2);
                } else {
                    if (charAt2 == 't') {
                        charAt2 = '\t';
                    } else if (charAt2 == 'r') {
                        charAt2 = '\r';
                    } else if (charAt2 == 'n') {
                        charAt2 = '\n';
                    } else if (charAt2 == 'f') {
                        charAt2 = '\f';
                    }
                    stringBuffer.append(charAt2);
                    i = i3;
                }
            } else {
                stringBuffer.append(charAt);
                i = i2;
            }
        }
        return stringBuffer.toString();
    }

    private static String strconvertToCharset(String str, String str2) throws UnsupportedEncodingException {
        return new String(str.getBytes(), str2);
    }

    private static String inputStreamconvertToCharset(InputStream inputStream, String str) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, str);
        StringBuffer stringBuffer = new StringBuffer();
        char[] cArr = new char[64];
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (read != -1) {
                stringBuffer.append(cArr, 0, read);
            } else {
                inputStreamReader.close();
                return stringBuffer.toString();
            }
        }
    }

    public static String correctGBUrlCodeString(String str) {
        if (str == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        String[] split = str.split("%");
        stringBuffer.append(split[0]);
        int i = 1;
        int i2 = 0;
        while (i < split.length) {
            stringBuffer.append("%");
            char[] toCharArray = split[i].toCharArray();
            if (toCharArray.length <= 0) {
                return null;
            }
            if (i2 == 1) {
                stringBuffer.append(toCharArray);
                if (toCharArray[0] == '3') {
                    stringBuffer.append("%");
                    stringBuffer.append(split[i + 1]);
                    stringBuffer.append("%");
                    stringBuffer.append(split[i + 2]);
                    i += 2;
                }
                i2 = 0;
            } else if (toCharArray[0] >= '0' && toCharArray[0] <= '7') {
                stringBuffer.append(toCharArray);
            } else if (toCharArray.length == 2) {
                stringBuffer.append(toCharArray);
                i2 = 1;
            } else {
                byte[] byte_to_hex = HextoChar.byte_to_hex((byte) toCharArray[2]);
                stringBuffer.append(toCharArray, 0, SimpleLog.LOG_LEVEL_DEBUG);
                stringBuffer.append("%");
                stringBuffer.append(new String(byte_to_hex));
                stringBuffer.append(toCharArray, MqttConnectOptions.MQTT_VERSION_3_1, toCharArray.length - 3);
            }
            i++;
        }
        return stringBuffer.toString();
    }

    public static boolean testUTF8(byte[] bArr) {
        int i;
        for (int i2 = 0; i2 < bArr.length; i2 += i) {
            i = bArr[i2] & 255;
            if ((i >>> 7) == 0) {
                i = 1;
            } else if ((i >>> 6) == 2) {
                return false;
            } else {
                if ((i >>> 5) == 6) {
                    i = 2;
                } else if ((i >>> 4) == 14) {
                    i = MqttConnectOptions.MQTT_VERSION_3_1;
                } else if ((i >>> 3) == 30) {
                    i = MqttConnectOptions.MQTT_VERSION_3_1_1;
                } else if ((i >>> 2) == 62) {
                    i = SimpleLog.LOG_LEVEL_ERROR;
                } else if ((i >>> 1) != 126) {
                    return false;
                } else {
                    i = 6;
                }
            }
            if ((i2 + i) - 1 >= bArr.length) {
                return false;
            }
            for (int i3 = i2 + 1; i3 < i + i2; i3++) {
                if (((bArr[i3] & 255) >>> 6) != 2) {
                    return false;
                }
            }
        }
        return true;
    }
}
