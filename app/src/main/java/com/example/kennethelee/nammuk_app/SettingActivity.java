package com.example.kennethelee.nammuk_app;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by speed on 2017-04-30.
 */

public class SettingActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState){
        final Context context = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //취소버튼 누르면 화면 나가기
        Button buttonCancel = (Button) findViewById(R.id.button_setting_cancel);
        buttonCancel.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //초기화버튼 누르면 기록초기화 다이얼로그
        Button buttonInit = (Button) findViewById(R.id.button_setting_init);
        buttonInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                //제목세팅
                alertDialogBuilder.setTitle("기록 초기화");

                // AlertDialog 세팅
                alertDialogBuilder
                        .setMessage("기록을 초기화하시겠습니까?")
                        .setCancelable(false)
                        .setPositiveButton("초기화",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        // 초기화한다
                                        initSetting();
                                    }
                                })
                        .setNegativeButton("취소",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(
                                            DialogInterface dialog, int id) {
                                        // 다이얼로그를 취소한다
                                        dialog.cancel();
                                    }
                                });
                //다이얼로그 생성
                AlertDialog alertDialog = alertDialogBuilder.create();

                //다이얼로그 보여주기
                alertDialog.show();
            }
        });

        //완료 버튼 누르면 토스트메시지(추후에 DB로 넘기는 기능으로)
        Button buttonComplete = (Button) findViewById(R.id.button_setting_complete);
        buttonComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SettingActivity.this, "설정이 완료되었습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //다이얼로그에서 초기화하면(추후에 DB전송도 필요)
    private void initSetting() {
        ToggleButton toggleBtnLock = (ToggleButton) findViewById(R.id.toggleButton_setting_lock);
        ToggleButton toggleBtnPedometer = (ToggleButton) findViewById(R.id.toggleButton_setting_pedometer);
        ToggleButton toggleBtnAlarm = (ToggleButton) findViewById(R.id.toggleButton_setting_alarm);
        EditText dietSaying = (EditText) findViewById(R.id.editText_setting_saying);

        toggleBtnAlarm.setChecked(false);
        toggleBtnLock.setChecked(false);
        toggleBtnPedometer.setChecked(false);
        dietSaying.setText(" ");
    }
}
