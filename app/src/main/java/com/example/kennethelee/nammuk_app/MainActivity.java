package com.example.kennethelee.nammuk_app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import static com.example.kennethelee.nammuk_app.R.id.add_fab;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //등록버튼
    private Boolean isFabOpen = false;
    private FloatingActionButton fab,fab_food,fab_exer;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //등록버튼
        fab = (FloatingActionButton) findViewById(add_fab);
        fab_food = (FloatingActionButton)findViewById(R.id.fab_food);
        fab_exer = (FloatingActionButton)findViewById(R.id.fab_exer);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "등록버튼입니다", Toast.LENGTH_SHORT).show();
                int id = view.getId();
                switch (id){
                    case R.id.add_fab:

                        animateFAB();
                        break;
                    case R.id.fab_food:

                        Log.d("KT", "Fab_food");
                        break;
                    case R.id.fab_exer:

                        Log.d("KT", "Fab_exer");
                        break;
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_myinfo) {
            // My정보 보기
            Intent intent = new Intent(this, MyInfoActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_myfood_reg) {

        } else if (id == R.id.nav_myexercise_reg) {

        } else if (id == R.id.nav_nammukfood) {
            //남먹음식 보기
            Intent intent = new Intent(this, NammukfoodActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_setting) {
            //설정 보기
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_aboutus) {
            //About Us 보기
            Intent intent = new Intent(this, AboutUsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = auto.edit();
            //editor.clear()는 auto에 들어있는 모든 정보를 기기에서 지웁니다.
            editor.clear();
            editor.commit();
            Toast.makeText(MainActivity.this, "로그아웃", Toast.LENGTH_SHORT).show();
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void animateFAB(){

        if(isFabOpen){

            fab.startAnimation(rotate_backward);
            fab_exer.setVisibility(View.VISIBLE);
            fab_food.setVisibility(View.VISIBLE);
            fab_food.startAnimation(fab_close);
            fab_exer.startAnimation(fab_close);
            fab_food.setClickable(false);
            fab_exer.setClickable(false);
            isFabOpen = false;
            Log.d("KT", "close");

        } else {

            fab.startAnimation(rotate_forward);
            fab_food.startAnimation(fab_open);
            fab_exer.startAnimation(fab_open);
            fab_food.setClickable(true);
            fab_exer.setClickable(true);
            isFabOpen = true;
            Log.d("KT","open");

        }
    }



}
