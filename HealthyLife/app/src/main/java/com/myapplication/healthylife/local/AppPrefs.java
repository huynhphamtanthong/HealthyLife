package com.myapplication.healthylife.local;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPrefs {
    private static SharedPreferences instance;
    private static Context context;

    public static SharedPreferences getInstance(Context context)  {
        if (instance == null)   {
            instance = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        }
        return instance;
    }
}
