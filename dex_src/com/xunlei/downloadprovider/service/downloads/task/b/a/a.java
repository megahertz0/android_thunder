package com.xunlei.downloadprovider.service.downloads.task.b.a;

import com.umeng.message.proguard.j;

// compiled from: SQLiteTableHelper.java
public abstract class a {
    protected final int a;
    protected final int b;

    public a(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    protected static String a(String str, String[][] strArr) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("CREATE TABLE IF NOT EXISTS `").append(str).append("`(");
        for (int i = 0; i < strArr.length; i++) {
            if (i > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("`").append(strArr[i][0]).append("` ").append(strArr[i][1]);
        }
        stringBuilder.append(j.t);
        return stringBuilder.toString();
    }
}
