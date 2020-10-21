package id.ac.ui.cs.mobileprogramming.helloworld;

import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final int TEXT_REQUEST = 1;
    private TextView mReplyHeadTextView;
    private TextView mReplyTextView;
    private static final String TAG = "MainActivity Debugging";
    public static final String EXTRA_MESSAGE =
            "id.ac.ui.cs.mobileprogramming.extra.MESSAGE";
    private EditText mMessageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMessageEditText = findViewById(R.id.editText_main);
        Log.d(TAG, "Ini On Create");
        mReplyHeadTextView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.text_message_reply);
        Log.d(TAG, "Visibilitas:" + mReplyHeadTextView.getVisibility());
    }

    public void launchSecondActivity(View view) {
        Log.d(TAG, "Button clicked!");
        Intent intent = new Intent(this, SecondActivity.class);
        String message = mMessageEditText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        Log.d("Main", "Terima Result" + requestCode);
        if (requestCode == TEXT_REQUEST) {
            Log.d("Main", "Terima Text Request" + resultCode);
            if (resultCode == RESULT_OK) {
                Log.d("Main", "Terima Result OK");
                String reply = data.getStringExtra(SecondActivity.EXTRA_REPLY);
                mReplyHeadTextView.setVisibility(View.VISIBLE);
                mReplyTextView.setText(reply);
                mReplyTextView.setVisibility(View.VISIBLE);
            }
        }
    }
}