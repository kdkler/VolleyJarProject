package com.ostech.provolley;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by yoon on 2016/9/25.
 */
public class VoleyApp extends Application {

    public static RequestQueue queues;
    @Override
    public void onCreate() {
        super.onCreate();
        queues= Volley.newRequestQueue(getApplicationContext());
    }
    public static RequestQueue getHttpQueues()
    {
        return queues;
    }
}


