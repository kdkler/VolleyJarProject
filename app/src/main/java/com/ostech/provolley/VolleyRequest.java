package com.ostech.provolley;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by yoon on 2016/12/7.
 */

public class VolleyRequest {

    public static StringRequest strRequest;
    public static JsonObjectRequest objectRequest;
    public  static JsonArrayRequest arrarRequest;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public static Context _context;

    public static void RequestStringGet( Context context,String url,String tag,VolleyInterface intface)
    {
        SelfApplication.getHttpQueues().cancelAll(tag);//关闭原来的tag请求,节省资源
        strRequest=new StringRequest(Request.Method.GET,url,intface.iListener(),intface.iErrListener());
        strRequest.setTag(tag);
        SelfApplication.getHttpQueues().add(strRequest);
        SelfApplication.getHttpQueues().start();
    }

    public static void RequestStringPost(Context context, String url, String tag, VolleyInterface intface, final Map<String,String> params)
    {
        SelfApplication.getHttpQueues().cancelAll(tag);
        strRequest=new StringRequest(Request.Method.POST,url,intface.iListener(),intface.iErrListener()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        strRequest.setTag(tag);
        SelfApplication.getHttpQueues().add(strRequest);
        SelfApplication.getHttpQueues().start();
    }

    public static void RequestJsonObjGet(Context context,String url ,String tag,JSONObject jsonRequest,VolleyInterface inface)
    {
        SelfApplication.getHttpQueues().cancelAll(tag);
        objectRequest=new JsonObjectRequest(Request.Method.GET,url, jsonRequest,inface.iListenerObject(),inface.iErrListener());
        strRequest.setTag(tag);
        SelfApplication.getHttpQueues().add(strRequest);
        SelfApplication.getHttpQueues().start();
    }
    public static void RequestJsonObjPost(Context context,String url,String tag,JSONObject jsonRequest,VolleyInterface inface, final Map<String,String> params)
    {
        SelfApplication.getHttpQueues().cancelAll(tag);
        objectRequest=new JsonObjectRequest(Request.Method.POST,url, jsonRequest,inface.iListenerObject(),inface.iErrListener()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        strRequest.setTag(tag);
        SelfApplication.getHttpQueues().add(strRequest);
        SelfApplication.getHttpQueues().start();
    }
    public static void RequestJsonArrayGet(Context context,String url ,String tag,VolleyInterface inface)
    {
        arrarRequest=new JsonArrayRequest(url,inface.iListenerArrary(),inface.iErrListener());
        strRequest.setTag(tag);
        SelfApplication.getHttpQueues().add(strRequest);
        SelfApplication.getHttpQueues().start();
        strRequest.setTag(tag);
        SelfApplication.getHttpQueues().add(strRequest);
        SelfApplication.getHttpQueues().start();
    }
}
