package com.sina.weibo.sdk.component;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.packet.d;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.exception.WeiboHttpException;
import com.sina.weibo.sdk.net.HttpManager;
import com.sina.weibo.sdk.net.NetStateManager;
import com.sina.weibo.sdk.net.WeiboParameters;
import com.sina.weibo.sdk.utils.LogUtil;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.umeng.a;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.download.Downloads.Impl;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;

public class GameManager {
    private static final String BOUNDARY;
    public static final String DEFAULT_CHARSET = "UTF-8";
    private static final String HTTP_METHOD_GET = "GET";
    private static final String HTTP_METHOD_POST = "POST";
    private static String INVITATION_ONE_FRINED_URL = null;
    private static String INVITATION_URL = null;
    private static final String MULTIPART_FORM_DATA = "multipart/form-data";
    private static final String TAG = "GameManager";
    private static StringBuffer URL;
    private static String URL_ACHIEVEMENT_ADD_UPDATE;
    private static String URL_ACHIEVEMENT_READ_PLAYER_FRIENDS;
    private static String URL_ACHIEVEMENT_READ_PLAYER_SCORE;
    private static String URL_ACHIEVEMENT_RELATION_ADD_UPDATE;
    private static String URL_ACHIEVEMENT_SCORE_ADD_UPDATE;
    private static String URL_ACHIEVEMENT_USER_GAIN;

    static {
        URL = new StringBuffer("https://api.weibo.com/2/proxy/darwin/graph/game/");
        BOUNDARY = HttpManager.getBoundry();
        URL_ACHIEVEMENT_ADD_UPDATE = URL + "achievement/add.json";
        URL_ACHIEVEMENT_RELATION_ADD_UPDATE = URL + "achievement/gain/add.json";
        URL_ACHIEVEMENT_SCORE_ADD_UPDATE = URL + "score/add.json";
        URL_ACHIEVEMENT_READ_PLAYER_SCORE = URL + "score/read_player.json";
        URL_ACHIEVEMENT_READ_PLAYER_FRIENDS = URL + "score/read_player_friends.json";
        URL_ACHIEVEMENT_USER_GAIN = URL + "achievement/user_gain.json";
        INVITATION_URL = "http://widget.weibo.com/invitation/app.php?";
        INVITATION_ONE_FRINED_URL = "http://widget.weibo.com/invitation/appinfo.php?";
    }

    public static String AddOrUpdateGameAchievement(Context context, WeiboParameters weiboParameters) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        weiboParameters.put("updated_time", simpleDateFormat.format(date));
        if (TextUtils.isEmpty((String) weiboParameters.get(Impl.COLUMN_CREATE_TIME))) {
            weiboParameters.put(Impl.COLUMN_CREATE_TIME, simpleDateFormat.format(date));
        }
        String readRsponse = HttpManager.readRsponse(requestHttpExecute(context, URL_ACHIEVEMENT_ADD_UPDATE, HTTP_METHOD_POST, weiboParameters));
        LogUtil.d(TAG, new StringBuilder("Response : ").append(readRsponse).toString());
        return readRsponse;
    }

    public static String addOrUpdateGameAchievementRelation(Context context, WeiboParameters weiboParameters) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        weiboParameters.put("updated_time", simpleDateFormat.format(date));
        if (TextUtils.isEmpty((String) weiboParameters.get(Impl.COLUMN_CREATE_TIME))) {
            weiboParameters.put(Impl.COLUMN_CREATE_TIME, simpleDateFormat.format(date));
        }
        String readRsponse = HttpManager.readRsponse(requestHttpExecute(context, URL_ACHIEVEMENT_RELATION_ADD_UPDATE, HTTP_METHOD_POST, weiboParameters));
        LogUtil.d(TAG, new StringBuilder("Response : ").append(readRsponse).toString());
        return readRsponse;
    }

    public static String addOrUpdateAchievementScore(Context context, String str, String str2, String str3, String str4, String str5) {
        WeiboParameters weiboParameters = new WeiboParameters(a.d);
        if (!TextUtils.isEmpty(str)) {
            weiboParameters.put(Constants.PARAM_ACCESS_TOKEN, str);
        }
        if (!TextUtils.isEmpty(str2)) {
            weiboParameters.put(SocialConstants.PARAM_SOURCE, str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            weiboParameters.put(WBConstants.GAME_PARAMS_GAME_ID, str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            weiboParameters.put(ParamKey.UID, str4);
        }
        if (!TextUtils.isEmpty(str5)) {
            weiboParameters.put(WBConstants.GAME_PARAMS_SCORE, str5);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        weiboParameters.put("updated_time", simpleDateFormat.format(date));
        if (TextUtils.isEmpty((String) weiboParameters.get(Impl.COLUMN_CREATE_TIME))) {
            weiboParameters.put(Impl.COLUMN_CREATE_TIME, simpleDateFormat.format(date));
        }
        String readRsponse = HttpManager.readRsponse(requestHttpExecute(context, URL_ACHIEVEMENT_SCORE_ADD_UPDATE, HTTP_METHOD_POST, weiboParameters));
        LogUtil.d(TAG, new StringBuilder("Response : ").append(readRsponse).toString());
        return readRsponse;
    }

    public static String readPlayerScoreInfo(Context context, String str, String str2, String str3, String str4) {
        WeiboParameters weiboParameters = new WeiboParameters(a.d);
        if (!TextUtils.isEmpty(str)) {
            weiboParameters.put(Constants.PARAM_ACCESS_TOKEN, str);
        }
        if (!TextUtils.isEmpty(str2)) {
            weiboParameters.put(SocialConstants.PARAM_SOURCE, str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            weiboParameters.put(WBConstants.GAME_PARAMS_GAME_ID, str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            weiboParameters.put(ParamKey.UID, str4);
        }
        String readRsponse = HttpManager.readRsponse(requestHttpExecute(context, URL_ACHIEVEMENT_READ_PLAYER_SCORE, HTTP_METHOD_GET, weiboParameters));
        LogUtil.d(TAG, new StringBuilder("Response : ").append(readRsponse).toString());
        return readRsponse;
    }

    public static String readPlayerFriendsScoreInfo(Context context, String str, String str2, String str3, String str4) {
        WeiboParameters weiboParameters = new WeiboParameters(a.d);
        if (!TextUtils.isEmpty(str)) {
            weiboParameters.put(Constants.PARAM_ACCESS_TOKEN, str);
        }
        if (!TextUtils.isEmpty(str2)) {
            weiboParameters.put(SocialConstants.PARAM_SOURCE, str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            weiboParameters.put(WBConstants.GAME_PARAMS_GAME_ID, str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            weiboParameters.put(ParamKey.UID, str4);
        }
        weiboParameters.put(Impl.COLUMN_CREATE_TIME, new Timestamp(new Date().getTime()));
        String readRsponse = HttpManager.readRsponse(requestHttpExecute(context, URL_ACHIEVEMENT_READ_PLAYER_FRIENDS, HTTP_METHOD_GET, weiboParameters));
        LogUtil.d(TAG, new StringBuilder("Response : ").append(readRsponse).toString());
        return readRsponse;
    }

    public static String readPlayerAchievementGain(Context context, String str, String str2, String str3, String str4) {
        WeiboParameters weiboParameters = new WeiboParameters(a.d);
        if (!TextUtils.isEmpty(str)) {
            weiboParameters.put(Constants.PARAM_ACCESS_TOKEN, str);
        }
        if (!TextUtils.isEmpty(str2)) {
            weiboParameters.put(SocialConstants.PARAM_SOURCE, str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            weiboParameters.put(WBConstants.GAME_PARAMS_GAME_ID, str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            weiboParameters.put(ParamKey.UID, str4);
        }
        weiboParameters.put(Impl.COLUMN_CREATE_TIME, new Timestamp(new Date().getTime()));
        String readRsponse = HttpManager.readRsponse(requestHttpExecute(context, URL_ACHIEVEMENT_USER_GAIN, HTTP_METHOD_GET, weiboParameters));
        LogUtil.d(TAG, new StringBuilder("Response : ").append(readRsponse).toString());
        return readRsponse;
    }

    public void invatationWeiboFriendsByList(Context context, String str, String str2, String str3, WeiboAuthListener weiboAuthListener) {
        WeiboParameters weiboParameters = new WeiboParameters(str2);
        weiboParameters.put(Constants.PARAM_ACCESS_TOKEN, str);
        weiboParameters.put(SocialConstants.PARAM_SOURCE, str2);
        String toString = new StringBuilder(String.valueOf(INVITATION_URL.toString())).append(weiboParameters.encodeUrl()).toString();
        GameRequestParam gameRequestParam = new GameRequestParam(context);
        gameRequestParam.setAppKey(str2);
        gameRequestParam.setToken(str);
        gameRequestParam.setLauncher(BrowserLauncher.GAME);
        gameRequestParam.setUrl(toString);
        gameRequestParam.setAuthListener(weiboAuthListener);
        Intent intent = new Intent(context, WeiboSdkBrowser.class);
        Bundle createRequestParamBundle = gameRequestParam.createRequestParamBundle();
        createRequestParamBundle.putString("key_specify_title", str3);
        intent.putExtras(createRequestParamBundle);
        context.startActivity(intent);
    }

    public void invatationWeiboFriendsInOnePage(Context context, String str, String str2, String str3, WeiboAuthListener weiboAuthListener, ArrayList<String> arrayList) {
        String str4;
        StringBuffer stringBuffer = new StringBuffer();
        if (arrayList != null) {
            for (int i = 0; i < arrayList.size(); i++) {
                str4 = (String) arrayList.get(i);
                if (i == 0) {
                    stringBuffer.append(str4);
                } else {
                    stringBuffer.append(new StringBuilder(MiPushClient.ACCEPT_TIME_SEPARATOR).append(str4).toString());
                }
            }
        }
        WeiboParameters weiboParameters = new WeiboParameters(str2);
        weiboParameters.put(Constants.PARAM_ACCESS_TOKEN, str);
        weiboParameters.put(SocialConstants.PARAM_SOURCE, str2);
        str4 = new StringBuilder(String.valueOf(INVITATION_ONE_FRINED_URL.toString())).append(weiboParameters.encodeUrl()).append("&uids=").append(stringBuffer.toString()).toString();
        GameRequestParam gameRequestParam = new GameRequestParam(context);
        gameRequestParam.setAppKey(str2);
        gameRequestParam.setToken(str);
        gameRequestParam.setLauncher(BrowserLauncher.GAME);
        gameRequestParam.setUrl(str4);
        gameRequestParam.setAuthListener(weiboAuthListener);
        Intent intent = new Intent(context, WeiboSdkBrowser.class);
        Bundle createRequestParamBundle = gameRequestParam.createRequestParamBundle();
        createRequestParamBundle.putString("key_specify_title", str3);
        intent.putExtras(createRequestParamBundle);
        context.startActivity(intent);
    }

    private static HttpResponse requestHttpExecute(Context context, String str, String str2, WeiboParameters weiboParameters) {
        Throwable e;
        HttpClient httpClient;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            HttpClient newHttpClient = HttpManager.getNewHttpClient();
            try {
                HttpUriRequest httpGet;
                newHttpClient.getParams().setParameter("http.route.default-proxy", NetStateManager.getAPN());
                HttpResponse execute = newHttpClient.execute(httpGet);
                int statusCode = execute.getStatusLine().getStatusCode();
                if (statusCode != 200) {
                    throw new WeiboHttpException(HttpManager.readRsponse(execute), statusCode);
                }
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e2) {
                    }
                }
                HttpManager.shutdownHttpClient(newHttpClient);
                return execute;
            } catch (IOException e3) {
                e = e3;
                httpClient = newHttpClient;
                try {
                    throw new WeiboException(e);
                } catch (Throwable th) {
                    e = th;
                    newHttpClient = httpClient;
                }
            } catch (Throwable th2) {
                e = th2;
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e4) {
                    }
                }
                HttpManager.shutdownHttpClient(newHttpClient);
                throw e;
            }
            if (str2.equals(HTTP_METHOD_GET)) {
                String toString = new StringBuilder(String.valueOf(str)).append("?").append(weiboParameters.encodeUrl()).toString();
                httpGet = new HttpGet(toString);
                LogUtil.d(TAG, new StringBuilder("requestHttpExecute GET Url : ").append(toString).toString());
            } else if (str2.equals(HTTP_METHOD_POST)) {
                LogUtil.d(TAG, new StringBuilder("requestHttpExecute POST Url : ").append(str).toString());
                HttpPost httpPost = new HttpPost(str);
                OutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                OutputStream outputStream;
                try {
                    if (weiboParameters.hasBinaryData()) {
                        httpPost.setHeader("Content-Type", new StringBuilder("multipart/form-data; boundary=").append(BOUNDARY).toString());
                        HttpManager.buildParams(byteArrayOutputStream2, weiboParameters);
                    } else {
                        Object obj = weiboParameters.get(d.d);
                        if (obj == null || !(obj instanceof String)) {
                            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
                        } else {
                            weiboParameters.remove(d.d);
                            httpPost.setHeader("Content-Type", (String) obj);
                        }
                        String encodeUrl = weiboParameters.encodeUrl();
                        LogUtil.d(TAG, new StringBuilder("requestHttpExecute POST postParam : ").append(encodeUrl).toString());
                        byteArrayOutputStream2.write(encodeUrl.getBytes(DEFAULT_CHARSET));
                    }
                    httpPost.setEntity(new ByteArrayEntity(byteArrayOutputStream2.toByteArray()));
                    HttpPost httpPost2 = httpPost;
                    outputStream = byteArrayOutputStream2;
                } catch (IOException e5) {
                    e = e5;
                    outputStream = byteArrayOutputStream2;
                    httpClient = newHttpClient;
                    throw new WeiboException(e);
                } catch (Throwable th3) {
                    e = th3;
                    outputStream = byteArrayOutputStream2;
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    HttpManager.shutdownHttpClient(newHttpClient);
                    throw e;
                }
            } else if (str2.equals("DELETE")) {
                httpGet = new HttpDelete(str);
            } else {
                httpGet = null;
            }
        } catch (IOException e6) {
            e = e6;
            ByteArrayOutputStream byteArrayOutputStream3 = null;
            throw new WeiboException(e);
        } catch (Throwable th4) {
            e = th4;
            newHttpClient = null;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            HttpManager.shutdownHttpClient(newHttpClient);
            throw e;
        }
    }
}
