package com.example.kennethelee.nammuk_app;

/**
 * Created by Kennethe Lee on 2017-04-24.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRegisterActivity extends AppCompatActivity {

    final int REQ_CODE_SELECT_IMAGE=100;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private Button buttonChange;
    private ProgressDialog mProgress;

    //유저정보 변수
    private EditText mIDField;
    private EditText mPWField;
    private EditText mPWConfirmField;
    private EditText mNNameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userregister1);


        //데이터삽입을 위한..
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        //유저등록
        mAuth = FirebaseAuth.getInstance();

        //진행중 메세지 출력
        mProgress = new ProgressDialog(this);

        //IDField
        mIDField = (EditText) findViewById(R.id.REmail);
        mPWField = (EditText) findViewById(R.id.RPassword);
        mPWConfirmField = (EditText) findViewById(R.id.RConfirmPassword);
        mNNameField = (EditText) findViewById(R.id.RNickName);

        buttonChange = (Button) findViewById(R.id.NextButton);
        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1. create child in root object
                //2. assign some value to the child object

                //넣고 싶은 필드 파싱해서 DB에 넣기
                final String user_id = mIDField.getText().toString().trim();
                final String user_pw = mPWField.getText().toString().trim();
                final String user_pw_confirm = mPWConfirmField.getText().toString().trim();
                final String user_nname = mNNameField.getText().toString().trim();

                if(user_pw_confirm.equals(user_pw)) { //비밀번호와 비밀번호 확인이 같다면

                    if (!TextUtils.isEmpty(user_id) && !TextUtils.isEmpty(user_pw) && !TextUtils.isEmpty(user_nname)) {

                        //진행중 메세지 출력
                        mProgress.setMessage("Signing Up...");
                        mProgress.show();

                        mAuth.createUserWithEmailAndPassword(user_id, user_pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                        String original_user_id = mAuth.getCurrentUser().getUid();
                                        DatabaseReference current_user_db = mDatabase.child(original_user_id);

                                        current_user_db.child("email").setValue(user_id);
                                        current_user_db.child("password").setValue(user_pw);
                                        current_user_db.child("nickname").setValue(user_nname);

                                        mProgress.dismiss();

                                        Intent intent = new Intent(UserRegisterActivity.this, UserRegisterBodyInfoActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);

                                }
                            }
                        });
                    }

                }else{ //비밀번호와 비밀번호 확인이 같지 않다면

                    //진행중 메세지 출력
                    mProgress.setMessage("Signing Up...");
                    mProgress.show();

                    Toast.makeText(UserRegisterActivity.this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(UserRegisterActivity.this, UserRegisterActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                }

            }

        });






    }

}


