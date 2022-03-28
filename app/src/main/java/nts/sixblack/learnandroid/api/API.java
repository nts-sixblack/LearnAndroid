package nts.sixblack.learnandroid.api;

import nts.sixblack.learnandroid.form.LoginForm;
import nts.sixblack.learnandroid.form.PostsForm;
import nts.sixblack.learnandroid.form.RegisterForm;
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


    @GET("user/find/name={name}/")
    Call<ResponObject> listUserByName(@Path("name")String name);

    @POST("user/register/")
    Call<ResponObject> register(@Body RegisterForm registerForm);

    @POST("user/login/")
    Call<ResponObject> login(@Body LoginForm loginForm);

    @GET("posts/{postsId}/")
    Call<ResponObject> findPosts(@Header("Authorization") String token,@Path("postsId") String postsId);

    @Multipart
    @POST("posts/uploadFile/")
    Call<ResponObject> newPosts(@Header("Authorization") String token,
                                @Part(PostsForm.KEY_CAPTION) RequestBody caption,
                                @Part MultipartBody.Part image,
                                @Part(PostsForm.KEY_USER_ID) RequestBody userId);
}
