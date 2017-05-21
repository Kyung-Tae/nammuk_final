package com.example.kennethelee.nammuk_app;

/**
 * Created by Kennethe Lee on 2017-04-24.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UserRegisterActivity extends AppCompatActivity {

    final int REQ_CODE_SELECT_IMAGE=100;
    private DatabaseReference mDatabase;
    private Button buttonChange;

    //유저정보 변수
    private EditText mIDField;
    private EditText mPWField;
    private EditText mNNameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userregister1);

        //데이터삽입을 위한..
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //IDField
        mIDField = (EditText) findViewById(R.id.REmail);
        mPWField = (EditText) findViewById(R.id.RPassword);
        mNNameField = (EditText) findViewById(R.id.RNickName);

        buttonChange = (Button) findViewById(R.id.NextButton);
        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1. create child in root object
                //2. assign some value to the child object

                //넣고 싶은 필드 파싱해서 DB에 넣기
                String user_id = mIDField.getText().toString().trim();
                String user_pw = mPWField.getText().toString().trim();
                String user_nname = mNNameField.getText().toString().trim();

                //어트리뷰트 묶어서 하나의 오브젝트로 넣기위해 묶기
                HashMap<String, String> dataMap = new HashMap<String, String>();
                dataMap.put("ID", user_id);
                dataMap.put("Password", user_pw);
                dataMap.put("Nickname", user_nname);

                //잘 들어갔는지 메세지 출력하기
                mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(UserRegisterActivity.this, "저장 성공.", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(UserRegisterActivity.this, "저장 실패.", Toast.LENGTH_LONG).show();
                        }

                    }
                });

                Intent intent = new Intent(UserRegisterActivity.this, UserRegisterBodyInfoActivity.class);
                startActivity(intent);
            }

        });



    }
}
