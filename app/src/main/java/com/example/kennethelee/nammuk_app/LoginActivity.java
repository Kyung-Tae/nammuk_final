package com.example.kennethelee.nammuk_app;

/**
 * Created by Kennethe Lee on 2017-04-24.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    /*자동로그인 관련 수정, 수정한 부분은 위에 주석 하나씩 달겠음
    참고사이트 -
    http://blog.naver.com/PostView.nhn?blogId=rain483&logNo=220812563378&parentCategoryNo=&categoryNo=16&viewDate=&isShowPopularPosts=false&from=postView
    */

    //변수지정
    EditText id, pwd;
    Button loginBtn, registerBtn;
    String loginId, loginPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //login xml로 변경
        setContentView(R.layout.activity_login);

        //아이디를 찾기
        id = (EditText) findViewById(R.id.EmailField);
        pwd = (EditText) findViewById(R.id.PasswordField);
        loginBtn = (Button) findViewById(R.id.loginbutton);
        registerBtn = (Button) findViewById(R.id.RegisterButton);
        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);

        //로그인 초기값
        loginId = auto.getString("inputId", null);
        loginPwd = auto.getString("inputPwd", null);

        //테스트값 추후에 DB에서 가져오는 것으로 변경해야함
        if (loginId != null && loginPwd != null) {
            if (loginId.equals("nammuk") && loginPwd.equals("1234")) {
                Toast.makeText(LoginActivity.this, loginId + "님 자동로그인 입니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        } else if (loginId == null && loginPwd == null) {
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (id.getText().toString().equals("nammuk") && pwd.getText().toString().equals("1234")) {
                        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
                        //아이디가 '김경태'이고 비밀번호가 '1234'일 경우 SharedPreferences.Editor를 통해
                        //auto의 loginId와 loginPwd에 값을 저장해 줍니다.
                        //에뮬의 한글자판 부재로 아이디 nammuk으로 변경
                        SharedPreferences.Editor autoLogin = auto.edit();
                        autoLogin.putString("inputId", id.getText().toString());
                        autoLogin.putString("inputPwd", pwd.getText().toString());
                        //꼭 commit()을 해줘야 값이 저장된다
                        autoLogin.commit();
                        Toast.makeText(LoginActivity.this, id.getText().toString() + "님 환영합니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "아이디와 비밀번호를 확인하세요", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

        Button buttonChange = (Button) findViewById(R.id.RegisterButton);
        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, UserRegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
