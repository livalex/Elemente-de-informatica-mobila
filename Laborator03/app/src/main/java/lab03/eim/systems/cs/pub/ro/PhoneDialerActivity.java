package lab03.eim.systems.cs.pub.ro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class PhoneDialerActivity extends AppCompatActivity {

    private EditText phoneNumberEditText;
    private ImageButton callImageButton;
    private ImageButton hangupImageButton;
    private ImageButton backspaceImageButton;
    private Button genericButton;

    private GenericButtonClickListener genericButtonClickListener
            = new GenericButtonClickListener();
    private BackspaceButtonClickListener backspaceButtonClickListener
            = new BackspaceButtonClickListener();
    private CallImageButtonClickListener callImageButtonClickListener
            = new CallImageButtonClickListener();
    private HangupImageButtonClickListener hangupImageButtonClickListener
            = new HangupImageButtonClickListener();

    private class GenericButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            phoneNumberEditText.setText(phoneNumberEditText.getText().toString()
                    + ((Button)view).getText().toString());
        }
    }

    private class BackspaceButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            String phoneNumber = phoneNumberEditText.getText().toString();

            if (phoneNumber.length() > 0) {
                phoneNumber = phoneNumber.substring(0, phoneNumber.length() - 1);
                phoneNumberEditText.setText(phoneNumber);
            }
        }
    }

    private class CallImageButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (ContextCompat.checkSelfPermission(PhoneDialerActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        PhoneDialerActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE},
                        Constants.PERMISSION_REQUEST_CALL_PHONE);
            } else {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + phoneNumberEditText.getText().toString()));
                startActivity(intent);
            }
        }
    }

    private class HangupImageButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_dialer);

//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        phoneNumberEditText = (EditText) findViewById(R.id.phone_number_edit_text);
        callImageButton = (ImageButton) findViewById(R.id.call_image_button);
        hangupImageButton = (ImageButton) findViewById(R.id.hangup_image_button);
        backspaceImageButton = (ImageButton) findViewById(R.id.backspace_image_button);

        callImageButton.setOnClickListener(callImageButtonClickListener);
        hangupImageButton.setOnClickListener(hangupImageButtonClickListener);
        backspaceImageButton.setOnClickListener(backspaceButtonClickListener);

        for (int i = 0; i < Constants.buttons.length; ++i) {
            genericButton = (Button) findViewById(Constants.buttons[i]);
            genericButton.setOnClickListener(genericButtonClickListener);
        }
    }
}