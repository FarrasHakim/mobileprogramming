package id.ac.ui.cs.mobileprogramming.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";
    TextView counterTextView = null;
    Button buttonStart, buttonStop, buttonReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Test Debugging", "Hello There. Ini On Create");

        counterTextView = (TextView)findViewById(R.id.counterText);
        buttonStart = (Button)findViewById(R.id.start_btn);
        buttonStop = (Button)findViewById(R.id.stop_btn);
        buttonReset = (Button)findViewById(R.id.reset_btn);


        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, CounterService.class));
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, CounterService.class));
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, CounterService.class));
                counterTextView.setText("0");
            }
        });
    }

    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            updateGUI(intent);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(br, new IntentFilter(CounterService.COUNTER_BR));
    }

    @Override
    protected void onStop() {
        unregisterReceiver(br);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, CounterService.class));
        super.onDestroy();
    }

    private void updateGUI(Intent intent) {
        if (intent.getExtras() != null) {
            String counter = intent.getStringExtra("counter");
            counterTextView.setText(counter);
        }
    }
}