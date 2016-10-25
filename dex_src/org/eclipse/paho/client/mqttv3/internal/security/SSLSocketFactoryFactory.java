package org.eclipse.paho.client.mqttv3.internal.security;

import com.xunlei.xllib.R;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Vector;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;
import org.eclipse.paho.client.mqttv3.logging.Logger;

public class SSLSocketFactoryFactory {
    public static final String CIPHERSUITES = "com.ibm.ssl.enabledCipherSuites";
    private static final String CLASS_NAME = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
    public static final String CLIENTAUTH = "com.ibm.ssl.clientAuthentication";
    public static final String DEFAULT_PROTOCOL = "TLS";
    public static final String JSSEPROVIDER = "com.ibm.ssl.contextProvider";
    public static final String KEYSTORE = "com.ibm.ssl.keyStore";
    public static final String KEYSTOREMGR = "com.ibm.ssl.keyManager";
    public static final String KEYSTOREPROVIDER = "com.ibm.ssl.keyStoreProvider";
    public static final String KEYSTOREPWD = "com.ibm.ssl.keyStorePassword";
    public static final String KEYSTORETYPE = "com.ibm.ssl.keyStoreType";
    public static final String SSLPROTOCOL = "com.ibm.ssl.protocol";
    public static final String SYSKEYMGRALGO = "ssl.KeyManagerFactory.algorithm";
    public static final String SYSKEYSTORE = "javax.net.ssl.keyStore";
    public static final String SYSKEYSTOREPWD = "javax.net.ssl.keyStorePassword";
    public static final String SYSKEYSTORETYPE = "javax.net.ssl.keyStoreType";
    public static final String SYSTRUSTMGRALGO = "ssl.TrustManagerFactory.algorithm";
    public static final String SYSTRUSTSTORE = "javax.net.ssl.trustStore";
    public static final String SYSTRUSTSTOREPWD = "javax.net.ssl.trustStorePassword";
    public static final String SYSTRUSTSTORETYPE = "javax.net.ssl.trustStoreType";
    public static final String TRUSTSTORE = "com.ibm.ssl.trustStore";
    public static final String TRUSTSTOREMGR = "com.ibm.ssl.trustManager";
    public static final String TRUSTSTOREPROVIDER = "com.ibm.ssl.trustStoreProvider";
    public static final String TRUSTSTOREPWD = "com.ibm.ssl.trustStorePassword";
    public static final String TRUSTSTORETYPE = "com.ibm.ssl.trustStoreType";
    private static final byte[] key;
    private static final String[] propertyKeys;
    private static final String xorTag = "{xor}";
    private Hashtable configs;
    private Properties defaultProperties;
    private Logger logger;

    static {
        propertyKeys = new String[]{SSLPROTOCOL, JSSEPROVIDER, KEYSTORE, KEYSTOREPWD, KEYSTORETYPE, KEYSTOREPROVIDER, KEYSTOREMGR, TRUSTSTORE, TRUSTSTOREPWD, TRUSTSTORETYPE, TRUSTSTOREPROVIDER, TRUSTSTOREMGR, CIPHERSUITES, CLIENTAUTH};
        key = new byte[]{(byte) -99, (byte) -89, (byte) -39, Byte.MIN_VALUE, (byte) 5, (byte) -72, (byte) -119, (byte) -100};
    }

    public static boolean isSupportedOnJVM() throws LinkageError, ExceptionInInitializerError {
        try {
            Class.forName("javax.net.ssl.SSLServerSocketFactory");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public SSLSocketFactoryFactory() {
        this.logger = null;
        this.configs = new Hashtable();
    }

    public SSLSocketFactoryFactory(Logger logger) {
        this();
        this.logger = logger;
    }

    private boolean keyValid(String str) {
        int i = 0;
        while (i < propertyKeys.length && !propertyKeys[i].equals(str)) {
            i++;
        }
        return i < propertyKeys.length;
    }

    private void checkPropertyKeys(Properties properties) throws IllegalArgumentException {
        for (String str : properties.keySet()) {
            if (!keyValid(str)) {
                throw new IllegalArgumentException(new StringBuffer(String.valueOf(str)).append(" is not a valid IBM SSL property key.").toString());
            }
        }
    }

    public static char[] toChar(byte[] bArr) {
        int i = 0;
        if (bArr == null) {
            return null;
        }
        char[] cArr = new char[(bArr.length / 2)];
        int i2 = 0;
        while (i2 < bArr.length) {
            int i3 = i + 1;
            int i4 = i2 + 1;
            int i5 = bArr[i2] & 255;
            i2 = i4 + 1;
            cArr[i] = (char) (((bArr[i4] & 255) << 8) + i5);
            i = i3;
        }
        return cArr;
    }

    public static byte[] toByte(char[] cArr) {
        int i = 0;
        if (cArr == null) {
            return null;
        }
        byte[] bArr = new byte[(cArr.length * 2)];
        int i2 = 0;
        while (i < cArr.length) {
            int i3 = i2 + 1;
            bArr[i2] = (byte) (cArr[i] & 255);
            int i4 = i3 + 1;
            i2 = i + 1;
            bArr[i3] = (byte) ((cArr[i] >> 8) & 255);
            i = i2;
            i2 = i4;
        }
        return bArr;
    }

    public static String obfuscate(char[] cArr) {
        if (cArr == null) {
            return null;
        }
        byte[] toByte = toByte(cArr);
        for (int i = 0; i < toByte.length; i++) {
            toByte[i] = (byte) ((toByte[i] ^ key[i % key.length]) & 255);
        }
        return new StringBuffer(xorTag).append(new String(SimpleBase64Encoder.encode(toByte))).toString();
    }

    public static char[] deObfuscate(String str) {
        char[] cArr = null;
        if (str == null) {
            return null;
        }
        try {
            byte[] decode = SimpleBase64Encoder.decode(str.substring(SimpleLog.LOG_LEVEL_ERROR));
            int i = 0;
            while (i < decode.length) {
                decode[i] = (byte) ((decode[i] ^ key[i % key.length]) & 255);
                i++;
            }
            return toChar(decode);
        } catch (Exception e) {
            return cArr;
        }
    }

    public static String packCipherSuites(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < strArr.length; i++) {
            stringBuffer.append(strArr[i]);
            if (i < strArr.length - 1) {
                stringBuffer.append(',');
            }
        }
        return stringBuffer.toString();
    }

    public static String[] unpackCipherSuites(String str) {
        if (str == null) {
            return null;
        }
        Vector vector = new Vector();
        int indexOf = str.indexOf(R.styleable.AppCompatTheme_listDividerAlertDialog);
        int i = 0;
        while (indexOf >= 0) {
            vector.add(str.substring(i, indexOf));
            i = indexOf + 1;
            indexOf = str.indexOf(R.styleable.AppCompatTheme_listDividerAlertDialog, i);
        }
        vector.add(str.substring(i));
        String[] strArr = new String[vector.size()];
        vector.toArray(strArr);
        return strArr;
    }

    private void convertPassword(Properties properties) {
        String property = properties.getProperty(KEYSTOREPWD);
        if (!(property == null || property.startsWith(xorTag))) {
            properties.put(KEYSTOREPWD, obfuscate(property.toCharArray()));
        }
        property = properties.getProperty(TRUSTSTOREPWD);
        if (property != null && !property.startsWith(xorTag)) {
            properties.put(TRUSTSTOREPWD, obfuscate(property.toCharArray()));
        }
    }

    public void initialize(Properties properties, String str) throws IllegalArgumentException {
        checkPropertyKeys(properties);
        Properties properties2 = new Properties();
        properties2.putAll(properties);
        convertPassword(properties2);
        if (str != null) {
            this.configs.put(str, properties2);
        } else {
            this.defaultProperties = properties2;
        }
    }

    public void merge(Properties properties, String str) throws IllegalArgumentException {
        checkPropertyKeys(properties);
        Properties properties2 = this.defaultProperties;
        if (str != null) {
            properties2 = (Properties) this.configs.get(str);
        }
        if (properties2 == null) {
            properties2 = new Properties();
        }
        convertPassword(properties);
        properties2.putAll(properties);
        if (str != null) {
            this.configs.put(str, properties2);
        } else {
            this.defaultProperties = properties2;
        }
    }

    public boolean remove(String str) {
        if (str != null) {
            return this.configs.remove(str) != null;
        } else {
            if (this.defaultProperties == null) {
                return false;
            }
            this.defaultProperties = null;
            return true;
        }
    }

    public Properties getConfiguration(String str) {
        Properties properties;
        if (str == null) {
            properties = this.defaultProperties;
        } else {
            properties = this.configs.get(str);
        }
        return properties;
    }

    private String getProperty(String str, String str2, String str3) {
        String propertyFromConfig = getPropertyFromConfig(str, str2);
        return (propertyFromConfig == null && str3 != null) ? System.getProperty(str3) : propertyFromConfig;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String getPropertyFromConfig(java.lang.String r3, java.lang.String r4) {
        throw new UnsupportedOperationException("Method not decompiled: org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory.getPropertyFromConfig(java.lang.String, java.lang.String):java.lang.String");
        /*
        this = this;
        r1 = 0;
        if (r3 == 0) goto L_0x0020;
    L_0x0003:
        r0 = r2.configs;
        r0 = r0.get(r3);
        r0 = (java.util.Properties) r0;
    L_0x000b:
        if (r0 == 0) goto L_0x0014;
    L_0x000d:
        r0 = r0.getProperty(r4);
        if (r0 == 0) goto L_0x0015;
    L_0x0013:
        return r0;
    L_0x0014:
        r0 = r1;
    L_0x0015:
        r1 = r2.defaultProperties;
        if (r1 == 0) goto L_0x0013;
    L_0x0019:
        r0 = r1.getProperty(r4);
        if (r0 == 0) goto L_0x0013;
    L_0x001f:
        goto L_0x0013;
    L_0x0020:
        r0 = r1;
        goto L_0x000b;
        */
    }

    public String getSSLProtocol(String str) {
        return getProperty(str, SSLPROTOCOL, null);
    }

    public String getJSSEProvider(String str) {
        return getProperty(str, JSSEPROVIDER, null);
    }

    public String getKeyStore(String str) {
        String str2 = KEYSTORE;
        String str3 = SYSKEYSTORE;
        str2 = getPropertyFromConfig(str, str2);
        return str2 != null ? str2 : System.getProperty(str3);
    }

    public char[] getKeyStorePassword(String str) {
        String property = getProperty(str, KEYSTOREPWD, SYSKEYSTOREPWD);
        if (property != null) {
            return property.startsWith(xorTag) ? deObfuscate(property) : property.toCharArray();
        } else {
            return null;
        }
    }

    public String getKeyStoreType(String str) {
        return getProperty(str, KEYSTORETYPE, SYSKEYSTORETYPE);
    }

    public String getKeyStoreProvider(String str) {
        return getProperty(str, KEYSTOREPROVIDER, null);
    }

    public String getKeyManager(String str) {
        return getProperty(str, KEYSTOREMGR, SYSKEYMGRALGO);
    }

    public String getTrustStore(String str) {
        return getProperty(str, TRUSTSTORE, SYSTRUSTSTORE);
    }

    public char[] getTrustStorePassword(String str) {
        String property = getProperty(str, TRUSTSTOREPWD, SYSTRUSTSTOREPWD);
        if (property != null) {
            return property.startsWith(xorTag) ? deObfuscate(property) : property.toCharArray();
        } else {
            return null;
        }
    }

    public String getTrustStoreType(String str) {
        return getProperty(str, TRUSTSTORETYPE, null);
    }

    public String getTrustStoreProvider(String str) {
        return getProperty(str, TRUSTSTOREPROVIDER, null);
    }

    public String getTrustManager(String str) {
        return getProperty(str, TRUSTSTOREMGR, SYSTRUSTMGRALGO);
    }

    public String[] getEnabledCipherSuites(String str) {
        return unpackCipherSuites(getProperty(str, CIPHERSUITES, null));
    }

    public boolean getClientAuthentication(String str) {
        String property = getProperty(str, CLIENTAUTH, null);
        return property != null ? Boolean.valueOf(property).booleanValue() : false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private javax.net.ssl.SSLContext getSSLContext(java.lang.String r14) throws org.eclipse.paho.client.mqttv3.MqttSecurityException {
        throw new UnsupportedOperationException("Method not decompiled: org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory.getSSLContext(java.lang.String):javax.net.ssl.SSLContext");
        /*
        this = this;
        r2 = 0;
        r7 = 2;
        r9 = 1;
        r8 = 0;
        r0 = r13.getSSLProtocol(r14);
        if (r0 != 0) goto L_0x000d;
    L_0x000a:
        r0 = "TLS";
    L_0x000d:
        r1 = r13.logger;
        if (r1 == 0) goto L_0x0028;
    L_0x0011:
        r3 = r13.logger;
        r4 = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
        r5 = "getSSLContext";
        r6 = "12000";
        r7 = new java.lang.Object[r7];
        if (r14 == 0) goto L_0x023e;
    L_0x0020:
        r1 = r14;
    L_0x0021:
        r7[r8] = r1;
        r7[r9] = r0;
        r3.fine(r4, r5, r6, r7);
    L_0x0028:
        r1 = r13.getJSSEProvider(r14);
        if (r1 != 0) goto L_0x0243;
    L_0x002e:
        r0 = javax.net.ssl.SSLContext.getInstance(r0);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r6 = r0;
    L_0x0033:
        r0 = r13.logger;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        if (r0 == 0) goto L_0x0059;
    L_0x0037:
        r1 = r13.logger;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r3 = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
        r4 = "getSSLContext";
        r5 = "12001";
        r0 = 2;
        r7 = new java.lang.Object[r0];	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r8 = 0;
        if (r14 == 0) goto L_0x024a;
    L_0x0048:
        r0 = r14;
    L_0x0049:
        r7[r8] = r0;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r0 = 1;
        r8 = r6.getProvider();	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r8 = r8.getName();	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r7[r0] = r8;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r1.fine(r3, r4, r5, r7);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
    L_0x0059:
        r0 = "com.ibm.ssl.keyStore";
        r1 = 0;
        r4 = r13.getProperty(r14, r0, r1);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        if (r4 != 0) goto L_0x006d;
    L_0x0063:
        r0 = "com.ibm.ssl.keyStore";
        r1 = "javax.net.ssl.keyStore";
        r4 = r13.getProperty(r14, r0, r1);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
    L_0x006d:
        r0 = r13.logger;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        if (r0 == 0) goto L_0x008e;
    L_0x0071:
        r1 = r13.logger;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r3 = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
        r5 = "getSSLContext";
        r7 = "12004";
        r0 = 2;
        r8 = new java.lang.Object[r0];	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r9 = 0;
        if (r14 == 0) goto L_0x024f;
    L_0x0082:
        r0 = r14;
    L_0x0083:
        r8[r9] = r0;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r9 = 1;
        if (r4 == 0) goto L_0x0254;
    L_0x0088:
        r0 = r4;
    L_0x0089:
        r8[r9] = r0;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r1.fine(r3, r5, r7, r8);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
    L_0x008e:
        r5 = r13.getKeyStorePassword(r14);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r0 = r13.logger;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        if (r0 == 0) goto L_0x00b6;
    L_0x0096:
        r1 = r13.logger;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r3 = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
        r7 = "getSSLContext";
        r8 = "12005";
        r0 = 2;
        r9 = new java.lang.Object[r0];	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r10 = 0;
        if (r14 == 0) goto L_0x0259;
    L_0x00a7:
        r0 = r14;
    L_0x00a8:
        r9[r10] = r0;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r10 = 1;
        if (r5 == 0) goto L_0x025e;
    L_0x00ad:
        r0 = obfuscate(r5);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
    L_0x00b1:
        r9[r10] = r0;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r1.fine(r3, r7, r8, r9);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
    L_0x00b6:
        r3 = r13.getKeyStoreType(r14);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        if (r3 != 0) goto L_0x00c0;
    L_0x00bc:
        r3 = java.security.KeyStore.getDefaultType();	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
    L_0x00c0:
        r0 = r13.logger;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        if (r0 == 0) goto L_0x00e1;
    L_0x00c4:
        r1 = r13.logger;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r7 = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
        r8 = "getSSLContext";
        r9 = "12006";
        r0 = 2;
        r10 = new java.lang.Object[r0];	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r11 = 0;
        if (r14 == 0) goto L_0x0263;
    L_0x00d5:
        r0 = r14;
    L_0x00d6:
        r10[r11] = r0;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r11 = 1;
        if (r3 == 0) goto L_0x0268;
    L_0x00db:
        r0 = r3;
    L_0x00dc:
        r10[r11] = r0;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r1.fine(r7, r8, r9, r10);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
    L_0x00e1:
        r1 = javax.net.ssl.KeyManagerFactory.getDefaultAlgorithm();	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r7 = r13.getKeyStoreProvider(r14);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r0 = r13.getKeyManager(r14);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        if (r0 == 0) goto L_0x0314;
    L_0x00ef:
        if (r4 == 0) goto L_0x0311;
    L_0x00f1:
        if (r3 == 0) goto L_0x0311;
    L_0x00f3:
        if (r0 == 0) goto L_0x0311;
    L_0x00f5:
        r8 = java.security.KeyStore.getInstance(r3);	 Catch:{ KeyStoreException -> 0x0283, CertificateException -> 0x0291, FileNotFoundException -> 0x029f, IOException -> 0x02ad, UnrecoverableKeyException -> 0x02b4 }
        r1 = new java.io.FileInputStream;	 Catch:{ KeyStoreException -> 0x0283, CertificateException -> 0x0291, FileNotFoundException -> 0x029f, IOException -> 0x02ad, UnrecoverableKeyException -> 0x02b4 }
        r1.<init>(r4);	 Catch:{ KeyStoreException -> 0x0283, CertificateException -> 0x0291, FileNotFoundException -> 0x029f, IOException -> 0x02ad, UnrecoverableKeyException -> 0x02b4 }
        r8.load(r1, r5);	 Catch:{ KeyStoreException -> 0x0283, CertificateException -> 0x0291, FileNotFoundException -> 0x029f, IOException -> 0x02ad, UnrecoverableKeyException -> 0x02b4 }
        if (r7 == 0) goto L_0x026d;
    L_0x0103:
        r1 = javax.net.ssl.KeyManagerFactory.getInstance(r0, r7);	 Catch:{ KeyStoreException -> 0x0283, CertificateException -> 0x0291, FileNotFoundException -> 0x029f, IOException -> 0x02ad, UnrecoverableKeyException -> 0x02b4 }
        r3 = r1;
    L_0x0108:
        r1 = r13.logger;	 Catch:{ KeyStoreException -> 0x0283, CertificateException -> 0x0291, FileNotFoundException -> 0x029f, IOException -> 0x02ad, UnrecoverableKeyException -> 0x02b4 }
        if (r1 == 0) goto L_0x014a;
    L_0x010c:
        r4 = r13.logger;	 Catch:{ KeyStoreException -> 0x0283, CertificateException -> 0x0291, FileNotFoundException -> 0x029f, IOException -> 0x02ad, UnrecoverableKeyException -> 0x02b4 }
        r7 = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
        r9 = "getSSLContext";
        r10 = "12010";
        r1 = 2;
        r11 = new java.lang.Object[r1];	 Catch:{ KeyStoreException -> 0x0283, CertificateException -> 0x0291, FileNotFoundException -> 0x029f, IOException -> 0x02ad, UnrecoverableKeyException -> 0x02b4 }
        r12 = 0;
        if (r14 == 0) goto L_0x0274;
    L_0x011d:
        r1 = r14;
    L_0x011e:
        r11[r12] = r1;	 Catch:{ KeyStoreException -> 0x0283, CertificateException -> 0x0291, FileNotFoundException -> 0x029f, IOException -> 0x02ad, UnrecoverableKeyException -> 0x02b4 }
        r1 = 1;
        if (r0 == 0) goto L_0x0279;
    L_0x0123:
        r11[r1] = r0;	 Catch:{ KeyStoreException -> 0x0283, CertificateException -> 0x0291, FileNotFoundException -> 0x029f, IOException -> 0x02ad, UnrecoverableKeyException -> 0x02b4 }
        r4.fine(r7, r9, r10, r11);	 Catch:{ KeyStoreException -> 0x0283, CertificateException -> 0x0291, FileNotFoundException -> 0x029f, IOException -> 0x02ad, UnrecoverableKeyException -> 0x02b4 }
        r1 = r13.logger;	 Catch:{ KeyStoreException -> 0x0283, CertificateException -> 0x0291, FileNotFoundException -> 0x029f, IOException -> 0x02ad, UnrecoverableKeyException -> 0x02b4 }
        r4 = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
        r7 = "getSSLContext";
        r9 = "12009";
        r0 = 2;
        r10 = new java.lang.Object[r0];	 Catch:{ KeyStoreException -> 0x0283, CertificateException -> 0x0291, FileNotFoundException -> 0x029f, IOException -> 0x02ad, UnrecoverableKeyException -> 0x02b4 }
        r11 = 0;
        if (r14 == 0) goto L_0x027e;
    L_0x0139:
        r0 = r14;
    L_0x013a:
        r10[r11] = r0;	 Catch:{ KeyStoreException -> 0x0283, CertificateException -> 0x0291, FileNotFoundException -> 0x029f, IOException -> 0x02ad, UnrecoverableKeyException -> 0x02b4 }
        r0 = 1;
        r11 = r3.getProvider();	 Catch:{ KeyStoreException -> 0x0283, CertificateException -> 0x0291, FileNotFoundException -> 0x029f, IOException -> 0x02ad, UnrecoverableKeyException -> 0x02b4 }
        r11 = r11.getName();	 Catch:{ KeyStoreException -> 0x0283, CertificateException -> 0x0291, FileNotFoundException -> 0x029f, IOException -> 0x02ad, UnrecoverableKeyException -> 0x02b4 }
        r10[r0] = r11;	 Catch:{ KeyStoreException -> 0x0283, CertificateException -> 0x0291, FileNotFoundException -> 0x029f, IOException -> 0x02ad, UnrecoverableKeyException -> 0x02b4 }
        r1.fine(r4, r7, r9, r10);	 Catch:{ KeyStoreException -> 0x0283, CertificateException -> 0x0291, FileNotFoundException -> 0x029f, IOException -> 0x02ad, UnrecoverableKeyException -> 0x02b4 }
    L_0x014a:
        r3.init(r8, r5);	 Catch:{ KeyStoreException -> 0x0283, CertificateException -> 0x0291, FileNotFoundException -> 0x029f, IOException -> 0x02ad, UnrecoverableKeyException -> 0x02b4 }
        r0 = r3.getKeyManagers();	 Catch:{ KeyStoreException -> 0x0283, CertificateException -> 0x0291, FileNotFoundException -> 0x029f, IOException -> 0x02ad, UnrecoverableKeyException -> 0x02b4 }
        r5 = r0;
    L_0x0152:
        r4 = r13.getTrustStore(r14);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r0 = r13.logger;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        if (r0 == 0) goto L_0x0177;
    L_0x015a:
        r1 = r13.logger;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r3 = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
        r7 = "getSSLContext";
        r8 = "12011";
        r0 = 2;
        r9 = new java.lang.Object[r0];	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r10 = 0;
        if (r14 == 0) goto L_0x02bb;
    L_0x016b:
        r0 = r14;
    L_0x016c:
        r9[r10] = r0;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r10 = 1;
        if (r4 == 0) goto L_0x02c0;
    L_0x0171:
        r0 = r4;
    L_0x0172:
        r9[r10] = r0;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r1.fine(r3, r7, r8, r9);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
    L_0x0177:
        r7 = r13.getTrustStorePassword(r14);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r0 = r13.logger;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        if (r0 == 0) goto L_0x019f;
    L_0x017f:
        r1 = r13.logger;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r3 = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
        r8 = "getSSLContext";
        r9 = "12012";
        r0 = 2;
        r10 = new java.lang.Object[r0];	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r11 = 0;
        if (r14 == 0) goto L_0x02c5;
    L_0x0190:
        r0 = r14;
    L_0x0191:
        r10[r11] = r0;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r11 = 1;
        if (r7 == 0) goto L_0x02ca;
    L_0x0196:
        r0 = obfuscate(r7);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
    L_0x019a:
        r10[r11] = r0;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r1.fine(r3, r8, r9, r10);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
    L_0x019f:
        r3 = r13.getTrustStoreType(r14);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        if (r3 != 0) goto L_0x01a9;
    L_0x01a5:
        r3 = java.security.KeyStore.getDefaultType();	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
    L_0x01a9:
        r0 = r13.logger;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        if (r0 == 0) goto L_0x01ca;
    L_0x01ad:
        r1 = r13.logger;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r8 = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
        r9 = "getSSLContext";
        r10 = "12013";
        r0 = 2;
        r11 = new java.lang.Object[r0];	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r12 = 0;
        if (r14 == 0) goto L_0x02cf;
    L_0x01be:
        r0 = r14;
    L_0x01bf:
        r11[r12] = r0;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r12 = 1;
        if (r3 == 0) goto L_0x02d4;
    L_0x01c4:
        r0 = r3;
    L_0x01c5:
        r11[r12] = r0;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r1.fine(r8, r9, r10, r11);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
    L_0x01ca:
        r1 = javax.net.ssl.TrustManagerFactory.getDefaultAlgorithm();	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r8 = r13.getTrustStoreProvider(r14);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r0 = r13.getTrustManager(r14);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        if (r0 == 0) goto L_0x030e;
    L_0x01d8:
        if (r4 == 0) goto L_0x030b;
    L_0x01da:
        if (r3 == 0) goto L_0x030b;
    L_0x01dc:
        if (r0 == 0) goto L_0x030b;
    L_0x01de:
        r3 = java.security.KeyStore.getInstance(r3);	 Catch:{ KeyStoreException -> 0x02ef, CertificateException -> 0x02f6, FileNotFoundException -> 0x02fd, IOException -> 0x0304 }
        r1 = new java.io.FileInputStream;	 Catch:{ KeyStoreException -> 0x02ef, CertificateException -> 0x02f6, FileNotFoundException -> 0x02fd, IOException -> 0x0304 }
        r1.<init>(r4);	 Catch:{ KeyStoreException -> 0x02ef, CertificateException -> 0x02f6, FileNotFoundException -> 0x02fd, IOException -> 0x0304 }
        r3.load(r1, r7);	 Catch:{ KeyStoreException -> 0x02ef, CertificateException -> 0x02f6, FileNotFoundException -> 0x02fd, IOException -> 0x0304 }
        if (r8 == 0) goto L_0x02d9;
    L_0x01ec:
        r1 = javax.net.ssl.TrustManagerFactory.getInstance(r0, r8);	 Catch:{ KeyStoreException -> 0x02ef, CertificateException -> 0x02f6, FileNotFoundException -> 0x02fd, IOException -> 0x0304 }
        r2 = r1;
    L_0x01f1:
        r1 = r13.logger;	 Catch:{ KeyStoreException -> 0x02ef, CertificateException -> 0x02f6, FileNotFoundException -> 0x02fd, IOException -> 0x0304 }
        if (r1 == 0) goto L_0x0232;
    L_0x01f5:
        r4 = r13.logger;	 Catch:{ KeyStoreException -> 0x02ef, CertificateException -> 0x02f6, FileNotFoundException -> 0x02fd, IOException -> 0x0304 }
        r7 = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
        r8 = "getSSLContext";
        r9 = "12017";
        r1 = 2;
        r10 = new java.lang.Object[r1];	 Catch:{ KeyStoreException -> 0x02ef, CertificateException -> 0x02f6, FileNotFoundException -> 0x02fd, IOException -> 0x0304 }
        r11 = 0;
        if (r14 == 0) goto L_0x02e0;
    L_0x0206:
        r1 = r14;
    L_0x0207:
        r10[r11] = r1;	 Catch:{ KeyStoreException -> 0x02ef, CertificateException -> 0x02f6, FileNotFoundException -> 0x02fd, IOException -> 0x0304 }
        r1 = 1;
        if (r0 == 0) goto L_0x02e5;
    L_0x020c:
        r10[r1] = r0;	 Catch:{ KeyStoreException -> 0x02ef, CertificateException -> 0x02f6, FileNotFoundException -> 0x02fd, IOException -> 0x0304 }
        r4.fine(r7, r8, r9, r10);	 Catch:{ KeyStoreException -> 0x02ef, CertificateException -> 0x02f6, FileNotFoundException -> 0x02fd, IOException -> 0x0304 }
        r0 = r13.logger;	 Catch:{ KeyStoreException -> 0x02ef, CertificateException -> 0x02f6, FileNotFoundException -> 0x02fd, IOException -> 0x0304 }
        r1 = "org.eclipse.paho.client.mqttv3.internal.security.SSLSocketFactoryFactory";
        r4 = "getSSLContext";
        r7 = "12016";
        r8 = 2;
        r8 = new java.lang.Object[r8];	 Catch:{ KeyStoreException -> 0x02ef, CertificateException -> 0x02f6, FileNotFoundException -> 0x02fd, IOException -> 0x0304 }
        r9 = 0;
        if (r14 == 0) goto L_0x02ea;
    L_0x0222:
        r8[r9] = r14;	 Catch:{ KeyStoreException -> 0x02ef, CertificateException -> 0x02f6, FileNotFoundException -> 0x02fd, IOException -> 0x0304 }
        r9 = 1;
        r10 = r2.getProvider();	 Catch:{ KeyStoreException -> 0x02ef, CertificateException -> 0x02f6, FileNotFoundException -> 0x02fd, IOException -> 0x0304 }
        r10 = r10.getName();	 Catch:{ KeyStoreException -> 0x02ef, CertificateException -> 0x02f6, FileNotFoundException -> 0x02fd, IOException -> 0x0304 }
        r8[r9] = r10;	 Catch:{ KeyStoreException -> 0x02ef, CertificateException -> 0x02f6, FileNotFoundException -> 0x02fd, IOException -> 0x0304 }
        r0.fine(r1, r4, r7, r8);	 Catch:{ KeyStoreException -> 0x02ef, CertificateException -> 0x02f6, FileNotFoundException -> 0x02fd, IOException -> 0x0304 }
    L_0x0232:
        r2.init(r3);	 Catch:{ KeyStoreException -> 0x02ef, CertificateException -> 0x02f6, FileNotFoundException -> 0x02fd, IOException -> 0x0304 }
        r0 = r2.getTrustManagers();	 Catch:{ KeyStoreException -> 0x02ef, CertificateException -> 0x02f6, FileNotFoundException -> 0x02fd, IOException -> 0x0304 }
    L_0x0239:
        r1 = 0;
        r6.init(r5, r0, r1);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        return r6;
    L_0x023e:
        r1 = "null (broker defaults)";
        goto L_0x0021;
    L_0x0243:
        r0 = javax.net.ssl.SSLContext.getInstance(r0, r1);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r6 = r0;
        goto L_0x0033;
    L_0x024a:
        r0 = "null (broker defaults)";
        goto L_0x0049;
    L_0x024f:
        r0 = "null (broker defaults)";
        goto L_0x0083;
    L_0x0254:
        r0 = "null";
        goto L_0x0089;
    L_0x0259:
        r0 = "null (broker defaults)";
        goto L_0x00a8;
    L_0x025e:
        r0 = "null";
        goto L_0x00b1;
    L_0x0263:
        r0 = "null (broker defaults)";
        goto L_0x00d6;
    L_0x0268:
        r0 = "null";
        goto L_0x00dc;
    L_0x026d:
        r1 = javax.net.ssl.KeyManagerFactory.getInstance(r0);	 Catch:{ KeyStoreException -> 0x0283, CertificateException -> 0x0291, FileNotFoundException -> 0x029f, IOException -> 0x02ad, UnrecoverableKeyException -> 0x02b4 }
        r3 = r1;
        goto L_0x0108;
    L_0x0274:
        r1 = "null (broker defaults)";
        goto L_0x011e;
    L_0x0279:
        r0 = "null";
        goto L_0x0123;
    L_0x027e:
        r0 = "null (broker defaults)";
        goto L_0x013a;
    L_0x0283:
        r0 = move-exception;
        r1 = new org.eclipse.paho.client.mqttv3.MqttSecurityException;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r1.<init>(r0);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        throw r1;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
    L_0x028a:
        r0 = move-exception;
        r1 = new org.eclipse.paho.client.mqttv3.MqttSecurityException;
        r1.<init>(r0);
        throw r1;
    L_0x0291:
        r0 = move-exception;
        r1 = new org.eclipse.paho.client.mqttv3.MqttSecurityException;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r1.<init>(r0);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        throw r1;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
    L_0x0298:
        r0 = move-exception;
        r1 = new org.eclipse.paho.client.mqttv3.MqttSecurityException;
        r1.<init>(r0);
        throw r1;
    L_0x029f:
        r0 = move-exception;
        r1 = new org.eclipse.paho.client.mqttv3.MqttSecurityException;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r1.<init>(r0);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        throw r1;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
    L_0x02a6:
        r0 = move-exception;
        r1 = new org.eclipse.paho.client.mqttv3.MqttSecurityException;
        r1.<init>(r0);
        throw r1;
    L_0x02ad:
        r0 = move-exception;
        r1 = new org.eclipse.paho.client.mqttv3.MqttSecurityException;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r1.<init>(r0);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        throw r1;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
    L_0x02b4:
        r0 = move-exception;
        r1 = new org.eclipse.paho.client.mqttv3.MqttSecurityException;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r1.<init>(r0);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        throw r1;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
    L_0x02bb:
        r0 = "null (broker defaults)";
        goto L_0x016c;
    L_0x02c0:
        r0 = "null";
        goto L_0x0172;
    L_0x02c5:
        r0 = "null (broker defaults)";
        goto L_0x0191;
    L_0x02ca:
        r0 = "null";
        goto L_0x019a;
    L_0x02cf:
        r0 = "null (broker defaults)";
        goto L_0x01bf;
    L_0x02d4:
        r0 = "null";
        goto L_0x01c5;
    L_0x02d9:
        r1 = javax.net.ssl.TrustManagerFactory.getInstance(r0);	 Catch:{ KeyStoreException -> 0x02ef, CertificateException -> 0x02f6, FileNotFoundException -> 0x02fd, IOException -> 0x0304 }
        r2 = r1;
        goto L_0x01f1;
    L_0x02e0:
        r1 = "null (broker defaults)";
        goto L_0x0207;
    L_0x02e5:
        r0 = "null";
        goto L_0x020c;
    L_0x02ea:
        r14 = "null (broker defaults)";
        goto L_0x0222;
    L_0x02ef:
        r0 = move-exception;
        r1 = new org.eclipse.paho.client.mqttv3.MqttSecurityException;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r1.<init>(r0);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        throw r1;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
    L_0x02f6:
        r0 = move-exception;
        r1 = new org.eclipse.paho.client.mqttv3.MqttSecurityException;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r1.<init>(r0);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        throw r1;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
    L_0x02fd:
        r0 = move-exception;
        r1 = new org.eclipse.paho.client.mqttv3.MqttSecurityException;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r1.<init>(r0);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        throw r1;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
    L_0x0304:
        r0 = move-exception;
        r1 = new org.eclipse.paho.client.mqttv3.MqttSecurityException;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        r1.<init>(r0);	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
        throw r1;	 Catch:{ NoSuchAlgorithmException -> 0x028a, NoSuchProviderException -> 0x0298, KeyManagementException -> 0x02a6 }
    L_0x030b:
        r0 = r2;
        goto L_0x0239;
    L_0x030e:
        r0 = r1;
        goto L_0x01d8;
    L_0x0311:
        r5 = r2;
        goto L_0x0152;
    L_0x0314:
        r0 = r1;
        goto L_0x00ef;
        */
    }

    public SSLSocketFactory createSocketFactory(String str) throws MqttSecurityException {
        SSLContext sSLContext = getSSLContext(str);
        if (this.logger != null) {
            Logger logger = this.logger;
            String str2 = CLASS_NAME;
            String str3 = "createSocketFactory";
            String str4 = "12020";
            Object[] objArr = new Object[2];
            objArr[0] = str != null ? str : "null (broker defaults)";
            objArr[1] = getEnabledCipherSuites(str) != null ? getProperty(str, CIPHERSUITES, null) : "null (using platform-enabled cipher suites)";
            logger.fine(str2, str3, str4, objArr);
        }
        return sSLContext.getSocketFactory();
    }
}
