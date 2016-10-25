package org.apache.commons.logging.impl;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogConfigurationException;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

public final class Log4jFactory extends LogFactory {
    private Hashtable attributes;
    private Hashtable instances;

    public Log4jFactory() {
        this.attributes = new Hashtable();
        this.instances = new Hashtable();
    }

    public final Object getAttribute(String str) {
        return this.attributes.get(str);
    }

    public final String[] getAttributeNames() {
        Vector vector = new Vector();
        Enumeration keys = this.attributes.keys();
        while (keys.hasMoreElements()) {
            vector.addElement((String) keys.nextElement());
        }
        String[] strArr = new String[vector.size()];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = (String) vector.elementAt(i);
        }
        return strArr;
    }

    public final Log getInstance(Class cls) throws LogConfigurationException {
        Log log = (Log) this.instances.get(cls);
        if (log != null) {
            return log;
        }
        log = new Log4JLogger(Logger.getLogger(cls));
        this.instances.put(cls, log);
        return log;
    }

    public final Log getInstance(String str) throws LogConfigurationException {
        Log log = (Log) this.instances.get(str);
        if (log != null) {
            return log;
        }
        log = new Log4JLogger(Logger.getLogger(str));
        this.instances.put(str, log);
        return log;
    }

    public final void release() {
        this.instances.clear();
    }

    public final void removeAttribute(String str) {
        this.attributes.remove(str);
    }

    public final void setAttribute(String str, Object obj) {
        if (obj == null) {
            this.attributes.remove(str);
        } else {
            this.attributes.put(str, obj);
        }
    }
}
