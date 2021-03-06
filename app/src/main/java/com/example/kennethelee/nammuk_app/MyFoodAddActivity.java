package com.example.kennethelee.nammuk_app;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by User on 2017-05-08.
 */

public class MyFoodAddActivity extends ListActivity {

    Button cancelbtn;
    Button searchbtn;
    Button camerabtn;
    Button makemybtn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfood_add);

        //list array로 받아서 보여주기(DB로 추후에 받아오는 것으로 변경해야함)
        //generate list
        ArrayList<String> list = new ArrayList<String>();
        list.add("item1");
        list.add("item2");
        list.add("item3");
        list.add("item4");
        list.add("item5");

        //instantiate custom adapter
        MyCustomAdapter_withregister adapter = new MyCustomAdapter_withregister(list, this);


        //handle listview and assign adapter
        ListView lView = (ListView)findViewById(android.R.id.list);
        lView.setAdapter(adapter);


        //취소 버튼은 나가기
        cancelbtn = (Button) findViewById(R.id.button_myfoodadd_cancel);
        cancelbtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //검색 버튼
        searchbtn = (Button) findViewById(R.id.button_myfoodadd_search);
        searchbtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                //검색기능
                Toast.makeText(MyFoodAddActivity.this, "검색기능입니다", Toast.LENGTH_SHORT).show();
            }
        });

        //카메라 버튼
        camerabtn = (Button) findViewById(R.id.button_myfoodadd_camera);
        camerabtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                //카메라기능
                Toast.makeText(MyFoodAddActivity.this, "카메라기능입니다", Toast.LENGTH_SHORT).show();
            }
        });

        //내 메뉴 만들기 버튼
        makemybtn = (Button) findViewById(R.id.button_myfoodadd_makemy);
        makemybtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                //내 메뉴 만드는 레이아웃
                Toast.makeText(MyFoodAddActivity.this, "마이메뉴기능입니다", Toast.LENGTH_SHORT).show();
                //내 식단 화면 보여주기
                Intent intent = new Intent(MyFoodAddActivity.this, MyFoodAddRecordActivity.class);
                startActivity(intent);

            }
        });
    }
}
