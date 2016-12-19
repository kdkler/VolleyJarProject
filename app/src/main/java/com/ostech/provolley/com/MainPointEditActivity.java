
package com.selftech.timeline;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainPointEditActivity extends AppCompatActivity {

    Button btnSave;
    EditText etPointLoac;
    private Intent intent=new Intent();
    private boolean isFromIntent=true;
    private static final String TAG = "MainPointEditActivity";
    public String strLineID=java.util.UUID.randomUUID().toString().replace("-","");



    String serviceString = Context.LOCATION_SERVICE;// 获取的是位置服务




    Handler handler=new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GetWebApiPostJsonData.SEND_SUCCESS:
                    //Toast.makeText(MainPointEditActivity.this, "提交成功",Toast.LENGTH_SHORT).show();

                    Intent intt=new Intent();
                    intt.putExtra("lineid", String.valueOf(strLineID));
                    intt.setClass(MainPointEditActivity.this, MainPointListActivity.class);//
                    MainPointEditActivity.this.startActivity(intt);
                    finish();

                    break;
                case GetWebApiPostJsonData.SEND_FAIL:
                    Toast.makeText(MainPointEditActivity.this, "提交失败", Toast.LENGTH_SHORT).show();
                    break;

                default:
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_point_edit);
        intent=getIntent();
        if(intent.getStringExtra("lineid")==""||intent.getStringExtra("lineid")==null)
        {
            isFromIntent=false;
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle(R.string.title_activity_main_point_edit);//设置title标题
        //toolbar.setLogo(R.mipmap.ic_launcher);//设置logo
        //toolbar.setSubtitle(R.string.title_activity_main_point_list);//设置副标题
        setSupportActionBar(toolbar);
        //设置返回按钮
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (isFromIntent) {
//                    Intent intt=new Intent();
//                    intt.putExtra("lineid", intent.getStringExtra("lineid"));
//                    intt.setClass(MainPointEditActivity.this, MainPointListActivity.class);//
//                    MainPointEditActivity.this.startActivity(intt);
//                    Log.d(TAG, "onClick: intent tiao zhuan");
//                    finish();
//                    Log.d(TAG, "onClick: finish fan hui");
//                }
//                else {
//                    finish();//返回按钮事件
//                }
                finish();//返回按钮事件
            }
        });
        if (isFromIntent) {
            strLineID = intent.getStringExtra("lineid");
        }

        btnSave = (Button)findViewById(R.id.btnSavePoint);
        etPointLoac = (EditText) findViewById(R.id.etPointLoac);
        btnSave.setOnClickListener(new ButtonClickListener());


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private class ButtonClickListener implements View.OnClickListener{

        public void onClick(View v) {
            switch(v.getId())
            {
                case R.id.btnSavePoint:
                    Log.d(TAG, "onClick: 点击了保存按钮");

                    String name=etPointLoac.getText().toString();
                    String pwd=etPointLoac.getText().toString();
                    if (name.equals("")) {
                        //Toast.makeText(MainEditActivity.this, "", Toast.LENGTH_LONG).show();
                        Snackbar.make(v, "地点不能为空诶。。。", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }else {
                        String url="http://www.selftech.cn/api/timepoint/TimePointAdd";
                        Map<String,String> map =new HashMap<String,String>();
                        map.put("PointLocation",name);
                        map.put("LineType","1");
                        map.put("PointRemark",name+name+"备注啊备注");
                        map.put("TimeLineID",strLineID);
                        LocationManager locationManager = (LocationManager) getSystemService(serviceString);// 调用getSystemService()方法来获取LocationManager对象
                        String provider = locationManager.NETWORK_PROVIDER;
                        provider = locationManager.GPS_PROVIDER;
                        Location locn = CommonHelper.getLocation(MainPointEditActivity.this,locationManager,provider);
                        if(locn!=null)
                        {
                            map.put("Longitude",locn.getLongitude()+"");//经度
                            map.put("Latitude",locn.getLatitude()+"");//纬度
                        }
                        new GetWebApiPostJsonData(handler).PostDataToServer(map,url);
                    }
                    break;
            }

        }
    }

}
