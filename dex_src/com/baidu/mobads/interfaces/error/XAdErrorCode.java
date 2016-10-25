package com.baidu.mobads.interfaces.error;

public enum XAdErrorCode {
    INTERFACE_USE_PROBLEM(1010001, "\u63a5\u53e3\u4f7f\u7528\u95ee\u9898"),
    WEBVIEW_LOAD_ERROR(1010002, "web\u8f7d\u5165\u5f02\u5e38"),
    VIEWKIT_PUT_PROBLEM(1010003, "\u63a7\u4ef6\u6446\u653e\u95ee\u9898"),
    VIEWKIT_TOO_SMALL(1010004, "\u63a7\u4ef6\u8fc7\u5c0f"),
    NETWORK_UNCONNECT(1020001, "\u7f51\u7edc\u8fde\u63a5\u95ee\u9898"),
    PERMISSION_PROBLEM(1030002, "\u6743\u9650\u8bbe\u7f6e\u95ee\u9898"),
    SETTINGS_ERROR(1030002, "\u8bbe\u7f6e\u95ee\u9898"),
    REQUEST_PARAM_ERROR(1040001, "\u8bf7\u6c42\u53c2\u6570\u95ee\u9898"),
    REQUEST_URL_TOO_LONG(1040002, "\u8bf7\u6c42\u4e32\u8fc7\u957f"),
    REQUEST_TIMEOUT(1040003, "\u8bf7\u6c42\u8d85\u65f6"),
    REQUEST_STATUS_CODE_ERROR(1040004, "\u72b6\u6001\u7801\u5f02\u5e38"),
    RESPONSE_PARSE_FAILED(3010001, "\u6e05\u5355\u89e3\u6790\u5931\u8d25"),
    RESPONSE_FIELD_LESS(3010002, "\u6e05\u5355\u7f3a\u5c11\u5b57\u6bb5"),
    RESPONSE_MTYPE_UNSUPPORT(3010003, "\u7269\u6599\u7c7b\u578b\u4e0d\u652f\u6301"),
    RESPONSE_ATYPE_UNSUPPORT(3010004, "\u8df3\u8f6c\u7c7b\u578b\u4e0d\u652f\u6301"),
    RESPONSE_HTML_ERROR(3010005, "\u5e7f\u544a\u670d\u52a1\u5668\u8fd4\u56de\u7684html\u5185\u5bb9\u9519\u8bef, AD-SDK\u65e0\u6cd5\u6e32\u67d3\u8be5\u5e7f\u544a"),
    FILTER_APP_INSTALLED(3020001, "\u63a8\u5e7f\u5e94\u7528\u5df2\u88ab\u5b89\u88c5"),
    FILTER_APP_UNINSTALLED(3020002, "\u63a8\u5e7f\u5e94\u7528\u672a\u88ab\u5b89\u88c5"),
    MCACHE_PREPARE_FAILED(3030001, "\u7f13\u5b58\u51c6\u5907\u5931\u8d25"),
    MCACHE_FETCH_FAILED(3030002, "\u7f13\u5b58\u7269\u6599\u5931\u8d25"),
    SHOW_STANDARD_UNFIT(3040001, "\u5e7f\u544a\u5c55\u73b0\u6807\u51c6\u4e0d\u8fbe\u6807"),
    SHOW_PROCESS_FAILED(3040002, "\u5e7f\u544a\u5c55\u73b0\u5931\u8d25"),
    CLICK_PARSE_FAILED(4010001, "\u70b9\u51fb\u4fe1\u606f\u89e3\u6790\u5931\u8d25"),
    CLICK_FIELD_LESS(4010002, "\u70b9\u51fb\u4fe1\u606f\u7f3a\u5931"),
    CLICK_FIELD_ERROR(4010003, "\u70b9\u51fb\u4fe1\u606f\u6709\u8bef"),
    CLICK_JUMP_FAILED(4010004, "\u70b9\u51fb\u8df3\u8f6c\u5931\u8d25"),
    LOADING_LP_ERROR(4020001, "\u6253\u5f00LP\u95ee\u9898"),
    LOADING_DL_ERROR(4020002, "\u4e0b\u8f7d\u5668\u95ee\u9898"),
    MONITOR_START_FAILED(4030001, "\u76d1\u63a7\u542f\u52a8\u5931\u8d25");
    public static final String ERROR_CODE_MESSAGE = "msg";
    private int a;
    private String b;

    static {
        String str = "\u63a5\u53e3\u4f7f\u7528\u95ee\u9898";
        INTERFACE_USE_PROBLEM = new XAdErrorCode("INTERFACE_USE_PROBLEM", 0, 1010001, "\u63a5\u53e3\u4f7f\u7528\u95ee\u9898");
        str = "web\u8f7d\u5165\u5f02\u5e38";
        WEBVIEW_LOAD_ERROR = new XAdErrorCode("WEBVIEW_LOAD_ERROR", 1, 1010002, "web\u8f7d\u5165\u5f02\u5e38");
        str = "\u63a7\u4ef6\u6446\u653e\u95ee\u9898";
        VIEWKIT_PUT_PROBLEM = new XAdErrorCode("VIEWKIT_PUT_PROBLEM", 2, 1010003, "\u63a7\u4ef6\u6446\u653e\u95ee\u9898");
        str = "\u63a7\u4ef6\u8fc7\u5c0f";
        VIEWKIT_TOO_SMALL = new XAdErrorCode("VIEWKIT_TOO_SMALL", 3, 1010004, "\u63a7\u4ef6\u8fc7\u5c0f");
        str = "\u7f51\u7edc\u8fde\u63a5\u95ee\u9898";
        NETWORK_UNCONNECT = new XAdErrorCode("NETWORK_UNCONNECT", 4, 1020001, "\u7f51\u7edc\u8fde\u63a5\u95ee\u9898");
        String str2 = "\u6743\u9650\u8bbe\u7f6e\u95ee\u9898";
        PERMISSION_PROBLEM = new XAdErrorCode("PERMISSION_PROBLEM", 5, 1030002, "\u6743\u9650\u8bbe\u7f6e\u95ee\u9898");
        str2 = "\u8bbe\u7f6e\u95ee\u9898";
        SETTINGS_ERROR = new XAdErrorCode("SETTINGS_ERROR", 6, 1030002, "\u8bbe\u7f6e\u95ee\u9898");
        str2 = "\u8bf7\u6c42\u53c2\u6570\u95ee\u9898";
        REQUEST_PARAM_ERROR = new XAdErrorCode("REQUEST_PARAM_ERROR", 7, 1040001, "\u8bf7\u6c42\u53c2\u6570\u95ee\u9898");
        str2 = "\u8bf7\u6c42\u4e32\u8fc7\u957f";
        REQUEST_URL_TOO_LONG = new XAdErrorCode("REQUEST_URL_TOO_LONG", 8, 1040002, "\u8bf7\u6c42\u4e32\u8fc7\u957f");
        str2 = "\u8bf7\u6c42\u8d85\u65f6";
        REQUEST_TIMEOUT = new XAdErrorCode("REQUEST_TIMEOUT", 9, 1040003, "\u8bf7\u6c42\u8d85\u65f6");
        str2 = "\u72b6\u6001\u7801\u5f02\u5e38";
        REQUEST_STATUS_CODE_ERROR = new XAdErrorCode("REQUEST_STATUS_CODE_ERROR", 10, 1040004, "\u72b6\u6001\u7801\u5f02\u5e38");
        str2 = "\u6e05\u5355\u89e3\u6790\u5931\u8d25";
        RESPONSE_PARSE_FAILED = new XAdErrorCode("RESPONSE_PARSE_FAILED", 11, 3010001, "\u6e05\u5355\u89e3\u6790\u5931\u8d25");
        str2 = "\u6e05\u5355\u7f3a\u5c11\u5b57\u6bb5";
        RESPONSE_FIELD_LESS = new XAdErrorCode("RESPONSE_FIELD_LESS", 12, 3010002, "\u6e05\u5355\u7f3a\u5c11\u5b57\u6bb5");
        str2 = "\u7269\u6599\u7c7b\u578b\u4e0d\u652f\u6301";
        RESPONSE_MTYPE_UNSUPPORT = new XAdErrorCode("RESPONSE_MTYPE_UNSUPPORT", 13, 3010003, "\u7269\u6599\u7c7b\u578b\u4e0d\u652f\u6301");
        str2 = "\u8df3\u8f6c\u7c7b\u578b\u4e0d\u652f\u6301";
        RESPONSE_ATYPE_UNSUPPORT = new XAdErrorCode("RESPONSE_ATYPE_UNSUPPORT", 14, 3010004, "\u8df3\u8f6c\u7c7b\u578b\u4e0d\u652f\u6301");
        str2 = "\u5e7f\u544a\u670d\u52a1\u5668\u8fd4\u56de\u7684html\u5185\u5bb9\u9519\u8bef, AD-SDK\u65e0\u6cd5\u6e32\u67d3\u8be5\u5e7f\u544a";
        RESPONSE_HTML_ERROR = new XAdErrorCode("RESPONSE_HTML_ERROR", 15, 3010005, "\u5e7f\u544a\u670d\u52a1\u5668\u8fd4\u56de\u7684html\u5185\u5bb9\u9519\u8bef, AD-SDK\u65e0\u6cd5\u6e32\u67d3\u8be5\u5e7f\u544a");
        str2 = "\u63a8\u5e7f\u5e94\u7528\u5df2\u88ab\u5b89\u88c5";
        FILTER_APP_INSTALLED = new XAdErrorCode("FILTER_APP_INSTALLED", 16, 3020001, "\u63a8\u5e7f\u5e94\u7528\u5df2\u88ab\u5b89\u88c5");
        str2 = "\u63a8\u5e7f\u5e94\u7528\u672a\u88ab\u5b89\u88c5";
        FILTER_APP_UNINSTALLED = new XAdErrorCode("FILTER_APP_UNINSTALLED", 17, 3020002, "\u63a8\u5e7f\u5e94\u7528\u672a\u88ab\u5b89\u88c5");
        str2 = "\u7f13\u5b58\u51c6\u5907\u5931\u8d25";
        MCACHE_PREPARE_FAILED = new XAdErrorCode("MCACHE_PREPARE_FAILED", 18, 3030001, "\u7f13\u5b58\u51c6\u5907\u5931\u8d25");
        str2 = "\u7f13\u5b58\u7269\u6599\u5931\u8d25";
        MCACHE_FETCH_FAILED = new XAdErrorCode("MCACHE_FETCH_FAILED", 19, 3030002, "\u7f13\u5b58\u7269\u6599\u5931\u8d25");
        str2 = "\u5e7f\u544a\u5c55\u73b0\u6807\u51c6\u4e0d\u8fbe\u6807";
        SHOW_STANDARD_UNFIT = new XAdErrorCode("SHOW_STANDARD_UNFIT", 20, 3040001, "\u5e7f\u544a\u5c55\u73b0\u6807\u51c6\u4e0d\u8fbe\u6807");
        str2 = "\u5e7f\u544a\u5c55\u73b0\u5931\u8d25";
        SHOW_PROCESS_FAILED = new XAdErrorCode("SHOW_PROCESS_FAILED", 21, 3040002, "\u5e7f\u544a\u5c55\u73b0\u5931\u8d25");
        str2 = "\u70b9\u51fb\u4fe1\u606f\u89e3\u6790\u5931\u8d25";
        CLICK_PARSE_FAILED = new XAdErrorCode("CLICK_PARSE_FAILED", 22, 4010001, "\u70b9\u51fb\u4fe1\u606f\u89e3\u6790\u5931\u8d25");
        str2 = "\u70b9\u51fb\u4fe1\u606f\u7f3a\u5931";
        CLICK_FIELD_LESS = new XAdErrorCode("CLICK_FIELD_LESS", 23, 4010002, "\u70b9\u51fb\u4fe1\u606f\u7f3a\u5931");
        str2 = "\u70b9\u51fb\u4fe1\u606f\u6709\u8bef";
        CLICK_FIELD_ERROR = new XAdErrorCode("CLICK_FIELD_ERROR", 24, 4010003, "\u70b9\u51fb\u4fe1\u606f\u6709\u8bef");
        str2 = "\u70b9\u51fb\u8df3\u8f6c\u5931\u8d25";
        CLICK_JUMP_FAILED = new XAdErrorCode("CLICK_JUMP_FAILED", 25, 4010004, "\u70b9\u51fb\u8df3\u8f6c\u5931\u8d25");
        str2 = "\u6253\u5f00LP\u95ee\u9898";
        LOADING_LP_ERROR = new XAdErrorCode("LOADING_LP_ERROR", 26, 4020001, "\u6253\u5f00LP\u95ee\u9898");
        str2 = "\u4e0b\u8f7d\u5668\u95ee\u9898";
        LOADING_DL_ERROR = new XAdErrorCode("LOADING_DL_ERROR", 27, 4020002, "\u4e0b\u8f7d\u5668\u95ee\u9898");
        str2 = "\u76d1\u63a7\u542f\u52a8\u5931\u8d25";
        MONITOR_START_FAILED = new XAdErrorCode("MONITOR_START_FAILED", 28, 4030001, "\u76d1\u63a7\u542f\u52a8\u5931\u8d25");
        c = new XAdErrorCode[]{INTERFACE_USE_PROBLEM, WEBVIEW_LOAD_ERROR, VIEWKIT_PUT_PROBLEM, VIEWKIT_TOO_SMALL, NETWORK_UNCONNECT, PERMISSION_PROBLEM, SETTINGS_ERROR, REQUEST_PARAM_ERROR, REQUEST_URL_TOO_LONG, REQUEST_TIMEOUT, REQUEST_STATUS_CODE_ERROR, RESPONSE_PARSE_FAILED, RESPONSE_FIELD_LESS, RESPONSE_MTYPE_UNSUPPORT, RESPONSE_ATYPE_UNSUPPORT, RESPONSE_HTML_ERROR, FILTER_APP_INSTALLED, FILTER_APP_UNINSTALLED, MCACHE_PREPARE_FAILED, MCACHE_FETCH_FAILED, SHOW_STANDARD_UNFIT, SHOW_PROCESS_FAILED, CLICK_PARSE_FAILED, CLICK_FIELD_LESS, CLICK_FIELD_ERROR, CLICK_JUMP_FAILED, LOADING_LP_ERROR, LOADING_DL_ERROR, MONITOR_START_FAILED};
    }

    private XAdErrorCode(int i, String str) {
        this.a = i;
        this.b = str;
    }

    public final int getCode() {
        return this.a;
    }

    public final String getMessage() {
        return this.b;
    }
}
