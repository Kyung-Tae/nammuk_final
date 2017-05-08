package com.example.kennethelee.nammuk_app;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by speed on 2017-05-07.
 */

public class MyExerciseActivity extends ListActivity {

    Button cancelbtn;
    Button addbtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myexercise);

        //list array로 받아서 보여주기(DB로 추후에 받아오는 것으로 변경해야함)
        //generate list
        ArrayList<String> list = new ArrayList<String>();
        list.add("item1");
        list.add("item2");

        //instantiate custom adapter
        MyCustomAdapter_withdelete adapter = new MyCustomAdapter_withdelete(list, this);

        //handle listview and assign adapter
        ListView lView = (ListView)findViewById(android.R.id.list);
        lView.setAdapter(adapter);

        //취소 버튼은 나가기
        cancelbtn = (Button) findViewById(R.id.button_myexercise_cancel);
        cancelbtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        addbtn = (Button) findViewById(R.id.button_myexercise_add);
        addbtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                //추가화면 보여주기
            }
        });
    }
}
