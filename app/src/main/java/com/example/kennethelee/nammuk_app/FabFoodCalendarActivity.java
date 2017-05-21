package com.example.kennethelee.nammuk_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

/**
 * Created by User on 2017-05-21.
 */

public class FabFoodCalendarActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_food_calendar);

        //취소버튼 누르면 화면 나가기
        Button buttonCancel = (Button) findViewById(R.id.button_foodcalendar_cancel);
        buttonCancel.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        CalendarView cal = (CalendarView) findViewById(R.id.calendarView_food);
        cal.setMaxDate(cal.getDate());


    }
}
