package com.ostech.provolley;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;

/**
 * Created by yoon on 2016/12/8.
 */

public abstract class VolleyIntfcArray {

    public Context _context;
    public static Response.Listener<JSONArray> _listenerArrar;
    public static Response.ErrorListener _errListener;
    public VolleyIntfcArray(Context context, Response.Listener<JSONArray> _listenerArrar, Response.ErrorListener errListener)
    {
        this._context=context;
        this._errListener=errListener;
        this._listenerArrar=_listenerArrar;
    }



    //请求时候的回调方法

    public abstract void onMySuccessArry(JSONArray arrar);
    public abstract void onMyError(VolleyError error);


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
