package id.ac.ui.cs.mobileprogramming.helloworld;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class CounterService extends Service {
    private final static String TAG = "CounterService";
    public static final String COUNTER_BR = "id.ac.ui.cs.mobileprogramming.helloworld";
    Intent bi = new Intent(COUNTER_BR);

    private Handler mHandler = new Handler();
    private long counter;
    private final int REFRESH_RATE = 100;
    private String counterText;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i(TAG, "Starting counter...");
        counter = 0;

        mHandler.removeCallbacks(startTimer);
        mHandler.postDelayed(startTimer, 0);
    }

    @Override
    public void onDestroy() {
        mHandler.removeCallbacks(startTimer);
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void updateCounter (float counter){
        counterText = String.valueOf(counter);

        bi.putExtra("counter", counterText);
        sendBroadcast(bi);

    }//end Update Timer

    private Runnable startTimer = new Runnable() {
        @Override
        public void run() {
            counter += 1;
            updateCounter(counter);
            mHandler.postDelayed(this, REFRESH_RATE);
        }
    };

}
