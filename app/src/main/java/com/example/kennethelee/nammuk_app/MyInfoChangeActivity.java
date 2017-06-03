package com.example.kennethelee.nammuk_app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Created by speed on 2017-04-29.
 */

public class MyInfoChangeActivity extends AppCompatActivity {

    final int REQ_CODE_SELECT_IMAGE=100;

    //사진저장을 위해
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference mStorageRef;
    private DatabaseReference mDatabase;
    private ProgressDialog mProgress;
    private FirebaseAuth mAuth;
    private Bitmap bitmap;
    private String stEmail;


    //유저신체정보 변수
    private RadioGroup mSexField;
    private RadioButton mMaleField;
    private RadioButton mFemaleField;
    private EditText mNicknameField;
    private EditText mAgeField;
    private EditText mHeightField;
    private EditText mWeightField;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myinfo_change);

        mNicknameField = (EditText) findViewById(R.id.change_nickname);
        mAgeField = (EditText) findViewById(R.id.change_age);
        mHeightField = (EditText) findViewById(R.id.change_height);
        mWeightField = (EditText) findViewById(R.id.change_weight);
        mSexField = (RadioGroup) findViewById(R.id.change_sexradiogroup);
        mMaleField = (RadioButton) findViewById(R.id.radio_male);
        mFemaleField = (RadioButton) findViewById(R.id.radio_female);

        //진행중 메세지 출력
        mProgress = new ProgressDialog(this);

        //유저등록
        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");


        //취소버튼 누르면 화면 나가기
        Button buttonCancel = (Button) findViewById(R.id.button_myinfochange_cancel);
        buttonCancel.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        //완료 버튼 누르면 일단은 토스트메세지 출력이지만 DB에 전송하는 메소드 작성해야함
        Button buttonComplete = (Button) findViewById(R.id.button_myinfochange_complete);
        buttonComplete.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view) {


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

                String user_nickname = mNicknameField.getText().toString().trim();
                String user_age = mAgeField.getText().toString().trim();
                String user_height = mHeightField.getText().toString().trim();
                String user_weight = mWeightField.getText().toString().trim();

                //BMI 계산을 위한..
                Double weight = Double.parseDouble(user_weight);
                Double height = Double.parseDouble(user_height)/100;
                Double user_bmi = weight / (height*height);
                user_bmi = Math.ceil(user_bmi);
                String user_bmi2 = String.valueOf(user_bmi);



                if (!TextUtils.isEmpty(user_sex) && !TextUtils.isEmpty(user_age) && !TextUtils.isEmpty(user_height)  && !TextUtils.isEmpty(user_weight)) {

                    //진행중 메세지 출력
                    mProgress.setMessage("Signing Up...");
                    mProgress.show();

                    FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();




                    if(mUser != null){
                        //유저가 로그인했으면

                        String original_user_id = mAuth.getCurrentUser().getUid();
                        DatabaseReference current_user_body_db = mDatabase.child(original_user_id).child("UserBodyInfo");

                        Hashtable<String, String> bodyprofile = new Hashtable<String, String>();
                        bodyprofile.put("sex", user_sex);
                        bodyprofile.put("age", user_age);
                        bodyprofile.put("height", user_height);
                        bodyprofile.put("weight", user_weight);
                        bodyprofile.put("bmi", user_bmi2);

                        current_user_body_db.setValue(bodyprofile);
                        current_user_body_db.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String s = dataSnapshot.getValue().toString();
                                Log.d("Bodyprofile", s);
                                if(dataSnapshot != null){
                                    Toast.makeText(MyInfoChangeActivity.this, "유저정보가 변경되었습니다.", Toast.LENGTH_LONG).show();

                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Toast.makeText(MyInfoChangeActivity.this, "유저정보에 실패하였습니다.", Toast.LENGTH_LONG).show();

                            }
                        });


                        mProgress.dismiss();

                        Intent mainIntent = new Intent(MyInfoChangeActivity.this, MyInfoActivity.class);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(mainIntent);

                    }else{
                        //유저가 로그인하지 않았으면
                        Toast.makeText(MyInfoChangeActivity.this, "로그인되지 않았습니다.", Toast.LENGTH_LONG).show();
                    }

                }


                Toast.makeText(MyInfoChangeActivity.this, "수정이 완료되었습니다", Toast.LENGTH_SHORT).show();
            }
        });




        //이미지 바꾸기(DB로 넘기는거 아직 미구현)
        Button buttonImgChange = (Button) findViewById(R.id.button_myinfochange_imgchange);
        buttonImgChange.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
            }
        });


        mStorageRef= storage.getReferenceFromUrl("gs://nammukfinal.appspot.com/");


        //유저등록
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();




    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(getBaseContext(), "resultCode : "+resultCode,Toast.LENGTH_SHORT).show();

        if(requestCode == REQ_CODE_SELECT_IMAGE)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                try {


                    //Uri에서 이미지 이름을 얻어온다.
                    String name_Str = getImageNameToUri(data.getData());

                    //이미지 데이터를 비트맵으로 받아온다.
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    ImageView image = (ImageView)findViewById(R.id.Userimage);


                    //접속중인 현재 사용자 uid받아오기
                    String original_user_id = mAuth.getCurrentUser().getUid();

                    //저장소에 이미지 업로드
                    StorageReference userpicRef = mStorageRef.child("UserPictures").child(original_user_id).child(original_user_id+".jpg");

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] upload_data = baos.toByteArray();

                    UploadTask uploadTask = userpicRef.putBytes(upload_data);
                    uploadTask.addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @SuppressWarnings("VisibleForTests")
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                            Uri downloadUrl = taskSnapshot.getDownloadUrl();
                            Log.d("uir", String.valueOf(downloadUrl));


                            //업로드가 되면 유저데이터베이스에 사진의 url 저장하기
                            mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
                            DatabaseReference current_user_db = mDatabase.child(original_user_id).child("UserInfo");
                            current_user_db.child("picture").setValue(String.valueOf(downloadUrl));
                        }
                    });


                    //배치해놓은 ImageView에 set
                    image.setImageBitmap(bitmap);

                    //성공했다는 메세지출력
                    Toast.makeText(getBaseContext(), "name_Str : "+name_Str , Toast.LENGTH_SHORT).show();



                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }



    public String getImageNameToUri(Uri data)
    {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(data, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        String imgPath = cursor.getString(column_index);
        String imgName = imgPath.substring(imgPath.lastIndexOf("/")+1);

        return imgName;
    }




}
