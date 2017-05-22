package com.example.kennethelee.nammuk_app;

/**
 * Created by Kennethe Lee on 2017-04-24.
 */

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

public class LoginActivity extends AppCompatActivity {

    /*자동로그인 관련 수정, 수정한 부분은 위에 주석 하나씩 달겠음
    참고사이트 -
    http://blog.naver.com/PostView.nhn?blogId=rain483&logNo=220812563378&parentCategoryNo=&categoryNo=16&viewDate=&isShowPopularPosts=false&from=postView
    */

    //변수지정
    private EditText mEmailField, mPasswordField;
    private Button mLoginBtn, registerBtn;
    //private String loginId, loginPwd;

    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //login xml로 변경
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        //아이디를 찾기
        mEmailField = (EditText) findViewById(R.id.EmailField);
        mPasswordField = (EditText) findViewById(R.id.PasswordField);
        mLoginBtn = (Button) findViewById(R.id.loginbutton);
        registerBtn = (Button) findViewById(R.id.RegisterButton);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() != null){
                    //로그인됐을때
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
            }
        };


        mLoginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startSignIn();
            }
        });

        Button buttonChange = (Button) findViewById(R.id.RegisterButton);
        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, UserRegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void startSignIn(){

        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {

            Toast.makeText(LoginActivity.this, "Fields are Empty", Toast.LENGTH_LONG).show();

        }else{

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "로그인 문제", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }



    }
}
