package com.ostech.provolley;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by yoon on 2016/12/7.
 */

public abstract class VolleyIntfcString {

    public Context _context;
    public static Response.Listener<String> _listener;
    public static Response.ErrorListener _errListener;
    public VolleyIntfcString(Context context, Response.Listener<String> listener, Response.ErrorListener errListener)
    {
        this._context=context;
        this._listener=listener;
        this._errListener=errListener;
    }


    //请求时候的回调方法

    public abstract void onMySuccess(String result);
    public abstract void onMyError(VolleyError error);

    public Response.Listener<String> iListener()
    {
        this._listener=new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                onMySuccess(s);
            }
        };
        return this._listener;
    }


    public Response.ErrorListener iErrListener()
    {
        this._errListener=new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onMyError(volleyError);
            }
        };
        return  this._errListener;
    }
}
