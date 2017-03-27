package bitsplease.wakeupshot;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.IntentService;
import android.app.Service;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    int  requestcode = 1; // requestcode correspond a activity. here 1 refers to SetAlarmActivity

    public static int alarmhour = 25, alarmminute = 61;
    String category;

    public static boolean setalarm = false;

    boolean[] categoryarray = new boolean[10];
    String[] listofcategoriesselected = new String[10];






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Bundle extras = getIntent().getExtras();
        if(extras != null){
            categoryarray = extras.getBooleanArray("categoryarray");
            Log.i("WUS", "categoryarray = " + categoryarray);
        }
         int i=0;
        if(categoryarray[0] == true){
            listofcategoriesselected[i++] = "auto";
        }
        if(categoryarray[1] == true){
            listofcategoriesselected[i++] = "music";
        }
        if(categoryarray[2] == true){
            listofcategoriesselected[i++] = "call";
        }
        if(categoryarray[3] == true){
            listofcategoriesselected[i++] = "comedy";
        }
        if(categoryarray[4] == true){
            listofcategoriesselected[i++] = "sports";
        }




        // Add Alarm Button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent startactivityintent = new Intent(MainActivity.this, SetAlarmActivity.class);
                startactivityintent.putExtra("listofselectedcategory",listofcategoriesselected);
                startActivityForResult(startactivityintent, requestcode);
            }
        });


        // code for drawer window from right
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //We start the service here
        startService();
        Log.i("WUS", "Service Started");
    }




// ******************* Service Related Methods ************************************//

    // Custom method to start service
    public void startService(){
        Intent startserviceintent = new Intent(this, bitsplease.wakeupshot.WakeUpShotService.class);
        startService(startserviceintent);
    }

    // Custom method to stop service
    public void stopService(){

    }





// ******************* Get Time from SetAlarmActivity Related Methods ************************************//

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestcode == 1 && resultCode == Activity.RESULT_OK){
            alarmhour = data.getIntExtra("hour", 25);
            alarmminute = data.getIntExtra("minute",25);
            category = data.getStringExtra("category");

            Log.i("WUS", "hour = " + alarmhour + " minute = " + alarmminute +" category: " + category);
            if(alarmhour !=25 && alarmminute != 61) {
                setAlarm();
                Log.i("WUS", "setAlarm called");
            }
        }
    }


    public void setAlarm(){
        // Code to set alarm.
            Log.i("WUS", "setAlarm hour:" + alarmhour + " minutes:" + alarmminute);





    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            MainActivity.this.finish();
        }
    }








    // ******************* Navigation Related Methods ************************************//

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
