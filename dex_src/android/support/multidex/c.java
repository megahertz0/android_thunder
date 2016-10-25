package android.support.multidex;

import java.io.File;
import java.io.FileFilter;

// compiled from: MultiDexExtractor.java
final class c implements FileFilter {
    final /* synthetic */ String a;

    c(String str) {
        this.a = str;
    }

    public final boolean accept(File file) {
        return !file.getName().startsWith(this.a);
    }
}
