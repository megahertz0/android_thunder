package org.eclipse.paho.client.mqttv3.persist;

import com.xunlei.download.proguard.c;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.util.Enumeration;
import java.util.Vector;
import org.eclipse.paho.client.mqttv3.MqttClientPersistence;
import org.eclipse.paho.client.mqttv3.MqttPersistable;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.eclipse.paho.client.mqttv3.internal.FileLock;
import org.eclipse.paho.client.mqttv3.internal.MqttPersistentData;

public class MqttDefaultFilePersistence implements MqttClientPersistence {
    private static final FilenameFilter FILE_FILTER;
    private static final String LOCK_FILENAME = ".lck";
    private static final String MESSAGE_BACKUP_FILE_EXTENSION = ".bup";
    private static final String MESSAGE_FILE_EXTENSION = ".msg";
    private File clientDir;
    private File dataDir;
    private FileLock fileLock;

    static {
        FILE_FILTER = new FilenameFilter() {
            public boolean accept(File file, String str) {
                return str.endsWith(MESSAGE_FILE_EXTENSION);
            }
        };
    }

    public MqttDefaultFilePersistence() {
        this(System.getProperty("user.dir"));
    }

    public MqttDefaultFilePersistence(String str) {
        this.clientDir = null;
        this.fileLock = null;
        this.dataDir = new File(str);
    }

    public void open(String str, String str2) throws MqttPersistenceException {
        int i = 0;
        if (this.dataDir.exists() && !this.dataDir.isDirectory()) {
            throw new MqttPersistenceException();
        } else if (!this.dataDir.exists() && !this.dataDir.mkdirs()) {
            throw new MqttPersistenceException();
        } else if (this.dataDir.canWrite()) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i2 = 0; i2 < str.length(); i2++) {
                char charAt = str.charAt(i2);
                if (isSafeChar(charAt)) {
                    stringBuffer.append(charAt);
                }
            }
            stringBuffer.append(c.q);
            while (i < str2.length()) {
                char charAt2 = str2.charAt(i);
                if (isSafeChar(charAt2)) {
                    stringBuffer.append(charAt2);
                }
                i++;
            }
            synchronized (this) {
                try {
                    if (this.clientDir == null) {
                        this.clientDir = new File(this.dataDir, stringBuffer.toString());
                        if (!this.clientDir.exists()) {
                            this.clientDir.mkdir();
                        }
                    }
                    this.fileLock = new FileLock(this.clientDir, LOCK_FILENAME);
                    restoreBackups(this.clientDir);
                } catch (Exception e) {
                    throw new MqttPersistenceException(32200);
                } catch (Throwable th) {
                }
            }
        } else {
            throw new MqttPersistenceException();
        }
    }

    private void checkIsOpen() throws MqttPersistenceException {
        if (this.clientDir == null) {
            throw new MqttPersistenceException();
        }
    }

    public void close() throws MqttPersistenceException {
        synchronized (this) {
            if (this.fileLock != null) {
                this.fileLock.release();
            }
            if (getFiles().length == 0) {
                this.clientDir.delete();
            }
            this.clientDir = null;
        }
    }

    public void put(String str, MqttPersistable mqttPersistable) throws MqttPersistenceException {
        checkIsOpen();
        File file = new File(this.clientDir, new StringBuffer(String.valueOf(str)).append(MESSAGE_FILE_EXTENSION).toString());
        File file2 = new File(this.clientDir, new StringBuffer(String.valueOf(str)).append(".msg.bup").toString());
        if (file.exists() && !file.renameTo(file2)) {
            file2.delete();
            file.renameTo(file2);
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(mqttPersistable.getHeaderBytes(), mqttPersistable.getHeaderOffset(), mqttPersistable.getHeaderLength());
            if (mqttPersistable.getPayloadBytes() != null) {
                fileOutputStream.write(mqttPersistable.getPayloadBytes(), mqttPersistable.getPayloadOffset(), mqttPersistable.getPayloadLength());
            }
            fileOutputStream.getFD().sync();
            fileOutputStream.close();
            if (file2.exists()) {
                file2.delete();
            }
            if (file2.exists() && !file2.renameTo(file)) {
                file.delete();
                file2.renameTo(file);
            }
        } catch (Throwable e) {
            throw new MqttPersistenceException(e);
        } catch (Throwable th) {
            if (file2.exists() && !file2.renameTo(file)) {
                file.delete();
                file2.renameTo(file);
            }
        }
    }

    public MqttPersistable get(String str) throws MqttPersistenceException {
        checkIsOpen();
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(this.clientDir, new StringBuffer(String.valueOf(str)).append(MESSAGE_FILE_EXTENSION).toString()));
            int available = fileInputStream.available();
            byte[] bArr = new byte[available];
            for (int i = 0; i < available; i += fileInputStream.read(bArr, i, available - i)) {
            }
            fileInputStream.close();
            return new MqttPersistentData(str, bArr, 0, available, null, 0, 0);
        } catch (Throwable e) {
            throw new MqttPersistenceException(e);
        }
    }

    public void remove(String str) throws MqttPersistenceException {
        checkIsOpen();
        File file = new File(this.clientDir, new StringBuffer(String.valueOf(str)).append(MESSAGE_FILE_EXTENSION).toString());
        if (file.exists()) {
            file.delete();
        }
    }

    public Enumeration keys() throws MqttPersistenceException {
        checkIsOpen();
        File[] files = getFiles();
        Vector vector = new Vector(files.length);
        for (int i = 0; i < files.length; i++) {
            String name = files[i].getName();
            vector.addElement(name.substring(0, name.length() - 4));
        }
        return vector.elements();
    }

    private File[] getFiles() throws MqttPersistenceException {
        checkIsOpen();
        File[] listFiles = this.clientDir.listFiles(FILE_FILTER);
        if (listFiles != null) {
            return listFiles;
        }
        throw new MqttPersistenceException();
    }

    private boolean isSafeChar(char c) {
        return Character.isJavaIdentifierPart(c) || c == '-';
    }

    private void restoreBackups(File file) throws MqttPersistenceException {
        File[] listFiles = file.listFiles(new FileFilter() {
            final MqttDefaultFilePersistence this$0;

            {
                this.this$0 = r1;
            }

            public boolean accept(File file) {
                return file.getName().endsWith(MESSAGE_BACKUP_FILE_EXTENSION);
            }
        });
        if (listFiles == null) {
            throw new MqttPersistenceException();
        }
        for (int i = 0; i < listFiles.length; i++) {
            File file2 = new File(file, listFiles[i].getName().substring(0, listFiles[i].getName().length() - 4));
            if (!listFiles[i].renameTo(file2)) {
                file2.delete();
                listFiles[i].renameTo(file2);
            }
        }
    }

    public boolean containsKey(String str) throws MqttPersistenceException {
        checkIsOpen();
        return new File(this.clientDir, new StringBuffer(String.valueOf(str)).append(MESSAGE_FILE_EXTENSION).toString()).exists();
    }

    public void clear() throws MqttPersistenceException {
        checkIsOpen();
        File[] files = getFiles();
        for (File file : files) {
            file.delete();
        }
    }
}
