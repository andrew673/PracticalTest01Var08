package ro.pub.cs.systems.eim.practicaltest01var08;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class PracticalTest01Var08MainActivity extends AppCompatActivity {

    private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;

    private EditText riddleText = null;
    private EditText answerText = null;
    private Button playButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var08_main);


        riddleText = (EditText)findViewById(R.id.name_edit_text1);
        answerText = (EditText)findViewById(R.id.name_edit_text2);

        playButton = (Button)findViewById(R.id.play);
        playButton.setOnClickListener(listener);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("riddleText")) {
                riddleText.setText(savedInstanceState.getString("riddleText"));
            } else {
                riddleText.setText(null);
            }
            if (savedInstanceState.containsKey("answerText")) {
                answerText.setText(savedInstanceState.getString("answerText"));
            } else {
                answerText.setText(null);
            }
        } else {
            riddleText.setText(null);
            answerText.setText(null);
        }
    }

    ButtonClickListener listener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.play:
                    if (!riddleText.getText().toString().equals(null) &&
                        !answerText.getText().toString().equals(null)) {
                        Intent intent = new Intent(getApplicationContext(), PracticalTest01Var02PlayActivity.class);
                        intent.putExtra("riddleText", riddleText.getText().toString());
                        intent.putExtra("answerText", answerText.getText().toString());
                        startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
                    }
                    break;
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("riddleText", riddleText.getText().toString());
        savedInstanceState.putString("answerText", answerText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey("riddleText")) {
            riddleText.setText(savedInstanceState.getString("riddleText"));
        } else {
            riddleText.setText(null);
        }
        if (savedInstanceState.containsKey("answerText")) {
            answerText.setText(savedInstanceState.getString("answerText"));
        } else {
            answerText.setText(null);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == SECONDARY_ACTIVITY_REQUEST_CODE) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }

}
