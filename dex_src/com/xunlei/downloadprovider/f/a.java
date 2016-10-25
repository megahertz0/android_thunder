package com.xunlei.downloadprovider.f;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.RSAPrivateKeySpec;

// compiled from: RSAHelper.java
public class a {
    public static String a;
    public static String b;
    public static String c;
    private static final String d;
    private static String e;
    private static String f;

    static {
        a = "89588253354830446636067465982233477200653781439201713123473720856401923696337632379398882518561519595067483455996944836557927638184152927007834884748583602683727907592690248376453374810583938874225189499061796177801316598179890411232614695736553565692497234794017545674873197360807039487593987168542695257929";
        b = "65537";
        c = "16101744606352866791678573657700659596052625106006637149723010787304244308087965146359147308942065387648197012804797491948301412181990895634912905800594565286916472278944833599109599584365038354019215238650610301321685578925510532094370951602391054407867385238051916427056499032831075625467154021291991361933";
        d = a.class.getName();
        e = "RSA";
        f = "RSA/ECB/NoPadding";
    }

    public static PrivateKey a(String str, String str2) throws Exception {
        return KeyFactory.getInstance("RSA").generatePrivate(new RSAPrivateKeySpec(new BigInteger(str), new BigInteger(str2)));
    }
}
