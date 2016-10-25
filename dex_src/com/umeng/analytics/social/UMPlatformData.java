package com.umeng.analytics.social;

import android.text.TextUtils;
import com.tencent.connect.common.Constants;
import com.umeng.analytics.a;
import com.umeng.socialize.PlatformConfig.Renren;
import com.umeng.socialize.PlatformConfig.TencentWeibo;
import java.util.Locale;

public class UMPlatformData {
    private UMedia a;
    private String b;
    private String c;
    private String d;
    private GENDER e;

    public enum GENDER {
        MALE(0) {
            public final String toString() {
                return String.format(Locale.US, "Male:%d", new Object[]{Integer.valueOf(this.value)});
            }
        },
        FEMALE(1) {
            public final String toString() {
                return String.format(Locale.US, "Female:%d", new Object[]{Integer.valueOf(this.value)});
            }
        };
        public int value;

        static {
            MALE = new AnonymousClass_1("MALE", 0, 0);
            FEMALE = new AnonymousClass_2("FEMALE", 1, 1);
            a = new com.umeng.analytics.social.UMPlatformData.GENDER[]{MALE, FEMALE};
        }

        private GENDER(int i) {
            this.value = i;
        }
    }

    public enum UMedia {
        SINA_WEIBO {
            public final String toString() {
                return "sina";
            }
        },
        TENCENT_WEIBO {
            public final String toString() {
                return TencentWeibo.Name;
            }
        },
        TENCENT_QZONE {
            public final String toString() {
                return Constants.SOURCE_QZONE;
            }
        },
        TENCENT_QQ {
            public final String toString() {
                return "qq";
            }
        },
        WEIXIN_FRIENDS {
            public final String toString() {
                return "wxsesion";
            }
        },
        WEIXIN_CIRCLE {
            public final String toString() {
                return "wxtimeline";
            }
        },
        RENREN {
            public final String toString() {
                return Renren.Name;
            }
        },
        DOUBAN {
            public final String toString() {
                return "douban";
            }
        };

        static {
            SINA_WEIBO = new AnonymousClass_1("SINA_WEIBO", 0);
            TENCENT_WEIBO = new AnonymousClass_2("TENCENT_WEIBO", 1);
            TENCENT_QZONE = new AnonymousClass_3("TENCENT_QZONE", 2);
            TENCENT_QQ = new AnonymousClass_4("TENCENT_QQ", 3);
            WEIXIN_FRIENDS = new AnonymousClass_5("WEIXIN_FRIENDS", 4);
            WEIXIN_CIRCLE = new AnonymousClass_6("WEIXIN_CIRCLE", 5);
            RENREN = new AnonymousClass_7("RENREN", 6);
            DOUBAN = new AnonymousClass_8("DOUBAN", 7);
            a = new com.umeng.analytics.social.UMPlatformData.UMedia[]{SINA_WEIBO, TENCENT_WEIBO, TENCENT_QZONE, TENCENT_QQ, WEIXIN_FRIENDS, WEIXIN_CIRCLE, RENREN, DOUBAN};
        }
    }

    public UMPlatformData(UMedia uMedia, String str) {
        this.b = d;
        this.c = d;
        if (uMedia == null || TextUtils.isEmpty(str)) {
            b.b(a.e, "parameter is not valid");
            return;
        }
        this.a = uMedia;
        this.b = str;
    }

    public String getWeiboId() {
        return this.c;
    }

    public void setWeiboId(String str) {
        this.c = str;
    }

    public UMedia getMeida() {
        return this.a;
    }

    public String getUsid() {
        return this.b;
    }

    public String getName() {
        return this.d;
    }

    public void setName(String str) {
        this.d = str;
    }

    public GENDER getGender() {
        return this.e;
    }

    public void setGender(GENDER gender) {
        this.e = gender;
    }

    public boolean isValid() {
        return (this.a == null || TextUtils.isEmpty(this.b)) ? false : true;
    }

    public String toString() {
        return new StringBuilder("UMPlatformData [meida=").append(this.a).append(", usid=").append(this.b).append(", weiboId=").append(this.c).append(", name=").append(this.d).append(", gender=").append(this.e).append("]").toString();
    }
}
