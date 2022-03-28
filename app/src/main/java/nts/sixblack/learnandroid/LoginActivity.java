package nts.sixblack.learnandroid;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import nts.sixblack.learnandroid.api.RetrofitClient;
import nts.sixblack.learnandroid.api.TokenResponse;
import nts.sixblack.learnandroid.form.LoginForm;
import nts.sixblack.learnandroid.model.ResponObject;
import nts.sixblack.learnandroid.model.User;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;
    private Gson gson;
    private String token = TokenResponse.accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = (EditText) findViewById(R.id.edtUsernameLoginActivity);
        edtPassword = (EditText) findViewById(R.id.edtPasswordLoginActivity);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        gson = new Gson();

        System.out.println(TokenResponse.accessToken);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                if (!username.isEmpty()&&!password.isEmpty()){
                    LoginForm loginForm = new LoginForm(username, password);
                    login(loginForm);
                } else {
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập tên đăng nhập, mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void login(LoginForm loginForm){

        Call<ResponObject> call = RetrofitClient.getInstance().getMyAPI().login(loginForm);
        call.enqueue(new Callback<ResponObject>() {
            @Override
            public void onResponse(Call<ResponObject> call, Response<ResponObject> response) {
                ResponObject responObject = response.body();
                if (responObject==null){
                    Toast.makeText(getApplicationContext(), "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                } else {

                    JsonObject jsonObject = gson.toJsonTree(responObject.getData()).getAsJsonObject();

                    TokenResponse.accessToken = jsonObject.get("token").getAsString();
                    TokenResponse.username = jsonObject.get("email").getAsString();

                    Intent intent = new Intent(getApplicationContext(), UploadImageActivity.class);
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(), "login success", Toast.LENGTH_SHORT).show();

                    System.out.println(TokenResponse.accessToken);

                }
            }

            @Override
            public void onFailure(Call<ResponObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}