package nts.sixblack.learnandroid;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class SendDataActivity extends AppCompatActivity {

    private Button btnSend;
    private EditText edtMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data);
        setTitle("");

        btnSend = findViewById(R.id.btnSend);
        edtMessage = findViewById(R.id.edtMessage);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = edtMessage.getText().toString();

                Intent intent = new Intent(SendDataActivity.this, GetDataActivity.class);
                intent.putExtra("message", message);

                SendDataActivity.this.startActivity(intent);
            }
        });
    }
}