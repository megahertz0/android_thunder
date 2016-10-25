package com.inmobi.sdk;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build.VERSION;
import android.os.Looper;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.webkit.WebView;
import com.inmobi.ads.i;
import com.inmobi.commons.a.a;
import com.inmobi.commons.a.b;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.e;
import com.inmobi.commons.core.utilities.uid.c;
import com.inmobi.sdk.InMobiSdk.LogLevel;
import com.inmobi.signals.o;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.android.spdy.SpdyAgent;

public final class InMobiSdk {
    private static final String TAG;

    /* synthetic */ class AnonymousClass_2 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[LogLevel.values().length];
            try {
                a[LogLevel.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[LogLevel.ERROR.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[LogLevel.DEBUG.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public enum AgeGroup {
        BELOW_18("below18"),
        BETWEEN_18_AND_20("between18and20"),
        BETWEEN_21_AND_24("between21and24"),
        BETWEEN_25_AND_34("between25and34"),
        BETWEEN_35_AND_54("between35and54"),
        ABOVE_55("above55");
        private String a;

        private AgeGroup(String str) {
            this.a = str;
        }

        public final String toString() {
            return this.a;
        }
    }

    public enum Education {
        HIGH_SCHOOL_OR_LESS("highschoolorless"),
        COLLEGE_OR_GRADUATE("collegeorgraduate"),
        POST_GRADUATE_OR_ABOVE("postgraduateorabove");
        private String a;

        private Education(String str) {
            this.a = str;
        }

        public final String toString() {
            return this.a;
        }
    }

    public enum Ethnicity {
        AFRICAN_AMERICAN("africanamerican"),
        ASIAN("asian"),
        CAUCASIAN("caucasian"),
        HISPANIC("hispanic"),
        OTHER("other");
        private String a;

        private Ethnicity(String str) {
            this.a = str;
        }

        public final String toString() {
            return this.a;
        }
    }

    public enum Gender {
        FEMALE("f"),
        MALE("m");
        private String a;

        private Gender(String str) {
            this.a = str;
        }

        public final String toString() {
            return this.a;
        }
    }

    public enum HouseHoldIncome {
        BELOW_USD_5K("belowusd5k"),
        BETWEEN_USD_5K_AND_10K("betweenusd5kand10k"),
        BETWEEN_USD_10K_AND_15K("betweenusd10kand15k"),
        BETWEEN_USD_15K_AND_20K("betweenusd15kand20k"),
        BETWEEN_USD_20K_AND_25K("betweenusd20kand25k"),
        BETWEEN_USD_25K_AND_50K("betweenusd25kand50k"),
        BETWEEN_USD_50K_AND_75K("betweenusd50kand75k"),
        BETWEEN_USD_75K_AND_100K("betweenusd75kand100k"),
        BETWEEN_USD_100K_AND_150K("betweenusd100kand150k"),
        ABOVE_USD_150K("aboveusd150k");
        private String a;

        private HouseHoldIncome(String str) {
            this.a = str;
        }

        public final String toString() {
            return this.a;
        }
    }

    public enum ImIdType {
        LOGIN,
        SESSION
    }

    public enum LogLevel {
        NONE,
        ERROR,
        DEBUG
    }

    static {
        TAG = InMobiSdk.class.getSimpleName();
    }

    public static void init(Context context, String str) {
        if (VERSION.SDK_INT < b.b()) {
            Logger.a(InternalLogLevel.ERROR, TAG, new StringBuilder("The minimum supported Android API level is ").append(b.b()).append(", SDK could not be initialized.").toString());
        } else if (context == null) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Context supplied as null, SDK could not be initialized.");
        } else if (str == null || str.trim().length() == 0) {
            Logger.a(InternalLogLevel.ERROR, TAG, "Account ID cannot be null or empty. Please provide a valid Account ID.");
        } else {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                try {
                    new WebView(context).getSettings().getUserAgentString();
                } catch (RuntimeException e) {
                    Logger.a(InternalLogLevel.ERROR, TAG, "WebView not found, SDK could not be initialized.");
                }
            }
            try {
                a.b(context);
                try {
                    com.inmobi.commons.core.utilities.a.b.a();
                    Intent intent = new Intent();
                    intent.setClassName(context.getPackageName(), "com.inmobi.rendering.InMobiAdActivity");
                    if (context.getPackageManager().resolveActivity(intent, AccessibilityNodeInfoCompat.ACTION_CUT) == null) {
                        Logger.a(InternalLogLevel.ERROR, TAG, "The activity com.inmobi.rendering.InMobiAdActivity not present in AndroidManifest. SDK could not be initialized.");
                        return;
                    }
                    String trim = str.trim();
                    if (!(trim.length() == 32 || trim.length() == 36)) {
                        Logger.a(InternalLogLevel.DEBUG, TAG, "Invalid account id passed to init. Please provide a valid account id");
                    }
                    if (a.a()) {
                        Logger.a(InternalLogLevel.INTERNAL, TAG, "SDK already initialized");
                        return;
                    }
                    if (hasSdkVersionChanged(context)) {
                        b.a(context, a.a(context));
                        b.a(context, b.c());
                    }
                    a.a(context, trim);
                    if (b.b(context)) {
                        List<String> b = a.b(context);
                        for (String trim2 : b) {
                            sendDbDeletionTelemetryEvent(trim2);
                        }
                        if (b.isEmpty()) {
                            b.a(context, false);
                        }
                    }
                    e.b();
                    initComponents();
                    com.inmobi.commons.core.configs.b.a().d();
                    com.inmobi.commons.core.utilities.a a = com.inmobi.commons.core.utilities.a.a();
                    if (a != null) {
                        a.a(new com.inmobi.commons.core.utilities.a.b() {
                            public final void a(boolean z) {
                                a.a(z);
                                if (z) {
                                    InMobiSdk.initComponents();
                                } else {
                                    InMobiSdk.deInitComponents();
                                }
                            }
                        });
                    }
                } catch (SecurityException e2) {
                    Logger.a(InternalLogLevel.ERROR, TAG, "SDK encountered an internal error, SDK could not be initialized.");
                }
            } catch (NullPointerException e3) {
                Logger.a(InternalLogLevel.ERROR, TAG, "SDK encountered an internal error, SDK could not be initialized.");
            }
        }
    }

    private static void sendDbDeletionTelemetryEvent(String str) {
        if (a.a()) {
            Map hashMap = new HashMap();
            hashMap.put("fileName", str);
            com.inmobi.commons.core.c.a.a().a("ads", "PersistentDataCleanFail", hashMap);
        }
    }

    private static boolean hasSdkVersionChanged(Context context) {
        return b.a(context) == null || !b.a(context).equals(b.c());
    }

    private static void initComponents() {
        c.a().b();
        c.a().d();
        com.inmobi.commons.core.configs.b.a().b();
        com.inmobi.rendering.a.c.a().b();
        com.inmobi.commons.core.a.c.a();
        com.inmobi.commons.core.c.a.a().b();
        o.a().b();
        i.a().b();
    }

    private static void deInitComponents() {
        com.inmobi.commons.core.configs.b.a().c();
        com.inmobi.commons.core.c.a.a().c();
        o.a().c();
        i.a().c();
    }

    public static String getVersion() {
        return b.c();
    }

    public static void setLogLevel(LogLevel logLevel) {
        switch (AnonymousClass_2.a[logLevel.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                Logger.a(InternalLogLevel.NONE);
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                Logger.a(InternalLogLevel.ERROR);
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                Logger.a(InternalLogLevel.DEBUG);
            default:
                break;
        }
    }

    public static final void addIdType(ImIdType imIdType, String str) {
        if (imIdType == ImIdType.LOGIN) {
            e.n(str);
        } else if (imIdType == ImIdType.SESSION) {
            e.o(str);
        }
    }

    public static final void removeIdType(ImIdType imIdType) {
        if (imIdType == ImIdType.LOGIN) {
            e.n(null);
        } else if (imIdType == ImIdType.SESSION) {
            e.o(null);
        }
    }

    public static final void setAge(int i) {
        e.a(i);
    }

    public static final void setAgeGroup(AgeGroup ageGroup) {
        e.a(ageGroup.toString().toLowerCase(Locale.ENGLISH));
    }

    public static final void setAreaCode(String str) {
        e.b(str);
    }

    public static final void setPostalCode(String str) {
        e.c(str);
    }

    public static final void setLocationWithCityStateCountry(String str, String str2, String str3) {
        e.d(str);
        e.e(str2);
        e.f(str3);
    }

    public static final void setYearOfBirth(int i) {
        e.b(i);
    }

    public static final void setGender(Gender gender) {
        e.g(gender.toString().toLowerCase(Locale.ENGLISH));
    }

    public static final void setEthnicity(Ethnicity ethnicity) {
        e.h(ethnicity.toString().toLowerCase(Locale.ENGLISH));
    }

    public static final void setEducation(Education education) {
        e.i(education.toString().toLowerCase(Locale.ENGLISH));
    }

    public static final void setLanguage(String str) {
        e.j(str);
    }

    public static final void setIncome(int i) {
        e.c(i);
    }

    public static final void setHouseHoldIncome(HouseHoldIncome houseHoldIncome) {
        e.k(houseHoldIncome.toString().toLowerCase(Locale.ENGLISH));
    }

    public static final void setInterests(String str) {
        e.l(str);
    }

    public static final void setNationality(String str) {
        e.m(str);
    }

    public static final void setLocation(Location location) {
        e.a(location);
    }
}
