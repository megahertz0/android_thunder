package com.google.zxing.client.result;

public enum ParsedResultType {
    ADDRESSBOOK,
    EMAIL_ADDRESS,
    PRODUCT,
    URI,
    TEXT,
    GEO,
    TEL,
    SMS,
    CALENDAR,
    WIFI,
    ISBN,
    VIN;

    static {
        ADDRESSBOOK = new ParsedResultType("ADDRESSBOOK", 0);
        EMAIL_ADDRESS = new ParsedResultType("EMAIL_ADDRESS", 1);
        PRODUCT = new ParsedResultType("PRODUCT", 2);
        URI = new ParsedResultType("URI", 3);
        TEXT = new ParsedResultType("TEXT", 4);
        GEO = new ParsedResultType("GEO", 5);
        TEL = new ParsedResultType("TEL", 6);
        SMS = new ParsedResultType("SMS", 7);
        CALENDAR = new ParsedResultType("CALENDAR", 8);
        WIFI = new ParsedResultType("WIFI", 9);
        ISBN = new ParsedResultType("ISBN", 10);
        VIN = new ParsedResultType("VIN", 11);
        a = new ParsedResultType[]{ADDRESSBOOK, EMAIL_ADDRESS, PRODUCT, URI, TEXT, GEO, TEL, SMS, CALENDAR, WIFI, ISBN, VIN};
    }
}
