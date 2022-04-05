package ro.pub.cs.systems.eim.practicaltest01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PracticalTest01SecondaryActivity extends AppCompatActivity {

    private TextView numberOfClicksTextView;
    private Button okButton;
    private Button cancelButton;

    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.ok_button) {
                setResult(RESULT_OK, null);
            } else if (view.getId() == R.id.cancel_button) {
                setResult(RESULT_CANCELED, null);
            }

            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_secondary);

        numberOfClicksTextView = (TextView) findViewById(R.id.number_of_clicks_text_view);
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getExtras().containsKey(Constants.NUMBER_OF_CLICKS)) {
                int clicks = intent.getIntExtra(Constants.NUMBER_OF_CLICKS, -1);
                numberOfClicksTextView.setText(String.valueOf(clicks));
            }
        }

        okButton = (Button) findViewById(R.id.ok_button);
        cancelButton = (Button) findViewById(R.id.cancel_button);

        okButton.setOnClickListener(buttonClickListener);
        cancelButton.setOnClickListener(buttonClickListener);
    }
}