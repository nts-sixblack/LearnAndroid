package nts.sixblack.learnandroid.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import nts.sixblack.learnandroid.MainActivity;
import nts.sixblack.learnandroid.api.RetrofitClient;
import nts.sixblack.learnandroid.api.TokenResponse;
import nts.sixblack.learnandroid.form.CommentForm;
import nts.sixblack.learnandroid.form.LoginForm;
import nts.sixblack.learnandroid.form.RegisterForm;
import nts.sixblack.learnandroid.layout_custom.CustomListPosts;
import nts.sixblack.learnandroid.model.Follow;
import nts.sixblack.learnandroid.model.Posts;
import nts.sixblack.learnandroid.model.PostsComment;
import nts.sixblack.learnandroid.model.ResponObject;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class ApiService {
    private String token = "Bearer "+ TokenResponse.accessToken;
    private Gson gson = new Gson();

    // feel posts
    public void feelPosts(long postsId){
        String token = "Bearer "+ TokenResponse.accessToken;
        RetrofitClient.getInstance().getMyAPI().feelPosts(token, postsId).enqueue(new Callback<ResponObject>() {
            @Override
            public void onResponse(Call<ResponObject> call, Response<ResponObject> response) {
                Log.i("call","feel success");
            }

            @Override
            public void onFailure(Call<ResponObject> call, Throwable t) {
                Log.e("error","feel error");
            }
        });
    }

    //comment posts
    public void commentPosts(CommentForm commentForm){
        String token = "Bearer "+ TokenResponse.accessToken;
        RetrofitClient.getInstance().getMyAPI().commentPosts(token, commentForm).enqueue(new Callback<ResponObject>() {
            @Override
            public void onResponse(Call<ResponObject> call, Response<ResponObject> response) {
                Log.i("call", "comment success");
            }

            @Override
            public void onFailure(Call<ResponObject> call, Throwable t) {
                Log.e("error", "comment error");
            }
        });


    }

    //delete comment
    public void deleteComment(String postsId){
        RetrofitClient.getInstance().getMyAPI().deleteComment(token, postsId).enqueue(new Callback<ResponObject>() {
            @Override
            public void onResponse(Call<ResponObject> call, Response<ResponObject> response) {
                Log.i("call","delete comment success");
            }

            @Override
            public void onFailure(Call<ResponObject> call, Throwable t) {
                Log.e("error","delete comment error");
            }
        });
    }

    // find posts by id
    public Posts findPostsById(long postsId){
        final Posts[] posts = {new Posts()};

        String tk = "Bearer "+ TokenResponse.accessToken;

        Call<ResponObject> call = RetrofitClient.getInstance().getMyAPI().findPostsById(tk, postsId);
        call.enqueue(new Callback<ResponObject>() {
            @Override
            public void onResponse(Call<ResponObject> call, Response<ResponObject> response) {
                ResponObject responObject = response.body();
                if (responObject == null) {
                    Log.e("error","no token");
                } else {
                    JsonObject object = gson.toJsonTree(responObject.getData()).getAsJsonObject();
                    posts[0] = gson.fromJson(object, Posts.class);

                }

            }

            @Override
            public void onFailure(Call<ResponObject> call, Throwable t) {

            }
        });

        return posts[0];
    }

    //new posts
    public void newPosts(RequestBody caption, MultipartBody.Part image){
        String token = "Bearer "+ TokenResponse.accessToken;
        Call<ResponObject> call = RetrofitClient.getInstance().getMyAPI().newPosts(token, caption, image);
        call.enqueue(new Callback<ResponObject>() {
            @Override
            public void onResponse(Call<ResponObject> call, Response<ResponObject> response) {
                Log.i("call","new posts success");
            }

            @Override
            public void onFailure(Call<ResponObject> call, Throwable t) {
                Log.e("error", t.getMessage());
            }
        });
    }

    // list post show
    public void lisPostsShow(RecyclerView.Adapter<CustomListPosts.PostsHolder> adapter, List<Posts> list){

        String token = "Bearer "+ TokenResponse.accessToken;

        Call<ResponObject> call = RetrofitClient.getInstance().getMyAPI().listsPostsOfUser(token);
        call.enqueue(new Callback<ResponObject>() {
            @Override
            public void onResponse(Call<ResponObject> call, Response<ResponObject> response) {
                ResponObject responObject = response.body();
                if (responObject == null){
                    Log.e("error","no token");
                } else {
                    JsonArray jsonArray = gson.toJsonTree(responObject.getData()).getAsJsonArray();
                    for (int i = 0; i<jsonArray.size(); i++){
                        JsonObject object = (JsonObject) jsonArray.get(i);
                        Posts[] posts = {new Posts()};
                        posts[0] = gson.fromJson(object, Posts.class);

                        list.add(posts[0]);
                    }

                    adapter.notifyDataSetChanged();
                    Log.i("call","list post show success");
                }

            }

            @Override
            public void onFailure(Call<ResponObject> call, Throwable t) {
                Log.e("error","list posts show error");
            }
        });

    }

    //delete posts
    public void deletePosts(long postsId){
        String token = "Bearer "+ TokenResponse.accessToken;
        RetrofitClient.getInstance().getMyAPI().deletePosts(token, postsId).enqueue(new Callback<ResponObject>() {
            @Override
            public void onResponse(Call<ResponObject> call, Response<ResponObject> response) {
                Log.i("call","delete posts success");
            }

            @Override
            public void onFailure(Call<ResponObject> call, Throwable t) {
                Log.e("error","delete posts error");
            }
        });
    }


    //  my list posts
    public void myLisPosts(RecyclerView.Adapter<CustomListPosts.PostsHolder> adapter, List<Posts> list){

        String token = "Bearer "+ TokenResponse.accessToken;

        Call<ResponObject> call = RetrofitClient.getInstance().getMyAPI().listsPostsOfUser(token);
        call.enqueue(new Callback<ResponObject>() {
            @Override
            public void onResponse(Call<ResponObject> call, Response<ResponObject> response) {
                ResponObject responObject = response.body();
                if (responObject == null){
                    Log.e("error","no token");
                } else {
                    JsonArray jsonArray = gson.toJsonTree(responObject.getData()).getAsJsonArray();
                    for (int i = 0; i<jsonArray.size(); i++){
                        JsonObject object = (JsonObject) jsonArray.get(i);
                        Posts[] posts = {new Posts()};
                        posts[0] = gson.fromJson(object, Posts.class);

                        list.add(posts[0]);
                    }

                    adapter.notifyDataSetChanged();
                    Log.i("call","my list post success");
                }

            }

            @Override
            public void onFailure(Call<ResponObject> call, Throwable t) {
                Log.e("error","my list posts error");
            }
        });

    }

    // list comment of posts
//    public void listCommentOfPosts(RecyclerView.Adapter<CustomListPosts.PostsHolder> adapter, List<PostsComment> list, long postsId){
    public void listCommentOfPosts(long postsId){

        String token = "Bearer "+ TokenResponse.accessToken;
        RetrofitClient.getInstance().getMyAPI().listCommentOfPosts(token, postsId).enqueue(new Callback<ResponObject>() {
            @Override
            public void onResponse(Call<ResponObject> call, Response<ResponObject> response) {
                ResponObject responObject = response.body();
                if (responObject == null){
                    Log.e("error","no token");
                } else {
                    JsonArray jsonArray = gson.toJsonTree(responObject.getData()).getAsJsonArray();
                    for (int i = 0; i<jsonArray.size(); i++){
                        JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                        PostsComment[] postsComments = {new PostsComment()};
                        postsComments[0] = gson.fromJson(jsonObject, PostsComment.class);

                        Log.i("comment",postsComments[0].getComment());
//                        list.add(postsComments[0]);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponObject> call, Throwable t) {
                Log.e("error","list comment of post error");
            }
        });
    }

    /////////////////////////////////////

    // follow
    public void follow(long userId){
        String token = "Bearer "+ TokenResponse.accessToken;

        RetrofitClient.getInstance().getMyAPI().follow(token, userId).enqueue(new Callback<ResponObject>() {
            @Override
            public void onResponse(Call<ResponObject> call, Response<ResponObject> response) {
                if (response.body() == null){
                    Log.e("error","no token");
                } else {
                    Log.i("call", "follow success");
                }
            }

            @Override
            public void onFailure(Call<ResponObject> call, Throwable t) {
                Log.e("error","follow error");
            }
        });
    }

    // list request
    public void listRequest(){
        String token = "Bearer "+ TokenResponse.accessToken;

        RetrofitClient.getInstance().getMyAPI().listRequest(token).enqueue(new Callback<ResponObject>() {
            @Override
            public void onResponse(Call<ResponObject> call, Response<ResponObject> response) {
                ResponObject responObject = response.body();
                if (responObject == null){
                    Log.e("error","no token");
                }  else {
                    JsonArray jsonArray = gson.toJsonTree(responObject.getData()).getAsJsonArray();
                    for (int i = 0; i<jsonArray.size(); i++){
                        JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                        Follow[] follows = {new Follow()};

                        follows[0] = gson.fromJson(jsonObject, Follow.class);
                        Log.i("name",follows[0].getEmail());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponObject> call, Throwable t) {
                Log.e("error","list request error");
            }
        });
    }

    //accept follow
    public void acceptFollow(long followId){
        String token = "Bearer "+ TokenResponse.accessToken;

        RetrofitClient.getInstance().getMyAPI().acceptFollow(token, followId).enqueue(new Callback<ResponObject>() {
            @Override
            public void onResponse(Call<ResponObject> call, Response<ResponObject> response) {
                if (response.body() == null){
                    Log.e("error", "no token");
                } else {
                    Log.i("call","accept success");
                }
            }

            @Override
            public void onFailure(Call<ResponObject> call, Throwable t) {
                Log.e("error","accept error");
            }
        });
    }

    public void changeFollowStatus(){
        String token = "Bearer "+ TokenResponse.accessToken;

        RetrofitClient.getInstance().getMyAPI().changeFollowStatus(token).enqueue(new Callback<ResponObject>() {
            @Override
            public void onResponse(Call<ResponObject> call, Response<ResponObject> response) {
                if (response.body() == null){
                    Log.e("error", "no token");
                } else {
                    Log.i("call","change follow status success");
                }
            }

            @Override
            public void onFailure(Call<ResponObject> call, Throwable t) {
                Log.e("error","accept error");
            }
        });
    }

    public void listFollower(){
        String token = "Bearer "+ TokenResponse.accessToken;

        RetrofitClient.getInstance().getMyAPI().listFollower(token).enqueue(new Callback<ResponObject>() {
            @Override
            public void onResponse(Call<ResponObject> call, Response<ResponObject> response) {
                ResponObject responObject = response.body();
                if (responObject == null){
                    Log.e("error", "no token");
                } else {
                    JsonArray jsonArray = gson.toJsonTree(responObject.getData()).getAsJsonArray();
                    for (int i = 0; i<jsonArray.size(); i++){
                        JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                        Follow[] follows = {new Follow()};
                        follows[0] = gson.fromJson(jsonObject, Follow.class);

                        Log.i("email",follows[0].getEmail());
                        Log.i("name",follows[0].getUserName());

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponObject> call, Throwable t) {
                Log.e("error","list follower error");

            }
        });
    }

    // list following
    public void listFollowing(){
        String token = "Bearer "+ TokenResponse.accessToken;

        RetrofitClient.getInstance().getMyAPI().listFollower(token).enqueue(new Callback<ResponObject>() {
            @Override
            public void onResponse(Call<ResponObject> call, Response<ResponObject> response) {
                ResponObject responObject = response.body();
                if (responObject == null){
                    Log.e("error", "no token");
                } else {
                    JsonArray jsonArray = gson.toJsonTree(responObject.getData()).getAsJsonArray();
                    for (int i = 0; i<jsonArray.size(); i++){
                        JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();
                        Follow[] follows = {new Follow()};
                        follows[0] = gson.fromJson(jsonObject, Follow.class);

                        Log.i("email",follows[0].getEmail());
                        Log.i("name",follows[0].getUserName());

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponObject> call, Throwable t) {
                Log.e("error","list following error");

            }
        });
    }


    ///////////////////
    public void updateAvatar(MultipartBody.Part image){
        String token = "Bearer "+ TokenResponse.accessToken;

        RetrofitClient.getInstance().getMyAPI().updateAvatar(token, image).enqueue(new Callback<ResponObject>() {
            @Override
            public void onResponse(Call<ResponObject> call, Response<ResponObject> response) {
                if (response.body() == null){
                    Log.e("error", "no token");
                } else {
                    Log.i("call","update avatar success");
                }
            }

            @Override
            public void onFailure(Call<ResponObject> call, Throwable t) {
                Log.e("error","update avatar error");
            }
        });
    }

    public void deleteAvatar(){
        String token = "Bearer "+ TokenResponse.accessToken;

        RetrofitClient.getInstance().getMyAPI().deleteAvatar(token).enqueue(new Callback<ResponObject>() {
            @Override
            public void onResponse(Call<ResponObject> call, Response<ResponObject> response) {
                if (response.body() == null){
                    Log.e("error", "no token");
                } else {
                    Log.i("call","delete avatar success");
                }
            }

            @Override
            public void onFailure(Call<ResponObject> call, Throwable t) {
                Log.e("error","delete avatar error");

            }
        });
    }

    public void updateBackground(MultipartBody.Part image){
        String token = "Bearer "+ TokenResponse.accessToken;

        RetrofitClient.getInstance().getMyAPI().updateAvatar(token, image).enqueue(new Callback<ResponObject>() {
            @Override
            public void onResponse(Call<ResponObject> call, Response<ResponObject> response) {
                if (response.body() == null){
                    Log.e("error", "no token");
                } else {
                    Log.i("call","update background success");
                }
            }

            @Override
            public void onFailure(Call<ResponObject> call, Throwable t) {
                Log.e("error","update background error");
            }
        });
    }

    public void deleteBackground(){
        String token = "Bearer "+ TokenResponse.accessToken;

        RetrofitClient.getInstance().getMyAPI().deleteAvatar(token).enqueue(new Callback<ResponObject>() {
            @Override
            public void onResponse(Call<ResponObject> call, Response<ResponObject> response) {
                if (response.body() == null){
                    Log.e("error", "no token");
                } else {
                    Log.i("call","delete background success");
                }
            }

            @Override
            public void onFailure(Call<ResponObject> call, Throwable t) {
                Log.e("error","delete background error");

            }
        });
    }


    ////////////////
    //register
    public void register(RegisterForm registerForm){
        String token = "Bearer "+ TokenResponse.accessToken;

        RetrofitClient.getInstance().getMyAPI().register(registerForm).enqueue(new Callback<ResponObject>() {
            @Override
            public void onResponse(Call<ResponObject> call, Response<ResponObject> response) {

            }

            @Override
            public void onFailure(Call<ResponObject> call, Throwable t) {

            }
        });
    }
}
