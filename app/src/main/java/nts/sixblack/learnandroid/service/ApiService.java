package nts.sixblack.learnandroid.service;

import android.util.Log;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import nts.sixblack.learnandroid.api.RetrofitClient;
import nts.sixblack.learnandroid.api.TokenResponse;
import nts.sixblack.learnandroid.layout_custom.CustomListPosts;
import nts.sixblack.learnandroid.model.Posts;
import nts.sixblack.learnandroid.model.ResponObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class ApiService {
    private String token = "Bearer "+ TokenResponse.accessToken;
    private Gson gson = new Gson();

    public void getListPost(RecyclerView.Adapter<CustomListPosts.PostsHolder> adapter, List<Posts> list, String userId){

        int id = Integer.parseInt(userId);
        Log.e("call","call");
        Call<ResponObject> call = RetrofitClient.getInstance().getMyAPI().listsPostsOfUser(token, id);
        call.enqueue(new Callback<ResponObject>() {
            @Override
            public void onResponse(Call<ResponObject> call, Response<ResponObject> response) {
                ResponObject responObject = response.body();
                if (responObject == null){
                    Log.e("error","no token");
                }
                JsonArray jsonArray = gson.toJsonTree(responObject.getData()).getAsJsonArray();
                for (int i = 0; i<jsonArray.size(); i++){
                    JsonObject jsonObject = (JsonObject) jsonArray.get(i);
                    Posts posts = new Posts();
                    posts.setCaption(jsonObject.get("caption").getAsString());
                    Log.e("caption", posts.getCaption());

                    JsonArray jsonArrayUser = jsonObject.getAsJsonArray("postsUserList");
                    JsonObject jsonUser = (JsonObject) jsonArrayUser.get(0);

                    posts.setPostsUser(jsonUser.get("name").getAsString());
                    Log.e("name", posts.getPostsUser());

                    JsonArray jsonArrayImage = jsonObject.getAsJsonArray("postsImageList");
                    JsonObject jsonImage = (JsonObject) jsonArrayImage.get(0);
                    posts.setPostsImage(jsonImage.get("image").getAsString());
                    Log.e("image", posts.getPostsImage());

                    list.add(posts);
                }

                Log.e("call","success");
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponObject> call, Throwable t) {
                Log.e("error","fail");
            }
        });

    }
}
