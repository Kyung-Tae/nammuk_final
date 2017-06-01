package com.example.kennethelee.nammuk_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by speed on 2017-06-01.
 */

public class FoodDicActivity_test extends AppCompatActivity implements FatSecretSearchFragment.FragmentCallbacks {
    Fragment fatSecretSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fatsecretmain);
        fatSecretSearch = new FatSecretSearchFragment();
        if (fatSecretSearch != null)
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new FatSecretSearchFragment()).commit();
    }

    @Override
    public void fromFragment() {

    }
}
