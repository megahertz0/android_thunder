package com.inmobi.commons.core.configs;

import com.inmobi.commons.core.c.a;
import com.inmobi.commons.core.configs.ConfigError.ErrorCode;
import com.inmobi.commons.core.network.c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import com.taobao.accs.common.Constants;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.download.DownloadManager;
import com.xunlei.download.Downloads.Impl;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

final class ConfigNetworkResponse {
    private static final String a;
    private Map<String, a> b;
    private Map<String, ConfigResponse> c;
    private c d;
    private ConfigError e;

    public static class ConfigResponse {
        private ConfigResponseStatus a;
        private a b;
        private ConfigError c;

        public enum ConfigResponseStatus {
            SUCCESS(200),
            NOT_MODIFIED(304),
            PRODUCT_NOT_FOUND(404),
            INTERNAL_ERROR(500),
            UNKNOWN(-1);
            private int a;

            private ConfigResponseStatus(int i) {
                this.a = i;
            }

            public final int getValue() {
                return this.a;
            }

            public static com.inmobi.commons.core.configs.ConfigNetworkResponse.ConfigResponse.ConfigResponseStatus fromValue(int i) {
                com.inmobi.commons.core.configs.ConfigNetworkResponse.ConfigResponse.ConfigResponseStatus[] values = values();
                int length = values.length;
                for (int i2 = 0; i2 < length; i2++) {
                    com.inmobi.commons.core.configs.ConfigNetworkResponse.ConfigResponse.ConfigResponseStatus configResponseStatus = values[i2];
                    if (configResponseStatus.a == i) {
                        return configResponseStatus;
                    }
                }
                return UNKNOWN;
            }
        }

        public ConfigResponse(JSONObject jSONObject, a aVar) {
            this.b = aVar;
            if (jSONObject != null) {
                a(jSONObject);
            }
        }

        private void a(JSONObject jSONObject) {
            try {
                this.a = ConfigResponseStatus.fromValue(jSONObject.getInt(Impl.COLUMN_STATUS));
                if (this.a == ConfigResponseStatus.SUCCESS) {
                    this.b.a(jSONObject.getJSONObject(ParamKey.CONTENT));
                    if (!this.b.c()) {
                        a(new ConfigError(ErrorCode.PARSING_ERROR, "The received config has failed validation."));
                        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Config type:").append(this.b.a()).append(" Error code:").append(c().a()).append(" Error message:").append(c().b()).toString());
                    }
                } else if (this.a == ConfigResponseStatus.NOT_MODIFIED) {
                    Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Config type:").append(this.b.a()).append(" Config not modified").toString());
                } else {
                    a(new ConfigError(ErrorCode.CONFIG_SERVER_INTERNAL_ERROR, this.a.toString()));
                    Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Config type:").append(this.b.a()).append(" Error code:").append(c().a()).append(" Error message:").append(c().b()).toString());
                }
            } catch (Throwable e) {
                a(new ConfigError(ErrorCode.PARSING_ERROR, e.getLocalizedMessage()));
                Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Config type:").append(this.b.a()).append(" Error code:").append(c().a()).append(" Error message:").append(c().b()).toString(), e);
                Map hashMap = new HashMap();
                hashMap.put(SelectCountryActivity.EXTRA_COUNTRY_NAME, this.b.a());
                hashMap.put(Constants.KEY_ERROR_CODE, "ParsingError");
                hashMap.put(DownloadManager.COLUMN_REASON, e.getLocalizedMessage());
                a.a().a("root", "InvalidConfig", hashMap);
            }
        }

        public a a() {
            return this.b;
        }

        public ConfigResponseStatus b() {
            return this.a;
        }

        public ConfigError c() {
            return this.c;
        }

        public void a(ConfigError configError) {
            this.c = configError;
        }

        public boolean d() {
            return this.c != null;
        }
    }

    static {
        a = ConfigNetworkResponse.class.getName();
    }

    ConfigNetworkResponse(Map<String, a> map, c cVar) {
        this.b = map;
        this.d = cVar;
        this.c = new HashMap();
        d();
    }

    private void d() {
        if (this.d.a()) {
            for (Entry entry : this.b.entrySet()) {
                ConfigResponse configResponse = new ConfigResponse(null, (a) entry.getValue());
                configResponse.a(new ConfigError(ErrorCode.NETWORK_ERROR, "Network error in fetching config."));
                this.c.put(entry.getKey(), configResponse);
            }
            a(new ConfigError(ErrorCode.NETWORK_ERROR, this.d.c().b()));
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Error code:").append(b().a()).append(" Error message:").append(b().b()).toString());
            Map hashMap = new HashMap();
            hashMap.put(SelectCountryActivity.EXTRA_COUNTRY_NAME, a(this.b));
            hashMap.put(Constants.KEY_ERROR_CODE, String.valueOf(this.d.c().a().getValue()));
            hashMap.put(DownloadManager.COLUMN_REASON, this.d.c().b());
            a.a().a("root", "InvalidConfig", hashMap);
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.d.b());
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                JSONObject jSONObject2 = jSONObject.getJSONObject(str);
                if (this.b.get(str) != null) {
                    this.c.put(str, new ConfigResponse(jSONObject2, (a) this.b.get(str)));
                }
            }
        } catch (Throwable e) {
            a(new ConfigError(ErrorCode.PARSING_ERROR, e.getLocalizedMessage()));
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Error code:").append(b().a()).append(" Error message:").append(b().b()).toString(), e);
            Map hashMap2 = new HashMap();
            hashMap2.put(SelectCountryActivity.EXTRA_COUNTRY_NAME, a(this.b));
            hashMap2.put(Constants.KEY_ERROR_CODE, "ParsingError");
            hashMap2.put(DownloadManager.COLUMN_REASON, e.getLocalizedMessage());
            a.a().a("root", "InvalidConfig", hashMap2);
        }
    }

    public final Map<String, ConfigResponse> a() {
        return this.c;
    }

    public final ConfigError b() {
        return this.e;
    }

    private void a(ConfigError configError) {
        this.e = configError;
    }

    private static String a(Map<String, a> map) {
        String str = com.umeng.a.d;
        String str2 = str;
        for (String str3 : map.keySet()) {
            str2 = str2 + str3 + MiPushClient.ACCEPT_TIME_SEPARATOR;
        }
        return str2.substring(0, str2.length() - 1);
    }
}
