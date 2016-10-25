package com.umeng.socialize.editorpage.location;

import android.location.Location;
import android.os.AsyncTask;
import com.umeng.socialize.utils.Log;

// compiled from: GetLocationTask.java
public class b extends AsyncTask<Void, Void, Location> {
    private static final String b = "Location";
    private a a;

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Void[]) objArr);
    }

    public b(a aVar) {
        this.a = aVar;
    }

    protected Location a(Void... voidArr) {
        int i = 0;
        while (i < 20) {
            try {
                if (isCancelled()) {
                    return null;
                }
                Location a = a();
                if (a != null) {
                    return a;
                }
                Thread.sleep(500);
                i++;
            } catch (InterruptedException e) {
                return null;
            }
        }
        return null;
    }

    private Location a() {
        Location b = this.a.b();
        if (b != null) {
            return b;
        }
        Log.d(b, "Fetch gps info from default failed,then fetch form network..");
        this.a.a("network");
        b = this.a.b();
        this.a.a(null);
        return b;
    }
}
