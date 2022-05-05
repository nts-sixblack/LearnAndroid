package nts.sixblack.learnandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.gson.Gson;
import nts.sixblack.learnandroid.form.CommentForm;
import nts.sixblack.learnandroid.model.Posts;
import nts.sixblack.learnandroid.service.ApiService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Intent intent;
    private Gson gson;
    private ProgressDialog progressDialog;
    private List<Posts> list;
    private ApiService apiService = new ApiService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("");
        gson = new Gson();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("waiting ...");

    }

    public void clickButton(View view){
        button = (Button) findViewById(view.getId());

        switch (button.getText().toString()){
            case "ListView":
                intent = new Intent(MainActivity.this, ListViewActivity.class);
                break;
            case "CardView":
                intent = new Intent(MainActivity.this, CardViewActivity.class);
                break;
            case "SendMessage":
                intent = new Intent(MainActivity.this, SendDataActivity.class);
                break;
            case "Calculator":
                intent = new Intent(MainActivity.this, CalculatorActivity.class);
                break;
            case "FormRegister":
                intent = new Intent(MainActivity.this, FormRegisterActivity.class);
                break;
            case "RecyclerView":
                intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
                break;
            case "Retrofit":
                intent = new Intent(MainActivity.this, RetrofitTestActivity.class);
                break;
            case "Login":
                intent = new Intent(MainActivity.this, LoginActivity.class);
                break;
            case "ListPosts":
                intent = new Intent(MainActivity.this, ListPostsActivity.class);
                break;
            case "Song":
                intent = new Intent(MainActivity.this, NewSongActivity.class);
                break;
            case "FeelPosts":
                apiService.feelPosts(1);
                intent = new Intent(MainActivity.this, NewSongActivity.class);
                break;
            case "CommentPosts":
                CommentForm commentForm = new CommentForm("comment", 2);
                apiService.commentPosts(commentForm);
                intent = new Intent(MainActivity.this, NewSongActivity.class);
                break;
            case "FindPostsById":
                apiService.findPostsById(1);
                intent = new Intent(MainActivity.this, NewSongActivity.class);
                break;
            case "NewPosts":
                intent = new Intent(getApplicationContext(), UploadImageActivity.class);
                break;
            case "ListCommentOfPosts":
                apiService.listCommentOfPosts(2);
                intent = new Intent(MainActivity.this, NewSongActivity.class);
                break;
            case "Follow":
                apiService.follow(1);
                intent = new Intent(MainActivity.this, MainActivity.class);
                break;
            case "ListRequest":
                apiService.listRequest();
                intent = new Intent(MainActivity.this, NewSongActivity.class);
                break;
            case "ChangeFollowStatus":
                apiService.changeFollowStatus();
                intent = new Intent(MainActivity.this, MainActivity.class);
                break;
            case "ListFollower":
                apiService.listFollower();
                intent = new Intent(MainActivity.this, NewSongActivity.class);
                break;
            case "ListFollowing":
                apiService.listFollowing();
                intent = new Intent(MainActivity.this, NewSongActivity.class);
                break;


        }

        MainActivity.this.startActivity(intent);
    }
}