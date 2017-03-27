package bitsplease.wakeupshot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SetAlarmActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{



    // ******************* Variable & Objects Definition************************************//


    int alarmhour, alarmminute = 0;
    TimePicker  timePicker;
    Spinner categoryspinner;

    Button alarmsetbutton;
    MainActivity mainActivity = new MainActivity();

    Intent resultintent;
    int requestcode = 1;

    String listofcategoriesselected[] = new String[10];
    List<String> categories;
    ArrayAdapter<String> categoryarrayadapter;
    static String category;


    // *******************  Methods ************************************//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Bundle extras = getIntent().getExtras();
        if(extras != null){
            listofcategoriesselected = extras.getStringArray("listofselectedcategory");
            Log.i("WUS", "list of categories selected =" + listofcategoriesselected );
        }




        timePicker = (TimePicker) findViewById(R.id.timePicker);
        categoryspinner = (Spinner) findViewById(R.id.spinner);
        alarmsetbutton = (Button) findViewById(R.id.alarmsetbutton);

        // Setup listener to determine change in the time
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(SetAlarmActivity.this, "onTimechanged called", Toast.LENGTH_SHORT).show();
                alarmhour = hourOfDay;
                alarmminute = minute;
                Log.i("WUS", "onTimeChanged called. Hour: " + alarmminute+ "minute: " + alarmminute);
            }
        });




        categoryspinner = (Spinner)findViewById(R.id.spinner);
        categories = new ArrayList<String>();
        int i=0;
        while(listofcategoriesselected[i] != null) {
            categories.add(listofcategoriesselected[i++]);
        }

        Log.i("WUS", "categories = "+ categories);

        categoryspinner.setOnItemSelectedListener(this);
        categoryarrayadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        categoryarrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryspinner.setAdapter(categoryarrayadapter);




        alarmsetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultintent = new Intent();
                resultintent.putExtra("hour", alarmhour);
                resultintent.putExtra("minute", alarmminute);
                resultintent.putExtra("category",category);
                setResult(Activity.RESULT_OK, resultintent);
                SetAlarmActivity.this.finish();
            }
        });





    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        resultintent = new Intent();
        setResult(Activity.RESULT_CANCELED, resultintent);
        SetAlarmActivity.this.finish();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        category = parent.getItemAtPosition(position).toString();
        Log.i("WUS","category at position: "+position +" is : "+ category +" : "+ parent.getItemAtPosition(position-1));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
