package nts.sixblack.learnandroid;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class FormRegisterActivity extends AppCompatActivity {

    private EditText edtFullName;
    private EditText edtUserName;
    private EditText edtPassword;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_register);
        setTitle("");

        edtFullName = findViewById(R.id.edtFullNameRegisterActivity);
        edtUserName = findViewById(R.id.edtUserNameRegisterActivity);
        edtPassword = findViewById(R.id.edtPasswordRegisterActivity);
        btnRegister = findViewById(R.id.btnRegisterFormRegisterActivity);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormRegisterActivity.this, ShowViewActivity.class);
                intent.putExtra("fullname", edtFullName.getText().toString());
                intent.putExtra("username", edtUserName.getText().toString());
                intent.putExtra("password", edtPassword.getText().toString());

                FormRegisterActivity.this.startActivity(intent);
            }
        });

    }
}