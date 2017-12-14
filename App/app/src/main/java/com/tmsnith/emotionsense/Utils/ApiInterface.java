package com.tmsnith.emotionsense.Utils;

import com.tmsnith.emotionsense.RequestModels.DocumentModel;
import com.tmsnith.emotionsense.RequestModels.ImageUrlModel;
import com.tmsnith.emotionsense.Resoponse.FileUploadResponseModel;
import com.tmsnith.emotionsense.Resoponse.SingleFaceImageResponse;
import com.tmsnith.emotionsense.Resoponse.TextResponse;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;


public interface ApiInterface {

//    @GET("/profile/{id}")
//    Call<UserModel> getProfile(@Path("id") String id);
//
//    @POST("/post")
//    @FormUrlEncoded
//
//    Call<UploadPostActivity.Response1> sendPostData(@Field("title") String title, @Field("description") String description,
//                                                    @Field("credit") int credit
//            , @Field("tags") String tag, @Field("picPostUrl") String picPostUrl, @Field("isPublic") String isPublic, @Field("authorId") String authorid,
//                                                    @Field("circlesId") String circlesId);
//
//    @POST("/user")
//    @FormUrlEncoded
//    Call<in.coders.fsociety.taskbuddy.Fragments.LoginFragment.UserSentResponse> sendUserData(
//            @Field("name") String name,
//            @Field("id") String id,
//            @Field("picUrl") String picUrl,
//            @Field("email") String email);
//
//    @GET("/profile/post/{id}")
//    Call<ProfilePostModel> getProfilePosts(@Path("id") String id);
//
//
//    @GET("/search")
//    Call<SearchResponse> getSearchResult(@Query("userId") String userID, @Query("keyword") String keyword);
//    @GET("/main/post")
//    Call<MainPostModel> getMainPosts(@Query("id") String id);
//
//    @FormUrlEncoded
//    @POST("/register")
//    Call<RegisterResponse> register(@Field("userId") String userId, @Field("postId") int postId);
//
//
//    @FormUrlEncoded
//    @POST("/friend/new/{userId}")
//    Call<RegisterResponse> addAsFriend(@Path("userId") String userId, @Field("friendId") String friendId);
//
//
//  @GET("/profile/circles/{id}")
//    Call<ProfileCircleResponse> getCircleList(@Path("id") String id);
/*
    @Multipart
    @POST("index/")
    Call<ResponseBody> uploadMultipleFilesDynamic(
            @Part("description") RequestBody description,
            @Part("file1") MultipartBody.Part file1,
            @Part("file") List<MultipartBody.Part> file);

*/
    @Multipart
    @POST("index/")
   Call<FileUploadResponseModel> postFileAndGetUrl(@Part("files\"; filename=\"IMG ") ArrayList<RequestBody> file);
   //Call<FileUploadResponseModel> postFileAndGetUrl(@Part("files") MultipartBody.Part files);
 }


