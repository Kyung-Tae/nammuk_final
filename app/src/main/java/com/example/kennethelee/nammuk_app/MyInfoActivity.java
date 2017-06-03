package com.example.kennethelee.nammuk_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by speed on 2017-04-27.
 */



public class MyInfoActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private DatabaseReference mphotoDatabase;
    private FirebaseAuth mAuth;
    private Bitmap bitmap;

    private TextView mNicknameView;
    private TextView mSexView;
    private TextView mAgeView;
    private TextView mHeightView;
    private TextView mWeightView;
    private TextView mBMIView;

    private TextView mObesityView;
    private TextView mDailyCalView;

    private ImageView mUserPictureView;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo);

        //유저등록
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();

        if(mUser != null) {
            //유저가 로그인했으면
            String original_user_id = mAuth.getCurrentUser().getUid();

            //데이터삽입을 위한..
            mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(original_user_id).child("UserBodyInfo");

            mNicknameView = (TextView) findViewById(R.id.user_nickname);
            mSexView = (TextView) findViewById(R.id.myinfo_sex);
            mAgeView = (TextView) findViewById(R.id.myinfo_age);
            mHeightView = (TextView) findViewById(R.id.myinfo_height);
            mWeightView = (TextView) findViewById(R.id.myinfo_weight);
            mBMIView = (TextView) findViewById(R.id.myinfo_bmi);
            mObesityView = (TextView) findViewById(R.id.user_obesity);
            mDailyCalView = (TextView) findViewById(R.id.user_onedaycal);

            mUserPictureView = (ImageView) findViewById(R.id.user_picture);

            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    String sex = dataSnapshot.child("sex").getValue().toString();
                    mSexView.setText("성별 : " + sex);

                    String age = dataSnapshot.child("age").getValue().toString();
                    mAgeView.setText(age +"세");

                    String height = dataSnapshot.child("height").getValue().toString();
                    mHeightView.setText("키 : " + height + "cm");

                    String weight = dataSnapshot.child("weight").getValue().toString();
                    mWeightView.setText("몸무게 : " +weight + "kg");

                    String bmi = dataSnapshot.child("bmi").getValue().toString();
                    mBMIView.setText("BMI : " + bmi);

                    Double double_bmi = Double.parseDouble(bmi);
                    Integer int_bmi = double_bmi.intValue();
                    String obesity = ObesityCheck(int_bmi);
                    mObesityView.setText(obesity);

                    Integer int_height = Integer.parseInt(height);
                    Double user_dailycal = (int_height - 100) * 0.9 * 25;
                    user_dailycal = Math.ceil(user_dailycal);
                    mDailyCalView.setText(user_dailycal + " KCAL");


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {



                }
            });


/*
            mphotoDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(original_user_id).child("UserInfo");

            mphotoDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    String value = dataSnapshot.getValue().toString();
                    String userpic = dataSnapshot.child("picture").getValue().toString();

                    mphotoDatabase.child("userpic").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            // Got the download URL for 'users/me/profile.png'
                            //Uri에서 이미지 이름을 얻어온다.
                            String name_Str = String.valueOf(uri);

                            //이미지 데이터를 비트맵으로 받아온다.
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());

                            //배치해놓은 ImageView에 set
                            mUserPictureView.setImageBitmap(bitmap);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle any errors
                        }
                    });


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
*/
        }


        //취소버튼 누르면 화면 나가기
        Button buttonCancel = (Button) findViewById(R.id.button_cancel_myinfo);
        buttonCancel.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //수정버튼 누르면 수정화면 가기
        Button buttonChange = (Button) findViewById(R.id.button_change_myinfo);
        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyInfoActivity.this, MyInfoChangeActivity.class);
                startActivity(intent);
            }
        });







    }


    public String ObesityCheck(Integer bmi){

        String obesity;

        if (bmi <= 18.5){
            obesity = "저체중";

            //저체중
        }else if(bmi > 18.5 && bmi <= 23){
            obesity = "정상";

            //정상
        }else if(bmi > 23 && bmi <= 25){
            obesity = "과체중";

            //과체중
        }else if(bmi < 25 && bmi <= 30){
            obesity = "비만";

            //비만
        }else{
            obesity = "고도비만";
            //고도비만
        }

        return  obesity;

    }





}
