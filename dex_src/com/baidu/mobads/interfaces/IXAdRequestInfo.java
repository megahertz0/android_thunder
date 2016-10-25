package com.baidu.mobads.interfaces;

public interface IXAdRequestInfo {
    public static final String ACT = "act";
    public static final String AD_COUNT = "n";
    public static final String AD_LOCAL_PIC = "local_pic";
    public static final String AD_TYPE = "at";
    public static final String APPID = "appid";
    public static final String BDR = "bdr";
    public static final String BRAND = "brd";
    public static final String CELL_ID = "cid";
    public static final String COST_NAME = "q";
    public static final String CS = "cs";
    public static final String DENSITY = "den";
    public static final String FET = "fet";
    public static final String GPS = "g";
    public static final String HEIGHT = "h";
    public static final String IMSI = "im";
    public static final String MAX_CONTENT_LENGTH = "cm";
    public static final String MAX_TITLE_LENGTH = "tm";
    public static final String NETWORK_OPERATOR = "nop";
    public static final String OS = "os";
    public static final String OSV = "osv";
    public static final String PACKAGE = "pk";
    public static final String PHONE_TYPE = "tp";
    public static final String P_VER = "p_ver";
    public static final String QUERY_HEIGHT = "lh";
    public static final String QUERY_WIDTH = "lw";
    public static final String SCREEN_HEIGHT = "sh";
    public static final String SCREEN_WIDTH = "sw";
    public static final String SDK_VALID = "trftp";
    public static final String SN = "sn";
    public static final String TEST_MODE = "md";
    public static final String V = "v";
    public static final String WIDTH = "w";
    public static final String WIFI = "wi";

    String getAct();

    int getAp();

    String getApid();

    int getApt();

    int getAt();

    int getH();

    int getN();

    String getProd();

    long getSes();

    String getSex();

    String getUk();

    int getW();

    String getZip();

    boolean isCanClick();
}
