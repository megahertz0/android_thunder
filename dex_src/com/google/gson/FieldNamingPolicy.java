package com.google.gson;

import com.umeng.socialize.common.SocializeConstants;
import java.lang.reflect.Field;
import java.util.Locale;

public enum FieldNamingPolicy implements FieldNamingStrategy {
    IDENTITY {
        public final String translateName(Field field) {
            return field.getName();
        }
    },
    UPPER_CAMEL_CASE {
        public final String translateName(Field field) {
            return FieldNamingPolicy.access$100(field.getName());
        }
    },
    UPPER_CAMEL_CASE_WITH_SPACES {
        public final String translateName(Field field) {
            return FieldNamingPolicy.access$100(FieldNamingPolicy.access$200(field.getName(), " "));
        }
    },
    LOWER_CASE_WITH_UNDERSCORES {
        public final String translateName(Field field) {
            return FieldNamingPolicy.access$200(field.getName(), "_").toLowerCase(Locale.ENGLISH);
        }
    },
    LOWER_CASE_WITH_DASHES {
        public final String translateName(Field field) {
            return FieldNamingPolicy.access$200(field.getName(), SocializeConstants.OP_DIVIDER_MINUS).toLowerCase(Locale.ENGLISH);
        }
    };

    private static String separateCamelCase(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt) && stringBuilder.length() != 0) {
                stringBuilder.append(str2);
            }
            stringBuilder.append(charAt);
        }
        return stringBuilder.toString();
    }

    private static String upperCaseFirstLetter(String str) {
        int i = 0;
        StringBuilder stringBuilder = new StringBuilder();
        char charAt = str.charAt(0);
        while (i < str.length() - 1 && !Character.isLetter(charAt)) {
            stringBuilder.append(charAt);
            i++;
            charAt = str.charAt(i);
        }
        if (i == str.length()) {
            return stringBuilder.toString();
        }
        return !Character.isUpperCase(charAt) ? stringBuilder.append(modifyString(Character.toUpperCase(charAt), str, i + 1)).toString() : str;
    }

    private static String modifyString(char c, String str, int i) {
        return i < str.length() ? c + str.substring(i) : String.valueOf(c);
    }
}
