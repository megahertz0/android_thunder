package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.Locale;

public final class DateTypeAdapter extends TypeAdapter<Date> {
    public static final TypeAdapterFactory FACTORY;
    private final DateFormat enUsFormat;
    private final DateFormat localFormat;

    public DateTypeAdapter() {
        this.enUsFormat = DateFormat.getDateTimeInstance(XZBDevice.DOWNLOAD_LIST_RECYCLE, XZBDevice.DOWNLOAD_LIST_RECYCLE, Locale.US);
        this.localFormat = DateFormat.getDateTimeInstance(XZBDevice.DOWNLOAD_LIST_RECYCLE, XZBDevice.DOWNLOAD_LIST_RECYCLE);
    }

    static {
        FACTORY = new TypeAdapterFactory() {
            public final <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
                return typeToken.getRawType() == Date.class ? new DateTypeAdapter() : null;
            }
        };
    }

    public final Date read(JsonReader jsonReader) throws IOException {
        if (jsonReader.peek() != JsonToken.NULL) {
            return deserializeToDate(jsonReader.nextString());
        }
        jsonReader.nextNull();
        return null;
    }

    private synchronized Date deserializeToDate(String str) {
        Date parse;
        try {
            parse = this.localFormat.parse(str);
        } catch (ParseException e) {
            try {
                parse = this.enUsFormat.parse(str);
            } catch (ParseException e2) {
                try {
                    parse = ISO8601Utils.parse(str, new ParsePosition(0));
                } catch (Throwable e3) {
                    throw new JsonSyntaxException(str, e3);
                } catch (Throwable th) {
                }
            }
        }
        return parse;
    }

    public final synchronized void write(JsonWriter jsonWriter, Date date) throws IOException {
        if (date == null) {
            jsonWriter.nullValue();
        } else {
            jsonWriter.value(this.enUsFormat.format(date));
        }
    }
}
