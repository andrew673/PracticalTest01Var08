package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PracticalTest01Var02PlayActivity extends AppCompatActivity {

    private TextView riddle = null;
    private TextView answer = null;
    private Button backButton = null;
    private Button checkButton = null;

    ButtonClickListener listener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.back:
                    setResult(RESULT_CANCELED, null);
                    break;
                case R.id.check:
                    if (getIntent().getStringExtra("answerText").equals(answer.getText().toString()))
                        setResult(RESULT_OK, null);
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_secondary);

        riddle = (TextView)findViewById(R.id.name_view_text1);
        answer = (EditText)findViewById(R.id.name_view_text2);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("riddleText")) {
            riddle.setText(intent.getStringExtra("riddleText"));
        }
        if (intent != null && intent.getExtras().containsKey("answerText")) {
            //answer.setText(intent.getStringExtra("answerText"));
        }

        backButton = (Button)findViewById(R.id.back);
        checkButton = (Button)findViewById(R.id.check);
        backButton.setOnClickListener(listener);
        checkButton.setOnClickListener(listener);

        Intent intent2 = new Intent(getApplicationContext(), PracticalTest01Var08Service.class);
        intent2.putExtra("answerText", answer.getText().toString());
        getApplicationContext().startService(intent2);
    }
}
