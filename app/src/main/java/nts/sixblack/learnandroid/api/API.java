package nts.sixblack.learnandroid.api;

import nts.sixblack.learnandroid.form.*;
import nts.sixblack.learnandroid.model.FollowInfo;
import nts.sixblack.learnandroid.model.ResponObject;
import nts.sixblack.learnandroid.model.User;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface API {
    String BASE_URL = "https://nts-sixblack-api-hexa.herokuapp.com/";
//    String BASE_URL = "http://54.169.188.10:8080/";

    // POST START

    //feel posts
    @POST("posts/like/{postsId}")
    Call<ResponObject> feelPosts(@Header("Authorization") String token, @Path("postsId") long postsId);

    //comment posts
    @POST("posts/comment/")
    Call<ResponObject> commentPosts(@Header("Authorization") String token, @Body CommentForm commentForm);

    //delete comment
    @GET("posts/comment/{commentId}")
    Call<ResponObject> deleteComment(@Header("Authorization") String token, @Path("commentId") String commentId);

    // find posts by id
    @GET("posts/{postsId}")
    Call<ResponObject> findPostsById(@Header("Authorization") String token,@Path("postsId") long postsId);

    //new posts
    @Multipart
    @POST("posts/uploadFile/")
    Call<ResponObject> newPosts(@Header("Authorization") String token,
                                @Part(PostsForm.KEY_CAPTION) RequestBody caption,
                                @Part MultipartBody.Part image);

    // list post show
    @GET("posts/show/")
    Call<ResponObject> listPostShow(@Header("Authorization") String token);

    //delete posts
    @DELETE("posts/{postsId}")
    Call<ResponObject> deletePosts(@Header("Authorization") String token,@Path("postsId") long postsId);

    // my lists posts
    @GET("posts/myPosts/")
    Call<ResponObject> listsPostsOfUser(@Header("Authorization") String token);

    //list comment of posts
    @GET("posts/listComment/{postsId}")
    Call<ResponObject> listCommentOfPosts(@Header("Authorization") String token, @Path("postsId") long postsId);

    // POST END

    // FOLLOW START

    // follow
    @GET("follow/send/{userId}")
    Call<ResponObject> follow(@Header("Authorization") String token, @Path("userId") long userId);

    //list request
    @GET("follow/request/")
    Call<ResponObject> listRequest(@Header("Authorization") String token);

    //accept follow
    @GET("follow/accept/{followId}")
    Call<ResponObject> acceptFollow(@Header("Authorization") String token, @Path("followId") long followId);

    //change follow status
    @GET("follow/followStatus/")
    Call<ResponObject> changeFollowStatus(@Header("Authorization") String token);

    // list follower
    @GET("follow/follower/")
    Call<ResponObject> listFollower(@Header("Authorization") String token);

    // list following
    @GET("follow/following/")
    Call<ResponObject> listFollowing(@Header("Authorization") String token);

    //refuse follow
    @GET("follow/delete/{followId}")
    Call<ResponObject> refuseFollow(@Header("Authorization") String token, @Path("followId") String followId);

    // FOLLOW END

    // USER START

    //update Avatar
    @Multipart
    @POST("user/updateAvatar/")
    Call<ResponObject> updateAvatar(@Header("Authorization") String token,
                                @Part MultipartBody.Part image);

    // delte Avatar
    @DELETE("user/deleteAvatar/")
    Call<ResponObject> deleteAvatar(@Header("Authorization") String token);

    //update Background
    @Multipart
    @POST("user/updateBackground/")
    Call<ResponObject> updateBackground(@Header("Authorization") String token,
                                    @Part MultipartBody.Part image);

    // delte Background
    @DELETE("user/deleteBackground/")
    Call<ResponObject> deleteBackground(@Header("Authorization") String token);

    // change name
    @POST("user/changeName/")
    Call<ResponObject> changeName(@Header("Authorization") String token, @Body UserNameForm userNameForm);

    // USER END


    //login
    @POST("user/login/")
    Call<ResponObject> login(@Body LoginForm loginForm);

    // register
    @POST("user/register/")
    Call<ResponObject> register(@Body RegisterForm registerForm);

    //find user by id
    @GET("user/{userId}")
    Call<ResponObject> findUserById(@Header("Authorization") String token, @Path("userId") String userId);

    // find user like name
    @GET("user/find/name={name}")
    Call<ResponObject> listUserByName(@Path("name")String name);





}
