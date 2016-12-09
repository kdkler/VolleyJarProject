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

    public static void RequestStringGet(Context context, String url, String tag, VolleyIntfcString intface)
    {
        SelfApplication.getHttpQueues().cancelAll(tag);//关闭原来的tag请求,节省资源
        strRequest=new StringRequest(Request.Method.GET,url,intface.iListener(),intface.iErrListener());
        strRequest.setTag(tag);
        SelfApplication.getHttpQueues().add(strRequest);
        SelfApplication.getHttpQueues().start();
    }

    public static void RequestStringPost(Context context, String url, String tag,final Map<String,String> params, VolleyIntfcString intface)
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


    public static void RequestJsonObjGet(Context context,String url ,String tag,VolleyIntfcObject intface)
    {
        SelfApplication.getHttpQueues().cancelAll(tag);
        objectRequest=new JsonObjectRequest(Request.Method.GET,url, null,intface.iListenerObject(),intface.iErrListener());
        objectRequest.setTag(tag);
        SelfApplication.getHttpQueues().add(objectRequest);
        SelfApplication.getHttpQueues().start();
    }
    public static void RequestJsonObjGet(Context context,String url ,String tag,JSONObject jsonRequest,VolleyIntfcObject intface)
    {
        SelfApplication.getHttpQueues().cancelAll(tag);
        objectRequest=new JsonObjectRequest(Request.Method.GET,url, jsonRequest,intface.iListenerObject(),intface.iErrListener());
        objectRequest.setTag(tag);
        SelfApplication.getHttpQueues().add(objectRequest);
        SelfApplication.getHttpQueues().start();
    }
    public static void RequestJsonObjPost(Context context,String url,String tag,JSONObject jsonRequest,VolleyIntfcObject intface)
    {
        SelfApplication.getHttpQueues().cancelAll(tag);
        objectRequest=new JsonObjectRequest(Request.Method.POST,url, jsonRequest,intface.iListenerObject(),intface.iErrListener()){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                return params;
//            }
            //使用StringRequest的时候需要重新getParams()方法,此时不需要重写
            //已经经过方法的测试,这个点需要注意
        };
        objectRequest.setTag(tag);
        SelfApplication.getHttpQueues().add(objectRequest);
        SelfApplication.getHttpQueues().start();
    }
    public static void RequestJsonArrayGet(Context context,String url ,String tag,VolleyIntfcArray intface)
    {
        arrarRequest=new JsonArrayRequest(url,intface.iListenerArrary(),intface.iErrListener());
        arrarRequest.setTag(tag);

        SelfApplication.getHttpQueues().add(arrarRequest);
        SelfApplication.getHttpQueues().start();
    }
}
