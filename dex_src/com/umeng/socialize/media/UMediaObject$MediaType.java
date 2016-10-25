package com.umeng.socialize.media;

import com.xunlei.analytics.b.c;

public enum UMediaObject$MediaType {
    IMAGE {
        public final String toString() {
            return "0";
        }
    },
    VEDIO {
        public final String toString() {
            return c.f;
        }
    },
    MUSIC {
        public final String toString() {
            return c.e;
        }
    },
    TEXT {
        public final String toString() {
            return c.c;
        }
    },
    TEXT_IMAGE {
        public final String toString() {
            return c.d;
        }
    },
    WEBPAGE {
        public final String toString() {
            return "5";
        }
    };

    public static UMediaObject$MediaType convertToEmun(String str) {
        UMediaObject$MediaType[] values = values();
        int length = values.length;
        for (int i = 0; i < length; i++) {
            UMediaObject$MediaType uMediaObject$MediaType = values[i];
            if (uMediaObject$MediaType.toString().equals(str)) {
                return uMediaObject$MediaType;
            }
        }
        return null;
    }
}
