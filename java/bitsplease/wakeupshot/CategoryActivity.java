package bitsplease.wakeupshot;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class CategoryActivity extends AppCompatActivity {

    CheckBox checkBoxAuto, checkBoxMusic,checkBoxCall, checkBoxComedy, checkBoxSports;
    Button nextbutton1;

    boolean[] categoryarray = new boolean[10];

    int i, j;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        initCheckBox();
        nextbutton1 = (Button) findViewById(R.id.NextButton1);

        nextbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startactivityintent = new Intent(CategoryActivity.this, MainActivity.class);
                startactivityintent.putExtra("categoryarray", categoryarray);
                startActivity(startactivityintent);
            }
        });
    }

    public void initCheckBox(){
        checkBoxAuto = (CheckBox) findViewById(R.id.checkBoxAuto);
        checkBoxCall = (CheckBox) findViewById(R.id.checkBoxCall);
        checkBoxComedy = (CheckBox) findViewById(R.id.checkBoxComedy);
        checkBoxMusic = (CheckBox) findViewById(R.id.checkBoxMusic);
        checkBoxSports = (CheckBox) findViewById(R.id.checkBoxSports);

        //categoryarray[0][0] = "Auto";
        //categoryarray[1][0] = "Music";
        //categoryarray[2][0] = "Call";
        //categoryarray[3][0] = "Comedy";
        //categoryarray[4][0] = "Sport";

        for(j=0; j<10; j++) {
            categoryarray[j] = false;
        }

        Log.i("WUS"," checkbox and array initialized");
    }


    @Override
    protected void onStart() {
        super.onStart();


        //

        checkBoxAuto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                categoryarray[0] = isChecked; // Call checkbox is checked
                //categoryarray[1] = isChecked;
                //categoryarray[2] = isChecked;
                //categoryarray[3] = isChecked;
                //categoryarray[4] = isChecked;

                if(isChecked == true) {
                    checkBoxMusic.setChecked(isChecked);
                    checkBoxCall.setChecked(isChecked);
                    checkBoxComedy.setChecked(isChecked);
                    checkBoxSports.setChecked(isChecked);
                }


                Log.i("WUS", "Auto checkbox checked:" + categoryarray[0] + categoryarray[1] + categoryarray[2] + categoryarray[3] + categoryarray[4]);
               // Toast.makeText(CategoryActivity.this,""+ categoryarray[0] + categoryarray[1] + categoryarray[2] + categoryarray[3] + categoryarray[4], Toast.LENGTH_SHORT).show();
            }
        });

        checkBoxMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                categoryarray[1] = isChecked; // Call checkbox is checked
              //  Toast.makeText(CategoryActivity.this, "Music :" + isChecked, Toast.LENGTH_SHORT).show();
                if(checkBoxAuto.isChecked()  && isChecked == false){
                    checkBoxAuto.setChecked(false);
                }
            }
        });

        checkBoxCall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                categoryarray[2] = isChecked; // Call checkbox is checked
                //Toast.makeText(CategoryActivity.this, "Call :" + isChecked, Toast.LENGTH_SHORT).show();

                if (checkBoxAuto.isChecked()  && isChecked == false){
                    checkBoxAuto.setChecked(false);
                }
            }
        });

        checkBoxComedy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                categoryarray[3] = isChecked; // Call checkbox is checked
                //Toast.makeText(CategoryActivity.this, "Comedy :" + isChecked, Toast.LENGTH_SHORT).show();

                if (checkBoxAuto.isChecked() && isChecked == false) {
                    checkBoxAuto.setChecked(false);
                }
            }
        });

        checkBoxSports.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                categoryarray[4] = isChecked; // Call checkbox is checked
                //Toast.makeText(CategoryActivity.this, "Sports :" + isChecked, Toast.LENGTH_SHORT).show();

                if(checkBoxAuto.isChecked()  && isChecked == false){
                    checkBoxAuto.setChecked(false);
                }
            }
        });


    }
}
