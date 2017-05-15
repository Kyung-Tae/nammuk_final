package com.example.kennethelee.nammuk_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by speed on 2017-05-14.
 */

public class FabFoodActivity extends AppCompatActivity {

    Button homebtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fab_food);

        //취소버튼과 동일
        homebtn = (Button) findViewById(R.id.button_fabfood_home);
        homebtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
