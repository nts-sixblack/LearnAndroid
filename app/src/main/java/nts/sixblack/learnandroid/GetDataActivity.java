package nts.sixblack.learnandroid;

import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class GetDataActivity extends AppCompatActivity {

    private TextView txtGetMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_data);
        setTitle("");

        txtGetMessage = findViewById(R.id.txtGetMessage);

        Intent intent = getIntent();
        txtGetMessage.setText(intent.getStringExtra("message"));

    }
}