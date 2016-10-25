package org.apache.commons.logging.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;

public final class Jdk14Logger implements Log {
    protected Logger logger;

    public Jdk14Logger(String str) {
        this.logger = null;
        this.logger = Logger.getLogger(str);
    }

    private void log(Level level, String str, Throwable th) {
        if (this.logger.isLoggable(level)) {
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            String str2 = "unknown";
            String str3 = "unknown";
            if (stackTrace != null && stackTrace.length > 2) {
                StackTraceElement stackTraceElement = stackTrace[2];
                str2 = stackTraceElement.getClassName();
                str3 = stackTraceElement.getMethodName();
            }
            if (th == null) {
                this.logger.logp(level, str2, str3, str);
            } else {
                this.logger.logp(level, str2, str3, str, th);
            }
        }
    }

    public final void debug(Object obj) {
        log(Level.FINE, String.valueOf(obj), null);
    }

    public final void debug(Object obj, Throwable th) {
        log(Level.FINE, String.valueOf(obj), th);
    }

    public final void error(Object obj) {
        log(Level.SEVERE, String.valueOf(obj), null);
    }

    public final void error(Object obj, Throwable th) {
        log(Level.SEVERE, String.valueOf(obj), th);
    }

    public final void fatal(Object obj) {
        log(Level.SEVERE, String.valueOf(obj), null);
    }

    public final void fatal(Object obj, Throwable th) {
        log(Level.SEVERE, String.valueOf(obj), th);
    }

    public final Logger getLogger() {
        return this.logger;
    }

    public final void info(Object obj) {
        log(Level.INFO, String.valueOf(obj), null);
    }

    public final void info(Object obj, Throwable th) {
        log(Level.INFO, String.valueOf(obj), th);
    }

    public final boolean isDebugEnabled() {
        return this.logger.isLoggable(Level.FINE);
    }

    public final boolean isErrorEnabled() {
        return this.logger.isLoggable(Level.SEVERE);
    }

    public final boolean isFatalEnabled() {
        return this.logger.isLoggable(Level.SEVERE);
    }

    public final boolean isInfoEnabled() {
        return this.logger.isLoggable(Level.INFO);
    }

    public final boolean isTraceEnabled() {
        return this.logger.isLoggable(Level.FINEST);
    }

    public final boolean isWarnEnabled() {
        return this.logger.isLoggable(Level.WARNING);
    }

    public final void trace(Object obj) {
        log(Level.FINEST, String.valueOf(obj), null);
    }

    public final void trace(Object obj, Throwable th) {
        log(Level.FINEST, String.valueOf(obj), th);
    }

    public final void warn(Object obj) {
        log(Level.WARNING, String.valueOf(obj), null);
    }

    public final void warn(Object obj, Throwable th) {
        log(Level.WARNING, String.valueOf(obj), th);
    }
}
