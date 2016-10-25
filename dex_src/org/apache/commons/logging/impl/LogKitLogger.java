package org.apache.commons.logging.impl;

import org.apache.commons.logging.Log;
import org.apache.log.Hierarchy;
import org.apache.log.Logger;

public final class LogKitLogger implements Log {
    protected Logger logger;

    public LogKitLogger(String str) {
        this.logger = null;
        this.logger = Hierarchy.getDefaultHierarchy().getLoggerFor(str);
    }

    public final void trace(Object obj) {
        debug(obj);
    }

    public final void trace(Object obj, Throwable th) {
        debug(obj, th);
    }

    public final void debug(Object obj) {
        if (obj != null) {
            this.logger.debug(String.valueOf(obj));
        }
    }

    public final void debug(Object obj, Throwable th) {
        if (obj != null) {
            this.logger.debug(String.valueOf(obj), th);
        }
    }

    public final void info(Object obj) {
        if (obj != null) {
            this.logger.info(String.valueOf(obj));
        }
    }

    public final void info(Object obj, Throwable th) {
        if (obj != null) {
            this.logger.info(String.valueOf(obj), th);
        }
    }

    public final void warn(Object obj) {
        if (obj != null) {
            this.logger.warn(String.valueOf(obj));
        }
    }

    public final void warn(Object obj, Throwable th) {
        if (obj != null) {
            this.logger.warn(String.valueOf(obj), th);
        }
    }

    public final void error(Object obj) {
        if (obj != null) {
            this.logger.error(String.valueOf(obj));
        }
    }

    public final void error(Object obj, Throwable th) {
        if (obj != null) {
            this.logger.error(String.valueOf(obj), th);
        }
    }

    public final void fatal(Object obj) {
        if (obj != null) {
            this.logger.fatalError(String.valueOf(obj));
        }
    }

    public final void fatal(Object obj, Throwable th) {
        if (obj != null) {
            this.logger.fatalError(String.valueOf(obj), th);
        }
    }

    public final boolean isDebugEnabled() {
        return this.logger.isDebugEnabled();
    }

    public final boolean isErrorEnabled() {
        return this.logger.isErrorEnabled();
    }

    public final boolean isFatalEnabled() {
        return this.logger.isFatalErrorEnabled();
    }

    public final boolean isInfoEnabled() {
        return this.logger.isInfoEnabled();
    }

    public final boolean isTraceEnabled() {
        return this.logger.isDebugEnabled();
    }

    public final boolean isWarnEnabled() {
        return this.logger.isWarnEnabled();
    }
}
