package org.apache.commons.logging.impl;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

public final class Log4JLogger implements Log {
    private static final String FQCN;
    static Class class$org$apache$commons$logging$impl$Log4JLogger;
    private Logger logger;

    static {
        Class class$;
        if (class$org$apache$commons$logging$impl$Log4JLogger == null) {
            class$ = class$("org.apache.commons.logging.impl.Log4JLogger");
            class$org$apache$commons$logging$impl$Log4JLogger = class$;
        } else {
            class$ = class$org$apache$commons$logging$impl$Log4JLogger;
        }
        FQCN = class$.getName();
    }

    static Class class$(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            throw new NoClassDefFoundError(e.getMessage());
        }
    }

    public Log4JLogger() {
        this.logger = null;
    }

    public Log4JLogger(String str) {
        this.logger = null;
        this.logger = Logger.getLogger(str);
    }

    public Log4JLogger(Logger logger) {
        this.logger = null;
        this.logger = logger;
    }

    public final void trace(Object obj) {
        this.logger.log(FQCN, Priority.DEBUG, obj, null);
    }

    public final void trace(Object obj, Throwable th) {
        this.logger.log(FQCN, Priority.DEBUG, obj, th);
    }

    public final void debug(Object obj) {
        this.logger.log(FQCN, Priority.DEBUG, obj, null);
    }

    public final void debug(Object obj, Throwable th) {
        this.logger.log(FQCN, Priority.DEBUG, obj, th);
    }

    public final void info(Object obj) {
        this.logger.log(FQCN, Priority.INFO, obj, null);
    }

    public final void info(Object obj, Throwable th) {
        this.logger.log(FQCN, Priority.INFO, obj, th);
    }

    public final void warn(Object obj) {
        this.logger.log(FQCN, Priority.WARN, obj, null);
    }

    public final void warn(Object obj, Throwable th) {
        this.logger.log(FQCN, Priority.WARN, obj, th);
    }

    public final void error(Object obj) {
        this.logger.log(FQCN, Priority.ERROR, obj, null);
    }

    public final void error(Object obj, Throwable th) {
        this.logger.log(FQCN, Priority.ERROR, obj, th);
    }

    public final void fatal(Object obj) {
        this.logger.log(FQCN, Priority.FATAL, obj, null);
    }

    public final void fatal(Object obj, Throwable th) {
        this.logger.log(FQCN, Priority.FATAL, obj, th);
    }

    public final Logger getLogger() {
        return this.logger;
    }

    public final boolean isDebugEnabled() {
        return this.logger.isDebugEnabled();
    }

    public final boolean isErrorEnabled() {
        return this.logger.isEnabledFor(Priority.ERROR);
    }

    public final boolean isFatalEnabled() {
        return this.logger.isEnabledFor(Priority.FATAL);
    }

    public final boolean isInfoEnabled() {
        return this.logger.isInfoEnabled();
    }

    public final boolean isTraceEnabled() {
        return this.logger.isDebugEnabled();
    }

    public final boolean isWarnEnabled() {
        return this.logger.isEnabledFor(Priority.WARN);
    }
}
