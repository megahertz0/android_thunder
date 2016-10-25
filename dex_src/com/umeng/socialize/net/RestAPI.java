package com.umeng.socialize.net;

import com.umeng.socialize.net.base.SocializeClient;
import com.umeng.socialize.net.base.SocializeReseponse;

public class RestAPI {
    private static SocializeClient a;

    static {
        a = new SocializeClient();
    }

    public static ActionBarResponse queryShareId(ActionBarRequest actionBarRequest) {
        return (ActionBarResponse) a.execute(actionBarRequest);
    }

    public static ExpiresInResponse queryExpire(ExpiresInRequest expiresInRequest) {
        return (ExpiresInResponse) a.execute(expiresInRequest);
    }

    public static GetPlatformKeyResponse queryPlatformKey(GetPlatformKeyRequest getPlatformKeyRequest) {
        return (GetPlatformKeyResponse) a.execute(getPlatformKeyRequest);
    }

    public static PlatformTokenUploadResponse uploadPlatformToken(PlatformTokenUploadReq platformTokenUploadReq) {
        return (PlatformTokenUploadResponse) a.execute(platformTokenUploadReq);
    }

    public static UpdatePlatformKeyResponse updatePlatformKey(UpdatePlatformKeyRequest updatePlatformKeyRequest) {
        return (UpdatePlatformKeyResponse) a.execute(updatePlatformKeyRequest);
    }

    public static UploadImageResponse uploadImage(UploadImageRequest uploadImageRequest) {
        return (UploadImageResponse) a.execute(uploadImageRequest);
    }

    public static UserInfoResponse getUserInfo(UserInfoRequest userInfoRequest) {
        return (UserInfoResponse) a.execute(userInfoRequest);
    }

    public static SocializeReseponse deleteOAuth(ShareDeleteOauthRequest shareDeleteOauthRequest) {
        return a.execute(shareDeleteOauthRequest);
    }

    public static ShareFriendsResponse queryFriendsList(ShareFriendsRequest shareFriendsRequest) {
        return (ShareFriendsResponse) a.execute(shareFriendsRequest);
    }

    public static ShareMultiResponse doShare(ShareMultiReqeust shareMultiReqeust) {
        return (ShareMultiResponse) a.execute(shareMultiReqeust);
    }

    public static SocializeReseponse doShare(SharePostRequest sharePostRequest) {
        return a.execute(sharePostRequest);
    }

    public static ShareMultiFollowResponse doFollow(ShareMultiFollowRequest shareMultiFollowRequest) {
        return (ShareMultiFollowResponse) a.execute(shareMultiFollowRequest);
    }

    public static UrlResponse uploadUrl(UrlRequest urlRequest) {
        return (UrlResponse) a.execute(urlRequest);
    }
}
