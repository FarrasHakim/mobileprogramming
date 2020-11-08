package id.ac.ui.cs.mobileprogramming.helloworld;

import android.os.PersistableBundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private static final String TEXT_STATE = "currentText";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView1);
        if(savedInstanceState!=null) {
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
        }
        Log.d("Test Debugging", "Hello There. Ini On Create");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString(TEXT_STATE,
                mTextView.getText().toString());
    }

    public void onClick(View v) {
        Toast toast = Toast.makeText(v.getContext(), "Berhasil di click", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void startTask(View view) {
        mTextView.setText("Napping...");
        new SimpleAsyncTask(mTextView).execute();
    }
}