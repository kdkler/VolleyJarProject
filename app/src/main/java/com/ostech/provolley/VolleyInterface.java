package com.ostech.provolley;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by yoon on 2016/12/7.
 */

public abstract class VolleyInterface {

    public Context _context;
    public static Response.Listener<String> _listener;
    public static Response.Listener<JSONObject> _listenerObject;
    public static Response.Listener<JSONArray> _listenerArrar;
    public static Response.ErrorListener _errListener;
    public VolleyInterface(Context context, Response.Listener<String> listener, Response.ErrorListener errListener)
    {
        this._context=context;
        this._listener=listener;
        this._errListener=errListener;
        this._listenerArrar=null;
        this._listenerObject=null;
    }

//    public VolleyInterface(Context context, Response.Listener<JSONObject> listenerObj, Response.ErrorListener errListener,JSONObject obj)
//    {
//        this._context=context;
//        this._listenerObject=listenerObj;
//        this._errListener=errListener;
//    }

    //请求时候的回调方法
    public abstract void onMySuccess(String result);
    public abstract void onMySuccessObject(JSONObject object);
    public abstract void onMySuccessArry(JSONArray arrar);
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

    public Response.Listener<JSONArray> iListenerArrary()
    {
        this._listenerArrar=new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                onMySuccessArry(jsonArray);
            }
        };
        return  _listenerArrar;
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
