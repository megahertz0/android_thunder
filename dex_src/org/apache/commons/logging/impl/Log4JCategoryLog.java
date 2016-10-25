package org.apache.commons.logging.impl;

import org.apache.commons.logging.Log;
import org.apache.log4j.Category;
import org.apache.log4j.Priority;

public final class Log4JCategoryLog implements Log {
    private static final String FQCN;
    static Class class$org$apache$commons$logging$impl$Log4JCategoryLog;
    private Category category;

    static {
        Class class$;
        if (class$org$apache$commons$logging$impl$Log4JCategoryLog == null) {
            class$ = class$("org.apache.commons.logging.impl.Log4JCategoryLog");
            class$org$apache$commons$logging$impl$Log4JCategoryLog = class$;
        } else {
            class$ = class$org$apache$commons$logging$impl$Log4JCategoryLog;
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

    public Log4JCategoryLog() {
        this.category = null;
    }

    public Log4JCategoryLog(String str) {
        this.category = null;
        this.category = Category.getInstance(str);
    }

    public Log4JCategoryLog(Category category) {
        this.category = null;
        this.category = category;
    }

    public final void trace(Object obj) {
        this.category.log(FQCN, Priority.DEBUG, obj, null);
    }

    public final void trace(Object obj, Throwable th) {
        this.category.log(FQCN, Priority.DEBUG, obj, th);
    }

    public final void debug(Object obj) {
        this.category.log(FQCN, Priority.DEBUG, obj, null);
    }

    public final void debug(Object obj, Throwable th) {
        this.category.log(FQCN, Priority.DEBUG, obj, th);
    }

    public final void info(Object obj) {
        this.category.log(FQCN, Priority.INFO, obj, null);
    }

    public final void info(Object obj, Throwable th) {
        this.category.log(FQCN, Priority.INFO, obj, th);
    }

    public final void warn(Object obj) {
        this.category.log(FQCN, Priority.WARN, obj, null);
    }

    public final void warn(Object obj, Throwable th) {
        this.category.log(FQCN, Priority.WARN, obj, th);
    }

    public final void error(Object obj) {
        this.category.log(FQCN, Priority.ERROR, obj, null);
    }

    public final void error(Object obj, Throwable th) {
        this.category.log(FQCN, Priority.ERROR, obj, th);
    }

    public final void fatal(Object obj) {
        this.category.log(FQCN, Priority.FATAL, obj, null);
    }

    public final void fatal(Object obj, Throwable th) {
        this.category.log(FQCN, Priority.FATAL, obj, th);
    }

    public final Category getCategory() {
        return this.category;
    }

    public final boolean isDebugEnabled() {
        return this.category.isDebugEnabled();
    }

    public final boolean isErrorEnabled() {
        return this.category.isEnabledFor(Priority.ERROR);
    }

    public final boolean isFatalEnabled() {
        return this.category.isEnabledFor(Priority.FATAL);
    }

    public final boolean isInfoEnabled() {
        return this.category.isInfoEnabled();
    }

    public final boolean isTraceEnabled() {
        return this.category.isDebugEnabled();
    }

    public final boolean isWarnEnabled() {
        return this.category.isEnabledFor(Priority.WARN);
    }
}
