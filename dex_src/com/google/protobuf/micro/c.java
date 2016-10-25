package com.google.protobuf.micro;

import java.io.IOException;

public class c extends IOException {
    public c(String str) {
        super(str);
    }

    static c a() {
        return new c("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
    }

    static c b() {
        return new c("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static c c() {
        return new c("CodedInputStream encountered a malformed varint.");
    }

    static c d() {
        return new c("Protocol message contained an invalid tag (zero).");
    }

    static c e() {
        return new c("Protocol message end-group tag did not match expected tag.");
    }

    static c f() {
        return new c("Protocol message tag had invalid wire type.");
    }

    static c g() {
        return new c("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
    }
}
