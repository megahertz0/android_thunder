package com.google.zxing.a.a;

import com.alipay.sdk.util.h;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.google.zxing.common.b;
import com.google.zxing.common.b.c;
import com.google.zxing.common.d;
import com.google.zxing.e;
import com.sina.weibo.sdk.component.WidgetRequestParam;
import com.tencent.connect.common.Constants;
import com.umeng.message.MsgConstant;
import com.umeng.socialize.common.SocializeConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Arrays;
import org.android.agoo.message.MessageService;
import org.android.spdy.SpdyAgent;

// compiled from: Decoder.java
public final class a {
    private static final String[] a;
    private static final String[] b;
    private static final String[] c;
    private static final String[] d;
    private static final String[] e;
    private com.google.zxing.a.a f;

    // compiled from: Decoder.java
    private enum a {
        ;

        public static int[] a() {
            return (int[]) g.clone();
        }

        static {
            a = 1;
            b = 2;
            c = 3;
            d = 4;
            e = 5;
            f = 6;
            g = new int[]{a, b, c, d, e, f};
        }
    }

    static {
        a = new String[]{"CTRL_PS", " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "CTRL_LL", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
        b = new String[]{"CTRL_PS", " ", "a", "b", "c", "d", "e", "f", IXAdRequestInfo.GPS, IXAdRequestInfo.HEIGHT, "i", "j", "k", "l", "m", IXAdRequestInfo.AD_COUNT, "o", "p", WidgetRequestParam.REQ_PARAM_COMMENT_TOPIC, "r", "s", anet.channel.strategy.dispatch.a.TIMESTAMP, "u", IXAdRequestInfo.V, IXAdRequestInfo.WIDTH, "x", "y", "z", "CTRL_US", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
        c = new String[]{"CTRL_PS", " ", "\u0001", "\u0002", "\u0003", "\u0004", "\u0005", "\u0006", "\u0007", "\b", "\t", "\n", "\u000b", "\f", "\r", "\u001b", "\u001c", "\u001d", "\u001e", "\u001f", "@", "\\", "^", "_", "`", "|", "~", "\u007f", "CTRL_LL", "CTRL_UL", "CTRL_PL", "CTRL_BS"};
        d = new String[]{com.umeng.a.d, "\r", "\r\n", ". ", ", ", ": ", "!", h.f, "#", "$", "%", com.alipay.sdk.sys.a.b, "'", SocializeConstants.OP_OPEN_PAREN, SocializeConstants.OP_CLOSE_PAREN, "*", SocializeConstants.OP_DIVIDER_PLUS, MiPushClient.ACCEPT_TIME_SEPARATOR, SocializeConstants.OP_DIVIDER_MINUS, ".", "/", ":", h.b, "<", "=", ">", "?", "[", "]", "{", h.d, "CTRL_UL"};
        e = new String[]{"CTRL_PS", " ", MessageService.MSG_DB_READY_REPORT, MessageService.MSG_DB_NOTIFY_REACHED, MessageService.MSG_DB_NOTIFY_CLICK, MessageService.MSG_DB_NOTIFY_DISMISS, MessageService.MSG_ACCS_READY_REPORT, Constants.VIA_SHARE_TYPE_TEXT, Constants.VIA_SHARE_TYPE_INFO, MsgConstant.MESSAGE_NOTIFY_ARRIVAL, MessageService.MSG_ACCS_NOTIFY_CLICK, MessageService.MSG_ACCS_NOTIFY_DISMISS, MiPushClient.ACCEPT_TIME_SEPARATOR, ".", "CTRL_UL", "CTRL_US"};
    }

    public final d a(com.google.zxing.a.a aVar) throws e {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        this.f = aVar;
        b bVar = aVar.d;
        boolean z = this.f.a;
        int i7 = this.f.c;
        if (z) {
            i = (i7 * 4) + 11;
        } else {
            i = (i7 * 4) + 14;
        }
        int[] iArr = new int[i];
        boolean[] zArr = new boolean[(((z ? R.styleable.AppCompatTheme_colorButtonNormal : 112) + (i7 * 16)) * i7)];
        if (z) {
            for (i2 = 0; i2 < iArr.length; i2++) {
                iArr[i2] = i2;
            }
        } else {
            i3 = i / 2;
            i4 = ((i + 1) + ((((i / 2) - 1) / 15) * 2)) / 2;
            for (i2 = 0; i2 < i3; i2++) {
                i5 = (i2 / 15) + i2;
                iArr[(i3 - i2) - 1] = (i4 - i5) - 1;
                iArr[i3 + i2] = (i5 + i4) + 1;
            }
        }
        i5 = 0;
        for (i6 = 0; i6 < i7; i6++) {
            i2 = z ? ((i7 - i6) * 4) + 9 : ((i7 - i6) * 4) + 12;
            int i8 = i6 * 2;
            int i9 = (i - 1) - i8;
            for (i4 = 0; i4 < i2; i4++) {
                int i10 = i4 * 2;
                for (i3 = 0; i3 < 2; i3++) {
                    zArr[(i5 + i10) + i3] = bVar.a(iArr[i8 + i3], iArr[i8 + i4]);
                    zArr[(((i2 * 2) + i5) + i10) + i3] = bVar.a(iArr[i8 + i4], iArr[i9 - i3]);
                    zArr[(((i2 * 4) + i5) + i10) + i3] = bVar.a(iArr[i9 - i3], iArr[i9 - i4]);
                    zArr[(((i2 * 6) + i5) + i10) + i3] = bVar.a(iArr[i9 - i4], iArr[i8 + i3]);
                }
            }
            i5 = (i2 * 8) + i5;
        }
        boolean[] a = a(zArr);
        i3 = a.length;
        i2 = a.a;
        i4 = a.a;
        StringBuilder stringBuilder = new StringBuilder(20);
        i6 = i4;
        i4 = i2;
        i2 = 0;
        while (i2 < i3) {
            if (i6 == a.f) {
                if (i3 - i2 >= 5) {
                    i = a(a, i2, XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
                    i2 += 5;
                    if (i == 0) {
                        if (i3 - i2 >= 11) {
                            i = a(a, i2, XZBDevice.Success) + 31;
                            i2 += 11;
                        }
                    }
                    i5 = 0;
                    while (i5 < i) {
                        if (i3 - i2 < 8) {
                            i = i3;
                            i2 = i;
                            i6 = i4;
                        } else {
                            stringBuilder.append((char) a(a, i2, XZBDevice.Wait));
                            i5++;
                            i2 += 8;
                        }
                    }
                    i = i2;
                    i2 = i;
                    i6 = i4;
                }
                return new d(null, stringBuilder.toString(), null, null);
            }
            i = i6 == a.d ? XZBDevice.DOWNLOAD_LIST_ALL : XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
            if (i3 - i2 < i) {
                return new d(null, stringBuilder.toString(), null, null);
            }
            String str;
            i7 = a(a, i2, i);
            i5 = i2 + i;
            switch (b.a[i6 - 1]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    str = a[i7];
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    str = b[i7];
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    str = c[i7];
                    break;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    str = d[i7];
                    break;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    str = e[i7];
                    break;
                default:
                    throw new IllegalStateException("Bad table");
            }
            if (str.startsWith("CTRL_")) {
                switch (str.charAt(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED)) {
                    case R.styleable.AppCompatTheme_textAppearanceSearchResultSubtitle:
                        i = a.f;
                        break;
                    case R.styleable.AppCompatTheme_searchViewStyle:
                        i = a.d;
                        break;
                    case R.styleable.AppCompatTheme_textAppearanceListItem:
                        i = a.b;
                        break;
                    case R.styleable.AppCompatTheme_textAppearanceListItemSmall:
                        i = a.c;
                        break;
                    case R.styleable.AppCompatTheme_panelMenuListTheme:
                        i = a.e;
                        break;
                    default:
                        i = a.a;
                        break;
                }
                if (str.charAt(R.styleable.Toolbar_contentInsetEnd) == 'L') {
                    i2 = i5;
                    i6 = i;
                    i4 = i;
                }
            } else {
                stringBuilder.append(str);
                i = i4;
            }
            i2 = i5;
            i6 = i;
        }
        return new d(null, stringBuilder.toString(), null, null);
    }

    private boolean[] a(boolean[] zArr) throws e {
        com.google.zxing.common.b.a aVar;
        int i = XZBDevice.Wait;
        if (this.f.c <= 2) {
            i = R.styleable.Toolbar_contentInsetEnd;
            aVar = com.google.zxing.common.b.a.c;
        } else if (this.f.c <= 8) {
            aVar = com.google.zxing.common.b.a.g;
        } else if (this.f.c <= 22) {
            i = XZBDevice.Stop;
            aVar = com.google.zxing.common.b.a.b;
        } else {
            i = XZBDevice.Fail;
            aVar = com.google.zxing.common.b.a.a;
        }
        int i2 = this.f.b;
        int length = zArr.length / i;
        if (length < i2) {
            throw e.a();
        }
        int i3 = length - i2;
        int[] iArr = new int[length];
        int length2 = zArr.length % i;
        int i4 = 0;
        while (i4 < length) {
            iArr[i4] = a(zArr, length2, i);
            i4++;
            length2 += i;
        }
        try {
            new c(aVar).a(iArr, i3);
            int i5 = (1 << i) - 1;
            i4 = 0;
            int i6 = 0;
            while (i4 < i2) {
                length2 = iArr[i4];
                if (length2 != 0 && length2 != i5) {
                    if (length2 == 1 || length2 == i5 - 1) {
                        i6++;
                    }
                    i4++;
                }
                throw e.a();
            }
            boolean[] zArr2 = new boolean[((i2 * i) - i6)];
            i4 = 0;
            i3 = 0;
            while (i3 < i2) {
                int i7 = iArr[i3];
                boolean z;
                if (i7 == 1 || i7 == i5 - 1) {
                    length2 = (i4 + i) - 1;
                    if (i7 > 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    Arrays.fill(zArr2, i4, length2, z);
                    i6 = (i - 1) + i4;
                } else {
                    length2 = i - 1;
                    while (length2 >= 0) {
                        length = i4 + 1;
                        if (((1 << length2) & i7) != 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        zArr2[i4] = z;
                        length2--;
                        i4 = length;
                    }
                    i6 = i4;
                }
                i3++;
                i4 = i6;
            }
            return zArr2;
        } catch (Throwable e) {
            throw e.a(e);
        }
    }

    private static int a(boolean[] zArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = i; i4 < i + i2; i4++) {
            i3 <<= 1;
            if (zArr[i4]) {
                i3 |= 1;
            }
        }
        return i3;
    }
}
