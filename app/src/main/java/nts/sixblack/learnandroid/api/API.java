package nts.sixblack.learnandroid.api;

import nts.sixblack.learnandroid.form.RegisterForm;
import nts.sixblack.learnandroid.model.FollowInfo;
import nts.sixblack.learnandroid.model.ResponObject;
import nts.sixblack.learnandroid.model.User;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface API {
    String BASE_URL = "https://nts-sixblack-api-hexa.herokuapp.com/";

    @GET("user/find/name={name}/")
    Call<ResponObject> listUserByName(@Path("name")String name);

    @POST("user/register/")
    Call<ResponObject> register(@Body RegisterForm registerForm);

}
