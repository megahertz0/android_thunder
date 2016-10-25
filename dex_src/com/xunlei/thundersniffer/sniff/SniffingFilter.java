package com.xunlei.thundersniffer.sniff;

import com.xunlei.thundersniffer.sniff.SniffingResource.Category;

public interface SniffingFilter {
    boolean accept(String str, String str2, String str3, Category category, String str4);
}
