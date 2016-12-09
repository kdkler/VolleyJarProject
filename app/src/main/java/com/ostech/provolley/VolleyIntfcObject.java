package com.ostech.provolley;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by yoon on 2016/12/8.
 */

public abstract class VolleyIntfcObject {

    public Context _context;
    public static Response.Listener<JSONObject> _listenerObject;
    public static Response.ErrorListener _errListener;
    public VolleyIntfcObject(Context context, Response.Listener<JSONObject> listenerObj, Response.ErrorListener errListener)
    {
        this._context=context;
        this._errListener=errListener;
        this._listenerObject=listenerObj;
    }


    //请求时候的回调方法
    public abstract void onMySuccessObject(JSONObject object);
    public abstract void onMyError(VolleyError error);



    public Response.Listener<JSONObject> iListenerObject()
    {
        this._listenerObject=new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                onMySuccessObject(jsonObject);
            }
        };
        return  _listenerObject;
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
