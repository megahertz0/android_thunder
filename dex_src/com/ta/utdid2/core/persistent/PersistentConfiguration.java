package com.ta.utdid2.core.persistent;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.support.v4.widget.AutoScrollHelper;
import com.ta.utdid2.android.utils.StringUtils;
import com.ta.utdid2.core.persistent.MySharedPreferences.MyEditor;
import com.umeng.a;
import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

public class PersistentConfiguration {
    private static final String KEY_TIMESTAMP = "t";
    private static final String KEY_TIMESTAMP2 = "t2";
    private boolean mCanRead;
    private boolean mCanWrite;
    private String mConfigName;
    private Context mContext;
    private Editor mEditor;
    private String mFolderName;
    private boolean mIsLessMode;
    private boolean mIsSafety;
    private MyEditor mMyEditor;
    private MySharedPreferences mMySP;
    private SharedPreferences mSp;
    private TransactionXMLFile mTxf;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public PersistentConfiguration(android.content.Context r11, java.lang.String r12, java.lang.String r13, boolean r14, boolean r15) {
        throw new UnsupportedOperationException("Method not decompiled: com.ta.utdid2.core.persistent.PersistentConfiguration.<init>(android.content.Context, java.lang.String, java.lang.String, boolean, boolean):void");
        /*
        this = this;
        r10.<init>();
        r0 = "";
        r10.mConfigName = r0;
        r0 = "";
        r10.mFolderName = r0;
        r0 = 0;
        r10.mIsSafety = r0;
        r0 = 0;
        r10.mCanRead = r0;
        r0 = 0;
        r10.mCanWrite = r0;
        r0 = 0;
        r10.mSp = r0;
        r0 = 0;
        r10.mMySP = r0;
        r0 = 0;
        r10.mEditor = r0;
        r0 = 0;
        r10.mMyEditor = r0;
        r0 = 0;
        r10.mContext = r0;
        r0 = 0;
        r10.mTxf = r0;
        r0 = 0;
        r10.mIsLessMode = r0;
        r10.mIsSafety = r14;
        r10.mIsLessMode = r15;
        r10.mConfigName = r13;
        r10.mFolderName = r12;
        r10.mContext = r11;
        r0 = 0;
        r4 = 0;
        if (r11 == 0) goto L_0x004d;
    L_0x003b:
        r0 = 0;
        r0 = r11.getSharedPreferences(r13, r0);
        r10.mSp = r0;
        r0 = r10.mSp;
        r1 = "t";
        r2 = 0;
        r0 = r0.getLong(r1, r2);
    L_0x004d:
        r2 = 0;
        r2 = android.os.Environment.getExternalStorageState();	 Catch:{ Exception -> 0x00fb }
    L_0x0052:
        r3 = com.ta.utdid2.android.utils.StringUtils.isEmpty(r2);
        if (r3 != 0) goto L_0x0112;
    L_0x0058:
        r3 = "mounted";
        r3 = r2.equals(r3);
        if (r3 == 0) goto L_0x0101;
    L_0x0061:
        r2 = 1;
        r10.mCanWrite = r2;
        r10.mCanRead = r2;
    L_0x0066:
        r2 = r10.mCanRead;
        if (r2 != 0) goto L_0x006e;
    L_0x006a:
        r2 = r10.mCanWrite;
        if (r2 == 0) goto L_0x01fc;
    L_0x006e:
        if (r11 == 0) goto L_0x01fc;
    L_0x0070:
        r2 = com.ta.utdid2.android.utils.StringUtils.isEmpty(r12);
        if (r2 != 0) goto L_0x01fc;
    L_0x0076:
        r2 = r10.getTransactionXMLFile(r12);
        r10.mTxf = r2;
        r2 = r10.mTxf;
        if (r2 == 0) goto L_0x01fc;
    L_0x0080:
        r2 = r10.mTxf;	 Catch:{ Exception -> 0x01eb }
        r3 = 0;
        r2 = r2.getMySharedPreferences(r13, r3);	 Catch:{ Exception -> 0x01eb }
        r10.mMySP = r2;	 Catch:{ Exception -> 0x01eb }
        r2 = r10.mMySP;	 Catch:{ Exception -> 0x01eb }
        r3 = "t";
        r6 = 0;
        r2 = r2.getLong(r3, r6);	 Catch:{ Exception -> 0x01eb }
        if (r15 != 0) goto L_0x0147;
    L_0x0096:
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 <= 0) goto L_0x0119;
    L_0x009a:
        r4 = r10.mSp;	 Catch:{ Exception -> 0x01ef }
        r5 = r10.mMySP;	 Catch:{ Exception -> 0x01ef }
        r10.copySPToMySP(r4, r5);	 Catch:{ Exception -> 0x01ef }
        r4 = r10.mTxf;	 Catch:{ Exception -> 0x01ef }
        r5 = 0;
        r4 = r4.getMySharedPreferences(r13, r5);	 Catch:{ Exception -> 0x01ef }
        r10.mMySP = r4;	 Catch:{ Exception -> 0x01ef }
        r4 = r0;
        r0 = r2;
    L_0x00ac:
        r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r2 != 0) goto L_0x00bc;
    L_0x00b0:
        r2 = 0;
        r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r2 != 0) goto L_0x00fa;
    L_0x00b6:
        r2 = 0;
        r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r2 != 0) goto L_0x00fa;
    L_0x00bc:
        r2 = java.lang.System.currentTimeMillis();
        r6 = r10.mIsLessMode;
        if (r6 == 0) goto L_0x00d4;
    L_0x00c4:
        r6 = r10.mIsLessMode;
        if (r6 == 0) goto L_0x00fa;
    L_0x00c8:
        r6 = 0;
        r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r4 != 0) goto L_0x00fa;
    L_0x00ce:
        r4 = 0;
        r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r0 != 0) goto L_0x00fa;
    L_0x00d4:
        r0 = r10.mSp;
        if (r0 == 0) goto L_0x00e7;
    L_0x00d8:
        r0 = r10.mSp;
        r0 = r0.edit();
        r1 = "t2";
        r0.putLong(r1, r2);
        r0.commit();
    L_0x00e7:
        r0 = r10.mMySP;	 Catch:{ Exception -> 0x01e8 }
        if (r0 == 0) goto L_0x00fa;
    L_0x00eb:
        r0 = r10.mMySP;	 Catch:{ Exception -> 0x01e8 }
        r0 = r0.edit();	 Catch:{ Exception -> 0x01e8 }
        r1 = "t2";
        r0.putLong(r1, r2);	 Catch:{ Exception -> 0x01e8 }
        r0.commit();	 Catch:{ Exception -> 0x01e8 }
    L_0x00fa:
        return;
    L_0x00fb:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x0052;
    L_0x0101:
        r3 = "mounted_ro";
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0112;
    L_0x010a:
        r2 = 1;
        r10.mCanRead = r2;
        r2 = 0;
        r10.mCanWrite = r2;
        goto L_0x0066;
    L_0x0112:
        r2 = 0;
        r10.mCanWrite = r2;
        r10.mCanRead = r2;
        goto L_0x0066;
    L_0x0119:
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 >= 0) goto L_0x012f;
    L_0x011d:
        r4 = r10.mMySP;	 Catch:{ Exception -> 0x01ef }
        r5 = r10.mSp;	 Catch:{ Exception -> 0x01ef }
        r10.copyMySPToSP(r4, r5);	 Catch:{ Exception -> 0x01ef }
        r4 = 0;
        r4 = r11.getSharedPreferences(r13, r4);	 Catch:{ Exception -> 0x01ef }
        r10.mSp = r4;	 Catch:{ Exception -> 0x01ef }
        r4 = r0;
        r0 = r2;
        goto L_0x00ac;
    L_0x012f:
        r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r4 != 0) goto L_0x01f8;
    L_0x0133:
        r4 = r10.mSp;	 Catch:{ Exception -> 0x01ef }
        r5 = r10.mMySP;	 Catch:{ Exception -> 0x01ef }
        r10.copySPToMySP(r4, r5);	 Catch:{ Exception -> 0x01ef }
        r4 = r10.mTxf;	 Catch:{ Exception -> 0x01ef }
        r5 = 0;
        r4 = r4.getMySharedPreferences(r13, r5);	 Catch:{ Exception -> 0x01ef }
        r10.mMySP = r4;	 Catch:{ Exception -> 0x01ef }
        r4 = r0;
        r0 = r2;
        goto L_0x00ac;
    L_0x0147:
        r4 = r10.mSp;	 Catch:{ Exception -> 0x01ef }
        r5 = "t2";
        r6 = 0;
        r4 = r4.getLong(r5, r6);	 Catch:{ Exception -> 0x01ef }
        r0 = r10.mMySP;	 Catch:{ Exception -> 0x01f4 }
        r1 = "t2";
        r6 = 0;
        r0 = r0.getLong(r1, r6);	 Catch:{ Exception -> 0x01f4 }
        r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r2 >= 0) goto L_0x017e;
    L_0x0161:
        r2 = 0;
        r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x017e;
    L_0x0167:
        r2 = r10.mSp;	 Catch:{ Exception -> 0x0179 }
        r3 = r10.mMySP;	 Catch:{ Exception -> 0x0179 }
        r10.copySPToMySP(r2, r3);	 Catch:{ Exception -> 0x0179 }
        r2 = r10.mTxf;	 Catch:{ Exception -> 0x0179 }
        r3 = 0;
        r2 = r2.getMySharedPreferences(r13, r3);	 Catch:{ Exception -> 0x0179 }
        r10.mMySP = r2;	 Catch:{ Exception -> 0x0179 }
        goto L_0x00ac;
    L_0x0179:
        r2 = move-exception;
        r2 = r4;
    L_0x017b:
        r4 = r2;
        goto L_0x00ac;
    L_0x017e:
        r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r2 <= 0) goto L_0x0198;
    L_0x0182:
        r2 = 0;
        r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x0198;
    L_0x0188:
        r2 = r10.mMySP;	 Catch:{ Exception -> 0x0179 }
        r3 = r10.mSp;	 Catch:{ Exception -> 0x0179 }
        r10.copyMySPToSP(r2, r3);	 Catch:{ Exception -> 0x0179 }
        r2 = 0;
        r2 = r11.getSharedPreferences(r13, r2);	 Catch:{ Exception -> 0x0179 }
        r10.mSp = r2;	 Catch:{ Exception -> 0x0179 }
        goto L_0x00ac;
    L_0x0198:
        r2 = 0;
        r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r2 != 0) goto L_0x01b4;
    L_0x019e:
        r2 = 0;
        r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x01b4;
    L_0x01a4:
        r2 = r10.mMySP;	 Catch:{ Exception -> 0x0179 }
        r3 = r10.mSp;	 Catch:{ Exception -> 0x0179 }
        r10.copyMySPToSP(r2, r3);	 Catch:{ Exception -> 0x0179 }
        r2 = 0;
        r2 = r11.getSharedPreferences(r13, r2);	 Catch:{ Exception -> 0x0179 }
        r10.mSp = r2;	 Catch:{ Exception -> 0x0179 }
        goto L_0x00ac;
    L_0x01b4:
        r2 = 0;
        r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r2 != 0) goto L_0x01d2;
    L_0x01ba:
        r2 = 0;
        r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x01d2;
    L_0x01c0:
        r2 = r10.mSp;	 Catch:{ Exception -> 0x0179 }
        r3 = r10.mMySP;	 Catch:{ Exception -> 0x0179 }
        r10.copySPToMySP(r2, r3);	 Catch:{ Exception -> 0x0179 }
        r2 = r10.mTxf;	 Catch:{ Exception -> 0x0179 }
        r3 = 0;
        r2 = r2.getMySharedPreferences(r13, r3);	 Catch:{ Exception -> 0x0179 }
        r10.mMySP = r2;	 Catch:{ Exception -> 0x0179 }
        goto L_0x00ac;
    L_0x01d2:
        r2 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r2 != 0) goto L_0x00ac;
    L_0x01d6:
        r2 = r10.mSp;	 Catch:{ Exception -> 0x0179 }
        r3 = r10.mMySP;	 Catch:{ Exception -> 0x0179 }
        r10.copySPToMySP(r2, r3);	 Catch:{ Exception -> 0x0179 }
        r2 = r10.mTxf;	 Catch:{ Exception -> 0x0179 }
        r3 = 0;
        r2 = r2.getMySharedPreferences(r13, r3);	 Catch:{ Exception -> 0x0179 }
        r10.mMySP = r2;	 Catch:{ Exception -> 0x0179 }
        goto L_0x00ac;
    L_0x01e8:
        r0 = move-exception;
        goto L_0x00fa;
    L_0x01eb:
        r2 = move-exception;
        r2 = r0;
        r0 = r4;
        goto L_0x017b;
    L_0x01ef:
        r4 = move-exception;
        r8 = r2;
        r2 = r0;
        r0 = r8;
        goto L_0x017b;
    L_0x01f4:
        r0 = move-exception;
        r0 = r2;
        r2 = r4;
        goto L_0x017b;
    L_0x01f8:
        r4 = r0;
        r0 = r2;
        goto L_0x00ac;
    L_0x01fc:
        r8 = r4;
        r4 = r0;
        r0 = r8;
        goto L_0x00ac;
        */
    }

    private TransactionXMLFile getTransactionXMLFile(String str) {
        File rootFolder = getRootFolder(str);
        if (rootFolder == null) {
            return null;
        }
        this.mTxf = new TransactionXMLFile(rootFolder.getAbsolutePath());
        return this.mTxf;
    }

    private File getRootFolder(String str) {
        if (Environment.getExternalStorageDirectory() == null) {
            return null;
        }
        File file = new File(String.format("%s%s%s", new Object[]{Environment.getExternalStorageDirectory().getAbsolutePath(), File.separator, str}));
        if (file.exists()) {
            return file;
        }
        file.mkdirs();
        return file;
    }

    private void copySPToMySP(SharedPreferences sharedPreferences, MySharedPreferences mySharedPreferences) {
        if (sharedPreferences != null && mySharedPreferences != null) {
            MyEditor edit = mySharedPreferences.edit();
            if (edit != null) {
                edit.clear();
                for (Entry entry : sharedPreferences.getAll().entrySet()) {
                    String str = (String) entry.getKey();
                    Object value = entry.getValue();
                    if (value instanceof String) {
                        edit.putString(str, (String) value);
                    } else if (value instanceof Integer) {
                        edit.putInt(str, ((Integer) value).intValue());
                    } else if (value instanceof Long) {
                        edit.putLong(str, ((Long) value).longValue());
                    } else if (value instanceof Float) {
                        edit.putFloat(str, ((Float) value).floatValue());
                    } else if (value instanceof Boolean) {
                        edit.putBoolean(str, ((Boolean) value).booleanValue());
                    }
                }
                edit.commit();
            }
        }
    }

    private void copyMySPToSP(MySharedPreferences mySharedPreferences, SharedPreferences sharedPreferences) {
        if (mySharedPreferences != null && sharedPreferences != null) {
            Editor edit = sharedPreferences.edit();
            if (edit != null) {
                edit.clear();
                for (Entry entry : mySharedPreferences.getAll().entrySet()) {
                    String str = (String) entry.getKey();
                    Object value = entry.getValue();
                    if (value instanceof String) {
                        edit.putString(str, (String) value);
                    } else if (value instanceof Integer) {
                        edit.putInt(str, ((Integer) value).intValue());
                    } else if (value instanceof Long) {
                        edit.putLong(str, ((Long) value).longValue());
                    } else if (value instanceof Float) {
                        edit.putFloat(str, ((Float) value).floatValue());
                    } else if (value instanceof Boolean) {
                        edit.putBoolean(str, ((Boolean) value).booleanValue());
                    }
                }
                edit.commit();
            }
        }
    }

    private boolean checkSDCardXMLFile() {
        if (this.mMySP == null) {
            return false;
        }
        boolean checkFile = this.mMySP.checkFile();
        if (checkFile) {
            return checkFile;
        }
        commit();
        return checkFile;
    }

    private void initEditor() {
        if (this.mEditor == null && this.mSp != null) {
            this.mEditor = this.mSp.edit();
        }
        if (this.mCanWrite && this.mMyEditor == null && this.mMySP != null) {
            this.mMyEditor = this.mMySP.edit();
        }
        checkSDCardXMLFile();
    }

    public void putInt(String str, int i) {
        if (!StringUtils.isEmpty(str) && !str.equals(KEY_TIMESTAMP)) {
            initEditor();
            if (this.mEditor != null) {
                this.mEditor.putInt(str, i);
            }
            if (this.mMyEditor != null) {
                this.mMyEditor.putInt(str, i);
            }
        }
    }

    public void putLong(String str, long j) {
        if (!StringUtils.isEmpty(str) && !str.equals(KEY_TIMESTAMP)) {
            initEditor();
            if (this.mEditor != null) {
                this.mEditor.putLong(str, j);
            }
            if (this.mMyEditor != null) {
                this.mMyEditor.putLong(str, j);
            }
        }
    }

    public void putBoolean(String str, boolean z) {
        if (!StringUtils.isEmpty(str) && !str.equals(KEY_TIMESTAMP)) {
            initEditor();
            if (this.mEditor != null) {
                this.mEditor.putBoolean(str, z);
            }
            if (this.mMyEditor != null) {
                this.mMyEditor.putBoolean(str, z);
            }
        }
    }

    public void putFloat(String str, float f) {
        if (!StringUtils.isEmpty(str) && !str.equals(KEY_TIMESTAMP)) {
            initEditor();
            if (this.mEditor != null) {
                this.mEditor.putFloat(str, f);
            }
            if (this.mMyEditor != null) {
                this.mMyEditor.putFloat(str, f);
            }
        }
    }

    public void putString(String str, String str2) {
        if (!StringUtils.isEmpty(str) && !str.equals(KEY_TIMESTAMP)) {
            initEditor();
            if (this.mEditor != null) {
                this.mEditor.putString(str, str2);
            }
            if (this.mMyEditor != null) {
                this.mMyEditor.putString(str, str2);
            }
        }
    }

    public void remove(String str) {
        if (!StringUtils.isEmpty(str) && !str.equals(KEY_TIMESTAMP)) {
            initEditor();
            if (this.mEditor != null) {
                this.mEditor.remove(str);
            }
            if (this.mMyEditor != null) {
                this.mMyEditor.remove(str);
            }
        }
    }

    public void reload() {
        if (!(this.mSp == null || this.mContext == null)) {
            this.mSp = this.mContext.getSharedPreferences(this.mConfigName, 0);
        }
        String str = null;
        try {
            str = Environment.getExternalStorageState();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!StringUtils.isEmpty(str)) {
            if (str.equals("mounted") || (str.equals("mounted_ro") && this.mMySP != null)) {
                try {
                    if (this.mTxf != null) {
                        this.mMySP = this.mTxf.getMySharedPreferences(this.mConfigName, 0);
                    }
                } catch (Exception e2) {
                }
            }
        }
    }

    public void clear() {
        initEditor();
        long currentTimeMillis = System.currentTimeMillis();
        if (this.mEditor != null) {
            this.mEditor.clear();
            this.mEditor.putLong(KEY_TIMESTAMP, currentTimeMillis);
        }
        if (this.mMyEditor != null) {
            this.mMyEditor.clear();
            this.mMyEditor.putLong(KEY_TIMESTAMP, currentTimeMillis);
        }
    }

    public boolean commit() {
        boolean z = true;
        long currentTimeMillis = System.currentTimeMillis();
        if (this.mEditor != null) {
            if (!(this.mIsLessMode || this.mSp == null)) {
                this.mEditor.putLong(KEY_TIMESTAMP, currentTimeMillis);
            }
            if (!this.mEditor.commit()) {
                z = false;
            }
        }
        if (!(this.mSp == null || this.mContext == null)) {
            this.mSp = this.mContext.getSharedPreferences(this.mConfigName, 0);
        }
        String str = null;
        try {
            str = Environment.getExternalStorageState();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!StringUtils.isEmpty(str)) {
            if (str.equals("mounted")) {
                if (this.mMySP == null) {
                    TransactionXMLFile transactionXMLFile = getTransactionXMLFile(this.mFolderName);
                    if (transactionXMLFile != null) {
                        this.mMySP = transactionXMLFile.getMySharedPreferences(this.mConfigName, 0);
                        if (this.mIsLessMode) {
                            copyMySPToSP(this.mMySP, this.mSp);
                        } else {
                            copySPToMySP(this.mSp, this.mMySP);
                        }
                        this.mMyEditor = this.mMySP.edit();
                    }
                } else if (!(this.mMyEditor == null || this.mMyEditor.commit())) {
                    z = false;
                }
            }
            if (str.equals("mounted") || (str.equals("mounted_ro") && this.mMySP != null)) {
                try {
                    if (this.mTxf != null) {
                        this.mMySP = this.mTxf.getMySharedPreferences(this.mConfigName, 0);
                    }
                } catch (Exception e2) {
                }
            }
        }
        return z;
    }

    public String getString(String str) {
        checkSDCardXMLFile();
        if (this.mSp != null) {
            String string = this.mSp.getString(str, a.d);
            if (!StringUtils.isEmpty(string)) {
                return string;
            }
        }
        return this.mMySP != null ? this.mMySP.getString(str, a.d) : a.d;
    }

    public int getInt(String str) {
        checkSDCardXMLFile();
        if (this.mSp != null) {
            return this.mSp.getInt(str, 0);
        }
        return this.mMySP != null ? this.mMySP.getInt(str, 0) : 0;
    }

    public long getLong(String str) {
        checkSDCardXMLFile();
        if (this.mSp != null) {
            return this.mSp.getLong(str, 0);
        }
        return this.mMySP != null ? this.mMySP.getLong(str, 0) : 0;
    }

    public float getFloat(String str) {
        checkSDCardXMLFile();
        if (this.mSp != null) {
            return this.mSp.getFloat(str, AutoScrollHelper.RELATIVE_UNSPECIFIED);
        }
        return this.mMySP != null ? this.mMySP.getFloat(str, AutoScrollHelper.RELATIVE_UNSPECIFIED) : AutoScrollHelper.RELATIVE_UNSPECIFIED;
    }

    public boolean getBoolean(String str) {
        checkSDCardXMLFile();
        if (this.mSp != null) {
            return this.mSp.getBoolean(str, false);
        }
        return this.mMySP != null ? this.mMySP.getBoolean(str, false) : false;
    }

    public Map<String, ?> getAll() {
        checkSDCardXMLFile();
        if (this.mSp != null) {
            return this.mSp.getAll();
        }
        return this.mMySP != null ? this.mMySP.getAll() : null;
    }
}
