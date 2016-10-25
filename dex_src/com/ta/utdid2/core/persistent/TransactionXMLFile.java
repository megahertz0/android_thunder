package com.ta.utdid2.core.persistent;

import com.sina.weibo.sdk.component.GameManager;
import com.ta.utdid2.core.persistent.MySharedPreferences.MyEditor;
import com.ta.utdid2.core.persistent.MySharedPreferences.OnSharedPreferenceChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParserException;

public class TransactionXMLFile {
    private static final Object GLOBAL_COMMIT_LOCK;
    public static final int MODE_PRIVATE = 0;
    public static final int MODE_WORLD_READABLE = 1;
    public static final int MODE_WORLD_WRITEABLE = 2;
    private File mPreferencesDir;
    private final Object mSync;
    private HashMap<File, MySharedPreferencesImpl> sSharedPrefs;

    private static final class MySharedPreferencesImpl implements MySharedPreferences {
        private static final Object mContent;
        private boolean hasChange;
        private final File mBackupFile;
        private final File mFile;
        private WeakHashMap<OnSharedPreferenceChangeListener, Object> mListeners;
        private Map mMap;
        private final int mMode;

        public final class EditorImpl implements MyEditor {
            private boolean mClear;
            private final Map<String, Object> mModified;

            public EditorImpl() {
                this.mModified = new HashMap();
                this.mClear = false;
            }

            public final MyEditor putString(String str, String str2) {
                synchronized (this) {
                    this.mModified.put(str, str2);
                }
                return this;
            }

            public final MyEditor putInt(String str, int i) {
                synchronized (this) {
                    this.mModified.put(str, Integer.valueOf(i));
                }
                return this;
            }

            public final MyEditor putLong(String str, long j) {
                synchronized (this) {
                    this.mModified.put(str, Long.valueOf(j));
                }
                return this;
            }

            public final MyEditor putFloat(String str, float f) {
                synchronized (this) {
                    this.mModified.put(str, Float.valueOf(f));
                }
                return this;
            }

            public final MyEditor putBoolean(String str, boolean z) {
                synchronized (this) {
                    this.mModified.put(str, Boolean.valueOf(z));
                }
                return this;
            }

            public final MyEditor remove(String str) {
                synchronized (this) {
                    this.mModified.put(str, this);
                }
                return this;
            }

            public final MyEditor clear() {
                synchronized (this) {
                    this.mClear = true;
                }
                return this;
            }

            public final boolean commit() {
                boolean access$2;
                Object obj = null;
                synchronized (GLOBAL_COMMIT_LOCK) {
                    try {
                        if (MySharedPreferencesImpl.this.mListeners.size() > 0) {
                            int i = 1;
                        }
                        if (obj != null) {
                            HashSet hashSet = new HashSet(MySharedPreferencesImpl.this.mListeners.keySet());
                            ArrayList arrayList = new ArrayList();
                        } else {
                            Set set = null;
                            List list = null;
                        }
                        synchronized (this) {
                            if (this.mClear) {
                                MySharedPreferencesImpl.this.mMap.clear();
                                this.mClear = false;
                            }
                            for (Entry entry : this.mModified.entrySet()) {
                                String str = (String) entry.getKey();
                                com.ta.utdid2.core.persistent.TransactionXMLFile.MySharedPreferencesImpl.EditorImpl value = entry.getValue();
                                if (value == this) {
                                    MySharedPreferencesImpl.this.mMap.remove(str);
                                } else {
                                    MySharedPreferencesImpl.this.mMap.put(str, value);
                                }
                                if (obj != null) {
                                    list.add(str);
                                }
                            }
                            this.mModified.clear();
                        }
                        access$2 = MySharedPreferencesImpl.this.writeFileLocked();
                        if (access$2) {
                            MySharedPreferencesImpl.this.setHasChange(true);
                        }
                    } catch (Throwable th) {
                    }
                }
                if (obj != null) {
                    for (i = list.size() - 1; i >= 0; i--) {
                        str = (String) list.get(i);
                        for (OnSharedPreferenceChangeListener onSharedPreferenceChangeListener : r3) {
                            if (onSharedPreferenceChangeListener != null) {
                                onSharedPreferenceChangeListener.onSharedPreferenceChanged(MySharedPreferencesImpl.this, str);
                            }
                        }
                    }
                }
                return access$2;
            }
        }

        static {
            mContent = new Object();
        }

        MySharedPreferencesImpl(File file, int i, Map map) {
            this.hasChange = false;
            this.mFile = file;
            this.mBackupFile = TransactionXMLFile.makeBackupFile(file);
            this.mMode = i;
            if (map == null) {
                map = new HashMap();
            }
            this.mMap = map;
            this.mListeners = new WeakHashMap();
        }

        public final boolean checkFile() {
            return this.mFile != null && new File(this.mFile.getAbsolutePath()).exists();
        }

        public final void setHasChange(boolean z) {
            synchronized (this) {
                this.hasChange = z;
            }
        }

        public final boolean hasFileChanged() {
            boolean z;
            synchronized (this) {
                z = this.hasChange;
            }
            return z;
        }

        public final void replace(Map map) {
            if (map != null) {
                synchronized (this) {
                    this.mMap = map;
                }
            }
        }

        public final void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
            synchronized (this) {
                this.mListeners.put(onSharedPreferenceChangeListener, mContent);
            }
        }

        public final void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
            synchronized (this) {
                this.mListeners.remove(onSharedPreferenceChangeListener);
            }
        }

        public final Map<String, ?> getAll() {
            Map hashMap;
            synchronized (this) {
                hashMap = new HashMap(this.mMap);
            }
            return hashMap;
        }

        public final String getString(String str, String str2) {
            String str3;
            synchronized (this) {
                str3 = (String) this.mMap.get(str);
                if (str3 == null) {
                    str3 = str2;
                }
            }
            return str3;
        }

        public final int getInt(String str, int i) {
            synchronized (this) {
                Integer num = (Integer) this.mMap.get(str);
                if (num != null) {
                    i = num.intValue();
                }
            }
            return i;
        }

        public final long getLong(String str, long j) {
            synchronized (this) {
                Long l = (Long) this.mMap.get(str);
                if (l != null) {
                    j = l.longValue();
                }
            }
            return j;
        }

        public final float getFloat(String str, float f) {
            synchronized (this) {
                Float f2 = (Float) this.mMap.get(str);
                if (f2 != null) {
                    f = f2.floatValue();
                }
            }
            return f;
        }

        public final boolean getBoolean(String str, boolean z) {
            synchronized (this) {
                Boolean bool = (Boolean) this.mMap.get(str);
                if (bool != null) {
                    z = bool.booleanValue();
                }
            }
            return z;
        }

        public final boolean contains(String str) {
            boolean containsKey;
            synchronized (this) {
                containsKey = this.mMap.containsKey(str);
            }
            return containsKey;
        }

        public final MyEditor edit() {
            return new EditorImpl();
        }

        private FileOutputStream createFileOutputStream(File file) {
            try {
                return new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                if (!file.getParentFile().mkdir()) {
                    return null;
                }
                try {
                    return new FileOutputStream(file);
                } catch (FileNotFoundException e2) {
                    return null;
                }
            }
        }

        private boolean writeFileLocked() {
            if (this.mFile.exists()) {
                if (this.mBackupFile.exists()) {
                    this.mFile.delete();
                } else if (!this.mFile.renameTo(this.mBackupFile)) {
                    return false;
                }
            }
            try {
                OutputStream createFileOutputStream = createFileOutputStream(this.mFile);
                if (createFileOutputStream == null) {
                    return false;
                }
                XmlUtils.writeMapXml(this.mMap, createFileOutputStream);
                createFileOutputStream.close();
                this.mBackupFile.delete();
                return true;
            } catch (XmlPullParserException e) {
                if (this.mFile.exists()) {
                    return false;
                }
                this.mFile.delete();
                return false;
            } catch (IOException e2) {
                if (this.mFile.exists()) {
                    return false;
                }
                this.mFile.delete();
                return false;
            }
        }
    }

    static {
        GLOBAL_COMMIT_LOCK = new Object();
    }

    public TransactionXMLFile(String str) {
        this.mSync = new Object();
        this.sSharedPrefs = new HashMap();
        if (str == null || str.length() <= 0) {
            throw new RuntimeException("Directory can not be empty");
        }
        this.mPreferencesDir = new File(str);
    }

    private File makeFilename(File file, String str) {
        if (str.indexOf(File.separatorChar) < 0) {
            return new File(file, str);
        }
        throw new IllegalArgumentException(new StringBuilder("File ").append(str).append(" contains a path separator").toString());
    }

    private File getPreferencesDir() {
        File file;
        synchronized (this.mSync) {
            file = this.mPreferencesDir;
        }
        return file;
    }

    private File getSharedPrefsFile(String str) {
        return makeFilename(getPreferencesDir(), new StringBuilder(String.valueOf(str)).append(".xml").toString());
    }

    public MySharedPreferences getMySharedPreferences(String str, int i) {
        MySharedPreferences mySharedPreferencesImpl;
        File sharedPrefsFile = getSharedPrefsFile(str);
        synchronized (GLOBAL_COMMIT_LOCK) {
            MySharedPreferencesImpl mySharedPreferencesImpl2 = (MySharedPreferencesImpl) this.sSharedPrefs.get(sharedPrefsFile);
            if (mySharedPreferencesImpl2 == null || mySharedPreferencesImpl2.hasFileChanged()) {
                File makeBackupFile = makeBackupFile(sharedPrefsFile);
                if (makeBackupFile.exists()) {
                    sharedPrefsFile.delete();
                    makeBackupFile.renameTo(sharedPrefsFile);
                }
                if (sharedPrefsFile.exists()) {
                    sharedPrefsFile.canRead();
                }
                Map map = null;
                if (sharedPrefsFile.exists() && sharedPrefsFile.canRead()) {
                    try {
                        InputStream fileInputStream = new FileInputStream(sharedPrefsFile);
                        map = XmlUtils.readMapXml(fileInputStream);
                        fileInputStream.close();
                    } catch (XmlPullParserException e) {
                        try {
                            FileInputStream fileInputStream2 = new FileInputStream(sharedPrefsFile);
                            byte[] bArr = new byte[fileInputStream2.available()];
                            fileInputStream2.read(bArr);
                            String str2 = new String(bArr, 0, bArr.length, GameManager.DEFAULT_CHARSET);
                        } catch (FileNotFoundException e2) {
                            e2.printStackTrace();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    } catch (FileNotFoundException e22) {
                        e22.printStackTrace();
                    } catch (IOException e32) {
                        e32.printStackTrace();
                    } catch (Exception e4) {
                        e4.printStackTrace();
                    }
                }
                synchronized (GLOBAL_COMMIT_LOCK) {
                    if (mySharedPreferencesImpl2 != null) {
                        mySharedPreferencesImpl2.replace(map);
                    } else {
                        mySharedPreferencesImpl2 = (MySharedPreferencesImpl) this.sSharedPrefs.get(sharedPrefsFile);
                        if (mySharedPreferencesImpl2 == null) {
                            mySharedPreferencesImpl = new MySharedPreferencesImpl(sharedPrefsFile, i, map);
                            this.sSharedPrefs.put(sharedPrefsFile, mySharedPreferencesImpl);
                        }
                    }
                }
            }
        }
        return mySharedPreferencesImpl;
    }

    private static File makeBackupFile(File file) {
        return new File(new StringBuilder(String.valueOf(file.getPath())).append(".bak").toString());
    }
}
