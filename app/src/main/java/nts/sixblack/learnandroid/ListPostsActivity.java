package nts.sixblack.learnandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import nts.sixblack.learnandroid.api.RetrofitClient;
import nts.sixblack.learnandroid.api.TokenResponse;
import nts.sixblack.learnandroid.layout_custom.CustomListPosts;
import nts.sixblack.learnandroid.model.Posts;
import nts.sixblack.learnandroid.model.ResponObject;
import nts.sixblack.learnandroid.service.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class ListPostsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Gson gson = new Gson();
    private ProgressDialog progressDialog;
    private List<Posts> list = new ArrayList<Posts>();
    private String token = "Bearer "+ TokenResponse.accessToken;
    private String userId = TokenResponse.userId;
    private CustomListPosts adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_posts);

        if (userId == null){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        progressDialog = new ProgressDialog(this);
        ApiService apiService = new ApiService();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewPosts);

        adapter = new CustomListPosts(this, list);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        apiService.getListPost(adapter, list, userId);
    }

}