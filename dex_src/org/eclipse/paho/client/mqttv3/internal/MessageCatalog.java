package org.eclipse.paho.client.mqttv3.internal;

import com.xunlei.xiazaibao.BuildConfig;

public abstract class MessageCatalog {
    private static MessageCatalog INSTANCE;

    protected abstract String getLocalizedMessage(int i);

    static {
        INSTANCE = null;
    }

    public static final String getMessage(int i) {
        if (INSTANCE == null) {
            if (ExceptionHelper.isClassAvailable("java.util.ResourceBundle")) {
                try {
                    INSTANCE = (MessageCatalog) Class.forName("org.eclipse.paho.client.mqttv3.internal.ResourceBundleCatalog").newInstance();
                } catch (Exception e) {
                    return BuildConfig.VERSION_NAME;
                }
            } else if (ExceptionHelper.isClassAvailable("org.eclipse.paho.client.mqttv3.internal.MIDPCatalog")) {
                try {
                    INSTANCE = (MessageCatalog) Class.forName("org.eclipse.paho.client.mqttv3.internal.MIDPCatalog").newInstance();
                } catch (Exception e2) {
                    return BuildConfig.VERSION_NAME;
                }
            }
        }
        return INSTANCE.getLocalizedMessage(i);
    }
}
