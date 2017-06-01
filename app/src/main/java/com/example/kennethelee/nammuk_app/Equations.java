package com.example.kennethelee.nammuk_app;

import android.content.Context;

/**
 * Created by speed on 2017-06-01.
 */

public class Equations {
    public static int dpToPx(Context context, float dp) {
        // Took from http://stackoverflow.com/questions/8309354/formula-px-to-dp-dp-to-px-android
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) ((dp * scale) + 0.5f);
    }
}