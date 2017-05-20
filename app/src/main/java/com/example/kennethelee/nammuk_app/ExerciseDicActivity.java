package com.example.kennethelee.nammuk_app;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by User on 2017-05-08.
 */

public class ExerciseDicActivity extends ListActivity {

    Button cancelbtn;
    Button searchbtn;
    Button makemybtn;
    Button myexbtn;
    private Spinner spinner1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_dic);

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
        cancelbtn = (Button) findViewById(R.id.button_exercisedic_cancel);
        cancelbtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //검색 버튼
        searchbtn = (Button) findViewById(R.id.button_exercisedic_search);
        searchbtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                //검색기능
                Toast.makeText(ExerciseDicActivity.this, "검색기능입니다", Toast.LENGTH_SHORT).show();
            }
        });

        //내 메뉴 만들기 버튼
        makemybtn = (Button) findViewById(R.id.button_exercisedic_makemy);
        makemybtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                //내 메뉴 만드는 레이아웃
                Toast.makeText(ExerciseDicActivity.this, "마이메뉴기능입니다", Toast.LENGTH_SHORT).show();
                //내 운동 화면 보여주기
                Intent intent = new Intent(ExerciseDicActivity.this, MyExerciseAddRecordActivity.class);
                startActivity(intent);

            }
        });

        myexbtn = (Button) findViewById(R.id.button_exercisedic_myexercise);
        myexbtn.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                //내 메뉴 만드는 레이아웃
                Toast.makeText(ExerciseDicActivity.this, "마이메뉴기능입니다", Toast.LENGTH_SHORT).show();
                //내 운동화면 보여주기
                Intent intent = new Intent(ExerciseDicActivity.this, MyExerciseActivity.class);
                startActivity(intent);

            }
        });

        //dropdown 메뉴 구현
        spinner1 = (Spinner) findViewById(R.id.spinner);
        spinner1.setOnItemSelectedListener(new ItemSelectedListener());
    }

    public class ItemSelectedListener implements AdapterView.OnItemSelectedListener {

        //get strings of first item
        String firstItem = String.valueOf(spinner1.getSelectedItem());

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (firstItem.equals(String.valueOf(spinner1.getSelectedItem()))) {
                // ToDo when first item is selected
            } else {
                Toast.makeText(parent.getContext(),
                        "You have selected : " + parent.getItemAtPosition(pos).toString(),
                        Toast.LENGTH_LONG).show();
                // Todo when item is selected by the user
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg) {

        }

    }

}
