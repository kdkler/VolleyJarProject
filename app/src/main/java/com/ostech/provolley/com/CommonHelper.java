package com.selftech.timeline;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yoon on 2016/9/25.
 */
public class CommonHelper {


    private static final String TAG = "CommonHelper";
    public static Context contex;
    private static final String urlAddUser="http://www.selftech.cn/api/AppUser/AppUserAdd";
    Handler comhandler=new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GetWebApiPostJsonData.SEND_SUCCESS:
                    Log.d(TAG, "handleMessage: 提交成功");

                    break;
                case GetWebApiPostJsonData.SEND_FAIL:
                    Log.d(TAG, "handleMessage: 提交失败");
                    break;

                default:
                    break;
            }
        };
    };


    
    
    public static String readStream(InputStream is)
    {
        InputStreamReader isr;
        String result="";
        try
        {
            String line="";
            isr=new InputStreamReader(is,"utf-8");
            BufferedReader br=new BufferedReader(isr);
            while((line=br.readLine())!=null)
            {
                result+=line;
            }
        }
        catch(UnsupportedEncodingException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  result;
    }

    //获取时间线列表
    public static List<EntityTimeLine> getJsonTimeLine(String strUrl) {
        List<EntityTimeLine> lineList=new ArrayList<>();
        try {
            String jsonString = CommonHelper.readStream(new URL(strUrl).openStream());
            Log.d(TAG, "getJsonTimeLine: 获取到的json数据是" + jsonString);
            JSONObject jsonobj;
            JSONArray jsonarr;
            EntityTimeLine enTimeline;
            try{
                Thread.sleep(0);
                //jsonobj=new JSONObject(jsonString);
                jsonarr=new JSONArray(jsonString);
                for (int i = 0; i <jsonarr.length() ; i++) {

                    jsonobj=jsonarr.getJSONObject(i);
                    enTimeline=new EntityTimeLine();
                    enTimeline.LineTitle=jsonobj.getString("LineTitle");
                    enTimeline.CreateTime=jsonobj.getString("CreateTime").substring(0,10).replace("T"," ");
                    enTimeline.TimeLineID=jsonobj.getString("TimeLineID");
                    enTimeline.LineRemark=jsonobj.getString("LineRemark");
                    lineList.add(enTimeline);
                }
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        Log.d(TAG, "getJsonTimeLine:获取到的数据 "+lineList);
        return  lineList;
    }
    //获取时间点列表
    public static List<EntityTimePoint> getJsonTimePoint(String strUrl) {
        List<EntityTimePoint> lineList=new ArrayList<>();
        try {
            String jsonString = CommonHelper.readStream(new URL(strUrl).openStream());
            Log.d(TAG, "getJsonTimePoint: 获取到的json数据是" + jsonString);
            JSONObject jsonobj;
            JSONArray jsonarr;
            EntityTimePoint enTimeline;
            try{
                Thread.sleep(0);
                //jsonobj=new JSONObject(jsonString);
                jsonarr=new JSONArray(jsonString);
                for (int i = 0; i <jsonarr.length() ; i++) {

                    jsonobj=jsonarr.getJSONObject(i);
                    enTimeline=new EntityTimePoint();
                    enTimeline.PointLocation=jsonobj.getString("PointLocation");
                    enTimeline.TimeLineID=jsonobj.getString("TimeLineID");

                    enTimeline.PointRemark=jsonobj.getString("PointRemark");
                    enTimeline.CreateTime=jsonobj.getString("CreateTime").substring(10,19).replace("T"," ");
                    lineList.add(enTimeline);
                }
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        Log.d(TAG, "getJsonTimeLine:获取到的数据 "+lineList);
        return  lineList;
    }


    public static EntityAppUser getJsonWbUserInfo(String Token)
    {
        String UName="";
        String strUrl="https://api.weibo.com/2/users/show.json?access_token=";
        strUrl+=Token;
        //strUrl="https://api.weibo.com/2/users/show.json?access_token=2.00JzGTPC0yI23v639da56a2dE55UtC&uid=2060849071";
        EntityAppUser entity=new EntityAppUser();

        try {
            String jsonString = CommonHelper.readStream(new URL(strUrl).openStream());
            Log.d(TAG, "getJsonTimePoint: 获取到的json数据是" + jsonString);
            JSONObject jsonobj;
            JSONArray jsonarr;

            try{
                Thread.sleep(0);
                jsonobj=new JSONObject(jsonString);
                entity.UserName  =jsonobj.getString("screen_name");
                entity.RegKey  =jsonobj.getString("idstr");
                //entity.Birthday  =jsonobj.getString("screen_name");
                //entity.EMail  =jsonobj.getString("screen_name");
                //entity.FaceUrlID  =jsonobj.getString("screen_name");
                entity.PhoneNo  ="";
                entity.RegFrom  ="weibo";
                entity.RegName  =jsonobj.getString("screen_name");
                Log.d(TAG, "getJsonWbUserInfo: 获得是用户是@"+entity.RegName);
                entity.Signature  =jsonobj.getString("description");
                String gender=jsonobj.getString("gender").toLowerCase();
                if(gender.equals("m"))
                {
                    entity.UserGender="1";

                }
                else if(gender.equals("f"))
                {
                    entity.UserGender="2";

                }
                else
                {
                    entity.UserGender="0";
                }

                String tag="selfstrpost";
                Map<String,String> map =new HashMap<String,String>();
                map.put("RegKey",entity.RegKey);
                map.put("UserName",entity.UserName);
                map.put("Signature",entity.Signature);
                map.put("RegFrom",entity.RegFrom);
                map.put("RegName",entity.RegName);
                map.put("UserGender",entity.UserGender);









                //entity.UserGender  = gender=="m"?2:gender=="f"?1:0;
            }
            catch(JSONException e)
            {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return  entity;
    }



    class AsyncTaskGetUserInfo extends AsyncTask<String, Void, EntityAppUser> {
        @Override
        protected EntityAppUser doInBackground(String... params) {
            Log.d(TAG, "doInBackground: 2");
            return CommonHelper.getJsonWbUserInfo(params[0]);
        }

        @Override
        protected void onPostExecute(EntityAppUser entity) {
            super.onPostExecute(entity);
            //执行成功以后


            if(entity.RegKey!=null)
            {

            }
            else
            {

            }


            Log.d(TAG, "doInBackground: 3");
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "doInBackground: 1");
            //progBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            Log.d(TAG, "doInBackground: 44");
        }
    }


    public static boolean getIsHaveThisUser(String strRegKey)
    {
        boolean isHave=false;
        String strUrl="http://selftech.cn/api/appuser/AppUserIsExit?regKey="+strRegKey;

        try{
            String jsonString = CommonHelper.readStream(new URL(strUrl).openStream());
            JSONObject jsonobj;
            try {
                jsonobj = new JSONObject(jsonString);

                return true;
            }
            catch (JSONException je)
            {
                je.printStackTrace();
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return  isHave;
    }

//判断网络连接状态
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
        } else {
            //如果仅仅是用来判断网络连接
            //则可以使用 cm.getActiveNetworkInfo().isAvailable();

            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }




    public static Location getLocation(Context context,LocationManager locationManager,String provider)
    {
        Location locn=null;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "getLocation: 用户未开启定位权限");
            //Toast.makeText(context,"您没有开启定位权限,建议开启,谢谢~",Toast.LENGTH_LONG).show();
            //return;
        }
        else
        {
            Log.d(TAG, "getLocation: 开启了定位权限");
            locn = locationManager.getLastKnownLocation(provider);// 调用getLastKnownLocation()方法获取当前的位置信息
//            locn.getLatitude();//获取纬度
//            locn.getLongitude();//获取经度

        }
        return  locn;
    }

    public static void getIconDraw()
    {
        ImageView image=null;
        image.setImageResource(R.drawable.ic_arrow_back_black_24dp);
        image.setImageResource(R.drawable.ic_bookmark_black_24dp);
        image.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
        image.setImageResource(R.drawable.ic_border_color_black_24dp);
        image.setImageResource(R.drawable.ic_check_black_24dp);
        image.setImageResource(R.drawable.ic_chevron_left_black_24dp);
        image.setImageResource(R.drawable.ic_create_black_24dp);
        image.setImageResource(R.drawable.ic_edit_black_24dp);
        image.setImageResource(R.drawable.ic_favorite_black_24dp);
        image.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        image.setImageResource(R.drawable.ic_add_red_24dp);
        image.setImageResource(R.drawable.ic_item_list_red_24dp);
        image.setImageResource(R.drawable.ic_item_list_all_black_24dp);
        image.setImageResource(R.drawable.ic_item_detail_black_24dp);
        image.setImageResource(R.drawable.ic_menu_camera);
        image.setImageResource(R.drawable.ic_menu_gallery);
        image.setImageResource(R.drawable.ic_menu_manage);
        image.setImageResource(R.drawable.ic_menu_send);
        image.setImageResource(R.drawable.ic_menu_share);
        image.setImageResource(R.drawable.ic_menu_slideshow);
        image.setImageResource(R.drawable.ic_pin_drop_black_24dp);
        image.setImageResource(R.drawable.ic_place_black_24dp);
        image.setImageResource(R.drawable.ic_menu_camera);




    }
}



