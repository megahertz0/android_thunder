package com.xunlei.thundersniffer.sniff.sniffer;

import android.text.TextUtils;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.thundersniffer.sniff.SniffingResource;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

final class ar {
    private static final int[] a;
    private static final String[] b;

    static interface a {
        void a(String str, boolean z);
    }

    static class b implements Comparator<SniffingResource> {
        private final Pattern a;

        b() {
            this.a = Pattern.compile("(:?([0-9]+)(:?\u96c6|\u8bdd))|(:?[^a-zA-Z]e[p]?[i]?[s]?[o]?[d]?[e]?[\\.]?([0-9]+))", SimpleLog.LOG_LEVEL_DEBUG);
        }

        public final /* synthetic */ int compare(Object obj, Object obj2) {
            SniffingResource sniffingResource = (SniffingResource) obj2;
            String str = ((SniffingResource) obj).resourceName;
            String str2 = sniffingResource.resourceName;
            int compareTo = str.compareTo(str2);
            if (compareTo == 0) {
                return compareTo;
            }
            int a = a(str);
            int a2 = a(str2);
            if (a >= 0 && a2 >= 0) {
                return a != a2 ? a - a2 : compareTo;
            } else {
                if (a < 0 || a == a2) {
                    return (a2 < 0 || a == a2) ? compareTo : -1;
                } else {
                    return 1;
                }
            }
        }

        private int a(String str) {
            Matcher matcher = this.a.matcher(str);
            if (!matcher.find()) {
                return -1;
            }
            MatchResult toMatchResult = matcher.toMatchResult();
            int groupCount = toMatchResult.groupCount();
            int i = 1;
            while (i <= groupCount) {
                if (toMatchResult.start(i) <= toMatchResult.end(i) && toMatchResult.start(i) >= 0) {
                    try {
                        return Integer.parseInt(str.substring(toMatchResult.start(i), toMatchResult.end(i)), SpdyProtocol.PUBKEY_SEQ_OPEN);
                    } catch (NumberFormatException e) {
                        return -1;
                    }
                }
                i++;
            }
            return -1;
        }
    }

    static class c implements Comparator<SniffingResourceGroup> {
        c() {
        }

        public final /* synthetic */ int compare(Object obj, Object obj2) {
            SniffingResourceGroup sniffingResourceGroup = (SniffingResourceGroup) obj;
            SniffingResourceGroup sniffingResourceGroup2 = (SniffingResourceGroup) obj2;
            if (sniffingResourceGroup == sniffingResourceGroup2) {
                return 0;
            }
            return sniffingResourceGroup.matchScore == sniffingResourceGroup2.matchScore ? -(sniffingResourceGroup.count - sniffingResourceGroup2.count) : -(sniffingResourceGroup.matchScore - sniffingResourceGroup2.matchScore);
        }
    }

    public static void a(String str, a aVar) {
        String str2;
        boolean z;
        String str3;
        Object obj = null;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setReadTimeout(3000);
            httpURLConnection.getResponseCode();
            String decode = URLDecoder.decode(httpURLConnection.getURL().toString(), CharsetConvert.UTF_8);
            httpURLConnection.disconnect();
            String str4 = "?src=";
            Object obj2 = "&word=";
            if (decode.contains(str4) && decode.contains(".baidu.com")) {
                int indexOf = decode.indexOf(str4);
                if (decode.contains(obj2)) {
                    str4 = decode.substring(str4.length() + indexOf, decode.indexOf(obj2));
                } else {
                    str4 = decode.substring(str4.length() + indexOf);
                }
            } else {
                ae.b();
                if (ae.e(decode)) {
                    ae.b();
                    str4 = ae.f(decode);
                } else {
                    ah.b();
                    if (ah.e(decode)) {
                        ah.b();
                        str4 = ah.f(decode);
                    } else {
                        str4 = decode;
                    }
                }
            }
            try {
                if (!TextUtils.isEmpty(str4)) {
                    str4 = com.xunlei.c.b.j(str4);
                }
                str2 = str4;
                z = false;
            } catch (IOException e) {
                IOException iOException;
                IOException iOException2 = e;
                str3 = str4;
                iOException = iOException2;
                iOException.printStackTrace();
                if (iOException instanceof SocketTimeoutException) {
                    z = true;
                    str2 = str3;
                } else {
                    z = false;
                    str2 = str3;
                }
                aVar.a(str2, z);
            }
        } catch (IOException e2) {
            iOException = e2;
            iOException.printStackTrace();
            if (iOException instanceof SocketTimeoutException) {
                z = true;
                str2 = str3;
            } else {
                z = false;
                str2 = str3;
            }
            aVar.a(str2, z);
        }
        aVar.a(str2, z);
    }

    public static void a(List<SniffingResource> list) {
        if (list != null) {
            Collections.sort(list, new b());
        }
    }

    public static void b(List<SniffingResourceGroup> list) {
        if (list != null) {
            Collections.sort(list, new c());
        }
    }

    public static boolean a(String str) {
        switch (com.xunlei.c.b.a(str)) {
            case SimpleLog.LOG_LEVEL_TRACE:
                return com.xunlei.c.b.c(com.xunlei.c.b.f(str));
            case SimpleLog.LOG_LEVEL_DEBUG:
                return true;
            default:
                return false;
        }
    }

    public static int b(String str) {
        if (!TextUtils.isEmpty(str)) {
            switch (com.xunlei.c.b.a(str)) {
                case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                    return 1;
                case SimpleLog.LOG_LEVEL_TRACE:
                    String f = com.xunlei.c.b.f(str);
                    if (com.xunlei.c.b.c(f)) {
                        return MqttConnectOptions.MQTT_VERSION_3_1;
                    }
                    return com.xunlei.c.b.d(f) ? 2 : 1;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    return MqttConnectOptions.MQTT_VERSION_3_1;
                case MqttConnectOptions.MQTT_VERSION_3_1:
                    return 2;
            }
        }
        return 0;
    }

    public static String a(String str, Pattern pattern) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? matcher.group(1) : null;
    }

    static {
        a = new int[]{-20319, -20317, -20304, -20295, -20292, -20283, -20265, -20257, -20242, -20230, -20051, -20036, -20032, -20026, -20002, -19990, -19986, -19982, -19976, -19805, -19784, -19775, -19774, -19763, -19756, -19751, -19746, -19741, -19739, -19728, -19725, -19715, -19540, -19531, -19525, -19515, -19500, -19484, -19479, -19467, -19289, -19288, -19281, -19275, -19270, -19263, -19261, -19249, -19243, -19242, -19238, -19235, -19227, -19224, -19218, -19212, -19038, -19023, -19018, -19006, -19003, -18996, -18977, -18961, -18952, -18783, -18774, -18773, -18763, -18756, -18741, -18735, -18731, -18722, -18710, -18697, -18696, -18526, -18518, -18501, -18490, -18478, -18463, -18448, -18447, -18446, -18239, -18237, -18231, -18220, -18211, -18201, -18184, -18183, -18181, -18012, -17997, -17988, -17970, -17964, -17961, -17950, -17947, -17931, -17928, -17922, -17759, -17752, -17733, -17730, -17721, -17703, -17701, -17697, -17692, -17683, -17676, -17496, -17487, -17482, -17468, -17454, -17433, -17427, -17417, -17202, -17185, -16983, -16970, -16942, -16915, -16733, -16708, -16706, -16689, -16664, -16657, -16647, -16474, -16470, -16465, -16459, -16452, -16448, -16433, -16429, -16427, -16423, -16419, -16412, -16407, -16403, -16401, -16393, -16220, -16216, -16212, -16205, -16202, -16187, -16180, -16171, -16169, -16158, -16155, -15959, -15958, -15944, -15933, -15920, -15915, -15903, -15889, -15878, -15707, -15701, -15681, -15667, -15661, -15659, -15652, -15640, -15631, -15625, -15454, -15448, -15436, -15435, -15419, -15416, -15408, -15394, -15385, -15377, -15375, -15369, -15363, -15362, -15183, -15180, -15165, -15158, -15153, -15150, -15149, -15144, -15143, -15141, -15140, -15139, -15128, -15121, -15119, -15117, -15110, -15109, -14941, -14937, -14933, -14930, -14929, -14928, -14926, -14922, -14921, -14914, -14908, -14902, -14894, -14889, -14882, -14873, -14871, -14857, -14678, -14674, -14670, -14668, -14663, -14654, -14645, -14630, -14594, -14429, -14407, -14399, -14384, -14379, -14368, -14355, -14353, -14345, -14170, -14159, -14151, -14149, -14145, -14140, -14137, -14135, -14125, -14123, -14122, -14112, -14109, -14099, -14097, -14094, -14092, -14090, -14087, -14083, -13917, -13914, -13910, -13907, -13906, -13905, -13896, -13894, -13878, -13870, -13859, -13847, -13831, -13658, -13611, -13601, -13406, -13404, -13400, -13398, -13395, -13391, -13387, -13383, -13367, -13359, -13356, -13343, -13340, -13329, -13326, -13318, -13147, -13138, -13120, -13107, -13096, -13095, -13091, -13076, -13068, -13063, -13060, -12888, -12875, -12871, -12860, -12858, -12852, -12849, -12838, -12831, -12829, -12812, -12802, -12607, -12597, -12594, -12585, -12556, -12359, -12346, -12320, -12300, -12120, -12099, -12089, -12074, -12067, -12058, -12039, -11867, -11861, -11847, -11831, -11798, -11781, -11604, -11589, -11536, -11358, -11340, -11339, -11324, -11303, -11097, -11077, -11067, -11055, -11052, -11045, -11041, -11038, -11024, -11020, -11019, -11018, -11014, -10838, -10832, -10815, -10800, -10790, -10780, -10764, -10587, -10544, -10533, -10519, -10331, -10329, -10328, -10322, -10315, -10309, -10307, -10296, -10281, -10274, -10270, -10262, -10260, -10256, -10254};
        b = new String[]{"a", "ai", "an", "ang", "ao", "ba", "bai", "ban", "bang", "bao", "bei", "ben", "beng", "bi", "bian", "biao", "bie", "bin", "bing", "bo", "bu", "ca", "cai", "can", "cang", "cao", "ce", "ceng", "cha", "chai", "chan", "chang", "chao", "che", "chen", "cheng", "chi", "chong", "chou", "chu", "chuai", "chuan", "chuang", "chui", "chun", "chuo", "ci", "cong", "cou", SocializeProtocolConstants.PROTOCOL_KEY_DEFAULT_ACCOUNT, "cuan", "cui", "cun", "cuo", "da", "dai", "dan", "dang", "dao", SocializeProtocolConstants.PROTOCOL_KEY_DE, "deng", "di", "dian", "diao", "die", "ding", "diu", "dong", "dou", "du", "duan", "dui", "dun", "duo", "e", SocializeProtocolConstants.PROTOCOL_KEY_EN, "er", "fa", "fan", "fang", "fei", "fen", "feng", "fo", "fou", "fu", "ga", "gai", "gan", "gang", "gao", "ge", "gei", "gen", "geng", "gong", "gou", "gu", "gua", "guai", "guan", "guang", "gui", "gun", "guo", "ha", "hai", "han", "hang", "hao", "he", "hei", "hen", "heng", "hong", "hou", "hu", "hua", "huai", "huan", "huang", "hui", "hun", "huo", "ji", "jia", "jian", "jiang", "jiao", "jie", "jin", "jing", "jiong", "jiu", "ju", "juan", "jue", "jun", "ka", "kai", "kan", "kang", "kao", "ke", "ken", "keng", "kong", "kou", "ku", "kua", "kuai", "kuan", "kuang", "kui", "kun", "kuo", "la", "lai", "lan", "lang", "lao", "le", "lei", "leng", "li", "lia", "lian", "liang", "liao", "lie", "lin", "ling", "liu", "long", "lou", "lu", "lv", "luan", "lue", "lun", "luo", "ma", "mai", "man", "mang", "mao", "me", "mei", "men", "meng", "mi", "mian", "miao", "mie", "min", "ming", "miu", "mo", "mou", "mu", "na", "nai", "nan", "nang", "nao", "ne", "nei", "nen", "neng", "ni", "nian", "niang", "niao", "nie", "nin", "ning", "niu", "nong", "nu", "nv", "nuan", "nue", "nuo", "o", "ou", "pa", "pai", "pan", "pang", "pao", "pei", "pen", "peng", "pi", "pian", "piao", "pie", "pin", "ping", "po", "pu", "qi", "qia", "qian", "qiang", "qiao", "qie", "qin", "qing", "qiong", "qiu", "qu", "quan", "que", "qun", "ran", "rang", "rao", "re", "ren", "reng", "ri", "rong", "rou", "ru", "ruan", "rui", "run", "ruo", "sa", "sai", "san", "sang", "sao", "se", "sen", "seng", "sha", "shai", "shan", "shang", "shao", "she", "shen", "sheng", "shi", "shou", "shu", "shua", "shuai", "shuan", "shuang", "shui", "shun", "shuo", "si", "song", "sou", "su", "suan", "sui", "sun", "suo", "ta", "tai", "tan", "tang", "tao", "te", "teng", "ti", "tian", "tiao", "tie", "ting", "tong", "tou", "tu", "tuan", "tui", "tun", "tuo", "wa", "wai", "wan", "wang", "wei", "wen", "weng", "wo", "wu", "xi", "xia", "xian", "xiang", "xiao", "xie", "xin", "xing", "xiong", "xiu", "xu", "xuan", "xue", "xun", "ya", "yan", "yang", "yao", "ye", "yi", "yin", "ying", "yo", "yong", "you", "yu", "yuan", "yue", "yun", "za", "zai", "zan", "zang", "zao", "ze", "zei", "zen", "zeng", "zha", "zhai", "zhan", "zhang", "zhao", "zhe", "zhen", "zheng", "zhi", "zhong", "zhou", "zhu", "zhua", "zhuai", "zhuan", "zhuang", "zhui", "zhun", "zhuo", "zi", "zong", "zou", "zu", "zuan", "zui", "zun", "zuo"};
    }

    private static int d(String str) {
        int i = 0;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            byte[] bytes = str.getBytes("gb2312");
            if (bytes == null || bytes.length > 2 || bytes.length <= 0) {
                throw new RuntimeException("illegal resource string");
            }
            if (bytes.length == 1) {
                i = bytes[0];
            }
            if (bytes.length != 2) {
                return i;
            }
            return ((bytes[1] + 256) + ((bytes[0] + 256) * 256)) - 65536;
        } catch (Exception e) {
            return 0;
        }
    }

    public static String c(String str) {
        String str2 = BuildConfig.VERSION_NAME;
        for (int i = 0; i < str.length(); i++) {
            int d = d(str.substring(i, i + 1));
            if (d <= 0 || d >= 160) {
                for (int length = a.length - 1; length >= 0; length--) {
                    if (a[length] <= d) {
                        str2 = str2 + b[length];
                        break;
                    }
                }
            } else {
                str2 = str2 + String.valueOf((char) d);
            }
        }
        return str2;
    }
}
