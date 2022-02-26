package nts.sixblack.learnandroid;

import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class ShowViewActivity extends AppCompatActivity {

    private TextView txtFullname;
    private TextView txtUserName;
    private TextView txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_view);
        setTitle("");

        txtFullname = findViewById(R.id.txtFullNameShowViewActitivity);
        txtUserName = findViewById(R.id.txtUserNameShowViewActivity);
        txtPassword = findViewById(R.id.txtPasswordShowViewActivity);

        Intent intent = getIntent();

        txtFullname.setText(intent.getStringExtra("fullname").toString());
        txtUserName.setText(intent.getStringExtra("username").toString());
        txtPassword.setText(intent.getStringExtra("password").toString());

    }
}