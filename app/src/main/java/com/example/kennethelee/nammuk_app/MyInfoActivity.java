package com.example.kennethelee.nammuk_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by speed on 2017-04-27.
 */

public class MyInfoActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);

        //취소버튼 누르면 화면 나가기
        Button buttonCancel = (Button) findViewById(R.id.button_cancel_myinfo);
        buttonCancel.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //수정버튼 누르면 수정화면 가기
        Button buttonChange = (Button) findViewById(R.id.button_change_myinfo);
        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyInfoActivity.this, MyInfoChangeActivity.class);
                startActivity(intent);
            }
        });

    }
}
