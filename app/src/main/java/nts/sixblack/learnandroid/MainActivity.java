package nts.sixblack.learnandroid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.gson.Gson;
import nts.sixblack.learnandroid.model.Posts;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private Intent intent;
    private Gson gson;
    private ProgressDialog progressDialog;
    private List<Posts> list;

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
        }

        MainActivity.this.startActivity(intent);
    }
}