package bitsplease.wakeupshot;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class WakeUpShotService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startID){
        // called when service started
        Log.i("WUS", "onStartCommand Started");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("WUS"," onDestroyStarted");
    }


    @Override
    public void onCreate(){
        super.onCreate();

        // Write the implementation here
        Log.i("WUS", "onCreate Started");


    }
}
