package com.xunlei.tdlive;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Images.Thumbnails;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.util.d;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import org.android.spdy.SpdyProtocol;

public class PhotoSelectActivity extends BaseActivity implements LoaderCallbacks<Cursor>, OnClickListener, OnScrollListener, OnItemClickListener {
    private CursorAdapter a;
    private HashSet<Integer> b;
    private File c;
    private File d;
    private boolean e;
    private boolean f;
    private int g;
    private String h;
    private int i;
    private boolean j;

    public PhotoSelectActivity() {
        this.b = new HashSet();
        this.c = null;
        this.d = null;
        this.e = false;
        this.f = false;
        this.g = 1;
        this.h = "\u62cd\u6444";
        this.j = false;
    }

    public /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        a(loader, (Cursor) obj);
    }

    private void a() {
        Date date = new Date(System.currentTimeMillis());
        this.c = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath(), new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss", Locale.US).format(date) + ".jpg");
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE", null);
        intent.putExtra("output", Uri.fromFile(this.c));
        startActivityForResult(intent, R.styleable.AppCompatTheme_buttonStyle);
    }

    private void a(String str, int i) {
        Date date = new Date(System.currentTimeMillis());
        this.d = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath(), new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss", Locale.US).format(date) + ".jpg");
        Intent intent = new Intent(this, CropImageActivity.class);
        intent.putExtra("input", str);
        intent.putExtra("output", Uri.fromFile(this.d));
        startActivityForResult(intent, i);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.xllive_activity_photo_select);
        this.g = getIntent().getIntExtra("max_select_num", 1);
        this.e = getIntent().getBooleanExtra("start_image_capture", false);
        this.f = getIntent().getBooleanExtra("image_crop", false);
        this.h = getIntent().getStringExtra("capture_image_tip");
        if (this.h == null) {
            this.h = BuildConfig.VERSION_NAME;
        }
        findViewById(R.id.action_layout).setVisibility(this.g > 1 ? 0 : SpdyProtocol.PUBKEY_SEQ_ADASH);
        findViewById(R.id.ok).setOnClickListener(this);
        findViewById(R.id.cancel).setOnClickListener(this);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        this.i = (int) (((float) (displayMetrics.widthPixels / 3)) - d.a(this, 4.0f));
        GridView gridView = (GridView) findViewById(R.id.photos);
        gridView.setOnScrollListener(this);
        gridView.setOnItemClickListener(this);
        dr drVar = new dr(this, this, null, false);
        this.a = drVar;
        gridView.setAdapter(drVar);
        if (this.e) {
            a();
        } else {
            getSupportLoaderManager().initLoader(0, null, this);
        }
    }

    protected void onResume() {
        super.onResume();
        setTitle("\u6240\u6709\u7167\u7247");
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        ArrayList arrayList;
        if (i == 100) {
            if (this.c == null || i2 != -1) {
                if (this.e) {
                    finish();
                }
            } else if (this.f) {
                a(Uri.fromFile(this.c).toString(), (int) R.styleable.AppCompatTheme_checkboxStyle);
            } else {
                arrayList = new ArrayList();
                arrayList.add(Uri.fromFile(this.c).toString());
                setResult(-1, new Intent().putStringArrayListExtra("images", arrayList));
                finish();
                this.c = null;
            }
        } else if (i != 101 && i != 102) {
        } else {
            if (this.d != null && i2 == -1) {
                arrayList = new ArrayList();
                arrayList.add(Uri.fromFile(this.d).toString());
                setResult(-1, new Intent().putStringArrayListExtra("images", arrayList));
                finish();
                this.d = null;
            } else if (i == 102) {
                finish();
            }
        }
    }

    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this, Thumbnails.EXTERNAL_CONTENT_URI, null, null, null, null);
    }

    public void a(Loader<Cursor> loader, Cursor cursor) {
        this.a.swapCursor(cursor);
    }

    public void onLoaderReset(Loader<Cursor> loader) {
    }

    public void onClick(View view) {
        if (view.getId() == R.id.ok) {
            b(view);
        } else if (view.getId() == R.id.cancel) {
            a(view);
        }
    }

    private void a(View view) {
        setResult(0);
        finish();
    }

    private void b(View view) {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            try {
                Cursor cursor = (Cursor) this.a.getItem(((Integer) it.next()).intValue() + 1);
                long j = cursor.getLong(cursor.getColumnIndex("image_id"));
                try {
                    cursor = getContentResolver().query(Media.EXTERNAL_CONTENT_URI, null, "_id=?", new String[]{String.valueOf(j)}, null);
                    try {
                        cursor.moveToFirst();
                        arrayList.add(cursor.getString(cursor.getColumnIndex("_data")));
                    } catch (Exception e) {
                    }
                } catch (Exception e2) {
                    cursor = null;
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Exception e3) {
            }
        }
        if (arrayList.size() <= 0) {
            a(view);
        } else if (this.f && arrayList.size() == 1) {
            a((String) arrayList.get(0), (int) R.styleable.AppCompatTheme_buttonStyleSmall);
        } else {
            setResult(-1, new Intent().putStringArrayListExtra("images", arrayList));
            finish();
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (i == 0) {
            a();
        } else if (this.g == 1) {
            this.b.clear();
            this.b.add(Integer.valueOf(i - 1));
            b(findViewById(R.id.ok));
        } else {
            if (this.b.contains(Integer.valueOf(i - 1))) {
                this.b.remove(Integer.valueOf(i - 1));
            } else if (this.b.size() < this.g) {
                this.b.add(Integer.valueOf(i - 1));
            } else {
                return;
            }
            this.a.notifyDataSetChanged();
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        this.j = i == 2;
        if (!this.j) {
            this.a.notifyDataSetChanged();
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }
}
