package com.example.kennethelee.nammuk_app;

/**
 * Created by Kennethe Lee on 2017-04-24.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class UserRegisterActivity extends AppCompatActivity {

    final int REQ_CODE_SELECT_IMAGE=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userregister1);

        Button buttonChange = (Button) findViewById(R.id.NextButton);
        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserRegisterActivity.this, UserRegisterBodyInfoActivity.class);
                startActivity(intent);
            }
        });



    }
}
