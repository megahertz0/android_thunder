package com.tencent.stat.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class d {
    public static File a(String str) {
        File file = new File(str);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                a(file.getParentFile().getAbsolutePath());
            }
            file.mkdir();
        }
        return file;
    }

    public static List<String> a(File file) {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        List<String> arrayList = new ArrayList();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return arrayList;
            }
            arrayList.add(readLine.trim());
        }
    }
}
