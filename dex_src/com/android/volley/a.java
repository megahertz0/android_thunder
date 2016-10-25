package com.android.volley;

import android.content.Intent;

// compiled from: AuthFailureError.java
public final class a extends w {
    private Intent c;

    public a(l lVar) {
        super(lVar);
    }

    public final String getMessage() {
        return this.c != null ? "User needs to (re)enter credentials." : super.getMessage();
    }
}
