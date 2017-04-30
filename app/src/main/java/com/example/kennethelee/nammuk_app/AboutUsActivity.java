package com.example.kennethelee.nammuk_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by speed on 2017-04-27.
 */

public class AboutUsActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);

        //취소버튼 누르면 화면 나가기
        Button buttonCancel = (Button) findViewById(R.id.button_cancel_aboutus);
        buttonCancel.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
