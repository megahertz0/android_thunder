package org.apache.commons.logging.impl;

import org.apache.commons.logging.Log;

public final class NoOpLog implements Log {
    public NoOpLog(String str) {
    }

    public final void trace(Object obj) {
    }

    public final void trace(Object obj, Throwable th) {
    }

    public final void debug(Object obj) {
    }

    public final void debug(Object obj, Throwable th) {
    }

    public final void info(Object obj) {
    }

    public final void info(Object obj, Throwable th) {
    }

    public final void warn(Object obj) {
    }

    public final void warn(Object obj, Throwable th) {
    }

    public final void error(Object obj) {
    }

    public final void error(Object obj, Throwable th) {
    }

    public final void fatal(Object obj) {
    }

    public final void fatal(Object obj, Throwable th) {
    }

    public final boolean isDebugEnabled() {
        return false;
    }

    public final boolean isErrorEnabled() {
        return false;
    }

    public final boolean isFatalEnabled() {
        return false;
    }

    public final boolean isInfoEnabled() {
        return false;
    }

    public final boolean isTraceEnabled() {
        return false;
    }

    public final boolean isWarnEnabled() {
        return false;
    }
}
