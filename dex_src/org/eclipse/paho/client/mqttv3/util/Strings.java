package org.eclipse.paho.client.mqttv3.util;

public final class Strings {
    private static final int INDEX_NOT_FOUND = -1;

    public static boolean equalsAny(CharSequence charSequence, CharSequence[] charSequenceArr) {
        Object obj;
        if (charSequence != null) {
            obj = null;
        } else if (charSequenceArr == null) {
            int i = 1;
        } else {
            obj = null;
        }
        if (charSequenceArr == null) {
            return r0;
        }
        boolean z = r0;
        i = 0;
        while (i < charSequenceArr.length) {
            if (z || charSequenceArr[i].equals(charSequence)) {
                z = true;
            } else {
                z = false;
            }
            i++;
        }
        return z;
    }

    public static boolean containsAny(CharSequence charSequence, CharSequence charSequence2) {
        return charSequence2 == null ? false : containsAny(charSequence, toCharArray(charSequence2));
    }

    public static boolean containsAny(CharSequence charSequence, char[] cArr) {
        if (isEmpty(charSequence) || isEmpty(cArr)) {
            return false;
        }
        int length = charSequence.length();
        int length2 = cArr.length;
        int i = length - 1;
        int i2 = length2 - 1;
        int i3 = 0;
        while (i3 < length) {
            char charAt = charSequence.charAt(i3);
            int i4 = 0;
            while (i4 < length2) {
                if (cArr[i4] == charAt) {
                    if (!Character.isHighSurrogate(charAt) || i4 == i2) {
                        return true;
                    }
                    if (i3 < i && cArr[i4 + 1] == charSequence.charAt(i3 + 1)) {
                        return true;
                    }
                }
                i4++;
            }
            i3++;
        }
        return false;
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    private static boolean isEmpty(char[] cArr) {
        return cArr == null || cArr.length == 0;
    }

    private static char[] toCharArray(CharSequence charSequence) {
        if (charSequence instanceof String) {
            return ((String) charSequence).toCharArray();
        }
        int length = charSequence.length();
        char[] cArr = new char[charSequence.length()];
        for (int i = 0; i < length; i++) {
            cArr[i] = charSequence.charAt(i);
        }
        return cArr;
    }

    public static int countMatches(CharSequence charSequence, CharSequence charSequence2) {
        int i = 0;
        if (isEmpty(charSequence) || isEmpty(charSequence2)) {
            return 0;
        }
        int i2 = 0;
        while (true) {
            i = indexOf(charSequence, charSequence2, i);
            if (i == -1) {
                return i2;
            }
            i2++;
            i += charSequence2.length();
        }
    }

    private static int indexOf(CharSequence charSequence, CharSequence charSequence2, int i) {
        return charSequence.toString().indexOf(charSequence2.toString(), i);
    }

    private Strings() {
    }
}
