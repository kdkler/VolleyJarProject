package com.ostech.provolley;

import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by yoon on 2016/12/5.
 */

public class CommonHelper {

    public static void Volley_get(String url, String ReqMethod, final TextView tvID) {

        JsonObjectRequest req=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
           String res="";
            @Override
            public void onResponse(JSONObject jsonObject) {
                //执行成功的回调
                Log.d("tag", "onResponse: 成功获取");
                try
                {
                    res =jsonObject.getString("TimeLineID");

                    Log.d("TAG", "onResponse: "+jsonObject.getString("TimeLineID"));
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }

        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("tag", "onResponse: 失败获取");
                Log.d("TAG", "onErrorResponse: "+volleyError.getMessage());
                //执行失败的回调
                tvID.setText("获取失败");
            }
        });
        req.setTag("get timelinelist");

        VoleyApp.getHttpQueues().add(req);

    }


    public void Volley_geti(String url,String tag) {


//        VolleyRequest.RequestStringGet(this, url, tag, new VolleyInterface(this,VolleyInterface._listener,VolleyInterface._errListener) {
//            @Override
//            public void onMySuccess(String result) {
//
//            }
//
//            @Override
//            public void onMyError(VolleyError error) {
//
//            }
//        });

        //JsonObjectRequest objectRequest=new JsonObjectRequest(Request.Method.GET,"url",)

    }
}
