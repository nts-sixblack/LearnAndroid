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
import nts.sixblack.learnandroid.form.RegisterForm;
import nts.sixblack.learnandroid.model.ResponObject;
import nts.sixblack.learnandroid.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class RetrofitTestActivity extends AppCompatActivity {
    private EditText edtFindName;
    private Button btnFind;
    private EditText edtFirstName;
    private EditText edtLastName;
    private EditText edtEmail;
    private EditText edtPhone;
    private EditText edtPassword;
    private Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_test);
        edtFindName = (EditText) findViewById(R.id.edtFindName);
        btnFind = (Button) findViewById(R.id.btnFindRetrofit);
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = edtFindName.getText().toString();
                if(!value.isEmpty()){
                    show(value);
                }
            }
        });

        edtFirstName = (EditText) findViewById(R.id.edtFirstName);
        edtLastName = (EditText) findViewById(R.id.edtLastName);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPhone = (EditText) findViewById(R.id.edtPhone);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = edtFirstName.getText().toString();
                String lastName = edtLastName.getText().toString();
                String email = edtEmail.getText().toString();
                String phone = edtPhone.getText().toString();
                String password = edtPassword.getText().toString();

                if (firstName.isEmpty()||lastName.isEmpty()||email.isEmpty()||phone.isEmpty()||password.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập đủ thông tin", Toast.LENGTH_LONG).show();
                } else {
                    RegisterForm registerForm = new RegisterForm();
                    registerForm.setFirstName(firstName);
                    registerForm.setLastName(lastName);
                    registerForm.setEmail(email);
                    registerForm.setPhone(phone);
                    registerForm.setPassword(password);

                    Call<ResponObject> call = RetrofitClient.getInstance().getMyAPI().register(registerForm);
                    call.enqueue(new Callback<ResponObject>() {
                        @Override
                        public void onResponse(Call<ResponObject> call, Response<ResponObject> response) {
                            ResponObject responObject = response.body();
                            if (responObject.getMessage().equals("Đăng nhập thành công")){
                                Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_LONG).show();
                                clear();
                            } else {
                                Toast.makeText(getApplicationContext(), "Đã tồn tại email, đăng ký thất bại", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponObject> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });



    }

    private void show(String name){
        Call<ResponObject> call = RetrofitClient.getInstance().getMyAPI().listUserByName(name);
        Gson gson = new Gson();
        call.enqueue(new Callback<ResponObject>() {
            @Override
            public void onResponse(Call<ResponObject> call, Response<ResponObject> response) {
                ResponObject responObject = response.body();
                String message = responObject.getMessage();
                System.out.println(responObject.getData());
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                ArrayList<User> list = (ArrayList) responObject.getData();

                List<User> userList = new ArrayList<User>();

                System.out.println("************");
                for (Object object:list){
                    JsonObject jsonObject = gson.toJsonTree(object).getAsJsonObject();

                    User user = new User();
                    user.setUserId(jsonObject.get("userId").getAsString());
                    user.setName(jsonObject.get("name").getAsString());
                    user.setPhone(jsonObject.get("phone").getAsString());

                    userList.add(user);
                }

                responObject.setData(userList);

                Intent intent = new Intent(RetrofitTestActivity.this, RecyclerViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("responObject", responObject);
                intent.putExtras(bundle);
                RetrofitTestActivity.this.startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void clear(){
        edtFirstName.setText("");
        edtLastName.setText("");
        edtEmail.setText("");
        edtPhone.setText("");
        edtPassword.setText("");
    }
}