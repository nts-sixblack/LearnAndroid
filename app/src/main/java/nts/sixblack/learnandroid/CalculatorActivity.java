package nts.sixblack.learnandroid;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class CalculatorActivity extends AppCompatActivity {

    TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        setTitle("");

        txtResult = findViewById(R.id.txtResult);
    }

    public void clickButton(View v){
        Button btnClick = (Button) v;
        String name = btnClick.getText().toString();

        if (name.equals("C")){
            txtResult.setText("");
        } else if (name.equals("ASC")){
            txtResult.setText(txtResult.getText().subSequence(0, txtResult.getText().toString().length()-1));
        } else if (name.equals("=")){
            txtResult.setText("Result =))");
        } else {
            txtResult.setText(txtResult.getText()+name);
        }
    }
}