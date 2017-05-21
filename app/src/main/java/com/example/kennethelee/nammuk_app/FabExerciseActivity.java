package com.example.kennethelee.nammuk_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by speed on 2017-05-14.
 */

public class FabExerciseActivity extends AppCompatActivity {

    Button homebtn;
    Button AerobicAddBtn;
    Button AnaerobicAddBtn;
    Button CalendarBtn;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_exercise);

        homebtn = (Button) findViewById(R.id.button_fabexercise_home);
        homebtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        AerobicAddBtn = (Button) findViewById(R.id.button_fabexercise_aerobicreg);
        AerobicAddBtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                //내 메뉴 만드는 레이아웃
                Toast.makeText(FabExerciseActivity.this, "운동사전기능입니다", Toast.LENGTH_SHORT).show();
                //내 식단 화면 보여주기
                Intent intent = new Intent(FabExerciseActivity.this, ExerciseDicActivity.class);
                startActivity(intent);

            }
        });

        AnaerobicAddBtn = (Button) findViewById(R.id.button_fabexercise_anaerobicreg);
        AnaerobicAddBtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                //내 메뉴 만드는 레이아웃
                Toast.makeText(FabExerciseActivity.this, "운동사전기능입니다", Toast.LENGTH_SHORT).show();
                //내 식단 화면 보여주기
                Intent intent = new Intent(FabExerciseActivity.this, ExerciseDicActivity.class);
                startActivity(intent);

            }
        });

        CalendarBtn = (Button) findViewById(R.id.button_fabexercise_calendar);
        CalendarBtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                //내 메뉴 만드는 레이아웃
                Toast.makeText(FabExerciseActivity.this, "달력기능입니다", Toast.LENGTH_SHORT).show();
                //달력 화면 보여주기
                Intent intent = new Intent(FabExerciseActivity.this, FabExerciseCalendarActivity.class);
                startActivity(intent);

            }
        });


    }


}
