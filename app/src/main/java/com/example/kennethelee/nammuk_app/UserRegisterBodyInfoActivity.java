package com.example.kennethelee.nammuk_app;

/**
 * Created by Kennethe Lee on 2017-04-24.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRegisterBodyInfoActivity extends AppCompatActivity {

    final int REQ_CODE_SELECT_IMAGE=100;

    private DatabaseReference mDatabase;
    private ProgressDialog mProgress;
    private FirebaseAuth mAuth;

    private Button buttonComplete;

    //유저신체정보 변수

    private RadioGroup mSexField;
    private RadioButton mMaleField;
    private RadioButton mFemaleField;
    private EditText mAgeField;
    private EditText mHeightField;
    private EditText mWeightField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userregister2);

        //데이터삽입을 위한..
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        //유저등록
        mAuth = FirebaseAuth.getInstance();

        //진행중 메세지 출력
        mProgress = new ProgressDialog(this);

        //IDField
        mAgeField = (EditText) findViewById(R.id.RAge);
        mHeightField = (EditText) findViewById(R.id.RHeight);
        mWeightField = (EditText) findViewById(R.id.RWeight);
        mSexField = (RadioGroup) findViewById(R.id.SexRadioGroup);
        mMaleField = (RadioButton) findViewById(R.id.MaleRadioButton);
        mFemaleField = (RadioButton) findViewById(R.id.FemaleRadioButton);

        buttonComplete = (Button) findViewById(R.id.CompleteButton);
        buttonComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //1. create child in root object
                //2. assign some value to the child object

                //넣고 싶은 필드 파싱해서 DB에 넣기

                String user_sex;

                if(mMaleField.isChecked()){
                    //남자 선택되었다면
                    user_sex = "남성";
                }else if(mFemaleField.isChecked()){
                    //여자 선택되었다면
                    user_sex = "여성";
                }else{
                    //아무 것도 선택되지 않았다면
                    user_sex = "Null";
                }

                String user_age = mAgeField.getText().toString().trim();
                String user_height = mHeightField.getText().toString().trim();
                String user_weight = mWeightField.getText().toString().trim();

                //BMI 계산을 위한..
                Double weight = Double.parseDouble(user_weight);
                Double height = Double.parseDouble(user_height)/100;
                Double user_bmi = weight / (height*height) / 0.1;



                if (!TextUtils.isEmpty(user_sex) && !TextUtils.isEmpty(user_age) && !TextUtils.isEmpty(user_height)  && !TextUtils.isEmpty(user_weight)) {

                    //진행중 메세지 출력
                    mProgress.setMessage("Signing Up...");
                    mProgress.show();

                    FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
                    if(mUser != null){
                        //유저가 로그인했으면

                        String original_user_id = mAuth.getCurrentUser().getUid();
                        DatabaseReference current_user_db = mDatabase.child(original_user_id).child("UserBodyInfo");

                        current_user_db.child("sex").setValue(user_sex);
                        current_user_db.child("age").setValue(user_age);
                        current_user_db.child("height").setValue(user_height);
                        current_user_db.child("weight").setValue(user_weight);
                        current_user_db.child("bmi").setValue(user_bmi);

                        mProgress.dismiss();

                        Intent mainIntent = new Intent(UserRegisterBodyInfoActivity.this, LoginActivity.class);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(mainIntent);

                    }else{
                        //유저가 로그인하지 않았으면
                        Toast.makeText(UserRegisterBodyInfoActivity.this, "로그인되지 않았습니다.", Toast.LENGTH_LONG).show();
                    }


                   /* mAuth.createUserWithEmailAndPassword(user_id, user_pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {



                            }
                        }
                    }); */

                }

        /*

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

                */
            }

        });





    }
}
