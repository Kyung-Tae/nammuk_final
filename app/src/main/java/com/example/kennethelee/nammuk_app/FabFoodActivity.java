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



public class FabFoodActivity extends AppCompatActivity {

    Button morningbtn;
    Button lunchBtn;
    Button dinnerBtn;
    Button supperBtn;
    Button CalendarBtn;
    Button homeBtn;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_food);

        morningbtn = (Button) findViewById(R.id.button_fabfood_morningreg);
        lunchBtn = (Button) findViewById(R.id.button_fabfood_lunchreg);
        dinnerBtn = (Button) findViewById(R.id.button_fabfood_dinnerreg);
        supperBtn = (Button) findViewById(R.id.button_fabfood_supperreg);
        homeBtn = (Button) findViewById(R.id.button_fabfood_home);

        homeBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        morningbtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FabFoodActivity.this, "음식사전기능입니다", Toast.LENGTH_SHORT).show();
                //여기 수정해보는중
                Intent intent = new Intent(FabFoodActivity.this, FoodDicActivity.class);
                //Intent intent = new Intent(FabFoodActivity.this, FoodDicActivity_test.class);
                startActivity(intent);

            }
        });

        lunchBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FabFoodActivity.this, "음식사전기능입니다", Toast.LENGTH_SHORT).show();
                //여기 수정해보는중
                Intent intent = new Intent(FabFoodActivity.this, FoodDicActivity.class);
                //Intent intent = new Intent(FabFoodActivity.this, FoodDicActivity_test.class);
                startActivity(intent);

            }
        });

        dinnerBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FabFoodActivity.this, "음식사전기능입니다", Toast.LENGTH_SHORT).show();
                //여기 수정해보는중
                Intent intent = new Intent(FabFoodActivity.this, FoodDicActivity.class);
                //Intent intent = new Intent(FabFoodActivity.this, FoodDicActivity_test.class);
                startActivity(intent);

            }
        });

        supperBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FabFoodActivity.this, "음식사전기능입니다", Toast.LENGTH_SHORT).show();
                //여기 수정해보는중
                Intent intent = new Intent(FabFoodActivity.this, FoodDicActivity.class);
                //Intent intent = new Intent(FabFoodActivity.this, FoodDicActivity_test.class);
                startActivity(intent);

            }
        });

        CalendarBtn = (Button) findViewById(R.id.button_fabfood_date);
        CalendarBtn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                //내 메뉴 만드는 레이아웃
                Toast.makeText(FabFoodActivity.this, "달력기능입니다", Toast.LENGTH_SHORT).show();
                //달력 화면 보여주기
                Intent intent = new Intent(FabFoodActivity.this, FabFoodCalendarActivity.class);
                startActivity(intent);

            }
        });

    }


}
