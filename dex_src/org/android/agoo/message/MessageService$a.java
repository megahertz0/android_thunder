package org.android.agoo.message;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.a;
import com.umeng.message.proguard.j;

// compiled from: Taobao
private class MessageService$a extends SQLiteOpenHelper {
    public MessageService$a(Context context) {
        super(context, "message_accs_db", null, 3);
    }

    public SQLiteDatabase getWritableDatabase() {
        return !a.a(super.getWritableDatabase().getPath(), 102400) ? null : super.getWritableDatabase();
    }

    private String a() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("create table accs_message");
        stringBuffer.append(j.s);
        stringBuffer.append("id text UNIQUE not null,");
        stringBuffer.append("state text,");
        stringBuffer.append("message text,");
        stringBuffer.append("create_time date");
        stringBuffer.append(");");
        return stringBuffer.toString();
    }

    private String b() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("create table message");
        stringBuffer.append(j.s);
        stringBuffer.append("id text UNIQUE not null,");
        stringBuffer.append("state integer,");
        stringBuffer.append("body_code integer,");
        stringBuffer.append("report long,");
        stringBuffer.append("target_time long,");
        stringBuffer.append("interval integer,");
        stringBuffer.append("type text,");
        stringBuffer.append("message text,");
        stringBuffer.append("notify integer,");
        stringBuffer.append("create_time date");
        stringBuffer.append(");");
        return stringBuffer.toString();
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase != null) {
            try {
                sQLiteDatabase.execSQL(b());
                sQLiteDatabase.execSQL("CREATE INDEX id_index ON message(id)");
                sQLiteDatabase.execSQL("CREATE INDEX body_code_index ON message(body_code)");
                sQLiteDatabase.execSQL(a());
            } catch (Throwable th) {
                ALog.e("MessageService", "messagedbhelper create", th, new Object[0]);
            }
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (sQLiteDatabase != null) {
            try {
                sQLiteDatabase.execSQL("delete from message where create_time< date('now','-7 day') and state=1");
            } catch (Throwable th) {
                try {
                    ALog.e("MessageService", "messagedbhelper create", th, new Object[0]);
                    try {
                        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS accs_message");
                        sQLiteDatabase.execSQL(a());
                    } catch (Throwable th2) {
                        ALog.e("MessageService", "MessageService onUpgrade is error", th2, new Object[0]);
                    }
                } catch (Throwable th3) {
                    try {
                        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS accs_message");
                        sQLiteDatabase.execSQL(a());
                    } catch (Throwable th4) {
                        ALog.e("MessageService", "MessageService onUpgrade is error", th4, new Object[0]);
                    }
                }
            }
        }
        try {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS accs_message");
            sQLiteDatabase.execSQL(a());
        } catch (Throwable th22) {
            ALog.e("MessageService", "MessageService onUpgrade is error", th22, new Object[0]);
        }
    }
}
