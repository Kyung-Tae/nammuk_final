package com.example.kennethelee.nammuk_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Boolean.valueOf;


/**
 * Created by speed on 2017-05-09.
 */

public class MyFoodAddRecordActivity extends AppCompatActivity {

    Button cancelbtn;
    Button regbtn;
    EditText foodname;
    EditText unitname;
    EditText unit;
    EditText calorie;
    Boolean checkflag=false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfood_record);

        //null이 없는지 확인용 버튼체크
        foodname = (EditText) findViewById(R.id.editText_foodname);
        unitname = (EditText) findViewById(R.id.editText_unitname);
        unit = (EditText) findViewById(R.id.editText_unit);
        calorie = (EditText) findViewById(R.id.editText_calorie);

        //취소 버튼은 나가기
        cancelbtn = (Button) findViewById(R.id.button_myfoodaddrecord_cancel);
        cancelbtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //등록버튼
        regbtn = (Button) findViewById(R.id.button_myfoodaddrecord_register);
        regbtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {

                String strfoodname = foodname.getText().toString();
                String strunitname = unitname.getText().toString();
                String strunit = unit.getText().toString();
                String strcalorie = calorie.getText().toString();

                if(!TextUtils.isEmpty(strfoodname)&&
                   !TextUtils.isEmpty(strunitname)&&
                   !TextUtils.isEmpty(strunit)&&
                   !TextUtils.isEmpty(strcalorie)){
                    checkflag = true;
                }

                //추후에 DB변경 -> itemlist에도 변경 -> 화면에 반영
                if(valueOf(checkflag)){
                    Toast.makeText(MyFoodAddRecordActivity.this, "등록합니다", Toast.LENGTH_SHORT).show();
                    checkflag = false;
                } else {
                    Toast.makeText(MyFoodAddRecordActivity.this, "빈칸이 없는지 확인해주세요", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
